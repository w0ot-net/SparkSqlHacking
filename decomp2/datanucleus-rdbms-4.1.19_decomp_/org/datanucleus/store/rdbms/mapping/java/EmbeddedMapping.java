package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.EmbeddedMetaData;
import org.datanucleus.metadata.FieldPersistenceModifier;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.InheritanceMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class EmbeddedMapping extends SingleFieldMapping {
   protected DiscriminatorMetaData discrimMetaData;
   protected DiscriminatorMapping discrimMapping;
   protected List javaTypeMappings;
   protected ClassLoaderResolver clr;
   protected EmbeddedMetaData emd;
   protected String typeName;
   protected short objectType = -1;
   protected AbstractClassMetaData embCmd = null;

   public void initialize(AbstractMemberMetaData fmd, Table table, ClassLoaderResolver clr) {
      throw (new NucleusException("subclass must override this method")).setFatal();
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr, EmbeddedMetaData emd, String typeName, int objectType) {
      super.initialize(mmd, table, clr);
      this.clr = clr;
      this.emd = emd;
      this.typeName = typeName;
      this.objectType = (short)objectType;
      MetaDataManager mmgr = table.getStoreManager().getMetaDataManager();
      AbstractClassMetaData rootEmbCmd = mmgr.getMetaDataForClass(typeName, clr);
      if (rootEmbCmd == null) {
         String[] fieldTypes = mmd.getFieldTypes();
         if (fieldTypes != null && fieldTypes.length == 1) {
            rootEmbCmd = mmgr.getMetaDataForClass(fieldTypes[0], clr);
         } else if (fieldTypes != null && fieldTypes.length > 1) {
            throw new NucleusUserException("Field " + mmd.getFullFieldName() + " is a reference field that is embedded with multiple possible implementations. DataNucleus doesnt support embedded reference fields that have more than 1 implementation");
         }

         if (rootEmbCmd == null) {
            rootEmbCmd = mmgr.getMetaDataForInterface(clr.classForName(typeName), clr);
            if (rootEmbCmd == null && mmd.getFieldTypes() != null && mmd.getFieldTypes().length == 1) {
               rootEmbCmd = mmgr.getMetaDataForInterface(clr.classForName(mmd.getFieldTypes()[0]), clr);
            }
         }
      }

      this.embCmd = rootEmbCmd;
      AbstractMemberMetaData[] embFmds = null;
      if (emd == null && rootEmbCmd.isEmbeddedOnly()) {
         embFmds = rootEmbCmd.getManagedMembers();
      } else if (emd != null) {
         embFmds = emd.getMemberMetaData();
      }

      String[] subclasses = mmgr.getSubclassesForClass(rootEmbCmd.getFullClassName(), true);
      if (subclasses != null && subclasses.length > 0) {
         if (rootEmbCmd.hasDiscriminatorStrategy()) {
            this.discrimMetaData = new DiscriminatorMetaData();
            InheritanceMetaData embInhMd = new InheritanceMetaData();
            embInhMd.setParent(rootEmbCmd);
            this.discrimMetaData.setParent(embInhMd);
            DiscriminatorMetaData dismd = rootEmbCmd.getDiscriminatorMetaDataRoot();
            if (dismd.getStrategy() != null && dismd.getStrategy() != DiscriminatorStrategy.NONE) {
               this.discrimMetaData.setStrategy(dismd.getStrategy());
            } else {
               this.discrimMetaData.setStrategy(DiscriminatorStrategy.CLASS_NAME);
            }

            ColumnMetaData disColmd = new ColumnMetaData();
            disColmd.setAllowsNull(Boolean.TRUE);
            DiscriminatorMetaData embDismd = emd.getDiscriminatorMetaData();
            if (embDismd != null && embDismd.getColumnMetaData() != null) {
               disColmd.setName(embDismd.getColumnMetaData().getName());
            } else {
               ColumnMetaData colmd = dismd.getColumnMetaData();
               if (colmd != null && colmd.getName() != null) {
                  disColmd.setName(colmd.getName());
               }
            }

            this.discrimMetaData.setColumnMetaData(disColmd);
            this.discrimMapping = DiscriminatorMapping.createDiscriminatorMapping(table, this.discrimMetaData);
            this.addDatastoreMapping(this.discrimMapping.getDatastoreMapping(0));
         } else {
            NucleusLogger.PERSISTENCE.info("Member " + mmd.getFullFieldName() + " is embedded and the type (" + rootEmbCmd.getFullClassName() + ") has potential subclasses. Impossible to detect which is stored embedded. Add a discriminator to the embedded type");
         }
      }

      int[] pcFieldNumbers = rootEmbCmd.getAllMemberPositions();

      for(int i = 0; i < pcFieldNumbers.length; ++i) {
         AbstractMemberMetaData rootEmbMmd = rootEmbCmd.getMetaDataForManagedMemberAtAbsolutePosition(pcFieldNumbers[i]);
         if (rootEmbMmd.getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
            this.addMappingForMember(rootEmbCmd, rootEmbMmd, embFmds);
         }
      }

      if (this.discrimMapping != null && subclasses != null && subclasses.length > 0) {
         for(int i = 0; i < subclasses.length; ++i) {
            AbstractClassMetaData subEmbCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(subclasses[i], clr);
            AbstractMemberMetaData[] subEmbMmds = subEmbCmd.getManagedMembers();
            if (subEmbMmds != null) {
               for(int j = 0; j < subEmbMmds.length; ++j) {
                  if (subEmbMmds[j].getPersistenceModifier() == FieldPersistenceModifier.PERSISTENT) {
                     this.addMappingForMember(subEmbCmd, subEmbMmds[j], embFmds);
                  }
               }
            }
         }
      }

   }

   private void addMappingForMember(AbstractClassMetaData embCmd, AbstractMemberMetaData embMmd, AbstractMemberMetaData[] embMmds) {
      if (this.emd == null || this.emd.getOwnerMember() == null || !this.emd.getOwnerMember().equals(embMmd.getName())) {
         AbstractMemberMetaData embeddedMmd = null;

         for(int j = 0; j < embMmds.length; ++j) {
            if (embMmds[j] == null) {
               throw new RuntimeException("embMmds[j] is null for class=" + embCmd.toString() + " type=" + this.typeName);
            }

            AbstractMemberMetaData embMmdForMmds = embCmd.getMetaDataForMember(embMmds[j].getName());
            if (embMmdForMmds != null && embMmdForMmds.getAbsoluteFieldNumber() == embMmd.getAbsoluteFieldNumber()) {
               embeddedMmd = embMmds[j];
            }
         }

         MappingManager mapMgr = this.table.getStoreManager().getMappingManager();
         JavaTypeMapping embMmdMapping;
         if (embeddedMmd != null) {
            embMmdMapping = mapMgr.getMapping(this.table, embeddedMmd, this.clr, FieldRole.ROLE_FIELD);
         } else {
            embMmdMapping = mapMgr.getMapping(this.table, embMmd, this.clr, FieldRole.ROLE_FIELD);
         }

         embMmdMapping.setAbsFieldNumber(embMmd.getAbsoluteFieldNumber());
         this.addJavaTypeMapping(embMmdMapping);

         for(int j = 0; j < embMmdMapping.getNumberOfDatastoreMappings(); ++j) {
            DatastoreMapping datastoreMapping = embMmdMapping.getDatastoreMapping(j);
            this.addDatastoreMapping(datastoreMapping);
            if (this.mmd.isPrimaryKey()) {
               Column col = datastoreMapping.getColumn();
               if (col != null) {
                  col.setPrimaryKey();
               }
            }
         }
      }

   }

   protected void prepareDatastoreMapping() {
   }

   public void addJavaTypeMapping(JavaTypeMapping mapping) {
      if (this.javaTypeMappings == null) {
         this.javaTypeMappings = new ArrayList();
      }

      if (mapping == null) {
         throw (new NucleusException("mapping argument in EmbeddedMapping.addJavaTypeMapping is null")).setFatal();
      } else {
         this.javaTypeMappings.add(mapping);
      }
   }

   public int getNumberOfJavaTypeMappings() {
      return this.javaTypeMappings != null ? this.javaTypeMappings.size() : 0;
   }

   public JavaTypeMapping getJavaTypeMapping(int i) {
      return this.javaTypeMappings == null ? null : (JavaTypeMapping)this.javaTypeMappings.get(i);
   }

   public JavaTypeMapping getJavaTypeMapping(String fieldName) {
      if (this.javaTypeMappings == null) {
         return null;
      } else {
         for(JavaTypeMapping m : this.javaTypeMappings) {
            if (m.getMemberMetaData().getName().equals(fieldName)) {
               return m;
            }
         }

         return null;
      }
   }

   public JavaTypeMapping getDiscriminatorMapping() {
      return this.discrimMapping;
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] param, Object value) {
      this.setObject(ec, ps, param, value, (ObjectProvider)null, -1);
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] param, Object value, ObjectProvider ownerOP, int ownerFieldNumber) {
      if (value == null) {
         int n = 0;
         String nullColumn = null;
         String nullValue = null;
         if (this.emd != null) {
            nullColumn = this.emd.getNullIndicatorColumn();
            nullValue = this.emd.getNullIndicatorValue();
         }

         if (this.discrimMapping != null) {
            this.discrimMapping.setObject(ec, ps, new int[]{param[n]}, (Object)null);
            ++n;
         }

         for(int i = 0; i < this.javaTypeMappings.size(); ++i) {
            JavaTypeMapping mapping = (JavaTypeMapping)this.javaTypeMappings.get(i);
            int[] posMapping = new int[mapping.getNumberOfDatastoreMappings()];

            for(int j = 0; j < posMapping.length; ++j) {
               posMapping[j] = param[n++];
            }

            if (nullColumn != null && nullValue != null && mapping.getMemberMetaData().getColumnMetaData().length > 0 && mapping.getMemberMetaData().getColumnMetaData()[0].getName().equals(nullColumn)) {
               if (!(mapping instanceof IntegerMapping) && !(mapping instanceof BigIntegerMapping) && !(mapping instanceof LongMapping) && !(mapping instanceof ShortMapping)) {
                  mapping.setObject(ec, ps, posMapping, nullValue);
               } else {
                  Object convertedValue = null;

                  try {
                     if (!(mapping instanceof IntegerMapping) && !(mapping instanceof ShortMapping)) {
                        if (mapping instanceof LongMapping || mapping instanceof BigIntegerMapping) {
                           convertedValue = Long.valueOf(nullValue);
                        }
                     } else {
                        convertedValue = Integer.valueOf(nullValue);
                     }
                  } catch (Exception var16) {
                  }

                  mapping.setObject(ec, ps, posMapping, convertedValue);
               }
            } else if (mapping.getNumberOfDatastoreMappings() > 0) {
               mapping.setObject(ec, ps, posMapping, (Object)null);
            }
         }
      } else {
         ApiAdapter api = ec.getApiAdapter();
         if (!api.isPersistable(value)) {
            throw (new NucleusException(Localiser.msg("041016", new Object[]{value.getClass(), value}))).setFatal();
         }

         AbstractClassMetaData embCmd = ec.getMetaDataManager().getMetaDataForClass(value.getClass(), ec.getClassLoaderResolver());
         ObjectProvider embSM = ec.findObjectProvider(value);
         if (embSM == null || api.getExecutionContext(value) == null) {
            embSM = ec.getNucleusContext().getObjectProviderFactory().newForEmbedded(ec, value, false, ownerOP, ownerFieldNumber);
            embSM.setPcObjectType(this.objectType);
         }

         int n = 0;
         if (this.discrimMapping != null) {
            if (this.discrimMetaData.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
               this.discrimMapping.setObject(ec, ps, new int[]{param[n]}, value.getClass().getName());
            } else if (this.discrimMetaData.getStrategy() == DiscriminatorStrategy.VALUE_MAP) {
               DiscriminatorMetaData valueDismd = embCmd.getInheritanceMetaData().getDiscriminatorMetaData();
               this.discrimMapping.setObject(ec, ps, new int[]{param[n]}, valueDismd.getValue());
            }

            ++n;
         }

         for(int i = 0; i < this.javaTypeMappings.size(); ++i) {
            JavaTypeMapping mapping = (JavaTypeMapping)this.javaTypeMappings.get(i);
            int[] posMapping = new int[mapping.getNumberOfDatastoreMappings()];

            for(int j = 0; j < posMapping.length; ++j) {
               posMapping[j] = param[n++];
            }

            int embAbsFieldNum = embCmd.getAbsolutePositionOfMember(mapping.getMemberMetaData().getName());
            if (embAbsFieldNum >= 0) {
               Object fieldValue = embSM.provideField(embAbsFieldNum);
               if (mapping instanceof EmbeddedPCMapping) {
                  mapping.setObject(ec, ps, posMapping, fieldValue, embSM, embAbsFieldNum);
               } else if (mapping.getNumberOfDatastoreMappings() > 0) {
                  mapping.setObject(ec, ps, posMapping, fieldValue);
               }
            } else {
               mapping.setObject(ec, ps, posMapping, (Object)null);
            }
         }
      }

   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] param) {
      return this.getObject(ec, rs, param, (ObjectProvider)null, -1);
   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] param, ObjectProvider ownerOP, int ownerFieldNumber) {
      Object value = null;
      int n = 0;
      AbstractClassMetaData embCmd = this.embCmd;
      if (this.discrimMapping != null) {
         Object discrimValue = this.discrimMapping.getObject(ec, rs, new int[]{param[n]});
         String className = ec.getMetaDataManager().getClassNameFromDiscriminatorValue((String)discrimValue, this.discrimMetaData);
         embCmd = this.storeMgr.getMetaDataManager().getMetaDataForClass(className, this.clr);
         ++n;
      }

      Class embeddedType = this.clr.classForName(embCmd.getFullClassName());
      if (this.mmd.getFieldTypes() != null && this.mmd.getFieldTypes().length > 0) {
         embeddedType = ec.getClassLoaderResolver().classForName(this.mmd.getFieldTypes()[0]);
      }

      ObjectProvider embOP = ec.getNucleusContext().getObjectProviderFactory().newForHollow(ec, embeddedType, (Object)null);
      embOP.setPcObjectType(this.objectType);
      value = embOP.getObject();
      String nullColumn = null;
      String nullValue = null;
      if (this.emd != null) {
         nullColumn = this.emd.getNullIndicatorColumn();
         nullValue = this.emd.getNullIndicatorValue();
      }

      for(int i = 0; i < this.javaTypeMappings.size(); ++i) {
         JavaTypeMapping mapping = (JavaTypeMapping)this.javaTypeMappings.get(i);
         int embAbsFieldNum = embCmd.getAbsolutePositionOfMember(mapping.getMemberMetaData().getName());
         if (embAbsFieldNum < 0) {
            int numSubParams = mapping.getNumberOfDatastoreMappings();
            n += numSubParams;
         } else if (mapping instanceof EmbeddedPCMapping) {
            int numSubParams = mapping.getNumberOfDatastoreMappings();
            int[] subParam = new int[numSubParams];
            int k = 0;

            for(int j = n; j < n + numSubParams; ++j) {
               subParam[k++] = param[j];
            }

            n += numSubParams;
            Object subValue = mapping.getObject(ec, rs, subParam, embOP, embAbsFieldNum);
            if (subValue != null) {
               embOP.replaceField(embAbsFieldNum, subValue);
            }
         } else {
            int[] posMapping = new int[mapping.getNumberOfDatastoreMappings()];

            for(int j = 0; j < posMapping.length; ++j) {
               posMapping[j] = param[n++];
            }

            Object fieldValue = mapping.getObject(ec, rs, posMapping);
            if (nullColumn != null && mapping.getMemberMetaData().getColumnMetaData()[0].getName().equals(nullColumn) && (nullValue == null && fieldValue == null || nullValue != null && fieldValue.toString().equals(nullValue))) {
               value = null;
               break;
            }

            if (fieldValue != null) {
               embOP.replaceField(embAbsFieldNum, fieldValue);
            } else {
               AbstractMemberMetaData embFmd = embCmd.getMetaDataForManagedMemberAtAbsolutePosition(embAbsFieldNum);
               if (!embFmd.getType().isPrimitive()) {
                  embOP.replaceField(embAbsFieldNum, fieldValue);
               }
            }
         }
      }

      if (this.emd != null) {
         String ownerField = this.emd.getOwnerMember();
         if (ownerField != null) {
            int ownerFieldNumberInElement = embCmd.getAbsolutePositionOfMember(ownerField);
            if (ownerFieldNumberInElement >= 0) {
               embOP.replaceField(ownerFieldNumberInElement, ownerOP.getObject());
            }
         }
      }

      if (value != null && ownerOP != null) {
         ec.registerEmbeddedRelation(ownerOP, ownerFieldNumber, embOP);
      }

      return value;
   }

   public Class getJavaType() {
      return this.clr.classForName(this.typeName);
   }
}
