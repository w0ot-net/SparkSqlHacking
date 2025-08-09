package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001\u0002\u000f\u001e\u0001\u0012B\u0001B\u0010\u0001\u0003\u0012\u0004%\ta\u0010\u0005\t\u0007\u0002\u0011\t\u0019!C\u0001\t\"A!\n\u0001B\tB\u0003&\u0001\tC\u0003L\u0001\u0011\u0005A\nC\u0003P\u0001\u0011\u0005\u0001\u000bC\u0003S\u0001\u0011\u00051\u000bC\u0003_\u0001\u0011\u0005s\fC\u0004b\u0001\u0005\u0005I\u0011\u00012\t\u000f\u0011\u0004\u0011\u0013!C\u0001K\"9\u0001\u000fAA\u0001\n\u0003\n\bb\u0002>\u0001\u0003\u0003%\ta\u001f\u0005\t\u007f\u0002\t\t\u0011\"\u0001\u0002\u0002!I\u00111\u0002\u0001\u0002\u0002\u0013\u0005\u0013Q\u0002\u0005\n\u00037\u0001\u0011\u0011!C\u0001\u0003;A\u0011\"a\n\u0001\u0003\u0003%\t%!\u000b\t\u0013\u00055\u0002!!A\u0005B\u0005=\u0002\"CA\u0019\u0001\u0005\u0005I\u0011IA\u001a\u0011%\t)\u0004AA\u0001\n\u0003\n9dB\u0005\u0002<u\t\t\u0011#\u0001\u0002>\u0019AA$HA\u0001\u0012\u0003\ty\u0004\u0003\u0004L)\u0011\u0005\u0011q\u000b\u0005\n\u0003c!\u0012\u0011!C#\u0003gA\u0011\"!\u0017\u0015\u0003\u0003%\t)a\u0017\t\u0011\u0005}C#%A\u0005\u0002\u0015D\u0011\"!\u0019\u0015\u0003\u0003%\t)a\u0019\t\u0011\u0005=D#%A\u0005\u0002\u0015D\u0011\"!\u001d\u0015\u0003\u0003%I!a\u001d\u0003\u0015A\u0013x\u000e_5nC2d\u0015G\u0003\u0002\u001f?\u0005A\u0001O]8yS6\fGN\u0003\u0002!C\u0005Aq\u000e\u001d;j[&TXMC\u0001#\u0003\u0019\u0011'/Z3{K\u000e\u00011#\u0002\u0001&W=\u0012\u0004C\u0001\u0014*\u001b\u00059#\"\u0001\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005):#AB!osJ+g\r\u0005\u0002-[5\tQ$\u0003\u0002/;\tA\u0001K]8yS6\fG\u000e\u0005\u0002'a%\u0011\u0011g\n\u0002\b!J|G-^2u!\t\u00194H\u0004\u00025s9\u0011Q\u0007O\u0007\u0002m)\u0011qgI\u0001\u0007yI|w\u000e\u001e \n\u0003!J!AO\u0014\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003u\u001d\na\u0001\\1nE\u0012\fW#\u0001!\u0011\u0005\u0019\n\u0015B\u0001\"(\u0005\u0019!u.\u001e2mK\u0006QA.Y7cI\u0006|F%Z9\u0015\u0005\u0015C\u0005C\u0001\u0014G\u0013\t9uE\u0001\u0003V]&$\bbB%\u0003\u0003\u0003\u0005\r\u0001Q\u0001\u0004q\u0012\n\u0014a\u00027b[\n$\u0017\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00055s\u0005C\u0001\u0017\u0001\u0011\u001dqD\u0001%AA\u0002\u0001\u000b\u0011b]3u\u0019\u0006l'\rZ1\u0015\u00055\u000b\u0006\"\u0002 \u0006\u0001\u0004\u0001\u0015\u0001\u00029s_b$2!\u0012+]\u0011\u0015)f\u00011\u0001W\u0003\u0005A\bcA,[\u00016\t\u0001L\u0003\u0002ZC\u00051A.\u001b8bY\u001eL!a\u0017-\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\u0005\b;\u001a\u0001\n\u00111\u0001A\u0003\r\u0011\bn\\\u0001\bm\u0006dW/Z!u)\t\u0001\u0005\rC\u0003V\u000f\u0001\u0007a+\u0001\u0003d_BLHCA'd\u0011\u001dq\u0004\u0002%AA\u0002\u0001\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001gU\t\u0001umK\u0001i!\tIg.D\u0001k\u0015\tYG.A\u0005v]\u000eDWmY6fI*\u0011QnJ\u0001\u000bC:tw\u000e^1uS>t\u0017BA8k\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003I\u0004\"a\u001d=\u000e\u0003QT!!\u001e<\u0002\t1\fgn\u001a\u0006\u0002o\u0006!!.\u0019<b\u0013\tIHO\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002yB\u0011a%`\u0005\u0003}\u001e\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0001\u0002\nA\u0019a%!\u0002\n\u0007\u0005\u001dqEA\u0002B]fDq!\u0013\u0007\u0002\u0002\u0003\u0007A0A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ty\u0001\u0005\u0004\u0002\u0012\u0005]\u00111A\u0007\u0003\u0003'Q1!!\u0006(\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u00033\t\u0019B\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0010\u0003K\u00012AJA\u0011\u0013\r\t\u0019c\n\u0002\b\u0005>|G.Z1o\u0011!Ie\"!AA\u0002\u0005\r\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A]A\u0016\u0011\u001dIu\"!AA\u0002q\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002y\u0006AAo\\*ue&tw\rF\u0001s\u0003\u0019)\u0017/^1mgR!\u0011qDA\u001d\u0011!I%#!AA\u0002\u0005\r\u0011A\u0003)s_bLW.\u00197McA\u0011A\u0006F\n\u0006)\u0005\u0005\u0013Q\n\t\u0007\u0003\u0007\nI\u0005Q'\u000e\u0005\u0005\u0015#bAA$O\u00059!/\u001e8uS6,\u0017\u0002BA&\u0003\u000b\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\ty%!\u0016\u000e\u0005\u0005E#bAA*m\u0006\u0011\u0011n\\\u0005\u0004y\u0005ECCAA\u001f\u0003\u0015\t\u0007\u000f\u001d7z)\ri\u0015Q\f\u0005\b}]\u0001\n\u00111\u0001A\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\n\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003K\nY\u0007\u0005\u0003'\u0003O\u0002\u0015bAA5O\t1q\n\u001d;j_:D\u0001\"!\u001c\u001a\u0003\u0003\u0005\r!T\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002vA\u00191/a\u001e\n\u0007\u0005eDO\u0001\u0004PE*,7\r\u001e"
)
public class ProximalL1 implements Proximal, Product, Serializable {
   private double lambda;

