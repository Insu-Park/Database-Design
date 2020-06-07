import java.util.HashMap;
import java.util.Map;

public class SharedGlobalWithUI {
    public static Map<String, Item> displayItems = new HashMap<String, Item>();
    public static void Invalidate(){
        // 동수 do
        // 판매 리스트 화면 갱신
        System.out.println("invalidate!");
    }
    public static int workingStaffNo;
    public static void PopUpError(String msg){
        // 동수 do
        // 에러 메세지 창 팝업
        System.out.println("Err msg Pop Up!");
    }
    public static String customSex, customRageCd;
}
