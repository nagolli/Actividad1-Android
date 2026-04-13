package com.viu.actividad1_android.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.viu.actividad1_android.activities.activity2MVVM.Screen2
import com.viu.actividad1_android.activities.ordersList.OrderListScreen
import com.viu.actividad1_android.activities.ordersList.OrderListViewModel
import com.viu.actividad1_android.activities.ordersList.OrderListViewModelFactory
import com.viu.actividad1_android.activities.productDetail.ProductDetailScreen
import com.viu.actividad1_android.activities.productDetail.ProductDetailViewModel
import com.viu.actividad1_android.activities.productDetail.ProductDetailViewModelFactory
import com.viu.actividad1_android.activities.productGrid.ProductGridScreen
import com.viu.actividad1_android.activities.productGrid.ProductGridViewModel
import com.viu.actividad1_android.activities.productGrid.ProductGridViewModelFactory
import com.viu.actividad1_android.data.category.repository.CategoryRepository
import com.viu.actividad1_android.data.category.repository.categoryFakeOrHttpDataSource
import com.viu.actividad1_android.data.order.repository.OrderRepository
import com.viu.actividad1_android.data.order.repository.orderFakeOrHttpDataSource
import com.viu.actividad1_android.data.product.repository.ProductRepository
import com.viu.actividad1_android.data.product.repository.productFakeOrHttpDataSource
import com.viu.actividad1_android.data.supplier.repository.SupplierRepository
import com.viu.actividad1_android.data.supplier.repository.supplierFakeOrHttpDataSource
import com.viu.actividad1_android.ui.topMenu.TopMenu

/**
 * Configura la navegación principal de la aplicación.
 *
 * Define el NavHost, las rutas disponibles y los ViewModels asociados
 * a cada pantalla siguiendo el patrón MVVM.
 */
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopMenu(
                onChessClick = { navController.navigate(InterfaceDefinitions.ProductGrid.route) },
                onCartClick = { navController.navigate(InterfaceDefinitions.Screen2.route) },
                onUserClick = { navController.navigate(InterfaceDefinitions.OrderList.route) }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = InterfaceDefinitions.ProductGrid.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            // Pantalla: Product Grid
            composable(InterfaceDefinitions.ProductGrid.route) {

                val viewModel: ProductGridViewModel = viewModel(
                    factory = ProductGridViewModelFactory(
                        products = ProductRepository(remote = productFakeOrHttpDataSource()),
                        categories = CategoryRepository(remote = categoryFakeOrHttpDataSource()),
                        suppliers = SupplierRepository(remote = supplierFakeOrHttpDataSource())
                    )
                )

                ProductGridScreen(
                    viewModel = viewModel,
                    onProductClick = { productId ->
                        navController.navigate(InterfaceDefinitions.ProductDetail.createRoute(productId))
                    }
                )
            }

            // Pantalla: Detalles de producto
            composable(
                route = InterfaceDefinitions.ProductDetail.route,
                arguments = listOf(navArgument("productId") { type = NavType.IntType })
            ) { backStackEntry ->
                val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                val viewModel: ProductDetailViewModel = viewModel(
                    factory = ProductDetailViewModelFactory(
                        products = ProductRepository(remote = productFakeOrHttpDataSource()),
                        productId = productId
                    )
                )

                ProductDetailScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            // Pantalla: Carrito
            composable(InterfaceDefinitions.Screen2.route) {
                Screen2(navController)
            }

            // Pantalla: Historial de pedidos
            composable(InterfaceDefinitions.OrderList.route) {

                val viewModel: OrderListViewModel = viewModel(
                    factory = OrderListViewModelFactory(
                        orders = OrderRepository(remote = orderFakeOrHttpDataSource())
                    )
                )

                OrderListScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}
