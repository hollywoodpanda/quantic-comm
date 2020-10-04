package br.net.uc.quantic.utils;

public class Values {

    public static final String SPACE_TEXTURE_PATH = "space.png";

    public static final float CAMERA_VIEWPORT_WIDTH = 800;

    public static final float CAMERA_VIEWPORT_HEIGHT = 600;

    public static final String SKIN_PATH = "uiskin.json";

    public static final float PARENT_ALPHA = 1;

    public static final String BUTTON_TITLE = "Enviar";

    public static final String GAME_OVER = "GAME OVER";

    public static final long INITIAL_LEVEL_TIME = 0L;

    public static final long DEFAULT_LEVEL_TIME = 10000L;

    public static final long DEFAULT_TIMER_STEP = 1000L;

    public static final void log (String text) {

        System.out.println(text);

    }

    public static final void logArray (float[] values) {

        System.out.print("[ ");

        for (float val : values) {
            System.out.print(val + ", ");
        }

        System.out.print(" ]\r\n");

    }

}
