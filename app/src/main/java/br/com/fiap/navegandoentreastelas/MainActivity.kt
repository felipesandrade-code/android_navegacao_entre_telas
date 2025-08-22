package br.com.fiap.navegandoentreastelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import androidx.navigation.navArgument
import br.com.fiap.navegandoentreastelas.screens.MenuScreen
import br.com.fiap.navegandoentreastelas.screens.PedidosScreen
import br.com.fiap.navegandoentreastelas.screens.PerfilScreen
import br.com.fiap.navegandoentreastelas.ui.theme.NavegandoEntreAsTelasTheme
import br.com.fiap.navegandoentreastelas.screens.LoginScreen
import androidx.compose.animation.AnimatedContentTransitionScope


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegandoEntreAsTelasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = "login",
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.End,
                                animationSpec = tween (1000)
                            )
                        },
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Start,
                                animationSpec = tween(1000)
                            )
                        }
                    ){
                        composable(route = "login"){
                            LoginScreen(navController)
                        }
                        composable(route = "menu"){
                            MenuScreen(navController)
                        }
                        composable(
                            route = "pedidos?cliente={cliente}",
                            arguments = listOf(navArgument(name = "cliente"){
                                defaultValue = "Sem cliente"
                            })
                        ){
                            PedidosScreen(navController, it.arguments?.getString("cliente",)!! )
                        }
                        composable(
                            route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument("nome"){
                                    type = NavType.StringType
                                },
                                navArgument("idade"){
                                    type = NavType.IntType
                                }
                            )
                        ){
                            val nome: String? =
                                it.arguments?.getString("nome", "")
                            val idade: Int? =
                                it.arguments?.getInt("idade",0)
                            PerfilScreen(navController,nome!!, idade!!) // double bang

                        }
                    }
                }
            }
        }
    }
}
