package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class NuoDBTypeInfo extends SQLTypeInfo {
   public NuoDBTypeInfo(String typeName, short dataType, int precision, String literalPrefix, String literalSuffix, String createParams, int nullable, boolean caseSensitive, short searchable, boolean unsignedAttribute, boolean fixedPrecScale, boolean autoIncrement, String localTypeName, short minimumScale, short maximumScale, int numPrecRadix) {
      super(typeName, dataType, precision, literalPrefix, literalSuffix, createParams, nullable, caseSensitive, searchable, unsignedAttribute, fixedPrecScale, autoIncrement, localTypeName, minimumScale, maximumScale, numPrecRadix);
   }

   public NuoDBTypeInfo(ResultSet rs) {
      super(rs);
   }
}
