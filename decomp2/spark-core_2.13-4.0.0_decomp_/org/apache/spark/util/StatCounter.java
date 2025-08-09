package org.apache.spark.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md\u0001B\u0015+\u0001MB\u0001B\u0012\u0001\u0003\u0002\u0003\u0006Ia\u0012\u0005\u0006\u001b\u0002!\tA\u0014\u0005\b%\u0002\u0001\r\u0011\"\u0003T\u0011\u001d9\u0006\u00011A\u0005\naCaA\u0018\u0001!B\u0013!\u0006bB0\u0001\u0001\u0004%I\u0001\u0019\u0005\bC\u0002\u0001\r\u0011\"\u0003c\u0011\u0019!\u0007\u0001)Q\u0005\u0015\"9Q\r\u0001a\u0001\n\u0013\u0001\u0007b\u00024\u0001\u0001\u0004%Ia\u001a\u0005\u0007S\u0002\u0001\u000b\u0015\u0002&\t\u000f)\u0004\u0001\u0019!C\u0005A\"91\u000e\u0001a\u0001\n\u0013a\u0007B\u00028\u0001A\u0003&!\nC\u0004p\u0001\u0001\u0007I\u0011\u00021\t\u000fA\u0004\u0001\u0019!C\u0005c\"11\u000f\u0001Q!\n)CQ!\u0014\u0001\u0005\u0002QDQ!\u001e\u0001\u0005\u0002YDQ!\u001e\u0001\u0005\u0002eDQ!\u001e\u0001\u0005\u0002mDQA \u0001\u0005\u0002QDQa \u0001\u0005\u0002MCa!!\u0001\u0001\t\u0003\u0001\u0007BBA\u0002\u0001\u0011\u0005\u0001\r\u0003\u0004\u0002\u0006\u0001!\t\u0001\u0019\u0005\u0007\u0003\u000f\u0001A\u0011\u00011\t\r\u0005%\u0001\u0001\"\u0001a\u0011\u0019\tY\u0001\u0001C\u0001A\"1\u0011q\u0004\u0001\u0005\u0002\u0001Da!!\t\u0001\t\u0003\u0001\u0007BBA\u0012\u0001\u0011\u0005\u0001\r\u0003\u0004\u0002(\u0001!\t\u0001\u0019\u0005\b\u0003S\u0001A\u0011IA\u0016\u000f\u001d\tiD\u000bE\u0001\u0003\u007f1a!\u000b\u0016\t\u0002\u0005\u0005\u0003BB'%\t\u0003\t\t\u0006C\u0004\u0002T\u0011\"\t!!\u0016\t\u000f\u0005MC\u0005\"\u0001\u0002Z!I\u00111\r\u0013\u0002\u0002\u0013%\u0011Q\r\u0002\f'R\fGoQ8v]R,'O\u0003\u0002,Y\u0005!Q\u000f^5m\u0015\tic&A\u0003ta\u0006\u00148N\u0003\u00020a\u00051\u0011\r]1dQ\u0016T\u0011!M\u0001\u0004_J<7\u0001A\n\u0004\u0001QR\u0004CA\u001b9\u001b\u00051$\"A\u001c\u0002\u000bM\u001c\u0017\r\\1\n\u0005e2$AB!osJ+g\r\u0005\u0002<\u0007:\u0011A(\u0011\b\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007fI\na\u0001\u0010:p_Rt\u0014\"A\u001c\n\u0005\t3\u0014a\u00029bG.\fw-Z\u0005\u0003\t\u0016\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0011\u001c\u0002\rY\fG.^3t!\rY\u0004JS\u0005\u0003\u0013\u0016\u0013A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\u0004\"!N&\n\u000513$A\u0002#pk\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0003\u001fF\u0003\"\u0001\u0015\u0001\u000e\u0003)BQA\u0012\u0002A\u0002\u001d\u000b\u0011A\\\u000b\u0002)B\u0011Q'V\u0005\u0003-Z\u0012A\u0001T8oO\u0006)an\u0018\u0013fcR\u0011\u0011\f\u0018\t\u0003kiK!a\u0017\u001c\u0003\tUs\u0017\u000e\u001e\u0005\b;\u0012\t\t\u00111\u0001U\u0003\rAH%M\u0001\u0003]\u0002\n!!\\;\u0016\u0003)\u000ba!\\;`I\u0015\fHCA-d\u0011\u001div!!AA\u0002)\u000b1!\\;!\u0003\ti''\u0001\u0004ne}#S-\u001d\u000b\u00033\"Dq!\u0018\u0006\u0002\u0002\u0003\u0007!*A\u0002ne\u0001\n\u0001\"\\1y-\u0006dW/Z\u0001\r[\u0006Dh+\u00197vK~#S-\u001d\u000b\u000336Dq!X\u0007\u0002\u0002\u0003\u0007!*A\u0005nCb4\u0016\r\\;fA\u0005AQ.\u001b8WC2,X-\u0001\u0007nS:4\u0016\r\\;f?\u0012*\u0017\u000f\u0006\u0002Ze\"9Q\fEA\u0001\u0002\u0004Q\u0015!C7j]Z\u000bG.^3!)\u0005y\u0015!B7fe\u001e,GCA(x\u0011\u0015A8\u00031\u0001K\u0003\u00151\u0018\r\\;f)\ty%\u0010C\u0003G)\u0001\u0007q\t\u0006\u0002Py\")Q0\u0006a\u0001\u001f\u0006)q\u000e\u001e5fe\u0006!1m\u001c9z\u0003\u0015\u0019w.\u001e8u\u0003\u0011iW-\u00198\u0002\u0007M,X.A\u0002nCb\f1!\\5o\u0003!1\u0018M]5b]\u000e,\u0017a\u00039paZ\u000b'/[1oG\u0016DS!HA\b\u00037\u0001B!!\u0005\u0002\u00185\u0011\u00111\u0003\u0006\u0004\u0003+a\u0013AC1o]>$\u0018\r^5p]&!\u0011\u0011DA\n\u0005\u0015\u0019\u0016N\\2fC\t\ti\"A\u00033]Er\u0003'\u0001\btC6\u0004H.\u001a,be&\fgnY3\u0002\u000bM$H-\u001a<\u0002\u0011A|\u0007o\u0015;eKZDS\u0001IA\b\u00037\t1b]1na2,7\u000b\u001e3fm\u0006AAo\\*ue&tw\r\u0006\u0002\u0002.A!\u0011qFA\u001c\u001d\u0011\t\t$a\r\u0011\u0005u2\u0014bAA\u001bm\u00051\u0001K]3eK\u001aLA!!\u000f\u0002<\t11\u000b\u001e:j]\u001eT1!!\u000e7\u0003-\u0019F/\u0019;D_VtG/\u001a:\u0011\u0005A#3\u0003\u0002\u00135\u0003\u0007\u0002B!!\u0012\u0002P5\u0011\u0011q\t\u0006\u0005\u0003\u0013\nY%\u0001\u0002j_*\u0011\u0011QJ\u0001\u0005U\u00064\u0018-C\u0002E\u0003\u000f\"\"!a\u0010\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007=\u000b9\u0006C\u0003GM\u0001\u0007q\tF\u0002P\u00037BaAR\u0014A\u0002\u0005u\u0003\u0003B\u001b\u0002`)K1!!\u00197\u0005)a$/\u001a9fCR,GMP\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003O\u0002B!!\u001b\u0002p5\u0011\u00111\u000e\u0006\u0005\u0003[\nY%\u0001\u0003mC:<\u0017\u0002BA9\u0003W\u0012aa\u00142kK\u000e$\b"
)
public class StatCounter implements Serializable {
   private long n;
   private double mu;
   private double m2;
   private double maxValue;
   private double minValue;

