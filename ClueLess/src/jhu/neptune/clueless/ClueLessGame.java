package jhu.neptune.clueless;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import jhu.neptune.clueless.assets.ClueLessAssets;
import jhu.neptune.clueless.screens.api.ClueLessMenuScreens;
import jhu.neptune.clueless.screens.api.ScreenManager;

/**
 * Main Class of ClueLess game. Extends LibGDX Game class.
 * Use ScreenManager singleton to change screens
 */
public class ClueLessGame extends Game {
	// Loads all of our assets and stores references to them.
	public ClueLessAssets assets;
	public BitmapFont font;

	public ClueLessGame() {
		super();
		assets = new ClueLessAssets();
	}

	//@Override
	public void create () {
		assets.getInstance().loadAllAssets();
		font = new BitmapFont();

		//Show SplashScreen
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().showScreen(ClueLessMenuScreens.SPLASH_SCREEN, 0);
	}

	//@Override
	public void render() {
		super.render();
	}

	/**
	 * Disposes all resources and assets
	 */
	//@Override
	public void dispose() {
		assets.getInstance().dispose();
		getScreen().dispose();
		font.dispose();
		Gdx.app.exit();
	}

	/** Updates the AssetManager, keeping it loading any assets in the preload queue.
	 * @return true if all loading is finished. */
	public boolean hasAssetsUpdated() {
		return assets.getInstance().manager.update();
	}
}
