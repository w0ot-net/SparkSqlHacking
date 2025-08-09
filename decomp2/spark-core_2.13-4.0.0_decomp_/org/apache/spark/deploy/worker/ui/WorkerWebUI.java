package org.apache.spark.deploy.worker.ui;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SSLOptions;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.worker.Worker;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.ui.JettyUtils$;
import org.apache.spark.ui.WebUI;
import org.apache.spark.ui.WebUI$;
import org.apache.spark.util.RpcUtils$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194Qa\u0004\t\u0001%qA\u0001b\u0005\u0001\u0003\u0006\u0004%\t!\u000b\u0005\t]\u0001\u0011\t\u0011)A\u0005U!Aq\u0006\u0001BC\u0002\u0013\u0005\u0001\u0007\u0003\u0005:\u0001\t\u0005\t\u0015!\u00032\u0011!Q\u0004A!A!\u0002\u0013Y\u0004\"B!\u0001\t\u0003\u0011\u0005\u0002\u0003%\u0001\u0005\u0004%\t\u0001E%\t\rA\u0003\u0001\u0015!\u0003K\u0011\u0015\t\u0006\u0001\"\u0001S\u000f\u00191\u0006\u0003#\u0001\u0013/\u001a1q\u0002\u0005E\u0001%aCQ!Q\u0006\u0005\u0002qCq!X\u0006C\u0002\u0013\u0005a\f\u0003\u0004f\u0017\u0001\u0006Ia\u0018\u0002\f/>\u00148.\u001a:XK\n,\u0016J\u0003\u0002\u0012%\u0005\u0011Q/\u001b\u0006\u0003'Q\taa^8sW\u0016\u0014(BA\u000b\u0017\u0003\u0019!W\r\u001d7ps*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xmE\u0002\u0001;\t\u0002\"A\b\u0011\u000e\u0003}Q!!\u0005\f\n\u0005\u0005z\"!B,fEVK\u0005CA\u0012'\u001b\u0005!#BA\u0013\u0017\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0014%\u0005\u001daunZ4j]\u001e\u001c\u0001!F\u0001+!\tYC&D\u0001\u0013\u0013\ti#C\u0001\u0004X_J\\WM]\u0001\bo>\u00148.\u001a:!\u0003\u001d9xN]6ESJ,\u0012!\r\t\u0003e]j\u0011a\r\u0006\u0003iU\n!![8\u000b\u0003Y\nAA[1wC&\u0011\u0001h\r\u0002\u0005\r&dW-\u0001\u0005x_J\\G)\u001b:!\u00035\u0011X-];fgR,G\rU8siB\u0011AhP\u0007\u0002{)\ta(A\u0003tG\u0006d\u0017-\u0003\u0002A{\t\u0019\u0011J\u001c;\u0002\rqJg.\u001b;?)\u0011\u0019UIR$\u0011\u0005\u0011\u0003Q\"\u0001\t\t\u000bM1\u0001\u0019\u0001\u0016\t\u000b=2\u0001\u0019A\u0019\t\u000bi2\u0001\u0019A\u001e\u0002\u000fQLW.Z8viV\t!\n\u0005\u0002L\u001d6\tAJ\u0003\u0002N-\u0005\u0019!\u000f]2\n\u0005=c%A\u0003*qGRKW.Z8vi\u0006AA/[7f_V$\b%\u0001\u0006j]&$\u0018.\u00197ju\u0016$\u0012a\u0015\t\u0003yQK!!V\u001f\u0003\tUs\u0017\u000e^\u0001\f/>\u00148.\u001a:XK\n,\u0016\n\u0005\u0002E\u0017M\u00111\"\u0017\t\u0003yiK!aW\u001f\u0003\r\u0005s\u0017PU3g)\u00059\u0016\u0001F*U\u0003RK5i\u0018*F'>+&kQ#`\u0005\u0006\u001bV)F\u0001`!\t\u00017-D\u0001b\u0015\t\u0011W'\u0001\u0003mC:<\u0017B\u00013b\u0005\u0019\u0019FO]5oO\u0006)2\u000bV!U\u0013\u000e{&+R*P+J\u001bUi\u0018\"B'\u0016\u0003\u0003"
)
public class WorkerWebUI extends WebUI {
   private final Worker worker;
   private final File workDir;
   private final RpcTimeout timeout;

   public static String STATIC_RESOURCE_BASE() {
      return WorkerWebUI$.MODULE$.STATIC_RESOURCE_BASE();
   }

   public Worker worker() {
      return this.worker;
   }

   public File workDir() {
      return this.workDir;
   }

   public RpcTimeout timeout() {
      return this.timeout;
   }

   public void initialize() {
      LogPage logPage = new LogPage(this);
      this.attachPage(logPage);
      this.attachPage(new WorkerPage(this));
      this.addStaticHandler(WorkerWebUI$.MODULE$.STATIC_RESOURCE_BASE(), this.addStaticHandler$default$2());
      this.attachHandler(JettyUtils$.MODULE$.createServletHandler("/log", JettyUtils$.MODULE$.textResponderToServlet((request) -> logPage.renderLog(request)), this.worker().conf(), JettyUtils$.MODULE$.createServletHandler$default$4()));
   }

   public WorkerWebUI(final Worker worker, final File workDir, final int requestedPort) {
      this.worker = worker;
      this.workDir = workDir;
      SecurityManager x$1 = worker.securityMgr();
      SSLOptions x$2 = worker.securityMgr().getSSLOptions("standalone");
      SparkConf x$4 = worker.conf();
      String x$5 = "WorkerUI";
      String x$6 = WebUI$.MODULE$.$lessinit$greater$default$5();
      int x$7 = WebUI$.MODULE$.$lessinit$greater$default$7();
      super(x$1, x$2, requestedPort, x$4, x$6, "WorkerUI", x$7);
      this.timeout = RpcUtils$.MODULE$.askRpcTimeout(worker.conf());
      this.initialize();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
