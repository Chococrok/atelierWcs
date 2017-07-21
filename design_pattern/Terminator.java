public class Terminator{

	private static Terminator sInstance;
	private int instanceCounter = 0;
	private static Object sLock = new Object();

	private Terminator(){
		
		instanceCounter++;
	}

	public void terminate(){
		System.out.println("Sarah Connor? I am terminator #" + this.instanceCounter);
	}
	
	public static Terminator getInstance(){
		synchronized(sLock){ /**ça sert au cas ou le singloton est appelé en même temps dans plusieurs thread différent ! il y a le risque que les deux arrivent en même temps dans le if et donc créé deux singloton  */
			if(sInstance == null){
				sInstance = new Terminator();
			}
		}
		return sInstance;
	}
	
}
