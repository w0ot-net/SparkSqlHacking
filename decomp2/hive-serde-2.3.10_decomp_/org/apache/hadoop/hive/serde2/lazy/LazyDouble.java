package org.apache.hadoop.hive.serde2.lazy;

import java.nio.charset.CharacterCodingException;
import org.apache.hadoop.hive.serde2.io.DoubleWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyDoubleObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyDouble extends LazyPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyDouble.class);

   public LazyDouble(LazyDoubleObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new DoubleWritable();
   }

   public LazyDouble(LazyDouble copy) {
      super((LazyPrimitive)copy);
      this.data = new DoubleWritable(((DoubleWritable)copy.data).get());
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      String byteData = null;
      if (!LazyUtils.isNumberMaybe(bytes.getData(), start, length)) {
         this.isNull = true;
      } else {
         try {
            byteData = Text.decode(bytes.getData(), start, length);
            ((DoubleWritable)this.data).set(Double.parseDouble(byteData));
            this.isNull = false;
         } catch (NumberFormatException e) {
            this.isNull = true;
            LOG.debug("Data not in the Double data type range so converted to null. Given data is :" + byteData, e);
         } catch (CharacterCodingException e) {
            this.isNull = true;
            LOG.debug("Data not in the Double data type range so converted to null.", e);
         }

      }
   }
}
