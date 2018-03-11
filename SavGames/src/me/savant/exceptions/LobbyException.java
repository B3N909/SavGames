package me.savant.exceptions;

import me.savant.minigame.GameHelper;

public class LobbyException extends Exception
{
	/**
	 * Thrown when name is invalid!
	 */
	private static final long serialVersionUID = 1L;

	public LobbyException(String message)
	{
		super(message);
		GameHelper.fail();
	}
}
