package com.example.bookReader.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookReader.component.ReaderLogo
import com.example.bookReader.navigation.ReaderScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


@Composable
fun ReaderSplashScreen(navController: NavController? = null) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800, easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            }
            )
        )
        delay(2000L)
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController?.navigate(ReaderScreen.LoginScreen.name)
        } else {
            navController?.navigate(ReaderScreen.ReaderHomeScreen.name)
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Surface(
            shape = CircleShape,
            border = BorderStroke(2.dp, Color.LightGray),
            modifier = Modifier
                .size(280.dp)
                .scale(scale.value),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                ReaderLogo()
                Spacer(modifier = Modifier.size(15.dp))
                Text(
                    text = "\"Read.Change.Yourself\"",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.LightGray
                )

            }

        }
    }


}

@Preview
@Composable
fun DefaultPreview() {
    ReaderSplashScreen()
}



