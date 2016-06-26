package ademar.goodreads.core.initializer

import android.content.Context

import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowLog
import com.raizlabs.android.dbflow.config.FlowManager

class DatabaseInitializer : Initializer.SynchronousInitializer() {

    override fun start(context: Context) {
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V)
        val flowConfig = FlowConfig.Builder(context)
                .openDatabasesOnInit(true)
                .build()
        FlowManager.init(flowConfig)
    }

}
