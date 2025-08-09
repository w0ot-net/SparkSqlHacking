package org.apache.hadoop.hive.serde2.lazy;

import java.nio.charset.CharacterCodingException;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyHiveVarchar extends LazyPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyHiveVarchar.class);
   protected int maxLength = -1;

   public LazyHiveVarchar(LazyHiveVarcharObjectInspector oi) {
      super((ObjectInspector)oi);
      this.maxLength = ((VarcharTypeInfo)oi.getTypeInfo()).getLength();
      this.data = new HiveVarcharWritable();
   }

   public LazyHiveVarchar(LazyHiveVarchar copy) {
      super((LazyPrimitive)copy);
      this.maxLength = copy.maxLength;
      this.data = new HiveVarcharWritable((HiveVarcharWritable)copy.data);
   }

   public void setValue(LazyHiveVarchar copy) {
      ((HiveVarcharWritable)this.data).set((HiveVarcharWritable)copy.data, this.maxLength);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      if (((LazyHiveVarcharObjectInspector)this.oi).isEscaped()) {
         Text textData = ((HiveVarcharWritable)this.data).getTextValue();
         LazyUtils.copyAndEscapeStringDataToText(bytes.getData(), start, length, ((LazyHiveVarcharObjectInspector)this.oi).getEscapeChar(), textData);
         ((HiveVarcharWritable)this.data).set(textData.toString(), this.maxLength);
         this.isNull = false;
      } else {
         try {
            String byteData = null;
            byteData = Text.decode(bytes.getData(), start, length);
            ((HiveVarcharWritable)this.data).set(byteData, this.maxLength);
            this.isNull = false;
         } catch (CharacterCodingException e) {
            this.isNull = true;
            LOG.debug("Data not in the HiveVarchar data type range so converted to null.", e);
         }
      }

   }
}
