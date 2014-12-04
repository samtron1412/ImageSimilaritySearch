package image.similarity.search.gui.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class AddImage extends JFrame {

  private JPanel contentPane;
  protected JButton btnChooseImage;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AddImage frame = new AddImage();
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
  public AddImage() {
    initGUI();
  }
  private void initGUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.columnWidths = new int[]{0, 0, 0};
    gbl_contentPane.rowHeights = new int[]{0, 0, 0};
    gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    contentPane.setLayout(gbl_contentPane);
    
    btnChooseImage = new JButton("Choose Image");
    GridBagConstraints gbcBtnChooseImage = new GridBagConstraints();
    gbcBtnChooseImage.gridx = 1;
    gbcBtnChooseImage.gridy = 1;
    contentPane.add(btnChooseImage, gbcBtnChooseImage);
  }

}
