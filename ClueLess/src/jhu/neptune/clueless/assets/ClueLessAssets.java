package jhu.neptune.clueless.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

import static jhu.neptune.clueless.assets.ClueLessAssets.Icons.TEAM_NEPTUNE_LOGO;
import static jhu.neptune.clueless.assets.ClueLessAssets.Icons.TEAM_NEPTUNE_LOGO_32x32;

/**
 * Static Singleton Utility class for game assets
 */
public class ClueLessAssets implements Disposable {

    public AssetManager manager;

    private ClueLessAssets instance;

    public ClueLessAssets() {
        manager = new AssetManager();
    }

    public ClueLessAssets getInstance() {
        if (instance == null) {
            instance = new ClueLessAssets();
        }
        return instance;
    }

    public void loadAllAssets() {

        // Load Application and Branding Icons
        manager.load(TEAM_NEPTUNE_LOGO, Texture.class);
        manager.load(TEAM_NEPTUNE_LOGO_32x32, Texture.class);


        // TODO: Load graphic assets
        // TODO: Load Audio assets
            // Load Sound Effects (fx)
            // Load Music

    }

    /**
     * Disposes of all assets.
     * Should only be called after application is closed by the user.
     */
    @Override
    public void dispose() {
        manager.dispose();
    }

    public class Graphics {

    }

    public static class Icons {
        public static final String TEAM_NEPTUNE_LOGO       = "icons/teamneptune.png";
        public static final String TEAM_NEPTUNE_LOGO_32x32 = "icons/teamneptune32x32.png";
    }

    // TODO: add sounds under "audio/fx" and "audio/music"
    public static class Audio {
        public static class SoundEffects {

        }

        public class Music {

        }
    }
}
