package image.similarity.search.gui.swing;

import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JScrollPane;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.CanvasFrame;

@SuppressWarnings("serial")
public class DemoSwingGui extends JFrame {

  private JPanel contentPane;
  protected JButton btnChooseFirstImage;
  protected JButton btnChooseSecondImage;
  protected JScrollPane scrollPaneShowFirstImage;
  protected JScrollPane scrollPaneShowSecondImage;
  protected JLabel lblShowFirstImage;
  protected JLabel lblShowSecondImage;
  protected JLabel lblCompareResult;
  protected JLabel lblDistanceBetweenImages;
  protected JButton btnCompare;
  protected JLabel lblShowResult;
  protected JLabel lblShowDistance;
  protected File firstFile;
  protected File secondFile;

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
    setBounds(100, 100, 700, 500);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.columnWidths = new int[] { 30, 172, 100, 30, 272, 30, 0 };
    gbl_contentPane.rowHeights = new int[] { 30, 0, 30, 0, 30, 30, 30, 30, 0 };
    gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0,
        0.0, Double.MIN_VALUE };
    gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
        0.0, 0.0, Double.MIN_VALUE };
    contentPane.setLayout(gbl_contentPane);

    btnChooseFirstImage = new JButton("Choose First Image");
    btnChooseFirstImage
        .addMouseListener(new BtnChooseFirstImageMouseListener());
    GridBagConstraints gbcBtnChooseFirstImage = new GridBagConstraints();
    gbcBtnChooseFirstImage.gridwidth = 2;
    gbcBtnChooseFirstImage.insets = new Insets(0, 0, 5, 5);
    gbcBtnChooseFirstImage.gridx = 1;
    gbcBtnChooseFirstImage.gridy = 1;
    contentPane.add(btnChooseFirstImage, gbcBtnChooseFirstImage);

    btnChooseSecondImage = new JButton("Choose Second Image");
    btnChooseSecondImage
        .addMouseListener(new BtnChooseSecondImageMouseListener());
    GridBagConstraints gbcBtnChooseSecondImage = new GridBagConstraints();
    gbcBtnChooseSecondImage.insets = new Insets(0, 0, 5, 5);
    gbcBtnChooseSecondImage.gridx = 4;
    gbcBtnChooseSecondImage.gridy = 1;
    contentPane.add(btnChooseSecondImage, gbcBtnChooseSecondImage);

    scrollPaneShowFirstImage = new JScrollPane();
    GridBagConstraints gbcScrollPaneShowFirstImage = new GridBagConstraints();
    gbcScrollPaneShowFirstImage.gridwidth = 2;
    gbcScrollPaneShowFirstImage.insets = new Insets(0, 0, 5, 5);
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
    gbcScrollPaneShowSecondImage.fill = GridBagConstraints.BOTH;
    gbcScrollPaneShowSecondImage.gridx = 4;
    gbcScrollPaneShowSecondImage.gridy = 3;
    contentPane.add(scrollPaneShowSecondImage, gbcScrollPaneShowSecondImage);

    lblShowSecondImage = new JLabel("");
    lblShowSecondImage.addMouseListener(new LblShowSecondImageMouseListener());
    scrollPaneShowSecondImage.setViewportView(lblShowSecondImage);

    lblCompareResult = new JLabel("Compare Result: ");
    GridBagConstraints gbcLblCompareResult = new GridBagConstraints();
    gbcLblCompareResult.anchor = GridBagConstraints.EAST;
    gbcLblCompareResult.insets = new Insets(0, 0, 5, 5);
    gbcLblCompareResult.gridx = 1;
    gbcLblCompareResult.gridy = 5;
    contentPane.add(lblCompareResult, gbcLblCompareResult);

    lblShowResult = new JLabel("0 %");
    GridBagConstraints gbcLblShowResult = new GridBagConstraints();
    gbcLblShowResult.anchor = GridBagConstraints.WEST;
    gbcLblShowResult.insets = new Insets(0, 0, 5, 5);
    gbcLblShowResult.gridx = 2;
    gbcLblShowResult.gridy = 5;
    contentPane.add(lblShowResult, gbcLblShowResult);

    btnCompare = new JButton("Compare");
    GridBagConstraints gbcBtnCompare = new GridBagConstraints();
    gbcBtnCompare.insets = new Insets(0, 0, 5, 5);
    gbcBtnCompare.gridx = 4;
    gbcBtnCompare.gridy = 5;
    contentPane.add(btnCompare, gbcBtnCompare);

    lblDistanceBetweenImages = new JLabel("Distance Between Images: ");
    GridBagConstraints gbcLblDistanceBetweenImages = new GridBagConstraints();
    gbcLblDistanceBetweenImages.anchor = GridBagConstraints.EAST;
    gbcLblDistanceBetweenImages.insets = new Insets(0, 0, 5, 5);
    gbcLblDistanceBetweenImages.gridx = 1;
    gbcLblDistanceBetweenImages.gridy = 6;
    contentPane.add(lblDistanceBetweenImages, gbcLblDistanceBetweenImages);

    lblShowDistance = new JLabel("0");
    GridBagConstraints gbcLblShowDistance = new GridBagConstraints();
    gbcLblShowDistance.anchor = GridBagConstraints.WEST;
    gbcLblShowDistance.insets = new Insets(0, 0, 5, 5);
    gbcLblShowDistance.gridx = 2;
    gbcLblShowDistance.gridy = 6;
    contentPane.add(lblShowDistance, gbcLblShowDistance);
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
}
