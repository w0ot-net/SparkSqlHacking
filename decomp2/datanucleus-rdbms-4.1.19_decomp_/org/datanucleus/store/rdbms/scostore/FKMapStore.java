package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.ClassDefinitionException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedKeyPCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.rdbms.query.PersistentClassROF;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.rdbms.query.StatementParameterMapping;
import org.datanucleus.store.rdbms.sql.DiscriminatorStatementGenerator;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.scostore.SetStore;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;

public class FKMapStore extends AbstractMapStore {
   private String updateFkStmt;
   private String getStmtLocked = null;
   private String getStmtUnlocked = null;
   private StatementClassMapping getMappingDef = null;
   private StatementParameterMapping getMappingParams = null;
   private final int ownerFieldNumber;
   protected int keyFieldNumber = -1;
   private int valueFieldNumber = -1;

   public FKMapStore(AbstractMemberMetaData mmd, RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
      this.setOwner(mmd);
      MapMetaData mapmd = (MapMetaData)mmd.getContainer();
      if (mapmd == null) {
         throw new NucleusUserException(Localiser.msg("056002", new Object[]{mmd.getFullFieldName()}));
      } else {
         boolean keyStoredInValue = false;
         if (mmd.getKeyMetaData() != null && mmd.getKeyMetaData().getMappedBy() != null) {
            keyStoredInValue = true;
         } else if (mmd.getValueMetaData() != null && mmd.getValueMetaData().getMappedBy() == null) {
            throw new NucleusUserException(Localiser.msg("056071", new Object[]{mmd.getFullFieldName()}));
         }

         this.keyType = mapmd.getKeyType();
         this.valueType = mapmd.getValueType();
         Class keyClass = clr.classForName(this.keyType);
         Class valueClass = clr.classForName(this.valueType);
         ApiAdapter api = this.getStoreManager().getApiAdapter();
         if (keyStoredInValue && !api.isPersistable(valueClass)) {
            throw new NucleusUserException(Localiser.msg("056072", new Object[]{mmd.getFullFieldName(), this.valueType}));
         } else if (!keyStoredInValue && !api.isPersistable(keyClass)) {
            throw new NucleusUserException(Localiser.msg("056073", new Object[]{mmd.getFullFieldName(), this.keyType}));
         } else {
            String ownerFieldName = mmd.getMappedBy();
            if (keyStoredInValue) {
               this.vmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(valueClass, clr);
               if (this.vmd == null) {
                  throw new NucleusUserException(Localiser.msg("056070", new Object[]{this.valueType, mmd.getFullFieldName()}));
               }

               this.valueTable = storeMgr.getDatastoreClass(this.valueType, clr);
               this.valueMapping = storeMgr.getDatastoreClass(this.valueType, clr).getIdMapping();
               this.valuesAreEmbedded = false;
               this.valuesAreSerialised = false;
               if (mmd.getMappedBy() != null) {
                  AbstractMemberMetaData vofmd = this.vmd.getMetaDataForMember(ownerFieldName);
                  if (vofmd == null) {
                     throw new NucleusUserException(Localiser.msg("056067", new Object[]{mmd.getFullFieldName(), ownerFieldName, valueClass.getName()}));
                  }

                  if (!clr.isAssignableFrom(vofmd.getType(), mmd.getAbstractClassMetaData().getFullClassName())) {
                     throw new NucleusUserException(Localiser.msg("056068", new Object[]{mmd.getFullFieldName(), vofmd.getFullFieldName(), vofmd.getTypeName(), mmd.getAbstractClassMetaData().getFullClassName()}));
                  }

                  this.ownerFieldNumber = this.vmd.getAbsolutePositionOfMember(ownerFieldName);
                  this.ownerMapping = this.valueTable.getMemberMapping(vofmd);
                  if (this.ownerMapping == null) {
                     throw new NucleusUserException(Localiser.msg("RDBMS.SCO.Map.InverseOwnerMappedByFieldNotPresent", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.valueType, ownerFieldName}));
                  }

                  if (this.isEmbeddedMapping(this.ownerMapping)) {
                     throw new NucleusUserException(Localiser.msg("056055", new Object[]{ownerFieldName, this.valueType, vofmd.getTypeName(), mmd.getClassName()}));
                  }
               } else {
                  this.ownerFieldNumber = -1;
                  this.ownerMapping = this.valueTable.getExternalMapping(mmd, 5);
                  if (this.ownerMapping == null) {
                     throw new NucleusUserException(Localiser.msg("056056", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.valueType}));
                  }
               }

               if (mmd.getKeyMetaData() == null || mmd.getKeyMetaData().getMappedBy() == null) {
                  throw new NucleusUserException(Localiser.msg("056050", new Object[]{valueClass.getName()}));
               }

               AbstractMemberMetaData vkfmd = null;
               String key_field_name = mmd.getKeyMetaData().getMappedBy();
               if (key_field_name != null) {
                  AbstractClassMetaData vkCmd = storeMgr.getMetaDataManager().getMetaDataForClass(valueClass, clr);
                  vkfmd = vkCmd != null ? vkCmd.getMetaDataForMember(key_field_name) : null;
                  if (vkfmd == null) {
                     throw new NucleusUserException(Localiser.msg("056052", new Object[]{valueClass.getName(), key_field_name}));
                  }
               }

               if (vkfmd == null) {
                  throw new ClassDefinitionException(Localiser.msg("056050", new Object[]{mmd.getFullFieldName()}));
               }

               if (!ClassUtils.typesAreCompatible(vkfmd.getType(), this.keyType, clr)) {
                  throw new NucleusUserException(Localiser.msg("056051", new Object[]{mmd.getFullFieldName(), this.keyType, vkfmd.getType().getName()}));
               }

               String keyFieldName = vkfmd.getName();
               this.keyFieldNumber = this.vmd.getAbsolutePositionOfMember(keyFieldName);
               this.keyMapping = this.valueTable.getMemberMapping(this.vmd.getMetaDataForManagedMemberAtAbsolutePosition(this.keyFieldNumber));
               if (this.keyMapping == null) {
                  throw new NucleusUserException(Localiser.msg("056053", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.valueType, keyFieldName}));
               }

               if (!this.keyMapping.hasSimpleDatastoreRepresentation()) {
                  throw new NucleusUserException("Invalid field type for map key field: " + mmd.getFullFieldName());
               }

               this.keysAreEmbedded = this.isEmbeddedMapping(this.keyMapping);
               this.keysAreSerialised = this.isEmbeddedMapping(this.keyMapping);
               this.mapTable = this.valueTable;
               if (mmd.getMappedBy() != null && this.ownerMapping.getTable() != this.mapTable) {
                  this.mapTable = this.ownerMapping.getTable();
               }
            } else {
               this.kmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(keyClass, clr);
               if (this.kmd == null) {
                  throw new NucleusUserException(Localiser.msg("056069", new Object[]{this.keyType, mmd.getFullFieldName()}));
               }

               this.valueTable = storeMgr.getDatastoreClass(this.keyType, clr);
               this.keyMapping = storeMgr.getDatastoreClass(this.keyType, clr).getIdMapping();
               this.keysAreEmbedded = false;
               this.keysAreSerialised = false;
               if (mmd.getMappedBy() != null) {
                  AbstractMemberMetaData kofmd = this.kmd.getMetaDataForMember(ownerFieldName);
                  if (kofmd == null) {
                     throw new NucleusUserException(Localiser.msg("056067", new Object[]{mmd.getFullFieldName(), ownerFieldName, keyClass.getName()}));
                  }

                  if (!ClassUtils.typesAreCompatible(kofmd.getType(), mmd.getAbstractClassMetaData().getFullClassName(), clr)) {
                     throw new NucleusUserException(Localiser.msg("056068", new Object[]{mmd.getFullFieldName(), kofmd.getFullFieldName(), kofmd.getTypeName(), mmd.getAbstractClassMetaData().getFullClassName()}));
                  }

                  this.ownerFieldNumber = this.kmd.getAbsolutePositionOfMember(ownerFieldName);
                  this.ownerMapping = this.valueTable.getMemberMapping(kofmd);
                  if (this.ownerMapping == null) {
                     throw new NucleusUserException(Localiser.msg("RDBMS.SCO.Map.InverseOwnerMappedByFieldNotPresent", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.keyType, ownerFieldName}));
                  }

                  if (this.isEmbeddedMapping(this.ownerMapping)) {
                     throw new NucleusUserException(Localiser.msg("056055", new Object[]{ownerFieldName, this.keyType, kofmd.getTypeName(), mmd.getClassName()}));
                  }
               } else {
                  this.ownerFieldNumber = -1;
                  this.ownerMapping = this.valueTable.getExternalMapping(mmd, 5);
                  if (this.ownerMapping == null) {
                     throw new NucleusUserException(Localiser.msg("056056", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.keyType}));
                  }
               }

               if (mmd.getValueMetaData() == null || mmd.getValueMetaData().getMappedBy() == null) {
                  throw new NucleusUserException(Localiser.msg("056057", new Object[]{keyClass.getName()}));
               }

               AbstractMemberMetaData vkfmd = null;
               String value_field_name = mmd.getValueMetaData().getMappedBy();
               if (value_field_name != null) {
                  AbstractClassMetaData vkCmd = storeMgr.getMetaDataManager().getMetaDataForClass(keyClass, clr);
                  vkfmd = vkCmd != null ? vkCmd.getMetaDataForMember(value_field_name) : null;
                  if (vkfmd == null) {
                     throw new NucleusUserException(Localiser.msg("056059", new Object[]{keyClass.getName(), value_field_name}));
                  }
               }

               if (vkfmd == null) {
                  throw new ClassDefinitionException(Localiser.msg("056057", new Object[]{mmd.getFullFieldName()}));
               }

               if (!ClassUtils.typesAreCompatible(vkfmd.getType(), this.valueType, clr)) {
                  throw new NucleusUserException(Localiser.msg("056058", new Object[]{mmd.getFullFieldName(), this.valueType, vkfmd.getType().getName()}));
               }

               String valueFieldName = vkfmd.getName();
               this.valueFieldNumber = this.kmd.getAbsolutePositionOfMember(valueFieldName);
               this.valueMapping = this.valueTable.getMemberMapping(this.kmd.getMetaDataForManagedMemberAtAbsolutePosition(this.valueFieldNumber));
               if (this.valueMapping == null) {
                  throw new NucleusUserException(Localiser.msg("056054", new Object[]{mmd.getAbstractClassMetaData().getFullClassName(), mmd.getName(), this.keyType, valueFieldName}));
               }

               if (!this.valueMapping.hasSimpleDatastoreRepresentation()) {
                  throw new NucleusUserException("Invalid field type for map value field: " + mmd.getFullFieldName());
               }

               this.valuesAreEmbedded = this.isEmbeddedMapping(this.valueMapping);
               this.valuesAreSerialised = this.isEmbeddedMapping(this.valueMapping);
               this.mapTable = this.valueTable;
               if (mmd.getMappedBy() != null && this.ownerMapping.getTable() != this.mapTable) {
                  this.mapTable = this.ownerMapping.getTable();
               }
            }

            this.initialise();
         }
      }
   }

   protected void initialise() {
      super.initialise();
      this.updateFkStmt = this.getUpdateFkStmt();
   }

   private boolean updateValueFk(ObjectProvider op, Object value, Object owner) {
      if (value == null) {
         return false;
      } else {
         this.validateValueForWriting(op, value);
         return this.updateValueFkInternal(op, value, owner);
      }
   }

   private boolean updateKeyFk(ObjectProvider op, Object key, Object owner) {
      if (key == null) {
         return false;
      } else {
         this.validateKeyForWriting(op, key);
         return this.updateKeyFkInternal(op, key, owner);
      }
   }

   protected void validateValueType(ClassLoaderResolver clr, Object value) {
      if (value == null) {
         throw new NullPointerException(Localiser.msg("056063"));
      } else {
         super.validateValueType(clr, value);
      }
   }

   public Object put(final ObjectProvider op, final Object newKey, final Object newValue) {
      if (this.keyFieldNumber >= 0) {
         this.validateKeyForWriting(op, newKey);
         this.validateValueType(op.getExecutionContext().getClassLoaderResolver(), newValue);
      } else {
         this.validateKeyType(op.getExecutionContext().getClassLoaderResolver(), newKey);
         this.validateValueForWriting(op, newValue);
      }

      V oldValue = (V)this.get(op, newKey);
      if (oldValue != newValue) {
         if (this.vmd != null) {
            if (oldValue != null && !oldValue.equals(newValue)) {
               this.removeValue(op, newKey, oldValue);
            }

            ExecutionContext ec = op.getExecutionContext();
            final Object newOwner = op.getObject();
            if (ec.getApiAdapter().isPersistent(newValue)) {
               if (ec != ec.getApiAdapter().getExecutionContext(newValue)) {
                  throw new NucleusUserException(Localiser.msg("RDBMS.SCO.Map.WriteValueInvalidWithDifferentPM"), ec.getApiAdapter().getIdForObject(newValue));
               }

               ObjectProvider vsm = ec.findObjectProvider(newValue);
               if (this.ownerFieldNumber >= 0) {
                  vsm.isLoaded(this.ownerFieldNumber);
                  Object oldOwner = vsm.provideField(this.ownerFieldNumber);
                  vsm.replaceFieldMakeDirty(this.ownerFieldNumber, newOwner);
                  if (ec.getManageRelations()) {
                     ec.getRelationshipManager(vsm).relationChange(this.ownerFieldNumber, oldOwner, newOwner);
                  }
               } else {
                  this.updateValueFk(op, newValue, newOwner);
               }

               vsm.isLoaded(this.keyFieldNumber);
               Object oldKey = vsm.provideField(this.keyFieldNumber);
               vsm.replaceFieldMakeDirty(this.keyFieldNumber, newKey);
               if (ec.getManageRelations()) {
                  ec.getRelationshipManager(vsm).relationChange(this.keyFieldNumber, oldKey, newKey);
               }
            } else {
               ec.persistObjectInternal(newValue, new FieldValues() {
                  public void fetchFields(ObjectProvider vsm) {
                     if (FKMapStore.this.ownerFieldNumber >= 0) {
                        vsm.replaceFieldMakeDirty(FKMapStore.this.ownerFieldNumber, newOwner);
                     }

                     vsm.replaceFieldMakeDirty(FKMapStore.this.keyFieldNumber, newKey);
                     JavaTypeMapping externalFKMapping = FKMapStore.this.valueTable.getExternalMapping(FKMapStore.this.ownerMemberMetaData, 5);
                     if (externalFKMapping != null) {
                        vsm.setAssociatedValue(externalFKMapping, op.getObject());
                     }

                  }

                  public void fetchNonLoadedFields(ObjectProvider opx) {
                  }

                  public FetchPlan getFetchPlanForLoading() {
                     return null;
                  }
               }, 0);
            }
         } else {
            ExecutionContext ec = op.getExecutionContext();
            final Object newOwner = op.getObject();
            if (ec.getApiAdapter().isPersistent(newKey)) {
               if (ec != ec.getApiAdapter().getExecutionContext(newKey)) {
                  throw new NucleusUserException(Localiser.msg("056060"), ec.getApiAdapter().getIdForObject(newKey));
               }

               ObjectProvider valOP = ec.findObjectProvider(newKey);
               if (this.ownerFieldNumber >= 0) {
                  valOP.isLoaded(this.ownerFieldNumber);
                  Object oldOwner = valOP.provideField(this.ownerFieldNumber);
                  valOP.replaceFieldMakeDirty(this.ownerFieldNumber, newOwner);
                  if (ec.getManageRelations()) {
                     ec.getRelationshipManager(valOP).relationChange(this.ownerFieldNumber, oldOwner, newOwner);
                  }
               } else {
                  this.updateKeyFk(op, newKey, newOwner);
               }

               valOP.isLoaded(this.valueFieldNumber);
               oldValue = (V)valOP.provideField(this.valueFieldNumber);
               valOP.replaceFieldMakeDirty(this.valueFieldNumber, newValue);
               if (ec.getManageRelations()) {
                  ec.getRelationshipManager(valOP).relationChange(this.valueFieldNumber, oldValue, newValue);
               }
            } else {
               ec.persistObjectInternal(newKey, new FieldValues() {
                  public void fetchFields(ObjectProvider vsm) {
                     if (FKMapStore.this.ownerFieldNumber >= 0) {
                        vsm.replaceFieldMakeDirty(FKMapStore.this.ownerFieldNumber, newOwner);
                     }

                     vsm.replaceFieldMakeDirty(FKMapStore.this.valueFieldNumber, newValue);
                     JavaTypeMapping externalFKMapping = FKMapStore.this.valueTable.getExternalMapping(FKMapStore.this.ownerMemberMetaData, 5);
                     if (externalFKMapping != null) {
                        vsm.setAssociatedValue(externalFKMapping, op.getObject());
                     }

                  }

                  public void fetchNonLoadedFields(ObjectProvider opx) {
                  }

                  public FetchPlan getFetchPlanForLoading() {
                     return null;
                  }
               }, 0);
            }
         }
      }

      if (this.ownerMemberMetaData.getMap().isDependentValue() && oldValue != null && !this.containsValue(op, oldValue)) {
         op.getExecutionContext().deleteObjectInternal(oldValue);
      }

      return oldValue;
   }

   public Object remove(ObjectProvider op, Object key) {
      if (!this.allowNulls && key == null) {
         return null;
      } else {
         Object oldValue = this.get(op, key);
         return this.remove(op, key, oldValue);
      }
   }

   public Object remove(ObjectProvider op, Object key, Object oldValue) {
      ExecutionContext ec = op.getExecutionContext();
      if (this.keyFieldNumber >= 0) {
         if (oldValue != null) {
            boolean deletingValue = false;
            ObjectProvider vsm = ec.findObjectProvider(oldValue);
            if (this.ownerMemberMetaData.getMap().isDependentValue()) {
               deletingValue = true;
               ec.deleteObjectInternal(oldValue);
               vsm.flush();
            } else if (this.ownerMapping.isNullable()) {
               if (this.ownerFieldNumber >= 0) {
                  Object oldOwner = vsm.provideField(this.ownerFieldNumber);
                  vsm.replaceFieldMakeDirty(this.ownerFieldNumber, (Object)null);
                  vsm.flush();
                  if (ec.getManageRelations()) {
                     ec.getRelationshipManager(vsm).relationChange(this.ownerFieldNumber, oldOwner, (Object)null);
                  }
               } else {
                  this.updateValueFkInternal(op, oldValue, (Object)null);
               }
            } else {
               deletingValue = true;
               ec.deleteObjectInternal(oldValue);
               vsm.flush();
            }

            if (this.ownerMemberMetaData.getMap().isDependentKey()) {
               if (!deletingValue && this.keyMapping.isNullable()) {
                  vsm.replaceFieldMakeDirty(this.keyFieldNumber, (Object)null);
                  vsm.flush();
                  if (ec.getManageRelations()) {
                     ec.getRelationshipManager(vsm).relationChange(this.keyFieldNumber, key, (Object)null);
                  }
               }

               op.getExecutionContext().deleteObjectInternal(key);
               ObjectProvider keyOP = ec.findObjectProvider(key);
               keyOP.flush();
            }
         }
      } else if (key != null) {
         boolean deletingKey = false;
         ObjectProvider ksm = ec.findObjectProvider(key);
         if (this.ownerMemberMetaData.getMap().isDependentKey()) {
            deletingKey = true;
            ec.deleteObjectInternal(key);
            ksm.flush();
         } else if (this.ownerMapping.isNullable()) {
            if (this.ownerFieldNumber >= 0) {
               Object oldOwner = ksm.provideField(this.ownerFieldNumber);
               ksm.replaceFieldMakeDirty(this.ownerFieldNumber, (Object)null);
               ksm.flush();
               if (ec.getManageRelations()) {
                  ec.getRelationshipManager(ksm).relationChange(this.ownerFieldNumber, oldOwner, (Object)null);
               }
            } else {
               this.updateKeyFkInternal(op, key, (Object)null);
            }
         } else {
            deletingKey = true;
            ec.deleteObjectInternal(key);
            ksm.flush();
         }

         if (this.ownerMemberMetaData.getMap().isDependentValue()) {
            if (!deletingKey && this.valueMapping.isNullable()) {
               ksm.replaceFieldMakeDirty(this.valueFieldNumber, (Object)null);
               ksm.flush();
               if (ec.getManageRelations()) {
                  ec.getRelationshipManager(ksm).relationChange(this.valueFieldNumber, oldValue, (Object)null);
               }
            }

            op.getExecutionContext().deleteObjectInternal(oldValue);
            ObjectProvider valOP = ec.findObjectProvider(oldValue);
            valOP.flush();
         }
      }

      return oldValue;
   }

   private void removeValue(ObjectProvider op, Object key, Object oldValue) {
      ExecutionContext ec = op.getExecutionContext();
      if (this.keyMapping.isNullable()) {
         ObjectProvider vsm = ec.findObjectProvider(oldValue);
         vsm.replaceFieldMakeDirty(this.keyFieldNumber, (Object)null);
         if (ec.getManageRelations()) {
            ec.getRelationshipManager(vsm).relationChange(this.keyFieldNumber, key, (Object)null);
         }

         if (this.ownerFieldNumber >= 0) {
            Object oldOwner = vsm.provideField(this.ownerFieldNumber);
            vsm.replaceFieldMakeDirty(this.ownerFieldNumber, (Object)null);
            if (ec.getManageRelations()) {
               ec.getRelationshipManager(vsm).relationChange(this.ownerFieldNumber, oldOwner, (Object)null);
            }
         } else {
            this.updateValueFk(op, oldValue, (Object)null);
         }
      } else {
         ec.deleteObjectInternal(oldValue);
      }

   }

   public void clear(ObjectProvider op) {
      Iterator iter = this.keySetStore().iterator(op);

      while(iter.hasNext()) {
         Object key = iter.next();
         if (key != null || this.allowNulls) {
            this.remove(op, key);
         }
      }

   }

   public void clearKeyOfValue(ObjectProvider op, Object key, Object oldValue) {
      ExecutionContext ec = op.getExecutionContext();
      if (this.keyMapping.isNullable()) {
         ObjectProvider vsm = ec.findObjectProvider(oldValue);
         if (!ec.getApiAdapter().isDeleted(oldValue)) {
            vsm.replaceFieldMakeDirty(this.keyFieldNumber, (Object)null);
            if (ec.getManageRelations()) {
               ec.getRelationshipManager(vsm).relationChange(this.keyFieldNumber, key, (Object)null);
            }
         }
      } else {
         ec.deleteObjectInternal(oldValue);
      }

   }

   public synchronized SetStore keySetStore() {
      return new MapKeySetStore(this.valueTable, this, this.clr);
   }

   public synchronized CollectionStore valueCollectionStore() {
      return new MapValueCollectionStore(this.valueTable, this, this.clr);
   }

   public synchronized SetStore entrySetStore() {
      return new MapEntrySetStore(this.valueTable, this, this.clr);
   }

   private String getUpdateFkStmt() {
      StringBuilder stmt = new StringBuilder("UPDATE ");
      stmt.append(this.getMapTable().toString());
      stmt.append(" SET ");

      for(int i = 0; i < this.ownerMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append(" = ");
         stmt.append(((AbstractDatastoreMapping)this.ownerMapping.getDatastoreMapping(i)).getUpdateInputParameter());
      }

      stmt.append(" WHERE ");
      if (this.keyFieldNumber >= 0) {
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.valueMapping, (String)null, true);
      } else {
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.keyMapping, (String)null, true);
      }

      return stmt.toString();
   }

   protected boolean updateValueFkInternal(ObjectProvider op, Object value, Object owner) {
      ExecutionContext ec = op.getExecutionContext();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         boolean retval;
         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, this.updateFkStmt, false);

            try {
               int jdbcPosition = 1;
               if (owner == null) {
                  if (this.ownerMemberMetaData != null) {
                     this.ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(1, this.ownerMapping), (Object)null, op, this.ownerMemberMetaData.getAbsoluteFieldNumber());
                  } else {
                     this.ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(1, this.ownerMapping), (Object)null);
                  }

                  jdbcPosition += this.ownerMapping.getNumberOfDatastoreMappings();
               } else {
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               }

               BackingStoreHelper.populateValueInStatement(ec, ps, value, jdbcPosition, this.valueMapping);
               sqlControl.executeStatementUpdate(ec, mconn, this.updateFkStmt, ps, true);
               retval = true;
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return retval;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056027", new Object[]{this.updateFkStmt}), e);
      }
   }

   protected boolean updateKeyFkInternal(ObjectProvider op, Object key, Object owner) {
      ExecutionContext ec = op.getExecutionContext();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         boolean retval;
         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, this.updateFkStmt, false);

            try {
               int jdbcPosition = 1;
               if (owner == null) {
                  if (this.ownerMemberMetaData != null) {
                     this.ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(1, this.ownerMapping), (Object)null, op, this.ownerMemberMetaData.getAbsoluteFieldNumber());
                  } else {
                     this.ownerMapping.setObject(ec, ps, MappingHelper.getMappingIndices(1, this.ownerMapping), (Object)null);
                  }

                  jdbcPosition += this.ownerMapping.getNumberOfDatastoreMappings();
               } else {
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               }

               BackingStoreHelper.populateKeyInStatement(ec, ps, key, jdbcPosition, this.keyMapping);
               sqlControl.executeStatementUpdate(ec, mconn, this.updateFkStmt, ps, true);
               retval = true;
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return retval;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056027", new Object[]{this.updateFkStmt}), e);
      }
   }

   protected Object getValue(ObjectProvider ownerOP, Object key) throws NoSuchElementException {
      if (!this.validateKeyForReading(ownerOP, key)) {
         return null;
      } else {
         ExecutionContext ec = ownerOP.getExecutionContext();
         if (this.getStmtLocked == null) {
            synchronized(this) {
               SQLStatement sqlStmt = this.getSQLStatementForGet(ownerOP);
               this.getStmtUnlocked = sqlStmt.getSelectStatement().toSQL();
               sqlStmt.addExtension("lock-for-update", true);
               this.getStmtLocked = sqlStmt.getSelectStatement().toSQL();
            }
         }

         Transaction tx = ec.getTransaction();
         String stmt = tx.getSerializeRead() != null && tx.getSerializeRead() ? this.getStmtLocked : this.getStmtUnlocked;
         Object value = null;

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
               StatementMappingIndex ownerIdx = this.getMappingParams.getMappingForParameter("owner");
               int numParams = ownerIdx.getNumberOfParameterOccurrences();

               for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
                  ownerIdx.getMapping().setObject(ec, ps, ownerIdx.getParameterPositionsForOccurrence(paramInstance), ownerOP.getObject());
               }

               StatementMappingIndex keyIdx = this.getMappingParams.getMappingForParameter("key");
               numParams = keyIdx.getNumberOfParameterOccurrences();

               for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
                  keyIdx.getMapping().setObject(ec, ps, keyIdx.getParameterPositionsForOccurrence(paramInstance), key);
               }

               try {
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

                  try {
                     boolean found = rs.next();
                     if (!found) {
                        throw new NoSuchElementException();
                     }

                     if (!this.valuesAreEmbedded && !this.valuesAreSerialised) {
                        if (this.valueMapping instanceof ReferenceMapping) {
                           int[] param = new int[this.valueMapping.getNumberOfDatastoreMappings()];

                           for(int i = 0; i < param.length; ++i) {
                              param[i] = i + 1;
                           }

                           value = this.valueMapping.getObject(ec, rs, param);
                        } else {
                           ResultObjectFactory rof = new PersistentClassROF(this.storeMgr, this.vmd, this.getMappingDef, false, (FetchPlan)null, this.clr.classForName(this.valueType));
                           value = rof.getObject(ec, rs);
                        }
                     } else {
                        int[] param = new int[this.valueMapping.getNumberOfDatastoreMappings()];

                        for(int i = 0; i < param.length; ++i) {
                           param[i] = i + 1;
                        }

                        if (!(this.valueMapping instanceof SerialisedPCMapping) && !(this.valueMapping instanceof SerialisedReferenceMapping) && !(this.valueMapping instanceof EmbeddedKeyPCMapping)) {
                           value = this.valueMapping.getObject(ec, rs, param);
                        } else {
                           int ownerFieldNumber = ((JoinTable)this.mapTable).getOwnerMemberMetaData().getAbsoluteFieldNumber();
                           value = this.valueMapping.getObject(ec, rs, param, ownerOP, ownerFieldNumber);
                        }
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

            return value;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056014", new Object[]{stmt}), e);
         }
      }
   }

   protected SQLStatement getSQLStatementForGet(ObjectProvider ownerOP) {
      SQLStatement sqlStmt = null;
      ClassLoaderResolver clr = ownerOP.getExecutionContext().getClassLoaderResolver();
      Class valueCls = clr.classForName(this.valueType);
      if (this.ownerMemberMetaData.getMap().getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         this.getMappingDef = new StatementClassMapping();
         if (this.valueTable.getDiscriminatorMetaData() != null && this.valueTable.getDiscriminatorMetaData().getStrategy() != DiscriminatorStrategy.NONE) {
            if (ClassUtils.isReferenceType(valueCls)) {
               String[] clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(this.valueType, clr);
               Class[] cls = new Class[clsNames.length];

               for(int i = 0; i < clsNames.length; ++i) {
                  cls[i] = clr.classForName(clsNames[i]);
               }

               sqlStmt = (new DiscriminatorStatementGenerator(this.storeMgr, clr, cls, true, (DatastoreIdentifier)null, (String)null)).getStatement();
            } else {
               sqlStmt = (new DiscriminatorStatementGenerator(this.storeMgr, clr, valueCls, true, (DatastoreIdentifier)null, (String)null)).getStatement();
            }

            this.iterateUsingDiscriminator = true;
         } else {
            UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, valueCls, true, (DatastoreIdentifier)null, (String)null);
            stmtGen.setOption("selectNucleusType");
            this.getMappingDef.setNucleusTypeColumnName("NUCLEUS_TYPE");
            sqlStmt = stmtGen.getStatement();
         }

         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.getMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.vmd, 0);
      } else {
         sqlStmt = new SQLStatement(this.storeMgr, this.mapTable, (DatastoreIdentifier)null, (String)null);
         sqlStmt.setClassLoaderResolver(clr);
         if (this.vmd != null) {
            SQLTable valueSqlTbl = sqlStmt.leftOuterJoin(sqlStmt.getPrimaryTable(), this.valueMapping, this.valueTable, (String)null, this.valueTable.getIdMapping(), (Object[])null, (String)null);
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.getMappingDef, ownerOP.getExecutionContext().getFetchPlan(), valueSqlTbl, this.vmd, 0);
         } else {
            sqlStmt.select(sqlStmt.getPrimaryTable(), (JavaTypeMapping)this.valueMapping, (String)null);
         }
      }

      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      SQLTable ownerSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.ownerMapping);
      SQLExpression ownerExpr = exprFactory.newExpression(sqlStmt, ownerSqlTbl, this.ownerMapping);
      SQLExpression ownerVal = exprFactory.newLiteralParameter(sqlStmt, this.ownerMapping, (Object)null, "OWNER");
      sqlStmt.whereAnd(ownerExpr.eq(ownerVal), true);
      if (this.keyMapping instanceof SerialisedMapping) {
         SQLExpression keyExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.keyMapping);
         SQLExpression keyVal = exprFactory.newLiteralParameter(sqlStmt, this.keyMapping, (Object)null, "KEY");
         sqlStmt.whereAnd(new BooleanExpression(keyExpr, Expression.OP_LIKE, keyVal), true);
      } else {
         SQLExpression keyExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.keyMapping);
         SQLExpression keyVal = exprFactory.newLiteralParameter(sqlStmt, this.keyMapping, (Object)null, "KEY");
         sqlStmt.whereAnd(keyExpr.eq(keyVal), true);
      }

      int inputParamNum = 1;
      StatementMappingIndex ownerIdx = new StatementMappingIndex(this.ownerMapping);
      StatementMappingIndex keyIdx = new StatementMappingIndex(this.keyMapping);
      if (sqlStmt.getNumberOfUnions() > 0) {
         for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
            int[] ownerPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < ownerPositions.length; ++k) {
               ownerPositions[k] = inputParamNum++;
            }

            ownerIdx.addParameterOccurrence(ownerPositions);
            int[] keyPositions = new int[this.keyMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < keyPositions.length; ++k) {
               keyPositions[k] = inputParamNum++;
            }

            keyIdx.addParameterOccurrence(keyPositions);
         }
      } else {
         int[] ownerPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

         for(int k = 0; k < ownerPositions.length; ++k) {
            ownerPositions[k] = inputParamNum++;
         }

         ownerIdx.addParameterOccurrence(ownerPositions);
         int[] keyPositions = new int[this.keyMapping.getNumberOfDatastoreMappings()];

         for(int k = 0; k < keyPositions.length; ++k) {
            keyPositions[k] = inputParamNum++;
         }

         keyIdx.addParameterOccurrence(keyPositions);
      }

      this.getMappingParams = new StatementParameterMapping();
      this.getMappingParams.addMappingForParameter("owner", ownerIdx);
      this.getMappingParams.addMappingForParameter("key", keyIdx);
      return sqlStmt;
   }
}
