import java.util.ArrayList;


public class State {
	private int num;
	private boolean finalState;
	ArrayList<State> transition;
	private State combined; 
	
	public State(int num){
		this.num = num;
		finalState = false;
		transition = new ArrayList<State>();
		combined = null;
	}
	
	public void setFinal(){
		finalState = true; 
	}
	
	public boolean isFinal()
	{
		return finalState;
	}
	
	public void addTransition(State s)
	{
		transition.add(s);
	}
	
	public State getTransition(int num)
	{
		if(combined != null){
			return combined.getTransition(num);
		}
		return transition.get(num);
	}
	
	public int getNum()
	{
		if(combined != null) return combined.getNum();
		return num;
	}
	public void setNum(int n)
	{
		num = n;
	}
	public void combined(State s)
	{
		this.combined = s;  
	}
}
