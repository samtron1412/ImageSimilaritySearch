package image.similarity.search.gui.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DemoSwingGui extends JFrame {

  private JPanel contentPane;

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
    /**
     * INIT FRAME AND CONTENT PANE
     */
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 700, 500);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    GridBagLayout gbl_contentPane = new GridBagLayout();
    gbl_contentPane.columnWidths = new int[] { 30, 134, 115, 30, 289, 30, 0 };
    gbl_contentPane.rowHeights = new int[] { 27, 25, 28, 237, 28, 22, 32, 28, 0 };
    gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0,
        0.0, Double.MIN_VALUE };
    gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
        0.0, 0.0, Double.MIN_VALUE };
    contentPane.setLayout(gbl_contentPane);

    /**
     * BUTTON BROWSE IMAGE 1
     */
    JButton btnChooseImage1 = new JButton("Choose Image 1");
    btnChooseImage1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent btnChooseImage1Event) {
        
      }
    });
    GridBagConstraints gbc_btnChooseImage1 = new GridBagConstraints();
    gbc_btnChooseImage1.gridwidth = 2;
    gbc_btnChooseImage1.insets = new Insets(0, 0, 5, 5);
    gbc_btnChooseImage1.gridx = 1;
    gbc_btnChooseImage1.gridy = 1;
    contentPane.add(btnChooseImage1, gbc_btnChooseImage1);

    /**
     * BUTTON BROWSE IMAGE 2
     */
    JButton btnChooseImage2 = new JButton("Choose Image 2");
    GridBagConstraints gbc_btnChooseImage2 = new GridBagConstraints();
    gbc_btnChooseImage2.insets = new Insets(0, 0, 5, 5);
    gbc_btnChooseImage2.gridx = 4;
    gbc_btnChooseImage2.gridy = 1;
    contentPane.add(btnChooseImage2, gbc_btnChooseImage2);

    /**
     * PANEL 1 TO SHOW IMAGE 1
     */
    JPanel panel1 = new JPanel();
    panel1.setBackground(Color.WHITE);
    GridBagConstraints gbc_panel1 = new GridBagConstraints();
    gbc_panel1.gridwidth = 2;
    gbc_panel1.insets = new Insets(0, 0, 5, 5);
    gbc_panel1.fill = GridBagConstraints.BOTH;
    gbc_panel1.gridx = 1;
    gbc_panel1.gridy = 3;
    contentPane.add(panel1, gbc_panel1);
    
    JLabel lblShowImage1 = new JLabel("");
    lblShowImage1.setHorizontalAlignment(SwingConstants.CENTER);
    lblShowImage1.setVerticalAlignment(SwingConstants.CENTER);
    panel1.add(lblShowImage1);

    /**
     * PANE 2 TO SHOW IMAGE 2
     */
    JPanel panel2 = new JPanel();
    panel2.setBackground(Color.WHITE);
    GridBagConstraints gbc_panel2 = new GridBagConstraints();
    gbc_panel2.insets = new Insets(0, 0, 5, 5);
    gbc_panel2.fill = GridBagConstraints.BOTH;
    gbc_panel2.gridx = 4;
    gbc_panel2.gridy = 3;
    contentPane.add(panel2, gbc_panel2);
    
    JLabel lblShowImage2 = new JLabel("");
    lblShowImage2.setHorizontalAlignment(SwingConstants.CENTER);
    lblShowImage2.setVerticalAlignment(SwingConstants.CENTER);
    panel2.add(lblShowImage2);

    /**
     * LABEL RESULT
     */
    JLabel lblResult = new JLabel("Compare Result: ");
    GridBagConstraints gbc_lblResult = new GridBagConstraints();
    gbc_lblResult.anchor = GridBagConstraints.EAST;
    gbc_lblResult.insets = new Insets(0, 0, 5, 5);
    gbc_lblResult.gridx = 1;
    gbc_lblResult.gridy = 5;
    contentPane.add(lblResult, gbc_lblResult);

    /**
     * LABEL TO SHOW RESULT AFTER COMPARE
     */
    JLabel lblShowResult = new JLabel("0 %");
    lblShowResult.setBackground(Color.GRAY);
    GridBagConstraints gbc_lblShowResult = new GridBagConstraints();
    gbc_lblShowResult.anchor = GridBagConstraints.WEST;
    gbc_lblShowResult.insets = new Insets(0, 0, 5, 5);
    gbc_lblShowResult.gridx = 2;
    gbc_lblShowResult.gridy = 5;
    contentPane.add(lblShowResult, gbc_lblShowResult);

    /**
     * BUTTON TO COMPARE TWO IMAGE
     */
    JButton btnCompare = new JButton("Compare");
    GridBagConstraints gbc_btnCompare = new GridBagConstraints();
    gbc_btnCompare.insets = new Insets(0, 0, 5, 5);
    gbc_btnCompare.gridx = 4;
    gbc_btnCompare.gridy = 5;
    contentPane.add(btnCompare, gbc_btnCompare);

    /**
     * LABEL DISTANCE
     */
    JLabel lblDistance = new JLabel("Distance Between Images: ");
    GridBagConstraints gbc_lblDistance = new GridBagConstraints();
    gbc_lblDistance.anchor = GridBagConstraints.EAST;
    gbc_lblDistance.insets = new Insets(0, 0, 5, 5);
    gbc_lblDistance.gridx = 1;
    gbc_lblDistance.gridy = 6;
    contentPane.add(lblDistance, gbc_lblDistance);

    /**
     * LABEL TO SHOW DISTANCE AFTER COMPARE
     */
    JLabel lblShowDistance = new JLabel("0");
    GridBagConstraints gbc_lblShowDistance = new GridBagConstraints();
    gbc_lblShowDistance.anchor = GridBagConstraints.WEST;
    gbc_lblShowDistance.insets = new Insets(0, 0, 5, 5);
    gbc_lblShowDistance.gridx = 2;
    gbc_lblShowDistance.gridy = 6;
    contentPane.add(lblShowDistance, gbc_lblShowDistance);
  }

}
