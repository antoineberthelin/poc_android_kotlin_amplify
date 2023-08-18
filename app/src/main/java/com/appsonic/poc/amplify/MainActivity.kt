package com.appsonic.poc.amplify

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.coroutineScope
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.api.aws.ApiAuthProviders
import com.amplifyframework.api.aws.AuthModeStrategyType
import com.amplifyframework.api.aws.sigv4.DefaultCognitoUserPoolsAuthProvider
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.DataStoreConfiguration
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.generated.model.User
import com.amplifyframework.kotlin.core.Amplify
import com.appsonic.poc.amplify.ui.theme.POC_AMPLIFYTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        initAmplify(this)

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

                        Button(onClick = {
                            refreshdatastore()
                        }) {
                            Text(text = "Refresh data store")
                        }
                    }
                }
            }
        }
    }

    private fun refreshdatastore() {
        this.lifecycle.coroutineScope.launch(Dispatchers.IO) {
            Amplify.DataStore.stop()
            Amplify.DataStore.start()
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

            Amplify.addPlugin(AWSDataStorePlugin.builder().dataStoreConfiguration(
                DataStoreConfiguration.builder()
                    .syncExpression(User::class.java) { User.LAST_NAME.eq("Doe") }
                    .build())
                .build())

            Amplify.addPlugin(AWSApiPlugin())
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