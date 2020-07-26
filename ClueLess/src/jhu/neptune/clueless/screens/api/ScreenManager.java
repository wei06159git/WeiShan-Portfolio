package jhu.neptune.clueless.screens.api;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Singleton class to Manage screens
 */
public class ScreenManager {

    private static ScreenManager instance;

    private Game game;

    private ScreenManager() {
        super();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(Game game) {
        this.game = game;
    }
    public void showScreen(ClueLessMenuScreens screenEnum, Object... params) {
        Screen currentScreen = game.getScreen();

        AbstractScreen newScreen = screenEnum.getScreen(params);

        newScreen.buildStage();
        game.setScreen(newScreen);

        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
    }
}