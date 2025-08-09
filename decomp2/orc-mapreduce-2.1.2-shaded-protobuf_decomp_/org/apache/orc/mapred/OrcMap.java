package org.apache.orc.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.apache.hadoop.io.WritableComparable;
import org.apache.orc.TypeDescription;

public final class OrcMap extends TreeMap implements WritableComparable {
   private final TypeDescription keySchema;
   private final TypeDescription valueSchema;

   public OrcMap(TypeDescription schema) {
      this.keySchema = (TypeDescription)schema.getChildren().get(0);
      this.valueSchema = (TypeDescription)schema.getChildren().get(1);
   }

   public void write(DataOutput output) throws IOException {
      output.writeInt(this.size());

      for(Map.Entry entry : this.entrySet()) {
         K key = (K)((WritableComparable)entry.getKey());
         V value = (V)((WritableComparable)entry.getValue());
         output.writeByte((key == null ? 0 : 2) | (value == null ? 0 : 1));
         if (key != null) {
            key.write(output);
         }

         if (value != null) {
            value.write(output);
         }
      }

   }

   public void readFields(DataInput input) throws IOException {
      this.clear();
      int size = input.readInt();

      for(int i = 0; i < size; ++i) {
         byte flag = input.readByte();
         K key;
         if ((flag & 2) != 0) {
            key = (K)OrcStruct.createValue(this.keySchema);
            key.readFields(input);
         } else {
            key = (K)null;
         }

         V value;
         if ((flag & 1) != 0) {
            value = (V)OrcStruct.createValue(this.valueSchema);
            value.readFields(input);
         } else {
            value = (V)null;
         }

         this.put(key, value);
      }

   }

   public int compareTo(OrcMap other) {
      if (other == null) {
         return -1;
      } else {
         int result = this.keySchema.compareTo(other.keySchema);
         if (result != 0) {
            return result;
         } else {
            result = this.valueSchema.compareTo(other.valueSchema);
            if (result != 0) {
               return result;
            } else {
               Iterator<Map.Entry<K, V>> ourItr = this.entrySet().iterator();
               Iterator<Map.Entry<K, V>> theirItr = other.entrySet().iterator();

               while(ourItr.hasNext() && theirItr.hasNext()) {
                  Map.Entry<K, V> ourItem = (Map.Entry)ourItr.next();
                  Map.Entry<K, V> theirItem = (Map.Entry)theirItr.next();
                  K ourKey = (K)((WritableComparable)ourItem.getKey());
                  K theirKey = (K)((WritableComparable)theirItem.getKey());
                  int val = ourKey.compareTo(theirKey);
                  if (val != 0) {
                     return val;
                  }

                  Comparable<V> ourValue = (Comparable)ourItem.getValue();
                  V theirValue = (V)((WritableComparable)theirItem.getValue());
                  if (ourValue == null) {
                     if (theirValue != null) {
                        return 1;
                     }
                  } else {
                     if (theirValue == null) {
                        return -1;
                     }

                     val = ((WritableComparable)ourItem.getValue()).compareTo(theirItem.getValue());
                     if (val != 0) {
                        return val;
                     }
                  }
               }

               if (ourItr.hasNext()) {
                  return 1;
               } else {
                  return theirItr.hasNext() ? -1 : 0;
               }
            }
         }
      }
   }

   public boolean equals(Object other) {
      return other != null && other.getClass() == this.getClass() && this.compareTo((OrcMap)other) == 0;
   }

   public int hashCode() {
      return super.hashCode();
   }
}
