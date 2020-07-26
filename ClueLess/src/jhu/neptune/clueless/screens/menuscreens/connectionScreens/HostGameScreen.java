package jhu.neptune.clueless.screens.menuscreens.connectionScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jhu.neptune.clueless.screens.api.*;

import java.net.*;

public class HostGameScreen extends AbstractScreen {
    private Skin skin;
    private Stage stage;
    private Table hostTable;
    private Music hoverSound;

    @Override
    public void buildStage() {
        skin = new Skin(Gdx.files.internal("skins/terra/terramotherui/terra-mother-ui.json"));
        hoverSound = Gdx.audio.newMusic(Gdx.files.internal("audio/fx/XF_HatMisc19.wav"));

        // Set the stage and allow input / output
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        root.setBackground((skin.getTiledDrawable("tile-a").tint(Color.valueOf("#DC143C"))));
        //root.setDebug(true); //Enables debug lines for tables.
        stage.addActor(root);

        ////////////////////////////////////////////////////////////////////////////////
        Label namePromptLabel = new Label("Enter your name:", skin);

        TextField nameTextField = new TextField("", skin);
        nameTextField.setMaxLength(20);
        nameTextField.setTextFieldListener((textField, c) -> ClueLessCommon.player_name = textField.getText());
        stage.setKeyboardFocus(nameTextField);
        nameTextField.setCursorPosition(nameTextField.getText().length());

        Label num_of_players_prompt = new Label("Select number of players:", skin);
        Label num_of_players = new Label("Selecting player # starts the server!", skin);

        InetAddress localHostAddress = null;
        Label host = null;
        try {
            localHostAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (localHostAddress != null) {
            host = new Label("ClueLess client will run\n on " + localHostAddress.getHostAddress(), skin);
        }
        ////////////////////////////////////////////////////////////////////////////////
        // Add widgets to the table here.
        hostTable = new Table(skin);
        hostTable.setBackground("window-c");
        hostTable.add(namePromptLabel).left();
        hostTable.add(nameTextField).right();

            hostTable.row();

        hostTable.add(num_of_players_prompt).left();
        preparePlayerSelection();

            hostTable.row();

        hostTable.add(host).left();

            hostTable.row();

        //Add option to return to main menu
        hostTable.add(ClueLessImageButtons.getInstance().getMenuButton("Return to Main Menu", ClueLessMenuScreens.MAIN_MENU)).left();
        hostTable.add(ClueLessImageButtons.getInstance().getMenuButton("Start a ClueLess game", ClueLessMenuScreens.MAIN_GAME_SCREEN).right());
            hostTable.row();

        hostTable.add(num_of_players).right();
            hostTable.row();

        //Build menu table done
        root.add(hostTable).height(600.0f).width(600.0f);
    }

    private void preparePlayerSelection() {
        ImageTextButton imageTextButton;
        for (int i = 3; i <7 ; i++) {
            imageTextButton = new ImageTextButton(Integer.toString(i), skin);

            imageTextButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ImageTextButton button = (ImageTextButton) event.getListenerActor();
                    ClueLessCommon.number_of_players = button.getLabel().getText().toString();
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    hoverSound.setVolume(0.50f);
                    hoverSound.play();
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    hoverSound.stop();
                }
            });
            if (i == 3) {
                hostTable.add(imageTextButton).expandX().right();
            } else {
                hostTable.add(imageTextButton).padLeft(10.0f);
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}
