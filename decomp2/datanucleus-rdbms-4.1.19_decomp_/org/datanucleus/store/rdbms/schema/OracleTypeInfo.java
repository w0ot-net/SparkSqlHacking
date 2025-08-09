package org.datanucleus.store.rdbms.schema;

import java.sql.ResultSet;

public class OracleTypeInfo extends SQLTypeInfo {
   public static final int TYPES_SYS_XMLTYPE = 2007;
   public static final String TYPES_NAME_SYS_XMLTYPE = "SYS.XMLTYPE";

   public OracleTypeInfo(ResultSet rs) {
      super(rs);
   }

   public OracleTypeInfo(String typeName, short dataType, int precision, String literalPrefix, String literalSuffix, String createParams, int nullable, boolean caseSensitive, short searchable, boolean unsignedAttribute, boolean fixedPrecScale, boolean autoIncrement, String localTypeName, short minimumScale, short maximumScale, int numPrecRadix) {
      super(typeName, dataType, precision, literalPrefix, literalSuffix, createParams, nullable, caseSensitive, searchable, unsignedAttribute, fixedPrecScale, autoIncrement, localTypeName, minimumScale, maximumScale, numPrecRadix);
   }
}
