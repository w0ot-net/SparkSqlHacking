package org.apache.spark.rpc.netty;

import java.io.Serializable;
import org.apache.spark.rpc.RpcAddress;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005e!\u0002\u000f\u001e\u0001v9\u0003\u0002\u0003\"\u0001\u0005+\u0007I\u0011A\"\t\u0011!\u0003!\u0011#Q\u0001\n\u0011C\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0017\"Aq\n\u0001BK\u0002\u0013\u0005\u0001\u000b\u0003\u0005U\u0001\tE\t\u0015!\u0003R\u0011\u0015)\u0006\u0001\"\u0001W\u0011\u001dY\u0006!!A\u0005\u0002qCq\u0001\u0019\u0001\u0012\u0002\u0013\u0005\u0011\rC\u0004m\u0001E\u0005I\u0011A7\t\u000f=\u0004\u0011\u0013!C\u0001a\"9!\u000fAA\u0001\n\u0003\u001a\bb\u0002?\u0001\u0003\u0003%\t! \u0005\n\u0003\u0007\u0001\u0011\u0011!C\u0001\u0003\u000bA\u0011\"a\u0003\u0001\u0003\u0003%\t%!\u0004\t\u0013\u0005m\u0001!!A\u0005\u0002\u0005u\u0001\"CA\u0014\u0001\u0005\u0005I\u0011IA\u0015\u0011%\ti\u0003AA\u0001\n\u0003\ny\u0003C\u0005\u00022\u0001\t\t\u0011\"\u0011\u00024!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0013qG\u0004\u000b\u0003wi\u0012\u0011!E\u0001;\u0005ub!\u0003\u000f\u001e\u0003\u0003E\t!HA \u0011\u0019)f\u0003\"\u0001\u0002X!I\u0011\u0011\u0007\f\u0002\u0002\u0013\u0015\u00131\u0007\u0005\n\u000332\u0012\u0011!CA\u00037B\u0011\"a\u0019\u0017\u0003\u0003%\t)!\u001a\t\u0013\u0005]d#!A\u0005\n\u0005e$A\u0003*qG6+7o]1hK*\u0011adH\u0001\u0006]\u0016$H/\u001f\u0006\u0003A\u0005\n1A\u001d9d\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7#\u0002\u0001)]I*\u0004CA\u0015-\u001b\u0005Q#\"A\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00055R#AB!osJ+g\r\u0005\u00020a5\tQ$\u0003\u00022;\ta\u0011J\u001c2pq6+7o]1hKB\u0011\u0011fM\u0005\u0003i)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00027\u007f9\u0011q'\u0010\b\u0003qqj\u0011!\u000f\u0006\u0003um\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002W%\u0011aHK\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0001\u0015I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002?U\u0005i1/\u001a8eKJ\fE\r\u001a:fgN,\u0012\u0001\u0012\t\u0003\u000b\u001ak\u0011aH\u0005\u0003\u000f~\u0011!B\u00159d\u0003\u0012$'/Z:t\u00039\u0019XM\u001c3fe\u0006#GM]3tg\u0002\nqaY8oi\u0016tG/F\u0001L!\tIC*\u0003\u0002NU\t\u0019\u0011I\\=\u0002\u0011\r|g\u000e^3oi\u0002\nqaY8oi\u0016DH/F\u0001R!\ty#+\u0003\u0002T;\t\u0019b*\u001a;usJ\u00038mQ1mY\u000e{g\u000e^3yi\u0006A1m\u001c8uKb$\b%\u0001\u0004=S:LGO\u0010\u000b\u0005/bK&\f\u0005\u00020\u0001!)!i\u0002a\u0001\t\")\u0011j\u0002a\u0001\u0017\")qj\u0002a\u0001#\u0006!1m\u001c9z)\u00119VLX0\t\u000f\tC\u0001\u0013!a\u0001\t\"9\u0011\n\u0003I\u0001\u0002\u0004Y\u0005bB(\t!\u0003\u0005\r!U\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\u0011'F\u0001#dW\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003%)hn\u00195fG.,GM\u0003\u0002jU\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005-4'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012T#\u00018+\u0005-\u001b\u0017AD2paf$C-\u001a4bk2$HeM\u000b\u0002c*\u0012\u0011kY\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003Q\u0004\"!\u001e>\u000e\u0003YT!a\u001e=\u0002\t1\fgn\u001a\u0006\u0002s\u0006!!.\u0019<b\u0013\tYhO\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002}B\u0011\u0011f`\u0005\u0004\u0003\u0003Q#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HcA&\u0002\b!A\u0011\u0011\u0002\b\u0002\u0002\u0003\u0007a0A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u001f\u0001R!!\u0005\u0002\u0018-k!!a\u0005\u000b\u0007\u0005U!&\u0001\u0006d_2dWm\u0019;j_:LA!!\u0007\u0002\u0014\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty\"!\n\u0011\u0007%\n\t#C\u0002\u0002$)\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002\nA\t\t\u00111\u0001L\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007Q\fY\u0003\u0003\u0005\u0002\nE\t\t\u00111\u0001\u007f\u0003!A\u0017m\u001d5D_\u0012,G#\u0001@\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001^\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005}\u0011\u0011\b\u0005\t\u0003\u0013!\u0012\u0011!a\u0001\u0017\u0006Q!\u000b]2NKN\u001c\u0018mZ3\u0011\u0005=22#\u0002\f\u0002B\u00055\u0003\u0003CA\"\u0003\u0013\"5*U,\u000e\u0005\u0005\u0015#bAA$U\u00059!/\u001e8uS6,\u0017\u0002BA&\u0003\u000b\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\ty%!\u0016\u000e\u0005\u0005E#bAA*q\u0006\u0011\u0011n\\\u0005\u0004\u0001\u0006ECCAA\u001f\u0003\u0015\t\u0007\u000f\u001d7z)\u001d9\u0016QLA0\u0003CBQAQ\rA\u0002\u0011CQ!S\rA\u0002-CQaT\rA\u0002E\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002h\u0005M\u0004#B\u0015\u0002j\u00055\u0014bAA6U\t1q\n\u001d;j_:\u0004b!KA8\t.\u000b\u0016bAA9U\t1A+\u001e9mKNB\u0001\"!\u001e\u001b\u0003\u0003\u0005\raV\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA>!\r)\u0018QP\u0005\u0004\u0003\u007f2(AB(cU\u0016\u001cG\u000f"
)
public class RpcMessage implements InboxMessage, Product, Serializable {
   private final RpcAddress senderAddress;
   private final Object content;
   private final NettyRpcCallContext context;