   public static double $lessinit$greater$default$1() {
      return ProximalL1$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final ProximalL1 x$0) {
      return ProximalL1$.MODULE$.unapply(x$0);
   }

   public static double apply$default$1() {
      return ProximalL1$.MODULE$.apply$default$1();
   }

   public static ProximalL1 apply(final double lambda) {
      return ProximalL1$.MODULE$.apply(lambda);
   }

   public static Function1 andThen(final Function1 g) {
      return ProximalL1$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ProximalL1$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double prox$default$2() {
      return Proximal.prox$default$2$(this);
   }

   public double lambda() {
      return this.lambda;
   }

   public void lambda_$eq(final double x$1) {
      this.lambda = x$1;
   }

   public ProximalL1 setLambda(final double lambda) {
      this.lambda_$eq(lambda);
      return this;
   }

   public void prox(final DenseVector x, final double rho) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, .MODULE$.max((double)0.0F, x.apply$mcD$sp(index$macro$2) - this.lambda() / rho) - .MODULE$.max((double)0.0F, -x.apply$mcD$sp(index$macro$2) - this.lambda() / rho));
      }

   }

   public double valueAt(final DenseVector x) {
      return this.lambda() * BoxesRunTime.unboxToDouble(x.foldLeft$mcD$sp(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(agg, entry) -> agg + .MODULE$.abs(entry)));
   }

   public ProximalL1 copy(final double lambda) {
      return new ProximalL1(lambda);
   }

   public double copy$default$1() {
      return this.lambda();
   }

   public String productPrefix() {
      return "ProximalL1";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.lambda());
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
      return x$1 instanceof ProximalL1;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "lambda";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.lambda()));
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
            if (x$1 instanceof ProximalL1) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               ProximalL1 var4 = (ProximalL1)x$1;
               if (this.lambda() == var4.lambda() && var4.canEqual(this)) {
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

   public ProximalL1(final double lambda) {
      this.lambda = lambda;
      super();
      Proximal.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
