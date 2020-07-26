package jhu.neptune.clueless.screens.gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jhu.neptune.clueless.networking.clients.ClueLessChatClient;
import jhu.neptune.clueless.networking.common.ChatNetwork;
import jhu.neptune.clueless.networking.servers.ClueLessChatServer;
import jhu.neptune.clueless.screens.api.AbstractScreen;


public class MainGameScreen extends AbstractScreen {

    private ClueLessChatServer chatServer;
    private ClueLessChatClient chatClient;

    //////////////////////////////////////////////////////////////////////////////
    private InputMultiplexer multiplexer;
    private Stage rootStage;
    private Stage chatStage;
    private Skin rootSkin;
    private Table rootTable;

    private int chatWidth = 400;
    private int chatHeight = 720;
    private static Label chatLabel;
    private static ScrollPane chatScrollPane;
    private static List<String> usersList;
    private ScrollPane userListScroll;
    private TextArea messageField;
    private ScrollPane inputScroll;
    private TextButton caseFileButton;

    public MainGameScreen(Object obj) {
        rootSkin = new Skin(Gdx.files.internal("skins/terra/terramotherui/terra-mother-ui.json"));
        rootStage = new Stage(new ScreenViewport());
        chatStage = new Stage(new ScreenViewport());

        multiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(multiplexer);

        rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setBackground((rootSkin.getTiledDrawable("tile-a")));

        generateChatWindow();
        generateGameBoard();
        rootStage.addActor(rootTable);
        multiplexer.addProcessor(rootStage);
        multiplexer.addProcessor(chatStage);

        try {
            chatServer = new ClueLessChatServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chatClient = new ClueLessChatClient();
    }

    @Override
    public void buildStage() {

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //    Generate Chat Window for MainGame Screen
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void generateGameBoard() {

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //    Generate Chat Window for MainGame Screen
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void generateChatWindow() {

        // Create layout table for the chat scene.
        Table rootChatTable = new Table();
        rootChatTable.setFillParent(true);

        chatLabel = new Label("", rootSkin);
        chatLabel.setWrap(true);
        chatLabel.setAlignment(Align.bottomLeft);

        chatScrollPane = new ScrollPane(chatLabel, rootSkin);
        chatScrollPane.setFadeScrollBars(false);

        rootChatTable.add(chatScrollPane).width(chatWidth).height(chatHeight - 50).colspan(2);

        usersList = new List<String>(rootSkin);

        userListScroll = new ScrollPane(usersList, rootSkin);
        userListScroll.setFadeScrollBars(false);

        rootChatTable.add(userListScroll).width(chatWidth - 250).height(chatHeight - 50).colspan(2);

        messageField = new TextArea("", rootSkin);
        messageField.setPrefRows(2);
        messageField.setCursorPosition(messageField.getText().length());
        messageField.setMaxLength(150);
        messageField.setTextFieldListener((textField, key) -> {

            if (key == '\r' || key == '\n') {
                textField.getOnscreenKeyboard().show(false);

                //Create ChatMessage
                ChatNetwork.ChatMessage msg = new ChatNetwork.ChatMessage();
                msg.text = messageField.getText();
                msg.timestamp = System.currentTimeMillis();

                //Post to server
                chatClient.client.sendTCP(msg);
                System.out.println("Sending message with content: " + msg);

                // Clear the textfield so the next message can be typed.
                messageField.setText("");
                messageField.setCursorPosition(messageField.getText().length());
                messageField.setMaxLength(100);
            }
        });

        inputScroll = new ScrollPane(messageField, rootSkin);
        inputScroll.setFadeScrollBars(false);

        rootChatTable.row();
        rootChatTable.add(inputScroll).width(chatWidth).colspan(2);

        caseFileButton = new TextButton("CaseFile", rootSkin);
        rootChatTable.add(caseFileButton).colspan(2);

        // Additional formatting
        rootChatTable.bottom();
        rootChatTable.right();
        chatStage.addActor(rootChatTable);
    }

    public static void onChatMessageReceived(String newMessage) {
        chatLabel.setText(chatLabel.getText() + newMessage + "\n");
        chatScrollPane.setScrollPercentY(100);
        chatScrollPane.layout();
        chatScrollPane.scrollTo(0,0,0,0);
    }

    public static void addUsersToList(String[] names){
        Array<String> users = usersList.getItems();
        for (String name : names) {
            if(!users.contains(name,false)) {
                users.add(name);
                usersList.setItems(users);
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        rootStage.act(delta);
        chatStage.act();
        rootStage.draw();
        chatStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        rootStage.getViewport().update(width, height, true);
        chatStage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        //Kill server/client
        chatServer.dispose();
        chatClient.dispose();

        //KillAssets
        rootSkin.dispose();
        rootStage.dispose();
        chatStage.dispose();
    }
}
