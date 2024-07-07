package dynamic_beat;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DynamicBeat extends JFrame {
	
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButtonEntered = new ImageIcon(MainDB.class.getResource("../images/exit_prsd.png"));
	private ImageIcon exitButtonNormal = new ImageIcon(MainDB.class.getResource("../images/exit_nm.png"));
	private ImageIcon startButtonNormal = new ImageIcon(MainDB.class.getResource("../images/gamestart_nm.png"));
	private ImageIcon startButtonEntered = new ImageIcon(MainDB.class.getResource("../images/gamestart_prsd.png"));
	private ImageIcon backButtonNormal = new ImageIcon(MainDB.class.getResource("../images/back_nm.png"));
	private ImageIcon backButtonEntered = new ImageIcon(MainDB.class.getResource("../images/back_prsd.png"));
	private ImageIcon leftButtonEntered = new ImageIcon(MainDB.class.getResource("../images/Assam_back.png"));
	private ImageIcon leftButtonNormal = new ImageIcon(MainDB.class.getResource("../images/Assam_left.png"));
	private ImageIcon rightButtonEntered = new ImageIcon(MainDB.class.getResource("../images/Assam_front.png"));
	private ImageIcon rightButtonNormal = new ImageIcon(MainDB.class.getResource("../images/Assam_right.png"));
	private ImageIcon easyButtonEntered = new ImageIcon(MainDB.class.getResource("../images/easyprsd.png"));
	private ImageIcon easyButtonNormal = new ImageIcon(MainDB.class.getResource("../images/easy.png"));
	private ImageIcon hardButtonEntered = new ImageIcon(MainDB.class.getResource("../images/hardprsd.png"));
	private ImageIcon hardButtonNormal = new ImageIcon(MainDB.class.getResource("../images/hard.png"));

	private Image introBackground = new ImageIcon(MainDB.class.getResource("../images/introbackground.png")).getImage();
	
	private JButton exitButton = new JButton(exitButtonNormal);
	private JButton startButton = new JButton(startButtonNormal);
	private JButton backButton = new JButton(backButtonNormal);
	private JButton leftButton = new JButton(leftButtonNormal);
	private JButton rightButton = new JButton(rightButtonNormal);
	private JButton easyButton = new JButton(easyButtonNormal);
	private JButton hardButton = new JButton(hardButtonNormal);

	private boolean isMainDBScreen = false;
	private boolean isGameScreen = false;
	ArrayList<Track> trackList = new ArrayList<Track>();

	private Music introMusic = new Music("introMusic.mp3", true);
	private Music selectedMusic;
	private Image titleImage;
	private Image selectedImage;
	private int nowSelected = 0;
	
	public static Game game;
	

	public DynamicBeat() {
		trackList.add(new Track("block_nm.png", "block_nm.png", "block_prsd.png", "test.mp3", "test.mp3","test"));
		trackList.add(new Track("block_prsd.png", "block_prsd.png", "block_prsd.png", "weak.mp3", "weak.mp3","weak"));
		setUndecorated(true);// �⺻ �޴��� �Ⱥ���
		setTitle("Dynamic Beat");
		setSize(MainDB.SCREEN_WIDTH, MainDB.SCREEN_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));// �̰� ������ introbackground �̹��� �Ⱥ��̰� ȸ�� ȭ�鸸 ����
		setLayout(null);
		addKeyListener(new KeyListener());
		introMusic.start();
		
		exitButton.setBounds(0, 0, 170, 50);
		exitButton.setContentAreaFilled(false);// ��ư�� ������ ä���
		exitButton.setBorderPainted(false);// ��ư�� �ܰ���
		exitButton.setFocusPainted(false);// ��ư�� ���õǾ��� �� �׵θ�
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonNormal);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(555, 360, 170, 50);
		startButton.setContentAreaFilled(false);// ��ư�� ������ ä���
		startButton.setBorderPainted(false);// ��ư�� �ܰ���
		startButton.setFocusPainted(false);// ��ư�� ���õǾ��� �� �׵θ�
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonNormal);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ���ӽ��� �̺�Ʈ
				// Music introMusic = new Music("introMusic.mp3", true);
				introMusic.close();
				enterMainDB();
			}
		});
		add(startButton);

		leftButton.setBounds(140, 310, 62, 62);
		leftButton.setContentAreaFilled(false);// ��ư�� ������ ä���
		leftButton.setBorderPainted(false);// ��ư�� �ܰ���
		leftButton.setFocusPainted(false);// ��ư�� ���õǾ��� �� �׵θ�
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonNormal);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ���� ��ư �̺�Ʈ
				selecLeft();
			}
		});
		add(leftButton);
		leftButton.setVisible(false);

		rightButton.setBounds(1078, 310, 62, 62);
		rightButton.setContentAreaFilled(false);// ��ư�� ������ ä���
		rightButton.setBorderPainted(false);// ��ư�� �ܰ���
		rightButton.setFocusPainted(false);// ��ư�� ���õǾ��� �� �׵θ�
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonNormal);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// ������ ��ư �̺�Ʈ
				selecRight();
			}
		});
		add(rightButton);
		rightButton.setVisible(false);

		easyButton.setBounds(375, 580, 170, 50);
		easyButton.setContentAreaFilled(false);// ��ư�� ������ ä���
		easyButton.setBorderPainted(false);// ��ư�� �ܰ���
		easyButton.setFocusPainted(false);// ��ư�� ���õǾ��� �� �׵θ�
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonNormal);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// easy ��ư �̺�Ʈ
				gameStart(nowSelected, "Easy");
			}
		});
		add(easyButton);
		easyButton.setVisible(false);

		hardButton.setBounds(675, 580, 170, 50);
		hardButton.setContentAreaFilled(false);// ��ư�� ������ ä���
		hardButton.setBorderPainted(false);// ��ư�� �ܰ���
		hardButton.setFocusPainted(false);// ��ư�� ���õǾ��� �� �׵θ�
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonNormal);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// hard ��ư �̺�Ʈ
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);
		hardButton.setVisible(false);

		backButton.setVisible(false);
		backButton.setBounds(0, 50, 170, 50);
		backButton.setContentAreaFilled(false);// ��ư�� ������ ä���
		backButton.setBorderPainted(false);// ��ư�� �ܰ���
		backButton.setFocusPainted(false);// ��ư�� ���õǾ��� �� �׵θ�
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEntered);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonNormal);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// back��ư �̺�Ʈ = ����ȭ������ ���ư��� �̺�Ʈ
				backMainDB();
			}
		});
		add(backButton);
		backButton.setVisible(false);

	}

	public void paint(Graphics g) {
		screenImage = createImage(MainDB.SCREEN_WIDTH, MainDB.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);//Graphics2D=���ڸ� ���� �̹�����
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {

		g.drawImage(introBackground, 0, 0, null);

		if (isMainDBScreen) {
			g.drawImage(selectedImage, 609, 300, null);
			g.drawImage(titleImage, 390, 100, null);
		}
		if (isGameScreen) {
			
			game.screenDraw(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(MainDB.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();
		selectedImage = new ImageIcon(MainDB.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}

	public void selecLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);

	}

	public void selecRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);

	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)
			selectedMusic.close();
		isMainDBScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		introBackground = new ImageIcon(MainDB.class.getResource("../images/introbackground.png")).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		
		game=new Game(trackList.get(nowSelected).getTitleName(),difficulty,trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);
	}

	public void backMainDB() {
		isMainDBScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		hardButton.setVisible(true);
		easyButton.setVisible(true);
		introBackground = new ImageIcon(MainDB.class.getResource("../images/africa.jpg")).getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();

	}

	public void enterMainDB() {
		introBackground = new ImageIcon(MainDB.class.getResource("../images/africa.jpg")).getImage();
		startButton.setVisible(false);
		// backButton.setVisible(true);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		isMainDBScreen = true;
		introMusic.close();
		selectTrack(0);
	}
}
