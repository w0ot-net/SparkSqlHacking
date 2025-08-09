package org.antlr.v4.runtime;

import java.nio.charset.StandardCharsets;
import org.antlr.v4.runtime.misc.Interval;

public abstract class CodePointCharStream implements CharStream {
   protected final int size;
   protected final String name;
   protected int position;

   private CodePointCharStream(int position, int remaining, String name) {
      assert position == 0;

      this.size = remaining;
      this.name = name;
      this.position = 0;
   }

   abstract Object getInternalStorage();

   public static CodePointCharStream fromBuffer(CodePointBuffer codePointBuffer) {
      return fromBuffer(codePointBuffer, "<unknown>");
   }

   public static CodePointCharStream fromBuffer(CodePointBuffer codePointBuffer, String name) {
      switch (codePointBuffer.getType()) {
         case BYTE:
            return new CodePoint8BitCharStream(codePointBuffer.position(), codePointBuffer.remaining(), name, codePointBuffer.byteArray(), codePointBuffer.arrayOffset());
         case CHAR:
            return new CodePoint16BitCharStream(codePointBuffer.position(), codePointBuffer.remaining(), name, codePointBuffer.charArray(), codePointBuffer.arrayOffset());
         case INT:
            return new CodePoint32BitCharStream(codePointBuffer.position(), codePointBuffer.remaining(), name, codePointBuffer.intArray(), codePointBuffer.arrayOffset());
         default:
            throw new UnsupportedOperationException("Not reached");
      }
   }

   public final void consume() {
      if (this.size - this.position == 0) {
         assert this.LA(1) == -1;

         throw new IllegalStateException("cannot consume EOF");
      } else {
         ++this.position;
      }
   }

   public final int index() {
      return this.position;
   }

   public final int size() {
      return this.size;
   }

   public final int mark() {
      return -1;
   }

   public final void release(int marker) {
   }

   public final void seek(int index) {
      this.position = index;
   }

   public final String getSourceName() {
      return this.name != null && !this.name.isEmpty() ? this.name : "<unknown>";
   }

   public final String toString() {
      return this.getText(Interval.of(0, this.size - 1));
   }

   private static final class CodePoint8BitCharStream extends CodePointCharStream {
      private final byte[] byteArray;

      private CodePoint8BitCharStream(int position, int remaining, String name, byte[] byteArray, int arrayOffset) {
         super(position, remaining, name, null);

         assert arrayOffset == 0;

         this.byteArray = byteArray;
      }

      public String getText(Interval interval) {
         int startIdx = Math.min(interval.a, this.size);
         int len = Math.min(interval.b - interval.a + 1, this.size - startIdx);
         return new String(this.byteArray, startIdx, len, StandardCharsets.ISO_8859_1);
      }

      public int LA(int i) {
         switch (Integer.signum(i)) {
            case -1:
               int offset = this.position + i;
               if (offset < 0) {
                  return -1;
               }

               return this.byteArray[offset] & 255;
            case 0:
               return 0;
            case 1:
               int offset = this.position + i - 1;
               if (offset >= this.size) {
                  return -1;
               }

               return this.byteArray[offset] & 255;
            default:
               throw new UnsupportedOperationException("Not reached");
         }
      }

      Object getInternalStorage() {
         return this.byteArray;
      }
   }

   private static final class CodePoint16BitCharStream extends CodePointCharStream {
      private final char[] charArray;

      private CodePoint16BitCharStream(int position, int remaining, String name, char[] charArray, int arrayOffset) {
         super(position, remaining, name, null);
         this.charArray = charArray;

         assert arrayOffset == 0;

      }

      public String getText(Interval interval) {
         int startIdx = Math.min(interval.a, this.size);
         int len = Math.min(interval.b - interval.a + 1, this.size - startIdx);
         return new String(this.charArray, startIdx, len);
      }

      public int LA(int i) {
         switch (Integer.signum(i)) {
            case -1:
               int offset = this.position + i;
               if (offset < 0) {
                  return -1;
               }

               return this.charArray[offset] & '\uffff';
            case 0:
               return 0;
            case 1:
               int offset = this.position + i - 1;
               if (offset >= this.size) {
                  return -1;
               }

               return this.charArray[offset] & '\uffff';
            default:
               throw new UnsupportedOperationException("Not reached");
         }
      }

      Object getInternalStorage() {
         return this.charArray;
      }
   }

   private static final class CodePoint32BitCharStream extends CodePointCharStream {
      private final int[] intArray;

      private CodePoint32BitCharStream(int position, int remaining, String name, int[] intArray, int arrayOffset) {
         super(position, remaining, name, null);
         this.intArray = intArray;

         assert arrayOffset == 0;

      }

      public String getText(Interval interval) {
         int startIdx = Math.min(interval.a, this.size);
         int len = Math.min(interval.b - interval.a + 1, this.size - startIdx);
         return new String(this.intArray, startIdx, len);
      }

      public int LA(int i) {
         switch (Integer.signum(i)) {
            case -1:
               int offset = this.position + i;
               if (offset < 0) {
                  return -1;
               }

               return this.intArray[offset];
            case 0:
               return 0;
            case 1:
               int offset = this.position + i - 1;
               if (offset >= this.size) {
                  return -1;
               }

               return this.intArray[offset];
            default:
               throw new UnsupportedOperationException("Not reached");
         }
      }

      Object getInternalStorage() {
         return this.intArray;
      }
   }
}
