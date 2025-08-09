package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001db\u0001\u0002\u000b\u0016\u0001rAQA\u000e\u0001\u0005\u0002]BQ!\u000f\u0001\u0005\u0002iBqa\u0013\u0001\u0002\u0002\u0013\u0005q\u0007C\u0004M\u0001\u0005\u0005I\u0011I'\t\u000fY\u0003\u0011\u0011!C\u0001/\"91\fAA\u0001\n\u0003a\u0006b\u00022\u0001\u0003\u0003%\te\u0019\u0005\bU\u0002\t\t\u0011\"\u0001l\u0011\u001d\u0001\b!!A\u0005BEDqa\u001d\u0001\u0002\u0002\u0013\u0005C\u000fC\u0004v\u0001\u0005\u0005I\u0011\t<\t\u000f]\u0004\u0011\u0011!C!q\u001e9!0FA\u0001\u0012\u0003Yha\u0002\u000b\u0016\u0003\u0003E\t\u0001 \u0005\u0007m9!\t!!\u0005\t\u000fUt\u0011\u0011!C#m\"A\u00111\u0003\b\u0002\u0002\u0013\u0005u\u0007C\u0005\u0002\u00169\t\t\u0011\"!\u0002\u0018!I\u0011Q\u0004\b\u0002\u0002\u0013%\u0011q\u0004\u0002\u000b!J|\u00070[7bY2\u0013$B\u0001\f\u0018\u0003!\u0001(o\u001c=j[\u0006d'B\u0001\r\u001a\u0003!y\u0007\u000f^5nSj,'\"\u0001\u000e\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019R\u0001A\u000f$O)\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007C\u0001\u0013&\u001b\u0005)\u0012B\u0001\u0014\u0016\u0005!\u0001&o\u001c=j[\u0006d\u0007C\u0001\u0010)\u0013\tIsDA\u0004Qe>$Wo\u0019;\u0011\u0005-\u001adB\u0001\u00172\u001d\ti\u0003'D\u0001/\u0015\ty3$\u0001\u0004=e>|GOP\u0005\u0002A%\u0011!gH\u0001\ba\u0006\u001c7.Y4f\u0013\t!TG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00023?\u00051A(\u001b8jiz\"\u0012\u0001\u000f\t\u0003I\u0001\tA\u0001\u001d:pqR\u00191HP%\u0011\u0005ya\u0014BA\u001f \u0005\u0011)f.\u001b;\t\u000b}\u0012\u0001\u0019\u0001!\u0002\u0003a\u00042!\u0011#G\u001b\u0005\u0011%BA\"\u001a\u0003\u0019a\u0017N\\1mO&\u0011QI\u0011\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002\u001f\u000f&\u0011\u0001j\b\u0002\u0007\t>,(\r\\3\t\u000f)\u0013\u0001\u0013!a\u0001\r\u0006\u0019!\u000f[8\u0002\t\r|\u0007/_\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u00039\u0003\"a\u0014+\u000e\u0003AS!!\u0015*\u0002\t1\fgn\u001a\u0006\u0002'\u0006!!.\u0019<b\u0013\t)\u0006K\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u00021B\u0011a$W\u0005\u00035~\u00111!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"!\u00181\u0011\u0005yq\u0016BA0 \u0005\r\te.\u001f\u0005\bC\u001a\t\t\u00111\u0001Y\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tA\rE\u0002fQvk\u0011A\u001a\u0006\u0003O~\t!bY8mY\u0016\u001cG/[8o\u0013\tIgM\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u00017p!\tqR.\u0003\u0002o?\t9!i\\8mK\u0006t\u0007bB1\t\u0003\u0003\u0005\r!X\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002Oe\"9\u0011-CA\u0001\u0002\u0004A\u0016\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003a\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u001d\u00061Q-];bYN$\"\u0001\\=\t\u000f\u0005d\u0011\u0011!a\u0001;\u0006Q\u0001K]8yS6\fG\u000e\u0014\u001a\u0011\u0005\u0011r1\u0003\u0002\b~\u0003\u000f\u0001BA`A\u0002q5\tqPC\u0002\u0002\u0002}\tqA];oi&lW-C\u0002\u0002\u0006}\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c81!\u0011\tI!a\u0004\u000e\u0005\u0005-!bAA\u0007%\u0006\u0011\u0011n\\\u0005\u0004i\u0005-A#A>\u0002\u000b\u0005\u0004\b\u000f\\=\u0002\u000fUt\u0017\r\u001d9msR\u0019A.!\u0007\t\u0011\u0005m!#!AA\u0002a\n1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\t\u0003E\u0002P\u0003GI1!!\nQ\u0005\u0019y%M[3di\u0002"
)
public class ProximalL2 implements Proximal, Product, Serializable {
   public static boolean unapply(final ProximalL2 x$0) {
      return ProximalL2$.MODULE$.unapply(x$0);
   }

   public static ProximalL2 apply() {
      return ProximalL2$.MODULE$.apply();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double prox$default$2() {
      return Proximal.prox$default$2$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public void prox(final DenseVector x, final double rho) {
      double xnorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(x, norm$.MODULE$.normDoubleToNormalNorm(norm$.MODULE$.canNorm(HasOps$.MODULE$.DV_canIterateValues(), norm$.MODULE$.scalarNorm_Double()))));
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         if (xnorm >= (double)1 / rho) {
            x.update$mcD$sp(index$macro$2, x.apply$mcD$sp(index$macro$2) * ((double)1 - (double)1 / (rho * xnorm)));
         } else {
            x.update$mcD$sp(index$macro$2, (double)0.0F);
         }
      }

   }

   public ProximalL2 copy() {
      return new ProximalL2();
   }

   public String productPrefix() {
      return "ProximalL2";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ProximalL2;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof ProximalL2) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((ProximalL2)x$1).canEqual(this);
   }

   public ProximalL2() {
      Proximal.$init$(this);
      Product.$init$(this);
   }
}
