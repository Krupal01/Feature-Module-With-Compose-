package com.example.dynamicfeaturedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dynamicfeaturedemo.ui.theme.DynamicFeatureDemoTheme
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splitInstallManager : SplitInstallManager = SplitInstallManagerFactory.create(this)
        var sessionId : Int = 0
        setContent {
            DynamicFeatureDemoTheme {
                // A surface container using the 'background' color from the theme
                var progressBarVisibility by remember {
                    mutableStateOf(false)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        
                        Button(onClick = { 
                            val intent = Intent().also {
                                it.setClassName(this@MainActivity,"com.example.oninstall.OnInstallActivity")
                            }
                            startActivity(intent)
                        }) {
                            Text(text = "use on install dynamic feature")
                        }
                        
                        Button(onClick = {
                            val splitInstallRequest = SplitInstallRequest.newBuilder().addModule("OnDemand").build()

                            val listener = SplitInstallStateUpdatedListener { state: SplitInstallSessionState ->
                                    if (state.sessionId() == sessionId) {
                                        when (state.status()) {
                                            SplitInstallSessionStatus.DOWNLOADING -> {
                                                progressBarVisibility = true
                                            }
                                            SplitInstallSessionStatus.INSTALLED -> {
                                                progressBarVisibility = false
                                                val intent = Intent().also {
                                                    it.setClassName(this@MainActivity,"com.example.ondemand.OnDemandActivity")
                                                }
                                                startActivity(intent)
                                            }
                                            SplitInstallSessionStatus.CANCELED -> {
                                                Log.i("listener","CANCELLED")
                                            }
                                            SplitInstallSessionStatus.CANCELING -> {
                                                progressBarVisibility = true
                                            }
                                            SplitInstallSessionStatus.DOWNLOADED -> {
                                                Log.i("listener","DOWNLOADED")
                                            }
                                            SplitInstallSessionStatus.FAILED -> {
                                                Log.i("listener","FAILED")
                                            }
                                            SplitInstallSessionStatus.INSTALLING -> {
                                                progressBarVisibility = true
                                            }
                                            SplitInstallSessionStatus.PENDING -> {
                                                Log.i("listener","PENDING")
                                            }
                                            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                                                Log.i("listener","REQUIRES_USER_CONFIRMATION")
                                            }
                                            SplitInstallSessionStatus.UNKNOWN -> {
                                                Log.i("listener","UNKNOWN")
                                            }
                                        }
                                    }
                                }
                            splitInstallManager.registerListener(listener)
                            splitInstallManager.startInstall(splitInstallRequest)
                                .addOnSuccessListener {
                                    sessionId = it
                                }
                                .addOnFailureListener {
                                    Log.i("Dynamic Feature Fail" , "Fail : $it")
                                }
                        }) {
                            Text(text = "use on demand dynamic feature")
                        }

                        AnimatedVisibility(visible = progressBarVisibility) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
