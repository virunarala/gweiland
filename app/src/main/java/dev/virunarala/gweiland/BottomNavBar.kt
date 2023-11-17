package dev.virunarala.gweiland

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.bottom_nav_bar_bg)
        ),
        modifier = modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_small))) {
            BottomNavItem(iconId = R.drawable.ic_money_shop, text = stringResource(id = R.string.money_shop))
            BottomNavItem(iconId = R.drawable.ic_exchange, text = stringResource(id = R.string.exchange))
            Image(
                painter = painterResource(id = R.drawable.ic_metaverse),
                contentDescription = stringResource(id = R.string.metaverse),
                modifier = Modifier
                    .size(40.dp)
            )
            BottomNavItem(iconId = R.drawable.ic_launchpad, text = stringResource(id = R.string.launchpads))
            BottomNavItem(iconId = R.drawable.ic_wallet, text = stringResource(id = R.string.wallet))
        }
    }
}

@Composable
fun BottomNavItem(
    iconId: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = text,
            modifier = Modifier
                .size(20.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = colorResource(id = R.color.gray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar()
}