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
   bytes = "\u0006\u0005\u00055c\u0001B\f\u0019\u0001\u0006B\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f!)!\t\u0001C!\u0007\"9A\nAA\u0001\n\u0003i\u0005bB(\u0001#\u0003%\t\u0001\u0015\u0005\b7\u0002\t\t\u0011\"\u0011]\u0011\u001d!\u0007!!A\u0005\u0002\u0015Dq!\u001b\u0001\u0002\u0002\u0013\u0005!\u000eC\u0004q\u0001\u0005\u0005I\u0011I9\t\u000fa\u0004\u0011\u0011!C\u0001s\"9a\u0010AA\u0001\n\u0003z\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e\u001dI\u0011\u0011\u0003\r\u0002\u0002#\u0005\u00111\u0003\u0004\t/a\t\t\u0011#\u0001\u0002\u0016!1a(\u0005C\u0001\u0003[A\u0011\"a\u0002\u0012\u0003\u0003%)%!\u0003\t\u0013\u0005=\u0012#!A\u0005\u0002\u0006E\u0002\"CA\u001b#\u0005\u0005I\u0011QA\u001c\u0011%\t\u0019%EA\u0001\n\u0013\t)E\u0001\u0006UsB,7+_7c_2T!!\u0007\u000e\u0002\u0011M\u001c\u0017\r\\1tS\u001eT!a\u0007\u000f\u0002\rM\u001c\u0017\r\\1q\u0015\tib$\u0001\u0004kg>tGg\u001d\u0006\u0002?\u0005\u0019qN]4\u0004\u0001M!\u0001A\t\u0014-!\t\u0019C%D\u0001\u0019\u0013\t)\u0003D\u0001\tTs6\u0014w\u000e\\%oM>\u001c\u00160\u001c2pYB\u0011qEK\u0007\u0002Q)\t\u0011&A\u0003tG\u0006d\u0017-\u0003\u0002,Q\t9\u0001K]8ek\u000e$\bCA\u00176\u001d\tq3G\u0004\u00020e5\t\u0001G\u0003\u00022A\u00051AH]8pizJ\u0011!K\u0005\u0003i!\nq\u0001]1dW\u0006<W-\u0003\u00027o\ta1+\u001a:jC2L'0\u00192mK*\u0011A\u0007K\u0001\u000bgfl'm\u001c7J]\u001a|W#\u0001\u001e\u0011\u0005\rZ\u0014B\u0001\u001f\u0019\u0005)\u0019\u00160\u001c2pY&sgm\\\u0001\fgfl'm\u001c7J]\u001a|\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003\u0001\u0006\u0003\"a\t\u0001\t\u000ba\u001a\u0001\u0019\u0001\u001e\u0002\tA\fG\u000f[\u000b\u0002\tB\u0011Q)\u0013\b\u0003\r\u001e\u0003\"a\f\u0015\n\u0005!C\u0013A\u0002)sK\u0012,g-\u0003\u0002K\u0017\n11\u000b\u001e:j]\u001eT!\u0001\u0013\u0015\u0002\t\r|\u0007/\u001f\u000b\u0003\u0001:Cq\u0001O\u0003\u0011\u0002\u0003\u0007!(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003ES#A\u000f*,\u0003M\u0003\"\u0001V-\u000e\u0003US!AV,\u0002\u0013Ut7\r[3dW\u0016$'B\u0001-)\u0003)\tgN\\8uCRLwN\\\u0005\u00035V\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tQ\f\u0005\u0002_G6\tqL\u0003\u0002aC\u0006!A.\u00198h\u0015\u0005\u0011\u0017\u0001\u00026bm\u0006L!AS0\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u0019\u0004\"aJ4\n\u0005!D#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA6o!\t9C.\u0003\u0002nQ\t\u0019\u0011I\\=\t\u000f=L\u0011\u0011!a\u0001M\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A\u001d\t\u0004gZ\\W\"\u0001;\u000b\u0005UD\u0013AC2pY2,7\r^5p]&\u0011q\u000f\u001e\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002{{B\u0011qe_\u0005\u0003y\"\u0012qAQ8pY\u0016\fg\u000eC\u0004p\u0017\u0005\u0005\t\u0019A6\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004;\u0006\u0005\u0001bB8\r\u0003\u0003\u0005\rAZ\u0001\tQ\u0006\u001c\bnQ8eKR\ta-\u0001\u0005u_N#(/\u001b8h)\u0005i\u0016AB3rk\u0006d7\u000fF\u0002{\u0003\u001fAqa\\\b\u0002\u0002\u0003\u00071.\u0001\u0006UsB,7+_7c_2\u0004\"aI\t\u0014\u000bE\t9\"a\t\u0011\r\u0005e\u0011q\u0004\u001eA\u001b\t\tYBC\u0002\u0002\u001e!\nqA];oi&lW-\u0003\u0003\u0002\"\u0005m!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011QEA\u0016\u001b\t\t9CC\u0002\u0002*\u0005\f!![8\n\u0007Y\n9\u0003\u0006\u0002\u0002\u0014\u0005)\u0011\r\u001d9msR\u0019\u0001)a\r\t\u000ba\"\u0002\u0019\u0001\u001e\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011HA !\u00119\u00131\b\u001e\n\u0007\u0005u\u0002F\u0001\u0004PaRLwN\u001c\u0005\t\u0003\u0003*\u0012\u0011!a\u0001\u0001\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u001d\u0003c\u00010\u0002J%\u0019\u00111J0\u0003\r=\u0013'.Z2u\u0001"
)
public class TypeSymbol extends SymbolInfoSymbol implements Product, Serializable {
   private final SymbolInfo symbolInfo;

   public static Option unapply(final TypeSymbol x$0) {
      return TypeSymbol$.MODULE$.unapply(x$0);
   }

   public static TypeSymbol apply(final SymbolInfo symbolInfo) {
      return TypeSymbol$.MODULE$.apply(symbolInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return TypeSymbol$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return TypeSymbol$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SymbolInfo symbolInfo() {
      return this.symbolInfo;
   }

   public String path() {
      return this.name();
   }

   public TypeSymbol copy(final SymbolInfo symbolInfo) {
      return new TypeSymbol(symbolInfo);
   }

   public SymbolInfo copy$default$1() {
      return this.symbolInfo();
   }

   public String productPrefix() {
      return "TypeSymbol";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.symbolInfo();
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
      return x$1 instanceof TypeSymbol;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "symbolInfo";
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
            if (x$1 instanceof TypeSymbol) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     TypeSymbol var4 = (TypeSymbol)x$1;
                     SymbolInfo var10000 = this.symbolInfo();
                     SymbolInfo var5 = var4.symbolInfo();
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

   public TypeSymbol(final SymbolInfo symbolInfo) {
      this.symbolInfo = symbolInfo;
      Product.$init$(this);
   }
}
