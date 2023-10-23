package moi.myapplication.fr

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Search(
    navController: NavController
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        modifier = Modifier
            .animateContentSize(),
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        placeholder = { Text(text = "") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                when (backStackEntry?.destination?.route) {
                    "Films" ->{
                        println(backStackEntry?.destination?.route);
                        navController.navigate("filmsSearch/" + text.text)}
                    "filmDetail/{idMovie}" -> navController.navigate("filmsSearch/" + text.text)
                    "filmsSearch/{searchTerm}" -> navController.navigate("filmsSearch/" + text.text)

                    "Series" -> navController.navigate("seriesSearch/" + text.text)
                    "serieDetail/{idSerie}" -> navController.navigate("seriesSearch/" + text.text)

                    "Acteurs" -> navController.navigate("peopleSearch/" + text.text)
                    "peopleDetail/{idPerson}" -> navController.navigate("peopleSearch/" + text.text)

                }
            })
    )
}