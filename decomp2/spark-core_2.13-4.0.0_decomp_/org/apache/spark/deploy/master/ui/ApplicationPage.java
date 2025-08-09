package org.apache.spark.deploy.master.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.ExecutorState$;
import org.apache.spark.deploy.StandaloneResourceUtils$;
import org.apache.spark.deploy.master.ApplicationInfo;
import org.apache.spark.deploy.master.ExecutorDesc;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.ui.ToolTips$;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.util.Utils$;
import scala.Enumeration;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.xml.Comment;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005Y3Qa\u0002\u0005\u0001\u0011QA\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\u0006A\u0001!\t!\t\u0005\b\u0017\u0001\u0011\r\u0011\"\u0003%\u0011\u0019Y\u0003\u0001)A\u0005K!)A\u0006\u0001C\u0001[!)a\n\u0001C\u0005\u001f\ny\u0011\t\u001d9mS\u000e\fG/[8o!\u0006<WM\u0003\u0002\n\u0015\u0005\u0011Q/\u001b\u0006\u0003\u00171\ta!\\1ti\u0016\u0014(BA\u0007\u000f\u0003\u0019!W\r\u001d7ps*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0005\u0002\u0001+A\u0011a\u0003G\u0007\u0002/)\u0011\u0011BD\u0005\u00033]\u0011\u0011bV3c+&\u0003\u0016mZ3\u0002\rA\f'/\u001a8u\u0007\u0001\u0001\"!\b\u0010\u000e\u0003!I!a\b\u0005\u0003\u00175\u000b7\u000f^3s/\u0016\u0014W+S\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\u001a\u0003CA\u000f\u0001\u0011\u0015Q\"\u00011\u0001\u001d+\u0005)\u0003C\u0001\u0014*\u001b\u00059#B\u0001\u0015\u000f\u0003\r\u0011\boY\u0005\u0003U\u001d\u0012aB\u00159d\u000b:$\u0007o\\5oiJ+g-A\u0004nCN$XM\u001d\u0011\u0002\rI,g\u000eZ3s)\tq#\tE\u00020sqr!\u0001\r\u001c\u000f\u0005E\"T\"\u0001\u001a\u000b\u0005MZ\u0012A\u0002\u001fs_>$h(C\u00016\u0003\u0015\u00198-\u00197b\u0013\t9\u0004(A\u0004qC\u000e\\\u0017mZ3\u000b\u0003UJ!AO\u001e\u0003\u0007M+\u0017O\u0003\u00028qA\u0011Q\bQ\u0007\u0002})\u0011q\bO\u0001\u0004q6d\u0017BA!?\u0005\u0011qu\u000eZ3\t\u000b\r+\u0001\u0019\u0001#\u0002\u000fI,\u0017/^3tiB\u0011Q\tT\u0007\u0002\r*\u0011q\tS\u0001\u0005QR$\bO\u0003\u0002J\u0015\u000691/\u001a:wY\u0016$(\"A&\u0002\u000f)\f7.\u0019:uC&\u0011QJ\u0012\u0002\u0013\u0011R$\boU3sm2,GOU3rk\u0016\u001cH/A\u0006fq\u0016\u001cW\u000f^8s%><HC\u0001\u0018Q\u0011\u0015\tf\u00011\u0001S\u0003!)\u00070Z2vi>\u0014\bCA*U\u001b\u0005Q\u0011BA+\u000b\u00051)\u00050Z2vi>\u0014H)Z:d\u0001"
)
public class ApplicationPage extends WebUIPage {
   private final MasterWebUI parent;
   private final RpcEndpointRef master;

   private RpcEndpointRef master() {
      return this.master;
   }

