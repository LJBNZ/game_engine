package game;
import static org.lwjgl.glfw.GLFW.glfwGetTime; 
import java.util.concurrent.TimeUnit;

public class Time {
	private double start;
	private double elapsed_sec;
	private int days;
	private int hours;
	private int minutes;
	private int seconds;
	private static final int WORLD_TIME_SPEED = 3600;
	
	public Time(double start) {
		this.start = glfwGetTime();
	}
	
	public void update() {
		elapsed_sec = (glfwGetTime() - start) * WORLD_TIME_SPEED;
		
		days = (int) TimeUnit.SECONDS.toDays((long) elapsed_sec);        
		hours = (int) (TimeUnit.SECONDS.toHours((long) elapsed_sec) - (days *24));
		minutes = (int) (TimeUnit.SECONDS.toMinutes((long) elapsed_sec) - (TimeUnit.SECONDS.toHours((long) elapsed_sec)* 60));
		seconds = (int) (TimeUnit.SECONDS.toSeconds((long) elapsed_sec) - (TimeUnit.SECONDS.toMinutes((long) elapsed_sec) *60));

	}
	
	public void outputTime() {
		System.out.format("D:%d H:%d M:%d S:%d \n", days, hours, minutes, seconds);
	}
	
	public int getCurrentDay() {
		return days;
	}
	
	public int getCurrentHour() {
		return hours;
	}
	
	public int getCurrentMinute() {
		return minutes;
	}
	
	public int getCurrentSeconds() {
		return seconds;
	}
	
}