   public static StatCounter apply(final Seq values) {
      return StatCounter$.MODULE$.apply(values);
   }

   public static StatCounter apply(final IterableOnce values) {
      return StatCounter$.MODULE$.apply(values);
   }

   private long n() {
      return this.n;
   }

   private void n_$eq(final long x$1) {
      this.n = x$1;
   }

   private double mu() {
      return this.mu;
   }

   private void mu_$eq(final double x$1) {
      this.mu = x$1;
   }

   private double m2() {
      return this.m2;
   }

   private void m2_$eq(final double x$1) {
      this.m2 = x$1;
   }

   private double maxValue() {
      return this.maxValue;
   }

   private void maxValue_$eq(final double x$1) {
      this.maxValue = x$1;
   }

   private double minValue() {
      return this.minValue;
   }

   private void minValue_$eq(final double x$1) {
      this.minValue = x$1;
   }

   public StatCounter merge(final double value) {
      double delta = value - this.mu();
      this.n_$eq(this.n() + 1L);
      this.mu_$eq(this.mu() + delta / (double)this.n());
      this.m2_$eq(this.m2() + delta * (value - this.mu()));
      this.maxValue_$eq(.MODULE$.max(this.maxValue(), value));
      this.minValue_$eq(.MODULE$.min(this.minValue(), value));
      return this;
   }

