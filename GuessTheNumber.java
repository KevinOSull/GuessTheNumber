
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
	private final int BUTTON_4 = 4;
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
	private JLabel howManyGuessesLabel;
	private JLabel timerOutputLabel;
	private JLabel countDownClockLabel;
	
	private JButton submitButton;
	private JButton easyLevelButton;
	private JButton intermediateLevelButton;
	private JButton hardLevelButton;
	private JButton extremeLevelButton;
	private JButton resetFieldsButton;
	private JButton peekButton;
	private JButton stopGameButton;
	private JButton exitButton;
	
	private Timer peekTimer;
	private Timer clearGuessLabel;
	private Timer howManyGuessesTimer;
	private Timer guessesTimer;
	
	private Timer sixtySecondsTimer;
	private Timer fortyFiveSecondsTimer;
	private Timer thirtySecondsTimer;
	private Timer fifthteenSecondTimer;

	private String[] difficultyModes = new String[]{"EASY","MEDIUM","HARD","EXTREME"};
	private int[] difficultyLevels = new int[]{EASY_GAME_MODE,INTERMEDIATE_GAME_MODE,HARD_GAME_MODE,EXTREME_HARD_GAME_MODE};
	private JLabel[] levelLabels = new JLabel[]{easyLevelLabel,intermediateLevelLabel,hardLevelLabel,extremeHardLevelLabel};
	private Timer[] gameTimers = new Timer[]{sixtySecondsTimer,fortyFiveSecondsTimer,thirtySecondsTimer,fifthteenSecondTimer};
	private int[] buttonDifficultyLevels = new int[]{BUTTON_1,BUTTON_2,BUTTON_3,BUTTON_4};
	private JButton[] levelButtons;
	private JButton[] gameFlowButtons = new JButton[]{submitButton,resetFieldsButton,stopGameButton,exitButton};
	
	private JTextField userGuessField;
	
	public GuessTheNumber(){
		gameStatus = GameStatus.GAME_ON;
		initializeFrame();
		initializeContentPane();
		addComponent();
		initializeDifficultyButtonListeners();
	}

	private void initializeDifficultyButtonListeners(){
		for(int i = 0; i < levelButtons.length; i++){
			difficultyGameActionListener(levelButtons[i]);
		}
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
		
		countDownClockLabel = addLabel("Countdown Timer: ",10,222,118,14);
		timerOutputLabel = addLabel("60",120,220,19,14);
		easyLevelLabel = addLabel("0",149,91,19,14);
		intermediateLevelLabel = addLabel("0",244,91,19,14);
		hardLevelLabel = addLabel("0",333,91,19,14);
		extremeHardLevelLabel = addLabel("0",431,91,19,14);
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
		exitButton = addButton("Exit",385,187,89,32);
		levelButtons = new JButton[]{easyLevelButton,intermediateLevelButton,hardLevelButton,extremeLevelButton};
		initializeButtonListeners();
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

	private void initializeButtonListeners(){
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(gameStatus == GameStatus.GAME_ON){
					startOfGame(e);
				}
			}
		});
	}

	private void startOfGame(ActionEvent e){
		boolean isValid = isDataValid();
		if(!isValid){
			errorMessages();
		}else{
			checkChoice(e);
		}
	}

	private boolean isDataValid(){
		return !isInputEmpty().get() 
		      && !isGuessOutOfRange().get()
			  && !isGuessValidNumber().get();
	}

	private void checkChoice(ActionEvent e){
		Object sourceObject = e.getSource();
		for(int i = 0; i < difficultyLevels.length; i++){
			if(turns == difficultyLevels[i] && sourceObject == submitButton){
				checkGuess(levelLabels[i],e);
			}
		}
	}
	
	private int processButton(){
		switch(buttonId){
			case BUTTON_1:
				level = EASY_GAME_MODE;
				printOutNumberOfTurns(easyLevelLabel,EASY_GAME_MODE);
				break;

			case BUTTON_2:
				level = INTERMEDIATE_GAME_MODE;
				printOutNumberOfTurns(intermediateLevelLabel,INTERMEDIATE_GAME_MODE);
				break;

			case BUTTON_3:
				level = HARD_GAME_MODE;
				printOutNumberOfTurns(hardLevelLabel,HARD_GAME_MODE);
				break;

			case BUTTON_4:
				level = EXTREME_HARD_GAME_MODE;
				printOutNumberOfTurns(extremeHardLevelLabel,EXTREME_HARD_GAME_MODE);
				break;
		}
		return buttonId;
	}

	private int setGameDifficulty(ActionEvent e){
		Object sourceObject = e.getSource();
		for(int i = 0; i < levelButtons.length; i++){
			if(sourceObject == levelButtons[i]){
				buttonId = buttonDifficultyLevels[i];
				selectedMode = difficultyModes[i];
			}
		}
		turns = processButton();
		lockInDifficultyLevel();
		return buttonId;
	}

	private void difficultyGameActionListener(JButton button){
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				turns = setGameDifficulty(e);
				turns = level;
				
			}
		});
	}

	private void lockInDifficultyLevel(){
		easyLevelButton.setEnabled(false);
		intermediateLevelButton.setEnabled(false);
		hardLevelButton.setEnabled(false);
		extremeLevelButton.setEnabled(false);
	}

	private void printOutNumberOfTurns(JLabel label,int numberOfTurns){
		label.setText("" + numberOfTurns);
		
	}

	private void checkGuess(JLabel label,ActionEvent e){

	}

	private Map<String,Supplier<Boolean>>getErrorChecks(){
		Map<String,Supplier<Boolean>>errorMessages = new LinkedHashMap<>();
		errorMessages.put(getErrorMessages("emptyInput"),isInputEmpty());
		errorMessages.put(getErrorMessages("outOfRangeGuess"),isGuessOutOfRange());		
		errorMessages.put(getErrorMessages("invalidNumber"),isGuessValidNumber());
		return errorMessages;
	}

	private void handleErrors(Map<String,Supplier<Boolean>>errorMessages){
		for(Map.Entry<String,Supplier<Boolean>>entry:errorMessages.entrySet()){
			if(entry.getValue().get()){
				JOptionPane.showMessageDialog(f,entry.getKey());
				return;
			}
		}
	}

	private void errorMessages(){
		Map<String,Supplier<Boolean>>errorChecks = getErrorChecks();
		handleErrors(errorChecks);
	}

	private String getErrorMessages(String message){
		switch(message){
			case "emptyInput":
				return String.format("FIELD CANNOT BE EMPTY!!");

			case "outOfRangeGuess":
				return String.format( userGuessField.getText() + " GUESS IS OUT OF RANGE!! (1-20 ONLY!)");

			case "invalidNumber":
				return String.format("NUMBERS ONLY!! NO LETTERS OR SYMBOLS!!");

		}
		return message;
	}

	private Supplier<Boolean> isInputEmpty(){
		return()->{
			return userGuessField.getText().trim().isEmpty();
		};
	}

	private Supplier<Boolean> isGuessValidNumber(){
		return()->{
			String inputString = userGuessField.getText().trim();
			try{
				Integer.parseInt(inputString);
				return false;
			}catch(NumberFormatException e){
				return true;
			}
		};
	}

	private Supplier<Boolean> isGuessOutOfRange(){
		return()->{
			String inputString = userGuessField.getText().trim();
			try{
				int guess  = Integer.parseInt(inputString);
				return guess < 1 || guess > 20;
			}catch(NumberFormatException e){
				return false;
			}
		};
	}

	private void stopGameClock(){

	}
	
	private void displayRandomNumber(){
		randomNumber = getRandomNumber();
	}

	private int getRandomNumber(){
		return (int)(Math.random()*20)+1;
	}

	private void playAgainPrompt(){

	}

	//VERSION 1 OF THIS METHOD, METHOD WAS REFACOTED
	/*private int setGameDifficulty(ActionEvent e){
		Object sourceObject = e.getSource();
		for(int i = 0; i < levelButtons.length; i++){
			if(sourceObject == levelButtons[i]){
				buttonId = i + 1;
				switch(buttonId){
					case 1:
						buttonId = BUTTON_1;
						selectedMode = "EASY";
						break;

					case 2:
						buttonId = BUTTON_2;
						selectedMode = "MEDIUM";
						break;

					case 3: buttonId = BUTTON_3;
						selectedMode = "HARD";
						break;

					case 4: 
						buttonId = BUTTON_4;
						selectedMode = "EXTREME";
						break;

					default:
						System.out.println("SOMETHING WENT WRONG!!!!!");
					
				}
			}
		}
		turns = processButton();
		lockInDifficultyLevel();
		return buttonId;

	}*/


	/*private void checkChoice(ActionEvent e){
		Object sourceObject = e.getSource();
		switch(turns){
			case EASY_GAME_MODE:
				if(sourceObject == submitButton){
					checkGuess(easyLevelLabel,e);
				}
				break;
			
			case INTERMEDIATE_GAME_MODE:
				if(sourceObject == submitButton){
					checkGuess(intermediateLevelLabel,e);
				}
				break;

			case HARD_GAME_MODE:
				if(sourceObject == submitButton){
					checkGuess(hardLevelLabel,e);
				}
				break;
			
			case EXTREME_HARD_GAME_MODE:
				if(sourceObject == submitButton){
					checkGuess(extremeHardLevelLabel,e);
				}
				break;
		}
	}*/
}