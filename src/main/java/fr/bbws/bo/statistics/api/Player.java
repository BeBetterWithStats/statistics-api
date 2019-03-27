package fr.bbws.bo.statistics.api;

public class Player {

	private String name;
	
	private String jersey;
	
	private String team;
	
	private KEY_WORDS fieldPosition;
	
	private int battingOrder;
	
	private int[] hit;
	
	private int[] ab;
	
	public Player( String name, String team, String jersey, KEY_WORDS position, int order) {
		this.name = name;
		this.hit = new int[ KEY_WORDS.MAX.intValue()];			
		this.ab = new int[ KEY_WORDS.MAX.intValue()];
		this.jersey = jersey;
		this.team = team;
		this.fieldPosition = position;
		this.battingOrder = order;
	}

	public String getName() {
		return name;
	}

	public String getJersey() {
		return jersey;
	}

	public String getTeam() {
		return team;
	}

	public KEY_WORDS getFieldPosition() {
		return fieldPosition;
	}

	public int getBattingOrder() {
		return battingOrder;
	}

	public String getAVG() {
		int nb_ab = 0;
		int nb_hit = 0;
		String[] zero = { "0.000", "0.000", ".000", "00", "0", ""};
		
		nb_ab = this.ab[KEY_WORDS.PITCHER.intValue()] 
				+ this.ab[KEY_WORDS.CATCHER.intValue()]
					+ this.ab[KEY_WORDS.FIRST_BASE.intValue()]
							+ this.ab[KEY_WORDS.SECOND_BASE.intValue()]
									+ this.ab[KEY_WORDS.THIRD_BASE.intValue()]
											+ this.ab[KEY_WORDS.SHORTSTOP.intValue()]
													+ this.ab[KEY_WORDS.LEFT_FIELD.intValue()]
															+ this.ab[KEY_WORDS.CENTER_FIELD.intValue()]
																	+ this.ab[KEY_WORDS.RIGHT_FIELD.intValue()]
																			+ this.ab[KEY_WORDS.OBR.intValue()]
																					+ this.ab[KEY_WORDS.K_SWINGING.intValue()]
																							+ this.ab[KEY_WORDS.K_LOOKING.intValue()]
																									- this.ab[KEY_WORDS.SACRIFICE.intValue()];
		nb_hit = this.hit[KEY_WORDS.PITCHER.intValue()] 
				 + this.hit[KEY_WORDS.CATCHER.intValue()]
						+ this.hit[KEY_WORDS.FIRST_BASE.intValue()]
								+ this.hit[KEY_WORDS.SECOND_BASE.intValue()]
										+ this.hit[KEY_WORDS.THIRD_BASE.intValue()]
												+ this.hit[KEY_WORDS.SHORTSTOP.intValue()]
														+ this.hit[KEY_WORDS.LEFT_FIELD.intValue()]
																+ this.hit[KEY_WORDS.CENTER_FIELD.intValue()]
																		+ this.hit[KEY_WORDS.RIGHT_FIELD.intValue()];
		
		String avg = Double.toString( (double) Math.round( (double) nb_hit / nb_ab * 1000) / 1000);
		return avg + zero[ avg.length()];
	}

	public int[] getHit() {
		return hit;
	}

	public int[] getAb() {
		return ab;
	}
	

	public String getFieldPreference() {
		
		int left = this.ab[KEY_WORDS.THIRD_BASE.intValue()]
						+ this.ab[KEY_WORDS.SHORTSTOP.intValue()]
							+ this.ab[KEY_WORDS.LEFT_FIELD.intValue()];
		
		int center = this.ab[KEY_WORDS.PITCHER.intValue()] 
						+ this.ab[KEY_WORDS.CENTER_FIELD.intValue()];
		
		int right = this.ab[KEY_WORDS.FIRST_BASE.intValue()]
						+ this.ab[KEY_WORDS.SECOND_BASE.intValue()]
							+ this.ab[KEY_WORDS.RIGHT_FIELD.intValue()];

		
		// RG si aucune frappe sur le troisieme base
		// shift � droite possible
		if ( 0 == this.ab[KEY_WORDS.THIRD_BASE.intValue()]) {
			left = left - this.ab[KEY_WORDS.LEFT_FIELD.intValue()];
			center = center + this.ab[KEY_WORDS.LEFT_FIELD.intValue()];
			return Integer.toString( left) + " | " +Integer.toString( center) + " | " + Integer.toString( right);
		}
		
		
		// RG si aucune frappe sur le premiere base
		// shift � gauche possible
		if ( 0 == this.ab[KEY_WORDS.FIRST_BASE.intValue()]) {
			right = right - this.ab[KEY_WORDS.RIGHT_FIELD.intValue()];
			center = center + this.ab[KEY_WORDS.RIGHT_FIELD.intValue()];
			return Integer.toString( left) + " | " +Integer.toString( center) + " | " + Integer.toString( right);
		}
		
		return Integer.toString( left) + " | " +Integer.toString( center) + " | " + Integer.toString( right);

	}	
	
	public void setFieldPosition(KEY_WORDS position) {
		this.fieldPosition = position;
	}

	public void setBattingOrder(int order) {
		this.battingOrder = order;
	}

	public void increaseAb( int position) {
		this.ab[position] = this.ab[position] + 1;
	}
	
	public void increaseHit( int position) {
		this.hit[position] = this.hit[position] + 1;
	}
		
	@Override
	public String toString() {
		/*
		String value = this.name + "  (AB) : ";
		for (int i = 1; i < 99; i++) {
			switch (Integer.toString(ab[i]).length()) {
			case 0:
				value +=" 00 |";
				break;
			case 1:
				value +=" 0" + Integer.toString(ab[i]) + " |";
				break;
			case 2:
				value +=" " + Integer.toString(ab[i]) + " |";
				break;
			default:
				value +="" + Integer.toString(ab[i]) + " |";
				break;
			}
		}
		
		value +="\n" + this.name + "   (H) : ";
		for (int i = 1; i < 99; i++) {
			switch (Integer.toString(hit[i]).length()) {
			case 0:
				value +=" 00 |";
				break;
			case 1:
				value +=" 0" + Integer.toString(hit[i]) + " |";
				break;
			case 2:
				value +=" " + Integer.toString(hit[i]) + " |";
				break;
			default:
				value +="" + Integer.toString(hit[i]) + " |";
				break;
			}
		}
		*/
		
		String value = "Ord. " + this.battingOrder + " " + this.name + " #" + this.jersey + " " + this.fieldPosition;
		return value;
		
	}

}
