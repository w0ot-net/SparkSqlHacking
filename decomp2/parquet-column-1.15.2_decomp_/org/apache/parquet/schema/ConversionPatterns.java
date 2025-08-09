package org.apache.parquet.schema;

import org.apache.parquet.Preconditions;

public abstract class ConversionPatterns {
   static final String MAP_REPEATED_NAME = "key_value";
   private static final String ELEMENT_NAME = "element";

   private static GroupType listWrapper(Type.Repetition repetition, String alias, LogicalTypeAnnotation logicalTypeAnnotation, Type nested) {
      if (!nested.isRepetition(Type.Repetition.REPEATED)) {
         throw new IllegalArgumentException("Nested type should be repeated: " + nested);
      } else {
         return new GroupType(repetition, alias, logicalTypeAnnotation, new Type[]{nested});
      }
   }

   public static GroupType mapType(Type.Repetition repetition, String alias, Type keyType, Type valueType) {
      return mapType(repetition, alias, "key_value", keyType, valueType);
   }

   public static GroupType stringKeyMapType(Type.Repetition repetition, String alias, String mapAlias, Type valueType) {
      return mapType(repetition, alias, mapAlias, new PrimitiveType(Type.Repetition.REQUIRED, PrimitiveType.PrimitiveTypeName.BINARY, "key", LogicalTypeAnnotation.stringType()), valueType);
   }

   public static GroupType stringKeyMapType(Type.Repetition repetition, String alias, Type valueType) {
      return stringKeyMapType(repetition, alias, "key_value", valueType);
   }

   public static GroupType mapType(Type.Repetition repetition, String alias, String mapAlias, Type keyType, Type valueType) {
      if (valueType == null) {
         return listWrapper(repetition, alias, LogicalTypeAnnotation.mapType(), new GroupType(Type.Repetition.REPEATED, mapAlias, LogicalTypeAnnotation.MapKeyValueTypeAnnotation.getInstance(), new Type[]{keyType}));
      } else if (!valueType.getName().equals("value")) {
         throw new RuntimeException(valueType.getName() + " should be value");
      } else {
         return listWrapper(repetition, alias, LogicalTypeAnnotation.mapType(), new GroupType(Type.Repetition.REPEATED, mapAlias, LogicalTypeAnnotation.MapKeyValueTypeAnnotation.getInstance(), new Type[]{keyType, valueType}));
      }
   }

   /** @deprecated */
   @Deprecated
   public static GroupType listType(Type.Repetition repetition, String alias, Type nestedType) {
      return listWrapper(repetition, alias, LogicalTypeAnnotation.listType(), nestedType);
   }

   public static GroupType listOfElements(Type.Repetition listRepetition, String name, Type elementType) {
      Preconditions.checkArgument(elementType.getName().equals("element"), "List element type must be named 'element'");
      return listWrapper(listRepetition, name, LogicalTypeAnnotation.listType(), new GroupType(Type.Repetition.REPEATED, "list", new Type[]{elementType}));
   }
}
