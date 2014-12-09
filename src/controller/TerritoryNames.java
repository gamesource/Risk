package controller;

public enum TerritoryNames {
	alaska("alaska"),
	alberta("alberta"),
	central_america("central america"),
	eastern_united_states("eastern united states"),
	greenland("greenland"),
	northwest_territory("northwest territory"),
	ontario("ontario"),
	quebec("quebec"),
	western_united_states("western united states"),
	argentina("argentina"),
	brazil("brazil"),
	peru("peru"),
	venezuela("venezuela"),
	great_britain("great britain"),
	iceland("iceland"),
	northern_europe("northern europe"),
	scandinavia("scandinavia"),
	southern_europe("southern europe"),
	ukraine("ukraine"),
	western_europe("western europe"),
	congo("congo"),
	east_africa("east africa"),
	egypt("egypt"),
	madagascar("madagascar"),
	north_africa("north africa"),
	south_africa("south africa"),
	afghanistan("afghanistan"),
	china("china"),
	india("india"),
	irkutsk("irkutsk"),
	japan("japan"),
	kamchatka("kamchatka"),
	middle_East("middle East"),
	mongolia("mongolia"),
	siam("siam"),
	siberia("siberia"),
	ural("ural"),
	yakutsk("yakutsk"),
	eastern_australia("eastern australia"),
	indonesia("indonesia"),
	new_guinea("new guinea"),
	western_australia("western australia"),
	invalid_territory("invalid territory");
	
	private final String name;
	TerritoryNames(String name) { this.name = name; }
    public String getName() { return name; }
}
