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

	
	

	public Boolean userCheck(String id) throws IOException, ParseException {
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();

		JSONObject jsonObj = (JSONObject) parser.parse(file);

		JSONArray accountArr = (JSONArray) jsonObj.get("회원정보");
		System.out.println(accountArr);
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject) accountArr.get(i);
			if (obj.get("id").equals(id)) {
				return false;
			}
		}
		return true;
	}
	
	public void userAdd(String id, String pass, String name, String phone) throws IOException, ParseException{
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
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
		accountArr.add(child);
		
		jsonObj.put("회원정보", accountArr);
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

	public SignUp() {

		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

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

		JButton signupBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		signupBtn.setBackground(new Color(255, 69, 0));
		signupBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		signupBtn.setBounds(938, 642, 142, 82);
		signupBtn.setFocusPainted(false);
		panel.add(signupBtn);

		JButton cancelBtn = new JButton("\uCDE8\uC18C");
		cancelBtn.setFont(new Font("굴림", Font.PLAIN, 25));
		cancelBtn.setBounds(748, 642, 142, 82);
		panel.add(cancelBtn);
		cancelBtn.setFocusPainted(false);

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

		String phoneNums[] = { "010", "011", "012", "013", "014", "015", "016", "017", "018", "019" };
		JComboBox phoneComboBox = new JComboBox(phoneNums);
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

		KeyListener passkey = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (passField.getText().equals("")) {
					passcheckLabel1.setText(null);
				} else {
					if (passField.getText().length() < 8 || passField.getText().length() >= 16) {
						passcheckLabel1.setForeground(Color.red);
						passcheckLabel1.setText("비밀번호 길이는 8이상 16미만으로 설정해주세요.");
						passchecker1 = false;
					} else if (passField.getText().length() >= 8 && passField.getText().length() < 16){
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
		
		KeyListener passkey2 = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (pass2Field.getText().equals("")) {
					passcheckLabel2.setText(null);
				}
				else if (passField.getText().equals(pass2Field.getText())){
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
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (idField.getText() != checkedId) {
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
		
		passField.addKeyListener(passkey);
		pass2Field.addKeyListener(passkey2);
		idField.addKeyListener(idkey);

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn log = new logIn();
				log.setVisible(true);
				frame.setVisible(false);
			}
		});

		idcheckBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id = idField.getText();
				try {
					if (id.length() < 8) {
						JOptionPane.showMessageDialog(frame, "8자리 이상으로 입력해주세요.", "중복 확인", JOptionPane.WARNING_MESSAGE);
					} else if (userCheck(id)) {
						JOptionPane.showMessageDialog(frame, "가입 가능한 아이디입니다.", "중복 확인", JOptionPane.PLAIN_MESSAGE);
						idchecker = true;
						checkedId = id;
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
// 회원가입 버튼 클릭
		signupBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String pass = passField.getText();
				String pass2 = pass2Field.getText();
				String name = nameField.getText();
				String phone = phoneComboBox.getSelectedItem() + phoneField1.getText() + phoneField2.getText();


				if (id.equals("")  || pass.equals("") || pass2.equals("") || name.equals("") || phoneField1.getText().equals("")
						|| phoneField2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "모든 값을 입력해주세요.", "입력값 에러", JOptionPane.ERROR_MESSAGE);
				}
				else if (idchecker == true && passchecker1 == true && passchecker2 == true) {
					System.out.println("회원가입 성공");
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다! 다시 로그인을 시도하세요.");
					try {
						userAdd(id, pass, name, phone);
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					// DataJson에 회원정보 추가해서 쓰기
					logIn login;
					login = new logIn();
					login.setVisible(true);
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "입력 값을 다시 확인해주세요.", "입력값 에러", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}