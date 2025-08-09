package org.apache.spark.status.protobuf;

import java.util.Map;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-<aa\u0002\u0005\t\u0002!\u0011bA\u0002\u000b\t\u0011\u0003AQ\u0003C\u0003\u001d\u0003\u0011\u0005a\u0004C\u0003 \u0003\u0011\u0005\u0001\u0005C\u0003;\u0003\u0011\u00051\bC\u0003R\u0003\u0011\u0005!\u000bC\u0003W\u0003\u0011\u0005q+A\u0003Vi&d7O\u0003\u0002\n\u0015\u0005A\u0001O]8u_\n,hM\u0003\u0002\f\u0019\u000511\u000f^1ukNT!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'o\u001a\t\u0003'\u0005i\u0011\u0001\u0003\u0002\u0006+RLGn]\n\u0003\u0003Y\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003I\t1bZ3u\u001fB$\u0018n\u001c8bYV\u0011\u0011e\n\u000b\u0004EA*\u0004cA\f$K%\u0011A\u0005\u0007\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0019:C\u0002\u0001\u0003\u0006Q\r\u0011\r!\u000b\u0002\u0002)F\u0011!&\f\t\u0003/-J!\u0001\f\r\u0003\u000f9{G\u000f[5oOB\u0011qCL\u0005\u0003_a\u00111!\u00118z\u0011\u0015\t4\u00011\u00013\u0003%\u0019wN\u001c3ji&|g\u000e\u0005\u0002\u0018g%\u0011A\u0007\u0007\u0002\b\u0005>|G.Z1o\u0011\u001514\u00011\u00018\u0003\u0019\u0011Xm];miB\u0019q\u0003O\u0013\n\u0005eB\"!\u0003$v]\u000e$\u0018n\u001c81\u00039\u0019X\r^*ue&twMR5fY\u0012$2\u0001P M!\t9R(\u0003\u0002?1\t!QK\\5u\u0011\u0015\u0001E\u00011\u0001B\u0003\u0015Ig\u000e];u!\t\u0011\u0015J\u0004\u0002D\u000fB\u0011A\tG\u0007\u0002\u000b*\u0011a)H\u0001\u0007yI|w\u000e\u001e \n\u0005!C\u0012A\u0002)sK\u0012,g-\u0003\u0002K\u0017\n11\u000b\u001e:j]\u001eT!\u0001\u0013\r\t\u000b5#\u0001\u0019\u0001(\u0002\u0003\u0019\u0004BaF(B[%\u0011\u0001\u000b\u0007\u0002\n\rVt7\r^5p]F\nabZ3u'R\u0014\u0018N\\4GS\u0016dG\rF\u0002B'RCQ!M\u0003A\u0002IBQAN\u0003A\u0002U\u00032a\u0006\u001dB\u00031\u0019X\r\u001e&NCB4\u0015.\u001a7e+\rA6M\u001a\u000b\u0004yeC\u0007\"\u0002!\u0007\u0001\u0004Q\u0006\u0003B.aE\u0016l\u0011\u0001\u0018\u0006\u0003;z\u000bA!\u001e;jY*\tq,\u0001\u0003kCZ\f\u0017BA1]\u0005\ri\u0015\r\u001d\t\u0003M\r$Q\u0001\u001a\u0004C\u0002%\u0012\u0011a\u0013\t\u0003M\u0019$Qa\u001a\u0004C\u0002%\u0012\u0011A\u0016\u0005\u0006S\u001a\u0001\rA[\u0001\u000baV$\u0018\t\u001c7Gk:\u001c\u0007\u0003B\fP56\u0002"
)
public final class Utils {
   public static void setJMapField(final Map input, final Function1 putAllFunc) {
      Utils$.MODULE$.setJMapField(input, putAllFunc);
   }

   public static String getStringField(final boolean condition, final Function0 result) {
      return Utils$.MODULE$.getStringField(condition, result);
   }

   public static void setStringField(final String input, final Function1 f) {
      Utils$.MODULE$.setStringField(input, f);
   }

   public static Option getOptional(final boolean condition, final Function0 result) {
      return Utils$.MODULE$.getOptional(condition, result);
   }
}
