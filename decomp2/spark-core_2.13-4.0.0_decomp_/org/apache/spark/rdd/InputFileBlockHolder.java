package org.apache.spark.rdd;

import org.apache.spark.unsafe.types.UTF8String;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q<a!\u0006\f\t\u0002aqbA\u0002\u0011\u0017\u0011\u0003A\u0012\u0005C\u0003)\u0003\u0011\u0005!F\u0002\u0003,\u0003\u0011a\u0003\u0002C\u0017\u0004\u0005\u000b\u0007I\u0011\u0001\u0018\t\u0011]\u001a!\u0011!Q\u0001\n=B\u0001\u0002O\u0002\u0003\u0006\u0004%\t!\u000f\u0005\t{\r\u0011\t\u0011)A\u0005u!Aah\u0001BC\u0002\u0013\u0005\u0011\b\u0003\u0005@\u0007\t\u0005\t\u0015!\u0003;\u0011\u0015A3\u0001\"\u0001A\u0011\u0015A3\u0001\"\u0001G\u0011\u00199\u0015\u0001)A\u0005\u0011\"1!,\u0001C\u00011mCa\u0001Z\u0001\u0005\u0002a)\u0007\"\u00024\u0002\t\u0003q\u0003\"B4\u0002\t\u0003I\u0004\"\u00025\u0002\t\u0003I\u0004\"B5\u0002\t\u0003Q\u0007\"B=\u0002\t\u0003Q\b\"B>\u0002\t\u0003Q\u0018\u0001F%oaV$h)\u001b7f\u00052|7m\u001b%pY\u0012,'O\u0003\u0002\u00181\u0005\u0019!\u000f\u001a3\u000b\u0005eQ\u0012!B:qCJ\\'BA\u000e\u001d\u0003\u0019\t\u0007/Y2iK*\tQ$A\u0002pe\u001e\u0004\"aH\u0001\u000e\u0003Y\u0011A#\u00138qkR4\u0015\u000e\\3CY>\u001c7\u000eS8mI\u0016\u00148CA\u0001#!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u001f\u0005%1\u0015\u000e\\3CY>\u001c7n\u0005\u0002\u0004E\u0005Aa-\u001b7f!\u0006$\b.F\u00010!\t\u0001T'D\u00012\u0015\t\u00114'A\u0003usB,7O\u0003\u000251\u00051QO\\:bM\u0016L!AN\u0019\u0003\u0015U#f\tO*ue&tw-A\u0005gS2,\u0007+\u0019;iA\u0005Y1\u000f^1si>3gm]3u+\u0005Q\u0004CA\u0012<\u0013\taDE\u0001\u0003M_:<\u0017\u0001D:uCJ$xJ\u001a4tKR\u0004\u0013A\u00027f]\u001e$\b.A\u0004mK:<G\u000f\u001b\u0011\u0015\t\u0005\u001bE)\u0012\t\u0003\u0005\u000ei\u0011!\u0001\u0005\u0006[)\u0001\ra\f\u0005\u0006q)\u0001\rA\u000f\u0005\u0006})\u0001\rA\u000f\u000b\u0002\u0003\u0006Q\u0011N\u001c9vi\ncwnY6\u0011\u0007%s\u0005+D\u0001K\u0015\tYE*\u0001\u0003mC:<'\"A'\u0002\t)\fg/Y\u0005\u0003\u001f*\u0013a#\u00138iKJLG/\u00192mKRC'/Z1e\u0019>\u001c\u0017\r\u001c\t\u0004#b\u000bU\"\u0001*\u000b\u0005M#\u0016AB1u_6L7M\u0003\u0002V-\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005]c\u0015\u0001B;uS2L!!\u0017*\u0003\u001f\u0005#x.\\5d%\u00164WM]3oG\u0016\f1c]3u)\"\u0014X-\u00193M_\u000e\fGNV1mk\u0016$\"\u0001X0\u0011\u0005\rj\u0016B\u00010%\u0005\u0011)f.\u001b;\t\u000b\u0001l\u0001\u0019A1\u0002\u0007I,g\r\u0005\u0002JE&\u00111M\u0013\u0002\u0007\u001f\nTWm\u0019;\u0002'\u001d,G\u000f\u00165sK\u0006$Gj\\2bYZ\u000bG.^3\u0015\u0003\u0005\f\u0001cZ3u\u0013:\u0004X\u000f\u001e$jY\u0016\u0004\u0016\r\u001e5\u0002\u001d\u001d,Go\u0015;beR|eMZ:fi\u0006Iq-\u001a;MK:<G\u000f[\u0001\u0004g\u0016$H\u0003\u0002/lobDQ!\f\nA\u00021\u0004\"!\u001c;\u000f\u00059\u0014\bCA8%\u001b\u0005\u0001(BA9*\u0003\u0019a$o\\8u}%\u00111\u000fJ\u0001\u0007!J,G-\u001a4\n\u0005U4(AB*ue&twM\u0003\u0002tI!)\u0001H\u0005a\u0001u!)aH\u0005a\u0001u\u0005)QO\\:fiR\tA,\u0001\u0006j]&$\u0018.\u00197ju\u0016\u0004"
)
public final class InputFileBlockHolder {
   public static void initialize() {
      InputFileBlockHolder$.MODULE$.initialize();
   }

   public static void unset() {
      InputFileBlockHolder$.MODULE$.unset();
   }

   public static void set(final String filePath, final long startOffset, final long length) {
      InputFileBlockHolder$.MODULE$.set(filePath, startOffset, length);
   }

   public static long getLength() {
      return InputFileBlockHolder$.MODULE$.getLength();
   }

   public static long getStartOffset() {
      return InputFileBlockHolder$.MODULE$.getStartOffset();
   }

   public static UTF8String getInputFilePath() {
      return InputFileBlockHolder$.MODULE$.getInputFilePath();
   }

   private static class FileBlock {
      private final UTF8String filePath;
      private final long startOffset;
      private final long length;

      public UTF8String filePath() {
         return this.filePath;
      }

      public long startOffset() {
         return this.startOffset;
      }

      public long length() {
         return this.length;
      }

      public FileBlock(final UTF8String filePath, final long startOffset, final long length) {
         this.filePath = filePath;
         this.startOffset = startOffset;
         this.length = length;
      }

      public FileBlock() {
         this(UTF8String.fromString(""), -1L, -1L);
      }
   }
}
