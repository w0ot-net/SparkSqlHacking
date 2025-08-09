package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.CollectionMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.state.RelationshipManager;
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

public class FKSetStore extends AbstractSetStore {
   private final int ownerFieldNumber;
   private String updateFkStmt;
   private String clearNullifyStmt;

   public FKSetStore(AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
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
               this.elementMapping = this.elementInfo[0].getDatastoreClass().getIdMapping();
               this.elementsAreEmbedded = false;
               this.elementsAreSerialised = false;
               if (mmd.getMappedBy() != null) {
                  AbstractMemberMetaData eofmd = this.emd.getMetaDataForMember(mmd.getMappedBy());
                  if (eofmd == null) {
                     throw new NucleusUserException(Localiser.msg("056024", new Object[]{mmd.getFullFieldName(), mmd.getMappedBy(), element_class.getName()}));
                  }

                  String ownerFieldName = eofmd.getName();
                  this.ownerFieldNumber = this.emd.getAbsolutePositionOfMember(ownerFieldName);
                  this.ownerMapping = this.elementInfo[0].getDatastoreClass().getMemberMapping(eofmd);
                  if (this.ownerMapping == null && this.elementInfo.length > 1) {
                     this.ownerMapping = this.elementInfo[0].getDatastoreClass().getMemberMapping(eofmd.getName());
                  }

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

            } else {
               throw new NucleusUserException(Localiser.msg("056075", new Object[]{this.ownerMemberMetaData.getFullFieldName(), this.elementType}));
            }
         }
      }
   }

   protected int getFieldNumberInElementForBidirectional(ObjectProvider op) {
      return this.ownerFieldNumber < 0 ? -1 : op.getClassMetaData().getAbsolutePositionOfMember(this.ownerMemberMetaData.getMappedBy());
   }

   private boolean updateElementFk(ObjectProvider op, Object element, Object owner) {
      if (element == null) {
         return false;
      } else {
         this.validateElementForWriting(op.getExecutionContext(), element, (FieldValues)null);
         ExecutionContext ec = op.getExecutionContext();
         String stmt = this.getUpdateFkStmt(element);

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            boolean retval;
            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, false);

               try {
                  ElementContainerStore.ElementInfo elemInfo = this.getElementInfoForElement(element);
                  JavaTypeMapping ownerMapping = null;
                  if (this.ownerMemberMetaData.getMappedBy() != null) {
                     ownerMapping = elemInfo.getDatastoreClass().getMemberMapping(this.ownerMemberMetaData.getMappedBy());
                  } else {
                     ownerMapping = elemInfo.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 5);
                  }

                  JavaTypeMapping elemMapping = elemInfo.getDatastoreClass().getIdMapping();
                  int jdbcPosition = 1;
                  if (owner == null) {
                     ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, ownerMapping), (Object)null, op, this.ownerMemberMetaData.getAbsoluteFieldNumber());
                  } else {
                     ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, ownerMapping), op.getObject(), op, this.ownerMemberMetaData.getAbsoluteFieldNumber());
                  }

                  jdbcPosition += ownerMapping.getNumberOfDatastoreMappings();
                  if (this.relationDiscriminatorMapping != null) {
                     jdbcPosition = BackingStoreHelper.populateRelationDiscriminatorInStatement(ec, ps, jdbcPosition, this);
                  }

                  BackingStoreHelper.populateElementForWhereClauseInStatement(ec, ps, element, jdbcPosition, elemMapping);
                  sqlControl.executeStatementUpdate(ec, mconn, stmt, ps, true);
                  retval = true;
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return retval;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056027", new Object[]{stmt}), e);
         }
      }
   }

   public void update(ObjectProvider op, Collection coll) {
      if (coll != null && !coll.isEmpty()) {
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
      }
   }

   public boolean add(final ObjectProvider op, Object element, int size) {
      if (element == null) {
         throw new NucleusUserException(Localiser.msg("056039"));
      } else {
         final Object newOwner = op.getObject();
         final ExecutionContext ec = op.getExecutionContext();
         boolean isPersistentInterface = this.storeMgr.getNucleusContext().getMetaDataManager().isPersistentInterface(this.elementType);
         final DatastoreClass elementTable = null;
         if (isPersistentInterface) {
            elementTable = this.storeMgr.getDatastoreClass(this.storeMgr.getNucleusContext().getMetaDataManager().getImplementationNameForPersistentInterface(this.elementType), this.clr);
         } else {
            Class elementTypeCls = this.clr.classForName(this.elementType);
            if (elementTypeCls.isInterface()) {
               elementTable = this.storeMgr.getDatastoreClass(element.getClass().getName(), this.clr);
            } else {
               elementTable = this.storeMgr.getDatastoreClass(this.elementType, this.clr);
            }
         }

         if (elementTable == null) {
            AbstractClassMetaData[] managingCmds = this.storeMgr.getClassesManagingTableForClass(this.emd, this.clr);
            if (managingCmds != null && managingCmds.length > 0) {
               for(int i = 0; i < managingCmds.length; ++i) {
                  Class tblCls = this.clr.classForName(managingCmds[i].getFullClassName());
                  if (tblCls.isAssignableFrom(element.getClass())) {
                     elementTable = this.storeMgr.getDatastoreClass(managingCmds[i].getFullClassName(), this.clr);
                     break;
                  }
               }
            }
         }

         boolean inserted = this.validateElementForWriting(ec, element, new FieldValues() {
            public void fetchFields(ObjectProvider elementOP) {
               if (elementTable != null) {
                  JavaTypeMapping externalFKMapping = elementTable.getExternalMapping(FKSetStore.this.ownerMemberMetaData, 5);
                  if (externalFKMapping != null) {
                     elementOP.setAssociatedValue(externalFKMapping, op.getObject());
                  }

                  if (FKSetStore.this.relationDiscriminatorMapping != null) {
                     elementOP.setAssociatedValue(FKSetStore.this.relationDiscriminatorMapping, FKSetStore.this.relationDiscriminatorValue);
                  }
               }

               int fieldNumInElement = FKSetStore.this.getFieldNumberInElementForBidirectional(elementOP);
               if (fieldNumInElement >= 0) {
                  Object currentOwner = elementOP.provideField(fieldNumInElement);
                  if (currentOwner == null) {
                     NucleusLogger.PERSISTENCE.info(Localiser.msg("056037", new Object[]{op.getObjectAsPrintable(), FKSetStore.this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(elementOP.getObject())}));
                     elementOP.replaceFieldMakeDirty(fieldNumInElement, newOwner);
                  } else if (currentOwner != newOwner) {
                     Object ownerId1 = ec.getApiAdapter().getIdForObject(currentOwner);
                     Object ownerId2 = ec.getApiAdapter().getIdForObject(newOwner);
                     if (ownerId1 != null && ownerId2 != null && ownerId1.equals(ownerId2)) {
                        if (!elementOP.getExecutionContext().getApiAdapter().isDetached(newOwner)) {
                           elementOP.replaceField(fieldNumInElement, newOwner);
                        }
                     } else if (op.getReferencedPC() == null) {
                        throw new NucleusUserException(Localiser.msg("056038", new Object[]{op.getObjectAsPrintable(), FKSetStore.this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(elementOP.getObject()), StringUtils.toJVMIDString(currentOwner)}));
                     }
                  }
               }

            }

            public void fetchNonLoadedFields(ObjectProvider opx) {
            }

            public FetchPlan getFetchPlanForLoading() {
               return null;
            }
         });
         if (inserted) {
            return true;
         } else {
            ObjectProvider elementOP = ec.findObjectProvider(element);
            if (elementOP == null) {
               Object elementId = ec.getApiAdapter().getIdForObject(element);
               if (elementId != null) {
                  element = (E)ec.findObject(elementId, false, false, element.getClass().getName());
                  if (element != null) {
                     elementOP = ec.findObjectProvider(element);
                  }
               }
            }

            int fieldNumInElement = this.getFieldNumberInElementForBidirectional(elementOP);
            if (fieldNumInElement >= 0 && elementOP != null) {
               elementOP.isLoaded(fieldNumInElement);
               Object oldOwner = elementOP.provideField(fieldNumInElement);
               if (oldOwner != newOwner) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("055009", new Object[]{op.getObjectAsPrintable(), this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(element)}));
                  }

                  elementOP.replaceFieldMakeDirty(fieldNumInElement, newOwner);
                  if (ec.getManageRelations()) {
                     RelationshipManager relationshipManager = ec.getRelationshipManager(elementOP);
                     relationshipManager.relationChange(fieldNumInElement, oldOwner, newOwner);
                     if (ec.isFlushing()) {
                        relationshipManager.process();
                     }
                  }

                  if (ec.isFlushing()) {
                     elementOP.flush();
                  }
               }

               return oldOwner != newOwner;
            } else {
               boolean contained = this.contains(op, element);
               return contained ? false : this.updateElementFk(op, element, newOwner);
            }
         }
      }
   }

   public boolean addAll(ObjectProvider op, Collection elements, int size) {
      if (elements != null && elements.size() != 0) {
         boolean success = false;
         Iterator<E> iter = elements.iterator();

         while(iter.hasNext()) {
            if (this.add(op, iter.next(), -1)) {
               success = true;
            }
         }

         return success;
      } else {
         return false;
      }
   }

   public boolean remove(ObjectProvider op, Object element, int size, boolean allowDependentField) {
      if (element == null) {
         return false;
      } else if (!this.validateElementForReading(op, element)) {
         return false;
      } else {
         Object elementToRemove = element;
         ExecutionContext ec = op.getExecutionContext();
         if (ec.getApiAdapter().isDetached(element)) {
            elementToRemove = ec.findObject(ec.getApiAdapter().getIdForObject(element), true, false, element.getClass().getName());
         }

         ObjectProvider elementOP = ec.findObjectProvider(elementToRemove);
         Object oldOwner = null;
         if (this.ownerFieldNumber >= 0 && !ec.getApiAdapter().isDeleted(elementToRemove)) {
            elementOP.isLoaded(this.ownerFieldNumber);
            oldOwner = elementOP.provideField(this.ownerFieldNumber);
         }

         if (this.ownerFieldNumber >= 0 && oldOwner != op.getObject() && oldOwner != null) {
            return false;
         } else {
            boolean deleteElement = this.checkRemovalOfElementShouldDelete(op);
            if (deleteElement) {
               if (ec.getApiAdapter().isPersistable(elementToRemove) && ec.getApiAdapter().isDeleted(elementToRemove)) {
                  elementOP.flush();
               } else {
                  ec.deleteObjectInternal(elementToRemove);
               }
            } else {
               this.manageRemovalOfElement(op, elementToRemove);
               this.updateElementFk(op, elementToRemove, (Object)null);
            }

            return true;
         }
      }
   }

   public boolean removeAll(ObjectProvider op, Collection elements, int size) {
      if (elements != null && elements.size() != 0) {
         boolean success = true;
         Iterator iter = elements.iterator();

         while(iter.hasNext()) {
            if (this.remove(op, iter.next(), -1, true)) {
               success = false;
            }
         }

         return success;
      } else {
         return false;
      }
   }

   protected boolean checkRemovalOfElementShouldDelete(ObjectProvider op) {
      boolean delete = false;
      boolean dependent = this.ownerMemberMetaData.getCollection().isDependentElement();
      if (this.ownerMemberMetaData.isCascadeRemoveOrphans()) {
         dependent = true;
      }

      if (dependent) {
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("056034"));
         }

         delete = true;
      } else if (this.ownerMapping.isNullable()) {
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("056036"));
         }

         delete = false;
      } else {
         if (NucleusLogger.DATASTORE.isDebugEnabled()) {
            NucleusLogger.DATASTORE.debug(Localiser.msg("056035"));
         }

         delete = true;
      }

      return delete;
   }

   protected void manageRemovalOfElement(ObjectProvider op, Object element) {
      ExecutionContext ec = op.getExecutionContext();
      if (this.relationType == RelationType.ONE_TO_MANY_BI && !ec.getApiAdapter().isDeleted(element)) {
         ObjectProvider elementOP = ec.findObjectProvider(element);
         if (elementOP != null) {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("055010", new Object[]{op.getObjectAsPrintable(), this.ownerMemberMetaData.getFullFieldName(), StringUtils.toJVMIDString(element)}));
            }

            int relatedFieldNumber = this.getFieldNumberInElementForBidirectional(elementOP);
            Object currentValue = elementOP.provideField(relatedFieldNumber);
            if (currentValue != null) {
               elementOP.replaceFieldMakeDirty(relatedFieldNumber, (Object)null);
               if (ec.isFlushing()) {
                  elementOP.flush();
               }
            }
         }
      }

   }

   public void clear(ObjectProvider op) {
      ExecutionContext ec = op.getExecutionContext();
      boolean deleteElements = this.checkRemovalOfElementShouldDelete(op);
      if (deleteElements) {
         Iterator elementsIter = this.iterator(op);
         if (elementsIter != null) {
            while(elementsIter.hasNext()) {
               Object element = elementsIter.next();
               if (ec.getApiAdapter().isPersistable(element) && ec.getApiAdapter().isDeleted(element)) {
                  ec.findObjectProvider(element).flush();
               } else {
                  ec.deleteObjectInternal(element);
               }
            }
         }
      } else {
         op.isLoaded(this.ownerMemberMetaData.getAbsoluteFieldNumber());
         Collection value = (Collection)op.provideField(this.ownerMemberMetaData.getAbsoluteFieldNumber());
         Iterator elementsIter = null;
         if (value != null && !value.isEmpty()) {
            elementsIter = value.iterator();
         } else {
            elementsIter = this.iterator(op);
         }

         if (elementsIter != null) {
            while(elementsIter.hasNext()) {
               Object element = elementsIter.next();
               this.manageRemovalOfElement(op, element);
            }
         }

         for(int i = 0; i < this.elementInfo.length; ++i) {
            String stmt = this.getClearNullifyStmt(this.elementInfo[i]);

            try {
               ManagedConnection mconn = this.storeMgr.getConnection(ec);
               SQLController sqlControl = this.storeMgr.getSQLController();

               try {
                  PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, false);

                  try {
                     int jdbcPosition = 1;
                     BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                     sqlControl.executeStatementUpdate(ec, mconn, stmt, ps, true);
                  } finally {
                     sqlControl.closeStatement(mconn, ps);
                  }
               } finally {
                  mconn.release();
               }
            } catch (SQLException e) {
               throw new NucleusDataStoreException(Localiser.msg("056013", new Object[]{stmt}), e);
            }
         }
      }

   }

   protected String getClearNullifyStmt(ElementContainerStore.ElementInfo info) {
      if (this.elementInfo.length == 1 && this.clearNullifyStmt != null) {
         return this.clearNullifyStmt;
      } else {
         StringBuilder stmt = new StringBuilder("UPDATE ");
         if (this.elementInfo.length > 1) {
            stmt.append(info.getDatastoreClass().toString());
         } else {
            stmt.append(this.containerTable.toString());
         }

         stmt.append(" SET ");
         JavaTypeMapping ownerMapping = this.ownerMapping;
         if (this.ownerMemberMetaData.getMappedBy() != null) {
            ownerMapping = info.getDatastoreClass().getMemberMapping(this.ownerMemberMetaData.getMappedBy());
         } else {
            ownerMapping = info.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 5);
         }

         for(int i = 0; i < ownerMapping.getNumberOfDatastoreMappings(); ++i) {
            if (i > 0) {
               stmt.append(", ");
            }

            stmt.append(ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            stmt.append("=NULL");
         }

         JavaTypeMapping relDiscrimMapping = info.getDatastoreClass().getExternalMapping(this.ownerMemberMetaData, 6);
         if (relDiscrimMapping != null) {
            for(int i = 0; i < relDiscrimMapping.getNumberOfDatastoreMappings(); ++i) {
               stmt.append(", ");
               stmt.append(relDiscrimMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               stmt.append("=NULL");
            }
         }

         stmt.append(" WHERE ");
         BackingStoreHelper.appendWhereClauseForMapping(stmt, ownerMapping, (String)null, true);
         if (this.elementInfo.length == 1) {
            this.clearNullifyStmt = stmt.toString();
         }

         return stmt.toString();
      }
   }

   private String getUpdateFkStmt(Object element) {
      if (this.elementInfo.length > 1) {
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

   public Iterator iterator(ObjectProvider op) {
      ExecutionContext ec = op.getExecutionContext();
      if (this.elementInfo != null && this.elementInfo.length != 0) {
         IteratorStatement iterStmt = this.getIteratorStatement(op.getExecutionContext().getClassLoaderResolver(), op.getExecutionContext().getFetchPlan(), true);
         SQLStatement sqlStmt = iterStmt.getSQLStatement();
         StatementClassMapping iteratorMappingClass = iterStmt.getStatementClassMapping();
         int inputParamNum = 1;
         StatementMappingIndex ownerStmtMapIdx = new StatementMappingIndex(this.ownerMapping);
         if (sqlStmt.getNumberOfUnions() > 0) {
            for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
               int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

               for(int k = 0; k < this.ownerMapping.getNumberOfDatastoreMappings(); ++k) {
                  paramPositions[k] = inputParamNum++;
               }

               ownerStmtMapIdx.addParameterOccurrence(paramPositions);
            }
         } else {
            int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < this.ownerMapping.getNumberOfDatastoreMappings(); ++k) {
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

            CollectionStoreIterator var15;
            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
               int numParams = ownerStmtMapIdx.getNumberOfParameterOccurrences();

               for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
                  ownerStmtMapIdx.getMapping().setObject(ec, ps, ownerStmtMapIdx.getParameterPositionsForOccurrence(paramInstance), op.getObject());
               }

               try {
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

                  try {
                     ResultObjectFactory rof = null;
                     if (this.elementsAreEmbedded || this.elementsAreSerialised) {
                        throw new NucleusException("Cannot have FK set with non-persistent objects");
                     }

                     ResultObjectFactory var45 = new PersistentClassROF(this.storeMgr, this.emd, iteratorMappingClass, false, (FetchPlan)null, this.clr.classForName(this.elementType));
                     var15 = new CollectionStoreIterator(op, rs, var45, this);
                  } finally {
                     rs.close();
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return var15;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
         } catch (MappedDatastoreException e) {
            throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
         }
      } else {
         return null;
      }
   }

   public IteratorStatement getIteratorStatement(ClassLoaderResolver clr, FetchPlan fp, boolean addRestrictionOnOwner) {
      SQLStatement sqlStmt = null;
      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      StatementClassMapping iteratorMappingClass = new StatementClassMapping();
      if (this.elementInfo[0].getDatastoreClass().getDiscriminatorMetaData() != null && this.elementInfo[0].getDatastoreClass().getDiscriminatorMetaData().getStrategy() != DiscriminatorStrategy.NONE) {
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
         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, iteratorMappingClass, fp, sqlStmt.getPrimaryTable(), this.emd, 0);
      } else {
         boolean selectFetchPlan = true;
         Class elementTypeCls = clr.classForName(this.elementType);
         if (elementTypeCls.isInterface() && this.elementInfo.length > 1) {
            selectFetchPlan = false;
         }

         for(int i = 0; i < this.elementInfo.length; ++i) {
            Class elementCls = clr.classForName(this.elementInfo[i].getClassName());
            UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, elementCls, true, (DatastoreIdentifier)null, (String)null);
            stmtGen.setOption("selectNucleusType");
            iteratorMappingClass.setNucleusTypeColumnName("NUCLEUS_TYPE");
            SQLStatement subStmt = stmtGen.getStatement();
            if (selectFetchPlan) {
               if (sqlStmt == null) {
                  SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(subStmt, iteratorMappingClass, fp, subStmt.getPrimaryTable(), this.elementInfo[i].getAbstractClassMetaData(), 0);
               } else {
                  SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(subStmt, (StatementClassMapping)null, fp, subStmt.getPrimaryTable(), this.elementInfo[i].getAbstractClassMetaData(), 0);
               }
            } else if (sqlStmt == null) {
               SQLStatementHelper.selectIdentityOfCandidateInStatement(subStmt, iteratorMappingClass, this.elementInfo[i].getAbstractClassMetaData());
            } else {
               SQLStatementHelper.selectIdentityOfCandidateInStatement(subStmt, (StatementClassMapping)null, this.elementInfo[i].getAbstractClassMetaData());
            }

            if (sqlStmt == null) {
               sqlStmt = subStmt;
            } else {
               sqlStmt.union(subStmt);
            }
         }

         if (sqlStmt == null) {
            throw new NucleusException("Unable to generate iterator statement for field " + this.getOwnerMemberMetaData().getFullFieldName());
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
