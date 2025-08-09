package org.apache.hadoop.hive.serde2.lazybinary;

import org.apache.hadoop.hive.serde2.lazy.ByteArrayRef;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.WritableStringObjectInspector;
import org.apache.hadoop.io.Text;

public class LazyBinaryString extends LazyBinaryPrimitive {
   LazyBinaryString(WritableStringObjectInspector OI) {
      super((ObjectInspector)OI);
      this.data = new Text();
   }

   public LazyBinaryString(LazyBinaryString copy) {
      super((LazyBinaryPrimitive)copy);
      this.data = new Text((Text)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      assert length > -1;

      ((Text)this.data).set(bytes.getData(), start, length);
   }
}
