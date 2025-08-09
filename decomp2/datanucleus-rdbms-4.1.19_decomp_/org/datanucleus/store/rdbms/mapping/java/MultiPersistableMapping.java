package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.store.rdbms.exceptions.NullValueException;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class MultiPersistableMapping extends MultiMapping {
   protected int getMappingNumberForValue(ExecutionContext ec, Object value) {
      if (value == null) {
         return -1;
      } else {
         ClassLoaderResolver clr = ec.getClassLoaderResolver();

         for(int i = 0; i < this.javaTypeMappings.length; ++i) {
            Class cls = clr.classForName(this.javaTypeMappings[i].getType());
            if (cls.isAssignableFrom(value.getClass())) {
               return i;
            }
         }

         Class mappingJavaType = null;
         MetaDataManager mmgr = this.storeMgr.getNucleusContext().getMetaDataManager();
         boolean isPersistentInterface = mmgr.isPersistentInterface(this.getType());
         if (isPersistentInterface) {
            mappingJavaType = clr.classForName(this.getType());
         } else if (this.mmd != null && this.mmd.getFieldTypes() != null && this.mmd.getFieldTypes().length == 1) {
            isPersistentInterface = mmgr.isPersistentInterface(this.mmd.getFieldTypes()[0]);
            if (isPersistentInterface) {
               mappingJavaType = clr.classForName(this.mmd.getFieldTypes()[0]);
            }
         }

         if (mappingJavaType != null && mappingJavaType.isAssignableFrom(value.getClass())) {
            return -2;
         } else {
            return -1;
         }
      }
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] pos, Object value) {
      this.setObject(ec, ps, pos, value, (ObjectProvider)null, -1);
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] pos, Object value, ObjectProvider ownerOP, int ownerFieldNumber) {
      boolean setValueFKOnly = false;
      if (pos != null && pos.length < this.getNumberOfDatastoreMappings()) {
         setValueFKOnly = true;
      }

      int javaTypeMappingNumber = this.getMappingNumberForValue(ec, value);
      if (value != null && javaTypeMappingNumber == -1) {
         throw new ClassCastException(Localiser.msg("041044", new Object[]{this.mmd != null ? this.mmd.getFullFieldName() : "", this.getType(), value.getClass().getName()}));
      } else {
         if (value != null) {
            ApiAdapter api = ec.getApiAdapter();
            ClassLoaderResolver clr = ec.getClassLoaderResolver();
            if (!ec.isInserting(value)) {
               Object id = api.getIdForObject(value);
               boolean requiresPersisting = false;
               if (ec.getApiAdapter().isDetached(value) && ownerOP != null) {
                  requiresPersisting = true;
               } else if (id == null) {
                  requiresPersisting = true;
               } else {
                  ExecutionContext valueEC = api.getExecutionContext(value);
                  if (valueEC != null && ec != valueEC) {
                     throw new NucleusUserException(Localiser.msg("041015"), id);
                  }
               }

               if (requiresPersisting) {
                  Object pcNew = ec.persistObjectInternal(value, (ObjectProvider)null, -1, 0);
                  ec.flushInternal(false);
                  api.getIdForObject(pcNew);
                  if (ec.getApiAdapter().isDetached(value) && ownerOP != null) {
                     ownerOP.replaceFieldMakeDirty(ownerFieldNumber, pcNew);
                     RelationType relationType = this.mmd.getRelationType(clr);
                     if (relationType == RelationType.ONE_TO_ONE_BI) {
                        ObjectProvider relatedSM = ec.findObjectProvider(pcNew);
                        AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(clr);
                        relatedSM.replaceFieldMakeDirty(relatedMmds[0].getAbsoluteFieldNumber(), ownerOP.getObject());
                     } else if (relationType == RelationType.MANY_TO_ONE_BI && NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                        NucleusLogger.PERSISTENCE.debug("PCMapping.setObject : object " + ownerOP.getInternalObjectId() + " has field " + ownerFieldNumber + " that is 1-N bidirectional - should really update the reference in the relation. Not yet supported");
                     }
                  }
               }

               if (this.getNumberOfDatastoreMappings() <= 0) {
                  return;
               }
            }
         }

         if (pos != null) {
            ObjectProvider op = value != null ? ec.findObjectProvider(value) : null;

            try {
               if (op != null) {
                  op.setStoringPC();
               }

               int n = 0;
               NotYetFlushedException notYetFlushed = null;

               for(int i = 0; i < this.javaTypeMappings.length; ++i) {
                  if (setValueFKOnly) {
                     n = 0;
                  } else if (n >= pos.length) {
                     n = 0;
                  }

                  int[] posMapping;
                  if (this.javaTypeMappings[i].getReferenceMapping() != null) {
                     posMapping = new int[this.javaTypeMappings[i].getReferenceMapping().getNumberOfDatastoreMappings()];
                  } else {
                     posMapping = new int[this.javaTypeMappings[i].getNumberOfDatastoreMappings()];
                  }

                  for(int j = 0; j < posMapping.length; ++j) {
                     posMapping[j] = pos[n++];
                  }

                  try {
                     if (javaTypeMappingNumber != -2 && (value == null || javaTypeMappingNumber != i)) {
                        if (!setValueFKOnly) {
                           this.javaTypeMappings[i].setObject(ec, ps, posMapping, (Object)null);
                        }
                     } else {
                        this.javaTypeMappings[i].setObject(ec, ps, posMapping, value);
                     }
                  } catch (NotYetFlushedException e) {
                     notYetFlushed = e;
                  }
               }

               if (notYetFlushed != null) {
                  throw notYetFlushed;
               }
            } finally {
               if (op != null) {
                  op.unsetStoringPC();
               }

            }

         }
      }
   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] pos) {
      int n = 0;

      for(int i = 0; i < this.javaTypeMappings.length; ++i) {
         if (n >= pos.length) {
            n = 0;
         }

         int[] posMapping;
         if (this.javaTypeMappings[i].getReferenceMapping() != null) {
            posMapping = new int[this.javaTypeMappings[i].getReferenceMapping().getNumberOfDatastoreMappings()];
         } else {
            posMapping = new int[this.javaTypeMappings[i].getNumberOfDatastoreMappings()];
         }

         for(int j = 0; j < posMapping.length; ++j) {
            posMapping[j] = pos[n++];
         }

         Object value = null;

         try {
            value = this.javaTypeMappings[i].getObject(ec, rs, posMapping);
            if (value != null) {
               if (IdentityUtils.isDatastoreIdentity(value)) {
                  Column col = null;
                  if (this.javaTypeMappings[i].getReferenceMapping() != null) {
                     col = this.javaTypeMappings[i].getReferenceMapping().getDatastoreMapping(0).getColumn();
                  } else {
                     col = this.javaTypeMappings[i].getDatastoreMapping(0).getColumn();
                  }

                  String className = col.getStoredJavaType();
                  Object var14 = ec.getNucleusContext().getIdentityManager().getDatastoreId(className, IdentityUtils.getTargetKeyForDatastoreIdentity(value));
                  return ec.findObject(var14, false, true, (String)null);
               }

               if (ec.getClassLoaderResolver().classForName(this.getType()).isAssignableFrom(value.getClass())) {
                  return value;
               }
            }
         } catch (NullValueException var10) {
         } catch (NucleusObjectNotFoundException var11) {
         }
      }

      return null;
   }
}
