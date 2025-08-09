package org.apache.hadoop.hive.serde2.typeinfo;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;

public class HiveDecimalUtils {
   public static HiveDecimal enforcePrecisionScale(HiveDecimal dec, DecimalTypeInfo typeInfo) {
      return HiveDecimal.enforcePrecisionScale(dec, typeInfo.precision(), typeInfo.scale());
   }

   public static HiveDecimalWritable enforcePrecisionScale(HiveDecimalWritable writable, DecimalTypeInfo typeInfo) {
      if (writable == null) {
         return null;
      } else {
         HiveDecimalWritable result = new HiveDecimalWritable(writable);
         result.mutateEnforcePrecisionScale(typeInfo.precision(), typeInfo.scale());
         return result.isSet() ? result : null;
      }
   }

   public static void validateParameter(int precision, int scale) {
      if (precision >= 1 && precision <= 38) {
         if (scale >= 0 && scale <= 38) {
            if (precision < scale) {
               throw new IllegalArgumentException("Decimal scale must be less than or equal to precision");
            }
         } else {
            throw new IllegalArgumentException("Decimal scale out of allowed range [0,38]");
         }
      } else {
         throw new IllegalArgumentException("Decimal precision out of allowed range [1,38]");
      }
   }

   public static int getPrecisionForType(PrimitiveTypeInfo typeInfo) {
      switch (typeInfo.getPrimitiveCategory()) {
         case DECIMAL:
            return ((DecimalTypeInfo)typeInfo).precision();
         case FLOAT:
            return 7;
         case DOUBLE:
            return 15;
         case BYTE:
            return 3;
         case SHORT:
            return 5;
         case INT:
            return 10;
         case LONG:
            return 19;
         case VOID:
            return 1;
         default:
            return 38;
      }
   }

   public static int getScaleForType(PrimitiveTypeInfo typeInfo) {
      switch (typeInfo.getPrimitiveCategory()) {
         case DECIMAL:
            return ((DecimalTypeInfo)typeInfo).scale();
         case FLOAT:
            return 7;
         case DOUBLE:
            return 15;
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
         case VOID:
            return 0;
         default:
            return 18;
      }
   }

   public static TypeInfo getDecimalTypeForPrimitiveCategories(PrimitiveTypeInfo a, PrimitiveTypeInfo b) {
      int prec1 = getPrecisionForType(a);
      int prec2 = getPrecisionForType(b);
      int scale1 = getScaleForType(a);
      int scale2 = getScaleForType(b);
      int intPart = Math.max(prec1 - scale1, prec2 - scale2);
      int decPart = Math.max(scale1, scale2);
      int prec = Math.min(intPart + decPart, 38);
      int scale = Math.min(decPart, 38 - intPart);
      return TypeInfoFactory.getDecimalTypeInfo(prec, scale);
   }

   public static DecimalTypeInfo getDecimalTypeForPrimitiveCategory(PrimitiveTypeInfo a) {
      if (a instanceof DecimalTypeInfo) {
         return (DecimalTypeInfo)a;
      } else {
         int prec = getPrecisionForType(a);
         int scale = getScaleForType(a);
         prec = Math.min(prec, 38);
         scale = Math.min(scale, 38 - (prec - scale));
         return TypeInfoFactory.getDecimalTypeInfo(prec, scale);
      }
   }
}
