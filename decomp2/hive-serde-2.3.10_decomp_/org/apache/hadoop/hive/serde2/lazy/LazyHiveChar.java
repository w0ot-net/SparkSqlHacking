package org.apache.hadoop.hive.serde2.lazy;

import java.nio.charset.CharacterCodingException;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyHiveChar extends LazyPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyHiveChar.class);
   protected int maxLength = -1;

   public LazyHiveChar(LazyHiveCharObjectInspector oi) {
      super((ObjectInspector)oi);
      this.maxLength = ((CharTypeInfo)oi.getTypeInfo()).getLength();
      this.data = new HiveCharWritable();
   }

   public LazyHiveChar(LazyHiveChar copy) {
      super((LazyPrimitive)copy);
      this.maxLength = copy.maxLength;
      this.data = new HiveCharWritable((HiveCharWritable)copy.data);
   }

   public void setValue(LazyHiveChar copy) {
      ((HiveCharWritable)this.data).set((HiveCharWritable)copy.data, this.maxLength);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (((LazyHiveCharObjectInspector)this.oi).isEscaped()) {
         Text textData = ((HiveCharWritable)this.data).getTextValue();
         LazyUtils.copyAndEscapeStringDataToText(bytes.getData(), start, length, ((LazyHiveCharObjectInspector)this.oi).getEscapeChar(), textData);
         ((HiveCharWritable)this.data).set(textData.toString(), this.maxLength);
         this.isNull = false;
      } else {
         String byteData = null;

         try {
            byteData = Text.decode(bytes.getData(), start, length);
            ((HiveCharWritable)this.data).set(byteData, this.maxLength);
            this.isNull = false;
         } catch (CharacterCodingException e) {
            this.isNull = true;
            LOG.debug("Data not in the HiveChar data type range so converted to null.", e);
         }
      }

   }
}
