package lv.dium.riskclient;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class riskclient extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture circle;
	Sprite myCircle;
	riskArea myArea;
	List<riskArea> myAreas = new ArrayList<riskArea>();

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("map.jpg");
		circle = new Texture("circle.png");

		for (int i = 0; i < 42; i++) {
			myAreas.add(new riskArea(this.getRandomNumberInRange(0,800), this.getRandomNumberInRange(0,550)));
		}

		//myArea = new riskArea(100,100);
		/*
		ParticleEffect effect = new ParticleEffect();
		effect.load(Gdx.files.internal("particles/blueLight.p"), particleAtlas);
		effect.start();
		*/
//Setting the position of the ParticleEffect
		//effect.setPosition(100, 100);

//Updating and Drawing the particle effect
//Delta being the time to progress the particle effect by, usually you pass in Gdx.graphics.getDeltaTime();
		//effect.draw(batch, Gdx.graphics.getDeltaTime());
	/*
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal("packed/game.atlas"));
		TextureAtlas.AtlasRegion region = atlas.findRegion("circle.png");
		*/

		/*myCircle = new Sprite(region);*/

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(img, 0, 0);


		for (int i = 0; i < 42; i++) {
			batch.draw(circle, myAreas.get(i).getX(), myAreas.get(i).getY());
		}

		//batch.draw(circle, myArea.getX(), myArea.getY());


		//myCircle.setPosition(100, 100);
		//myCircle.draw(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
