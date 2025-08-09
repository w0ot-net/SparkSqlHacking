package org.apache.spark.scheduler.cluster.k8s;

import io.fabric8.kubernetes.api.model.Pod;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\f\u0018\u0001\u0012B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0001\")q\n\u0001C\u0001!\"91\u000bAA\u0001\n\u0003!\u0006b\u0002,\u0001#\u0003%\ta\u0016\u0005\bE\u0002\t\t\u0011\"\u0011d\u0011\u001da\u0007!!A\u0005\u00025Dq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004y\u0001\u0005\u0005I\u0011I=\t\u0013\u0005\u0005\u0001!!A\u0005\u0002\u0005\r\u0001\"CA\u0007\u0001\u0005\u0005I\u0011IA\b\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002\u0018\u0001\t\t\u0011\"\u0011\u0002\u001a!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013QD\u0004\n\u0003C9\u0012\u0011!E\u0001\u0003G1\u0001BF\f\u0002\u0002#\u0005\u0011Q\u0005\u0005\u0007\u001fB!\t!a\u000f\t\u0013\u0005]\u0001#!A\u0005F\u0005e\u0001\"CA\u001f!\u0005\u0005I\u0011QA \u0011%\t\u0019\u0005EA\u0001\n\u0003\u000b)\u0005C\u0005\u0002RA\t\t\u0011\"\u0003\u0002T\tI\u0001k\u001c3GC&dW\r\u001a\u0006\u00031e\t1a\u001b\u001dt\u0015\tQ2$A\u0004dYV\u001cH/\u001a:\u000b\u0005qi\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tqr$A\u0003ta\u0006\u00148N\u0003\u0002!C\u00051\u0011\r]1dQ\u0016T\u0011AI\u0001\u0004_J<7\u0001A\n\u0006\u0001\u0015ZsF\r\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0011\u00051jS\"A\f\n\u00059:\"!\u0004$j]\u0006d\u0007k\u001c3Ti\u0006$X\r\u0005\u0002'a%\u0011\u0011g\n\u0002\b!J|G-^2u!\t\u00194H\u0004\u00025s9\u0011Q\u0007O\u0007\u0002m)\u0011qgI\u0001\u0007yI|w\u000e\u001e \n\u0003!J!AO\u0014\u0002\u000fA\f7m[1hK&\u0011A(\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003u\u001d\n1\u0001]8e+\u0005\u0001\u0005CA!M\u001b\u0005\u0011%BA\"E\u0003\u0015iw\u000eZ3m\u0015\t)e)A\u0002ba&T!a\u0012%\u0002\u0015-,(-\u001a:oKR,7O\u0003\u0002J\u0015\u00069a-\u00192sS\u000eD$\"A&\u0002\u0005%|\u0017BA'C\u0005\r\u0001v\u000eZ\u0001\u0005a>$\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003#J\u0003\"\u0001\f\u0001\t\u000by\u001a\u0001\u0019\u0001!\u0002\t\r|\u0007/\u001f\u000b\u0003#VCqA\u0010\u0003\u0011\u0002\u0003\u0007\u0001)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003aS#\u0001Q-,\u0003i\u0003\"a\u00171\u000e\u0003qS!!\u00180\u0002\u0013Ut7\r[3dW\u0016$'BA0(\u0003)\tgN\\8uCRLwN\\\u0005\u0003Cr\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\r\u0005\u0002fU6\taM\u0003\u0002hQ\u0006!A.\u00198h\u0015\u0005I\u0017\u0001\u00026bm\u0006L!a\u001b4\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005q\u0007C\u0001\u0014p\u0013\t\u0001xEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002tmB\u0011a\u0005^\u0005\u0003k\u001e\u00121!\u00118z\u0011\u001d9\b\"!AA\u00029\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001>\u0011\u0007mt8/D\u0001}\u0015\tix%\u0001\u0006d_2dWm\u0019;j_:L!a ?\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u000b\tY\u0001E\u0002'\u0003\u000fI1!!\u0003(\u0005\u001d\u0011un\u001c7fC:Dqa\u001e\u0006\u0002\u0002\u0003\u00071/\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u00013\u0002\u0012!9qoCA\u0001\u0002\u0004q\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u00039\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002I\u00061Q-];bYN$B!!\u0002\u0002 !9qODA\u0001\u0002\u0004\u0019\u0018!\u0003)pI\u001a\u000b\u0017\u000e\\3e!\ta\u0003cE\u0003\u0011\u0003O\t\u0019\u0004\u0005\u0004\u0002*\u0005=\u0002)U\u0007\u0003\u0003WQ1!!\f(\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\r\u0002,\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005U\u0012\u0011H\u0007\u0003\u0003oQ!a\u00135\n\u0007q\n9\u0004\u0006\u0002\u0002$\u0005)\u0011\r\u001d9msR\u0019\u0011+!\u0011\t\u000by\u001a\u0002\u0019\u0001!\u0002\u000fUt\u0017\r\u001d9msR!\u0011qIA'!\u00111\u0013\u0011\n!\n\u0007\u0005-sE\u0001\u0004PaRLwN\u001c\u0005\t\u0003\u001f\"\u0012\u0011!a\u0001#\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005U\u0003cA3\u0002X%\u0019\u0011\u0011\f4\u0003\r=\u0013'.Z2u\u0001"
)
public class PodFailed implements FinalPodState, Product, Serializable {
   private final Pod pod;

   public static Option unapply(final PodFailed x$0) {
      return PodFailed$.MODULE$.unapply(x$0);
   }

   public static PodFailed apply(final Pod pod) {
      return PodFailed$.MODULE$.apply(pod);
   }

   public static Function1 andThen(final Function1 g) {
      return PodFailed$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return PodFailed$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Pod pod() {
      return this.pod;
   }

   public PodFailed copy(final Pod pod) {
      return new PodFailed(pod);
   }

   public Pod copy$default$1() {
      return this.pod();
   }

   public String productPrefix() {
      return "PodFailed";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.pod();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof PodFailed;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "pod";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof PodFailed) {
               label40: {
                  PodFailed var4 = (PodFailed)x$1;
                  Pod var10000 = this.pod();
                  Pod var5 = var4.pod();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public PodFailed(final Pod pod) {
      this.pod = pod;
      Product.$init$(this);
   }
}
