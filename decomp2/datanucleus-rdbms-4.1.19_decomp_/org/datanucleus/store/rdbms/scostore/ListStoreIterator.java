package org.datanucleus.store.rdbms.scostore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
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

public class ListStoreIterator implements ListIterator {
   private final ObjectProvider op;
   private final ListIterator delegate;
   private Object lastElement = null;
   private int currentIndex = -1;
   private final AbstractListStore abstractListStore;

   ListStoreIterator(ObjectProvider op, ResultSet resultSet, ResultObjectFactory rof, AbstractListStore als) throws MappedDatastoreException {
      this.op = op;
      this.abstractListStore = als;
      ExecutionContext ec = op.getExecutionContext();
      ArrayList results = new ArrayList();
      if (resultSet != null) {
         Table containerTable = als.getContainerTable();
         boolean elementsAreSerialised = als.isElementsAreSerialised();
         boolean elementsAreEmbedded = als.isElementsAreEmbedded();

         Object nextElement;
         for(JavaTypeMapping elementMapping = als.getElementMapping(); this.next(resultSet); results.add(nextElement)) {
            if (!elementsAreEmbedded && !elementsAreSerialised) {
               if (!(elementMapping instanceof ReferenceMapping)) {
                  nextElement = rof.getObject(ec, resultSet);
               } else {
                  int[] param = new int[elementMapping.getNumberOfDatastoreMappings()];

                  for(int i = 0; i < param.length; ++i) {
                     param[i] = i + 1;
                  }

                  nextElement = elementMapping.getObject(ec, resultSet, param);
               }
            } else {
               int[] param = new int[elementMapping.getNumberOfDatastoreMappings()];

               for(int i = 0; i < param.length; ++i) {
                  param[i] = i + 1;
               }

               if (!(elementMapping instanceof SerialisedPCMapping) && !(elementMapping instanceof SerialisedReferenceMapping) && !(elementMapping instanceof EmbeddedElementPCMapping)) {
                  nextElement = elementMapping.getObject(ec, resultSet, param);
               } else {
                  int ownerFieldNumber = -1;
                  if (containerTable != null) {
                     ownerFieldNumber = this.getOwnerMemberMetaData(this.abstractListStore.containerTable).getAbsoluteFieldNumber();
                  }

                  nextElement = elementMapping.getObject(ec, resultSet, param, op, ownerFieldNumber);
               }
            }
         }
      }

      this.delegate = results.listIterator();
   }

   public void add(Object elem) {
      this.currentIndex = this.delegate.nextIndex();
      this.abstractListStore.add(this.op, elem, this.currentIndex, -1);
      this.delegate.add(elem);
      this.lastElement = null;
   }

   public boolean hasNext() {
      return this.delegate.hasNext();
   }

   public boolean hasPrevious() {
      return this.delegate.hasPrevious();
   }

   public Object next() {
      this.currentIndex = this.delegate.nextIndex();
      this.lastElement = this.delegate.next();
      return this.lastElement;
   }

   public int nextIndex() {
      return this.delegate.nextIndex();
   }

   public Object previous() {
      this.currentIndex = this.delegate.previousIndex();
      this.lastElement = this.delegate.previous();
      return this.lastElement;
   }

   public int previousIndex() {
      return this.delegate.previousIndex();
   }

   public synchronized void remove() {
      if (this.lastElement == null) {
         throw new IllegalStateException("No entry to remove");
      } else {
         this.abstractListStore.remove(this.op, this.currentIndex, -1);
         this.delegate.remove();
         this.lastElement = null;
         this.currentIndex = -1;
      }
   }

   public synchronized void set(Object elem) {
      if (this.lastElement == null) {
         throw new IllegalStateException("No entry to replace");
      } else {
         this.abstractListStore.set(this.op, this.currentIndex, elem, true);
         this.delegate.set(elem);
         this.lastElement = elem;
      }
   }

   protected AbstractMemberMetaData getOwnerMemberMetaData(Table containerTable) {
      return ((JoinTable)containerTable).getOwnerMemberMetaData();
   }

   protected boolean next(Object resultSet) throws MappedDatastoreException {
      try {
         return ((ResultSet)resultSet).next();
      } catch (SQLException e) {
         throw new MappedDatastoreException(e.getMessage(), e);
      }
   }
}
