package org.apache.orc.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.hadoop.io.WritableComparable;
import org.apache.orc.TypeDescription;

public class OrcList extends ArrayList implements WritableComparable {
   private final TypeDescription childSchema;

   public OrcList(TypeDescription schema) {
      this.childSchema = (TypeDescription)schema.getChildren().get(0);
   }

   public OrcList(TypeDescription schema, int initialCapacity) {
      super(initialCapacity);
      this.childSchema = (TypeDescription)schema.getChildren().get(0);
   }

   public void write(DataOutput output) throws IOException {
      Iterator<E> itr = this.iterator();
      output.writeInt(this.size());

      while(itr.hasNext()) {
         E obj = (E)((WritableComparable)itr.next());
         output.writeBoolean(obj != null);
         if (obj != null) {
            obj.write(output);
         }
      }

   }

   public void readFields(DataInput input) throws IOException {
      this.clear();
      int size = input.readInt();
      this.ensureCapacity(size);

      for(int i = 0; i < size; ++i) {
         if (input.readBoolean()) {
            E obj = (E)OrcStruct.createValue(this.childSchema);
            obj.readFields(input);
            this.add(obj);
         } else {
            this.add((Object)null);
         }
      }

   }

   public int compareTo(OrcList other) {
      if (other == null) {
         return -1;
      } else {
         int result = this.childSchema.compareTo(other.childSchema);
         if (result != 0) {
            return result;
         } else {
            int ourSize = this.size();
            int otherSize = other.size();

            for(int e = 0; e < ourSize && e < otherSize; ++e) {
               E ours = (E)((WritableComparable)this.get(e));
               E theirs = (E)((WritableComparable)other.get(e));
               if (ours == null) {
                  if (theirs != null) {
                     return 1;
                  }
               } else {
                  if (theirs == null) {
                     return -1;
                  }

                  int val = ours.compareTo(theirs);
                  if (val != 0) {
                     return val;
                  }
               }
            }

            return ourSize - otherSize;
         }
      }
   }

   public boolean equals(Object other) {
      return other != null && other.getClass() == this.getClass() && this.compareTo((OrcList)other) == 0;
   }

   public int hashCode() {
      return super.hashCode();
   }
}
