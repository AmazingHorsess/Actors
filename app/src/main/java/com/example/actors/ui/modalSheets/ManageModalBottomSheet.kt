package com.example.actors.ui.modalSheets

import android.content.Context
import android.text.BoringLayout
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SnapSpec
import androidx.compose.foundation.gestures.Draggable2DState
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.example.actors.ui.components.ModalBottomSheetLayout.ModalBottomSheetState
import com.example.actors.ui.components.ModalBottomSheetLayout.ModalBottomSheetValue
import com.example.actors.ui.components.ModalBottomSheetLayout.SwipeableDefaults
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun modalBottomSheetState(
    initialValue: ModalBottomSheetValue = ModalBottomSheetValue.Hidden,
    skipHalfExpanded: Boolean = true,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    ): ModalBottomSheetState {
    return com.example.actors.ui.components.ModalBottomSheetLayout.rememberModalBottomSheetState(
        initialValue = initialValue,

        animationSpec = animationSpec,
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun manageBottomSheetState(
    modalSheetState: ModalBottomSheetState

) : () -> Job{
    val coroutineScope = rememberCoroutineScope()
    val hideOrShowModalBottomSheet = {
        coroutineScope.launch {
            when {
                modalSheetState.isVisible -> modalSheetState.hide()
                !modalSheetState.isVisible -> modalSheetState.show()
            }
        }
    }
    return hideOrShowModalBottomSheet
}
