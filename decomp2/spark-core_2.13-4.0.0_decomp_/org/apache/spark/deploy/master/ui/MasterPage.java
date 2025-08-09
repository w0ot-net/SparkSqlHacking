package org.apache.spark.deploy.master.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.deploy.DeployMessages;
import org.apache.spark.deploy.JsonProtocol$;
import org.apache.spark.deploy.StandaloneResourceUtils$;
import org.apache.spark.deploy.master.ApplicationInfo;
import org.apache.spark.deploy.master.ApplicationState$;
import org.apache.spark.deploy.master.DriverInfo;
import org.apache.spark.deploy.master.DriverState$;
import org.apache.spark.deploy.master.WorkerInfo;
import org.apache.spark.deploy.master.WorkerState$;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.util.Utils$;
import org.json4s.JValue;
import scala.Enumeration;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}e!\u0002\f\u0018\u0001]\u0019\u0003\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011B\u0016\t\u000b=\u0002A\u0011\u0001\u0019\t\u000fi\u0001!\u0019!C\u0005g!1!\b\u0001Q\u0001\nQBqa\u000f\u0001C\u0002\u0013%A\b\u0003\u0004O\u0001\u0001\u0006I!\u0010\u0005\b\u001f\u0002\u0011\r\u0011\"\u0003Q\u0011\u0019I\u0006\u0001)A\u0005#\")!\f\u0001C\u00017\")1\u000e\u0001C!Y\"1q\u0010\u0001C\u0001\u0003\u0003Aq!a\u0003\u0001\t\u0003\ti\u0001C\u0004\u0002\u0012\u0001!I!a\u0005\t\u000f\u0005\u0005\u0002\u0001\"\u0003\u0002$!9\u0011\u0011\u0007\u0001\u0005\n\u0005M\u0002bBA \u0001\u0011\u0005\u0011\u0011\t\u0005\b\u0003G\u0002A\u0011BA3\u0011\u001d\t\u0019\b\u0001C\u0005\u0003kBq!!!\u0001\t\u0013\t\u0019\tC\u0004\u0002\u0010\u0002!I!!%\t\u000f\u0005U\u0005\u0001\"\u0003\u0002\u0018\nQQ*Y:uKJ\u0004\u0016mZ3\u000b\u0005aI\u0012AA;j\u0015\tQ2$\u0001\u0004nCN$XM\u001d\u0006\u00039u\ta\u0001Z3qY>L(B\u0001\u0010 \u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0013%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002E\u0005\u0019qN]4\u0014\u0005\u0001!\u0003CA\u0013(\u001b\u00051#B\u0001\r\u001e\u0013\tAcEA\u0005XK\n,\u0016\nU1hK\u00061\u0001/\u0019:f]R\u001c\u0001\u0001\u0005\u0002-[5\tq#\u0003\u0002//\tYQ*Y:uKJ<VMY+J\u0003\u0019a\u0014N\\5u}Q\u0011\u0011G\r\t\u0003Y\u0001AQ!\u000b\u0002A\u0002-*\u0012\u0001\u000e\t\u0003kaj\u0011A\u000e\u0006\u0003ou\t1A\u001d9d\u0013\tIdG\u0001\bSa\u000e,e\u000e\u001a9pS:$(+\u001a4\u0002\u000f5\f7\u000f^3sA\u0005)A/\u001b;mKV\tQ\bE\u0002?\u0003\u000ek\u0011a\u0010\u0006\u0002\u0001\u0006)1oY1mC&\u0011!i\u0010\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\u0011[eBA#J!\t1u(D\u0001H\u0015\tA%&\u0001\u0004=e>|GOP\u0005\u0003\u0015~\na\u0001\u0015:fI\u00164\u0017B\u0001'N\u0005\u0019\u0019FO]5oO*\u0011!jP\u0001\u0007i&$H.\u001a\u0011\u0002!)\u001cxN\u001c$jK2$\u0007+\u0019;uKJtW#A)\u0011\u0005I;V\"A*\u000b\u0005Q+\u0016\u0001C7bi\u000eD\u0017N\\4\u000b\u0005Y{\u0014\u0001B;uS2L!\u0001W*\u0003\u000bI+w-\u001a=\u0002#)\u001cxN\u001c$jK2$\u0007+\u0019;uKJt\u0007%\u0001\bhKRl\u0015m\u001d;feN#\u0018\r^3\u0016\u0003q\u0003\"!\u00185\u000f\u0005y3gBA0f\u001d\t\u0001GM\u0004\u0002bG:\u0011aIY\u0005\u0002E%\u0011\u0001%I\u0005\u0003=}I!\u0001H\u000f\n\u0005\u001d\\\u0012A\u0004#fa2|\u00170T3tg\u0006<Wm]\u0005\u0003S*\u00141#T1ti\u0016\u00148\u000b^1uKJ+7\u000f]8og\u0016T!aZ\u000e\u0002\u0015I,g\u000eZ3s\u0015N|g\u000e\u0006\u0002ngB\u0011a.]\u0007\u0002_*\u0011\u0001/I\u0001\u0007UN|g\u000eN:\n\u0005I|'A\u0002&WC2,X\rC\u0003u\u0015\u0001\u0007Q/A\u0004sKF,Xm\u001d;\u0011\u0005YlX\"A<\u000b\u0005aL\u0018\u0001\u00025uiBT!A_>\u0002\u000fM,'O\u001e7fi*\tA0A\u0004kC.\f'\u000f^1\n\u0005y<(A\u0005%uiB\u001cVM\u001d<mKR\u0014V-];fgR\fA\u0003[1oI2,\u0017\t\u001d9LS2d'+Z9vKN$H\u0003BA\u0002\u0003\u0013\u00012APA\u0003\u0013\r\t9a\u0010\u0002\u0005+:LG\u000fC\u0003u\u0017\u0001\u0007Q/A\fiC:$G.\u001a#sSZ,'oS5mYJ+\u0017/^3tiR!\u00111AA\b\u0011\u0015!H\u00021\u0001v\u0003EA\u0017M\u001c3mK.KG\u000e\u001c*fcV,7\u000f\u001e\u000b\u0007\u0003\u0007\t)\"a\u0006\t\u000bQl\u0001\u0019A;\t\u000f\u0005eQ\u00021\u0001\u0002\u001c\u00051\u0011m\u0019;j_:\u0004bAPA\u000f\u0007\u0006\r\u0011bAA\u0010\u007f\tIa)\u001e8di&|g.M\u0001\u001dM>\u0014X.\u0019;X_J\\WM\u001d*fg>,(oY3t\t\u0016$\u0018-\u001b7t)\r\u0019\u0015Q\u0005\u0005\b\u0003Oq\u0001\u0019AA\u0015\u0003\u00199xN]6feB!\u00111FA\u0017\u001b\u0005I\u0012bAA\u00183\tQqk\u001c:lKJLeNZ8\u00025\u0019|'/\\1u\u001b\u0006\u001cH/\u001a:SKN|WO]2fg&sWk]3\u0015\u0007\r\u000b)\u0004C\u0004\u00028=\u0001\r!!\u000f\u0002\u0019\u0005d\u0017N^3X_J\\WM]:\u0011\u000by\nY$!\u000b\n\u0007\u0005urHA\u0003BeJ\f\u00170\u0001\u0004sK:$WM\u001d\u000b\u0005\u0003\u0007\n\t\u0007\u0005\u0004\u0002F\u0005=\u0013Q\u000b\b\u0005\u0003\u000f\nYED\u0002G\u0003\u0013J\u0011\u0001Q\u0005\u0004\u0003\u001bz\u0014a\u00029bG.\fw-Z\u0005\u0005\u0003#\n\u0019FA\u0002TKFT1!!\u0014@!\u0011\t9&!\u0018\u000e\u0005\u0005e#bAA.\u007f\u0005\u0019\u00010\u001c7\n\t\u0005}\u0013\u0011\f\u0002\u0005\u001d>$W\rC\u0003u!\u0001\u0007Q/A\u0005x_J\\WM\u001d*poR!\u0011qMA5!\u001dq\u0014QDA\u0015\u0003\u0007Bq!a\u001b\u0012\u0001\u0004\ti'\u0001\ntQ><(+Z:pkJ\u001cWmQ8mk6t\u0007c\u0001 \u0002p%\u0019\u0011\u0011O \u0003\u000f\t{w\u000e\\3b]\u00061\u0011\r\u001d9S_^$B!a\u0011\u0002x!9\u0011\u0011\u0010\nA\u0002\u0005m\u0014aA1qaB!\u00111FA?\u0013\r\ty(\u0007\u0002\u0010\u0003B\u0004H.[2bi&|g.\u00138g_\u0006y\u0011m\u0019;jm\u0016$%/\u001b<feJ{w\u000f\u0006\u0003\u0002D\u0005\u0015\u0005bBAD'\u0001\u0007\u0011\u0011R\u0001\u0007IJLg/\u001a:\u0011\t\u0005-\u00121R\u0005\u0004\u0003\u001bK\"A\u0003#sSZ,'/\u00138g_\u0006\u00112m\\7qY\u0016$X\r\u001a#sSZ,'OU8x)\u0011\t\u0019%a%\t\u000f\u0005\u001dE\u00031\u0001\u0002\n\u0006IAM]5wKJ\u0014vn\u001e\u000b\u0007\u0003\u0007\nI*a'\t\u000f\u0005\u001dU\u00031\u0001\u0002\n\"9\u0011QT\u000bA\u0002\u00055\u0014\u0001D:i_^$UO]1uS>t\u0007"
)
public class MasterPage extends WebUIPage {
   private final MasterWebUI parent;
   private final RpcEndpointRef master;
   private final Option title;
   private final Regex jsonFieldPattern;

