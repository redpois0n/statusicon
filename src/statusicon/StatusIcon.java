package statusicon;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class StatusIcon {

	private int width;
	private int height;
	private TrayIcon icon;
	private int max = 100;
	private int value;
	private List<Image> progressIcons = new ArrayList<Image>();
	
	public StatusIcon(int width, int height, TrayIcon icon, List<Image> progressIcons) {
		this.width = width;
		this.height = height;
		this.icon = icon;
		this.progressIcons = progressIcons;
	}
	
	public StatusIcon(int width, int height, TrayIcon icon) {
		this(width, height, icon, new ArrayList<Image>());
	}
	
	public StatusIcon(TrayIcon icon) {
		this(16, 16, icon, new ArrayList<Image>());
	}
	
	public StatusIcon() {
		this(16, 16, null);
	}
	
	public ImageIcon getIcon() {
		return new ImageIcon(update());
	}
	
	public BufferedImage update() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();

		Image current = progressIcons.get((int) (((float) value / (float) max) * progressIcons.size()));
		g.drawImage(current, 0, 0, width, height, null);
		
		if (icon != null) {
			icon.setImage(image);
		}
		
		return image;
	}
	
	public List<Image> getIcons() {
		return this.progressIcons;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
		update();
	}
	
	public int getMaximum() {
		return this.max;
	}
	
	public void setMaximum(int max) {
		this.max = max;
	}
}
