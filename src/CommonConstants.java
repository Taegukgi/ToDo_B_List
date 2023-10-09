import java.awt.*;

public class CommonConstants {
    public static final Dimension GUI_SIZE = new Dimension(540,760);
    // Dimension 클래스는 너비와 높이를 나타내는 클래스. setWidth() 와 setHeight() 
    // Dimension GUI_SIZE 클래스를 Public Static final 상수 선언
    // 기준은 너비 540 과 높이 760 설정하겠다.

    public static final Dimension BANNER_SIZE = new Dimension(GUI_SIZE.width, 50);
    // Dimension BANNER_SIZE 클래스를 Public Static final 상수 선언
    // GUI_SIZE.width, 50 : 너비는 50으로 설정하겠다.

    public static final Dimension TASKPANEL_SIZE = new Dimension(GUI_SIZE.width - 30, GUI_SIZE.height - 175 );
    //  TASKPANEL_SIZE 는 Dimension(540,760)에서 너비 - 30 과 높이 -175 빼고 설정
    //  TASKPANEL_SIZE 는 510 , 585 설정하겠다.

    public static final Dimension ADDTASK_BUTTON_SIZE = new Dimension(GUI_SIZE.width, 50);
    // ADDTASK_BUTTON_SIZE 는 전체 사이즈 ( 540 , 760 ) 중 너비 50으로 설정하겠다.

    public static final Dimension TASKFIELD_SIZE = new Dimension((int)(TASKPANEL_SIZE.width * 0.80), 50);
    //  TASKFIELD_SIZE는 Dimension( 540 , 760 ) 전체 사이즈 안에 
    //  TASKPANEL_SIZE.width(= TaskPanel 너비 값을 가지고 와서) * 0.80( 80% 곱하여 설정 값 적용) , 윈도우 높이는 50 값으로 설정하겠다.

    public static final Dimension CHECKBOX_SIZE = new Dimension((int)(TASKFIELD_SIZE.width * 0.05), 50);
    //  CHECKBOX_SIZE는 Dimension( 540, 760 ) 전체 사이즈 안에
    //  TASKFIELD_SIZE.width 너비 값을 가지고 와서 * 0.05( 5% 곱하여 설정 값 적용) , 윈도우 높이는 50 값으로 설정하겠다.
    
    public static final Dimension DELETE_BUTTON_SIZE = new Dimension((int)(TASKFIELD_SIZE.width * 0.12), 50);
    //  DELETE_BUTTON_SIZE는 Dimension( 540, 760 ) 전체 사이즈 안에
    // TASKFIELD_SIZE.width 너비 값을 가지고 와서 * 0.12( 12% 곱하여 설정 값 적용) , 윈도우 높이는 50 값으로 설정하겠다.

}

