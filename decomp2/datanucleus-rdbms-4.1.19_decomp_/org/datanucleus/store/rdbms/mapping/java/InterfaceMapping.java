package org.datanucleus.store.rdbms.mapping.java;

import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.exceptions.NullValueException;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.StringUtils;

public class InterfaceMapping extends ReferenceMapping {
   private String implementationClasses;

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      super.initialize(mmd, table, clr);
      if (mmd.getType().isInterface() && mmd.getFieldTypes() != null && mmd.getFieldTypes().length == 1) {
         Class fieldTypeCls = clr.classForName(mmd.getFieldTypes()[0]);
         if (fieldTypeCls.isInterface()) {
            this.type = mmd.getFieldTypes()[0];
         }
      }

   }

   public void setImplementationClasses(String implementationClasses) {
      this.implementationClasses = implementationClasses;
   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] pos) {
      if (!ec.getMetaDataManager().isPersistentInterface(this.type)) {
         return super.getObject(ec, rs, pos);
      } else if (this.mappingStrategy != 1 && this.mappingStrategy != 2) {
         String[] implTypes = null;
         if (this.implementationClasses != null) {
            implTypes = StringUtils.split(this.implementationClasses, ",");
         } else {
            implTypes = ec.getMetaDataManager().getClassesImplementingInterface(this.getType(), ec.getClassLoaderResolver());
         }

         int n = 0;

         for(int i = 0; i < implTypes.length; ++i) {
            JavaTypeMapping mapping;
            if (implTypes.length <= this.javaTypeMappings.length) {
               mapping = this.javaTypeMappings[i];
            } else {
               PersistableMapping m = (PersistableMapping)this.javaTypeMappings[0];
               mapping = this.storeMgr.getMappingManager().getMapping(ec.getClassLoaderResolver().classForName(implTypes[i]));

               for(int j = 0; j < m.getDatastoreMappings().length; ++j) {
                  mapping.addDatastoreMapping(m.getDatastoreMappings()[j]);
               }

               for(int j = 0; j < m.getJavaTypeMapping().length; ++j) {
                  ((PersistableMapping)mapping).addJavaTypeMapping(m.getJavaTypeMapping()[j]);
               }

               ((PersistableMapping)mapping).setReferenceMapping(m.getReferenceMapping());
            }

            if (n >= pos.length) {
               n = 0;
            }

            int[] posMapping;
            if (mapping.getReferenceMapping() != null) {
               posMapping = new int[mapping.getReferenceMapping().getNumberOfDatastoreMappings()];
            } else {
               posMapping = new int[mapping.getNumberOfDatastoreMappings()];
            }

            for(int j = 0; j < posMapping.length; ++j) {
               posMapping[j] = pos[n++];
            }

            Object value = null;

            try {
               value = mapping.getObject(ec, rs, posMapping);
            } catch (NullValueException var11) {
            } catch (NucleusObjectNotFoundException var12) {
            }

            if (value != null) {
               if (IdentityUtils.isDatastoreIdentity(value)) {
                  String className;
                  if (mapping.getReferenceMapping() != null) {
                     className = mapping.getReferenceMapping().getDatastoreMapping(0).getColumn().getStoredJavaType();
                  } else {
                     className = mapping.getDatastoreMapping(0).getColumn().getStoredJavaType();
                  }

                  Object var18 = ec.getNucleusContext().getIdentityManager().getDatastoreId(className, IdentityUtils.getTargetKeyForDatastoreIdentity(value));
                  return ec.findObject(var18, false, true, (String)null);
               }

               if (ec.getClassLoaderResolver().classForName(this.getType()).isAssignableFrom(value.getClass())) {
                  return value;
               }
            }
         }

         return null;
      } else {
         throw new NucleusUserException("DataNucleus does not support use of mapping-strategy=" + this.mappingStrategy + " with a \"persistable interface\"");
      }
   }
}
