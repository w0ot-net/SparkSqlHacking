package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.util.ClassUtils;

public class ReferenceIdMapping extends ReferenceMapping {
   public ReferenceIdMapping(ReferenceMapping refMapping) {
      this.initialize(refMapping.storeMgr, refMapping.type);
      this.table = refMapping.table;
      this.javaTypeMappings = new JavaTypeMapping[refMapping.javaTypeMappings.length];
      System.arraycopy(refMapping.javaTypeMappings, 0, this.javaTypeMappings, 0, this.javaTypeMappings.length);
   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] param) {
      Object value = super.getObject(ec, rs, param);
      if (value != null) {
         ApiAdapter api = ec.getApiAdapter();
         return api.getIdForObject(value);
      } else {
         return null;
      }
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] param, Object value) {
      if (value == null) {
         super.setObject(ec, ps, param, (Object)null);
      } else if (this.mappingStrategy != 1 && this.mappingStrategy != 2) {
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         int colPos = 0;

         for(int i = 0; i < this.javaTypeMappings.length; ++i) {
            int[] cols = new int[this.javaTypeMappings[i].getNumberOfDatastoreMappings()];

            for(int j = 0; j < cols.length; ++j) {
               cols[j] = param[colPos++];
            }

            Class cls = clr.classForName(this.javaTypeMappings[i].getType());
            AbstractClassMetaData implCmd = ec.getMetaDataManager().getMetaDataForClass(cls, clr);
            if (implCmd.getObjectidClass().equals(value.getClass().getName())) {
               if (IdentityUtils.isDatastoreIdentity(value)) {
                  Object key = IdentityUtils.getTargetKeyForDatastoreIdentity(value);
                  if (key instanceof String) {
                     this.javaTypeMappings[i].setString(ec, ps, cols, (String)key);
                  } else {
                     this.javaTypeMappings[i].setObject(ec, ps, cols, key);
                  }
               } else if (IdentityUtils.isSingleFieldIdentity(value)) {
                  Object key = IdentityUtils.getTargetKeyForSingleFieldIdentity(value);
                  if (key instanceof String) {
                     this.javaTypeMappings[i].setString(ec, ps, cols, (String)key);
                  } else {
                     this.javaTypeMappings[i].setObject(ec, ps, cols, key);
                  }
               } else {
                  String[] pkMemberNames = implCmd.getPrimaryKeyMemberNames();

                  for(int j = 0; j < pkMemberNames.length; ++j) {
                     Object pkMemberValue = ClassUtils.getValueForIdentityField(value, pkMemberNames[j]);
                     if (pkMemberValue instanceof Byte) {
                        this.getDatastoreMapping(j).setByte(ps, param[j], (Byte)pkMemberValue);
                     } else if (pkMemberValue instanceof Character) {
                        this.getDatastoreMapping(j).setChar(ps, param[j], (Character)pkMemberValue);
                     } else if (pkMemberValue instanceof Integer) {
                        this.getDatastoreMapping(j).setInt(ps, param[j], (Integer)pkMemberValue);
                     } else if (pkMemberValue instanceof Long) {
                        this.getDatastoreMapping(j).setLong(ps, param[j], (Long)pkMemberValue);
                     } else if (pkMemberValue instanceof Short) {
                        this.getDatastoreMapping(j).setShort(ps, param[j], (Short)pkMemberValue);
                     } else if (pkMemberValue instanceof String) {
                        this.getDatastoreMapping(j).setString(ps, param[j], (String)pkMemberValue);
                     } else {
                        this.getDatastoreMapping(j).setObject(ps, param[j], pkMemberValue);
                     }
                  }
               }
            } else {
               this.javaTypeMappings[i].setObject(ec, ps, cols, (Object)null);
            }
         }

      } else {
         String refString = this.getReferenceStringForObject(ec, value);
         this.getJavaTypeMapping()[0].setString(ec, ps, param, refString);
      }
   }
}
