package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.operators.HasOps$;
import breeze.numerics.package;
import breeze.numerics.package$abs$absDoubleImpl$;
import breeze.numerics.package$signum$signumDoubleImpl$;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=d\u0001\u0002\u000e\u001c\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\t\u0001b\u0001\n\u00039\u0005BB&\u0001A\u0003%\u0001\nC\u0003M\u0001\u0011\u0005Q\nC\u0004\\\u0001E\u0005I\u0011\u0001/\t\u000f\u001d\u0004\u0011\u0011!C\u0001Q\"9!\u000eAI\u0001\n\u0003a\u0006bB6\u0001\u0003\u0003%\t\u0005\u001c\u0005\bk\u0002\t\t\u0011\"\u0001w\u0011\u001dQ\b!!A\u0005\u0002mD\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\t\u0013\u0005M\u0001!!A\u0005\u0002\u0005U\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u0011%\t)\u0003AA\u0001\n\u0003\n9\u0003C\u0005\u0002*\u0001\t\t\u0011\"\u0011\u0002,!I\u0011Q\u0006\u0001\u0002\u0002\u0013\u0005\u0013qF\u0004\n\u0003gY\u0012\u0011!E\u0001\u0003k1\u0001BG\u000e\u0002\u0002#\u0005\u0011q\u0007\u0005\u0007\u0005R!\t!a\u0014\t\u0013\u0005%B#!A\u0005F\u0005-\u0002\"CA))\u0005\u0005I\u0011QA*\u0011%\t9\u0006FA\u0001\n\u0003\u000bI\u0006C\u0005\u0002fQ\t\t\u0011\"\u0003\u0002h\tI\u0001K]8kK\u000e$H*\r\u0006\u00039u\t\u0001\u0002\u001d:pq&l\u0017\r\u001c\u0006\u0003=}\t\u0001b\u001c9uS6L'0\u001a\u0006\u0002A\u00051!M]3fu\u0016\u001c\u0001aE\u0003\u0001G%j\u0003\u0007\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003U-j\u0011aG\u0005\u0003Ym\u0011\u0001\u0002\u0015:pq&l\u0017\r\u001c\t\u0003I9J!aL\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011'\u000f\b\u0003e]r!a\r\u001c\u000e\u0003QR!!N\u0011\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0013B\u0001\u001d&\u0003\u001d\u0001\u0018mY6bO\u0016L!AO\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005a*\u0013!A:\u0016\u0003y\u0002\"\u0001J \n\u0005\u0001+#A\u0002#pk\ndW-\u0001\u0002tA\u00051A(\u001b8jiz\"\"\u0001R#\u0011\u0005)\u0002\u0001\"\u0002\u001f\u0004\u0001\u0004q\u0014A\u00049s_*,7\r^*j[BdW\r_\u000b\u0002\u0011B\u0011!&S\u0005\u0003\u0015n\u0011\u0011\u0004\u0015:pU\u0016\u001cG\u000f\u0015:pE\u0006\u0014\u0017\u000e\\5usNKW\u000e\u001d7fq\u0006y\u0001O]8kK\u000e$8+[7qY\u0016D\b%\u0001\u0003qe>DHc\u0001(R3B\u0011AeT\u0005\u0003!\u0016\u0012A!\u00168ji\")!K\u0002a\u0001'\u0006\t\u0001\u0010E\u0002U/zj\u0011!\u0016\u0006\u0003-~\ta\u0001\\5oC2<\u0017B\u0001-V\u0005-!UM\\:f-\u0016\u001cGo\u001c:\t\u000fi3\u0001\u0013!a\u0001}\u0005\u0019!\u000f[8\u0002\u001dA\u0014x\u000e\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tQL\u000b\u0002?=.\nq\f\u0005\u0002aK6\t\u0011M\u0003\u0002cG\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003I\u0016\n!\"\u00198o_R\fG/[8o\u0013\t1\u0017MA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAaY8qsR\u0011A)\u001b\u0005\by!\u0001\n\u00111\u0001?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A7\u0011\u00059\u001cX\"A8\u000b\u0005A\f\u0018\u0001\u00027b]\u001eT\u0011A]\u0001\u0005U\u00064\u0018-\u0003\u0002u_\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a\u001e\t\u0003IaL!!_\u0013\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005q|\bC\u0001\u0013~\u0013\tqXEA\u0002B]fD\u0001\"!\u0001\r\u0003\u0003\u0005\ra^\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005\u001d\u0001#BA\u0005\u0003\u001faXBAA\u0006\u0015\r\ti!J\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA\t\u0003\u0017\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qCA\u000f!\r!\u0013\u0011D\u0005\u0004\u00037)#a\u0002\"p_2,\u0017M\u001c\u0005\t\u0003\u0003q\u0011\u0011!a\u0001y\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\ri\u00171\u0005\u0005\t\u0003\u0003y\u0011\u0011!a\u0001o\u0006A\u0001.Y:i\u0007>$W\rF\u0001x\u0003!!xn\u0015;sS:<G#A7\u0002\r\u0015\fX/\u00197t)\u0011\t9\"!\r\t\u0011\u0005\u0005!#!AA\u0002q\f\u0011\u0002\u0015:pU\u0016\u001cG\u000fT\u0019\u0011\u0005)\"2#\u0002\u000b\u0002:\u0005\u0015\u0003CBA\u001e\u0003\u0003rD)\u0004\u0002\u0002>)\u0019\u0011qH\u0013\u0002\u000fI,h\u000e^5nK&!\u00111IA\u001f\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003\u000f\ni%\u0004\u0002\u0002J)\u0019\u00111J9\u0002\u0005%|\u0017b\u0001\u001e\u0002JQ\u0011\u0011QG\u0001\u0006CB\u0004H.\u001f\u000b\u0004\t\u0006U\u0003\"\u0002\u001f\u0018\u0001\u0004q\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u00037\n\t\u0007\u0005\u0003%\u0003;r\u0014bAA0K\t1q\n\u001d;j_:D\u0001\"a\u0019\u0019\u0003\u0003\u0005\r\u0001R\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA5!\rq\u00171N\u0005\u0004\u0003[z'AB(cU\u0016\u001cG\u000f"
)
public class ProjectL1 implements Proximal, Product, Serializable {
   private final double s;
   private final ProjectProbabilitySimplex projectSimplex;

