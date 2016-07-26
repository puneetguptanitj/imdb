import java.util.HashMap;
import java.util.Map;

public class State {
	private Map<String, Integer> space = new HashMap<>();
	private Map<Integer, Integer> revIndex = new HashMap<>();
	public Map<String, Integer> getSpace() {
		return space;
	}
	public void setSpace(Map<String, Integer> space) {
		this.space = space;
	}
	public Map<Integer, Integer> getRevIndex() {
		return revIndex;
	}
	public void setRevIndex(Map<Integer, Integer> revIndex) {
		this.revIndex = revIndex;
	}
	@Override
	public String toString(){
		return "[\n\t"+space.toString() + "\n\t" + revIndex.toString() +"\n]";
	}
	
}
