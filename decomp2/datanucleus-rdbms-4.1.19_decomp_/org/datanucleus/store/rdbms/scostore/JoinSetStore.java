package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.fieldmanager.DynamicSchemaFieldManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
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
import org.datanucleus.store.types.SCO;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class JoinSetStore extends AbstractSetStore {
   protected String locateStmt;
   protected String maxOrderColumnIdStmt;

   public JoinSetStore(AbstractMemberMetaData mmd, CollectionTable joinTable, ClassLoaderResolver clr) {
      super(joinTable.getStoreManager(), clr);
      this.containerTable = joinTable;
      this.setOwner(mmd);
      this.ownerMapping = joinTable.getOwnerMapping();
      this.elementMapping = joinTable.getElementMapping();
      this.orderMapping = joinTable.getOrderMapping();
      this.relationDiscriminatorMapping = joinTable.getRelationDiscriminatorMapping();
      this.relationDiscriminatorValue = joinTable.getRelationDiscriminatorValue();
      this.elementType = mmd.getCollection().getElementType();
      this.elementsAreEmbedded = joinTable.isEmbeddedElement();
      this.elementsAreSerialised = joinTable.isSerialisedElement();
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
            if (this.emd != null && !this.elementsAreEmbedded) {
               this.elementInfo = this.getElementInformationForClass();
            } else {
               this.elementInfo = null;
            }
         }
      }

   }

   public void update(ObjectProvider op, Collection coll) {
      if (coll != null && !coll.isEmpty()) {
         if (!this.ownerMemberMetaData.getCollection().isSerializedElement() && !this.ownerMemberMetaData.getCollection().isEmbeddedElement()) {
            Iterator elemIter = this.iterator(op);
            Collection existing = new HashSet();

            while(elemIter.hasNext()) {
               Object elem = elemIter.next();
               if (!coll.contains(elem)) {
                  this.remove(op, elem, -1, true);
               } else {
                  existing.add(elem);
               }
            }

            if (existing.size() != coll.size()) {
               for(Object elem : coll) {
                  if (!existing.contains(elem)) {
                     this.add(op, elem, 0);
                  }
               }
            }

         } else {
            this.clear(op);
            this.addAll(op, coll, 0);
         }
      } else {
         this.clear(op);
      }
   }

   private boolean elementAlreadyContainsOwnerInMtoN(ObjectProvider ownerOP, Object element) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      if (ec.getOperationQueue() != null) {
         if (this.locate(ownerOP, element)) {
            NucleusLogger.DATASTORE.info(Localiser.msg("056040", new Object[]{this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(ownerOP.getObject()), element}));
            return true;
         } else {
            return false;
         }
      } else {
         ObjectProvider elementOP = ec.findObjectProvider(element);
         if (elementOP != null) {
            AbstractMemberMetaData[] relatedMmds = this.ownerMemberMetaData.getRelatedMemberMetaData(ec.getClassLoaderResolver());
            Object elementColl = elementOP.provideField(relatedMmds[0].getAbsoluteFieldNumber());
            if (elementColl != null && elementColl instanceof Collection && elementColl instanceof SCO && ((Collection)elementColl).contains(ownerOP.getObject())) {
               NucleusLogger.DATASTORE.info(Localiser.msg("056040", new Object[]{this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(ownerOP.getObject()), element}));
               return true;
            }
         } else if (this.locate(ownerOP, element)) {
            NucleusLogger.DATASTORE.info(Localiser.msg("056040", new Object[]{this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(ownerOP.getObject()), element}));
            return true;
         }

         return false;
      }
   }

   public boolean add(ObjectProvider op, Object element, int size) {
      ExecutionContext ec = op.getExecutionContext();
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

      boolean modified = false;
      boolean toBeInserted = true;
      if (this.relationType == RelationType.MANY_TO_MANY_BI) {
         toBeInserted = !this.elementAlreadyContainsOwnerInMtoN(op, element);
      }

      if (toBeInserted) {
         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);

            try {
               int orderID = -1;
               if (this.orderMapping != null) {
                  orderID = this.getNextIDForOrderColumn(op);
               }

               int[] returnCode = this.doInternalAdd(op, element, mconn, false, orderID, true);
               if (returnCode[0] > 0) {
                  modified = true;
               }
            } finally {
               mconn.release();
            }
         } catch (MappedDatastoreException e) {
            String msg = Localiser.msg("056009", new Object[]{e.getMessage()});
            NucleusLogger.DATASTORE.error(msg, e);
            throw new NucleusDataStoreException(msg, e);
         }
      }

      return modified;
   }

   public boolean addAll(ObjectProvider op, Collection elements, int size) {
      if (elements != null && elements.size() != 0) {
         List exceptions = new ArrayList();
         boolean batched = elements.size() > 1;
         ExecutionContext ec = op.getExecutionContext();

         for(Object element : elements) {
            this.validateElementForWriting(ec, element, (FieldValues)null);
            if (this.relationType == RelationType.ONE_TO_MANY_BI) {
               ObjectProvider elementOP = op.getExecutionContext().findObjectProvider(element);
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

         boolean modified = false;

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);

            try {
               SQLController sqlControl = this.storeMgr.getSQLController();

               try {
                  sqlControl.processStatementsForConnection(mconn);
               } catch (SQLException e) {
                  throw new MappedDatastoreException("SQLException", e);
               }

               int nextOrderID = 0;
               if (this.orderMapping != null) {
                  nextOrderID = this.getNextIDForOrderColumn(op);
               }

               Iterator var25 = elements.iterator();
               E element = (E)null;

               while(var25.hasNext()) {
                  element = (E)var25.next();

                  try {
                     boolean toBeInserted = true;
                     if (this.relationType == RelationType.MANY_TO_MANY_BI) {
                        toBeInserted = !this.elementAlreadyContainsOwnerInMtoN(op, element);
                     }

                     if (toBeInserted) {
                        int[] rc = this.doInternalAdd(op, element, mconn, batched, nextOrderID, !batched || batched && !var25.hasNext());
                        if (rc != null) {
                           for(int i = 0; i < rc.length; ++i) {
                              if (rc[i] > 0) {
                                 modified = true;
                              }
                           }
                        }

                        ++nextOrderID;
                     }
                  } catch (MappedDatastoreException mde) {
                     exceptions.add(mde);
                     NucleusLogger.DATASTORE.error("Exception thrown", mde);
                  }
               }
            } finally {
               mconn.release();
            }
         } catch (MappedDatastoreException e) {
            exceptions.add(e);
            NucleusLogger.DATASTORE.error("Exception thrown", e);
         }

         if (!exceptions.isEmpty()) {
            String msg = Localiser.msg("056009", new Object[]{((Exception)exceptions.get(0)).getMessage()});
            NucleusLogger.DATASTORE.error(msg);
            throw new NucleusDataStoreException(msg, (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]), op.getObject());
         } else {
            return modified;
         }
      } else {
         return false;
      }
   }

   public boolean removeAll(ObjectProvider op, Collection elements, int size) {
      if (elements != null && elements.size() != 0) {
         boolean modified = this.removeAllInternal(op, elements, size);
         boolean dependent = this.ownerMemberMetaData.getCollection().isDependentElement();
         if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
            dependent = true;
         }

         if (dependent) {
            op.getExecutionContext().deleteObjects(elements.toArray());
         }

         return modified;
      } else {
         return false;
      }
   }

   protected boolean removeAllInternal(ObjectProvider op, Collection elements, int size) {
      boolean modified = false;
      String removeAllStmt = this.getRemoveAllStmt(op, elements);

      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

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

         return modified;
      } catch (SQLException e) {
         NucleusLogger.DATASTORE.error("Exception on removeAll", e);
         throw new NucleusDataStoreException(Localiser.msg("056012", new Object[]{removeAllStmt}), e);
      }
   }

   protected String getRemoveStmt(Object element) {
      StringBuilder stmt = (new StringBuilder("DELETE FROM ")).append(this.containerTable.toString()).append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      BackingStoreHelper.appendWhereClauseForElement(stmt, this.elementMapping, element, this.elementsAreSerialised, (String)null, false);
      if (this.relationDiscriminatorMapping != null) {
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
      }

      return stmt.toString();
   }

   protected String getRemoveAllStmt(ObjectProvider op, Collection elements) {
      if (elements != null && elements.size() != 0) {
         StringBuilder stmt = (new StringBuilder("DELETE FROM ")).append(this.containerTable.toString()).append(" WHERE ");
         Iterator elementsIter = elements.iterator();

         for(boolean first = true; elementsIter.hasNext(); first = false) {
            Object element = elementsIter.next();
            stmt.append(first ? "(" : " OR (");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            BackingStoreHelper.appendWhereClauseForElement(stmt, this.elementMapping, element, this.elementsAreSerialised, (String)null, false);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            stmt.append(")");
         }

         return stmt.toString();
      } else {
         return null;
      }
   }

   private boolean locate(ObjectProvider op, Object element) {
      boolean exists = true;
      String stmt = this.getLocateStmt(element);

      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               jdbcPosition = BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
               }

               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  if (!rs.next()) {
                     exists = false;
                  }
               } catch (SQLException var22) {
                  rs.close();
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return exists;
      } catch (SQLException e) {
         NucleusLogger.DATASTORE.error(Localiser.msg("RDBMS.SCO.LocateRequestFailed", new Object[]{stmt}), e);
         throw new NucleusDataStoreException(Localiser.msg("RDBMS.SCO.LocateRequestFailed", new Object[]{stmt}), e);
      }
   }

   protected int[] doInternalAdd(ObjectProvider op, Object element, ManagedConnection conn, boolean batched, int orderId, boolean executeNow) throws MappedDatastoreException {
      if (this.storeMgr.getBooleanObjectProperty("datanucleus.rdbms.dynamicSchemaUpdates")) {
         DynamicSchemaFieldManager dynamicSchemaFM = new DynamicSchemaFieldManager(this.storeMgr, op);
         Collection coll = new HashSet();
         coll.add(element);
         dynamicSchemaFM.storeObjectField(this.ownerMemberMetaData.getAbsoluteFieldNumber(), coll);
         if (dynamicSchemaFM.hasPerformedSchemaUpdates()) {
            this.invalidateAddStmt();
         }
      }

      String addStmt = this.getAddStmtForJoinTable();
      boolean notYetFlushedError = false;
      ExecutionContext ec = op.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         PreparedStatement ps = sqlControl.getStatementForUpdate(conn, addStmt, batched);

         int[] var13;
         try {
            int jdbcPosition = 1;
            jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
            jdbcPosition = BackingStoreHelper.populateElementInStatement(ec, ps, element, jdbcPosition, this.elementMapping);
            if (this.orderMapping != null) {
               jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, orderId, jdbcPosition, this.orderMapping);
            }

            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
            }

            var13 = sqlControl.executeStatementUpdate(ec, conn, addStmt, ps, executeNow);
         } catch (NotYetFlushedException nfe) {
            notYetFlushedError = true;
            throw nfe;
         } finally {
            if (notYetFlushedError) {
               sqlControl.abortStatementForConnection(conn, ps);
            } else {
               sqlControl.closeStatement(conn, ps);
            }

         }

         return var13;
      } catch (SQLException e) {
         throw new MappedDatastoreException(addStmt, e);
      }
   }

   private synchronized String getLocateStmt(Object element) {
      if (this.elementMapping instanceof ReferenceMapping && this.elementMapping.getNumberOfDatastoreMappings() > 1) {
         return this.getLocateStatementString(element);
      } else {
         if (this.locateStmt == null) {
            synchronized(this) {
               this.locateStmt = this.getLocateStatementString(element);
            }
         }

         return this.locateStmt;
      }
   }

   private String getLocateStatementString(Object element) {
      StringBuilder stmt = (new StringBuilder("SELECT 1 FROM ")).append(this.containerTable.toString()).append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      BackingStoreHelper.appendWhereClauseForElement(stmt, this.elementMapping, element, this.elementsAreSerialised, (String)null, false);
      if (this.relationDiscriminatorMapping != null) {
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
      }

      return stmt.toString();
   }

   protected int getNextIDForOrderColumn(ObjectProvider op) {
      ExecutionContext ec = op.getExecutionContext();
      String stmt = this.getMaxOrderColumnIdStmt();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         int nextID;
         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
               }

               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  if (!rs.next()) {
                     nextID = 1;
                  } else {
                     nextID = rs.getInt(1) + 1;
                  }

                  JDBCUtils.logWarnings(rs);
               } finally {
                  rs.close();
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return nextID;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056020", new Object[]{stmt}), e);
      }
   }

   private synchronized String getMaxOrderColumnIdStmt() {
      if (this.maxOrderColumnIdStmt == null) {
         synchronized(this) {
            StringBuilder stmt = new StringBuilder("SELECT MAX(" + this.orderMapping.getDatastoreMapping(0).getColumn().getIdentifier().toString() + ")");
            stmt.append(" FROM ").append(this.containerTable.toString()).append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.maxOrderColumnIdStmt = stmt.toString();
         }
      }

      return this.maxOrderColumnIdStmt;
   }

   public Iterator iterator(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      IteratorStatement iterStmt = this.getIteratorStatement(ec.getClassLoaderResolver(), ec.getFetchPlan(), true);
      SQLStatement sqlStmt = iterStmt.sqlStmt;
      StatementClassMapping iteratorMappingClass = iterStmt.stmtClassMapping;
      int inputParamNum = 1;
      StatementMappingIndex ownerStmtMapIdx = new StatementMappingIndex(this.ownerMapping);
      if (sqlStmt.getNumberOfUnions() > 0) {
         for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
            int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < paramPositions.length; ++k) {
               paramPositions[k] = inputParamNum++;
            }

            ownerStmtMapIdx.addParameterOccurrence(paramPositions);
         }
      } else {
         int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

         for(int k = 0; k < paramPositions.length; ++k) {
            paramPositions[k] = inputParamNum++;
         }

         ownerStmtMapIdx.addParameterOccurrence(paramPositions);
      }

      if (ec.getTransaction().getSerializeRead() != null && ec.getTransaction().getSerializeRead()) {
         sqlStmt.addExtension("lock-for-update", true);
      }

      String stmt = sqlStmt.getSelectStatement().toSQL();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         ResultObjectFactory rof;
         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
            int numParams = ownerStmtMapIdx.getNumberOfParameterOccurrences();

            for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
               ownerStmtMapIdx.getMapping().setObject(ec, ps, ownerStmtMapIdx.getParameterPositionsForOccurrence(paramInstance), ownerOP.getObject());
            }

            try {
               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
                     if (this.elementMapping instanceof ReferenceMapping) {
                        rof = new CollectionStoreIterator(ownerOP, rs, (ResultObjectFactory)null, this);
                        return rof;
                     }

                     rof = new PersistentClassROF(this.storeMgr, this.emd, iteratorMappingClass, false, (FetchPlan)null, this.clr.classForName(this.elementType));
                     CollectionStoreIterator var15 = new CollectionStoreIterator(ownerOP, rs, rof, this);
                     return var15;
                  }

                  rof = new CollectionStoreIterator(ownerOP, rs, (ResultObjectFactory)null, this);
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

   public IteratorStatement getIteratorStatement(ClassLoaderResolver clr, FetchPlan fp, boolean addRestrictionOnOwner) {
      SQLStatement sqlStmt = null;
      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      StatementClassMapping iteratorMappingClass = null;
      if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
         if (this.elementMapping instanceof ReferenceMapping) {
            sqlStmt = new SQLStatement(this.storeMgr, this.containerTable, (DatastoreIdentifier)null, (String)null);
            sqlStmt.setClassLoaderResolver(clr);
            sqlStmt.select(sqlStmt.getPrimaryTable(), (JavaTypeMapping)this.elementMapping, (String)null);
         } else {
            iteratorMappingClass = new StatementClassMapping();

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
                  iteratorMappingClass.setNucleusTypeColumnName("NUCLEUS_TYPE");
                  elementStmt = stmtGen.getStatement();
               }

               if (sqlStmt == null) {
                  sqlStmt = elementStmt;
                  SQLTable elementSqlTbl = elementStmt.getTable(this.elementInfo[i].getDatastoreClass(), elementStmt.getPrimaryTable().getGroupName());
                  SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(elementStmt, iteratorMappingClass, fp, elementSqlTbl, this.emd, 0);
               } else {
                  SQLTable elementSqlTbl = elementStmt.getTable(this.elementInfo[i].getDatastoreClass(), elementStmt.getPrimaryTable().getGroupName());
                  SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(elementStmt, iteratorMappingClass, fp, elementSqlTbl, this.emd, 0);
                  sqlStmt.union(elementStmt);
               }
            }
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

      if (this.orderMapping != null) {
         SQLTable orderSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.orderMapping);
         SQLExpression[] orderExprs = new SQLExpression[this.orderMapping.getNumberOfDatastoreMappings()];
         boolean[] descendingOrder = new boolean[this.orderMapping.getNumberOfDatastoreMappings()];
         orderExprs[0] = exprFactory.newExpression(sqlStmt, orderSqlTbl, this.orderMapping);
         sqlStmt.setOrdering(orderExprs, descendingOrder);
      }

      return new IteratorStatement(this, sqlStmt, iteratorMappingClass);
   }
}
