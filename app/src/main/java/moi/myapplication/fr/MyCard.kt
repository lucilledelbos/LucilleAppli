package moi.myapplication.fr

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun MyCard (
    route: String,
    imgPath: String?,
    firstName: String,
    date: String?,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { },
    )  {
        Column() {
            if (imgPath != null) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500$imgPath",
                    contentDescription = firstName
                )
            }
            Text(
                text = firstName,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                maxLines = 1,
                modifier = Modifier.width(180.dp),
            )
            if (date != null) {
                Text(
                    text = date, color = Color.Black
                )
            }

        }
    }
}
