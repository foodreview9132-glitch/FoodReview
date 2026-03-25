package uk.ac.tees.mad.foodreview.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.foodreview.ui.theme.Dimens


@Composable
fun ThemeSettingCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean ,
    onToggle: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(Dimens.ElevationSmall)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.CardPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                when {
                    isDarkMode -> {

                        Text(
                            text = "Dark Mode",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Enable Dark Theme",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
                        )

                    }

                    else -> {
                        Text(
                            text = "Light Mode",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Enable Dark theme",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f)
                        )

                    }
                }
            }
            Switch(
                checked = isDarkMode,
                onCheckedChange = {
                    onToggle(it)
                } ,
            )
        }
    }
}
