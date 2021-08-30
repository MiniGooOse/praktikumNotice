package de.umr.ds.images;

/**
 * A kernel is a 2D-array. The array is transposed after initialization which
 * enables a more intuitive way of initializing a kernel. E.g a non-symmetric
 * kernel can be initialized by Kernel({{0,0,1} {0,1,0} {1,0,0}}) although the
 * array dimensions are actually [height][width].
 *
 */
public class Kernel {

	private double[][] k;
	private int height;
	private int width;

	/**
	 * Initializes the kernel by its transpose.
	 * 
	 * @param k
	 */
	Kernel(double[][] k) {
		// transpose
		this.k = new double[k.length][k[0].length]; // swap
		for (int x = 0; x < k[0].length; x++)
			for (int y = 0; y < k.length; y++)
				this.k[y][x] = k[x][y];
		this.width = this.k.length;
		this.height = this.k[0].length;

		if (this.width % 2 != 1 || this.height % 2 != 1)
			throw new IllegalArgumentException("Kernel size need to be odd-numbered");
		if (this.width < 3 || this.height < 3)
			throw new IllegalArgumentException("Minimum dimension is 3");
	}

	/**
	 * Convolves a single-channel image with the kernel.
	 * 
	 * @param img A single-channel image
	 * @return The convolved image
	 */
	public int[][] convolve(int[][] img) {
		final int xSize = img.length;
		final int ySize = img[1].length;
		final int newXSize = xSize - width + 1;
		final int newYSize = ySize - height + 1;
		int[][] converted = new int[newXSize][newYSize];



		for(int x = 0; x < newXSize; x++){
			for(int y = 0; y < newYSize; y++){
				//System.out.println(x + " "+ y);
				int sum = 0;
				int[][] block = new int[width][height];
				int[][] mul = new int[width][height];

				for(int i = 0; i < width; i++){
					for(int j = 0; j < height; j++){
						block[i][j] = img[x + i][y + j];
					}
				}

				// cal matrix
				for(int i = 0; i < width; i++){
					for(int j = 0; j < height; j++){
						mul[i][j] = (int)(this.k[i][j] * block[i][j]);
					}

				}
				// sum
				for(int i = 0; i < width; i++){
					for(int j = 0; j < height; j++){
						sum += mul[i][j];
					}
				}

				converted[x][y] = sum;
			}
		}

		return converted;
	}

	public int[][] convolve2(int[][] img) {
		final int xSize = img.length;
		final int ySize = img[1].length;
		final int xMargin = (width - 1)/ 2;
		final int yMargin = (height - 1)/ 2;

		int[][] framed = new int[xSize][ySize];
		for(int x = xMargin; x < xSize - xMargin; x++){
			for(int y = yMargin; y < ySize - yMargin; y++){
				framed[x][y] =img[x - xMargin][y-yMargin];
			}
		}

		int[][] converted = new int[xSize][ySize];

		for(int x = 0; x < xSize; x++){
			for(int y = 0; y < ySize; y++){
				//System.out.println(x + " "+ y);
				int sum = 0;
				int[][] block = new int[width][height];
				int[][] mul = new int[width][height];

				for(int i = 0; i < width; i++){
					for(int j = 0; j < height; j++){
						block[i][j] = framed[x + i][y + j];
					}
				}

				// cal matrix
				for(int i = 0; i < width; i++){
					for(int j = 0; j < height; j++){
						mul[i][j] = (int)(this.k[i][j] * block[i][j]);
					}

				}
				// sum
				for(int i = 0; i < width; i++){
					for(int j = 0; j < height; j++){
						sum += mul[i][j];
					}
				}

				converted[x][y] = sum;
			}
		}

		return converted;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}


}
