package org.datanucleus.store.rdbms.mapping.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.datastore.OracleBlobRDBMSMapping;

public class OracleMapMapping extends MapMapping {
   public void postInsert(ObjectProvider ownerOP) {
      if (this.containerIsStoredInSingleColumn()) {
         ExecutionContext ec = ownerOP.getExecutionContext();
         Map value = (Map)ownerOP.provideField(this.mmd.getAbsoluteFieldNumber());
         if (value != null && (this.mmd.getMap().keyIsPersistent() || this.mmd.getMap().valueIsPersistent())) {
            for(Map.Entry entry : value.entrySet()) {
               if (this.mmd.getMap().keyIsPersistent() && entry.getKey() != null) {
                  Object key = entry.getKey();
                  if (ec.findObjectProvider(key) == null || ec.getApiAdapter().getExecutionContext(key) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, key, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }

               if (this.mmd.getMap().valueIsPersistent() && entry.getValue() != null) {
                  Object val = entry.getValue();
                  if (ec.findObjectProvider(val) == null || ec.getApiAdapter().getExecutionContext(val) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, val, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

         byte[] bytes = new byte[0];
         if (value != null) {
            try {
               ByteArrayOutputStream baos = new ByteArrayOutputStream();
               ObjectOutputStream oos = new ObjectOutputStream(baos);
               oos.writeObject(value);
               bytes = baos.toByteArray();
            } catch (IOException var8) {
            }
         }

         OracleBlobRDBMSMapping.updateBlobColumn(ownerOP, this.getTable(), this.getDatastoreMapping(0), bytes);
      } else {
         super.postInsert(ownerOP);
      }

   }

   public void postUpdate(ObjectProvider ownerOP) {
      if (this.containerIsStoredInSingleColumn()) {
         ExecutionContext ec = ownerOP.getExecutionContext();
         Map value = (Map)ownerOP.provideField(this.mmd.getAbsoluteFieldNumber());
         if (value != null && (this.mmd.getMap().keyIsPersistent() || this.mmd.getMap().valueIsPersistent())) {
            for(Map.Entry entry : value.entrySet()) {
               if (this.mmd.getMap().keyIsPersistent() && entry.getKey() != null) {
                  Object key = entry.getKey();
                  if (ec.findObjectProvider(key) == null || ec.getApiAdapter().getExecutionContext(key) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, key, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }

               if (this.mmd.getMap().valueIsPersistent() && entry.getValue() != null) {
                  Object val = entry.getValue();
                  if (ec.findObjectProvider(val) == null || ec.getApiAdapter().getExecutionContext(val) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, val, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
                  }
               }
            }
         }

         this.postInsert(ownerOP);
      } else {
         super.postUpdate(ownerOP);
      }

   }
}
