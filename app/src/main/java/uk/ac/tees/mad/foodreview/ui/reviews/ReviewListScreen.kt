package uk.ac.tees.mad.foodreview.ui.reviews

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.foodreview.FoodReviewApp
import uk.ac.tees.mad.foodreview.domain.model.Review
import uk.ac.tees.mad.foodreview.ui.theme.Dimens
import uk.ac.tees.mad.foodreview.utils.viewModelFactory

@Composable
fun ReviewListScreen(
    onSettingClick: () -> Unit,
    onDraftFeedbackClick: () -> Unit,
    viewModel: ReviewListViewModel = viewModel<ReviewListViewModel>(
        factory = viewModelFactory {
            ReviewListViewModel(
                foodReviewRepository = FoodReviewApp.appModule.foodReviewRepository
            )
        }
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ReviewListScreenContent(
        uiState = uiState,
        onSettingClick = onSettingClick,
        onDraftFeedbackClick = onDraftFeedbackClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewListScreenContent(
    uiState: ReviewListUiState,
    onSettingClick: () -> Unit,
    onDraftFeedbackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Reviews"
                    )
                },
                actions = {
                    IconButton(onSettingClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "setting"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            Button(
                onClick = onDraftFeedbackClick,
                shape = MaterialTheme.shapes.small,
                contentPadding = PaddingValues(horizontal = Dimens.Medium)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "edit"
                )

                Spacer(Modifier.width(Dimens.ExtraSmall))

                Text(
                    text = "Draft"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.error != null -> {
                    Text(
                        text = uiState.error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(Dimens.Medium),
                        verticalArrangement = Arrangement.spacedBy(Dimens.ExtraSmall)
                    ) {
                        items(uiState.list) { review ->
                            ReviewCard(
                                review,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ReviewCard(
    review: Review,
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.ElevationSmall
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.medium
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.CardPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.Small)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = review.foodName,
                    style = MaterialTheme.typography.titleMedium
                )

                IconButton(
                    onClick = {
                        shareReview(
                            context = context,
                            review = review
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "share",
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(review.rating) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "star",
                        tint = Color.Yellow
                    )
                }
            }

            Text(
                text = "\" ${review.reviewText}\"",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

fun shareReview(
    context: Context,
    review: Review
) {

    val shareText = """
        🍽 Food: ${review.foodName}
        ⭐ Rating: ${review.rating}
        💬 "${review.reviewText}"
    """.trimIndent()

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
    }
    context.startActivity(Intent.createChooser(intent, "Share Review"))
}


@Composable
@Preview(showBackground = true)
fun ReviewListScreenPreview() {
    ReviewListScreenContent(
        uiState = ReviewListUiState(),
        onSettingClick = {},
        onDraftFeedbackClick = {}
    )
}