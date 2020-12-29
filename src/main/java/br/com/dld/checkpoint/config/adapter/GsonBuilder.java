package br.com.dld.checkpoint.config.adapter;

import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author David Duarte
 */
public class GsonBuilder {

    public static Gson build() {
        return new com.google.gson.GsonBuilder()
                .registerTypeAdapter(LocalDate.class, LocalDateAdapter.LocalDateSerializer)
                .registerTypeAdapter(LocalDate.class, LocalDateAdapter.LocalDateDeserializer)
                .registerTypeAdapter(LocalDateTime.class, LocalDateTimeAdapter.LocalDateTimeSerializer)
                .registerTypeAdapter(LocalDateTime.class, LocalDateTimeAdapter.LocalDateTimeDeserializer)
                .create();
    }
}
