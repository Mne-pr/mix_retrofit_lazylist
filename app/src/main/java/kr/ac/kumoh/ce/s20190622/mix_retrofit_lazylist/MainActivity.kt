package kr.ac.kumoh.ce.s20190622.mix_retrofit_lazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20190622.mix_retrofit_lazylist.ui.theme.Mix_retrofit_lazylistTheme


class MainActivity : ComponentActivity() {
    private val viewModel: SongViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(viewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: SongViewModel){
    val songList by viewModel.songList.observeAsState(emptyList())

    Mix_retrofit_lazylistTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SongList(songList)
        }
    }
}

@Composable
fun SongList(list: List<Song>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(all = 8.dp)
    ) {
        items(list) { song ->
            SongItem(song)
        }
    }
}

@Composable
fun SongItem(song: Song) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffffffcc))
            .padding(16.dp)
    ) {
        TextTitle("${song.title}")
        TextSinger("${song.singer}")
        Text("★ ${song.rating}", fontSize = 20.sp)
    }
}

@Composable
fun TextTitle(title: String) {
    Text(title, fontSize = 30.sp, fontFamily = FontFamily.Serif)
}

@Composable
fun TextSinger(singer: String) {
    Text(singer, fontSize = 20.sp, fontFamily = FontFamily.Serif)
}