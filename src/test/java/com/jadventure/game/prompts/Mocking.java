package com.jadventure.game.prompts;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import com.jadventure.game.entities.Player;
import com.jadventure.game.monsters.MonsterFactory;
import com.jadventure.game.navigation.Coordinate;
import com.jadventure.game.navigation.Location;
import com.jadventure.game.navigation.LocationType;

public class Mocking {
	Player player;
	Location location;
	CommandCollection collection;
	MonsterFactory monsterfactory;

	@Before
	public void setUp() {
		Coordinate coordinate = new Coordinate(1, 1, 0);
		String title = "At the edge of a forest";
		String description = "The are many big trees and some tick busses, " + "looks difficult to go through.";
		LocationType locationType = LocationType.FOREST;
		location = new Location(coordinate, title, description, locationType);
		location.setDangerRating(5);

		player = Player.getInstance("recruit");
		player.setLevel(1);
		player.setLocation(location);

		monsterfactory = new MonsterFactory();

		collection = CommandCollection.getInstance();
		collection.initPlayer(player);
	}

	@Test
	public void generateMonsterTest() {
		Player spy = spy(player);

		when(spy.getArmour()).thenReturn(5);
		assertEquals(spy.getArmour(), 5);
		verify(spy, times(1)).getArmour();

		monsterfactory.generateMonster(spy);
		verify(spy, times(1)).getLevel();
		verify(spy, times(2)).getLocation();
		verify(spy, times(1)).getLocationType();
		verifyNoMoreInteractions(spy);
	}
}
