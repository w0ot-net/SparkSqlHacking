package org.apache.spark.executor;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkEnv;
import org.apache.spark.deploy.SparkHadoopUtil.;
import org.apache.spark.deploy.yarn.Client$;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.util.YarnContainerInfoHelper$;
import scala.Option;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055b!B\n\u0015\u0001Ya\u0002\"C\u0014\u0001\u0005\u0003\u0005\u000b\u0011B\u00150\u0011!\u0001\u0004A!A!\u0002\u0013\t\u0004\u0002\u0003 \u0001\u0005\u0003\u0005\u000b\u0011B\u0019\t\u0011}\u0002!\u0011!Q\u0001\nEB\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006I!\r\u0005\t\u0003\u0002\u0011\t\u0011)A\u0005\u0005\"Aa\t\u0001B\u0001B\u0003%q\t\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003M\u0011!y\u0005A!A!\u0002\u0013\u0001\u0006\"\u0002,\u0001\t\u00039\u0006\u0002\u00032\u0001\u0011\u000b\u0007I\u0011B2\t\u000b1\u0004A\u0011I7\t\r}\u0004A\u0011IA\u0001\u0011\u001d\tI\u0001\u0001C!\u0003\u00039\u0001\"a\u0003\u0015\u0011\u00031\u0012Q\u0002\u0004\b'QA\tAFA\b\u0011\u00191\u0006\u0003\"\u0001\u0002\u0018!9\u0011\u0011\u0004\t\u0005\u0002\u0005m!\u0001I-be:\u001cu.\u0019:tK\u001e\u0013\u0018-\u001b8fI\u0016CXmY;u_J\u0014\u0015mY6f]\u0012T!!\u0006\f\u0002\u0011\u0015DXmY;u_JT!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'oZ\n\u0004\u0001u\t\u0003C\u0001\u0010 \u001b\u0005!\u0012B\u0001\u0011\u0015\u0005q\u0019u.\u0019:tK\u001e\u0013\u0018-\u001b8fI\u0016CXmY;u_J\u0014\u0015mY6f]\u0012\u0004\"AI\u0013\u000e\u0003\rR!\u0001\n\f\u0002\u0011%tG/\u001a:oC2L!AJ\u0012\u0003\u000f1{wmZ5oO\u00061!\u000f]2F]Z\u001c\u0001\u0001\u0005\u0002+[5\t1F\u0003\u0002--\u0005\u0019!\u000f]2\n\u00059Z#A\u0002*qG\u0016sg/\u0003\u0002(?\u0005IAM]5wKJ,&\u000f\u001c\t\u0003emr!aM\u001d\u0011\u0005Q:T\"A\u001b\u000b\u0005YB\u0013A\u0002\u001fs_>$hHC\u00019\u0003\u0015\u00198-\u00197b\u0013\tQt'\u0001\u0004Qe\u0016$WMZ\u0005\u0003yu\u0012aa\u0015;sS:<'B\u0001\u001e8\u0003))\u00070Z2vi>\u0014\u0018\nZ\u0001\fE&tG-\u00113ee\u0016\u001c8/\u0001\u0005i_N$h.Y7f\u0003\u0015\u0019wN]3t!\t\u0019E)D\u00018\u0013\t)uGA\u0002J]R\f1!\u001a8w!\tA\u0015*D\u0001\u0017\u0013\tQeC\u0001\u0005Ta\u0006\u00148.\u00128w\u00035\u0011Xm]8ve\u000e,7OR5mKB\u00191)T\u0019\n\u00059;$AB(qi&|g.A\bsKN|WO]2f!J|g-\u001b7f!\t\tF+D\u0001S\u0015\t\u0019f#\u0001\u0005sKN|WO]2f\u0013\t)&KA\bSKN|WO]2f!J|g-\u001b7f\u0003\u0019a\u0014N\\5u}QQ\u0001,\u0017.\\9vsv\fY1\u0011\u0005y\u0001\u0001\"B\u0014\u000b\u0001\u0004I\u0003\"\u0002\u0019\u000b\u0001\u0004\t\u0004\"\u0002 \u000b\u0001\u0004\t\u0004\"B \u000b\u0001\u0004\t\u0004\"\u0002!\u000b\u0001\u0004\t\u0004\"B!\u000b\u0001\u0004\u0011\u0005\"\u0002$\u000b\u0001\u00049\u0005\"B&\u000b\u0001\u0004a\u0005\"B(\u000b\u0001\u0004\u0001\u0016a\u00055bI>|\u0007oQ8oM&<WO]1uS>tW#\u00013\u0011\u0005\u0015TW\"\u00014\u000b\u0005\u001dD\u0017\u0001B2p]\u001aT!!\u001b\r\u0002\r!\fGm\\8q\u0013\tYgMA\u0007D_:4\u0017nZ;sCRLwN\\\u0001\u0011O\u0016$Xk]3s\u00072\f7o\u001d)bi\",\u0012A\u001c\t\u0004_R<hB\u00019s\u001d\t!\u0014/C\u00019\u0013\t\u0019x'A\u0004qC\u000e\\\u0017mZ3\n\u0005U4(aA*fc*\u00111o\u000e\t\u0003qvl\u0011!\u001f\u0006\u0003un\f1A\\3u\u0015\u0005a\u0018\u0001\u00026bm\u0006L!A`=\u0003\u0007U\u0013F*\u0001\bfqR\u0014\u0018m\u0019;M_\u001e,&\u000f\\:\u0016\u0005\u0005\r\u0001#\u0002\u001a\u0002\u0006E\n\u0014bAA\u0004{\t\u0019Q*\u00199\u0002#\u0015DHO]1di\u0006#HO]5ckR,7/\u0001\u0011ZCJt7i\\1sg\u0016<%/Y5oK\u0012,\u00050Z2vi>\u0014()Y2lK:$\u0007C\u0001\u0010\u0011'\u0011\u0001\u0012\u0011C\u0011\u0011\u0007\r\u000b\u0019\"C\u0002\u0002\u0016]\u0012a!\u00118z%\u00164GCAA\u0007\u0003\u0011i\u0017-\u001b8\u0015\t\u0005u\u00111\u0005\t\u0004\u0007\u0006}\u0011bAA\u0011o\t!QK\\5u\u0011\u001d\t)C\u0005a\u0001\u0003O\tA!\u0019:hgB!1)!\u000b2\u0013\r\tYc\u000e\u0002\u0006\u0003J\u0014\u0018-\u001f"
)
public class YarnCoarseGrainedExecutorBackend extends CoarseGrainedExecutorBackend {
   private Configuration hadoopConfiguration;
   private final SparkEnv env;
   private volatile boolean bitmap$0;

