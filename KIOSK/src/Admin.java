import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class Admin extends ShareData{

	private JFrame frame;

//
	public Admin() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uAD00\uB9AC\uC790 \uBAA8\uB4DC");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblNewLabel.setBounds(34, 22, 347, 85);
		panel.add(lblNewLabel);
		
		JButton resetJsonBtn = new JButton("\uB370\uC774\uD130 \uCD08\uAE30\uD654");
		resetJsonBtn.setBounds(92, 124, 125, 66);
		panel.add(resetJsonBtn);
		
		// 초기화 버튼 이벤트 리스너
		
		resetJsonBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				resetClicked = true;
				DataJson data = new DataJson();  // JSON 데이터 초기화
				data.DataReset();
				data.DataPrint();
				resetClicked = false;
			}
		});
	}

	public void setVisible(boolean b) {	// 외부 클래스에서 해당 클래스의 frame을 setVisible 호출할 경우 처리
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
