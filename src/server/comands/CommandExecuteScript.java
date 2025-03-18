package server.comands;


import java.util.ArrayList;

import client.dataValidation.CheckInputFile;
import client.dataValidation.CommandParser;
import client.dataValidation.InputChecker;
import client.executeScript.CollectionCommandsInFile;
import client.executeScript.FileStack;
/**
 * Класс, реализующий команду выполнения скрипта из файла.
 * Реализует интерфейс {@link CommandWithArgFile}, что означает, что команда принимает аргумент — имя файла.
 * 
 */
public class CommandExecuteScript extends Command<String>{
	InputChecker checkInputFile = new CheckInputFile();
	CommandParser parseCommand = new CommandParser();
	public static ArrayList<ArrayList<String>> collectionOfMultipleFiles = new ArrayList<>();
	public static ArrayList<String> fileCommands = new ArrayList<>();
	 /**
	   * Метод для выполнения команды выполнения скрипта из файла.
	   * Читает команды из файла и выполняет их.
	   * 
	   * @param file Имя файла, из которого будут прочитаны команды.
	 * @return 
	   */

	@Override 
	public String command(String file) {
/*
		CollectionCommandsInFile collectionCommandInFile = new CollectionCommandsInFile();
		ArrayList<String> oneCollection = new ArrayList<>();
		try {
			
			fileCommands = collectionCommandInFile.readComands(file);
			collectionOfMultipleFiles.add(fileCommands);
	    	for(String oldFile: FileStack.fileStack) {
	    		if(file.equals(oldFile)){
	    			System.out.println("команда execute_script не выполнена из-за рекурсии в ваших файлах, имя файла:"+ file);
	    			collectionOfMultipleFiles.clear();
	    			fileCommands.clear();
	    			return null;
	    				
	    			}
	    		}
	    	FileStack.fileStack.add(file);
	    	while(!collectionOfMultipleFiles.isEmpty()) {
	    		if(collectionOfMultipleFiles.size()>0){
	    			oneCollection =collectionOfMultipleFiles.get(collectionOfMultipleFiles.size()-1);
	    		}
		    	while (!oneCollection.isEmpty()) {
	
		    	    String command = oneCollection.remove(0); // Извлекаем и удаляем первый элемент
		    	    
		    	    Command<?> commandRez = CommandManager.commands.get(parseCommand.parseCommandName(command)[0]);
		    	    if(commandRez!= null) {
		    	    	commandRez.inputChecker = checkInputFile;
		    	    }
		    	    ExecuteCommand.executeCommand(command);
		    	    
		    	}
		    	if(collectionOfMultipleFiles.size()>0){
		    		collectionOfMultipleFiles.remove(collectionOfMultipleFiles.size()-1);
		    	}
	    	}
		}catch(RuntimeException e){
			System.out.println("ошибка: файл не найден");
		}
*/
		return null;
    	
	}
	
}
