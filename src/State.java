import java.util.ArrayList;


public class State {
	private int num;
	private boolean finalState;
	ArrayList<State> transition;
	
	
	public State(int num){
		this.num = num;
		finalState = false;
		transition = new ArrayList<State>();
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
		return transition.get(num);
	}
	
	public int getNum()
	{
		return num;
	}
}
