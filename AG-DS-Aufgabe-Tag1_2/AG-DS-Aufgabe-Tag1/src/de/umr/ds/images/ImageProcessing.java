package de.umr.ds.images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageProcessing {

	/**
	 * Converts the input RGB image to a single-channel gray scale array.
	 * 
	 * @param img The input RGB image
	 * @return A 2-D array with intensities
	 */
	private static int[][] convertToGrayScaleArray(BufferedImage img) {

		final double rCoeff = 0.299;
		final double gCoeff = 0.587;
		final double bCoeff = 0.114;
		final int xMax = img.getWidth();
		final int yMax = img.getHeight();
		int[][] listOrigin = new int[xMax][yMax];
		int[][] listGray = new int[xMax][yMax];
		for(int x = 0; x < xMax; x++){
			for(int y = 0; y < yMax; y++){
				int pixel = img.getRGB(x, y);
				listOrigin[x][y] = pixel;
			}
		}

		 int color2 = listOrigin[0][0];
		int r2 = (color2 >> 16) & 0xFF;
		int g2 = (color2 >> 8) & 0xFF;
		int b2 = color2 & 0xFF;
		System.out.println(Integer.toBinaryString(r2));
		System.out.println(Integer.toBinaryString(g2));
		System.out.println(Integer.toBinaryString(b2));
		System.out.println(" line ");


		for(int x = 0; x < xMax; x++){
			for(int y = 0; y < yMax; y++){
				int color = listOrigin[x][y];
				int r = (color >> 16) & 0xFF;
				int g = (color >> 8) & 0xFF;
				int b = color & 0xFF;
				int newG = (int)(rCoeff * r + g * gCoeff + b * bCoeff);
				int gray = newG | newG << 8 | newG << 16;
				listGray[x][y] = gray;
			}

		}
		return listGray;
	}

	/**
	 * Converts a single-channel (gray scale) array to an RGB image.
	 * 
	 * @param img
	 * @return BufferedImage
	 */
	private static BufferedImage convertToBufferedImage(int[][] img) {
		final int xMax = img.length;
		final int yMax = img[1].length;
		BufferedImage grayImg = new BufferedImage(xMax, yMax, 1);
		for(int x = 0; x < xMax; x++){
			for(int y = 0; y < yMax; y++){
				grayImg.setRGB(x,y, img[x][y]);
			}
		}
		return grayImg;
	}

	/**
	 * Converts input image to gray scale and applies the kernel.
	 * 
	 * @param img    RGB input image
	 * @param kernel
	 * @return convolved gray-scale image
	 */
	private static BufferedImage filter(BufferedImage img, Kernel kernel) {

		// TODO

		return null;
	}

	public static void main(String[] args) {
		ImageProcessing i = new ImageProcessing();

		BufferedImage img = null;
		try {
			URL url = i.getClass().getResource("example.jpg");
			File file = new File(url.getPath());
			img = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		convertToGrayScaleArray(img);


	}

}
