
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.util.function.Supplier;
import java.util.LinkedHashMap;
import javax.swing.*;
public class GuessTheNumber extends JFrame{
	
	private final JFrame f = new JFrame();
	
	//Game variable constants
	private final int EASY_GAME_MODE = 15;
	private final int INTERMEDIATE_GAME_MODE = 10;
	private final int HARD_GAME_MODE = 5;
	private final int EXTREME_HARD_GAME_MODE = 3;
	private final int BUTTON_1 = 1;
	private final int BUTTON_2 = 2;
	private final int BUTTON_3 = 3;
	private final int WIDTH = 300;
	private final int HEIGHT = 30;
	private final int Y = 10;
	private final int X = 120;
	
	private boolean isWin = false;
	private GameStatus gameStatus;
	
	private int level;
	private int turns;
	private int randomNumber;
	private int buttonId;
	private int countDownSixty;
	private int countDownFortyFive = 45;
	private int countDownThirty = 30;
	private int countDownFifteen = 15;
	private String selectedMode;
	private JPanel contentPane;
	
	private JLabel headerTitleTextLabel;
	private JLabel easyLevelLabel;
	private JLabel intermediateLevelLabel;
	private JLabel hardLevelLabel;
	private JLabel extremeHardLevelLabel;
	private JLabel randomNumberAnswerLabel;
	private JLabel randomNumberLabel;
	private JLabel outputLabel;
/*)	private JLabel howManyGuessesLabel;
	private JLabel sixtySecondsLabel;
	private JLabel fortyFiveSecondsLabel;
	private JLabel thirtySecondsLabel;
	private JLabel fifteenSecondsLabel;
	private JLabel timerOutputLabel;
	private JLabel countDownClockLabel;*/
	
	private JButton submitButton;
	private JButton easyLevelButton;
	private JButton intermediateLevelButton;
	private JButton hardLevelButton;
	private JButton extremeLevelButton;
	private JButton resetFieldsButton;
	private JButton peekButton;
	private JButton stopGameButton;
	
	private JTextField userGuessField;
	
	
	
	private Timer peekTimer;
	private Timer clearGuessLabel;
	private Timer howManyGuessesTimer;
	private Timer guessesTimer;
	
	private Timer sixtySecondsTimer;
	private Timer fortyFiveSecondsTimer;
	private Timer thirtySecondsTimer;
	private Timer fifthteenSecondTimer;
	
	public GuessTheNumber(){
		initializeFrame();
		initializeContentPane();
		addComponent();
		
	}
	
	private void initializeFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,495,356);
	}
	
	private void initializeContentPane(){
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255,255,0));
		contentPane.setForeground(new Color(255,128,64));
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
	}
	
	private void addComponent(){
		headerTitleTextLabel = new JLabel("Guess The Number");
		headerTitleTextLabel.setBackground(new Color(255,128,64));
		headerTitleTextLabel.setForeground(new Color(255,0,0));
		headerTitleTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerTitleTextLabel.setFont(new Font("Trebuchet Ms",Font.BOLD,18));
		headerTitleTextLabel.setBounds(120, 11, 300, 30);
		headerTitleTextLabel.setOpaque(true);
		contentPane.add(headerTitleTextLabel);
		
		addLabel("Countdown Timer: ",10,222,118,14);
		addLabel("60",120,220,19,14);
		easyLevelLabel = addLabel("1",149,91,19,14);
		intermediateLevelLabel = addLabel("1",244,91,19,14);
		hardLevelLabel = addLabel("1",333,91,19,14);
		extremeHardLevelLabel = addLabel("1",431,91,19,14);
		outputLabel = addLabel("",220,130,140,30);
		outputLabel.setBackground(Color.BLUE);
		outputLabel.setOpaque(true);
		randomNumberLabel = addLabel("The Random number was: ",10,231,150,14);
		randomNumberAnswerLabel = addLabel("10",160,231,19,14);
		
		userGuessField = addTextField(10,50,89,32);
		
		submitButton = addButton("Submit",10,82,89,32);
		easyLevelButton = addButton("Easy",111,52,89,32);
		intermediateLevelButton = addButton("Medium",203,52,89,32);
		hardLevelButton = addButton("Hard",296,52,89,32);
		extremeLevelButton = addButton("Extreme",395,52,89,32);
		resetFieldsButton = addButton("Reset",10,153,89,32);
		stopGameButton = addButton("Stop",10,188,89,32);
		peekButton = addButton("Peek",10,118,89,32);
	}
	
	private JLabel addLabel(String text,int x,int y,int width,int height){
		JLabel label = new JLabel(text);
		label.setForeground(new Color(255,0,0));
		label.setFont(new Font("Tahoma",Font.BOLD,11));
		label.setBounds(x,y,width,height);
		contentPane.add(label);
		return label;
	}
	
	private JTextField addTextField(int x,int y,int width,int height){
		JTextField textField = new JTextField();
		textField.setBounds(x,y,width,height);
		textField.setColumns(10);
		contentPane.add(textField);
		return textField;
	}
	
	private JButton addButton(String text,int x,int y,int width,int height){
		JButton button = new JButton(text);
		button.setBounds(x,y,width,height);
		contentPane.add(button);
		return button;
	}
	
}