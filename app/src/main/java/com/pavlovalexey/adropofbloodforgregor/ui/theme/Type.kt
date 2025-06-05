package com.pavlovalexey.adropofbloodforgregor.ui.theme

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val DisplayLarge_57_Light = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Light,
    fontSize = 57.sp,
    lineHeight = 64.sp,
    letterSpacing = (-0.25).sp
)

val DisplayMedium_45_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 45.sp,
    lineHeight = 52.sp,
    letterSpacing = 0.sp
)

val DisplaySmall_36_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 36.sp,
    lineHeight = 44.sp,
    letterSpacing = 0.sp
)

val HeadlineLarge_32_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    letterSpacing = 0.sp
)

val HeadlineMedium_28_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 28.sp,
    lineHeight = 36.sp,
    letterSpacing = 0.sp
)

val HeadlineSmall_24_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
)

val TitleLarge_22_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)

val TitleMedium_16_Medium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.15.sp
)

val TitleSmall_14_Medium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp
)

val BodyLarge_16_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)

val BodyMedium_14_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.25.sp
)

val BodySmall_12_Regular = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.4.sp
)

val LabelLarge_14_Medium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp
)

val LabelMedium_12_Medium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)

val LabelSmall_11_Medium = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)


val AppTypography = Typography(
    displayLarge   = DisplayLarge_57_Light,
    displayMedium  = DisplayMedium_45_Regular,
    displaySmall   = DisplaySmall_36_Regular,

    headlineLarge  = HeadlineLarge_32_Regular,
    headlineMedium = HeadlineMedium_28_Regular,
    headlineSmall  = HeadlineSmall_24_Regular,

    titleLarge     = TitleLarge_22_Regular,
    titleMedium    = TitleMedium_16_Medium,
    titleSmall     = TitleSmall_14_Medium,

    bodyLarge      = BodyLarge_16_Regular,
    bodyMedium     = BodyMedium_14_Regular,
    bodySmall      = BodySmall_12_Regular,

    labelLarge     = LabelLarge_14_Medium,
    labelMedium    = LabelMedium_12_Medium,
    labelSmall     = LabelSmall_11_Medium
)


val Typography.DisplayLarge_57_Light: TextStyle
    get() = displayLarge

val Typography.DisplayMedium_45_Regular: TextStyle
    get() = displayMedium

val Typography.DisplaySmall_36_Regular: TextStyle
    get() = displaySmall

val Typography.HeadlineLarge_32_Regular: TextStyle
    get() = headlineLarge

val Typography.HeadlineMedium_28_Regular: TextStyle
    get() = headlineMedium

val Typography.HeadlineSmall_24_Regular: TextStyle
    get() = headlineSmall

val Typography.TitleLarge_22_Regular: TextStyle
    get() = titleLarge

val Typography.TitleMedium_16_Medium: TextStyle
    get() = titleMedium

val Typography.TitleSmall_14_Medium: TextStyle
    get() = titleSmall

val Typography.BodyLarge_16_Regular: TextStyle
    get() = bodyLarge

val Typography.BodyMedium_14_Regular: TextStyle
    get() = bodyMedium

val Typography.BodySmall_12_Regular: TextStyle
    get() = bodySmall

val Typography.LabelLarge_14_Medium: TextStyle
    get() = labelLarge

val Typography.LabelMedium_12_Medium: TextStyle
    get() = labelMedium

val Typography.LabelSmall_11_Medium: TextStyle
    get() = labelSmall
