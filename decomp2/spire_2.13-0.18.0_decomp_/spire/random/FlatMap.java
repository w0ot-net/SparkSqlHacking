package spire.random;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015f\u0001B\r\u001b\u0001~A\u0001\"\u0012\u0001\u0003\u0016\u0004%\tA\u0012\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005\u000f\"AA\n\u0001BK\u0002\u0013\u0005Q\n\u0003\u0005R\u0001\tE\t\u0015!\u0003O\u0011\u0015\u0011\u0006\u0001\"\u0001T\u0011\u001d9\u0006!!A\u0005\u0002aCq\u0001\u001a\u0001\u0012\u0002\u0013\u0005Q\rC\u0004t\u0001E\u0005I\u0011\u0001;\t\u000fe\u0004\u0011\u0011!C!u\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0002\u0005\n\u0003#\u0001\u0011\u0011!C\u0001\u0003'A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\t\u0013\u0005%\u0002!!A\u0005\u0002\u0005-\u0002\"CA\u001b\u0001\u0005\u0005I\u0011IA\u001c\u0011%\tY\u0004AA\u0001\n\u0003\ni\u0004C\u0005\u0002@\u0001\t\t\u0011\"\u0011\u0002B!I\u00111\t\u0001\u0002\u0002\u0013\u0005\u0013QI\u0004\n\u0003\u0013R\u0012\u0011!E\u0001\u0003\u00172\u0001\"\u0007\u000e\u0002\u0002#\u0005\u0011Q\n\u0005\u0007%N!\t!!\u0017\t\u0013\u0005}2#!A\u0005F\u0005\u0005\u0003\"CA.'\u0005\u0005I\u0011QA/\u0011%\t)hEA\u0001\n\u0003\u000b9\bC\u0005\u0002\u001cN\t\t\u0011\"\u0003\u0002\u001e\n9a\t\\1u\u001b\u0006\u0004(BA\u000e\u001d\u0003\u0019\u0011\u0018M\u001c3p[*\tQ$A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0007\u0001JUfE\u0003\u0001C\u001d2\u0014\b\u0005\u0002#K5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13E\u0001\u0004B]f\u0014VM\u001a\t\u0004Q%ZS\"\u0001\u000e\n\u0005)R\"AA(q!\taS\u0006\u0004\u0001\u0005\r9\u0002AQ1\u00010\u0005\u0005\u0011\u0015C\u0001\u00194!\t\u0011\u0013'\u0003\u00023G\t9aj\u001c;iS:<\u0007C\u0001\u00125\u0013\t)4EA\u0002B]f\u0004\"AI\u001c\n\u0005a\u001a#a\u0002)s_\u0012,8\r\u001e\t\u0003u\ts!a\u000f!\u000f\u0005qzT\"A\u001f\u000b\u0005yr\u0012A\u0002\u001fs_>$h(C\u0001%\u0013\t\t5%A\u0004qC\u000e\\\u0017mZ3\n\u0005\r#%\u0001D*fe&\fG.\u001b>bE2,'BA!$\u0003\r\u0019XOY\u000b\u0002\u000fB\u0019\u0001&\u000b%\u0011\u00051JE!\u0002&\u0001\u0005\u0004y#!A!\u0002\tM,(\rI\u0001\u0002WV\ta\n\u0005\u0003#\u001f\";\u0013B\u0001)$\u0005%1UO\\2uS>t\u0017'\u0001\u0002lA\u00051A(\u001b8jiz\"2\u0001V+W!\u0011A\u0003\u0001S\u0016\t\u000b\u0015+\u0001\u0019A$\t\u000b1+\u0001\u0019\u0001(\u0002\t\r|\u0007/_\u000b\u00043rsFc\u0001.`CB!\u0001\u0006A.^!\taC\fB\u0003K\r\t\u0007q\u0006\u0005\u0002-=\u0012)aF\u0002b\u0001_!9QI\u0002I\u0001\u0002\u0004\u0001\u0007c\u0001\u0015*7\"9AJ\u0002I\u0001\u0002\u0004\u0011\u0007\u0003\u0002\u0012P7\u000e\u00042\u0001K\u0015^\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*2AZ9s+\u00059'FA$iW\u0005I\u0007C\u00016p\u001b\u0005Y'B\u00017n\u0003%)hn\u00195fG.,GM\u0003\u0002oG\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005A\\'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)!j\u0002b\u0001_\u0011)af\u0002b\u0001_\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TcA;xqV\taO\u000b\u0002OQ\u0012)!\n\u0003b\u0001_\u0011)a\u0006\u0003b\u0001_\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012a\u001f\t\u0004y\u0006\rQ\"A?\u000b\u0005y|\u0018\u0001\u00027b]\u001eT!!!\u0001\u0002\t)\fg/Y\u0005\u0004\u0003\u000bi(AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\fA\u0019!%!\u0004\n\u0007\u0005=1EA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u00024\u0003+A\u0011\"a\u0006\f\u0003\u0003\u0005\r!a\u0003\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\ti\u0002E\u0003\u0002 \u0005\u00152'\u0004\u0002\u0002\")\u0019\u00111E\u0012\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002(\u0005\u0005\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\f\u00024A\u0019!%a\f\n\u0007\u0005E2EA\u0004C_>dW-\u00198\t\u0011\u0005]Q\"!AA\u0002M\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u001910!\u000f\t\u0013\u0005]a\"!AA\u0002\u0005-\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005-\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003m\fa!Z9vC2\u001cH\u0003BA\u0017\u0003\u000fB\u0001\"a\u0006\u0012\u0003\u0003\u0005\raM\u0001\b\r2\fG/T1q!\tA3c\u0005\u0003\u0014C\u0005=\u0003\u0003BA)\u0003/j!!a\u0015\u000b\u0007\u0005Us0\u0001\u0002j_&\u00191)a\u0015\u0015\u0005\u0005-\u0013!B1qa2LXCBA0\u0003K\nI\u0007\u0006\u0004\u0002b\u0005-\u0014q\u000e\t\u0007Q\u0001\t\u0019'a\u001a\u0011\u00071\n)\u0007B\u0003K-\t\u0007q\u0006E\u0002-\u0003S\"QA\f\fC\u0002=Ba!\u0012\fA\u0002\u00055\u0004\u0003\u0002\u0015*\u0003GBa\u0001\u0014\fA\u0002\u0005E\u0004C\u0002\u0012P\u0003G\n\u0019\b\u0005\u0003)S\u0005\u001d\u0014aB;oCB\u0004H._\u000b\u0007\u0003s\nY)a%\u0015\t\u0005m\u0014Q\u0013\t\u0006E\u0005u\u0014\u0011Q\u0005\u0004\u0003\u007f\u001a#AB(qi&|g\u000eE\u0004#\u0003\u0007\u000b9)!$\n\u0007\u0005\u00155E\u0001\u0004UkBdWM\r\t\u0005Q%\nI\tE\u0002-\u0003\u0017#QAS\fC\u0002=\u0002bAI(\u0002\n\u0006=\u0005\u0003\u0002\u0015*\u0003#\u00032\u0001LAJ\t\u0015qsC1\u00010\u0011%\t9jFA\u0001\u0002\u0004\tI*A\u0002yIA\u0002b\u0001\u000b\u0001\u0002\n\u0006E\u0015\u0001D<sSR,'+\u001a9mC\u000e,GCAAP!\ra\u0018\u0011U\u0005\u0004\u0003Gk(AB(cU\u0016\u001cG\u000f"
)
public class FlatMap implements Op, Product, Serializable {
   private final Op sub;
   private final Function1 k;

