package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.CollectionMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.OrderMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
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
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class FKListStore extends AbstractListStore {
   private final int ownerFieldNumber;
   private String updateFkStmt;
   private String clearNullifyStmt;
   private String removeAtNullifyStmt;
   private String setStmt;
   private String unsetStmt;

   public FKListStore(AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
      this.setOwner(mmd);
      CollectionMetaData colmd = mmd.getCollection();
      if (colmd == null) {
         throw new NucleusUserException(Localiser.msg("056001", new Object[]{mmd.getFullFieldName()}));
      } else {
         this.elementType = colmd.getElementType();
         Class element_class = clr.classForName(this.elementType);
         if (ClassUtils.isReferenceType(element_class)) {
            this.elementIsPersistentInterface = storeMgr.getNucleusContext().getMetaDataManager().isPersistentInterface(element_class.getName());
            if (this.elementIsPersistentInterface) {
               this.emd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForInterface(element_class, clr);
            } else {
               this.emd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForImplementationOfReference(element_class, (Object)null, clr);
            }
         } else {
            this.emd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(element_class, clr);
         }

         if (this.emd == null) {
            throw new NucleusUserException(Localiser.msg("056003", new Object[]{element_class.getName(), mmd.getFullFieldName()}));
         } else {
            this.elementInfo = this.getElementInformationForClass();
            if (this.elementInfo != null && this.elementInfo.length != 0) {
               if (this.elementInfo.length == 1 && ClassUtils.isReferenceType(element_class)) {
                  this.elementType = this.emd.getFullClassName();
               }

               this.elementMapping = this.elementInfo[0].getDatastoreClass().getIdMapping();
               this.elementsAreEmbedded = false;
               this.elementsAreSerialised = false;
               String mappedByFieldName = mmd.getMappedBy();
               if (mappedByFieldName != null) {
                  AbstractMemberMetaData eofmd = this.elementInfo[0].getAbstractClassMetaData().getMetaDataForMember(mappedByFieldName);
                  if (eofmd == null) {
                     throw new NucleusUserException(Localiser.msg("056024", new Object[]{mmd.getFullFieldName(), mappedByFieldName, element_class.getName()}));
                  }

                  if (!clr.isAssignableFrom(eofmd.getType(), mmd.getAbstractClassMetaData().getFullClassName())) {
                     throw new NucleusUserException(Localiser.msg("056025", new Object[]{mmd.getFullFieldName(), eofmd.getFullFieldName(), eofmd.getTypeName(), mmd.getAbstractClassMetaData().getFullClassName()}));
                  }

                  String ownerFieldName = eofmd.getName();
                  this.ownerFieldNumber = this.elementInfo[0].getAbstractClassMetaData().getAbsolutePositionOfMember(ownerFieldName);
                  this.ownerMapping = this.elementInfo[0].getDatastoreClass().getMemberMapping(eofmd);
                  if (this.ownerMapping == null) {
                     throw new NucleusUserException(Localiser.msg("056029", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.elementType, ownerFieldName}));
                  }

                  if (this.isEmbeddedMapping(this.ownerMapping)) {
                     throw new NucleusUserException(Localiser.msg("056026", new Object[]{ownerFieldName, this.elementType, eofmd.getTypeName(), mmd.getClassName()}));
                  }
               } else {
                  this.ownerFieldNumber = -1;
                  this.ownerMapping = this.elementInfo[0].getDatastoreClass().getExternalMapping(mmd, 5);
                  if (this.ownerMapping == null) {
                     throw new NucleusUserException(Localiser.msg("056030", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.elementType}));
                  }
               }

               this.orderMapping = this.elementInfo[0].getDatastoreClass().getExternalMapping(mmd, 4);
               if (mmd.getOrderMetaData() != null && !mmd.getOrderMetaData().isIndexedList()) {
                  this.indexedList = false;
               }

               if (this.orderMapping == null && this.indexedList) {
                  throw new NucleusUserException(Localiser.msg("056041", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.elementType}));
               } else {
                  this.relationDiscriminatorMapping = this.elementInfo[0].getDatastoreClass().getExternalMapping(mmd, 6);
                  if (this.relationDiscriminatorMapping != null) {
                     this.relationDiscriminatorValue = mmd.getValueForExtension("relation-discriminator-value");
                     if (this.relationDiscriminatorValue == null) {
                        this.relationDiscriminatorValue = mmd.getFullFieldName();
                     }
                  }

                  this.containerTable = this.elementInfo[0].getDatastoreClass();
                  if (mmd.getMappedBy() != null && this.ownerMapping.getTable() != this.containerTable) {
                     this.containerTable = this.ownerMapping.getTable();
                  }

               }
            } else {
               throw new NucleusUserException(Localiser.msg("056075", new Object[]{this.ownerMemberMetaData.getFullFieldName(), this.elementType}));
            }
         }
      }
   }

   public Object set(ObjectProvider op, int index, Object element, boolean allowDependentField) {
      this.validateElementForWriting(op, element, -1);
      E oldElement = (E)this.get(op, index);

      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            String unsetStmt = this.getUnsetStmt();
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, unsetStmt, false);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               if (this.orderMapping != null) {
                  jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, index, jdbcPosition, this.orderMapping);
               }

               if (this.relationDiscriminatorMapping != null) {
                  BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
               }

               sqlControl.executeStatementUpdate(ec, mconn, unsetStmt, ps, true);
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }

            String var37 = this.getSetStmt(element);
            PreparedStatement ps2 = sqlControl.getStatementForUpdate(mconn, var37, false);

            try {
               ElementContainerStore.ElementInfo elemInfo = this.getElementInfoForElement(element);
               JavaTypeMapping elemMapping = this.elementMapping;
               JavaTypeMapping orderMapping = this.orderMapping;
               if (elemInfo != null) {
                  elemMapping = elemInfo.getDatastoreClass().getIdMapping();
                  orderMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 4);
               }

               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps2, jdbcPosition, this);
               if (orderMapping != null) {
                  jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps2, index, jdbcPosition, orderMapping);
               }

               if (this.relationDiscriminatorMapping != null) {
                  jdbcPosition = BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps2, jdbcPosition, this);
               }

               BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps2, element, jdbcPosition, elemMapping);
               sqlControl.executeStatementUpdate(ec, mconn, var37, ps2, true);
            } finally {
               ps2.close();
            }
         } finally {
            mconn.release();
         }
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056015", new Object[]{this.setStmt}), e);
      }

      boolean dependent = this.getOwnerMemberMetaData().getCollection().isDependentElement();
      if (this.getOwnerMemberMetaData().isCascadeRemoveOrphans()) {
         dependent = true;
      }

      if (dependent && allowDependentField && oldElement != null) {
         op.getExecutionContext().deleteObjectInternal(oldElement);
      }

      return oldElement;
   }

   private boolean updateElementFk(ObjectProvider op, Object element, Object owner, int index) {
      if (element == null) {
         return false;
      } else {
         ExecutionContext ec = op.getExecutionContext();
         String updateFkStmt = this.getUpdateFkStmt(element);

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            boolean retval;
            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, updateFkStmt, false);

               try {
                  ElementContainerStore.ElementInfo elemInfo = this.getElementInfoForElement(element);
                  JavaTypeMapping ownerMapping = null;
                  if (this.ownerMemberMetaData.getMappedBy() != null) {
                     ownerMapping = elemInfo.getDatastoreClass().getMemberMapping(this.ownerMemberMetaData.getMappedBy());
                  } else {
                     ownerMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 5);
                  }

                  JavaTypeMapping elemMapping = elemInfo.getDatastoreClass().getIdMapping();
                  JavaTypeMapping orderMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 4);
                  int jdbcPosition = 1;
                  if (owner == null) {
                     if (this.ownerMemberMetaData != null) {
                        ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, ownerMapping), (Object)null, op, this.ownerMemberMetaData.getAbsoluteFieldNumber());
                     } else {
                        ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, ownerMapping), (Object)null);
                     }

                     jdbcPosition += ownerMapping.getNumberOfDatastoreMappings();
                  } else {
                     jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                  }

                  if (orderMapping != null) {
                     jdbcPosition = BackingStoreHelper.populateOrderInStatement(ec, ps, index, jdbcPosition, orderMapping);
                  }

                  if (this.relationDiscriminatorMapping != null) {
                     jdbcPosition = BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                  }

                  BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, elemMapping);
                  sqlControl.executeStatementUpdate(ec, mconn, updateFkStmt, ps, true);
                  retval = true;
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return retval;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056027", new Object[]{updateFkStmt}), e);
         }
      }
   }

   public void update(ObjectProvider op, Collection coll) {
      if (coll != null && !coll.isEmpty()) {
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
      }
   }

   protected boolean internalAdd(ObjectProvider op, int startAt, boolean atEnd, Collection c, int size) {
      if (c != null && c.size() != 0) {
         int currentListSize = size < 0 ? this.size(op) : size;
         boolean shiftingElements = true;
         if (atEnd || startAt == currentListSize) {
            shiftingElements = false;
            startAt = currentListSize;
         }

         boolean elementsNeedPositioning = false;
         int position = startAt;
         Iterator elementIter = c.iterator();

         while(elementIter.hasNext()) {
            if (shiftingElements) {
               position = -1;
            }

            boolean inserted = this.validateElementForWriting(op, elementIter.next(), position);
            if (!inserted || shiftingElements) {
               elementsNeedPositioning = true;
            }

            if (!shiftingElements) {
               ++position;
            }
         }

         if (shiftingElements) {
            try {
               int shift = c.size();
               ExecutionContext ec = op.getExecutionContext();
               ManagedConnection mconn = this.storeMgr.getConnection(ec);

               try {
                  for(int i = currentListSize - 1; i >= startAt; --i) {
                     this.internalShift(op, mconn, true, i, shift, false);
                  }
               } finally {
                  mconn.release();
               }
            } catch (MappedDatastoreException e) {
               throw new NucleusDataStoreException(Localiser.msg("056009", new Object[]{e.getMessage()}), e.getCause());
            }
         }

         if (shiftingElements || elementsNeedPositioning) {
            for(Object element : c) {
               this.updateElementFk(op, element, op.getObject(), startAt);
               ++startAt;
            }
         }

         return true;
      } else {
         return true;
      }
   }

   public boolean removeAll(ObjectProvider op, Collection elements, int size) {
      if (elements != null && elements.size() != 0) {
         boolean modified = false;
         if (this.indexedList) {
            int[] indices = this.getIndicesOf(op, elements);
            if (indices == null) {
               return false;
            }

            for(int i = 0; i < indices.length; ++i) {
               this.internalRemoveAt(op, indices[i], -1);
               modified = true;
            }

            boolean dependent = this.ownerMemberMetaData.getCollection().isDependentElement();
            if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
               dependent = true;
            }

            if (dependent) {
               op.getExecutionContext().deleteObjects(elements.toArray());
            }
         } else {
            for(Object element : elements) {
               this.remove(op, element, size, true);
            }
         }

         return modified;
      } else {
         return false;
      }
   }

   protected boolean internalRemove(ObjectProvider op, Object element, int size) {
      if (this.indexedList) {
         int index = this.indexOf(op, element);
         if (index == -1) {
            return false;
         }

         this.internalRemoveAt(op, index, size);
      } else if (this.ownerMapping.isNullable()) {
         ExecutionContext ec = op.getExecutionContext();
         ObjectProvider elementSM = ec.findObjectProvider(element);
         if (this.relationType == RelationType.ONE_TO_MANY_BI) {
            elementSM.replaceFieldMakeDirty(this.ownerMemberMetaData.getRelatedMemberMetaData(this.clr)[0].getAbsoluteFieldNumber(), (Object)null);
            if (op.getExecutionContext().isFlushing()) {
               elementSM.flush();
            }
         } else {
            this.updateElementFk(op, element, (Object)null, -1);
         }
      } else {
         op.getExecutionContext().deleteObjectInternal(element);
      }

      return true;
   }

   protected void manageRemovalOfElement(ObjectProvider ownerOP, Object element) {
   }

   protected void internalRemoveAt(ObjectProvider op, int index, int size) {
      if (!this.indexedList) {
         throw new NucleusUserException("Cannot remove an element from a particular position with an ordered list since no indexes exist");
      } else {
         boolean nullify = false;
         if (this.ownerMapping.isNullable() && this.orderMapping != null && this.orderMapping.isNullable()) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("056043"));
            nullify = true;
         } else {
            NucleusLogger.DATASTORE.debug(Localiser.msg("056042"));
         }

         String stmt;
         if (nullify) {
            stmt = this.getRemoveAtNullifyStmt();
         } else {
            stmt = this.getRemoveAtStmt();
         }

         this.internalRemoveAt(op, index, stmt, size);
      }
   }

   public void clear(ObjectProvider op) {
      boolean deleteElements = false;
      ExecutionContext ec = op.getExecutionContext();
      boolean dependent = this.ownerMemberMetaData.getCollection().isDependentElement();
      if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
         dependent = true;
      }

      if (dependent) {
         NucleusLogger.DATASTORE.debug(Localiser.msg("056034"));
         deleteElements = true;
      } else if (this.ownerMapping.isNullable() && this.orderMapping == null) {
         NucleusLogger.DATASTORE.debug(Localiser.msg("056036"));
         deleteElements = false;
      } else if (this.ownerMapping.isNullable() && this.orderMapping != null && this.orderMapping.isNullable()) {
         NucleusLogger.DATASTORE.debug(Localiser.msg("056036"));
         deleteElements = false;
      } else {
         NucleusLogger.DATASTORE.debug(Localiser.msg("056035"));
         deleteElements = true;
      }

      if (deleteElements) {
         Iterator elementsIter = this.iterator(op);
         if (elementsIter != null) {
            while(elementsIter.hasNext()) {
               Object element = elementsIter.next();
               if (ec.getApiAdapter().isPersistable(element) && ec.getApiAdapter().isDeleted(element)) {
                  ObjectProvider objSM = ec.findObjectProvider(element);
                  objSM.flush();
               } else {
                  ec.deleteObjectInternal(element);
               }
            }
         }
      } else {
         String clearNullifyStmt = this.getClearNullifyStmt();

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, clearNullifyStmt, false);

               try {
                  int jdbcPosition = 1;
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                  if (this.relationDiscriminatorMapping != null) {
                     BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                  }

                  sqlControl.executeStatementUpdate(ec, mconn, clearNullifyStmt, ps, true);
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056013", new Object[]{clearNullifyStmt}), e);
         }
      }

   }

   protected boolean validateElementForWriting(final ObjectProvider op, Object element, final int index) {
      final Object newOwner = op.getObject();
      ElementContainerStore.ElementInfo info = this.getElementInfoForElement(element);
      final DatastoreClass elementTable;
      if (this.storeMgr.getNucleusContext().getMetaDataManager().isPersistentInterface(this.elementType)) {
         elementTable = this.storeMgr.getDatastoreClass(this.storeMgr.getNucleusContext().getMetaDataManager().getImplementationNameForPersistentInterface(this.elementType), this.clr);
      } else {
         elementTable = this.storeMgr.getDatastoreClass(element.getClass().getName(), this.clr);
      }

      final JavaTypeMapping orderMapping;
      if (info != null) {
         orderMapping = info.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 4);
      } else {
         orderMapping = this.orderMapping;
      }

      boolean inserted = super.validateElementForWriting(op.getExecutionContext(), element, new FieldValues() {
         public void fetchFields(ObjectProvider elemOP) {
            if (elementTable != null) {
               JavaTypeMapping externalFKMapping = elementTable.getExternalMapping(FKListStore.this.ownerMemberMetaData, 5);
               if (externalFKMapping != null) {
                  elemOP.setAssociatedValue(externalFKMapping, op.getObject());
               }

               if (FKListStore.this.relationDiscriminatorMapping != null) {
                  elemOP.setAssociatedValue(FKListStore.this.relationDiscriminatorMapping, FKListStore.this.relationDiscriminatorValue);
               }

               if (orderMapping != null && index >= 0) {
                  if (FKListStore.this.ownerMemberMetaData.getOrderMetaData() != null && FKListStore.this.ownerMemberMetaData.getOrderMetaData().getMappedBy() != null) {
                     Object indexValue = null;
                     if (!orderMapping.getMemberMetaData().getTypeName().equals(ClassNameConstants.JAVA_LANG_LONG) && !orderMapping.getMemberMetaData().getTypeName().equals(ClassNameConstants.LONG)) {
                        indexValue = index;
                     } else {
                        indexValue = (long)index;
                     }

                     elemOP.replaceFieldMakeDirty(orderMapping.getMemberMetaData().getAbsoluteFieldNumber(), indexValue);
                  } else {
                     elemOP.setAssociatedValue(orderMapping, index);
                  }
               }
            }

            if (FKListStore.this.ownerFieldNumber >= 0) {
               Object currentOwner = elemOP.provideField(FKListStore.this.ownerFieldNumber);
               if (currentOwner == null) {
                  NucleusLogger.PERSISTENCE.info(Localiser.msg("056037", new Object[]{op.getObjectAsPrintable(), FKListStore.this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(elemOP.getObject())}));
                  elemOP.replaceFieldMakeDirty(FKListStore.this.ownerFieldNumber, newOwner);
               } else if (currentOwner != newOwner && op.getReferencedPC() == null) {
                  throw new NucleusUserException(Localiser.msg("056038", new Object[]{op.getObjectAsPrintable(), FKListStore.this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(elemOP.getObject()), StringUtils.toJVMIDString(currentOwner)}));
               }
            }

         }

         public void fetchNonLoadedFields(ObjectProvider opx) {
         }

         public FetchPlan getFetchPlanForLoading() {
            return null;
         }
      });
      return inserted;
   }

   protected ListIterator listIterator(ObjectProvider op, int startIdx, int endIdx) {
      ExecutionContext ec = op.getExecutionContext();
      Transaction tx = ec.getTransaction();
      if (this.elementInfo != null && this.elementInfo.length != 0) {
         IteratorStatement iterStmt = this.getIteratorStatement(op.getExecutionContext().getClassLoaderResolver(), ec.getFetchPlan(), true, startIdx, endIdx);
         SQLStatement sqlStmt = iterStmt.getSQLStatement();
         StatementClassMapping resultMapping = iterStmt.getStatementClassMapping();
         int inputParamNum = 1;
         StatementMappingIndex ownerIdx = new StatementMappingIndex(this.ownerMapping);
         if (sqlStmt.getNumberOfUnions() > 0) {
            for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
               int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

               for(int k = 0; k < this.ownerMapping.getNumberOfDatastoreMappings(); ++k) {
                  paramPositions[k] = inputParamNum++;
               }

               ownerIdx.addParameterOccurrence(paramPositions);
            }
         } else {
            int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < this.ownerMapping.getNumberOfDatastoreMappings(); ++k) {
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

            ListStoreIterator var18;
            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
               int numParams = ownerIdx.getNumberOfParameterOccurrences();

               for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
                  ownerIdx.getMapping().setObject(ec, ps, ownerIdx.getParameterPositionsForOccurrence(paramInstance), op.getObject());
               }

               try {
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

                  try {
                     ResultObjectFactory rof = null;
                     if (this.elementsAreEmbedded || this.elementsAreSerialised) {
                        throw new NucleusException("Cannot have FK set with non-persistent objects");
                     }

                     ResultObjectFactory var48 = new PersistentClassROF(this.storeMgr, this.emd, resultMapping, false, (FetchPlan)null, this.clr.classForName(this.elementType));
                     var18 = new ListStoreIterator(op, rs, var48, this);
                  } finally {
                     rs.close();
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return var18;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
         } catch (MappedDatastoreException e) {
            throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
         }
      } else {
         return null;
      }
   }

   private String getUpdateFkStmt(Object element) {
      if (this.elementMapping instanceof ReferenceMapping && this.elementMapping.getNumberOfDatastoreMappings() > 1) {
         return this.getUpdateFkStatementString(element);
      } else {
         if (this.updateFkStmt == null) {
            synchronized(this) {
               this.updateFkStmt = this.getUpdateFkStatementString(element);
            }
         }

         return this.updateFkStmt;
      }
   }

   private String getUpdateFkStatementString(Object element) {
      JavaTypeMapping ownerMapping = this.ownerMapping;
      JavaTypeMapping elemMapping = this.elementMapping;
      JavaTypeMapping orderMapping = this.orderMapping;
      JavaTypeMapping relDiscrimMapping = this.relationDiscriminatorMapping;
      Table table = this.containerTable;
      if (this.elementInfo.length > 1) {
         ElementContainerStore.ElementInfo elemInfo = this.getElementInfoForElement(element);
         if (elemInfo != null) {
            table = elemInfo.getDatastoreClass();
            if (this.ownerMemberMetaData.getMappedBy() != null) {
               ownerMapping = elemInfo.getDatastoreClass().getMemberMapping(this.ownerMemberMetaData.getMappedBy());
            } else {
               ownerMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 5);
            }

            elemMapping = elemInfo.getDatastoreClass().getIdMapping();
            orderMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 4);
            relDiscrimMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 6);
         }
      }

      StringBuilder stmt = (new StringBuilder("UPDATE ")).append(table.toString()).append(" SET ");

      for(int i = 0; i < ownerMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append("=");
         stmt.append(((AbstractDatastoreMapping)ownerMapping.getDatastoreMapping(i)).getUpdateInputParameter());
      }

      if (orderMapping != null) {
         for(int i = 0; i < orderMapping.getNumberOfDatastoreMappings(); ++i) {
            stmt.append(",");
            stmt.append(orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            stmt.append("=");
            stmt.append(((AbstractDatastoreMapping)orderMapping.getDatastoreMapping(i)).getUpdateInputParameter());
         }
      }

      if (relDiscrimMapping != null) {
         for(int i = 0; i < relDiscrimMapping.getNumberOfDatastoreMappings(); ++i) {
            stmt.append(",");
            stmt.append(relDiscrimMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            stmt.append("=");
            stmt.append(((AbstractDatastoreMapping)relDiscrimMapping.getDatastoreMapping(i)).getUpdateInputParameter());
         }
      }

      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForElement(stmt, elemMapping, element, this.elementsAreSerialised, (String)null, true);
      return stmt.toString();
   }

   private String getClearNullifyStmt() {
      if (this.clearNullifyStmt == null) {
         synchronized(this) {
            StringBuilder stmt = new StringBuilder("UPDATE ");
            if (this.elementInfo.length > 1) {
               stmt.append("?");
            } else {
               stmt.append(this.containerTable.toString());
            }

            stmt.append(" SET ");

            for(int i = 0; i < this.ownerMapping.getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(", ");
               }

               stmt.append(this.ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString() + "=NULL");
            }

            if (this.orderMapping != null) {
               for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(", ");
                  stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString() + "=-1");
               }
            }

            if (this.relationDiscriminatorMapping != null) {
               for(int i = 0; i < this.relationDiscriminatorMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(", ");
                  stmt.append(this.relationDiscriminatorMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
                  stmt.append("=NULL");
               }
            }

            stmt.append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.clearNullifyStmt = stmt.toString();
         }
      }

      return this.clearNullifyStmt;
   }

   private String getSetStmt(Object element) {
      if (this.setStmt != null) {
         return this.setStmt;
      } else {
         String stmt = this.getSetStatementString(element);
         if (this.elementInfo.length == 1) {
            this.setStmt = stmt;
         }

         return stmt;
      }
   }

   private String getSetStatementString(Object element) {
      ElementContainerStore.ElementInfo elemInfo = this.getElementInfoForElement(element);
      Table table = this.containerTable;
      JavaTypeMapping ownerMapping = this.ownerMapping;
      JavaTypeMapping elemMapping = this.elementMapping;
      JavaTypeMapping relDiscrimMapping = this.relationDiscriminatorMapping;
      JavaTypeMapping orderMapping = this.orderMapping;
      if (elemInfo != null) {
         table = elemInfo.getDatastoreClass();
         elemMapping = elemInfo.getDatastoreClass().getIdMapping();
         if (this.ownerMemberMetaData.getMappedBy() != null) {
            ownerMapping = table.getMemberMapping(elemInfo.getAbstractClassMetaData().getMetaDataForMember(this.ownerMemberMetaData.getMappedBy()));
         } else {
            ownerMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 5);
         }

         orderMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 4);
         relDiscrimMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 6);
      }

      StringBuilder stmt = (new StringBuilder("UPDATE ")).append(table.toString()).append(" SET ");

      for(int i = 0; i < ownerMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append(" = ");
         stmt.append(((AbstractDatastoreMapping)ownerMapping.getDatastoreMapping(i)).getUpdateInputParameter());
      }

      if (orderMapping != null) {
         for(int i = 0; i < orderMapping.getNumberOfDatastoreMappings(); ++i) {
            stmt.append(",");
            stmt.append(orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            stmt.append(" = ");
            stmt.append(((AbstractDatastoreMapping)orderMapping.getDatastoreMapping(i)).getUpdateInputParameter());
         }
      }

      if (relDiscrimMapping != null) {
         for(int i = 0; i < relDiscrimMapping.getNumberOfDatastoreMappings(); ++i) {
            stmt.append(",");
            stmt.append(relDiscrimMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            stmt.append(" = ");
            stmt.append(((AbstractDatastoreMapping)relDiscrimMapping.getDatastoreMapping(i)).getUpdateInputParameter());
         }
      }

      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForElement(stmt, elemMapping, element, this.isElementsAreSerialised(), (String)null, true);
      return stmt.toString();
   }

   private String getUnsetStmt() {
      if (this.unsetStmt == null) {
         synchronized(this) {
            StringBuilder stmt = new StringBuilder("UPDATE ");
            stmt.append(this.containerTable.toString());
            stmt.append(" SET ");

            for(int i = 0; i < this.ownerMapping.getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(",");
               }

               stmt.append(this.ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               stmt.append("=NULL");
            }

            if (this.orderMapping != null) {
               for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(",");
                  stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
                  stmt.append("=-1");
               }
            }

            if (this.relationDiscriminatorMapping != null) {
               for(int i = 0; i < this.relationDiscriminatorMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(",");
                  stmt.append(this.relationDiscriminatorMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
                  stmt.append(" = NULL");
               }
            }

            stmt.append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.orderMapping, (String)null, false);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.unsetStmt = stmt.toString();
         }
      }

      return this.unsetStmt;
   }

   private String getRemoveAtNullifyStmt() {
      if (this.removeAtNullifyStmt == null) {
         synchronized(this) {
            StringBuilder stmt = new StringBuilder("UPDATE ");
            if (this.elementInfo.length > 1) {
               stmt.append("?");
            } else {
               stmt.append(this.containerTable.toString());
            }

            stmt.append(" SET ");

            for(int i = 0; i < this.ownerMapping.getNumberOfDatastoreMappings(); ++i) {
               if (i > 0) {
                  stmt.append(", ");
               }

               stmt.append(this.ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               stmt.append("=NULL");
            }

            if (this.orderMapping != null) {
               for(int i = 0; i < this.orderMapping.getNumberOfDatastoreMappings(); ++i) {
                  stmt.append(", ");
                  stmt.append(this.orderMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
                  stmt.append("=-1");
               }
            }

            stmt.append(" WHERE ");
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
            BackingStoreHelper.appendWhereClauseForMapping(stmt, this.orderMapping, (String)null, false);
            if (this.relationDiscriminatorMapping != null) {
               BackingStoreHelper.appendWhereClauseForMapping(stmt, this.relationDiscriminatorMapping, (String)null, false);
            }

            this.removeAtNullifyStmt = stmt.toString();
         }
      }

      return this.removeAtNullifyStmt;
   }

   public IteratorStatement getIteratorStatement(ClassLoaderResolver clr, FetchPlan fp, boolean addRestrictionOnOwner, int startIdx, int endIdx) {
      SQLStatement sqlStmt = null;
      StatementClassMapping stmtClassMapping = new StatementClassMapping();
      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      if (this.elementInfo.length == 1 && this.elementInfo[0].getDatastoreClass().getDiscriminatorMetaData() != null && this.elementInfo[0].getDatastoreClass().getDiscriminatorMetaData().getStrategy() != DiscriminatorStrategy.NONE) {
         String elementType = this.ownerMemberMetaData.getCollection().getElementType();
         if (!ClassUtils.isReferenceType(clr.classForName(elementType))) {
            sqlStmt = (new DiscriminatorStatementGenerator(this.storeMgr, clr, clr.classForName(this.elementInfo[0].getClassName()), true, (DatastoreIdentifier)null, (String)null)).getStatement();
         } else {
            String[] clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(elementType, clr);
            Class[] cls = new Class[clsNames.length];

            for(int i = 0; i < clsNames.length; ++i) {
               cls[i] = clr.classForName(clsNames[i]);
            }

            sqlStmt = (new DiscriminatorStatementGenerator(this.storeMgr, clr, cls, true, (DatastoreIdentifier)null, (String)null)).getStatement();
         }

         this.iterateUsingDiscriminator = true;
         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, stmtClassMapping, fp, sqlStmt.getPrimaryTable(), this.emd, 0);
      } else {
         for(int i = 0; i < this.elementInfo.length; ++i) {
            Class elementCls = clr.classForName(this.elementInfo[i].getClassName());
            UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, elementCls, true, (DatastoreIdentifier)null, (String)null);
            stmtGen.setOption("selectNucleusType");
            stmtClassMapping.setNucleusTypeColumnName("NUCLEUS_TYPE");
            SQLStatement subStmt = stmtGen.getStatement();
            if (sqlStmt == null) {
               if (this.elementInfo.length > 1) {
                  SQLStatementHelper.selectIdentityOfCandidateInStatement(subStmt, stmtClassMapping, this.elementInfo[i].getAbstractClassMetaData());
               } else {
                  SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(subStmt, stmtClassMapping, fp, subStmt.getPrimaryTable(), this.elementInfo[i].getAbstractClassMetaData(), 0);
               }
            } else if (this.elementInfo.length > 1) {
               SQLStatementHelper.selectIdentityOfCandidateInStatement(subStmt, (StatementClassMapping)null, this.elementInfo[i].getAbstractClassMetaData());
            } else {
               SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(subStmt, (StatementClassMapping)null, fp, subStmt.getPrimaryTable(), this.elementInfo[i].getAbstractClassMetaData(), 0);
            }

            if (sqlStmt == null) {
               sqlStmt = subStmt;
            } else {
               sqlStmt.union(subStmt);
            }
         }

         if (sqlStmt == null) {
            throw new NucleusException("Unable to generate iterator statement for field=" + this.getOwnerMemberMetaData().getFullFieldName());
         }
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
      } else {
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
