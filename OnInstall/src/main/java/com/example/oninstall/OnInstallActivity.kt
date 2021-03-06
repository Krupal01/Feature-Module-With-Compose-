package com.example.oninstall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.dynamicfeaturedemo.dynamicdependencies.OnInstallModuleDependencies
import com.example.dynamicfeaturedemo.module.VMFactory
import com.example.dynamicfeaturedemo.viewmodel.MainViewModel
import com.example.oninstall.component.DaggerOnInstallComponent
import com.example.oninstall.component.OnInstallComponent
import com.example.oninstall.ui.theme.DynamicFeatureDemoTheme
import com.example.oninstall.viewmodel.OnInstallViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class OnInstallActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: OnInstallViewModel

    @Inject
    lateinit var vmFactory: VMFactory
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerOnInstallComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    OnInstallModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this,vmFactory).get(MainViewModel::class.java)

        setContent {
            val datalist = remember {
                viewModel.dataList
            }
            val count = remember {
                mainViewModel.count
            }
            DynamicFeatureDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("${datalist.toList()} total ${count.value}")
                }
            }
        }
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