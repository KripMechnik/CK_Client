package com.example.ck_project.data.utils;

import androidx.annotation.NonNull;
import com.example.ck_project.domain.entity.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.function.Consumer;

public class CallToConsumer1<SOURCE, DEST> implements Callback<SOURCE> {
    @NonNull
    private final Consumer<Status<DEST>> callback;

    @NonNull
    private final Mapper<SOURCE, DEST> mapper;

    public CallToConsumer1(
            @NonNull Consumer<Status<DEST>> callback,
            @NonNull Mapper<SOURCE, DEST> mapper
    ) {
        this.callback = callback;
        this.mapper = mapper;
    }

    @Override
    public void onResponse(@NonNull Call<SOURCE> call, @NonNull Response<SOURCE> response) {
        callback.accept(
                new Status<>(
                        response.code(),
                        mapper.map(response.body()),
                        null
                )
        );
    }

    @Override
    public void onFailure(@NonNull Call<SOURCE> call, @NonNull Throwable throwable) {
        callback.accept(
                new Status<>(
                        -1,
                        null,
                        throwable
                )
        );
    }

    public interface Mapper<SOURCE, DEST> {
        DEST map(SOURCE source);
    }
}
