import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Ticket extends ShareData{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ticket window = new Ticket();
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
	public Ticket() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
		
		int hour = (Integer.parseInt(userTimeTicket)) / 60;
		int min = (Integer.parseInt(userTimeTicket)) % 60;
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
		
		dayLabel.setText(userDayTicket + "��");
		
		timeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int answer = JOptionPane.showConfirmDialog(frame, "�ð����� ����Ͻðڽ��ϱ�? ���� ����ȭ������ �̵��˴ϴ�.", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //����ڰ� yes�� ������ ���
					System.out.println("�ð��� ���, ���� �̵�.");
					timeTicketUse = true;
					LocalDateTime now = LocalDateTime.now();
					String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� HH�� mm�� ss��"));
					System.out.println(formatedNow);
					startTicketTime = formatedNow;
					
					Date date = new Date();
					long timeMilli = date.getTime();
					System.out.println(timeMilli);
					startTimeMilli = timeMilli;
					
					MainStage ms = new MainStage();
					ms.setVisible(true);
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
				
				int answer = JOptionPane.showConfirmDialog(frame, "�Ⱓ���� ����Ͻðڽ��ϱ�? ���� ����ȭ������ �̵��˴ϴ�.", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //����ڰ� yes�� ������ ���
					System.out.println("�Ⱓ�� ���, ���� �̵�.");
					dayTicketUse = true;
					LocalDateTime now = LocalDateTime.now();
					String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy�� MM�� dd�� HH�� mm�� ss��"));
					System.out.println(formatedNow);
					startTicketTime = formatedNow;
					
					Date date = new Date();
					long timeMilli = date.getTime();
					System.out.println(timeMilli);
					startTimeMilli = timeMilli;
					
					MainStage ms = new MainStage();
					ms.setVisible(true);
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
