import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// 로그인 화면 클래스
public class logIn extends ShareData{

	private JFrame frame;
	private JTextField idField;
	private JPasswordField passField;
	
	// 메인함수

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logIn window = new logIn();
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
	public logIn(){
		initialize();
	}
	
	public void ticketUseCheck() {  // 로그인 시 해당 계정이 이용권을 사용하는 중이었는지를 확인함.
		if (timeTicketUse.equals("true") || dayTicketUse.equals("true")){ // 시간권, 기간권 둘 중 하나가 사용중이었을 경우
			RoomStage rs = new RoomStage();	// 룸 선택 클래스를 호출함.
			rs.setVisible(true);
			frame.setVisible(false);
		} else {
			Ticket tk = new Ticket();   // 이용권을 사용하는 상태가 아닌 경우 이용권 보유 화면 클래스를 호출함.
			tk.setVisible(true);
			frame.setVisible(false);
			return;
		}
	}
	/*
	 * 메서드 이름에다 대고 ctrl + 좌클릭. 함수 호출부~선언부 사이를 넘나듬.
	 * 괄호에다가 대고 ctrl+shift+p : 괄호의 여는 부분과 닫는 부분 사이를 넘나듬.
	 */
	
	public Boolean loginCheck(String id, String pass) throws IOException, ParseException{   // 입력한 아이디, 비번이 JSON 회원정보에 있는 정보와 일치하는 것이 있으면 필요한 데이터를 파싱해서 static 변수에 저장함.
		// 경로에 있는 json파일을 열람. utf-8 형식으로 인코딩.
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		
		// jsonObj 변수가 JSON 전체 데이터를 파싱한 값을 받음.
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");	// JSON의 회원정보만 파싱해서 accoutArr 배열에 저장함.
		System.out.println(accountArr);
		
		// json파일의 해체는 map형식을 JSONObject로, 배열 형식을 JSONArray형태로 번갈아서 받는 방법을 활용한다.
		
		for(int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i); // JSON 가장 내부에 있는 map을 JSONObject 형태로 받음.
			if (obj.get("id").equals(id) && obj.get("password").equals(pass)) {	// 입력한 아이디 비번과 JSON의 아이디 비번들 중에 같은 것을 찾음.
				// ShareData에 있는 static 변수들에 해당 회원의 필요한 정보들을 저장함. 이제 이 정보들은 JSON을 열고 파싱하는 과정없이도 바로 호출할 수 있음.
				userStatus = obj; // JSON 전체 값
				userName = (String)obj.get("name");  // 해당 계정의 이름
				userId = (String)obj.get("id");		// 해당 계정의 아이디
				userPass = (String)obj.get("password"); // 해당 계정의 비번
				userPhone = (String)obj.get("phone");	// 해당 계정의 휴대폰 번호
				userTimeTicket = (String) obj.get("timeTicket");  // 시간권 시간 누적합
				userDayTicket = (String)obj.get("dayTicket");	  // 기간권 시간 누적합
				timeTicketUse = (String)obj.get("timeTicketUse"); // 시간권 사용 여부
				dayTicketUse =  (String)obj.get("dayTicketUse");  // 기간권 사용 여부
				startTicketTime =  (String)obj.get("startTicketTime");  // 대실 시작 시간(대실 하지 않았을 경우 "X"값 가짐)
				
				System.out.println(userName);
				System.out.println(userId);
				System.out.println(userPass);
				System.out.println(userPhone);
				System.out.println(userTimeTicket);
				System.out.println(userDayTicket);
				return true;    // 입력한 정보와 일치하는 회원 정보를 찾았으므로 true를 반환.
			}
		}
		return false;  // 입력한 정보와 일치하는 회원 정보를 찾지 못해서 false를 반환.
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// 프레임 설정 
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		
		//패널 설정
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 69, 0), 5));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(350, 200, 500, 400);
		panel.setLayout(null);
		frame.getContentPane().add(panel); // 프레임 위에 getContentPane() 위에 panel 올림
		
		JLabel LoginLabel = new JLabel("Log In");
		LoginLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		LoginLabel.setBounds(24, 13, 137, 63);
		panel.add(LoginLabel);
		
		JLabel idLabel = new JLabel("ID :");
		idLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		idLabel.setBounds(89, 105, 80, 51);
		panel.add(idLabel);
		
		JLabel passLabel = new JLabel("PASS :");
		passLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		passLabel.setBounds(55, 184, 80, 51);
		panel.add(passLabel);
		
		//id 입력창 설정
		idField = new JTextField();
		idField.setBounds(160, 115, 250, 40);
		idField.setColumns(10);
		panel.add(idField);
		
		//password 입력창 설정
		passField = new JPasswordField();
		passField.setBounds(160, 195, 250, 40);
		panel.add(passField);
		
		//회원가입 버튼 설정
		JButton newAccountBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		newAccountBtn.setFocusPainted(false);
		newAccountBtn.setBackground(new Color(147, 112, 219));
		newAccountBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		newAccountBtn.setBounds(45, 290, 190, 80);
		panel.add(newAccountBtn);
		
		//로그인 버튼 설정
		JButton loginBtn = new JButton("\uB85C\uADF8\uC778");
		loginBtn.setBackground(new Color(255, 127, 80));
		loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		loginBtn.setBounds(263, 290, 190, 80);
		loginBtn.setFocusPainted(false);
		panel.add(loginBtn);
		
		//회원가입 버튼 클릭 이벤트
		newAccountBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SignUp signup = new SignUp();    //회원가입 클래스 호출
				signup.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		//로그인 버튼 클릭 이벤트
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String testId = idField.getText();
				String testPass = ""; // passField.getPassword();로 받으면 char [] 배열로 선언해야하는데 배열에 담긴 값을 조건문 비교하기가 귀찮아짐.
				for(int i = 0; i < passField.getPassword().length; i++) {
					testPass += passField.getPassword()[i];
				} //비밀번호 창에 입력된 문자들 하나하나를 더해서 string 문자열에 합친다. 아래에서 조건문을 사용할 때 간단하게 .equals를 사용할 수 있다.
				System.out.println(testId);
				System.out.println(testPass);
				if(testId.equals("") || testPass.equals("")) {   // 아이디, 비번창 중 하나라도 비어있을 경우.
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					System.out.println("로그인 실패 > 로그인 정보 미입력");
				}
				
				else if(testId != null && testPass != null) {	// 아이디, 비번 모두 입력이 되어있는 경우.
					try {
						if(loginCheck(testId, testPass)) {  // 입력한 아이디 비번이 JSON에 존재하는 정보와 일치하는 것이 있는지 확인함.
							if (testId.equals("admin0320") && testPass.equals("admin0320")) { // 관리자 아이디 비번으로 입력되었을 경우.
								int answer = JOptionPane.showConfirmDialog(frame, "관리자 모드로 로그인 하시겠습니까?", "관리자 모드",JOptionPane.YES_NO_OPTION );
								if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
									System.out.println("관리자 모드 접속");
									Admin admin = new Admin();	// 관리자 모드 클래스 호출.
									admin.setVisible(true);
									frame.setVisible(false);
									return;
								} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
									System.out.println("일반 로그인");
									ticketUseCheck();    // 로그인 한 회원의 이용권 사용 정보 체크 함수
									return;
								}
								
							} // 로그인 체크 함수를 통과했는데 일반 계정이었을 경우.
							System.out.println("로그인 성공");
							JOptionPane.showMessageDialog(null, userName + " 고객님, 환영합니다!");
							ticketUseCheck();	// 로그인 한 회원의 이용권 사용 정보 체크 함수
						} else {
							System.out.println("로그인 실패 > 로그인 정보 불일치");
							JOptionPane.showMessageDialog(null, "일치하는 회원 정보가 없습니다.");
							return;
						}
					} catch (HeadlessException | IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
	} // initialize() 끝

	public void setVisible(boolean b) {    // 외부 클래스에서 해당 클래스의 frame을 setVisible 호출할 경우 처리.
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}

}
