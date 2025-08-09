package org.apache.orc.impl;

import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DateColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.Decimal64ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ListColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.MapColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.StructColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.TimestampColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.UnionColumnVector;
import org.apache.orc.TypeDescription;

public class TypeUtils {
   private TypeUtils() {
   }

   public static ColumnVector createColumn(TypeDescription schema, TypeDescription.RowBatchVersion version, int maxSize) {
      switch (schema.getCategory()) {
         case BOOLEAN:
         case BYTE:
         case SHORT:
         case INT:
         case LONG:
            return new LongColumnVector(maxSize);
         case DATE:
            return new DateColumnVector(maxSize);
         case TIMESTAMP:
         case TIMESTAMP_INSTANT:
            return new TimestampColumnVector(maxSize);
         case FLOAT:
         case DOUBLE:
            return new DoubleColumnVector(maxSize);
         case DECIMAL:
            int precision = schema.getPrecision();
            int scale = schema.getScale();
            if (version != TypeDescription.RowBatchVersion.ORIGINAL && precision <= 18) {
               return new Decimal64ColumnVector(maxSize, precision, scale);
            }

            return new DecimalColumnVector(maxSize, precision, scale);
         case STRING:
         case BINARY:
         case CHAR:
         case VARCHAR:
            return new BytesColumnVector(maxSize);
         case STRUCT:
            List<TypeDescription> children = schema.getChildren();
            ColumnVector[] fieldVector = new ColumnVector[children.size()];

            for(int i = 0; i < fieldVector.length; ++i) {
               fieldVector[i] = createColumn((TypeDescription)children.get(i), version, maxSize);
            }

            return new StructColumnVector(maxSize, fieldVector);
         case UNION:
            List<TypeDescription> children = schema.getChildren();
            ColumnVector[] fieldVector = new ColumnVector[children.size()];

            for(int i = 0; i < fieldVector.length; ++i) {
               fieldVector[i] = createColumn((TypeDescription)children.get(i), version, maxSize);
            }

            return new UnionColumnVector(maxSize, fieldVector);
         case LIST:
            List<TypeDescription> children = schema.getChildren();
            return new ListColumnVector(maxSize, createColumn((TypeDescription)children.get(0), version, maxSize));
         case MAP:
            List<TypeDescription> children = schema.getChildren();
            return new MapColumnVector(maxSize, createColumn((TypeDescription)children.get(0), version, maxSize), createColumn((TypeDescription)children.get(1), version, maxSize));
         default:
            throw new IllegalArgumentException("Unknown type " + String.valueOf(schema.getCategory()));
      }
   }
}
