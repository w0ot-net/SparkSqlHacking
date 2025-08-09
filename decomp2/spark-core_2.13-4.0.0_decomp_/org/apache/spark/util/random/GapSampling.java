package org.apache.spark.util.random;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import scala.math.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e4QAE\n\u0001/uA\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\tk\u0001\u0011\t\u0011)A\u0005m!AQ\b\u0001B\u0001B\u0003%!\u0007C\u0003?\u0001\u0011\u0005q\bC\u0004F\u0001\t\u0007I\u0011\u0002$\t\r\u001d\u0003\u0001\u0015!\u00033\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u001di\u0005\u00011A\u0005\n9Cqa\u0014\u0001A\u0002\u0013%\u0001\u000b\u0003\u0004W\u0001\u0001\u0006KA\u0013\u0005\u0006/\u0002!I\u0001W\u0004\t3N\t\t\u0011#\u0001\u00185\u001aA!cEA\u0001\u0012\u000392\fC\u0003?\u001b\u0011\u0005\u0011\rC\u0004c\u001bE\u0005I\u0011A2\t\u000f9l\u0011\u0013!C\u0001_\"9\u0011/DA\u0001\n\u0013\u0011(aC$baN\u000bW\u000e\u001d7j]\u001eT!\u0001F\u000b\u0002\rI\fg\u000eZ8n\u0015\t1r#\u0001\u0003vi&d'B\u0001\r\u001a\u0003\u0015\u0019\b/\u0019:l\u0015\tQ2$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00029\u0005\u0019qN]4\u0014\u0007\u0001qB\u0005\u0005\u0002 E5\t\u0001EC\u0001\"\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0003E\u0001\u0004B]f\u0014VM\u001a\t\u0003K9r!A\n\u0017\u000f\u0005\u001dZS\"\u0001\u0015\u000b\u0005%R\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0005J!!\f\u0011\u0002\u000fA\f7m[1hK&\u0011q\u0006\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003[\u0001\n\u0011A\u001a\t\u0003?MJ!\u0001\u000e\u0011\u0003\r\u0011{WO\u00197f\u0003\r\u0011hn\u001a\t\u0003omj\u0011\u0001\u000f\u0006\u0003-eR\u0011AO\u0001\u0005U\u00064\u0018-\u0003\u0002=q\t1!+\u00198e_6\fq!\u001a9tS2|g.\u0001\u0004=S:LGO\u0010\u000b\u0005\u0001\n\u001bE\t\u0005\u0002B\u00015\t1\u0003C\u00032\t\u0001\u0007!\u0007C\u00046\tA\u0005\t\u0019\u0001\u001c\t\u000fu\"\u0001\u0013!a\u0001e\u0005\u0019AN\\9\u0016\u0003I\nA\u0001\u001c8rA\u000511/Y7qY\u0016$\u0012A\u0013\t\u0003?-K!\u0001\u0014\u0011\u0003\u0007%sG/\u0001\td_VtGOR8s\tJ|\u0007\u000f]5oOV\t!*\u0001\u000bd_VtGOR8s\tJ|\u0007\u000f]5oO~#S-\u001d\u000b\u0003#R\u0003\"a\b*\n\u0005M\u0003#\u0001B+oSRDq!V\u0005\u0002\u0002\u0003\u0007!*A\u0002yIE\n\u0011cY8v]R4uN\u001d#s_B\u0004\u0018N\\4!\u0003\u001d\tGM^1oG\u0016$\u0012!U\u0001\f\u000f\u0006\u00048+Y7qY&tw\r\u0005\u0002B\u001bM\u0019QB\b/\u0011\u0005u\u0003W\"\u00010\u000b\u0005}K\u0014AA5p\u0013\tyc\fF\u0001[\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%eU\tAM\u000b\u00027K.\na\r\u0005\u0002hY6\t\u0001N\u0003\u0002jU\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003W\u0002\n!\"\u00198o_R\fG/[8o\u0013\ti\u0007NA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001aT#\u00019+\u0005I*\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#A:\u0011\u0005Q<X\"A;\u000b\u0005YL\u0014\u0001\u00027b]\u001eL!\u0001_;\u0003\r=\u0013'.Z2u\u0001"
)
public class GapSampling implements Serializable {
   private final double f;
   private final Random rng;
   private final double epsilon;
   private final double lnq;
   private int countForDropping;

   public static double $lessinit$greater$default$3() {
      return GapSampling$.MODULE$.$lessinit$greater$default$3();
   }

   public static Random $lessinit$greater$default$2() {
      return GapSampling$.MODULE$.$lessinit$greater$default$2();
   }

   private double lnq() {
      return this.lnq;
   }

   public int sample() {
      if (this.countForDropping() > 0) {
         this.countForDropping_$eq(this.countForDropping() - 1);
         return 0;
      } else {
         this.advance();
         return 1;
      }
   }

   private int countForDropping() {
      return this.countForDropping;
   }

   private void countForDropping_$eq(final int x$1) {
      this.countForDropping = x$1;
   }

   private void advance() {
      double u = .MODULE$.max(this.rng.nextDouble(), this.epsilon);
      this.countForDropping_$eq((int)(.MODULE$.log(u) / this.lnq()));
   }

   public GapSampling(final double f, final Random rng, final double epsilon) {
      this.f = f;
      this.rng = rng;
      this.epsilon = epsilon;
      scala.Predef..MODULE$.require(f > (double)0.0F && f < (double)1.0F, () -> "Sampling fraction (" + this.f + ") must reside on open interval (0, 1)");
      scala.Predef..MODULE$.require(epsilon > (double)0.0F, () -> "epsilon (" + this.epsilon + ") must be > 0");
      this.lnq = .MODULE$.log1p(-f);
      this.countForDropping = 0;
      this.advance();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
