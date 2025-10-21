package jade;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    private int width, hight;
    private String title;
    private long glfwindow;

    private static Window window = null;
    private Window(){
        this.width = 1080;
        this.hight = 720;
        title = "THe game TM";
    }

    public static Window get(){
        if(Window.window == null)
            Window.window = new Window();

        return Window.window;
    }

    public void run(){
        System.out.println("REEEEEEEEEEEEEE version:"+ Version.getVersion());

        init();
        loop();

    }

    public void init(){
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        //glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE); //maximisi the window

        // Create the window
        glfwindow = glfwCreateWindow(this.width, this.hight, this.title, NULL, NULL);
        if ( glfwindow == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwindow);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(glfwindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

    }

    public void loop(){

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);



        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(glfwindow) ) {
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(glfwindow); // swap the color buffers


        }

    }

}
