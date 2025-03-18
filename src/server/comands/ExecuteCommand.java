package server.comands;

import java.util.Map;

public class ExecuteCommand {
    @SuppressWarnings("unchecked")
    public static String executeCommand(String commandName, Object[] args) {
        Command<?> command = CommandManager.commands.get(commandName);

        if (command == null) {
            return "Ошибка: Команда не найдена.";
        }

        try {
            if (args.length == 2 && args[0] instanceof Long && args[1] instanceof Map) { 
                Command<Object[]> typedCommand = (Command<Object[]>) command;
                return typedCommand.command(args);
            } else if (args.length == 1 && args[0] instanceof Long) { 
                Command<Long> typedCommand = (Command<Long>) command;
                return typedCommand.command((Long) args[0]);
            } else if (args.length == 1 && args[0] instanceof Map) { 
                Command<Map<String, Object>> typedCommand = (Command<Map<String, Object>>) command;
                return typedCommand.command((Map<String, Object>) args[0]);
            } else if (args.length == 0) { 
                Command<Void> typedCommand = (Command<Void>) command;
                return typedCommand.command(null);
            } else {
                return "Ошибка: Неверный формат аргументов для команды.";
            }
        } catch (ClassCastException e) {
            return "Ошибка приведения типов: " + e.getMessage();
        } catch (Exception e) {
            return "Ошибка выполнения команды: " + e.getMessage();
        }
    }
}


