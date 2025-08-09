package breeze.linalg;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction2;

public interface ZippedValues$mcDD$sp extends ZippedValues {
   // $FF: synthetic method
   static boolean exists$(final ZippedValues$mcDD$sp $this, final Function2 f) {
      return $this.exists(f);
   }

   default boolean exists(final Function2 f) {
      return this.exists$mcDD$sp(f);
   }

   // $FF: synthetic method
   static boolean exists$mcDD$sp$(final ZippedValues$mcDD$sp $this, final Function2 f) {
      return $this.exists$mcDD$sp(f);
   }

   default boolean exists$mcDD$sp(final Function2 f) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreach$mcDD$sp((JFunction2.mcVDD.sp)(a, b) -> {
            if (f.apply$mcZDD$sp(a, b)) {
               throw new NonLocalReturnControl.mcZ.sp(var2, true);
            }
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
   static boolean forall$(final ZippedValues$mcDD$sp $this, final Function2 f) {
      return $this.forall(f);
   }

   default boolean forall(final Function2 f) {
      return this.forall$mcDD$sp(f);
   }

   // $FF: synthetic method
   static boolean forall$mcDD$sp$(final ZippedValues$mcDD$sp $this, final Function2 f) {
      return $this.forall$mcDD$sp(f);
   }

   default boolean forall$mcDD$sp(final Function2 f) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreach$mcDD$sp((JFunction2.mcVDD.sp)(a, b) -> {
            if (!f.apply$mcZDD$sp(a, b)) {
               throw new NonLocalReturnControl.mcZ.sp(var2, false);
            }
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
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
