package com.viu.actividad1_android.activities.productGrid

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.viu.actividad1_android.R
import com.viu.actividad1_android.data.product.Product

/**
 * Composable que muestra una tarjeta de producto reutilizable.
 *
 * Esta tarjeta incluye la imagen, el nombre y el precio del producto.
 * Se utiliza dentro del grid de productos y permite detectar clics
 * para navegar al detalle del producto.
 *
 * @param product Objeto de dominio que contiene la información del producto.
 * @param onProductClick Callback que se ejecuta al pulsar la tarjeta, devolviendo el ID del producto.
 */
@Composable
fun ProductCard(
    product: Product,
    onProductClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onProductClick(product.id) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(MaterialTheme.colorScheme.background),
                contentScale = ContentScale.Fit
            )

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "${product.price} ${stringResource(R.string.currency_euro)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
