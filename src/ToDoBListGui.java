import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

// JFrame 클래스를 부모로 ActionListener로 인터페이스를 구현하겠다.
public class ToDoBListGui extends JFrame implements ActionListener { // extends 는 클래스를 확장하는 거고 implements는 인터페이스를 구현하는 것이다.

    // private 클래스 내부에서만 접근 가능하다.
    // Jpanel 의 taskPanel, taskComponentPanel
    private JPanel taskPanel, taskComponentPanel;

    // ToDoBListGui 객체를 생성
    public ToDoBListGui() {
        // super() 자신이 상속받은 부모의 생성자를 호출하는 메소드
        // 자식 클래스 생성자 호출
        // To Do List Application 호출하겠다.
        super("To Do List AppLication");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponents();
    }

    private void addGuiComponents() {
        // 베너라벨 
        JLabel bannerLabel = new JLabel("To do List");
        // 폰트파일 추가하여 크기는 36크기로 설정
        bannerLabel.setFont(createFont("resources/lemon_milk/LemonMilk.otf", 36f));
        bannerLabel.setBounds(
                // CommonConstants 안에 GUI_SIZE.width 값 - bannerLabel 너비 값을 2로 나누고 간격은 15로 설정하였다.
                (CommonConstants.GUI_SIZE.width - bannerLabel.getPreferredSize().width) / 2,
                15,
                // 베너사이즈 너비와 높이 설정
                CommonConstants.BANNER_SIZE.width,
                CommonConstants.BANNER_SIZE.height
        );

        // taskPanel 과 taskComponentPanel 생성
        // 전체 할 일과 개별적인 할 일 관리 용이하게 하기 위해서 만들었다.
        taskPanel = new JPanel();

        taskComponentPanel = new JPanel();
        // taskComponentPanel 레이아웃 설정
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        // 패널 추가
        taskPanel.add(taskComponentPanel);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        // 스코롤패널는 Bounds에서 사이즈를 받아 처리
        scrollPane.setBounds(8,70, CommonConstants.TASKPANEL_SIZE.width, CommonConstants.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        // 최대크기 설정
        scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
        // VerticalScrollBarPolicy 수직 스크롤 막대를 표시할지 여부와 표시 방법을 결정하는데 사용되는 속성
        // AS_NEEDED 높이가 콘텐츠를 표시하기에 충분하지 않은 경우만 수직 스크롤 막대를 표시함.
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // HorizontalScrollBarPolicy 수평 스크롤 막대를 표시할지 여부와 표시 방법을 결정하는데 사용되는 속성
        // NEVER 는 표시하지 않음.
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // 수직 스코롤 막대 
        // UnitIncrement 스코롤 막대의 단위 증분을 나타내는 클래스
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        // 수직 스코롤 막대생성은 20으로 설정
        verticalScrollBar.setUnitIncrement(20);

        // 해야 할 일 추가 생성
        JButton addTaskButton = new JButton("Add Task");
        // 폰트 파일 경로 추가하여 크기는 18폰트트
        addTaskButton.setFont(createFont("resources/lemon_milk/LemonMilk.otf", 18f));
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // 해야 할 일 버튼크기 설정
        // -5 는 TaskButton 과 GUI 화면 오른쪽 사이의 간격 
        // - 88 는 TaskButton 과 GUI 화면 하단 사이의 간격
        addTaskButton.setBounds(-5, CommonConstants.GUI_SIZE.height - 88 ,
                CommonConstants.ADDTASK_BUTTON_SIZE.width, CommonConstants.ADDTASK_BUTTON_SIZE.height);
        // this 는 TaskButton 말함.
        addTaskButton.addActionListener(this);

        // 패널 추가하기기
        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);
    }

    // 사용자 지정 글꼴을 생성하는 메소드
    // resource 폰트 글꼴 리소스 경로
    private Font createFont(String resource, float size) {
        // 사용자 지정 글꼴이 포함된 리소스의 경로를 가져옴.
        String filePath = getClass().getClassLoader().getResource(resource).getPath();

        // 만약 filePath.contains 사용자 지정 글꼴을 저장하는데
        if(filePath.contains("%20")) {
            filePath = getClass().getClassLoader().getResource(resource).getPath()
                    // 공백이 포함되어져 있으면 replaceAll 사용하여 공백을 제거한다.
                    .replaceAll("%20", " ");
     니
        try {
            // 지정된 경로의 파일을 File 객체로 생성하는 과정에서
            File customFontFile = new File(filePath);
            // TureType 글꼴을 생성하고, 지정된 크기로 글꼴을 변환하고
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            // 생성된 글꼴을 반환 받는다
            return customFont;

        // Exception 발생했을 경우, 이를 처리하기 위한 문장
        }catch ( Exception e) {
            // 예외정보 출력하고
            System.out.println("Error: " + e);
        }
        // null 반환한다.
        return null;
    }

    @Override 
         // 해당 메소드가 부모 클래스에 있는 메서드를 Override 했다는 것을 명시적으로 선언

        // ActionListener 추상클래스 메소드 
    public void actionPerformed(ActionEvent e) {
        // ActionCommand 할당 값을 command 변수에 저장한다.
        String command = e.getActionCommand();

        // equalsIgnoreCase() 는 String 클래스에서 기본으로 제공하는 메소드이다. 이름과 같이  대소문자 구별 없이, 두 문자열의 동일 여부 비교
        // "ADD Task" 인지 여부를 체크하여
        if(command.equalsIgnoreCase("Add Task")){
            // 해야될 일 생성하고 채널에 추가한다.
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            // 해야될 일 패널이 두 개 이상인지 여부를 체크하고,
            if(taskComponentPanel.getComponentCount() > 1) {
                // previousTask 는 TaskComponent 클래스에 저장된다.
                // 해야될 일 패널의 마지막 두 번째 가져와서 저장한다.
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2 );
                    // 이때 TaskField 와 배경색상은 null 값으로 출력한다.
                    previousTask.getTaskField().setBackground(null);
            }
            
            // taskComponent TaskField() 메소드 값을 받고 requestFocus() 다시 설정한다.
            taskComponent.getTaskField().requestFocus();
            repaint();
            // taskComponent 레이아웃을 다시 계산한다.
            revalidate();
        }
    }
}
