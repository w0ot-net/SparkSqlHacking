package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001db\u0001\u0002\u000b\u0016\u0001rAQA\u000e\u0001\u0005\u0002]BQ!\u000f\u0001\u0005\u0002iBqa\u0013\u0001\u0002\u0002\u0013\u0005q\u0007C\u0004M\u0001\u0005\u0005I\u0011I'\t\u000fY\u0003\u0011\u0011!C\u0001/\"91\fAA\u0001\n\u0003a\u0006b\u00022\u0001\u0003\u0003%\te\u0019\u0005\bU\u0002\t\t\u0011\"\u0001l\u0011\u001d\u0001\b!!A\u0005BEDqa\u001d\u0001\u0002\u0002\u0013\u0005C\u000fC\u0004v\u0001\u0005\u0005I\u0011\t<\t\u000f]\u0004\u0011\u0011!C!q\u001e9!0FA\u0001\u0012\u0003Yha\u0002\u000b\u0016\u0003\u0003E\t\u0001 \u0005\u0007m9!\t!!\u0005\t\u000fUt\u0011\u0011!C#m\"A\u00111\u0003\b\u0002\u0002\u0013\u0005u\u0007C\u0005\u0002\u00169\t\t\u0011\"!\u0002\u0018!I\u0011Q\u0004\b\u0002\u0002\u0013%\u0011q\u0004\u0002\u0013!J|\u00070[7bY2{wMQ1se&,'O\u0003\u0002\u0017/\u0005A\u0001O]8yS6\fGN\u0003\u0002\u00193\u0005Aq\u000e\u001d;j[&TXMC\u0001\u001b\u0003\u0019\u0011'/Z3{K\u000e\u00011#\u0002\u0001\u001eG\u001dR\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"AB!osJ+g\r\u0005\u0002%K5\tQ#\u0003\u0002'+\tA\u0001K]8yS6\fG\u000e\u0005\u0002\u001fQ%\u0011\u0011f\b\u0002\b!J|G-^2u!\tY3G\u0004\u0002-c9\u0011Q\u0006M\u0007\u0002])\u0011qfG\u0001\u0007yI|w\u000e\u001e \n\u0003\u0001J!AM\u0010\u0002\u000fA\f7m[1hK&\u0011A'\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003e}\ta\u0001P5oSRtD#\u0001\u001d\u0011\u0005\u0011\u0002\u0011\u0001\u00029s_b$2a\u000f J!\tqB(\u0003\u0002>?\t!QK\\5u\u0011\u0015y$\u00011\u0001A\u0003\u0005A\bcA!E\r6\t!I\u0003\u0002D3\u00051A.\u001b8bY\u001eL!!\u0012\"\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003=\u001dK!\u0001S\u0010\u0003\r\u0011{WO\u00197f\u0011\u001dQ%\u0001%AA\u0002\u0019\u000b1A\u001d5p\u0003\u0011\u0019w\u000e]=\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005q\u0005CA(U\u001b\u0005\u0001&BA)S\u0003\u0011a\u0017M\\4\u000b\u0003M\u000bAA[1wC&\u0011Q\u000b\u0015\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003a\u0003\"AH-\n\u0005i{\"aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA/a!\tqb,\u0003\u0002`?\t\u0019\u0011I\\=\t\u000f\u00054\u0011\u0011!a\u00011\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012\u0001\u001a\t\u0004K\"lV\"\u00014\u000b\u0005\u001d|\u0012AC2pY2,7\r^5p]&\u0011\u0011N\u001a\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002m_B\u0011a$\\\u0005\u0003]~\u0011qAQ8pY\u0016\fg\u000eC\u0004b\u0011\u0005\u0005\t\u0019A/\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003\u001dJDq!Y\u0005\u0002\u0002\u0003\u0007\u0001,\u0001\u0005iCND7i\u001c3f)\u0005A\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00039\u000ba!Z9vC2\u001cHC\u00017z\u0011\u001d\tG\"!AA\u0002u\u000b!\u0003\u0015:pq&l\u0017\r\u001c'pO\n\u000b'O]5feB\u0011AED\n\u0005\u001du\f9\u0001\u0005\u0003\u007f\u0003\u0007AT\"A@\u000b\u0007\u0005\u0005q$A\u0004sk:$\u0018.\\3\n\u0007\u0005\u0015qPA\tBEN$(/Y2u\rVt7\r^5p]B\u0002B!!\u0003\u0002\u00105\u0011\u00111\u0002\u0006\u0004\u0003\u001b\u0011\u0016AA5p\u0013\r!\u00141\u0002\u000b\u0002w\u0006)\u0011\r\u001d9ms\u00069QO\\1qa2LHc\u00017\u0002\u001a!A\u00111\u0004\n\u0002\u0002\u0003\u0007\u0001(A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\t\u0011\u0007=\u000b\u0019#C\u0002\u0002&A\u0013aa\u00142kK\u000e$\b"
)
public class ProximalLogBarrier implements Proximal, Product, Serializable {
   public static boolean unapply(final ProximalLogBarrier x$0) {
      return ProximalLogBarrier$.MODULE$.unapply(x$0);
   }

   public static ProximalLogBarrier apply() {
      return ProximalLogBarrier$.MODULE$.apply();
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
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, (double)0.5F * (x.apply$mcD$sp(index$macro$2) + .MODULE$.sqrt(x.apply$mcD$sp(index$macro$2) * x.apply$mcD$sp(index$macro$2) + (double)4 / rho)));
      }

   }

   public ProximalLogBarrier copy() {
      return new ProximalLogBarrier();
   }

   public String productPrefix() {
      return "ProximalLogBarrier";
   }

   public int productArity() {
      return 0;
   }

   public Object productElement(final int x$1) {
      Object var2 = Statics.ioobe(x$1);
      return var2;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ProximalLogBarrier;
   }

   public String productElementName(final int x$1) {
      String var2 = (String)Statics.ioobe(x$1);
      return var2;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var2;
      if (x$1 instanceof ProximalLogBarrier) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2 && ((ProximalLogBarrier)x$1).canEqual(this);
   }

   public ProximalLogBarrier() {
      Proximal.$init$(this);
      Product.$init$(this);
   }
}
