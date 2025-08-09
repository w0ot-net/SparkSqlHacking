package breeze.optimize;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005)3A!\u0004\b\u0001'!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001a\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u001d)\u0003A1A\u0005\u0002\u0019BaA\u000b\u0001!\u0002\u00139\u0003bB\u0016\u0001\u0005\u0004%\tA\n\u0005\u0007Y\u0001\u0001\u000b\u0011B\u0014\t\u000b5\u0002A\u0011\u0001\u0018\t\u000fY\u0002\u0011\u0013!C\u0001o!)!\t\u0001C\u0001\u0007\"9\u0001\nAI\u0001\n\u00039\u0004bB%\u0001#\u0003%\ta\u000e\u0002\u0016'R\u0014xN\\4X_24W\rT5oKN+\u0017M]2i\u0015\ty\u0001#\u0001\u0005paRLW.\u001b>f\u0015\u0005\t\u0012A\u00022sK\u0016TXm\u0001\u0001\u0014\u0005\u0001!\u0002CA\u000b\u0017\u001b\u0005q\u0011BA\f\u000f\u0005=\u0019UOY5d\u0019&tWmU3be\u000eD\u0017aC7bqj{w.\\%uKJ\u0004\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u00111!\u00138u\u0003Ei\u0017\r\u001f'j]\u0016\u001cV-\u0019:dQ&#XM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\t\u001aC\u0005\u0005\u0002\u0016\u0001!)\u0001d\u0001a\u00013!)qd\u0001a\u00013\u0005\u00111-M\u000b\u0002OA\u0011!\u0004K\u0005\u0003Sm\u0011a\u0001R8vE2,\u0017aA22A\u0005\u00111MM\u0001\u0004GJ\u0002\u0013\u0001C7j]&l\u0017N_3\u0015\u0007\u001dzC\u0007C\u00031\u0011\u0001\u0007\u0011'A\u0001g!\r)\"gJ\u0005\u0003g9\u0011A\u0002R5gM\u001a+hn\u0019;j_:Dq!\u000e\u0005\u0011\u0002\u0003\u0007q%\u0001\u0003j]&$\u0018AE7j]&l\u0017N_3%I\u00164\u0017-\u001e7uII*\u0012\u0001\u000f\u0016\u0003OeZ\u0013A\u000f\t\u0003w\u0001k\u0011\u0001\u0010\u0006\u0003{y\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005}Z\u0012AC1o]>$\u0018\r^5p]&\u0011\u0011\t\u0010\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!E7j]&l\u0017N_3XSRD'i\\;oIR!q\u0005R#G\u0011\u0015\u0001$\u00021\u00012\u0011\u001d)$\u0002%AA\u0002\u001dBqa\u0012\u0006\u0011\u0002\u0003\u0007q%A\u0003c_VtG-A\u000enS:LW.\u001b>f/&$\bNQ8v]\u0012$C-\u001a4bk2$HEM\u0001\u001c[&t\u0017.\\5{K^KG\u000f\u001b\"pk:$G\u0005Z3gCVdG\u000fJ\u001a"
)
public class StrongWolfeLineSearch extends CubicLineSearch {
   private final int maxZoomIter;
   private final int maxLineSearchIter;
   private final double c1;
   private final double c2;

   public double c1() {
      return this.c1;
   }

   public double c2() {
      return this.c2;
   }

   public double minimize(final DiffFunction f, final double init) {
      return this.minimizeWithBound(f, init, Double.POSITIVE_INFINITY);
   }

   public double minimize$default$2() {
      return (double)1.0F;
   }

