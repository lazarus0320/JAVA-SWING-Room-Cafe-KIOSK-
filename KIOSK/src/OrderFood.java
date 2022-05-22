import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OrderFood extends ShareData {

	private JFrame frame;
	
	public JLabel[] foodNameLabel = new JLabel[5];	// 객체 배열로 음식 이름 라벨들을 생성
	public JLabel[] foodPriceLabel = new JLabel[5];	// 객체 배열로 음식 가격 라벨들을 생성
	public JPanel[] foodPanel = new JPanel[5];		// 객체 배열로 음식 버튼을 담을 패널들을 생성
	public JButton[] foodButton = new JButton[5];	// 객체 배열로 음식 이미지를 담을 버튼들을 생성
	
	public int selectedRow = -1;	// 선택한 테이블 행(초기값 -1)
	public int selectedCol = -1;	// 선택한 테이블 행(초기값 -1)
	public int totalPrice = 0;
	public Boolean addChecker = false;
	private JTextField chargeField;


	public void loadFoodData() throws IOException, ParseException {		// JSON 데이터의 음식정보를 열람하여 음식 종류별로 배열에 담는 함수.
		// JSON 오픈
		FileInputStream fileInputStream = new FileInputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
		BufferedReader file = new BufferedReader(inputStreamReader);
		JSONParser parser = new JSONParser();
		
		// 음식정보 받아옴
		JSONObject jsonObj = (JSONObject)parser.parse(file);
		JSONArray accountArr = (JSONArray)jsonObj.get("음식정보");
		
		for (int i = 0; i < accountArr.size(); i++) {
			JSONObject obj = (JSONObject)accountArr.get(i);
			if (obj.get("sort").equals("분식류")) {
				snackData.add(obj);
			}
			else if (obj.get("sort").equals("면류")) {
				noodleData.add(obj);
			}
			else if (obj.get("sort").equals("식사류")) {
				mealData.add(obj);
			}
			else if (obj.get("sort").equals("음료류")) {
				drinkData.add(obj);
			}
		}
		
		System.out.println("분식류" + snackData);
		System.out.println("면류" + noodleData);
		System.out.println("식사류" + mealData);
		System.out.println("음료류" + drinkData);
	}
	
	public void cleanFoodLabel() {	// 음식 종류마다 데이터의 개수가 다르기 때문에 다른 음식 종류를 클릭했음에도 이전 음식 종류 값이 사라지지 않는 버그를 해결하는 함수
		for (int i = 0; i < 5; i++) {
			foodNameLabel[i].setText("");
			foodPriceLabel[i].setText("");
			foodButton[i].setVisible(false);	
		}
	}
	
	public void showFoodData(JButton Btn) throws IOException {	// 클릭한 음식 탭의 종류에 해당하는 데이터들을 화면에 띄우는 함수.
		if (Btn.getText().equals("분식류")) {	// 이전에 json에서 파싱한 값을 종류별로 static변수에 담아두었기 때문에 음식탭을 마구잡이로 눌렀을 경우에도 굳이 새로 값을 파싱하지 않고 저장된 값을 빠르게 불러올 수 있다.
			cleanFoodLabel();
			for (int i = 0; i < snackData.size(); i++) {
				HashMap<String, String> obj = (HashMap)snackData.get(i);
				System.out.println(obj);
				String foodName = obj.get("name");
				foodNameLabel[i].setText(foodName);
				foodPriceLabel[i].setText(obj.get("price"));
				
				foodButton[i].setVisible(true);		// 데이터가 존재할 경우 버튼을 보이도록 함
				ImageIcon icon = new ImageIcon("./images/" + foodName + ".jpg");	// 버튼에 이미지를 삽입
				foodButton[i].setIcon(icon);
			}
			
		} else if (Btn.getText().equals("면류")) {
			cleanFoodLabel();
			for (int i = 0; i < noodleData.size(); i++) {
				HashMap<String, String> obj = (HashMap)noodleData.get(i);
				System.out.println(obj);
				String foodName = obj.get("name");
				foodNameLabel[i].setText(foodName);
				foodPriceLabel[i].setText(obj.get("price"));
				
				foodButton[i].setVisible(true);
				ImageIcon icon = new ImageIcon("./images/" + foodName + ".jpg");
				foodButton[i].setIcon(icon);
			}
		} else if (Btn.getText().equals("식사류")) {
			cleanFoodLabel();
			for (int i = 0; i < mealData.size(); i++) {
				HashMap<String, String> obj = (HashMap)mealData.get(i);
				System.out.println(obj);
				String foodName = obj.get("name");
				foodNameLabel[i].setText(foodName);
				foodPriceLabel[i].setText(obj.get("price"));
				
				foodButton[i].setVisible(true);
				ImageIcon icon = new ImageIcon("./images/" + foodName + ".jpg");
				foodButton[i].setIcon(icon);
			}
		} else if (Btn.getText().equals("음료류")) {
			cleanFoodLabel();
			for (int i = 0; i < drinkData.size(); i++) {
				HashMap<String, String> obj = (HashMap)drinkData.get(i);
				System.out.println(obj);
				String foodName = obj.get("name");
				foodNameLabel[i].setText(foodName);
				foodPriceLabel[i].setText(obj.get("price"));
				
				foodButton[i].setVisible(true);
				ImageIcon icon = new ImageIcon("./images/" + foodName + ".jpg");
				foodButton[i].setIcon(icon);
			}
		}
	};
	
	public void tableCellCenter(JTable table) {
		// 테이블 내용 가운데 정렬하기
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
        dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
        
        TableColumnModel tcm = table.getColumnModel() ; // 정렬할 테이블의 컬럼모델을 가져옴
      //전체 열에 지정
        for(int i = 0 ; i < tcm.getColumnCount() ; i++){
        tcm.getColumn(i).setCellRenderer(dtcr);  // 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
        }
         
        //특정 열에 지정
        tcm.getColumn(0).setCellRenderer(dtcr);  
        tcm.getColumn(1).setCellRenderer(dtcr);
        tcm.getColumn(2).setCellRenderer(dtcr);
	}
	
	public class tableListener extends MouseAdapter{	// 테이블 클릭 이벤트 리스너 함수 설정
		@Override
	    public void mouseClicked(MouseEvent e) {
			JTable table = (JTable) e.getSource();	// e.getSource(); 메소드를 사용해서 이벤트리스너를 등록하고, 이를 실행하려고 할 때 그 이벤트가 실행되는 특정 컨테이너의 모든 속성을 가져온다.
		    if (e.getButton() == 1) { //클릭
		    	selectedRow = table.getSelectedRow();
		    	selectedCol = table.getSelectedColumn();
		    	System.out.println("선택한 행 번호 : " + selectedRow);		    	
		    
		    	} 
//		    if (e.getClickCount() == 2) { System.out.println("더블클릭");} // 더블클릭
//		    if (e.getButton() == 3) { System.out.println("오른쪽클릭");} // 오른쪽 클릭
		    
		}
	}
	
	public String makeBuyTime() {  // 음식 구매 시간을 만들어주는 함수. 음식 구매 영수증에 구매 시간을 표시할 용도로 사용함.
		LocalDateTime now = LocalDateTime.now();	// 현재 시간을 밀리초로 받아옴.
		String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분ss초"));	// 현재 시간을 나타내는 형식을 지정함.
		System.out.println(formatedNow);
		return formatedNow;	// 이용권 구매시간을 yyyy년MM월dd일 HH시mm분ss초 양식으로 반환함(String)
	}
	
	
	public void makeBuyData(String time , int chargeVal, int changeVal, int totalPrice, DefaultTableModel model) {  //영수증 결제 영수증 파일을 생성하는 함수. 음식 구매 시간, 지불금액, 거스름돈을 매개변수로 받음.
		try { 
			try {
				// 디렉토리 생성
				Path directoryPath = Paths.get("C:\\KIOSK\\KIOSK_FOOD_RECEIPT");	// 해당 경로를 directoryPath 객체명으로 지정함.
				Files.createDirectory(directoryPath);	// 해당 경로를 생성함
				System.out.println(directoryPath + " 디렉토리가 생성되었습니다.");
				} catch (FileAlreadyExistsException e) {
				System.out.println("디렉토리가 이미 존재합니다");
				} catch (NoSuchFileException e) {
				System.out.println("디렉토리 경로가 존재하지 않습니다");
				}catch (IOException e) {
				e.printStackTrace();
				}
			
			// 메모장 파일을 해당 경로에 만들되, 파일명은 회원의 이름 + 음식 구매 시간 + ".txt"로 지정함. 이렇게 하면 파일 중복을 피할 수 있음.
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\KIOSK\\KIOSK_FOOD_RECEIPT\\" + userName + time  + ".txt");
			OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
			BufferedWriter file = new BufferedWriter(OutputStreamWriter);
			
			// 영수증 파일을 작성함
			file.write("룸카페 KIOSK 음식 구매 영수증"); 
			file.newLine();	// 개행
			
			file.write("사업자번호 : 123-4567-890    대표자 : 민지훈");
			file.newLine();
			file.write("회원 이름 : " + userName); 
			file.newLine();
			file.write("결제 시간 : " + time); 
			file.newLine();
			file.write("==============================="); 
			file.newLine();
			file.write("주문 내역            수량            금액"); 
			file.newLine();
			file.write("==============================="); 
			file.newLine();
			
//			file.write(selectedTicketName + "                  " + selectedTicketPrice + "원"); 
//			file.newLine();
			for(int i = 0 ; i < model.getRowCount(); i++){
				String rowName = (String) model.getValueAt(i, 0);
				String rowAmount = (String) model.getValueAt(i, 1);
				String rowPrice = (String) model.getValueAt(i, 2);
				// 문자열 포멧팅으로 정렬해서 출력하려 했으나 실패함. 좋은 방법 찾으면 수정할 예정.
				file.write(String.format("%-20.10s", rowName));
				file.write(String.format("%-20.10s", rowAmount));
				file.write(String.format("%-20.10s", rowPrice));
				file.newLine();
			}
			file.write("------------------------------------------------------");
			file.newLine();
			file.write("총액                  " + totalPrice + "원");
			file.newLine();
			file.write("지불금액              " + chargeVal + "원");
			file.newLine();
			file.write("거스름돈              " + changeVal + "원");		
			
			
			file.flush(); 
			file.close(); 
			System.out.println("C:\\KIOSK\\KIOSK_FOOD_RECEIPT\\ 경로에 영수증 파일 생성됨.");
		} catch (IOException e) { 
				e.printStackTrace(); 
		}
	}
	

	
	public OrderFood() {
		
		try {
			loadFoodData();
		} catch (IOException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 800);
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Room Cafe KIOSK");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		panel.setBounds(0, 0, 1200, 800);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel foodMenuPanel = new JPanel();
		foodMenuPanel.setBackground(new Color(255, 255, 255));
		foodMenuPanel.setBounds(30, 90, 1120, 375);
		panel.add(foodMenuPanel);
		foodMenuPanel.setLayout(null);
		
		// foodPanel 설정
		for(int i = 0; i < foodPanel.length; i++) {
			foodPanel[i] = new JPanel();
			foodMenuPanel.add(foodPanel[i]);
		}

		foodPanel[0].setBounds(18, 30, 200, 200);
		foodPanel[1].setBounds(238, 30, 200, 200);
		foodPanel[2].setBounds(458, 30, 200, 200);
		foodPanel[3].setBounds(678, 30, 200, 200);
		foodPanel[4].setBounds(898, 30, 200, 200);
		
		for(int i = 0; i < foodButton.length; i++) {
			foodButton[i] = new JButton();
			foodButton[i].setSize(new Dimension(200, 200));
			foodButton[i].setContentAreaFilled(false);
			foodButton[i].setBorderPainted(false);
			foodPanel[i].add(foodButton[i]);
		}

		// foodNameLabel 설정
		for(int i = 0; i < foodNameLabel.length; i++) {
			foodNameLabel[i] = new JLabel();
			foodNameLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			foodNameLabel[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			foodMenuPanel.add(foodNameLabel[i]);
		}
		
		foodNameLabel[0].setBounds(18, 250, 200, 45);
		foodNameLabel[1].setBounds(238, 250, 200, 45);
		foodNameLabel[2].setBounds(458, 250, 200, 45);
		foodNameLabel[3].setBounds(678, 250, 200, 45);
		foodNameLabel[4].setBounds(898, 250, 200, 45);
		
		
		// foodPriceLabel 설정
		for(int i = 0; i < foodPriceLabel.length; i++) {
			foodPriceLabel[i] = new JLabel();
			foodPriceLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			foodPriceLabel[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
			foodMenuPanel.add(foodPriceLabel[i]);
		}
		
		foodPriceLabel[0].setBounds(18, 305, 200, 45);
		foodPriceLabel[1].setBounds(238, 305, 200, 45);
		foodPriceLabel[2].setBounds(458, 305, 200, 45);
		foodPriceLabel[3].setBounds(678, 305, 200, 45);
		foodPriceLabel[4].setBounds(898, 305, 200, 45);
		
		
		JPanel sortBtnPanel = new JPanel();
		sortBtnPanel.setBackground(new Color(0, 0, 0));
		sortBtnPanel.setBounds(30, 25, 1120, 65);
		panel.add(sortBtnPanel);
		sortBtnPanel.setLayout(null);
		
		JButton snackBtn = new JButton("\uBD84\uC2DD\uB958");
		snackBtn.setSelected(true);
		try {
			showFoodData(snackBtn);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if (snackBtn.isSelected()) {
			snackBtn.setForeground(new Color(0, 0, 0));
			snackBtn.setBackground(new Color(255, 255, 255));
		} else {
			snackBtn.setForeground(new Color(255, 255, 255));
			snackBtn.setBackground(new Color(255, 140, 0));
		}
		snackBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		snackBtn.setBounds(40, 0, 235, 65);
		snackBtn.setBorder(null);
		snackBtn.setFocusPainted(false);
		
		sortBtnPanel.add(snackBtn);
		
		
		
		JButton noodleBtn = new JButton("\uBA74\uB958");
		if (noodleBtn.isSelected()) {
			noodleBtn.setForeground(new Color(0, 0, 0));
			noodleBtn.setBackground(new Color(255, 255, 255));
		} else {
			noodleBtn.setForeground(new Color(255, 255, 255));
			noodleBtn.setBackground(new Color(255, 140, 0));
		}
		noodleBtn.setForeground(new Color(255, 255, 255));
		noodleBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		noodleBtn.setBounds(305, 0, 235, 65);
		noodleBtn.setBackground(new Color(255, 140, 0));
		noodleBtn.setBorder(null);
		noodleBtn.setFocusPainted(false);
		sortBtnPanel.add(noodleBtn);
		
		JButton mealBtn = new JButton("\uC2DD\uC0AC\uB958");
		if (mealBtn.isSelected()) {
			mealBtn.setForeground(new Color(0, 0, 0));
			mealBtn.setBackground(new Color(255, 255, 255));
		} else {
			mealBtn.setForeground(new Color(255, 255, 255));
			mealBtn.setBackground(new Color(255, 140, 0));
		}
		mealBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		mealBtn.setBounds(570, 0, 235, 65);
		mealBtn.setBackground(new Color(255, 140, 0));
		mealBtn.setBorder(null);
		mealBtn.setFocusPainted(false);
		sortBtnPanel.add(mealBtn);
		
		JButton drinkBtn = new JButton("\uC74C\uB8CC\uB958");
		if (drinkBtn.isSelected()) {
			drinkBtn.setForeground(new Color(0, 0, 0));
			drinkBtn.setBackground(new Color(255, 255, 255));
		} else {
			drinkBtn.setForeground(new Color(255, 255, 255));
			drinkBtn.setBackground(new Color(255, 140, 0));
		}
		drinkBtn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		drinkBtn.setBounds(835, 0, 235, 66);
		drinkBtn.setBackground(new Color(255, 140, 0));
		drinkBtn.setBorder(null);
		drinkBtn.setFocusPainted(false);
		sortBtnPanel.add(drinkBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(47, 79, 79));
		panel_1.setBounds(0, 466, 1186, 58);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC8FC\uBB38 \uB0B4\uC5ED");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel.setBounds(214, 0, 210, 58);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uACC4\uC0B0");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel_1.setBounds(806, 0, 175, 58);
		panel_1.add(lblNewLabel_1);
		
		
		// 음식 선택 테이블 생성
		String[] headings = new String[] {"주문 메뉴", "수량", "가격"};
		Object[][] data = new Object[][] {
		};
		JPanel tablePanel = new JPanel();
		tablePanel.setLocation(0, 520);
		tablePanel.setSize(575, 245);
		panel.add(tablePanel);
		DefaultTableModel model = new DefaultTableModel(data, headings) {	// 행 추가, 삭제 가능하게 하기 위한 표 설정
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {	// 셀 더블클릭으로 수정 못하게 막는 함수
		       //all cells false
		       return false;
		    }
		};
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(565, 205));
		table.setFillsViewportHeight(true);
		tablePanel.add(new JScrollPane(table));
		table.getTableHeader().setReorderingAllowed(false);	// 컬럼들 이동 불가능 설정
		table.getTableHeader().setResizingAllowed(false);	// 컬럼들 크기 조절 불가능 설정
		table.setRowHeight(30);
		
		tableCellCenter(table);
		table.addMouseListener(new tableListener());	// 테이블 클릭 이벤트 리스너 함수 호출
		
		JButton addBtn = new JButton("+");
		addBtn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		addBtn.setBounds(582, 534, 65, 65);
		addBtn.setFocusPainted(false);
		addBtn.setBackground(new Color(0X00AAFF));
		panel.add(addBtn);
		
		JButton minusBtn = new JButton("-");
		minusBtn.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		minusBtn.setBounds(582, 610, 65, 65);
		minusBtn.setFocusPainted(false);
		minusBtn.setBackground(new Color(0X4dab3c));
		panel.add(minusBtn);
		
		JButton delBtn = new JButton("X");
		delBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		delBtn.setBounds(582, 687, 65, 65);
		delBtn.setFocusPainted(false);
		delBtn.setBackground(new Color(0Xdc143c));
		panel.add(delBtn);
		
		JPanel buyPanel = new JPanel();
		buyPanel.setBounds(657, 524, 380, 241);
		panel.add(buyPanel);
		buyPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\uCD1D\uC561 :");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(71, 32, 70, 40);
		buyPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\uAE08\uC561 \uC785\uB825 :");
		lblNewLabel_2_1.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		lblNewLabel_2_1.setBounds(12, 101, 131, 40);
		buyPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\uAC70\uC2A4\uB984\uB3C8 :");
		lblNewLabel_2_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		lblNewLabel_2_1_1.setBounds(23, 168, 120, 40);
		buyPanel.add(lblNewLabel_2_1_1);
		
		JLabel totalPriceLabel = new JLabel("");
		totalPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalPriceLabel.setFont(new Font("굴림체", Font.PLAIN, 22));
		totalPriceLabel.setBounds(153, 32, 179, 40);
		buyPanel.add(totalPriceLabel);
		
		JLabel changeLabel = new JLabel("");
		changeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		changeLabel.setFont(new Font("굴림체", Font.PLAIN, 22));
		changeLabel.setBounds(153, 168, 179, 40);
		buyPanel.add(changeLabel);
		
		chargeField = new JTextField();
		chargeField.setHorizontalAlignment(SwingConstants.RIGHT);
		chargeField.setFont(new Font("굴림체", Font.PLAIN, 22));
		chargeField.setBounds(155, 101, 188, 40);
		chargeField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));	// padding 적용 상하좌우 순서
		buyPanel.add(chargeField);
		chargeField.setColumns(10);
		
		JButton chargeBtn = new JButton("\uACC4\uC0B0");
		chargeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chargeBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		chargeBtn.setBounds(1049, 534, 122, 100);
		panel.add(chargeBtn);
		
		JButton exitBtn = new JButton("\uB098\uAC00\uAE30");
		exitBtn.setFont(new Font("맑은 고딕", Font.BOLD, 28));
		exitBtn.setBounds(1049, 652, 122, 100);
		panel.add(exitBtn);
	
		
		snackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (snackBtn.isSelected()) {
					return;
				} else {
					try {
						showFoodData(snackBtn);	// 클릭한 음식류의 데이터를 화면에 보이도록 함
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					snackBtn.setSelected(true);	// 클릭한 버튼 제외 모두 선택 해제.
					noodleBtn.setSelected(false);
					mealBtn.setSelected(false);
					drinkBtn.setSelected(false);
					
					snackBtn.setForeground(new Color(0, 0, 0));
					snackBtn.setBackground(new Color(255, 255, 255));
					
					noodleBtn.setForeground(new Color(255, 255, 255));
					noodleBtn.setBackground(new Color(255, 140, 0));
					
					mealBtn.setForeground(new Color(255, 255, 255));
					mealBtn.setBackground(new Color(255, 140, 0));
					
					drinkBtn.setForeground(new Color(255, 255, 255));
					drinkBtn.setBackground(new Color(255, 140, 0));
					
				}
			}
		});
		
		noodleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (noodleBtn.isSelected()) {
					return;
				} else {
					try {
						showFoodData(noodleBtn);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					snackBtn.setSelected(false);
					noodleBtn.setSelected(true);
					mealBtn.setSelected(false);
					drinkBtn.setSelected(false);
					
					snackBtn.setForeground(new Color(255, 255, 255));
					snackBtn.setBackground(new Color(255, 140, 0));
					
					noodleBtn.setForeground(new Color(0, 0, 0));
					noodleBtn.setBackground(new Color(255, 255, 255));
					
					mealBtn.setForeground(new Color(255, 255, 255));
					mealBtn.setBackground(new Color(255, 140, 0));
					
					drinkBtn.setForeground(new Color(255, 255, 255));
					drinkBtn.setBackground(new Color(255, 140, 0));
				}
			}
		});
		
		mealBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mealBtn.isSelected()) {
					return;
				} else {
					try {
						showFoodData(mealBtn);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					snackBtn.setSelected(false);
					noodleBtn.setSelected(false);
					mealBtn.setSelected(true);
					drinkBtn.setSelected(false);
					
					snackBtn.setForeground(new Color(255, 255, 255));
					snackBtn.setBackground(new Color(255, 140, 0));
					
					noodleBtn.setForeground(new Color(255, 255, 255));
					noodleBtn.setBackground(new Color(255, 140, 0));
					
					mealBtn.setForeground(new Color(0, 0, 0));
					mealBtn.setBackground(new Color(255, 255, 255));
					
					drinkBtn.setForeground(new Color(255, 255, 255));
					drinkBtn.setBackground(new Color(255, 140, 0));
				}
			}
		});
		
		drinkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (drinkBtn.isSelected()) {
					return;
				} else {
					try {
						showFoodData(drinkBtn);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					snackBtn.setSelected(false);
					noodleBtn.setSelected(false);
					mealBtn.setSelected(false);
					drinkBtn.setSelected(true);
					
					snackBtn.setForeground(new Color(255, 255, 255));
					snackBtn.setBackground(new Color(255, 140, 0));
					
					noodleBtn.setForeground(new Color(255, 255, 255));
					noodleBtn.setBackground(new Color(255, 140, 0));
					
					mealBtn.setForeground(new Color(255, 255, 255));
					mealBtn.setBackground(new Color(255, 140, 0));
					
					drinkBtn.setForeground(new Color(0, 0, 0));
					drinkBtn.setBackground(new Color(255, 255, 255));
				}
			}
		});
		
		foodButton[0].addActionListener(new ActionListener() {	// 첫번째 음식 버튼을 눌렀을 경우
			/*	음식 버튼을 눌렀을 때, 아래 테이블에서 지금 누른 음식의 이름과 동일한 이름이 이미 존재하는 경우,
			 * 	굳이 새로운 행을 추가하지 않고 수량만 +1 값으로 수정한다.
			 *	음식 버튼을 눌렀을 때, 아래 테이블에 지금 누른 음식과 동일한 이름이 없는 경우,
			 *	문자열 배열 3개를 생성해서 화면에 표시되어 있는 첫번째 음식의 정보를 할당받고 새로운 행으로 추가한다.
			 *	음식의 종류는 수십가지이지만, 화면에 표시되는 정보를 추출할 수 있는 방법이 존재하므로
			 *	모든 음식의 값을 일일이 할당받을 필요가 없다.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("음식 이름 : " + foodNameLabel[0].getText());
				System.out.println("음식 가격 : " + foodPriceLabel[0].getText());
				
				String addFood[] = new String[3];
				addFood[0] = foodNameLabel[0].getText();
				System.out.println("테이블 행의 수 : " + table.getRowCount());
				for(int i = 0 ; i < table.getRowCount(); i++){
					if ( (foodNameLabel[0].getText()).equals(table.getValueAt(i, 0)) ) {
						System.out.println("값이 존재함");
						table.setValueAt(Integer.toString(Integer.parseInt((String) table.getValueAt(i, 1)) + 1), i, 1);
			        	return;
					}
				}
				addFood[1] = "1";
				addFood[2] = foodPriceLabel[0].getText();
				model.addRow(addFood);
			}
		});
		
		foodButton[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("음식 이름 : " + foodNameLabel[1].getText());
				System.out.println("음식 가격 : " + foodPriceLabel[1].getText());
				
				String addFood[] = new String[3];
				addFood[0] = foodNameLabel[1].getText();
				System.out.println("테이블 행의 수 : " + table.getRowCount());
				for(int i = 0 ; i < table.getRowCount(); i++){
					if ( (foodNameLabel[1].getText()).equals(table.getValueAt(i, 0)) ) {
						System.out.println("값이 존재함");
						table.setValueAt(Integer.toString(Integer.parseInt((String) table.getValueAt(i, 1)) + 1), i, 1);
			        	return;
					}
				}
				addFood[1] = "1";
				addFood[2] = foodPriceLabel[1].getText();
				model.addRow(addFood);
			}
		});
		
		foodButton[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("음식 이름 : " + foodNameLabel[2].getText());
				System.out.println("음식 가격 : " + foodPriceLabel[2].getText());
				
				String addFood[] = new String[3];
				addFood[0] = foodNameLabel[2].getText();
				System.out.println("테이블 행의 수 : " + table.getRowCount());
				for(int i = 0 ; i < table.getRowCount(); i++){
					if ( (foodNameLabel[2].getText()).equals(table.getValueAt(i, 0)) ) {
						System.out.println("값이 존재함");
						table.setValueAt(Integer.toString(Integer.parseInt((String) table.getValueAt(i, 1)) + 1), i, 1);
			        	return;
					}
				}
				addFood[1] = "1";
				addFood[2] = foodPriceLabel[2].getText();
				model.addRow(addFood);
			}
		});
		
		foodButton[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("음식 이름 : " + foodNameLabel[3].getText());
				System.out.println("음식 가격 : " + foodPriceLabel[3].getText());
				
				String addFood[] = new String[3];
				addFood[0] = foodNameLabel[3].getText();
				System.out.println("테이블 행의 수 : " + table.getRowCount());
				for(int i = 0 ; i < table.getRowCount(); i++){
					if ( (foodNameLabel[3].getText()).equals(table.getValueAt(i, 0)) ) {
						System.out.println("값이 존재함");
						table.setValueAt(Integer.toString(Integer.parseInt((String) table.getValueAt(i, 1)) + 1), i, 1);
			        	return;
					}
				}
				addFood[1] = "1";
				addFood[2] = foodPriceLabel[3].getText();
				model.addRow(addFood);
			}
		});
		
		foodButton[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("음식 이름 : " + foodNameLabel[4].getText());
				System.out.println("음식 가격 : " + foodPriceLabel[4].getText());
				
				String addFood[] = new String[3];
				addFood[0] = foodNameLabel[4].getText();
				System.out.println("테이블 행의 수 : " + table.getRowCount());
				for(int i = 0 ; i < table.getRowCount(); i++){
					if ( (foodNameLabel[4].getText()).equals(table.getValueAt(i, 0)) ) {
						System.out.println("값이 존재함");
						table.setValueAt(Integer.toString(Integer.parseInt((String) table.getValueAt(i, 1)) + 1), i, 1);
			        	return;
					}
				}
				addFood[1] = "1";
				addFood[2] = foodPriceLabel[4].getText();
				model.addRow(addFood);
			}
		});
		
		addBtn.addActionListener(new ActionListener() {		
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (selectedRow == -1) {	// 아무것도 선택되지 않은 초기상태에서는 그냥 함수 탈출
					return;
					
				} else {
					model.setValueAt(Integer.toString(Integer.parseInt((String) table.getValueAt(selectedRow, 1)) + 1), selectedRow, 1);
				}
			}
		});
		
		minusBtn.addActionListener(new ActionListener() {		
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (selectedRow == -1) {	// 아무것도 선택되지 않은 초기상태에서는 그냥 함수 탈출
					return;
					
				} else {
					if (table.getValueAt(selectedRow, 1).equals("1")) {
						model.removeRow(selectedRow);
						selectedRow -= 1;
						return;
					} else {
					model.setValueAt(Integer.toString(Integer.parseInt((String) table.getValueAt(selectedRow, 1)) - 1), selectedRow, 1);
					}
				}
			}
		});
		
		delBtn.addActionListener(new ActionListener() {		// 선택한 행을 삭제함.
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (selectedRow == -1) {	// 아무것도 선택되지 않은 초기상태에서는 그냥 함수 탈출
					return;
					
				} else {
					model.removeRow(selectedRow);	// 선택된 행이 있을때는 그 행을 지우고, 한 칸 위의 행으로 포커싱을 맞춘다. 연달아서 삭제를 가능하게 해준다.
					selectedRow -= 1;
				}
			}
		});
		
		model.addTableModelListener(new TableModelListener() {	// 테이블 이벤트 처리 리스너. 모델로 받아온 테이블 값이 변경될 때마다 함수가 실행된다.
			
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				System.out.println("테이블 변경");				
				for(int i = 0 ; i < model.getRowCount(); i++){
					int rowAmount = Integer.parseInt((String) model.getValueAt(i, 1));
					int rowPrice = Integer.parseInt((String) model.getValueAt(i, 2));
					totalPrice += (rowAmount * rowPrice);
				}
				totalPriceLabel.setText(Integer.toString(totalPrice));
				totalPrice = 0;
				chargeField.setText("");	// 테이블 항목이 변경되면 지불금액 창을 빈칸으로 초기화함.(예를들어 지불금액에 3000원 써놓고 음식을 더 추가했을때 3000원으로 사지 못하도록 초기화하는 것임.)
				changeLabel.setText("");
			}
		});
		
		KeyListener changeKey = new KeyListener() {	// 키 눌렀다가 땠을 때 동작하는 함수. 먼저 어떻게 동작하는지 만들어 놓고, 나중에 특정 필드에 적용할 수 있도록 함.
			// 필요없는 부분도 반드시 빈칸으로라도 구현해야 작동함.
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {	// 키를 눌렀다 땠을때 동작하는 함수
				// TODO Auto-generated method stub
				int chargeValue = 0;
				try {
					chargeValue = Integer.parseInt(chargeField.getText()); // 사용자의 지불 금액 텍스트 필드의 값을 정수형으로 형변환해서 저장함.
				} catch(NumberFormatException e1) {	// 만약 지불 금액 텍스트 필드에 정수가 아닌 문자를 입력했을 경우, 지불 금액 텍스트 필드를 빈칸으로 초기화함.
					chargeField.setText("");
				}
				// 사용자가 지불하려는 금액에서 BuyTicket 클래스에서 선택했던 이용권의 가격을 뺀 값을 changeVal에 저장함.
				int totalPrice = Integer.parseInt(totalPriceLabel.getText());	// 총액을 정수형으로 변환해 저장.
				int changeVal = chargeValue - totalPrice;
				if (changeVal >= 0) {	// 만약 그 값이 0보다 크면 그 값만큼 거스름돈으로 걸러줘야함. 거스름돈 라벨에 그 값을 표시하도록 함.
					changeLabel.setText(Integer.toString(changeVal)); // 라벨에 텍스트를 입력하는 것이므로 String 형변환 처리함.
				} else {	// changeVal이 0보다 작다는 것은 지불해야할 금액보다 적은 금액을 입력한 것이므로 '-'를 표시하도록 한다.
					changeLabel.setText("-");
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		// 위에서 설정한 changeKey 키 입력 리스너를 chargeField(지불금액 입력 텍스트필드 컴포넌트)에 적용함.
		chargeField.addKeyListener(changeKey);
		
		
		chargeBtn.addActionListener(new ActionListener() {	// 계산 버튼 클릭 이벤트 리스너 설정
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int answer = JOptionPane.showConfirmDialog(frame, "결제하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
				if(answer==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
					if (chargeField.getText().equals("")) {
						JOptionPane.showMessageDialog(frame, "결제 금액을 입력해주세요.", "Message", JOptionPane.WARNING_MESSAGE );
						return;
					}
					if (totalPriceLabel.getText().equals("")) {
						JOptionPane.showMessageDialog(frame, "주문할 음식을 먼저 선택해주세요.", "Message", JOptionPane.WARNING_MESSAGE );
						return;
					}
					int chargeVal = Integer.parseInt(chargeField.getText());	// 지불금액 입럭 텍스트 필드에서 가져온 값을 정수로 형변환 하여 chargeVal에 저장함.
					int totalPrice = Integer.parseInt(totalPriceLabel.getText());
					if (totalPrice > chargeVal) {	// 만약 구매하려는 이용권의 가격이 사용자가 지불한다고 입력한 금액보다 클 경우
						System.out.println("결제 금액을 확인해주세요.");
						JOptionPane.showMessageDialog(frame, "결제 금액을 확인해주세요.", "Message", JOptionPane.WARNING_MESSAGE );
						return;	// 함수 종료
					}
					int changeVal = Integer.parseInt(changeLabel.getText());	// 거스름돈이 얼마인지 나타내는 필드에서 가져온 값을 정수로 형변환 하여 changeVal에 저장함.
					//	구매 조건을 충족할 경우
					System.out.println("결제 완료.");
					String buyTime = makeBuyTime();	// 이용권을 구매한 시간을 반환하도록 하는 함수 호출
					int answer2 = JOptionPane.showConfirmDialog(frame, "결제 되었습니다. 영수증을 출력하시겠습니까?", "confirm",JOptionPane.YES_NO_OPTION );
					
					if(answer2==JOptionPane.YES_OPTION){  //사용자가 yes를 눌렀을 경우
						System.out.println("영수증 출력 선택.");
						makeBuyData(buyTime, chargeVal, changeVal, totalPrice, model);	// 영수증 결제 영수증 파일을 생성하는 함수 호출. 이용권 구매 시간, 지불금액, 거스름돈, 총액, model테이블을 인수로 보냄.
						System.out.println("영수증 생성 완료.");
						JOptionPane.showMessageDialog(frame, "영수증을 출력했습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
						//로그인 후 대실정보 열람 기능 추가 예정.
					} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
						System.out.println("영수증 출력 거부.");
					}
					JOptionPane.showMessageDialog(frame, "음식 주문이 완료되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
					return;
					
				} else{  //사용자가 Yes 이외의 값을 눌렀을 경우
					System.out.println("결제 취소.");
					return;
				}
				
			}
		});
		
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RoomStage rs = new RoomStage();
				rs.setVisible(true);
				frame.setVisible(false);
			}
		});
		
	}
	
	public void setVisible(boolean b) {  // 외부 클래스에서 해당 클래스의 frame을 setVisible 호출할 경우 처리
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
