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
   bytes = "\u0006\u0005\u0005eb\u0001\u0002\f\u0018\u0001\u0002B\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005s!)Q\b\u0001C\u0001}!9\u0011\tAA\u0001\n\u0003\u0011\u0005b\u0002#\u0001#\u0003%\t!\u0012\u0005\b!\u0002\t\t\u0011\"\u0011R\u0011\u001dQ\u0006!!A\u0005\u0002mCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004g\u0001\u0005\u0005I\u0011I4\t\u000f9\u0004\u0011\u0011!C\u0001_\"9A\u000fAA\u0001\n\u0003*\bbB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011\u001dY\b!!A\u0005Bq<qA`\f\u0002\u0002#\u0005qP\u0002\u0005\u0017/\u0005\u0005\t\u0012AA\u0001\u0011\u0019i\u0004\u0003\"\u0001\u0002\u001a!9\u0011\u0010EA\u0001\n\u000bR\b\"CA\u000e!\u0005\u0005I\u0011QA\u000f\u0011%\t\t\u0003EA\u0001\n\u0003\u000b\u0019\u0003C\u0005\u00020A\t\t\u0011\"\u0003\u00022\taqJ\u00196fGR\u001c\u00160\u001c2pY*\u0011\u0001$G\u0001\tg\u000e\fG.Y:jO*\u0011!dG\u0001\u0007g\u000e\fG.\u00199\u000b\u0005qi\u0012A\u00026t_:$4OC\u0001\u001f\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0011%J\u0016\u0011\u0005\t\u001aS\"A\f\n\u0005\u0011:\"\u0001E*z[\n|G.\u00138g_NKXNY8m!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\f\u001b\u000f\u00055\u0012dB\u0001\u00182\u001b\u0005y#B\u0001\u0019 \u0003\u0019a$o\\8u}%\t\u0001&\u0003\u00024O\u00059\u0001/Y2lC\u001e,\u0017BA\u001b7\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019t%\u0001\u0006ts6\u0014w\u000e\\%oM>,\u0012!\u000f\t\u0003EiJ!aO\f\u0003\u0015MKXNY8m\u0013:4w.A\u0006ts6\u0014w\u000e\\%oM>\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002@\u0001B\u0011!\u0005\u0001\u0005\u0006o\r\u0001\r!O\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002@\u0007\"9q\u0007\u0002I\u0001\u0002\u0004I\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\r*\u0012\u0011hR\u0016\u0002\u0011B\u0011\u0011JT\u0007\u0002\u0015*\u00111\nT\u0001\nk:\u001c\u0007.Z2lK\u0012T!!T\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002P\u0015\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005\u0011\u0006CA*Y\u001b\u0005!&BA+W\u0003\u0011a\u0017M\\4\u000b\u0003]\u000bAA[1wC&\u0011\u0011\f\u0016\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003q\u0003\"AJ/\n\u0005y;#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA1e!\t1#-\u0003\u0002dO\t\u0019\u0011I\\=\t\u000f\u0015D\u0011\u0011!a\u00019\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012\u0001\u001b\t\u0004S2\fW\"\u00016\u000b\u0005-<\u0013AC2pY2,7\r^5p]&\u0011QN\u001b\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002qgB\u0011a%]\u0005\u0003e\u001e\u0012qAQ8pY\u0016\fg\u000eC\u0004f\u0015\u0005\u0005\t\u0019A1\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0003%ZDq!Z\u0006\u0002\u0002\u0003\u0007A,\u0001\u0005iCND7i\u001c3f)\u0005a\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003I\u000ba!Z9vC2\u001cHC\u00019~\u0011\u001d)g\"!AA\u0002\u0005\fAb\u00142kK\u000e$8+_7c_2\u0004\"A\t\t\u0014\u000bA\t\u0019!a\u0004\u0011\r\u0005\u0015\u00111B\u001d@\u001b\t\t9AC\u0002\u0002\n\u001d\nqA];oi&lW-\u0003\u0003\u0002\u000e\u0005\u001d!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011\u0011CA\f\u001b\t\t\u0019BC\u0002\u0002\u0016Y\u000b!![8\n\u0007U\n\u0019\u0002F\u0001\u0000\u0003\u0015\t\u0007\u000f\u001d7z)\ry\u0014q\u0004\u0005\u0006oM\u0001\r!O\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)#a\u000b\u0011\t\u0019\n9#O\u0005\u0004\u0003S9#AB(qi&|g\u000e\u0003\u0005\u0002.Q\t\t\u00111\u0001@\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003g\u00012aUA\u001b\u0013\r\t9\u0004\u0016\u0002\u0007\u001f\nTWm\u0019;"
)
public class ObjectSymbol extends SymbolInfoSymbol implements Product, Serializable {
   private final SymbolInfo symbolInfo;

   public static Option unapply(final ObjectSymbol x$0) {
      return ObjectSymbol$.MODULE$.unapply(x$0);
   }

   public static ObjectSymbol apply(final SymbolInfo symbolInfo) {
      return ObjectSymbol$.MODULE$.apply(symbolInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return ObjectSymbol$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ObjectSymbol$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SymbolInfo symbolInfo() {
      return this.symbolInfo;
   }

   public ObjectSymbol copy(final SymbolInfo symbolInfo) {
      return new ObjectSymbol(symbolInfo);
   }

   public SymbolInfo copy$default$1() {
      return this.symbolInfo();
   }

   public String productPrefix() {
      return "ObjectSymbol";
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
      return x$1 instanceof ObjectSymbol;
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
            if (x$1 instanceof ObjectSymbol) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     ObjectSymbol var4 = (ObjectSymbol)x$1;
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

   public ObjectSymbol(final SymbolInfo symbolInfo) {
      this.symbolInfo = symbolInfo;
      Product.$init$(this);
   }
}
