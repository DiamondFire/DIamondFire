
package utm.csc492.diamondfire;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {

        // comment this out, if there are no new images
        packTextures();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 480;
		new LwjglApplication(new DiamondFire(), config);
	}


    /**
     * Pack the textures found in Android/assets/images dir.
     */

    static void packTextures() {
        // Write code to pack textures
    }
}
