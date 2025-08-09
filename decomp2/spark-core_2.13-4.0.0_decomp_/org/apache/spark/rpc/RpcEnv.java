package org.apache.spark.rpc;

import java.nio.channels.ReadableByteChannel;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.util.RpcUtils$;
import scala.Function0;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005EuAB\f\u0019\u0011\u0003Q\u0002E\u0002\u0004#1!\u0005!d\t\u0005\u0006U\u0005!\t\u0001\f\u0005\u0006[\u0005!\tA\f\u0005\n\u0003C\n\u0011\u0013!C\u0001\u0003GBa!L\u0001\u0005\u0002\u0005edA\u0002\u0012\u0019\u0003\u0003Q\u0002\u0007\u0003\u00052\r\t\u0005\t\u0015!\u00033\u0011\u0015Qc\u0001\"\u00017\u0011!AdA1A\u0005\u0002iI\u0004BB\u001f\u0007A\u0003%!\b\u0003\u0004?\r\u0019\u0005\u0001d\u0010\u0005\u0006\u0011\u001a1\t!\u0013\u0005\u0006\u001b\u001a1\tA\u0014\u0005\u0006;\u001a1\tA\u0018\u0005\u0006O\u001a!\t\u0001\u001b\u0005\u0006U\u001a!\ta\u001b\u0005\u0006_\u001a1\t\u0001\u001d\u0005\u0006k\u001a1\tA\u001e\u0005\u0006o\u001a1\tA\u001e\u0005\u0006q\u001a1\t!\u001f\u0005\b\u0003/1a\u0011AA\r\u0011\u001d\t\tC\u0002D\u0001\u0003G\taA\u00159d\u000b:4(BA\r\u001b\u0003\r\u0011\bo\u0019\u0006\u00037q\tQa\u001d9be.T!!\b\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0012aA8sOB\u0011\u0011%A\u0007\u00021\t1!\u000b]2F]Z\u001c\"!\u0001\u0013\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0011\u0002\r\r\u0014X-\u0019;f)5y\u00131HA\u001f\u0003\u0003\nY%!\u0014\u0002XA\u0011\u0011EB\n\u0003\r\u0011\nAaY8oMB\u00111\u0007N\u0007\u00025%\u0011QG\u0007\u0002\n'B\f'o[\"p]\u001a$\"aL\u001c\t\u000bEB\u0001\u0019\u0001\u001a\u0002)\u0011,g-Y;mi2{wn[;q)&lWm\\;u+\u0005Q\u0004CA\u0011<\u0013\ta\u0004D\u0001\u0006Sa\u000e$\u0016.\\3pkR\fQ\u0003Z3gCVdG\u000fT8pWV\u0004H+[7f_V$\b%A\u0006f]\u0012\u0004x.\u001b8u%\u00164GC\u0001!D!\t\t\u0013)\u0003\u0002C1\tq!\u000b]2F]\u0012\u0004x.\u001b8u%\u00164\u0007\"\u0002#\f\u0001\u0004)\u0015\u0001C3oIB|\u0017N\u001c;\u0011\u0005\u00052\u0015BA$\u0019\u0005-\u0011\u0006oY#oIB|\u0017N\u001c;\u0002\u000f\u0005$GM]3tgV\t!\n\u0005\u0002\"\u0017&\u0011A\n\u0007\u0002\u000b%B\u001c\u0017\t\u001a3sKN\u001c\u0018!D:fiV\u0004XI\u001c3q_&tG\u000fF\u0002A\u001frCQ\u0001U\u0007A\u0002E\u000bAA\\1nKB\u0011!+\u0017\b\u0003'^\u0003\"\u0001\u0016\u0014\u000e\u0003US!AV\u0016\u0002\rq\u0012xn\u001c;?\u0013\tAf%\u0001\u0004Qe\u0016$WMZ\u0005\u00035n\u0013aa\u0015;sS:<'B\u0001-'\u0011\u0015!U\u00021\u0001F\u0003i\t7/\u001f8d'\u0016$X\u000f]#oIB|\u0017N\u001c;SK\u001a\u0014\u00150\u0016*J)\tyV\rE\u0002aG\u0002k\u0011!\u0019\u0006\u0003E\u001a\n!bY8oGV\u0014(/\u001a8u\u0013\t!\u0017M\u0001\u0004GkR,(/\u001a\u0005\u0006M:\u0001\r!U\u0001\u0004kJL\u0017!F:fiV\u0004XI\u001c3q_&tGOU3g\u0005f,&+\u0013\u000b\u0003\u0001&DQAZ\bA\u0002E\u000b\u0001c]3ukB,e\u000e\u001a9pS:$(+\u001a4\u0015\u0007\u0001cW\u000eC\u0003I!\u0001\u0007!\nC\u0003o!\u0001\u0007\u0011+\u0001\u0007f]\u0012\u0004x.\u001b8u\u001d\u0006lW-\u0001\u0003ti>\u0004HCA9u!\t)#/\u0003\u0002tM\t!QK\\5u\u0011\u0015!\u0015\u00031\u0001A\u0003!\u0019\b.\u001e;e_^tG#A9\u0002!\u0005<\u0018-\u001b;UKJl\u0017N\\1uS>t\u0017a\u00033fg\u0016\u0014\u0018.\u00197ju\u0016,\"A_?\u0015\u0007m\fi\u0001\u0005\u0002}{2\u0001A!\u0002@\u0015\u0005\u0004y(!\u0001+\u0012\t\u0005\u0005\u0011q\u0001\t\u0004K\u0005\r\u0011bAA\u0003M\t9aj\u001c;iS:<\u0007cA\u0013\u0002\n%\u0019\u00111\u0002\u0014\u0003\u0007\u0005s\u0017\u0010C\u0004\u0002\u0010Q\u0001\r!!\u0005\u0002+\u0011,7/\u001a:jC2L'0\u0019;j_:\f5\r^5p]B!Q%a\u0005|\u0013\r\t)B\n\u0002\n\rVt7\r^5p]B\n!BZ5mKN+'O^3s+\t\tY\u0002E\u0002\"\u0003;I1!a\b\u0019\u0005A\u0011\u0006oY#om\u001aKG.Z*feZ,'/A\u0006pa\u0016t7\t[1o]\u0016dG\u0003BA\u0013\u0003s\u0001B!a\n\u000265\u0011\u0011\u0011\u0006\u0006\u0005\u0003W\ti#\u0001\u0005dQ\u0006tg.\u001a7t\u0015\u0011\ty#!\r\u0002\u00079LwN\u0003\u0002\u00024\u0005!!.\u0019<b\u0013\u0011\t9$!\u000b\u0003'I+\u0017\rZ1cY\u0016\u0014\u0015\u0010^3DQ\u0006tg.\u001a7\t\u000b\u00194\u0002\u0019A)\t\u000bA\u001b\u0001\u0019A)\t\r\u0005}2\u00011\u0001R\u0003\u0011Awn\u001d;\t\u000f\u0005\r3\u00011\u0001\u0002F\u0005!\u0001o\u001c:u!\r)\u0013qI\u0005\u0004\u0003\u00132#aA%oi\")\u0011g\u0001a\u0001e!9\u0011qJ\u0002A\u0002\u0005E\u0013aD:fGV\u0014\u0018\u000e^=NC:\fw-\u001a:\u0011\u0007M\n\u0019&C\u0002\u0002Vi\u0011qbU3dkJLG/_'b]\u0006<WM\u001d\u0005\n\u00033\u001a\u0001\u0013!a\u0001\u00037\n!b\u00197jK:$Xj\u001c3f!\r)\u0013QL\u0005\u0004\u0003?2#a\u0002\"p_2,\u0017M\\\u0001\u0011GJ,\u0017\r^3%I\u00164\u0017-\u001e7uIY*\"!!\u001a+\t\u0005m\u0013qM\u0016\u0003\u0003S\u0002B!a\u001b\u0002v5\u0011\u0011Q\u000e\u0006\u0005\u0003_\n\t(A\u0005v]\u000eDWmY6fI*\u0019\u00111\u000f\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002x\u00055$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dKR\tr&a\u001f\u0002~\u0005\u0005\u0015QQAD\u0003\u0013\u000bY)a$\t\u000bA+\u0001\u0019A)\t\r\u0005}T\u00011\u0001R\u0003-\u0011\u0017N\u001c3BI\u0012\u0014Xm]:\t\r\u0005\rU\u00011\u0001R\u0003A\tGM^3si&\u001cX-\u00113ee\u0016\u001c8\u000fC\u0004\u0002D\u0015\u0001\r!!\u0012\t\u000bE*\u0001\u0019\u0001\u001a\t\u000f\u0005=S\u00011\u0001\u0002R!9\u0011QR\u0003A\u0002\u0005\u0015\u0013A\u00048v[V\u001b\u0018M\u00197f\u0007>\u0014Xm\u001d\u0005\b\u00033*\u0001\u0019AA.\u0001"
)
public abstract class RpcEnv {
   private final RpcTimeout defaultLookupTimeout;

