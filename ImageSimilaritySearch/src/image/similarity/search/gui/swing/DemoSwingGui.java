package image.similarity.search.gui.swing;

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

@SuppressWarnings("serial")
public class DemoSwingGui extends JFrame {

  private JPanel contentPane;
  private JButton btnChooseFirstImage;
  private JButton btnChooseSecondImage;
  private JLabel lblCompareResult;
  private JLabel lblDistanceBetweenImages;
  private JButton btnCompare;
  private JLabel lblShowResult;
  private JLabel lblShowDistance;
  private JScrollPane scrollPaneShowFirstImage;
  private JScrollPane scrollPaneShowSecondImage;

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
    GridBagConstraints gbcBtnChooseFirstImage = new GridBagConstraints();
    gbcBtnChooseFirstImage.gridwidth = 2;
    gbcBtnChooseFirstImage.insets = new Insets(0, 0, 5, 5);
    gbcBtnChooseFirstImage.gridx = 1;
    gbcBtnChooseFirstImage.gridy = 1;
    contentPane.add(getBtnChooseFirstImage(), gbcBtnChooseFirstImage);
    GridBagConstraints gbcBtnChooseSecondImage = new GridBagConstraints();
    gbcBtnChooseSecondImage.insets = new Insets(0, 0, 5, 5);
    gbcBtnChooseSecondImage.gridx = 4;
    gbcBtnChooseSecondImage.gridy = 1;
    contentPane.add(getBtnChooseSecondImage(), gbcBtnChooseSecondImage);
    GridBagConstraints gbc_scrollPaneShowFirstImage = new GridBagConstraints();
    gbc_scrollPaneShowFirstImage.gridwidth = 2;
    gbc_scrollPaneShowFirstImage.insets = new Insets(0, 0, 5, 5);
    gbc_scrollPaneShowFirstImage.fill = GridBagConstraints.BOTH;
    gbc_scrollPaneShowFirstImage.gridx = 1;
    gbc_scrollPaneShowFirstImage.gridy = 3;
    contentPane.add(getScrollPane_1(), gbc_scrollPaneShowFirstImage);
    GridBagConstraints gbc_scrollPaneShowSecondImage = new GridBagConstraints();
    gbc_scrollPaneShowSecondImage.insets = new Insets(0, 0, 5, 5);
    gbc_scrollPaneShowSecondImage.fill = GridBagConstraints.BOTH;
    gbc_scrollPaneShowSecondImage.gridx = 4;
    gbc_scrollPaneShowSecondImage.gridy = 3;
    contentPane.add(getScrollPane_2(), gbc_scrollPaneShowSecondImage);
    GridBagConstraints gbcLblCompareResult = new GridBagConstraints();
    gbcLblCompareResult.anchor = GridBagConstraints.EAST;
    gbcLblCompareResult.insets = new Insets(0, 0, 5, 5);
    gbcLblCompareResult.gridx = 1;
    gbcLblCompareResult.gridy = 5;
    contentPane.add(getLblCompareResult(), gbcLblCompareResult);
    GridBagConstraints gbcLblShowResult = new GridBagConstraints();
    gbcLblShowResult.anchor = GridBagConstraints.WEST;
    gbcLblShowResult.insets = new Insets(0, 0, 5, 5);
    gbcLblShowResult.gridx = 2;
    gbcLblShowResult.gridy = 5;
    contentPane.add(getLblShowResult(), gbcLblShowResult);
    GridBagConstraints gbcBtnCompare = new GridBagConstraints();
    gbcBtnCompare.insets = new Insets(0, 0, 5, 5);
    gbcBtnCompare.gridx = 4;
    gbcBtnCompare.gridy = 5;
    contentPane.add(getBtnCompare(), gbcBtnCompare);
    GridBagConstraints gbcLblDistanceBetweenImages = new GridBagConstraints();
    gbcLblDistanceBetweenImages.anchor = GridBagConstraints.EAST;
    gbcLblDistanceBetweenImages.insets = new Insets(0, 0, 5, 5);
    gbcLblDistanceBetweenImages.gridx = 1;
    gbcLblDistanceBetweenImages.gridy = 6;
    contentPane.add(getLblDistanceBetweenImages(), gbcLblDistanceBetweenImages);
    GridBagConstraints gbcLblShowDistance = new GridBagConstraints();
    gbcLblShowDistance.anchor = GridBagConstraints.WEST;
    gbcLblShowDistance.insets = new Insets(0, 0, 5, 5);
    gbcLblShowDistance.gridx = 2;
    gbcLblShowDistance.gridy = 6;
    contentPane.add(getLblShowDistance(), gbcLblShowDistance);
  }

  public JButton getBtnChooseFirstImage() {
    if (btnChooseFirstImage == null) {
      btnChooseFirstImage = new JButton("Choose First Image");
      btnChooseFirstImage
          .addMouseListener(new BtnChooseFirstImageMouseListener());
    }
    return btnChooseFirstImage;
  }

  public JButton getBtnChooseSecondImage() {
    if (btnChooseSecondImage == null) {
      btnChooseSecondImage = new JButton("Choose Second Image");
      btnChooseSecondImage
          .addMouseListener(new BtnChooseSecondImageMouseListener());
    }
    return btnChooseSecondImage;
  }

  public JLabel getLblCompareResult() {
    if (lblCompareResult == null) {
      lblCompareResult = new JLabel("Compare Result: ");
    }
    return lblCompareResult;
  }

  public JLabel getLblDistanceBetweenImages() {
    if (lblDistanceBetweenImages == null) {
      lblDistanceBetweenImages = new JLabel("Distance Between Images: ");
    }
    return lblDistanceBetweenImages;
  }

  public JButton getBtnCompare() {
    if (btnCompare == null) {
      btnCompare = new JButton("Compare");
      btnCompare.addMouseListener(new BtnCompareMouseListener());
    }
    return btnCompare;
  }

  public JLabel getLblShowResult() {
    if (lblShowResult == null) {
      lblShowResult = new JLabel("0 %");
    }
    return lblShowResult;
  }

  public JLabel getLblShowDistance() {
    if (lblShowDistance == null) {
      lblShowDistance = new JLabel("0");
    }
    return lblShowDistance;
  }

  public JScrollPane getScrollPane_1() {
    if (scrollPaneShowFirstImage == null) {
      scrollPaneShowFirstImage = new JScrollPane();
    }
    return scrollPaneShowFirstImage;
  }

  public JScrollPane getScrollPane_2() {
    if (scrollPaneShowSecondImage == null) {
      scrollPaneShowSecondImage = new JScrollPane();
    }
    return scrollPaneShowSecondImage;
  }

  private class BtnChooseFirstImageMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent chooseFirstImageEvent) {
      JFileChooser firstFileChooser = new JFileChooser();
      int returnVal = firstFileChooser.showOpenDialog(getBtnChooseFirstImage());

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File firstFile = firstFileChooser.getSelectedFile();
        try {
          BufferedImage firstImage = ImageIO
              .read(new File(firstFile.getPath()));
          /*
           * Image scaledFirstImage = firstImage.getScaledInstance(
           * pnShowFirstImage.getWidth(), pnShowFirstImage.getHeight(),
           * Image.SCALE_SMOOTH);
           */
          JLabel lblFirstImage = new JLabel(new ImageIcon(firstImage));
          scrollPaneShowFirstImage.setViewportView(lblFirstImage);
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
      int returnVal = secondFileChooser.showOpenDialog(getBtnChooseSecondImage());

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File secondFile = secondFileChooser.getSelectedFile();
        try {
          BufferedImage secondImage = ImageIO
              .read(new File(secondFile.getPath()));
          /*
           * Image scaledFirstImage = firstImage.getScaledInstance(
           * pnShowFirstImage.getWidth(), pnShowFirstImage.getHeight(),
           * Image.SCALE_SMOOTH);
           */
          JLabel lblSecondImage = new JLabel(new ImageIcon(secondImage));
          scrollPaneShowSecondImage.setViewportView(lblSecondImage);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private class BtnCompareMouseListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent compareEvent) {
      //TODO: implement image processing here
    }
  }
}
