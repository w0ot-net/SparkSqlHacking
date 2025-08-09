package org.apache.hadoop.hive.serde2.lazy;

import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive.LazyBinaryObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.io.BytesWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyBinary extends LazyPrimitive {
   private static final Logger LOG = LoggerFactory.getLogger(LazyBinary.class);
   private static final boolean DEBUG_LOG_ENABLED;

   public LazyBinary(LazyBinaryObjectInspector oi) {
      super((ObjectInspector)oi);
      this.data = new BytesWritable();
   }

   public LazyBinary(LazyBinary other) {
      super((LazyPrimitive)other);
      BytesWritable incoming = (BytesWritable)other.getWritableObject();
      byte[] bytes = new byte[incoming.getLength()];
      System.arraycopy(incoming.getBytes(), 0, bytes, 0, incoming.getLength());
      this.data = new BytesWritable(bytes);
   }

   public void init(ByteArrayRef bytes, int start, int length) {
      super.init(bytes, start, length);
      byte[] recv = new byte[length];
      System.arraycopy(bytes.getData(), start, recv, 0, length);
      byte[] decoded = decodeIfNeeded(recv);
      decoded = decoded.length > 0 ? decoded : recv;
      ((BytesWritable)this.data).set(decoded, 0, decoded.length);
   }

   public static byte[] decodeIfNeeded(byte[] recv) {
      boolean arrayByteBase64 = Base64.isArrayByteBase64(recv);
      if (DEBUG_LOG_ENABLED && arrayByteBase64) {
         LOG.debug("Data only contains Base64 alphabets only so try to decode the data.");
      }

      return arrayByteBase64 ? Base64.decodeBase64(recv) : recv;
   }

   static {
      DEBUG_LOG_ENABLED = LOG.isDebugEnabled();
   }
}
