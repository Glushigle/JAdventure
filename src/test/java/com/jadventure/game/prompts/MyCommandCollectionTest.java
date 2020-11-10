package com.jadventure.game.prompts;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.jadventure.game.DeathException;
import com.jadventure.game.GameBeans;
import com.jadventure.game.entities.Player;
import com.jadventure.game.monsters.Troll;
import com.jadventure.game.navigation.Coordinate;
import com.jadventure.game.navigation.Direction;
import com.jadventure.game.navigation.Location;
import com.jadventure.game.navigation.LocationType;
import com.jadventure.game.repository.LocationRepository;

public class MyCommandCollectionTest {
	Player player;
	Location location;
	CommandCollection collection;
	PrintStream stdout;
	ByteArrayOutputStream outContent;

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

		stdout = System.out;

		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void tearDown() {
		System.setOut(stdout);
	}

	@Test
	public void commandTeleportTest() {
		collection.command_teleport("1,0,0");
		assertTrue(player.getLocation().getCoordinate().equals(new Coordinate(1, 0, 0)));
	}

	@Test
	public void commandMaxhealthTest() {
		collection.command_maxhealth("-10");
		assertTrue(outContent.toString().contains("Maximum health must be possitive"));
		collection.command_maxhealth("10");
		assertEquals(player.getHealthMax(), 10);
	}

	@Test
	public void commandHealthTest() {
		collection.command_health("-8");
		assertTrue(outContent.toString().contains("Health must be possitive"));
		collection.command_health("8");
		assertEquals(player.getHealth(), 8);
	}
	
	@Test
	public void commandArmourTest() {
		collection.command_armour("5");
		assertEquals(player.getArmour(), 5);
	}
	
	@Test
	public void commandLevelTest() {
		collection.command_level("5");
		assertEquals(player.getLevel(), 5);
	}
	
	@Test
	public void commandGoldTest() {
		collection.command_gold("50");
		assertEquals(player.getGold(), 50);
	}
	
	@Test
	public void commandAttackTest() {
		collection.command_attack("12.5");
		assertEquals(player.getDamage(), 12.5, 0.1);
	}

	@Test
	public void commandATest() throws DeathException {
		collection.command_a("Goblin");
		assertTrue(outContent.toString().contains("Opponent not found"));
	}
	
	@Test
	public void commandPTest(){
		collection.command_p("book");
		assertFalse(outContent.toString().contains("picked up"));
	}
	
	@Test
	public void commandDTest(){
		collection.command_d("book");
		assertFalse(outContent.toString().contains("dropped"));
	}
	
	@Test
	public void commandTalkTest() throws DeathException{
		collection.command_talk("Goblin");
		assertTrue(outContent.toString().contains("Unable to talk to"));
	}
}