   private RpcEndpointRef master() {
      return this.master;
   }

   private Option title() {
      return this.title;
   }

   private Regex jsonFieldPattern() {
      return this.jsonFieldPattern;
   }

   public DeployMessages.MasterStateResponse getMasterState() {
      return (DeployMessages.MasterStateResponse)this.master().askSync(DeployMessages.RequestMasterState$.MODULE$, .MODULE$.apply(DeployMessages.MasterStateResponse.class));
   }

   public JValue renderJson(final HttpServletRequest request) {
      boolean var3 = false;
      Some var4 = null;
      Option var5 = this.jsonFieldPattern().findFirstMatchIn(request.getRequestURI());
      if (scala.None..MODULE$.equals(var5)) {
         return JsonProtocol$.MODULE$.writeMasterState(this.getMasterState(), JsonProtocol$.MODULE$.writeMasterState$default$2());
      } else {
         if (var5 instanceof Some) {
            var3 = true;
            var4 = (Some)var5;
            Regex.Match m = (Regex.Match)var4.value();
            String var10000 = m.group(1);
            String var7 = "clusterutilization";
            if (var10000 == null) {
               if (var7 == null) {
                  return JsonProtocol$.MODULE$.writeClusterUtilization(this.getMasterState());
               }
            } else if (var10000.equals(var7)) {
               return JsonProtocol$.MODULE$.writeClusterUtilization(this.getMasterState());
            }
         }

         if (var3) {
            Regex.Match m = (Regex.Match)var4.value();
            return JsonProtocol$.MODULE$.writeMasterState(this.getMasterState(), new Some(m.group(1)));
         } else {
            throw new MatchError(var5);
         }
      }
   }

   public void handleAppKillRequest(final HttpServletRequest request) {
      this.handleKillRequest(request, (id) -> {
         $anonfun$handleAppKillRequest$1(this, id);
         return BoxedUnit.UNIT;
      });
   }

