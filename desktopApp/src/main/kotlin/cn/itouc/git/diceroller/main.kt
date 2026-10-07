包 cn.itouc.git.diceroller

导入 androidx.compose.desktop.ui.tooling.preview.Preview
导入 androidx.compose.foundation.layout.*
导入 androidx.compose.foundation.rememberScrollState
导入 androidx.compose.foundation.verticalScroll
导入 androidx.compose.material.*
导入 androidx.compose.runtime.*
导入 androidx.compose.ui.Alignment
导入 androidx.compose.ui.Modifier
导入 androidx.compose.ui.unit.dp
导入 androidx.compose.ui.window.Window
导入 androidx.compose.ui.window.application
导入 androidx.compose.ui.window.rememberWindowState
导入 kotlin.random.Random

val diceTypes = arrayOf("D4", "D6", "D8", "D10", "D12", "D20")

@Composable
fun FeatureCard(title: String, content: @Composable () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            文本(text = title, style = MaterialTheme.typography.h6)
            空白Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
@Preview
fun App() {
    var selectedDice by remember { mutableStateOf("D6") }
    var result1 by remember { mutableStateOf("Ready!") }

    var diceCountText by remember { mutableStateOf("1") }
    var result2 by remember { mutableStateOf("Ready!") }

    var comboText by remember { mutableStateOf("") }
    var result3 by remember { mutableStateOf("Ready!") }

    var nText by remember { mutableStateOf("1") }
    var mText by remember { mutableStateOf("6") }
    var result4 by remember { mutableStateOf("Ready!") }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Diceroller", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(20.dp))

            FeatureCard("功能一：切换面数") {
                var expanded by remember { mutableStateOf(false) }
                Box {
                    OutlinedButton(onClick = { expanded = true }) { Text(text = selectedDice) }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        for (type in diceTypes) {
                            DropdownMenuItem(onClick = {
                                selectedDice = type
                                expanded = false
                            }) {
                                Text(text = type)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    val sides = selectedDice.substring(1).toInt()
                    val roll = Random.nextInt(1, sides + 1)
                    result1 = "Roll $selectedDice: $roll"
                }) { Text(text = "ROLL") }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = result1, style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = Modifier.height(16.dp))

            FeatureCard("功能二：n个六面骰求和") {
                OutlinedTextField(
                    value = diceCountText,
                    onValueChange = {
                        var result = ""
                        for (c in it) {
                            if (c >= '0' && c <= '9') {
                                result = result + c
                            }
                        }
                        diceCountText = result
                    },
                    modifier = Modifier.width(120.dp),
                    label = { Text("数量 n") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    val count = try {
                        diceCountText.toInt()
                    } catch (e: NumberFormatException) {
                        1
                    }
                    var total = 0
                    var i = 1
                    while (i <= count) {
                        total = total + Random.nextInt(1, 7)
                        i = i + 1
                    }
                    result2 = "${count}D6: $total"
                }) { Text(text = "ROLL") }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = result2, style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = Modifier.height(16.dp))

            FeatureCard("功能三：任意组合") {
                OutlinedTextField(
                    value = comboText,
                    onValueChange = { comboText = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("如 3D6 + 2D8") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    if (comboText.length > 0) {
                        result3 = "Total: ${calculateDiceRoll(comboText)}"
                    }
                }) { Text(text = "ROLL") }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = result3, style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = Modifier.height(16.dp))

            FeatureCard("功能四：掷 n 个 m 面骰") {
                Row {
                    OutlinedTextField(
                        value = nText,
                        onValueChange = {
                            var result = ""
                            for (c in it) {
                                if (c >= '0' && c <= '9') {
                                    result = result + c
                                }
                            }
                            nText = result
                        },
                        modifier = Modifier.width(100.dp),
                        label = { Text("个数 n") }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    OutlinedTextField(
                        value = mText,
                        onValueChange = {
                            var result = ""
                            for (c in it) {
                                if (c >= '0' && c <= '9') {
                                    result = result + c
                                }
                            }
                            mText = result
                        },
                        modifier = Modifier.width(100.dp),
                        label = { Text("面数 m") }
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    val n = try {
                        nText.toInt()
                    } catch (e: NumberFormatException) {
                        1
                    }
                    val m = try {
                        mText.toInt()
                    } catch (e: NumberFormatException) {
                        6
                    }
                    var total = 0
                    var i = 1
                    while (i <= n) {
                        total = total + Random.nextInt(1, m + 1)
                        i = i + 1
                    }
                    result4 = "${n}D${m}: $total"
                }) { Text(text = "ROLL") }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = result4, style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

fun calculateDiceRoll(input: String): Int {
    var total = 0
    val parts = input.split("+")
    for (part in parts) {
        val upperPart = part.uppercase()
        val dIndex = upperPart.indexOf("D")
        if (dIndex > 0) {
            val countStr = part.substring(0, dIndex).trim()
            val sidesStr = part.substring(dIndex + 1).trim()
            val count = try { countStr.toInt() } catch (e: NumberFormatException) { 0 }
            val sides = try { sidesStr.toInt() } catch (e: NumberFormatException) { 0 }
            if (count > 0 && sides > 0) {
                var i = 1
                while (i <= count) {
                    total = total + Random.nextInt(1, sides + 1)
                    i = i + 1
                }
            }
        }
    }
    return total
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Diceroller",
        state = rememberWindowState(width = 500.dp, height = 800.dp)
    ) {
        App()
    }
}
