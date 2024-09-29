package com.norm.mycustomswipereveal

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ContactsScreen() {
    val context = LocalContext.current
    val contacts = remember {
        mutableStateListOf(
            *(1..100).map {
                ContactUI(
                    id = it,
                    name = "Contact $it",
                    isOptionsRevealed = false,
                )
            }.toTypedArray()
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(
            items = contacts,
//            key = { index, contact ->
//                contact.id
//            }
        ) { index, contact ->
            SwipeableItemWithActions(
                isRevealed = contact.isOptionsRevealed,
                actions = {
                    ActionIcon(
                        onClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            Toast.makeText(
                                context,
                                "Contact ${contact.id} was shared.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                        backgroundColor = Color.Blue,
                        icon = Icons.Default.Share,
                    )
                    ActionIcon(
                        onClick = {
                            contacts[index] = contact.copy(isOptionsRevealed = false)
                            Toast.makeText(
                                context,
                                "Contact ${contact.id} was sent an email.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                        backgroundColor = Color.Gray,
                        icon = Icons.Default.Email,
                    )
                    ActionIcon(
                        onClick = {
                            contacts.remove(contact)
                            Toast.makeText(
                                context,
                                "Contact ${contact.id} was deleted.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        },
                        backgroundColor = Color.Red,
                        icon = Icons.Default.Delete,
                    )
                },
                onExpanded = {
                    contacts[index] = contact.copy(isOptionsRevealed = true)
                },
                onCollapsed = {
                    contacts[index] = contact.copy(isOptionsRevealed = false)
                }
            ) {
                Text(
                    text = "Contact ${contact.id}",
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}