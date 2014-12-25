package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CardView extends JFrame{
	public CardView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 258, 236);
		getContentPane().setLayout(null);
		
		JButton btnTrade = new JButton("Close");
		btnTrade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO:Trade in card
			}
		});
		btnTrade.setBounds(175, 160, 90, 25);
		getContentPane().add(btnTrade);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(80, 160, 90, 25);
		getContentPane().add(btnCancel);

	}
}
