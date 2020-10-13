package GrayscaleImg;

import edu.duke.*;

import java.io.File;

public class ImageSaver {
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            String fileName = image.getFileName();
            String newFileName = "gray-" + fileName;
            image.setFileName(newFileName);
            image.draw();
            image.save();
        }
    }

    public static void main(String[] args) {
        ImageSaver is = new ImageSaver();
        is.doSave();
    }
}
