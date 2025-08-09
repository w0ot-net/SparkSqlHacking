package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.util.Utils$;
import scala.Function0;
import scala.Option;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005eaB\u000e\u001d!\u0003\r\ta\t\u0005\u0006U\u0001!\ta\u000b\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006_\u0001!\tA\u0010\u0005\u0007_\u00011\t\u0001\b\"\t\u000be\u0003A\u0011\u0001.\t\u000be\u0003A\u0011A0\t\u000b\t\u0004A\u0011A2\t\u000b\t\u0004A\u0011A3\t\u000b!\u0004A\u0011A5\t\u000b!\u0004A\u0011A6\t\u000b!\u0004A\u0011\u00018\t\u000bE\u0004A\u0011\u0001:\t\u000bE\u0004A\u0011\u0001;\t\u000b]\u0004A\u0011\u0001=\t\u000b]\u0004A\u0011\u0001>\t\u000bu\u0004A\u0011\u0001@\t\ru\u0004A\u0011AA\u0001\u0011\u001d\t9\u0001\u0001D\u0001\u0003\u0013Aq!a\u0005\u0001\r\u0003\t)\u0002C\u0004\u0002$\u0001!\t!!\n\t\u000f\u0005E\u0002\u0001\"\u0001\u00024!9\u0011\u0011\b\u0001\u0005\u0002\u0005m\u0002bBA$\u0001\u0011\u0005\u0011\u0011\n\u0005\b\u0003+\u0002a\u0011AA,\u0011\u001d\t)\u0006\u0001C\u0001\u00037Bq!!\u001b\u0001\t#\tYGA\tSK\u0006$wJ\u001c7z'B\f'o[\"p]\u001aT!!\b\u0010\u0002\u000bM\u0004\u0018M]6\u000b\u0005}\u0001\u0013AB1qC\u000eDWMC\u0001\"\u0003\ry'oZ\u0002\u0001'\t\u0001A\u0005\u0005\u0002&Q5\taEC\u0001(\u0003\u0015\u00198-\u00197b\u0013\tIcE\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00031\u0002\"!J\u0017\n\u000592#\u0001B+oSR\f1aZ3u)\t\tD\b\u0005\u00023s9\u00111g\u000e\t\u0003i\u0019j\u0011!\u000e\u0006\u0003m\t\na\u0001\u0010:p_Rt\u0014B\u0001\u001d'\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005a2\u0003\"B\u001f\u0003\u0001\u0004\t\u0014aA6fsR\u0019\u0011g\u0010!\t\u000bu\u001a\u0001\u0019A\u0019\t\u000b\u0005\u001b\u0001\u0019A\u0019\u0002\u0019\u0011,g-Y;miZ\u000bG.^3\u0016\u0005\r3EC\u0001#P!\t)e\t\u0004\u0001\u0005\u000b\u001d#!\u0019\u0001%\u0003\u0003Q\u000b\"!\u0013'\u0011\u0005\u0015R\u0015BA&'\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!J'\n\u000593#aA!os\")\u0001\u000b\u0002a\u0001#\u0006)QM\u001c;ssB\u0019!k\u0016#\u000e\u0003MS!\u0001V+\u0002\r\r|gNZ5h\u0015\t1F$\u0001\u0005j]R,'O\\1m\u0013\tA6KA\u0006D_:4\u0017nZ#oiJL\u0018\u0001E4fiRKW.Z!t'\u0016\u001cwN\u001c3t)\tYf\f\u0005\u0002&9&\u0011QL\n\u0002\u0005\u0019>tw\rC\u0003>\u000b\u0001\u0007\u0011\u0007F\u0002\\A\u0006DQ!\u0010\u0004A\u0002EBQ!\u0011\u0004A\u0002E\n1bZ3u)&lW-Q:NgR\u00111\f\u001a\u0005\u0006{\u001d\u0001\r!\r\u000b\u00047\u001a<\u0007\"B\u001f\t\u0001\u0004\t\u0004\"B!\t\u0001\u0004\t\u0014AD4fiNK'0Z!t\u0005f$Xm\u001d\u000b\u00037*DQ!P\u0005A\u0002E\"2a\u00177n\u0011\u0015i$\u00021\u00012\u0011\u0015\t%\u00021\u00012)\rYv\u000e\u001d\u0005\u0006{-\u0001\r!\r\u0005\u0006\u0003.\u0001\raW\u0001\fO\u0016$8+\u001b>f\u0003N\\%\r\u0006\u0002\\g\")Q\b\u0004a\u0001cQ\u00191,\u001e<\t\u000buj\u0001\u0019A\u0019\t\u000b\u0005k\u0001\u0019A\u0019\u0002\u0017\u001d,GoU5{K\u0006\u001bXJ\u0019\u000b\u00037fDQ!\u0010\bA\u0002E\"2aW>}\u0011\u0015it\u00021\u00012\u0011\u0015\tu\u00021\u00012\u0003-9W\r^*ju\u0016\f5o\u00122\u0015\u0005m{\b\"B\u001f\u0011\u0001\u0004\tD#B.\u0002\u0004\u0005\u0015\u0001\"B\u001f\u0012\u0001\u0004\t\u0004\"B!\u0012\u0001\u0004\t\u0014!C4fi>\u0003H/[8o)\u0011\tY!!\u0005\u0011\t\u0015\ni!M\u0005\u0004\u0003\u001f1#AB(qi&|g\u000eC\u0003>%\u0001\u0007\u0011'\u0001\u0004hKR\fE\u000e\\\u000b\u0003\u0003/\u0001R!JA\r\u0003;I1!a\u0007'\u0005\u0015\t%O]1z!\u0015)\u0013qD\u00192\u0013\r\t\tC\n\u0002\u0007)V\u0004H.\u001a\u001a\u0002\r\u001d,G/\u00138u)\u0019\t9#!\f\u00020A\u0019Q%!\u000b\n\u0007\u0005-bEA\u0002J]RDQ!\u0010\u000bA\u0002EBa!\u0011\u000bA\u0002\u0005\u001d\u0012aB4fi2{gn\u001a\u000b\u00067\u0006U\u0012q\u0007\u0005\u0006{U\u0001\r!\r\u0005\u0006\u0003V\u0001\raW\u0001\nO\u0016$Hi\\;cY\u0016$b!!\u0010\u0002D\u0005\u0015\u0003cA\u0013\u0002@%\u0019\u0011\u0011\t\u0014\u0003\r\u0011{WO\u00197f\u0011\u0015id\u00031\u00012\u0011\u0019\te\u00031\u0001\u0002>\u0005Qq-\u001a;C_>dW-\u00198\u0015\r\u0005-\u0013\u0011KA*!\r)\u0013QJ\u0005\u0004\u0003\u001f2#a\u0002\"p_2,\u0017M\u001c\u0005\u0006{]\u0001\r!\r\u0005\u0007\u0003^\u0001\r!a\u0013\u0002\u0011\r|g\u000e^1j]N$B!a\u0013\u0002Z!)Q\b\u0007a\u0001cQ!\u00111JA/\u0011\u0019\u0001\u0016\u00041\u0001\u0002`A\"\u0011\u0011MA3!\u0011\u0011v+a\u0019\u0011\u0007\u0015\u000b)\u0007B\u0006\u0002h\u0005u\u0013\u0011!A\u0001\u0006\u0003A%aA0%c\u0005\t2-\u0019;dQ&cG.Z4bYZ\u000bG.^3\u0016\t\u00055\u00141\u000f\u000b\u0005\u0003_\ny\b\u0006\u0003\u0002r\u0005U\u0004cA#\u0002t\u0011)qI\u0007b\u0001\u0011\"A\u0011q\u000f\u000e\u0005\u0002\u0004\tI(\u0001\u0005hKR4\u0016\r\\;f!\u0015)\u00131PA9\u0013\r\tiH\n\u0002\ty\tLh.Y7f}!)QH\u0007a\u0001c\u0001"
)
public interface ReadOnlySparkConf {
   // $FF: synthetic method
   static String get$(final ReadOnlySparkConf $this, final String key) {
      return $this.get(key);
   }

