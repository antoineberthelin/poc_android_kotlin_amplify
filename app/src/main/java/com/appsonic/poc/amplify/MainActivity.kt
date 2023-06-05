package com.appsonic.poc.amplify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewModelScope
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.generated.model.Todo
import com.amplifyframework.kotlin.core.Amplify
import com.appsonic.poc.amplify.ui.theme.POC_AMPLIFYTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //initAmplify(this)

        //addRandomTodo()

        super.onCreate(savedInstanceState)
        setContent {
            POC_AMPLIFYTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {

                    Greeting("Android")

//                    EditableExposedDropdownMenuSample()

  //                      ExposedDropdownMenuSample()

    //                    Sample2()

                        docs()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ExposedDropdownMenuSample() {
        val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
        // We want to react on tap/press on TextField to show menu
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                label = { Text("Label") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        text = {Text(text = selectionOption)}
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EditableExposedDropdownMenuSample() {
        val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf("") }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedOptionText,
                onValueChange = { selectedOptionText = it },
                label = { Text("Label") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            // filter options based on text field value
            val filteringOptions =
                options.filter { it.contains(selectedOptionText, ignoreCase = true) }
            if (filteringOptions.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    filteringOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            modifier = Modifier,
                            text = {Text(text = selectionOption)}
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Sample2() {
        var expanded by remember { mutableStateOf(false) }
        Column() {
            Text(text = "Before")

            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "klqklklqs"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                DropdownMenuItem(
                    onClick = {
                        //navigator.navigate(Root.Draft.Compose(it.draftId))
                    },
                    text = { Text(text = "edit") }
                )
                DropdownMenuItem(
                    onClick = {
                        //      viewModel.delete(it)
                    },
                    text = {
                        Text(
                            text = "remove",
                            color = Color.Red,
                        )
                    }
                )
            }
        }
        Column() {
            Text(text = "After")
            Text(text = "After1")
            Text(text = "After2")
            Text(text = "After3")
            Text(text = "After4")
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun docs() {
        val options = listOf("Option 1", "comment", "Afghanistan", "Albania", "Algeria", "Andorra", "Egypt")
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf("") }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                // The `menuAnchor` modifier must be passed to the text field for correctness.
                modifier = Modifier.menuAnchor(),
                value = selectedOptionText,
                onValueChange = { selectedOptionText = it },
                label = { Text("Label") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    //focusedContainerColor = Color.White,
                    //unfocusedContainerColor = Color.White
                ),
            )
            // filter options based on text field value
            val filteringOptions = options.filter { it.contains(selectedOptionText, ignoreCase = true) }
            if (filteringOptions.isNotEmpty()) {
                DropdownMenu(
                    modifier = Modifier
                        .background(Color.White)
                        .exposedDropdownSize(true)
                    ,
                    properties = PopupProperties(focusable = false),
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    filteringOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }
        }
    }

    private fun addRandomTodo() {


        for (i in 0..20) {
            this.lifecycle.coroutineScope.launch(Dispatchers.IO) {
                try {
                    Log.i("DataStore", "TODO_START $i")
                    val todo = Todo.builder().name("The name").description("The description").build()
                    Amplify.DataStore.save(todo)
                    Amplify.DataStore.delete(todo)
                    Log.i("DataStore", "TODO_END $i")
                } catch (error: DataStoreException) {
                    Log.e("DataStoreException", "Saved new question failed.", error)
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {

        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    private fun initAmplify(mainActivity: MainActivity) {
        // Add the necessary plugins to initialize Amplify
        try {
            Log.i("MyAmplifyApp", "Initialized Amplify")

            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.addPlugin(AWSDataStorePlugin())

            Amplify.configure(mainActivity)

            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        POC_AMPLIFYTheme {
            Greeting("Android")
        }
    }


}