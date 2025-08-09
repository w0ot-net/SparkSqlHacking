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
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.Table;

public class ArrayStoreIterator implements Iterator {
   private final ExecutionContext ec;
   private final Iterator delegate;
   private Object lastElement = null;

   ArrayStoreIterator(ObjectProvider op, ResultSet rs, ResultObjectFactory rof, ElementContainerStore backingStore) throws MappedDatastoreException {
      this.ec = op.getExecutionContext();
      ArrayList results = new ArrayList();
      Object nextElement;
      if (rs != null) {
         for(JavaTypeMapping elementMapping = backingStore.getElementMapping(); this.next(rs); results.add(nextElement)) {
            if (!backingStore.isElementsAreEmbedded() && !backingStore.isElementsAreSerialised()) {
               if (!(elementMapping instanceof ReferenceMapping)) {
                  nextElement = rof.getObject(this.ec, rs);
               } else {
                  int[] param = new int[elementMapping.getNumberOfDatastoreMappings()];

                  for(int i = 0; i < param.length; ++i) {
                     param[i] = i + 1;
                  }

                  nextElement = elementMapping.getObject(this.ec, rs, param);
               }
            } else {
               int[] param = new int[elementMapping.getNumberOfDatastoreMappings()];

               for(int i = 0; i < param.length; ++i) {
                  param[i] = i + 1;
               }

               if (!(elementMapping instanceof SerialisedPCMapping) && !(elementMapping instanceof SerialisedReferenceMapping) && !(elementMapping instanceof EmbeddedElementPCMapping)) {
                  nextElement = elementMapping.getObject(this.ec, rs, param);
               } else {
                  int ownerFieldNumber = -1;
                  if (backingStore.getContainerTable() != null) {
                     ownerFieldNumber = this.getOwnerFieldMetaData(backingStore.getContainerTable()).getAbsoluteFieldNumber();
                  }

                  nextElement = elementMapping.getObject(this.ec, rs, param, op, ownerFieldNumber);
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
   }

   protected boolean next(Object rs) throws MappedDatastoreException {
      try {
         return ((ResultSet)rs).next();
      } catch (SQLException e) {
         throw new MappedDatastoreException("SQLException", e);
      }
   }

   protected AbstractMemberMetaData getOwnerFieldMetaData(Table containerTable) {
      return ((JoinTable)containerTable).getOwnerMemberMetaData();
   }
}
