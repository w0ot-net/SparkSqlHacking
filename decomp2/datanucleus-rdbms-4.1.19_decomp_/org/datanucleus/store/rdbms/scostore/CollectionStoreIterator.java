package org.datanucleus.store.rdbms.scostore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedElementPCMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.Table;

class CollectionStoreIterator implements Iterator {
   private final AbstractCollectionStore collStore;
   private final ObjectProvider op;
   private final ExecutionContext ec;
   private final Iterator delegate;
   private Object lastElement = null;

   CollectionStoreIterator(ObjectProvider op, ResultSet rs, ResultObjectFactory rof, AbstractCollectionStore store) throws MappedDatastoreException {
      this.op = op;
      this.ec = op.getExecutionContext();
      this.collStore = store;
      ArrayList results = new ArrayList();
      Object nextElement;
      if (rs != null) {
         for(; this.next(rs); results.add(nextElement)) {
            if (!this.collStore.elementsAreEmbedded && !this.collStore.elementsAreSerialised) {
               if (!(this.collStore.elementMapping instanceof ReferenceMapping)) {
                  nextElement = rof.getObject(this.ec, rs);
               } else {
                  int[] param = new int[this.collStore.elementMapping.getNumberOfDatastoreMappings()];

                  for(int i = 0; i < param.length; ++i) {
                     param[i] = i + 1;
                  }

                  nextElement = this.collStore.elementMapping.getObject(this.ec, rs, param);
               }
            } else {
               int[] param = new int[this.collStore.elementMapping.getNumberOfDatastoreMappings()];

               for(int i = 0; i < param.length; ++i) {
                  param[i] = i + 1;
               }

               if (!(this.collStore.elementMapping instanceof SerialisedPCMapping) && !(this.collStore.elementMapping instanceof SerialisedReferenceMapping) && !(this.collStore.elementMapping instanceof EmbeddedElementPCMapping)) {
                  nextElement = this.collStore.elementMapping.getObject(this.ec, rs, param);
               } else {
                  int ownerFieldNumber = -1;
                  if (this.collStore.containerTable != null) {
                     ownerFieldNumber = this.getOwnerMemberMetaData(this.collStore.containerTable).getAbsoluteFieldNumber();
                  }

                  nextElement = this.collStore.elementMapping.getObject(this.ec, rs, param, op, ownerFieldNumber);
               }
            }
         }
      }

      this.delegate = results.iterator();
   }

   public boolean hasNext() {
      return this.delegate.hasNext();
   }

   public Object next() {
      this.lastElement = this.delegate.next();
      return this.lastElement;
   }

   public synchronized void remove() {
      if (this.lastElement == null) {
         throw new IllegalStateException("No entry to remove");
      } else {
         this.collStore.remove(this.op, this.lastElement, -1, true);
         this.delegate.remove();
         this.lastElement = null;
      }
   }

   protected boolean next(Object rs) throws MappedDatastoreException {
      try {
         return ((ResultSet)rs).next();
      } catch (SQLException e) {
         throw new MappedDatastoreException("SQLException", e);
      }
   }

   protected AbstractMemberMetaData getOwnerMemberMetaData(Table containerTable) {
      return ((JoinTable)containerTable).getOwnerMemberMetaData();
   }
}
