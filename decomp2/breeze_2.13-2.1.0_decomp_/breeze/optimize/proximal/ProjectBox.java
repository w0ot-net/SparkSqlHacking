package breeze.optimize.proximal;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001B\u000e\u001d\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0011\u0002\u0011\t\u0012)A\u0005\u007f!A\u0011\n\u0001BK\u0002\u0013\u0005a\b\u0003\u0005K\u0001\tE\t\u0015!\u0003@\u0011\u0015Y\u0005\u0001\"\u0001M\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u001dI\u0006!%A\u0005\u0002iCq!\u001a\u0001\u0002\u0002\u0013\u0005a\rC\u0004j\u0001E\u0005I\u0011\u00016\t\u000f1\u0004\u0011\u0013!C\u0001U\"9Q\u000eAA\u0001\n\u0003r\u0007bB<\u0001\u0003\u0003%\t\u0001\u001f\u0005\by\u0002\t\t\u0011\"\u0001~\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0001\u0002\u001a!I\u00111\u0005\u0001\u0002\u0002\u0013\u0005\u0013Q\u0005\u0005\n\u0003S\u0001\u0011\u0011!C!\u0003WA\u0011\"!\f\u0001\u0003\u0003%\t%a\f\t\u0013\u0005E\u0002!!A\u0005B\u0005Mr!CA\u001c9\u0005\u0005\t\u0012AA\u001d\r!YB$!A\t\u0002\u0005m\u0002BB&\u0016\t\u0003\t\u0019\u0006C\u0005\u0002.U\t\t\u0011\"\u0012\u00020!I\u0011QK\u000b\u0002\u0002\u0013\u0005\u0015q\u000b\u0005\n\u0003;*\u0012\u0011!CA\u0003?B\u0011\"!\u001d\u0016\u0003\u0003%I!a\u001d\u0003\u0015A\u0013xN[3di\n{\u0007P\u0003\u0002\u001e=\u0005A\u0001O]8yS6\fGN\u0003\u0002 A\u0005Aq\u000e\u001d;j[&TXMC\u0001\"\u0003\u0019\u0011'/Z3{K\u000e\u00011#\u0002\u0001%U9\n\u0004CA\u0013)\u001b\u00051#\"A\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005%2#AB!osJ+g\r\u0005\u0002,Y5\tA$\u0003\u0002.9\tA\u0001K]8yS6\fG\u000e\u0005\u0002&_%\u0011\u0001G\n\u0002\b!J|G-^2u!\t\u0011$H\u0004\u00024q9\u0011AgN\u0007\u0002k)\u0011aGI\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dJ!!\u000f\u0014\u0002\u000fA\f7m[1hK&\u00111\b\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003s\u0019\n\u0011\u0001\\\u000b\u0002\u007fA\u0019\u0001iQ#\u000e\u0003\u0005S!A\u0011\u0011\u0002\r1Lg.\u00197h\u0013\t!\u0015IA\u0006EK:\u001cXMV3di>\u0014\bCA\u0013G\u0013\t9eE\u0001\u0004E_V\u0014G.Z\u0001\u0003Y\u0002\n\u0011!^\u0001\u0003k\u0002\na\u0001P5oSRtDcA'O\u001fB\u00111\u0006\u0001\u0005\u0006{\u0015\u0001\ra\u0010\u0005\u0006\u0013\u0016\u0001\raP\u0001\u0005aJ|\u0007\u0010F\u0002S+^\u0003\"!J*\n\u0005Q3#\u0001B+oSRDQA\u0016\u0004A\u0002}\n\u0011\u0001\u001f\u0005\b1\u001a\u0001\n\u00111\u0001F\u0003\r\u0011\bn\\\u0001\u000faJ|\u0007\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005Y&FA#]W\u0005i\u0006C\u00010d\u001b\u0005y&B\u00011b\u0003%)hn\u00195fG.,GM\u0003\u0002cM\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011|&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006!1m\u001c9z)\riu\r\u001b\u0005\b{!\u0001\n\u00111\u0001@\u0011\u001dI\u0005\u0002%AA\u0002}\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001lU\tyD,\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005y\u0007C\u00019v\u001b\u0005\t(B\u0001:t\u0003\u0011a\u0017M\\4\u000b\u0003Q\fAA[1wC&\u0011a/\u001d\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003e\u0004\"!\n>\n\u0005m4#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$Hc\u0001@\u0002\u0004A\u0011Qe`\u0005\u0004\u0003\u00031#aA!os\"A\u0011QA\u0007\u0002\u0002\u0003\u0007\u00110A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0017\u0001R!!\u0004\u0002\u0014yl!!a\u0004\u000b\u0007\u0005Ea%\u0001\u0006d_2dWm\u0019;j_:LA!!\u0006\u0002\u0010\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY\"!\t\u0011\u0007\u0015\ni\"C\u0002\u0002 \u0019\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\u0006=\t\t\u00111\u0001\u007f\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007=\f9\u0003\u0003\u0005\u0002\u0006A\t\t\u00111\u0001z\u0003!A\u0017m\u001d5D_\u0012,G#A=\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\\\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005m\u0011Q\u0007\u0005\t\u0003\u000b\u0019\u0012\u0011!a\u0001}\u0006Q\u0001K]8kK\u000e$(i\u001c=\u0011\u0005-*2#B\u000b\u0002>\u0005%\u0003cBA \u0003\u000bzt(T\u0007\u0003\u0003\u0003R1!a\u0011'\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0012\u0002B\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\u0005-\u0013\u0011K\u0007\u0003\u0003\u001bR1!a\u0014t\u0003\tIw.C\u0002<\u0003\u001b\"\"!!\u000f\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000b5\u000bI&a\u0017\t\u000buB\u0002\u0019A \t\u000b%C\u0002\u0019A \u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011MA7!\u0015)\u00131MA4\u0013\r\t)G\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b\u0015\nIgP \n\u0007\u0005-dE\u0001\u0004UkBdWM\r\u0005\t\u0003_J\u0012\u0011!a\u0001\u001b\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005U\u0004c\u00019\u0002x%\u0019\u0011\u0011P9\u0003\r=\u0013'.Z2u\u0001"
)
public class ProjectBox implements Proximal, Product, Serializable {
   private final DenseVector l;
   private final DenseVector u;

