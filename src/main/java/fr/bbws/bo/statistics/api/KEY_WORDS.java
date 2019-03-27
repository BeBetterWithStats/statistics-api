package fr.bbws.bo.statistics.api;

public enum KEY_WORDS {

	DESIGNATED_HITTER(0),
	PITCHER (1),
	CATCHER (2),
	FIRST_BASE (3),
	SECOND_BASE (4),
	THIRD_BASE (5),
	SHORTSTOP (6),
	LEFT_FIELD (7),
	CENTER_FIELD (8),
	RIGHT_FIELD (9),
	K_SWINGING (10),
	K_LOOKING (11),
	WALK (12),
	SACRIFICE (13),
	STOLEN_BASE (14),
	OBR (15),
	HIT_BY_PITCH (16),
	SLUGGING_ZERO (20),
	SLUGGING_1B (21),
	SLUGGING_2B (22),
	SLUGGING_3B (23),
	SLUGGING_4B (24),
	SLUGGING_GDP (25),
	UNKNOWN (99),
	MAX (100);
	
	private int key = 0;
	
	KEY_WORDS(int key) {
		this.key = key;
	}
	
	public int intValue() {
		return this.key;
	}
}
