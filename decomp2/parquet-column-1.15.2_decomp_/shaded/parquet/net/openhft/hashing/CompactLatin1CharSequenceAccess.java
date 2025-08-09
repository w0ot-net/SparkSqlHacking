package shaded.parquet.net.openhft.hashing;

import java.nio.ByteOrder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.jetbrains.annotations.NotNull;

@ParametersAreNonnullByDefault
public class CompactLatin1CharSequenceAccess extends Access {
   @NotNull
   static final Access INSTANCE = new CompactLatin1CharSequenceAccess();
   @NotNull
   private static final Access INSTANCE_NON_NATIVE;
   @NotNull
   private static final UnsafeAccess UNSAFE;
   private static final long UNSAFE_IDX_ADJUST;
   private static final long ARRAY_IDX_ADJUST;

   private CompactLatin1CharSequenceAccess() {
   }

   public long getLong(byte[] input, long offset) {
      long byteIdx = offset + UNSAFE_IDX_ADJUST >> 1;
      long compact = UNSAFE.getUnsignedInt(input, byteIdx);
      long expanded = (compact << 16 | compact) & 281470681808895L;
      expanded = (expanded << 8 | expanded) & 71777214294589695L;
      return ((int)offset & 1) == 1 ? expanded << 8 : expanded;
   }

   public int getInt(byte[] input, long offset) {
      long byteIdx = offset + UNSAFE_IDX_ADJUST >> 1;
      int compact = UNSAFE.getShort(input, byteIdx) & '\uffff';
      int expanded = (compact << 8 | compact) & 16711935;
      return ((int)offset & 1) == 1 ? expanded << 8 : expanded;
   }

   public long getUnsignedInt(byte[] input, long offset) {
      long byteIdx = offset + UNSAFE_IDX_ADJUST >> 1;
      int compact = UNSAFE.getShort(input, byteIdx) & '\uffff';
      long expanded = (long)((compact << 8 | compact) & 16711935);
      return ((int)offset & 1) == 1 ? expanded << 8 : expanded;
   }

   public int getShort(byte[] input, long offset) {
      if (((int)offset & 1) == 0) {
         int byteIdx = (int)(offset >> 1);
         return input[byteIdx] & 255;
      } else {
         int byteIdx = (int)(offset + ARRAY_IDX_ADJUST >> 1);
         return input[byteIdx] << 8;
      }
   }

   public int getUnsignedShort(byte[] input, long offset) {
      if (((int)offset & 1) == 0) {
         int byteIdx = (int)(offset >> 1);
         return input[byteIdx] & 255;
      } else {
         int byteIdx = (int)(offset + ARRAY_IDX_ADJUST >> 1);
         return (input[byteIdx] & 255) << 8;
      }
   }

   public int getByte(byte[] input, long offset) {
      return ARRAY_IDX_ADJUST == (long)((int)offset & 1) ? 0 : input[(int)(offset >> 1)];
   }

   public int getUnsignedByte(byte[] input, long offset) {
      return ARRAY_IDX_ADJUST == (long)((int)offset & 1) ? 0 : input[(int)(offset >> 1)] & 255;
   }

   @NotNull
   public ByteOrder byteOrder(byte[] input) {
      return UNSAFE.byteOrder(input);
   }

   @NotNull
   protected Access reverseAccess() {
      return INSTANCE_NON_NATIVE;
   }

   static {
      INSTANCE_NON_NATIVE = Access.newDefaultReverseAccess(INSTANCE);
      UNSAFE = UnsafeAccess.INSTANCE;
      UNSAFE_IDX_ADJUST = UnsafeAccess.BYTE_BASE * 2L + (long)(ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? 1 : 0);
      ARRAY_IDX_ADJUST = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? 1L : 0L;
   }
}