   public static Option unapply(final ProjectL1 x$0) {
      return ProjectL1$.MODULE$.unapply(x$0);
   }

   public static ProjectL1 apply(final double s) {
      return ProjectL1$.MODULE$.apply(s);
   }

   public static Function1 andThen(final Function1 g) {
      return ProjectL1$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ProjectL1$.MODULE$.compose(g);
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

   public ProjectProbabilitySimplex projectSimplex() {
      return this.projectSimplex;
   }

   public void prox(final DenseVector x, final double rho) {
      DenseVector u = (DenseVector)package.abs$.MODULE$.apply(x, HasOps$.MODULE$.fromLowOrderCanMapActiveValues(DenseVector$.MODULE$.DV_scalarOf(), package$abs$absDoubleImpl$.MODULE$, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double())));
      this.projectSimplex().prox(u, rho);
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, package.signum$.MODULE$.apply$mDDc$sp(x.apply$mcD$sp(index$macro$2), package$signum$signumDoubleImpl$.MODULE$) * u.apply$mcD$sp(index$macro$2));
      }

   }

   public double prox$default$2() {
      return (double)1.0F;
   }

   public ProjectL1 copy(final double s) {
      return new ProjectL1(s);
   }

   public double copy$default$1() {
      return this.s();
   }

   public String productPrefix() {
      return "ProjectL1";
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
      return x$1 instanceof ProjectL1;
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
            if (x$1 instanceof ProjectL1) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               ProjectL1 var4 = (ProjectL1)x$1;
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

   public ProjectL1(final double s) {
      this.s = s;
      Proximal.$init$(this);
      Product.$init$(this);
      this.projectSimplex = new ProjectProbabilitySimplex(s);
   }
}
