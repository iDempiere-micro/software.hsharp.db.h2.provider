package software.hsharp.db.h2.provider

import org.h2.jdbcx.JdbcDataSource
import org.osgi.service.component.annotations.Component
import software.hsharp.api.icommon.ICConnection
import software.hsharp.api.icommon.IDatabase
import software.hsharp.api.icommon.IDatabaseSetup
import java.sql.Connection
import java.sql.Driver
import java.sql.DriverManager
import javax.naming.InitialContext
import javax.sql.DataSource

@Component
open class H2DB : IDatabase {
    override fun fubar() {
        // left intentionally blank for H2
    }

    /** Connection Timeout in seconds   */
    protected val CONNECTION_TIMEOUT = 10

    /** Driver                  */
    private val driverObj: org.h2.Driver = registerIfNeeded(org.h2.Driver())
    private var driverRegistered: Boolean = false

    private fun registerIfNeeded(driverInst: org.h2.Driver): org.h2.Driver {
        if (!driverRegistered) {
            DriverManager.registerDriver(driverInst)
            DriverManager.setLoginTimeout(CONNECTION_TIMEOUT)
            driverRegistered = true
        }
        return driverInst
    }

    override val status: String
        get() = ""
    override val driver: Driver
        get() = driverObj

    override val defaultSetupParameters: IDatabaseSetup
        get() = H2DatabaseSetup("jdbc:h2:mem:idempiere;DB_CLOSE_DELAY=-1", "", "")

    override fun setup(parameters: IDatabaseSetup) {
    }

    private var cnnString: String? = null

    override fun connect(connection: ICConnection): DataSource? {
        val ds = JdbcDataSource()
        ds.setURL(getConnectionURL(connection))
        InitialContext()

        return ds
    }

    /**
     *  Get Database Connection String.
     *  Requirements:
     *      - createdb -E UNICODE compiere
     *  @param connection Connection Descriptor
     *  @return connection String
     */
    open fun getConnectionURL(connection: ICConnection): String
    {
        dbName = connection.dbName
        //  jdbc:h2:file:<filepath>

        return "jdbc:h2:file:$dbName"
    }

    var dbName = ""

    /**
     * Close
     */
    open fun close() {

        try {
            // dataSource.close()
        } catch (e: Exception) {
        }
    } // 	close

    override fun cleanup(connection: Connection) {
    }
}
