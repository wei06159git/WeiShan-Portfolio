package jhu.neptune.clueless.screens.api;

import jhu.neptune.clueless.screens.gamescreens.MainGameScreen;
import jhu.neptune.clueless.screens.menuscreens.*;
import jhu.neptune.clueless.screens.menuscreens.connectionScreens.*;

/**
 * If a new screen is added, make sure to add it here
 * and make sure to edit the ClueLessGame class
 * so that it handles the screen
 * Brilliant methods obtained from here: https://stackoverflow.com/a/44015675
 */
public enum ClueLessMenuScreens {

    SPLASH_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new SplashScreen();
        }
    },

    MAIN_MENU {
        public AbstractScreen getScreen(Object... params) { return new MainMenu(); }
    },

        // Screens used to host/connect to game
        CONNECT_TO_HOST {
            public AbstractScreen getScreen(Object... params) { return new ConnectToHostScreen();}
        },
        HOST_GAME {
            public AbstractScreen getScreen(Object... params) {return new HostGameScreen();}
        },

    SETTINGS_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new SettingsScreen();
        }
    },
    HELP_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new HelpScreen();
        }
    },
    CREDITS_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new CreditsScreen();
        }
    },
    MAIN_GAME_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new MainGameScreen(params);
        }
    },

    EXIT_GAME {
        @Override
        public AbstractScreen getScreen(Object... params) {
            return new ExitGame();
        }
    };
    public abstract AbstractScreen getScreen(Object... params);
}