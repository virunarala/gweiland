package dev.virunarala.gweiland.home.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import dev.virunarala.gweiland.R
import dev.virunarala.gweiland.common.model.SortOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterItems(
    modifier: Modifier = Modifier,
    onSortClick: (sortOption: SortOptions) -> Unit
) {

    var query by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Row(modifier = modifier) {
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .weight(2f),
            maxLines = 1,
            decorationBox = {
                TextFieldDefaults.OutlinedTextFieldDecorationBox(
                    value = query,
                    innerTextField = it,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = stringResource(id = R.string.search)
                        )
                    },
                    label = { Text(
                        text = stringResource(R.string.search_cryptocurrency),
                        style = MaterialTheme.typography.labelLarge
                    ) },
                    contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                        dimensionResource(id = R.dimen.padding_medium)),
                    container = {
                        TextFieldDefaults.OutlinedBorderContainerBox(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.padding_large))
                        )
                    }
                )
            }
        )

        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))

        Box(modifier = Modifier
            .weight(1f)) {
            OutlinedButton(
                onClick = { expanded = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_filter_list_24),
                    contentDescription = stringResource(id = R.string.sort)
                )
                Text(text = stringResource(id = R.string.sort))
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                DropdownMenuItem(
                    text = { Text(text = stringResource(R.string.sort_by_market_cap)) },
                    onClick = {
                        onSortClick(SortOptions.MarketCap)
                        expanded = false
                    }
                )
                Divider()
                DropdownMenuItem(
                    text = { Text(text = stringResource(R.string.sort_by_price)) },
                    onClick = {
                        onSortClick(SortOptions.Price)
                        expanded = false
                    }
                )
                Divider()
                DropdownMenuItem(
                    text = { Text(text = stringResource(R.string.sort_by_volume)) },
                    onClick = {
                        onSortClick(SortOptions.Volume24H)
                        expanded = false
                    }
                )
            }
        }


    }
}