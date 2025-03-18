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
 * Класс, реализующий команду добавления нового фильма в коллекцию, если его сборы являются максимальными.
 */
public class CommandAddIfMax extends Command<Map<String, Object>> {

    @Override
    public String command(Map<String, Object> args) {
        if (args == null) {
            return "Ошибка: данные о фильме отсутствуют.";
        }

        double maxTotalBoxOffice = MovieCollection.movies.stream()
                .mapToDouble(Movie::getTotalBoxOffice)
                .max()
                .orElse(0); // Если коллекция пуста, считаем 0

        try {
            double totalBoxOffice = (double) args.get("TotalBoxOffice");

            if (totalBoxOffice <= maxTotalBoxOffice) {
                return "Ошибка: Сборы фильма не являются максимальными.";
            }

            IdGeneratoin idGen = new IdGeneratoin();
            Movie movie = new Movie(
                idGen.UpdateId(),
                (String) args.get("Name"),
                (Coordinates) args.get("Coordinates"),
                ZonedDateTime.now(),
                (int) args.get("OscarsCount"),
                totalBoxOffice,
                (double) args.get("UsaBoxOffice"),
                (MovieGenre) args.get("Genre"),
                (Person) args.get("Operator")
            );

            if (!IsValidMovie.isValidMovie(movie)) {
                return "Ошибка: В введённых данных обнаружена ошибка, фильм не добавлен.";
            }

            MovieCollection.movies.add(movie);
            return "Фильм успешно добавлен в коллекцию.";

        } catch (NullPointerException | ClassCastException e) {
            return "Ошибка: Некорректные данные о фильме.";
        }
    }
}

