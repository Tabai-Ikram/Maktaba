package com.ElOuedUniv.maktaba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ElOuedUniv.maktaba.data.local.OnboardingDataStore
import com.ElOuedUniv.maktaba.presentation.navigation.NavGraph
import com.ElOuedUniv.maktaba.presentation.navigation.Screen
import com.ElOuedUniv.maktaba.presentation.theme.MaktabaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var onboardingDataStore: OnboardingDataStore
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            // مراقبة حالة الـ Onboarding من الـ DataStore
            val isCompleted by onboardingDataStore.isOnboardingCompleted.collectAsState(initial = null)

            MaktabaTheme {
                // ننتظر حتى يتم تحميل القيمة من الـ DataStore (لتجنب الوميض)
                if (isCompleted != null) {
                    val startDestination = if (isCompleted == true) {
                        Screen.BookList.route
                    } else {
                        Screen.Onboarding.route
                    }
                    
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
