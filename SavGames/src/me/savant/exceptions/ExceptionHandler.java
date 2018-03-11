package me.savant.exceptions;

import me.savant.main.SavGames;

public class ExceptionHandler
{
	public static void throwException()
	{
		SavGames.fail();
	}
}
