package org.datanucleus.store.rdbms.mapping.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.datanucleus.ExecutionContext;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.datastore.OracleBlobRDBMSMapping;

public class OracleSerialisedPCMapping extends SerialisedPCMapping implements MappingCallbacks {
   public void insertPostProcessing(ObjectProvider op) {
      Object value = op.provideField(this.mmd.getAbsoluteFieldNumber());
      ObjectProvider sm = null;
      if (value != null) {
         ExecutionContext ec = op.getExecutionContext();
         sm = ec.findObjectProvider(value);
         if (sm == null || sm.getExecutionContext().getApiAdapter().getExecutionContext(value) == null) {
            sm = ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, value, false, op, this.mmd.getAbsoluteFieldNumber());
         }
      }

      if (sm != null) {
         sm.setStoringPC();
      }

      byte[] bytes = new byte[0];
      if (value != null) {
         try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            bytes = baos.toByteArray();
         } catch (IOException var7) {
         }
      }

      OracleBlobRDBMSMapping.updateBlobColumn(op, this.getTable(), this.getDatastoreMapping(0), bytes);
      if (sm != null) {
         sm.unsetStoringPC();
      }

   }

   public void postInsert(ObjectProvider op) {
   }

   public void postUpdate(ObjectProvider op) {
      this.insertPostProcessing(op);
   }

   public void postFetch(ObjectProvider op) {
   }

   public void preDelete(ObjectProvider op) {
   }
}
