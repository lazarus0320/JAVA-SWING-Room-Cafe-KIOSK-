import java.awt.Dimension;
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

// 이용권 윈도우 구매창 클래스 (BuyTicket에서 넘어옴)
public class BuyTicketWindow extends ShareData{

	private JFrame frame;
	private JTextField chargeField;
	public boolean timeTicketBuy = false;
	public boolean dayTicketBuy = false;


	public String makeRentTime() {  // 이용권 구매 시간을 만들어주는 함수. 이용권 구매 영수증에 구매 시간을 표시할 용도로 사용함.
		LocalDateTime now = LocalDateTime.now();	// 현재 시간을 밀리초로 받아옴.
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분ss초"));	// 현재 시간을 나타내는 형식을 지정함.
		System.out.println(formatedNow);
		return formatedNow;	// 이용권 구매시간을 yyyy년MM월dd일 HH시mm분ss초 양식으로 반환함(String)
	}
	
	public void makeRentData(String time , int chargeVal, int changeVal) {  //영수증 결제 영수증 파일을 생성하는 함수. 이용권 구매 시간, 지불금액, 거스름돈을 매개변수로 받음.
		try { 
			try {
				// 디렉토리 생성
				Path directoryPath = Paths.get("C:\\KIOSK\\KIOSK_RECEIPT");	// 해당 경로를 directoryPath 객체명으로 지정함.
				Files.createDirectory(directoryPath);	// 해당 경로를 생성함
				System.out.println(directoryPath + " 디렉토리가 생성되었습니다.");
				} catch (FileAlreadyExistsException e) {
				System.out.println("디렉토리가 이미 존재합니다");
				} catch (NoSuchFileException e) {
				System.out.println("디렉토리 경로가 존재하지 않습니다");
				}catch (IOException e) {
				e.printStackTrace();
				}
			
			// 메모장 파일을 해당 경로에 만들되, 파일명은 회원의 이름 + 이용권 구매 시간 + ".txt"로 지정함. 이렇게 하면 파일 중복을 피할 수 있음.
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\KIOSK\\KIOSK_RECEIPT\\" + userName + time  + ".txt");
			OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
			BufferedWriter file = new BufferedWriter(OutputStreamWriter);
			
			// 영수증 파일을 작성함
			file.write("룸카페 KIOSK 영수증"); 
			file.newLine();	// 개행
			
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
	
	public void addTicketData() {	// 사용자가 구매한 이용권에 대한 정보를 JSON에 갱신하는 함수 호출
		System.out.println("addTicketData 메서드 실행");
		if (selectedTicketName == "1시간"){	// 선택한 이용권의 이름이 1시간일경우,
			try {
				addTicketTime(60);	// 60분을 인수값으로 넣어줌.
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
	public void addTicketTime(int time) throws IOException, ParseException{ // 구매한 시간권의 시간을 분단위로 받아와서 JSON의 해당 회원의 정보에 추가함.
		// JSON 파일 오픈
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		// 회원정보 value값을 배열 형태로 얻어옴.
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			// i번째 인덱스의 map값을 obj로 받아옴.
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("name").equals(userName)) {		// 특정 map값의 "name"에 있는 value값과 로그인 한 회원의 이름이 같을 경우,
				String strTimeTicketVal = (String)obj.get("timeTicket");	// "timeTicket"(시간권 누적합)을 strTimeTicketVal에다가 문자열 형태로 저장함.
				int changeTime = Integer.parseInt(strTimeTicketVal) + time;	// 갱신할 시간을 changeTime으로 선언하고 기존 시간권 누적합과 구매한 시간권의 시간을 더한 값을 저장함.
				obj.put("timeTicket", Integer.toString(changeTime));	// put메서드는 map의 key에 대응되는 value값을 변경하는데 사용함. "timeTicket" key의 value를 String 형변환한 changeTime으로 변경함.
				System.out.println("addTicketTime 메서드 값 : " + changeTime);
			}
		}
		
		timeTicketBuy = true;	// ShareData의 timeTicketUse를 true로 바로 돌리지 않고 이걸 true로 하는 이유 : 이용권 사놓고 즉시 사용을 하지 않았다면 대실을 시작한 게 아닌데 만약 
		// timeTicketBut를 true로 할당함. 나중에 이용권 즉시 사용을 선택할 경우에 이것이 true라면 ShareData의 timeTicketUse 값을 true로 바꿔줄 것임.
		// 그 후에 프로그램을 껐다가 켜도, 해당 회원의 timeTicketUse가 true상태라면 아직 대실중인 상태로 인식될 수 있도록 만들 수 있음.
		
		// 하지만 만약에 여기서 바로 ShareData의 timeTicketUse를 바로 true로 돌려버리면 이용권 사놓고 즉시 사용을 거부했을 때도 timeTicketUse가 true상태로 저장되어 있기 때문에
		// 프로그램 껐다가 해당 회원정보로 로그인해도 대실중인 상태로 인식되는 문제가 생기게 됨. 그것을 방지하기 위해 만들어진 변수임.
		
		
		// JSON 파일 오픈, 위에서 수정한 jsonObj를 JSON파일에 새로 작성하여 정보를 갱신하고 저장함.
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
	
	public void addTicketDay(int day) throws IOException, ParseException{  // 구매한 기간권의 시간을 일단위로 받아와서 JSON의 해당 회원의 정보에 추가함. 자세한 내용은 위와 비슷함
		
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
				System.out.println("addTicketDay 메서드 값 : " + changeDay);
			}
		}
		
		dayTicketBuy = true;

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
	
	
	
	public void ticketQuickUse() {	// 구매한 이용권을 즉시 사용하도록 하는 함수 호출.
		System.out.println("이용권 즉시 사용 선택. 메인으로 이동");
		// 구매한 이용권의 종류에 따라 ShareData의 static 변수를 true로 지정함.
		// 해당 계정에 대해 시간권 또는 기간권을 사용중인 상태로 지정함.
		if (timeTicketBuy == true) {
			timeTicketUse = "true";
		}
		else if (dayTicketBuy == true) {
			dayTicketUse = "true";
		}
		// 룸선택 화면 클래스를 호출함.
		RoomStage rs = new RoomStage();
		rs.setVisible(true);
		frame.setVisible(false);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public BuyTicketWindow() {
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
		
		
		KeyListener changeKey = new KeyListener() {	// 키 눌렀다가 땠을 때 동작하는 함수. 먼저 어떻게 동작하는지 만들어 놓고, 나중에 특정 필드에 적용할 수 있도록 함.
			// 필요없는 부분도 반드시 빈칸으로라도 구현해야 작동함.
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {	// 키를 눌렀다 땠을때 동작하는 함수
				// TODO Auto-generated method stub
				int chargeValue = 0;
				try {
					chargeValue = Integer.parseInt(chargeField.getText()); // 사용자의 지불 금액 텍스트 필드의 값을 정수형으로 형변환해서 저장함.
				} catch(NumberFormatException e1) {	// 만약 지불 금액 텍스트 필드에 정수가 아닌 문자를 입력했을 경우, 지불 금액 텍스트 필드를 빈칸으로 초기화함.
					chargeField.setText("");
				}
				// 사용자가 지불하려는 금액에서 BuyTicket 클래스에서 선택했던 이용권의 가격을 뺀 값을 changeVal에 저장함.
				int changeVal = chargeValue - selectedTicketPrice;
				if (changeVal >= 0) {	// 만약 그 값이 0보다 크면 그 값만큼 거스름돈으로 걸러줘야함. 거스름돈 라벨에 그 값을 표시하도록 함.
					changeLabel.setText(Integer.toString(changeVal)); // 라벨에 텍스트를 입력하는 것이므로 String 형변환 처리함.
				} else {	// changeVal이 0보다 작다는 것은 지불해야할 금액보다 적은 금액을 입력한 것이므로 '-'를 표시하도록 한다.
					changeLabel.setText("-");
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		// 위에서 설정한 changeKey 키 입력 리스너를 chargeField(지불금액 입력 텍스트필드 컴포넌트)에 적용함.
		chargeField.addKeyListener(changeKey);
		
		
		// 취소 버튼 이벤트 리스너 설정
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Ticket tk = new Ticket();	// 이용권 구매를 취소하는 것이므로 이용권 보유 현황 클래스를 호출함.
				tk.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		// 구매 버튼 이벤트 리스너 설정
		payBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int answer = JOptionPane.showConfirmDialog(frame, "결제하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
					int chargeVal = Integer.parseInt(chargeField.getText());	// 지불금액 입럭 텍스트 필드에서 가져온 값을 정수로 형변환 하여 chargeVal에 저장함.
					int changeVal = Integer.parseInt(changeLabel.getText());	// 거스름돈이 얼마인지 나타내는 필드에서 가져온 값을 정수로 형변환 하여 changeVal에 저장함.
					if (selectedTicketPrice > chargeVal) {	// 만약 구매하려는 이용권의 가격이 사용자가 지불한다고 입력한 금액보다 클 경우
						System.out.println("결제 금액을 확인해주세요.");
						JOptionPane.showMessageDialog(frame, "결제 금액을 확인해주세요.", "Message", JOptionPane.WARNING_MESSAGE );
						return;	// 함수 종료
					}
					//	구매 조건을 충족할 경우
					System.out.println("결제 완료.");
					String rentTime = makeRentTime();	// 이용권을 구매한 시간을 반환하도록 하는 함수 호출
					
					addTicketData();	// 사용자가 구매한 이용권에 대한 정보를 JSON에 갱신하는 함수 호출
					int answer2 = JOptionPane.showConfirmDialog(frame, "결제 되었습니다. 영수증을 출력하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
					if(answer2==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
						System.out.println("영수증 출력 선택.");
						makeRentData(rentTime, chargeVal, changeVal);	// 영수증 결제 영수증 파일을 생성하는 함수 호출. 이용권 구매 시간, 지불금액, 거스름돈을 인수로 보냄.

						//로그인 후 대실정보 열람 기능 추가 예정.
					} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
						System.out.println("영수증 출력 거부.");
					}
					//이용권을 당장 쓸 수도 있도록 하는 기능 추가
					int answer3 = JOptionPane.showConfirmDialog(frame, "이용권을 즉시 사용하시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION );
					if(answer3==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
						ticketQuickUse();	// 구매한 이용권을 즉시 사용하도록 하는 함수 호출.
						

						//로그인 후 대실정보 열람 기능 추가 예정.
					} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
						// 즉시 사용을 거부하였으므로 사용자가 보유한 이용권을 열람하는 클래스를 호출함.
						System.out.println("이용권 즉시 사용 거부.");
						Ticket tk = new Ticket();
						tk.setVisible(true);
						frame.setVisible(false);
					}
				} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
					System.out.println("결제 취소.");
					
				}
				
			}
		});
	}

	public void setVisible(boolean b) {	  // 외부 클래스에서 해당 클래스의 frame을 setVisible 호출할 경우 처리
		// TODO Auto-generated method stub
		frame.setVisible(b);	
	}
}