   public static Option unapply(final FlatMap x$0) {
      return FlatMap$.MODULE$.unapply(x$0);
   }

   public static FlatMap apply(final Op sub, final Function1 k) {
      return FlatMap$.MODULE$.apply(sub, k);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Op flatMap(final Function1 f) {
      return Op.flatMap$(this, f);
   }

   public Op map(final Function1 f) {
      return Op.map$(this, f);
   }

   public final Either resume(final Generator gen) {
      return Op.resume$(this, gen);
   }

   public Object run(final Generator gen) {
      return Op.run$(this, gen);
   }

   public Op sub() {
      return this.sub;
   }

   public Function1 k() {
      return this.k;
   }

   public FlatMap copy(final Op sub, final Function1 k) {
      return new FlatMap(sub, k);
   }

   public Op copy$default$1() {
      return this.sub();
   }

   public Function1 copy$default$2() {
      return this.k();
   }

   public String productPrefix() {
      return "FlatMap";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.sub();
            break;
         case 1:
            var10000 = this.k();
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
      return x$1 instanceof FlatMap;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "sub";
            break;
         case 1:
            var10000 = "k";
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
            if (x$1 instanceof FlatMap) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     FlatMap var4 = (FlatMap)x$1;
                     Op var10000 = this.sub();
                     Op var5 = var4.sub();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     Function1 var7 = this.k();
                     Function1 var6 = var4.k();
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

   public FlatMap(final Op sub, final Function1 k) {
      this.sub = sub;
      this.k = k;
      Op.$init$(this);
      Product.$init$(this);
   }
}
