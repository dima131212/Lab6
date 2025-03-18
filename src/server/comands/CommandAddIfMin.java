package server.comands;

import java.time.ZonedDateTime;
import java.util.Map;

import client.dataValidation.IsValidMovie;
import server.dataStorage.*;

public class CommandAddIfMin extends Command<Map<String, Object>> {
    /**
     * Метод для выполнения команды добавления фильма, если его сборы являются минимальными в коллекции.
     * @return Сообщение о результате выполнения команды.
     */
    @Override
    public String command(Map<String, Object> movieData) {
        if (movieData == null) {
            return "Ошибка: данные фильма отсутствуют.";
        }

        // Находим минимальный TotalBoxOffice в коллекции
        double minTotalBoxOffice = MovieCollection.movies.stream()
                .mapToDouble(Movie::getTotalBoxOffice)
                .min()
                .orElse(Double.MAX_VALUE); // Если коллекция пуста, установим максимально возможное значение

        try {
            double newMovieTotalBoxOffice = (double) movieData.get("TotalBoxOffice");

            if (newMovieTotalBoxOffice < minTotalBoxOffice) {
                IdGeneratoin idGenerator = new IdGeneratoin();
                Movie movie = new Movie(
                        idGenerator.UpdateId(),
                        (String) movieData.get("Name"),
                        (Coordinates) movieData.get("Coordinates"),
                        ZonedDateTime.now(),
                        (int) movieData.get("OscarsCount"),
                        newMovieTotalBoxOffice,
                        (double) movieData.get("UsaBoxOffice"),
                        (MovieGenre) movieData.get("Genre"),
                        (Person) movieData.get("Operator")
                );

                if (IsValidMovie.isValidMovie(movie)) {
                    MovieCollection.movies.add(movie);
                    return "Фильм успешно добавлен в коллекцию.";
                } else {
                    return "Ошибка: в введённых данных обнаружена ошибка, фильм не добавлен в коллекцию.";
                }
            } else {
                return "Фильм не добавлен, так как его сборы не являются минимальными.";
            }

        } catch (NullPointerException | ClassCastException e) {
            return "Ошибка: некорректные данные фильма.";
        }
    }
}

