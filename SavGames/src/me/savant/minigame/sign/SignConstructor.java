package me.savant.minigame.sign;

public class SignConstructor
{
	/*
	 * Minigame Name %name%
	 * Map %map%
	 * Current Players - %current%
	 * Max Players - %max%
	 * Status - %status%
	 */
	
	String[] lines;
	public SignConstructor(String... lines)
	{
		this.lines = lines;
	}
}
