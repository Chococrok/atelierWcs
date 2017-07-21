import java.util.*;

public class Button extends View{
	
	private ArrayList<OnClickListener> listeners = new ArrayList<>();
	
	public Button(int id){
		super(id);
	}
	
	public void addOnClickListener(OnClickListener l){
		this.listeners.add(l);
	}
	
	void simulateUserClick(){
		for(OnClickListener l :this.listeners){
			l.onClick(this);
		}
	}
}
