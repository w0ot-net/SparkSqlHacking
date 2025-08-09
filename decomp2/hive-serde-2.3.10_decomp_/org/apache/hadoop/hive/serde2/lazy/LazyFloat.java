package org.apache.hadoop.hive.serde2.lazy;

import java.nio.charset.CharacterCodingException;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyFloatObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyFloat extends LazyPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyFloat.class);

   public LazyFloat(LazyFloatObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new FloatWritable();
   }

   public LazyFloat(LazyFloat copy) {
      super((LazyPrimitive)copy);
      this.data = new FloatWritable(((FloatWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      String byteData = null;
      if (!LazyUtils.isNumberMaybe(bytes.getData(), start, length)) {
         this.isNull = true;
      } else {
         try {
            byteData = Text.decode(bytes.getData(), start, length);
            ((FloatWritable)this.data).set(Float.parseFloat(byteData));
            this.isNull = false;
         } catch (NumberFormatException e) {
            this.isNull = true;
            LOG.debug("Data not in the Float data type range so converted to null. Given data is :" + byteData, e);
         } catch (CharacterCodingException e) {
            this.isNull = true;
            LOG.debug("Data not in the Float data type range so converted to null.", e);
         }

      }
   }
}
