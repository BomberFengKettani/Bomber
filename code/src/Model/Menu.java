package Model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.Graphics;

public class Menu{

	private BufferedImage troll;
	Graphics g;
	
	public void Menu(){
		
		try{
			troll = ImageIO.read(getClass().getResourceAsStream("/Images/test.gif"));
		} catch(IOException e){
			e.printStackTrace();
		}
		
		g.drawImage(troll, 50, 50, 50, 50, null);
	}
	
	
	
	
	
}
