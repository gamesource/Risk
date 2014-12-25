package controller;

import interfaces.CardBehaviour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Board;
import model.Player;
import model.ThreeSlotsCard;
import model.TwoSlotsCard;

public class CardController {

	static Board board = Board.getInstance();
	public static void tradeCards(HashMap<Integer, SoldierTypes> map) {
		Player current_player = board.getCurrentPlayer();
		
		ArrayList<CardBehaviour> cards = new ArrayList<CardBehaviour>();
		Iterator<Integer> iterator = map.keySet().iterator();  
		   
		while (iterator.hasNext()) {  
			Integer key = iterator.next();  
			SoldierTypes soldier = map.get(key);
			
			CardBehaviour card = null;
			if(soldier == SoldierTypes.all) {
				card = new ThreeSlotsCard();
				card.addSoldier(SoldierTypes.footman);
				card.addSoldier(SoldierTypes.horseman);
				card.addSoldier(SoldierTypes.cannon);
			}
			else {
				card = new TwoSlotsCard(TerritoryNames.afghanistan);
				card.addSoldier(SoldierTypes.footman);
			}
			
			cards.add(card);
		}  
		
		
	}
}
