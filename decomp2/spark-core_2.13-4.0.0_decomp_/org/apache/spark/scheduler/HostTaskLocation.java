package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c!\u0002\f\u0018\u0001fy\u0002\u0002\u0003\u001e\u0001\u0005+\u0007I\u0011I\u001e\t\u0011\u0011\u0003!\u0011#Q\u0001\nqBQ!\u0012\u0001\u0005\u0002\u0019CQ!\u0013\u0001\u0005B)Cqa\u0013\u0001\u0002\u0002\u0013\u0005A\nC\u0004O\u0001E\u0005I\u0011A(\t\u000fi\u0003\u0011\u0011!C!7\"91\rAA\u0001\n\u0003!\u0007b\u00025\u0001\u0003\u0003%\t!\u001b\u0005\b_\u0002\t\t\u0011\"\u0011q\u0011\u001d9\b!!A\u0005\u0002aDq! \u0001\u0002\u0002\u0013\u0005c\u0010C\u0005\u0002\u0002\u0001\t\t\u0011\"\u0011\u0002\u0004!I\u0011Q\u0001\u0001\u0002\u0002\u0013\u0005\u0013qA\u0004\u000b\u0003\u00179\u0012\u0011!E\u00013\u00055a!\u0003\f\u0018\u0003\u0003E\t!GA\b\u0011\u0019)\u0005\u0003\"\u0001\u0002(!A\u0011\nEA\u0001\n\u000b\nI\u0003C\u0005\u0002,A\t\t\u0011\"!\u0002.!I\u0011\u0011\u0007\t\u0002\u0002\u0013\u0005\u00151\u0007\u0005\n\u0003\u007f\u0001\u0012\u0011!C\u0005\u0003\u0003\u0012\u0001\u0003S8tiR\u000b7o\u001b'pG\u0006$\u0018n\u001c8\u000b\u0005aI\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<7#\u0002\u0001!M)j\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g\r\u0005\u0002(Q5\tq#\u0003\u0002*/\taA+Y:l\u0019>\u001c\u0017\r^5p]B\u0011\u0011eK\u0005\u0003Y\t\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002/o9\u0011q&\u000e\b\u0003aQj\u0011!\r\u0006\u0003eM\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002G%\u0011aGI\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0014H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00027E\u0005!\u0001n\\:u+\u0005a\u0004CA\u001fB\u001d\tqt\b\u0005\u00021E%\u0011\u0001II\u0001\u0007!J,G-\u001a4\n\u0005\t\u001b%AB*ue&twM\u0003\u0002AE\u0005)\u0001n\\:uA\u00051A(\u001b8jiz\"\"a\u0012%\u0011\u0005\u001d\u0002\u0001\"\u0002\u001e\u0004\u0001\u0004a\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003q\nAaY8qsR\u0011q)\u0014\u0005\bu\u0015\u0001\n\u00111\u0001=\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0015\u0016\u0003yE[\u0013A\u0015\t\u0003'bk\u0011\u0001\u0016\u0006\u0003+Z\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005]\u0013\u0013AC1o]>$\u0018\r^5p]&\u0011\u0011\f\u0016\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001]!\ti&-D\u0001_\u0015\ty\u0006-\u0001\u0003mC:<'\"A1\u0002\t)\fg/Y\u0005\u0003\u0005z\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012!\u001a\t\u0003C\u0019L!a\u001a\u0012\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005)l\u0007CA\u0011l\u0013\ta'EA\u0002B]fDqA\\\u0005\u0002\u0002\u0003\u0007Q-A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002cB\u0019!/\u001e6\u000e\u0003MT!\u0001\u001e\u0012\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002wg\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\tIH\u0010\u0005\u0002\"u&\u00111P\t\u0002\b\u0005>|G.Z1o\u0011\u001dq7\"!AA\u0002)\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011Al \u0005\b]2\t\t\u00111\u0001f\u0003!A\u0017m\u001d5D_\u0012,G#A3\u0002\r\u0015\fX/\u00197t)\rI\u0018\u0011\u0002\u0005\b]:\t\t\u00111\u0001k\u0003AAun\u001d;UCN\\Gj\\2bi&|g\u000e\u0005\u0002(!M)\u0001#!\u0005\u0002\u001eA1\u00111CA\ry\u001dk!!!\u0006\u000b\u0007\u0005]!%A\u0004sk:$\u0018.\\3\n\t\u0005m\u0011Q\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u0010\u0003Ki!!!\t\u000b\u0007\u0005\r\u0002-\u0001\u0002j_&\u0019\u0001(!\t\u0015\u0005\u00055A#\u0001/\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u001d\u000by\u0003C\u0003;'\u0001\u0007A(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005U\u00121\b\t\u0005C\u0005]B(C\u0002\u0002:\t\u0012aa\u00149uS>t\u0007\u0002CA\u001f)\u0005\u0005\t\u0019A$\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002DA\u0019Q,!\u0012\n\u0007\u0005\u001dcL\u0001\u0004PE*,7\r\u001e"
)
public class HostTaskLocation implements TaskLocation, Product, Serializable {
   private final String host;

   public static Option unapply(final HostTaskLocation x$0) {
      return HostTaskLocation$.MODULE$.unapply(x$0);
   }

   public static HostTaskLocation apply(final String host) {
      return HostTaskLocation$.MODULE$.apply(host);
   }

   public static Function1 andThen(final Function1 g) {
      return HostTaskLocation$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return HostTaskLocation$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String host() {
      return this.host;
   }

   public String toString() {
      return this.host();
   }

   public HostTaskLocation copy(final String host) {
      return new HostTaskLocation(host);
   }

   public String copy$default$1() {
      return this.host();
   }

   public String productPrefix() {
      return "HostTaskLocation";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.host();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof HostTaskLocation;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "host";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof HostTaskLocation) {
               label40: {
                  HostTaskLocation var4 = (HostTaskLocation)x$1;
                  String var10000 = this.host();
                  String var5 = var4.host();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public HostTaskLocation(final String host) {
      this.host = host;
      Product.$init$(this);
   }
}
