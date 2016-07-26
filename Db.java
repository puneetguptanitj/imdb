import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Db {
	//easier traversal through list. states.get(0) points to most recent transaction
	List<State> states = new LinkedList<State>();
	{
		//Simplifies logic base db and all transactions are now States
		states.add(new State());
	}
	public Integer  get(String key){
		for(int i =0 ; i < states.size() ; i++){
			if(states.get(i).getData().containsKey(key)){
				Integer value = states.get(i).getData().get(key);
				return value;
			};
		}
		return null;
	}
	public void delete(String key){
		/*
		 * important to use our own method as oldValue
		 * (where ever in transaction list needs to be now nullified);
		 */
		Integer oldValue = get(key); 
		states.get(0).getData().put(key, null);
		decreaseCount(oldValue);
	}
	private void increaseCount(Integer value){
		Integer count = states.get(0).getRevIndex().get(value);
		if(count == null){
			count = 0;
		}
		states.get(0).getRevIndex().put(value, ++count);
	}
	private void decreaseCount(Integer value){
		if(value != null){
			Integer count = states.get(0).getRevIndex().get(value);
			if(count == null){
				count = 0;
			}
			states.get(0).getRevIndex().put(value, --count);
		}
	}
	public void set(String key, Integer value){
		/*
		 * important to use our own method as oldValue
		 * (where ever in transaction list needs to be now nullified);
		 */
		Integer oldValue = get(key); 
		states.get(0).getData().put(key,value);
		if(!value.equals(oldValue)){
			increaseCount(value);
			decreaseCount(oldValue);
		}
	}
	public Integer count(Integer value){
		Integer count = 0;
		for(int i =0 ; i < states.size() ; i++){
			if(states.get(i).getRevIndex().containsKey(value)){
				count += states.get(i).getRevIndex().get(value);
			}
		}
		return count;
	}
	//transaction a occurs after b, b's values would be overwritten
	private void merge (State a, State b){
		for(Entry<String, Integer> entry : a.getData().entrySet()){
			b.getData().put(entry.getKey(), entry.getValue());
		}
		for(Entry<Integer,Integer> entry: a.getRevIndex().entrySet()){
			Integer count = b.getRevIndex().get(entry.getKey());
			if(count == null){
				count = 0;
			}
			b.getRevIndex().put(entry.getKey(), entry.getValue()+ count);
		}
	}
	public void commit(){
		if(states.size() ==1){
			System.out.println( "NO TRANSACTION");
		}
		for(int i=0; i <states.size() -1 ; i++){
			/*
			 * having list initialized with one state helps here
			 * merging to actuall Db is no different than merging two transactions
			 */
			merge(states.get(i), states.get(i+1));
		}
		State finalState  = states.get(states.size() -1);
		states = new LinkedList<State>();
		states.add(finalState);
	}
	public void end(){
		System.exit(0);
	}
	public void begin(){
		states.add(0, new State());
	}
	public void rollback(){
		if(states.size() ==1){
			System.out.println( "NO TRANSACTION");
		}
		states.remove(0);
	}
}
