import java.util.List;

public class Command{
	public enum Type{
		GET, SET, DELETE, END, COUNT, BEGIN, ROLLBACK, COMMIT
	}
	Type  type;
	List<String> tokens;
}


