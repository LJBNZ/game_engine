package engine;

import java.util.ArrayList;

public class Animation {
	private ArrayList<Frame> frames;
	private int current_frame;
	
	public Animation(int length) {
		frames = new ArrayList<Frame>();
		current_frame = 0;
	}
	
	public void render() {
		current_frame++;
		current_frame %= frames.size();
	}
}
