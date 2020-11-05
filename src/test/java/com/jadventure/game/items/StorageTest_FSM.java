package com.jadventure.game.items;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jadventure.game.entities.Player;
import com.jadventure.game.navigation.Coordinate;
import com.jadventure.game.navigation.Location;
import com.jadventure.game.navigation.LocationType;
import com.jadventure.game.prompts.CommandCollection;

public class StorageTest_FSM {
	Storage storage;

	@Before
	public void setUp() {
		storage = new Storage(5);
	}

	@Test
	public void TestCase1() {

		storage.add(A());
		assertEquals(2, storage.calculateWeight().intValue());
		storage.remove(B());
		assertEquals(2, storage.calculateWeight().intValue());
		storage.remove(A());
		assertEquals(0, storage.calculateWeight().intValue());
		storage.remove(A());
		assertEquals(0, storage.calculateWeight().intValue());

	}

	@Test
	public void TestCase2() {

		storage.add(B());
		assertEquals(3, storage.calculateWeight().intValue());
		storage.add(B());
		assertEquals(3, storage.calculateWeight().intValue());
		storage.remove(A());
		assertEquals(3, storage.calculateWeight().intValue());
		storage.remove(B());
		assertEquals(0, storage.calculateWeight().intValue());
		storage.remove(B());
		assertEquals(0, storage.calculateWeight().intValue());

	}

	@Test
	public void TestCase3() {

		storage.add(A());
		assertEquals(2, storage.calculateWeight().intValue());
		storage.add(A());
		assertEquals(4, storage.calculateWeight().intValue());
		storage.add(A());
		assertEquals(4, storage.calculateWeight().intValue());
		storage.add(B());
		assertEquals(4, storage.calculateWeight().intValue());
		storage.remove(B());
		assertEquals(4, storage.calculateWeight().intValue());
		storage.remove(A());
		assertEquals(2, storage.calculateWeight().intValue());

	}

	@Test
	public void TestCase4() {

		storage.add(B());
		assertEquals(3, storage.calculateWeight().intValue());
		storage.add(A());
		assertEquals(5, storage.calculateWeight().intValue());
		storage.add(A());
		assertEquals(5, storage.calculateWeight().intValue());
		storage.add(B());
		assertEquals(5, storage.calculateWeight().intValue());
		storage.remove(A());
		assertEquals(3, storage.calculateWeight().intValue());

	}

	@Test
	public void TestCase5() {

		storage.add(B());
		assertEquals(3, storage.calculateWeight().intValue());
		storage.add(A());
		assertEquals(5, storage.calculateWeight().intValue());
		storage.remove(B());
		assertEquals(2, storage.calculateWeight().intValue());
		storage.add(B());
		assertEquals(5, storage.calculateWeight().intValue());

	}

	private Item A() {
		Map<String, Integer> properties = new HashMap<>();
		properties.put("health", 0);
		properties.put("weight", 2);
		return new Item("A", "", "A", "", 1, properties);
	}

	private Item B() {
		Map<String, Integer> properties = new HashMap<>();
		properties.put("health", 0);
		properties.put("weight", 3);
		return new Item("B", "", "B", "", 1, properties);
	}
}
