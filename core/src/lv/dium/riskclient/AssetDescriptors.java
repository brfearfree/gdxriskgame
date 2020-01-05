package lv.dium.riskclient;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

public class AssetDescriptors {
    public static final AssetDescriptor<Texture> skeleton = new AssetDescriptor<Texture>(Assets.SKELETON, Texture.class);
    public static final AssetDescriptor<Texture> brokenRocket = new AssetDescriptor<Texture>(Assets.BROKENROCKET, Texture.class);
    public static final AssetDescriptor<Texture> hex = new AssetDescriptor<Texture>(Assets.HEX, Texture.class);
    public static final AssetDescriptor<Texture> rocket = new AssetDescriptor<Texture>(Assets.ROCKET, Texture.class);

    public static final AssetDescriptor<Texture> hex_main = new AssetDescriptor<Texture>(Assets.HEX_MAIN, Texture.class);
    public static final AssetDescriptor<Texture> hex_targeteable = new AssetDescriptor<Texture>(Assets.HEX_TARGETABLE, Texture.class);
    public static final AssetDescriptor<Texture> hex_targeted = new AssetDescriptor<Texture>(Assets.HEX_TARGETED, Texture.class);


    private AssetDescriptors(){}
}