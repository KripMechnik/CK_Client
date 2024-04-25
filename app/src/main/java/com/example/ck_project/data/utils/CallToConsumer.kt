package com.example.ck_project.data.utils

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
        callback!!.accept(
            Status(
                response.code(),
                mapper.map(response.body()!!),
                null
            )
        )
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
