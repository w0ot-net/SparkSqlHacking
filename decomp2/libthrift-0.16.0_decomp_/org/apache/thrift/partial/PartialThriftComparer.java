package org.apache.thrift.partial;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TBase;

public class PartialThriftComparer {
   private ThriftMetadata.ThriftStruct metadata;

   public PartialThriftComparer(ThriftMetadata.ThriftStruct metadata) {
      this.metadata = metadata;
   }

   public boolean areEqual(TBase t1, TBase t2, StringBuilder sb) {
      return this.areEqual((ThriftMetadata.ThriftStruct)this.metadata, t1, t2, sb);
   }

   private boolean areEqual(ThriftMetadata.ThriftObject data, Object o1, Object o2, StringBuilder sb) {
      byte fieldType = data.data.valueMetaData.type;
      switch (fieldType) {
         case 2:
         case 3:
         case 4:
         case 6:
         case 8:
         case 10:
         case 11:
            return this.areEqual((ThriftMetadata.ThriftPrimitive)data, o1, o2, sb);
         case 5:
         case 7:
         case 9:
         default:
            throw unsupportedFieldTypeException(fieldType);
         case 12:
            return this.areEqual((ThriftMetadata.ThriftStruct)data, o1, o2, sb);
         case 13:
            return this.areEqual((ThriftMetadata.ThriftMap)data, o1, o2, sb);
         case 14:
            return this.areEqual((ThriftMetadata.ThriftSet)data, o1, o2, sb);
         case 15:
            return this.areEqual((ThriftMetadata.ThriftList)data, o1, o2, sb);
         case 16:
            return this.areEqual((ThriftMetadata.ThriftEnum)data, o1, o2, sb);
      }
   }

   private boolean areEqual(ThriftMetadata.ThriftStruct data, Object o1, Object o2, StringBuilder sb) {
      ComparisonResult result = this.checkNullEquality(data, o1, o2, sb);
      if (result != PartialThriftComparer.ComparisonResult.UNKNOWN) {
         return result == PartialThriftComparer.ComparisonResult.EQUAL;
      } else {
         TBase t1 = (TBase)o1;
         TBase t2 = (TBase)o2;
         if (data.fields.size() == 0) {
            if (t1.equals(t2)) {
               return true;
            } else {
               this.appendNotEqual(data, sb, t1, t2, "struct1", "struct2");
               return false;
            }
         } else {
            boolean overallResult = true;

            for(Object o : data.fields.values()) {
               ThriftMetadata.ThriftObject field = (ThriftMetadata.ThriftObject)o;
               Object f1 = t1.getFieldValue(field.fieldId);
               Object f2 = t2.getFieldValue(field.fieldId);
               overallResult = overallResult && this.areEqual(field, f1, f2, sb);
            }

            return overallResult;
         }
      }
   }

   private boolean areEqual(ThriftMetadata.ThriftPrimitive data, Object o1, Object o2, StringBuilder sb) {
      ComparisonResult result = this.checkNullEquality(data, o1, o2, sb);
      if (result != PartialThriftComparer.ComparisonResult.UNKNOWN) {
         return result == PartialThriftComparer.ComparisonResult.EQUAL;
      } else {
         if (data.isBinary()) {
            if (this.areBinaryFieldsEqual(o1, o2)) {
               return true;
            }
         } else if (o1.equals(o2)) {
            return true;
         }

         this.appendNotEqual(data, sb, o1, o2, "o1", "o2");
         return false;
      }
   }

   private boolean areEqual(ThriftMetadata.ThriftEnum data, Object o1, Object o2, StringBuilder sb) {
      ComparisonResult result = this.checkNullEquality(data, o1, o2, sb);
      if (result != PartialThriftComparer.ComparisonResult.UNKNOWN) {
         return result == PartialThriftComparer.ComparisonResult.EQUAL;
      } else if (o1.equals(o2)) {
         return true;
      } else {
         this.appendNotEqual(data, sb, o1, o2, "o1", "o2");
         return false;
      }
   }

