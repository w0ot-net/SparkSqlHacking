package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.CollectionMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.scostore.ListStore;
import org.datanucleus.util.Localiser;

public abstract class AbstractListStore extends AbstractCollectionStore implements ListStore {
   protected boolean indexedList = true;
   protected String indexOfStmt;
   protected String lastIndexOfStmt;
   protected String removeAtStmt;
   protected String shiftStmt;

   protected AbstractListStore(RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
   }

   public Iterator iterator(ObjectProvider op) {
      return this.listIterator(op);
   }

   public ListIterator listIterator(ObjectProvider op) {
      return this.listIterator(op, -1, -1);
   }

   protected abstract ListIterator listIterator(ObjectProvider var1, int var2, int var3);

   public boolean add(ObjectProvider op, Object element, int size) {
      return this.internalAdd(op, 0, true, Collections.singleton(element), size);
   }

   public void add(ObjectProvider op, Object element, int index, int size) {
      this.internalAdd(op, index, false, Collections.singleton(element), size);
   }

   public boolean addAll(ObjectProvider op, Collection elements, int size) {
      return this.internalAdd(op, 0, true, elements, size);
   }

   public boolean addAll(ObjectProvider op, Collection elements, int index, int size) {
      return this.internalAdd(op, index, false, elements, size);
   }

   protected abstract boolean internalAdd(ObjectProvider var1, int var2, boolean var3, Collection var4, int var5);

   public Object get(ObjectProvider op, int index) {
      ListIterator<E> iter = this.listIterator(op, index, index);
      if (iter != null && iter.hasNext()) {
         if (!this.indexedList) {
            E obj = (E)null;

            for(int position = 0; iter.hasNext(); ++position) {
               obj = (E)iter.next();
               if (position == index) {
                  return obj;
               }
            }
         }

         return iter.next();
      } else {
         return null;
      }
   }

   public int indexOf(ObjectProvider op, Object element) {
      this.validateElementForReading(op, element);
      return this.internalIndexOf(op, element, this.getIndexOfStmt(element));
   }

   public int lastIndexOf(ObjectProvider op, Object element) {
      this.validateElementForReading(op, element);
      return this.internalIndexOf(op, element, this.getLastIndexOfStmt(element));
   }

