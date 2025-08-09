package scala.xml.include.sax;

import java.io.InputStream;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U;Q!\u0006\f\t\u0002}1Q!\t\f\t\u0002\tBQaJ\u0001\u0005\u0002!:Q!K\u0001\t\u0002)2Q\u0001L\u0001\t\u00025BQa\n\u0003\u0005\u00029Bqa\f\u0003C\u0002\u0013\u0005\u0001\u0007\u0003\u0004=\t\u0001\u0006I!\r\u0005\b{\u0011\u0011\r\u0011\"\u00011\u0011\u0019qD\u0001)A\u0005c!9q\b\u0002b\u0001\n\u0003\u0001\u0004B\u0002!\u0005A\u0003%\u0011\u0007C\u0004B\t\t\u0007I\u0011\u0001\u0019\t\r\t#\u0001\u0015!\u00032\u0011\u001d\u0019EA1A\u0005\u0002ABa\u0001\u0012\u0003!\u0002\u0013\t\u0004bB#\u0005\u0005\u0004%\t\u0001\r\u0005\u0007\r\u0012\u0001\u000b\u0011B\u0019\t\u000f\u001d#!\u0019!C\u0001a!1\u0001\n\u0002Q\u0001\nEBQ!S\u0001\u0005\u0002)\u000b!#\u00128d_\u0012Lgn\u001a%fkJL7\u000f^5dg*\u0011q\u0003G\u0001\u0004g\u0006D(BA\r\u001b\u0003\u001dIgn\u00197vI\u0016T!a\u0007\u000f\u0002\u0007alGNC\u0001\u001e\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"\u0001I\u0001\u000e\u0003Y\u0011!#\u00128d_\u0012Lgn\u001a%fkJL7\u000f^5dgN\u0011\u0011a\t\t\u0003I\u0015j\u0011\u0001H\u0005\u0003Mq\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001 \u00035)enY8eS:<g*Y7fgB\u00111\u0006B\u0007\u0002\u0003\tiQI\\2pI&twMT1nKN\u001c\"\u0001B\u0012\u0015\u0003)\nqAY5h+\u000e\u001bF'F\u00012!\t\u0011\u0014H\u0004\u00024oA\u0011A\u0007H\u0007\u0002k)\u0011aGH\u0001\u0007yI|w\u000e\u001e \n\u0005ab\u0012A\u0002)sK\u0012,g-\u0003\u0002;w\t11\u000b\u001e:j]\u001eT!\u0001\u000f\u000f\u0002\u0011\tLw-V\"Ti\u0001\n!\u0002\\5ui2,WkQ*5\u0003-a\u0017\u000e\u001e;mKV\u001b5\u000b\u000e\u0011\u0002\u0017UtWo];bYV\u001b5\u000bN\u0001\rk:,8/^1m+\u000e\u001bF\u0007I\u0001\tE&<W\u000b\u0016$2m\u0005I!-[4V)\u001a\u000bd\u0007I\u0001\fY&$H\u000f\\3V)\u001a\u000bd'\u0001\u0007mSR$H.Z+U\rF2\u0004%\u0001\u0003vi\u001aD\u0014!B;uMb\u0002\u0013a\u00023fM\u0006,H\u000e^\u0001\tI\u00164\u0017-\u001e7uA\u00051\"/Z1e\u000b:\u001cw\u000eZ5oO\u001a\u0013x.\\*ue\u0016\fW\u000e\u0006\u00022\u0017\")A\n\u0006a\u0001\u001b\u0006\u0011\u0011N\u001c\t\u0003\u001dNk\u0011a\u0014\u0006\u0003!F\u000b!![8\u000b\u0003I\u000bAA[1wC&\u0011Ak\u0014\u0002\f\u0013:\u0004X\u000f^*ue\u0016\fW\u000e"
)
public final class EncodingHeuristics {
   public static String readEncodingFromStream(final InputStream in) {
      return EncodingHeuristics$.MODULE$.readEncodingFromStream(in);
   }

   public static class EncodingNames$ {
      public static final EncodingNames$ MODULE$ = new EncodingNames$();
      private static final String bigUCS4 = "UCS-4";
      private static final String littleUCS4 = "UCS-4";
      private static final String unusualUCS4 = "UCS-4";
      private static final String bigUTF16 = "UTF-16BE";
      private static final String littleUTF16 = "UTF-16LE";
      private static final String utf8 = "UTF-8";
      private static final String default;

      static {
         default = MODULE$.utf8();
      }

      public String bigUCS4() {
         return bigUCS4;
      }

      public String littleUCS4() {
         return littleUCS4;
      }

      public String unusualUCS4() {
         return unusualUCS4;
      }

      public String bigUTF16() {
         return bigUTF16;
      }

      public String littleUTF16() {
         return littleUTF16;
      }

      public String utf8() {
         return utf8;
      }

      public String default() {
         return default;
      }
   }
}
