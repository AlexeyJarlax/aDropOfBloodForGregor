package com.pavlovalexey.adropofbloodforgregor.ui.theme.bloodCustoms

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text2
import com.pavlovalexey.adropofbloodforgregor.ui.theme.text1NotActive

@Composable
fun CustomButtonOne(
    onClick: () -> Unit,
    text: String? = null,
    icon: Any? = null,
    modifier: Modifier = Modifier,
    textColor: Color = text2,
    iconColor: Color = text2,
    enabled: Boolean = true,
    fontSize: TextUnit = 16.sp,
    iconPadding: Int = 0,
    pressedColor: Color = text1NotActive,
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (enabled) Color.Transparent else pressedColor,
        label = ""
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = textColor,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        ),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(2.dp),
        modifier = modifier.wrapContentWidth()
    ) {
        if (icon != null) {
            when (icon) {
                is ImageVector -> Icon(
                    modifier = Modifier.padding(bottom = iconPadding.dp),
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (enabled) iconColor else Color.Gray
                )

                is Int -> Icon(
                    modifier = Modifier.padding(bottom = iconPadding.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = if (enabled) iconColor else Color.Gray
                )
            }
        }
        if (text != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = fontSize,
                color = if (enabled) textColor else Color.Gray
            )
        }
    }
}

/** Пример использования:

Spacer(modifier = Modifier.height(8.dp))

var password by remember { mutableStateOf("") }

CustomButtonOne(
onClick = { viewModel.onPasswordEntered(password) },
text = stringResource(R.string.enter),
textColor = if (password.isEmpty()) My5 else My7,
iconColor = if (password.isEmpty()) My5 else My7,
icon = R.drawable.login_30dp или Icons.Default.InsertPhoto
)

или
CustomButtonOne(
onClick = { },
text = if (isPreviewVisible)
context.getString(R.string.cam_bat_hide)
else
context.getString(R.string.cam_bat_start),
icon = Icons.Default.InsertPhoto
)

не желательно передавать modifier
 */
