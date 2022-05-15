import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

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
	
	public String roomState[] = {"false", "false", "false", "false", "false", "false", "false", "false", "false", "false"};
	
	public void RoomStateCheck() throws IOException, ParseException{
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("룸정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			roomState[i] = (String)obj.get("room");	
		}
		System.out.println(roomState);
		
	}
	
	public void RentedRoomCheck() throws IOException, ParseException{ // 이용권 써놓고 프로그램 껐다 켜도 데이터 불러오도록 만듬
		
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("name").equals(userName)) {
				startTicketTime = (String)obj.get("startTicketTime"); // 이용권 사용시간(밀리초)을 불러옴
				if ((startTicketTime.equals("X") == false) && ((String)obj.get("timeTicketUse")).equals("true")) {
					timeTicketUse = true;
					String roomNumStr = (String)obj.get("rentRoomNum");
					selectedRoomNum = Integer.parseInt(roomNumStr);
					System.out.println("룸넘버" + selectedRoomNum);
				}
				else if ((startTicketTime.equals("X") == false) && ((String)obj.get("dayTicketUse")).equals("true")) {
					dayTicketUse = true;
					String roomNumStr = (String)obj.get("rentRoomNum");
					selectedRoomNum = Integer.parseInt(roomNumStr);
					System.out.println("룸넘버" + selectedRoomNum);
				}
			}
		}

	}
	
	public void roomStateDataCheck(int roomNum) throws IOException, ParseException{
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("룸정보");
		for (int i = 0; i < accountArr.size(); i++) {
			if (i == roomNum) {
			JSONObject obj = (JSONObject)accountArr.get(i);
				obj.put("room", "exist");
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
	

	
	public void checkRentTime() throws IOException, ParseException {
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("회원정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("name").equals(userName)) {
				if (timeTicketUse == true) {
					obj.put("timeTicketUse", "true");
				}
				else if (dayTicketUse == true) {		
					obj.put("dayTicketUse", "true");
				}
				Date date = new Date();
				long timeMilli = date.getTime();
				obj.put("startTicketTime", Long.toString(timeMilli));
				obj.put("rentRoomNum", Integer.toString(selectedRoomNum));
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
	public RoomStage() {
		try {
			RoomStateCheck();
		} catch (IOException | ParseException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			RentedRoomCheck();
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
		
		JButton[] rooms = new JButton[10];
		for(int i = 0; i < rooms.length; i++) {
			rooms[i] = new JButton();
			rooms[i].setText(Integer.toString(i+1));
			if (startTicketTime.equals("X") == false) {
				rooms[i].setBackground(new Color(125, 125, 125));
			}
			else if (roomState[i].equals("exist")) {
				rooms[i].setBackground(new Color(255, 69, 0));
				
			}
			else if(roomState[i].equals("None")){
				rooms[i].setBackground(new Color(65, 105, 225));
			}
			rooms[i].setFocusPainted(false);
			rooms[i].setFont(new Font("돋움", Font.BOLD, 30));
			panel.add(rooms[i]);
		}
		
		
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
		

		rooms[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (startTicketTime.equals("X") == false) {
            		return;
            	}
            	else if (roomState[0].equals("exist")) {
                	JOptionPane.showMessageDialog(null, "이미 대실한 룸입니다. 다른 룸을 선택해주세요.");
                	return;
                }
                LineBorder tb = new LineBorder(Color.yellow, 5, true);
                for (int j = 0; j < rooms.length; j++) {        		
	        		rooms[j].setBorder(null);
	        		rooms[j].setSelected(false);
                }
                rooms[0].setBorder(tb);
                rooms[0].setSelected(true);
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
		
		
		
		JButton checkBtn = new JButton("\uB300\uC2E4\uD558\uAE30");
		checkBtn.setForeground(new Color(255, 255, 255));
		checkBtn.setBackground(new Color(220, 20, 60));
		checkBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		checkBtn.setBounds(275, 543, 216, 172);
		checkBtn.setFocusPainted(false);
		panel.add(checkBtn);
		
		if (startTicketTime.equals("X") == false) {
    		checkBtn.setText("퇴실 하기");
    		LineBorder tb = new LineBorder(Color.green, 5, true);
    		rooms[selectedRoomNum-1].setBorder(tb);
    	}
		
		JButton orderBtn = new JButton("\uC74C\uC2DD\uC8FC\uBB38");
		orderBtn.setForeground(Color.WHITE);
		orderBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		orderBtn.setBackground(new Color(65, 105, 225));
		orderBtn.setBounds(689, 543, 216, 172);
		orderBtn.setFocusPainted(false);
		panel.add(orderBtn);
		
		checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	for (int i = 0; i < rooms.length; i++) {
            		if (rooms[i].isSelected()) {
            			int answer = JOptionPane.showConfirmDialog(null, String.format("%d번 룸을 대실하시겠습니까?", i+1), "confirm", JOptionPane.YES_NO_OPTION);
            			if(answer == JOptionPane.YES_OPTION) {
            				try {
								roomStateDataCheck(i);
							} catch (IOException | ParseException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
            				selectedRoomNum = i+1;
            				System.out.println(selectedRoomNum);
            				try {
								checkRentTime();
								System.out.println("대실 완료!");
							} catch (IOException | ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
            				// RoomStage 퇴실기능 적용 화면으로 전환
            				RoomStage rs2 = new RoomStage();
            				rs2.setVisible(true);
            				frame.setVisible(false);
            				return;
            			}
            			else {
            				return;
            			}
            		}
            			
            	}
            		JOptionPane.showMessageDialog(null, "대실할 룸을 먼저 선택하세요.");
            }
		});
	}
	
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
	
}
