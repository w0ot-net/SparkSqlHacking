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
   bytes = "\u0006\u0005\u0005]c\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005I!AQ\b\u0001BK\u0002\u0013\u0005a\b\u0003\u0005C\u0001\tE\t\u0015!\u0003@\u0011\u0015\u0019\u0005\u0001\"\u0001E\u0011\u001dA\u0005!!A\u0005\u0002%Cq\u0001\u0014\u0001\u0012\u0002\u0013\u0005Q\nC\u0004Y\u0001E\u0005I\u0011A-\t\u000fm\u0003\u0011\u0011!C!9\"9Q\rAA\u0001\n\u00031\u0007b\u00026\u0001\u0003\u0003%\ta\u001b\u0005\bc\u0002\t\t\u0011\"\u0011s\u0011\u001dI\b!!A\u0005\u0002iD\u0001b \u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0001\u0005\n\u0003\u000b\u0001\u0011\u0011!C!\u0003\u000fA\u0011\"!\u0003\u0001\u0003\u0003%\t%a\u0003\t\u0013\u00055\u0001!!A\u0005B\u0005=q!CA\n5\u0005\u0005\t\u0012AA\u000b\r!I\"$!A\t\u0002\u0005]\u0001BB\"\u0014\t\u0003\ty\u0003C\u0005\u0002\nM\t\t\u0011\"\u0012\u0002\f!I\u0011\u0011G\n\u0002\u0002\u0013\u0005\u00151\u0007\u0005\n\u0003s\u0019\u0012\u0011!CA\u0003wA\u0011\"!\u0014\u0014\u0003\u0003%I!a\u0014\u0003\u0015MKgn\u001a7f)f\u0004XM\u0003\u0002\u001c9\u0005A1oY1mCNLwM\u0003\u0002\u001e=\u000511oY1mCBT!a\b\u0011\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005\t\u0013aA8sO\u000e\u00011\u0003\u0002\u0001%Q9\u0002\"!\n\u0014\u000e\u0003iI!a\n\u000e\u0003\tQK\b/\u001a\t\u0003S1j\u0011A\u000b\u0006\u0002W\u0005)1oY1mC&\u0011QF\u000b\u0002\b!J|G-^2u!\tysG\u0004\u00021k9\u0011\u0011\u0007N\u0007\u0002e)\u00111GI\u0001\u0007yI|w\u000e\u001e \n\u0003-J!A\u000e\u0016\u0002\u000fA\f7m[1hK&\u0011\u0001(\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003m)\nq\u0001^=qKJ+g-F\u0001%\u0003!!\u0018\u0010]3SK\u001a\u0004\u0013AB:z[\n|G.F\u0001@!\t)\u0003)\u0003\u0002B5\t11+_7c_2\fqa]=nE>d\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0004\u000b\u001a;\u0005CA\u0013\u0001\u0011\u0015QT\u00011\u0001%\u0011\u0015iT\u00011\u0001@\u0003\u0011\u0019w\u000e]=\u0015\u0007\u0015S5\nC\u0004;\rA\u0005\t\u0019\u0001\u0013\t\u000fu2\u0001\u0013!a\u0001\u007f\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001(+\u0005\u0011z5&\u0001)\u0011\u0005E3V\"\u0001*\u000b\u0005M#\u0016!C;oG\",7m[3e\u0015\t)&&\u0001\u0006b]:|G/\u0019;j_:L!a\u0016*\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0003iS#aP(\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005i\u0006C\u00010d\u001b\u0005y&B\u00011b\u0003\u0011a\u0017M\\4\u000b\u0003\t\fAA[1wC&\u0011Am\u0018\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u001d\u0004\"!\u000b5\n\u0005%T#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HC\u00017p!\tIS.\u0003\u0002oU\t\u0019\u0011I\\=\t\u000fA\\\u0011\u0011!a\u0001O\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012a\u001d\t\u0004i^dW\"A;\u000b\u0005YT\u0013AC2pY2,7\r^5p]&\u0011\u00010\u001e\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002|}B\u0011\u0011\u0006`\u0005\u0003{*\u0012qAQ8pY\u0016\fg\u000eC\u0004q\u001b\u0005\u0005\t\u0019\u00017\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004;\u0006\r\u0001b\u00029\u000f\u0003\u0003\u0005\raZ\u0001\tQ\u0006\u001c\bnQ8eKR\tq-\u0001\u0005u_N#(/\u001b8h)\u0005i\u0016AB3rk\u0006d7\u000fF\u0002|\u0003#Aq\u0001]\t\u0002\u0002\u0003\u0007A.\u0001\u0006TS:<G.\u001a+za\u0016\u0004\"!J\n\u0014\u000bM\tI\"!\n\u0011\u000f\u0005m\u0011\u0011\u0005\u0013@\u000b6\u0011\u0011Q\u0004\u0006\u0004\u0003?Q\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003G\tiBA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\n\u0002.5\u0011\u0011\u0011\u0006\u0006\u0004\u0003W\t\u0017AA5p\u0013\rA\u0014\u0011\u0006\u000b\u0003\u0003+\tQ!\u00199qYf$R!RA\u001b\u0003oAQA\u000f\fA\u0002\u0011BQ!\u0010\fA\u0002}\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002>\u0005%\u0003#B\u0015\u0002@\u0005\r\u0013bAA!U\t1q\n\u001d;j_:\u0004R!KA#I}J1!a\u0012+\u0005\u0019!V\u000f\u001d7fe!A\u00111J\f\u0002\u0002\u0003\u0007Q)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0015\u0011\u0007y\u000b\u0019&C\u0002\u0002V}\u0013aa\u00142kK\u000e$\b"
)
public class SingleType extends Type implements Product, Serializable {
   private final Type typeRef;
   private final Symbol symbol;

   public static Option unapply(final SingleType x$0) {
      return SingleType$.MODULE$.unapply(x$0);
   }

   public static SingleType apply(final Type typeRef, final Symbol symbol) {
      return SingleType$.MODULE$.apply(typeRef, symbol);
   }

   public static Function1 tupled() {
      return SingleType$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SingleType$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Type typeRef() {
      return this.typeRef;
   }

   public Symbol symbol() {
      return this.symbol;
   }

   public SingleType copy(final Type typeRef, final Symbol symbol) {
      return new SingleType(typeRef, symbol);
   }

   public Type copy$default$1() {
      return this.typeRef();
   }

   public Symbol copy$default$2() {
      return this.symbol();
   }

   public String productPrefix() {
      return "SingleType";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.typeRef();
            break;
         case 1:
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
      return x$1 instanceof SingleType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "typeRef";
            break;
         case 1:
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
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof SingleType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     SingleType var4 = (SingleType)x$1;
                     Type var10000 = this.typeRef();
                     Type var5 = var4.typeRef();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Symbol var7 = this.symbol();
                     Symbol var6 = var4.symbol();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
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

   public SingleType(final Type typeRef, final Symbol symbol) {
      this.typeRef = typeRef;
      this.symbol = symbol;
      Product.$init$(this);
   }
}
