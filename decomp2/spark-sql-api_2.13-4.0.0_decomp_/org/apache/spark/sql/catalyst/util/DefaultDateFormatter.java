package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Enumeration;
import scala.PartialFunction;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005!3AAB\u0004\u0001)!A\u0011\u0004\u0001B\u0001B\u0003%!\u0004\u0003\u0005\"\u0001\t\u0005\t\u0015!\u0003#\u0011!I\u0003A!A!\u0002\u0013Q\u0003\"\u0002\u0019\u0001\t\u0003\t\u0004\"\u0002\u001c\u0001\t\u0003:$\u0001\u0006#fM\u0006,H\u000e\u001e#bi\u00164uN]7biR,'O\u0003\u0002\t\u0013\u0005!Q\u000f^5m\u0015\tQ1\"\u0001\u0005dCR\fG._:u\u0015\taQ\"A\u0002tc2T!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0003\u0005\u0002\u0017/5\tq!\u0003\u0002\u0019\u000f\t!\u0012j]89mA\nD)\u0019;f\r>\u0014X.\u0019;uKJ\fa\u0001\\8dC2,\u0007CA\u000e \u001b\u0005a\"B\u0001\u0005\u001e\u0015\u0005q\u0012\u0001\u00026bm\u0006L!\u0001\t\u000f\u0003\r1{7-\u00197f\u00031aWmZ1ds\u001a{'/\\1u!\t\u0019cE\u0004\u0002\u0017I%\u0011QeB\u0001\u0012\u0019\u0016<\u0017mY=ECR,gi\u001c:nCR\u001c\u0018BA\u0014)\u0005AaUmZ1ds\u0012\u000bG/\u001a$pe6\fGO\u0003\u0002&\u000f\u0005I\u0011n\u001d)beNLgn\u001a\t\u0003W9j\u0011\u0001\f\u0006\u0002[\u0005)1oY1mC&\u0011q\u0006\f\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q!!g\r\u001b6!\t1\u0002\u0001C\u0003\u001a\t\u0001\u0007!\u0004C\u0003\"\t\u0001\u0007!\u0005C\u0003*\t\u0001\u0007!&A\u0003qCJ\u001cX\r\u0006\u00029wA\u00111&O\u0005\u0003u1\u00121!\u00138u\u0011\u0015aT\u00011\u0001>\u0003\u0005\u0019\bC\u0001 F\u001d\ty4\t\u0005\u0002AY5\t\u0011I\u0003\u0002C'\u00051AH]8pizJ!\u0001\u0012\u0017\u0002\rA\u0013X\rZ3g\u0013\t1uI\u0001\u0004TiJLgn\u001a\u0006\u0003\t2\u0002"
)
public class DefaultDateFormatter extends Iso8601DateFormatter {
   public int parse(final String s) {
      int var10000;
      try {
         var10000 = SparkDateTimeUtils$.MODULE$.stringToDateAnsi(UTF8String.fromString(s), SparkDateTimeUtils$.MODULE$.stringToDateAnsi$default$2());
      } catch (Throwable var4) {
         PartialFunction catchExpr$4 = this.checkParsedDiff(s, (sx) -> BoxesRunTime.boxToInteger($anonfun$parse$2(this, sx)));
         if (!catchExpr$4.isDefinedAt(var4)) {
            throw var4;
         }

         var10000 = BoxesRunTime.unboxToInt(catchExpr$4.apply(var4));
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final int $anonfun$parse$2(final DefaultDateFormatter $this, final String s) {
      return $this.legacyFormatter().parse(s);
   }

   public DefaultDateFormatter(final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      super(DateFormatter$.MODULE$.defaultPattern(), locale, legacyFormat, isParsing);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
