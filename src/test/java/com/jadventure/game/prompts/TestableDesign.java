package com.jadventure.game.prompts;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.jadventure.game.entities.Player;
import com.jadventure.game.navigation.Coordinate;
import com.jadventure.game.navigation.Location;
import com.jadventure.game.navigation.LocationType;

public class TestableDesign {
	Player player;
	Location location;
	CommandCollection collection;

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

		collection = CommandCollection.getInstance();
		collection.initPlayer(player);
	}

	@Test
	public void addItemToLocationTest() {
		collection.dummy_addItemToLocation();
		assertEquals(player.getLocation().getItems().size(), 1);
	}
}
