package org.apache.orc.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.hadoop.io.Text;

public class DictionaryUtils {
   private DictionaryUtils() {
   }

   public static void getTextInternal(Text result, int position, DynamicIntArray keyOffsets, DynamicByteArray byteArray) {
      int offset = keyOffsets.get(position);
      int length;
      if (position + 1 == keyOffsets.size()) {
         length = byteArray.size() - offset;
      } else {
         length = keyOffsets.get(position + 1) - offset;
      }

      byteArray.setText(result, offset, length);
   }

   public static ByteBuffer getTextInternal(int position, DynamicIntArray keyOffsets, DynamicByteArray byteArray) {
      int offset = keyOffsets.get(position);
      int length;
      if (position + 1 == keyOffsets.size()) {
         length = byteArray.size() - offset;
      } else {
         length = keyOffsets.get(position + 1) - offset;
      }

      return byteArray.get(offset, length);
   }

   public static int writeToTextInternal(OutputStream out, int position, DynamicIntArray keyOffsets, DynamicByteArray byteArray) throws IOException {
      int offset = keyOffsets.get(position);
      int length;
      if (position + 1 == keyOffsets.size()) {
         length = byteArray.size() - offset;
      } else {
         length = keyOffsets.get(position + 1) - offset;
      }

      byteArray.write(out, offset, length);
      return length;
   }

   public static boolean equalsInternal(byte[] bytes, int offset, int length, int position, DynamicIntArray keyOffsets, DynamicByteArray byteArray) {
      int byteArrayOffset = keyOffsets.get(position);
      int keyLength;
      if (position + 1 == keyOffsets.size()) {
         keyLength = byteArray.size() - byteArrayOffset;
      } else {
         keyLength = keyOffsets.get(position + 1) - byteArrayOffset;
      }

      return 0 == byteArray.compare(bytes, offset, length, byteArrayOffset, keyLength);
   }
}
