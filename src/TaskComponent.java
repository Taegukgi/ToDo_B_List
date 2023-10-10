import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

@Getter // Class 내 모든 필드의 Getter method를 자동 생성한다.
@Setter // Class 내 모든 필드의 Setter method를 자동 생성한다.

 // JPanel 클래스를 확장시키고 ActionListner로 인터페이스를 구현하겠다.    
public class TaskComponent extends JPanel implements ActionListener { // extends 는 클래스를 확장하는 거고 implements는 인터페이스를 구현하는 것이다.

      // Private 클래스 내부에서만 접근 가능하다.
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;
    private JPanel parentPanel;

  
    // TasckComponent 의 parentPanel 새로운 인스턴스를 생성
    // parentPanel 변수에 매개변수로 전달된 parentPaneld을 할당
    public TaskComponent(JPanel parentPanel) {
        this.parentPanel = parentPanel;

         // taskField 변수에 새로운 JTextPane 인스턴스를 생성
        taskField = new JTextPane(); 
        // BorderFactory 클래스는 Border를 만드는 공장과 같은 클래스
        // createLineBorder 간결한 테두리
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        //taskField에 크기 설정
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        // taskField에 타입은 'text/html'
        taskField.setContentType("text/html");

        // FocusListener 클래스는 taskField 상태가 변경 될 때 호출되는 이벤트 리스너 
        taskField.addFocusListener(new FocusListener() {
            @Override
            //focusGained() 메드는 taskField가 할당을 받으면 focusGained() 메소드는 taskField의 배경색을 Color.WHITE로 설정
            // FocusEvent e는 FocusListener 인터페이스의 메소드에서 전달되는 이벤트 객채
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.WHITE);
            }

            @Override
            //focusLost() 메소드는 taskField가 할당값이 없을 때 focusLost() 메소드는 taskField의 배경색을 null로 설정
            public void focusLost(FocusEvent e) {
                taskField.setBackground(null);
            }
        });

        // CheckBox 생성
        checkBox = new JCheckBox();
        // PreferredSize는 Comonstants.CHCKBOX_SIZE 에서 받아옴
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        // Cursor 지정된 형태의 새로운 커서 객체를 생성
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // CheckBox 이벤트 처리
        // this는 TaskComponent 클래스의 인스턴스를 말하는 것이며, addActionListener() 에 값이 전달하면 CheckBox 이벤트를 처리함.
        checkBox.addActionListener(this);

        // deleteButton 생성 텍스트를 "X" 표시해서 생성
        deleteButton = new JButton("X");
        // PreferredSize는 Comonstants.DELETE_BUTTON_SIZE 에서 받아옴
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        // Cursor 지정된 형태의 새로운 커서 객체를 생성
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // this는 TaskComponent 클래스의 인스턴스를 말하는 것이며, addActionListener() 에 값이 전달하면 deleteButton 이벤트를 처리함.
        deleteButton.addActionListener(this);

        // 요소객체들 추가
        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // isSelected()는 웹 페이지에서 element가 선택되어있는지릏 확인하는 데 사용 라디오 버튼, 체크박스, 드롭다운과 같은 형태에서만 사용이 가능 
        // 활성화 되었을 경우에는 true, 비활성화 되어 있을 경우에는 false를 반환
        if(checkBox.isSelected()){
            // taskField의 텍스트에서 모든 HTML 태그를 제거하고 < 와 > 사이의 모든 문자가 제거
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            // <s> 텍스트 한가운데 라인을 추가하여 더 이상 정확하지 않거나 관련이 없는 텍스트를 표현할 때 사용.
            taskField.setText("<html><s>" +taskText+ "</s><html>");

            // checkBox.isSelected() false 값을 처리하는데 else if 에서 아래 코드가 컴퓨터는 false 값으로 처리하여 인식하게 되며,
            // !checkBox.isSelected() 사용하는 이유는 false 값 과 아무런 행동을 하지 않는 것에 대한 값을 ture 로 인식하게 만들었다.
        }else if (!checkBox.isSelected()) {
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);
        }

        // 텍스트를 "X" 대.소문자에 관련된 처리
        // equalsIgnoreCase() 는 String 클래스에서 기본으로 제공하는 메소드이다. 이름과 같이  대소문자 구별 없이, 두 문자열의 동일 여부 비교교
        if(e.getActionCommand().equalsIgnoreCase("X")) {
            // this 는 TackComponent 클래스의 인스턴를 말함.
            // parentPanel 게거하고 다시 만들어주는 역활 수행
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
