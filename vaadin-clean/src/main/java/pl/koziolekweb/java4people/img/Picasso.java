package pl.koziolekweb.java4people.img;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.vaadin.terminal.StreamResource.StreamSource;

@SuppressWarnings("serial")
public class Picasso implements StreamSource {

	private String imgDef;

	public Picasso(Character imgDef) {
		this.imgDef = imgDef.toString();
	}

	public InputStream getStream() {
		BufferedImage img = new BufferedImage(16, 16,
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = img.getGraphics();
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 16, 16);

		graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		graphics.setColor(Color.RED);
		graphics.drawString(imgDef, 4, 12);

		ByteArrayOutputStream imgstr = new ByteArrayOutputStream();
		try {
			ImageIO.write(img, "png", imgstr);
			return new ByteArrayInputStream(imgstr.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
