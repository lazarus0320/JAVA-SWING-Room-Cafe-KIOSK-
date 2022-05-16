import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Ticket extends ShareData{

	private JFrame frame;
	
	public void checkRentTime() throws IOException, ParseException {  // 대실하지 않은 계정이 로그인 했을 경우, 새로운 계정이 Ticket 클래스에 접근할 경우, 또는 이용권 사놓고 즉시 사용 거부했을 경우에 계정이 소지한 이용권의 시간을 파싱함.
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("name").equals(userName)) {
	
				userTimeTicket = (String)obj.get("timeTicket");
				userDayTicket = (String)obj.get("dayTicket");
				System.out.println("userTimeTicket, userDayTicket 정보 불러옴");
				System.out.println("userTimeTicket" + userTimeTicket);
				System.out.println("userDayTicket" + userDayTicket);
			}
		}
	}
	

	public Ticket() {
		
		frame = new JFrame();
		try {
			checkRentTime();
		} catch (IOException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.desktop);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uBCF4\uC720 \uC774\uC6A9\uAD8C \uC120\uD0DD");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		lblNewLabel.setBounds(396, 35, 537, 84);
		panel.add(lblNewLabel);
		
		JPanel timePanel = new JPanel();
		timePanel.setBackground(new Color(50, 205, 50));
		timePanel.setBounds(120, 180, 400, 400);
		panel.add(timePanel);
		timePanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC794\uC5EC \uC2DC\uAC04\uAD8C");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblNewLabel_1.setBounds(95, 26, 247, 40);
		timePanel.add(lblNewLabel_1);
		
		JLabel timeLabel = new JLabel("00:00");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("굴림", Font.BOLD, 70));
		timeLabel.setBounds(44, 170, 304, 79);
		timePanel.add(timeLabel);
		
		JButton timeBtn = new JButton("\uC0AC\uC6A9\uD558\uAE30");
		timeBtn.setFocusPainted(false);
		timeBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		timeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		timeBtn.setBounds(30, 320, 340, 54);
		timePanel.add(timeBtn);
		
		JPanel dayPanel = new JPanel();
		dayPanel.setBackground(new Color(30, 144, 255));
		dayPanel.setBounds(660, 180, 400, 400);
		panel.add(dayPanel);
		dayPanel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC794\uC5EC \uAE30\uAC04\uAD8C");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblNewLabel_1_1.setBounds(95, 25, 223, 40);
		dayPanel.add(lblNewLabel_1_1);
		
		JLabel dayLabel = new JLabel("0\uC77C");
		dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dayLabel.setFont(new Font("굴림", Font.BOLD, 70));
		dayLabel.setBounds(102, 170, 209, 79);
		dayPanel.add(dayLabel);
		
		JButton dayBtn = new JButton("\uC0AC\uC6A9\uD558\uAE30");
		dayBtn.setFocusPainted(false);
		dayBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		dayBtn.setBounds(30, 320, 340, 54);
		dayPanel.add(dayBtn);
		
		JButton buyBtn = new JButton("\uC774\uC6A9\uAD8C \uAD6C\uB9E4");
		buyBtn.setFocusPainted(false);
		buyBtn.setFont(new Font("굴림", Font.BOLD, 35));
		buyBtn.setBounds(410, 633, 365, 84);
		panel.add(buyBtn);
		
		JButton helpBtn = new JButton("?");
		helpBtn.setFocusPainted(false);
		helpBtn.setFont(new Font("굴림", Font.BOLD, 35));
		helpBtn.setBounds(32, 654, 67, 63);
		panel.add(helpBtn);
		
		int hour;
		int min;
		int day;
		if (userTimeTicket.equals("0") == false) {
			hour = (Integer.parseInt(userTimeTicket)) / 60;
			min = (Integer.parseInt(userTimeTicket)) % 60;
		}
		else {
			hour = 0;
			min = 0;
		}

		if (hour < 10 && min < 10) {
			timeLabel.setText("0"+ hour + ":" + "0" + min);
		}
		else if (hour < 10 && min >= 10) {
			timeLabel.setText("0"+ hour + ":" + min);
		}
		else if (hour >= 10 && min < 10) {
			timeLabel.setText(hour + ":" + "0" + min);
		} else {
		timeLabel.setText(hour + ":" + min);
		}
		
		if (userDayTicket.equals("0") == false) {
			day = Integer.parseInt(userDayTicket);
		} else {
			day = 0;
		}
		
		dayLabel.setText(day + "일");
		
		timeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int answer = JOptionPane.showConfirmDialog(frame, "시간권을 사용하시겠습니까? 사용시 룸 선택 화면으로 이동됩니다.", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
					if (userTimeTicket.equals("0")) {
						JOptionPane.showMessageDialog(null, "보유한 시간권이 없습니다. 이용권을 구매해주세요.");
						return;
					}
					System.out.println("시간권 사용, RoomStage 이동.");
					timeTicketUse = "true";
	
					RoomStage rs = new RoomStage();
					rs.setVisible(true);
					frame.setVisible(false);
					
				} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
					System.out.println("취소.");
				}
			}
		});
		
		dayBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int answer = JOptionPane.showConfirmDialog(frame, "기간권을 사용하시겠습니까? 사용시 룸 선택 화면으로 이동됩니다.", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
					if (userDayTicket.equals("0")) {
						JOptionPane.showMessageDialog(null, "보유한 기간권이 없습니다. 이용권을 구매해주세요.");
						return;
					}
					System.out.println("기간권 사용, RoomStage 이동.");
					dayTicketUse = "true";
					
					RoomStage rs = new RoomStage();
					rs.setVisible(true);
					frame.setVisible(false);
					
				} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
					System.out.println("취소.");
				}
			}
		});
		
		buyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				BuyTicket bt = new BuyTicket();
				bt.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		helpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextArea helpMsg = new JTextArea("시간권은 분 단위로 룸카페를 이용할 수 있는 이용권입니다. \n"
						+ "퇴실시에 남은 시간이 저장됩니다.\n"
						+ "기간권은 일 단위로 룸카페를 이용할 수 있는 이용권입니다.\n"
						+ "24시간 이용 가능하나, 중도 퇴실시 하루 단위로 소멸됩니다.");
				helpMsg.setFont(new Font("굴림", Font.BOLD, 20));
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(frame, helpMsg, "도움말", JOptionPane.INFORMATION_MESSAGE );
			}
		});
	}
		
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
	
}
