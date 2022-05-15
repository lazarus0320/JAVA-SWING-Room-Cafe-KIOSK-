import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	public String makeRentTime() {
		LocalDateTime now = LocalDateTime.now();
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분ss초"));
		System.out.println(formatedNow);
		return formatedNow;
	}
	
	public void makeRentData(String time , int chargeVal, int changeVal) {
		try { 
			try {
				// 디렉토리 생성
				Path directoryPath = Paths.get("C:\\KIOSK\\KIOSK_RECEIPT");
				Files.createDirectory(directoryPath);
				System.out.println(directoryPath + " 디렉토리가 생성되었습니다.");
				} catch (FileAlreadyExistsException e) {
				System.out.println("디렉토리가 이미 존재합니다");
				} catch (NoSuchFileException e) {
				System.out.println("디렉토리 경로가 존재하지 않습니다");
				}catch (IOException e) {
				e.printStackTrace();
				}
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\KIOSK\\KIOSK_RECEIPT\\" + userName + time  + ".txt");
			OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
			BufferedWriter file = new BufferedWriter(OutputStreamWriter);

			file.write("룸카페 KIOSK 영수증"); 
			file.newLine();
			
			file.write("사업자번호 : 123-4567-890    대표자 : 민지훈");
			file.newLine();
			file.write("회원 이름 : " + userName); 
			file.newLine();
			file.write("결제 시간 : " + time); 
			file.newLine();
			file.write("==============================="); 
			file.newLine();
			file.write("이용권 이름            금액"); 
			file.newLine();
			file.write("==============================="); 
			file.newLine();
			file.write(selectedTicketName + "                  " + selectedTicketPrice + "원"); 
			file.newLine();
			file.write("------------------------------------------------------");
			file.newLine();
			file.write("지불금액              " + chargeVal + "원");
			file.newLine();
			file.write("거스름돈              " + changeVal + "원");		
			
			
			file.flush(); 
			file.close(); 
			System.out.println("C:\\KIOSK\\KIOSK_RECEIPT\\ 경로에 영수증 파일 생성됨.");
		} catch (IOException e) { 
				e.printStackTrace(); 
		}
	}
	
	public void addTicketData() {
		System.out.println("addTicketData 메서드 실행");
		if (selectedTicketName == "1시간"){
			try {
				addTicketTime(60);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "2시간"){
			try {
				addTicketTime(120);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "3시간"){
			try {
				addTicketTime(180);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "4시간"){
			try {
				addTicketTime(240);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "5시간"){
			try {
				addTicketTime(300);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "10시간"){
			try {
				addTicketTime(600);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "1일"){
			try {
				addTicketDay(1);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "2일"){
			try {
				addTicketDay(2);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "3일"){
			try {
				addTicketDay(3);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "5일"){
			try {
				addTicketDay(5);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "10일"){
			try {
				addTicketDay(10);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addTicketDay(int day) throws IOException, ParseException{
		
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("name").equals(userName)) {
				String strDayTicketVal = (String)obj.get("dayTicket");
				int changeDay = Integer.parseInt(strDayTicketVal) + day;
				obj.put("dayTicket", Integer.toString(changeDay));
			}
		}

		try { 
			FileOutputStream fileOutputStream2 = new FileOutputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
			OutputStreamWriter OutputStreamWriter2 = new OutputStreamWriter(fileOutputStream2, "utf-8");
			BufferedWriter file2 = new BufferedWriter(OutputStreamWriter2);
			
			System.out.println(jsonObj.toJSONString());
			file2.write(jsonObj.toJSONString()); 
			file2.flush(); 
			file2.close(); 
		} catch (IOException e) { 
				e.printStackTrace(); 
		}
	}
	
	public void addTicketTime(int time) throws IOException, ParseException{
		
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("name").equals(userName)) {
				String strTimeTicketVal = (String)obj.get("timeTicket");
				int changeTime = Integer.parseInt(strTimeTicketVal) + time;
				obj.put("timeTicket", Integer.toString(changeTime));
			}
		}
		try { 
			FileOutputStream fileOutputStream2 = new FileOutputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
			OutputStreamWriter OutputStreamWriter2 = new OutputStreamWriter(fileOutputStream2, "utf-8");
			BufferedWriter file2 = new BufferedWriter(OutputStreamWriter2);
			
			System.out.println(jsonObj.toJSONString());
			file2.write(jsonObj.toJSONString()); 
			file2.flush(); 
			file2.close(); 
		} catch (IOException e) { 
				e.printStackTrace(); 
		}
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
		frame.setTitle("이용권 구매");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 484, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC774\uC6A9\uAD8C \uAD6C\uB9E4");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblNewLabel.setBounds(171, 10, 145, 39);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC120\uD0DD\uD55C \uC774\uC6A9\uAD8C :");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(49, 93, 145, 39);
		panel.add(lblNewLabel_1);
		
		chargeField = new JTextField();
		chargeField.setBounds(215, 233, 205, 39);
		panel.add(chargeField);
		chargeField.setColumns(10);
		
		JLabel ticketNameLabel = new JLabel("");
		ticketNameLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		ticketNameLabel.setBounds(215, 100, 199, 30);
		panel.add(ticketNameLabel);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uC6A9\uAD8C \uAC00\uACA9    :");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(49, 160, 145, 24);
		panel.add(lblNewLabel_2);
		
		JLabel ticketPriceLabel = new JLabel("");
		ticketPriceLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		ticketPriceLabel.setBounds(215, 160, 199, 30);
		panel.add(ticketPriceLabel);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uAE08\uC561 \uC785\uB825       :");
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(49, 235, 145, 24);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uAC70\uC2A4\uB984\uB3C8        :");
		lblNewLabel_2_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(49, 304, 145, 24);
		panel.add(lblNewLabel_2_1_1);
		
		JButton cancelBtn = new JButton("\uCDE8\uC18C");
		cancelBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		cancelBtn.setBounds(12, 391, 222, 60);
		panel.add(cancelBtn);
		
		JButton payBtn = new JButton("\uACB0\uC81C");
		payBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		payBtn.setBounds(250, 391, 222, 60);
		panel.add(payBtn);
		
		
		ticketNameLabel.setText(selectedTicketName);
		ticketPriceLabel.setText(Integer.toString(selectedTicketPrice) + "원");
		
		JLabel changeLabel = new JLabel((String) null);
		changeLabel.setFont(new Font("굴림", Font.PLAIN, 20));
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
					chargeValue = Integer.parseInt(chargeField.getText()); // 사용자가 지불하려는 금액
				} catch(NumberFormatException e1) {
					chargeField.setText("");
				}
				int price = selectedTicketPrice;  // 티켓 가격
				int realChargeVal = chargeValue - price;
				if (realChargeVal >= 0) {
					changeLabel.setText(Integer.toString(chargeValue - price)); // 거스름돈 출력
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
		
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int answer = JOptionPane.showConfirmDialog(frame, "결제하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
					int chargeVal = Integer.parseInt(chargeField.getText());
					int changeVal = Integer.parseInt(changeLabel.getText());
					if (selectedTicketPrice > chargeVal) {
						System.out.println("결제 금액을 확인해주세요.");
						JOptionPane.showMessageDialog(frame, "결제 금액을 확인해주세요.", "Message", JOptionPane.WARNING_MESSAGE );
						return;
					}
					System.out.println("결제 완료.");
					String rentTime = makeRentTime();
					
					addTicketData();
					int answer2 = JOptionPane.showConfirmDialog(frame, "결제 되었습니다. 영수증을 출력하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
					if(answer2==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
						System.out.println("영수증 출력 선택.");
						makeRentData(rentTime, chargeVal, changeVal);

						//로그인 후 대실정보 열람 기능 추가 예정.
					} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
						System.out.println("영수증 출력 거부.");
					}
					//이용권을 당장 쓸 수도 있도록 하는 기능 추가
					int answer3 = JOptionPane.showConfirmDialog(frame, selectedRoomNum + "번룸에서 이용권을 즉시 사용하시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION );
					if(answer3==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
						System.out.println("이용권 즉시 사용 선택. 메인으로 이동");
						timeTicketUse = true;
						MainStage ms = new MainStage();
						ms.setVisible(true);
						frame.setVisible(false);

						//로그인 후 대실정보 열람 기능 추가 예정.
					} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
						System.out.println("이용권 즉시 사용 거부.");
					}
				} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
					System.out.println("결제 취소.");
				}
				
			}
		});
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
