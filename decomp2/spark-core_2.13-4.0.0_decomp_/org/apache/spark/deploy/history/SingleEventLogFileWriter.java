package org.apache.spark.deploy.history;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea\u0001B\n\u0015\u0001}A\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\te\u0001\u0011\t\u0011)A\u0005g!Aq\u0007\u0001B\u0001B\u0003%\u0001\b\u0003\u0005A\u0001\t\u0005\t\u0015!\u0003B\u0011!)\u0005A!A!\u0002\u00131\u0005\"\u0002(\u0001\t\u0003y\u0005b\u0002,\u0001\u0005\u0004%\te\u0016\u0005\u00071\u0002\u0001\u000b\u0011B\u0013\t\u000be\u0003A\u0011\u0003.\t\u000b\u0001\u0004A\u0011I1\t\u000b\u0015\u0004A\u0011\t4\t\u000f9\u0004\u0011\u0013!C\u0001_\")!\u0010\u0001C!C\u001e)1\u0010\u0006E\u0001y\u001a)1\u0003\u0006E\u0001{\"1aj\u0004C\u0001\u0003\u0007Aq!!\u0002\u0010\t\u0003\t9\u0001C\u0005\u0002\u0014=\t\n\u0011\"\u0001\u0002\u0016\tA2+\u001b8hY\u0016,e/\u001a8u\u0019><g)\u001b7f/JLG/\u001a:\u000b\u0005U1\u0012a\u00025jgR|'/\u001f\u0006\u0003/a\ta\u0001Z3qY>L(BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\t\t\u0003C\tj\u0011\u0001F\u0005\u0003GQ\u0011!#\u0012<f]Rdun\u001a$jY\u0016<&/\u001b;fe\u0006)\u0011\r\u001d9JIB\u0011ae\f\b\u0003O5\u0002\"\u0001K\u0016\u000e\u0003%R!A\u000b\u0010\u0002\rq\u0012xn\u001c;?\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001'\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u00059Z\u0013\u0001D1qa\u0006#H/Z7qi&#\u0007c\u0001\u001b6K5\t1&\u0003\u00027W\t1q\n\u001d;j_:\f!\u0002\\8h\u0005\u0006\u001cX\rR5s!\tId(D\u0001;\u0015\tYD(A\u0002oKRT\u0011!P\u0001\u0005U\u00064\u0018-\u0003\u0002@u\t\u0019QKU%\u0002\u0013M\u0004\u0018M]6D_:4\u0007C\u0001\"D\u001b\u0005A\u0012B\u0001#\u0019\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0006iC\u0012|w\u000e]\"p]\u001a\u0004\"a\u0012'\u000e\u0003!S!!\u0013&\u0002\t\r|gN\u001a\u0006\u0003\u0017j\ta\u0001[1e_>\u0004\u0018BA'I\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u00061A(\u001b8jiz\"b\u0001U)S'R+\u0006CA\u0011\u0001\u0011\u0015!c\u00011\u0001&\u0011\u0015\u0011d\u00011\u00014\u0011\u00159d\u00011\u00019\u0011\u0015\u0001e\u00011\u0001B\u0011\u0015)e\u00011\u0001G\u0003\u001dawn\u001a)bi\",\u0012!J\u0001\tY><\u0007+\u0019;iA\u0005q\u0011N\u001c)s_\u001e\u0014Xm]:QCRDW#A.\u0011\u0005q{V\"A/\u000b\u0005yc\u0014\u0001\u00027b]\u001eL!\u0001M/\u0002\u000bM$\u0018M\u001d;\u0015\u0003\t\u0004\"\u0001N2\n\u0005\u0011\\#\u0001B+oSR\f!b\u001e:ji\u0016,e/\u001a8u)\r\u0011w-\u001b\u0005\u0006Q.\u0001\r!J\u0001\nKZ,g\u000e\u001e&t_:DqA[\u0006\u0011\u0002\u0003\u00071.A\u0006gYV\u001c\b\u000eT8hO\u0016\u0014\bC\u0001\u001bm\u0013\ti7FA\u0004C_>dW-\u00198\u0002)]\u0014\u0018\u000e^3Fm\u0016tG\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0005\u0001(FA6rW\u0005\u0011\bCA:y\u001b\u0005!(BA;w\u0003%)hn\u00195fG.,GM\u0003\u0002xW\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005e$(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006!1\u000f^8q\u0003a\u0019\u0016N\\4mK\u00163XM\u001c;M_\u001e4\u0015\u000e\\3Xe&$XM\u001d\t\u0003C=\u0019\"a\u0004@\u0011\u0005Qz\u0018bAA\u0001W\t1\u0011I\\=SK\u001a$\u0012\u0001`\u0001\u000bO\u0016$Hj\\4QCRDG#C\u0013\u0002\n\u0005-\u0011QBA\b\u0011\u00159\u0014\u00031\u00019\u0011\u0015!\u0013\u00031\u0001&\u0011\u0015\u0011\u0014\u00031\u00014\u0011!\t\t\"\u0005I\u0001\u0002\u0004\u0019\u0014\u0001F2p[B\u0014Xm]:j_:\u001cu\u000eZ3d\u001d\u0006lW-\u0001\u000bhKRdun\u001a)bi\"$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003/Q#aM9"
)
public class SingleEventLogFileWriter extends EventLogFileWriter {
   private final String logPath;

   public static Option getLogPath$default$4() {
      return SingleEventLogFileWriter$.MODULE$.getLogPath$default$4();
   }

   public static String getLogPath(final URI logBaseDir, final String appId, final Option appAttemptId, final Option compressionCodecName) {
      return SingleEventLogFileWriter$.MODULE$.getLogPath(logBaseDir, appId, appAttemptId, compressionCodecName);
   }

   public String logPath() {
      return this.logPath;
   }

   public String inProgressPath() {
      String var10000 = this.logPath();
      return var10000 + EventLogFileWriter$.MODULE$.IN_PROGRESS();
   }

   public void start() {
      this.requireLogBaseDirAsDirectory();
      this.initLogFile(new Path(this.inProgressPath()), (os) -> new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8)));
   }

   public void writeEvent(final String eventJson, final boolean flushLogger) {
      this.writeLine(eventJson, flushLogger);
   }

   public boolean writeEvent$default$2() {
      return false;
   }

   public void stop() {
      this.closeWriter();
      this.renameFile(new Path(this.inProgressPath()), new Path(this.logPath()), this.shouldOverwrite());
   }

   public SingleEventLogFileWriter(final String appId, final Option appAttemptId, final URI logBaseDir, final SparkConf sparkConf, final Configuration hadoopConf) {
      super(appId, appAttemptId, logBaseDir, sparkConf, hadoopConf);
      this.logPath = SingleEventLogFileWriter$.MODULE$.getLogPath(logBaseDir, appId, appAttemptId, this.compressionCodecName());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
