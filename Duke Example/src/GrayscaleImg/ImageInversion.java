package GrayscaleImg;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class ImageInversion {
    public ImageResource makeInverse(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setBlue(255 - inPixel.getBlue());
            pixel.setGreen(255 - inPixel.getGreen());
        }

        return outImage;
    }

    public void testInverse() {
        ImageResource ir = new ImageResource();
        ImageResource inv = makeInverse(ir);
        inv.draw();
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inFile = new ImageResource(f);
            ImageResource inv = makeInverse(inFile);
            String newFileName = "inverted-" + inFile.getFileName();
            inv.setFileName(newFileName);
            inv.draw();
            inv.save();
        }
    }

    public static void main(String[] args) {
        ImageInversion inv = new ImageInversion();
        inv.selectAndConvert();
    }
}
