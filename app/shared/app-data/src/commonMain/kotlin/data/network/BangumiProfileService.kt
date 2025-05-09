/*
 * Copyright (C) 2024-2025 OpenAni and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license, which can be found at the following link.
 *
 * https://github.com/open-ani/ani/blob/main/LICENSE
 */

package me.him188.ani.app.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.him188.ani.app.data.models.UserInfo
import me.him188.ani.app.data.repository.RepositoryAuthorizationException
import me.him188.ani.app.data.repository.RepositoryException
import me.him188.ani.datasources.bangumi.BangumiClient
import me.him188.ani.datasources.bangumi.models.BangumiUser
import me.him188.ani.utils.coroutines.IO_
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

interface BangumiProfileService {
    @Throws(RepositoryException::class, CancellationException::class)
    suspend fun getSelfUserInfo(accessToken: String?): UserInfo
}

fun BangumiProfileService(): BangumiProfileService {
    return BangumiProfileServiceImpl()
}

internal class BangumiProfileServiceImpl(
    private val ioDispatcher: CoroutineContext = Dispatchers.IO_,
) : BangumiProfileService, KoinComponent {
    private val client: BangumiClient by inject()

    override suspend fun getSelfUserInfo(accessToken: String?): UserInfo {
        return withContext(ioDispatcher) {
            client.getSelfInfoByToken(accessToken)?.toUserInfo() ?: throw RepositoryAuthorizationException()
        }
    }
}

private fun BangumiUser.toUserInfo() = UserInfo(
    id = id,
    username = username,
    nickname = nickname,
    avatarUrl = avatar.medium,
    sign = sign,
)