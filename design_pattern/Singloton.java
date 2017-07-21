import java.util.*;
import java.lang.*;

public class Singloton{
	public static void main(String args[]){
	
		System.out.println("Hello, World");
		
		Terminator t = Terminator.getInstance();
		Terminator t2 = Terminator.getInstance();
		t.terminate();
		t2.terminate();
	}
}
