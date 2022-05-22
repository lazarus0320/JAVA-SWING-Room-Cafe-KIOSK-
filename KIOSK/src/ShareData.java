import java.util.ArrayList;

import org.json.simple.JSONObject;
//static data 클래스(휘발성)
public class ShareData {
	// JSON에서 파싱된 값들이 여기서 저장되거나, 여기서 저장된 값들이 다시 JSON 파일에 저장됨.
	// JSON 파일은 반드시 string형태로 저장되어야하므로, JSON에 들어가야할 데이터들은 웬만하면 string형태로 사용하는 것을 권장함.
	public static int selectedRoomNum = 0; // 룸선택 화면에서 선택한 방의 번호.
	public static JSONObject userStatus = null; // userStatus가 null이면 퇴실했거나 로그인하지 않은 상태임.
	public static String userName = null;
	public static String userId = null;
	public static String userPass = null;
	public static String userPhone = null;
	public static String userTimeTicket = null; // 회원의 보유 시간권 누적합
	public static String userDayTicket = null;	// 회원의 보유 기간권 누적합
	
	public static String startTicketTime = "X"; // 회원이 대실을 했을 경우 입력되는 대실 시간. 밀리초 형태로 저장됨.
	
	public static String timeTicketUse = "false";	// 회원이 시간권으로 대실했는지 여부
	public static String dayTicketUse = "false";	// 회원이 기간권으로 대실했는지 여부
	
	
	public static String selectedTicketName = null;	// 이용권 구매 클래스에서 선택한 이용권의 이름을 저장함
	public static int selectedTicketPrice = 0;		// 이용권 구매 클래스에서 선택한 이용권의 가격을 저장함
	
	public static boolean resetClicked = false;		// 일단 보류
	
	
	// JSON 데이터의 음식정보를 열람하여 음식 종류별로 배열에 담는다. OrderFood 클래스의 loadFoodData() 함수에 의해 값이 할당됨.
	public static ArrayList<Object> snackData = new ArrayList<>();	// 분식류 map 값들을 배열로 받음
	public static ArrayList<Object> noodleData = new ArrayList<>(); // 면류 map 값들을 배열로 받음
	public static ArrayList<Object> mealData = new ArrayList<>();	// 식사류 map 값들을 배열로 받음
	public static ArrayList<Object> drinkData = new ArrayList<>();  // 음료류 map 값들을 배열로 받음
	
	public void resetData(){
		selectedRoomNum = 0; // 룸선택 화면에서 선택한 방의 번호.
		userStatus = null; // userStatus가 null이면 퇴실했거나 로그인하지 않은 상태임.
		userName = null;
		userId = null;
		userPass = null;
		userPhone = null;
		userTimeTicket = null; // 회원의 보유 시간권 누적합
		userDayTicket = null;	// 회원의 보유 기간권 누적합
		
		startTicketTime = "X"; // 회원이 대실을 했을 경우 입력되는 대실 시간. 밀리초 형태로 저장됨.
		
		timeTicketUse = "false";	// 회원이 시간권으로 대실했는지 여부
		dayTicketUse = "false";	// 회원이 기간권으로 대실했는지 여부
		
		
		selectedTicketName = null;	// 이용권 구매 클래스에서 선택한 이용권의 이름을 저장함
		selectedTicketPrice = 0;		// 이용권 구매 클래스에서 선택한 이용권의 가격을 저장함
		
		resetClicked = false;	
	}
}

