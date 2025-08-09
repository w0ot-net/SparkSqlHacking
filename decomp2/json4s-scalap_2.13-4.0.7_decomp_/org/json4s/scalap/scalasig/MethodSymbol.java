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
   bytes = "\u0006\u0005\u0005ec\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005I\u0001\tE\t\u0015!\u0003C\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u001dq\u0005!!A\u0005\u0002=CqA\u0015\u0001\u0012\u0002\u0013\u00051\u000bC\u0004_\u0001E\u0005I\u0011A0\t\u000f\u0005\u0004\u0011\u0011!C!E\"91\u000eAA\u0001\n\u0003a\u0007bB7\u0001\u0003\u0003%\tA\u001c\u0005\bi\u0002\t\t\u0011\"\u0011v\u0011\u001da\b!!A\u0005\u0002uD\u0011\"!\u0002\u0001\u0003\u0003%\t%a\u0002\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\b\u0001\u0005\u0005I\u0011IA\t\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)bB\u0005\u0002\u001ai\t\t\u0011#\u0001\u0002\u001c\u0019A\u0011DGA\u0001\u0012\u0003\ti\u0002\u0003\u0004J'\u0011\u0005\u0011Q\u0007\u0005\n\u0003\u001f\u0019\u0012\u0011!C#\u0003#A\u0011\"a\u000e\u0014\u0003\u0003%\t)!\u000f\t\u0013\u0005}2#!A\u0005\u0002\u0006\u0005\u0003\"CA('\u0005\u0005I\u0011BA)\u00051iU\r\u001e5pINKXNY8m\u0015\tYB$\u0001\u0005tG\u0006d\u0017m]5h\u0015\tib$\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u0003?\u0001\naA[:p]R\u001a(\"A\u0011\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001!\u0003F\f\t\u0003K\u0019j\u0011AG\u0005\u0003Oi\u0011\u0001cU=nE>d\u0017J\u001c4p'fl'm\u001c7\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\u000fA\u0013x\u000eZ;diB\u0011qf\u000e\b\u0003aUr!!\r\u001b\u000e\u0003IR!a\r\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0013B\u0001\u001c+\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001O\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005YR\u0013AC:z[\n|G.\u00138g_V\tA\b\u0005\u0002&{%\u0011aH\u0007\u0002\u000b'fl'm\u001c7J]\u001a|\u0017aC:z[\n|G.\u00138g_\u0002\n\u0001\"\u00197jCN\u0014VMZ\u000b\u0002\u0005B\u0019\u0011fQ#\n\u0005\u0011S#AB(qi&|g\u000e\u0005\u0002*\r&\u0011qI\u000b\u0002\u0004\u0013:$\u0018!C1mS\u0006\u001c(+\u001a4!\u0003\u0019a\u0014N\\5u}Q\u00191\nT'\u0011\u0005\u0015\u0002\u0001\"\u0002\u001e\u0006\u0001\u0004a\u0004\"\u0002!\u0006\u0001\u0004\u0011\u0015\u0001B2paf$2a\u0013)R\u0011\u001dQd\u0001%AA\u0002qBq\u0001\u0011\u0004\u0011\u0002\u0003\u0007!)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003QS#\u0001P+,\u0003Y\u0003\"a\u0016/\u000e\u0003aS!!\u0017.\u0002\u0013Ut7\r[3dW\u0016$'BA.+\u0003)\tgN\\8uCRLwN\\\u0005\u0003;b\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012\u0001\u0019\u0016\u0003\u0005V\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A2\u0011\u0005\u0011LW\"A3\u000b\u0005\u0019<\u0017\u0001\u00027b]\u001eT\u0011\u0001[\u0001\u0005U\u00064\u0018-\u0003\u0002kK\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!R\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\ty'\u000f\u0005\u0002*a&\u0011\u0011O\u000b\u0002\u0004\u0003:L\bbB:\f\u0003\u0003\u0005\r!R\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003Y\u00042a\u001e>p\u001b\u0005A(BA=+\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003wb\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019a0a\u0001\u0011\u0005%z\u0018bAA\u0001U\t9!i\\8mK\u0006t\u0007bB:\u000e\u0003\u0003\u0005\ra\\\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002d\u0003\u0013Aqa\u001d\b\u0002\u0002\u0003\u0007Q)\u0001\u0005iCND7i\u001c3f)\u0005)\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\r\fa!Z9vC2\u001cHc\u0001@\u0002\u0018!91/EA\u0001\u0002\u0004y\u0017\u0001D'fi\"|GmU=nE>d\u0007CA\u0013\u0014'\u0015\u0019\u0012qDA\u0016!\u001d\t\t#a\n=\u0005.k!!a\t\u000b\u0007\u0005\u0015\"&A\u0004sk:$\u0018.\\3\n\t\u0005%\u00121\u0005\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u0017\u0003gi!!a\f\u000b\u0007\u0005Er-\u0001\u0002j_&\u0019\u0001(a\f\u0015\u0005\u0005m\u0011!B1qa2LH#B&\u0002<\u0005u\u0002\"\u0002\u001e\u0017\u0001\u0004a\u0004\"\u0002!\u0017\u0001\u0004\u0011\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u0007\nY\u0005\u0005\u0003*\u0007\u0006\u0015\u0003#B\u0015\u0002Hq\u0012\u0015bAA%U\t1A+\u001e9mKJB\u0001\"!\u0014\u0018\u0003\u0003\u0005\raS\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA*!\r!\u0017QK\u0005\u0004\u0003/*'AB(cU\u0016\u001cG\u000f"
)
public class MethodSymbol extends SymbolInfoSymbol implements Product, Serializable {
   private final SymbolInfo symbolInfo;
   private final Option aliasRef;

   public static Option unapply(final MethodSymbol x$0) {
      return MethodSymbol$.MODULE$.unapply(x$0);
   }

   public static MethodSymbol apply(final SymbolInfo symbolInfo, final Option aliasRef) {
      return MethodSymbol$.MODULE$.apply(symbolInfo, aliasRef);
   }

   public static Function1 tupled() {
      return MethodSymbol$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return MethodSymbol$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SymbolInfo symbolInfo() {
      return this.symbolInfo;
   }

   public Option aliasRef() {
      return this.aliasRef;
   }

   public MethodSymbol copy(final SymbolInfo symbolInfo, final Option aliasRef) {
      return new MethodSymbol(symbolInfo, aliasRef);
   }

   public SymbolInfo copy$default$1() {
      return this.symbolInfo();
   }

   public Option copy$default$2() {
      return this.aliasRef();
   }

   public String productPrefix() {
      return "MethodSymbol";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.symbolInfo();
            break;
         case 1:
            var10000 = this.aliasRef();
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
      return x$1 instanceof MethodSymbol;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "symbolInfo";
            break;
         case 1:
            var10000 = "aliasRef";
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
            if (x$1 instanceof MethodSymbol) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     MethodSymbol var4 = (MethodSymbol)x$1;
                     SymbolInfo var10000 = this.symbolInfo();
                     SymbolInfo var5 = var4.symbolInfo();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Option var7 = this.aliasRef();
                     Option var6 = var4.aliasRef();
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

   public MethodSymbol(final SymbolInfo symbolInfo, final Option aliasRef) {
      this.symbolInfo = symbolInfo;
      this.aliasRef = aliasRef;
      Product.$init$(this);
   }
}
