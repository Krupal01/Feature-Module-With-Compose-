package com.example.ondemand

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.UserHandle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dynamicfeaturedemo.dynamicdependencies.OnDemandModuleDependencies
import com.example.ondemand.component.DaggerOnDemandComponent
import com.example.ondemand.ui.theme.DynamicFeatureDemoTheme
import com.example.ondemand.viewmodel.OnDemandViewModel
import com.google.android.play.core.splitcompat.SplitCompat
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class OnDemandActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: OnDemandViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerOnDemandComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    OnDemandModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            val datalist = remember {
                viewModel.dataList
            }
            DynamicFeatureDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(datalist.toList().toString())
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DynamicFeatureDemoTheme {
        Greeting("Android")
    }
}