   public void handleDriverKillRequest(final HttpServletRequest request) {
      this.handleKillRequest(request, (id) -> {
         $anonfun$handleDriverKillRequest$1(this, id);
         return BoxedUnit.UNIT;
      });
   }

   private void handleKillRequest(final HttpServletRequest request, final Function1 action) {
      if (this.parent.killEnabled() && this.parent.master().securityMgr().checkModifyPermissions(request.getRemoteUser())) {
         boolean killFlag = scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString((String)scala.Option..MODULE$.apply(request.getParameter("terminate")).getOrElse(() -> "false")));
         Option id = scala.Option..MODULE$.apply(request.getParameter("id"));
         if (id.isDefined() && killFlag) {
            action.apply(id.get());
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         Thread.sleep(100L);
      }
   }

   private String formatWorkerResourcesDetails(final WorkerInfo worker) {
      Map usedInfo = worker.resourcesInfoUsed();
      Map freeInfo = worker.resourcesInfoFree();
      return StandaloneResourceUtils$.MODULE$.formatResourcesDetails(usedInfo, freeInfo);
   }

   private String formatMasterResourcesInUse(final WorkerInfo[] aliveWorkers) {
      Map totalInfo = (Map)scala.collection.ArrayOps..MODULE$.groupBy$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$1) -> x$1.resourcesInfo(), .MODULE$.apply(Map.class))), (x$2) -> x$2.iterator(), .MODULE$.apply(Tuple2.class))), (x$3) -> (String)x$3._1()).map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            Tuple2[] rInfoArr = (Tuple2[])x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])rInfoArr), (x$4) -> BoxesRunTime.boxToInteger($anonfun$formatMasterResourcesInUse$5(x$4)), .MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
         } else {
            throw new MatchError(x0$1);
         }
      });
      Map usedInfo = (Map)scala.collection.ArrayOps..MODULE$.groupBy$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$5) -> x$5.resourcesInfoUsed(), .MODULE$.apply(Map.class))), (x$6) -> x$6.iterator(), .MODULE$.apply(Tuple2.class))), (x$7) -> (String)x$7._1()).map((x0$2) -> {
         if (x0$2 != null) {
            String rName = (String)x0$2._1();
            Tuple2[] rInfoArr = (Tuple2[])x0$2._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(rName), scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])rInfoArr), (x$8) -> BoxesRunTime.boxToInteger($anonfun$formatMasterResourcesInUse$10(x$8)), .MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
         } else {
            throw new MatchError(x0$2);
         }
      });
      return StandaloneResourceUtils$.MODULE$.formatResourcesUsed(totalInfo, usedInfo);
   }

   public Seq render(final HttpServletRequest request) {
      DeployMessages.MasterStateResponse state = this.getMasterState();
      boolean showResourceColumn = scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(state.workers()), (x$9) -> BoxesRunTime.boxToBoolean($anonfun$render$1(x$9)))));
      Seq workerHeaders = showResourceColumn ? new scala.collection.immutable..colon.colon("Worker Id", new scala.collection.immutable..colon.colon("Address", new scala.collection.immutable..colon.colon("State", new scala.collection.immutable..colon.colon("Cores", new scala.collection.immutable..colon.colon("Memory", new scala.collection.immutable..colon.colon("Resources", scala.collection.immutable.Nil..MODULE$)))))) : new scala.collection.immutable..colon.colon("Worker Id", new scala.collection.immutable..colon.colon("Address", new scala.collection.immutable..colon.colon("State", new scala.collection.immutable..colon.colon("Cores", new scala.collection.immutable..colon.colon("Memory", scala.collection.immutable.Nil..MODULE$)))));
      WorkerInfo[] workers = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(state.workers()), (x$10) -> x$10.id(), scala.math.Ordering.String..MODULE$);
      WorkerInfo[] aliveWorkers = (WorkerInfo[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(state.workers()), (x$11) -> BoxesRunTime.boxToBoolean($anonfun$render$3(x$11)));
      Seq workerTable = UIUtils$.MODULE$.listingTable(workerHeaders, this.workerRow(showResourceColumn), scala.Predef..MODULE$.wrapRefArray(workers), UIUtils$.MODULE$.listingTable$default$4(), UIUtils$.MODULE$.listingTable$default$5(), UIUtils$.MODULE$.listingTable$default$6(), UIUtils$.MODULE$.listingTable$default$7(), UIUtils$.MODULE$.listingTable$default$8(), UIUtils$.MODULE$.listingTable$default$9());
      Seq appHeaders = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application ID", "Name", "Cores", "Memory per Executor", "Resources Per Executor", "Submitted Time", "User", "State", "Duration"})));
      ApplicationInfo[] activeApps = (ApplicationInfo[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(state.activeApps()), (x$12) -> BoxesRunTime.boxToLong($anonfun$render$4(x$12)), scala.math.Ordering.Long..MODULE$)));
      Seq activeAppsTable = UIUtils$.MODULE$.listingTable(appHeaders, (app) -> this.appRow(app), scala.Predef..MODULE$.wrapRefArray(activeApps), UIUtils$.MODULE$.listingTable$default$4(), UIUtils$.MODULE$.listingTable$default$5(), UIUtils$.MODULE$.listingTable$default$6(), UIUtils$.MODULE$.listingTable$default$7(), UIUtils$.MODULE$.listingTable$default$8(), UIUtils$.MODULE$.listingTable$default$9());
      ApplicationInfo[] completedApps = (ApplicationInfo[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(state.completedApps()), (x$13) -> BoxesRunTime.boxToLong($anonfun$render$6(x$13)), scala.math.Ordering.Long..MODULE$)));
      Seq completedAppsTable = UIUtils$.MODULE$.listingTable(appHeaders, (app) -> this.appRow(app), scala.Predef..MODULE$.wrapRefArray(completedApps), UIUtils$.MODULE$.listingTable$default$4(), UIUtils$.MODULE$.listingTable$default$5(), UIUtils$.MODULE$.listingTable$default$6(), UIUtils$.MODULE$.listingTable$default$7(), UIUtils$.MODULE$.listingTable$default$8(), UIUtils$.MODULE$.listingTable$default$9());
      Seq activeDriverHeaders = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submission ID", "Submitted Time", "Worker", "State", "Cores", "Memory", "Resources", "Main Class", "Duration"})));
      DriverInfo[] activeDrivers = (DriverInfo[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(state.activeDrivers()), (x$14) -> BoxesRunTime.boxToLong($anonfun$render$8(x$14)), scala.math.Ordering.Long..MODULE$)));
      Seq activeDriversTable = UIUtils$.MODULE$.listingTable(activeDriverHeaders, (driver) -> this.activeDriverRow(driver), scala.Predef..MODULE$.wrapRefArray(activeDrivers), UIUtils$.MODULE$.listingTable$default$4(), UIUtils$.MODULE$.listingTable$default$5(), UIUtils$.MODULE$.listingTable$default$6(), UIUtils$.MODULE$.listingTable$default$7(), UIUtils$.MODULE$.listingTable$default$8(), UIUtils$.MODULE$.listingTable$default$9());
      Seq completedDriverHeaders = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submission ID", "Submitted Time", "Worker", "State", "Cores", "Memory", "Resources", "Main Class"})));
      DriverInfo[] completedDrivers = (DriverInfo[])scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(state.completedDrivers()), (x$15) -> BoxesRunTime.boxToLong($anonfun$render$10(x$15)), scala.math.Ordering.Long..MODULE$)));
      Seq completedDriversTable = UIUtils$.MODULE$.listingTable(completedDriverHeaders, (driver) -> this.completedDriverRow(driver), scala.Predef..MODULE$.wrapRefArray(completedDrivers), UIUtils$.MODULE$.listingTable$default$4(), UIUtils$.MODULE$.listingTable$default$5(), UIUtils$.MODULE$.listingTable$default$6(), UIUtils$.MODULE$.listingTable$default$7(), UIUtils$.MODULE$.listingTable$default$8(), UIUtils$.MODULE$.listingTable$default$9());
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var108 = new UnprefixedAttribute("class", new Text("row"), $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var109 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      TopScope var10015 = scala.xml.TopScope..MODULE$;
      NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var110 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
      TopScope var10024 = scala.xml.TopScope..MODULE$;
      NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      Null var10032 = scala.xml.Null..MODULE$;
      TopScope var10033 = scala.xml.TopScope..MODULE$;
      NodeSeq var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      Null var10041 = scala.xml.Null..MODULE$;
      TopScope var10042 = scala.xml.TopScope..MODULE$;
      NodeSeq var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("URL:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(state.uri());
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(state.restUri().map((uri) -> {
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                    "));
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("REST URL:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(uri);
         $buf.$amp$plus(new Text("\n                    "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var5 = new UnprefixedAttribute("class", new Text("rest-uri"), $md);
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text(" (cluster mode)"));
         $buf.$amp$plus(new Elem((String)null, "span", var5, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                  "));
         return new Elem((String)null, "li", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      }).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty()));
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Workers:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(aliveWorkers.length));
      $buf.$amp$plus(new Text(" Alive,\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(workers), (x$16) -> BoxesRunTime.boxToBoolean($anonfun$render$14(x$16)))));
      $buf.$amp$plus(new Text(" Dead,\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(workers), (x$17) -> BoxesRunTime.boxToBoolean($anonfun$render$15(x$17)))));
      $buf.$amp$plus(new Text(" Decommissioned,\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(workers), (x$18) -> BoxesRunTime.boxToBoolean($anonfun$render$16(x$18)))));
      $buf.$amp$plus(new Text(" Unknown\n              "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Cores in use:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$19) -> BoxesRunTime.boxToInteger($anonfun$render$17(x$19)), .MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      $buf.$amp$plus(new Text(" Total,\n                "));
      $buf.$amp$plus(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$20) -> BoxesRunTime.boxToInteger($anonfun$render$18(x$20)), .MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      $buf.$amp$plus(new Text(" Used"));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Memory in use:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n                "));
      $buf.$amp$plus(Utils$.MODULE$.megabytesToString((long)BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$21) -> BoxesRunTime.boxToInteger($anonfun$render$19(x$21)), .MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$))));
      $buf.$amp$plus(new Text(" Total,\n                "));
      $buf.$amp$plus(Utils$.MODULE$.megabytesToString((long)BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(aliveWorkers), (x$22) -> BoxesRunTime.boxToInteger($anonfun$render$20(x$22)), .MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$))));
      $buf.$amp$plus(new Text(" Used"));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Resources in use:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n                "));
      $buf.$amp$plus(this.formatMasterResourcesInUse(aliveWorkers));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Applications:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(state.activeApps().length));
      $buf.$amp$plus(new Text(" "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var111 = new UnprefixedAttribute("href", new Text("#running-app"), $md);
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Running"));
      $buf.$amp$plus(new Elem((String)null, "a", var111, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(",\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(state.completedApps().length));
      $buf.$amp$plus(new Text(" "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var112 = new UnprefixedAttribute("href", new Text("#completed-app"), $md);
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Completed"));
      $buf.$amp$plus(new Elem((String)null, "a", var112, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Drivers:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(state.activeDrivers().length));
      $buf.$amp$plus(new Text(" Running\n                ("));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(state.activeDrivers()), (x$23) -> BoxesRunTime.boxToBoolean($anonfun$render$21(x$23)))));
      $buf.$amp$plus(new Text(" Waiting),\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(state.completedDrivers().length));
      $buf.$amp$plus(new Text(" Completed\n                ("));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(state.completedDrivers()), (x$24) -> BoxesRunTime.boxToBoolean($anonfun$render$22(x$24)))));
      $buf.$amp$plus(new Text(" Killed,\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(state.completedDrivers()), (x$25) -> BoxesRunTime.boxToBoolean($anonfun$render$23(x$25)))));
      $buf.$amp$plus(new Text(" Failed,\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(state.completedDrivers()), (x$26) -> BoxesRunTime.boxToBoolean($anonfun$render$24(x$26)))));
      $buf.$amp$plus(new Text(" Error,\n                "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(scala.collection.ArrayOps..MODULE$.count$extension(scala.Predef..MODULE$.refArrayOps(state.completedDrivers()), (x$27) -> BoxesRunTime.boxToBoolean($anonfun$render$25(x$27)))));
      $buf.$amp$plus(new Text(" Relaunching)\n              "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Status:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(state.status());
      $buf.$amp$plus(new Text("\n                ("));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var113 = new UnprefixedAttribute("href", "/environment/", $md);
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Environment"));
      $buf.$amp$plus(new Elem((String)null, "a", var113, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(",\n                "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var114 = new UnprefixedAttribute("href", "/logPage/?self&logType=out", $md);
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Log"));
      $buf.$amp$plus(new Elem((String)null, "a", var114, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(")\n              "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "ul", var110, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var109, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var108, var10006, false, var10008.seqToNodeSeq($buf)));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var115 = new UnprefixedAttribute("class", new Text("row"), $md);
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var116 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var117 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-workers','aggregated-workers')"), $md);
      var117 = new UnprefixedAttribute("class", new Text("collapse-aggregated-workers collapse-table"), var117);
      var10024 = scala.xml.TopScope..MODULE$;
      var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n                "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var119 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var119, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n                "));
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Workers ("));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(workers.length));
      $buf.$amp$plus(new Text(")"));
      $buf.$amp$plus(new Elem((String)null, "a", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "span", var117, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var120 = new UnprefixedAttribute("class", new Text("aggregated-workers collapsible-table"), $md);
      var10024 = scala.xml.TopScope..MODULE$;
      var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(workerTable);
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "div", var120, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var116, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var115, var10006, false, var10008.seqToNodeSeq($buf)));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var121 = new UnprefixedAttribute("class", new Text("row"), $md);
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var122 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var123 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-activeApps','aggregated-activeApps')"), $md);
      var123 = new UnprefixedAttribute("class", new Text("collapse-aggregated-activeApps collapse-table"), var123);
      var123 = new UnprefixedAttribute("id", new Text("running-app"), var123);
      var10024 = scala.xml.TopScope..MODULE$;
      var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n                "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var126 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var126, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n                "));
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Running Applications ("));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(activeApps.length));
      $buf.$amp$plus(new Text(")"));
      $buf.$amp$plus(new Elem((String)null, "a", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "span", var123, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var127 = new UnprefixedAttribute("class", new Text("aggregated-activeApps collapsible-table"), $md);
      var10024 = scala.xml.TopScope..MODULE$;
      var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(activeAppsTable);
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "div", var127, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var122, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var121, var10006, false, var10008.seqToNodeSeq($buf)));
      Elem var10001 = new Elem;
      Null var10005 = scala.xml.Null..MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      Object var10010;
      if (hasDrivers$1(activeDrivers, completedDrivers)) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var128 = new UnprefixedAttribute("class", new Text("row"), $md);
         var10015 = scala.xml.TopScope..MODULE$;
         var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n               "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var129 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                 "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var130 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-activeDrivers',\n                     'aggregated-activeDrivers')"), $md);
         var130 = new UnprefixedAttribute("class", new Text("collapse-aggregated-activeDrivers collapse-table"), var130);
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                   "));
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                     "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var132 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var132, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n                     "));
         Null var10050 = scala.xml.Null..MODULE$;
         TopScope var10051 = scala.xml.TopScope..MODULE$;
         NodeSeq var10053 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Running Drivers ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(activeDrivers.length));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var10050, var10051, false, var10053.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                   "));
         $buf.$amp$plus(new Elem((String)null, "h4", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                 "));
         $buf.$amp$plus(new Elem((String)null, "span", var130, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                 "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var133 = new UnprefixedAttribute("class", new Text("aggregated-activeDrivers collapsible-table"), $md);
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                   "));
         $buf.$amp$plus(activeDriversTable);
         $buf.$amp$plus(new Text("\n                 "));
         $buf.$amp$plus(new Elem((String)null, "div", var133, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n               "));
         $buf.$amp$plus(new Elem((String)null, "div", var129, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n             "));
         var10010 = new Elem((String)null, "div", var128, var10015, false, var10017.seqToNodeSeq($buf));
      } else {
         var10010 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10010);
      $buf.$amp$plus(new Text("\n        "));
      var10001.<init>((String)null, "div", var10005, var10006, false, var10008.seqToNodeSeq($buf));
      $buf.$amp$plus(var10001);
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var134 = new UnprefixedAttribute("class", new Text("row"), $md);
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var135 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var136 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-completedApps',\n                'aggregated-completedApps')"), $md);
      var136 = new UnprefixedAttribute("class", new Text("collapse-aggregated-completedApps collapse-table"), var136);
      var136 = new UnprefixedAttribute("id", new Text("completed-app"), var136);
      var10024 = scala.xml.TopScope..MODULE$;
      var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n                "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var139 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var139, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n                "));
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Completed Applications ("));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(completedApps.length));
      $buf.$amp$plus(new Text(")"));
      $buf.$amp$plus(new Elem((String)null, "a", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "span", var136, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var140 = new UnprefixedAttribute("class", new Text("aggregated-completedApps collapsible-table"), $md);
      var10024 = scala.xml.TopScope..MODULE$;
      var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(completedAppsTable);
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "div", var140, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "div", var135, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var134, var10006, false, var10008.seqToNodeSeq($buf)));
      var10001 = new Elem;
      var10005 = scala.xml.Null..MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      if (hasDrivers$1(activeDrivers, completedDrivers)) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var141 = new UnprefixedAttribute("class", new Text("row"), $md);
         var10015 = scala.xml.TopScope..MODULE$;
         var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var142 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                  "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var143 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-completedDrivers',\n                      'aggregated-completedDrivers')"), $md);
         var143 = new UnprefixedAttribute("class", new Text("collapse-aggregated-completedDrivers collapse-table"), var143);
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                    "));
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var145 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var145, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n                      "));
         Null var268 = scala.xml.Null..MODULE$;
         TopScope var269 = scala.xml.TopScope..MODULE$;
         NodeSeq var270 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Completed Drivers ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(completedDrivers.length));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var268, var269, false, var270.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                    "));
         $buf.$amp$plus(new Elem((String)null, "h4", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                  "));
         $buf.$amp$plus(new Elem((String)null, "span", var143, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                  "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var146 = new UnprefixedAttribute("class", new Text("aggregated-completedDrivers collapsible-table"), $md);
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                    "));
         $buf.$amp$plus(completedDriversTable);
         $buf.$amp$plus(new Text("\n                  "));
         $buf.$amp$plus(new Elem((String)null, "div", var146, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                "));
         $buf.$amp$plus(new Elem((String)null, "div", var142, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10010 = new Elem((String)null, "div", var141, var10015, false, var10017.seqToNodeSeq($buf));
      } else {
         var10010 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10010);
      $buf.$amp$plus(new Text("\n        "));
      var10001.<init>((String)null, "div", var10005, var10006, false, var10008.seqToNodeSeq($buf));
      $buf.$amp$plus(var10001);
      return UIUtils$.MODULE$.basicSparkPage(request, () -> scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf), (String)this.title().getOrElse(() -> "Spark Master at " + state.uri()), UIUtils$.MODULE$.basicSparkPage$default$4());
   }

   private Function1 workerRow(final boolean showResourceColumn) {
      return (worker) -> {
         Elem var10000 = new Elem;
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         Elem var10009 = new Elem;
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         Object var10018;
         if (worker.isAlive()) {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var13 = new UnprefixedAttribute("href", UIUtils$.MODULE$.makeHref(this.parent.master().reverseProxy(), worker.id(), worker.webUiAddress()), $md);
            TopScope var10023 = scala.xml.TopScope..MODULE$;
            NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(worker.id());
            $buf.$amp$plus(new Text("\n            "));
            var10018 = new Elem((String)null, "a", var13, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = worker.id();
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n      "));
         var10009.<init>((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf));
         $buf.$amp$plus(var10009);
         $buf.$amp$plus(new Text("\n      "));
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(worker.host());
         $buf.$amp$plus(new Text(":"));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(worker.port()));
         $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(worker.state());
         $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(BoxesRunTime.boxToInteger(worker.cores()));
         $buf.$amp$plus(new Text(" ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(worker.coresUsed()));
         $buf.$amp$plus(new Text(" Used)"));
         $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var14 = new UnprefixedAttribute("sorttable_customkey", scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s.%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(worker.memory()), BoxesRunTime.boxToInteger(worker.memoryUsed())})), $md);
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(Utils$.MODULE$.megabytesToString((long)worker.memory()));
         $buf.$amp$plus(new Text("\n        ("));
         $buf.$amp$plus(Utils$.MODULE$.megabytesToString((long)worker.memoryUsed()));
         $buf.$amp$plus(new Text(" Used)\n      "));
         $buf.$amp$plus(new Elem((String)null, "td", var14, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         Object var15;
         if (showResourceColumn) {
            var10013 = scala.xml.Null..MODULE$;
            var10014 = scala.xml.TopScope..MODULE$;
            var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(this.formatWorkerResourcesDetails(worker));
            var15 = new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf));
         } else {
            var15 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var15);
         $buf.$amp$plus(new Text("\n    "));
         var10000.<init>((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         return var10000;
      };
   }

   private Seq appRow(final ApplicationInfo app) {
      Object var45;
      label36: {
         label35: {
            if (this.parent.killEnabled()) {
               Enumeration.Value var10000 = app.state();
               Enumeration.Value var3 = ApplicationState$.MODULE$.RUNNING();
               if (var10000 == null) {
                  if (var3 == null) {
                     break label35;
                  }
               } else if (var10000.equals(var3)) {
                  break label35;
               }

               var10000 = app.state();
               Enumeration.Value var4 = ApplicationState$.MODULE$.WAITING();
               if (var10000 == null) {
                  if (var4 == null) {
                     break label35;
                  }
               } else if (var10000.equals(var4)) {
                  break label35;
               }
            }

            var45 = BoxedUnit.UNIT;
            break label36;
         }

         String confirm = "if (window.confirm('Are you sure you want to kill application " + app.id() + " ?')) { this.parentNode.submit(); return true; } else { return false; }";
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var28 = new UnprefixedAttribute("style", new Text("display:inline"), $md);
         var28 = new UnprefixedAttribute("method", new Text("POST"), var28);
         var28 = new UnprefixedAttribute("action", new Text("app/kill/"), var28);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var31 = new UnprefixedAttribute("value", app.id(), $md);
         var31 = new UnprefixedAttribute("name", new Text("id"), var31);
         var31 = new UnprefixedAttribute("type", new Text("hidden"), var31);
         $buf.$amp$plus(new Elem((String)null, "input", var31, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var34 = new UnprefixedAttribute("value", new Text("true"), $md);
         var34 = new UnprefixedAttribute("name", new Text("terminate"), var34);
         var34 = new UnprefixedAttribute("type", new Text("hidden"), var34);
         $buf.$amp$plus(new Elem((String)null, "input", var34, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var37 = new UnprefixedAttribute("class", new Text("kill-link"), $md);
         var37 = new UnprefixedAttribute("onclick", confirm, var37);
         var37 = new UnprefixedAttribute("href", new Text("#"), var37);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("(kill)"));
         $buf.$amp$plus(new Elem((String)null, "a", var37, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var45 = new Elem((String)null, "form", var28, var10005, false, var10007.seqToNodeSeq($buf));
      }

      Object killLink = var45;
      Elem var46 = new Elem;
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var47 = scala.xml.TopScope..MODULE$;
      NodeSeq var48 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var55 = scala.xml.TopScope..MODULE$;
      NodeSeq var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var40 = new UnprefixedAttribute("href", "app/?appId=" + app.id(), $md);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(app.id());
      $buf.$amp$plus(new Elem((String)null, "a", var40, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(killLink);
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var55, false, var64.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Elem var10009 = new Elem;
      var10013 = scala.xml.Null..MODULE$;
      var55 = scala.xml.TopScope..MODULE$;
      var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      Object var10018;
      if (!app.isFinished() && !app.desc().appUiUrl().isBlank()) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var41 = new UnprefixedAttribute("href", UIUtils$.MODULE$.makeHref(this.parent.master().reverseProxy(), app.id(), app.desc().appUiUrl()), $md);
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(app.desc().name());
         var10018 = new Elem((String)null, "a", var41, var10023, false, var10025.seqToNodeSeq($buf));
      } else {
         var10018 = app.desc().name();
      }

      $buf.$amp$plus(var10018);
      $buf.$amp$plus(new Text("\n      "));
      var10009.<init>((String)null, "td", var10013, var55, false, var64.seqToNodeSeq($buf));
      $buf.$amp$plus(var10009);
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var55 = scala.xml.TopScope..MODULE$;
      var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(app.coresGranted()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var55, false, var64.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var42 = new UnprefixedAttribute("sorttable_customkey", Integer.toString(app.desc().memoryPerExecutorMB()), $md);
      var55 = scala.xml.TopScope..MODULE$;
      var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(Utils$.MODULE$.megabytesToString((long)app.desc().memoryPerExecutorMB()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var42, var55, false, var64.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var55 = scala.xml.TopScope..MODULE$;
      var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(StandaloneResourceUtils$.MODULE$.formatResourceRequirements(app.desc().resourceReqsPerExecutor()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var55, false, var64.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var55 = scala.xml.TopScope..MODULE$;
      var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(UIUtils$.MODULE$.formatDate(app.submitDate()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var55, false, var64.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var55 = scala.xml.TopScope..MODULE$;
      var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(app.desc().user());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var55, false, var64.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var55 = scala.xml.TopScope..MODULE$;
      var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(app.state().toString());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var55, false, var64.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var43 = new UnprefixedAttribute("sorttable_customkey", Long.toString(app.duration()), $md);
      var55 = scala.xml.TopScope..MODULE$;
      var64 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(UIUtils$.MODULE$.formatDuration(app.duration()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var43, var55, false, var64.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      var46.<init>((String)null, "tr", var10004, var47, false, var48.seqToNodeSeq($buf));
      return var46;
   }

   private Seq activeDriverRow(final DriverInfo driver) {
      return this.driverRow(driver, true);
   }

   private Seq completedDriverRow(final DriverInfo driver) {
      return this.driverRow(driver, false);
   }

   private Seq driverRow(final DriverInfo driver, final boolean showDuration) {
      Object var42;
      label32: {
         label31: {
            if (this.parent.killEnabled()) {
               Enumeration.Value var10000 = driver.state();
               Enumeration.Value var4 = DriverState$.MODULE$.RUNNING();
               if (var10000 == null) {
                  if (var4 == null) {
                     break label31;
                  }
               } else if (var10000.equals(var4)) {
                  break label31;
               }

               var10000 = driver.state();
               Enumeration.Value var5 = DriverState$.MODULE$.SUBMITTED();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label31;
                  }
               } else if (var10000.equals(var5)) {
                  break label31;
               }
            }

            var42 = BoxedUnit.UNIT;
            break label32;
         }

         String confirm = "if (window.confirm('Are you sure you want to kill driver " + driver.id() + " ?')) { this.parentNode.submit(); return true; } else { return false; }";
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var26 = new UnprefixedAttribute("style", new Text("display:inline"), $md);
         var26 = new UnprefixedAttribute("method", new Text("POST"), var26);
         var26 = new UnprefixedAttribute("action", new Text("driver/kill/"), var26);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var29 = new UnprefixedAttribute("value", driver.id(), $md);
         var29 = new UnprefixedAttribute("name", new Text("id"), var29);
         var29 = new UnprefixedAttribute("type", new Text("hidden"), var29);
         $buf.$amp$plus(new Elem((String)null, "input", var29, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var32 = new UnprefixedAttribute("value", new Text("true"), $md);
         var32 = new UnprefixedAttribute("name", new Text("terminate"), var32);
         var32 = new UnprefixedAttribute("type", new Text("hidden"), var32);
         $buf.$amp$plus(new Elem((String)null, "input", var32, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var35 = new UnprefixedAttribute("class", new Text("kill-link"), $md);
         var35 = new UnprefixedAttribute("onclick", confirm, var35);
         var35 = new UnprefixedAttribute("href", new Text("#"), var35);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("(kill)"));
         $buf.$amp$plus(new Elem((String)null, "a", var35, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var42 = new Elem((String)null, "form", var26, var10005, false, var10007.seqToNodeSeq($buf));
      }

      Object killLink = var42;
      Elem var43 = new Elem;
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var44 = scala.xml.TopScope..MODULE$;
      NodeSeq var45 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var51 = scala.xml.TopScope..MODULE$;
      NodeSeq var60 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(driver.id());
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(killLink);
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var51, false, var60.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var51 = scala.xml.TopScope..MODULE$;
      var60 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(UIUtils$.MODULE$.formatDate(driver.submitDate()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var51, false, var60.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var51 = scala.xml.TopScope..MODULE$;
      var60 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(driver.worker().map((w) -> {
         if (w.isAlive()) {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var4 = new UnprefixedAttribute("href", UIUtils$.MODULE$.makeHref(this.parent.master().reverseProxy(), w.id(), w.webUiAddress()), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(w.id());
            $buf.$amp$plus(new Text("\n          "));
            return new Elem((String)null, "a", var4, var10005, false, var10007.seqToNodeSeq($buf));
         } else {
            return w.id();
         }
      }).getOrElse(() -> "None"));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var51, false, var60.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var51 = scala.xml.TopScope..MODULE$;
      var60 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(driver.state());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var51, false, var60.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var38 = new UnprefixedAttribute("sorttable_customkey", Integer.toString(driver.desc().cores()), $md);
      var51 = scala.xml.TopScope..MODULE$;
      var60 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(driver.desc().cores()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var38, var51, false, var60.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var39 = new UnprefixedAttribute("sorttable_customkey", Integer.toString(driver.desc().mem()), $md);
      var51 = scala.xml.TopScope..MODULE$;
      var60 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(Utils$.MODULE$.megabytesToString((long)driver.desc().mem()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var39, var51, false, var60.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var51 = scala.xml.TopScope..MODULE$;
      var60 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(StandaloneResourceUtils$.MODULE$.formatResourcesAddresses(driver.resources()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var51, false, var60.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var51 = scala.xml.TopScope..MODULE$;
      var60 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(driver.desc().command().arguments().apply(2));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var51, false, var60.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Object var10009;
      if (showDuration) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var40 = new UnprefixedAttribute("sorttable_customkey", Long.toString(-driver.startTime()), $md);
         var51 = scala.xml.TopScope..MODULE$;
         var60 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(UIUtils$.MODULE$.formatDuration(System.currentTimeMillis() - driver.startTime()));
         $buf.$amp$plus(new Text("\n        "));
         var10009 = new Elem((String)null, "td", var40, var51, false, var60.seqToNodeSeq($buf));
      } else {
         var10009 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10009);
      $buf.$amp$plus(new Text("\n    "));
      var43.<init>((String)null, "tr", var10004, var44, false, var45.seqToNodeSeq($buf));
      return var43;
   }

   // $FF: synthetic method
   public static final void $anonfun$handleAppKillRequest$2(final MasterPage $this, final ApplicationInfo app) {
      $this.parent.master().removeApplication(app, ApplicationState$.MODULE$.KILLED());
   }

   // $FF: synthetic method
   public static final void $anonfun$handleAppKillRequest$1(final MasterPage $this, final String id) {
      $this.parent.master().idToApp().get(id).foreach((app) -> {
         $anonfun$handleAppKillRequest$2($this, app);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$handleDriverKillRequest$1(final MasterPage $this, final String id) {
      $this.master().ask(new DeployMessages.RequestKillDriver(id), .MODULE$.apply(DeployMessages.KillDriverResponse.class));
   }

   // $FF: synthetic method
   public static final int $anonfun$formatMasterResourcesInUse$5(final Tuple2 x$4) {
      return ((ResourceInformation)x$4._2()).addresses().length;
   }

   // $FF: synthetic method
   public static final int $anonfun$formatMasterResourcesInUse$10(final Tuple2 x$8) {
      return ((ResourceInformation)x$8._2()).addresses().length;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$1(final WorkerInfo x$9) {
      return x$9.resourcesInfoUsed().nonEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$3(final WorkerInfo x$11) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$11.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.ALIVE();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final long $anonfun$render$4(final ApplicationInfo x$12) {
      return x$12.startTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$render$6(final ApplicationInfo x$13) {
      return x$13.endTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$render$8(final DriverInfo x$14) {
      return x$14.startTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$render$10(final DriverInfo x$15) {
      return x$15.startTime();
   }

   private static final boolean hasDrivers$1(final DriverInfo[] activeDrivers$1, final DriverInfo[] completedDrivers$1) {
      return activeDrivers$1.length > 0 || completedDrivers$1.length > 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$14(final WorkerInfo x$16) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$16.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.DEAD();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$15(final WorkerInfo x$17) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$17.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.DECOMMISSIONED();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$16(final WorkerInfo x$18) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$18.state();
         Enumeration.Value var1 = WorkerState$.MODULE$.UNKNOWN();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final int $anonfun$render$17(final WorkerInfo x$19) {
      return x$19.cores();
   }

   // $FF: synthetic method
   public static final int $anonfun$render$18(final WorkerInfo x$20) {
      return x$20.coresUsed();
   }

   // $FF: synthetic method
   public static final int $anonfun$render$19(final WorkerInfo x$21) {
      return x$21.memory();
   }

   // $FF: synthetic method
   public static final int $anonfun$render$20(final WorkerInfo x$22) {
      return x$22.memoryUsed();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$21(final DriverInfo x$23) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$23.state();
         Enumeration.Value var1 = DriverState$.MODULE$.SUBMITTED();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$22(final DriverInfo x$24) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$24.state();
         Enumeration.Value var1 = DriverState$.MODULE$.KILLED();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$23(final DriverInfo x$25) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$25.state();
         Enumeration.Value var1 = DriverState$.MODULE$.FAILED();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$24(final DriverInfo x$26) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$26.state();
         Enumeration.Value var1 = DriverState$.MODULE$.ERROR();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$25(final DriverInfo x$27) {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = x$27.state();
         Enumeration.Value var1 = DriverState$.MODULE$.RELAUNCHING();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public MasterPage(final MasterWebUI parent) {
      super("");
      this.parent = parent;
      this.master = parent.masterEndpointRef();
      this.title = (Option)parent.master().conf().get((ConfigEntry)UI$.MODULE$.MASTER_UI_TITLE());
      this.jsonFieldPattern = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("/json/([a-zA-Z]+).*"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
