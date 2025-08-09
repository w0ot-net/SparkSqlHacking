package org.apache.spark.unsafe.types;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Arrays;
import org.apache.spark.types.variant.Variant;
import org.apache.spark.unsafe.Platform;

public class VariantVal implements Serializable {
   protected final byte[] value;
   protected final byte[] metadata;

   public VariantVal(byte[] value, byte[] metadata) {
      this.value = value;
      this.metadata = metadata;
   }

   public byte[] getValue() {
      return this.value;
   }

   public byte[] getMetadata() {
      return this.metadata;
   }

   public static VariantVal readFromUnsafeRow(long offsetAndSize, Object baseObject, long baseOffset) {
      int offset = (int)(offsetAndSize >> 32);
      int totalSize = (int)offsetAndSize;
      int valueSize = Platform.getInt(baseObject, baseOffset + (long)offset);
      int metadataSize = totalSize - 4 - valueSize;
      byte[] value = new byte[valueSize];
      byte[] metadata = new byte[metadataSize];
      Platform.copyMemory(baseObject, baseOffset + (long)offset + 4L, value, (long)Platform.BYTE_ARRAY_OFFSET, (long)valueSize);
      Platform.copyMemory(baseObject, baseOffset + (long)offset + 4L + (long)valueSize, metadata, (long)Platform.BYTE_ARRAY_OFFSET, (long)metadataSize);
      return new VariantVal(value, metadata);
   }

   public String debugString() {
      String var10000 = Arrays.toString(this.value);
      return "VariantVal{value=" + var10000 + ", metadata=" + Arrays.toString(this.metadata) + "}";
   }

   public String toJson(ZoneId zoneId) {
      return (new Variant(this.value, this.metadata)).toJson(zoneId);
   }

   public String toString() {
      return this.toJson(ZoneOffset.UTC);
   }

   public boolean equals(Object other) {
      if (!(other instanceof VariantVal o)) {
         return false;
      } else {
         return Arrays.equals(this.value, o.value) && Arrays.equals(this.metadata, o.metadata);
      }
   }

   public int hashCode() {
      int result = Arrays.hashCode(this.value);
      result = 31 * result + Arrays.hashCode(this.metadata);
      return result;
   }
}
