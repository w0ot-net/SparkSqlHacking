package org.datanucleus.store.rdbms.sql.expression;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.TypeConverterMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.store.types.converters.TypeConverterHelper;

public class TypeConverterLiteral extends DelegatedExpression implements SQLLiteral {
   public TypeConverterLiteral(SQLStatement stmt, JavaTypeMapping mapping, Object value, String parameterName) {
      super(stmt, (SQLTable)null, mapping);
      if (!(mapping instanceof TypeConverterMapping)) {
         throw new NucleusException("Attempt to create TypeConverterLiteral for mapping of type " + mapping.getClass().getName());
      } else {
         TypeConverterMapping convMapping = (TypeConverterMapping)mapping;
         TypeConverter conv = convMapping.getTypeConverter();
         Class datastoreType = TypeConverterHelper.getDatastoreTypeForTypeConverter(conv, convMapping.getJavaType());
         if (datastoreType == String.class) {
            this.delegate = new StringLiteral(stmt, mapping, value, parameterName);
         } else if (Date.class.isAssignableFrom(datastoreType)) {
            this.delegate = new TemporalLiteral(stmt, mapping, value, parameterName);
         } else if (datastoreType != Integer.class && datastoreType != Long.class && datastoreType != Short.class && datastoreType != BigInteger.class) {
            if (datastoreType != Double.class && datastoreType != Float.class && datastoreType != BigDecimal.class) {
               if (datastoreType == Boolean.class) {
                  this.delegate = new BooleanLiteral(stmt, mapping, value, parameterName);
               } else if (datastoreType == Byte.class) {
                  this.delegate = new ByteLiteral(stmt, mapping, value, parameterName);
               } else if (datastoreType == Character.class) {
                  this.delegate = new CharacterLiteral(stmt, mapping, value, parameterName);
               } else {
                  if (datastoreType != Enum.class) {
                     throw new NucleusException("Could not create TypeConverterLiteral for mapping of type " + mapping.getClass().getName() + " with datastoreType=" + datastoreType.getName() + " - no available supported expression");
                  }

                  this.delegate = new EnumLiteral(stmt, mapping, value, parameterName);
               }
            } else {
               this.delegate = new FloatingPointLiteral(stmt, mapping, value, parameterName);
            }
         } else {
            this.delegate = new IntegerLiteral(stmt, mapping, value, parameterName);
         }

      }
   }

   public Object getValue() {
      return ((SQLLiteral)this.delegate).getValue();
   }

   public void setNotParameter() {
      ((SQLLiteral)this.delegate).setNotParameter();
   }
}
