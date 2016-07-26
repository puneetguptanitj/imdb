import java.util.HashMap;
import java.util.Map;

public class State {
	private Map<String, Integer> data = new HashMap<>();
	private Map<Integer, Integer> revIndex = new HashMap<>();
	public Map<String, Integer> getData() {
		return data;
	}
	public void setData(Map<String, Integer> data) {
		this.data = data;
	}
	public Map<Integer, Integer> getRevIndex() {
		return revIndex;
	}
	public void setRevIndex(Map<Integer, Integer> revIndex) {
		this.revIndex = revIndex;
	}
	@Override
	public String toString(){
		return "[\n\t"+data.toString() + "\n\t" + revIndex.toString() +"\n]";
	}
	
}
