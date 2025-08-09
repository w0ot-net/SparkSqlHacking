package org.apache.hive.service.rpc.thrift;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Public;
import org.apache.hadoop.hive.common.classification.InterfaceStability.Stable;

@Public
@Stable
public class TCLIServiceConstants {
   public static final Set PRIMITIVE_TYPES = EnumSet.noneOf(TTypeId.class);
   public static final Set COMPLEX_TYPES;
   public static final Set COLLECTION_TYPES;
   public static final Map TYPE_NAMES;
   public static final String CHARACTER_MAXIMUM_LENGTH = "characterMaximumLength";
   public static final String PRECISION = "precision";
   public static final String SCALE = "scale";

   static {
      PRIMITIVE_TYPES.add(TTypeId.BOOLEAN_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.TINYINT_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.SMALLINT_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.INT_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.BIGINT_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.FLOAT_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.DOUBLE_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.STRING_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.TIMESTAMP_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.BINARY_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.DECIMAL_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.NULL_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.DATE_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.VARCHAR_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.CHAR_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.INTERVAL_YEAR_MONTH_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.INTERVAL_DAY_TIME_TYPE);
      PRIMITIVE_TYPES.add(TTypeId.TIMESTAMPLOCALTZ_TYPE);
      COMPLEX_TYPES = EnumSet.noneOf(TTypeId.class);
      COMPLEX_TYPES.add(TTypeId.ARRAY_TYPE);
      COMPLEX_TYPES.add(TTypeId.MAP_TYPE);
      COMPLEX_TYPES.add(TTypeId.STRUCT_TYPE);
      COMPLEX_TYPES.add(TTypeId.UNION_TYPE);
      COMPLEX_TYPES.add(TTypeId.USER_DEFINED_TYPE);
      COLLECTION_TYPES = EnumSet.noneOf(TTypeId.class);
      COLLECTION_TYPES.add(TTypeId.ARRAY_TYPE);
      COLLECTION_TYPES.add(TTypeId.MAP_TYPE);
      TYPE_NAMES = new EnumMap(TTypeId.class);
      TYPE_NAMES.put(TTypeId.ARRAY_TYPE, "ARRAY");
      TYPE_NAMES.put(TTypeId.BIGINT_TYPE, "BIGINT");
      TYPE_NAMES.put(TTypeId.BINARY_TYPE, "BINARY");
      TYPE_NAMES.put(TTypeId.BOOLEAN_TYPE, "BOOLEAN");
      TYPE_NAMES.put(TTypeId.CHAR_TYPE, "CHAR");
      TYPE_NAMES.put(TTypeId.DATE_TYPE, "DATE");
      TYPE_NAMES.put(TTypeId.DECIMAL_TYPE, "DECIMAL");
      TYPE_NAMES.put(TTypeId.DOUBLE_TYPE, "DOUBLE");
      TYPE_NAMES.put(TTypeId.FLOAT_TYPE, "FLOAT");
      TYPE_NAMES.put(TTypeId.INTERVAL_DAY_TIME_TYPE, "INTERVAL_DAY_TIME");
      TYPE_NAMES.put(TTypeId.INTERVAL_YEAR_MONTH_TYPE, "INTERVAL_YEAR_MONTH");
      TYPE_NAMES.put(TTypeId.INT_TYPE, "INT");
      TYPE_NAMES.put(TTypeId.MAP_TYPE, "MAP");
      TYPE_NAMES.put(TTypeId.NULL_TYPE, "NULL");
      TYPE_NAMES.put(TTypeId.SMALLINT_TYPE, "SMALLINT");
      TYPE_NAMES.put(TTypeId.STRING_TYPE, "STRING");
      TYPE_NAMES.put(TTypeId.STRUCT_TYPE, "STRUCT");
      TYPE_NAMES.put(TTypeId.TIMESTAMPLOCALTZ_TYPE, "TIMESTAMP WITH LOCAL TIME ZONE");
      TYPE_NAMES.put(TTypeId.TIMESTAMP_TYPE, "TIMESTAMP");
      TYPE_NAMES.put(TTypeId.TINYINT_TYPE, "TINYINT");
      TYPE_NAMES.put(TTypeId.UNION_TYPE, "UNIONTYPE");
      TYPE_NAMES.put(TTypeId.VARCHAR_TYPE, "VARCHAR");
   }
}
