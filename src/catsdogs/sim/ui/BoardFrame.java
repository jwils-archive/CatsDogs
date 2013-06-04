package catsdogs.sim.ui;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import catsdogs.sim.Board;
import catsdogs.sim.BoardPanel;
import catsdogs.sim.GameEngine;





public class BoardFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected JLabel playerLabel;
	protected BoardPanel bp;
	public BoardFrame(GameEngine engine){
		this.setPreferredSize(new Dimension(640, 480));

		this.bp = new BoardPanel(engine);
		JScrollPane boardScroller = new JScrollPane();
		boardScroller.getViewport().add(bp);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(boardScroller, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setTitle("Explorers");
		
		//TODO: SETUP UP LABELS ON BOARD PANEL FOR PLAYER COLORS
		//Set up labels...
		playerLabel = new JLabel("Player Name");
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(playerLabel);
		this.getContentPane().add(panel, BorderLayout.NORTH);
		
		this.pack();
		this.setVisible(false);
		
	}

}