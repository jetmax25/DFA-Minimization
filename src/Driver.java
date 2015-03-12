import java.util.ArrayList;
import java.util.Scanner;


public class Driver {
	static State states[];
	static State acceptStates[];
	static State regStates[];
	static int possibleMerge[][];
	static int numLetters;
	static boolean done[][];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int numCases = in.nextInt();
		
	
		for(int i = 0; i < numCases; i++)
		{
			int numStates = in.nextInt();
			
			states = new State[numStates];
			
			for(int j = 0; j < numStates; j++)
			{
				states[j] = new State(j);
			}
			
			numLetters = in.nextInt();
			
			int numFinal = in.nextInt();
			
			for(int j = 0; j < numFinal; j++)
			{
				states[in.nextInt()].setFinal();
			}
			
			for(int j = 0; j < numStates; j++)
			{
				for(int k = 0; k < numLetters; k++)
				{
					states[j].addTransition(states[in.nextInt()]);
				}
			}
			acceptStates = new State[numFinal];
			regStates = new State[numStates - numFinal];
			int f = 0, r = 0;
			for(int j = 0; j < numStates; j++)
			{
				if(states[j].isFinal()){
					acceptStates[f] = states[j];
					f++;
				}
				else{
					regStates[r] = states[j];
					r++;
				}
			}
			
			possibleMerge = new int[numStates][numStates];
			
			//lets get down to buisness to defeat the huns
			for(int j = 0; j < numStates; j++)
			{
				for(int k = j; k < numStates; k++)
				{
					if(k <= j || possibleMerge[j][k] != 0) continue;
					else eliminateDif(j, k);
					
				}
			}
			
			for(int j = 0; j < numStates; j++)
			{
				for(int k = j; k < numStates; k++)
				{
					if(possibleMerge[j][k] == 2) continue;
					
					done = new boolean[numStates][numStates];
					if( !checkDif(j,k) ){
						possibleMerge[j][k] = 2;
						possibleMerge[k][j] = 2;
					}
				}
			}
			
			
			
			boolean dif[] = new boolean[numStates];
			int count = 0;
			for(int j = 0; j < numStates; j++)
			{
				if(!dif[j]){
					states[j].setNum(count++);
					for(int k = j + 1; k < numStates; k++)
					{
						if( possibleMerge[j][k] == 0){
							states[k].combined(states[j]);
							dif[k] = true;
						}
						
					}
				}
			}
			
			for(int j = 0; j < numStates; j++)
			{
				if(!dif[j]){
					for(int k = 0; k < numLetters; k++){
						
						System.out.print(states[j].getTransition(k).getNum() + " "); 
					}
					System.out.println();
				}
			}
			
			
		}
	}
	
	static void eliminateDif(int a, int b)
	{
		
		if(states[a].isFinal() != states[b].isFinal()){
			possibleMerge[a][b] = 2;
			possibleMerge[b][a] = 2;
		}
	}
	
	static boolean checkDif(int a, int b)
	{
		if(a == b) return true;
		if(possibleMerge[a][b] == 2) return false; 
		
		
		if(done[a][b]){
			return true;
		}
		
		done[a][b] = true;
		
		boolean merge = true;
		for(int i = 0; i < numLetters; i++)
		{
			if(( states[a].getTransition(i).getNum() == a || states[a].getTransition(i).getNum() == b) && (states[b].getTransition(i).getNum() == a || states[b].getTransition(i).getNum() == b)) continue;
			merge = merge && checkDif(states[a].getTransition(i).getNum(), states[b].getTransition(i).getNum());
		}
		return merge;
	}

}
