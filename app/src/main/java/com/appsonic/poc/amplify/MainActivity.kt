package com.appsonic.poc.amplify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

        initAmplify(this)

        addRandomTodo()

        super.onCreate(savedInstanceState)
        setContent {
            POC_AMPLIFYTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun addRandomTodo() {


        for (i in 0..100) {
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