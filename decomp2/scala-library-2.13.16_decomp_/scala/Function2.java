package scala;

import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005i4qAB\u0004\u0011\u0002\u0007\u0005!\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0019\u0005a\u0003C\u0003Z\u0001\u0011\u0005!\fC\u0003g\u0001\u0011\u0005q\rC\u0003n\u0001\u0011\u0005cNA\u0005Gk:\u001cG/[8oe)\t\u0001\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\t-1E+G\n\u0003\u00011\u0001\"!\u0004\b\u000e\u0003\u001dI!aD\u0004\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0003\u0005\u0002\u000e'%\u0011Ac\u0002\u0002\u0005+:LG/A\u0003baBd\u0017\u0010F\u0002\u0018\u0007F\u0003\"\u0001G\r\r\u0001\u0011I!\u0004\u0001Q\u0001\u0002\u0013\u0015\ra\u0007\u0002\u0002%F\u0011Ad\b\t\u0003\u001buI!AH\u0004\u0003\u000f9{G\u000f[5oOB\u0011Q\u0002I\u0005\u0003C\u001d\u00111!\u00118zQ\rI2E\n\t\u0003\u001b\u0011J!!J\u0004\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G\u001d\u0002%)\u0011\t\u0004Q-rcBA\u0007*\u0013\tQs!A\u0007Ta\u0016\u001c\u0017.\u00197ju\u0006\u0014G.Z\u0005\u0003Y5\u0012Qa\u0012:pkBT!AK\u0004\u0011\u00115y\u0013\u0007N\u001c;{II!\u0001M\u0004\u0003\rQ+\b\u000f\\37!\ti!'\u0003\u00024\u000f\t\u0019\u0011J\u001c;\u0011\u00055)\u0014B\u0001\u001c\b\u0005\u0011auN\\4\u0011\u00055A\u0014BA\u001d\b\u0005\u00151En\\1u!\ti1(\u0003\u0002=\u000f\t1Ai\\;cY\u0016\u0004\"!\u0004 \n\u0005}:!a\u0002\"p_2,\u0017M\\\u0005\u0003\u00036\naAU3ukJt\u0017\u0007\u0002\u0013)S)BQ\u0001\u0012\u0002A\u0002\u0015\u000b!A^\u0019\u0011\u0005a1E!C$\u0001A\u0003\u0005\tR1\u0001\u001c\u0005\t!\u0016\u0007K\u0002GG%\u000bTa\t&O!>\u00032\u0001K\u0016L!\u0015iA*\r\u001b;\u0013\tiuA\u0001\u0004UkBdWmM\u0005\u0003\u001f6\nA!\u0011:hgF\"A\u0005K\u0015+\u0011\u0015\u0011&\u00011\u0001T\u0003\t1(\u0007\u0005\u0002\u0019)\u0012IQ\u000b\u0001Q\u0001\u0002#\u0015\ra\u0007\u0002\u0003)JB3\u0001V\u0012Xc\u0015\u0019#J\u0014-Pc\u0011!\u0003&\u000b\u0016\u0002\u000f\r,(O]5fIV\t1\f\u0005\u0003\u000e9\u0016s\u0016BA/\b\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0003\u000e9N;\u0002FA\u0002a!\t\tG-D\u0001c\u0015\t\u0019w!\u0001\u0006b]:|G/\u0019;j_:L!!\u001a2\u0003\u001bUt7\u000f]3dS\u0006d\u0017N_3e\u0003\u0019!X\u000f\u001d7fIV\t\u0001\u000e\u0005\u0003\u000e9&<\u0002\u0003B\u0007k\u000bNK!a[\u0004\u0003\rQ+\b\u000f\\33Q\t!\u0001-\u0001\u0005u_N#(/\u001b8h)\u0005y\u0007C\u00019x\u001d\t\tX\u000f\u0005\u0002s\u000f5\t1O\u0003\u0002u\u0013\u00051AH]8pizJ!A^\u0004\u0002\rA\u0013X\rZ3g\u0013\tA\u0018P\u0001\u0004TiJLgn\u001a\u0006\u0003m\u001e\u0001"
)
public interface Function2 {
   Object apply(final Object v1, final Object v2);

   // $FF: synthetic method
   static Function1 curried$(final Function2 $this) {
      return $this.curried();
   }