   public StatCounter merge(final IterableOnce values) {
      values.iterator().foreach((v) -> $anonfun$merge$1(this, BoxesRunTime.unboxToDouble(v)));
      return this;
   }

   public StatCounter merge(final StatCounter other) {
      if (other == null) {
         if (this == null) {
            return this.merge(other.copy());
         }
      } else if (other.equals(this)) {
         return this.merge(other.copy());
      }

      if (this.n() == 0L) {
         this.mu_$eq(other.mu());
         this.m2_$eq(other.m2());
         this.n_$eq(other.n());
         this.maxValue_$eq(other.maxValue());
         this.minValue_$eq(other.minValue());
      } else if (other.n() != 0L) {
         double delta = other.mu() - this.mu();
         if (other.n() * 10L < this.n()) {
            this.mu_$eq(this.mu() + delta * (double)other.n() / (double)(this.n() + other.n()));
         } else if (this.n() * 10L < other.n()) {
            this.mu_$eq(other.mu() - delta * (double)this.n() / (double)(this.n() + other.n()));
         } else {
            this.mu_$eq((this.mu() * (double)this.n() + other.mu() * (double)other.n()) / (double)(this.n() + other.n()));
         }

         this.m2_$eq(this.m2() + other.m2() + delta * delta * (double)this.n() * (double)other.n() / (double)(this.n() + other.n()));
         this.n_$eq(this.n() + other.n());
         this.maxValue_$eq(.MODULE$.max(this.maxValue(), other.maxValue()));
         this.minValue_$eq(.MODULE$.min(this.minValue(), other.minValue()));
      }

      return this;
   }

   public StatCounter copy() {
      StatCounter other = new StatCounter();
      other.n_$eq(this.n());
      other.mu_$eq(this.mu());
      other.m2_$eq(this.m2());
      other.maxValue_$eq(this.maxValue());
      other.minValue_$eq(this.minValue());
      return other;
   }

   public long count() {
      return this.n();
   }

   public double mean() {
      return this.mu();
   }

   public double sum() {
      return (double)this.n() * this.mu();
   }

   public double max() {
      return this.maxValue();
   }

   public double min() {
      return this.minValue();
   }

   public double variance() {
      return this.popVariance();
   }

   public double popVariance() {
      return this.n() == 0L ? Double.NaN : this.m2() / (double)this.n();
   }

   public double sampleVariance() {
      return this.n() <= 1L ? Double.NaN : this.m2() / (double)(this.n() - 1L);
   }

   public double stdev() {
      return this.popStdev();
   }

   public double popStdev() {
      return .MODULE$.sqrt(this.popVariance());
   }

   public double sampleStdev() {
      return .MODULE$.sqrt(this.sampleVariance());
   }

   public String toString() {
      return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("(count: %d, mean: %f, stdev: %f, max: %f, min: %f)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(this.count()), BoxesRunTime.boxToDouble(this.mean()), BoxesRunTime.boxToDouble(this.stdev()), BoxesRunTime.boxToDouble(this.max()), BoxesRunTime.boxToDouble(this.min())}));
   }

   // $FF: synthetic method
   public static final StatCounter $anonfun$merge$1(final StatCounter $this, final double v) {
      return $this.merge(v);
   }

   public StatCounter(final IterableOnce values) {
      this.n = 0L;
      this.mu = (double)0.0F;
      this.m2 = (double)0.0F;
      this.maxValue = Double.NEGATIVE_INFINITY;
      this.minValue = Double.POSITIVE_INFINITY;
      this.merge(values);
   }

   public StatCounter() {
      this(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
