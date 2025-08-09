package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb\u0001\u0002\f\u0018\u0001\u0002B\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t}\u0001\u0011\t\u0012)A\u0005q!)q\b\u0001C\u0001\u0001\"9A\tAA\u0001\n\u0003)\u0005bB$\u0001#\u0003%\t\u0001\u0013\u0005\b'\u0002\t\t\u0011\"\u0011U\u0011\u001di\u0006!!A\u0005\u0002yCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004g\u0001\u0005\u0005I\u0011I4\t\u000f9\u0004\u0011\u0011!C\u0001_\"9A\u000fAA\u0001\n\u0003*\bbB<\u0001\u0003\u0003%\t\u0005\u001f\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011\u001dY\b!!A\u0005Bq<qA`\f\u0002\u0002#\u0005qP\u0002\u0005\u0017/\u0005\u0005\t\u0012AA\u0001\u0011\u0019y\u0004\u0003\"\u0001\u0002\u001a!9\u0011\u0010EA\u0001\n\u000bR\b\"CA\u000e!\u0005\u0005I\u0011QA\u000f\u0011%\t\t\u0003EA\u0001\n\u0003\u000b\u0019\u0003C\u0005\u00020A\t\t\u0011\"\u0003\u00022\tA1\t[5mIJ,gN\u0003\u0002\u00193\u0005A1oY1mCNLwM\u0003\u0002\u001b7\u000511oY1mCBT!\u0001H\u000f\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005q\u0012aA8sO\u000e\u00011\u0003\u0002\u0001\"O)\u0002\"AI\u0013\u000e\u0003\rR\u0011\u0001J\u0001\u0006g\u000e\fG.Y\u0005\u0003M\r\u0012a!\u00118z%\u00164\u0007C\u0001\u0012)\u0013\tI3EA\u0004Qe>$Wo\u0019;\u0011\u0005-\u001adB\u0001\u00172\u001d\ti\u0003'D\u0001/\u0015\tys$\u0001\u0004=e>|GOP\u0005\u0002I%\u0011!gI\u0001\ba\u0006\u001c7.Y4f\u0013\t!TG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00023G\u0005Q1/_7c_2\u0014VMZ:\u0016\u0003a\u00022aK\u001d<\u0013\tQTGA\u0002TKF\u0004\"A\t\u001f\n\u0005u\u001a#aA%oi\u0006Y1/_7c_2\u0014VMZ:!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011i\u0011\t\u0003\u0005\u0002i\u0011a\u0006\u0005\u0006m\r\u0001\r\u0001O\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002B\r\"9a\u0007\u0002I\u0001\u0002\u0004A\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0013*\u0012\u0001HS\u0016\u0002\u0017B\u0011A*U\u0007\u0002\u001b*\u0011ajT\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001U\u0012\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002S\u001b\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005)\u0006C\u0001,\\\u001b\u00059&B\u0001-Z\u0003\u0011a\u0017M\\4\u000b\u0003i\u000bAA[1wC&\u0011Al\u0016\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003m\na\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002bIB\u0011!EY\u0005\u0003G\u000e\u00121!\u00118z\u0011\u001d)\u0007\"!AA\u0002m\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u00015\u0011\u0007%d\u0017-D\u0001k\u0015\tY7%\u0001\u0006d_2dWm\u0019;j_:L!!\u001c6\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003aN\u0004\"AI9\n\u0005I\u001c#a\u0002\"p_2,\u0017M\u001c\u0005\bK*\t\t\u00111\u0001b\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005U3\bbB3\f\u0003\u0003\u0005\raO\u0001\tQ\u0006\u001c\bnQ8eKR\t1(\u0001\u0005u_N#(/\u001b8h)\u0005)\u0016AB3rk\u0006d7\u000f\u0006\u0002q{\"9QMDA\u0001\u0002\u0004\t\u0017\u0001C\"iS2$'/\u001a8\u0011\u0005\t\u00032#\u0002\t\u0002\u0004\u0005=\u0001CBA\u0003\u0003\u0017A\u0014)\u0004\u0002\u0002\b)\u0019\u0011\u0011B\u0012\u0002\u000fI,h\u000e^5nK&!\u0011QBA\u0004\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003#\t9\"\u0004\u0002\u0002\u0014)\u0019\u0011QC-\u0002\u0005%|\u0017b\u0001\u001b\u0002\u0014Q\tq0A\u0003baBd\u0017\u0010F\u0002B\u0003?AQAN\nA\u0002a\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002&\u0005-\u0002\u0003\u0002\u0012\u0002(aJ1!!\u000b$\u0005\u0019y\u0005\u000f^5p]\"A\u0011Q\u0006\u000b\u0002\u0002\u0003\u0007\u0011)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\r\u0011\u0007Y\u000b)$C\u0002\u00028]\u0013aa\u00142kK\u000e$\b"
)
public class Children implements Product, Serializable {
   private final Seq symbolRefs;

   public static Option unapply(final Children x$0) {
      return Children$.MODULE$.unapply(x$0);
   }

   public static Children apply(final Seq symbolRefs) {
      return Children$.MODULE$.apply(symbolRefs);
   }

   public static Function1 andThen(final Function1 g) {
      return Children$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return Children$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq symbolRefs() {
      return this.symbolRefs;
   }

   public Children copy(final Seq symbolRefs) {
      return new Children(symbolRefs);
   }

   public Seq copy$default$1() {
      return this.symbolRefs();
   }

   public String productPrefix() {
      return "Children";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.symbolRefs();
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
      return x$1 instanceof Children;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "symbolRefs";
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
            if (x$1 instanceof Children) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     Children var4 = (Children)x$1;
                     Seq var10000 = this.symbolRefs();
                     Seq var5 = var4.symbolRefs();
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

   public Children(final Seq symbolRefs) {
      this.symbolRefs = symbolRefs;
      Product.$init$(this);
   }
}
