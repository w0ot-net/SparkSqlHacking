package org.apache.hadoop.hive.serde2.lazy;

import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyStringObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;

public class LazyString extends LazyPrimitive {
   public LazyString(LazyStringObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new Text();
   }

   public LazyString(LazyString copy) {
      super((LazyPrimitive)copy);
      this.data = new Text((Text)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (((LazyStringObjectInspector)this.oi).isEscaped()) {
         byte escapeChar = ((LazyStringObjectInspector)this.oi).getEscapeChar();
         byte[] inputBytes = bytes.getData();
         LazyUtils.copyAndEscapeStringDataToText(inputBytes, start, length, escapeChar, (Text)this.data);
      } else {
         ((Text)this.data).set(bytes.getData(), start, length);
      }

      this.isNull = false;
   }
}
