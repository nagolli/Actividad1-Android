package com.viu.actividad1_android.activities.ordersList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.viu.actividad1_android.R
import com.viu.actividad1_android.data.order.Order

/**
 * Vista de un item de pedido. Muestra fecha, hora, estado
 *
 * precio total y muestra un carrousel con los productos.
 *
 * ToDo: OnClick detalles completos del pedido.
 *
 * @param order Pedido a mostrar.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderItem(
    order: Order
) {
    val pagerState = rememberPagerState(pageCount = { order.products.size })

    LaunchedEffect(pagerState) {
        while (true) {
            kotlinx.coroutines.delay(5000)
            if (pagerState.pageCount == 0) continue

            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp)
            ) { page ->
                val product = order.products[page]
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.orders_date) + " ${order.date.split(" ")[0]}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.orders_hour) + " ${order.date.split(" ")[1]}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.orders_total) +
                            " ${order.totalPrice()}" +
                            stringResource(R.string.currency_euro),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = order.state,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
