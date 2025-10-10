package main;

public class MainApp {

	public static void main(String[] args) {
		
		try {
			
			String [] comando = {"xcopy", "C:\\Windows\\explorer.exe", "C:\\Users\\Elias\\workspace1\\Tarea1.06","/Y"};

			
			ProcessBuilder pb = new ProcessBuilder(comando);
			Process p = pb.start();
			
			
		} catch (Exception e) {
			
		}
		
	}
}