   default Function1 curried() {
      return (x1) -> (x2) -> this.apply(x1, x2);
   }

   // $FF: synthetic method
   static Function1 tupled$(final Function2 $this) {
      return $this.tupled();
   }

   default Function1 tupled() {
      return (x0$1) -> {
         if (x0$1 != null) {
            Object x1 = x0$1._1();
            Object x2 = x0$1._2();
            return this.apply(x1, x2);
         } else {
            throw new MatchError((Object)null);
         }
      };
   }

   // $FF: synthetic method
   static String toString$(final Function2 $this) {
      return $this.toString();
   }

   default String toString() {
      return "<function2>";
   }

   // $FF: synthetic method
   static boolean apply$mcZDD$sp$(final Function2 $this, final double v1, final double v2) {
      return $this.apply$mcZDD$sp(v1, v2);
   }

   default boolean apply$mcZDD$sp(final double v1, final double v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDDD$sp$(final Function2 $this, final double v1, final double v2) {
      return $this.apply$mcDDD$sp(v1, v2);
   }

   default double apply$mcDDD$sp(final double v1, final double v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFDD$sp$(final Function2 $this, final double v1, final double v2) {
      return $this.apply$mcFDD$sp(v1, v2);
   }

   default float apply$mcFDD$sp(final double v1, final double v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIDD$sp$(final Function2 $this, final double v1, final double v2) {
      return $this.apply$mcIDD$sp(v1, v2);
   }

   default int apply$mcIDD$sp(final double v1, final double v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJDD$sp$(final Function2 $this, final double v1, final double v2) {
      return $this.apply$mcJDD$sp(v1, v2);
   }

   default long apply$mcJDD$sp(final double v1, final double v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVDD$sp$(final Function2 $this, final double v1, final double v2) {
      $this.apply$mcVDD$sp(v1, v2);
   }

   default void apply$mcVDD$sp(final double v1, final double v2) {
      this.apply(v1, v2);
   }

   // $FF: synthetic method
   static boolean apply$mcZDI$sp$(final Function2 $this, final double v1, final int v2) {
      return $this.apply$mcZDI$sp(v1, v2);
   }

   default boolean apply$mcZDI$sp(final double v1, final int v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDDI$sp$(final Function2 $this, final double v1, final int v2) {
      return $this.apply$mcDDI$sp(v1, v2);
   }

   default double apply$mcDDI$sp(final double v1, final int v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFDI$sp$(final Function2 $this, final double v1, final int v2) {
      return $this.apply$mcFDI$sp(v1, v2);
   }

   default float apply$mcFDI$sp(final double v1, final int v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIDI$sp$(final Function2 $this, final double v1, final int v2) {
      return $this.apply$mcIDI$sp(v1, v2);
   }

   default int apply$mcIDI$sp(final double v1, final int v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJDI$sp$(final Function2 $this, final double v1, final int v2) {
      return $this.apply$mcJDI$sp(v1, v2);
   }

   default long apply$mcJDI$sp(final double v1, final int v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVDI$sp$(final Function2 $this, final double v1, final int v2) {
      $this.apply$mcVDI$sp(v1, v2);
   }

   default void apply$mcVDI$sp(final double v1, final int v2) {
      this.apply(v1, v2);
   }

   // $FF: synthetic method
   static boolean apply$mcZDJ$sp$(final Function2 $this, final double v1, final long v2) {
      return $this.apply$mcZDJ$sp(v1, v2);
   }

   default boolean apply$mcZDJ$sp(final double v1, final long v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDDJ$sp$(final Function2 $this, final double v1, final long v2) {
      return $this.apply$mcDDJ$sp(v1, v2);
   }

   default double apply$mcDDJ$sp(final double v1, final long v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFDJ$sp$(final Function2 $this, final double v1, final long v2) {
      return $this.apply$mcFDJ$sp(v1, v2);
   }

   default float apply$mcFDJ$sp(final double v1, final long v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIDJ$sp$(final Function2 $this, final double v1, final long v2) {
      return $this.apply$mcIDJ$sp(v1, v2);
   }

   default int apply$mcIDJ$sp(final double v1, final long v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJDJ$sp$(final Function2 $this, final double v1, final long v2) {
      return $this.apply$mcJDJ$sp(v1, v2);
   }

   default long apply$mcJDJ$sp(final double v1, final long v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVDJ$sp$(final Function2 $this, final double v1, final long v2) {
      $this.apply$mcVDJ$sp(v1, v2);
   }

   default void apply$mcVDJ$sp(final double v1, final long v2) {
      this.apply(v1, v2);
   }

   // $FF: synthetic method
   static boolean apply$mcZID$sp$(final Function2 $this, final int v1, final double v2) {
      return $this.apply$mcZID$sp(v1, v2);
   }

   default boolean apply$mcZID$sp(final int v1, final double v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDID$sp$(final Function2 $this, final int v1, final double v2) {
      return $this.apply$mcDID$sp(v1, v2);
   }

   default double apply$mcDID$sp(final int v1, final double v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFID$sp$(final Function2 $this, final int v1, final double v2) {
      return $this.apply$mcFID$sp(v1, v2);
   }

   default float apply$mcFID$sp(final int v1, final double v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIID$sp$(final Function2 $this, final int v1, final double v2) {
      return $this.apply$mcIID$sp(v1, v2);
   }

   default int apply$mcIID$sp(final int v1, final double v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJID$sp$(final Function2 $this, final int v1, final double v2) {
      return $this.apply$mcJID$sp(v1, v2);
   }

   default long apply$mcJID$sp(final int v1, final double v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVID$sp$(final Function2 $this, final int v1, final double v2) {
      $this.apply$mcVID$sp(v1, v2);
   }

   default void apply$mcVID$sp(final int v1, final double v2) {
      this.apply(v1, v2);
   }

   // $FF: synthetic method
   static boolean apply$mcZII$sp$(final Function2 $this, final int v1, final int v2) {
      return $this.apply$mcZII$sp(v1, v2);
   }

   default boolean apply$mcZII$sp(final int v1, final int v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDII$sp$(final Function2 $this, final int v1, final int v2) {
      return $this.apply$mcDII$sp(v1, v2);
   }

   default double apply$mcDII$sp(final int v1, final int v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFII$sp$(final Function2 $this, final int v1, final int v2) {
      return $this.apply$mcFII$sp(v1, v2);
   }

   default float apply$mcFII$sp(final int v1, final int v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIII$sp$(final Function2 $this, final int v1, final int v2) {
      return $this.apply$mcIII$sp(v1, v2);
   }

   default int apply$mcIII$sp(final int v1, final int v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJII$sp$(final Function2 $this, final int v1, final int v2) {
      return $this.apply$mcJII$sp(v1, v2);
   }

   default long apply$mcJII$sp(final int v1, final int v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVII$sp$(final Function2 $this, final int v1, final int v2) {
      $this.apply$mcVII$sp(v1, v2);
   }

   default void apply$mcVII$sp(final int v1, final int v2) {
      this.apply(v1, v2);
   }

   // $FF: synthetic method
   static boolean apply$mcZIJ$sp$(final Function2 $this, final int v1, final long v2) {
      return $this.apply$mcZIJ$sp(v1, v2);
   }

   default boolean apply$mcZIJ$sp(final int v1, final long v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDIJ$sp$(final Function2 $this, final int v1, final long v2) {
      return $this.apply$mcDIJ$sp(v1, v2);
   }

   default double apply$mcDIJ$sp(final int v1, final long v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFIJ$sp$(final Function2 $this, final int v1, final long v2) {
      return $this.apply$mcFIJ$sp(v1, v2);
   }

   default float apply$mcFIJ$sp(final int v1, final long v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIIJ$sp$(final Function2 $this, final int v1, final long v2) {
      return $this.apply$mcIIJ$sp(v1, v2);
   }

   default int apply$mcIIJ$sp(final int v1, final long v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJIJ$sp$(final Function2 $this, final int v1, final long v2) {
      return $this.apply$mcJIJ$sp(v1, v2);
   }

   default long apply$mcJIJ$sp(final int v1, final long v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVIJ$sp$(final Function2 $this, final int v1, final long v2) {
      $this.apply$mcVIJ$sp(v1, v2);
   }

   default void apply$mcVIJ$sp(final int v1, final long v2) {
      this.apply(v1, v2);
   }

   // $FF: synthetic method
   static boolean apply$mcZJD$sp$(final Function2 $this, final long v1, final double v2) {
      return $this.apply$mcZJD$sp(v1, v2);
   }

   default boolean apply$mcZJD$sp(final long v1, final double v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDJD$sp$(final Function2 $this, final long v1, final double v2) {
      return $this.apply$mcDJD$sp(v1, v2);
   }

   default double apply$mcDJD$sp(final long v1, final double v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFJD$sp$(final Function2 $this, final long v1, final double v2) {
      return $this.apply$mcFJD$sp(v1, v2);
   }

   default float apply$mcFJD$sp(final long v1, final double v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIJD$sp$(final Function2 $this, final long v1, final double v2) {
      return $this.apply$mcIJD$sp(v1, v2);
   }

   default int apply$mcIJD$sp(final long v1, final double v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJJD$sp$(final Function2 $this, final long v1, final double v2) {
      return $this.apply$mcJJD$sp(v1, v2);
   }

   default long apply$mcJJD$sp(final long v1, final double v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVJD$sp$(final Function2 $this, final long v1, final double v2) {
      $this.apply$mcVJD$sp(v1, v2);
   }

   default void apply$mcVJD$sp(final long v1, final double v2) {
      this.apply(v1, v2);
   }

   // $FF: synthetic method
   static boolean apply$mcZJI$sp$(final Function2 $this, final long v1, final int v2) {
      return $this.apply$mcZJI$sp(v1, v2);
   }

   default boolean apply$mcZJI$sp(final long v1, final int v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDJI$sp$(final Function2 $this, final long v1, final int v2) {
      return $this.apply$mcDJI$sp(v1, v2);
   }

   default double apply$mcDJI$sp(final long v1, final int v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFJI$sp$(final Function2 $this, final long v1, final int v2) {
      return $this.apply$mcFJI$sp(v1, v2);
   }

   default float apply$mcFJI$sp(final long v1, final int v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIJI$sp$(final Function2 $this, final long v1, final int v2) {
      return $this.apply$mcIJI$sp(v1, v2);
   }

   default int apply$mcIJI$sp(final long v1, final int v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJJI$sp$(final Function2 $this, final long v1, final int v2) {
      return $this.apply$mcJJI$sp(v1, v2);
   }

   default long apply$mcJJI$sp(final long v1, final int v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVJI$sp$(final Function2 $this, final long v1, final int v2) {
      $this.apply$mcVJI$sp(v1, v2);
   }

   default void apply$mcVJI$sp(final long v1, final int v2) {
      this.apply(v1, v2);
   }

   // $FF: synthetic method
   static boolean apply$mcZJJ$sp$(final Function2 $this, final long v1, final long v2) {
      return $this.apply$mcZJJ$sp(v1, v2);
   }

   default boolean apply$mcZJJ$sp(final long v1, final long v2) {
      return BoxesRunTime.unboxToBoolean(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static double apply$mcDJJ$sp$(final Function2 $this, final long v1, final long v2) {
      return $this.apply$mcDJJ$sp(v1, v2);
   }

   default double apply$mcDJJ$sp(final long v1, final long v2) {
      return BoxesRunTime.unboxToDouble(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static float apply$mcFJJ$sp$(final Function2 $this, final long v1, final long v2) {
      return $this.apply$mcFJJ$sp(v1, v2);
   }

   default float apply$mcFJJ$sp(final long v1, final long v2) {
      return BoxesRunTime.unboxToFloat(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static int apply$mcIJJ$sp$(final Function2 $this, final long v1, final long v2) {
      return $this.apply$mcIJJ$sp(v1, v2);
   }

   default int apply$mcIJJ$sp(final long v1, final long v2) {
      return BoxesRunTime.unboxToInt(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static long apply$mcJJJ$sp$(final Function2 $this, final long v1, final long v2) {
      return $this.apply$mcJJJ$sp(v1, v2);
   }

   default long apply$mcJJJ$sp(final long v1, final long v2) {
      return BoxesRunTime.unboxToLong(this.apply(v1, v2));
   }

   // $FF: synthetic method
   static void apply$mcVJJ$sp$(final Function2 $this, final long v1, final long v2) {
      $this.apply$mcVJJ$sp(v1, v2);
   }

   default void apply$mcVJJ$sp(final long v1, final long v2) {
      this.apply(v1, v2);
   }

   static void $init$(final Function2 $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
