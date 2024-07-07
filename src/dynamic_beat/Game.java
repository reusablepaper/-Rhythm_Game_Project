package dynamic_beat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	private Image gameinfoImage = new ImageIcon(MainDB.class.getResource("../images/gameinfo.png")).getImage();
	private Image judgementlineImage = new ImageIcon(MainDB.class.getResource("../images/judgementline.png")).getImage();

	private Image noteroutelineImage = new ImageIcon(MainDB.class.getResource("../images/noterouteline.png")).getImage();
	//private Image noteroutelineImage = new ImageIcon(MainDB.class.getResource("../images/noteroutprsdline.png")).getImage();
	private Image noterouteSImage = new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	private Image noterouteDImage = new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	private Image noterouteFImage = new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	private Image noterouteSB1Image = new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	private Image noterouteSB2Image = new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	private Image noterouteJImage = new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	private Image noterouteKImage = new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	private Image noterouteLImage = new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	private Image blueFlareImage = new ImageIcon(MainDB.class.getResource("../images/flare.png")).getImage();
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	public Game(String titleName,String difficulty,String musicTitle) {
		this.titleName=titleName;
		this.difficulty=difficulty;
		this.musicTitle=musicTitle;
		gameMusic=new Music(this.musicTitle,false);
	
		
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noterouteSImage, 228, 30, null);
		g.drawImage(noterouteDImage, 332, 30, null);
		g.drawImage(noterouteFImage, 436, 30, null);
		g.drawImage(noterouteSB1Image, 540, 30, null);
		g.drawImage(noterouteSB2Image, 640, 30, null);
		g.drawImage(noterouteJImage, 744, 30, null);
		g.drawImage(noterouteKImage, 848, 30, null);
		g.drawImage(noterouteLImage, 952, 30, null);
		
		g.drawImage(noteroutelineImage, 224, 30, null);
		g.drawImage(noteroutelineImage, 328, 30, null);
		g.drawImage(noteroutelineImage, 432, 30, null);
		g.drawImage(noteroutelineImage, 536, 30, null);
		g.drawImage(noteroutelineImage, 740, 30, null);
		g.drawImage(noteroutelineImage, 844, 30, null);
		g.drawImage(noteroutelineImage, 948, 30, null);
		g.drawImage(noteroutelineImage, 1052, 30, null);
		g.drawImage(gameinfoImage, 0, 660, null);
		g.drawImage(judgementlineImage, 0, 580, null);
		for(int i=0;i<noteList.size();i++) 
		{
			Note note=noteList.get(i);
			if(!note.isPorceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//글자 벡터 이미지
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1192, 702);
		g.setFont(new Font("Elephant",Font.BOLD,30));
		g.drawString("000000", 565, 702);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270-4, 612);
		g.drawString("D", 374-4, 612);
		g.drawString("F", 478-4, 612);
		g.drawString("Space Bar", 580-20, 612);
		g.drawString("J", 784-4, 612);
		g.drawString("K", 889-4, 612);
		g.drawString("L", 993-4, 612);
		//g.drawImage(blueFlareImage,320,370,null);
		}
	
	public void pressS() {
		judge("S");
		noterouteSImage= new ImageIcon(MainDB.class.getResource("../images/noterouteprsd.png")).getImage();
		new Music("drum1.mp3",false).start();
	}
	public void releaseS() {
		noterouteSImage= new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
		//new Music("drum1.mp3",false).start();
	}
	public void pressD() {
		judge("D");
		noterouteDImage= new ImageIcon(MainDB.class.getResource("../images/noterouteprsd.png")).getImage();
		new Music("drum1.mp3",false).start();
	}
	public void releaseD() {
		noterouteDImage= new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
		//new Music("drum1.mp3",false).start();
	}
	public void pressF() {
		judge("F");
		noterouteFImage= new ImageIcon(MainDB.class.getResource("../images/noterouteprsd.png")).getImage();
		new Music("drum1.mp3",false).start();
	}
	public void releaseF() {
		noterouteFImage= new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	}
	public void pressSB() {
		judge("Space");
		noterouteSB1Image= new ImageIcon(MainDB.class.getResource("../images/noterouteprsd.png")).getImage();
		noterouteSB2Image= new ImageIcon(MainDB.class.getResource("../images/noterouteprsd.png")).getImage();
		new Music("drum1.mp3",false).start();
	}
	public void releaseSB() {
		
		noterouteSB1Image= new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
		noterouteSB2Image= new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	}
	public void pressJ() {
		judge("J");
		noterouteJImage= new ImageIcon(MainDB.class.getResource("../images/noterouteprsd.png")).getImage();
		new Music("drum1.mp3",false).start();
	}
	public void releaseJ() {
		noterouteJImage= new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	}
	public void pressK() {
		judge("K");
		noterouteKImage= new ImageIcon(MainDB.class.getResource("../images/noterouteprsd.png")).getImage();
		new Music("drum1.mp3",false).start();
	}
	public void releaseK() {
		noterouteKImage= new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	}
	public void pressL() {
		judge("L");
		noterouteLImage= new ImageIcon(MainDB.class.getResource("../images/noterouteprsd.png")).getImage();
		new Music("drum1.mp3",false).start();
	}
	public void releaseL() {
		noterouteLImage= new ImageIcon(MainDB.class.getResource("../images/noteroute.png")).getImage();
	}
	@Override
	public void run() {
		dropNotes();
	}
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	public void dropNotes() {
		Beat[] beats = null;
		if (titleName.equals("test")&&difficulty.equals("Easy")){
			int startTime=1000-MainDB.REACH_TIME*1000;
			beats=new Beat[] {
					new Beat(0,"S"),
					new Beat(300,"D"),
					new Beat(600,"F"),
					new Beat(900,"Space"),
			};
		}
		if (titleName.equals("test")&&difficulty.equals("Hard")){
			int startTime=1000-MainDB.REACH_TIME*1000;
			beats=new Beat[] {
					new Beat(0,"S"),
					new Beat(300,"D"),
					new Beat(600,"F"),
					new Beat(900,"Space"),
					new Beat(0,"S"),
					new Beat(300,"J"),
					new Beat(600,"K"),
					new Beat(900,"L"),
			};
		}
		if (titleName.equals("weak")&&difficulty.equals("Easy")){
			int startTime=1000;
			beats=new Beat[] {
					new Beat(1000,"Space"),
					new Beat(2000,"K"),
					new Beat(3000,"L"),
			};
		}
		if (titleName.equals("weak")&&difficulty.equals("Hard")){
			int startTime=1000;
			beats=new Beat[] {
					new Beat(1000,"Space"),
					new Beat(2000,"K"),
					new Beat(3000,"L"),
					new Beat(1000,"S"),
					new Beat(5000,"D"),
					new Beat(4000,"F"),
			};
		}
		int i=0;
		gameMusic.start();
		while(i<beats.length&&!isInterrupted()) {
			boolean dropped=false;
			if(beats[i].getTime()<=gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped=true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	public void judge(String input) {
		for(int i=0;i<noteList.size();i++) {
			Note note=noteList.get(i);
			if(input.equals(note.getNoteType())) {
				note.judge();
				break;
			}
		}
	}
}
