/*
 * Copyright (C) 2024 OpenAni and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license, which can be found at the following link.
 *
 * https://github.com/open-ani/ani/blob/main/LICENSE
 */

package me.him188.ani.app.ui.foundation.layout

import androidx.compose.material3.adaptive.Posture
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass


// Workaround for CMP bug #1144
/**
 * Calculates and returns [WindowAdaptiveInfo] of the provided context. It's a convenient function
 * that uses the default [WindowSizeClass] constructor and the default [Posture] calculation
 * functions to retrieve [WindowSizeClass] and [Posture].
 *
 * @return [WindowAdaptiveInfo] of the provided context
 */
@Composable
expect fun currentWindowAdaptiveInfo1(): WindowAdaptiveInfo 
