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
	
	public void checkRentTime() throws IOException, ParseException {  // ������� ���� ������ �α��� ���� ���, ���ο� ������ Ticket Ŭ������ ������ ���, �Ǵ� �̿�� ����� ��� ��� �ź����� ��쿡 ������ ������ �̿���� �ð��� �Ľ���.
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("ȸ������");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("name").equals(userName)) {
	
				userTimeTicket = (String)obj.get("timeTicket");
				userDayTicket = (String)obj.get("dayTicket");
				System.out.println("userTimeTicket, userDayTicket ���� �ҷ���");
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
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 50));
		lblNewLabel.setBounds(396, 35, 537, 84);
		panel.add(lblNewLabel);
		
		JPanel timePanel = new JPanel();
		timePanel.setBackground(new Color(50, 205, 50));
		timePanel.setBounds(120, 180, 400, 400);
		panel.add(timePanel);
		timePanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\uC794\uC5EC \uC2DC\uAC04\uAD8C");
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 40));
		lblNewLabel_1.setBounds(95, 26, 247, 40);
		timePanel.add(lblNewLabel_1);
		
		JLabel timeLabel = new JLabel("00:00");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("����", Font.BOLD, 70));
		timeLabel.setBounds(44, 170, 304, 79);
		timePanel.add(timeLabel);
		
		JButton timeBtn = new JButton("\uC0AC\uC6A9\uD558\uAE30");
		timeBtn.setFocusPainted(false);
		timeBtn.setFont(new Font("����", Font.PLAIN, 25));
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
		lblNewLabel_1_1.setFont(new Font("���� ���", Font.BOLD, 40));
		lblNewLabel_1_1.setBounds(95, 25, 223, 40);
		dayPanel.add(lblNewLabel_1_1);
		
		JLabel dayLabel = new JLabel("0\uC77C");
		dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dayLabel.setFont(new Font("����", Font.BOLD, 70));
		dayLabel.setBounds(102, 170, 209, 79);
		dayPanel.add(dayLabel);
		
		JButton dayBtn = new JButton("\uC0AC\uC6A9\uD558\uAE30");
		dayBtn.setFocusPainted(false);
		dayBtn.setFont(new Font("����", Font.PLAIN, 25));
		dayBtn.setBounds(30, 320, 340, 54);
		dayPanel.add(dayBtn);
		
		JButton buyBtn = new JButton("\uC774\uC6A9\uAD8C \uAD6C\uB9E4");
		buyBtn.setFocusPainted(false);
		buyBtn.setFont(new Font("����", Font.BOLD, 35));
		buyBtn.setBounds(410, 633, 365, 84);
		panel.add(buyBtn);
		
		JButton helpBtn = new JButton("?");
		helpBtn.setFocusPainted(false);
		helpBtn.setFont(new Font("����", Font.BOLD, 35));
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
		
		dayLabel.setText(day + "��");
		
		timeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int answer = JOptionPane.showConfirmDialog(frame, "�ð����� ����Ͻðڽ��ϱ�? ���� �� ���� ȭ������ �̵��˴ϴ�.", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //����ڰ� yes�� ������ ���
					if (userTimeTicket.equals("0")) {
						JOptionPane.showMessageDialog(null, "������ �ð����� �����ϴ�. �̿���� �������ּ���.");
						return;
					}
					System.out.println("�ð��� ���, RoomStage �̵�.");
					timeTicketUse = "true";
	
					RoomStage rs = new RoomStage();
					rs.setVisible(true);
					frame.setVisible(false);
					
				} else{  //����ڰ� Yes �̿��� ���� ������ ���
					System.out.println("���.");
				}
			}
		});
		
		dayBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int answer = JOptionPane.showConfirmDialog(frame, "�Ⱓ���� ����Ͻðڽ��ϱ�? ���� �� ���� ȭ������ �̵��˴ϴ�.", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //����ڰ� yes�� ������ ���
					if (userDayTicket.equals("0")) {
						JOptionPane.showMessageDialog(null, "������ �Ⱓ���� �����ϴ�. �̿���� �������ּ���.");
						return;
					}
					System.out.println("�Ⱓ�� ���, RoomStage �̵�.");
					dayTicketUse = "true";
					
					RoomStage rs = new RoomStage();
					rs.setVisible(true);
					frame.setVisible(false);
					
				} else{  //����ڰ� Yes �̿��� ���� ������ ���
					System.out.println("���.");
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
				JTextArea helpMsg = new JTextArea("�ð����� �� ������ ��ī�並 �̿��� �� �ִ� �̿���Դϴ�. \n"
						+ "��ǽÿ� ���� �ð��� ����˴ϴ�.\n"
						+ "�Ⱓ���� �� ������ ��ī�並 �̿��� �� �ִ� �̿���Դϴ�.\n"
						+ "24�ð� �̿� �����ϳ�, �ߵ� ��ǽ� �Ϸ� ������ �Ҹ�˴ϴ�.");
				helpMsg.setFont(new Font("����", Font.BOLD, 20));
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(frame, helpMsg, "����", JOptionPane.INFORMATION_MESSAGE );
			}
		});
	}
		
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
	
}
