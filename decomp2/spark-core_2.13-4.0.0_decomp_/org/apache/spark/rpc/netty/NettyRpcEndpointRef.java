package org.apache.spark.rpc.netty;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.rpc.AbortableRpcFuture;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcEndpointAddress;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcTimeout;
import scala.Predef.;
import scala.concurrent.Future;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e!\u0002\f\u0018\u0001]\t\u0003\u0002\u0003\u0014\u0001\u0005\u000b\u0007I\u0011\u0002\u0015\t\u00115\u0002!\u0011!Q\u0001\n%B\u0001\"\u000e\u0001\u0003\u0006\u0004%IA\u000e\u0005\tu\u0001\u0011\t\u0011)A\u0005o!A1\b\u0001BA\u0002\u0013%A\b\u0003\u0005B\u0001\t\u0005\r\u0011\"\u0003C\u0011!A\u0005A!A!B\u0013i\u0004\"\u0002(\u0001\t\u0003y\u0005\"\u0003+\u0001\u0001\u0004\u0005\r\u0011\"\u0001V\u0011%i\u0006\u00011AA\u0002\u0013\u0005a\fC\u0005a\u0001\u0001\u0007\t\u0011)Q\u0005-\")1\r\u0001C!I\")\u0001\u000e\u0001C\u0005S\")A\u000f\u0001C\u0005k\")1\u0010\u0001C!y\"9\u0011\u0011\u0003\u0001\u0005B\u0005M\u0001bBA*\u0001\u0011\u0005\u0013Q\u000b\u0005\b\u0003k\u0002A\u0011IA<\u0011\u001d\tY\b\u0001C!\u0003{Bq!a \u0001\t\u000b\n\t\tC\u0004\u0002\u000e\u0002!)%a$\u0003'9+G\u000f^=Sa\u000e,e\u000e\u001a9pS:$(+\u001a4\u000b\u0005aI\u0012!\u00028fiRL(B\u0001\u000e\u001c\u0003\r\u0011\bo\u0019\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sON\u0011\u0001A\t\t\u0003G\u0011j\u0011!G\u0005\u0003Ke\u0011aB\u00159d\u000b:$\u0007o\\5oiJ+g-\u0001\u0003d_:47\u0001A\u000b\u0002SA\u0011!fK\u0007\u00027%\u0011Af\u0007\u0002\n'B\f'o[\"p]\u001a\fQaY8oM\u0002B#AA\u0018\u0011\u0005A\u001aT\"A\u0019\u000b\u0003I\nQa]2bY\u0006L!\u0001N\u0019\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018aD3oIB|\u0017N\u001c;BI\u0012\u0014Xm]:\u0016\u0003]\u0002\"a\t\u001d\n\u0005eJ\"A\u0005*qG\u0016sG\r]8j]R\fE\r\u001a:fgN\f\u0001#\u001a8ea>Lg\u000e^!eIJ,7o\u001d\u0011\u0002\u00119,G\u000f^=F]Z,\u0012!\u0010\t\u0003}}j\u0011aF\u0005\u0003\u0001^\u00111BT3uif\u0014\u0006oY#om\u0006aa.\u001a;us\u0016sgo\u0018\u0013fcR\u00111I\u0012\t\u0003a\u0011K!!R\u0019\u0003\tUs\u0017\u000e\u001e\u0005\b\u000f\u001a\t\t\u00111\u0001>\u0003\rAH%M\u0001\n]\u0016$H/_#om\u0002B#aB\u0018)\u0005\u001dY\u0005C\u0001\u0019M\u0013\ti\u0015G\u0001\u0005w_2\fG/\u001b7f\u0003\u0019a\u0014N\\5u}Q!\u0001+\u0015*T!\tq\u0004\u0001C\u0003'\u0011\u0001\u0007\u0011\u0006C\u00036\u0011\u0001\u0007q\u0007C\u0003<\u0011\u0001\u0007Q(\u0001\u0004dY&,g\u000e^\u000b\u0002-B\u0011qkW\u0007\u00021*\u0011A+\u0017\u0006\u00035n\tqA\\3uo>\u00148.\u0003\u0002]1\nyAK]1ogB|'\u000f^\"mS\u0016tG/\u0001\u0006dY&,g\u000e^0%KF$\"aQ0\t\u000f\u001dS\u0011\u0011!a\u0001-\u000691\r\\5f]R\u0004\u0003FA\u00060Q\tY1*A\u0004bI\u0012\u0014Xm]:\u0016\u0003\u0015\u0004\"a\t4\n\u0005\u001dL\"A\u0003*qG\u0006#GM]3tg\u0006Q!/Z1e\u001f\nTWm\u0019;\u0015\u0005\rS\u0007\"B6\u000e\u0001\u0004a\u0017AA5o!\ti'/D\u0001o\u0015\ty\u0007/\u0001\u0002j_*\t\u0011/\u0001\u0003kCZ\f\u0017BA:o\u0005Ey%M[3di&s\u0007/\u001e;TiJ,\u0017-\\\u0001\foJLG/Z(cU\u0016\u001cG\u000f\u0006\u0002Dm\")qO\u0004a\u0001q\u0006\u0019q.\u001e;\u0011\u00055L\u0018B\u0001>o\u0005Iy%M[3di>+H\u000f];u'R\u0014X-Y7\u0002\t9\fW.Z\u000b\u0002{B\u0019a0a\u0003\u000f\u0007}\f9\u0001E\u0002\u0002\u0002Ej!!a\u0001\u000b\u0007\u0005\u0015q%\u0001\u0004=e>|GOP\u0005\u0004\u0003\u0013\t\u0014A\u0002)sK\u0012,g-\u0003\u0003\u0002\u000e\u0005=!AB*ue&twMC\u0002\u0002\nE\nA\"Y:l\u0003\n|'\u000f^1cY\u0016,B!!\u0006\u0002$Q1\u0011qCA#\u0003\u0013\"B!!\u0007\u00026A)1%a\u0007\u0002 %\u0019\u0011QD\r\u0003%\u0005\u0013wN\u001d;bE2,'\u000b]2GkR,(/\u001a\t\u0005\u0003C\t\u0019\u0003\u0004\u0001\u0005\u000f\u0005\u0015\u0002C1\u0001\u0002(\t\tA+\u0005\u0003\u0002*\u0005=\u0002c\u0001\u0019\u0002,%\u0019\u0011QF\u0019\u0003\u000f9{G\u000f[5oOB\u0019\u0001'!\r\n\u0007\u0005M\u0012GA\u0002B]fD\u0011\"a\u000e\u0011\u0003\u0003\u0005\u001d!!\u000f\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0004\u0002<\u0005\u0005\u0013qD\u0007\u0003\u0003{Q1!a\u00102\u0003\u001d\u0011XM\u001a7fGRLA!a\u0011\u0002>\tA1\t\\1tgR\u000bw\rC\u0004\u0002HA\u0001\r!a\f\u0002\u000f5,7o]1hK\"9\u00111\n\tA\u0002\u00055\u0013a\u0002;j[\u0016|W\u000f\u001e\t\u0004G\u0005=\u0013bAA)3\tQ!\u000b]2US6,w.\u001e;\u0002\u0007\u0005\u001c8.\u0006\u0003\u0002X\u0005%DCBA-\u0003c\n\u0019\b\u0006\u0003\u0002\\\u0005-\u0004CBA/\u0003G\n9'\u0004\u0002\u0002`)\u0019\u0011\u0011M\u0019\u0002\u0015\r|gnY;se\u0016tG/\u0003\u0003\u0002f\u0005}#A\u0002$viV\u0014X\r\u0005\u0003\u0002\"\u0005%DaBA\u0013#\t\u0007\u0011q\u0005\u0005\n\u0003[\n\u0012\u0011!a\u0002\u0003_\n!\"\u001a<jI\u0016t7-\u001a\u00136!\u0019\tY$!\u0011\u0002h!9\u0011qI\tA\u0002\u0005=\u0002bBA&#\u0001\u0007\u0011QJ\u0001\u0005g\u0016tG\rF\u0002D\u0003sBq!a\u0012\u0013\u0001\u0004\ty#\u0001\u0005u_N#(/\u001b8h)\u0005i\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0004\u0006%\u0005c\u0001\u0019\u0002\u0006&\u0019\u0011qQ\u0019\u0003\u000f\t{w\u000e\\3b]\"9\u00111\u0012\u000bA\u0002\u0005=\u0012\u0001\u0002;iCR\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003#\u00032\u0001MAJ\u0013\r\t)*\r\u0002\u0004\u0013:$\b"
)
public class NettyRpcEndpointRef extends RpcEndpointRef {
   private final transient SparkConf conf;
   private final RpcEndpointAddress endpointAddress;
   private transient volatile NettyRpcEnv nettyEnv;
   private transient volatile TransportClient client;

