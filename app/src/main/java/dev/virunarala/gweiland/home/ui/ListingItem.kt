package dev.virunarala.gweiland.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.virunarala.gweiland.R
import dev.virunarala.gweiland.home.data.model.ListingUiModel
import dev.virunarala.gweiland.ui.theme.EmeraldGreen

@Composable
fun ListingItem(
    listing: ListingUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(id = R.dimen.padding_small),
                bottom = dimensionResource(id = R.dimen.padding_small)
            )
    ) {

        AsyncImage(
            model = listing.logo,
            placeholder = painterResource(id = R.drawable.dollar_placeholder),
            error = painterResource(id = R.drawable.dollar_placeholder),
            contentDescription = listing.name,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))

        Column {
            Text(
                text = listing.symbol,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = listing.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        val change = listing.dailyChange

        val graphColor = if(change<0) Color.Red else EmeraldGreen
        val graph = if(change<0) R.drawable.negative_change_graph else R.drawable.positive_change_graph
        val changeDescription = if(change<0) R.string.growth else R.string.fall
        Icon(
            painter = painterResource(id = graph),
            contentDescription = stringResource(id = changeDescription),
            tint = graphColor,
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(30.dp)
                .weight(1f)
        )

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
            .weight(1f)) {
            Text(
                text = stringResource(id = R.string.current_price,listing.usdPrice.toString()),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            val changeTextColor = if(change<0) Color.Red else EmeraldGreen

            Text(
                text = stringResource(id = R.string.price_change,listing.dailyChange.toString()),
                style = MaterialTheme.typography.bodyMedium,
                color = changeTextColor
            )
        }
    }
}