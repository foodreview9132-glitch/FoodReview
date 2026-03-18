package uk.ac.tees.mad.foodreview.ui.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.foodreview.ui.theme.Dimens

@Composable
fun UserProfileCard(
    email: String,
    lastLogin: String,
    modifier: Modifier = Modifier
) {

    Card(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier.fillMaxWidth() .padding(Dimens.CardPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "user",
                modifier = Modifier
                    .size(Dimens.IconLarge)
                    .padding(end = Dimens.Small)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = email,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "last login at  $lastLogin",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}