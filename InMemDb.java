import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InMemDb {

	static Db db= new Db();

	public static void main(String []args) throws IOException{
		Scanner reader                 = new Scanner(System.in);
		System.out.print("imdb> ");
		Command command                = parse(reader.nextLine());
		while(!command.type.equals(CommandType.END)){
			processCmd(command);
			System.out.print("imdb> ");
			command = parse(reader.nextLine());
		}
		reader.close();
	}

	private static Command parse(String commandStr){
		List<String> tokens = Arrays.asList(commandStr.trim().split(" "));
		Command command     = new Command();
		command.type        = CommandType.valueOf(tokens.get(0));
		command.tokens      = tokens;
		return command;
	}

	/*
	 * haven't done much input validation, have assumed that tokens are valid.
	 */
	private static void processCmd(Command command){
		switch (command.type) {
		case GET:
			System.out.println(db.get(command.tokens.get(1)));
			break;
		case SET:
			db.set(command.tokens.get(1), Integer.parseInt(command.tokens.get(2)));
			break;
		case DELETE:
			db.delete(command.tokens.get(1));
			break;
		case END:
			db.end();
			break;
		case COUNT:
			System.out.println(db.count(Integer.parseInt(command.tokens.get(1))));
			break;
		case BEGIN:
			db.begin();
			break;
		case ROLLBACK:
			db.rollback();
			break;
		case COMMIT:
			db.commit();
			break;
		}
	}
}
