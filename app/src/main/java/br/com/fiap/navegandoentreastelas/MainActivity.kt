package br.com.fiap.navegandoentreastelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.navegandoentreastelas.screens.MenuScreen
import br.com.fiap.navegandoentreastelas.screens.PedidosScreen
import br.com.fiap.navegandoentreastelas.screens.PerfilScreen
import br.com.fiap.navegandoentreastelas.ui.theme.NavegandoEntreAsTelasTheme
import br.com.fiap.navegandoentreastelas.screens.LoginScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavegandoEntreAsTelasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login"){
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
