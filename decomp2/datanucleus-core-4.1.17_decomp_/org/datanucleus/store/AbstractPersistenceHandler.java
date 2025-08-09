package org.datanucleus.store;

import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.exceptions.DatastoreReadOnlyException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractPersistenceHandler implements StorePersistenceHandler {
   protected StoreManager storeMgr;

   public AbstractPersistenceHandler(StoreManager storeMgr) {
      this.storeMgr = storeMgr;
   }

   public void batchStart(ExecutionContext ec, PersistenceBatchType batchType) {
   }

   public void batchEnd(ExecutionContext ec, PersistenceBatchType type) {
   }

   public void insertObjects(ObjectProvider... ops) {
      if (ops.length == 1) {
         this.insertObject(ops[0]);
      } else {
         for(int i = 0; i < ops.length; ++i) {
            this.insertObject(ops[i]);
         }

      }
   }

   public void deleteObjects(ObjectProvider... ops) {
      if (ops.length == 1) {
         this.deleteObject(ops[0]);
      } else {
         for(int i = 0; i < ops.length; ++i) {
            this.deleteObject(ops[i]);
         }

      }
   }

   public void locateObjects(ObjectProvider[] ops) {
      if (ops.length == 1) {
         this.locateObject(ops[0]);
      } else {
         for(int i = 0; i < ops.length; ++i) {
            this.locateObject(ops[i]);
         }

      }
   }

   public Object[] findObjects(ExecutionContext ec, Object[] ids) {
      Object[] objects = new Object[ids.length];

      for(int i = 0; i < ids.length; ++i) {
         objects[i] = this.findObject(ec, ids[i]);
      }

      return objects;
   }

   public void assertReadOnlyForUpdateOfObject(ObjectProvider op) {
      if (op.getExecutionContext().getBooleanProperty("datanucleus.readOnlyDatastore")) {
         if (op.getExecutionContext().getStringProperty("datanucleus.readOnlyDatastoreAction").equalsIgnoreCase("EXCEPTION")) {
            throw new DatastoreReadOnlyException(Localiser.msg("032004", op.getObjectAsPrintable()), op.getExecutionContext().getClassLoaderResolver());
         } else {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("032005", op.getObjectAsPrintable()));
            }

         }
      } else {
         AbstractClassMetaData cmd = op.getClassMetaData();
         if (cmd.hasExtension("read-only")) {
            String value = cmd.getValueForExtension("read-only");
            if (!StringUtils.isWhitespace(value)) {
               boolean readonly = Boolean.valueOf(value);
               if (readonly) {
                  if (op.getExecutionContext().getStringProperty("datanucleus.readOnlyDatastoreAction").equalsIgnoreCase("EXCEPTION")) {
                     throw new DatastoreReadOnlyException(Localiser.msg("032006", op.getObjectAsPrintable()), op.getExecutionContext().getClassLoaderResolver());
                  }

                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("032007", op.getObjectAsPrintable()));
                  }

                  return;
               }
            }
         }

      }
   }
}
