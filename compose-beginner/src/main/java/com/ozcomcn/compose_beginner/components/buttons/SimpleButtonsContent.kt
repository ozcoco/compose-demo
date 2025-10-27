package com.ozcomcn.compose_beginner.components.buttons

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Snooze
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.animateFloatingActionButton
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

enum class SimpleButtonsContent(
    val title: String, val content: @Composable () -> Unit
) {
    Filled(
        title = "Filled", content = { FilledButtonsContent() }),
    Tonal(
        title = "Tonal", content = { TonalButtonsContent() }),
    Outlined(
        title = "Outlined", content = { OutlinedButtonsContent() }),
    Elevated(
        title = "Elevated", content = { ElevatedButtonsContent() }),
    TextButton(
        title = "TextButton", content = { TextButtonContent() }),
    FAB(
        title = "FAB", content = { FABContent() }),
    SmallFAB(
        title = "SmallFAB", content = { SmallFABContent() }),
    LargeFAB(
        title = "LargeFAB", content = { LargeFABContent() }),
    ExtendedFAB(
        title = "ExtendedFAB", content = { ExtendedFABContent() }),

    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    FABMenu(
        title = "FABMenu", content = {
            Box(modifier = Modifier.fillMaxSize()) {
                FABMenuContent()
                FABMenuContent2()
            }
        }),

    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    IconButton(
        title = "IconButton", content = {
            IconButtonContent()
        }),

    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    SegmentedButton(
        title = "IconButton", content = {
            SegmentedButtonContent()
        }),

    @OptIn(ExperimentalMaterial3ExpressiveApi::class)
    SplitButton(
        title = "IconButton", content = {
            SplitButtonContent()
        }),
}


@Composable
fun FilledButtonsContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.align(Alignment.Center), onClick = { }) {
            Text(text = "Filled")
        }
    }
}

@Composable
fun TonalButtonsContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        FilledTonalButton(
            modifier = Modifier.align(Alignment.Center), onClick = { }) {
            Text(text = "Tonal")
        }
    }
}

@Composable
fun OutlinedButtonsContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        OutlinedButton(
            modifier = Modifier.align(Alignment.Center), onClick = { }) {
            Text(text = "Outlined")
        }
    }
}

@Composable
fun ElevatedButtonsContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        ElevatedButton(
            modifier = Modifier.align(Alignment.Center), onClick = { }) {
            Text(text = "Elevated")
        }
    }
}

@Composable
fun TextButtonContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        TextButton(
            modifier = Modifier.align(Alignment.Center), onClick = { }) {
            Text(text = "Text Button")
        }
    }
}

@Composable
fun FABContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier.align(Alignment.Center), onClick = { }) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}

@Composable
fun SmallFABContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        SmallFloatingActionButton(
            modifier = Modifier.align(Alignment.Center), onClick = { }) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }
    }
}

@Composable
fun LargeFABContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        LargeFloatingActionButton(
            modifier = Modifier.align(Alignment.Center), onClick = { }) {
            Icon(Icons.Filled.Add, "Large floating action button")
        }
    }
}

@Composable
fun ExtendedFABContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        ExtendedFloatingActionButton(
            modifier = Modifier.align(Alignment.Center),
            onClick = { },
            icon = { Icon(Icons.Filled.Add, "Extended floating action button") },
            text = { Text(text = "Extended FAB") })
    }
}


@ExperimentalMaterial3ExpressiveApi
@Composable
fun FABMenuContent2() {
    val constraints = ConstraintSet {
        val fabMenu = createRefFor("fab_menu")
        val fabMenuItems = createRefFor("fab_menu_items")
        constrain(fabMenu) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
        }
        constrain(fabMenuItems) {
            bottom.linkTo(fabMenu.top, margin = 8.dp)
            end.linkTo(fabMenu.end)
        }
    }
    var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }
    BackHandler(fabMenuExpanded) { fabMenuExpanded = false }
    val items = listOf(
        Icons.AutoMirrored.Filled.Message to "Reply",
        Icons.Filled.People to "Reply all",
        Icons.Filled.Contacts to "Forward",
        Icons.Filled.Snooze to "Snooze",
        Icons.Filled.Archive to "Archive",
        Icons.AutoMirrored.Filled.Label to "Label",
    )
    ConstraintLayout(
        constraintSet = constraints, modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            modifier = Modifier.layoutId("fab_menu"),
            onClick = {
                fabMenuExpanded = !fabMenuExpanded
            },
            shape = if (fabMenuExpanded) CircleShape else FloatingActionButtonDefaults.shape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            Icon(
                if (fabMenuExpanded) Icons.Filled.Close else Icons.Filled.Add,
                contentDescription = null,
            )
        }
        Column(
            modifier = Modifier
                .layoutId("fab_menu_items")
                .wrapContentSize(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items.forEach { (icon, label) ->
                AnimatedVisibility(
                    visible = fabMenuExpanded,
                    enter = expandIn(
                        expandFrom = Alignment.BottomEnd,
                        animationSpec = tween(180, easing = LinearOutSlowInEasing)
                    ),
                    exit = shrinkOut(
                        shrinkTowards = Alignment.BottomEnd,
                        animationSpec = tween(100, easing = FastOutSlowInEasing)
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    ExtendedFloatingActionButton(
                        onClick = { },
                        shape = CircleShape,
                        elevation = FloatingActionButtonDefaults.loweredElevation(
                            0.dp,
                            0.dp,
                            0.dp,
                            0.dp
                        ),
                        icon = { Icon(icon, label) },
                        text = { Text(text = label) })
                }
            }
        }


    }
}


