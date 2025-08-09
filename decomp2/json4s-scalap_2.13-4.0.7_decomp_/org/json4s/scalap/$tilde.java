package org.json4s.scalap;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\r\u001b\u0001\u0006B\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005u!Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005L\u0001\tE\t\u0015!\u0003I\u0011\u0015a\u0005\u0001\"\u0001N\u0011\u0015\u0011\u0006\u0001\"\u0011T\u0011\u001da\u0006!!A\u0005\u0002uCqA\u001a\u0001\u0012\u0002\u0013\u0005q\rC\u0004v\u0001E\u0005I\u0011\u0001<\t\u000fm\u0004\u0011\u0011!C!y\"9Q\u0010AA\u0001\n\u0003q\b\"CA\u0003\u0001\u0005\u0005I\u0011AA\u0004\u0011%\ti\u0001AA\u0001\n\u0003\ny\u0001C\u0005\u0002\u001e\u0001\t\t\u0011\"\u0001\u0002 !I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00131\u0006\u0005\n\u0003_\u0001\u0011\u0011!C!\u0003cA\u0011\"a\r\u0001\u0003\u0003%\t%!\u000e\b\u0013\u0005e\"$!A\t\u0002\u0005mb\u0001C\r\u001b\u0003\u0003E\t!!\u0010\t\r1\u001bB\u0011AA%\u0011\u001d\u00116#!A\u0005FMC\u0011\"a\u0013\u0014\u0003\u0003%\t)!\u0014\t\u0013\u0005}3#!A\u0005\u0002\u0006\u0005\u0004\"CA@'\u0005\u0005I\u0011BAA\u0005\u0019!C/\u001b7eK*\u00111\u0004H\u0001\u0007g\u000e\fG.\u00199\u000b\u0005uq\u0012A\u00026t_:$4OC\u0001 \u0003\ry'oZ\u0002\u0001+\r\u0011C(S\n\u0005\u0001\rJC\u0006\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003I)J!aK\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011Q&\u000e\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!\r\u0011\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0013B\u0001\u001b&\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005Q*\u0013AA02+\u0005Q\u0004CA\u001e=\u0019\u0001!a!\u0010\u0001\u0005\u0006\u0004q$!A!\u0012\u0005}\u0012\u0005C\u0001\u0013A\u0013\t\tUEA\u0004O_RD\u0017N\\4\u0011\u0005\u0011\u001a\u0015B\u0001#&\u0005\r\te._\u0001\u0004?F\u0002\u0013AA03+\u0005A\u0005CA\u001eJ\t\u0019Q\u0005\u0001\"b\u0001}\t\t!)A\u0002`e\u0001\na\u0001P5oSRtDc\u0001(Q#B!q\n\u0001\u001eI\u001b\u0005Q\u0002\"\u0002\u001d\u0006\u0001\u0004Q\u0004\"\u0002$\u0006\u0001\u0004A\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003Q\u0003\"!\u0016.\u000e\u0003YS!a\u0016-\u0002\t1\fgn\u001a\u0006\u00023\u0006!!.\u0019<b\u0013\tYfK\u0001\u0004TiJLgnZ\u0001\u0005G>\u0004\u00180F\u0002_C\u000e$2a\u00183f!\u0011y\u0005\u0001\u00192\u0011\u0005m\nG!B\u001f\b\u0005\u0004q\u0004CA\u001ed\t\u0015QuA1\u0001?\u0011\u001dAt\u0001%AA\u0002\u0001DqAR\u0004\u0011\u0002\u0003\u0007!-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0007!\u001cH/F\u0001jU\tQ$nK\u0001l!\ta\u0017/D\u0001n\u0015\tqw.A\u0005v]\u000eDWmY6fI*\u0011\u0001/J\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001:n\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006{!\u0011\rA\u0010\u0003\u0006\u0015\"\u0011\rAP\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\r9\u0018P_\u000b\u0002q*\u0012\u0001J\u001b\u0003\u0006{%\u0011\rA\u0010\u0003\u0006\u0015&\u0011\rAP\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003Q\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012a \t\u0004I\u0005\u0005\u0011bAA\u0002K\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019!)!\u0003\t\u0011\u0005-A\"!AA\u0002}\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\t!\u0015\t\u0019\"!\u0007C\u001b\t\t)BC\u0002\u0002\u0018\u0015\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tY\"!\u0006\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003C\t9\u0003E\u0002%\u0003GI1!!\n&\u0005\u001d\u0011un\u001c7fC:D\u0001\"a\u0003\u000f\u0003\u0003\u0005\rAQ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002U\u0003[A\u0001\"a\u0003\u0010\u0003\u0003\u0005\ra`\u0001\tQ\u0006\u001c\bnQ8eKR\tq0\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003C\t9\u0004\u0003\u0005\u0002\fE\t\t\u00111\u0001C\u0003\u0019!C/\u001b7eKB\u0011qjE\n\u0005'\r\ny\u0004\u0005\u0003\u0002B\u0005\u001dSBAA\"\u0015\r\t)\u0005W\u0001\u0003S>L1ANA\")\t\tY$A\u0003baBd\u00170\u0006\u0004\u0002P\u0005U\u0013\u0011\f\u000b\u0007\u0003#\nY&!\u0018\u0011\r=\u0003\u00111KA,!\rY\u0014Q\u000b\u0003\u0006{Y\u0011\rA\u0010\t\u0004w\u0005eC!\u0002&\u0017\u0005\u0004q\u0004B\u0002\u001d\u0017\u0001\u0004\t\u0019\u0006\u0003\u0004G-\u0001\u0007\u0011qK\u0001\bk:\f\u0007\u000f\u001d7z+\u0019\t\u0019'a\u001d\u0002xQ!\u0011QMA=!\u0015!\u0013qMA6\u0013\r\tI'\n\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f\u0011\ni'!\u001d\u0002v%\u0019\u0011qN\u0013\u0003\rQ+\b\u000f\\33!\rY\u00141\u000f\u0003\u0006{]\u0011\rA\u0010\t\u0004w\u0005]D!\u0002&\u0018\u0005\u0004q\u0004\"CA>/\u0005\u0005\t\u0019AA?\u0003\rAH\u0005\r\t\u0007\u001f\u0002\t\t(!\u001e\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\r\u0005cA+\u0002\u0006&\u0019\u0011q\u0011,\u0003\r=\u0013'.Z2u\u0001"
)
public class $tilde implements Product, Serializable {
   private final Object _1;
   private final Object _2;

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object _1() {
      return this._1;
   }

   public Object _2() {
      return this._2;
   }

   public String toString() {
      return (new StringBuilder(5)).append("(").append(this._1()).append(" ~ ").append(this._2()).append(")").toString();
   }

   public $tilde copy(final Object _1, final Object _2) {
      return new $tilde(_1, _2);
   }

   public Object copy$default$1() {
      return this._1();
   }

   public Object copy$default$2() {
      return this._2();
   }

   public String productPrefix() {
      return "~";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this._1();
            break;
         case 1:
            var10000 = this._2();
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
      return x$1 instanceof $tilde;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "_1";
            break;
         case 1:
            var10000 = "_2";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof $tilde) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               $tilde var4 = ($tilde)x$1;
               if (BoxesRunTime.equals(this._1(), var4._1()) && BoxesRunTime.equals(this._2(), var4._2()) && var4.canEqual(this)) {
                  break label51;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public $tilde(final Object _1, final Object _2) {
      this._1 = _1;
      this._2 = _2;
      Product.$init$(this);
   }
}
