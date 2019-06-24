package com.jay.mvvmdaggerkotlin.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

//https://github.com/googlesamples/android-architecture-components/issues/115
public final class ApiResponse<V> {

    @Nullable
    private final V data;

    @Nullable
    private final Throwable error;

    /**
     * In Case of successful API request, pass the received data here.
     *
     * @param Data received data from successful API Call.
     */
    public ApiResponse(@NonNull final V Data) {
        Objects.requireNonNull(Data);

        this.data = Data;
        this.error = null;
    }

    /**
     * In case of failed API request, pass error.
     *
     * @param error The cause of the failed API Call.
     */
    public ApiResponse(@NonNull final Throwable error) {
        Objects.requireNonNull(error);

        this.error = error;
        this.data = null;
    }

    public boolean isSuccessful() {
        return data != null && error == null;
    }

    @NonNull
    public V getData() {
        if (data == null) {
            throw new IllegalStateException("Data is null; Call ApiResponse#isSuccessful() first.");
        }
        return data;
    }

    @NonNull
    public Throwable getError() {
        if (error == null) {
            throw new IllegalStateException("Error is null; Call ApiResponse#isSuccessful() first.");
        }
        return error;
    }
}
