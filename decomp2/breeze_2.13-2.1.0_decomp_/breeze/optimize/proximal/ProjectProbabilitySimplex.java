package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.operators.HasOps$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}c\u0001\u0002\r\u001a\u0001\u0002B\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!)\u0001\t\u0001C\u0001\u0003\")A\t\u0001C\u0001\u000b\"91\u000bAI\u0001\n\u0003!\u0006bB0\u0001\u0003\u0003%\t\u0001\u0019\u0005\bE\u0002\t\n\u0011\"\u0001U\u0011\u001d\u0019\u0007!!A\u0005B\u0011Dq!\u001c\u0001\u0002\u0002\u0013\u0005a\u000eC\u0004s\u0001\u0005\u0005I\u0011A:\t\u000fe\u0004\u0011\u0011!C!u\"I\u00111\u0001\u0001\u0002\u0002\u0013\u0005\u0011Q\u0001\u0005\n\u0003\u001f\u0001\u0011\u0011!C!\u0003#A\u0011\"!\u0006\u0001\u0003\u0003%\t%a\u0006\t\u0013\u0005e\u0001!!A\u0005B\u0005m\u0001\"CA\u000f\u0001\u0005\u0005I\u0011IA\u0010\u000f%\t\u0019#GA\u0001\u0012\u0003\t)C\u0002\u0005\u00193\u0005\u0005\t\u0012AA\u0014\u0011\u0019\u0001%\u0003\"\u0001\u0002@!I\u0011\u0011\u0004\n\u0002\u0002\u0013\u0015\u00131\u0004\u0005\n\u0003\u0003\u0012\u0012\u0011!CA\u0003\u0007B\u0011\"a\u0012\u0013\u0003\u0003%\t)!\u0013\t\u0013\u0005U##!A\u0005\n\u0005]#!\u0007)s_*,7\r\u001e)s_\n\f'-\u001b7jif\u001c\u0016.\u001c9mKbT!AG\u000e\u0002\u0011A\u0014x\u000e_5nC2T!\u0001H\u000f\u0002\u0011=\u0004H/[7ju\u0016T\u0011AH\u0001\u0007EJ,WM_3\u0004\u0001M)\u0001!I\u0014,]A\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1\u0011I\\=SK\u001a\u0004\"\u0001K\u0015\u000e\u0003eI!AK\r\u0003\u0011A\u0013x\u000e_5nC2\u0004\"A\t\u0017\n\u00055\u001a#a\u0002)s_\u0012,8\r\u001e\t\u0003_]r!\u0001M\u001b\u000f\u0005E\"T\"\u0001\u001a\u000b\u0005Mz\u0012A\u0002\u001fs_>$h(C\u0001%\u0013\t14%A\u0004qC\u000e\\\u0017mZ3\n\u0005aJ$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001c$\u0003\u0005\u0019X#\u0001\u001f\u0011\u0005\tj\u0014B\u0001 $\u0005\u0019!u.\u001e2mK\u0006\u00111\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\u001b\u0005C\u0001\u0015\u0001\u0011\u0015Q4\u00011\u0001=\u0003\u0011\u0001(o\u001c=\u0015\u0007\u0019K\u0015\u000b\u0005\u0002#\u000f&\u0011\u0001j\t\u0002\u0005+:LG\u000fC\u0003K\t\u0001\u00071*A\u0001y!\rau\nP\u0007\u0002\u001b*\u0011a*H\u0001\u0007Y&t\u0017\r\\4\n\u0005Ak%a\u0003#f]N,g+Z2u_JDqA\u0015\u0003\u0011\u0002\u0003\u0007A(A\u0002sQ>\fa\u0002\u001d:pq\u0012\"WMZ1vYR$#'F\u0001VU\tadkK\u0001X!\tAV,D\u0001Z\u0015\tQ6,A\u0005v]\u000eDWmY6fI*\u0011AlI\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00010Z\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002CC\"9!H\u0002I\u0001\u0002\u0004a\u0014AD2paf$C-\u001a4bk2$H%M\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003\u0015\u0004\"AZ6\u000e\u0003\u001dT!\u0001[5\u0002\t1\fgn\u001a\u0006\u0002U\u0006!!.\u0019<b\u0013\tawM\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002_B\u0011!\u0005]\u0005\u0003c\u000e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001^<\u0011\u0005\t*\u0018B\u0001<$\u0005\r\te.\u001f\u0005\bq*\t\t\u00111\u0001p\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t1\u0010E\u0002}\u007fRl\u0011! \u0006\u0003}\u000e\n!bY8mY\u0016\u001cG/[8o\u0013\r\t\t! \u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\b\u00055\u0001c\u0001\u0012\u0002\n%\u0019\u00111B\u0012\u0003\u000f\t{w\u000e\\3b]\"9\u0001\u0010DA\u0001\u0002\u0004!\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2!ZA\n\u0011\u001dAX\"!AA\u0002=\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002_\u0006AAo\\*ue&tw\rF\u0001f\u0003\u0019)\u0017/^1mgR!\u0011qAA\u0011\u0011\u001dA\b#!AA\u0002Q\f\u0011\u0004\u0015:pU\u0016\u001cG\u000f\u0015:pE\u0006\u0014\u0017\u000e\\5usNKW\u000e\u001d7fqB\u0011\u0001FE\n\u0006%\u0005%\u0012Q\u0007\t\u0007\u0003W\t\t\u0004\u0010\"\u000e\u0005\u00055\"bAA\u0018G\u00059!/\u001e8uS6,\u0017\u0002BA\u001a\u0003[\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t9$!\u0010\u000e\u0005\u0005e\"bAA\u001eS\u0006\u0011\u0011n\\\u0005\u0004q\u0005eBCAA\u0013\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0011\u0015Q\t\u0005\u0006uU\u0001\r\u0001P\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tY%!\u0015\u0011\t\t\ni\u0005P\u0005\u0004\u0003\u001f\u001a#AB(qi&|g\u000e\u0003\u0005\u0002TY\t\t\u00111\u0001C\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u00033\u00022AZA.\u0013\r\tif\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class ProjectProbabilitySimplex implements Proximal, Product, Serializable {
   private final double s;

   public static Option unapply(final ProjectProbabilitySimplex x$0) {
      return ProjectProbabilitySimplex$.MODULE$.unapply(x$0);
   }

   public static ProjectProbabilitySimplex apply(final double s) {
      return ProjectProbabilitySimplex$.MODULE$.apply(s);
   }

   public static Function1 andThen(final Function1 g) {
      return ProjectProbabilitySimplex$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ProjectProbabilitySimplex$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public double s() {
      return this.s;
   }

   public void prox(final DenseVector x, final double rho) {
      double[] sorted = (double[]).MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps(x.data$mcD$sp()), scala.package..MODULE$.Ordering().apply(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).reverse());
      double[] cum = (double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps((double[]).MODULE$.scanLeft$extension(scala.Predef..MODULE$.doubleArrayOps(sorted), BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(x$1, x$2) -> x$1 + x$2, scala.reflect.ClassTag..MODULE$.Double())), 1, x.length() + 1);
      DenseVector cs = DenseVector$.MODULE$.apply$mDc$sp((double[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.doubleArrayOps(cum))), (elem) -> BoxesRunTime.boxToDouble($anonfun$prox$2(this, elem)), scala.reflect.ClassTag..MODULE$.Double()));
      int ndx = .MODULE$.count$extension(scala.Predef..MODULE$.doubleArrayOps(((DenseVector)DenseVector$.MODULE$.apply$mDc$sp(sorted).$minus(cs, HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double())).data$mcD$sp()), (JFunction1.mcZD.sp)(x$3) -> x$3 >= (double)0.0F) - 1;
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, scala.math.package..MODULE$.max(x.apply$mcD$sp(index$macro$2) - cs.apply$mcD$sp(ndx), (double)0.0F));
      }

   }

   public double prox$default$2() {
      return (double)1.0F;
   }

   public ProjectProbabilitySimplex copy(final double s) {
      return new ProjectProbabilitySimplex(s);
   }

   public double copy$default$1() {
      return this.s();
   }

   public String productPrefix() {
      return "ProjectProbabilitySimplex";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.s());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ProjectProbabilitySimplex;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "s";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.s()));
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof ProjectProbabilitySimplex) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               ProjectProbabilitySimplex var4 = (ProjectProbabilitySimplex)x$1;
               if (this.s() == var4.s() && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final double $anonfun$prox$2(final ProjectProbabilitySimplex $this, final Tuple2 elem) {
      return (elem._1$mcD$sp() - $this.s()) / (double)(elem._2$mcI$sp() + 1);
   }

   public ProjectProbabilitySimplex(final double s) {
      this.s = s;
      Proximal.$init$(this);
      Product.$init$(this);
      boolean cond$macro$1 = s > (double)0;
      if (!cond$macro$1) {
         throw new IllegalArgumentException((new StringBuilder(59)).append("requirement failed: ").append("Proximal:ProjectProbabilitySimplex Radius s must be strictly positive").append(": ").append("ProjectProbabilitySimplex.this.s.>(0)").toString());
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
