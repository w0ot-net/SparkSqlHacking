package org.apache.spark.util.random;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a!B\u000b\u0017\u0001i\u0001\u0003\u0002\u0003\u001b\u0001\u0005\u000b\u0007I\u0011A\u001b\t\u0011e\u0002!\u0011!Q\u0001\nYB\u0001B\u000f\u0001\u0003\u0006\u0004%\ta\u000f\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005y!AA\t\u0001B\u0001B\u0003%a\u0007C\u0003F\u0001\u0011\u0005a\tC\u0004M\u0001\t\u0007I\u0011C\u001b\t\r5\u0003\u0001\u0015!\u00037\u0011\u0015q\u0005\u0001\"\u0005P\u0011\u001d\u0019\u0006\u00011A\u0005\n=Cq\u0001\u0016\u0001A\u0002\u0013%Q\u000b\u0003\u0004\\\u0001\u0001\u0006K\u0001\u0015\u0005\u00069\u0002!\t!\u0018\u0005\u0006=\u0002!IaX\u0004\tAZ\t\t\u0011#\u0001\u001bC\u001aAQCFA\u0001\u0012\u0003Q\"\rC\u0003F!\u0011\u0005\u0001\u000eC\u0004j!E\u0005I\u0011\u00016\t\u000fU\u0004\u0012\u0013!C\u0001m\"9\u0001\u0010EA\u0001\n\u0013I(AF$baN\u000bW\u000e\u001d7j]\u001e\u0014V\r\u001d7bG\u0016lWM\u001c;\u000b\u0005]A\u0012A\u0002:b]\u0012|WN\u0003\u0002\u001a5\u0005!Q\u000f^5m\u0015\tYB$A\u0003ta\u0006\u00148N\u0003\u0002\u001e=\u00051\u0011\r]1dQ\u0016T\u0011aH\u0001\u0004_J<7c\u0001\u0001\"OA\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1\u0011I\\=SK\u001a\u0004\"\u0001K\u0019\u000f\u0005%zcB\u0001\u0016/\u001b\u0005Y#B\u0001\u0017.\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\u0013\n\u0005A\u001a\u0013a\u00029bG.\fw-Z\u0005\u0003eM\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001M\u0012\u0002\u0003\u0019,\u0012A\u000e\t\u0003E]J!\u0001O\u0012\u0003\r\u0011{WO\u00197f\u0003\t1\u0007%A\u0002s]\u001e,\u0012\u0001\u0010\t\u0003{\u0005k\u0011A\u0010\u0006\u00033}R\u0011\u0001Q\u0001\u0005U\u00064\u0018-\u0003\u0002C}\t1!+\u00198e_6\fAA\u001d8hA\u00059Q\r]:jY>t\u0017A\u0002\u001fj]&$h\b\u0006\u0003H\u0013*[\u0005C\u0001%\u0001\u001b\u00051\u0002\"\u0002\u001b\u0007\u0001\u00041\u0004b\u0002\u001e\u0007!\u0003\u0005\r\u0001\u0010\u0005\b\t\u001a\u0001\n\u00111\u00017\u0003\u0005\t\u0018AA9!\u0003)\u0001x.[:t_:<U)M\u000b\u0002!B\u0011!%U\u0005\u0003%\u000e\u00121!\u00138u\u0003A\u0019w.\u001e8u\r>\u0014HI]8qa&tw-\u0001\u000bd_VtGOR8s\tJ|\u0007\u000f]5oO~#S-\u001d\u000b\u0003-f\u0003\"AI,\n\u0005a\u001b#\u0001B+oSRDqAW\u0006\u0002\u0002\u0003\u0007\u0001+A\u0002yIE\n\u0011cY8v]R4uN\u001d#s_B\u0004\u0018N\\4!\u0003\u0019\u0019\u0018-\u001c9mKR\t\u0001+A\u0004bIZ\fgnY3\u0015\u0003Y\u000bacR1q'\u0006l\u0007\u000f\\5oOJ+\u0007\u000f\\1dK6,g\u000e\u001e\t\u0003\u0011B\u00192\u0001E\u0011d!\t!w-D\u0001f\u0015\t1w(\u0001\u0002j_&\u0011!'\u001a\u000b\u0002C\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uII*\u0012a\u001b\u0016\u0003y1\\\u0013!\u001c\t\u0003]Nl\u0011a\u001c\u0006\u0003aF\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005I\u001c\u0013AC1o]>$\u0018\r^5p]&\u0011Ao\u001c\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'F\u0001xU\t1D.\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001{!\tYh0D\u0001}\u0015\tix(\u0001\u0003mC:<\u0017BA@}\u0005\u0019y%M[3di\u0002"
)
public class GapSamplingReplacement implements Serializable {
   private final double f;
   private final Random rng;
   private final double epsilon;
   private final double q;
   private int countForDropping;

   public static double $lessinit$greater$default$3() {
      return GapSamplingReplacement$.MODULE$.$lessinit$greater$default$3();
   }

   public static Random $lessinit$greater$default$2() {
      return GapSamplingReplacement$.MODULE$.$lessinit$greater$default$2();
   }

   public double f() {
      return this.f;
   }

   public Random rng() {
      return this.rng;
   }

   public double q() {
      return this.q;
   }

   public int poissonGE1() {
      double pp = this.q() + ((double)1.0F - this.q()) * this.rng().nextDouble();
      int r = 1;

      for(double var4 = pp * this.rng().nextDouble(); var4 > this.q(); var4 *= this.rng().nextDouble()) {
         ++r;
      }

      return r;
   }

   private int countForDropping() {
      return this.countForDropping;
   }

   private void countForDropping_$eq(final int x$1) {
      this.countForDropping = x$1;
   }

   public int sample() {
      if (this.countForDropping() > 0) {
         this.countForDropping_$eq(this.countForDropping() - 1);
         return 0;
      } else {
         int r = this.poissonGE1();
         this.advance();
         return r;
      }
   }

   private void advance() {
      double u = .MODULE$.max(this.rng().nextDouble(), this.epsilon);
      this.countForDropping_$eq((int)(.MODULE$.log(u) / -this.f()));
   }

   public GapSamplingReplacement(final double f, final Random rng, final double epsilon) {
      this.f = f;
      this.rng = rng;
      this.epsilon = epsilon;
      scala.Predef..MODULE$.require(f > (double)0.0F, () -> "Sampling fraction (" + this.f() + ") must be > 0");
      scala.Predef..MODULE$.require(epsilon > (double)0.0F, () -> "epsilon (" + this.epsilon + ") must be > 0");
      this.q = .MODULE$.exp(-f);
      this.countForDropping = 0;
      this.advance();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
