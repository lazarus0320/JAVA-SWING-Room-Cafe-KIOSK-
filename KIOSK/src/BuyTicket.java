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

// 이용권 구매 화면 클래스
public class BuyTicket extends ShareData{

	private JFrame frame;
	
	public void doBuyTicket(String name, int price) { // 클릭한 이용권의 정보를 매개변수로 받아 구입을 진행하는 함수를 호출함.
		// ShareData의 static변수 selectedTicketName, selectedTicketPrice에 사용자가 선택한 이용권 이름과 가격을 저장하도록 하고 이용권 윈도우 구매창 클래스를 호출.
		selectedTicketName = name;	
		selectedTicketPrice = price;
		BuyTicketWindow btw = new BuyTicketWindow();
		btw.setVisible(true);
	}

	public BuyTicket() {
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
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		lblNewLabel.setBounds(311, 0, 567, 92);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2DC\uAC04\uAD8C");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblNewLabel_1.setBounds(288, 103, 140, 41);
		panel.add(lblNewLabel_1);
		
		JButton hour1Btn = new JButton("1\uC2DC\uAC04 / 5000\uC6D0");
		hour1Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		hour1Btn.setBounds(199, 174, 308, 92);
		panel.add(hour1Btn);
		
		JButton hour2Btn = new JButton("2\uC2DC\uAC04 / 8000\uC6D0");
		hour2Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		hour2Btn.setBounds(199, 294, 308, 92);
		panel.add(hour2Btn);
		
		JButton hour3Btn = new JButton("3\uC2DC\uAC04 / 13000\uC6D0");
		hour3Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		hour3Btn.setBounds(199, 414, 310, 92);
		panel.add(hour3Btn);
		
		JButton hour4Btn = new JButton("4\uC2DC\uAC04 / 17000\uC6D0");
		hour4Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		hour4Btn.setBounds(199, 534, 310, 92);
		panel.add(hour4Btn);
		
		JButton hour5Btn = new JButton("5\uC2DC\uAC04 / 22000\uC6D0");
		hour5Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		hour5Btn.setBounds(199, 654, 310, 92);
		panel.add(hour5Btn);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uAE30\uAC04\uAD8C");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblNewLabel_1_1.setBounds(778, 103, 140, 41);
		panel.add(lblNewLabel_1_1);
		
		JButton day1Btn = new JButton("1\uC77C / 35000\uC6D0");
		day1Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		day1Btn.setBounds(686, 174, 310, 92);
		panel.add(day1Btn);
		
		JButton day2Btn = new JButton("2\uC77C / 65000\uC6D0");
		day2Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		day2Btn.setBounds(686, 294, 310, 92);
		panel.add(day2Btn);
		
		JButton day3Btn = new JButton("3\uC77C / 95000\uC6D0");
		day3Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		day3Btn.setBounds(686, 414, 310, 92);
		panel.add(day3Btn);
		
		JButton day5Btn = new JButton("5\uC77C / 140000\uC6D0");
		day5Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		day5Btn.setBounds(686, 534, 310, 92);
		panel.add(day5Btn);
		
		JButton day10Btn = new JButton("10\uC77C / 260000\uC6D0");
		day10Btn.setFont(new Font("굴림", Font.PLAIN, 30));
		day10Btn.setBounds(686, 654, 310, 92);
		panel.add(day10Btn);
		
		
		// 1시간 시간권 클릭 이벤트 리스너 설정
		hour1Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("1시간", 5000); // 클릭한 이용권의 정보를 인수로 넘기고 구입을 진행하는 함수를 호출함.
			}
		});
		hour2Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("2시간", 8000);
			}
		});
		
		hour3Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("3시간", 13000);
			}
		});
		
		hour4Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("4시간", 17000);
			}
		});
		
		hour5Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("5시간", 22000);
			}
		});
		
		// 1일 기간권 클릭 이벤트 리스너 설정
		day1Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("1일", 35000);  // 클릭한 이용권의 정보를 인수로 넘기고 구입을 진행하는 함수를 호출함.
			}
		});
		
		day2Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("2일", 65000);
			}
		});
		
		day3Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("3일", 95000);
			}
		});
		
		day5Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("5일", 140000);
			}
		});
		
		day10Btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				doBuyTicket("10일", 260000);
			}
		});
		
		
	}

	public void setVisible(boolean b) {	// 외부 클래스에서 해당 클래스의 frame을 setVisible 호출할 경우 처리
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
