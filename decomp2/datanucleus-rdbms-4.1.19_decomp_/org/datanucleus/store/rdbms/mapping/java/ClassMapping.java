package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.types.converters.ClassStringConverter;

public class ClassMapping extends SingleFieldMapping {
   private static ClassStringConverter converter = new ClassStringConverter();

   public void initialize(AbstractMemberMetaData fmd, Table table, ClassLoaderResolver clr) {
      super.initialize(fmd, table, clr);
      converter.setClassLoaderResolver(this.storeMgr.getNucleusContext().getClassLoaderResolver((ClassLoader)null));
   }

   public Class getJavaType() {
      return Class.class;
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return ClassNameConstants.JAVA_LANG_STRING;
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      this.getDatastoreMapping(0).setObject(ps, exprIndex[0], converter.toDatastoreType((Class)value));
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      if (exprIndex == null) {
         return null;
      } else {
         Object datastoreValue = this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]);
         Object value = null;
         if (datastoreValue != null) {
            value = converter.toMemberType((String)datastoreValue);
         }

         return value;
      }
   }
}
