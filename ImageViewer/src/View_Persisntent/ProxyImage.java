package View_Persisntent;

import Model.Bitmap;
import Model.Image;

public class ProxyImage extends Image {
    
    private final ImageLoader loader;
    private Image realImage;

    public ProxyImage(ImageLoader loader) {
        this.loader = loader;
    }
    
    @Override
    public Bitmap getBitmap() {
        checkImageLoaded();
        return realImage.getBitmap();
    }

    private void checkImageLoaded() {
        if (realImage != null) return;
        realImage = loader.load();
    }
}