package org.apache.derby.impl.db;

import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.db.DatabaseContext;
import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

final class DatabaseContextImpl extends ContextImpl implements DatabaseContext {
   private final Database db;

   DatabaseContextImpl(ContextManager var1, Database var2) {
      super(var1, "Database");
      this.db = var2;
   }

   public void cleanupOnError(Throwable var1) {
      if (var1 instanceof StandardException var2) {
         if (var2.getSeverity() >= 40000) {
            this.popMe();
            if (var2.getSeverity() >= 45000) {
               DataDictionary var3 = this.db.getDataDictionary();
               if (var3 != null) {
                  var3.disableIndexStatsRefresher();
               }
            }

            if (var2.getSeverity() == 45000) {
               getContextService().notifyAllActiveThreads(this);
               getMonitor().shutdown(this.db);
            }

         }
      }
   }

   public boolean equals(Object var1) {
      if (var1 instanceof DatabaseContext) {
         return ((DatabaseContextImpl)var1).db == this.db;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.db.hashCode();
   }

   public Database getDatabase() {
      return this.db;
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
