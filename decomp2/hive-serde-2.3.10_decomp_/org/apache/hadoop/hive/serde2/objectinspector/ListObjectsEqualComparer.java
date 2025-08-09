package org.apache.hadoop.hive.serde2.objectinspector;

import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.ByteObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.IntObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.LongObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoUtils;

public class ListObjectsEqualComparer {
   FieldComparer[] fieldComparers;
   int numFields;

   public ListObjectsEqualComparer(ObjectInspector[] oi0, ObjectInspector[] oi1) {
      if (oi0.length != oi1.length) {
         throw new RuntimeException("Sizes of two lists of object inspectors don't match.");
      } else {
         this.numFields = oi0.length;
         this.fieldComparers = new FieldComparer[this.numFields];

         for(int i = 0; i < oi0.length; ++i) {
            this.fieldComparers[i] = new FieldComparer(oi0[i], oi1[i]);
         }

      }
   }

   public boolean areEqual(Object[] ol0, Object[] ol1) {
      if (ol0.length == this.numFields && ol1.length == this.numFields) {
         for(int i = this.numFields - 1; i >= 0; --i) {
            if (!this.fieldComparers[i].areEqual(ol0[i], ol1[i])) {
               return false;
            }
         }

         return true;
      } else if (ol0.length != ol1.length) {
         return false;
      } else {
         assert ol0.length <= this.numFields;

         assert ol1.length <= this.numFields;

         for(int i = 0; i < Math.min(ol0.length, ol1.length); ++i) {
            if (!this.fieldComparers[i].areEqual(ol0[i], ol1[i])) {
               return false;
            }
         }

         return true;
      }
   }

   static enum CompareType {
      COMPARE_STRING,
      COMPARE_TEXT,
      COMPARE_INT,
      COMPARE_LONG,
      COMPARE_BYTE,
      COMPARE_BOOL,
      OTHER;
   }

   class FieldComparer {
      protected ObjectInspector oi0;
      protected ObjectInspector oi1;
      protected ObjectInspector compareOI;
      protected CompareType compareType;
      protected ObjectInspectorConverters.Converter converter0;
      protected ObjectInspectorConverters.Converter converter1;
      protected StringObjectInspector soi0;
      protected StringObjectInspector soi1;
      protected IntObjectInspector ioi0;
      protected IntObjectInspector ioi1;
      protected LongObjectInspector loi0;
      protected LongObjectInspector loi1;
      protected ByteObjectInspector byoi0;
      protected ByteObjectInspector byoi1;
      protected BooleanObjectInspector boi0;
      protected BooleanObjectInspector boi1;

      public FieldComparer(ObjectInspector oi0, ObjectInspector oi1) {
         this.oi0 = oi0;
         this.oi1 = oi1;
         TypeInfo type0 = TypeInfoUtils.getTypeInfoFromObjectInspector(oi0);
         TypeInfo type1 = TypeInfoUtils.getTypeInfoFromObjectInspector(oi1);
         if (type0.equals(TypeInfoFactory.stringTypeInfo) && type1.equals(TypeInfoFactory.stringTypeInfo)) {
            this.soi0 = (StringObjectInspector)oi0;
            this.soi1 = (StringObjectInspector)oi1;
            if (!this.soi0.preferWritable() && !this.soi1.preferWritable()) {
               this.compareType = ListObjectsEqualComparer.CompareType.COMPARE_STRING;
            } else {
               this.compareType = ListObjectsEqualComparer.CompareType.COMPARE_TEXT;
            }
         } else if (type0.equals(TypeInfoFactory.intTypeInfo) && type1.equals(TypeInfoFactory.intTypeInfo)) {
            this.compareType = ListObjectsEqualComparer.CompareType.COMPARE_INT;
            this.ioi0 = (IntObjectInspector)oi0;
            this.ioi1 = (IntObjectInspector)oi1;
         } else if (type0.equals(TypeInfoFactory.longTypeInfo) && type1.equals(TypeInfoFactory.longTypeInfo)) {
            this.compareType = ListObjectsEqualComparer.CompareType.COMPARE_LONG;
            this.loi0 = (LongObjectInspector)oi0;
            this.loi1 = (LongObjectInspector)oi1;
         } else if (type0.equals(TypeInfoFactory.byteTypeInfo) && type1.equals(TypeInfoFactory.byteTypeInfo)) {
            this.compareType = ListObjectsEqualComparer.CompareType.COMPARE_BYTE;
            this.byoi0 = (ByteObjectInspector)oi0;
            this.byoi1 = (ByteObjectInspector)oi1;
         } else if (type0.equals(TypeInfoFactory.booleanTypeInfo) && type1.equals(TypeInfoFactory.booleanTypeInfo)) {
            this.compareType = ListObjectsEqualComparer.CompareType.COMPARE_BOOL;
            this.boi0 = (BooleanObjectInspector)oi0;
            this.boi1 = (BooleanObjectInspector)oi1;
         } else {
            this.compareType = ListObjectsEqualComparer.CompareType.OTHER;
         }

      }

      public boolean areEqual(Object o0, Object o1) {
         if (o0 == null && o1 == null) {
            return true;
         } else if (o0 != null && o1 != null) {
            switch (this.compareType) {
               case COMPARE_TEXT:
                  return this.soi0.getPrimitiveWritableObject(o0).equals(this.soi1.getPrimitiveWritableObject(o1));
               case COMPARE_INT:
                  return this.ioi0.get(o0) == this.ioi1.get(o1);
               case COMPARE_LONG:
                  return this.loi0.get(o0) == this.loi1.get(o1);
               case COMPARE_BYTE:
                  return this.byoi0.get(o0) == this.byoi1.get(o1);
               case COMPARE_BOOL:
                  return this.boi0.get(o0) == this.boi1.get(o1);
               case COMPARE_STRING:
                  return this.soi0.getPrimitiveJavaObject(o0).equals(this.soi1.getPrimitiveJavaObject(o1));
               default:
                  return ObjectInspectorUtils.compare(o0, this.oi0, o1, this.oi1) == 0;
            }
         } else {
            return false;
         }
      }
   }
}
