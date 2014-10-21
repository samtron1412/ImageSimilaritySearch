package image.similarity.search.gui.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JSplitPane;

public class DemoSwingGui extends JFrame {

  private JPanel contentPane;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
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
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.columnWidths = new int[]{222, 212, 0};
    gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
    gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
    gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
    contentPane.setLayout(gbl_contentPane);
    
    JButton btnImage = new JButton("Image1");
    GridBagConstraints gbc_btnImage = new GridBagConstraints();
    gbc_btnImage.insets = new Insets(0, 0, 5, 5);
    gbc_btnImage.gridx = 0;
    gbc_btnImage.gridy = 0;
    contentPane.add(btnImage, gbc_btnImage);
    
    JButton btnImage_1 = new JButton("Image2");
    GridBagConstraints gbc_btnImage_1 = new GridBagConstraints();
    gbc_btnImage_1.insets = new Insets(0, 0, 5, 0);
    gbc_btnImage_1.gridx = 1;
    gbc_btnImage_1.gridy = 0;
    contentPane.add(btnImage_1, gbc_btnImage_1);
  }

}
