package software.hsharp.db.h2.provider

import software.hsharp.api.icommon.IDatabaseSetup

data class H2DatabaseSetup(
    val url: String,
    val userName: String,
    val password: String,
    override val connectionTimeout: Long = 0,
    override val validationTimeout: Long = 0,
    override val idleTimeout: Long = 0,
    override val leakDetectionThreshold: Long = 0,
    override val maxLifetime: Long = 0,
    override val maximumPoolSize: Int = 0,
    override val minimumIdle: Int = 0,
    override val cachePrepStmts: Boolean = false,
    override val prepStmtCacheSize: Int = 0,
    override val prepStmtCacheSqlLimit: Int = 0
) : IDatabaseSetup