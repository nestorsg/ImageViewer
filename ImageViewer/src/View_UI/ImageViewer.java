package View_UI;

import Model.Image;

public interface ImageViewer {

    public void setImage(Image image);

    public Image getImage();

    public void showPrevImage();

    public void showNextImage();
}
