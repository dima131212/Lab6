package server.comands;

import java.time.ZonedDateTime;
import java.util.Map;

import client.dataValidation.IsValidMovie;
import server.dataStorage.Coordinates;
import server.dataStorage.IdGeneratoin;
import server.dataStorage.Movie;
import server.dataStorage.MovieCollection;
import server.dataStorage.MovieGenre;
import server.dataStorage.Person;

/**
 * Класс, реализующий команду добавления нового фильма в коллекцию.
 */
public class CommandAdd extends Command<Map<String, Object>> {
    
    /**
     * Метод для выполнения команды добавления фильма.
     * @param data Map с данными для создания фильма.
     * @return Результат выполнения команды.
     */
    @Override
    public String command(Map<String, Object> data) {
        if (data == null || data.isEmpty()) {
            return "Ошибка: Данные отсутствуют.";
        }
        
        try {
            IdGeneratoin idGen = new IdGeneratoin();
            Movie movie = new Movie(
                idGen.UpdateId(),
                (String) data.get("Name"),
                (Coordinates) data.get("Coordinates"),
                ZonedDateTime.now(),
                (int) data.get("OscarsCount"),
                (double) data.get("TotalBoxOffice"),
                (double) data.get("UsaBoxOffice"),
                (MovieGenre) data.get("Genre"),
                (Person) data.get("Operator")
            );
            
            if (IsValidMovie.isValidMovie(movie)) {
                MovieCollection.movies.add(movie);
                return "Фильм успешно добавлен в коллекцию.";
            } else {
                return "Ошибка: В введённых данных обнаружена ошибка, фильм не добавлен.";
            }
        } catch (Exception e) {
            return "Ошибка: Неверные данные. " + e.getMessage();
        }
    }
}
