package breeze.linalg;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;

@ScalaSignature(
   bytes = "\u0006\u0005!3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0019\u0005\u0011\u0004C\u0003?\u0001\u0011\u0005q\bC\u0003F\u0001\u0011\u0005aI\u0001\u0007[SB\u0004X\r\u001a,bYV,7O\u0003\u0002\b\u0011\u00051A.\u001b8bY\u001eT\u0011!C\u0001\u0007EJ,WM_3\u0004\u0001U\u0019A\"I\u001d\u0014\u0005\u0001i\u0001C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002+A\u0011aBF\u0005\u0003/=\u0011A!\u00168ji\u00069am\u001c:fC\u000eDGCA\u000b\u001b\u0011\u0015Y\"\u00011\u0001\u001d\u0003\u00051\u0007#\u0002\b\u001e?a*\u0012B\u0001\u0010\u0010\u0005%1UO\\2uS>t'\u0007\u0005\u0002!C1\u0001A!\u0003\u0012\u0001A\u0003\u0005\tQ1\u0001$\u0005\t1\u0016'\u0005\u0002%OA\u0011a\"J\u0005\u0003M=\u0011qAT8uQ&tw\r\u0005\u0002\u000fQ%\u0011\u0011f\u0004\u0002\u0004\u0003:L\bfA\u0011,]A\u0011a\u0002L\u0005\u0003[=\u00111b\u001d9fG&\fG.\u001b>fIF*1e\f\u00193c9\u0011a\u0002M\u0005\u0003c=\ta\u0001R8vE2,\u0017\u0007\u0002\u00134oAq!\u0001N\u001c\u000e\u0003UR!A\u000e\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0002C\u0001\u0011:\t%Q\u0004\u0001)A\u0001\u0002\u000b\u00071E\u0001\u0002We!\u001a\u0011h\u000b\u001f2\u000b\rz\u0003'P\u00192\t\u0011\u001at\u0007E\u0001\u0007KbL7\u000f^:\u0015\u0005\u0001\u001b\u0005C\u0001\bB\u0013\t\u0011uBA\u0004C_>dW-\u00198\t\u000bm\u0019\u0001\u0019\u0001#\u0011\u000b9ir\u0004\u000f!\u0002\r\u0019|'/\u00197m)\t\u0001u\tC\u0003\u001c\t\u0001\u0007A\t"
)
public interface ZippedValues {
   void foreach(final Function2 f);

   // $FF: synthetic method
   static boolean exists$(final ZippedValues $this, final Function2 f) {
      return $this.exists(f);
   }

   default boolean exists(final Function2 f) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreach((a, b) -> {
            $anonfun$exists$1(f, var2, a, b);
            return BoxedUnit.UNIT;
         });
         var10000 = false;
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = var4.value$mcZ$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean forall$(final ZippedValues $this, final Function2 f) {
      return $this.forall(f);
   }

   default boolean forall(final Function2 f) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreach((a, b) -> {
            $anonfun$forall$1(f, var2, a, b);
            return BoxedUnit.UNIT;
         });
         var10000 = true;
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = var4.value$mcZ$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static void foreach$mcDD$sp$(final ZippedValues $this, final Function2 f) {
      $this.foreach$mcDD$sp(f);
   }

   default void foreach$mcDD$sp(final Function2 f) {
      this.foreach(f);
   }

   // $FF: synthetic method
   static boolean exists$mcDD$sp$(final ZippedValues $this, final Function2 f) {
      return $this.exists$mcDD$sp(f);
   }

   default boolean exists$mcDD$sp(final Function2 f) {
      return this.exists(f);
   }

   // $FF: synthetic method
   static boolean forall$mcDD$sp$(final ZippedValues $this, final Function2 f) {
      return $this.forall$mcDD$sp(f);
   }

   default boolean forall$mcDD$sp(final Function2 f) {
      return this.forall(f);
   }

   // $FF: synthetic method
   static void $anonfun$exists$1(final Function2 f$1, final Object nonLocalReturnKey1$1, final Object a, final Object b) {
      if (BoxesRunTime.unboxToBoolean(f$1.apply(a, b))) {
         throw new NonLocalReturnControl.mcZ.sp(nonLocalReturnKey1$1, true);
      }
   }

   // $FF: synthetic method
   static void $anonfun$forall$1(final Function2 f$2, final Object nonLocalReturnKey2$1, final Object a, final Object b) {
      if (!BoxesRunTime.unboxToBoolean(f$2.apply(a, b))) {
         throw new NonLocalReturnControl.mcZ.sp(nonLocalReturnKey2$1, false);
      }
   }

   static void $init$(final ZippedValues $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
