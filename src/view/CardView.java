package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import controller.SoldierTypes;
import controller.TerritoryController;
import controller.TerritoryNames;

public class CardView extends JFrame{
	
	private HashMap<Integer, SoldierTypes> map = new HashMap<Integer, SoldierTypes>();
	private ArrayList<SoldierTypes> soldiers = new ArrayList<SoldierTypes>();
	
	
	public CardView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 300);
		getContentPane().setLayout(null);
		
		JButton btnTrade = new JButton("Trade");
		btnTrade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO:Trade in card
			}
		});
		btnTrade.setBounds(265, 220, 90, 25);
		getContentPane().add(btnTrade);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(170, 220, 90, 25);
		getContentPane().add(btnCancel);

	}
	
	public void createCard(final int number, final SoldierTypes soldier, TerritoryNames territory, String path, int x, int y, int width, int height) {
		final JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBounds(x, y, width, height);
		panel.setBackground(Color.white);
		getContentPane().add(panel);
		
		JLabel label = new JLabel(territory==null?"333":territory.getName());
		panel.add(label, BorderLayout.NORTH);
		
		JLabel labelImage = new JLabel(new ImageIcon(getClass().getResource("images/" + path)));
		panel.add(labelImage, BorderLayout.CENTER);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(mapContains(number)) {
					panel.setBackground(Color.white);
					map.remove(number);
				}
				else {
					map.put(number, soldier);
					panel.setBackground(Color.pink);
				}
			}
		});
	}
	
	public void setImage(int number, TerritoryNames territory, int soldier_strength) {
		String path = "";
		SoldierTypes soldier = SoldierTypes.footman;
		switch (soldier_strength) {
			case 1:
				path = "footman.png";
				soldier = SoldierTypes.footman;
				break;
			case 5:
				path = "horseman.png";	
				soldier = SoldierTypes.horseman;
				break;
			case 10:
				path = "cannon.png";
				soldier = SoldierTypes.cannon;
				break;
			default:
				path = "wild.png";
				soldier = SoldierTypes.all;
				break;
		}
		
		createCard(number, soldier, territory, path, 10 + (number)*115, 10, 110, 180);
	}
	
	private boolean mapContains(Integer key) {
		if(map.get(key) == null) {
			return false;
		}
		return true;
	}
}
