package org.apache.spark.sql.types;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.spark.annotation.Stable;

@Stable
public class DataTypes {
   public static final DataType StringType;
   public static final DataType BinaryType;
   public static final DataType BooleanType;
   public static final DataType DateType;
   public static final DataType TimestampType;
   public static final DataType TimestampNTZType;
   public static final DataType CalendarIntervalType;
   public static final DataType DoubleType;
   public static final DataType FloatType;
   public static final DataType ByteType;
   public static final DataType IntegerType;
   public static final DataType LongType;
   public static final DataType ShortType;
   public static final DataType NullType;
   public static final DataType VariantType;

   public static ArrayType createArrayType(DataType elementType) {
      if (elementType == null) {
         throw new IllegalArgumentException("elementType should not be null.");
      } else {
         return new ArrayType(elementType, true);
      }
   }

   public static ArrayType createArrayType(DataType elementType, boolean containsNull) {
      if (elementType == null) {
         throw new IllegalArgumentException("elementType should not be null.");
      } else {
         return new ArrayType(elementType, containsNull);
      }
   }

   public static DecimalType createDecimalType(int precision, int scale) {
      return DecimalType$.MODULE$.apply(precision, scale);
   }

   public static DecimalType createDecimalType() {
      return DecimalType$.MODULE$.USER_DEFAULT();
   }

   public static DayTimeIntervalType createDayTimeIntervalType(byte startField, byte endField) {
      return DayTimeIntervalType$.MODULE$.apply(startField, endField);
   }

   public static DayTimeIntervalType createDayTimeIntervalType() {
      return DayTimeIntervalType$.MODULE$.DEFAULT();
   }

   public static YearMonthIntervalType createYearMonthIntervalType(byte startField, byte endField) {
      return YearMonthIntervalType$.MODULE$.apply(startField, endField);
   }

   public static YearMonthIntervalType createYearMonthIntervalType() {
      return YearMonthIntervalType$.MODULE$.DEFAULT();
   }

   public static MapType createMapType(DataType keyType, DataType valueType) {
      if (keyType == null) {
         throw new IllegalArgumentException("keyType should not be null.");
      } else if (valueType == null) {
         throw new IllegalArgumentException("valueType should not be null.");
      } else {
         return new MapType(keyType, valueType, true);
      }
   }

   public static MapType createMapType(DataType keyType, DataType valueType, boolean valueContainsNull) {
      if (keyType == null) {
         throw new IllegalArgumentException("keyType should not be null.");
      } else if (valueType == null) {
         throw new IllegalArgumentException("valueType should not be null.");
      } else {
         return new MapType(keyType, valueType, valueContainsNull);
      }
   }

   public static StructField createStructField(String name, DataType dataType, boolean nullable, Metadata metadata) {
      if (name == null) {
         throw new IllegalArgumentException("name should not be null.");
      } else if (dataType == null) {
         throw new IllegalArgumentException("dataType should not be null.");
      } else if (metadata == null) {
         throw new IllegalArgumentException("metadata should not be null.");
      } else {
         return new StructField(name, dataType, nullable, metadata);
      }
   }

   public static StructField createStructField(String name, DataType dataType, boolean nullable) {
      return createStructField(name, dataType, nullable, (new MetadataBuilder()).build());
   }

   public static StructType createStructType(List fields) {
      return createStructType((StructField[])fields.toArray(new StructField[fields.size()]));
   }

   public static StructType createStructType(StructField[] fields) {
      if (fields == null) {
         throw new IllegalArgumentException("fields should not be null.");
      } else {
         Set<String> distinctNames = new HashSet();

         for(StructField field : fields) {
            if (field == null) {
               throw new IllegalArgumentException("fields should not contain any null.");
            }

            distinctNames.add(field.name());
         }

         if (distinctNames.size() != fields.length) {
            throw new IllegalArgumentException("fields should have distinct names.");
         } else {
            return StructType$.MODULE$.apply(fields);
         }
      }
   }

   public static CharType createCharType(int length) {
      return new CharType(length);
   }

   public static VarcharType createVarcharType(int length) {
      return new VarcharType(length);
   }

   static {
      StringType = StringType$.MODULE$;
      BinaryType = BinaryType$.MODULE$;
      BooleanType = BooleanType$.MODULE$;
      DateType = DateType$.MODULE$;
      TimestampType = TimestampType$.MODULE$;
      TimestampNTZType = TimestampNTZType$.MODULE$;
      CalendarIntervalType = CalendarIntervalType$.MODULE$;
      DoubleType = DoubleType$.MODULE$;
      FloatType = FloatType$.MODULE$;
      ByteType = ByteType$.MODULE$;
      IntegerType = IntegerType$.MODULE$;
      LongType = LongType$.MODULE$;
      ShortType = ShortType$.MODULE$;
      NullType = NullType$.MODULE$;
      VariantType = VariantType$.MODULE$;
   }
}
