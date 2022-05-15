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
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy��MM��dd�� HH��mm��ss��"));
		System.out.println(formatedNow);
		return formatedNow;
	}
	
	public void makeRentData(String time , int chargeVal, int changeVal) {
		try { 
			try {
				// ���丮 ����
				Path directoryPath = Paths.get("C:\\KIOSK\\KIOSK_RECEIPT");
				Files.createDirectory(directoryPath);
				System.out.println(directoryPath + " ���丮�� �����Ǿ����ϴ�.");
				} catch (FileAlreadyExistsException e) {
				System.out.println("���丮�� �̹� �����մϴ�");
				} catch (NoSuchFileException e) {
				System.out.println("���丮 ��ΰ� �������� �ʽ��ϴ�");
				}catch (IOException e) {
				e.printStackTrace();
				}
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\KIOSK\\KIOSK_RECEIPT\\" + userName + time  + ".txt");
			OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
			BufferedWriter file = new BufferedWriter(OutputStreamWriter);

			file.write("��ī�� KIOSK ������"); 
			file.newLine();
			
			file.write("����ڹ�ȣ : 123-4567-890    ��ǥ�� : ������");
			file.newLine();
			file.write("ȸ�� �̸� : " + userName); 
			file.newLine();
			file.write("���� �ð� : " + time); 
			file.newLine();
			file.write("==============================="); 
			file.newLine();
			file.write("�̿�� �̸�            �ݾ�"); 
			file.newLine();
			file.write("==============================="); 
			file.newLine();
			file.write(selectedTicketName + "                  " + selectedTicketPrice + "��"); 
			file.newLine();
			file.write("------------------------------------------------------");
			file.newLine();
			file.write("���ұݾ�              " + chargeVal + "��");
			file.newLine();
			file.write("�Ž�����              " + changeVal + "��");		
			
			
			file.flush(); 
			file.close(); 
			System.out.println("C:\\KIOSK\\KIOSK_RECEIPT\\ ��ο� ������ ���� ������.");
		} catch (IOException e) { 
				e.printStackTrace(); 
		}
	}
	
	public void addTicketData() {
		System.out.println("addTicketData �޼��� ����");
		if (selectedTicketName == "1�ð�"){
			try {
				addTicketTime(60);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "2�ð�"){
			try {
				addTicketTime(120);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "3�ð�"){
			try {
				addTicketTime(180);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "4�ð�"){
			try {
				addTicketTime(240);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "5�ð�"){
			try {
				addTicketTime(300);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "10�ð�"){
			try {
				addTicketTime(600);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "1��"){
			try {
				addTicketDay(1);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "2��"){
			try {
				addTicketDay(2);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "3��"){
			try {
				addTicketDay(3);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "5��"){
			try {
				addTicketDay(5);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (selectedTicketName == "10��"){
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
		JSONArray accountArr = (JSONArray)jsonObj.get("ȸ������");
		
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
		JSONArray accountArr = (JSONArray)jsonObj.get("ȸ������");
		
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
		frame.setTitle("�̿�� ����");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 484, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC774\uC6A9\uAD8C \uAD6C\uB9E4");
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 25));
		lblNewLabel.setBounds(171, 10, 145, 39);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC120\uD0DD\uD55C \uC774\uC6A9\uAD8C :");
		lblNewLabel_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(49, 93, 145, 39);
		panel.add(lblNewLabel_1);
		
		chargeField = new JTextField();
		chargeField.setBounds(215, 233, 205, 39);
		panel.add(chargeField);
		chargeField.setColumns(10);
		
		JLabel ticketNameLabel = new JLabel("");
		ticketNameLabel.setFont(new Font("����", Font.PLAIN, 20));
		ticketNameLabel.setBounds(215, 100, 199, 30);
		panel.add(ticketNameLabel);
		
		JLabel lblNewLabel_2 = new JLabel("\uC774\uC6A9\uAD8C \uAC00\uACA9    :");
		lblNewLabel_2.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(49, 160, 145, 24);
		panel.add(lblNewLabel_2);
		
		JLabel ticketPriceLabel = new JLabel("");
		ticketPriceLabel.setFont(new Font("����", Font.PLAIN, 20));
		ticketPriceLabel.setBounds(215, 160, 199, 30);
		panel.add(ticketPriceLabel);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uAE08\uC561 \uC785\uB825       :");
		lblNewLabel_2_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(49, 235, 145, 24);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uAC70\uC2A4\uB984\uB3C8        :");
		lblNewLabel_2_1_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(49, 304, 145, 24);
		panel.add(lblNewLabel_2_1_1);
		
		JButton cancelBtn = new JButton("\uCDE8\uC18C");
		cancelBtn.setFont(new Font("����", Font.PLAIN, 25));
		cancelBtn.setBounds(12, 391, 222, 60);
		panel.add(cancelBtn);
		
		JButton payBtn = new JButton("\uACB0\uC81C");
		payBtn.setFont(new Font("����", Font.PLAIN, 25));
		payBtn.setBounds(250, 391, 222, 60);
		panel.add(payBtn);
		
		
		ticketNameLabel.setText(selectedTicketName);
		ticketPriceLabel.setText(Integer.toString(selectedTicketPrice) + "��");
		
		JLabel changeLabel = new JLabel((String) null);
		changeLabel.setFont(new Font("����", Font.PLAIN, 20));
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
					chargeValue = Integer.parseInt(chargeField.getText()); // ����ڰ� �����Ϸ��� �ݾ�
				} catch(NumberFormatException e1) {
					chargeField.setText("");
				}
				int price = selectedTicketPrice;  // Ƽ�� ����
				int realChargeVal = chargeValue - price;
				if (realChargeVal >= 0) {
					changeLabel.setText(Integer.toString(chargeValue - price)); // �Ž����� ���
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
				int answer = JOptionPane.showConfirmDialog(frame, "�����Ͻðڽ��ϱ�?", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //����ڰ� yes�� ������ ���
					int chargeVal = Integer.parseInt(chargeField.getText());
					int changeVal = Integer.parseInt(changeLabel.getText());
					if (selectedTicketPrice > chargeVal) {
						System.out.println("���� �ݾ��� Ȯ�����ּ���.");
						JOptionPane.showMessageDialog(frame, "���� �ݾ��� Ȯ�����ּ���.", "Message", JOptionPane.WARNING_MESSAGE );
						return;
					}
					System.out.println("���� �Ϸ�.");
					String rentTime = makeRentTime();
					
					addTicketData();
					int answer2 = JOptionPane.showConfirmDialog(frame, "���� �Ǿ����ϴ�. �������� ����Ͻðڽ��ϱ�?", "confirm",JOptionPane.YES_NO_OPTION );
					if(answer2==JOptionPane.YES_OPTION){  //����ڰ� yes�� ������ ���
						System.out.println("������ ��� ����.");
						makeRentData(rentTime, chargeVal, changeVal);

						//�α��� �� ������� ���� ��� �߰� ����.
					} else{  //����ڰ� Yes �̿��� ���� ������ ���
						System.out.println("������ ��� �ź�.");
					}
					//�̿���� ���� �� ���� �ֵ��� �ϴ� ��� �߰�
					int answer3 = JOptionPane.showConfirmDialog(frame, selectedRoomNum + "���뿡�� �̿���� ��� ����Ͻðڽ��ϱ�?", "confirm", JOptionPane.YES_NO_OPTION );
					if(answer3==JOptionPane.YES_OPTION){  //����ڰ� yes�� ������ ���
						System.out.println("�̿�� ��� ��� ����. �������� �̵�");
						timeTicketUse = true;
						MainStage ms = new MainStage();
						ms.setVisible(true);
						frame.setVisible(false);

						//�α��� �� ������� ���� ��� �߰� ����.
					} else{  //����ڰ� Yes �̿��� ���� ������ ���
						System.out.println("�̿�� ��� ��� �ź�.");
					}
				} else{  //����ڰ� Yes �̿��� ���� ������ ���
					System.out.println("���� ���.");
				}
				
			}
		});
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
