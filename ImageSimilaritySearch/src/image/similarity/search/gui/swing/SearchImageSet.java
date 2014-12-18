package image.similarity.search.gui.swing;

import image.similarity.search.common.CompareImages;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JTextPane;

public class SearchImageSet extends JFrame {

  private JPanel contentPane;
  private boolean folderChoosed = false;
  private boolean imageChoosed = false;
  protected JButton btnChooseImageFolder;
  protected JTextField txtPathOfImageFolder;
  protected JButton btnChooseImage;
  protected JTextField txtPathOfImageInput;
  protected JFileChooser imageChooser = new JFileChooser();
  protected JFileChooser folderChooser = new JFileChooser();
  protected File folder;
  protected File image;
  protected JButton btnSearch;
  protected File[] listImages;
  protected JTextPane txtpnPathOfImages;

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
          SearchImageSet frame = new SearchImageSet();
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
  public SearchImageSet() {
    initGUI();
  }

  private void initGUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
    gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0,
        Double.MIN_VALUE };
    gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        0.0, 1.0, Double.MIN_VALUE };
    contentPane.setLayout(gbl_contentPane);

    btnChooseImageFolder = new JButton("Choose Image Folder");
    btnChooseImageFolder
        .addActionListener(new BtnChooseImageFolderActionListener());
    GridBagConstraints gbcBtnChooseImageFolder = new GridBagConstraints();
    gbcBtnChooseImageFolder.insets = new Insets(0, 0, 5, 5);
    gbcBtnChooseImageFolder.gridx = 1;
    gbcBtnChooseImageFolder.gridy = 1;
    contentPane.add(btnChooseImageFolder, gbcBtnChooseImageFolder);

    btnChooseImage = new JButton("Choose Image");
    btnChooseImage.addActionListener(new BtnChooseImageActionListener());
    GridBagConstraints gbcBtnChooseImage = new GridBagConstraints();
    gbcBtnChooseImage.insets = new Insets(0, 0, 5, 0);
    gbcBtnChooseImage.gridx = 3;
    gbcBtnChooseImage.gridy = 1;
    contentPane.add(btnChooseImage, gbcBtnChooseImage);

    txtPathOfImageFolder = new JTextField();
    txtPathOfImageFolder.setText("Path of image folder");
    txtPathOfImageFolder.setColumns(10);
    GridBagConstraints gbcTxtPathOfImageFolder = new GridBagConstraints();
    gbcTxtPathOfImageFolder.insets = new Insets(0, 0, 5, 5);
    gbcTxtPathOfImageFolder.fill = GridBagConstraints.HORIZONTAL;
    gbcTxtPathOfImageFolder.gridx = 1;
    gbcTxtPathOfImageFolder.gridy = 3;
    contentPane.add(txtPathOfImageFolder, gbcTxtPathOfImageFolder);

    txtPathOfImageInput = new JTextField();
    txtPathOfImageInput.setText("Path of image input");
    GridBagConstraints gbcTxtPathOfImageInput = new GridBagConstraints();
    gbcTxtPathOfImageInput.insets = new Insets(0, 0, 5, 0);
    gbcTxtPathOfImageInput.fill = GridBagConstraints.HORIZONTAL;
    gbcTxtPathOfImageInput.gridx = 3;
    gbcTxtPathOfImageInput.gridy = 3;
    contentPane.add(txtPathOfImageInput, gbcTxtPathOfImageInput);
    txtPathOfImageInput.setColumns(10);

    btnSearch = new JButton("Search");
    btnSearch.addActionListener(new BtnSearchActionListener());
    btnSearch.setEnabled(false);
    GridBagConstraints gbcBtnSearch = new GridBagConstraints();
    gbcBtnSearch.gridwidth = 3;
    gbcBtnSearch.insets = new Insets(0, 0, 5, 0);
    gbcBtnSearch.gridx = 1;
    gbcBtnSearch.gridy = 5;
    contentPane.add(btnSearch, gbcBtnSearch);
    
    txtpnPathOfImages = new JTextPane();
    txtpnPathOfImages.setText("Path of images result");
    GridBagConstraints gbcTxtpnPathOfImages = new GridBagConstraints();
    gbcTxtpnPathOfImages.gridwidth = 3;
    gbcTxtpnPathOfImages.insets = new Insets(0, 0, 0, 5);
    gbcTxtpnPathOfImages.fill = GridBagConstraints.BOTH;
    gbcTxtpnPathOfImages.gridx = 1;
    gbcTxtpnPathOfImages.gridy = 7;
    contentPane.add(txtpnPathOfImages, gbcTxtpnPathOfImages);
  }

  private class BtnChooseImageFolderActionListener implements ActionListener {
    public void actionPerformed(ActionEvent arg0) {
      folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int returnVal = folderChooser.showOpenDialog(btnChooseImageFolder);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        folderChoosed = true;
        folder = folderChooser.getSelectedFile();
        System.out.println(folder.getPath());
        listImages = folder.listFiles(new FilenameFilter() {
          public boolean accept(File dir, String name) {
            if (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")) {
              return true;
            } else {
              return false;
            }
          }
        });
        
        System.out.println(listImages);

        txtPathOfImageFolder.setText(folder.getPath());
        if (imageChoosed)
          btnSearch.setEnabled(true);
      }
    }
  }

  private class BtnChooseImageActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int returnVal = imageChooser.showOpenDialog(btnChooseImage);

      if (returnVal == JFileChooser.APPROVE_OPTION) {
        imageChoosed = true;
        image = imageChooser.getSelectedFile();
        txtPathOfImageInput.setText(image.getPath());
        if (folderChoosed) btnSearch.setEnabled(true);
      }
    }
  }
  
  private class BtnSearchActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      HashMap<String, Double> imageLst = CompareImages.compareWithSetOfImages(image, listImages);
      System.out.println(imageLst.size());
      System.out.println(imageLst.toString());
    }
  }
}
