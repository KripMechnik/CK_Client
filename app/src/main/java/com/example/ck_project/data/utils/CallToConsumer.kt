package com.example.ck_project.data.utils

import android.util.Log
import androidx.annotation.NonNull
import com.example.ck_project.domain.entity.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.Consumer

class CallToConsumer<SOURCE, DEST>(

    private val callback: Consumer<Status<DEST>?>?,
    private val mapper: Mapper<SOURCE, DEST>
) : Callback<SOURCE> {

    override fun onResponse(call: Call<SOURCE>, response: Response<SOURCE>) {
        try {
            callback!!.accept(
                Status(
                    response.code(),
                    mapper.map(response.body()!!),
                    null
                )
            )
        } catch (e: Exception){
            if (response.code() == 400){
                callback!!.accept(
                    Status(
                        response.code(),
                        null,
                        Throwable("${response.errorBody()?.string()}")
                    )
                )
            }


        }

    }

    override fun onFailure(call: Call<SOURCE>, throwable: Throwable) {
        callback!!.accept(
            Status(
                -1,
                null,
                throwable
            )
        )
    }

    fun interface Mapper<SOURCE, DEST> {
        fun map(source: SOURCE): DEST
    }
}
