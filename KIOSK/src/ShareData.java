import org.json.simple.JSONObject;

public class ShareData {
	public static int selectedRoomNum = 0;
	//Main���� ������ ���� ��ȣ. �ٸ� �гο��� �ش� �� ��ȣ�� ����Ҷ� ���⼭ �����ؾ���.
	public static JSONObject userStatus = null;
	// userStatus�� null�̸� ����߰ų� �α������� ���� ������.
	public static String userName = null;
	public static String userId = null;
	public static String userPass = null;
	public static String userPhone = null;
	public static String userTimeTicket = null;
	public static String userDayTicket = null;
	
	public static String startTicketTime = null;
	public static String finishTicketTime = null;
	public static long startTimeMilli = 0;
	public static long finishTimeMilli = 0;
	
	public static boolean timeTicketUse = false;
	public static boolean dayTicketUse = false;
	
	public static String selectedTicketName = null;
	public static int selectedTicketPrice = 0;
}
