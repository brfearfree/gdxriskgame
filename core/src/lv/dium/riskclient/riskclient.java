package lv.dium.riskclient;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class riskclient extends Game {
	public SpriteBatch batch;
	ShapeDrawer drawer;
	Texture img;
	Texture myImg;
	Texture myShapesImg;
	Texture circle;
	Sprite myCircle;
	riskArea myArea;
	List<riskArea> myAreas = new ArrayList<riskArea>();
	Texture myTexture;

	BitmapFont font;

	FitViewport viewport;
	OrthographicCamera camera;
	ShapeRenderer shape;

	private ShapeRenderer shapeRenderer;
	private FrameBuffer frameBuffer;
	private SpriteBatch spriteBatch;

	TextureRegion mainRegion;

	public final static float WIDTH = 1920;
	public final static float HEIGHT = 1024;

	public static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	@Override
	public void create () {

		Float width = 1920f;
		Float height = 1024f;

		batch = new SpriteBatch();
		font = new BitmapFont();

		camera = new OrthographicCamera(1920, 1024);

		for (int i = 0; i < 42; i++) {
			myAreas.add(new riskArea(this.getRandomNumberInRange(25,width.intValue()-25), this.getRandomNumberInRange(25,height.intValue()-25)));
		}

		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.drawPixel(0, 0);
		myTexture = new Texture(pixmap); //remember to dispose of later
		pixmap.dispose();
		myTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		TextureRegion region = new TextureRegion(myTexture, 0, 0, 1, 1);

		drawer = new ShapeDrawer(batch, region);

		setScreen(new MainMenuScreen(this));

	}
/*
	@Override
	public void render () {
		camera.update();
		Gdx.gl20.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		float secondsPassed = Gdx.graphics.getDeltaTime();
		//System.out.println(secondsPassed+"s");
		drawer.setColor(0f, 150f, 150f, 1);
		for (int i = 0; i < 42; i++) {
			myAreas.get(i).tick(secondsPassed);
			drawer.setColor(myAreas.get(i).getColorA(), myAreas.get(i).getColorB(), myAreas.get(i).getColorC(), 1);
			drawer.filledPolygon(myAreas.get(i).getX(), myAreas.get(i).getY(), 6, 40, 40);
		}

		batch.end();
	}
	*/
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		myImg.dispose();
		myTexture.dispose();
	}
}
