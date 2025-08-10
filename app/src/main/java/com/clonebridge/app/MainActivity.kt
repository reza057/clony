package com.clonebridge.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val nav = rememberNavController()
                NavHost(nav, startDestination = "home") {
                    composable("home") { HomeScreen(onSend = { nav.navigate("send") }, onReceive = { nav.navigate("receive") }) }
                    composable("send") { SendScreen(onBack = { nav.popBackStack() }) }
                    composable("receive") { ReceiveScreen(onBack = { nav.popBackStack() }) }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onSend: () -> Unit, onReceive: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("CloneBridge", style = MaterialTheme.typography.h4)
            Spacer(Modifier.height(24.dp))
            Button(onClick = onSend, modifier = Modifier.fillMaxWidth(0.7f)) { Text("فرستادن (دستگاه قدیم)") }
            Spacer(Modifier.height(12.dp))
            OutlinedButton(onClick = onReceive, modifier = Modifier.fillMaxWidth(0.7f)) { Text("دریافت (دستگاه جدید)") }
        }
    }
}

@Composable
fun SendScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(title = { Text("ارسال — انتخاب آیتم‌ها") }, navigationIcon = {
            IconButton(onClick = onBack) { Text("<") }
        })
        Spacer(Modifier.height(16.dp))
        Text("این نسخه یک اسکلت MVP است. برای ساخت و اجرای کامل به keystore و تنظیمات بیشتر نیاز است.")
    }
}

@Composable
fun ReceiveScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        TopAppBar(title = { Text("دریافت — اسکن QR") }, navigationIcon = {
            IconButton(onClick = onBack) { Text("<") }
        })
        Spacer(Modifier.height(16.dp))
        Text("برای اسکن QR و دانلود manifest نیاز به افزونه‌هایی مثل ML Kit یا ZXing است.")
    }
}
