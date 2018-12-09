package cs230.application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import cs230.system.DatabaseManager;
//import cs230.system.PassInfo;
import cs230.system.SharedData;
import cs230.system.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class AvatarDrawController {

	@FXML
	private Canvas canvas;

	private GraphicsContext ctx;
	private int mx, my;
	private int[] pixels;
	private int imageWidth = 128;
	private int imageHeight = 128;
	private BufferedImage newAvatar;
	private boolean drawing;
	private int[] pallet;
	private int palletIndex;
	private int brushSize;
	
	private final int bigBrush = 10;
	private final int smallBrush = 3;

	/**
	 * Called when the user clicks the save button
	 * @param e
	 */
	public void saveAvatar(ActionEvent e) {
		
		// Store the images in the right place
		String path = new File("").getAbsolutePath();
		path = path + "/Assets/" + SharedData.getUsername() + "Image.png";

		File out = new File(path);
		
		// Try and save, show the user a message based on the result
		try {
			ImageIO.write(newAvatar, "png", out);
			JOptionPane.showMessageDialog(null, "Avatar has been saved", "AvatarDraw", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Unable to save avatar", "AvatarDraw", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

		/*
		User oldUser = PassInfo.getCurrentUser();
		User newUser = PassInfo.getCurrentUser();
		newUser.setAvatarFilePath(path);
		DatabaseManager.editRecord(oldUser, newUser, "user");
		PassInfo.setCurrentUser(newUser);
		*/
	}

	/*
	 * Called by pressing the leave button
	 */
	public void leaveDialog(ActionEvent e) {
		// Code here to go back to UserInfo page
	}

	/*
	 * Called when we bring the mouse up from the canvas
	 */
	public void mouseUp(MouseEvent e) {
		this.drawing = false;
		e.consume();
	}

	/*
	 * Called when we push the mouse onto the canvas
	 */
	public void mouseDown(MouseEvent e) {
		this.drawing = true;
		e.consume();
	}

	/**
	 * Called when the mouse is clicked on the canvase
	 * we can see if it was in certain areas here
	 * @param e
	 */
	public void canvasClick(MouseEvent e) {
		
		// Color pallet selection
		if (this.mx >= 96 && this.mx < 96 + (26 * this.pallet.length)) {
			if (this.my >= 270 && this.my <= 300) {
				int opi = palletIndex;
				palletIndex = (mx - 96) / 26;
				if(palletIndex < 0 || palletIndex >= this.pallet.length) {
					palletIndex = opi;
				}
			}
		}
		
		// Brus size selection
		if(this.mx >= 16 && this.mx <= 32) {
			if(this.my >= 16 && this.my <= 32) {
				this.brushSize = this.bigBrush;
			}else if(this.my >= 40 && this.my <= 56) {
				this.brushSize = this.smallBrush;
			}
		}

		paintOnImage();
		e.consume();
	}

	/**
	 * Called when the mouse moves over the canvas
	 * @param e
	 */
	public void mouseMove(MouseEvent e) {
		mx = (int) e.getX();
		my = (int) e.getY();

		if (this.drawing) {
			paintOnImage();
		}

		e.consume();
	}

	/**
	 * Called when we are painting on the image
	 * this sets the relavent pixels to the right
	 * color
	 */
	private void paintOnImage() {
		int ix = mx - (400 - this.imageWidth) / 2;
		int iy = my - (300 - this.imageHeight) / 2;

		boolean valid = !(ix < 0 || iy < 0 || ix >= this.imageWidth || iy >= this.imageWidth);

		// Check if we are inside the "image"
		if (valid) {
			circle(ix, iy, brushSize, this.pallet[this.palletIndex]);
		}

		bufferToFX();
	}

	/**
	 * Helper function to draw a circle on the avatar image
	 * @param x - The X center of the circle
	 * @param y - The Y center of the circle
	 * @param r - The radius of the cirlce
	 * @param color - The color of the circle
	 */
	private void circle(int x, int y, int r, int color) {
		for (int xx = -r; xx < r; xx++) {
			for (int yy = -r; yy < r; yy++) {
				if (xx * xx + yy * yy < r * r) {
					setPixel(x + xx, y + yy, color);
				}
			}
		}
	}

	private void bufferToFX() {
		
		// Clear the area before we draw it again
		this.ctx.clearRect(0, 0, 400d, 400d);
		
		// Show updated avatar image
		newAvatar.setRGB(0, 0, imageWidth, imageHeight, pixels, 0, imageWidth);
		int x = (400 - this.imageWidth) / 2;
		int y = (300 - this.imageHeight) / 2;
		this.ctx.drawImage(SwingFXUtils.toFXImage(newAvatar, null), x, y);
		
		
		// Draw the pallet selection
		int xo = 96;
		int yo = 270;
		this.ctx.setFill(Color.rgb(123, 123, 123));
		this.ctx.fillRect(xo-8, yo-8, 26*this.pallet.length + 8, 36);
		
		for (int i = 0; i < this.pallet.length; i++) {
			int s = 16;
			int spacing = 10;

			int p = this.pallet[i];

			int rr = (p >> 16) & 0xFF;
			int gg = (p >> 8) & 0xFF;
			int bb = (p) & 0xFF;
			this.ctx.setFill(Color.rgb(rr, gg, bb));
			
			this.ctx.fillRect(xo + (s * i) + (spacing * i), yo, s, s);
			
			if(this.palletIndex == i) {
				this.ctx.fillRect(xo + (s * i) + (spacing * i), yo + s + 4, s, 2);
			}
		}
		// Draw the brush size section
		this.ctx.setFill(Color.GRAY);
		this.ctx.fillRect(16 - 8, 16 - 8, 56 * 2, 56);
		this.ctx.setFill(Color.WHITE);
		
		this.ctx.fillOval(16, 16, 16, 16);
		this.ctx.setFill(Color.BLACK);
		if(this.brushSize == this.bigBrush) {
			this.ctx.fillOval(16 + 4, 16 + 4, 8, 8);
		}
		this.ctx.fillText("Big brush", 35, 28);
		
		this.ctx.setFill(Color.WHITE);
		this.ctx.fillOval(16, 40, 16, 16);
		this.ctx.setFill(Color.BLACK);
		if(this.brushSize == this.smallBrush) {
			this.ctx.fillOval(16 + 4, 40 + 4, 8, 8);
		}
		this.ctx.fillText("Small brush", 35, 52);
		
	}

	/**
	 * Helper function to quickly set pixels
	 * @param x - x coordinate on the image
	 * @param y - y coordinate on the image
	 * @param color - the color we want the pixel to be
	 */
	private void setPixel(int x, int y, int color) {
		
		// We don't want to set the pixels outside of the array
		// this would result in an array out of bounds exception
		if (x < 0 || y < 0 || x >= this.imageWidth || y >= this.imageHeight)
			return;
		this.pixels[x + y * this.imageWidth] = color;
	}

	/*
	 * Helper function to make colors easier
	 */
	private int makeRGB(int r, int g, int b) {
		return (r << 16) | (g << 8) | b;
	}

	/*
	 * Called when the scene loads
	 */
	public void initialize() {
		this.ctx = canvas.getGraphicsContext2D();
		this.newAvatar = new BufferedImage(this.imageWidth, this.imageHeight, BufferedImage.TYPE_INT_RGB);
		this.pixels = new int[this.imageWidth * this.imageHeight];

		// Create a pallet; Red, Green, Blue, Yellow, Pink, Cyan, Black and White 
		this.pallet = new int[] { makeRGB(255, 0, 0), makeRGB(0, 255, 0), makeRGB(0, 0, 255), makeRGB(255, 255, 0),
				makeRGB(255, 0, 255), makeRGB(0, 255, 255), makeRGB(0, 0, 0), makeRGB(255, 255, 255) };

		this.brushSize = bigBrush;
		
		// Make sure something is on the canvas so the user isn't confused
		bufferToFX();
	}
}
