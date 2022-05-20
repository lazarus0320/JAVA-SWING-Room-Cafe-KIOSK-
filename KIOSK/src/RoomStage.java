import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RoomStage extends ShareData{

	private JFrame frame;
	// 10개 룸의 대실 여부를 나타내는 배열. exist는 대실 불가능. None는 대실 가능한 상태임.
	public String roomState[] = {"None", "None", "None", "None", "None", "None", "None", "None", "None", "None"};
	
	public void RoomStateCheck() throws IOException, ParseException{	// JSON 데이터의 룸정보를 받아와 roomState배열에 저장하는 함수.
		// JSON 파일 오픈
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("룸정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			roomState[i] = (String)obj.get("room");	
			System.out.println(roomState[i]);
		}
	}
	
	public void RentedRoomCheck() throws IOException, ParseException{ // 로그인한 계정이 대실을 했는지 체크하고 대실했던 룸의 방번호를 받아오는 함수
		// JSON 데이터에서 회원정보 부분을 받아옴
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			// 로그인 계정의 이름과 데이터에 있는 계정이 일치하는 map이 있는지 확인
			if (obj.get("name").equals(userName)) {
				/* ShareData의 static변수 startTicketTime에 이용권을 사용한 시간을 밀리초 형태로 담고있는 "startTicketTime" key의 value값을 받아옴.
				 만약 로그인 한 계정의 JSON 데이터에 "startTicketTime" key의 value값이 "X"라면 대실이 되지 않은 상태일 것이고,
				 "X"가 아니라면 이전에 대실을 한 상태라는 것을 이용한다.
				 */
				startTicketTime = (String)obj.get("startTicketTime"); 
				
				// startTicketTime은 이전에 대실을 했을 경우 밀리초값을 가지고 있기 때문에 첫번째 항은 true가 되고, timeTicketUse는 보유한 시간권을 사용하여 룸 선택 클래스로 진입했거나 시간권을 구매하자마자 즉시 사용하여 룸 선택 클래스로 진입했을 때 true를 가짐.
				if ((startTicketTime.equals("X") == false) && ((String)obj.get("timeTicketUse")).equals("true")) {
					timeTicketUse = "true"; // (String)obj.get("timeTicketUse")).equals("true")를 자주 사용할 것이기 때문에 static 변수에다가 저장해서 쓰는게 쓸데없는 연산을 줄일 수 있음.
					selectedRoomNum = Integer.parseInt((String)obj.get("rentRoomNum"));	// 선택한 룸에 대한 정보를 저장
					System.out.println("룸넘버" + selectedRoomNum);
				}
				else if ((startTicketTime.equals("X") == false) && ((String)obj.get("dayTicketUse")).equals("true")) {
					dayTicketUse = "true";
					selectedRoomNum = Integer.parseInt((String)obj.get("rentRoomNum"));
					System.out.println("룸넘버" + selectedRoomNum);
				}
			}
		}

	}
	
	public void roomStateDataCheck(int roomNum) throws IOException, ParseException{  // JSON 룸 정보 데이터에 접근해서 정보를 갱신하는 함수.
		// JSON 파일 오픈
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		// 룸 정보에 대한 데이터를 받아옴
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("룸정보");
		for (int i = 0; i < accountArr.size(); i++) {
			if (i == roomNum) {	// 사용자가 선택한 룸번호와 일치하는 경우 해당 map을 선택하고 "room" key의 value를 "exist"로 갱신함. (방 대실 상태)
			JSONObject obj = (JSONObject)accountArr.get(i);
				obj.put("room", "exist");
			}
		}
		
		try { 
			// JSON 파일을 다시 오픈하고 갱신된 값을 수정해서 저장함.
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
	

	
	public void checkRentTime() throws IOException, ParseException {	// 룸 대실 시간을 JSON에 기록하는 함수
		// JSON 오픈
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		// 회원정보 받아옴
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			// 로그인한 계정의 이름과 같은 value 값을 가지고 있는 map을 obj로 받음.
			if (obj.get("name").equals(userName)) {
				// 만약 시간권을 사용중인 상황이라면 
				if (timeTicketUse.equals("true")) {	// static 변수 timeTicketUse(시간권 사용 여부)가 true인 경우 해당 계정의 데이터 값에 있는 "timeTicketUse"을 "true"로 갱신함. (나중에 다시 로그인 했을 때 시간권 사용 여부를 데이터에서 받아와 확인하기 위함)
					obj.put("timeTicketUse", "true");
				}
				else if (dayTicketUse.equals("true")) {		
					obj.put("dayTicketUse", "true");
				}
				
				Date date = new Date();
				long timeMilli = date.getTime();	// 현재 시간을 밀리초단위로 받아서 timeMilli에 저장함. 단위가 커서 long으로 받았음.
				obj.put("startTicketTime", Long.toString(timeMilli));	// JSON은 문자열로 저장해야하므로 String으로 형변환하여 "startTicketTime"에 저장.
				obj.put("rentRoomNum", Integer.toString(selectedRoomNum));	// 선택했던 룸 넘버도 "rentRoomNum"에 저장.
			}
		}
		try { 
			// JSON 파일 오픈하고 변경된 데이터를 수정하고 저장함.
			FileOutputStream fileOutputStream2 = new FileOutputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
			OutputStreamWriter OutputStreamWriter2 = new OutputStreamWriter(fileOutputStream2, "utf-8");
			BufferedWriter file2 = new BufferedWriter(OutputStreamWriter2);
			//
			System.out.println(jsonObj.toJSONString());
			file2.write(jsonObj.toJSONString()); 
			file2.flush(); 
			file2.close(); 
		} catch (IOException e) { 
				e.printStackTrace(); 
		}
	}
	
	public void roomCheckout() throws IOException, ParseException {	// 룸 퇴실. startTicketTime = "X" 초기화, 사용시간만큼 해당 이용권의 누적합에서 차감.
		Date date = new Date();
		long checkoutTime = date.getTime();	// 퇴실 시간을 밀리초로 받아옴.
		long usedRoomTime = checkoutTime - Long.parseLong(startTicketTime);	// 퇴실시간 - 대실시간을 밀리초로 저장.
		long usedRoomTime_min = TimeUnit.MILLISECONDS.toMinutes(usedRoomTime);	// 분단위로 변환
		long usedRoomTime_day = TimeUnit.MILLISECONDS.toDays(usedRoomTime);		// 일단위로 변환
		usedRoomTime_day += 1;	// 기간권은 1분을 사용해도 퇴실시 1일이 차감되어야하므로 1을 더해줌.
		System.out.println("대실 이용 시간 : " + usedRoomTime_min + "분");
		System.out.println("대실 이용 시간 : " + (usedRoomTime_day) + "일");
		
		// JSON 오픈
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		// 회원정보 받아옴
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			// 로그인한 계정의 이름과 같은 value 값을 가지고 있는 map을 obj로 받음.
			if (obj.get("name").equals(userName)) {
				// 만약 시간권을 사용중인 상황이라면 
				selectedRoomNum = Integer.parseInt((String)obj.get("rentRoomNum"));	// 회원이 대실했던 룸 정보를 받아옴.
				obj.put("rentRoomNum", "X");	// 룸정보 초기화	
				if (timeTicketUse.equals("true")) {		// 시간권 사용 여부와 대실 시작시간을 초기화
					obj.put("timeTicketUse", "false");
					obj.put("startTicketTime", "X");
					timeTicketUse = "false";
					startTicketTime = "X";
					System.out.println("timeTicketUse : " + timeTicketUse);
					
					// 룸 사용시간만큼 해당 이용권의 누적합에서 차감.
					int getTimeTicket = Integer.parseInt((String)obj.get("timeTicket"));
					int changeTimeTicket = getTimeTicket - (int)usedRoomTime_min;
					if (changeTimeTicket < 0) changeTimeTicket = 0;
					if (changeTimeTicket == 0) {
						obj.put("timeTicket", "X");
					} else {
						obj.put("timeTicket", Integer.toString(changeTimeTicket));
					}
					
				}
				else if (dayTicketUse.equals("true")) {		
					obj.put("dayTicketUse", "false");
					obj.put("startTicketTime", "X");
					dayTicketUse = "false";
					startTicketTime = "X";
					System.out.println("dayTicketUse : " + dayTicketUse);
					
					int getTimeTicket = Integer.parseInt((String)obj.get("dayTicket"));
					int changeTimeTicket = getTimeTicket - (int)usedRoomTime_day;
					if (changeTimeTicket < 0) changeTimeTicket = 0;
					if (changeTimeTicket == 0) {
						obj.put("dayTicket", "X");
					} else {
						obj.put("dayTicket", Integer.toString(changeTimeTicket));
					}
					
				}
			}
		}
		
		JSONArray roomArr = (JSONArray)jsonObj.get("룸정보");
		for (int i = 0; i < roomArr.size(); i++) {
			JSONObject roomObj = (JSONObject)roomArr.get(i);
			if (i == selectedRoomNum-1) {	// 대실했던 룸을 대실 가능한 상태로 갱신해줌.
				roomObj.put("room", "None");
			}
		}
		selectedRoomNum = 0;
		try { 
			// JSON 파일 오픈하고 변경된 데이터를 수정하고 저장함.
			FileOutputStream fileOutputStream2 = new FileOutputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
			OutputStreamWriter OutputStreamWriter2 = new OutputStreamWriter(fileOutputStream2, "utf-8");
			BufferedWriter file2 = new BufferedWriter(OutputStreamWriter2);
			//
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
	public RoomStage() {
		try {
			RoomStateCheck();	//JSON 데이터의 룸정보를 받아와 roomState배열에 저장하는 함수 호출.
		} catch (IOException | ParseException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			RentedRoomCheck();	// 로그인한 계정이 대실을 했는지 체크하고 대실했던 룸의 방번호를 받아오는 함수 호출.
		} catch (IOException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("대실할 룸을 선택하세요.");
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("돋움체", Font.BOLD, 50));
		title.setBounds(285, 40, 616, 69);
		panel.add(title);
		
		System.out.println(startTicketTime);
		
		JButton[] rooms = new JButton[10];	// 객체 배열을 사용해 10개의 비슷한 기능을 하는 버튼들을 한번에 생성함.
		for(int i = 0; i < rooms.length; i++) {
			rooms[i] = new JButton();
			rooms[i].setText(Integer.toString(i+1));	// 방 번호를 1~10까지 지정해줌.
			if (startTicketTime.equals("X") == false) {	// 이용권 사용 시간이 "X"가 아닌 경우는 즉, 방을 대실하고 퇴실을 하지 않은 상태라는 것과 같음.
				rooms[i].setBackground(new Color(125, 125, 125));	// 방 대실이 불가능한 상황이므로 회색으로 처리
			}
			else if (roomState[i].equals("exist")) {	// 룸 대실 불가능한 경우 빨간색으로 지정
				rooms[i].setBackground(new Color(255, 69, 0));
				
			}	
			else if(roomState[i].equals("None")){		// 룸 대실 가능한 경우 파란색으로 지정
				rooms[i].setBackground(new Color(65, 105, 225));
			}
			rooms[i].setFocusPainted(false);
			rooms[i].setFont(new Font("돋움", Font.BOLD, 30));
			panel.add(rooms[i]);
		}
		
		// 버튼의 위치가 각각 다르므로 일일이 지정해주었음.
		rooms[0].setBounds(124, 170, 135, 114);
		rooms[1].setBounds(324, 170, 135, 114);
		rooms[2].setBounds(524, 170, 135, 114);
		rooms[3].setBounds(724, 170, 135, 114);
		rooms[4].setBounds(924, 170, 135, 114);
		rooms[5].setBounds(124, 356, 135, 114);
		rooms[6].setBounds(324, 356, 135, 114);
		rooms[7].setBounds(524, 356, 135, 114);
		rooms[8].setBounds(724, 356, 135, 114);
		rooms[9].setBounds(924, 356, 135, 114);
		
		
		// 각 버튼들의 버튼 클릭 이벤트 리스너. 반복문으로 적용이 되지 않아 일일이 따로 설정하였음.
		rooms[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {	// 해당 계정이 룸을 대실한 상태인 경우 버튼 눌러도 아무 동작 하지 않음.
            		return;	// 함수 탈출
            	}
            	else if (roomState[0].equals("exist")) {	// 룸 대실은 하지 않은 상태이지만 해당 룸을 대실할 수 없는 경우
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;	// 함수 탈출
                }
            	//룸 대실을 하지 않았고, 해당 룸을 대실할 수 있는 경우.
                LineBorder tb = new LineBorder(Color.yellow, 5, true);	// 객체 이름 tb :  윤곽선을 노란색, 크기 5로 지정
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);	// 모든 버튼 객체의 윤곽선 설정을 해제함.
	        		rooms[j].setSelected(false);	// 모든 버튼 객체의 선택 상태를 해제함.
                }
                rooms[0].setBorder(tb);	// 선택한 버튼 객체의 윤곽선만 노란색, 크기 5, 사각형 모양으로 지정
                rooms[0].setSelected(true); // 선택한 버튼 객체의 선택 상태만 true로 설정함.
            }
        });

		rooms[1].addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[1].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);
	        		rooms[j].setSelected(false);
                }
                rooms[1].setBorder(tb);
                rooms[1].setSelected(true);
            }
        });
		
		rooms[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[2].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);	 
	        		rooms[j].setSelected(false);
                }
                rooms[2].setBorder(tb);
                rooms[2].setSelected(true);
            }
        });
		
		rooms[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[3].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);	   
	        		rooms[j].setSelected(false);
                }
                rooms[3].setBorder(tb);
                rooms[3].setSelected(true);
            }
        });
		
		rooms[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[4].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);
	        		rooms[j].setSelected(false);
                }
                rooms[4].setBorder(tb);
                rooms[4].setSelected(true);
            }
        });
		
		rooms[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[5].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);	 
	        		rooms[j].setSelected(false);
                }
                rooms[5].setBorder(tb);
                rooms[5].setSelected(true);
            }
        });
		
		rooms[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[6].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);	
	        		rooms[j].setSelected(false);
                }
                rooms[6].setBorder(tb);
                rooms[6].setSelected(true);
            }
        });
		
		rooms[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[7].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);	
	        		rooms[j].setSelected(false);
                }
                rooms[7].setBorder(tb);
                rooms[7].setSelected(true);
            }
        });
		
		rooms[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[8].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);
	        		rooms[j].setSelected(false);
                }
                rooms[8].setBorder(tb);
                rooms[8].setSelected(true);
            }
        });
		
		rooms[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[9].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);	
	        		rooms[j].setSelected(false);
                }
                rooms[9].setBorder(tb);
                rooms[9].setSelected(true);
            }
        });
		
		
		// 대실/퇴실 버튼 설정
		JButton checkBtn = new JButton("\uB300\uC2E4\uD558\uAE30");
		checkBtn.setForeground(new Color(255, 255, 255));
		checkBtn.setBackground(new Color(220, 20, 60));
		checkBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		checkBtn.setBounds(275, 543, 216, 172);
		checkBtn.setFocusPainted(false);
		panel.add(checkBtn);
		
		if (startTicketTime.equals("X") == false) {	// 해당 계정이 대실을 한 상태인 경우
    		checkBtn.setText("퇴실하기");	// 버튼 이름을 퇴실하기로 설정.
    		LineBorder tb = new LineBorder(Color.green, 5, true);
    		rooms[selectedRoomNum-1].setBorder(tb);	// 대실한 방 번호의 윤곽선을 초록색, 5크기로 설정.
    		title.setText("대실중입니다.");	// 타이틀 라벨의 텍스트를 설정
    		
    	}
		
		// 음식 주문 버튼 설정
		JButton orderBtn = new JButton("\uC74C\uC2DD\uC8FC\uBB38");
		orderBtn.setForeground(Color.WHITE);
		orderBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		orderBtn.setBackground(new Color(65, 105, 225));
		orderBtn.setBounds(689, 543, 216, 172);
		orderBtn.setFocusPainted(false);
		panel.add(orderBtn);
		
		
		// 대실/퇴실 버튼 이벤트 리스너 설정 (퇴실 기능 미구현)
		checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) { // 해당 계정이 대실한 상태일 경우
            		int answer = JOptionPane.showConfirmDialog(null, String.format("퇴실하시겠습니까?"), "confirm", JOptionPane.YES_NO_OPTION);
        			if(answer == JOptionPane.YES_OPTION) {
        				try {
							roomCheckout();
						} catch (IOException | ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
        				Ticket tk = new Ticket();
        				tk.setVisible(true);
        				frame.setVisible(false);
        			} else {
        				return; // 함수 탈출
        			}
            	}
            	// 해당 계정이 대실을 하지 않은 상태인 경우.
            	for (int i = 0; i < rooms.length; i++) {
            		if (rooms[i].isSelected()) {	// 위에서 단 하나의 버튼 객체만 isSelected() 상태가 되기로 설정되었으므로 선택한 버튼이 어떤 것인지를 반복문으로 찾아낸다. 
            			int answer = JOptionPane.showConfirmDialog(null, String.format("%d번 룸을 대실하시겠습니까?", i+1), "confirm", JOptionPane.YES_NO_OPTION);
            			if(answer == JOptionPane.YES_OPTION) {
            				try {
								roomStateDataCheck(i); // JSON 룸 정보 데이터에 접근해서 정보를 갱신하는 함수. (선택한 룸을 대실 상태로 변경)
							} catch (IOException | ParseException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
            				selectedRoomNum = i+1;	// 선택한 룸 넘버를 갱신
            				System.out.println(selectedRoomNum);
            				try {
								checkRentTime();	// 룸 대실 시간을 JSON에 기록하는 함수
								System.out.println("대실 완료!");
							} catch (IOException | ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				// RoomStage 퇴실기능 적용 화면으로 전환. 같은 클래스를 호출하는 것이지만 여러가지 값이 바뀌어 있으므로 기존과 달라진 화면을 볼 수 있을 것이다.
            				RoomStage rs2 = new RoomStage();	
            				rs2.setVisible(true);
            				frame.setVisible(false);
            				return;	//함수 탈출
            			}
            			else {	// YES를 선택하지 않았을 경우 그냥 함수 탈출
            				return;
            			}
            		}
            			
            	}	// 선택한 룸 버튼이 없는데 대실하기 버튼을 눌렀을 경우
            		JOptionPane.showMessageDialog(null, "대실할 룸을 먼저 선택하세요.");
            }
		});
		
		
		// 음식 주문 버튼 이벤트 리스너. 미구현
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(startTicketTime.equals("X") == false) {
					JOptionPane.showMessageDialog(null, "음식 주문 기능입니다. 미구현.");
					return;
				}
				JOptionPane.showMessageDialog(null, "먼저 룸을 대실해야합니다.");
			}
		});
	}
	
	public void setVisible(boolean b) { // 외부 클래스에서 해당 클래스의 frame을 setVisible 호출할 경우 처리
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
	
}
