package com.codewithmehyo.androidtestatgmobile.ui.components

/*
@Composable
fun TopCarousel(
    modifier: Modifier = Modifier,
    onMediaClick: (mediaUrl:String, adTagUrl:String) -> Unit = {_,_->},
    items: List<MediaItemUI>
) {
    Carousel(
        itemCount = items.size,
        modifier = modifier
            .fillMaxWidth()
            .height(376.dp),

        ) { indexOfCarouselItem ->
        val featuredMovie = items[indexOfCarouselItem]
        val backgroundColor = MaterialTheme.colorScheme.background
        Box {
            AsyncImage(
                model = featuredMovie.imageUrl,
                contentDescription = null,
                placeholder = painterResource(
                    id = featuredMovie.imagePlaceHolder
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        val brush = Brush.horizontalGradient(
                            listOf(backgroundColor, Color.Transparent)
                        )
                        drawRect(brush)
                    }
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = featuredMovie.title,
                        style = MaterialTheme.typography.displaySmall
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Button(
                        onClick = {
                            onMediaClick(
                                featuredMovie.videoUrl,
                                featuredMovie.adUrl
                            )
                        }
                    ) {
                        Text(text = "Play Now")
                    }
                }
            }
        }
    }
}
*/