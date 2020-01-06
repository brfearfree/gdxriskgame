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

public class MyActor extends Actor {

    Sprite sprite;
    Sprite sprite_main;
    private BitmapFont font = new BitmapFont();

    private int units = 12;
    private boolean isSelectedAsMain = false;
    private boolean isSelectedAsTarget = false;
    private boolean isValidTarget = false;

    private final int id;

    private final Texture texture_basic;
    private final Texture texture_main;
    private final Texture texture_targetable;
    private final Texture texture_targeted;

    public MyActor(final int id, final float x, final float y) {
        this.id = id;

        texture_basic = ActorExample.manager.get(AssetDescriptors.hex);
        texture_basic.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texture_main = ActorExample.manager.get(AssetDescriptors.hex_main);
        texture_main.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texture_targetable = ActorExample.manager.get(AssetDescriptors.hex_targeteable);
        texture_targetable.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texture_targeted = ActorExample.manager.get(AssetDescriptors.hex_targeted);
        texture_targeted.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        sprite = new Sprite(texture_basic);

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
                    Gdx.app.log("Touch down area at x: ", String.valueOf(x));

                    if (currentActor.isSelectedAsMain) {
                        ActorExample.mainAreaDeselected(id);
                        Gdx.app.log("De-selected main area ", String.valueOf(id));
                    }
                    else if(isValidTarget){
                        if(isSelectedAsTarget){
                            Gdx.app.log("Target unlocked ", String.valueOf(id));
                            ActorExample.targetAreaDeselected(id);
                        }
                        else {
                            Gdx.app.log("Target locked ", String.valueOf(id));
                            ActorExample.targetAreaSelected(id);
                        }
                    }
                    else {
                        ActorExample.newMainArea(id);
                        Gdx.app.log("Picked main area ", String.valueOf(id));
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

        int adjustX = 40;
        if(units<10){
            adjustX = 50;
        }

        ActorExample.font.draw(batch, String.valueOf(id), sprite.getX()+adjustX, sprite.getY()+60);
    }

    public MyActor setUnits(int units) {
        this.units = units;
        return this;
    }
    public MyActor setColorFromServer(String serverColor) {

        /*
        allColors.add("red");
        allColors.add("green");
        allColors.add("grey");
        allColors.add("blue");
        allColors.add("yellow");
        allColors.add("purple");
        */

        if(serverColor == null){
            this.setColor(Color.ORANGE);
        }
        else if(serverColor.equals("red")){
            this.setColor(Color.RED);
        }
        else if(serverColor.equals("green")){
            this.setColor(Color.GREEN);
        }
        else if(serverColor.equals("grey")){
            this.setColor(Color.GRAY);
        }
        else if(serverColor.equals("blue")){
            this.setColor(Color.BLUE);
        }
        else if(serverColor.equals("yellow")){
            this.setColor(Color.YELLOW);
        }
        else if(serverColor.equals("purple")){
            this.setColor(Color.PURPLE);
        }
        else{
            this.setColor(Color.ROYAL);
        }
        return this;
    }

    public void setValidTarget(boolean validTarget) {
        isValidTarget = validTarget;
        if(isValidTarget){
            sprite.setTexture(texture_targetable);
        }
        else{
            sprite.setTexture(texture_basic);
            if(isSelectedAsTarget) {
                isSelectedAsTarget = false;
            }
        }
    }

    public void setMain(boolean isMain){
        if(isMain) {
            isSelectedAsMain = true;
            sprite.setTexture(texture_main);
        }
        else{
            isSelectedAsMain = false;
            sprite.setTexture(texture_basic);
        }
    }

    public void setAsTarget(boolean isTarget){
        if(isTarget) {
            isSelectedAsTarget = true;
            sprite.setTexture(texture_targeted);
            Gdx.app.log("READY TO FIRE! ", String.valueOf(id));
        }
        else{
            isSelectedAsTarget = false;
            Gdx.app.log("EVERYBODY CALM. ", String.valueOf(id));
            sprite.setTexture(texture_targetable);
        }
    }


    public int getId() {
        return id;
    }
}