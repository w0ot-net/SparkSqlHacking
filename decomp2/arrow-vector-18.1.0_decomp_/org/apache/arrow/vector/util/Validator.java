package org.apache.arrow.vector.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;

public class Validator {
   public static void compareSchemas(Schema schema1, Schema schema2) {
      if (!schema2.equals(schema1)) {
         String var10002 = String.valueOf(schema2);
         throw new IllegalArgumentException("Different schemas:\n" + var10002 + "\n" + String.valueOf(schema1));
      }
   }

   public static void compareDictionaries(List encodings1, List encodings2, DictionaryProvider provider1, DictionaryProvider provider2) {
      if (encodings1.size() != encodings2.size()) {
         int var11 = encodings1.size();
         throw new IllegalArgumentException("Different dictionary encoding count:\n" + var11 + "\n" + encodings2.size());
      } else {
         for(int i = 0; i < encodings1.size(); ++i) {
            if (!((DictionaryEncoding)encodings1.get(i)).equals(encodings2.get(i))) {
               String var10002 = String.valueOf(encodings1.get(i));
               throw new IllegalArgumentException("Different dictionary encodings:\n" + var10002 + "\n" + String.valueOf(encodings2.get(i)));
            }

            long id = ((DictionaryEncoding)encodings1.get(i)).getId();
            Dictionary dict1 = provider1.lookup(id);
            Dictionary dict2 = provider2.lookup(id);
            if (dict1 == null || dict2 == null) {
               throw new IllegalArgumentException("The DictionaryProvider did not contain the required dictionary with id: " + id + "\n" + String.valueOf(dict1) + "\n" + String.valueOf(dict2));
            }

            try {
               compareFieldVectors(dict1.getVector(), dict2.getVector());
            } catch (IllegalArgumentException e) {
               throw new IllegalArgumentException("Different dictionaries:\n" + String.valueOf(dict1) + "\n" + String.valueOf(dict2), e);
            }
         }

      }
   }

   public static void compareDictionaryProviders(DictionaryProvider provider1, DictionaryProvider provider2) {
      List<Long> ids1 = new ArrayList(provider1.getDictionaryIds());
      List<Long> ids2 = new ArrayList(provider2.getDictionaryIds());
      Collections.sort(ids1);
      Collections.sort(ids2);
      if (!ids1.equals(ids2)) {
         String var10002 = String.valueOf(ids1);
         throw new IllegalArgumentException("Different ids in dictionary providers:\n" + var10002 + "\n" + String.valueOf(ids2));
      } else {
         for(long id : ids1) {
            Dictionary dict1 = provider1.lookup(id);
            Dictionary dict2 = provider2.lookup(id);

            try {
               compareFieldVectors(dict1.getVector(), dict2.getVector());
            } catch (IllegalArgumentException e) {
               throw new IllegalArgumentException("Different dictionaries:\n" + String.valueOf(dict1) + "\n" + String.valueOf(dict2), e);
            }
         }

      }
   }

   public static void compareVectorSchemaRoot(VectorSchemaRoot root1, VectorSchemaRoot root2) {
      compareSchemas(root2.getSchema(), root1.getSchema());
      if (root1.getRowCount() != root2.getRowCount()) {
         int var5 = root1.getRowCount();
         throw new IllegalArgumentException("Different row count:\n" + var5 + " != " + root2.getRowCount());
      } else {
         List<FieldVector> vectors1 = root1.getFieldVectors();
         List<FieldVector> vectors2 = root2.getFieldVectors();
         if (vectors1.size() != vectors2.size()) {
            String var10002 = vectors1.toString();
            throw new IllegalArgumentException("Different column count:\n" + var10002 + "\n!=\n" + vectors2.toString());
         } else {
            for(int i = 0; i < vectors1.size(); ++i) {
               compareFieldVectors((FieldVector)vectors1.get(i), (FieldVector)vectors2.get(i));
            }

         }
      }
   }

   public static void compareFieldVectors(FieldVector vector1, FieldVector vector2) {
      Field field1 = vector1.getField();
      if (!field1.equals(vector2.getField())) {
         String var7 = String.valueOf(field1);
         throw new IllegalArgumentException("Different Fields:\n" + var7 + "\n!=\n" + String.valueOf(vector2.getField()));
      } else {
         int valueCount = vector1.getValueCount();
         if (valueCount != vector2.getValueCount()) {
            throw new IllegalArgumentException("Different value count for field " + String.valueOf(field1) + " : " + valueCount + " != " + vector2.getValueCount());
         } else {
            for(int j = 0; j < valueCount; ++j) {
               Object obj1 = vector1.getObject(j);
               Object obj2 = vector2.getObject(j);
               if (!equals(field1.getType(), obj1, obj2)) {
                  String var10002 = String.valueOf(field1);
                  throw new IllegalArgumentException("Different values in column:\n" + var10002 + " at index " + j + ": " + String.valueOf(obj1) + " != " + String.valueOf(obj2));
               }
            }

         }
      }
   }

   static boolean equals(ArrowType type, Object o1, Object o2) {
      if (type instanceof ArrowType.FloatingPoint) {
         ArrowType.FloatingPoint fpType = (ArrowType.FloatingPoint)type;
         switch (fpType.getPrecision()) {
            case DOUBLE:
               return equalEnough((Double)o1, (Double)o2);
            case SINGLE:
               return equalEnough((Float)o1, (Float)o2);
            case HALF:
            default:
               throw new UnsupportedOperationException("unsupported precision: " + String.valueOf(fpType));
         }
      } else if (!(type instanceof ArrowType.Binary) && !(type instanceof ArrowType.LargeBinary) && !(type instanceof ArrowType.FixedSizeBinary)) {
         return o1 instanceof byte[] && o2 instanceof byte[] ? Arrays.equals((byte[])o1, (byte[])o2) : Objects.equals(o1, o2);
      } else {
         return Arrays.equals((byte[])o1, (byte[])o2);
      }
   }

   static boolean equalEnough(Float f1, Float f2) {
      if (f1 != null && f2 != null) {
         if (f1.isNaN()) {
            return f2.isNaN();
         } else if (!f1.isInfinite()) {
            float average = Math.abs((f1 + f2) / 2.0F);
            float differenceScaled = Math.abs(f1 - f2) / (average == 0.0F ? 1.0F : average);
            return differenceScaled < 1.0E-6F;
         } else {
            return f2.isInfinite() && Math.signum(f1) == Math.signum(f2);
         }
      } else {
         return f1 == null && f2 == null;
      }
   }

   static boolean equalEnough(Double f1, Double f2) {
      if (f1 != null && f2 != null) {
         if (f1.isNaN()) {
            return f2.isNaN();
         } else if (!f1.isInfinite()) {
            double average = Math.abs((f1 + f2) / (double)2.0F);
            double differenceScaled = Math.abs(f1 - f2) / (average == (double)0.0F ? (double)1.0F : average);
            return differenceScaled < 1.0E-12;
         } else {
            return f2.isInfinite() && Math.signum(f1) == Math.signum(f2);
         }
      } else {
         return f1 == null && f2 == null;
      }
   }
}
