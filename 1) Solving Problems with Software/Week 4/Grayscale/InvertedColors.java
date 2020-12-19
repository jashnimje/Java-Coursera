
/**
 * Write a description of class InvertedColors here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class InvertedColors {
    public ImageResource makeInverted(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }

    public void testInverted() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeInverted(ir);
        gray.draw();
    }

    public void batchConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource image = new ImageResource(f);
            ImageResource gray = makeInverted(image);
            String fname = image.getFileName();
            String newName = "inverted-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
}
