package org.apache.parquet.filter2.predicate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.schema.PrimitiveType;

public class ValidTypeMap {
   private static final Map classToParquetType = new HashMap();
   private static final Map parquetTypeToClass = new HashMap();

   private ValidTypeMap() {
   }

   private static void add(Class c, PrimitiveType.PrimitiveTypeName p) {
      Set<PrimitiveType.PrimitiveTypeName> descriptors = (Set)classToParquetType.get(c);
      if (descriptors == null) {
         descriptors = new HashSet();
         classToParquetType.put(c, descriptors);
      }

      descriptors.add(p);
      Set<Class<?>> classes = (Set)parquetTypeToClass.get(p);
      if (classes == null) {
         classes = new HashSet();
         parquetTypeToClass.put(p, classes);
      }

      classes.add(c);
   }

   public static void assertTypeValid(Operators.Column foundColumn, PrimitiveType.PrimitiveTypeName primitiveType) {
      Class<T> foundColumnType = foundColumn.getColumnType();
      ColumnPath columnPath = foundColumn.getColumnPath();
      Set<PrimitiveType.PrimitiveTypeName> validTypeDescriptors = (Set)classToParquetType.get(foundColumnType);
      if (validTypeDescriptors == null) {
         StringBuilder message = new StringBuilder();
         message.append("Column ").append(columnPath.toDotString()).append(" was declared as type: ").append(foundColumnType.getName()).append(" which is not supported in FilterPredicates.");
         Set<Class<?>> supportedTypes = (Set)parquetTypeToClass.get(primitiveType);
         if (supportedTypes != null) {
            message.append(" Supported types for this column are: ").append(supportedTypes);
         } else {
            message.append(" There are no supported types for columns of " + primitiveType);
         }

         throw new IllegalArgumentException(message.toString());
      } else if (!validTypeDescriptors.contains(primitiveType)) {
         StringBuilder message = new StringBuilder();
         message.append("FilterPredicate column: ").append(columnPath.toDotString()).append("'s declared type (").append(foundColumnType.getName()).append(") does not match the schema found in file metadata. Column ").append(columnPath.toDotString()).append(" is of type: ").append(primitiveType).append("\nValid types for this column are: ").append(parquetTypeToClass.get(primitiveType));
         throw new IllegalArgumentException(message.toString());
      }
   }

   static {
      for(PrimitiveType.PrimitiveTypeName t : PrimitiveType.PrimitiveTypeName.values()) {
         Class<?> c = t.javaType;
         if (c.isPrimitive()) {
            c = PrimitiveToBoxedClass.get(c);
         }

         add(c, t);
      }

   }
}
