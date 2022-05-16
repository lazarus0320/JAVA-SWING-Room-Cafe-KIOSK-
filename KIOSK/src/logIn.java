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
	
	public void ticketUseCheck() {
		if (timeTicketUse.equals("true") || dayTicketUse.equals("true")){
			RoomStage rs = new RoomStage();
			rs.setVisible(true);
			frame.setVisible(false);
		} else {
			Ticket tk = new Ticket();
			tk.setVisible(true);
			frame.setVisible(false);
			return;
		}
	}
	
	
	public Boolean loginCheck(String id, String pass) throws IOException, ParseException{
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		System.out.println(accountArr);
		for(int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("id").equals(id) && obj.get("password").equals(pass)) {
				userStatus = obj;
				userName = (String)obj.get("name");
				userId = (String)obj.get("id");
				userPass = (String)obj.get("password");
				userPhone = (String)obj.get("phone");
				userTimeTicket = (String) obj.get("timeTicket");  // 시간권 시간
				userDayTicket = (String)obj.get("dayTicket");	  // 기간권 시간
				timeTicketUse = (String)obj.get("timeTicketUse");
				dayTicketUse =  (String)obj.get("dayTicketUse");
				
				System.out.println(userName);
				System.out.println(userId);
				System.out.println(userPass);
				System.out.println(userPhone);
				System.out.println(userTimeTicket);
				System.out.println(userDayTicket);
				return true;
			}
		}
		return false;
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(255, 69, 0), 5));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(350, 200, 500, 400);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
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
		
		idField = new JTextField();
		idField.setBounds(160, 115, 250, 40);
		panel.add(idField);
		idField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setBounds(160, 195, 250, 40);
		panel.add(passField);
		
		JButton newAccountBtn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		newAccountBtn.setFocusPainted(false);
		newAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		newAccountBtn.setBackground(new Color(147, 112, 219));
		newAccountBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		newAccountBtn.setBounds(45, 290, 190, 80);
		panel.add(newAccountBtn);
		
		JButton loginBtn = new JButton("\uB85C\uADF8\uC778");
		loginBtn.setBackground(new Color(255, 127, 80));
		loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		loginBtn.setBounds(263, 290, 190, 80);
		loginBtn.setFocusPainted(false);
		panel.add(loginBtn);
		
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		
		newAccountBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SignUp signup = new SignUp();
				signup.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String testId = idField.getText();
				String testPass = "";
				for(int i = 0; i < passField.getPassword().length; i++) {
					testPass += passField.getPassword()[i];
				}
				System.out.println(testId);
				System.out.println(testPass);
				if(testId.equals("") || testPass.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					System.out.println("로그인 실패 > 로그인 정보 미입력");
				}
				
				else if(testId != null && testPass != null) {		
					try {
						try {
							if(loginCheck(testId, testPass)) {
								if (testId.equals("admin0320") && testPass.equals("admin0320")) {
									int answer = JOptionPane.showConfirmDialog(frame, "관리자 모드로 로그인 하시겠습니까?", "관리자 모드",JOptionPane.YES_NO_OPTION );
									if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
										System.out.println("관리자 모드 접속");
										Admin admin = new Admin();
										admin.setVisible(true);
										frame.setVisible(false);
										return;
									} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
										System.out.println("일반 로그인");
										ticketUseCheck();
										return;
									}
									
								}
								System.out.println("로그인 성공");
								JOptionPane.showMessageDialog(null, userName + " 고객님, 환영합니다!");
								ticketUseCheck();
							} else {
								System.out.println("로그인 실패 > 로그인 정보 불일치");
								JOptionPane.showMessageDialog(null, "일치하는 회원 정보가 없습니다.");
								return;
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				
			}
		});
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}

}
