public class GuessTheNumberGuiVersion{
	private static final int X_CO_ORDINATE = 400;
	private static final int Y_CO_ORDINATE = 200;
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 300;
	public static void main(String[]args){
		GuessTheNumber gn = new GuessTheNumber();
		gn.setBounds(X_CO_ORDINATE,Y_CO_ORDINATE,WINDOW_WIDTH,WINDOW_HEIGHT);
		gn.setVisible(true);
		
	}
}