package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.util.ClassUtils;

public class PersistableIdMapping extends PersistableMapping {
   public PersistableIdMapping(PersistableMapping pcMapping) {
      this.initialize(pcMapping.storeMgr, pcMapping.type);
      this.table = pcMapping.table;
      this.javaTypeMappings = new JavaTypeMapping[pcMapping.javaTypeMappings.length];
      System.arraycopy(pcMapping.javaTypeMappings, 0, this.javaTypeMappings, 0, this.javaTypeMappings.length);
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
      } else {
         if (this.cmd == null) {
            this.cmd = ec.getMetaDataManager().getMetaDataForClass(this.getType(), ec.getClassLoaderResolver());
         }

         if (IdentityUtils.isDatastoreIdentity(value)) {
            if (this.getJavaTypeMapping()[0] instanceof DatastoreIdMapping) {
               this.getJavaTypeMapping()[0].setObject(ec, ps, param, value);
            } else {
               Object key = IdentityUtils.getTargetKeyForDatastoreIdentity(value);
               if (key instanceof String) {
                  this.getJavaTypeMapping()[0].setString(ec, ps, param, (String)key);
               } else {
                  this.getJavaTypeMapping()[0].setObject(ec, ps, param, key);
               }
            }
         } else if (IdentityUtils.isSingleFieldIdentity(value)) {
            Object key = IdentityUtils.getTargetKeyForSingleFieldIdentity(value);
            if (key instanceof String) {
               this.getJavaTypeMapping()[0].setString(ec, ps, param, (String)key);
            } else {
               this.getJavaTypeMapping()[0].setObject(ec, ps, param, key);
            }
         } else {
            String[] pkMemberNames = this.cmd.getPrimaryKeyMemberNames();

            for(int i = 0; i < pkMemberNames.length; ++i) {
               Object pkMemberValue = ClassUtils.getValueForIdentityField(value, pkMemberNames[i]);
               if (pkMemberValue instanceof Byte) {
                  this.getDatastoreMapping(i).setByte(ps, param[i], (Byte)pkMemberValue);
               } else if (pkMemberValue instanceof Character) {
                  this.getDatastoreMapping(i).setChar(ps, param[i], (Character)pkMemberValue);
               } else if (pkMemberValue instanceof Integer) {
                  this.getDatastoreMapping(i).setInt(ps, param[i], (Integer)pkMemberValue);
               } else if (pkMemberValue instanceof Long) {
                  this.getDatastoreMapping(i).setLong(ps, param[i], (Long)pkMemberValue);
               } else if (pkMemberValue instanceof Short) {
                  this.getDatastoreMapping(i).setShort(ps, param[i], (Short)pkMemberValue);
               } else if (pkMemberValue instanceof String) {
                  this.getDatastoreMapping(i).setString(ps, param[i], (String)pkMemberValue);
               } else {
                  this.getDatastoreMapping(i).setObject(ps, param[i], pkMemberValue);
               }
            }
         }

      }
   }
}
