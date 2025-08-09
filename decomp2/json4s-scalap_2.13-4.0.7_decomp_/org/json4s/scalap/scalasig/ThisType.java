package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001\u0002\f\u0018\u0001\u0002B\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005s!)Q\b\u0001C\u0001}!9\u0011\tAA\u0001\n\u0003\u0011\u0005b\u0002#\u0001#\u0003%\t!\u0012\u0005\b!\u0002\t\t\u0011\"\u0011R\u0011\u001dQ\u0006!!A\u0005\u0002mCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004g\u0001\u0005\u0005I\u0011I4\t\u000f9\u0004\u0011\u0011!C\u0001_\"9A\u000fAA\u0001\n\u0003*\bbB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011\u001dY\b!!A\u0005Bq<qA`\f\u0002\u0002#\u0005qP\u0002\u0005\u0017/\u0005\u0005\t\u0012AA\u0001\u0011\u0019i\u0004\u0003\"\u0001\u0002\u001a!9\u0011\u0010EA\u0001\n\u000bR\b\"CA\u000e!\u0005\u0005I\u0011QA\u000f\u0011%\t\t\u0003EA\u0001\n\u0003\u000b\u0019\u0003C\u0005\u00020A\t\t\u0011\"\u0003\u00022\tAA\u000b[5t)f\u0004XM\u0003\u0002\u00193\u0005A1oY1mCNLwM\u0003\u0002\u001b7\u000511oY1mCBT!\u0001H\u000f\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005q\u0012aA8sO\u000e\u00011\u0003\u0002\u0001\"K-\u0002\"AI\u0012\u000e\u0003]I!\u0001J\f\u0003\tQK\b/\u001a\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\b!J|G-^2u!\taCG\u0004\u0002.e9\u0011a&M\u0007\u0002_)\u0011\u0001gH\u0001\u0007yI|w\u000e\u001e \n\u0003!J!aM\u0014\u0002\u000fA\f7m[1hK&\u0011QG\u000e\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003g\u001d\naa]=nE>dW#A\u001d\u0011\u0005\tR\u0014BA\u001e\u0018\u0005\u0019\u0019\u00160\u001c2pY\u000691/_7c_2\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002@\u0001B\u0011!\u0005\u0001\u0005\u0006o\r\u0001\r!O\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002@\u0007\"9q\u0007\u0002I\u0001\u0002\u0004I\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\r*\u0012\u0011hR\u0016\u0002\u0011B\u0011\u0011JT\u0007\u0002\u0015*\u00111\nT\u0001\nk:\u001c\u0007.Z2lK\u0012T!!T\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002P\u0015\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\u0011\u0006CA*Y\u001b\u0005!&BA+W\u0003\u0011a\u0017M\\4\u000b\u0003]\u000bAA[1wC&\u0011\u0011\f\u0016\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003q\u0003\"AJ/\n\u0005y;#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA1e!\t1#-\u0003\u0002dO\t\u0019\u0011I\\=\t\u000f\u0015D\u0011\u0011!a\u00019\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012\u0001\u001b\t\u0004S2\fW\"\u00016\u000b\u0005-<\u0013AC2pY2,7\r^5p]&\u0011QN\u001b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002qgB\u0011a%]\u0005\u0003e\u001e\u0012qAQ8pY\u0016\fg\u000eC\u0004f\u0015\u0005\u0005\t\u0019A1\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003%ZDq!Z\u0006\u0002\u0002\u0003\u0007A,\u0001\u0005iCND7i\u001c3f)\u0005a\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003I\u000ba!Z9vC2\u001cHC\u00019~\u0011\u001d)g\"!AA\u0002\u0005\f\u0001\u0002\u00165jgRK\b/\u001a\t\u0003EA\u0019R\u0001EA\u0002\u0003\u001f\u0001b!!\u0002\u0002\fezTBAA\u0004\u0015\r\tIaJ\u0001\beVtG/[7f\u0013\u0011\ti!a\u0002\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u0012\u0005]QBAA\n\u0015\r\t)BV\u0001\u0003S>L1!NA\n)\u0005y\u0018!B1qa2LHcA \u0002 !)qg\u0005a\u0001s\u00059QO\\1qa2LH\u0003BA\u0013\u0003W\u0001BAJA\u0014s%\u0019\u0011\u0011F\u0014\u0003\r=\u0003H/[8o\u0011!\ti\u0003FA\u0001\u0002\u0004y\u0014a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0007\t\u0004'\u0006U\u0012bAA\u001c)\n1qJ\u00196fGR\u0004"
)
public class ThisType extends Type implements Product, Serializable {
   private final Symbol symbol;

   public static Option unapply(final ThisType x$0) {
      return ThisType$.MODULE$.unapply(x$0);
   }

   public static ThisType apply(final Symbol symbol) {
      return ThisType$.MODULE$.apply(symbol);
   }

   public static Function1 andThen(final Function1 g) {
      return ThisType$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ThisType$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Symbol symbol() {
      return this.symbol;
   }

   public ThisType copy(final Symbol symbol) {
      return new ThisType(symbol);
   }

   public Symbol copy$default$1() {
      return this.symbol();
   }

   public String productPrefix() {
      return "ThisType";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.symbol();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ThisType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "symbol";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof ThisType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     ThisType var4 = (ThisType)x$1;
                     Symbol var10000 = this.symbol();
                     Symbol var5 = var4.symbol();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public ThisType(final Symbol symbol) {
      this.symbol = symbol;
      Product.$init$(this);
   }
}
