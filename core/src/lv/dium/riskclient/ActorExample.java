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
    private final AssetManager manager;

    static final ArrayList<MyActor> myActors = new ArrayList<>();

    public static final BitmapFont font = new BitmapFont();

    public ActorExample(Game game) {
        this.game = game;
        manager = new AssetManager();
    }

    @Override
    public void show() {
        viewport = new FitViewport(1920, 1024);
        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);

        loadAssets();

        SecureChatClient.start();

        // South Americas
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 10, 200));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 10, 300));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 95, 250));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 95, 350));

        // Europe
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 355, 200));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 355, 300));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 355, 400));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 270, 350));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 270, 450));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 440, 350));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 440, 250));


        // North America
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 270, 550));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 270, 650));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 270, 750));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 180, 600));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 180, 700));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 180, 800));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 360, 700));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 360, 600));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 450, 650));

        // Africa

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 440, 150));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 440, 50));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 530, 200));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 530, 100));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 530, 0));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 620, 50));

        // Asia

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 530, 300));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 530, 400));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 530, 500));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 620, 350));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 620, 450));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 620, 550));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 620, 650));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 710, 300));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 710, 500));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 710, 600));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 710, 700));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 800, 450));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 800, 550));

        // Australia
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 800, 250));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 800, 150));

        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 890, 200));
        myActors.add(new MyActor(manager.get(AssetDescriptors.hex) , AssetDescriptors.hex.fileName, 890, 100));


        for (MyActor a : myActors) {
            stage.addActor(a);
        }
    }

    public static void loadGameFromServerState(GameRisk serverState){
        if(serverState != null && serverState.getId()!=null)
        for (GameArea a : serverState.getAreas()) {
            myActors.get(Integer.valueOf(a.getId())).setUnits(Integer.valueOf(a.getStr())).setColorFromServer(a.getColor());
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

        manager.finishLoading();
    }
}