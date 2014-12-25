package view;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DiceView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, JLabel> map = new HashMap<Integer, JLabel>();

	/**
	 * Create the frame.
	 */
	public DiceView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 258, 236);
		getContentPane().setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(78, 164, 89, 23);
		getContentPane().add(btnClose);

	}
	
	public void createDie(Integer number, int x, int y, int width, int height) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, width, height);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("");
		panel.add(label);
		if(number < 3) {
			panel.setBackground(Color.red);
		}
		else {
			panel.setBackground(Color.white);
		}
		
		map.put(number, label);
	}
	
	public void setImage(Integer number, String path) {
		if(number < 3) {
			createDie(number, 10 + number*80, 10, 70, 70);
		}
		else {
			createDie(number, 10 + (number - 3)*80, 90, 70, 70);
		}
		
		ImageIcon image = new ImageIcon(
	                getClass().getResource(
	                    "images/" + path));
	    if (image != null) {
	    	map.get(number).setIcon(image);
	    }		
	}
}
