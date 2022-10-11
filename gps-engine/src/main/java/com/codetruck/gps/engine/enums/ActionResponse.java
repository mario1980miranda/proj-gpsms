package com.codetruck.gps.engine.enums;

public enum ActionResponse {
	NOT(0L),
	YES(1L),
	WHATEVER(2L);
	
	private long option;

	ActionResponse(final long option) {
		this.option = option;
	}
	
	public long getOption() {
		return this.option;
	}
	
	public static ActionResponse get(final long option) {
		
		if (YES.getOption() == option) {
			return YES;
		}
		
		if (NOT.getOption() == option) {
			return NOT;
		}
		
		return WHATEVER;
	}
	
	public static String getDescription(final ActionResponse response) {
		
		if (YES.equals(response)) {
			return "Yes";
		}
		
		if (NOT.equals(response)) {
			return "Not";
		}
		
		return "-";
		
	}
	
}
