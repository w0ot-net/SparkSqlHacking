package org.json4s.jackson;

import org.json4s.AsJsonInput;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u<Qa\u0003\u0007\t\u0002M1Q!\u0006\u0007\t\u0002YAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0002\u0001BqAP\u0001\u0012\u0002\u0013\u0005q\bC\u0003M\u0003\u0011\u0005Q\nC\u0004[\u0003E\u0005I\u0011A.\t\u000bu\u000bA\u0011\u00010\t\u000f\u001d\f\u0011\u0013!C\u0001Q\")1.\u0001C\u0001Y\")!0\u0001C\u0001w\u00069\u0001/Y2lC\u001e,'BA\u0007\u000f\u0003\u001dQ\u0017mY6t_:T!a\u0004\t\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005\t\u0012aA8sO\u000e\u0001\u0001C\u0001\u000b\u0002\u001b\u0005a!a\u00029bG.\fw-Z\n\u0003\u0003]\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0014\u0003%\u0001\u0018M]:f\u0015N|g.\u0006\u0002\"]Q\u0019!eN\u001d\u0015\u0005\r:\u0003C\u0001\u0013&\u001b\u0005q\u0011B\u0001\u0014\u000f\u0005\u0019Qe+\u00197vK\"9\u0001fAA\u0001\u0002\bI\u0013AC3wS\u0012,gnY3%cA\u0019AE\u000b\u0017\n\u0005-r!aC!t\u0015N|g.\u00138qkR\u0004\"!\f\u0018\r\u0001\u0011)qf\u0001b\u0001a\t\t\u0011)\u0005\u00022iA\u0011\u0001DM\u0005\u0003ge\u0011qAT8uQ&tw\r\u0005\u0002\u0019k%\u0011a'\u0007\u0002\u0004\u0003:L\b\"\u0002\u001d\u0004\u0001\u0004a\u0013AA5o\u0011\u001dQ4\u0001%AA\u0002m\na#^:f\u0005&<G)Z2j[\u0006dgi\u001c:E_V\u0014G.\u001a\t\u00031qJ!!P\r\u0003\u000f\t{w\u000e\\3b]\u0006\u0019\u0002/\u0019:tK*\u001bxN\u001c\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0001iS\u000b\u0002\u0003*\u00121HQ\u0016\u0002\u0007B\u0011A)S\u0007\u0002\u000b*\u0011aiR\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001S\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002K\u000b\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b=\"!\u0019\u0001\u0019\u0002\u0019A\f'o]3Kg>tw\n\u001d;\u0016\u00059;FcA(Y3R\u0011\u0001k\u0015\t\u00041E\u001b\u0013B\u0001*\u001a\u0005\u0019y\u0005\u000f^5p]\"9A+BA\u0001\u0002\b)\u0016AC3wS\u0012,gnY3%eA\u0019AE\u000b,\u0011\u00055:F!B\u0018\u0006\u0005\u0004\u0001\u0004\"\u0002\u001d\u0006\u0001\u00041\u0006b\u0002\u001e\u0006!\u0003\u0005\raO\u0001\u0017a\u0006\u00148/\u001a&t_:|\u0005\u000f\u001e\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0001\t\u0018\u0003\u0006_\u0019\u0011\r\u0001M\u0001\re\u0016tG-\u001a:K-\u0006dW/\u001a\u000b\u0003?\u0016$\"a\t1\t\u000f\u0005<\u0001\u0013!a\u0002E\u00069am\u001c:nCR\u001c\bC\u0001\u0013d\u0013\t!gBA\u0004G_Jl\u0017\r^:\t\u000b\u0019<\u0001\u0019A\u0012\u0002\u000bY\fG.^3\u0002-I,g\u000eZ3s\u0015Z\u000bG.^3%I\u00164\u0017-\u001e7uII\"\"!\u001b6+\u0005\t\u0014\u0005\"\u00024\t\u0001\u0004\u0019\u0013aC2p[B\f7\r\u001e&t_:$\"!\u001c=\u0011\u00059,hBA8t!\t\u0001\u0018$D\u0001r\u0015\t\u0011(#\u0001\u0004=e>|GOP\u0005\u0003if\ta\u0001\u0015:fI\u00164\u0017B\u0001<x\u0005\u0019\u0019FO]5oO*\u0011A/\u0007\u0005\u0006s&\u0001\raI\u0001\u0002I\u0006Q\u0001O]3uifT5o\u001c8\u0015\u00055d\b\"B=\u000b\u0001\u0004\u0019\u0003"
)
public final class package {
   public static String prettyJson(final JValue d) {
      return package$.MODULE$.prettyJson(d);
   }

   public static String compactJson(final JValue d) {
      return package$.MODULE$.compactJson(d);
   }

   public static Formats renderJValue$default$2(final JValue value) {
      return package$.MODULE$.renderJValue$default$2(value);
   }

   public static JValue renderJValue(final JValue value, final Formats formats) {
      return package$.MODULE$.renderJValue(value, formats);
   }

   public static boolean parseJsonOpt$default$2() {
      return package$.MODULE$.parseJsonOpt$default$2();
   }

   public static Option parseJsonOpt(final Object in, final boolean useBigDecimalForDouble, final AsJsonInput evidence$2) {
      return package$.MODULE$.parseJsonOpt(in, useBigDecimalForDouble, evidence$2);
   }

   public static boolean parseJson$default$2() {
      return package$.MODULE$.parseJson$default$2();
   }

   public static JValue parseJson(final Object in, final boolean useBigDecimalForDouble, final AsJsonInput evidence$1) {
      return package$.MODULE$.parseJson(in, useBigDecimalForDouble, evidence$1);
   }
}
