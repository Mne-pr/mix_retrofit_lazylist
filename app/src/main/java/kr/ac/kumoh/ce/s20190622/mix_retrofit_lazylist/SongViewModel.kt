package kr.ac.kumoh.ce.s20190622.mix_retrofit_lazylist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SongViewModel : ViewModel() {
    private val SERVER_URL = "https://port-0-forsmartappback-5yc2g32mlomgovxh.sel5.cloudtype.app/"
    private val songApi: SongApi
    private val _songList = MutableLiveData<List<Song>>()

    val songList: LiveData<List<Song>>
        get() = _songList

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        songApi = retrofit.create(SongApi::class.java)
        fetchData()
    }

    // 따로 만든 이유, 서버가 리프레시될 때 이 것만 수행할 수도 있으므로
    private fun fetchData() {
        // 메인 스레드가 아닌 곳에서 동작해야 하는 것임
        viewModelScope.launch {
            try{
                val response = songApi.getSongs()
                _songList.value = response
            } catch (e:Exception) {
                Log.e("fetchData()", e.toString())
            }
        }
    }
}
