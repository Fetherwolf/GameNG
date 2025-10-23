package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean mouseButtPressed[] = new boolean[3];
    private boolean isDragging;

    private MouseListener(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    public static MouseListener get(){
        if(MouseListener.instance == null)
            MouseListener.instance = new MouseListener();

        return MouseListener.instance;
    }

    public static void mousePosCallback(long window, double xPos, double yPos){
        get().lastX= get().xPos;
        get().lastY= get().yPos;
        get().xPos = xPos;
        get().yPos = yPos;
        get().isDragging = get().mouseButtPressed[0] || get().mouseButtPressed[1] || get().mouseButtPressed[2];
    }

    public static void mouseButtonCallback(long window, int button,int action, int mods){
        if(action == GLFW_PRESS) {
            if(button<get().mouseButtPressed.length)
                get().mouseButtPressed[button] = true;
        } else if(action == GLFW_RELEASE){
            if(button<get().mouseButtPressed.length){
                get().mouseButtPressed[button] = false;
                get().isDragging = false;
            }
         }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame(){
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static double getX(){
        return get().xPos;
    }
    public static double getY(){
        return get().yPos;
    }

    public static double getDeltaX(){
        return (get().lastX-get().xPos);
    }
    public static double getDeltaY(){
        return (get().lastY-get().yPos);
    }

    public static double getScrollX(){
        return get().scrollX;
    }
    public static double getScrollY(){
        return get().scrollY;
    }

    public static boolean getIsDragging(){
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button){
        if(get().mouseButtPressed.length < button)
            return get().mouseButtPressed[button];
        else
            return false;
    }
}
