package spire.random.rng;

import java.lang.invoke.SerializedLambda;
import scala.Array.;
import scala.runtime.java8.JFunction1;

public final class Well19937acIndexCache$ {
   public static final Well19937acIndexCache$ MODULE$ = new Well19937acIndexCache$();
   private static final int K = 19937;
   private static final int R;
   private static final int R_1;
   private static final int R_2;
   private static final int M1;
   private static final int M2;
   private static final int M3;
   private static final int[] vm1;
   private static final int[] vm2;
   private static final int[] vm3;
   private static final int[] vrm1;
   private static final int[] vrm2;

   static {
      R = (MODULE$.K() + 31) / 32;
      R_1 = MODULE$.R() - 1;
      R_2 = MODULE$.R() - 2;
      M1 = 70;
      M2 = 179;
      M3 = 449;
      vm1 = (int[]).MODULE$.tabulate(MODULE$.R(), (JFunction1.mcII.sp)(i) -> (i + MODULE$.M1()) % MODULE$.R(), scala.reflect.ClassTag..MODULE$.Int());
      vm2 = (int[]).MODULE$.tabulate(MODULE$.R(), (JFunction1.mcII.sp)(i) -> (i + MODULE$.M2()) % MODULE$.R(), scala.reflect.ClassTag..MODULE$.Int());
      vm3 = (int[]).MODULE$.tabulate(MODULE$.R(), (JFunction1.mcII.sp)(i) -> (i + MODULE$.M3()) % MODULE$.R(), scala.reflect.ClassTag..MODULE$.Int());
      vrm1 = (int[]).MODULE$.tabulate(MODULE$.R(), (JFunction1.mcII.sp)(i) -> (i + MODULE$.R_1()) % MODULE$.R(), scala.reflect.ClassTag..MODULE$.Int());
      vrm2 = (int[]).MODULE$.tabulate(MODULE$.R(), (JFunction1.mcII.sp)(i) -> (i + MODULE$.R_2()) % MODULE$.R(), scala.reflect.ClassTag..MODULE$.Int());
   }

   private final int K() {
      return K;
   }

   private final int R() {
      return R;
   }

   private final int R_1() {
      return R_1;
   }

   private final int R_2() {
      return R_2;
   }

   private final int M1() {
      return M1;
   }

   private final int M2() {
      return M2;
   }

   private final int M3() {
      return M3;
   }

   public int[] vm1() {
      return vm1;
   }

   public int[] vm2() {
      return vm2;
   }

   public int[] vm3() {
      return vm3;
   }

   public int[] vrm1() {
      return vrm1;
   }

   public int[] vrm2() {
      return vrm2;
   }

   private Well19937acIndexCache$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
