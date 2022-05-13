import org.json.simple.JSONObject;

public class ShareData {
	public static int selectedRoomNum = 0;
	//Main에서 선택한 방의 번호. 다른 패널에서 해당 룸 번호를 사용할때 여기서 참조해야함.
	public static JSONObject userStatus = null;
	// userStatus가 null이면 퇴실했거나 로그인하지 않은 상태임.
}
