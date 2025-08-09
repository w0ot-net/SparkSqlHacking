package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyBooleanObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.BooleanWritable;

public class LazyBoolean extends LazyPrimitive {
   public LazyBoolean(LazyBooleanObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new BooleanWritable();
   }

   public LazyBoolean(LazyBoolean copy) {
      super((LazyPrimitive)copy);
      this.data = new BooleanWritable(((BooleanWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (length == 4 && Character.toUpperCase(bytes.getData()[start]) == 84 && Character.toUpperCase(bytes.getData()[start + 1]) == 82 && Character.toUpperCase(bytes.getData()[start + 2]) == 85 && Character.toUpperCase(bytes.getData()[start + 3]) == 69) {
         ((BooleanWritable)this.data).set(true);
         this.isNull = false;
      } else if (length == 5 && Character.toUpperCase(bytes.getData()[start]) == 70 && Character.toUpperCase(bytes.getData()[start + 1]) == 65 && Character.toUpperCase(bytes.getData()[start + 2]) == 76 && Character.toUpperCase(bytes.getData()[start + 3]) == 83 && Character.toUpperCase(bytes.getData()[start + 4]) == 69) {
         ((BooleanWritable)this.data).set(false);
         this.isNull = false;
      } else if (((LazyBooleanObjectInspector)this.oi).isExtendedLiteral()) {
         if (length == 1) {
            byte c = bytes.getData()[start];
            if (c != 49 && c != 116 && c != 84) {
               if (c != 48 && c != 102 && c != 70) {
                  this.isNull = true;
               } else {
                  ((BooleanWritable)this.data).set(false);
                  this.isNull = false;
               }
            } else {
               ((BooleanWritable)this.data).set(true);
               this.isNull = false;
            }
         }
      } else {
         this.isNull = true;
         this.logExceptionMessage(bytes, start, length, "BOOLEAN");
      }

   }
}
