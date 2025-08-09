package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.ElementMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.KeyMetaData;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.ValueMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.exceptions.NoTableManagedException;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.ColumnCreator;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class ReferenceMapping extends MultiPersistableMapping implements MappingCallbacks {
   public static final int PER_IMPLEMENTATION_MAPPING = 0;
   public static final int ID_MAPPING = 1;
   public static final int XCALIA_MAPPING = 2;
   protected int mappingStrategy = 0;

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      if (mmd.hasExtension("mapping-strategy")) {
         String strategy = mmd.getValueForExtension("mapping-strategy");
         if (strategy.equalsIgnoreCase("identity")) {
            this.mappingStrategy = 1;
         } else if (strategy.equalsIgnoreCase("xcalia")) {
            this.mappingStrategy = 2;
         }
      }

      this.numberOfDatastoreMappings = 0;
      super.initialize(mmd, table, clr);
      this.prepareDatastoreMapping(clr);
   }

   public int getMappingStrategy() {
      return this.mappingStrategy;
   }

   public JavaTypeMapping getJavaTypeMappingForType(Class type) {
      if (this.mappingStrategy == 0) {
         for(int i = 0; i < this.javaTypeMappings.length; ++i) {
            JavaTypeMapping m = this.javaTypeMappings[i];
            Class implType = this.storeMgr.getNucleusContext().getClassLoaderResolver(type.getClassLoader()).classForName(m.getType());
            if (type.isAssignableFrom(implType)) {
               return m;
            }
         }
      }

      return this;
   }

   protected void prepareDatastoreMapping(ClassLoaderResolver clr) {
      if (this.mappingStrategy == 0) {
         if (this.roleForMember == FieldRole.ROLE_ARRAY_ELEMENT) {
            ColumnMetaData[] colmds = null;
            ElementMetaData elemmd = this.mmd.getElementMetaData();
            if (elemmd != null && elemmd.getColumnMetaData() != null && elemmd.getColumnMetaData().length > 0) {
               colmds = elemmd.getColumnMetaData();
            }

            this.createPerImplementationColumnsForReferenceField(false, false, false, false, this.roleForMember, colmds, clr);
         } else if (this.roleForMember == FieldRole.ROLE_COLLECTION_ELEMENT) {
            ColumnMetaData[] colmds = null;
            AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(clr);
            ElementMetaData elemmd = this.mmd.getElementMetaData();
            if (elemmd != null && elemmd.getColumnMetaData() != null && elemmd.getColumnMetaData().length > 0) {
               colmds = elemmd.getColumnMetaData();
            } else if (relatedMmds != null && relatedMmds[0].getJoinMetaData() != null && relatedMmds[0].getJoinMetaData().getColumnMetaData() != null && relatedMmds[0].getJoinMetaData().getColumnMetaData().length > 0) {
               colmds = relatedMmds[0].getJoinMetaData().getColumnMetaData();
            }

            this.createPerImplementationColumnsForReferenceField(false, false, false, false, this.roleForMember, colmds, clr);
         } else if (this.roleForMember == FieldRole.ROLE_MAP_KEY) {
            ColumnMetaData[] colmds = null;
            KeyMetaData keymd = this.mmd.getKeyMetaData();
            if (keymd != null && keymd.getColumnMetaData() != null && keymd.getColumnMetaData().length > 0) {
               colmds = keymd.getColumnMetaData();
            }

            this.createPerImplementationColumnsForReferenceField(false, false, false, false, this.roleForMember, colmds, clr);
         } else if (this.roleForMember == FieldRole.ROLE_MAP_VALUE) {
            ColumnMetaData[] colmds = null;
            ValueMetaData valuemd = this.mmd.getValueMetaData();
            if (valuemd != null && valuemd.getColumnMetaData() != null && valuemd.getColumnMetaData().length > 0) {
               colmds = valuemd.getColumnMetaData();
            }

            this.createPerImplementationColumnsForReferenceField(false, false, false, false, this.roleForMember, colmds, clr);
         } else if (this.mmd.getMappedBy() == null) {
            boolean embedded = this.mmd.isEmbedded() || this.mmd.getEmbeddedMetaData() != null;
            this.createPerImplementationColumnsForReferenceField(false, true, false, embedded, this.roleForMember, this.mmd.getColumnMetaData(), clr);
         } else {
            AbstractClassMetaData refCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForInterface(this.mmd.getType(), clr);
            if (refCmd != null && refCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
               AbstractClassMetaData[] cmds = this.storeMgr.getClassesManagingTableForClass(refCmd, clr);
               if (cmds == null || cmds.length <= 0) {
                  return;
               }

               if (cmds.length > 1) {
                  NucleusLogger.PERSISTENCE.warn("Field " + this.mmd.getFullFieldName() + " represents either a 1-1 relation, or a N-1 relation where the other end uses \"subclass-table\" inheritance strategy and more than 1 subclasses with a table. This is not fully supported currently");
               }

               this.storeMgr.getDatastoreClass(cmds[0].getFullClassName(), clr).getIdMapping();
            } else {
               String[] implTypes = MetaDataUtils.getInstance().getImplementationNamesForReferenceField(this.mmd, FieldRole.ROLE_FIELD, clr, this.storeMgr.getMetaDataManager());

               for(int j = 0; j < implTypes.length; ++j) {
                  JavaTypeMapping refMapping = this.storeMgr.getDatastoreClass(implTypes[j], clr).getIdMapping();
                  JavaTypeMapping mapping = this.storeMgr.getMappingManager().getMapping(clr.classForName(implTypes[j]));
                  mapping.setReferenceMapping(refMapping);
                  this.addJavaTypeMapping(mapping);
               }
            }
         }
      } else if (this.mappingStrategy == 1 || this.mappingStrategy == 2) {
         MappingManager mapMgr = this.storeMgr.getMappingManager();
         JavaTypeMapping mapping = mapMgr.getMapping(String.class);
         mapping.setMemberMetaData(this.mmd);
         mapping.setTable(this.table);
         mapping.setRoleForMember(this.roleForMember);
         Column col = mapMgr.createColumn(mapping, String.class.getName(), 0);
         mapMgr.createDatastoreMapping(mapping, this.mmd, 0, col);
         this.addJavaTypeMapping(mapping);
      }

   }

   private String getReferenceFieldType(FieldRole fieldRole) {
      String fieldTypeName = this.mmd.getTypeName();
      if (this.mmd.getFieldTypes() != null && this.mmd.getFieldTypes().length == 1) {
         fieldTypeName = this.mmd.getFieldTypes()[0];
      }

      if (this.mmd.hasCollection()) {
         fieldTypeName = this.mmd.getCollection().getElementType();
      } else if (this.mmd.hasArray()) {
         fieldTypeName = this.mmd.getArray().getElementType();
      } else if (this.mmd.hasMap()) {
         if (fieldRole == FieldRole.ROLE_MAP_KEY) {
            fieldTypeName = this.mmd.getMap().getKeyType();
         } else if (fieldRole == FieldRole.ROLE_MAP_VALUE) {
            fieldTypeName = this.mmd.getMap().getValueType();
         }
      }

      return fieldTypeName;
   }

   void createPerImplementationColumnsForReferenceField(boolean pk, boolean nullable, boolean serialised, boolean embedded, FieldRole fieldRole, ColumnMetaData[] columnMetaData, ClassLoaderResolver clr) {
      if (this instanceof InterfaceMapping && this.mmd != null && this.mmd.hasExtension("implementation-classes")) {
         ((InterfaceMapping)this).setImplementationClasses(this.mmd.getValueForExtension("implementation-classes"));
      }

      String[] implTypes = null;

      try {
         implTypes = MetaDataUtils.getInstance().getImplementationNamesForReferenceField(this.mmd, fieldRole, clr, this.storeMgr.getMetaDataManager());
      } catch (NucleusUserException nue) {
         if (this.storeMgr.getBooleanProperty("datanucleus.store.allowReferencesWithNoImplementations", false)) {
            NucleusLogger.DATASTORE_SCHEMA.warn("Possible problem encountered while adding columns for field " + this.mmd.getFullFieldName() + " : " + nue.getMessage());
            return;
         }

         throw nue;
      }

      if (implTypes.length > 1) {
         pk = false;
      }

      if (implTypes.length > 1 && !pk) {
         nullable = true;
      }

      Collection implClasses = new ArrayList();

      for(int i = 0; i < implTypes.length; ++i) {
         Class type = clr.classForName(implTypes[i]);
         if (type == null) {
            throw new NucleusUserException(Localiser.msg("020189", new Object[]{this.mmd.getTypeName(), implTypes[i]}));
         }

         if (type.isInterface()) {
            throw new NucleusUserException(Localiser.msg("020190", new Object[]{this.mmd.getFullFieldName(), this.mmd.getTypeName(), implTypes[i]}));
         }

         Iterator iter = implClasses.iterator();
         boolean toBeAdded = true;
         Class clsToSwap = null;

         while(iter.hasNext()) {
            Class cls = (Class)iter.next();
            if (cls == type) {
               toBeAdded = false;
               break;
            }

            if (type.isAssignableFrom(cls)) {
               clsToSwap = cls;
               toBeAdded = false;
               break;
            }

            if (cls.isAssignableFrom(type)) {
               toBeAdded = false;
               break;
            }
         }

         if (toBeAdded) {
            implClasses.add(type);
         } else if (clsToSwap != null) {
            implClasses.remove(clsToSwap);
            implClasses.add(type);
         }
      }

      int colPos = 0;

      for(Class implClass : implClasses) {
         boolean present = false;
         int numJavaTypeMappings = this.getJavaTypeMapping().length;

         for(int i = 0; i < numJavaTypeMappings; ++i) {
            JavaTypeMapping implMapping = this.getJavaTypeMapping()[i];
            if (implClass.getName().equals(implMapping.getType())) {
               present = true;
            }
         }

         if (!present) {
            String fieldTypeName = this.getReferenceFieldType(fieldRole);
            boolean isPersistentInterfaceField = this.storeMgr.getNucleusContext().getMetaDataManager().isPersistentInterface(fieldTypeName);
            boolean columnsNeeded = true;
            if (isPersistentInterfaceField && !this.storeMgr.getNucleusContext().getMetaDataManager().isPersistentInterfaceImplementation(fieldTypeName, implClass.getName())) {
               columnsNeeded = false;
            }

            if (columnsNeeded) {
               JavaTypeMapping m;
               if (this.storeMgr.getMappedTypeManager().isSupportedMappedType(implClass.getName())) {
                  m = this.storeMgr.getMappingManager().getMapping(implClass, serialised, embedded, this.mmd.getFullFieldName());
               } else {
                  try {
                     DatastoreClass dc = this.storeMgr.getDatastoreClass(implClass.getName(), clr);
                     m = dc.getIdMapping();
                  } catch (NoTableManagedException ex) {
                     throw new NucleusUserException("Cannot define columns for " + this.mmd.getFullFieldName() + " due to " + ex.getMessage(), ex);
                  }
               }

               ColumnMetaData[] columnMetaDataForType = null;
               if (columnMetaData != null && columnMetaData.length > 0) {
                  if (columnMetaData.length < colPos + m.getNumberOfDatastoreMappings()) {
                     throw new NucleusUserException(Localiser.msg("020186", new Object[]{this.mmd.getFullFieldName(), "" + columnMetaData.length, "" + (colPos + m.getNumberOfDatastoreMappings())}));
                  }

                  columnMetaDataForType = new ColumnMetaData[m.getNumberOfDatastoreMappings()];
                  System.arraycopy(columnMetaData, colPos, columnMetaDataForType, 0, columnMetaDataForType.length);
                  colPos += columnMetaDataForType.length;
               }

               ColumnCreator.createColumnsForField(implClass, this, this.table, this.storeMgr, this.mmd, pk, nullable, serialised, embedded, fieldRole, columnMetaDataForType, clr, true);
               if (NucleusLogger.DATASTORE.isInfoEnabled()) {
                  NucleusLogger.DATASTORE.info(Localiser.msg("020188", new Object[]{implClass, this.mmd.getName()}));
               }
            }
         }
      }

   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return (this.mappingStrategy == 1 || this.mappingStrategy == 2) && index == 0 ? String.class.getName() : super.getJavaTypeForDatastoreMapping(index);
   }

   public int getMappingNumberForValue(ExecutionContext ec, Object value) {
      if (this.mappingStrategy == 0) {
         return super.getMappingNumberForValue(ec, value);
      } else if (this.mappingStrategy != 1 && this.mappingStrategy != 2) {
         throw new NucleusException("Mapping strategy of interface/Object fields not yet supported");
      } else {
         return -2;
      }
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] pos, Object value, ObjectProvider ownerOP, int ownerFieldNumber) {
      if (this.mappingStrategy == 0) {
         super.setObject(ec, ps, pos, value, ownerOP, ownerFieldNumber);
      } else if (this.mappingStrategy == 1 || this.mappingStrategy == 2) {
         if (value == null) {
            this.getJavaTypeMapping()[0].setString(ec, ps, pos, (String)null);
         } else {
            this.getJavaTypeMapping()[0].setString(ec, ps, pos, this.getReferenceStringForObject(ec, value));
         }
      }

   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] pos) {
      if (this.mappingStrategy == 0) {
         return super.getObject(ec, rs, pos);
      } else if (this.mappingStrategy != 1 && this.mappingStrategy != 2) {
         throw new NucleusException("Mapping strategy of interface/Object fields not yet supported");
      } else {
         String refString = this.getJavaTypeMapping()[0].getString(ec, rs, pos);
         return refString == null ? null : this.getObjectForReferenceString(ec, refString);
      }
   }

   public Class getJavaType() {
      return null;
   }

   protected String getReferenceStringForObject(ExecutionContext ec, Object value) {
      if (ec.getApiAdapter().isPersistable(value)) {
         ObjectProvider op = ec.findObjectProvider(value);
         if (op == null) {
            ec.persistObjectInternal(value, (ObjectProvider)null, -1, 0);
            op = ec.findObjectProvider(value);
            op.flush();
         }

         String refString = null;
         if (this.mappingStrategy == 1) {
            refString = value.getClass().getName() + ":" + op.getInternalObjectId();
         } else if (this.mappingStrategy == 2) {
            AbstractClassMetaData cmd = op.getClassMetaData();
            DiscriminatorMetaData dismd = cmd.getDiscriminatorMetaData();
            String definer = null;
            if (dismd != null && dismd.getValue() != null) {
               definer = dismd.getValue();
            } else {
               definer = cmd.getFullClassName();
            }

            if (cmd.getIdentityType() == IdentityType.DATASTORE) {
               refString = definer + ":" + IdentityUtils.getTargetKeyForDatastoreIdentity(op.getInternalObjectId());
            } else {
               refString = definer + ":" + op.getInternalObjectId().toString();
            }
         }

         return refString;
      } else {
         throw new NucleusException("Identity mapping of non-persistable interface/Object fields not supported");
      }
   }

   protected Object getObjectForReferenceString(ExecutionContext ec, String refString) {
      int sepPos = refString.indexOf(58);
      String refDefiner = refString.substring(0, sepPos);
      String refClassName = null;
      String refId = refString.substring(sepPos + 1);
      AbstractClassMetaData refCmd = null;
      if (this.mappingStrategy == 1) {
         refCmd = ec.getMetaDataManager().getMetaDataForClass(refDefiner, ec.getClassLoaderResolver());
      } else {
         refCmd = ec.getMetaDataManager().getMetaDataForClass(refDefiner, ec.getClassLoaderResolver());
         if (refCmd == null) {
            refCmd = ec.getMetaDataManager().getMetaDataForDiscriminator(refDefiner);
         }
      }

      if (refCmd == null) {
         throw new NucleusException("Reference field contains reference to class of type " + refDefiner + " but no metadata found for this class");
      } else {
         refClassName = refCmd.getFullClassName();
         Object id = null;
         if (refCmd.getIdentityType() == IdentityType.DATASTORE) {
            if (this.mappingStrategy == 1) {
               id = ec.getNucleusContext().getIdentityManager().getDatastoreId(refId);
            } else if (this.mappingStrategy == 2) {
               id = ec.getNucleusContext().getIdentityManager().getDatastoreId(refCmd.getFullClassName(), refId);
            }
         } else if (refCmd.getIdentityType() == IdentityType.APPLICATION) {
            id = ec.getNucleusContext().getIdentityManager().getApplicationId(ec.getClassLoaderResolver(), refCmd, refId);
         }

         return ec.findObject(id, true, false, refClassName);
      }
   }

   public void postFetch(ObjectProvider op) {
   }

   public void insertPostProcessing(ObjectProvider op) {
   }

   public void postInsert(ObjectProvider op) {
   }

   public void postUpdate(ObjectProvider op) {
   }

   public void preDelete(ObjectProvider op) {
      boolean isDependentElement = this.mmd.isDependent();
      if (isDependentElement) {
         for(int i = 0; i < this.javaTypeMappings.length; ++i) {
            JavaTypeMapping mapping = this.javaTypeMappings[i];
            if (mapping instanceof PersistableMapping) {
               int fieldNumber = this.getMemberMetaData().getAbsoluteFieldNumber();
               op.isLoaded(fieldNumber);
               Object pc = op.provideField(fieldNumber);
               if (pc != null) {
                  op.replaceFieldMakeDirty(fieldNumber, (Object)null);
                  this.storeMgr.getPersistenceHandler().updateObject(op, new int[]{fieldNumber});
                  op.getExecutionContext().deleteObjectInternal(pc);
               }
            }
         }

      }
   }
}