   public static Option unapply(final ProjectBox x$0) {
      return ProjectBox$.MODULE$.unapply(x$0);
   }

   public static ProjectBox apply(final DenseVector l, final DenseVector u) {
      return ProjectBox$.MODULE$.apply(l, u);
   }

   public static Function1 tupled() {
      return ProjectBox$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ProjectBox$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double valueAt(final DenseVector x) {
      return Proximal.valueAt$(this, x);
   }

   public DenseVector l() {
      return this.l;
   }

   public DenseVector u() {
      return this.u;
   }

   public void prox(final DenseVector x, final double rho) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, .MODULE$.max(this.l().apply$mcD$sp(index$macro$2), .MODULE$.min(x.apply$mcD$sp(index$macro$2), this.u().apply$mcD$sp(index$macro$2))));
      }

   }

   public double prox$default$2() {
      return (double)0.0F;
   }

   public ProjectBox copy(final DenseVector l, final DenseVector u) {
      return new ProjectBox(l, u);
   }

   public DenseVector copy$default$1() {
      return this.l();
   }

   public DenseVector copy$default$2() {
      return this.u();
   }

   public String productPrefix() {
      return "ProjectBox";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.l();
            break;
         case 1:
            var10000 = this.u();
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
      return x$1 instanceof ProjectBox;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "l";
            break;
         case 1:
            var10000 = "u";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof ProjectBox) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     ProjectBox var4 = (ProjectBox)x$1;
                     DenseVector var10000 = this.l();
                     DenseVector var5 = var4.l();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     var10000 = this.u();
                     DenseVector var6 = var4.u();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public ProjectBox(final DenseVector l, final DenseVector u) {
      this.l = l;
      this.u = u;
      Proximal.$init$(this);
      Product.$init$(this);
   }
}
