package org.apache.derby.impl.store.raw.data;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.OutputStream;
import org.apache.derby.iapi.services.io.ArrayInputStream;
import org.apache.derby.iapi.services.io.CompressedNumber;
import org.apache.derby.iapi.services.io.DataInputUtil;

public final class StoredFieldHeader {
   private static final int FIELD_INITIAL = 0;
   public static final int FIELD_NULL = 1;
   public static final int FIELD_OVERFLOW = 2;
   private static final int FIELD_NOT_NULLABLE = 4;
   public static final int FIELD_EXTENSIBLE = 8;
   public static final int FIELD_TAGGED = 16;
   protected static final int FIELD_FIXED = 32;
   public static final int FIELD_NONEXISTENT = 5;
   public static final int STORED_FIELD_HEADER_STATUS_SIZE = 1;

   public static final boolean isNull(int var0) {
      return (var0 & 1) == 1;
   }

   public static final boolean isOverflow(int var0) {
      return (var0 & 2) == 2;
   }

   public static final boolean isNonexistent(int var0) {
      return (var0 & 5) == 5;
   }

   public static final boolean isExtensible(int var0) {
      return (var0 & 8) == 8;
   }

   public static final boolean isNullorNonExistent(int var0) {
      return (var0 & 1) != 0;
   }

   public static final boolean isTagged(int var0) {
      return (var0 & 16) == 16;
   }

   public static final boolean isFixed(int var0) {
      return (var0 & 32) == 32;
   }

   public static final boolean isNullable(int var0) {
      return (var0 & 4) == 0;
   }

   public static final int size(int var0, int var1, int var2) {
      if ((var0 & 33) == 0) {
         if (var1 <= 63) {
            return 2;
         } else {
            return var1 <= 16383 ? 3 : 5;
         }
      } else if ((var0 & 1) != 0) {
         return 1;
      } else {
         return var2 > 2 ? 5 : 3;
      }
   }

   public static final int setInitial() {
      return 0;
   }

   public static final int setNull(int var0, boolean var1) {
      if (var1) {
         var0 |= 1;
      } else {
         var0 &= -2;
      }

      return var0;
   }

   public static final int setOverflow(int var0, boolean var1) {
      if (var1) {
         var0 |= 2;
      } else {
         var0 &= -3;
      }

      return var0;
   }

   public static final int setNonexistent(int var0) {
      var0 |= 5;
      return var0;
   }

   public static final int setExtensible(int var0, boolean var1) {
      if (var1) {
         var0 |= 8;
      } else {
         var0 &= -9;
      }

      return var0;
   }

   public static final int setTagged(int var0, boolean var1) {
      if (var1) {
         var0 |= 16;
      } else {
         var0 &= -17;
      }

      return var0;
   }

   public static final int setFixed(int var0, boolean var1) {
      if (var1) {
         var0 |= 32;
      } else {
         var0 &= -33;
      }

      return var0;
   }

   public static final int write(OutputStream var0, int var1, int var2, int var3) throws IOException {
      int var4 = 1;
      var0.write(var1);
      if (isNull(var1)) {
         return var4;
      } else {
         if (isFixed(var1)) {
            if (var3 > 2) {
               int var5 = var3 - CompressedNumber.writeInt(var0, var2);

               for(int var6 = var5; var6 > 0; --var6) {
                  var0.write(0);
               }

               var4 += var3;
            } else {
               var0.write(var2 >>> 8 & 255);
               var0.write(var2 >>> 0 & 255);
               var4 += 2;
            }
         } else {
            var4 += CompressedNumber.writeInt(var0, var2);
         }

         return var4;
      }
   }

   public static final int readStatus(ObjectInput var0) throws IOException {
      int var1;
      if ((var1 = var0.read()) >= 0) {
         return var1;
      } else {
         throw new EOFException();
      }
   }

   public static final int readStatus(byte[] var0, int var1) {
      return var0[var1];
   }

   public static final int readTotalFieldLength(byte[] var0, int var1) throws IOException {
      if ((var0[var1++] & 1) != 1) {
         byte var2 = var0[var1];
         if ((var2 & -64) == 0) {
            return var2 + 2;
         } else {
            return (var2 & 128) == 0 ? ((var2 & 63) << 8 | var0[var1 + 1] & 255) + 3 : ((var2 & 127) << 24 | (var0[var1 + 1] & 255) << 16 | (var0[var1 + 2] & 255) << 8 | var0[var1 + 3] & 255) + 5;
         }
      } else {
         return 1;
      }
   }

   public static final int readFieldLengthAndSetStreamPosition(byte[] var0, int var1, int var2, int var3, ArrayInputStream var4) throws IOException {
      if ((var2 & 33) == 0) {
         int var11 = var0[var1++];
         if ((var11 & -64) != 0) {
            if ((var11 & 128) == 0) {
               var11 = (var11 & 63) << 8 | var0[var1++] & 255;
            } else {
               var11 = (var11 & 127) << 24 | (var0[var1++] & 255) << 16 | (var0[var1++] & 255) << 8 | var0[var1++] & 255;
            }
         }

         var4.setPosition(var1);
         return var11;
      } else if ((var2 & 1) != 0) {
         var4.setPosition(var1);
         return 0;
      } else {
         int var5;
         if (var3 <= 2) {
            var5 = (var0[var1++] & 255) << 8 | var0[var1++] & 255;
         } else {
            var5 = var0[var1];
            if ((var5 & -64) != 0) {
               if ((var5 & 128) == 0) {
                  var5 = (var5 & 63) << 8 | var0[var1 + 1] & 255;
               } else {
                  var5 = (var5 & 127) << 24 | (var0[var1 + 1] & 255) << 16 | (var0[var1 + 2] & 255) << 8 | var0[var1 + 3] & 255;
               }
            }

            var1 += var3;
         }

         var4.setPosition(var1);
         return var5;
      }
   }

   public static final int readFieldDataLength(ObjectInput var0, int var1, int var2) throws IOException {
      if ((var1 & 33) == 0) {
         return CompressedNumber.readInt((DataInput)var0);
      } else if ((var1 & 1) != 0) {
         return 0;
      } else {
         int var3;
         if (var2 <= 2) {
            int var4 = var0.read();
            int var5 = var0.read();
            if ((var4 | var5) < 0) {
               throw new EOFException();
            }

            var3 = (var4 << 8) + (var5 << 0);
         } else {
            var3 = CompressedNumber.readInt((DataInput)var0);
            int var6 = var2 - CompressedNumber.sizeInt(var3);
            if (var6 != 0) {
               DataInputUtil.skipFully(var0, var6);
            }
         }

         return var3;
      }
   }

   public static String toDebugString(int var0) {
      return null;
   }
}
