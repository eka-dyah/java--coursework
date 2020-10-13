package GrayscaleImg;

import edu.duke.*;

import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int avrg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
            pixel.setRed(avrg);
            pixel.setBlue(avrg);
            pixel.setGreen(avrg);
        }

        return outImage;
    }

    public void testGray() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inFile = new ImageResource(f);
            ImageResource gray = makeGray(inFile);
            String newFileName = "gray-" + inFile.getFileName();
            gray.setFileName(newFileName);
            gray.draw();
            gray.save();
        }
    }

    public static void main(String[] args) {
        GrayScaleConverter gray = new GrayScaleConverter();
        gray.selectAndConvert();
    }
}