   public Seq render(final HttpServletRequest request) {
      String appId = request.getParameter("appId");
      DeployMessages.MasterStateResponse state = (DeployMessages.MasterStateResponse)this.master().askSync(DeployMessages.RequestMasterState$.MODULE$, .MODULE$.apply(DeployMessages.MasterStateResponse.class));
      ApplicationInfo app = (ApplicationInfo)scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(state.activeApps()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$render$1(appId, x$1))).getOrElse(() -> (ApplicationInfo)scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(state.completedApps()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$render$3(appId, x$2))).orNull(scala..less.colon.less..MODULE$.refl()));
      if (app == null) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var72 = new UnprefixedAttribute("class", new Text("row"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("No running application with ID "));
         $buf.$amp$plus(appId);
         Elem msg = new Elem((String)null, "div", var72, var10005, false, var10007.seqToNodeSeq($buf));
         return UIUtils$.MODULE$.basicSparkPage(request, () -> msg, "Not Found", UIUtils$.MODULE$.basicSparkPage$default$4());
      } else {
         Seq executorHeaders = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ExecutorID", "Worker", "Cores", "Memory", "Resource Profile Id", "Resources", "State", "Logs"})));
         Seq allExecutors = ((IterableOnceOps)app.executors().values().$plus$plus(app.removedExecutors())).toSet().toSeq();
         Seq executors = (Seq)allExecutors.filter((exec) -> BoxesRunTime.boxToBoolean($anonfun$render$5(exec)));
         Seq removedExecutors = (Seq)allExecutors.diff(executors);
         Seq executorsTable = UIUtils$.MODULE$.listingTable(executorHeaders, (executor) -> this.executorRow(executor), executors, UIUtils$.MODULE$.listingTable$default$4(), UIUtils$.MODULE$.listingTable$default$5(), UIUtils$.MODULE$.listingTable$default$6(), UIUtils$.MODULE$.listingTable$default$7(), UIUtils$.MODULE$.listingTable$default$8(), UIUtils$.MODULE$.listingTable$default$9());
         Seq removedExecutorsTable = UIUtils$.MODULE$.listingTable(executorHeaders, (executor) -> this.executorRow(executor), removedExecutors, UIUtils$.MODULE$.listingTable$default$4(), UIUtils$.MODULE$.listingTable$default$5(), UIUtils$.MODULE$.listingTable$default$6(), UIUtils$.MODULE$.listingTable$default$7(), UIUtils$.MODULE$.listingTable$default$8(), UIUtils$.MODULE$.listingTable$default$9());
         NodeBuffer $buf = new NodeBuffer();
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var73 = new UnprefixedAttribute("class", new Text("row"), $md);
         Elem var10001 = new Elem;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var74 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         Elem var10010 = new Elem;
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var75 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
         Elem var10019 = new Elem;
         TopScope var10024 = scala.xml.TopScope..MODULE$;
         NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         Null var10032 = scala.xml.Null..MODULE$;
         TopScope var10033 = scala.xml.TopScope..MODULE$;
         NodeSeq var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         Null var10041 = scala.xml.Null..MODULE$;
         TopScope var10042 = scala.xml.TopScope..MODULE$;
         NodeSeq var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("ID:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(app.id());
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Name:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(app.desc().name());
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("User:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(app.desc().user());
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Cores:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(app.desc().maxCores().isEmpty() ? scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Unlimited (%s granted)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(app.coresGranted())})) : scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s (%s granted, %s left)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{app.desc().maxCores().get(), BoxesRunTime.boxToInteger(app.coresGranted()), BoxesRunTime.boxToInteger(app.coresLeft())})));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var76 = new UnprefixedAttribute("data-placement", new Text("top"), $md);
         var76 = new UnprefixedAttribute("title", ToolTips$.MODULE$.APPLICATION_EXECUTOR_LIMIT(), var76);
         var76 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var76);
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         Null var10050 = scala.xml.Null..MODULE$;
         TopScope var10051 = scala.xml.TopScope..MODULE$;
         NodeSeq var10053 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Executor Limit: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10050, var10051, false, var10053.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                "));
         $buf.$amp$plus(app.getExecutorLimit() == Integer.MAX_VALUE ? "Unlimited" : BoxesRunTime.boxToInteger(app.getExecutorLimit()));
         $buf.$amp$plus(new Text("\n                ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(app.executors().size()));
         $buf.$amp$plus(new Text(" granted)\n              "));
         $buf.$amp$plus(new Elem((String)null, "span", var76, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Executor Memory - Default Resource Profile:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(Utils$.MODULE$.megabytesToString((long)app.desc().memoryPerExecutorMB()));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Executor Resources - Default Resource Profile:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(StandaloneResourceUtils$.MODULE$.formatResourceRequirements(app.desc().resourceReqsPerExecutor()));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Submit Date:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(UIUtils$.MODULE$.formatDate(app.submitDate()));
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Duration:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(UIUtils$.MODULE$.formatDuration(app.duration()));
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("State:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(app.state());
         $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         Object var10028;
         if (!app.isFinished()) {
            if (app.desc().appUiUrl().isBlank()) {
               var10032 = scala.xml.Null..MODULE$;
               var10033 = scala.xml.TopScope..MODULE$;
               var10035 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               var10041 = scala.xml.Null..MODULE$;
               var10042 = scala.xml.TopScope..MODULE$;
               var10044 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               $buf.$amp$plus(new Text("Application UI:"));
               $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
               $buf.$amp$plus(new Text(" Disabled"));
               var10028 = new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf));
            } else {
               var10032 = scala.xml.Null..MODULE$;
               var10033 = scala.xml.TopScope..MODULE$;
               var10035 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               var10041 = scala.xml.Null..MODULE$;
               var10042 = scala.xml.TopScope..MODULE$;
               var10044 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               $buf.$amp$plus(new Text("\n                      "));
               MetaData $md = scala.xml.Null..MODULE$;
               MetaData var79 = new UnprefixedAttribute("href", UIUtils$.MODULE$.makeHref(this.parent.master().reverseProxy(), app.id(), app.desc().appUiUrl()), $md);
               var10051 = scala.xml.TopScope..MODULE$;
               var10053 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               $buf.$amp$plus(new Text("Application Detail UI"));
               $buf.$amp$plus(new Elem((String)null, "a", var79, var10051, false, var10053.seqToNodeSeq($buf)));
               $buf.$amp$plus(new Text("\n                  "));
               $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
               var10028 = new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf));
            }
         } else if (this.parent.master().historyServerUrl().nonEmpty()) {
            var10032 = scala.xml.Null..MODULE$;
            var10033 = scala.xml.TopScope..MODULE$;
            var10035 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            var10041 = scala.xml.Null..MODULE$;
            var10042 = scala.xml.TopScope..MODULE$;
            var10044 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                    "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var80 = new UnprefixedAttribute("href", this.parent.master().historyServerUrl().get() + "/history/" + app.id(), $md);
            var10051 = scala.xml.TopScope..MODULE$;
            var10053 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                      Application History UI"));
            $buf.$amp$plus(new Elem((String)null, "a", var80, var10051, false, var10053.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
            var10028 = new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf));
         } else {
            var10028 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10028);
         $buf.$amp$plus(new Text("\n          "));
         var10019.<init>((String)null, "ul", var75, var10024, false, var10026.seqToNodeSeq($buf));
         $buf.$amp$plus(var10019);
         $buf.$amp$plus(new Text("\n        "));
         var10010.<init>((String)null, "div", var74, var10015, false, var10017.seqToNodeSeq($buf));
         $buf.$amp$plus(var10010);
         $buf.$amp$plus(new Text("\n      "));
         var10001.<init>((String)null, "div", var73, var10006, false, var10008.seqToNodeSeq($buf));
         $buf.$amp$plus(var10001);
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var81 = new UnprefixedAttribute("class", new Text("row"), $md);
         var10001 = new Elem;
         var10006 = scala.xml.TopScope..MODULE$;
         var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(new Comment(" Executors "));
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var82 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         var10010 = new Elem;
         var10015 = scala.xml.TopScope..MODULE$;
         var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var83 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-executors','aggregated-executors')"), $md);
         var83 = new UnprefixedAttribute("class", new Text("collapse-aggregated-executors collapse-table"), var83);
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         var10032 = scala.xml.Null..MODULE$;
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var85 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var85, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n              "));
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Executor Summary ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(allExecutors.length()));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "h4", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "span", var83, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var86 = new UnprefixedAttribute("class", new Text("aggregated-executors collapsible-table"), $md);
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(executorsTable);
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "div", var86, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         Object var98;
         if (removedExecutors.nonEmpty()) {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var87 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-removedExecutors',\n                  'aggregated-removedExecutors')"), $md);
            var87 = new UnprefixedAttribute("class", new Text("collapse-aggregated-removedExecutors collapse-table"), var87);
            var10024 = scala.xml.TopScope..MODULE$;
            var10026 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            var10032 = scala.xml.Null..MODULE$;
            var10033 = scala.xml.TopScope..MODULE$;
            var10035 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                  "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var89 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
            $buf.$amp$plus(new Elem((String)null, "span", var89, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
            $buf.$amp$plus(new Text("\n                  "));
            var10041 = scala.xml.Null..MODULE$;
            var10042 = scala.xml.TopScope..MODULE$;
            var10044 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Removed Executors ("));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(removedExecutors.length()));
            $buf.$amp$plus(new Text(")"));
            $buf.$amp$plus(new Elem((String)null, "a", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(new Elem((String)null, "h4", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            Elem var97 = new Elem((String)null, "span", var87, var10024, false, var10026.seqToNodeSeq($buf));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var90 = new UnprefixedAttribute("class", new Text("aggregated-removedExecutors collapsible-table"), $md);
            TopScope var10025 = scala.xml.TopScope..MODULE$;
            NodeSeq var10027 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(removedExecutorsTable);
            $buf.$amp$plus(new Text("\n              "));
            var98 = var97.$plus$plus(new Elem((String)null, "div", var90, var10025, false, var10027.seqToNodeSeq($buf)));
         } else {
            var98 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var98);
         $buf.$amp$plus(new Text("\n        "));
         var10010.<init>((String)null, "div", var82, var10015, false, var10017.seqToNodeSeq($buf));
         $buf.$amp$plus(var10010);
         $buf.$amp$plus(new Text("\n      "));
         var10001.<init>((String)null, "div", var81, var10006, false, var10008.seqToNodeSeq($buf));
         $buf.$amp$plus(var10001);
         return UIUtils$.MODULE$.basicSparkPage(request, () -> scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf), "Application: " + app.desc().name(), UIUtils$.MODULE$.basicSparkPage$default$4());
      }
   }

   private Seq executorRow(final ExecutorDesc executor) {
      String workerUrlRef = UIUtils$.MODULE$.makeHref(this.parent.master().reverseProxy(), executor.worker().id(), executor.worker().webUiAddress());
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(executor.id()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var18 = new UnprefixedAttribute("href", workerUrlRef, $md);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(executor.worker().id());
      $buf.$amp$plus(new Elem((String)null, "a", var18, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(executor.cores()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(executor.memory()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(executor.rpId()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(StandaloneResourceUtils$.MODULE$.formatResourcesAddresses(executor.resources()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(executor.state());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var19 = new UnprefixedAttribute("href", workerUrlRef + "/logPage/?appId=" + executor.application().id() + "&executorId=" + executor.id() + "&logType=stdout", $md);
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("stdout"));
      $buf.$amp$plus(new Elem((String)null, "a", var19, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var20 = new UnprefixedAttribute("href", workerUrlRef + "/logPage/?appId=" + executor.application().id() + "&executorId=" + executor.id() + "&logType=stderr", $md);
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("stderr"));
      $buf.$amp$plus(new Elem((String)null, "a", var20, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$1(final String appId$1, final ApplicationInfo x$1) {
      boolean var3;
      label23: {
         String var10000 = x$1.id();
         if (var10000 == null) {
            if (appId$1 == null) {
               break label23;
            }
         } else if (var10000.equals(appId$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$3(final String appId$1, final ApplicationInfo x$2) {
      boolean var3;
      label23: {
         String var10000 = x$2.id();
         if (var10000 == null) {
            if (appId$1 == null) {
               break label23;
            }
         } else if (var10000.equals(appId$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$5(final ExecutorDesc exec) {
      boolean var2;
      if (ExecutorState$.MODULE$.isFinished(exec.state())) {
         label29: {
            Enumeration.Value var10000 = exec.state();
            Enumeration.Value var1 = ExecutorState$.MODULE$.EXITED();
            if (var10000 == null) {
               if (var1 == null) {
                  break label29;
               }
            } else if (var10000.equals(var1)) {
               break label29;
            }

            var2 = false;
            return var2;
         }
      }

      var2 = true;
      return var2;
   }

   public ApplicationPage(final MasterWebUI parent) {
      super("app");
      this.parent = parent;
      this.master = parent.masterEndpointRef();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
