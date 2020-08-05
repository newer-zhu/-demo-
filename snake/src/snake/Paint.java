package snake;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
public class Paint extends JPanel implements KeyListener, ActionListener{
	ImageIcon title;
	ImageIcon up;
	ImageIcon left;
	ImageIcon right;    //声明图片
	ImageIcon down ;
	ImageIcon body; 
	
	int len =3;	 //初始长度
	int score = 0;//初始分数
	String header = "R";     //头部朝向
	boolean isStarted = false;  //判断状态
	boolean isFailed = false;
	javax.swing.Timer timer = new javax.swing.Timer(150,this);  //定时鈡
	int[] locX = new int[820];		//定义坐标数组容器
	int[] locY = new int[820];
	int shitX;          //食物坐标
	int shitY;
	Random rand = new Random();   //随机数
	Clip bgm;  //声明背景音乐
	
	public Paint() {
		loadImages();  //载入图片
		initSnake();//初始化
		this.setFocusable(true);
		this.addKeyListener(this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);      //设置画笔
		this.setBackground(Color.green);   //设置背景颜色
		title.paintIcon(this, g, 25, 11);     //画出title
		
		g.fillRect(25, 75, 850, 600);    //游戏区
		g.setColor(Color.red);
		g.setFont(new Font("",Font.BOLD,15));
		g.drawString("长度 "+ len, 550, 30);   //打印长度分数
		g.drawString("战斗值 " + score, 550, 55);
		switch(header){				//打印头部
		case "L":
			left.paintIcon(this, g, locX[0], locY[0]);
			break;
		case "R":
			right.paintIcon(this, g, locX[0], locY[0]);
			break;
		case "U":
			up.paintIcon(this, g, locX[0], locY[0]);
			break;
		case "D":
			down.paintIcon(this, g, locX[0], locY[0]);
			break;
		}
		for(int i=1; i<len; i++) {			//打印身体
			body.paintIcon(this, g, locX[i], locY[i]);
		}
		body.paintIcon(this, g, shitX, shitY);  //打印食物
		if(! isStarted) {
			g.setColor(Color.white);		//字体设置
			g.setFont(new Font("",Font.BOLD,30));
			g.drawString("按下enter操纵老八,奥利给！", 300, 330);
		}
		if(isFailed) {
			g.setColor(Color.red);		//字体设置
			g.setFont(new Font("arial",Font.BOLD,35));
			g.drawString("RIP", 400, 330);
		}
	}
	
	public void initSnake() {					//初始化
		len = 3;
		locX[0] = 100;
		locY[0] = 100;
		locX[1] = 75;
		locY[1] = 100;
		locX[2] = 50;
		locY[2] = 100;
		shitX = 25 + 25*rand.nextInt(34);
		shitY = 75 + 25*rand.nextInt(24);
		header = "R";
		score =0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ENTER) {
			if(isFailed) {
				isFailed = false;    //重新开始游戏
				initSnake();
			}else {
			isStarted = !isStarted; //暂停功能
			}
			repaint();			//enter按下重画1
		}else if(keyCode == KeyEvent.VK_LEFT) {
			header = "L";
		}else if(keyCode == KeyEvent.VK_RIGHT) {
			header = "R";
		}
		else if(keyCode == KeyEvent.VK_UP) {
			header = "U";
		}else if(keyCode == KeyEvent.VK_DOWN) {
			header = "D";
		}
;		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isStarted && !isFailed) {
			for(int i=len-1; i>0; i--) {		//控制身体的移动
				locX[i] =locX[i-1];
				locY[i] = locY[i-1];
				
			}
			if(header == "R") {
				locX[0] += 25;		//控制头部移动
				if(locX[0] > 850)
					locX[0] = 25;
			}else if(header == "L") {
				locX[0] -= 25;
				if(locX[0] < 25)
					locX[0] = 850;
			}else if(header == "U") {
				locY[0] -= 25;
				if(locY[0] < 75 )
					locY[0] = 650;
			}else if(header == "D") {
				locY[0] += 25;
				if(locY[0] > 650)
					locY[0] = 75;
			}
			
			if(locX[0] == shitX && locY[0] == shitY) {
				loadBGM();  //载入音乐
				playBGM();
				len++;  //长度+1即可，逻辑为后面跟着前一个，不需要重新设定坐标
				score += 5;
				locX[len-1] = locX[len-2];
				locY[len-1] = locY[len-2];
				shitX = 25 + 25*rand.nextInt(34);
				shitY = 75 + 25*rand.nextInt(24);
			}
			
			for(int i=1; i<len; i++) {
				if(locX[i] == locX[0] && locY[i] == locY[0])
					isFailed = true;
			}
			repaint();
		}
		timer.start();
		
	}

	private void loadBGM() {		//背景音乐
			try {
				bgm = AudioSystem.getClip();
				InputStream s = this.getClass().getClassLoader().getResourceAsStream("music/olegy.wav");
				AudioInputStream ss = AudioSystem.getAudioInputStream(s);
				bgm.open(ss);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private void playBGM() {
		bgm.start();
	}
	
	private void loadImages() {  //导入图片
		InputStream s;
		try {
		s = getClass().getClassLoader().getResourceAsStream("pic/title8.png");
		title = new ImageIcon(ImageIO.read(s));
		s = getClass().getClassLoader().getResourceAsStream("pic/up.png");
		up = new ImageIcon(ImageIO.read(s));
		s = getClass().getClassLoader().getResourceAsStream("pic/left.png");
		left = new ImageIcon(ImageIO.read(s));
		s = getClass().getClassLoader().getResourceAsStream("pic/right.png");
		right = new ImageIcon(ImageIO.read(s));
		s = getClass().getClassLoader().getResourceAsStream("pic/down.png");
		down = new ImageIcon(ImageIO.read(s));
		s = getClass().getClassLoader().getResourceAsStream("pic/body.png");
		body = new ImageIcon(ImageIO.read(s));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
