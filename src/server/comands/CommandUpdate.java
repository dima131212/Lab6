package server.comands;

import server.dataStorage.*;
import client.dataValidation.*;

import java.util.Map;

public class CommandUpdate extends Command<Object[]> {

    @Override
    public String command(Object[] args) {    	 
        if (args.length != 2 || !(args[0] instanceof Number) || !(args[1] instanceof Map)) {
            return "Ошибка: Некорректный формат аргументов для команды.";
        }

        long id = ((Number) args[0]).longValue();
        @SuppressWarnings("unchecked")
        Map<String, Object> newMovieData = (Map<String, Object>) args[1];

        // Ищем фильм по ID
        Movie movieToUpdate = MovieCollection.movies.stream()
            .filter(movie -> movie.getId() == id)
            .findFirst()
            .orElse(null);
        

        if (movieToUpdate == null) {
            return "Фильм с ID = " + id + " не найден.";
        }

        try {
            // Создаём новый объект Movie с обновлёнными данными
            Movie updatedMovie = new Movie(
                movieToUpdate.getId(),
                (String) newMovieData.get("Name"),
                (Coordinates) newMovieData.get("Coordinates"),
                movieToUpdate.getCreationDate(),
                (int) newMovieData.get("OscarsCount"),
                (double) newMovieData.get("TotalBoxOffice"),
                (double) newMovieData.get("UsaBoxOffice"),
                (MovieGenre) newMovieData.get("Genre"),
                (Person) newMovieData.get("Operator")
            );

            // Проверяем корректность нового фильма
            if (!IsValidMovie.isValidMovie(updatedMovie)) {
                return "Ошибка: Введенные данные фильма некорректны.";
            }

            // Удаляем старый объект и добавляем новый
            MovieCollection.movies.remove(movieToUpdate);
            MovieCollection.movies.add(updatedMovie);

            return "Фильм с ID = " + id + " успешно обновлён.";

        } catch (Exception e) {
            return "Ошибка при обновлении данных фильма: " + e.getMessage();
        }
    }
}

