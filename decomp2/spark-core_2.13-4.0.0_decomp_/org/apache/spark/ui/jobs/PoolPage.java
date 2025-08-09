package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.scheduler.Schedulable;
import org.apache.spark.status.PoolData;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import scala.Tuple2;
import scala.Option.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013Q\u0001B\u0003\u0001\u000f=A\u0001\u0002\u0006\u0001\u0003\u0002\u0003\u0006IA\u0006\u0005\u00065\u0001!\ta\u0007\u0005\u0006=\u0001!\ta\b\u0002\t!>|G\u000eU1hK*\u0011aaB\u0001\u0005U>\u00147O\u0003\u0002\t\u0013\u0005\u0011Q/\u001b\u0006\u0003\u0015-\tQa\u001d9be.T!\u0001D\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0011aA8sON\u0011\u0001\u0001\u0005\t\u0003#Ii\u0011aB\u0005\u0003'\u001d\u0011\u0011bV3c+&\u0003\u0016mZ3\u0002\rA\f'/\u001a8u\u0007\u0001\u0001\"a\u0006\r\u000e\u0003\u0015I!!G\u0003\u0003\u0013M#\u0018mZ3t)\u0006\u0014\u0017A\u0002\u001fj]&$h\b\u0006\u0002\u001d;A\u0011q\u0003\u0001\u0005\u0006)\t\u0001\rAF\u0001\u0007e\u0016tG-\u001a:\u0015\u0005\u0001\"\u0004cA\u0011,]9\u0011!\u0005\u000b\b\u0003G\u0019j\u0011\u0001\n\u0006\u0003KU\ta\u0001\u0010:p_Rt\u0014\"A\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005%R\u0013a\u00029bG.\fw-\u001a\u0006\u0002O%\u0011A&\f\u0002\u0004'\u0016\f(BA\u0015+!\ty#'D\u00011\u0015\t\t$&A\u0002y[2L!a\r\u0019\u0003\t9{G-\u001a\u0005\u0006k\r\u0001\rAN\u0001\be\u0016\fX/Z:u!\t9d(D\u00019\u0015\tI$(\u0001\u0003iiR\u0004(BA\u001e=\u0003\u001d\u0019XM\u001d<mKRT\u0011!P\u0001\bU\u0006\\\u0017M\u001d;b\u0013\ty\u0004H\u0001\nIiR\u00048+\u001a:wY\u0016$(+Z9vKN$\b"
)
public class PoolPage extends WebUIPage {
   private final StagesTab parent;

   public Seq render(final HttpServletRequest request) {
      String poolName = (String).MODULE$.apply(request.getParameter("poolname")).map((poolname) -> UIUtils$.MODULE$.decodeURLParameter(poolname)).getOrElse(() -> {
         throw new IllegalArgumentException("Missing poolname parameter");
      });
      Schedulable pool = (Schedulable)this.parent.sc().flatMap((x$1) -> x$1.getPoolForName(poolName)).getOrElse(() -> {
         throw new IllegalArgumentException("Unknown pool: " + poolName);
      });
      PoolData uiPool = (PoolData)this.parent.store().asOption(() -> this.parent.store().pool(poolName)).getOrElse(() -> new PoolData(poolName, (Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$)));
      Seq activeStages = (Seq)uiPool.stageIds().toSeq().map((x$2) -> $anonfun$render$7(this, BoxesRunTime.unboxToInt(x$2)));
      StageTableBase activeStagesTable = new StageTableBase(this.parent.store(), request, activeStages, "", "activeStage", this.parent.basePath(), "stages/pool", this.parent.isFairScheduler(), this.parent.killEnabled(), false);
      PoolTable poolTable = new PoolTable((Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(pool), uiPool)}))), this.parent);
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Summary "));
      ObjectRef content = ObjectRef.create((new Elem((String)null, "h4", var10004, var10005, false, var10007.seqToNodeSeq($buf))).$plus$plus(poolTable.toNodeSeq(request)));
      if (activeStages.nonEmpty()) {
         NodeSeq var10001 = (NodeSeq)content.elem;
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var17 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-poolActiveStages',\n            'aggregated-poolActiveStages')"), $md);
         var17 = new UnprefixedAttribute("class", new Text("collapse-aggregated-poolActiveStages collapse-table"), var17);
         TopScope var21 = scala.xml.TopScope..MODULE$;
         NodeSeq var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10015 = scala.xml.Null..MODULE$;
         TopScope var10016 = scala.xml.TopScope..MODULE$;
         NodeSeq var10018 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var19 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var19, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n            "));
         Null var10024 = scala.xml.Null..MODULE$;
         TopScope var10025 = scala.xml.TopScope..MODULE$;
         NodeSeq var10027 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Active Stages ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(activeStages.size()));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var10024, var10025, false, var10027.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "h4", var10015, var10016, false, var10018.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         Elem var10002 = new Elem((String)null, "span", var17, var21, false, var10009.seqToNodeSeq($buf));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var20 = new UnprefixedAttribute("class", new Text("aggregated-poolActiveStages collapsible-table"), $md);
         TopScope var10008 = scala.xml.TopScope..MODULE$;
         NodeSeq var10010 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(activeStagesTable.toNodeSeq());
         $buf.$amp$plus(new Text("\n        "));
         content.elem = var10001.$plus$plus(var10002.$plus$plus(new Elem((String)null, "div", var20, var10008, false, var10010.seqToNodeSeq($buf))));
      }

      return UIUtils$.MODULE$.headerSparkPage(request, "Fair Scheduler Pool: " + poolName, () -> (NodeSeq)content.elem, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
   }

   // $FF: synthetic method
   public static final StageData $anonfun$render$7(final PoolPage $this, final int x$2) {
      return $this.parent.store().lastStageAttempt(x$2);
   }

   public PoolPage(final StagesTab parent) {
      super("pool");
      this.parent = parent;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