   public double minimizeWithBound(final DiffFunction f, final double init, final double bound) {
      Object var6 = new Object();

      try {
         .MODULE$.require(init <= bound, () -> "init value should <= bound");
         DoubleRef t = DoubleRef.create(init);
         ObjectRef low = ObjectRef.create(this.phi$1((double)0.0F, f));
         double fval = ((CubicLineSearch.Bracket)low.elem).fval();
         double dd = ((CubicLineSearch.Bracket)low.elem).dd();
         if (dd > (double)0) {
            throw new FirstOrderException((new StringBuilder(48)).append("Line search invoked with non-descent direction: ").append(dd).toString());
         } else {
            scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.maxLineSearchIter).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
               CubicLineSearch.Bracket c = this.phi$1(t.elem, f);
               if (!Double.isInfinite(c.fval()) && !Double.isNaN(c.fval())) {
                  if (c.fval() > fval + this.c1() * t.elem * dd || c.fval() >= ((CubicLineSearch.Bracket)low.elem).fval() && i > 0) {
                     this.logger().debug(() -> (new StringBuilder(28)).append("Line search t: ").append(t.elem).append(" fval: ").append(c.fval()).append(" cdd: ").append(c.dd()).toString());
                     throw new NonLocalReturnControl.mcD.sp(var6, this.zoom$1((CubicLineSearch.Bracket)low.elem, c, fval, dd, f));
                  }

                  if (scala.math.package..MODULE$.abs(c.dd()) <= this.c2() * scala.math.package..MODULE$.abs(dd)) {
                     throw new NonLocalReturnControl.mcD.sp(var6, c.t());
                  }

                  if (c.dd() >= (double)0) {
                     this.logger().debug(() -> (new StringBuilder(34)).append("Line search t: ").append(t.elem).append(" fval: ").append(c.fval()).append(" rhs: ").append(fval + this.c1() * t.elem * dd).append(" cdd: ").append(c.dd()).toString());
                     throw new NonLocalReturnControl.mcD.sp(var6, this.zoom$1(c, (CubicLineSearch.Bracket)low.elem, fval, dd, f));
                  }

                  low.elem = c;
                  if (t.elem == bound) {
                     this.logger().debug(() -> "Reach bound, satisfy sufficent decrease condition, but not curvature condition satisfied.");
                     throw new NonLocalReturnControl.mcD.sp(var6, bound);
                  }

                  t.elem *= (double)1.5F;
                  if (t.elem > bound) {
                     t.elem = bound;
                  }

                  this.logger().debug(() -> (new StringBuilder(84)).append("Sufficent Decrease condition but not curvature condition satisfied. Increased t to: ").append(t.elem).toString());
               } else {
                  t.elem /= (double)2.0F;
                  this.logger().error(() -> (new StringBuilder(71)).append("Encountered bad values in function evaluation. Decreasing step size to ").append(t.elem).toString());
               }

            });
            throw new FirstOrderException("Line search failed");
         }
      } catch (NonLocalReturnControl var14) {
         if (var14.key() == var6) {
            return var14.value$mcD$sp();
         } else {
            throw var14;
         }
      }
   }

   public double minimizeWithBound$default$2() {
      return (double)1.0F;
   }

   public double minimizeWithBound$default$3() {
      return (double)1.0F;
   }

   private final CubicLineSearch.Bracket phi$1(final double t, final DiffFunction f$1) {
      Tuple2 var6 = f$1.calculate(BoxesRunTime.boxToDouble(t));
      if (var6 != null) {
         double pval = var6._1$mcD$sp();
         double pdd = var6._2$mcD$sp();
         Tuple2.mcDD.sp var4 = new Tuple2.mcDD.sp(pval, pdd);
         double pval = ((Tuple2)var4)._1$mcD$sp();
         double pdd = ((Tuple2)var4)._2$mcD$sp();
         return new CubicLineSearch.Bracket(t, pdd, pval);
      } else {
         throw new MatchError(var6);
      }
   }

   private final double zoom$1(final CubicLineSearch.Bracket linit, final CubicLineSearch.Bracket rinit, final double fval$1, final double dd$1, final DiffFunction f$1) {
      Object var8 = new Object();

      try {
         ObjectRef low = ObjectRef.create(linit);
         ObjectRef hi = ObjectRef.create(rinit);
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.maxZoomIter).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            double t = ((CubicLineSearch.Bracket)low.elem).t() > ((CubicLineSearch.Bracket)hi.elem).t() ? this.interp((CubicLineSearch.Bracket)hi.elem, (CubicLineSearch.Bracket)low.elem) : this.interp((CubicLineSearch.Bracket)low.elem, (CubicLineSearch.Bracket)hi.elem);
            CubicLineSearch.Bracket c = this.phi$1(t, f$1);
            this.logger().info(() -> (new StringBuilder(34)).append("Line search t: ").append(t).append(" fval: ").append(c.fval()).append(" rhs: ").append(fval$1 + this.c1() * c.t() * dd$1).append(" cdd: ").append(c.dd()).toString());
            if (Double.isNaN(t)) {
               throw new FirstOrderException("Line search zoom failed");
            } else {
               if (!(c.fval() > fval$1 + this.c1() * c.t() * dd$1) && !(c.fval() >= ((CubicLineSearch.Bracket)low.elem).fval())) {
                  if (scala.math.package..MODULE$.abs(c.dd()) <= this.c2() * scala.math.package..MODULE$.abs(dd$1)) {
                     throw new NonLocalReturnControl.mcD.sp(var8, c.t());
                  }

                  if (c.dd() * (((CubicLineSearch.Bracket)hi.elem).t() - ((CubicLineSearch.Bracket)low.elem).t()) >= (double)0) {
                     this.logger().debug(() -> "flipping");
                     hi.elem = (CubicLineSearch.Bracket)low.elem;
                  }

                  this.logger().debug(() -> "low=c");
                  low.elem = c;
               } else {
                  hi.elem = c;
                  this.logger().debug(() -> "hi=c");
               }

            }
         });
         throw new FirstOrderException("Line search zoom failed");
      } catch (NonLocalReturnControl var12) {
         if (var12.key() == var8) {
            return var12.value$mcD$sp();
         } else {
            throw var12;
         }
      }
   }

   public StrongWolfeLineSearch(final int maxZoomIter, final int maxLineSearchIter) {
      this.maxZoomIter = maxZoomIter;
      this.maxLineSearchIter = maxLineSearchIter;
      this.c1 = 1.0E-4;
      this.c2 = 0.9;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
