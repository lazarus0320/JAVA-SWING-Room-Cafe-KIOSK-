import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MainStage extends ShareData{

	private JFrame frame;
	
	public boolean[] roomState = {false,false,false,true,false,false,false,true,false,false};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainStage window = new MainStage();
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
	public MainStage() {
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
		frame.getContentPane().setLayout(null);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Room Cafe KIOSK");
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("돋움체", Font.BOLD, 50));
		title.setBounds(370, 40, 469, 69);
		panel.add(title);
		
		JButton[] rooms = new JButton[10];
		for(int i = 0; i < rooms.length; i++) {
			rooms[i] = new JButton();
			rooms[i].setBackground(new Color(255, 69, 0));
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
		
		rooms[0].setText("1");
		rooms[1].setText("2");
		rooms[2].setText("3");
		rooms[3].setText("4");
		rooms[4].setText("5");
		rooms[5].setText("6");
		rooms[6].setText("7");
		rooms[7].setText("8");
		rooms[8].setText("9");
		rooms[9].setText("10");
		
		rooms[3].setBackground(new Color(65, 105, 225));
		rooms[7].setBackground(new Color(65, 105, 225));
		

		rooms[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (roomState[0] == false) {
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
                if (roomState[1] == false) {
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
                if (roomState[2] == false) {
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
                if (roomState[3] == false) {
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
                if (roomState[4] == false) {
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
                if (roomState[5] == false) {
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
                if (roomState[6] == false) {
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
                if (roomState[7] == false) {
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
                if (roomState[8] == false) {
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
                if (roomState[9] == false) {
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
            				selectedRoomNum = i+1;
            				System.out.println(selectedRoomNum);
            				logIn login = new logIn();
            				login.setVisible(true);
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
