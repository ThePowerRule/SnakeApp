package application;
import java.util.HashMap;
import java.util.Map;
import gui.*;

public class AppManager{
	private static AppManager currentAppManager;
	private ServerBridge socket;
	private Map<Class<?>,String> snakeTypes = new HashMap<Class<?>, String>();
	//private static GUIController controller = new GUIController();
	private AppManager(){
	}
	private void init(){
		//Initialize and configure the snake.
		AppConfig.addSnakes();
		socket = new ServerBridge();
		//connectToServer();
	}
	public static void main(String[] args) {
		currentAppManager = new AppManager();
		currentAppManager.init();
		//Launch GUI
		GUI.run();
	}
	public void addSnakeType(Class<?> snakeClass, String snakeName) {
		snakeTypes.put(snakeClass, snakeName);
	}
	public void connectToServer(String serverAddress, int port){
		//Run the socket
		System.out.println("Connecting to server...");
		System.out.println("Socket live before connecting: " + socket.isConnected());
		socket.connectToServer(serverAddress, port);
		System.out.println("Socket live after connecting: " + socket.isConnected());
		//Did the socket connect?
		if(socket.isConnected())  {
			System.out.println("Connected!");
		}
		else System.out.println("Failed to Connect!");
		System.out.println("Socket live before listening: " + socket.isConnected());
		//Initialize the socket
		socket.listenAndParse();
		System.out.println("Socket live after listening: " + socket.isConnected());
	}
	public static AppManager getCurrentAppManager() {
		return currentAppManager;
	}

	//This is a console that was built into previous versions of the application.
	//Now that this project is in the process of migrating into JavaFX, the console
	//will not work effectively.  Before un-commenting this class, please BE SURE
	//this will be changed.  Thanks!

	public static class Console{
		//private static String[] lines = new String[64];
		//private static int numLines = 0;
		//private static TextArea gui = new TextArea("Welcome!");
		public static void init() {
			//gui.setEditable(false);
			//lines = new String[64];
			//numLines = 0;
		}
		public static void addText(String str) {
			/*
			numLines ++;
			if(numLines == lines.length) {
				for(int i = 0; i < lines.length-1; i ++) {
					lines[i] = lines[i+1];
				}
				numLines--;
			}
			lines[numLines] = str;
			String strings = "";
			for(int i = numLines; i >= 0; i --){
				String s = lines[i];
				if(s != null)	strings += s + "\n";
			}
			gui.setText(strings);
			 */
			System.out.println(str);
		}
	}

}