   default String get(final String key) {
      return (String)this.getOption(key).getOrElse(() -> {
         throw new NoSuchElementException(key);
      });
   }

   // $FF: synthetic method
   static String get$(final ReadOnlySparkConf $this, final String key, final String defaultValue) {
      return $this.get(key, defaultValue);
   }

   default String get(final String key, final String defaultValue) {
      return (String)this.getOption(key).getOrElse(() -> defaultValue);
   }

   Object get(final ConfigEntry entry);

   // $FF: synthetic method
   static long getTimeAsSeconds$(final ReadOnlySparkConf $this, final String key) {
      return $this.getTimeAsSeconds(key);
   }

   default long getTimeAsSeconds(final String key) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.timeStringAsSeconds(this.get(key))));
   }

   // $FF: synthetic method
   static long getTimeAsSeconds$(final ReadOnlySparkConf $this, final String key, final String defaultValue) {
      return $this.getTimeAsSeconds(key, defaultValue);
   }

   default long getTimeAsSeconds(final String key, final String defaultValue) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.timeStringAsSeconds(this.get(key, defaultValue))));
   }

   // $FF: synthetic method
   static long getTimeAsMs$(final ReadOnlySparkConf $this, final String key) {
      return $this.getTimeAsMs(key);
   }

   default long getTimeAsMs(final String key) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.timeStringAsMs(this.get(key))));
   }

   // $FF: synthetic method
   static long getTimeAsMs$(final ReadOnlySparkConf $this, final String key, final String defaultValue) {
      return $this.getTimeAsMs(key, defaultValue);
   }

   default long getTimeAsMs(final String key, final String defaultValue) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.timeStringAsMs(this.get(key, defaultValue))));
   }

   // $FF: synthetic method
   static long getSizeAsBytes$(final ReadOnlySparkConf $this, final String key) {
      return $this.getSizeAsBytes(key);
   }

   default long getSizeAsBytes(final String key) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsBytes(this.get(key))));
   }

   // $FF: synthetic method
   static long getSizeAsBytes$(final ReadOnlySparkConf $this, final String key, final String defaultValue) {
      return $this.getSizeAsBytes(key, defaultValue);
   }

   default long getSizeAsBytes(final String key, final String defaultValue) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsBytes(this.get(key, defaultValue))));
   }

   // $FF: synthetic method
   static long getSizeAsBytes$(final ReadOnlySparkConf $this, final String key, final long defaultValue) {
      return $this.getSizeAsBytes(key, defaultValue);
   }

   default long getSizeAsBytes(final String key, final long defaultValue) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsBytes(this.get(key, defaultValue + "B"))));
   }

   // $FF: synthetic method
   static long getSizeAsKb$(final ReadOnlySparkConf $this, final String key) {
      return $this.getSizeAsKb(key);
   }

   default long getSizeAsKb(final String key) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsKb(this.get(key))));
   }

   // $FF: synthetic method
   static long getSizeAsKb$(final ReadOnlySparkConf $this, final String key, final String defaultValue) {
      return $this.getSizeAsKb(key, defaultValue);
   }

   default long getSizeAsKb(final String key, final String defaultValue) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsKb(this.get(key, defaultValue))));
   }

   // $FF: synthetic method
   static long getSizeAsMb$(final ReadOnlySparkConf $this, final String key) {
      return $this.getSizeAsMb(key);
   }

   default long getSizeAsMb(final String key) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsMb(this.get(key))));
   }

   // $FF: synthetic method
   static long getSizeAsMb$(final ReadOnlySparkConf $this, final String key, final String defaultValue) {
      return $this.getSizeAsMb(key, defaultValue);
   }

   default long getSizeAsMb(final String key, final String defaultValue) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsMb(this.get(key, defaultValue))));
   }

   // $FF: synthetic method
   static long getSizeAsGb$(final ReadOnlySparkConf $this, final String key) {
      return $this.getSizeAsGb(key);
   }

   default long getSizeAsGb(final String key) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsGb(this.get(key))));
   }

   // $FF: synthetic method
   static long getSizeAsGb$(final ReadOnlySparkConf $this, final String key, final String defaultValue) {
      return $this.getSizeAsGb(key, defaultValue);
   }

   default long getSizeAsGb(final String key, final String defaultValue) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> Utils$.MODULE$.byteStringAsGb(this.get(key, defaultValue))));
   }

   Option getOption(final String key);

   Tuple2[] getAll();

   // $FF: synthetic method
   static int getInt$(final ReadOnlySparkConf $this, final String key, final int defaultValue) {
      return $this.getInt(key, defaultValue);
   }

   default int getInt(final String key, final int defaultValue) {
      return BoxesRunTime.unboxToInt(this.catchIllegalValue(key, (JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(this.getOption(key).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$getInt$2(x$1))).getOrElse((JFunction0.mcI.sp)() -> defaultValue))));
   }

   // $FF: synthetic method
   static long getLong$(final ReadOnlySparkConf $this, final String key, final long defaultValue) {
      return $this.getLong(key, defaultValue);
   }

   default long getLong(final String key, final long defaultValue) {
      return BoxesRunTime.unboxToLong(this.catchIllegalValue(key, (JFunction0.mcJ.sp)() -> BoxesRunTime.unboxToLong(this.getOption(key).map((x$2) -> BoxesRunTime.boxToLong($anonfun$getLong$2(x$2))).getOrElse((JFunction0.mcJ.sp)() -> defaultValue))));
   }

   // $FF: synthetic method
   static double getDouble$(final ReadOnlySparkConf $this, final String key, final double defaultValue) {
      return $this.getDouble(key, defaultValue);
   }

   default double getDouble(final String key, final double defaultValue) {
      return BoxesRunTime.unboxToDouble(this.catchIllegalValue(key, (JFunction0.mcD.sp)() -> BoxesRunTime.unboxToDouble(this.getOption(key).map((x$3) -> BoxesRunTime.boxToDouble($anonfun$getDouble$2(x$3))).getOrElse((JFunction0.mcD.sp)() -> defaultValue))));
   }

   // $FF: synthetic method
   static boolean getBoolean$(final ReadOnlySparkConf $this, final String key, final boolean defaultValue) {
      return $this.getBoolean(key, defaultValue);
   }

   default boolean getBoolean(final String key, final boolean defaultValue) {
      return BoxesRunTime.unboxToBoolean(this.catchIllegalValue(key, (JFunction0.mcZ.sp)() -> BoxesRunTime.unboxToBoolean(this.getOption(key).map((x$4) -> BoxesRunTime.boxToBoolean($anonfun$getBoolean$2(x$4))).getOrElse((JFunction0.mcZ.sp)() -> defaultValue))));
   }

   boolean contains(final String key);

   // $FF: synthetic method
   static boolean contains$(final ReadOnlySparkConf $this, final ConfigEntry entry) {
      return $this.contains(entry);
   }

   default boolean contains(final ConfigEntry entry) {
      return this.contains(entry.key());
   }

   // $FF: synthetic method
   static Object catchIllegalValue$(final ReadOnlySparkConf $this, final String key, final Function0 getValue) {
      return $this.catchIllegalValue(key, getValue);
   }

   default Object catchIllegalValue(final String key, final Function0 getValue) {
      try {
         return getValue.apply();
      } catch (NumberFormatException var5) {
         throw (new NumberFormatException("Illegal value for config key " + key + ": " + var5.getMessage())).initCause(var5);
      } catch (IllegalArgumentException var6) {
         throw new IllegalArgumentException("Illegal value for config key " + key + ": " + var6.getMessage(), var6);
      }
   }

   // $FF: synthetic method
   static int $anonfun$getInt$2(final String x$1) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   static long $anonfun$getLong$2(final String x$2) {
      return .MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   static double $anonfun$getDouble$2(final String x$3) {
      return .MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(x$3));
   }

   // $FF: synthetic method
   static boolean $anonfun$getBoolean$2(final String x$4) {
      return .MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   static void $init$(final ReadOnlySparkConf $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
