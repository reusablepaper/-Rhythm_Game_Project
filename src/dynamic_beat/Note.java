package dynamic_beat;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image notebasicImage = new ImageIcon(MainDB.class.getResource("../images/notebasic.png")).getImage();
	private int x, y = 580 - (1000 / MainDB.SLEEP_TIME * MainDB.NOTE_SPEED) * MainDB.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;

	public String getNoteType() {
		return noteType;
	}
	public boolean isPorceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	}

	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;

	}

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(notebasicImage, x, y, null);
		} else {
			g.drawImage(notebasicImage, x, y, null);
			g.drawImage(notebasicImage, x + 100, y, null);
		}
	}

	public void drop() {
		y += MainDB.NOTE_SPEED;
		if (y>620) {
			System.out.println("Miss");
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if (proceeded) {
					Thread.sleep(MainDB.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	public void judge() {
		if(y>=613) {
			System.out.println("Late");
			close();
		}
		else if(y>=600) {
			System.out.println("Good");
			close();
		}
		else if(y>=587) {
			System.out.println("Great");
			close();
		}
		else if(y>=565) {
			System.out.println("Perfect");
			close();
		}
		else if(y>=550) {
			System.out.println("Great");
			close();
		}
		else if(y>=535) {
			System.out.println("Early");
			close();
		}
	}
}
