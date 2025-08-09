package org.apache.derby.impl.store.replication;

import java.util.Date;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.shared.common.error.ErrorStringBuilder;

public class ReplicationLogger {
   private final boolean verbose = PropertyUtil.getSystemBoolean("derby.replication.verbose", true);
   private final String dbname;

   public ReplicationLogger(String var1) {
      this.dbname = var1;
   }

   public void logError(String var1, Throwable var2) {
      if (this.verbose) {
         Monitor.logTextMessage("R001", new Date());
         if (var1 != null) {
            Monitor.logTextMessage(var1, this.dbname);
         }

         if (var2 != null) {
            ErrorStringBuilder var3 = new ErrorStringBuilder(Monitor.getStream().getHeader());
            var3.stackTrace(var2);
            Monitor.logMessage(var3.get().toString());
            var3.reset();
         }

         Monitor.logTextMessage("R002");
      }

   }

   public void logText(String var1, boolean var2) {
      if (this.verbose) {
         if (var2) {
            Monitor.logTextMessage("R001", new Date());
            Monitor.logMessage(var1);
            Monitor.logTextMessage("R002");
         } else {
            Monitor.logTextMessage("R013", new Date(), var1);
         }
      }

   }
}
