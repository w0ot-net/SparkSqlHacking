package org.datanucleus.store.rdbms.mapping.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.datastore.OracleBlobRDBMSMapping;

public class OracleSerialisedObjectMapping extends SerialisedMapping implements MappingCallbacks {
   public void postFetch(ObjectProvider sm) {
   }

   public void insertPostProcessing(ObjectProvider op) {
      byte[] bytes = new byte[0];
      Object value = op.provideField(this.mmd.getAbsoluteFieldNumber());
      if (value != null) {
         try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            bytes = baos.toByteArray();
         } catch (IOException var6) {
         }
      }

      OracleBlobRDBMSMapping.updateBlobColumn(op, this.getTable(), this.getDatastoreMapping(0), bytes);
   }

   public void postInsert(ObjectProvider op) {
   }

   public void postUpdate(ObjectProvider op) {
      this.insertPostProcessing(op);
   }

   public void preDelete(ObjectProvider op) {
   }
}
