package lv.dium.riskclient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MyActor extends Actor {

    Sprite sprite;
    public boolean isSwitched = false;

    public MyActor(Texture texture, final String actorName, float x, float y) {
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        sprite = new Sprite(texture);

        //spritePos(sprite.getX(), sprite.getY());
        spritePos(x, y);
        setTouchable(Touchable.enabled);

        final MyActor currentActor = this;

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float mx, float my, int pointer, int button) {

                double r = 54f; // radius
                double s = 6f; // sides

                double cx = 57f;
                double cy = 50f;

                double m = r * Math.cos(Math.PI / s);   // the min dist from an edge to the center
                double d = Math.hypot(mx-cx, my-cy), // the mouse's distance to the center of the polygon
                        a = Math.atan2(cy-my, mx-cx); // angle of the mouse pointer

                if(d <= (r+m)/2 + Math.cos(a*s) * (r-m) / 2){
                    Gdx.app.log("Touch down asset with name ", actorName);
                    if(currentActor.isSwitched){
                        currentActor.setColor(Color.BLUE);
                        currentActor.isSwitched = false;
                    }
                    else{
                        currentActor.setColor(Color.RED);
                        currentActor.isSwitched = true;
                    }
                }
                return true;
            }
        });
    }

    public void spritePos(float x, float y){
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void act(float delta) {

        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor(); //keep reference to avoid multiple method calls
        sprite.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        sprite.draw(batch);
    }
}