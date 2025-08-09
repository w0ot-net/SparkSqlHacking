package org.apache.orc.impl.mask;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.DataMask;
import org.apache.orc.TypeDescription;

public class SHA256MaskFactory extends MaskFactory {
   private final MessageDigest md;
   private static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   SHA256MaskFactory() {
      try {
         this.md = MessageDigest.getInstance("SHA-256");
      } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException(e);
      }
   }

   public static String printHexBinary(byte[] data) {
      char[] out = new char[data.length << 1];
      int i = 0;

      for(int j = 0; i < data.length; ++i) {
         out[j++] = DIGITS[(240 & data[i]) >>> 4];
         out[j++] = DIGITS[15 & data[i]];
      }

      return new String(out);
   }

   void maskString(BytesColumnVector source, int row, BytesColumnVector target, TypeDescription schema) {
      this.md.update(source.vector[row], source.start[row], source.length[row]);
      byte[] hash = printHexBinary(this.md.digest()).getBytes(StandardCharsets.UTF_8);
      int targetLength = hash.length;
      switch (schema.getCategory()) {
         case VARCHAR:
            if (schema.getMaxLength() < hash.length) {
               targetLength = schema.getMaxLength();
            }
            break;
         case CHAR:
            targetLength = schema.getMaxLength();
            if (targetLength > hash.length) {
               byte[] tmp = Arrays.copyOf(hash, targetLength);
               Arrays.fill(tmp, hash.length, tmp.length - 1, (byte)32);
               hash = tmp;
            }
      }

      target.vector[row] = hash;
      target.start[row] = 0;
      target.length[row] = targetLength;
   }

   void maskBinary(BytesColumnVector source, int row, BytesColumnVector target) {
      ByteBuffer sourceBytes = ByteBuffer.wrap(source.vector[row], source.start[row], source.length[row]);
      byte[] hash = this.md.digest(sourceBytes.array());
      int targetLength = hash.length;
      target.vector[row] = hash;
      target.start[row] = 0;
      target.length[row] = targetLength;
   }

   protected DataMask buildBinaryMask(TypeDescription schema) {
      return new BinaryMask();
   }

   protected DataMask buildBooleanMask(TypeDescription schema) {
      return new NullifyMask();
   }

   protected DataMask buildLongMask(TypeDescription schema) {
      return new NullifyMask();
   }

   protected DataMask buildDecimalMask(TypeDescription schema) {
      return new NullifyMask();
   }

   protected DataMask buildDoubleMask(TypeDescription schema) {
      return new NullifyMask();
   }

   protected DataMask buildStringMask(TypeDescription schema) {
      return new StringMask(schema);
   }

   protected DataMask buildDateMask(TypeDescription schema) {
      return new NullifyMask();
   }

   protected DataMask buildTimestampMask(TypeDescription schema) {
      return new NullifyMask();
   }

   class StringMask implements DataMask {
      final TypeDescription schema;

      StringMask(TypeDescription schema) {
         this.schema = schema;
      }

      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         BytesColumnVector target = (BytesColumnVector)masked;
         BytesColumnVector source = (BytesColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         if (original.isRepeating) {
            target.isNull[0] = source.isNull[0];
            if (target.noNulls || !target.isNull[0]) {
               SHA256MaskFactory.this.maskString(source, 0, target, this.schema);
            }
         } else {
            for(int r = start; r < start + length; ++r) {
               target.isNull[r] = source.isNull[r];
               if (target.noNulls || !target.isNull[r]) {
                  SHA256MaskFactory.this.maskString(source, r, target, this.schema);
               }
            }
         }

      }
   }

   class BinaryMask implements DataMask {
      public void maskData(ColumnVector original, ColumnVector masked, int start, int length) {
         BytesColumnVector target = (BytesColumnVector)masked;
         BytesColumnVector source = (BytesColumnVector)original;
         target.noNulls = original.noNulls;
         target.isRepeating = original.isRepeating;
         if (original.isRepeating) {
            target.isNull[0] = source.isNull[0];
            if (target.noNulls || !target.isNull[0]) {
               SHA256MaskFactory.this.maskBinary(source, 0, target);
            }
         } else {
            for(int r = start; r < start + length; ++r) {
               target.isNull[r] = source.isNull[r];
               if (target.noNulls || !target.isNull[r]) {
                  SHA256MaskFactory.this.maskBinary(source, r, target);
               }
            }
         }

      }
   }
}
