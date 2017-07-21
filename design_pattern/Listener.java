import java.util.*;
import java.lang.*;

public class Listener{

	public static void main(String args[]){
		Button button = new Button(1);
		button.addOnClickListener(new OnClickListener(){
			public void onClick(View v){
				System.out.println("yoooo" + button.getId());
			}
		});
		button.addOnClickListener(new OnClickListener(){
			public void onClick(View v){
				System.out.println("yoooo" + button.getId());
			}
		});
		button.addOnClickListener(new OnClickListener(){
			public void onClick(View v){
				System.out.println("yoooo" + button.getId());
			}
		});
		button.addOnClickListener(new OnClickListener(){
			public void onClick(View v){
				System.out.println("yoooo" + button.getId());
			}
		});
		
		try{ Thread.sleep(1000); } catch (InterruptedException ex) {}
		button.simulateUserClick();
	

	}
}
