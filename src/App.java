import javax.swing.*;
// 경량 GUI 툴킷으로, 최적화된 윈도우 기반 애플리케이션을 구축하기 위한 다양한 위젯을 갖추고있다. JFC(Java Foundation Class)의 일부.

public class App {
    public static  void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            // Swing 기본적으로 mvc 모델을 채택한다. Model의 값이 변경되면 View의 형태를 다시 그리며, 이것을 컨트롤러가 제어 ( 단 스레드로 작동하며 동시에 여러 스레드에서 작동 될 경우 문제가 발생 )
            // InnokeLater는 Runable 이벤트를 이벤트 스레드에 등록하고 다음 작업을 실행
            
            @Override
            // 해당 메소드가 부모 클래스에 있는 메서드를 Override 했다는 것을 명시적으로 선언
            
            public void run() { new ToDoBListGui().setVisible(true); }
            // run() 메소드는 ToDoBListGui 클래스의 인스턴드 생성
            // ToDoBListGui 클래스의 setVisible() 메소드를 호출하여 왼도우를 표시
            // setVisible() 메소드에 ture를 전달하면 윈도우가 표시되고, false를 전달하면 윈도우가 숨겨짐.
        });
    }
}