   public static void main(final String[] args) {
      YarnCoarseGrainedExecutorBackend$.MODULE$.main(args);
   }

   private Configuration hadoopConfiguration$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.hadoopConfiguration = .MODULE$.get().newConfiguration(this.env.conf());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.hadoopConfiguration;
   }

   private Configuration hadoopConfiguration() {
      return !this.bitmap$0 ? this.hadoopConfiguration$lzycompute() : this.hadoopConfiguration;
   }

   public Seq getUserClassPath() {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(Client$.MODULE$.getUserClasspathUrls(this.env.conf(), true)).toImmutableArraySeq();
   }

   public Map extractLogUrls() {
      return (Map)YarnContainerInfoHelper$.MODULE$.getLogUrls(this.hadoopConfiguration(), scala.None..MODULE$).getOrElse(() -> (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$));
   }

   public Map extractAttributes() {
      return (Map)YarnContainerInfoHelper$.MODULE$.getAttributes(this.hadoopConfiguration(), scala.None..MODULE$).getOrElse(() -> (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$));
   }

   public YarnCoarseGrainedExecutorBackend(final RpcEnv rpcEnv, final String driverUrl, final String executorId, final String bindAddress, final String hostname, final int cores, final SparkEnv env, final Option resourcesFile, final ResourceProfile resourceProfile) {
      super(rpcEnv, driverUrl, executorId, bindAddress, hostname, cores, env, resourcesFile, resourceProfile);
      this.env = env;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
