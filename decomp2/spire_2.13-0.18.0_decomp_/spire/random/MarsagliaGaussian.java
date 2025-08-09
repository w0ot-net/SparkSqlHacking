package spire.random;

import algebra.ring.Field;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.package$;
import spire.syntax.LiteralIntMultiplicativeSemigroupOps$;
import spire.syntax.package;

@ScalaSignature(
   bytes = "\u0006\u000594AAC\u0006\u0003!!A!\b\u0001B\u0002B\u0003-1\b\u0003\u0005I\u0001\t\r\t\u0015a\u0003J\u0011!i\u0005AaA!\u0002\u0017q\u0005\u0002C)\u0001\u0005\u0007\u0005\u000b1\u0002*\t\u0011U\u0003!1!Q\u0001\fYCQ!\u0017\u0001\u0005\u0002iCqA\u0019\u0001C\u0002\u001351\r\u0003\u0004h\u0001\u0001\u0006i\u0001\u001a\u0005\u0006Q\u0002!\t!\u001b\u0002\u0012\u001b\u0006\u00148/Y4mS\u0006<\u0015-^:tS\u0006t'B\u0001\u0007\u000e\u0003\u0019\u0011\u0018M\u001c3p[*\ta\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0005Eq2c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u00042!\u0007\u000e\u001d\u001b\u0005Y\u0011BA\u000e\f\u0005!9\u0015-^:tS\u0006t\u0007CA\u000f\u001f\u0019\u0001!\u0011b\b\u0001!\u0002\u0003\u0005)\u0019\u0001\u0011\u0003\u0003\u0005\u000b\"!\t\u0013\u0011\u0005M\u0011\u0013BA\u0012\u0015\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aE\u0013\n\u0005\u0019\"\"aA!os\"\"a\u0004K\u00166!\t\u0019\u0012&\u0003\u0002+)\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u0019C&L\u0018/\u001d\t\u0019R&\u0003\u0002/)\u0005)a\t\\8biF\"A\u0005\r\u001b\u0016\u001d\t\tD'D\u00013\u0015\t\u0019t\"\u0001\u0004=e>|GOP\u0005\u0002+E*1EN\u001c:q9\u00111cN\u0005\u0003qQ\ta\u0001R8vE2,\u0017\u0007\u0002\u00131iU\t!\"\u001a<jI\u0016t7-\u001a\u00132!\raT\t\b\b\u0003{\ts!A\u0010!\u000f\u0005Ez\u0014\"\u0001\b\n\u0005\u0005k\u0011aB1mO\u0016\u0014'/Y\u0005\u0003\u0007\u0012\u000bq\u0001]1dW\u0006<WM\u0003\u0002B\u001b%\u0011ai\u0012\u0002\u0006\r&,G\u000e\u001a\u0006\u0003\u0007\u0012\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\rQ5\nH\u0007\u0002\t&\u0011A\n\u0012\u0002\u0006\u001dJ{w\u000e^\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004c\u0001&P9%\u0011\u0001\u000b\u0012\u0002\u0005)JLw-\u0001\u0006fm&$WM\\2fIQ\u00022\u0001P*\u001d\u0013\t!vIA\u0003Pe\u0012,'/\u0001\u0006fm&$WM\\2fIU\u00022!G,\u001d\u0013\tA6BA\u0004V]&4wN]7\u0002\rqJg.\u001b;?)\u0005YFC\u0002/^=~\u0003\u0017\rE\u0002\u001a\u0001qAQA\u000f\u0004A\u0004mBQ\u0001\u0013\u0004A\u0004%CQ!\u0014\u0004A\u00049CQ!\u0015\u0004A\u0004ICQ!\u0016\u0004A\u0004Y\u000b\u0011!^\u000b\u0002IB\u0019\u0011$\u001a\u000f\n\u0005\u0019\\!\u0001\u0002#jgR\f!!\u001e\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0011TG\u000eC\u0003l\u0013\u0001\u0007A$\u0001\u0003nK\u0006t\u0007\"B7\n\u0001\u0004a\u0012AB:uI\u0012+g\u000f"
)
public class MarsagliaGaussian implements Gaussian {
   public final Field evidence$1;
   public final NRoot evidence$2;
   public final Trig evidence$3;
   public final Order evidence$4;
   public final Uniform evidence$5;
   public final Dist u;

   public Dist u() {
      return this.u;
   }

   public Dist apply(final Object mean, final Object stdDev) {
      return new DistFromGen((gen) -> this.loop$1(gen, stdDev, mean));
   }

   public Dist u$mcD$sp() {
      return this.u();
   }

   public Dist u$mcF$sp() {
      return this.u();
   }

   public Dist apply$mcD$sp(final double mean, final double stdDev) {
      return this.apply(BoxesRunTime.boxToDouble(mean), BoxesRunTime.boxToDouble(stdDev));
   }

   public Dist apply$mcF$sp(final float mean, final float stdDev) {
      return this.apply(BoxesRunTime.boxToFloat(mean), BoxesRunTime.boxToFloat(stdDev));
   }

   public boolean specInstance$() {
      return false;
   }

   private final Object loop$1(final Generator gen$1, final Object stdDev$3, final Object mean$3) {
      Object x;
      Object s;
      do {
         x = this.u().apply(gen$1);
         Object y = this.u().apply(gen$1);
         s = this.evidence$1.plus(this.evidence$1.times(x, x), this.evidence$1.times(y, y));
      } while(this.evidence$4.gteqv(s, package$.MODULE$.Field().apply(this.evidence$1).one()) || this.evidence$4.eqv(s, package$.MODULE$.Field().apply(this.evidence$1).zero()));

      Object scale = this.evidence$1.times(stdDev$3, this.evidence$2.sqrt(this.evidence$1.div(LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(package.field$.MODULE$.literalIntMultiplicativeSemigroupOps(-2), this.evidence$3.log(s), this.evidence$1), s)));
      return this.evidence$1.plus(this.evidence$1.times(x, scale), mean$3);
   }

   public MarsagliaGaussian(final Field evidence$1, final NRoot evidence$2, final Trig evidence$3, final Order evidence$4, final Uniform evidence$5) {
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      this.evidence$3 = evidence$3;
      this.evidence$4 = evidence$4;
      this.evidence$5 = evidence$5;
      if (!this.specInstance$()) {
         this.u = Dist$.MODULE$.uniform(this.evidence$1.negate(package$.MODULE$.Field().apply(this.evidence$1).one()), package$.MODULE$.Field().apply(this.evidence$1).one(), this.evidence$5);
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
