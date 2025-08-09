package org.datanucleus.store.rdbms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.table.TableImpl;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ValidateTableSchemaTransaction extends AbstractSchemaTransaction {
   protected TableImpl table;

   public ValidateTableSchemaTransaction(RDBMSStoreManager rdbmsMgr, int isolationLevel, TableImpl table) {
      super(rdbmsMgr, isolationLevel);
      this.table = table;
   }

   protected void run(ClassLoaderResolver clr) throws SQLException {
      synchronized(this.rdbmsMgr) {
         List autoCreateErrors = new ArrayList();

         try {
            this.table.validate(this.getCurrentConnection(), false, true, autoCreateErrors);
         } catch (Exception e) {
            NucleusLogger.DATASTORE_SCHEMA.error("Exception thrown during update of schema for table " + this.table, e);
            throw new NucleusException("Exception thrown during update of schema for table " + this.table, e);
         }

      }
   }

   public String toString() {
      return Localiser.msg("050048", new Object[]{this.table, this.rdbmsMgr.getCatalogName(), this.rdbmsMgr.getSchemaName()});
   }
}
