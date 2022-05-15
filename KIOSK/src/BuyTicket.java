import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BuyTicket extends ShareData{

	private JFrame frame;
	
	public int ticketPrice = 0;
	public String ticketName = null;

	
	public void doBuyTicket(String name, int price) {
		selectedTicketName = name;
		selectedTicketPrice = price;
		BuyTicketWindow btw = new BuyTicketWindow();
		btw.setVisible(true);
	}

	BuyTicket() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC774\uC6A9\uAD8C \uAD6C\uB9E4\uD558\uAE30");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 50));
		lblNewLabel.setBounds(311, 0, 567, 92);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2DC\uAC04\uAD8C");
		lblNewLabel_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 40));
		lblNewLabel_1.setBounds(288, 103, 140, 41);
		panel.add(lblNewLabel_1);
		
		JButton hour1Btn = new JButton("1\uC2DC\uAC04 / 5000\uC6D0");
		hour1Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		hour1Btn.setBounds(199, 174, 308, 92);
		panel.add(hour1Btn);
		
		JButton hour2Btn = new JButton("2\uC2DC\uAC04 / 8000\uC6D0");
		hour2Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		hour2Btn.setBounds(199, 294, 308, 92);
		panel.add(hour2Btn);
		
		JButton hour3Btn = new JButton("3\uC2DC\uAC04 / 13000\uC6D0");
		hour3Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		hour3Btn.setBounds(199, 414, 310, 92);
		panel.add(hour3Btn);
		
		JButton hour4Btn = new JButton("4\uC2DC\uAC04 / 17000\uC6D0");
		hour4Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		hour4Btn.setBounds(199, 534, 310, 92);
		panel.add(hour4Btn);
		
		JButton hour5Btn = new JButton("5\uC2DC\uAC04 / 22000\uC6D0");
		hour5Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		hour5Btn.setBounds(199, 654, 310, 92);
		panel.add(hour5Btn);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uAE30\uAC04\uAD8C");
		lblNewLabel_1_1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 40));
		lblNewLabel_1_1.setBounds(778, 103, 140, 41);
		panel.add(lblNewLabel_1_1);
		
		JButton day1Btn = new JButton("1\uC77C / 35000\uC6D0");
		day1Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		day1Btn.setBounds(686, 174, 310, 92);
		panel.add(day1Btn);
		
		JButton day2Btn = new JButton("2\uC77C / 65000\uC6D0");
		day2Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		day2Btn.setBounds(686, 294, 310, 92);
		panel.add(day2Btn);
		
		JButton day3Btn = new JButton("3\uC77C / 95000\uC6D0");
		day3Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		day3Btn.setBounds(686, 414, 310, 92);
		panel.add(day3Btn);
		
		JButton day5Btn = new JButton("5\uC77C / 140000\uC6D0");
		day5Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		day5Btn.setBounds(686, 534, 310, 92);
		panel.add(day5Btn);
		
		JButton day10Btn = new JButton("10\uC77C / 260000\uC6D0");
		day10Btn.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		day10Btn.setBounds(686, 654, 310, 92);
		panel.add(day10Btn);
		
		hour1Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 5000;
				ticketName = "1Ω√∞£";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		hour2Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 8000;
				ticketName = "2Ω√∞£";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		hour3Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 13000;
				ticketName = "3Ω√∞£";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		hour4Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 17000;
				ticketName = "4Ω√∞£";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		hour5Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 22000;
				ticketName = "5Ω√∞£";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		day1Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 35000;
				ticketName = "1¿œ";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		day2Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 65000;
				ticketName = "2¿œ";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		day3Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 95000;
				ticketName = "3¿œ";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		day5Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 140000;
				ticketName = "5¿œ";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		day10Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ticketPrice = 260000;
				ticketName = "10¿œ";
				doBuyTicket(ticketName, ticketPrice);
			}
		});
		
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
