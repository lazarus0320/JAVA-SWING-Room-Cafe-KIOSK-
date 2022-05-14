
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
		/* ��� ���� ��� �����ϴ� ��� ���� https://ponyozzang.tistory.com/157
		 * �����ڷ� JSON�� �ʱ�ȭ�ϴ� ����� ������ ��� ���ӽÿ� ������ �ʱ�ȭ ��ư�� ������ ����ǵ��� �Ѵ�.
		 * �� ó������ JSON �ڷḸ �������ְ�, DataJson Ŭ������ ������ ��带 �ϼ��ϸ� ������ �����̴�.
		 * 
		 */
		
		JSONArray childrens = new JSONArray();
		
		JSONObject child = new JSONObject();
		child.put("id", "admin0320");
		child.put("password", "admin0320");
		child.put("name", "������");
		child.put("phone", "01082485327");
		child.put("timeTicket", "10000");
		child.put("dayTicket", "100");
		childrens.add(child);
		
		parent.put("ȸ������", childrens);
		
		JSONObject child2 = new JSONObject();
		child2.put("id", "hong1234");
		child2.put("password", "12345678");
		child2.put("name", "ȫ�浿");
		child2.put("phone", "01012345678");
		child2.put("timeTicket", "120");
		child2.put("dayTicket", "3");
		childrens.add(child2);
		
		parent.put("ȸ������", childrens);
		
		
		JSONArray foodArr = new JSONArray();
		JSONObject food = new JSONObject();
		food.put("name", "������");
		food.put("price", "3000");
		food.put("sort", "�нķ�");
		foodArr.add(food);
		
		JSONObject food2 = new JSONObject();
		food2.put("name", "�踻��");
		food2.put("price", "3000");
		food2.put("sort", "�нķ�");
		foodArr.add(food2);
		
		JSONObject food3 = new JSONObject();
		food3.put("name", "���");
		food3.put("price", "3000");
		food3.put("sort", "�нķ�");
		foodArr.add(food3);
		
		JSONObject food4 = new JSONObject();
		food4.put("name", "�õ�����");
		food4.put("price", "2000");
		food4.put("sort", "�нķ�");
		foodArr.add(food4);
		
		JSONObject food5 = new JSONObject();
		food5.put("name", "���");
		food5.put("price", "2500");
		food5.put("sort", "���");
		foodArr.add(food5);
		
		JSONObject food6 = new JSONObject();
		food6.put("name", "¥�İ�Ƽ");
		food6.put("price", "2500");
		food6.put("sort", "���");
		foodArr.add(food6);
		
		JSONObject food7 = new JSONObject();
		food7.put("name", "���İ�Ƽ");
		food7.put("price", "3000");
		food7.put("sort", "���");
		foodArr.add(food7);
		
		JSONObject food8 = new JSONObject();
		food8.put("name", "������");
		food8.put("price", "8000");
		food8.put("sort", "�Ļ��");
		foodArr.add(food8);
		
		JSONObject food9 = new JSONObject();
		food9.put("name", "�������");
		food9.put("price", "6000");
		food9.put("sort", "�Ļ��");
		foodArr.add(food9);
		
		JSONObject food10 = new JSONObject();
		food10.put("name", "���л������");
		food10.put("price", "5500");
		food10.put("sort", "�Ļ��");
		foodArr.add(food10);
		
		JSONObject food11 = new JSONObject();
		food11.put("name", "���ܺ����");
		food11.put("price", "6000");
		food11.put("sort", "�Ļ��");
		foodArr.add(food11);
		
		JSONObject food12 = new JSONObject();
		food12.put("name", "���̽��Ƹ޸�ī��");
		food12.put("price", "3000");
		food12.put("sort", "�����");
		foodArr.add(food12);
		
		JSONObject food13 = new JSONObject();
		food13.put("name", "��ī�ݶ�");
		food13.put("price", "2000");
		food13.put("sort", "�����");
		foodArr.add(food13);
		
		JSONObject food14 = new JSONObject();
		food14.put("name", "ȯŸ");
		food14.put("price", "2000");
		food14.put("sort", "�����");
		foodArr.add(food14);
		
		JSONObject food15 = new JSONObject();
		food15.put("name", "��ũ����ũ");
		food15.put("price", "4500");
		food15.put("sort", "�����");
		foodArr.add(food15);
		
		parent.put("��������", foodArr);
		
		
	}
	void DataPrint() {
		System.out.println(parent.toString());
		System.out.println("JSON ������ �ʱ�ȭ �Ϸ�");
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
