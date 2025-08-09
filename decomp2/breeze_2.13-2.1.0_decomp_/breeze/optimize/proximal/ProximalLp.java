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
   bytes = "\u0006\u0005\u0005mc\u0001B\f\u0019\u0001~A\u0001\"\u000f\u0001\u0003\u0016\u0004%\tA\u000f\u0005\t\t\u0002\u0011\t\u0012)A\u0005w!)Q\t\u0001C\u0001\r\")\u0011\n\u0001C\u0001\u0015\"9!\u000bAA\u0001\n\u0003\u0019\u0006bB+\u0001#\u0003%\tA\u0016\u0005\bC\u0002\t\t\u0011\"\u0011c\u0011\u001dY\u0007!!A\u0005\u00021Dq\u0001\u001d\u0001\u0002\u0002\u0013\u0005\u0011\u000fC\u0004x\u0001\u0005\u0005I\u0011\t=\t\u0011}\u0004\u0011\u0011!C\u0001\u0003\u0003A\u0011\"a\u0003\u0001\u0003\u0003%\t%!\u0004\t\u0013\u0005E\u0001!!A\u0005B\u0005M\u0001\"CA\u000b\u0001\u0005\u0005I\u0011IA\f\u0011%\tI\u0002AA\u0001\n\u0003\nYbB\u0005\u0002 a\t\t\u0011#\u0001\u0002\"\u0019Aq\u0003GA\u0001\u0012\u0003\t\u0019\u0003\u0003\u0004F#\u0011\u0005\u00111\b\u0005\n\u0003+\t\u0012\u0011!C#\u0003/A\u0011\"!\u0010\u0012\u0003\u0003%\t)a\u0010\t\u0013\u0005\r\u0013#!A\u0005\u0002\u0006\u0015\u0003\"CA)#\u0005\u0005I\u0011BA*\u0005)\u0001&o\u001c=j[\u0006dG\n\u001d\u0006\u00033i\t\u0001\u0002\u001d:pq&l\u0017\r\u001c\u0006\u00037q\t\u0001b\u001c9uS6L'0\u001a\u0006\u0002;\u00051!M]3fu\u0016\u001c\u0001aE\u0003\u0001A\u0019RS\u0006\u0005\u0002\"I5\t!EC\u0001$\u0003\u0015\u00198-\u00197b\u0013\t)#E\u0001\u0004B]f\u0014VM\u001a\t\u0003O!j\u0011\u0001G\u0005\u0003Sa\u0011\u0001\u0002\u0015:pq&l\u0017\r\u001c\t\u0003C-J!\u0001\f\u0012\u0003\u000fA\u0013x\u000eZ;diB\u0011aF\u000e\b\u0003_Qr!\u0001M\u001a\u000e\u0003ER!A\r\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0013BA\u001b#\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000e\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005U\u0012\u0013!A2\u0016\u0003m\u00022\u0001P B\u001b\u0005i$B\u0001 \u001d\u0003\u0019a\u0017N\\1mO&\u0011\u0001)\u0010\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002\"\u0005&\u00111I\t\u0002\u0007\t>,(\r\\3\u0002\u0005\r\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002H\u0011B\u0011q\u0005\u0001\u0005\u0006s\r\u0001\raO\u0001\u0005aJ|\u0007\u0010F\u0002L\u001dB\u0003\"!\t'\n\u00055\u0013#\u0001B+oSRDQa\u0014\u0003A\u0002m\n\u0011\u0001\u001f\u0005\b#\u0012\u0001\n\u00111\u0001B\u0003\r\u0011\bn\\\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002H)\"9\u0011(\u0002I\u0001\u0002\u0004Y\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002/*\u00121\bW\u0016\u00023B\u0011!lX\u0007\u00027*\u0011A,X\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u0018\u0012\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002a7\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\u0019\u0007C\u00013j\u001b\u0005)'B\u00014h\u0003\u0011a\u0017M\\4\u000b\u0003!\fAA[1wC&\u0011!.\u001a\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u00035\u0004\"!\t8\n\u0005=\u0014#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u0001:v!\t\t3/\u0003\u0002uE\t\u0019\u0011I\\=\t\u000fYL\u0011\u0011!a\u0001[\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012!\u001f\t\u0004uv\u0014X\"A>\u000b\u0005q\u0014\u0013AC2pY2,7\r^5p]&\u0011ap\u001f\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0004\u0005%\u0001cA\u0011\u0002\u0006%\u0019\u0011q\u0001\u0012\u0003\u000f\t{w\u000e\\3b]\"9aoCA\u0001\u0002\u0004\u0011\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2aYA\b\u0011\u001d1H\"!AA\u00025\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002[\u0006AAo\\*ue&tw\rF\u0001d\u0003\u0019)\u0017/^1mgR!\u00111AA\u000f\u0011\u001d1x\"!AA\u0002I\f!\u0002\u0015:pq&l\u0017\r\u001c'q!\t9\u0013cE\u0003\u0012\u0003K\t\t\u0004\u0005\u0004\u0002(\u000552hR\u0007\u0003\u0003SQ1!a\u000b#\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\f\u0002*\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005M\u0012\u0011H\u0007\u0003\u0003kQ1!a\u000eh\u0003\tIw.C\u00028\u0003k!\"!!\t\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u001d\u000b\t\u0005C\u0003:)\u0001\u00071(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u001d\u0013Q\n\t\u0005C\u0005%3(C\u0002\u0002L\t\u0012aa\u00149uS>t\u0007\u0002CA(+\u0005\u0005\t\u0019A$\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002VA\u0019A-a\u0016\n\u0007\u0005eSM\u0001\u0004PE*,7\r\u001e"
)
public class ProximalLp implements Proximal, Product, Serializable {
   private final DenseVector c;

   public static Option unapply(final ProximalLp x$0) {
      return ProximalLp$.MODULE$.unapply(x$0);
   }

   public static ProximalLp apply(final DenseVector c) {
      return ProximalLp$.MODULE$.apply(c);
   }

   public static Function1 andThen(final Function1 g) {
      return ProximalLp$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ProximalLp$.MODULE$.compose(g);
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

   public DenseVector c() {
      return this.c;
   }

   public void prox(final DenseVector x, final double rho) {
      int index$macro$2 = 0;

      for(int limit$macro$4 = x.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
         x.update$mcD$sp(index$macro$2, .MODULE$.max((double)0.0F, x.apply$mcD$sp(index$macro$2) - this.c().apply$mcD$sp(index$macro$2) / rho));
      }

   }

   public ProximalLp copy(final DenseVector c) {
      return new ProximalLp(c);
   }

   public DenseVector copy$default$1() {
      return this.c();
   }

   public String productPrefix() {
      return "ProximalLp";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.c();
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
      return x$1 instanceof ProximalLp;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "c";
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
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof ProximalLp) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     ProximalLp var4 = (ProximalLp)x$1;
                     DenseVector var10000 = this.c();
                     DenseVector var5 = var4.c();
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

   public ProximalLp(final DenseVector c) {
      this.c = c;
      Proximal.$init$(this);
      Product.$init$(this);
   }
}
