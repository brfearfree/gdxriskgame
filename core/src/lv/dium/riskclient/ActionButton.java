package lv.dium.riskclient;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.Random;

public class ActionButton extends Actor {

    Sprite sprite;

    public String label = "Click me!";
    public String action = "";
    public boolean isHandled = false;

    public ActionButton(final float x, final float y) {
        Texture texture = ActorExample.manager.get(AssetDescriptors.hex);
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        sprite = new Sprite(texture);

        spritePos(x, y);
        setTouchable(Touchable.enabled);

        final ActionButton currentActor = this;

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
                    Gdx.app.log("Touch down button with label ", label);

                    if(action.equals("login")) {
                        if(!isHandled) {
                            isHandled = true;
                            int playerIndex = new Random().nextInt(100);
                            playerIndex = 22;
                            SecureChatClient.send("!lJohnDoe" + playerIndex + ":psw");
                            currentActor.setColor(Color.CORAL);
                            label = "OK";
                        }
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

        ActorExample.font.draw(batch, label, sprite.getX()+17, sprite.getY()+60);
    }

}