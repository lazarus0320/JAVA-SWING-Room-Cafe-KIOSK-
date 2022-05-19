import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// 회원 가입 클래스
public class SignUp extends ShareData {

	private JFrame frame;
	private JTextField nameField;
	private JTextField phoneField1;
	private JTextField idField;
	private JTextField passField;
	private JTextField pass2Field;
	private JTextField phoneField2;

	public boolean idchecker = false;
	public String checkedId = null;
	public boolean passchecker1 = false;
	public boolean passchecker2 = false;
	
	// idchecker, passchecker1, 2가 전부 true일때만 회원가입이 가능하도록 함.

	
	

	public Boolean userCheck(String id) throws IOException, ParseException {  // 입력한 아이디가 JSON 회원정보 내에 존재하는지 확인하는 함수
		// 경로에 있는 JSON 데이터를 열람함.
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		
		// jsonObj라는 이름으로 JSON 데이터 전체 값을 파싱받음.
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(file);

		// 받아온 값중 회원정보에 해당하는 배열 부분만을 JSONArray 형태로 받아옴.
		JSONArray accountArr = (JSONArray) jsonObj.get("회원정보");
		System.out.println(accountArr);
		for (int i = 0; i < accountArr.size(); i++) {
			// 배열 내부의 map들에 대해 JSONObject 형태로 받아오고 id키의 value값과 사용자가 입력한 id값과 같은 것이 있을 경우 false를 반환함.
			JSONObject obj = (JSONObject) accountArr.get(i);
			if (obj.get("id").equals(id)) {
				return false;
			}
		} // JSON 회원정보에 사용자가 입력한 아이디가 없으므로 true를 반환함.
		return true;
	}
	
	public void userAdd(String id, String pass, String name, String phone) throws IOException, ParseException{ // 사용자가 입력한 회원정보를 JSON 회원정보에 새로운 값으로 추가하는 함수 호출.
		// JSON의 회원정보에 대한 데이터만 파싱해서 accountArr에 받아온다.
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		
		// JSONObject 객체를 생성해서 map을 구성함.
		JSONObject child = new JSONObject();
		child.put("id", id);
		child.put("password", pass);
		child.put("name", name);
		child.put("phone", phone);
		child.put("timeTicket", "0");
		child.put("dayTicket", "0");
		child.put("timeTicketUse", "false");
		child.put("dayTicketUse", "false");
		child.put("startTicketTime", "X");
		child.put("rentRoomNum", "X");
		accountArr.add(child);	// 구성한 Map을 배열에다가 추가함.
		
		jsonObj.put("회원정보", accountArr);  // 구성한 배열을 Map에다가 추가함.
		// map바깥에 array바깥에 map으로 싸여있는 JSON 구조를 생성함.
		
		try { 
			// JSON 파일을 열람.
			FileOutputStream fileOutputStream2 = new FileOutputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
			OutputStreamWriter OutputStreamWriter2 = new OutputStreamWriter(fileOutputStream2, "utf-8");
			BufferedWriter file2 = new BufferedWriter(OutputStreamWriter2);
			
			// 위에서 만들어낸 Map을 string으로 형반환해서 파일에 작성함. JSON 파일은 반드시 string 형태로 작성되어야함.
			System.out.println(jsonObj.toJSONString());
			file2.write(jsonObj.toJSONString()); 
			file2.flush(); 
			file2.close(); 
		} catch (IOException e) { 
				e.printStackTrace(); 
		}
	}

