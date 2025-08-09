package org.apache.spark.rpc;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d!\u0002\u000f\u001e\u0001~)\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0019\u0003!\u0011#Q\u0001\nyB\u0001b\u0012\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\u0013\")Q\n\u0001C\u0001\u001d\")1\u000b\u0001C\u0001{!)A\u000b\u0001C\u0001{!)Q\u000b\u0001C!-\"9q\u000bAA\u0001\n\u0003A\u0006bB.\u0001#\u0003%\t\u0001\u0018\u0005\bO\u0002\t\n\u0011\"\u0001i\u0011\u001dQ\u0007!!A\u0005B-Dqa\u001d\u0001\u0002\u0002\u0013\u0005\u0001\nC\u0004u\u0001\u0005\u0005I\u0011A;\t\u000fm\u0004\u0011\u0011!C!y\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0002\u0005\n\u0003'\u0001\u0011\u0011!C!\u0003+A\u0011\"!\u0007\u0001\u0003\u0003%\t%a\u0007\t\u0013\u0005u\u0001!!A\u0005B\u0005}q\u0001CA\u0012;!\u0005q$!\n\u0007\u000fqi\u0002\u0012A\u0010\u0002(!1Q*\u0006C\u0001\u0003gAq!!\u000e\u0016\t\u0003\t9\u0004C\u0004\u0002>U!\t!a\u0010\t\u000f\u0005\u0015S\u0003\"\u0001\u0002H!I\u0011QJ\u000b\u0002\u0002\u0013\u0005\u0015q\n\u0005\n\u0003C*\u0012\u0011!C\u0005\u0003G\u0012!B\u00159d\u0003\u0012$'/Z:t\u0015\tqr$A\u0002sa\u000eT!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\n\u0005\u0001\u0019bs\u0006\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VM\u001a\t\u0003O5J!A\f\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001'\u000f\b\u0003c]r!A\r\u001c\u000e\u0003MR!\u0001N\u001b\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!K\u0005\u0003q!\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001\bK\u0001\u0005Q>\u001cH/F\u0001?!\ty4I\u0004\u0002A\u0003B\u0011!\u0007K\u0005\u0003\u0005\"\na\u0001\u0015:fI\u00164\u0017B\u0001#F\u0005\u0019\u0019FO]5oO*\u0011!\tK\u0001\u0006Q>\u001cH\u000fI\u0001\u0005a>\u0014H/F\u0001J!\t9#*\u0003\u0002LQ\t\u0019\u0011J\u001c;\u0002\u000bA|'\u000f\u001e\u0011\u0002\rqJg.\u001b;?)\ry\u0015K\u0015\t\u0003!\u0002i\u0011!\b\u0005\u0006y\u0015\u0001\rA\u0010\u0005\u0006\u000f\u0016\u0001\r!S\u0001\tQ>\u001cH\u000fU8si\u0006QAo\\*qCJ\\WK\u0015'\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012AP\u0001\u0005G>\u0004\u0018\u0010F\u0002P3jCq\u0001P\u0005\u0011\u0002\u0003\u0007a\bC\u0004H\u0013A\u0005\t\u0019A%\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tQL\u000b\u0002?=.\nq\f\u0005\u0002aK6\t\u0011M\u0003\u0002cG\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003I\"\n!\"\u00198o_R\fG/[8o\u0013\t1\u0017MA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001jU\tIe,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002YB\u0011QN]\u0007\u0002]*\u0011q\u000e]\u0001\u0005Y\u0006twMC\u0001r\u0003\u0011Q\u0017M^1\n\u0005\u0011s\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003mf\u0004\"aJ<\n\u0005aD#aA!os\"9!PDA\u0001\u0002\u0004I\u0015a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001~!\u0011q\u00181\u0001<\u000e\u0003}T1!!\u0001)\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0004\u0003\u000by(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0003\u0002\u0012A\u0019q%!\u0004\n\u0007\u0005=\u0001FA\u0004C_>dW-\u00198\t\u000fi\u0004\u0012\u0011!a\u0001m\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\ra\u0017q\u0003\u0005\buF\t\t\u00111\u0001J\u0003!A\u0017m\u001d5D_\u0012,G#A%\u0002\r\u0015\fX/\u00197t)\u0011\tY!!\t\t\u000fi\u001c\u0012\u0011!a\u0001m\u0006Q!\u000b]2BI\u0012\u0014Xm]:\u0011\u0005A+2\u0003B\u000b'\u0003S\u0001B!a\u000b\u000225\u0011\u0011Q\u0006\u0006\u0004\u0003_\u0001\u0018AA5p\u0013\rQ\u0014Q\u0006\u000b\u0003\u0003K\tQ!\u00199qYf$RaTA\u001d\u0003wAQ\u0001P\fA\u0002yBQaR\fA\u0002%\u000bQB\u001a:p[V\u0013Hn\u0015;sS:<GcA(\u0002B!1\u00111\t\rA\u0002y\n1!\u001e:j\u000311'o\\7Ta\u0006\u00148.\u0016*M)\ry\u0015\u0011\n\u0005\u0007\u0003\u0017J\u0002\u0019\u0001 \u0002\u0011M\u0004\u0018M]6Ve2\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002R\u0005u\u0003#B\u0014\u0002T\u0005]\u0013bAA+Q\t1q\n\u001d;j_:\u0004RaJA-}%K1!a\u0017)\u0005\u0019!V\u000f\u001d7fe!A\u0011q\f\u000e\u0002\u0002\u0003\u0007q*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001a\u0011\u00075\f9'C\u0002\u0002j9\u0014aa\u00142kK\u000e$\b"
)
public class RpcAddress implements Product, Serializable {
   private final String host;
   private final int port;

   public static Option unapply(final RpcAddress x$0) {
      return RpcAddress$.MODULE$.unapply(x$0);
   }

   public static RpcAddress fromSparkURL(final String sparkUrl) {
      return RpcAddress$.MODULE$.fromSparkURL(sparkUrl);
   }

   public static RpcAddress fromUrlString(final String uri) {
      return RpcAddress$.MODULE$.fromUrlString(uri);
   }

   public static RpcAddress apply(final String host, final int port) {
      return RpcAddress$.MODULE$.apply(host, port);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String host() {
      return this.host;
   }

   public int port() {
      return this.port;
   }

   public String hostPort() {
      String var10000 = this.host();
      return var10000 + ":" + this.port();
   }

   public String toSparkURL() {
      return "spark://" + this.hostPort();
   }

   public String toString() {
      return this.hostPort();
   }

   public RpcAddress copy(final String host, final int port) {
      return new RpcAddress(host, port);
   }

   public String copy$default$1() {
      return this.host();
   }

   public int copy$default$2() {
      return this.port();
   }

   public String productPrefix() {
      return "RpcAddress";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.host();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.port());
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
      return x$1 instanceof RpcAddress;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "host";
         }
         case 1 -> {
            return "port";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.host()));
      var1 = Statics.mix(var1, this.port());
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof RpcAddress) {
               RpcAddress var4 = (RpcAddress)x$1;
               if (this.port() == var4.port()) {
                  label44: {
                     String var10000 = this.host();
                     String var5 = var4.host();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
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

   public RpcAddress(final String host, final int port) {
      this.host = host;
      this.port = port;
      Product.$init$(this);
   }
}
