package engine;

public class Frame {
	private int length;
	private Sprite sprite;
	private int elapsed_frames;
	
	public Frame(int length, Sprite sprite) {
		this.length = length;
		this.sprite = sprite;
		elapsed_frames = 0;
	}
	
	public boolean render() {
		sprite.render();
		elapsed_frames++;
		if (elapsed_frames >= length) {
			elapsed_frames = 0;
			return true;
		}
		
		return false;
	}
}
