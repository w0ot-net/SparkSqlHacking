package org.apache.spark.api.python;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.Socket;
import org.apache.spark.SparkEnv;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.security.SocketAuthServer;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005}3Q\u0001C\u0005\u0001\u001bMA\u0001B\n\u0001\u0003\u0006\u0004%\t\u0001\u000b\u0005\t[\u0001\u0011\t\u0011)A\u0005S!Aa\u0006\u0001BC\u0002\u0013\u0005q\u0006\u0003\u0005K\u0001\t\u0005\t\u0015!\u00031\u0011\u0015Y\u0005\u0001\"\u0001M\u0011\u0015\t\u0006\u0001\"\u0011S\u0011\u0015i\u0006\u0001\"\u0001_\u0005y)en\u0019:zaR,G\rU=uQ>t'I]8bI\u000e\f7\u000f^*feZ,'O\u0003\u0002\u000b\u0017\u00051\u0001/\u001f;i_:T!\u0001D\u0007\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u000f\u001f\u0005)1\u000f]1sW*\u0011\u0001#E\u0001\u0007CB\f7\r[3\u000b\u0003I\t1a\u001c:h'\r\u0001A\u0003\t\t\u0004+aQR\"\u0001\f\u000b\u0005]i\u0011\u0001C:fGV\u0014\u0018\u000e^=\n\u0005e1\"\u0001E*pG.,G/Q;uQN+'O^3s!\tYb$D\u0001\u001d\u0015\u0005i\u0012!B:dC2\f\u0017BA\u0010\u001d\u0005\u0011)f.\u001b;\u0011\u0005\u0005\"S\"\u0001\u0012\u000b\u0005\rj\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0015\u0012#a\u0002'pO\u001eLgnZ\u0001\u0004K:48\u0001A\u000b\u0002SA\u0011!fK\u0007\u0002\u001b%\u0011A&\u0004\u0002\t'B\f'o[#om\u0006!QM\u001c<!\u0003-IGm]!oI\u001aKG.Z:\u0016\u0003A\u00022!M\u001d=\u001d\t\u0011tG\u0004\u00024m5\tAG\u0003\u00026O\u00051AH]8pizJ\u0011!H\u0005\u0003qq\tq\u0001]1dW\u0006<W-\u0003\u0002;w\t\u00191+Z9\u000b\u0005ab\u0002\u0003B\u000e>\u007f\tK!A\u0010\u000f\u0003\rQ+\b\u000f\\33!\tY\u0002)\u0003\u0002B9\t!Aj\u001c8h!\t\u0019uI\u0004\u0002E\u000bB\u00111\u0007H\u0005\u0003\rr\ta\u0001\u0015:fI\u00164\u0017B\u0001%J\u0005\u0019\u0019FO]5oO*\u0011a\tH\u0001\rS\u0012\u001c\u0018I\u001c3GS2,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00075{\u0005\u000b\u0005\u0002O\u00015\t\u0011\u0002C\u0003'\u000b\u0001\u0007\u0011\u0006C\u0003/\u000b\u0001\u0007\u0001'\u0001\tiC:$G.Z\"p]:,7\r^5p]R\u0011!d\u0015\u0005\u0006)\u001a\u0001\r!V\u0001\u0007g>\u001c7.\u001a;\u0011\u0005Y[V\"A,\u000b\u0005aK\u0016a\u00018fi*\t!,\u0001\u0003kCZ\f\u0017B\u0001/X\u0005\u0019\u0019vnY6fi\u0006Ir/Y5u)&dGN\u0011:pC\u0012\u001c\u0017m\u001d;ECR\f7+\u001a8u)\u0005Q\u0002"
)
public class EncryptedPythonBroadcastServer extends SocketAuthServer {
   private final SparkEnv env;
   private final Seq idsAndFiles;

   public SparkEnv env() {
      return this.env;
   }

   public Seq idsAndFiles() {
      return this.idsAndFiles;
   }

   public void handleConnection(final Socket socket) {
      DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
      ObjectRef socketIn = ObjectRef.create((Object)null);
      Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
         this.idsAndFiles().foreach((x0$1) -> BoxesRunTime.boxToLong($anonfun$handleConnection$6(this, out, x0$1)));
         this.logTrace(() -> "waiting for python to accept broadcast data over socket");
         out.flush();
         socketIn.elem = socket.getInputStream();
         ((InputStream)socketIn.elem).read();
         this.logTrace(() -> "done serving broadcast data");
      }, (JFunction0.mcV.sp)() -> {
         JavaUtils.closeQuietly((InputStream)socketIn.elem);
         JavaUtils.closeQuietly(out);
      });
   }

   public void waitTillBroadcastDataSent() {
      this.getResult();
   }

   // $FF: synthetic method
   public static final long $anonfun$handleConnection$6(final EncryptedPythonBroadcastServer $this, final DataOutputStream out$7, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long id = x0$1._1$mcJ$sp();
         String path = (String)x0$1._2();
         out$7.writeLong(id);
         InputStream in = $this.env().serializerManager().wrapForEncryption((InputStream)(new FileInputStream(path)));
         return BoxesRunTime.unboxToLong(Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcJ.sp)() -> Utils$.MODULE$.copyStream(in, out$7, false, Utils$.MODULE$.copyStream$default$4()), (JFunction0.mcV.sp)() -> in.close()));
      } else {
         throw new MatchError(x0$1);
      }
   }

   public EncryptedPythonBroadcastServer(final SparkEnv env, final Seq idsAndFiles) {
      super("broadcast-decrypt-server");
      this.env = env;
      this.idsAndFiles = idsAndFiles;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
