import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java BMPMirrorFlipper <bmp-file-path>");
            return;
        }

        String inputFilePath = args[0];
        File inputFile = new File(inputFilePath);

        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("Error: The specified file does not exist or is not a valid file.");
            return;
        }

        try {
            BufferedImage originalImage = ImageIO.read(inputFile);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            BufferedImage mirroredImage = new BufferedImage(width, height, originalImage.getType());

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int mirroredX = width - 1 - x;
                    mirroredImage.setRGB(mirroredX, y, originalImage.getRGB(x, y));
                }
            }

            String outputFilePath = inputFile.getParent() + "/mirrored_" + inputFile.getName();
            File outputFile = new File(outputFilePath);
            ImageIO.write(mirroredImage, "bmp", outputFile);

            System.out.println("Mirrored BMP saved as: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Error processing the BMP file: " + e.getMessage());
        }
    }
}
