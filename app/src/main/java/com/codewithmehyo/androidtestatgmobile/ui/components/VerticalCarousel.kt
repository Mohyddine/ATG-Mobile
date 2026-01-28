package com.codewithmehyo.androidtestatgmobile.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.codewithmehyo.androidtestatgmobile.features.home.model.MediaItemUI

@Composable
fun VerticalCarousel(
    modifier: Modifier = Modifier,
    onMediaClick: (mediaUrl: String, adTagUrl: String) -> Unit = { _, _ -> },
    items: List<MediaItemUI>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { Spacer(Modifier.width(12.dp)) }
        items(items) { item ->
            Card(
                modifier = Modifier
                    .width(120.dp)
                    .height(200.dp),
                onClick = { onMediaClick(item.videoUrl, item.adUrl) },
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(item.imagePlaceHolder),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    onError = {
                        Log.e("IMAGE", "Failed to load: ${item.imageUrl}")
                    }
                )
            }
        }
        item { Spacer(Modifier.width(12.dp)) }
    }
}
