package spire.std;

import scala.reflect.ScalaSignature;
import spire.algebra.IsRational;
import spire.math.Rational;
import spire.math.Rational$;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003/\u0001\u0011\u0005q\u0006C\u00033\u0001\u0011\u00051\u0007C\u00036\u0001\u0011\u0005a\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0003?\u0001\u0011\u0005qHA\u0006GY>\fG/S:SK\u0006d'B\u0001\u0006\f\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0019\u0005)1\u000f]5sK\u000e\u00011\u0003\u0002\u0001\u0010+y\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007c\u0001\f\u001a75\tqC\u0003\u0002\u0019\u0017\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u000e\u0018\u0005)I5OU1uS>t\u0017\r\u001c\t\u0003!qI!!H\t\u0003\u000b\u0019cw.\u0019;\u0011\u0005}\u0001S\"A\u0005\n\u0005\u0005J!A\u0006$m_\u0006$HK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8\u0002\r\u0011Jg.\u001b;%)\u0005!\u0003C\u0001\t&\u0013\t1\u0013C\u0001\u0003V]&$\u0018\u0001\u0003;p\t>,(\r\\3\u0015\u0005%b\u0003C\u0001\t+\u0013\tY\u0013C\u0001\u0004E_V\u0014G.\u001a\u0005\u0006[\t\u0001\raG\u0001\u0002q\u0006!1-Z5m)\tY\u0002\u0007C\u00032\u0007\u0001\u00071$A\u0001b\u0003\u00151Gn\\8s)\tYB\u0007C\u00032\t\u0001\u00071$A\u0003s_VtG\r\u0006\u0002\u001co!)\u0011'\u0002a\u00017\u00059\u0011n],i_2,GC\u0001\u001e>!\t\u00012(\u0003\u0002=#\t9!i\\8mK\u0006t\u0007\"B\u0019\u0007\u0001\u0004Y\u0012A\u0003;p%\u0006$\u0018n\u001c8bYR\u0011\u0001I\u0012\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007.\tA!\\1uQ&\u0011QI\u0011\u0002\t%\u0006$\u0018n\u001c8bY\")\u0011g\u0002a\u00017\u0001"
)
public interface FloatIsReal extends IsRational, FloatTruncatedDivision {
   // $FF: synthetic method
   static double toDouble$(final FloatIsReal $this, final float x) {
      return $this.toDouble(x);
   }

   default double toDouble(final float x) {
      return this.toDouble$mcF$sp(x);
   }

   // $FF: synthetic method
   static float ceil$(final FloatIsReal $this, final float a) {
      return $this.ceil(a);
   }

   default float ceil(final float a) {
      return this.ceil$mcF$sp(a);
   }

   // $FF: synthetic method
   static float floor$(final FloatIsReal $this, final float a) {
      return $this.floor(a);
   }

   default float floor(final float a) {
      return this.floor$mcF$sp(a);
   }

   // $FF: synthetic method
   static float round$(final FloatIsReal $this, final float a) {
      return $this.round(a);
   }

   default float round(final float a) {
      return this.round$mcF$sp(a);
   }

   // $FF: synthetic method
   static boolean isWhole$(final FloatIsReal $this, final float a) {
      return $this.isWhole(a);
   }

   default boolean isWhole(final float a) {
      return this.isWhole$mcF$sp(a);
   }

   // $FF: synthetic method
   static Rational toRational$(final FloatIsReal $this, final float a) {
      return $this.toRational(a);
   }

   default Rational toRational(final float a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static double toDouble$mcF$sp$(final FloatIsReal $this, final float x) {
      return $this.toDouble$mcF$sp(x);
   }

   default double toDouble$mcF$sp(final float x) {
      return (double)x;
   }

   // $FF: synthetic method
   static float ceil$mcF$sp$(final FloatIsReal $this, final float a) {
      return $this.ceil$mcF$sp(a);
   }

   default float ceil$mcF$sp(final float a) {
      return (float)Math.ceil((double)a);
   }

   // $FF: synthetic method
   static float floor$mcF$sp$(final FloatIsReal $this, final float a) {
      return $this.floor$mcF$sp(a);
   }

   default float floor$mcF$sp(final float a) {
      return (float)Math.floor((double)a);
   }

   // $FF: synthetic method
   static float round$mcF$sp$(final FloatIsReal $this, final float a) {
      return $this.round$mcF$sp(a);
   }

   default float round$mcF$sp(final float a) {
      return spire.math.package$.MODULE$.round(a);
   }

   // $FF: synthetic method
   static boolean isWhole$mcF$sp$(final FloatIsReal $this, final float a) {
      return $this.isWhole$mcF$sp(a);
   }

   default boolean isWhole$mcF$sp(final float a) {
      return (double)a % (double)1.0F == (double)0.0F;
   }

   static void $init$(final FloatIsReal $this) {
   }
}
