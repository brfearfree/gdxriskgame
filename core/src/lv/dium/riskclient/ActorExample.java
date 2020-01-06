package lv.dium.riskclient;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class ActorExample implements Screen {

    private final Game game;
    private Stage stage;
    private Viewport viewport;
    public static AssetManager manager = null;

    static final ArrayList<MyActor> myActors = new ArrayList<>();

    public static final BitmapFont font = new BitmapFont();

    private static GameRisk gameRisk;

    private static int mainAreaId = 99;
    private static int targetAreaId = 99;

    public ActorExample(Game game) {
        this.game = game;
        manager = new AssetManager();
    }

    public static void mainAreaDeselected(int actionId) {
        mainAreaId = 99;
        // no main area means no valid targets
        for (MyActor a: myActors) {
            a.setValidTarget(false);

            if(actionId == a.getId()){
                a.setMain(false);
            }
        }
    }

    public static void newMainArea(int areaId) {
        // If looks like loaded and required area exists in game scenario
        if(mainAreaId != 99 && mainAreaId != areaId){
            mainAreaDeselected(mainAreaId);
        }
        mainAreaId = areaId;
        myActors.get(mainAreaId).setMain(true);

        if(gameRisk != null && gameRisk.areas != null && gameRisk.areas.get(areaId) != null){
            for (Integer linkId : gameRisk.areas.get(areaId).links) {
                if(myActors.get(linkId) != null){
                    myActors.get(linkId).setValidTarget(true);
                }
            }
        }
    }

    public static void targetAreaDeselected(int areaId) {
        targetAreaId = 99;
        myActors.get(areaId).setAsTarget(false);
    }

    public static void targetAreaSelected(int areaId) {
        if(targetAreaId != 99){
            targetAreaDeselected(targetAreaId);
        }
        targetAreaId = areaId;
        myActors.get(areaId).setAsTarget(true);
    }

    @Override
    public void show() {
        viewport = new FitViewport(1920, 1024);
        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);

        loadAssets();

        SecureChatClient.start();

        int areaID = 0;
        //+ North America
        myActors.add(new MyActor(areaID++, 460, 662));
        myActors.add(new MyActor(areaID++, 370, 611));
        myActors.add(new MyActor(areaID++, 280, 560));

        myActors.add(new MyActor(areaID++, 370, 713));
        myActors.add(new MyActor(areaID++, 280, 662));
        myActors.add(new MyActor(areaID++, 190, 611));

        myActors.add(new MyActor(areaID++, 280, 764));
        myActors.add(new MyActor(areaID++, 190, 713));
        myActors.add(new MyActor(areaID++, 190, 815));

        //+ South Americas
        myActors.add(new MyActor(areaID++, 100, 353));
        myActors.add(new MyActor(areaID++, 100, 251));
        myActors.add(new MyActor(areaID++, 10, 302));
        myActors.add(new MyActor(areaID++, 10, 200));

        //+ Europe
        myActors.add(new MyActor(areaID++, 280, 459));
        myActors.add(new MyActor(areaID++, 280, 357));
        myActors.add(new MyActor(areaID++, 370, 408));

        myActors.add(new MyActor(areaID++, 370, 204));
        myActors.add(new MyActor(areaID++, 370, 306));

        myActors.add(new MyActor(areaID++, 460, 357));
        myActors.add(new MyActor(areaID++, 460, 255));

        //+ Asia
        myActors.add(new MyActor(areaID++, 640, 561)); //20
        myActors.add(new MyActor(areaID++, 640, 663)); //21
        myActors.add(new MyActor(areaID++, 820, 561)); //22

        myActors.add(new MyActor(areaID++, 730, 612)); //23
        myActors.add(new MyActor(areaID++, 730, 510)); //24
        myActors.add(new MyActor(areaID++, 820, 459)); //25
        myActors.add(new MyActor(areaID++, 550, 408)); //26
        myActors.add(new MyActor(areaID++, 640, 459)); //27
        myActors.add(new MyActor(areaID++, 550, 306)); //28
        myActors.add(new MyActor(areaID++, 640, 357)); //29
        myActors.add(new MyActor(areaID++, 730, 306)); //30
        myActors.add(new MyActor(areaID++, 550, 510)); //31

        //+ Australia 32-35
        myActors.add(new MyActor(areaID++, 820, 255));

        myActors.add(new MyActor(areaID++, 820, 153));
        myActors.add(new MyActor(areaID++, 910, 204));

        myActors.add(new MyActor( areaID++,910, 102));

        //+ Africa 36-41
        myActors.add(new MyActor(areaID++, 460, 153));
        myActors.add(new MyActor(areaID++, 550, 204));
        myActors.add(new MyActor(areaID++, 550, 102));

        myActors.add(new MyActor(areaID++, 460, 51));
        myActors.add(new MyActor(areaID++, 550, 0));
        myActors.add(new MyActor(areaID++, 640, 51));

        for (MyActor a : myActors) {
            stage.addActor(a);
        }

        ActionButton loginButton = new ActionButton( 1200, 100);
        loginButton.action = "login";
        loginButton.label = "Log In";
        stage.addActor(loginButton);
    }

    public static void loadGameFromServerState(GameRisk serverState){
        gameRisk = serverState;
        if(serverState != null && serverState.getId()!=null)
        for (GameArea a : serverState.getAreas()) {
            int index = Integer.valueOf(a.getId());
            myActors.get(index).setUnits(Integer.valueOf(a.getStr())).setColorFromServer(a.getColor());
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(0.57f, 0.77f, 0.85f, 1);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        //WssHandler.disconnect();
        stage.dispose();
        SecureChatClient.group.shutdownGracefully();
    }

    private void loadAssets() {

        manager.load(AssetDescriptors.brokenRocket);
        manager.load(AssetDescriptors.hex);
        manager.load(AssetDescriptors.rocket);
        manager.load(AssetDescriptors.skeleton);

        manager.load(AssetDescriptors.hex_targeted);
        manager.load(AssetDescriptors.hex_targeteable);
        manager.load(AssetDescriptors.hex_main);

        manager.finishLoading();
    }
}