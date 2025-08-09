package org.datanucleus.store.rdbms.sql.expression;

import java.util.Date;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.TypeConverterMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.types.converters.TypeConverter;
import org.datanucleus.store.types.converters.TypeConverterHelper;

public class TypeConverterExpression extends DelegatedExpression {
   public TypeConverterExpression(SQLStatement stmt, SQLTable table, JavaTypeMapping mapping) {
      super(stmt, table, mapping);
      if (!(mapping instanceof TypeConverterMapping)) {
         throw new NucleusException("Attempt to create TypeConverterExpression for mapping of type " + mapping.getClass().getName());
      } else {
         TypeConverterMapping convMapping = (TypeConverterMapping)mapping;
         TypeConverter conv = convMapping.getTypeConverter();
         Class datastoreType = TypeConverterHelper.getDatastoreTypeForTypeConverter(conv, convMapping.getJavaType());
         if (datastoreType == String.class) {
            this.delegate = new StringExpression(stmt, table, mapping);
         } else if (Date.class.isAssignableFrom(datastoreType)) {
            this.delegate = new TemporalExpression(stmt, table, mapping);
         } else {
            if (!Number.class.isAssignableFrom(datastoreType)) {
               throw new NucleusException("Could not create TypeConverterExpression for mapping of type " + mapping.getClass().getName() + " with datastoreType=" + datastoreType.getName() + " - no available supported expression");
            }

            this.delegate = new NumericExpression(stmt, table, mapping);
         }

      }
   }
}
