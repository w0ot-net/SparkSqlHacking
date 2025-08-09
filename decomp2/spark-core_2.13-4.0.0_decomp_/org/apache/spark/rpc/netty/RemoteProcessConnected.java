package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c!\u0002\f\u0018\u0001^\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011#Q\u0001\nyBQa\u0011\u0001\u0005\u0002\u0011Cqa\u0012\u0001\u0002\u0002\u0013\u0005\u0001\nC\u0004K\u0001E\u0005I\u0011A&\t\u000fY\u0003\u0011\u0011!C!/\"9\u0001\rAA\u0001\n\u0003\t\u0007bB3\u0001\u0003\u0003%\tA\u001a\u0005\bY\u0002\t\t\u0011\"\u0011n\u0011\u001d!\b!!A\u0005\u0002UDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\b\u0015\u0005%q#!A\t\u0002]\tYAB\u0005\u0017/\u0005\u0005\t\u0012A\f\u0002\u000e!11\t\u0005C\u0001\u0003KA\u0001b \t\u0002\u0002\u0013\u0015\u0013\u0011\u0001\u0005\n\u0003O\u0001\u0012\u0011!CA\u0003SA\u0011\"!\f\u0011\u0003\u0003%\t)a\f\t\u0013\u0005m\u0002#!A\u0005\n\u0005u\"A\u0006*f[>$X\r\u0015:pG\u0016\u001c8oQ8o]\u0016\u001cG/\u001a3\u000b\u0005aI\u0012!\u00028fiRL(B\u0001\u000e\u001c\u0003\r\u0011\bo\u0019\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sON)\u0001A\t\u0015-_A\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u0004\"!\u000b\u0016\u000e\u0003]I!aK\f\u0003\u0019%s'm\u001c=NKN\u001c\u0018mZ3\u0011\u0005\rj\u0013B\u0001\u0018%\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001M\u001d\u000f\u0005E:dB\u0001\u001a7\u001b\u0005\u0019$B\u0001\u001b6\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0013\n\u0005a\"\u0013a\u00029bG.\fw-Z\u0005\u0003um\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000f\u0013\u0002\u001bI,Wn\u001c;f\u0003\u0012$'/Z:t+\u0005q\u0004CA A\u001b\u0005I\u0012BA!\u001a\u0005)\u0011\u0006oY!eIJ,7o]\u0001\u000fe\u0016lw\u000e^3BI\u0012\u0014Xm]:!\u0003\u0019a\u0014N\\5u}Q\u0011QI\u0012\t\u0003S\u0001AQ\u0001P\u0002A\u0002y\nAaY8qsR\u0011Q)\u0013\u0005\by\u0011\u0001\n\u00111\u0001?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0014\u0016\u0003}5[\u0013A\u0014\t\u0003\u001fRk\u0011\u0001\u0015\u0006\u0003#J\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005M#\u0013AC1o]>$\u0018\r^5p]&\u0011Q\u000b\u0015\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001Y!\tIf,D\u0001[\u0015\tYF,\u0001\u0003mC:<'\"A/\u0002\t)\fg/Y\u0005\u0003?j\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u00012\u0011\u0005\r\u001a\u0017B\u00013%\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t9'\u000e\u0005\u0002$Q&\u0011\u0011\u000e\n\u0002\u0004\u0003:L\bbB6\t\u0003\u0003\u0005\rAY\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u00039\u00042a\u001c:h\u001b\u0005\u0001(BA9%\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003gB\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011a/\u001f\t\u0003G]L!\u0001\u001f\u0013\u0003\u000f\t{w\u000e\\3b]\"91NCA\u0001\u0002\u00049\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$\"\u0001\u0017?\t\u000f-\\\u0011\u0011!a\u0001E\u0006A\u0001.Y:i\u0007>$W\rF\u0001c\u0003!!xn\u0015;sS:<G#\u0001-\u0002\r\u0015\fX/\u00197t)\r1\u0018q\u0001\u0005\bW:\t\t\u00111\u0001h\u0003Y\u0011V-\\8uKB\u0013xnY3tg\u000e{gN\\3di\u0016$\u0007CA\u0015\u0011'\u0015\u0001\u0012qBA\u000e!\u0019\t\t\"a\u0006?\u000b6\u0011\u00111\u0003\u0006\u0004\u0003+!\u0013a\u0002:v]RLW.Z\u0005\u0005\u00033\t\u0019BA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!!\b\u0002$5\u0011\u0011q\u0004\u0006\u0004\u0003Ca\u0016AA5p\u0013\rQ\u0014q\u0004\u000b\u0003\u0003\u0017\tQ!\u00199qYf$2!RA\u0016\u0011\u0015a4\u00031\u0001?\u0003\u001d)h.\u00199qYf$B!!\r\u00028A!1%a\r?\u0013\r\t)\u0004\n\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005eB#!AA\u0002\u0015\u000b1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\u0004E\u0002Z\u0003\u0003J1!a\u0011[\u0005\u0019y%M[3di\u0002"
)
public class RemoteProcessConnected implements InboxMessage, Product, Serializable {
   private final RpcAddress remoteAddress;

   public static Option unapply(final RemoteProcessConnected x$0) {
      return RemoteProcessConnected$.MODULE$.unapply(x$0);
   }

   public static RemoteProcessConnected apply(final RpcAddress remoteAddress) {
      return RemoteProcessConnected$.MODULE$.apply(remoteAddress);
   }

   public static Function1 andThen(final Function1 g) {
      return RemoteProcessConnected$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return RemoteProcessConnected$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public RpcAddress remoteAddress() {
      return this.remoteAddress;
   }

   public RemoteProcessConnected copy(final RpcAddress remoteAddress) {
      return new RemoteProcessConnected(remoteAddress);
   }

   public RpcAddress copy$default$1() {
      return this.remoteAddress();
   }

   public String productPrefix() {
      return "RemoteProcessConnected";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.remoteAddress();
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
      return x$1 instanceof RemoteProcessConnected;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "remoteAddress";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof RemoteProcessConnected) {
               label40: {
                  RemoteProcessConnected var4 = (RemoteProcessConnected)x$1;
                  RpcAddress var10000 = this.remoteAddress();
                  RpcAddress var5 = var4.remoteAddress();
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

   public RemoteProcessConnected(final RpcAddress remoteAddress) {
      this.remoteAddress = remoteAddress;
      Product.$init$(this);
   }
}
