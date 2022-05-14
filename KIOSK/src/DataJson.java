
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class DataJson{
	JSONObject parent = new JSONObject();
	DataJson() {
		/* 경로 없을 경우 생성하는 방법 참고 https://ponyozzang.tistory.com/157
		 * 생성자로 JSON을 초기화하는 기능은 관리자 모드 접속시에 데이터 초기화 버튼을 누르면 실행되도록 한다.
		 * 맨 처음에는 JSON 자료만 공유해주고, DataJson 클래스는 관리자 모드를 완성하면 공유할 예정이다.
		 * 
		 */
		
		JSONArray childrens = new JSONArray();
		
		JSONObject child = new JSONObject();
		child.put("id", "admin0320");
		child.put("password", "admin0320");
		child.put("name", "민지훈");
		child.put("phone", "01082485327");
		child.put("timeTicket", "10000");
		child.put("dayTicket", "100");
		childrens.add(child);
		
		parent.put("회원정보", childrens);
		
		JSONObject child2 = new JSONObject();
		child2.put("id", "hong1234");
		child2.put("password", "12345678");
		child2.put("name", "홍길동");
		child2.put("phone", "01012345678");
		child2.put("timeTicket", "120");
		child2.put("dayTicket", "3");
		childrens.add(child2);
		
		parent.put("회원정보", childrens);
		
		
		JSONArray foodArr = new JSONArray();
		JSONObject food = new JSONObject();
		food.put("name", "떡볶이");
		food.put("price", "3000");
		food.put("sort", "분식류");
		foodArr.add(food);
		
		JSONObject food2 = new JSONObject();
		food2.put("name", "김말이");
		food2.put("price", "3000");
		food2.put("sort", "분식류");
		foodArr.add(food2);
		
		JSONObject food3 = new JSONObject();
		food3.put("name", "김밥");
		food3.put("price", "3000");
		food3.put("sort", "분식류");
		foodArr.add(food3);
		
		JSONObject food4 = new JSONObject();
		food4.put("name", "냉동만두");
		food4.put("price", "2000");
		food4.put("sort", "분식류");
		foodArr.add(food4);
		
		JSONObject food5 = new JSONObject();
		food5.put("name", "라면");
		food5.put("price", "2500");
		food5.put("sort", "면류");
		foodArr.add(food5);
		
		JSONObject food6 = new JSONObject();
		food6.put("name", "짜파게티");
		food6.put("price", "2500");
		food6.put("sort", "면류");
		foodArr.add(food6);
		
		JSONObject food7 = new JSONObject();
		food7.put("name", "스파게티");
		food7.put("price", "3000");
		food7.put("sort", "면류");
		foodArr.add(food7);
		
		JSONObject food8 = new JSONObject();
		food8.put("name", "마라탕");
		food8.put("price", "8000");
		food8.put("sort", "식사류");
		foodArr.add(food8);
		
		JSONObject food9 = new JSONObject();
		food9.put("name", "돈까스덮밥");
		food9.put("price", "6000");
		food9.put("sort", "식사류");
		foodArr.add(food9);
		
		JSONObject food10 = new JSONObject();
		food10.put("name", "대패삽겹비빔밥");
		food10.put("price", "5500");
		food10.put("sort", "식사류");
		foodArr.add(food10);
		
		JSONObject food11 = new JSONObject();
		food11.put("name", "돌솥비빔밥");
		food11.put("price", "6000");
		food11.put("sort", "식사류");
		foodArr.add(food11);
		
		JSONObject food12 = new JSONObject();
		food12.put("name", "아이스아메리카노");
		food12.put("price", "3000");
		food12.put("sort", "음료류");
		foodArr.add(food12);
		
		JSONObject food13 = new JSONObject();
		food13.put("name", "코카콜라");
		food13.put("price", "2000");
		food13.put("sort", "음료류");
		foodArr.add(food13);
		
		JSONObject food14 = new JSONObject();
		food14.put("name", "환타");
		food14.put("price", "2000");
		food14.put("sort", "음료류");
		foodArr.add(food14);
		
		JSONObject food15 = new JSONObject();
		food15.put("name", "밀크쉐이크");
		food15.put("price", "4500");
		food15.put("sort", "음료류");
		foodArr.add(food15);
		
		parent.put("음식정보", foodArr);
		
		
	}
	void DataPrint() {
		System.out.println(parent.toString());
		System.out.println("JSON 데이터 초기화 완료");
	}
	
	void DataReset() {
		try { 
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\KIOSK\\KIOSK_USER\\user_database.json");
			OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
			BufferedWriter file = new BufferedWriter(OutputStreamWriter);
			
			file.write(parent.toJSONString()); 
			file.flush(); 
			file.close(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
}
