package org.datanucleus.store.rdbms;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.StoreDataManager;
import org.datanucleus.store.rdbms.schema.RDBMSSchemaHandler;
import org.datanucleus.store.rdbms.table.ClassTable;
import org.datanucleus.store.rdbms.table.ClassView;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.TableImpl;
import org.datanucleus.store.rdbms.table.ViewImpl;
import org.datanucleus.store.schema.StoreSchemaData;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class DeleteTablesSchemaTransaction extends AbstractSchemaTransaction {
   StoreDataManager storeDataMgr = null;
   Writer writer;

   public DeleteTablesSchemaTransaction(RDBMSStoreManager rdbmsMgr, int isolationLevel, StoreDataManager dataMgr) {
      super(rdbmsMgr, isolationLevel);
      this.storeDataMgr = dataMgr;
   }

   public void setWriter(Writer writer) {
      this.writer = writer;
   }

   protected void run(ClassLoaderResolver clr) throws SQLException {
      synchronized(this.rdbmsMgr) {
         boolean success = true;

         try {
            NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050045", new Object[]{this.rdbmsMgr.getCatalogName(), this.rdbmsMgr.getSchemaName()}));
            Map baseTablesByName = new HashMap();
            Map viewsByName = new HashMap();

            for(RDBMSStoreData data : this.storeDataMgr.getManagedStoreData()) {
               if (NucleusLogger.DATASTORE_SCHEMA.isDebugEnabled()) {
                  NucleusLogger.DATASTORE_SCHEMA.debug(Localiser.msg("050046", new Object[]{data.getName()}));
               }

               if (data.hasTable()) {
                  if (data.mapsToView()) {
                     viewsByName.put(data.getDatastoreIdentifier(), data.getTable());
                  } else {
                     baseTablesByName.put(data.getDatastoreIdentifier(), data.getTable());
                  }
               }
            }

            Iterator viewsIter = viewsByName.values().iterator();

            while(viewsIter.hasNext()) {
               ViewImpl view = (ViewImpl)viewsIter.next();
               if (this.writer != null) {
                  try {
                     if (view instanceof ClassView) {
                        this.writer.write("-- ClassView " + view.toString() + " for classes " + StringUtils.objectArrayToString(((ClassView)view).getManagedClasses()) + "\n");
                     }
                  } catch (IOException ioe) {
                     NucleusLogger.DATASTORE_SCHEMA.error("error writing DDL into file", ioe);
                  }

                  ((ViewImpl)viewsIter.next()).drop(this.getCurrentConnection());
               } else {
                  StoreSchemaData info = this.rdbmsMgr.getSchemaHandler().getSchemaData(this.getCurrentConnection(), "columns", new Object[]{view});
                  if (info != null) {
                     ((ViewImpl)viewsIter.next()).drop(this.getCurrentConnection());
                  }
               }
            }

            Map<TableImpl, Boolean> schemaExistsForTableMap = new HashMap();

            for(TableImpl tbl : baseTablesByName.values()) {
               if (this.writer != null) {
                  try {
                     if (tbl instanceof ClassTable) {
                        this.writer.write("-- Constraints for ClassTable " + tbl.toString() + " for classes " + StringUtils.objectArrayToString(((ClassTable)tbl).getManagedClasses()) + "\n");
                     } else if (tbl instanceof JoinTable) {
                        this.writer.write("-- Constraints for JoinTable " + tbl.toString() + " for join relationship\n");
                     }
                  } catch (IOException ioe) {
                     NucleusLogger.DATASTORE_SCHEMA.error("error writing DDL into file", ioe);
                  }

                  tbl.dropConstraints(this.getCurrentConnection());
               } else {
                  boolean exists = false;

                  try {
                     String tableType = ((RDBMSSchemaHandler)this.rdbmsMgr.getSchemaHandler()).getTableType(this.getCurrentConnection(), tbl);
                     if (tableType != null) {
                        exists = true;
                     }
                  } catch (Exception var14) {
                     exists = false;
                  }

                  schemaExistsForTableMap.put(tbl, exists);
                  if (exists) {
                     tbl.dropConstraints(this.getCurrentConnection());
                  }
               }
            }

            for(TableImpl tbl : baseTablesByName.values()) {
               if (this.writer != null) {
                  try {
                     if (tbl instanceof ClassTable) {
                        this.writer.write("-- ClassTable " + tbl.toString() + " for classes " + StringUtils.objectArrayToString(((ClassTable)tbl).getManagedClasses()) + "\n");
                     } else if (tbl instanceof JoinTable) {
                        this.writer.write("-- JoinTable " + tbl.toString() + " for join relationship\n");
                     }
                  } catch (IOException ioe) {
                     NucleusLogger.DATASTORE_SCHEMA.error("error writing DDL into file", ioe);
                  }

                  tbl.drop(this.getCurrentConnection());
               } else {
                  Boolean schemaExists = (Boolean)schemaExistsForTableMap.get(tbl);
                  if (schemaExists != null && schemaExists == Boolean.TRUE) {
                     tbl.drop(this.getCurrentConnection());
                  }
               }
            }
         } catch (Exception e) {
            success = false;
            String errorMsg = Localiser.msg("050047", new Object[]{e});
            NucleusLogger.DATASTORE_SCHEMA.error(errorMsg);
            throw new NucleusUserException(errorMsg, e);
         }

         if (!success) {
            throw new NucleusException("DeleteTables operation failed");
         }
      }
   }

   public String toString() {
      return Localiser.msg("050045", new Object[]{this.rdbmsMgr.getCatalogName(), this.rdbmsMgr.getSchemaName()});
   }
}
