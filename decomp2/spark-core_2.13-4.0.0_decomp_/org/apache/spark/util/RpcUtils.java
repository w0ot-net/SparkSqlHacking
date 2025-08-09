package org.apache.spark.util;

import org.apache.spark.SparkConf;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcTimeout;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y;aa\u0003\u0007\t\u00029!bA\u0002\f\r\u0011\u0003qq\u0003C\u0003\u001f\u0003\u0011\u0005\u0001\u0005C\u0003\"\u0003\u0011\u0005!\u0005C\u0003B\u0003\u0011\u0005!\tC\u0003H\u0003\u0011\u0005\u0001\nC\u0004K\u0003\t\u0007I\u0011A&\t\r1\u000b\u0001\u0015!\u0003D\u0011\u001di\u0015A1A\u0005\n9CaAU\u0001!\u0002\u0013y\u0005\"B*\u0002\t\u0003!\u0016\u0001\u0003*qGV#\u0018\u000e\\:\u000b\u00055q\u0011\u0001B;uS2T!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'o\u001a\t\u0003+\u0005i\u0011\u0001\u0004\u0002\t%B\u001cW\u000b^5mgN\u0011\u0011\u0001\u0007\t\u00033qi\u0011A\u0007\u0006\u00027\u0005)1oY1mC&\u0011QD\u0007\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001F\u0001\u000e[\u0006\\W\r\u0012:jm\u0016\u0014(+\u001a4\u0015\t\rJc\u0007\u0010\t\u0003I\u001dj\u0011!\n\u0006\u0003M9\t1A\u001d9d\u0013\tASE\u0001\bSa\u000e,e\u000e\u001a9pS:$(+\u001a4\t\u000b)\u001a\u0001\u0019A\u0016\u0002\t9\fW.\u001a\t\u0003YMr!!L\u0019\u0011\u00059RR\"A\u0018\u000b\u0005Az\u0012A\u0002\u001fs_>$h(\u0003\u000235\u00051\u0001K]3eK\u001aL!\u0001N\u001b\u0003\rM#(/\u001b8h\u0015\t\u0011$\u0004C\u00038\u0007\u0001\u0007\u0001(\u0001\u0003d_:4\u0007CA\u001d;\u001b\u0005q\u0011BA\u001e\u000f\u0005%\u0019\u0006/\u0019:l\u0007>tg\rC\u0003>\u0007\u0001\u0007a(\u0001\u0004sa\u000e,eN\u001e\t\u0003I}J!\u0001Q\u0013\u0003\rI\u00038-\u00128w\u00035\t7o\u001b*qGRKW.Z8viR\u00111I\u0012\t\u0003I\u0011K!!R\u0013\u0003\u0015I\u00038\rV5nK>,H\u000fC\u00038\t\u0001\u0007\u0001(\u0001\tm_>\\W\u000f\u001d*qGRKW.Z8viR\u00111)\u0013\u0005\u0006o\u0015\u0001\r\u0001O\u0001\u0011\u0013:3\u0015JT%U\u000b~#\u0016*T#P+R+\u0012aQ\u0001\u0012\u0013:3\u0015JT%U\u000b~#\u0016*T#P+R\u0003\u0013AF'B1~kUiU*B\u000f\u0016{6+\u0013.F?&su,\u0014\"\u0016\u0003=\u0003\"!\u0007)\n\u0005ES\"aA%oi\u00069R*\u0011-`\u001b\u0016\u001b6+Q$F?NK%,R0J\u001d~k%\tI\u0001\u0014[\u0006DX*Z:tC\u001e,7+\u001b>f\u0005f$Xm\u001d\u000b\u0003\u001fVCQa\u000e\u0006A\u0002a\u0002"
)
public final class RpcUtils {
   public static int maxMessageSizeBytes(final SparkConf conf) {
      return RpcUtils$.MODULE$.maxMessageSizeBytes(conf);
   }

   public static RpcTimeout INFINITE_TIMEOUT() {
      return RpcUtils$.MODULE$.INFINITE_TIMEOUT();
   }

   public static RpcTimeout lookupRpcTimeout(final SparkConf conf) {
      return RpcUtils$.MODULE$.lookupRpcTimeout(conf);
   }

   public static RpcTimeout askRpcTimeout(final SparkConf conf) {
      return RpcUtils$.MODULE$.askRpcTimeout(conf);
   }

   public static RpcEndpointRef makeDriverRef(final String name, final SparkConf conf, final RpcEnv rpcEnv) {
      return RpcUtils$.MODULE$.makeDriverRef(name, conf, rpcEnv);
   }
}
