package server.dataStorage;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdditionalInput {
	private Map<String, Boolean> additionalInputForCommand = new LinkedHashMap<>();
	public AdditionalInput() {
		additionalInputForCommand.put("add", true);
		additionalInputForCommand.put("add_if_min", true);
		additionalInputForCommand.put("add_if_max", true);
		additionalInputForCommand.put("clear", false);
		additionalInputForCommand.put("execute_script", false);
		additionalInputForCommand.put("help", false);
		additionalInputForCommand.put("history", false);
		additionalInputForCommand.put("info", false);
		additionalInputForCommand.put("min_by_genre", false);
		additionalInputForCommand.put("print_ascending", false);
		additionalInputForCommand.put("print_field_descending_operator", false);
		additionalInputForCommand.put("remove_by_id", false);
		additionalInputForCommand.put("save", false);
		additionalInputForCommand.put("show", false);
		additionalInputForCommand.put("update", true);
	}
	public Map<String, Boolean> getAdditionalInputForCommand () {
		return additionalInputForCommand;
	}
}
