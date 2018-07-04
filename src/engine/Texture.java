package engine;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
	private int id;
	private int width;
	private int height;
	
	public Texture() {
		this.id = glGenTextures();
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, this.id);
	}
	
	public void setParam(int name, int value) {
		glTexParameteri(GL_TEXTURE_2D, name, value);
	}

    public void uploadData(int internalFormat, int width, int height, int format, ByteBuffer data) {
        glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, GL_UNSIGNED_BYTE, data);
    }
	
    public void delete() {
        glDeleteTextures(id);
    }
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
        }
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        }
    }
    
    
    public static Texture createTexture(int width, int height, ByteBuffer data) {
        Texture texture = new Texture();
        texture.setWidth(width);
        texture.setHeight(height);

        texture.bind();

        texture.setParam(GL_TEXTURE_WRAP_S, GL_CLAMP);
        texture.setParam(GL_TEXTURE_WRAP_T, GL_CLAMP);
        texture.setParam(GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        texture.setParam(GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        texture.uploadData(GL_RGBA8, width, height, GL_RGBA, data);

        return texture;
    }

    
    public static Texture loadTexture(String path) {
        ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            /* Prepare image buffers */
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            /* Load image */
            stbi_set_flip_vertically_on_load(true);
            image = stbi_load(path, w, h, comp, 4);
            System.out.println(path);
            if (image == null) {
                throw new RuntimeException("Failed to load a texture file!"
                                           + System.lineSeparator() + stbi_failure_reason());
            }

            /* Get width and height of image */
            width = w.get();
            height = h.get();
        }

        return createTexture(width, height, image);
    }
}
