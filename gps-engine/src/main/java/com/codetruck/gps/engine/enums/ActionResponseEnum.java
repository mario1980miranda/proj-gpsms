package com.codetruck.gps.engine.enums;

public enum ActionResponseEnum {
	NO(0L),
	YES(1L),
	WHATEVER(2L);
	
	private long option;

	ActionResponseEnum(final long option) {
		this.option = option;
	}
	
	public long getOption() {
		return this.option;
	}
	
	public static ActionResponseEnum get(final long option) {
		
		if (YES.getOption() == option) {
			return YES;
		}
		
		if (NO.getOption() == option) {
			return NO;
		}
		
		return WHATEVER;
	}
	
	public static String getDescription(final ActionResponseEnum response) {
		
		if (YES.equals(response)) {
			return "Yes";
		}
		
		if (NO.equals(response)) {
			return "Not";
		}
		
		return "-";
		
	}
	
}
