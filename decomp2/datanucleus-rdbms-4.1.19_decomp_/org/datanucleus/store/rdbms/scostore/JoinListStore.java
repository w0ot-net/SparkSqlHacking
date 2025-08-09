package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.CollectionMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.OrderMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.fieldmanager.DynamicSchemaFieldManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.query.PersistentClassROF;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.rdbms.sql.DiscriminatorStatementGenerator;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.StatementGenerator;
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.CollectionTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class JoinListStore extends AbstractListStore {
   private String setStmt;

   public JoinListStore(AbstractMemberMetaData mmd, CollectionTable joinTable, ClassLoaderResolver clr) {
      super(joinTable.getStoreManager(), clr);
      this.containerTable = joinTable;
      this.setOwner(mmd);
      if (this.ownerMemberMetaData.getOrderMetaData() != null && !this.ownerMemberMetaData.getOrderMetaData().isIndexedList()) {
         this.indexedList = false;
      }

      this.ownerMapping = joinTable.getOwnerMapping();
      this.elementMapping = joinTable.getElementMapping();
      this.orderMapping = joinTable.getOrderMapping();
      this.relationDiscriminatorMapping = joinTable.getRelationDiscriminatorMapping();
      this.relationDiscriminatorValue = joinTable.getRelationDiscriminatorValue();
      this.elementType = mmd.getCollection().getElementType();
      this.elementsAreEmbedded = joinTable.isEmbeddedElement();
      this.elementsAreSerialised = joinTable.isSerialisedElement();
      if (this.orderMapping == null && this.indexedList) {
         throw new NucleusUserException(Localiser.msg("056044", new Object[]{this.ownerMemberMetaData.getFullFieldName(), joinTable.toString()}));
      } else {
         if (this.elementsAreSerialised) {
            this.elementInfo = null;
         } else {
            Class element_class = clr.classForName(this.elementType);
            if (ClassUtils.isReferenceType(element_class)) {
               String[] implNames = MetaDataUtils.getInstance().getImplementationNamesForReferenceField(this.ownerMemberMetaData, FieldRole.ROLE_COLLECTION_ELEMENT, clr, this.storeMgr.getMetaDataManager());
               this.elementInfo = new ElementContainerStore.ElementInfo[implNames.length];

               for(int i = 0; i < implNames.length; ++i) {
                  DatastoreClass table = this.storeMgr.getDatastoreClass(implNames[i], clr);
                  AbstractClassMetaData cmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(implNames[i], clr);
                  this.elementInfo[i] = new ElementContainerStore.ElementInfo(cmd, table);
               }
            } else {
               this.emd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(element_class, clr);
               if (this.emd != null) {
                  if (!this.elementsAreEmbedded) {
                     this.elementInfo = this.getElementInformationForClass();
                  } else {
                     this.elementInfo = null;
                  }
               } else {
                  this.elementInfo = null;
               }
            }
         }

      }
   }

   protected boolean internalAdd(ObjectProvider op, int start, boolean atEnd, Collection c, int size) {
      if (c != null && c.size() != 0) {
         if (this.relationType == RelationType.MANY_TO_MANY_BI && this.ownerMemberMetaData.getMappedBy() != null) {
            return true;
         } else {
            int shift = c.size();
            ExecutionContext ec = op.getExecutionContext();

            for(Object element : c) {
               this.validateElementForWriting(ec, element, (FieldValues)null);
               if (this.relationType == RelationType.ONE_TO_MANY_BI) {
                  ObjectProvider elementOP = ec.findObjectProvider(element);
                  if (elementOP != null) {
                     AbstractMemberMetaData[] relatedMmds = this.ownerMemberMetaData.getRelatedMemberMetaData(this.clr);
                     Object elementOwner = elementOP.provideField(relatedMmds[0].getAbsoluteFieldNumber());
                     if (elementOwner == null) {
                        NucleusLogger.PERSISTENCE.info(Localiser.msg("056037", new Object[]{op.getObjectAsPrintable(), this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(elementOP.getObject())}));
                        elementOP.replaceField(relatedMmds[0].getAbsoluteFieldNumber(), op.getObject());
                     } else if (elementOwner != op.getObject() && op.getReferencedPC() == null) {
                        throw new NucleusUserException(Localiser.msg("056038", new Object[]{op.getObjectAsPrintable(), this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(elementOP.getObject()), StringUtils.toJVMIDString(elementOwner)}));
                     }
                  }
               }
            }

            int currentListSize = 0;
            if (size < 0) {
               currentListSize = this.size(op);
            } else {
               currentListSize = size;
            }

            if (this.storeMgr.getBooleanObjectProperty("datanucleus.rdbms.dynamicSchemaUpdates")) {
               DynamicSchemaFieldManager dynamicSchemaFM = new DynamicSchemaFieldManager(this.storeMgr, op);
               dynamicSchemaFM.storeObjectField(this.getOwnerMemberMetaData().getAbsoluteFieldNumber(), c);
               if (dynamicSchemaFM.hasPerformedSchemaUpdates()) {
                  this.invalidateAddStmt();
               }
            }

            String addStmt = this.getAddStmtForJoinTable();

            try {
               ManagedConnection mconn = this.storeMgr.getConnection(ec);
               SQLController sqlControl = this.storeMgr.getSQLController();

               try {
                  if (!atEnd && start != currentListSize) {
                     boolean batched = currentListSize - start > 0;

                     for(int i = currentListSize - 1; i >= start; --i) {
                        this.internalShift(op, mconn, batched, i, shift, i == start);
                     }
                  } else {
                     start = currentListSize;
                  }

                  int jdbcPosition = 1;
                  boolean batched = c.size() > 1;

                  for(Object element : c) {
                     PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, addStmt, batched);

                     try {
                        jdbcPosition = 1;
                        jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                        jdbcPosition = BackingStoreHelper.populateElementInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
                        if (this.orderMapping != null) {
                           jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, start, jdbcPosition, this.orderMapping);
                        }

                        if (this.relationDiscriminatorMapping != null) {
                           BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                        }

                        ++start;
                        Iterator iter;
                        sqlControl.executeStatementUpdate(ec, mconn, addStmt, ps, !iter.hasNext());
                     } finally {
                        sqlControl.closeStatement(mconn, ps);
                     }
                  }
               } finally {
                  mconn.release();
               }

               return true;
            } catch (MappedDatastoreException e) {
               throw new NucleusDataStoreException(Localiser.msg("056009", new Object[]{addStmt}), e);
            } catch (SQLException e) {
               throw new NucleusDataStoreException(Localiser.msg("056009", new Object[]{addStmt}), e);
            }
         }
      } else {
         return true;
      }
   }

   public Object set(ObjectProvider op, int index, Object element, boolean allowDependentField) {
      ExecutionContext ec = op.getExecutionContext();
      this.validateElementForWriting(ec, element, (FieldValues)null);
      E oldElement = (E)this.get(op, index);
      if (this.storeMgr.getBooleanObjectProperty("datanucleus.rdbms.dynamicSchemaUpdates")) {
         DynamicSchemaFieldManager dynamicSchemaFM = new DynamicSchemaFieldManager(this.storeMgr, op);
         Collection coll = new ArrayList();
         coll.add(element);
         dynamicSchemaFM.storeObjectField(this.getOwnerMemberMetaData().getAbsoluteFieldNumber(), coll);
         if (dynamicSchemaFM.hasPerformedSchemaUpdates()) {
            this.setStmt = null;
         }
      }

      String setStmt = this.getSetStmt();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, setStmt, false);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateElementInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               if (this.getOwnerMemberMetaData().getOrderMetaData() != null && !this.getOwnerMemberMetaData().getOrderMetaData().isIndexedList()) {
                  NucleusLogger.PERSISTENCE.warn("Calling List.addElement at a position for an ordered list is a stupid thing to do; the ordering is set my the ordering specification. Use an indexed list to do this correctly");
               } else {
                  jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, index, jdbcPosition, this.orderMapping);
               }

               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
               }

               sqlControl.executeStatementUpdate(ec, mconn, setStmt, ps, true);
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056015", new Object[]{setStmt}), e);
      }

      CollectionMetaData collmd = this.ownerMemberMetaData.getCollection();
      boolean dependent = collmd.isDependentElement();
      if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
         dependent = true;
      }

      if (dependent && !collmd.isEmbeddedElement() && allowDependentField && oldElement != null && !this.contains(op, oldElement)) {
         ec.deleteObjectInternal(oldElement);
      }

      return oldElement;
   }

   public void update(ObjectProvider op, Collection coll) {
      if (coll != null && !coll.isEmpty()) {
         if (!this.ownerMemberMetaData.getCollection().isSerializedElement() && !this.ownerMemberMetaData.getCollection().isEmbeddedElement()) {
            Collection existing = new ArrayList();
            Iterator elemIter = this.iterator(op);

            while(elemIter.hasNext()) {
               Object elem = elemIter.next();
               if (!coll.contains(elem)) {
                  this.remove(op, elem, -1, true);
               } else {
                  existing.add(elem);
               }
            }

            if (!existing.equals(coll)) {
               this.clear(op);
               this.addAll(op, coll, 0);
            }
         } else {
            this.clear(op);
            this.addAll(op, coll, 0);
         }
      } else {
         this.clear(op);
      }
   }

   protected boolean internalRemove(ObjectProvider ownerOP, Object element, int size) {
      boolean modified = false;
      if (this.indexedList) {
         Collection elements = new ArrayList();
         elements.add(element);
         int[] indices = this.getIndicesOf(ownerOP, elements);
         if (indices == null) {
            return false;
         }

         for(int i = 0; i < indices.length; ++i) {
            this.internalRemoveAt(ownerOP, indices[i], size);
            modified = true;
         }
      } else {
         ExecutionContext ec = ownerOP.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);

         try {
            int[] rcs = this.internalRemove(ownerOP, mconn, false, element, true);
            if (rcs != null && rcs[0] > 0) {
               modified = true;
            }
         } catch (MappedDatastoreException sqe) {
            String msg = Localiser.msg("056012", new Object[]{sqe.getMessage()});
            NucleusLogger.DATASTORE.error(msg, sqe.getCause());
            throw new NucleusDataStoreException(msg, sqe, ownerOP.getObject());
         } finally {
            mconn.release();
         }
      }

      return modified;
   }

   private int[] internalRemove(ObjectProvider op, ManagedConnection conn, boolean batched, Object element, boolean executeNow) throws MappedDatastoreException {
      ExecutionContext ec = op.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();
      String removeStmt = this.getRemoveStmt(element);

      try {
         PreparedStatement ps = sqlControl.getStatementForUpdate(conn, removeStmt, batched);

         int[] var11;
         try {
            int jdbcPosition = 1;
            jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
            jdbcPosition = BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
            }

            var11 = sqlControl.executeStatementUpdate(ec, conn, removeStmt, ps, executeNow);
         } finally {
            sqlControl.closeStatement(conn, ps);
         }

         return var11;
      } catch (SQLException sqle) {
         throw new MappedDatastoreException("SQLException", sqle);
      }
   }

   public boolean removeAll(ObjectProvider op, Collection elements, int size) {
      if (elements != null && elements.size() != 0) {
         int currentListSize = this.size(op);
         int[] indices = this.getIndicesOf(op, elements);
         if (indices == null) {
            return false;
         } else {
            boolean modified = false;
            SQLController sqlControl = this.storeMgr.getSQLController();
            ExecutionContext ec = op.getExecutionContext();
            String removeAllStmt = this.getRemoveAllStmt(elements);

            try {
               ManagedConnection mconn = this.storeMgr.getConnection(ec);

               try {
                  PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, removeAllStmt, false);

                  try {
                     int jdbcPosition = 1;

                     for(Object element : elements) {
                        jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                        jdbcPosition = BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
                        if (this.relationDiscriminatorMapping != null) {
                           jdbcPosition = BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                        }
                     }

                     int[] number = sqlControl.executeStatementUpdate(ec, mconn, removeAllStmt, ps, true);
                     if (number[0] > 0) {
                        modified = true;
                     }
                  } finally {
                     sqlControl.closeStatement(mconn, ps);
                  }
               } finally {
                  mconn.release();
               }
            } catch (SQLException e) {
               NucleusLogger.DATASTORE.error(e);
               throw new NucleusDataStoreException(Localiser.msg("056012", new Object[]{removeAllStmt}), e);
            }

            try {
               boolean batched = this.storeMgr.allowsBatching();
               ManagedConnection mconn = this.storeMgr.getConnection(ec);

               try {
                  for(int i = 0; i < currentListSize; ++i) {
                     int shift = 0;
                     boolean removed = false;

                     for(int j = 0; j < indices.length; ++j) {
                        if (indices[j] == i) {
                           removed = true;
                           break;
                        }

                        if (indices[j] < i) {
                           ++shift;
                        }
                     }

                     if (!removed && shift > 0) {
                        this.internalShift(op, mconn, batched, i, -1 * shift, i == currentListSize - 1);
                     }
                  }
               } finally {
                  mconn.release();
               }
            } catch (MappedDatastoreException e) {
               NucleusLogger.DATASTORE.error(e);
               throw new NucleusDataStoreException(Localiser.msg("056012", new Object[]{removeAllStmt}), e);
            }

            boolean dependent = this.getOwnerMemberMetaData().getCollection().isDependentElement();
            if (this.getOwnerMemberMetaData().isCascadeRemoveOrphans()) {
               dependent = true;
            }

            if (dependent) {
               op.getExecutionContext().deleteObjects(elements.toArray());
            }

            return modified;
         }
      } else {
         return false;
      }
   }

   protected void internalRemoveAt(ObjectProvider op, int index, int size) {
      if (!this.indexedList) {
         throw new NucleusUserException("Cannot remove an element from a particular position with an ordered list since no indexes exist");
      } else {
         this.internalRemoveAt(op, index, this.getRemoveAtStmt(), size);
      }
   }

   protected ListIterator listIterator(ObjectProvider op, int startIdx, int endIdx) {
      ExecutionContext ec = op.getExecutionContext();
      Transaction tx = ec.getTransaction();
      IteratorStatement iterStmt = this.getIteratorStatement(op.getExecutionContext().getClassLoaderResolver(), ec.getFetchPlan(), true, startIdx, endIdx);
      SQLStatement sqlStmt = iterStmt.getSQLStatement();
      StatementClassMapping resultMapping = iterStmt.getStatementClassMapping();
      int inputParamNum = 1;
      StatementMappingIndex ownerIdx = new StatementMappingIndex(this.ownerMapping);
      if (sqlStmt.getNumberOfUnions() > 0) {
         for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
            int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < paramPositions.length; ++k) {
               paramPositions[k] = inputParamNum++;
            }

            ownerIdx.addParameterOccurrence(paramPositions);
         }
      } else {
         int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

         for(int k = 0; k < paramPositions.length; ++k) {
            paramPositions[k] = inputParamNum++;
         }

         ownerIdx.addParameterOccurrence(paramPositions);
      }

      if (tx.getSerializeRead() != null && tx.getSerializeRead()) {
         sqlStmt.addExtension("lock-for-update", true);
      }

      String stmt = sqlStmt.getSelectStatement().toSQL();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         ResultObjectFactory rof;
         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
            int numParams = ownerIdx.getNumberOfParameterOccurrences();

            for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
               ownerIdx.getMapping().setObject(ec, ps, ownerIdx.getParameterPositionsForOccurrence(paramInstance), op.getObject());
            }

            try {
               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
                     if (this.elementMapping instanceof ReferenceMapping) {
                        rof = new ListStoreIterator(op, rs, (ResultObjectFactory)null, this);
                        return rof;
                     }

                     rof = new PersistentClassROF(this.storeMgr, this.emd, resultMapping, false, (FetchPlan)null, this.clr.classForName(this.elementType));
                     ListStoreIterator var18 = new ListStoreIterator(op, rs, rof, this);
                     return var18;
                  }

                  rof = new ListStoreIterator(op, rs, (ResultObjectFactory)null, this);
               } finally {
                  rs.close();
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return rof;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
      } catch (MappedDatastoreException e) {
         throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
      }
   }

   protected String getSetStmt() {
      if (this.setStmt == null) {
         synchronized(this) {
            StringBuilder stmt = (new StringBuilder("UPDATE ")).append(this.containerTable.toString()).append(" SET ");

            for(int i = 0; i < this.elementMapping.getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(",");
               }

               stmt.append(this.elementMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               stmt.append(" = ");
               stmt.append(((AbstractDatastoreMapping)this.elementMapping.getDatastoreMapping(i)).getUpdateInputParameter());
            }

            stmt.append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            if (this.getOwnerMemberMetaData().getOrderMetaData() == null || this.getOwnerMemberMetaData().getOrderMetaData().isIndexedList()) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.orderMapping, (String)null, false);
            }

            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.setStmt = stmt.toString();
         }
      }

      return this.setStmt;
   }

   protected String getRemoveAllStmt(Collection elements) {
      if (elements != null && elements.size() != 0) {
         StringBuilder stmt = (new StringBuilder("DELETE FROM ")).append(this.containerTable.toString()).append(" WHERE ");
         boolean first = true;

         for(Object element : elements) {
            stmt.append(first ? "(" : " OR (");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            BackingStoreHelper.appendWhereClauseForElement(stmt, this.elementMapping, element, this.isElementsAreSerialised(), (String)null, false);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            stmt.append(")");
            first = false;
         }

         return stmt.toString();
      } else {
         return null;
      }
   }

   public IteratorStatement getIteratorStatement(ClassLoaderResolver clr, FetchPlan fp, boolean addRestrictionOnOwner, int startIdx, int endIdx) {
      SQLStatement sqlStmt = null;
      StatementClassMapping stmtClassMapping = new StatementClassMapping();
      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
         if (this.elementMapping instanceof ReferenceMapping) {
            sqlStmt = new SQLStatement(this.storeMgr, this.containerTable, (DatastoreIdentifier)null, (String)null);
            sqlStmt.setClassLoaderResolver(clr);
            sqlStmt.select(sqlStmt.getPrimaryTable(), (JavaTypeMapping)this.elementMapping, (String)null);
         } else {
            for(int i = 0; i < this.elementInfo.length; ++i) {
               Class elementCls = clr.classForName(this.elementInfo[i].getClassName());
               SQLStatement elementStmt = null;
               if (this.elementInfo[i].getDiscriminatorStrategy() != null && this.elementInfo[i].getDiscriminatorStrategy() != DiscriminatorStrategy.NONE) {
                  String elementType = this.ownerMemberMetaData.getCollection().getElementType();
                  if (ClassUtils.isReferenceType(clr.classForName(elementType))) {
                     String[] clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(elementType, clr);
                     Class[] cls = new Class[clsNames.length];

                     for(int j = 0; j < clsNames.length; ++j) {
                        cls[j] = clr.classForName(clsNames[j]);
                     }

                     StatementGenerator stmtGen = new DiscriminatorStatementGenerator(this.storeMgr, clr, cls, true, (DatastoreIdentifier)null, (String)null, this.containerTable, (DatastoreIdentifier)null, this.elementMapping);
                     if (this.allowNulls) {
                        stmtGen.setOption("allowNulls");
                     }

                     elementStmt = stmtGen.getStatement();
                  } else {
                     StatementGenerator stmtGen = new DiscriminatorStatementGenerator(this.storeMgr, clr, elementCls, true, (DatastoreIdentifier)null, (String)null, this.containerTable, (DatastoreIdentifier)null, this.elementMapping);
                     if (this.allowNulls) {
                        stmtGen.setOption("allowNulls");
                     }

                     elementStmt = stmtGen.getStatement();
                  }

                  this.iterateUsingDiscriminator = true;
               } else {
                  StatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, elementCls, true, (DatastoreIdentifier)null, (String)null, this.containerTable, (DatastoreIdentifier)null, this.elementMapping);
                  stmtGen.setOption("selectNucleusType");
                  stmtClassMapping.setNucleusTypeColumnName("NUCLEUS_TYPE");
                  elementStmt = stmtGen.getStatement();
               }

               if (sqlStmt == null) {
                  sqlStmt = elementStmt;
               } else {
                  sqlStmt.union(elementStmt);
               }
            }

            SQLTable elementSqlTbl = sqlStmt.getTable(this.elementInfo[0].getDatastoreClass(), sqlStmt.getPrimaryTable().getGroupName());
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, stmtClassMapping, fp, elementSqlTbl, this.emd, 0);
         }
      } else {
         sqlStmt = new SQLStatement(this.storeMgr, this.containerTable, (DatastoreIdentifier)null, (String)null);
         sqlStmt.setClassLoaderResolver(clr);
         sqlStmt.select(sqlStmt.getPrimaryTable(), (JavaTypeMapping)this.elementMapping, (String)null);
      }

      if (addRestrictionOnOwner) {
         SQLTable ownerSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.ownerMapping);
         SQLExpression ownerExpr = exprFactory.newExpression(sqlStmt, ownerSqlTbl, this.ownerMapping);
         SQLExpression ownerVal = exprFactory.newLiteralParameter(sqlStmt, this.ownerMapping, (Object)null, "OWNER");
         sqlStmt.whereAnd(ownerExpr.eq(ownerVal), true);
      }

      if (this.relationDiscriminatorMapping != null) {
         SQLTable distSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.relationDiscriminatorMapping);
         SQLExpression distExpr = exprFactory.newExpression(sqlStmt, distSqlTbl, this.relationDiscriminatorMapping);
         SQLExpression distVal = exprFactory.newLiteral(sqlStmt, this.relationDiscriminatorMapping, this.relationDiscriminatorValue);
         sqlStmt.whereAnd(distExpr.eq(distVal), true);
      }

      if (this.indexedList) {
         boolean needsOrdering = true;
         if (startIdx == -1 && endIdx == -1) {
            SQLExpression indexExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.orderMapping);
            SQLExpression indexVal = exprFactory.newLiteral(sqlStmt, this.orderMapping, 0);
            sqlStmt.whereAnd(indexExpr.ge(indexVal), true);
         } else if (startIdx >= 0 && endIdx == startIdx) {
            needsOrdering = false;
            SQLExpression indexExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.orderMapping);
            SQLExpression indexVal = exprFactory.newLiteral(sqlStmt, this.orderMapping, startIdx);
            sqlStmt.whereAnd(indexExpr.eq(indexVal), true);
         } else {
            if (startIdx >= 0) {
               SQLExpression indexExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.orderMapping);
               SQLExpression indexVal = exprFactory.newLiteral(sqlStmt, this.orderMapping, startIdx);
               sqlStmt.whereAnd(indexExpr.ge(indexVal), true);
            } else {
               SQLExpression indexExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.orderMapping);
               SQLExpression indexVal = exprFactory.newLiteral(sqlStmt, this.orderMapping, 0);
               sqlStmt.whereAnd(indexExpr.ge(indexVal), true);
            }

            if (endIdx >= 0) {
               SQLExpression indexExpr2 = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.orderMapping);
               SQLExpression indexVal2 = exprFactory.newLiteral(sqlStmt, this.orderMapping, endIdx);
               sqlStmt.whereAnd(indexExpr2.lt(indexVal2), true);
            }
         }

         if (needsOrdering) {
            SQLTable orderSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.orderMapping);
            SQLExpression[] orderExprs = new SQLExpression[this.orderMapping.getNumberOfDatastoreMappings()];
            boolean[] descendingOrder = new boolean[this.orderMapping.getNumberOfDatastoreMappings()];
            orderExprs[0] = exprFactory.newExpression(sqlStmt, orderSqlTbl, this.orderMapping);
            sqlStmt.setOrdering(orderExprs, descendingOrder);
         }
      } else if (this.elementInfo != null && this.elementInfo.length > 0) {
         DatastoreClass elementTbl = this.elementInfo[0].getDatastoreClass();
         OrderMetaData.FieldOrder[] orderComponents = this.ownerMemberMetaData.getOrderMetaData().getFieldOrders();
         SQLExpression[] orderExprs = new SQLExpression[orderComponents.length];
         boolean[] orderDirs = new boolean[orderComponents.length];

         for(int i = 0; i < orderComponents.length; ++i) {
            String fieldName = orderComponents[i].getFieldName();
            JavaTypeMapping fieldMapping = elementTbl.getMemberMapping(this.elementInfo[0].getAbstractClassMetaData().getMetaDataForMember(fieldName));
            orderDirs[i] = !orderComponents[i].isForward();
            SQLTable fieldSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), fieldMapping);
            orderExprs[i] = exprFactory.newExpression(sqlStmt, fieldSqlTbl, fieldMapping);
         }

         sqlStmt.setOrdering(orderExprs, orderDirs);
      }

      return new IteratorStatement(this, sqlStmt, stmtClassMapping);
   }
}
