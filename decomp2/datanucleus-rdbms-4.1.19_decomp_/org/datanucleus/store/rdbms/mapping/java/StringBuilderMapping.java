package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.store.types.converters.StringBuilderStringConverter;
import org.datanucleus.store.types.converters.TypeConverter;

public class StringBuilderMapping extends StringMapping {
   protected static final TypeConverter converter = new StringBuilderStringConverter();

   public String getJavaTypeForDatastoreMapping(int index) {
      return ClassNameConstants.JAVA_LANG_STRING;
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] exprIndex, Object value) {
      Object v = converter.toDatastoreType((StringBuilder)value);
      super.setObject(ec, ps, exprIndex, v);
   }

   public Object getObject(ExecutionContext ec, ResultSet resultSet, int[] exprIndex) {
      if (exprIndex == null) {
         return null;
      } else {
         Object value = this.getDatastoreMapping(0).getObject(resultSet, exprIndex[0]);
         return value != null ? converter.toMemberType((String)value) : null;
      }
   }

   public Class getJavaType() {
      return StringBuilder.class;
   }
}
