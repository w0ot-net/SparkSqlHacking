package org.datanucleus.store.rdbms.mapping.java;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import org.datanucleus.ExecutionContext;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.datastore.OracleBlobRDBMSMapping;

public class OracleCollectionMapping extends CollectionMapping {
   public void postInsert(ObjectProvider ownerOP) {
      if (this.containerIsStoredInSingleColumn()) {
         ExecutionContext ec = ownerOP.getExecutionContext();
         Collection value = (Collection)ownerOP.provideField(this.mmd.getAbsoluteFieldNumber());
         if (value != null && this.mmd.getCollection().elementIsPersistent()) {
            Object[] collElements = value.toArray();

            for(Object elem : collElements) {
               if (elem != null) {
                  ObjectProvider elemOP = ec.findObjectProvider(elem);
                  if (elemOP == null || ec.getApiAdapter().getExecutionContext(elem) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, elem, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
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
            } catch (IOException var10) {
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
         Collection value = (Collection)ownerOP.provideField(this.mmd.getAbsoluteFieldNumber());
         if (value != null && this.mmd.getCollection().elementIsPersistent()) {
            Object[] collElements = value.toArray();

            for(Object elem : collElements) {
               if (elem != null) {
                  ObjectProvider elemOP = ec.findObjectProvider(elem);
                  if (elemOP == null || ec.getApiAdapter().getExecutionContext(elem) == null) {
                     ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, elem, false, ownerOP, this.mmd.getAbsoluteFieldNumber());
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
