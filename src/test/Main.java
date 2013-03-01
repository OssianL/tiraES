package test;


import com.artemis.ArtemisWorld;

import core.Aspect;
import core.Entity;
import core.EntityWorld;

public class Main implements Runnable {

	private EntityWorld entityWorld = new EntityWorld();
	private static int testEntityCount = 100000;
	
	public Main() {
		entityWorld.addSystem(new TestSystem(new Aspect(TestComponent.class)));
	}
	
	@Override
	public void run() {
		
		while(true) {
			long timeStart = System.nanoTime();
			for(int i = 0; i < 100; i++) {
				entityWorld.process();
			}
			System.out.println("entity count: " + entityWorld.getEntityCount());
			System.out.println("aika: " + ((System.nanoTime() - timeStart) / (1000000 * 100)) + "ms");
			
			
			/*
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			System.out.println("---------------------------------------------");
		}
		
	}
	
	public void performanceTest() {
		
		long timeStart = System.nanoTime();
		for(int i = 0; i < testEntityCount; i++) {
			TestEntityFactory.createTestEntity(entityWorld);
		}
		System.out.println("add " + testEntityCount + " entities: " + ((System.nanoTime() - timeStart) / (1000000)) + "ms");
		
		timeStart = System.nanoTime();
		for(int i = 0; i < 100; i++) {
			entityWorld.process();
		}
		System.out.println("process " + testEntityCount + " entities: " + ((System.nanoTime() - timeStart) / (1000000 * 100)) + "ms");
		
		System.out.println("---------------------------------------------");
	}
	
	public void artemisPerformanceTest() {
		ArtemisWorld artemisWorld = new ArtemisWorld();
		artemisWorld.setSystem(new ArtemisTestSystem());
		artemisWorld.initialize();
		
		long timeStart = System.nanoTime();
		for(int i = 0; i < testEntityCount; i++) {
			TestEntityFactory.createArtemisTestEntity(artemisWorld);
		}
		System.out.println("artemis add " + testEntityCount + " entities: " + ((System.nanoTime() - timeStart) / (1000000)) + "ms");
		
		timeStart = System.nanoTime();
		for(int i = 0; i < 100; i++) {
			artemisWorld.process();
		}
		System.out.println("artemis process " + testEntityCount + " entities: " + ((System.nanoTime() - timeStart) / (1000000 * 100)) + "ms");
		
		System.out.println("---------------------------------------------");
		
	}

	public static void main(String args[]) {
		/*
		Thread thread = new Thread(new Main());
		thread.start();*/
		
		Main asdf = new Main();
		asdf.artemisPerformanceTest();
		asdf.performanceTest();
		
	}
}
