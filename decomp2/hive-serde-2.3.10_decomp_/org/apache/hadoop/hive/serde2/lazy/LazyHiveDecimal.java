package org.apache.hadoop.hive.serde2.lazy;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyHiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyHiveDecimal extends LazyPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyHiveDecimal.class);
   private final int precision;
   private final int scale;
   private static final byte[] nullBytes = new byte[]{0, 0, 0, 0};

   public LazyHiveDecimal(LazyHiveDecimalObjectInspector oi) {
      super((ObjectInspector)oi);
      DecimalTypeInfo typeInfo = (DecimalTypeInfo)oi.getTypeInfo();
      if (typeInfo == null) {
         throw new RuntimeException("Decimal type used without type params");
      } else {
         this.precision = typeInfo.precision();
         this.scale = typeInfo.scale();
         this.data = new HiveDecimalWritable();
      }
   }

   public LazyHiveDecimal(LazyHiveDecimal copy) {
      super((LazyPrimitive)copy);
      this.precision = copy.precision;
      this.scale = copy.scale;
      this.data = new HiveDecimalWritable((HiveDecimalWritable)copy.data);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      ((HiveDecimalWritable)this.data).setFromBytes(bytes.getData(), start, length);
      if (!((HiveDecimalWritable)this.data).isSet()) {
         this.isNull = true;
      } else {
         this.isNull = !((HiveDecimalWritable)this.data).mutateEnforcePrecisionScale(this.precision, this.scale);
      }

      if (this.isNull) {
         LOG.debug("Data not in the HiveDecimal data type range so converted to null. Given data is :" + new String(bytes.getData(), start, length, StandardCharsets.UTF_8));
      }

   }

   public HiveDecimalWritable getWritableObject() {
      return (HiveDecimalWritable)this.data;
   }

   public static void writeUTF8(OutputStream outputStream, HiveDecimal hiveDecimal, int scale) throws IOException {
      if (hiveDecimal == null) {
         outputStream.write(nullBytes);
      } else {
         byte[] scratchBuffer = new byte[79];
         int index = hiveDecimal.toFormatBytes(scale, scratchBuffer);
         outputStream.write(scratchBuffer, index, scratchBuffer.length - index);
      }

   }

   public static void writeUTF8(OutputStream outputStream, HiveDecimal hiveDecimal, int scale, byte[] scratchBuffer) throws IOException {
      if (hiveDecimal == null) {
         outputStream.write(nullBytes);
      } else {
         int index = hiveDecimal.toFormatBytes(scale, scratchBuffer);
         outputStream.write(scratchBuffer, index, scratchBuffer.length - index);
      }

   }

   public static void writeUTF8(OutputStream outputStream, HiveDecimalWritable hiveDecimalWritable, int scale, byte[] scratchBuffer) throws IOException {
      if (hiveDecimalWritable != null && hiveDecimalWritable.isSet()) {
         int index = hiveDecimalWritable.toFormatBytes(scale, scratchBuffer);
         outputStream.write(scratchBuffer, index, scratchBuffer.length - index);
      } else {
         outputStream.write(nullBytes);
      }

   }
}
