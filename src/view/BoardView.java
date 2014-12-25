package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import controller.TerritoryController;
import controller.TerritoryNames;
import controller.TurnPhrases;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;

import model.Territory;

import state.State;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BoardView {

	private JFrame frame;
	private HashMap<TerritoryNames, JPanel> map;
	private HashMap<TerritoryNames, JLabel> lblMap;
	private TerritoryNames currentTerritory = null;
	private TerritoryNames secondTerritory = null;
	private int armyToAttack = 0;
	
	/**
	 * Create the application.
	 */
	public BoardView() {
		
		map = new HashMap<TerritoryNames, JPanel>();
		lblMap = new HashMap<TerritoryNames, JLabel>();
		initialize();
		TerritoryController.newTurn();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1120, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		createPanel(55, 81, 61, 59, TerritoryNames.alaska);
		createPanel(126, 81, 92, 59, TerritoryNames.northwest_territory);
		createPanel(228, 33, 116, 107, TerritoryNames.greenland);
		createPanel(91, 151, 58, 59, TerritoryNames.alberta);
		createPanel(159, 151, 44, 59, TerritoryNames.ontario);
		createPanel(213, 151, 78, 59, TerritoryNames.quebec);
		createPanel(115, 221, 92, 65, TerritoryNames.western_united_states);
		createPanel(213, 221, 44, 65, TerritoryNames.eastern_united_states);
		createPanel(153, 297, 92, 59, TerritoryNames.central_america);
		createPanel(136, 365, 78, 59, TerritoryNames.venezuela);
		createPanel(106, 435, 68, 107, TerritoryNames.peru);
		createPanel(184, 434, 83, 59, TerritoryNames.brazil);
		createPanel(184, 505, 61, 107, TerritoryNames.argentina);
		createPanel(405, 79, 96, 52, TerritoryNames.iceland);
		createPanel(511, 101, 83, 70, TerritoryNames.scandinavia);
		createPanel(604, 101, 68, 130, TerritoryNames.ukraine);
		createPanel(443, 140, 58, 91, TerritoryNames.great_britain);
		createPanel(453, 242, 68, 70, TerritoryNames.western_europe);
		createPanel(511, 182, 83, 52, TerritoryNames.northern_europe);
		createPanel(531, 242, 83, 65, TerritoryNames.southern_europe);
		createPanel(405, 323, 116, 78, TerritoryNames.north_africa);
		createPanel(531, 324, 83, 52, TerritoryNames.egypt);
		createPanel(473, 412, 48, 59, TerritoryNames.congo);
		createPanel(531, 383, 101, 88, TerritoryNames.east_africa);
		createPanel(495, 483, 78, 59, TerritoryNames.south_africa);
		createPanel(642, 433, 61, 78, TerritoryNames.madagascar);
		createPanel(622, 242, 81, 130, TerritoryNames.middle_East);
		createPanel(674, 171, 78, 60, TerritoryNames.afghanistan);
		createPanel(713, 242, 78, 88, TerritoryNames.india);
		createPanel(674, 101, 101, 59, TerritoryNames.ural);
		createPanel(762, 171, 154, 59, TerritoryNames.china);
		createPanel(785, 53, 68, 107, TerritoryNames.siberia);
		createPanel(801, 242, 78, 52, TerritoryNames.siam);
		createPanel(857, 130, 83, 30, TerritoryNames.mongolia);
		createPanel(857, 81, 83, 38, TerritoryNames.irkutsk);
		createPanel(857, 33, 83, 45, TerritoryNames.yakutsk);
		createPanel(950, 33, 109, 98, TerritoryNames.kamchatka);
		createPanel(950, 140, 109, 30, TerritoryNames.japan);
		createPanel(857, 346, 78, 38, TerritoryNames.indonesia);
		createPanel(950, 365, 68, 70, TerritoryNames.new_guinea);
		createPanel(867, 395, 73, 98, TerritoryNames.western_australia);
		createPanel(950, 441, 68, 70, TerritoryNames.eastern_australia);
		
		final JButton button = new JButton(State.draft.getValue());
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(currentTerritory != null) {
					State state = TerritoryController.getCurrentState();
					if(state == State.draft) {
						TerritoryController.addSoldier(currentTerritory);
					} 
					else if(state == State.attack) {
						if(secondTerritory != null)
						{
							TerritoryController.attack(currentTerritory, secondTerritory, armyToAttack);
							secondTerritory = null;
						}
					}
					else if(state == State.fortify) {
						if(secondTerritory != null)
						{
							TerritoryController.fortify(currentTerritory, secondTerritory, 1);
						}
					}
					
					updatePanel();
					map.get(currentTerritory).setBackground(Color.red);
					
					updateNeighbours(currentTerritory);
					if(secondTerritory != null)
					{
						map.get(secondTerritory).setBackground(Color.blue);
					}
				}
			}
		});
		button.setBounds(322, 644, 118, 42);
		frame.getContentPane().add(button);
		
		final JButton btnNext = new JButton("Complete");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(currentTerritory != null) {
					State state = TerritoryController.getCurrentState();
					if(state == State.draft) {
						int remainedSoldiers = TerritoryController.getRemainingSoldiers();
						if(remainedSoldiers > 0)
						{
							JOptionPane.showMessageDialog(frame, "You should draft " + remainedSoldiers + " soldiers.");
							return ;
						}
					}
					else if(state == State.attack)
					{
						secondTerritory = null;
					}
					else if(state == State.fortify)
					{
						TerritoryController.changeCurrentPlayer();
						TerritoryController.newTurn();
						updatePanel();
						secondTerritory = null;
					}
					TerritoryController.changeState(false);
					state = TerritoryController.getCurrentState();
					button.setText(state.getValue());
				}
			}
		});
		btnNext.setBounds(442, 644, 118, 42);
		frame.getContentPane().add(btnNext);
		
	}
	
	public JPanel createPanel(int x, int y, int width, int height, final TerritoryNames name) {
		final JPanel panel = new JPanel();

		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(x, y, width, height);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblName = new JLabel(name.getName());
		panel.add(lblName, BorderLayout.NORTH);
		final JLabel lblArmy = new JLabel("");
		
		panel.add(lblArmy, BorderLayout.CENTER);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				State state = TerritoryController.getCurrentState();
				if(!TerritoryController.isCurrentPlayersTerritory(name)) {
					if(state == State.draft) {
						return;
					}
					else if(state == State.attack) {
						armyToAttack = 0;
						secondTerritory = name;
						if(TerritoryController.isNeighbour(currentTerritory, secondTerritory)) {
							String army = JOptionPane.showInputDialog(frame,
			                        "Enter your army size: ", null);
							
							if(!validateInput(army))
							{
								return;
							}
							
							armyToAttack = Integer.parseInt(army);
							Territory territory = TerritoryController.queryTerritory(currentTerritory);
							while(armyToAttack >= territory.getSoldierList().size())
							{
								army = JOptionPane.showInputDialog(frame,
				                        "Enter your army size again: ", null);
								
								if(!validateInput(army))
								{
									return;
								}
								
								armyToAttack = Integer.parseInt(army);
							}
							
							for(int i = 0 ; i < armyToAttack ; i++)
							{
								territory.getSoldierList().remove(0);
							}
							
							updatePanel();
							updateNeighbours(currentTerritory);
							map.get(secondTerritory).setBackground(Color.blue);
							map.get(currentTerritory).setBackground(Color.red);
						}
					}
				}
				else if(state == State.draft || state == State.attack) {
					currentTerritory = name;
					updatePanel();
					map.get(currentTerritory).setBackground(Color.red);
					updateNeighbours(currentTerritory);
				}
				else if(state == State.fortify) {
					
					if(secondTerritory == null ) {
						secondTerritory = name;
						if(TerritoryController.isNeighbour(currentTerritory, secondTerritory)) {
							map.get(secondTerritory).setBackground(Color.blue);
						}
						else {
							secondTerritory = null;
							currentTerritory = name;
							updatePanel();
							map.get(currentTerritory).setBackground(Color.red);
							updateNeighbours(currentTerritory);
						}
					}
					else {
						secondTerritory = null;
						currentTerritory = name;
						updatePanel();
						map.get(currentTerritory).setBackground(Color.red);
						updateNeighbours(currentTerritory);
					}
				}
				
				System.out.println(name.getName());
			}

		});
		
		map.put(name, panel);
		lblMap.put(name, lblArmy);
		
		return panel;
	}
	
	private boolean validateInput(String input)
	{
		if(input == null)
		{
			secondTerritory = null;
			return false;
		}
		
		if(input.equals("") || input.trim().length()==0 || Integer.valueOf(input).intValue()==0) 
		{
			JOptionPane.showMessageDialog(frame, "Invalid number !");
			secondTerritory = null;
			return false;
		}
		return true;
	}
	
	public void updatePanel() {
		Iterator<TerritoryNames> iterator = map.keySet().iterator();  
		   
		while (iterator.hasNext()) {  
			TerritoryNames key = iterator.next();  
			JPanel territory = map.get(key);
			Color color = TerritoryController.getTerritoryColor(key);
			territory.setBackground(color);
			
		}  
		updateArmy();
	}
	
	public void updateArmy() {
		Iterator<TerritoryNames> iterator = lblMap.keySet().iterator();  
		   
		while (iterator.hasNext()) {  
			TerritoryNames key = iterator.next();  
			lblMap.get(key).setText(""+TerritoryController.getNumberOfArmy(key));
		}
	}
	
	public void updateNeighbours(TerritoryNames name) {
		ArrayList<TerritoryNames> ally_neighbours = TerritoryController.getNeighbours(name, false);
		
		for(TerritoryNames ally_neighbour : ally_neighbours) {
			JPanel panel = map.get(ally_neighbour);
			panel.setBackground(Color.gray);
		}
		
		ArrayList<TerritoryNames> enemy_neighbours = TerritoryController.getNeighbours(name, true);
		for(TerritoryNames enemy_neighbour : enemy_neighbours) {
			JPanel panel = map.get(enemy_neighbour);
			panel.setBackground(Color.lightGray);
		}
	}
}
