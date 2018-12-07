package cs230.application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import cs230.system.DatabaseManager;
import cs230.system.PassInfo;
import cs230.system.SharedData;
import cs230.system.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * All of the interactions between the user and the AvatarDraw window happen here 
 * @author 964552
 *
 */
public class AvatarDrawController {
	
	private final int bigBrush = 10;
	private final int smallBrush = 3;
	private final int bigLine = 10;
	private final int smallLine = 3;

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
	private int currentLineSize;
	private boolean usingBrush;
	private int lx, ly;
	
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
		this.lx = -1;
		this.ly = -1;

		// Make sure something is on the canvas so the user isn't confused
		bufferToFX();
	}
	

	/**
	 * Called when the user clicks the save button
	 * 
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
			JOptionPane.showMessageDialog(null, "Avatar has been saved", 
					"AvatarDraw", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Unable to save avatar", 
					"AvatarDraw", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

		User oldUser = PassInfo.getCurrentUser();
		User newUser = PassInfo.getCurrentUser();
		newUser.setAvatarFilePath(path);
		DatabaseManager.editRecord(oldUser, newUser, "user");
		PassInfo.setCurrentUser(newUser);
	}

	/*
	 *
	 * Called by pressing the leave button
	 */
	public void leaveDialog(ActionEvent e) {
		// Code here to go back to UserInfo page
	}

	/*
	 * Called when we bring the mouse up from the canvas
	 */
	@FXML
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
	 * 
	 * @param e
	 */
	public void canvasClick(MouseEvent e) {

		// Color pallet selection
		if (this.mx >= 96 && this.mx < 96 + (26 * this.pallet.length)) {
			if (this.my >= 270 && this.my <= 300) {
				int opi = palletIndex;
				palletIndex = (mx - 96) / 26;
				if (palletIndex < 0 || palletIndex >= this.pallet.length) {
					palletIndex = opi;
				}
			}
		}

		// Brus size selection
		int xo = 16;
		int s = 16;
		int yo  = 16;
		if (this.mx >= xo && this.mx <= xo + s) {
			if (this.my >= yo && this.my <= yo + s) {
				this.brushSize = this.bigBrush;
				this.usingBrush = true;
			} else if (this.my >= yo + 24 && this.my <= yo + 24 + s) {
				this.brushSize = this.smallBrush;
				this.usingBrush = true;
			}
			
			yo += 72;
			if (this.my >= yo && this.my <= yo + s) {
				this.currentLineSize = this.bigLine;
				this.usingBrush = false;
			} else if (this.my >= yo + 24 && this.my <= yo + 24 + s) {
				this.currentLineSize = this.smallLine;
				this.usingBrush = false;
			}
			
		}

		if(this.usingBrush) {
			paintOnImage();
		}else { // Using the line tool
			drawLineOnImage();
		}
		bufferToFX();
		e.consume();
	}

	/**
	 * Called when the mouse moves over the canvas
	 * 
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
		if(!valid)return;

		// Check if we are inside the "image"
		if (valid && this.usingBrush) {
			circle(ix, iy, brushSize, this.pallet[this.palletIndex]);
		}

		bufferToFX();
	}
	
	private void drawLineOnImage() {
		int ix = mx - (400 - this.imageWidth) / 2;
		int iy = my - (300 - this.imageHeight) / 2;
		
		boolean valid = !(ix < 0 || iy < 0 || ix >= this.imageWidth || iy >= this.imageWidth);
		if(!valid)return;
		
		if(lx == -1 || ly == -1) {
			lx = ix;
			ly = iy;
		}else {// Set a second position and draw
			int x0 = lx;
			int y0 = ly;
			int x1 = ix;
			int y1 = iy;
			int w = x1 - x0;
			int h = y1 - y0;
			int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
			if (w < 0)
				dx1 = -1;
			else if (w > 0)
				dx1 = 1;

			if (h < 0)
				dy1 = -1;
			else if (h > 0)
				dy1 = 1;

			if (w < 0)
				dx2 = -1;
			else if (w > 0)
				dx2 = 1;

			int longest = Math.abs(w);
			int shortest = Math.abs(h);
			if (!(longest > shortest)) {
				longest = Math.abs(h);
				shortest = Math.abs(w);
				if (h < 0)
					dy2 = -1;
				else if (h > 0)
					dy2 = 1;
				dx2 = 0;
			}
			int numerator = longest >> 1;
			for (int i = 0; i <= longest; i++) {
				circle(x0, y0, this.currentLineSize==this.bigLine?this.bigLine:this.smallLine, this.pallet[this.palletIndex]);
				numerator += shortest;
				if (!(numerator < longest)) {
					numerator -= longest;
					x0 += dx1;
					y0 += dy1;
				} else {
					x0 += dx2;
					y0 += dy2;
				}
			}
			
			lx = -1;
			ly = -1;
		}
		
		bufferToFX();
	}

	/**
	 * Helper function to draw a circle on the avatar image
	 * 
	 * @param x     - The X center of the circle
	 * @param y     - The Y center of the circle
	 * @param r     - The radius of the cirlce
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
		this.ctx.setFill(Color.LIGHTGRAY);
		this.ctx.fillRect(x - 8, y - 8, this.imageWidth + 8, this.imageHeight + 8);
		this.ctx.setFill(Color.GREY);
		this.ctx.fillRect(x, y, this.imageWidth + 8, this.imageHeight + 8);
		this.ctx.drawImage(SwingFXUtils.toFXImage(newAvatar, null), x, y);

		// Draw the pallet selection
		int xo = 96;
		int yo = 270;
		this.ctx.setFill(Color.rgb(123, 123, 123));
		this.ctx.fillRect(xo - 8, yo - 8, 26 * this.pallet.length + 8, 36);

		for (int i = 0; i < this.pallet.length; i++) {
			int s = 16;
			int spacing = 10;

			int p = this.pallet[i];

			int rr = (p >> 16) & 0xFF;
			int gg = (p >> 8) & 0xFF;
			int bb = (p) & 0xFF;
			this.ctx.setFill(Color.rgb(rr, gg, bb));

			this.ctx.fillRect(xo + (s * i) + (spacing * i), yo, s, s);

			if (this.palletIndex == i) {
				this.ctx.fillRect(xo + (s * i) + (spacing * i), yo + s + 4, s, 2);
			}
		}
		
		xo = 16;
		yo = 16;
		int s = 16;
		
		// Draw the brush size section
		this.ctx.setFill(Color.GRAY);
		this.ctx.fillRect(xo - 8, yo - 8, 56 * 2, 56);
		this.ctx.setFill(Color.WHITE);

		this.ctx.fillOval(xo, yo, s, s);
		this.ctx.setFill(Color.BLACK);
		if (this.brushSize == this.bigBrush && this.usingBrush) {
			this.ctx.fillOval(xo + (s/4), yo + (s/4), (s/2), (s/2));
		}
		this.ctx.fillText("Big brush", xo + s + 4, 28);

		this.ctx.setFill(Color.WHITE);
		this.ctx.fillOval(xo, yo + 20, s, s);
		this.ctx.setFill(Color.BLACK);
		if (this.brushSize == this.smallBrush && this.usingBrush) {
			this.ctx.fillOval(xo + (s/4), yo + 20 + (s/4), s/2, s/2);
		}
		this.ctx.fillText("Small brush", xo + s + 4,48);
		
		
		yo += 72;
		// Line selection tool
		this.ctx.setFill(Color.GRAY);
		this.ctx.fillRect(xo - 8, yo - 8, 56 * 2, 56);
		this.ctx.setFill(Color.WHITE);

		this.ctx.fillOval(xo, yo, s, s);
		this.ctx.setFill(Color.BLACK);
		if (this.currentLineSize == this.bigLine && !this.usingBrush) {
			this.ctx.fillOval(xo + s/4, yo + s/4, s/2, s/2);
		}
		this.ctx.fillText("Big line", xo + s + 4, yo + 12);

		this.ctx.setFill(Color.WHITE);
		this.ctx.fillOval(xo, yo + 20, s, s);
		this.ctx.setFill(Color.BLACK);
		if (this.currentLineSize == this.smallLine && !this.usingBrush) {
			this.ctx.fillOval(xo + s/4, yo + 20 + (s/4), s/2, s/2);
		}
		this.ctx.fillText("Small line", xo + s + 4, yo + 34);

	}

	/**
	 * Helper function to quickly set pixels
	 * 
	 * @param x     - x coordinate on the image
	 * @param y     - y coordinate on the image
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
}
