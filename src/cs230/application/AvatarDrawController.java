package cs230.application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import cs230.system.SharedData;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
	private int pallextIndex;

	public void saveAvatar(ActionEvent e) {
		String path = new File("").getAbsolutePath();
		path = path + "/Assets/" + SharedData.getUsername() + "Image.png";

		File out = new File(path);
		try {
			ImageIO.write(newAvatar, "png", out);
			JOptionPane.showMessageDialog(null, "Avatar has been saved", "AvatarDraw", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Unable to save avatar", "AvatarDraw", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}

	public void leaveDialog(ActionEvent e) {
		// Code here to go back to UserInfo page
	}

	public void mouseUp(MouseEvent e) {
		this.drawing = false;
		e.consume();
	}

	public void mouseDown(MouseEvent e) {
		this.drawing = true;
		e.consume();
	}

	public void canvasClick(MouseEvent e) {
		paintOnImage();
		e.consume();
	}

	public void mouseMove(MouseEvent e) {
		mx = (int) e.getX();
		my = (int) e.getY();
		e.consume();

		if (this.drawing) {
			paintOnImage();
		}
	}

	private void paintOnImage() {
		int ix = mx - (400 - this.imageWidth) / 2;
		int iy = my - (300 - this.imageHeight) / 2;

		boolean valid = !(ix < 0 || iy < 0 || ix >= this.imageWidth || iy >= this.imageWidth);

		// Check if we are inside the "image"
		if (valid) {
			int scale = 10;
			for (int xx = -scale; xx < scale; xx++) {
				for (int yy = -scale; yy < scale; yy++) {
					// setPixel(ix + xx, iy + yy, makeRGB(255,0,255));
				}
			}

			circle(ix, iy, 10, makeRGB(255,0,0));
		}

		bufferToFX();
	}

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
		this.ctx.clearRect(0, 0, 400d, 400d);
		newAvatar.setRGB(0, 0, imageWidth, imageHeight, pixels, 0, imageWidth);

		int x = (400 - this.imageWidth) / 2;
		int y = (300 - this.imageHeight) / 2;

		this.ctx.drawImage(SwingFXUtils.toFXImage(newAvatar, null), x, y);
		
		for(int i = 0; i < this.pallet.length; i++) {
			int s = 16;
			int spacing = 10;
			
			int p = this.pallet[i];
			
			int rr = (p >> 16) & 0xFF;
			int gg = (p >> 8) & 0xFF;
			int bb = (p) & 0xFF;
			this.ctx.setFill(Color.rgb(rr,gg,bb));
			this.ctx.fillRect(16, 16 + i * s + (spacing * i), s, s);
		}
	}

	private void setPixel(int x, int y, int color) {
		if (x < 0 || y < 0 || x >= this.imageWidth || y >= this.imageHeight)
			return;
		this.pixels[x + y * this.imageWidth] = color;
	}

	private int makeRGB(int r, int g, int b) {
		return (r << 16) | (g << 8) | b;
	}

	public void initialize() {
		this.ctx = canvas.getGraphicsContext2D();
		this.newAvatar = new BufferedImage(this.imageWidth, this.imageHeight, BufferedImage.TYPE_INT_RGB);
		this.pixels = new int[this.imageWidth * this.imageHeight];

		this.pallet = new int[] { makeRGB(255, 0, 0), makeRGB(0, 255, 0), makeRGB(0, 0, 255) };

		bufferToFX();
	}
}