   public static RpcEnv create(final String name, final String bindAddress, final String advertiseAddress, final int port, final SparkConf conf, final SecurityManager securityManager, final int numUsableCores, final boolean clientMode) {
      return RpcEnv$.MODULE$.create(name, bindAddress, advertiseAddress, port, conf, securityManager, numUsableCores, clientMode);
   }

   public static boolean create$default$6() {
      return RpcEnv$.MODULE$.create$default$6();
   }

   public static RpcEnv create(final String name, final String host, final int port, final SparkConf conf, final SecurityManager securityManager, final boolean clientMode) {
      return RpcEnv$.MODULE$.create(name, host, port, conf, securityManager, clientMode);
   }

   public RpcTimeout defaultLookupTimeout() {
      return this.defaultLookupTimeout;
   }

   public abstract RpcEndpointRef endpointRef(final RpcEndpoint endpoint);

   public abstract RpcAddress address();

   public abstract RpcEndpointRef setupEndpoint(final String name, final RpcEndpoint endpoint);

   public abstract Future asyncSetupEndpointRefByURI(final String uri);

   public RpcEndpointRef setupEndpointRefByURI(final String uri) {
      return (RpcEndpointRef)this.defaultLookupTimeout().awaitResult(this.asyncSetupEndpointRefByURI(uri));
   }

   public RpcEndpointRef setupEndpointRef(final RpcAddress address, final String endpointName) {
      return this.setupEndpointRefByURI((new RpcEndpointAddress(address, endpointName)).toString());
   }

   public abstract void stop(final RpcEndpointRef endpoint);

   public abstract void shutdown();

   public abstract void awaitTermination();

   public abstract Object deserialize(final Function0 deserializationAction);

   public abstract RpcEnvFileServer fileServer();

   public abstract ReadableByteChannel openChannel(final String uri);

   public RpcEnv(final SparkConf conf) {
      this.defaultLookupTimeout = RpcUtils$.MODULE$.lookupRpcTimeout(conf);
   }
}
