package moi.myapplication.fr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import moi.myapplication.fr.ui.theme.LucilleApplicationTheme

@Composable
fun ScreenP(windowClass : WindowSizeClass, navController: NavController) {
    when(windowClass.widthSizeClass){
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment= Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()){

                chat()
                nom()
                annee()
                champollion()
                mail()
                linkedin()
                bouton(navController)
            }
        } else ->{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement= Arrangement.SpaceEvenly,
                verticalAlignment= Alignment.CenterVertically
            ){
                Column (
                    modifier= Modifier.padding(start = 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                        ){
                    chat()
                    nom()

                }
                Column (

                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    annee()
                    champollion()
                    mail()
                    linkedin()
                    bouton(navController)
                }
            }
        }

    }

}

@Composable
fun chat(){
    Image(
        painterResource(id = R.drawable.carre),
        contentDescription = "Photo Profil",
        modifier = Modifier
            .size(250.dp)
            .padding(20.dp)
            .clip(CircleShape)

    )
}
@Composable
fun nom(){
    Text(
        text = "Lucille Delbos",
        style = MaterialTheme.typography.displaySmall,

        modifier = Modifier
            .padding(1.dp),

    )
}
@Composable
fun annee(){
    Text(
        text = "Etudiante de 4eme annee",
        style = MaterialTheme.typography.titleLarge,

        modifier = Modifier
            .padding(5.dp)

    )
}
@Composable
fun champollion(){
    Text(
        text = "Ecole d'ingenieur ISIS - INU Champollion",
        style = MaterialTheme.typography.bodyLarge,

        modifier = Modifier
            .padding(15.dp)
    )
}
@Composable
fun mail(){
    Row(modifier = Modifier.padding(top=30.dp)){

        Image(painterResource(id = R.drawable.email),
            contentDescription = "icon email",
            modifier = Modifier.size(30.dp)

        )
        Text(
            text = "lucille@delbos.org",
            style = MaterialTheme.typography.bodyLarge,

            modifier = Modifier
                .padding(7.dp)
        )
    }
}
@Composable
fun linkedin(){
    Row(){

        Image(painterResource(id = R.drawable.linkedin),
            contentDescription = "icon email",
            modifier = Modifier.size(30.dp)

        )
        Text(
            text = "www.linkedin/in/lucille-delbos",
            style = MaterialTheme.typography.bodyLarge,

            modifier = Modifier
                .padding(7.dp)
        )


    }
}
@Composable
fun bouton(navController: NavController){
    Button(onClick = { navController.navigate("Films") },
        modifier = Modifier.padding(top=40.dp)
    ) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))

        Text(text = "Demarrer")

    }
}












