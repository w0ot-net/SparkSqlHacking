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
   bytes = "\u0006\u0005\u0005\u0015c!\u0002\f\u0018\u0001^\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011#Q\u0001\nyBQa\u0011\u0001\u0005\u0002\u0011Cqa\u0012\u0001\u0002\u0002\u0013\u0005\u0001\nC\u0004K\u0001E\u0005I\u0011A&\t\u000fY\u0003\u0011\u0011!C!/\"9\u0001\rAA\u0001\n\u0003\t\u0007bB3\u0001\u0003\u0003%\tA\u001a\u0005\bY\u0002\t\t\u0011\"\u0011n\u0011\u001d!\b!!A\u0005\u0002UDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\b\u0015\u0005%q#!A\t\u0002]\tYAB\u0005\u0017/\u0005\u0005\t\u0012A\f\u0002\u000e!11\t\u0005C\u0001\u0003KA\u0001b \t\u0002\u0002\u0013\u0015\u0013\u0011\u0001\u0005\n\u0003O\u0001\u0012\u0011!CA\u0003SA\u0011\"!\f\u0011\u0003\u0003%\t)a\f\t\u0013\u0005m\u0002#!A\u0005\n\u0005u\"!\u0007*f[>$X\r\u0015:pG\u0016\u001c8\u000fR5tG>tg.Z2uK\u0012T!\u0001G\r\u0002\u000b9,G\u000f^=\u000b\u0005iY\u0012a\u0001:qG*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014xmE\u0003\u0001E!bs\u0006\u0005\u0002$M5\tAEC\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9CE\u0001\u0004B]f\u0014VM\u001a\t\u0003S)j\u0011aF\u0005\u0003W]\u0011A\"\u00138c_blUm]:bO\u0016\u0004\"aI\u0017\n\u00059\"#a\u0002)s_\u0012,8\r\u001e\t\u0003aer!!M\u001c\u000f\u0005I2T\"A\u001a\u000b\u0005Q*\u0014A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0015J!\u0001\u000f\u0013\u0002\u000fA\f7m[1hK&\u0011!h\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003q\u0011\nQB]3n_R,\u0017\t\u001a3sKN\u001cX#\u0001 \u0011\u0005}\u0002U\"A\r\n\u0005\u0005K\"A\u0003*qG\u0006#GM]3tg\u0006q!/Z7pi\u0016\fE\r\u001a:fgN\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002F\rB\u0011\u0011\u0006\u0001\u0005\u0006y\r\u0001\rAP\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002F\u0013\"9A\b\u0002I\u0001\u0002\u0004q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0019*\u0012a(T\u0016\u0002\u001dB\u0011q\nV\u0007\u0002!*\u0011\u0011KU\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0015\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002V!\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\u0006CA-_\u001b\u0005Q&BA.]\u0003\u0011a\u0017M\\4\u000b\u0003u\u000bAA[1wC&\u0011qL\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\t\u0004\"aI2\n\u0005\u0011$#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA4k!\t\u0019\u0003.\u0003\u0002jI\t\u0019\u0011I\\=\t\u000f-D\u0011\u0011!a\u0001E\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A\u001c\t\u0004_J<W\"\u00019\u000b\u0005E$\u0013AC2pY2,7\r^5p]&\u00111\u000f\u001d\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002wsB\u00111e^\u0005\u0003q\u0012\u0012qAQ8pY\u0016\fg\u000eC\u0004l\u0015\u0005\u0005\t\u0019A4\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u00031rDqa[\u0006\u0002\u0002\u0003\u0007!-\u0001\u0005iCND7i\u001c3f)\u0005\u0011\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003a\u000ba!Z9vC2\u001cHc\u0001<\u0002\b!91NDA\u0001\u0002\u00049\u0017!\u0007*f[>$X\r\u0015:pG\u0016\u001c8\u000fR5tG>tg.Z2uK\u0012\u0004\"!\u000b\t\u0014\u000bA\ty!a\u0007\u0011\r\u0005E\u0011q\u0003 F\u001b\t\t\u0019BC\u0002\u0002\u0016\u0011\nqA];oi&lW-\u0003\u0003\u0002\u001a\u0005M!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u0011QDA\u0012\u001b\t\tyBC\u0002\u0002\"q\u000b!![8\n\u0007i\ny\u0002\u0006\u0002\u0002\f\u0005)\u0011\r\u001d9msR\u0019Q)a\u000b\t\u000bq\u001a\u0002\u0019\u0001 \u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011GA\u001c!\u0011\u0019\u00131\u0007 \n\u0007\u0005UBE\u0001\u0004PaRLwN\u001c\u0005\t\u0003s!\u0012\u0011!a\u0001\u000b\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0002cA-\u0002B%\u0019\u00111\t.\u0003\r=\u0013'.Z2u\u0001"
)
public class RemoteProcessDisconnected implements InboxMessage, Product, Serializable {
   private final RpcAddress remoteAddress;

   public static Option unapply(final RemoteProcessDisconnected x$0) {
      return RemoteProcessDisconnected$.MODULE$.unapply(x$0);
   }

   public static RemoteProcessDisconnected apply(final RpcAddress remoteAddress) {
      return RemoteProcessDisconnected$.MODULE$.apply(remoteAddress);
   }

   public static Function1 andThen(final Function1 g) {
      return RemoteProcessDisconnected$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return RemoteProcessDisconnected$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public RpcAddress remoteAddress() {
      return this.remoteAddress;
   }

   public RemoteProcessDisconnected copy(final RpcAddress remoteAddress) {
      return new RemoteProcessDisconnected(remoteAddress);
   }

   public RpcAddress copy$default$1() {
      return this.remoteAddress();
   }

   public String productPrefix() {
      return "RemoteProcessDisconnected";
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
      return x$1 instanceof RemoteProcessDisconnected;
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
            if (x$1 instanceof RemoteProcessDisconnected) {
               label40: {
                  RemoteProcessDisconnected var4 = (RemoteProcessDisconnected)x$1;
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

   public RemoteProcessDisconnected(final RpcAddress remoteAddress) {
      this.remoteAddress = remoteAddress;
      Product.$init$(this);
   }
}
