package uk.ac.tees.mad.foodreview.ui.feedback_submission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.HapticFeedbackConstantsCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.foodreview.FoodReviewApp
import uk.ac.tees.mad.foodreview.ui.theme.Dimens
import uk.ac.tees.mad.foodreview.utils.viewModelFactory

@Composable
fun FeedbackSubmissionScreen(
    viewModel: FeedBackSubmissionViewModel = viewModel<FeedBackSubmissionViewModel>(
        factory = viewModelFactory {
            FeedBackSubmissionViewModel(
                foodReviewRepository = FoodReviewApp.appModule.foodReviewRepository ,
                preferenceManager = FoodReviewApp.appModule.preferenceManager
            )
        }
    ),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.feedBackUiState.collectAsStateWithLifecycle()

    val hapticFeedback = LocalHapticFeedback.current
    LaunchedEffect(uiState.isSubmissionSuccess) {
        if(uiState.isSubmissionSuccess){
            viewModel.reset()
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
        }
    }

    FeedbackSubmissionScreenContent(
        uiState = uiState,
        onReviewTextChange = viewModel::onReviewTextChange,
        onRatingChange = viewModel::onRatingChange,
        onFoodNameChange = viewModel::onFoodNameChange,
        onSubmissionCLick = viewModel::onSubmissionClick,
        onBackCLick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackSubmissionScreenContent(
    uiState: FeedbackSubmissionUiState,
    onReviewTextChange: (String) -> Unit,
    onRatingChange: (Int) -> Unit,
    onFoodNameChange: (String) -> Unit,
    onSubmissionCLick: () -> Unit,
    onBackCLick: () -> Unit
) {

    Scaffold (
        modifier = Modifier.fillMaxSize() ,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Feedback"
                    )
                } ,
                navigationIcon = {
                    IconButton(onBackCLick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack ,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = Dimens.ScreenHorizontal) ,
            verticalArrangement = Arrangement.spacedBy(Dimens.ExtraSmall)){

            Spacer(modifier = Modifier.height(Dimens.ExtraSmall))

            Text(
                text = "write you feedback about your loved food",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
//                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                onValueChange = {
                    if (it.length <= 50) {
                        onFoodNameChange(it)
                    }
                },
                value = uiState.foodName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.ScreenHorizontal),
                label = {
                    Text(
                        text = "Chicken Biryani"
                    )
                },
                singleLine = true,
                maxLines = 1 ,
                shape = MaterialTheme.shapes.medium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (1..5).forEach { star ->
                    IconButton(onClick = { onRatingChange(star) }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "rating star",
                            modifier = Modifier.size(Dimens.IconLarge),
                            tint = if (star <= uiState.rating) Color.Yellow else Color.Gray
                        )
                    }
                }
            }

            OutlinedTextField(
                value = uiState.reviewText,
                onValueChange = onReviewTextChange,
                label = {
                    Text(
                        text = "write your review"
                    )
                },
                shape = MaterialTheme.shapes.medium,
                maxLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.ScreenHorizontal)
                    .height(120.dp)
            )

            if (uiState.isReviewEnable) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.ScreenHorizontal),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = Dimens.ElevationSmall
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Dimens.CardPadding)
                    ) {
                        Text(
                            text = uiState.foodName,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            repeat(uiState.rating) {
                                Box(
                                    modifier = Modifier.size(Dimens.IconLarge),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "star",
                                        tint = Color.Yellow
                                    )
                                }
                            }
                        }

                        Text(
                            text = "\"${uiState.reviewText}\"",
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic
                        )
                    }

                }
            }


            Button(
                enabled = uiState.isSubmitEnable && !uiState.isLoading,
                onClick = onSubmissionCLick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.ScreenHorizontal)
            ) {
                when{
                    uiState.isLoading ->{
                        CircularProgressIndicator(
                            modifier = Modifier.size(Dimens.IconMedium) ,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    else->{
                        Text(
                            text = "submit review"
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomePreview() {
    FeedbackSubmissionScreenContent(
        uiState = FeedbackSubmissionUiState(),
        onReviewTextChange = {},
        onRatingChange = {},
        onFoodNameChange = {},
        onSubmissionCLick = {},
        onBackCLick = {} ,

    )
}