   private SparkConf conf() {
      return this.conf;
   }

   private RpcEndpointAddress endpointAddress() {
      return this.endpointAddress;
   }

   private NettyRpcEnv nettyEnv() {
      return this.nettyEnv;
   }

   private void nettyEnv_$eq(final NettyRpcEnv x$1) {
      this.nettyEnv = x$1;
   }

   public TransportClient client() {
      return this.client;
   }

   public void client_$eq(final TransportClient x$1) {
      this.client = x$1;
   }

   public RpcAddress address() {
      return this.endpointAddress().rpcAddress() != null ? this.endpointAddress().rpcAddress() : null;
   }

   private void readObject(final ObjectInputStream in) {
      in.defaultReadObject();
      this.nettyEnv_$eq((NettyRpcEnv)NettyRpcEnv$.MODULE$.currentEnv().value());
      this.client_$eq((TransportClient)NettyRpcEnv$.MODULE$.currentClient().value());
   }

   private void writeObject(final ObjectOutputStream out) {
      out.defaultWriteObject();
   }

   public String name() {
      return this.endpointAddress().name();
   }

   public AbortableRpcFuture askAbortable(final Object message, final RpcTimeout timeout, final ClassTag evidence$4) {
      return this.nettyEnv().askAbortable(new RequestMessage(this.nettyEnv().address(), this, message), timeout, evidence$4);
   }

   public Future ask(final Object message, final RpcTimeout timeout, final ClassTag evidence$5) {
      return this.askAbortable(message, timeout, evidence$5).future();
   }

   public void send(final Object message) {
      .MODULE$.require(message != null, () -> "Message is null");
      this.nettyEnv().send(new RequestMessage(this.nettyEnv().address(), this, message));
   }

   public String toString() {
      return "NettyRpcEndpointRef(" + this.endpointAddress() + ")";
   }

   public final boolean equals(final Object that) {
      if (!(that instanceof NettyRpcEndpointRef var4)) {
         return false;
      } else {
         boolean var6;
         label30: {
            RpcEndpointAddress var10000 = this.endpointAddress();
            RpcEndpointAddress var5 = var4.endpointAddress();
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   public final int hashCode() {
      return this.endpointAddress() == null ? 0 : this.endpointAddress().hashCode();
   }

   public NettyRpcEndpointRef(final SparkConf conf, final RpcEndpointAddress endpointAddress, final NettyRpcEnv nettyEnv) {
      this.conf = conf;
      this.endpointAddress = endpointAddress;
      this.nettyEnv = nettyEnv;
      super(conf);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
