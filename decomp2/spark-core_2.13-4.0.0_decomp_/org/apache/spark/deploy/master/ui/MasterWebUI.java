package org.apache.spark.deploy.master.ui;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import org.apache.spark.SSLOptions;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.Utils$;
import org.apache.spark.deploy.master.ApplicationInfo;
import org.apache.spark.deploy.master.Master;
import org.apache.spark.deploy.master.WorkerInfo;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.ui.JettyUtils$;
import org.apache.spark.ui.WebUI;
import org.apache.spark.ui.WebUI$;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.Function1;
import scala.Option;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb!B\f\u0019\u0001i!\u0003\u0002C\u000e\u0001\u0005\u000b\u0007I\u0011A\u0019\t\u0011Y\u0002!\u0011!Q\u0001\nIB\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\u0006}\u0001!\ta\u0010\u0005\b\t\u0002\u0011\r\u0011\"\u0001F\u0011\u0019a\u0005\u0001)A\u0005\r\"9Q\n\u0001b\u0001\n\u0003q\u0005B\u0002*\u0001A\u0003%q\nC\u0004T\u0001\t\u0007I\u0011\u0001(\t\rQ\u0003\u0001\u0015!\u0003P\u0011\u001d)\u0006A1A\u0005\u0002YCaA\u0019\u0001!\u0002\u00139\u0006\"B2\u0001\t\u0003!\u0007\"\u00025\u0001\t\u0003!\u0007\"B5\u0001\t\u0003Q\u0007\"\u00029\u0001\t\u0013\t\b\"\u0002?\u0001\t\u0013ix\u0001CA\u000b1!\u0005!$a\u0006\u0007\u000f]A\u0002\u0012\u0001\u000e\u0002\u001a!1ah\u0005C\u0001\u0003CA\u0011\"a\t\u0014\u0005\u0004%I!!\n\t\u0011\u0005E2\u0003)A\u0005\u0003O\u00111\"T1ti\u0016\u0014x+\u001a2V\u0013*\u0011\u0011DG\u0001\u0003k&T!a\u0007\u000f\u0002\r5\f7\u000f^3s\u0015\tib$\u0001\u0004eKBdw.\u001f\u0006\u0003?\u0001\nQa\u001d9be.T!!\t\u0012\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0013aA8sON\u0019\u0001!\n\u0016\u0011\u0005\u0019BS\"A\u0014\u000b\u0005eq\u0012BA\u0015(\u0005\u00159VMY+J!\tYc&D\u0001-\u0015\tic$\u0001\u0005j]R,'O\\1m\u0013\tyCFA\u0004M_\u001e<\u0017N\\4\u0004\u0001U\t!\u0007\u0005\u00024i5\t!$\u0003\u000265\t1Q*Y:uKJ\fq!\\1ti\u0016\u0014\b%A\u0007sKF,Xm\u001d;fIB{'\u000f\u001e\t\u0003sqj\u0011A\u000f\u0006\u0002w\u0005)1oY1mC&\u0011QH\u000f\u0002\u0004\u0013:$\u0018A\u0002\u001fj]&$h\bF\u0002A\u0005\u000e\u0003\"!\u0011\u0001\u000e\u0003aAQa\u0007\u0003A\u0002IBQa\u000e\u0003A\u0002a\n\u0011#\\1ti\u0016\u0014XI\u001c3q_&tGOU3g+\u00051\u0005CA$K\u001b\u0005A%BA%\u001f\u0003\r\u0011\boY\u0005\u0003\u0017\"\u0013aB\u00159d\u000b:$\u0007o\\5oiJ+g-\u0001\nnCN$XM]#oIB|\u0017N\u001c;SK\u001a\u0004\u0013aC6jY2,e.\u00192mK\u0012,\u0012a\u0014\t\u0003sAK!!\u0015\u001e\u0003\u000f\t{w\u000e\\3b]\u0006a1.\u001b7m\u000b:\f'\r\\3eA\u0005\u0019B-Z2p[6L7o]5p]\u0016s\u0017M\u00197fI\u0006!B-Z2p[6L7o]5p]\u0016s\u0017M\u00197fI\u0002\nQ\u0003Z3d_6l\u0017n]:j_:\fE\u000e\\8x\u001b>$W-F\u0001X!\tAvL\u0004\u0002Z;B\u0011!LO\u0007\u00027*\u0011A\fM\u0001\u0007yI|w\u000e\u001e \n\u0005yS\u0014A\u0002)sK\u0012,g-\u0003\u0002aC\n11\u000b\u001e:j]\u001eT!A\u0018\u001e\u0002-\u0011,7m\\7nSN\u001c\u0018n\u001c8BY2|w/T8eK\u0002\n!\"\u001b8ji&\fG.\u001b>f)\u0005)\u0007CA\u001dg\u0013\t9'H\u0001\u0003V]&$\u0018\u0001C1eIB\u0013x\u000e_=\u0002\u001b%$Gk\\+j\u0003\u0012$'/Z:t)\tYg\u000eE\u0002:Y^K!!\u001c\u001e\u0003\r=\u0003H/[8o\u0011\u0015yw\u00021\u0001X\u0003\tIG-A\u0004jg2{7-\u00197\u0015\u0005=\u0013\b\"B:\u0011\u0001\u0004!\u0018aB1eIJ,7o\u001d\t\u0003kjl\u0011A\u001e\u0006\u0003ob\f1A\\3u\u0015\u0005I\u0018\u0001\u00026bm\u0006L!a\u001f<\u0003\u0017%sW\r^!eIJ,7o]\u0001 SN$UmY8n[&\u001c8/[8oS:<'+Z9vKN$\u0018\t\u001c7po\u0016$GCA(\u007f\u0011\u0019y\u0018\u00031\u0001\u0002\u0002\u0005\u0019!/Z9\u0011\t\u0005\r\u0011\u0011C\u0007\u0003\u0003\u000bQA!a\u0002\u0002\n\u0005!\u0001\u000e\u001e;q\u0015\u0011\tY!!\u0004\u0002\u000fM,'O\u001e7fi*\u0011\u0011qB\u0001\bU\u0006\\\u0017M\u001d;b\u0013\u0011\t\u0019\"!\u0002\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f^\u0001\f\u001b\u0006\u001cH/\u001a:XK\n,\u0016\n\u0005\u0002B'M\u00191#a\u0007\u0011\u0007e\ni\"C\u0002\u0002 i\u0012a!\u00118z%\u00164GCAA\f\u0003M\u0019F+\u0011+J\u0007~\u0013ViU(V%\u000e+u\fR%S+\t\t9\u0003\u0005\u0003\u0002*\u0005=RBAA\u0016\u0015\r\ti\u0003_\u0001\u0005Y\u0006tw-C\u0002a\u0003W\tAc\u0015+B)&\u001buLU#T\u001fV\u00136)R0E\u0013J\u0003\u0003"
)
public class MasterWebUI extends WebUI {
   private final Master master;
   private final RpcEndpointRef masterEndpointRef;
   private final boolean killEnabled;
   private final boolean decommissionEnabled;
   private final String decommissionAllowMode;