   private boolean areEqual(ThriftMetadata.ThriftList data, Object o1, Object o2, StringBuilder sb) {
      List<Object> l1 = (List)o1;
      List<Object> l2 = (List)o2;
      ComparisonResult result = this.checkNullEquality(data, o1, o2, sb);
      if (result != PartialThriftComparer.ComparisonResult.UNKNOWN) {
         return result == PartialThriftComparer.ComparisonResult.EQUAL;
      } else if (!this.checkSizeEquality(data, l1, l2, sb, "list")) {
         return false;
      } else {
         for(int i = 0; i < l1.size(); ++i) {
            Object e1 = l1.get(i);
            Object e2 = l2.get(i);
            if (!this.areEqual(data.elementData, e1, e2, sb)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean areEqual(ThriftMetadata.ThriftSet data, Object o1, Object o2, StringBuilder sb) {
      Set<Object> s1 = (Set)o1;
      Set<Object> s2 = (Set)o2;
      ComparisonResult result = this.checkNullEquality(data, o1, o2, sb);
      if (result != PartialThriftComparer.ComparisonResult.UNKNOWN) {
         return result == PartialThriftComparer.ComparisonResult.EQUAL;
      } else if (!this.checkSizeEquality(data, s1, s2, sb, "set")) {
         return false;
      } else {
         for(Object e1 : s1) {
            if (!s2.contains(e1)) {
               this.appendResult(data, sb, "Element %s in s1 not found in s2", e1);
               return false;
            }
         }

         return true;
      }
   }

   private boolean areEqual(ThriftMetadata.ThriftMap data, Object o1, Object o2, StringBuilder sb) {
      Map<Object, Object> m1 = (Map)o1;
      Map<Object, Object> m2 = (Map)o2;
      ComparisonResult result = this.checkNullEquality(data, o1, o2, sb);
      if (result != PartialThriftComparer.ComparisonResult.UNKNOWN) {
         return result == PartialThriftComparer.ComparisonResult.EQUAL;
      } else if (!this.checkSizeEquality(data, m1.keySet(), m2.keySet(), sb, "map.keySet")) {
         return false;
      } else {
         for(Map.Entry e1 : m1.entrySet()) {
            Object k1 = e1.getKey();
            if (!m2.containsKey(k1)) {
               this.appendResult(data, sb, "Key %s in m1 not found in m2", k1);
               return false;
            }

            Object v1 = e1.getValue();
            Object v2 = m2.get(k1);
            if (!this.areEqual(data.valueData, v1, v2, sb)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean areBinaryFieldsEqual(Object o1, Object o2) {
      if (o1 instanceof byte[]) {
         if (Arrays.equals((byte[])o1, (byte[])o2)) {
            return true;
         }
      } else {
         if (!(o1 instanceof ByteBuffer)) {
            throw new UnsupportedOperationException(String.format("Unsupported binary field type: %s", o1.getClass().getName()));
         }

         if (((ByteBuffer)o1).compareTo((ByteBuffer)o2) == 0) {
            return true;
         }
      }

      return false;
   }

   private void appendResult(ThriftMetadata.ThriftObject data, StringBuilder sb, String format, Object... args) {
      if (sb != null) {
         String msg = String.format(format, args);
         sb.append(data.fieldId.getFieldName());
         sb.append(" : ");
         sb.append(msg);
      }

   }

   private void appendNotEqual(ThriftMetadata.ThriftObject data, StringBuilder sb, Object o1, Object o2, String o1name, String o2name) {
      String o1s = o1.toString();
      String o2s = o2.toString();
      if (o1s.length() + o2s.length() < 100) {
         this.appendResult(data, sb, "%s (%s) != %s (%s)", o1name, o1s, o2name, o2s);
      } else {
         this.appendResult(data, sb, "%s != %s\n%s =\n%s\n%s =\n%s\n", o1name, o2name, o1name, o1s, o2name, o2s);
      }

   }

   private ComparisonResult checkNullEquality(ThriftMetadata.ThriftObject data, Object o1, Object o2, StringBuilder sb) {
      if (o1 == null && o2 == null) {
         return PartialThriftComparer.ComparisonResult.EQUAL;
      } else {
         if (o1 == null) {
            this.appendResult(data, sb, "o1 (null) != o2");
         }

         if (o2 == null) {
            this.appendResult(data, sb, "o1 != o2 (null)");
         }

         return PartialThriftComparer.ComparisonResult.UNKNOWN;
      }
   }

   private boolean checkSizeEquality(ThriftMetadata.ThriftObject data, Collection c1, Collection c2, StringBuilder sb, String typeName) {
      if (c1.size() != c2.size()) {
         this.appendResult(data, sb, "%s1.size(%d) != %s2.size(%d)", typeName, c1.size(), typeName, c2.size());
         return false;
      } else {
         return true;
      }
   }

   static UnsupportedOperationException unsupportedFieldTypeException(byte fieldType) {
      return new UnsupportedOperationException("field type not supported: '" + fieldType + "'");
   }

   private static enum ComparisonResult {
      UNKNOWN,
      EQUAL,
      NOT_EQUAL;
   }
}
