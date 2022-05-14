import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BuyTicketWindow extends ShareData{

	private JFrame frame;
	private JTextField chargeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyTicketWindow window = new BuyTicketWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BuyTicketWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 500);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("ÀÌ¿ë±Ç ±¸¸Å");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 484, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC774\uC6A9\uAD8C \uAD6C\uB9E4");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		lblNewLabel.setBounds(171, 10, 145, 39);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC120\uD0DD\uD55C \uC774\uC6A9\uAD8C :");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(49, 93, 145, 39);
		panel.add(lblNewLabel_1);
		
		chargeField = new JTextField();
		chargeField.setBounds(215, 233, 205, 39);
		panel.add(chargeField);
		chargeField.setColumns(10);
		
		JLabel ticketNameLabel = new JLabel("");
		ticketNameLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		ticketNameLabel.setBounds(215, 100, 199, 30);
		panel.add(ticketNameLabel);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uC6A9\uAD8C \uAC00\uACA9    :");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(49, 160, 145, 24);
		panel.add(lblNewLabel_2);
		
		JLabel ticketPriceLabel = new JLabel("");
		ticketPriceLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		ticketPriceLabel.setBounds(215, 160, 199, 30);
		panel.add(ticketPriceLabel);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uAE08\uC561 \uC785\uB825       :");
		lblNewLabel_2_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(49, 235, 145, 24);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uAC70\uC2A4\uB984\uB3C8        :");
		lblNewLabel_2_1_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(49, 304, 145, 24);
		panel.add(lblNewLabel_2_1_1);
		
		JButton cancelBtn = new JButton("\uCDE8\uC18C");
		cancelBtn.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		cancelBtn.setBounds(12, 391, 222, 60);
		panel.add(cancelBtn);
		
		JButton payBtn = new JButton("\uACB0\uC81C");
		payBtn.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		payBtn.setBounds(250, 391, 222, 60);
		panel.add(payBtn);
		
		
		ticketNameLabel.setText(selectedTicketName);
		ticketPriceLabel.setText(Integer.toString(selectedTicketPrice) + "¿ø");
		
		JLabel changeLabel = new JLabel((String) null);
		changeLabel.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		changeLabel.setBounds(215, 304, 199, 30);
		panel.add(changeLabel);
		
		
		KeyListener changeKey = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int chargeValue = 0;
				try {
					chargeValue = Integer.parseInt(chargeField.getText()); // »ç¿ëÀÚ°¡ ÁöºÒÇÏ·Á´Â ±Ý¾×
				} catch(NumberFormatException e1) {
					chargeField.setText("");
				}
				int price = selectedTicketPrice;  // Æ¼ÄÏ °¡°Ý
				int realChargeVal = chargeValue - price;
				if (realChargeVal >= 0) {
					changeLabel.setText(Integer.toString(chargeValue - price)); // °Å½º¸§µ· Ãâ·Â
				} else {
					changeLabel.setText("-");
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		chargeField.addKeyListener(changeKey);
		
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
