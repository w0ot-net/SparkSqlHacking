package org.json4s.scalap.scalasig;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dd\u0001\u0002\u000e\u001c\u0001\u0012B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0012)A\u0005{!A\u0011\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005J\u0001\tE\t\u0015!\u0003D\u0011\u0015Q\u0005\u0001\"\u0001L\u0011!y\u0005\u0001#b\u0001\n\u0003\u0001\u0006bB+\u0001\u0003\u0003%\tA\u0016\u0005\b3\u0002\t\n\u0011\"\u0001[\u0011\u001d)\u0007!%A\u0005\u0002\u0019Dq\u0001\u001b\u0001\u0002\u0002\u0013\u0005\u0013\u000eC\u0004s\u0001\u0005\u0005I\u0011A:\t\u000fQ\u0004\u0011\u0011!C\u0001k\"91\u0010AA\u0001\n\u0003b\b\"CA\u0004\u0001\u0005\u0005I\u0011AA\u0005\u0011%\t\u0019\u0002AA\u0001\n\u0003\n)\u0002C\u0005\u0002\u001a\u0001\t\t\u0011\"\u0011\u0002\u001c!I\u0011Q\u0004\u0001\u0002\u0002\u0013\u0005\u0013q\u0004\u0005\n\u0003C\u0001\u0011\u0011!C!\u0003G9\u0011\"a\n\u001c\u0003\u0003E\t!!\u000b\u0007\u0011iY\u0012\u0011!E\u0001\u0003WAaA\u0013\u000b\u0005\u0002\u0005\r\u0003\"CA\u000f)\u0005\u0005IQIA\u0010\u0011%\t)\u0005FA\u0001\n\u0003\u000b9\u0005C\u0005\u0002NQ\t\t\u0011\"!\u0002P!I\u0011Q\f\u000b\u0002\u0002\u0013%\u0011q\f\u0002\f\u00072\f7o]*z[\n|GN\u0003\u0002\u001d;\u0005A1oY1mCNLwM\u0003\u0002\u001f?\u000511oY1mCBT!\u0001I\u0011\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005\u0011\u0013aA8sO\u000e\u00011\u0003\u0002\u0001&S=\u0002\"AJ\u0014\u000e\u0003mI!\u0001K\u000e\u0003!MKXNY8m\u0013:4wnU=nE>d\u0007C\u0001\u0016.\u001b\u0005Y#\"\u0001\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u00059Z#a\u0002)s_\u0012,8\r\u001e\t\u0003aar!!\r\u001c\u000f\u0005I*T\"A\u001a\u000b\u0005Q\u001a\u0013A\u0002\u001fs_>$h(C\u0001-\u0013\t94&A\u0004qC\u000e\\\u0017mZ3\n\u0005eR$\u0001D*fe&\fG.\u001b>bE2,'BA\u001c,\u0003)\u0019\u00180\u001c2pY&sgm\\\u000b\u0002{A\u0011aEP\u0005\u0003\u007fm\u0011!bU=nE>d\u0017J\u001c4p\u0003-\u0019\u00180\u001c2pY&sgm\u001c\u0011\u0002\u0017QD\u0017n\u001d+za\u0016\u0014VMZ\u000b\u0002\u0007B\u0019!\u0006\u0012$\n\u0005\u0015[#AB(qi&|g\u000e\u0005\u0002+\u000f&\u0011\u0001j\u000b\u0002\u0004\u0013:$\u0018\u0001\u0004;iSN$\u0016\u0010]3SK\u001a\u0004\u0013A\u0002\u001fj]&$h\bF\u0002M\u001b:\u0003\"A\n\u0001\t\u000bm*\u0001\u0019A\u001f\t\u000b\u0005+\u0001\u0019A\"\u0002\u0011M,GN\u001a+za\u0016,\u0012!\u0015\t\u0004U\u0011\u0013\u0006C\u0001\u0014T\u0013\t!6D\u0001\u0003UsB,\u0017\u0001B2paf$2\u0001T,Y\u0011\u001dYt\u0001%AA\u0002uBq!Q\u0004\u0011\u0002\u0003\u00071)\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003mS#!\u0010/,\u0003u\u0003\"AX2\u000e\u0003}S!\u0001Y1\u0002\u0013Ut7\r[3dW\u0016$'B\u00012,\u0003)\tgN\\8uCRLwN\\\u0005\u0003I~\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012a\u001a\u0016\u0003\u0007r\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00016\u0011\u0005-\u0004X\"\u00017\u000b\u00055t\u0017\u0001\u00027b]\u001eT\u0011a\\\u0001\u0005U\u00064\u0018-\u0003\u0002rY\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012AR\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t1\u0018\u0010\u0005\u0002+o&\u0011\u0001p\u000b\u0002\u0004\u0003:L\bb\u0002>\r\u0003\u0003\u0005\rAR\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0003u\u0004BA`A\u0002m6\tqPC\u0002\u0002\u0002-\n!bY8mY\u0016\u001cG/[8o\u0013\r\t)a \u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\f\u0005E\u0001c\u0001\u0016\u0002\u000e%\u0019\u0011qB\u0016\u0003\u000f\t{w\u000e\\3b]\"9!PDA\u0001\u0002\u00041\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A[A\f\u0011\u001dQx\"!AA\u0002\u0019\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\r\u0006AAo\\*ue&tw\rF\u0001k\u0003\u0019)\u0017/^1mgR!\u00111BA\u0013\u0011\u001dQ(#!AA\u0002Y\f1b\u00117bgN\u001c\u00160\u001c2pYB\u0011a\u0005F\n\u0006)\u00055\u0012\u0011\b\t\b\u0003_\t)$P\"M\u001b\t\t\tDC\u0002\u00024-\nqA];oi&lW-\u0003\u0003\u00028\u0005E\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA!\u00111HA!\u001b\t\tiDC\u0002\u0002@9\f!![8\n\u0007e\ni\u0004\u0006\u0002\u0002*\u0005)\u0011\r\u001d9msR)A*!\u0013\u0002L!)1h\u0006a\u0001{!)\u0011i\u0006a\u0001\u0007\u00069QO\\1qa2LH\u0003BA)\u00033\u0002BA\u000b#\u0002TA)!&!\u0016>\u0007&\u0019\u0011qK\u0016\u0003\rQ+\b\u000f\\33\u0011!\tY\u0006GA\u0001\u0002\u0004a\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\r\t\u0004W\u0006\r\u0014bAA3Y\n1qJ\u00196fGR\u0004"
)
public class ClassSymbol extends SymbolInfoSymbol implements Product, Serializable {
   private Option selfType;
   private final SymbolInfo symbolInfo;
   private final Option thisTypeRef;
   private volatile boolean bitmap$0;

