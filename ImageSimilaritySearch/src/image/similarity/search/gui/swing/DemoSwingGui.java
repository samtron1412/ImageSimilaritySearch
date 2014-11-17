package image.similarity.search.gui.swing;

import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;
import image.similarity.search.compare.DynamicTimeWarping;
import image.similarity.search.contour.Contour;
import image.similarity.search.timeseries.RadicalScanning;
import image.similarity.search.timeseries.ShowLineChart;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.JScrollPane;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.CanvasFrame;
import org.jfree.ui.RefineryUtilities;

@SuppressWarnings("serial")
public class DemoSwingGui extends JFrame {

  private JPanel contentPane;
  protected JButton btnChooseFirstImage;
  protected JButton btnChooseSecondImage;
  protected JScrollPane scrollPaneShowFirstImage;
  protected JScrollPane scrollPaneShowSecondImage;
  protected JLabel lblShowFirstImage;
  protected JLabel lblShowSecondImage;
  protected JButton btnCompare;
  protected File firstFile;
  protected File secondFile;
  protected JButton btnShowFirstContour;
  protected JButton btnShowTimeSeries;
  protected JButton btnShowSecondContour;
  protected JLabel lblShowDistance;
  protected JLabel lblDistance;
  protected JLabel lblCompareResult;
  protected JLabel lblShowPercent;
  protected float[] firstTimeSeries;
  protected float[] secondTimeSeries;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          /**
           * ADD NIMBUS LOOK AND FEEL
           */
          for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
              UIManager.setLookAndFeel(info.getClassName());
              break;
            }
          }

          /**
           * INIT FRAME AND SET IT VISIBLE
           */
          DemoSwingGui frame = new DemoSwingGui();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public DemoSwingGui() {
    initGUI();
  }

  private void initGUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 790, 600);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.columnWidths = new int[] { 30, 175, 175, 30, 175, 175, 30,
        0 };
    gbl_contentPane.rowHeights = new int[] { 30, 0, 30, 0, 30, 21, 30, 30, 0 };
    gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 1.0,
        1.0, 0.0, Double.MIN_VALUE };
    gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
        0.0, 0.0, Double.MIN_VALUE };
    contentPane.setLayout(gbl_contentPane);

    btnChooseFirstImage = new JButton("Choose First Image");
    btnChooseFirstImage
        .addMouseListener(new BtnChooseFirstImageMouseListener());
    GridBagConstraints gbcBtnChooseFirstImage = new GridBagConstraints();
    gbcBtnChooseFirstImage.insets = new Insets(0, 0, 5, 5);
    gbcBtnChooseFirstImage.gridx = 1;
    gbcBtnChooseFirstImage.gridy = 1;
    contentPane.add(btnChooseFirstImage, gbcBtnChooseFirstImage);

    btnChooseSecondImage = new JButton("Choose Second Image");
    btnChooseSecondImage
        .addMouseListener(new BtnChooseSecondImageMouseListener());

    btnShowFirstContour = new JButton("Show First Contour");
    btnShowFirstContour.setEnabled(false);
    btnShowFirstContour
        .addMouseListener(new BtnShowFirstContourMouseListener());
    GridBagConstraints gbcBtnShowFirstContour = new GridBagConstraints();
    gbcBtnShowFirstContour.insets = new Insets(0, 0, 5, 5);
    gbcBtnShowFirstContour.gridx = 2;
    gbcBtnShowFirstContour.gridy = 1;
    contentPane.add(btnShowFirstContour, gbcBtnShowFirstContour);
    GridBagConstraints gbcBtnChooseSecondImage = new GridBagConstraints();
    gbcBtnChooseSecondImage.insets = new Insets(0, 0, 5, 5);
    gbcBtnChooseSecondImage.gridx = 4;
    gbcBtnChooseSecondImage.gridy = 1;
    contentPane.add(btnChooseSecondImage, gbcBtnChooseSecondImage);

    btnShowSecondContour = new JButton("Show Second Contour");
    btnShowSecondContour.setEnabled(false);
    btnShowSecondContour
        .addMouseListener(new BtnShowSecondContourMouseListener());
    GridBagConstraints gbcBtnShowSecondContour = new GridBagConstraints();
    gbcBtnShowSecondContour.insets = new Insets(0, 0, 5, 5);
    gbcBtnShowSecondContour.gridx = 5;
    gbcBtnShowSecondContour.gridy = 1;
    contentPane.add(btnShowSecondContour, gbcBtnShowSecondContour);

    scrollPaneShowFirstImage = new JScrollPane();
    GridBagConstraints gbcScrollPaneShowFirstImage = new GridBagConstraints();
    gbcScrollPaneShowFirstImage.insets = new Insets(0, 0, 5, 5);
    gbcScrollPaneShowFirstImage.gridwidth = 2;
    gbcScrollPaneShowFirstImage.fill = GridBagConstraints.BOTH;
    gbcScrollPaneShowFirstImage.gridx = 1;
    gbcScrollPaneShowFirstImage.gridy = 3;
    contentPane.add(scrollPaneShowFirstImage, gbcScrollPaneShowFirstImage);

    lblShowFirstImage = new JLabel("");
    lblShowFirstImage.addMouseListener(new LblShowFirstImageMouseListener());
    scrollPaneShowFirstImage.setViewportView(lblShowFirstImage);

    scrollPaneShowSecondImage = new JScrollPane();
    GridBagConstraints gbcScrollPaneShowSecondImage = new GridBagConstraints();
    gbcScrollPaneShowSecondImage.insets = new Insets(0, 0, 5, 5);
    gbcScrollPaneShowSecondImage.gridwidth = 2;
    gbcScrollPaneShowSecondImage.fill = GridBagConstraints.BOTH;
    gbcScrollPaneShowSecondImage.gridx = 4;
    gbcScrollPaneShowSecondImage.gridy = 3;
    contentPane.add(scrollPaneShowSecondImage, gbcScrollPaneShowSecondImage);

    lblShowSecondImage = new JLabel("");
    lblShowSecondImage.addMouseListener(new LblShowSecondImageMouseListener());
    scrollPaneShowSecondImage.setViewportView(lblShowSecondImage);

    lblDistance = new JLabel("Distance Between Images: ");
    GridBagConstraints gbcLblDistance = new GridBagConstraints();
    gbcLblDistance.anchor = GridBagConstraints.EAST;
    gbcLblDistance.insets = new Insets(0, 0, 5, 5);
    gbcLblDistance.gridx = 1;
    gbcLblDistance.gridy = 5;
    contentPane.add(lblDistance, gbcLblDistance);

    lblShowDistance = new JLabel("0");
    GridBagConstraints gbcLblShowDistance = new GridBagConstraints();
    gbcLblShowDistance.anchor = GridBagConstraints.WEST;
    gbcLblShowDistance.insets = new Insets(0, 0, 5, 5);
    gbcLblShowDistance.gridx = 2;
    gbcLblShowDistance.gridy = 5;
    contentPane.add(lblShowDistance, gbcLblShowDistance);

    btnCompare = new JButton("Compare");
    btnCompare.addMouseListener(new BtnCompareMouseListener());
    GridBagConstraints gbcBtnCompare = new GridBagConstraints();
    gbcBtnCompare.insets = new Insets(0, 0, 5, 5);
    gbcBtnCompare.gridx = 5;
    gbcBtnCompare.gridy = 5;
    contentPane.add(btnCompare, gbcBtnCompare);

    lblCompareResult = new JLabel("Compare Result: ");
    GridBagConstraints gbcLblCompareResult = new GridBagConstraints();
    gbcLblCompareResult.anchor = GridBagConstraints.EAST;
    gbcLblCompareResult.insets = new Insets(0, 0, 5, 5);
    gbcLblCompareResult.gridx = 1;
    gbcLblCompareResult.gridy = 6;
    contentPane.add(lblCompareResult, gbcLblCompareResult);

    lblShowPercent = new JLabel("0 %");
    GridBagConstraints gbcLblShowPercent = new GridBagConstraints();
    gbcLblShowPercent.anchor = GridBagConstraints.WEST;
    gbcLblShowPercent.insets = new Insets(0, 0, 5, 5);
    gbcLblShowPercent.gridx = 2;
    gbcLblShowPercent.gridy = 6;
    contentPane.add(lblShowPercent, gbcLblShowPercent);

    btnShowTimeSeries = new JButton("Show Time Series");
    btnShowTimeSeries.setEnabled(false);
    btnShowTimeSeries.addMouseListener(new BtnShowTimeSeriesMouseListener());
    GridBagConstraints gbcBtnShowTimeSeries = new GridBagConstraints();
    gbcBtnShowTimeSeries.insets = new Insets(0, 0, 5, 5);
    gbcBtnShowTimeSeries.gridx = 5;
    gbcBtnShowTimeSeries.gridy = 6;
    contentPane.add(btnShowTimeSeries, gbcBtnShowTimeSeries);
  }

  private class BtnChooseFirstImageMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent chooseFirstImageEvent) {
      JFileChooser firstFileChooser = new JFileChooser();
      int returnVal = firstFileChooser.showOpenDialog(btnChooseFirstImage);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        firstFile = firstFileChooser.getSelectedFile();
        try {
          BufferedImage firstImage = ImageIO
              .read(new File(firstFile.getPath()));
          lblShowFirstImage.setIcon(new ImageIcon(firstImage));
          scrollPaneShowFirstImage.setViewportView(lblShowFirstImage);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private class BtnChooseSecondImageMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent chooseSecondImageEvent) {
      JFileChooser secondFileChooser = new JFileChooser();
      int returnVal = secondFileChooser.showOpenDialog(lblShowSecondImage);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        secondFile = secondFileChooser.getSelectedFile();
        try {
          BufferedImage secondImage = ImageIO.read(new File(secondFile
              .getPath()));
          lblShowSecondImage.setIcon(new ImageIcon(secondImage));
          scrollPaneShowSecondImage.setViewportView(lblShowSecondImage);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private class LblShowFirstImageMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent showFirstImageEvent) {
      if (showFirstImageEvent.getClickCount() % 2 == 0) {
        if (firstFile != null) {
          final IplImage firstIplImage = cvLoadImage(firstFile.getPath());
          final CanvasFrame firstCanvas = new CanvasFrame("First Image", 1);
          // firstCanvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
          firstCanvas.showImage(firstIplImage);
        }
      }
    }
  }

  private class LblShowSecondImageMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent showSecondImageEvent) {
      if (showSecondImageEvent.getClickCount() % 2 == 0) {
        if (secondFile != null) {
          final IplImage secondIplImage = cvLoadImage(secondFile.getPath());
          final CanvasFrame secondCanvas = new CanvasFrame("Second Image", 1);
          // firstCanvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
          secondCanvas.showImage(secondIplImage);
        }
      }
    }
  }

  private class BtnCompareMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent compareEvent) {
      if (firstFile != null && secondFile != null) {
        Map<String, Object> firstContourMap = Contour.imageToContour(firstFile
            .getPath());
        firstTimeSeries = RadicalScanning.contourToTimeSeries(firstContourMap);

        Map<String, Object> secondContourMap = Contour
            .imageToContour(secondFile.getPath());
        secondTimeSeries = RadicalScanning
            .contourToTimeSeries(secondContourMap);

        DynamicTimeWarping dtw = new DynamicTimeWarping(firstTimeSeries,
            secondTimeSeries);
        lblShowDistance.setText(Double.toString(dtw.getDistance()));
        lblShowDistance.setText(Double.toString(dtw.getDistance()));
        btnShowFirstContour.setVisible(true);
        btnShowSecondContour.setVisible(true);
        btnShowTimeSeries.setEnabled(true);
      } else {
        Frame frame = new Frame();
        JOptionPane.showMessageDialog(frame,
            "Please choose two images before compare.", "Something Error",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  private class BtnShowFirstContourMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent showFirstContourEvent) {
      if (firstFile != null) {
        File firstContourFile = new File(firstFile.getPath());
        String firstContourPath = "Images\\dst\\" + firstContourFile.getName();
        final IplImage firstContourIplImage = cvLoadImage(firstContourPath);
        final CanvasFrame firstContourCanvas = new CanvasFrame(
            "First contour image", 1);
        firstContourCanvas.showImage(firstContourIplImage);
      }
    }
  }

  private class BtnShowSecondContourMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent showSecondContourEvent) {
      if (secondFile != null) {
        File secondContourFile = new File(secondFile.getPath());
        String secondContourPath = "Images\\dst\\"
            + secondContourFile.getName();
        final IplImage secondContourIplImage = cvLoadImage(secondContourPath);
        final CanvasFrame secondContourCanvas = new CanvasFrame(
            "Second contour image", 1);
        secondContourCanvas.showImage(secondContourIplImage);
      }
    }
  }

  private class BtnShowTimeSeriesMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent showSecondTimeSeries) {
      ShowLineChart chart2 = new ShowLineChart("Second Time Series",
          "Time Series", secondTimeSeries);
      chart2.pack();
      RefineryUtilities.centerFrameOnScreen(chart2);
      chart2.setVisible(true);
    }
  }
}