   public static Option unapply(final RpcMessage x$0) {
      return RpcMessage$.MODULE$.unapply(x$0);
   }

   public static RpcMessage apply(final RpcAddress senderAddress, final Object content, final NettyRpcCallContext context) {
      return RpcMessage$.MODULE$.apply(senderAddress, content, context);
   }

   public static Function1 tupled() {
      return RpcMessage$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RpcMessage$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public RpcAddress senderAddress() {
      return this.senderAddress;
   }

   public Object content() {
      return this.content;
   }

   public NettyRpcCallContext context() {
      return this.context;
   }

   public RpcMessage copy(final RpcAddress senderAddress, final Object content, final NettyRpcCallContext context) {
      return new RpcMessage(senderAddress, content, context);
   }

   public RpcAddress copy$default$1() {
      return this.senderAddress();
   }

   public Object copy$default$2() {
      return this.content();
   }

   public NettyRpcCallContext copy$default$3() {
      return this.context();
   }

   public String productPrefix() {
      return "RpcMessage";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.senderAddress();
         }
         case 1 -> {
            return this.content();
         }
         case 2 -> {
            return this.context();
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
      return x$1 instanceof RpcMessage;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "senderAddress";
         }
         case 1 -> {
            return "content";
         }
         case 2 -> {
            return "context";
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
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof RpcMessage) {
               label51: {
                  RpcMessage var4 = (RpcMessage)x$1;
                  RpcAddress var10000 = this.senderAddress();
                  RpcAddress var5 = var4.senderAddress();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label51;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label51;
                  }

                  if (BoxesRunTime.equals(this.content(), var4.content())) {
                     label52: {
                        NettyRpcCallContext var7 = this.context();
                        NettyRpcCallContext var6 = var4.context();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label52;
                           }
                        } else if (!var7.equals(var6)) {
                           break label52;
                        }

                        if (var4.canEqual(this)) {
                           break label59;
                        }
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public RpcMessage(final RpcAddress senderAddress, final Object content, final NettyRpcCallContext context) {
      this.senderAddress = senderAddress;
      this.content = content;
      this.context = context;
      Product.$init$(this);
   }
}
