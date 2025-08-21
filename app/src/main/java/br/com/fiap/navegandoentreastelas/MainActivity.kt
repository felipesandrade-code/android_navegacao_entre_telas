package br.com.fiap.navegandoentreastelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ){
                        composable(route = "login"){ LoginScreen()}
                        composable(route = "menu"){ MenuScreen()}
                        composable(route = "pedidos"){ PedidosScreen()}
                        composable(route = "perfil"){ PerfilScreen()}
                    }
                }
            }
        }
    }
}
