package sounds;

public interface Command {

	void undo();
	void redo();
	void execute();
}
