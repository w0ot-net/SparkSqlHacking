package org.datanucleus.store.rdbms.mapping;

import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ElementMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.KeyMetaData;
import org.datanucleus.metadata.ValueMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.ArrayMapping;
import org.datanucleus.store.rdbms.mapping.java.BitSetMapping;
import org.datanucleus.store.rdbms.mapping.java.CollectionMapping;
import org.datanucleus.store.rdbms.mapping.java.MapMapping;
import org.datanucleus.store.rdbms.mapping.java.OracleArrayMapping;
import org.datanucleus.store.rdbms.mapping.java.OracleBitSetMapping;
import org.datanucleus.store.rdbms.mapping.java.OracleCollectionMapping;
import org.datanucleus.store.rdbms.mapping.java.OracleMapMapping;
import org.datanucleus.store.rdbms.mapping.java.OracleSerialisedObjectMapping;
import org.datanucleus.store.rdbms.mapping.java.OracleSerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.OracleStringLobMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.StringMapping;

public class OracleRDBMSMappingManager extends RDBMSMappingManager {
   public OracleRDBMSMappingManager(RDBMSStoreManager storeMgr) {
      super(storeMgr);
   }

   protected Class getOverrideMappingClass(Class mappingClass, AbstractMemberMetaData mmd, FieldRole fieldRole) {
      if (mappingClass.equals(BitSetMapping.class)) {
         return OracleBitSetMapping.class;
      } else if (!mappingClass.equals(StringMapping.class)) {
         if (mappingClass.equals(SerialisedMapping.class)) {
            return OracleSerialisedObjectMapping.class;
         } else if (mappingClass.equals(SerialisedPCMapping.class)) {
            return OracleSerialisedPCMapping.class;
         } else if (mappingClass.equals(ArrayMapping.class)) {
            return OracleArrayMapping.class;
         } else if (mappingClass.equals(MapMapping.class)) {
            return OracleMapMapping.class;
         } else {
            return mappingClass.equals(CollectionMapping.class) ? OracleCollectionMapping.class : mappingClass;
         }
      } else {
         String jdbcType = null;
         if (fieldRole != FieldRole.ROLE_COLLECTION_ELEMENT && fieldRole != FieldRole.ROLE_ARRAY_ELEMENT) {
            if (fieldRole == FieldRole.ROLE_MAP_KEY) {
               KeyMetaData keymd = mmd != null ? mmd.getKeyMetaData() : null;
               if (keymd != null && keymd.getColumnMetaData() != null && keymd.getColumnMetaData().length > 0) {
                  jdbcType = keymd.getColumnMetaData()[0].getJdbcTypeName();
               }
            } else if (fieldRole == FieldRole.ROLE_MAP_VALUE) {
               ValueMetaData valmd = mmd != null ? mmd.getValueMetaData() : null;
               if (valmd != null && valmd.getColumnMetaData() != null && valmd.getColumnMetaData().length > 0) {
                  jdbcType = valmd.getColumnMetaData()[0].getJdbcTypeName();
               }
            } else if (mmd != null && mmd.getColumnMetaData() != null && mmd.getColumnMetaData().length > 0) {
               jdbcType = mmd.getColumnMetaData()[0].getJdbcTypeName();
            }
         } else {
            ElementMetaData elemmd = mmd != null ? mmd.getElementMetaData() : null;
            if (elemmd != null && elemmd.getColumnMetaData() != null && elemmd.getColumnMetaData().length > 0) {
               jdbcType = elemmd.getColumnMetaData()[0].getJdbcTypeName();
            }
         }

         if (jdbcType != null) {
            String jdbcTypeLower = jdbcType.toLowerCase();
            if (jdbcTypeLower.indexOf("blob") >= 0 || jdbcTypeLower.indexOf("clob") >= 0) {
               return OracleStringLobMapping.class;
            }
         }

         return mappingClass;
      }
   }
}