   public Master master() {
      return this.master;
   }

   public RpcEndpointRef masterEndpointRef() {
      return this.masterEndpointRef;
   }

   public boolean killEnabled() {
      return this.killEnabled;
   }

   public boolean decommissionEnabled() {
      return this.decommissionEnabled;
   }

   public String decommissionAllowMode() {
      return this.decommissionAllowMode;
   }

   public void initialize() {
      MasterPage masterPage = new MasterPage(this);
      this.attachPage(new ApplicationPage(this));
      this.attachPage(new LogPage(this));
      EnvironmentPage envPage = new EnvironmentPage(this, this.master().conf());
      this.attachPage(envPage);
      this.attachHandler(JettyUtils$.MODULE$.createServletHandler("/environment", JettyUtils$.MODULE$.htmlResponderToServlet((request) -> envPage.render(request)), this.master().conf(), JettyUtils$.MODULE$.createServletHandler$default$4()));
      this.attachPage(masterPage);
      this.addStaticHandler(MasterWebUI$.MODULE$.org$apache$spark$deploy$master$ui$MasterWebUI$$STATIC_RESOURCE_DIR(), this.addStaticHandler$default$2());
      Utils$.MODULE$.addRenderLogHandler(this, this.master().conf());
      if (this.killEnabled()) {
         String x$1 = "/app/kill";
         String x$2 = "/";
         Function1 x$3 = (request) -> {
            $anonfun$initialize$2(masterPage, request);
            return BoxedUnit.UNIT;
         };
         Set x$4 = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"POST"})));
         String x$5 = JettyUtils$.MODULE$.createRedirectHandler$default$4();
         this.attachHandler(JettyUtils$.MODULE$.createRedirectHandler("/app/kill", "/", x$3, x$5, x$4));
         String x$6 = "/driver/kill";
         String x$7 = "/";
         Function1 x$8 = (request) -> {
            $anonfun$initialize$3(masterPage, request);
            return BoxedUnit.UNIT;
         };
         Set x$9 = (Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"POST"})));
         String x$10 = JettyUtils$.MODULE$.createRedirectHandler$default$4();
         this.attachHandler(JettyUtils$.MODULE$.createRedirectHandler("/driver/kill", "/", x$8, x$10, x$9));
      }

      if (this.decommissionEnabled()) {
         this.attachHandler(JettyUtils$.MODULE$.createServletHandler("/workers/kill", new HttpServlet() {
            // $FF: synthetic field
            private final MasterWebUI $outer;

            public void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
               Seq hostnames = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.Option..MODULE$.apply(req.getParameterValues("host")).getOrElse(() -> (String[])scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(String.class)))).toImmutableArraySeq();
               if (!this.$outer.org$apache$spark$deploy$master$ui$MasterWebUI$$isDecommissioningRequestAllowed(req)) {
                  resp.sendError(405);
               } else {
                  Integer removedWorkers = (Integer)this.$outer.masterEndpointRef().askSync(new DeployMessages.DecommissionWorkersOnHosts(hostnames), scala.reflect.ClassTag..MODULE$.apply(Integer.class));
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Decommissioning of hosts ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOSTS..MODULE$, hostnames)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" decommissioned ", " workers"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_REMOVED_WORKERS..MODULE$, removedWorkers)}))))));
                  if (.MODULE$.Integer2int(removedWorkers) > 0) {
                     resp.setStatus(200);
                  } else if (BoxesRunTime.equalsNumObject(removedWorkers, BoxesRunTime.boxToInteger(0))) {
                     resp.sendError(404);
                  } else {
                     resp.setStatus(500);
                  }
               }
            }

            public {
               if (MasterWebUI.this == null) {
                  throw null;
               } else {
                  this.$outer = MasterWebUI.this;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }, ""));
      }
   }

   public void addProxy() {
      ServletContextHandler handler = JettyUtils$.MODULE$.createProxyHandler((id) -> this.idToUiAddress(id));
      this.attachHandler(handler);
   }

   public Option idToUiAddress(final String id) {
      DeployMessages.MasterStateResponse state = (DeployMessages.MasterStateResponse)this.masterEndpointRef().askSync(DeployMessages.RequestMasterState$.MODULE$, scala.reflect.ClassTag..MODULE$.apply(DeployMessages.MasterStateResponse.class));
      Option maybeWorkerUiAddress = scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps(state.workers()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$idToUiAddress$1(id, x$1))).map((x$2) -> x$2.webUiAddress());
      Option maybeAppUiAddress = scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps(state.activeApps()), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$idToUiAddress$3(id, x$3))).map((x$4) -> x$4.desc().appUiUrl());
      return maybeWorkerUiAddress.orElse(() -> maybeAppUiAddress);
   }

   private boolean isLocal(final InetAddress address) {
      if (!address.isAnyLocalAddress() && !address.isLoopbackAddress()) {
         boolean var10000;
         try {
            var10000 = NetworkInterface.getByInetAddress(address) != null;
         } catch (SocketException var2) {
            var10000 = false;
         }

         return var10000;
      } else {
         return true;
      }
   }

   public boolean org$apache$spark$deploy$master$ui$MasterWebUI$$isDecommissioningRequestAllowed(final HttpServletRequest req) {
      String var3 = this.decommissionAllowMode();
      switch (var3 == null ? 0 : var3.hashCode()) {
         case 62368553:
            if ("ALLOW".equals(var3)) {
               return true;
            }
            break;
         case 72607563:
            if ("LOCAL".equals(var3)) {
               return this.isLocal(InetAddress.getByName(req.getRemoteAddr()));
            }
      }

      return false;
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$2(final MasterPage masterPage$1, final HttpServletRequest request) {
      masterPage$1.handleAppKillRequest(request);
   }

   // $FF: synthetic method
   public static final void $anonfun$initialize$3(final MasterPage masterPage$1, final HttpServletRequest request) {
      masterPage$1.handleDriverKillRequest(request);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$idToUiAddress$1(final String id$1, final WorkerInfo x$1) {
      boolean var3;
      label23: {
         String var10000 = x$1.id();
         if (var10000 == null) {
            if (id$1 == null) {
               break label23;
            }
         } else if (var10000.equals(id$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$idToUiAddress$3(final String id$1, final ApplicationInfo x$3) {
      boolean var3;
      label23: {
         String var10000 = x$3.id();
         if (var10000 == null) {
            if (id$1 == null) {
               break label23;
            }
         } else if (var10000.equals(id$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public MasterWebUI(final Master master, final int requestedPort) {
      this.master = master;
      SecurityManager x$1 = master.securityMgr();
      SSLOptions x$2 = master.securityMgr().getSSLOptions("standalone");
      SparkConf x$4 = master.conf();
      String x$5 = "MasterUI";
      String x$6 = WebUI$.MODULE$.$lessinit$greater$default$5();
      int x$7 = WebUI$.MODULE$.$lessinit$greater$default$7();
      super(x$1, x$2, requestedPort, x$4, x$6, "MasterUI", x$7);
      this.masterEndpointRef = master.self();
      this.killEnabled = BoxesRunTime.unboxToBoolean(master.conf().get(UI$.MODULE$.UI_KILL_ENABLED()));
      this.decommissionEnabled = BoxesRunTime.unboxToBoolean(master.conf().get(package$.MODULE$.DECOMMISSION_ENABLED()));
      this.decommissionAllowMode = (String)master.conf().get(UI$.MODULE$.MASTER_UI_DECOMMISSION_ALLOW_MODE());
      this.initialize();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