   public static Option unapply(final ClassSymbol x$0) {
      return ClassSymbol$.MODULE$.unapply(x$0);
   }

   public static ClassSymbol apply(final SymbolInfo symbolInfo, final Option thisTypeRef) {
      return ClassSymbol$.MODULE$.apply(symbolInfo, thisTypeRef);
   }

   public static Function1 tupled() {
      return ClassSymbol$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ClassSymbol$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SymbolInfo symbolInfo() {
      return this.symbolInfo;
   }

   public Option thisTypeRef() {
      return this.thisTypeRef;
   }

   private Option selfType$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.selfType = this.thisTypeRef().map((x) -> $anonfun$selfType$1(this, BoxesRunTime.unboxToInt(x)));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.selfType;
   }

   public Option selfType() {
      return !this.bitmap$0 ? this.selfType$lzycompute() : this.selfType;
   }

   public ClassSymbol copy(final SymbolInfo symbolInfo, final Option thisTypeRef) {
      return new ClassSymbol(symbolInfo, thisTypeRef);
   }

   public SymbolInfo copy$default$1() {
      return this.symbolInfo();
   }

   public Option copy$default$2() {
      return this.thisTypeRef();
   }

   public String productPrefix() {
      return "ClassSymbol";
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
            var10000 = this.thisTypeRef();
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
      return x$1 instanceof ClassSymbol;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "symbolInfo";
            break;
         case 1:
            var10000 = "thisTypeRef";
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
            if (x$1 instanceof ClassSymbol) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     ClassSymbol var4 = (ClassSymbol)x$1;
                     SymbolInfo var10000 = this.symbolInfo();
                     SymbolInfo var5 = var4.symbolInfo();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Option var7 = this.thisTypeRef();
                     Option var6 = var4.thisTypeRef();
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

   // $FF: synthetic method
   public static final Type $anonfun$selfType$1(final ClassSymbol $this, final int x) {
      return (Type)$this.applyRule(ScalaSigEntryParsers$.MODULE$.parseEntry(ScalaSigEntryParsers$.MODULE$.typeEntry(), x));
   }

   public ClassSymbol(final SymbolInfo symbolInfo, final Option thisTypeRef) {
      this.symbolInfo = symbolInfo;
      this.thisTypeRef = thisTypeRef;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
