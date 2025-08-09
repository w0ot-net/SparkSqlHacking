package org.apache.hadoop.hive.serde2.lazy;

import java.nio.charset.CharacterCodingException;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LazyPrimitive extends LazyObject {
   private static final Logger LOG = LoggerFactory.getLogger(LazyPrimitive.class);
   protected Writable data;

   protected LazyPrimitive(ObjectInspector oi) {
      super(oi);
   }

   protected LazyPrimitive(LazyPrimitive copy) {
      super(copy.oi);
      this.isNull = copy.isNull;
   }

   public Writable getWritableObject() {
      return this.isNull ? null : this.data;
   }

   public String toString() {
      return this.isNull ? null : this.data.toString();
   }

   public int hashCode() {
      return this.isNull ? 0 : this.data.hashCode();
   }

   public boolean equals(Object obj) {
      if (!(obj instanceof LazyPrimitive)) {
         return false;
      } else if (this.data == obj) {
         return true;
      } else {
         return this.data != null && obj != null ? this.data.equals(((LazyPrimitive)obj).getWritableObject()) : false;
      }
   }

   public void logExceptionMessage(ByteArrayRef bytes, int start, int length, String dataType) {
      try {
         if (LOG.isDebugEnabled()) {
            String byteData = Text.decode(bytes.getData(), start, length);
            LOG.debug("Data not in the " + dataType + " data type range so converted to null. Given data is :" + byteData, new Exception("For debugging purposes"));
         }
      } catch (CharacterCodingException e1) {
         LOG.debug("Data not in the " + dataType + " data type range so converted to null.", e1);
      }

   }
}
