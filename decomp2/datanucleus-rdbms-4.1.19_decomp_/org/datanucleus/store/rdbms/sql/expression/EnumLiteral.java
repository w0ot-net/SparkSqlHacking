package org.datanucleus.store.rdbms.sql.expression;

import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.EnumMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.util.TypeConversionHelper;

public class EnumLiteral extends EnumExpression implements SQLLiteral {
   private final Enum value;

   public EnumLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super(stmt, (SQLTable)null, mapping);
      this.parameterName = parameterName;
      if (value == null) {
         this.value = null;
      } else {
         if (!(value instanceof Enum)) {
            throw new NucleusException("Cannot create " + this.getClass().getName() + " for value of type " + value.getClass().getName());
         }

         this.value = (Enum)value;
      }

      if (mapping.getJavaTypeForDatastoreMapping(0).equals(ClassNameConstants.JAVA_LANG_STRING)) {
         this.delegate = new StringLiteral(stmt, mapping, this.value != null ? this.value.name() : null, parameterName);
      } else {
         this.delegate = new IntegerLiteral(stmt, mapping, this.getValueAsInt(mapping), parameterName);
      }

   }

   public void setJavaTypeMapping(JavaTypeMapping mapping) {
      super.setJavaTypeMapping(mapping);
      if (mapping.getJavaTypeForDatastoreMapping(0).equals(ClassNameConstants.JAVA_LANG_STRING)) {
         this.delegate = new StringLiteral(this.stmt, mapping, this.value != null ? this.value.name() : null, this.parameterName);
      } else {
         this.delegate = new IntegerLiteral(this.stmt, mapping, this.getValueAsInt(mapping), this.parameterName);
      }

   }

   public Object getValue() {
      return this.value;
   }

   public boolean isParameter() {
      return this.delegate.isParameter();
   }

   public void setNotParameter() {
      ((SQLLiteral)this.delegate).setNotParameter();
   }

   private Integer getValueAsInt(JavaTypeMapping mapping) {
      Integer val = null;
      if (this.value != null) {
         val = this.value.ordinal();
         if (mapping instanceof EnumMapping) {
            EnumMapping m = (EnumMapping)mapping;
            val = (int)TypeConversionHelper.getValueFromEnum(m.getMemberMetaData(), m.getRoleForMember(), this.value);
         }
      }

      return val;
   }
}