   public boolean remove(ObjectProvider op, Object element, int size, boolean allowDependentField) {
      if (!this.validateElementForReading(op, element)) {
         return false;
      } else {
         Object elementToRemove = element;
         ExecutionContext ec = op.getExecutionContext();
         if (ec.getApiAdapter().isDetached(element)) {
            elementToRemove = ec.findObject(ec.getApiAdapter().getIdForObject(element), true, false, element.getClass().getName());
         }

         boolean modified = this.internalRemove(op, elementToRemove, size);
         if (allowDependentField) {
            CollectionMetaData collmd = this.ownerMemberMetaData.getCollection();
            boolean dependent = collmd.isDependentElement();
            if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
               dependent = true;
            }

            if (dependent && !collmd.isEmbeddedElement()) {
               op.getExecutionContext().deleteObjectInternal(elementToRemove);
            }
         }

         return modified;
      }
   }

   public Object remove(ObjectProvider op, int index, int size) {
      E element = (E)this.get(op, index);
      if (this.indexedList) {
         this.internalRemoveAt(op, index, size);
      } else {
         this.internalRemove(op, element, size);
      }

      CollectionMetaData collmd = this.ownerMemberMetaData.getCollection();
      boolean dependent = collmd.isDependentElement();
      if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
         dependent = true;
      }

      if (dependent && !collmd.isEmbeddedElement() && !this.contains(op, element)) {
         op.getExecutionContext().deleteObjectInternal(element);
      }

      return element;
   }

   protected abstract boolean internalRemove(ObjectProvider var1, Object var2, int var3);

   protected abstract void internalRemoveAt(ObjectProvider var1, int var2, int var3);

   public List subList(ObjectProvider op, int startIdx, int endIdx) {
      ListIterator iter = this.listIterator(op, startIdx, endIdx);
      List list = new ArrayList();

      while(iter.hasNext()) {
         list.add(iter.next());
      }

      return !this.indexedList && list.size() > endIdx - startIdx ? list.subList(startIdx, endIdx) : list;
   }

   protected int[] getIndicesOf(ObjectProvider op, Collection elements) {
      if (elements != null && !elements.isEmpty()) {
         Iterator iter = elements.iterator();

         while(iter.hasNext()) {
            this.validateElementForReading(op, iter.next());
         }

         String stmt = this.getIndicesOfStmt(elements);

         try {
            ExecutionContext ec = op.getExecutionContext();
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            int i;
            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, false);

               try {
                  Iterator elemIter = elements.iterator();
                  int jdbcPosition = 1;

                  while(elemIter.hasNext()) {
                     Object element = elemIter.next();
                     jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                     jdbcPosition = BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
                     if (this.relationDiscriminatorMapping != null) {
                        jdbcPosition = BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                     }
                  }

                  List<Integer> indexes = new ArrayList();
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

                  try {
                     while(rs.next()) {
                        indexes.add(rs.getInt(1));
                     }

                     JDBCUtils.logWarnings(rs);
                  } finally {
                     rs.close();
                  }

                  if (!indexes.isEmpty()) {
                     i = 0;
                     int[] indicesReturn = new int[indexes.size()];

                     for(Integer idx : indexes) {
                        indicesReturn[i++] = idx;
                     }

                     int[] var37 = indicesReturn;
                     return var37;
                  }

                  i = (int)null;
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return (int[])i;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056017", new Object[]{stmt}), e);
         }
      } else {
         return null;
      }
   }

   protected int internalIndexOf(ObjectProvider op, Object element, String stmt) {
      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         int index;
         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, false);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               jdbcPosition = BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
               }

               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  boolean found = rs.next();
                  if (found) {
                     index = rs.getInt(1);
                     JDBCUtils.logWarnings(rs);
                     int var12 = index;
                     return var12;
                  }

                  JDBCUtils.logWarnings(rs);
                  index = -1;
               } finally {
                  rs.close();
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return index;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056017", new Object[]{stmt}), e);
      }
   }

   protected void internalRemoveAt(ObjectProvider op, int index, String stmt, int size) {
      int currentListSize = 0;
      if (size < 0) {
         currentListSize = this.size(op);
      } else {
         currentListSize = size;
      }

      ExecutionContext ec = op.getExecutionContext();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, false);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, index, jdbcPosition, this.orderMapping);
               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
               }

               int[] rowsDeleted = sqlControl.executeStatementUpdate(ec, mconn, stmt, ps, true);
               if (rowsDeleted[0] == 0) {
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }

            if (index != currentListSize - 1) {
               for(int i = index + 1; i < currentListSize; ++i) {
                  this.internalShift(op, mconn, false, i, -1, true);
               }
            }
         } finally {
            mconn.release();
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056012", new Object[]{stmt}), e);
      } catch (MappedDatastoreException e) {
         throw new NucleusDataStoreException(Localiser.msg("056012", new Object[]{stmt}), e);
      }
   }

   protected int[] internalShift(ObjectProvider op, ManagedConnection conn, boolean batched, int oldIndex, int amount, boolean executeNow) throws MappedDatastoreException {
      ExecutionContext ec = op.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();
      String shiftStmt = this.getShiftStmt();

      try {
         PreparedStatement ps = sqlControl.getStatementForUpdate(conn, shiftStmt, batched);

         int[] var12;
         try {
            int jdbcPosition = 1;
            jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, amount, jdbcPosition, this.orderMapping);
            jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
            jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, oldIndex, jdbcPosition, this.orderMapping);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
            }

            var12 = sqlControl.executeStatementUpdate(ec, conn, shiftStmt, ps, executeNow);
         } finally {
            sqlControl.closeStatement(conn, ps);
         }

         return var12;
      } catch (SQLException sqle) {
         throw new MappedDatastoreException(shiftStmt, sqle);
      }
   }

   protected String getIndexOfStmt(Object element) {
      if (this.elementMapping instanceof ReferenceMapping && this.elementMapping.getNumberOfDatastoreMappings() > 1) {
         return this.getIndexOfStatementString(element);
      } else {
         if (this.indexOfStmt == null) {
            synchronized(this) {
               this.indexOfStmt = this.getIndexOfStatementString(element);
            }
         }

         return this.indexOfStmt;
      }
   }

   private String getIndexOfStatementString(Object element) {
      StringBuilder stmt = new StringBuilder("SELECT ");

      for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      stmt.append(" FROM ").append(this.containerTable.toString()).append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      BackingStoreHelper.appendWhereClauseForElement(stmt, this.elementMapping, element, this.isElementsAreSerialised(), (String)null, false);
      if (this.relationDiscriminatorMapping != null) {
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
      }

      stmt.append(" ORDER BY ");

      for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      return stmt.toString();
   }

   protected String getLastIndexOfStmt(Object element) {
      if (this.elementMapping instanceof ReferenceMapping && this.elementMapping.getNumberOfDatastoreMappings() > 1) {
         return this.getLastIndexOfStatementString(element);
      } else {
         if (this.lastIndexOfStmt == null) {
            synchronized(this) {
               this.lastIndexOfStmt = this.getLastIndexOfStatementString(element);
            }
         }

         return this.lastIndexOfStmt;
      }
   }

   private String getLastIndexOfStatementString(Object element) {
      StringBuilder stmt = new StringBuilder("SELECT ");

      for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      stmt.append(" FROM ").append(this.containerTable.toString()).append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      BackingStoreHelper.appendWhereClauseForElement(stmt, this.elementMapping, element, this.isElementsAreSerialised(), (String)null, false);
      if (this.relationDiscriminatorMapping != null) {
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
      }

      stmt.append(" ORDER BY ");

      for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append(" DESC ");
      }

      return stmt.toString();
   }

   protected String getIndicesOfStmt(Collection elements) {
      StringBuilder stmt = new StringBuilder("SELECT ");

      for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      stmt.append(" FROM ").append(this.containerTable.toString()).append(" WHERE ");
      Iterator iter = elements.iterator();

      for(boolean first_element = true; iter.hasNext(); first_element = false) {
         Object element = iter.next();
         stmt.append(first_element ? "(" : " OR (");
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
         BackingStoreHelper.appendWhereClauseForElement(stmt, this.elementMapping, element, this.isElementsAreSerialised(), (String)null, false);
         if (this.relationDiscriminatorMapping != null) {
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
         }

         stmt.append(")");
      }

      stmt.append(" ORDER BY ");

      for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString()).append(" DESC");
      }

      return stmt.toString();
   }

   protected String getRemoveAtStmt() {
      if (this.removeAtStmt == null) {
         synchronized(this) {
            StringBuilder stmt = (new StringBuilder("DELETE FROM ")).append(this.containerTable.toString()).append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            if (this.orderMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.orderMapping, (String)null, false);
            }

            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.removeAtStmt = stmt.toString();
         }
      }

      return this.removeAtStmt;
   }

   protected String getShiftStmt() {
      if (this.shiftStmt == null) {
         synchronized(this) {
            StringBuilder stmt = (new StringBuilder("UPDATE ")).append(this.containerTable.toString()).append(" SET ");

            for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(",");
               }

               stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               stmt.append(" = ");
               stmt.append(((AbstractDatastoreMapping)this.orderMapping.getDatastoreMapping(i)).getUpdateInputParameter());
               stmt.append(" + ");
               stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            }

            stmt.append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.orderMapping, (String)null, false);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.shiftStmt = stmt.toString();
         }
      }

      return this.shiftStmt;
   }
}
