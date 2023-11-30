package kr.ac.kumoh.ce.s20190622.mix_retrofit_lazylist

import retrofit2.http.GET

interface SongApi {
    @GET("song")
    suspend fun getSongs(): List<Song>
}
