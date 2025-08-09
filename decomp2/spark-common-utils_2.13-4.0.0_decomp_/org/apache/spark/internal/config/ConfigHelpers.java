package org.apache.spark.internal.config;

import java.util.concurrent.TimeUnit;
import org.apache.spark.network.util.ByteUnit;
import scala.Function1;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005er!\u0002\u0007\u000e\u0011\u0013Ab!\u0002\u000e\u000e\u0011\u0013Y\u0002\"\u0002\u0012\u0002\t\u0003\u0019\u0003\"\u0002\u0013\u0002\t\u0003)\u0003\"\u0002%\u0002\t\u0003I\u0005\"B(\u0002\t\u0003\u0001\u0006\"B1\u0002\t\u0003\u0011\u0007\"\u00027\u0002\t\u0003i\u0007\"\u0002@\u0002\t\u0003y\bbBA\u0003\u0003\u0011\u0005\u0011q\u0001\u0005\b\u00037\tA\u0011AA\u000f\u0011\u001d\t\u0019#\u0001C\u0001\u0003K\tQbQ8oM&<\u0007*\u001a7qKJ\u001c(B\u0001\b\u0010\u0003\u0019\u0019wN\u001c4jO*\u0011\u0001#E\u0001\tS:$XM\u001d8bY*\u0011!cE\u0001\u0006gB\f'o\u001b\u0006\u0003)U\ta!\u00199bG\",'\"\u0001\f\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005e\tQ\"A\u0007\u0003\u001b\r{gNZ5h\u0011\u0016d\u0007/\u001a:t'\t\tA\u0004\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\t\u0001\u0002^8Ok6\u0014WM]\u000b\u0003M%\"Ra\n\u001a@\t\u001a\u0003\"\u0001K\u0015\r\u0001\u0011)!f\u0001b\u0001W\t\tA+\u0005\u0002-_A\u0011Q$L\u0005\u0003]y\u0011qAT8uQ&tw\r\u0005\u0002\u001ea%\u0011\u0011G\b\u0002\u0004\u0003:L\b\"B\u001a\u0004\u0001\u0004!\u0014!A:\u0011\u0005UbdB\u0001\u001c;!\t9d$D\u00019\u0015\tIt#\u0001\u0004=e>|GOP\u0005\u0003wy\ta\u0001\u0015:fI\u00164\u0017BA\u001f?\u0005\u0019\u0019FO]5oO*\u00111H\b\u0005\u0006\u0001\u000e\u0001\r!Q\u0001\nG>tg/\u001a:uKJ\u0004B!\b\"5O%\u00111I\b\u0002\n\rVt7\r^5p]FBQ!R\u0002A\u0002Q\n1a[3z\u0011\u001595\u00011\u00015\u0003)\u0019wN\u001c4jORK\b/Z\u0001\ni>\u0014un\u001c7fC:$2AS'O!\ti2*\u0003\u0002M=\t9!i\\8mK\u0006t\u0007\"B\u001a\u0005\u0001\u0004!\u0004\"B#\u0005\u0001\u0004!\u0014aC:ue&tw\rV8TKF,\"!\u0015/\u0015\u0007Ikv\fE\u0002T1ns!\u0001\u0016,\u000f\u0005]*\u0016\"A\u0010\n\u0005]s\u0012a\u00029bG.\fw-Z\u0005\u00033j\u00131aU3r\u0015\t9f\u0004\u0005\u0002)9\u0012)!&\u0002b\u0001W!)a,\u0002a\u0001i\u0005\u00191\u000f\u001e:\t\u000b\u0001+\u0001\u0019\u00011\u0011\tu\u0011EgW\u0001\fg\u0016\fHk\\*ue&tw-\u0006\u0002dQR\u0019A\u0007Z5\t\u000b\u00154\u0001\u0019\u00014\u0002\u0003Y\u00042a\u0015-h!\tA\u0003\u000eB\u0003+\r\t\u00071\u0006C\u0003k\r\u0001\u00071.A\btiJLgnZ\"p]Z,'\u000f^3s!\u0011i\"i\u001a\u001b\u0002\u001dQLW.\u001a$s_6\u001cFO]5oOR\u0019a.\u001d:\u0011\u0005uy\u0017B\u00019\u001f\u0005\u0011auN\\4\t\u000by;\u0001\u0019\u0001\u001b\t\u000bM<\u0001\u0019\u0001;\u0002\tUt\u0017\u000e\u001e\t\u0003krl\u0011A\u001e\u0006\u0003ob\f!bY8oGV\u0014(/\u001a8u\u0015\tI(0\u0001\u0003vi&d'\"A>\u0002\t)\fg/Y\u0005\u0003{Z\u0014\u0001\u0002V5nKVs\u0017\u000e^\u0001\ri&lW\rV8TiJLgn\u001a\u000b\u0006i\u0005\u0005\u00111\u0001\u0005\u0006K\"\u0001\rA\u001c\u0005\u0006g\"\u0001\r\u0001^\u0001\u000fEf$XM\u0012:p[N#(/\u001b8h)\u0015q\u0017\u0011BA\u0006\u0011\u0015q\u0016\u00021\u00015\u0011\u0019\u0019\u0018\u00021\u0001\u0002\u000eA!\u0011qBA\f\u001b\t\t\tBC\u0002z\u0003'Q1!!\u0006\u0012\u0003\u001dqW\r^<pe.LA!!\u0007\u0002\u0012\tA!)\u001f;f+:LG/\u0001\u0007csR,Gk\\*ue&tw\rF\u00035\u0003?\t\t\u0003C\u0003f\u0015\u0001\u0007a\u000e\u0003\u0004t\u0015\u0001\u0007\u0011QB\u0001\u0010e\u0016<W\r\u001f$s_6\u001cFO]5oOR1\u0011qEA\u001b\u0003o\u0001B!!\u000b\u000225\u0011\u00111\u0006\u0006\u0005\u0003[\ty#\u0001\u0005nCR\u001c\u0007.\u001b8h\u0015\tIh$\u0003\u0003\u00024\u0005-\"!\u0002*fO\u0016D\b\"\u00020\f\u0001\u0004!\u0004\"B#\f\u0001\u0004!\u0004"
)
public final class ConfigHelpers {
   public static Regex regexFromString(final String str, final String key) {
      return ConfigHelpers$.MODULE$.regexFromString(str, key);
   }

   public static String byteToString(final long v, final ByteUnit unit) {
      return ConfigHelpers$.MODULE$.byteToString(v, unit);
   }

   public static long byteFromString(final String str, final ByteUnit unit) {
      return ConfigHelpers$.MODULE$.byteFromString(str, unit);
   }

   public static String timeToString(final long v, final TimeUnit unit) {
      return ConfigHelpers$.MODULE$.timeToString(v, unit);
   }

   public static long timeFromString(final String str, final TimeUnit unit) {
      return ConfigHelpers$.MODULE$.timeFromString(str, unit);
   }

   public static String seqToString(final Seq v, final Function1 stringConverter) {
      return ConfigHelpers$.MODULE$.seqToString(v, stringConverter);
   }

   public static Seq stringToSeq(final String str, final Function1 converter) {
      return ConfigHelpers$.MODULE$.stringToSeq(str, converter);
   }

   public static boolean toBoolean(final String s, final String key) {
      return ConfigHelpers$.MODULE$.toBoolean(s, key);
   }

   public static Object toNumber(final String s, final Function1 converter, final String key, final String configType) {
      return ConfigHelpers$.MODULE$.toNumber(s, converter, key, configType);
   }
}
