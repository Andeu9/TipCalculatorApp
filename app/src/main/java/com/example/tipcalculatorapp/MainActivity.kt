package com.example.tipcalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}

@Composable
fun Screen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F5FB)),
        contentAlignment = Alignment.Center
    ) {
        TipCalculatorCard()
    }
}

@Composable
fun TipCalculatorCard() {
    val bill = 2000

    var selectedPercent by remember { mutableIntStateOf(10) }

    val tip = bill * selectedPercent / 100
    val total = bill + tip

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Счёт в ресторане",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Text(
                text = formatRub(bill),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PercentButton(
                    percent = 5,
                    isSelected = selectedPercent == 5,
                    onClick = { selectedPercent = 5 },
                    modifier = Modifier.weight(1f)
                )
                PercentButton(
                    percent = 10,
                    isSelected = selectedPercent == 10,
                    onClick = { selectedPercent = 10 },
                    modifier = Modifier.weight(1f)
                )
                PercentButton(
                    percent = 15,
                    isSelected = selectedPercent == 15,
                    onClick = { selectedPercent = 15 },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Чаевые:",
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = formatRub(tip),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Итого:",
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = formatRub(total),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun PercentButton(
    percent: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(

            containerColor = if (isSelected) Color(0xFF2979FF) else Color(0xFFE8ECF4),
            contentColor = if (isSelected) Color.White else Color.Black
        )
    ) {
        Text(text = "$percent%", fontSize = 16.sp)
    }
}

fun formatRub(amount: Int): String {
    val withSpaces = amount.toString().reversed().chunked(3).joinToString(" ").reversed()
    return "$withSpaces ₽"
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    Screen()
}