	public SignUp() {
		// frame 설정
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		// panel 설정
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		// frame위에 getContentPane()위에 panel 올림.
		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 60));
		lblNewLabel.setBounds(47, 28, 291, 93);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(103, 440, 151, 60);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(103, 521, 151, 60);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_1_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		lblNewLabel_1_1_1.setBounds(103, 205, 151, 60);
		panel.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_1_1_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		lblNewLabel_1_1_1_1.setBounds(103, 285, 151, 60);
		panel.add(lblNewLabel_1_1_1_1);

		nameField = new JTextField();
		nameField.setFont(new Font("굴림", Font.PLAIN, 25));
		nameField.setBounds(330, 454, 246, 45);
		panel.add(nameField);
		nameField.setColumns(10);

		phoneField1 = new JTextField();
		phoneField1.setFont(new Font("굴림", Font.PLAIN, 25));
		phoneField1.setColumns(10);
		phoneField1.setBounds(434, 535, 79, 45);
		panel.add(phoneField1);

		idField = new JTextField();
		idField.setFont(new Font("굴림", Font.PLAIN, 25));
		idField.setColumns(10);
		idField.setBounds(330, 219, 246, 45);
		panel.add(idField);

		passField = new JTextField();
		passField.setFont(new Font("굴림", Font.PLAIN, 25));
		passField.setColumns(10);
		passField.setBounds(330, 299, 246, 45);
		panel.add(passField);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		lblNewLabel_1_1_1_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		lblNewLabel_1_1_1_1_1.setBounds(103, 370, 206, 60);
		panel.add(lblNewLabel_1_1_1_1_1);

		pass2Field = new JTextField();
		pass2Field.setFont(new Font("굴림", Font.PLAIN, 25));
		pass2Field.setColumns(10);
		pass2Field.setBounds(330, 375, 246, 45);
		panel.add(pass2Field);

		JLabel idcheckLabel = new JLabel(
				"\uC544\uC774\uB514\uB294 \uCD5C\uC18C 8\uAE00\uC790 \uC774\uC0C1\uC73C\uB85C \uC124\uC815\uD558\uC138\uC694.");
		idcheckLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		idcheckLabel.setBounds(739, 203, 409, 76);
		panel.add(idcheckLabel);

		JLabel lblNewLabel_2_1 = new JLabel(
				"\uBE44\uBC00\uBC88\uD638\uB294 \uC601\uC5B4, \uC22B\uC790\uB97C \uD63C\uC6A9\uD558\uC5EC");
		lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(724, 451, 430, 76);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel(
				"8\uAE00\uC790 \uC774\uC0C1, 16\uAE00\uC790 \uBBF8\uB9CC\uC73C\uB85C \uC124\uC815\uD558\uC138\uC694.");
		lblNewLabel_2_1_1.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(724, 505, 412, 76);
		panel.add(lblNewLabel_2_1_1);
		
		// 회원가입 버튼 설정
		JButton signupBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		signupBtn.setBackground(new Color(255, 69, 0));
		signupBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		signupBtn.setBounds(938, 642, 142, 82);
		signupBtn.setFocusPainted(false);
		panel.add(signupBtn);
		
		// 취소 버튼 설정
		JButton cancelBtn = new JButton("\uCDE8\uC18C");
		cancelBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		cancelBtn.setBounds(748, 642, 142, 82);
		panel.add(cancelBtn);
		cancelBtn.setFocusPainted(false);
		
		// 중복확인 버튼 설정
		JButton idcheckBtn = new JButton("\uC911\uBCF5 \uD655\uC778");
		idcheckBtn.setFont(new Font("굴림", Font.PLAIN, 18));
		idcheckBtn.setBounds(598, 219, 114, 46);
		panel.add(idcheckBtn);

		phoneField2 = new JTextField();
		phoneField2.setFont(new Font("굴림", Font.PLAIN, 25));
		phoneField2.setColumns(10);
		phoneField2.setBounds(539, 535, 79, 45);
		panel.add(phoneField2);

		JLabel lblNewLabel_3 = new JLabel("-");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3.setBounds(416, 550, 21, 20);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("-");
		lblNewLabel_3_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(519, 550, 21, 20);
		panel.add(lblNewLabel_3_1);

		// 휴대폰 번호 콤보박스 설정
		String phoneNums[] = { "010", "011", "012", "013", "014", "015", "016", "017", "018", "019" };
		JComboBox phoneComboBox = new JComboBox(phoneNums); // 인수로 콤보박스에 들어갈 선택지들을 배열로 받음.
		phoneComboBox.setBounds(330, 535, 74, 46);
		panel.add(phoneComboBox);

		JLabel passcheckLabel1 = new JLabel("");
		passcheckLabel1.setFont(new Font("굴림", Font.PLAIN, 20));
		passcheckLabel1.setBounds(615, 299, 478, 46);
		panel.add(passcheckLabel1);
		
		JLabel passcheckLabel2 = new JLabel("");
		passcheckLabel2.setFont(new Font("굴림", Font.PLAIN, 20));
		passcheckLabel2.setBounds(615, 374, 478, 46);
		panel.add(passcheckLabel2);

		KeyListener passkey = new KeyListener() {	// 키 리스너 이벤트 설정, 이름 : passkey
			// 키 리스너는 반드시 keyTyped, keyReleased, keyPressed 모두 선언하는게 기본 양식임.
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {	// 키 눌렀다가 땠을 때 동작하는 함수. 먼저 어떻게 동작하는지 만들어 놓고, 나중에 비밀번호 입력 필드에 적용할 수 있도록 함.
				// TODO Auto-generated method stub
				if (passField.getText().equals("")) {	// 만약 비번 입력창이 빈칸 상태라면 passcheckLabel1의 텍스트를 빈칸으로 만든다.
					passcheckLabel1.setText(null);
				} else {
					if (passField.getText().length() < 8 || passField.getText().length() >= 16) {	// 8~15자리가 아닌 경우
						passcheckLabel1.setForeground(Color.red);
						passcheckLabel1.setText("비밀번호 길이는 8이상 16미만으로 설정해주세요.");
						passchecker1 = false;
					} else if (passField.getText().length() >= 8 && passField.getText().length() < 16){		// 8~15자리 조건을 충족할 경우
						passcheckLabel1.setForeground(Color.green);
						passcheckLabel1.setText("사용 가능한 비밀번호입니다.");
						passchecker1 = true;
					}
				}
				System.out.println(passchecker1);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		KeyListener passkey2 = new KeyListener() {	// 키 리스너 이벤트 설정, 이름 : passkey2

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {  // 키 눌렀다가 땠을 때 동작하는 함수. 먼저 어떻게 동작하는지 만들어 놓고, 나중에 비밀번호 재확인 입력 필드에 적용할 수 있도록 함.
				// TODO Auto-generated method stub
				if (pass2Field.getText().equals("")) {
					passcheckLabel2.setText(null);
				}
				else if (passField.getText().equals(pass2Field.getText())){  // 비번 입력 필드에서 입력한 값과 비번 재확인 필드에서 입력한 값이 같을때
					passchecker2 = true;
					passcheckLabel2.setForeground(Color.green);
					passcheckLabel2.setText("비밀번호 일치");
				} else {
					passchecker2 = false;
					passcheckLabel2.setForeground(Color.red);
					passcheckLabel2.setText("비밀번호 불일치");
				}
				System.out.println(passchecker2);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		KeyListener idkey = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {  // 키 눌렀다가 땠을 때 동작하는 함수. 먼저 어떻게 동작하는지 만들어 놓고, 나중에 아이디 입력 필드에 적용할 수 있도록 함.
				// TODO Auto-generated method stub
				if (idField.getText() != checkedId) {  // 중복확인 통과된 id값과 현재 아이디 필드에 입력된 값이 다를 경우 다시 중복확인을 받도록 만듬.(중복확인 받아놓고 아이디 입력값을 바꿀경우 다시 중복확인을 받도록 유도)
					idcheckLabel.setForeground(Color.black);
					idcheckLabel.setText("아이디는 최소 8글자 이상으로 설정하세요.");
					idchecker = false;
					System.out.println(idchecker);
				} 

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		// 위에서 만들었던 3가지 키 리스너들을 어디에 적용할지 지정함.
		passField.addKeyListener(passkey);
		pass2Field.addKeyListener(passkey2);
		idField.addKeyListener(idkey);
		
		
		// 취소버튼을 누르면 로그인 클래스로 돌아가도록 함.
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn log = new logIn();
				log.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		
		
		// 중복확인 버튼 이벤트 리스너 설정
		idcheckBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = idField.getText();  // 현재 아이디 필드에 입력되어 있는 값을 id 변수에 받음.
				try {	// 아이디는 최소 8자리 이상으로 맞추도록 함.
					if (id.length() < 8) {
						JOptionPane.showMessageDialog(frame, "8자리 이상으로 입력해주세요.", "중복 확인", JOptionPane.WARNING_MESSAGE);
					} else if (userCheck(id)) {		// 입력한 아이디가 JSON 회원정보 내에 존재하는지 확인하는 함수 호출
						JOptionPane.showMessageDialog(frame, "가입 가능한 아이디입니다.", "중복 확인", JOptionPane.PLAIN_MESSAGE);
						idchecker = true;
						checkedId = id;		// 중복확인이 완료된 id값을 checkedId 변수에 받음.
						idcheckLabel.setForeground(Color.green);
						idcheckLabel.setText("중복 확인 완료");
						
						
					} else {
						JOptionPane.showMessageDialog(frame, "중복된 아이디입니다.", "중복 확인", JOptionPane.WARNING_MESSAGE);
					}
					System.out.println(idchecker);
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
// 회원가입 버튼 이벤트 리스너 설정
		signupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 화면의 모든 필드값에 대한 입력값들을 전부 변수로 받아옴.
				String id = idField.getText();
				String pass = passField.getText();
				String pass2 = pass2Field.getText();
				String name = nameField.getText();
				String phone = phoneComboBox.getSelectedItem() + phoneField1.getText() + phoneField2.getText();
				

				if (id.equals("")  || pass.equals("") || pass2.equals("") || name.equals("") || phoneField1.getText().equals("")
						|| phoneField2.getText().equals("")) {
					// 값이 하나라도 입력되지 않은 것이 있을 경우
					JOptionPane.showMessageDialog(null, "모든 값을 입력해주세요.", "입력값 에러", JOptionPane.ERROR_MESSAGE);
				}
				else if (idchecker == true && passchecker1 == true && passchecker2 == true) {
					// 아이디 중복확인, 비번 양식, 비번 재입력까지 모두 만족했을 경우 
					System.out.println("회원가입 성공");
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다! 다시 로그인을 시도하세요.");
					try {
						userAdd(id, pass, name, phone);	// 사용자가 입력한 회원정보를 JSON 회원정보에 새로운 값으로 추가하는 함수 호출.
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					logIn login = new logIn();	// 회원가입 완료되었으므로 로그인 클래스를 호출함.
					login.setVisible(true);
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "입력 값을 다시 확인해주세요.", "입력값 에러", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

	}

	public void setVisible(boolean b) {  // 외부 클래스에서 해당 클래스의 frame을 setVisible 호출할 경우 처리
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