//@RememberInComposition
@ExperimentalMaterial3ExpressiveApi
@Composable
fun FABMenuContent() {
    val listState = rememberLazyListState()
    val fabVisible by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }
    val focusRequester = remember { FocusRequester() }

    Box {
//        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
//            for (index in 0 until 100) {
//                item {
//                    Text(
//                        text = "List item - $index",
//                        modifier = Modifier
//                            .clickable {}
//                            .fillMaxWidth()
//                            .padding(24.dp),
//                    )
//                }
//            }
//        }

        val items =
            listOf(
                Icons.AutoMirrored.Filled.Message to "Reply",
                Icons.Filled.People to "Reply all",
                Icons.Filled.Contacts to "Forward",
                Icons.Filled.Snooze to "Snooze",
                Icons.Filled.Archive to "Archive",
                Icons.AutoMirrored.Filled.Label to "Label",
            )

        var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

        BackHandler(fabMenuExpanded) { fabMenuExpanded = false }

        FloatingActionButtonMenu(
            modifier = Modifier.align(Alignment.BottomStart),
            expanded = fabMenuExpanded,
            button = {
                ToggleFloatingActionButton(
                    modifier =
                        Modifier
                            .semantics {
                                traversalIndex = -1f
                                stateDescription = if (fabMenuExpanded) "Expanded" else "Collapsed"
                                contentDescription = "Toggle menu"
                            }
                            .animateFloatingActionButton(
                                visible = true,
                                alignment = Alignment.BottomEnd,
                            )
                            .focusRequester(focusRequester),
                    checked = fabMenuExpanded,
                    onCheckedChange = { fabMenuExpanded = !fabMenuExpanded },
                ) {
                    val imageVector by remember {
                        derivedStateOf {
                            if (checkedProgress > 0.5f) Icons.Filled.Close else Icons.Filled.Add
                        }
                    }
                    Icon(
                        painter = rememberVectorPainter(imageVector),
                        contentDescription = null,
                        modifier = Modifier.animateIcon({ checkedProgress }),
                    )
                }
            },
        ) {
            items.forEachIndexed { i, item ->
                FloatingActionButtonMenuItem(
                    modifier =
                        Modifier
                            .semantics {
                                isTraversalGroup = true
                                // Add a custom a11y action to allow closing the menu when focusing
                                // the last menu item, since the close button comes before the first
                                // menu item in the traversal order.
                                if (i == items.size - 1) {
                                    customActions =
                                        listOf(
                                            CustomAccessibilityAction(
                                                label = "Close menu",
                                                action = {
                                                    fabMenuExpanded = false
                                                    true
                                                },
                                            )
                                        )
                                }
                            },
                    onClick = { fabMenuExpanded = false },
                    icon = { Icon(item.first, contentDescription = null) },
                    text = { Text(text = item.second) },
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3ExpressiveApi
@Composable
fun IconButtonContent() {
    val constraintSet = ConstraintSet {
        val iconButton = createRefFor("btn")
        constrain(iconButton) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
    }
    ConstraintLayout(
        constraintSet,
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { /* Do something */ },
            modifier = Modifier.layoutId("btn"),
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3ExpressiveApi
@Composable
fun SegmentedButtonContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Single choice
        var selectedIndex by remember { mutableIntStateOf(0) }
        val options = listOf("Day", "Month", "Week")
        SingleChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options.size
                    ),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex,
                    label = { Text(label) }
                )
            }
        }
        // Multi choice
        val selectedOptions = remember {
            mutableStateListOf(false, false, false)
        }
        val options2 = listOf("Walk", "Ride", "Drive")
        MultiChoiceSegmentedButtonRow {
            options2.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = options2.size
                    ),
                    checked = selectedOptions[index],
                    onCheckedChange = {
                        selectedOptions[index] = !selectedOptions[index]
                    },
                    icon = { SegmentedButtonDefaults.Icon(selectedOptions[index]) },
                    label = {
                        when (label) {
                            "Walk" -> Icon(
                                imageVector =
                                    Icons.AutoMirrored.Filled.DirectionsWalk,
                                contentDescription = "Directions Walk"
                            )

                            "Ride" -> Icon(
                                imageVector =
                                    Icons.Default.DirectionsBus,
                                contentDescription = "Directions Bus"
                            )

                            "Drive" -> Icon(
                                imageVector =
                                    Icons.Default.DirectionsCar,
                                contentDescription = "Directions Car"
                            )
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterial3ExpressiveApi
@Composable
fun SplitButtonContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var checked by remember { mutableStateOf(false) }
        SplitButtonLayout(
            leadingButton = {
                SplitButtonDefaults.LeadingButton(onClick = { /* Do Nothing */ }) {
                    Icon(
                        Icons.Filled.Edit,
                        modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                        contentDescription = "Localized description",
                    )
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("My Button")
                }
            },
            trailingButton = {
                val description = "Toggle Button"
                // Icon-only trailing button should have a tooltip for a11y.
                TooltipBox(
                    positionProvider =
                        TooltipDefaults.rememberTooltipPositionProvider(TooltipAnchorPosition.Above),
                    tooltip = { PlainTooltip { Text(description) } },
                    state = rememberTooltipState(),
                ) {
                    SplitButtonDefaults.TrailingButton(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        modifier =
                            Modifier.semantics {
                                stateDescription = if (checked) "Expanded" else "Collapsed"
                                contentDescription = description
                            },
                    ) {
                        val rotation: Float by
                        animateFloatAsState(
                            targetValue = if (checked) 180f else 0f,
                            label = "Trailing Icon Rotation",
                        )
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            modifier =
                                Modifier
                                    .size(SplitButtonDefaults.TrailingIconSize)
                                    .graphicsLayer {
                                        this.rotationZ = rotation
                                    },
                            contentDescription = "Localized description",
                        )
                    }
                }
            },
        )
    }
}