package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.scheduler.TaskLocality$;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.AppStatusUtils$;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.TaskData;
import org.apache.spark.status.api.v1.TaskMetrics;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.util.Utils$;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.StringOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.LongRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.xml.Elem;
import scala.xml.EntityRef;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b!B\u0007\u000f\u0001AA\u0002\u0002C\u000f\u0001\u0005\u0003\u0005\u000b\u0011B\u0010\t\u0011\r\u0002!\u0011!Q\u0001\n\u0011BQA\u000b\u0001\u0005\u0002-Bqa\f\u0001C\u0002\u0013%\u0001\u0007\u0003\u00048\u0001\u0001\u0006I!\r\u0005\bq\u0001\u0011\r\u0011\"\u0003:\u0011\u0019\u0001\u0005\u0001)A\u0005u!9\u0011\t\u0001b\u0001\n\u0013\u0011\u0005B\u0002$\u0001A\u0003%1\tC\u0003H\u0001\u0011%\u0001\nC\u0003]\u0001\u0011\u0005Q\fC\u0003w\u0001\u0011%qOA\u0005Ti\u0006<W\rU1hK*\u0011q\u0002E\u0001\u0005U>\u00147O\u0003\u0002\u0012%\u0005\u0011Q/\u001b\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sON\u0011\u0001!\u0007\t\u00035mi\u0011\u0001E\u0005\u00039A\u0011\u0011bV3c+&\u0003\u0016mZ3\u0002\rA\f'/\u001a8u\u0007\u0001\u0001\"\u0001I\u0011\u000e\u00039I!A\t\b\u0003\u0013M#\u0018mZ3t)\u0006\u0014\u0017!B:u_J,\u0007CA\u0013)\u001b\u00051#BA\u0014\u0013\u0003\u0019\u0019H/\u0019;vg&\u0011\u0011F\n\u0002\u000f\u0003B\u00048\u000b^1ukN\u001cFo\u001c:f\u0003\u0019a\u0014N\\5u}Q\u0019A&\f\u0018\u0011\u0005\u0001\u0002\u0001\"B\u000f\u0004\u0001\u0004y\u0002\"B\u0012\u0004\u0001\u0004!\u0013\u0001\u0005+J\u001b\u0016c\u0015JT#`\u000b:\u000b%\tT#E+\u0005\t\u0004C\u0001\u001a6\u001b\u0005\u0019$\"\u0001\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001a$a\u0002\"p_2,\u0017M\\\u0001\u0012)&kU\tT%O\u000b~+e*\u0011\"M\u000b\u0012\u0003\u0013a\u0004+J\u001b\u0016c\u0015JT#`\u0019\u0016;UI\u0014#\u0016\u0003i\u0002\"a\u000f \u000e\u0003qR!!P\u001a\u0002\u0007alG.\u0003\u0002@y\t!Q\t\\3n\u0003A!\u0016*T#M\u0013:+u\fT#H\u000b:#\u0005%\u0001\nN\u0003b{F+S'F\u0019&sUi\u0018+B'.\u001bV#A\"\u0011\u0005I\"\u0015BA#4\u0005\rIe\u000e^\u0001\u0014\u001b\u0006Cv\fV%N\u000b2Ke*R0U\u0003N[5\u000bI\u0001\u0019O\u0016$Hj\\2bY&$\u0018pU;n[\u0006\u0014\u0018p\u0015;sS:<GCA%U!\tQ\u0015K\u0004\u0002L\u001fB\u0011AjM\u0007\u0002\u001b*\u0011aJH\u0001\u0007yI|w\u000e\u001e \n\u0005A\u001b\u0014A\u0002)sK\u0012,g-\u0003\u0002S'\n11\u000b\u001e:j]\u001eT!\u0001U\u001a\t\u000bUS\u0001\u0019\u0001,\u0002\u001f1|7-\u00197jif\u001cV/\\7bef\u0004BAS,J3&\u0011\u0001l\u0015\u0002\u0004\u001b\u0006\u0004\bC\u0001\u001a[\u0013\tY6G\u0001\u0003M_:<\u0017A\u0002:f]\u0012,'\u000f\u0006\u0002_UB\u0019q\fZ4\u000f\u0005\u0001\u0014gB\u0001'b\u0013\u0005!\u0014BA24\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u001a4\u0003\u0007M+\u0017O\u0003\u0002dgA\u00111\b[\u0005\u0003Sr\u0012AAT8eK\")1n\u0003a\u0001Y\u00069!/Z9vKN$\bCA7u\u001b\u0005q'BA8q\u0003\u0011AG\u000f\u001e9\u000b\u0005E\u0014\u0018aB:feZdW\r\u001e\u0006\u0002g\u00069!.Y6beR\f\u0017BA;o\u0005IAE\u000f\u001e9TKJ4H.\u001a;SKF,Xm\u001d;\u0002\u00195\f7.\u001a+j[\u0016d\u0017N\\3\u0015!yC\u0018QBA\t\u0003+\tI\"!\b\u0002\"\u0005\u0015\u0002\"B=\r\u0001\u0004Q\u0018!\u0003;bg.\u001ch)\u001e8d!\r\u001140`\u0005\u0003yN\u0012\u0011BR;oGRLwN\u001c\u0019\u0011\u0007}#g\u0010E\u0002\u0000\u0003\u0013i!!!\u0001\u000b\t\u0005\r\u0011QA\u0001\u0003mFR1!a\u0002'\u0003\r\t\u0007/[\u0005\u0005\u0003\u0017\t\tA\u0001\u0005UCN\\G)\u0019;b\u0011\u0019\ty\u0001\u0004a\u00013\u0006Y1-\u001e:sK:$H+[7f\u0011\u0019\t\u0019\u0002\u0004a\u0001\u0007\u0006!\u0001/Y4f\u0011\u0019\t9\u0002\u0004a\u0001\u0007\u0006A\u0001/Y4f'&TX\r\u0003\u0004\u0002\u001c1\u0001\raQ\u0001\u000bi>$\u0018\r\u001c)bO\u0016\u001c\bBBA\u0010\u0019\u0001\u00071)A\u0004ti\u0006<W-\u00133\t\r\u0005\rB\u00021\u0001D\u00039\u0019H/Y4f\u0003R$X-\u001c9u\u0013\u0012Da!a\n\r\u0001\u0004\u0019\u0015A\u0003;pi\u0006dG+Y:lg\u0002"
)
public class StagePage extends WebUIPage {
   private final StagesTab parent;
   private final AppStatusStore store;
   private final boolean TIMELINE_ENABLED;
   private final Elem TIMELINE_LEGEND;
   private final int MAX_TIMELINE_TASKS;

   private boolean TIMELINE_ENABLED() {
      return this.TIMELINE_ENABLED;
   }

   private Elem TIMELINE_LEGEND() {
      return this.TIMELINE_LEGEND;
   }

   private int MAX_TIMELINE_TASKS() {
      return this.MAX_TIMELINE_TASKS;
   }

   private String getLocalitySummaryString(final Map localitySummary) {
      Map names = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(TaskLocality$.MODULE$.PROCESS_LOCAL().toString()), "Process local"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(TaskLocality$.MODULE$.NODE_LOCAL().toString()), "Node local"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(TaskLocality$.MODULE$.RACK_LOCAL().toString()), "Rack local"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(TaskLocality$.MODULE$.ANY().toString()), "Any")})));
      Seq localityNamesAndCounts = ((IterableOnceOps)names.flatMap((x0$1) -> {
         if (x0$1 != null) {
            String key = (String)x0$1._1();
            String name = (String)x0$1._2();
            return localitySummary.get(key).map((count) -> $anonfun$getLocalitySummaryString$2(name, BoxesRunTime.unboxToLong(count)));
         } else {
            throw new MatchError(x0$1);
         }
      })).toSeq();
      return ((IterableOnceOps)localityNamesAndCounts.sorted(scala.math.Ordering.String..MODULE$)).mkString("; ");
   }

   public Seq render(final HttpServletRequest request) {
      Object var3 = new Object();

      Seq var10000;
      try {
         String parameterId = request.getParameter("id");
         .MODULE$.require(parameterId != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(parameterId)), () -> "Missing id parameter");
         String parameterAttempt = request.getParameter("attempt");
         .MODULE$.require(parameterAttempt != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(parameterAttempt)), () -> "Missing attempt parameter");
         String parameterTaskSortColumn = request.getParameter("task.sort");
         String parameterTaskSortDesc = request.getParameter("task.desc");
         String parameterTaskPageSize = request.getParameter("task.pageSize");
         String eventTimelineParameterTaskPage = request.getParameter("task.eventTimelinePageNumber");
         String eventTimelineParameterTaskPageSize = request.getParameter("task.eventTimelinePageSize");
         IntRef eventTimelineTaskPage = IntRef.create(BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(eventTimelineParameterTaskPage).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$render$3(x$1))).getOrElse((JFunction0.mcI.sp)() -> 1)));
         IntRef eventTimelineTaskPageSize = IntRef.create(BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(eventTimelineParameterTaskPageSize).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$render$5(x$2))).getOrElse((JFunction0.mcI.sp)() -> 100)));
         String taskSortColumn = (String)scala.Option..MODULE$.apply(parameterTaskSortColumn).map((sortColumn) -> UIUtils$.MODULE$.decodeURLParameter(sortColumn)).getOrElse(() -> "Index");
         boolean taskSortDesc = BoxesRunTime.unboxToBoolean(scala.Option..MODULE$.apply(parameterTaskSortDesc).map((x$3x) -> BoxesRunTime.boxToBoolean($anonfun$render$9(x$3x))).getOrElse((JFunction0.mcZ.sp)() -> false));
         int taskPageSize = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(parameterTaskPageSize).map((x$4x) -> BoxesRunTime.boxToInteger($anonfun$render$11(x$4x))).getOrElse((JFunction0.mcI.sp)() -> 100));
         int stageId = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(parameterId));
         int stageAttemptId = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(parameterAttempt));
         String stageHeader = "Details for Stage " + stageId + " (Attempt " + stageAttemptId + ")";
         Tuple2 var20 = (Tuple2)this.parent.store().asOption(() -> this.parent.store().stageAttempt(stageId, stageAttemptId, false, this.parent.store().stageAttempt$default$4(), this.parent.store().stageAttempt$default$5(), this.parent.store().stageAttempt$default$6())).getOrElse(() -> {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var10 = new UnprefixedAttribute("id", new Text("no-info"), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            Null var10013 = scala.xml.Null..MODULE$;
            TopScope var10014 = scala.xml.TopScope..MODULE$;
            NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("No information to display for Stage "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(stageId));
            $buf.$amp$plus(new Text(" (Attempt "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(stageAttemptId));
            $buf.$amp$plus(new Text(")"));
            $buf.$amp$plus(new Elem((String)null, "p", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            Elem content = new Elem((String)null, "div", var10, var10005, false, var10007.seqToNodeSeq($buf));
            throw new NonLocalReturnControl(var3, UIUtils$.MODULE$.headerSparkPage(request, stageHeader, () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7()));
         });
         if (var20 == null) {
            throw new MatchError(var20);
         }

         StageData stageData = (StageData)var20._1();
         Seq stageJobIds = (Seq)var20._2();
         Tuple2 var19 = new Tuple2(stageData, stageJobIds);
         StageData stageData = (StageData)var19._1();
         Seq stageJobIds = (Seq)var19._2();
         Map localitySummary = this.store.localitySummary(stageData.stageId(), stageData.attemptId());
         int totalTasks = stageData.numActiveTasks() + stageData.numCompleteTasks() + stageData.numFailedTasks() + stageData.numKilledTasks();
         if (totalTasks == 0) {
            Null var91 = scala.xml.Null..MODULE$;
            TopScope var92 = scala.xml.TopScope..MODULE$;
            NodeSeq var93 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var10013 = scala.xml.Null..MODULE$;
            TopScope var95 = scala.xml.TopScope..MODULE$;
            NodeSeq var97 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Summary Metrics"));
            $buf.$amp$plus(new Elem((String)null, "h4", var10013, var95, false, var97.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text(" No tasks have started yet\n          "));
            var10013 = scala.xml.Null..MODULE$;
            var95 = scala.xml.TopScope..MODULE$;
            var97 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Tasks"));
            $buf.$amp$plus(new Elem((String)null, "h4", var10013, var95, false, var97.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text(" No tasks have started yet\n        "));
            Elem content = new Elem((String)null, "div", var91, var92, false, var93.seqToNodeSeq($buf));
            return UIUtils$.MODULE$.headerSparkPage(request, stageHeader, () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
         }

         if (eventTimelineTaskPageSize.elem < 1 || eventTimelineTaskPageSize.elem > totalTasks) {
            eventTimelineTaskPageSize.elem = totalTasks;
         }

         int eventTimelineTotalPages = (totalTasks + eventTimelineTaskPageSize.elem - 1) / eventTimelineTaskPageSize.elem;
         if (eventTimelineTaskPage.elem < 1 || eventTimelineTaskPage.elem > eventTimelineTotalPages) {
            eventTimelineTaskPage.elem = 1;
         }

         Elem var87 = new Elem;
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var79 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
         Elem var10009 = new Elem;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10022 = scala.xml.Null..MODULE$;
         TopScope var10023 = scala.xml.TopScope..MODULE$;
         NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         Null var10031 = scala.xml.Null..MODULE$;
         TopScope var10032 = scala.xml.TopScope..MODULE$;
         NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Resource Profile Id: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(stageData.resourceProfileId()));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         var10031 = scala.xml.Null..MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Total Time Across All Tasks: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(UIUtils$.MODULE$.formatDuration(stageData.executorRunTime()));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         var10031 = scala.xml.Null..MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Locality Level Summary: "));
         $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(this.getLocalitySummaryString(localitySummary));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         Object var10018;
         if (ApiHelper$.MODULE$.hasInput(stageData)) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            var10031 = scala.xml.Null..MODULE$;
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Input Size / Records: "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            String var10027 = Utils$.MODULE$.bytesToString(stageData.inputBytes());
            $buf.$amp$plus(var10027 + " / " + stageData.inputRecords());
            $buf.$amp$plus(new Text("\n            "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (ApiHelper$.MODULE$.hasOutput(stageData)) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            var10031 = scala.xml.Null..MODULE$;
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Output Size / Records: "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            String var129 = Utils$.MODULE$.bytesToString(stageData.outputBytes());
            $buf.$amp$plus(var129 + " / " + stageData.outputRecords());
            $buf.$amp$plus(new Text("\n            "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (ApiHelper$.MODULE$.hasShuffleRead(stageData)) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            var10031 = scala.xml.Null..MODULE$;
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Shuffle Read Size / Records: "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            String var130 = Utils$.MODULE$.bytesToString(stageData.shuffleReadBytes());
            $buf.$amp$plus(var130 + " / " + stageData.shuffleReadRecords());
            $buf.$amp$plus(new Text("\n            "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (ApiHelper$.MODULE$.hasShuffleWrite(stageData)) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            var10031 = scala.xml.Null..MODULE$;
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Shuffle Write Size / Records: "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n               "));
            String var131 = Utils$.MODULE$.bytesToString(stageData.shuffleWriteBytes());
            $buf.$amp$plus(var131 + " / " + stageData.shuffleWriteRecords());
            $buf.$amp$plus(new Text("\n            "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (ApiHelper$.MODULE$.hasBytesSpilled(stageData)) {
            NodeBuffer $buf = new NodeBuffer();
            Null var117 = scala.xml.Null..MODULE$;
            TopScope var10024 = scala.xml.TopScope..MODULE$;
            NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            Null var145 = scala.xml.Null..MODULE$;
            TopScope var10033 = scala.xml.TopScope..MODULE$;
            NodeSeq var10035 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Spill (Memory): "));
            $buf.$amp$plus(new Elem((String)null, "strong", var145, var10033, false, var10035.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(Utils$.MODULE$.bytesToString(stageData.memoryBytesSpilled()));
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(new Elem((String)null, "li", var117, var10024, false, var10026.seqToNodeSeq($buf)));
            var117 = scala.xml.Null..MODULE$;
            var10024 = scala.xml.TopScope..MODULE$;
            var10026 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            var145 = scala.xml.Null..MODULE$;
            var10033 = scala.xml.TopScope..MODULE$;
            var10035 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Spill (Disk): "));
            $buf.$amp$plus(new Elem((String)null, "strong", var145, var10033, false, var10035.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(Utils$.MODULE$.bytesToString(stageData.diskBytesSpilled()));
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(new Elem((String)null, "li", var117, var10024, false, var10026.seqToNodeSeq($buf)));
            var10018 = $buf;
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (!stageJobIds.isEmpty()) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            var10031 = scala.xml.Null..MODULE$;
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Associated Job Ids: "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(((IterableOps)stageJobIds.sorted(scala.math.Ordering.Int..MODULE$)).map((jobId) -> $anonfun$render$17(this, request, BoxesRunTime.unboxToInt(jobId))));
            $buf.$amp$plus(new Text("\n            "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n        "));
         var10009.<init>((String)null, "ul", var79, var10014, false, var10016.seqToNodeSeq($buf));
         $buf.$amp$plus(var10009);
         $buf.$amp$plus(new Text("\n      "));
         var87.<init>((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         Elem summary = var87;
         Option stageGraph = this.parent.store().asOption(() -> this.parent.store().operationGraphForStage(stageId));
         Seq dagViz = UIUtils$.MODULE$.showDagVizForStage(stageId, stageGraph);
         long currentTime = System.currentTimeMillis();
         StringOps var88 = scala.collection.StringOps..MODULE$;
         Predef var10001 = .MODULE$;
         String var10002 = UIUtils$.MODULE$.formatImportJavaScript(request, "/static/stagepage.js", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"setTaskThreadDumpEnabled"})));
         String js = var88.stripMargin$extension(var10001.augmentString("\n         |" + var10002 + "\n         |\n         |setTaskThreadDumpEnabled(" + this.parent.threadDumpEnabled() + ");\n         |"));
         NodeSeq var89 = summary.$plus$plus(dagViz);
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var80 = new UnprefixedAttribute("id", new Text("showAdditionalMetrics"), $md);
         var89 = var89.$plus$plus(new Elem((String)null, "div", var80, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$)).$plus$plus(this.makeTimeline(() -> {
            int from = (eventTimelineTaskPage.elem - 1) * eventTimelineTaskPageSize.elem;
            int dataSize = (int)this.store.taskCount(stageData.stageId(), stageData.attemptId());
            int to = scala.runtime.RichInt..MODULE$.min$extension(.MODULE$.intWrapper(dataSize), eventTimelineTaskPage.elem * eventTimelineTaskPageSize.elem);
            Seq sliceData = this.store.taskList(stageData.stageId(), stageData.attemptId(), from, to - from, ApiHelper$.MODULE$.indexName(taskSortColumn), !taskSortDesc, this.store.taskList$default$7());
            return sliceData;
         }, currentTime, eventTimelineTaskPage.elem, eventTimelineTaskPageSize.elem, eventTimelineTotalPages, stageId, stageAttemptId, totalTasks));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var81 = new UnprefixedAttribute("id", new Text("parent-container"), $md);
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var82 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/utils.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
         var82 = new UnprefixedAttribute("type", new Text("module"), var82);
         $buf.$amp$plus(new Elem((String)null, "script", var82, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var84 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/stagepage.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
         var84 = new UnprefixedAttribute("type", new Text("module"), var84);
         $buf.$amp$plus(new Elem((String)null, "script", var84, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var86 = new UnprefixedAttribute("type", new Text("module"), $md);
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(js));
         $buf.$amp$plus(new Elem((String)null, "script", var86, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         NodeSeq content = var89.$plus$plus(new Elem((String)null, "div", var81, var10006, false, var10008.seqToNodeSeq($buf)));
         Function0 x$3 = () -> content;
         StagesTab x$4 = this.parent;
         boolean x$5 = true;
         boolean x$6 = true;
         Option x$7 = UIUtils$.MODULE$.headerSparkPage$default$5();
         var10000 = UIUtils$.MODULE$.headerSparkPage(request, stageHeader, x$3, x$4, x$7, true, true);
      } catch (NonLocalReturnControl var78) {
         if (var78.key() != var3) {
            throw var78;
         }

         var10000 = (Seq)var78.value();
      }

      return var10000;
   }

   private Seq makeTimeline(final Function0 tasksFunc, final long currentTime, final int page, final int pageSize, final int totalPages, final int stageId, final int stageAttemptId, final int totalTasks) {
      if (!this.TIMELINE_ENABLED()) {
         return (Seq)scala.package..MODULE$.Seq().empty();
      } else {
         Seq tasks = (Seq)tasksFunc.apply();
         HashSet executorsSet = new HashSet();
         LongRef minLaunchTime = LongRef.create(Long.MAX_VALUE);
         LongRef maxFinishTime = LongRef.create(Long.MIN_VALUE);
         String executorsArrayStr = ((IterableOnceOps)((IterableOps)((IterableOps)tasks.sortBy((x$6) -> BoxesRunTime.boxToLong($anonfun$makeTimeline$1(x$6)), scala.math.Ordering.Long..MODULE$)).take(this.MAX_TIMELINE_TASKS())).map((taskInfo) -> {
            String executorId = taskInfo.executorId();
            String host = taskInfo.host();
            executorsSet.$plus$eq(new Tuple2(executorId, host));
            long launchTime = taskInfo.launchTime().getTime();
            long finishTime = BoxesRunTime.unboxToLong(taskInfo.duration().map((JFunction1.mcJJ.sp)(x$7) -> taskInfo.launchTime().getTime() + x$7).getOrElse((JFunction0.mcJ.sp)() -> currentTime));
            long totalExecutionTime = finishTime - launchTime;
            minLaunchTime.elem = scala.runtime.RichLong..MODULE$.min$extension(.MODULE$.longWrapper(launchTime), minLaunchTime.elem);
            maxFinishTime.elem = scala.runtime.RichLong..MODULE$.max$extension(.MODULE$.longWrapper(finishTime), maxFinishTime.elem);
            Option metricsOpt = taskInfo.taskMetrics();
            long shuffleReadTime = BoxesRunTime.unboxToLong(metricsOpt.map((x$8) -> BoxesRunTime.boxToLong($anonfun$makeTimeline$5(x$8))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
            double shuffleReadTimeProportion = toProportion$1(shuffleReadTime, totalExecutionTime);
            long shuffleWriteTime = (long)((double)BoxesRunTime.unboxToLong(metricsOpt.map((x$9) -> BoxesRunTime.boxToLong($anonfun$makeTimeline$7(x$9))).getOrElse((JFunction0.mcJ.sp)() -> 0L)) / (double)1000000.0F);
            double shuffleWriteTimeProportion = toProportion$1(shuffleWriteTime, totalExecutionTime);
            long serializationTime = BoxesRunTime.unboxToLong(metricsOpt.map((x$10) -> BoxesRunTime.boxToLong($anonfun$makeTimeline$9(x$10))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
            double serializationTimeProportion = toProportion$1(serializationTime, totalExecutionTime);
            long deserializationTime = BoxesRunTime.unboxToLong(metricsOpt.map((x$11) -> BoxesRunTime.boxToLong($anonfun$makeTimeline$11(x$11))).getOrElse((JFunction0.mcJ.sp)() -> 0L));
            double deserializationTimeProportion = toProportion$1(deserializationTime, totalExecutionTime);
            long gettingResultTime = AppStatusUtils$.MODULE$.gettingResultTime(taskInfo);
            double gettingResultTimeProportion = toProportion$1(gettingResultTime, totalExecutionTime);
            long schedulerDelay = AppStatusUtils$.MODULE$.schedulerDelay(taskInfo);
            double schedulerDelayProportion = toProportion$1(schedulerDelay, totalExecutionTime);
            long executorOverhead = serializationTime + deserializationTime;
            long executorRunTime = taskInfo.duration().isDefined() ? scala.math.package..MODULE$.max(totalExecutionTime - executorOverhead - gettingResultTime - schedulerDelay, 0L) : BoxesRunTime.unboxToLong(metricsOpt.map((x$12) -> BoxesRunTime.boxToLong($anonfun$makeTimeline$13(x$12))).getOrElse((JFunction0.mcJ.sp)() -> scala.math.package..MODULE$.max(totalExecutionTime - executorOverhead - gettingResultTime - schedulerDelay, 0L)));
            long executorComputingTime = executorRunTime - shuffleReadTime - shuffleWriteTime;
            double executorComputingTimeProportion = scala.math.package..MODULE$.max((double)100 - schedulerDelayProportion - shuffleReadTimeProportion - shuffleWriteTimeProportion - serializationTimeProportion - deserializationTimeProportion - gettingResultTimeProportion, (double)0.0F);
            int schedulerDelayProportionPos = 0;
            double deserializationTimeProportionPos = (double)schedulerDelayProportionPos + schedulerDelayProportion;
            double shuffleReadTimeProportionPos = deserializationTimeProportionPos + deserializationTimeProportion;
            double executorRuntimeProportionPos = shuffleReadTimeProportionPos + shuffleReadTimeProportion;
            double shuffleWriteTimeProportionPos = executorRuntimeProportionPos + executorComputingTimeProportion;
            double serializationTimeProportionPos = shuffleWriteTimeProportionPos + shuffleWriteTimeProportion;
            double gettingResultTimeProportionPos = serializationTimeProportionPos + serializationTimeProportion;
            int index = taskInfo.index();
            int attempt = taskInfo.attempt();
            String svgTag = totalExecutionTime == 0L ? "<svg class=\"task-assignment-timeline-duration-bar\"></svg>" : scala.collection.StringOps..MODULE$.stripMargin$extension(.MODULE$.augmentString("<svg class=\"task-assignment-timeline-duration-bar\">\n                 |<rect class=\"scheduler-delay-proportion\"\n                   |x=\"" + schedulerDelayProportionPos + "%\" y=\"0px\" height=\"26px\"\n                   |width=\"" + schedulerDelayProportion + "%\"></rect>\n                 |<rect class=\"deserialization-time-proportion\"\n                   |x=\"" + deserializationTimeProportionPos + "%\" y=\"0px\" height=\"26px\"\n                   |width=\"" + deserializationTimeProportion + "%\"></rect>\n                 |<rect class=\"shuffle-read-time-proportion\"\n                   |x=\"" + shuffleReadTimeProportionPos + "%\" y=\"0px\" height=\"26px\"\n                   |width=\"" + shuffleReadTimeProportion + "%\"></rect>\n                 |<rect class=\"executor-runtime-proportion\"\n                   |x=\"" + executorRuntimeProportionPos + "%\" y=\"0px\" height=\"26px\"\n                   |width=\"" + executorComputingTimeProportion + "%\"></rect>\n                 |<rect class=\"shuffle-write-time-proportion\"\n                   |x=\"" + shuffleWriteTimeProportionPos + "%\" y=\"0px\" height=\"26px\"\n                   |width=\"" + shuffleWriteTimeProportion + "%\"></rect>\n                 |<rect class=\"serialization-time-proportion\"\n                   |x=\"" + serializationTimeProportionPos + "%\" y=\"0px\" height=\"26px\"\n                   |width=\"" + serializationTimeProportion + "%\"></rect>\n                 |<rect class=\"getting-result-time-proportion\"\n                   |x=\"" + gettingResultTimeProportionPos + "%\" y=\"0px\" height=\"26px\"\n                   |width=\"" + gettingResultTimeProportion + "%\"></rect></svg>"));
            StringOps var10000 = scala.collection.StringOps..MODULE$;
            Predef var10001 = .MODULE$;
            String var10005 = taskInfo.status();
            String var10006 = UIUtils$.MODULE$.formatDate(new Date(launchTime));
            String var64;
            if (taskInfo.duration().isEmpty()) {
               UIUtils$ var10007 = UIUtils$.MODULE$;
               var64 = "<br>Finish Time: " + var10007.formatDate(new Date(finishTime));
            } else {
               var64 = "";
            }

            String timelineObject = var10000.stripMargin$extension(var10001.augmentString("\n             |{\n               |'className': 'task task-assignment-timeline-object',\n               |'group': '" + executorId + "',\n               |'content': '<div class=\"task-assignment-timeline-content\"\n                 |data-toggle=\"tooltip\" data-placement=\"top\"\n                 |data-html=\"true\" data-container=\"body\"\n                 |data-title=\"Task " + index + " (attempt " + attempt + ")<br>\n                 |Status: " + var10005 + "<br>\n                 |Launch Time: " + var10006 + "\n                 |" + var64 + "\n                 |<br>Scheduler Delay: " + schedulerDelay + " ms\n                 |<br>Task Deserialization Time: " + UIUtils$.MODULE$.formatDuration(deserializationTime) + "\n                 |<br>Shuffle Read Time: " + UIUtils$.MODULE$.formatDuration(shuffleReadTime) + "\n                 |<br>Executor Computing Time: " + UIUtils$.MODULE$.formatDuration(executorComputingTime) + "\n                 |<br>Shuffle Write Time: " + UIUtils$.MODULE$.formatDuration(shuffleWriteTime) + "\n                 |<br>Result Serialization Time: " + UIUtils$.MODULE$.formatDuration(serializationTime) + "\n                 |<br>Getting Result Time: " + UIUtils$.MODULE$.formatDuration(gettingResultTime) + "\">\n                 |" + svgTag + "',\n               |'start': new Date(" + launchTime + "),\n               |'end': new Date(" + finishTime + ")\n             |}\n           |")).replaceAll("[\\r\\n]+", " ");
            return timelineObject;
         })).mkString("[", ",", "]");
         String groupArrayStr = ((IterableOnceOps)executorsSet.map((x0$1) -> {
            if (x0$1 != null) {
               String executorId = (String)x0$1._1();
               String host = (String)x0$1._2();
               return "\n            {\n              'id': '" + executorId + "',\n              'content': '" + executorId + " / " + host + "',\n            }\n          ";
            } else {
               throw new MatchError(x0$1);
            }
         })).mkString("[", ",", "]");
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var43 = new UnprefixedAttribute("class", new Text("expand-task-assignment-timeline"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var44 = new UnprefixedAttribute("class", new Text("expand-task-assignment-timeline-arrow arrow-closed"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var44, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n      "));
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Event Timeline"));
         $buf.$amp$plus(new Elem((String)null, "a", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n    "));
         Elem var10000 = new Elem((String)null, "span", var43, var10005, false, var10007.seqToNodeSeq($buf));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var45 = new UnprefixedAttribute("class", new Text("collapsed"), $md);
         var45 = new UnprefixedAttribute("id", new Text("task-assignment-timeline"), var45);
         Elem var10001 = new Elem;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         Object var10010;
         if (this.MAX_TIMELINE_TASKS() < tasks.size()) {
            Null var77 = scala.xml.Null..MODULE$;
            TopScope var10015 = scala.xml.TopScope..MODULE$;
            NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            Only the most recent "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(this.MAX_TIMELINE_TASKS()));
            $buf.$amp$plus(new Text(" tasks\n            (of "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(tasks.size()));
            $buf.$amp$plus(new Text(" total) are shown.\n          "));
            var10010 = new Elem((String)null, "strong", var77, var10015, false, var10017.seqToNodeSeq($buf));
         } else {
            var10010 = scala.package..MODULE$.Seq().empty();
         }

         $buf.$amp$plus(var10010);
         $buf.$amp$plus(new Text("\n      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var47 = new UnprefixedAttribute("class", new Text("control-panel"), $md);
         TopScope var78 = scala.xml.TopScope..MODULE$;
         NodeSeq var79 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var48 = new UnprefixedAttribute("id", new Text("task-assignment-timeline-zoom-lock"), $md);
         TopScope var10024 = scala.xml.TopScope..MODULE$;
         NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var49 = new UnprefixedAttribute("type", new Text("checkbox"), $md);
         $buf.$amp$plus(new Elem((String)null, "input", var49, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n          "));
         Null var10032 = scala.xml.Null..MODULE$;
         TopScope var10033 = scala.xml.TopScope..MODULE$;
         NodeSeq var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Enable zooming"));
         $buf.$amp$plus(new Elem((String)null, "span", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "div", var48, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         Null var10023 = scala.xml.Null..MODULE$;
         var10024 = scala.xml.TopScope..MODULE$;
         var10026 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var50 = new UnprefixedAttribute("style", new Text("margin-bottom: 0px;"), $md);
         var50 = new UnprefixedAttribute("class", new Text("form-inline float-right justify-content-end"), var50);
         var50 = new UnprefixedAttribute("action", scala.collection.immutable.Nil..MODULE$, var50);
         var50 = new UnprefixedAttribute("method", new Text("get"), var50);
         var50 = new UnprefixedAttribute("id", "form-event-timeline-page", var50);
         var10033 = scala.xml.TopScope..MODULE$;
         var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         Null var10041 = scala.xml.Null..MODULE$;
         TopScope var10042 = scala.xml.TopScope..MODULE$;
         NodeSeq var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Tasks: "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(totalTasks));
         $buf.$amp$plus(new Text(". "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(totalPages));
         $buf.$amp$plus(new Text(" Pages. Jump to"));
         $buf.$amp$plus(new Elem((String)null, "label", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var55 = new UnprefixedAttribute("value", Integer.toString(stageId), $md);
         var55 = new UnprefixedAttribute("name", new Text("id"), var55);
         var55 = new UnprefixedAttribute("type", new Text("hidden"), var55);
         $buf.$amp$plus(new Elem((String)null, "input", var55, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var58 = new UnprefixedAttribute("value", Integer.toString(stageAttemptId), $md);
         var58 = new UnprefixedAttribute("name", new Text("attempt"), var58);
         var58 = new UnprefixedAttribute("type", new Text("hidden"), var58);
         $buf.$amp$plus(new Elem((String)null, "input", var58, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var61 = new UnprefixedAttribute("class", new Text("col-1 form-control"), $md);
         var61 = new UnprefixedAttribute("value", Integer.toString(page), var61);
         var61 = new UnprefixedAttribute("id", "form-event-timeline-page-no", var61);
         var61 = new UnprefixedAttribute("name", new Text("task.eventTimelinePageNumber"), var61);
         var61 = new UnprefixedAttribute("type", new Text("text"), var61);
         $buf.$amp$plus(new Elem((String)null, "input", var61, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n\n            "));
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text(". Show "));
         $buf.$amp$plus(new Elem((String)null, "label", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var66 = new UnprefixedAttribute("class", new Text("col-1 form-control"), $md);
         var66 = new UnprefixedAttribute("value", Integer.toString(pageSize), var66);
         var66 = new UnprefixedAttribute("name", new Text("task.eventTimelinePageSize"), var66);
         var66 = new UnprefixedAttribute("id", "form-event-timeline-page-size", var66);
         var66 = new UnprefixedAttribute("type", new Text("text"), var66);
         $buf.$amp$plus(new Elem((String)null, "input", var66, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n            "));
         var10041 = scala.xml.Null..MODULE$;
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("items in a page."));
         $buf.$amp$plus(new Elem((String)null, "label", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var71 = new UnprefixedAttribute("class", new Text("btn btn-spark"), $md);
         var71 = new UnprefixedAttribute("type", new Text("submit"), var71);
         var10042 = scala.xml.TopScope..MODULE$;
         var10044 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Go"));
         $buf.$amp$plus(new Elem((String)null, "button", var71, var10042, false, var10044.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "form", var50, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "div", var10023, var10024, false, var10026.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         $buf.$amp$plus(new Elem((String)null, "div", var47, var78, false, var79.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         $buf.$amp$plus(this.TIMELINE_LEGEND());
         $buf.$amp$plus(new Text("\n    "));
         var10001.<init>((String)null, "div", var45, var10006, false, var10008.seqToNodeSeq($buf));
         NodeSeq var74 = var10000.$plus$plus(var10001);
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var73 = new UnprefixedAttribute("type", new Text("text/javascript"), $md);
         var10006 = scala.xml.TopScope..MODULE$;
         var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply("drawTaskAssignmentTimeline(" + groupArrayStr + ", " + executorsArrayStr + ", " + minLaunchTime.elem + ", " + maxFinishTime.elem + ", " + UIUtils$.MODULE$.getTimeZoneOffset() + ")"));
         $buf.$amp$plus(new Text("\n    "));
         return var74.$plus$plus(new Elem((String)null, "script", var73, var10006, false, var10008.seqToNodeSeq($buf)));
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$getLocalitySummaryString$2(final String name$1, final long count) {
      return name$1 + ": " + count;
   }

   // $FF: synthetic method
   public static final int $anonfun$render$3(final String x$1) {
      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final int $anonfun$render$5(final String x$2) {
      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(x$2));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$9(final String x$3) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(.MODULE$.augmentString(x$3));
   }

   // $FF: synthetic method
   public static final int $anonfun$render$11(final String x$4) {
      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(x$4));
   }

   // $FF: synthetic method
   public static final NodeBuffer $anonfun$render$17(final StagePage $this, final HttpServletRequest request$1, final int jobId) {
      String jobURL = scala.collection.StringOps..MODULE$.format$extension(.MODULE$.augmentString("%s/jobs/job/?id=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{UIUtils$.MODULE$.prependBaseUri(request$1, $this.parent.basePath(), UIUtils$.MODULE$.prependBaseUri$default$3()), BoxesRunTime.boxToInteger(jobId)}));
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var8 = new UnprefixedAttribute("href", jobURL, $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(Integer.toString(jobId));
      $buf.$amp$plus(new Elem((String)null, "a", var8, var10006, false, var10008.seqToNodeSeq($buf)));
      Null var10005 = scala.xml.Null..MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new EntityRef("nbsp"));
      $buf.$amp$plus(new Elem((String)null, "span", var10005, var10006, false, var10008.seqToNodeSeq($buf)));
      return $buf;
   }

   // $FF: synthetic method
   public static final long $anonfun$makeTimeline$1(final TaskData x$6) {
      return -x$6.launchTime().getTime();
   }

   private static final double toProportion$1(final long time, final long totalExecutionTime$1) {
      return (double)time / (double)totalExecutionTime$1 * (double)100;
   }

   // $FF: synthetic method
   public static final long $anonfun$makeTimeline$5(final TaskMetrics x$8) {
      return x$8.shuffleReadMetrics().fetchWaitTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$makeTimeline$7(final TaskMetrics x$9) {
      return x$9.shuffleWriteMetrics().writeTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$makeTimeline$9(final TaskMetrics x$10) {
      return x$10.resultSerializationTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$makeTimeline$11(final TaskMetrics x$11) {
      return x$11.executorDeserializeTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$makeTimeline$13(final TaskMetrics x$12) {
      return x$12.executorRunTime();
   }

   public StagePage(final StagesTab parent, final AppStatusStore store) {
      super("stage");
      this.parent = parent;
      this.store = store;
      this.TIMELINE_ENABLED = BoxesRunTime.unboxToBoolean(parent.conf().get(UI$.MODULE$.UI_TIMELINE_ENABLED()));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var7 = new UnprefixedAttribute("class", new Text("legend-area"), $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10014 = scala.xml.Null..MODULE$;
      TopScope var10015 = scala.xml.TopScope..MODULE$;
      NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      List legendPairs = new scala.collection.immutable..colon.colon(new Tuple2("scheduler-delay-proportion", "Scheduler Delay"), new scala.collection.immutable..colon.colon(new Tuple2("deserialization-time-proportion", "Task Deserialization Time"), new scala.collection.immutable..colon.colon(new Tuple2("shuffle-read-time-proportion", "Shuffle Read Time"), new scala.collection.immutable..colon.colon(new Tuple2("executor-runtime-proportion", "Executor Computing Time"), new scala.collection.immutable..colon.colon(new Tuple2("shuffle-write-time-proportion", "Shuffle Write Time"), new scala.collection.immutable..colon.colon(new Tuple2("serialization-time-proportion", "Result Serialization Time"), new scala.collection.immutable..colon.colon(new Tuple2("getting-result-time-proportion", "Getting Result Time"), scala.collection.immutable.Nil..MODULE$)))))));
      $buf.$amp$plus(((List)legendPairs.zipWithIndex()).map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var3 = (Tuple2)x0$1._1();
            int index = x0$1._2$mcI$sp();
            if (var3 != null) {
               String classAttr = (String)var3._1();
               String name = (String)var3._2();
               NodeBuffer $buf = new NodeBuffer();
               MetaData $md = scala.xml.Null..MODULE$;
               MetaData var11 = new UnprefixedAttribute("class", classAttr, $md);
               var11 = new UnprefixedAttribute("height", new Text("10px"), var11);
               var11 = new UnprefixedAttribute("width", new Text("10px"), var11);
               var11 = new UnprefixedAttribute("y", 10 + index % 3 * 15 + "px", var11);
               var11 = new UnprefixedAttribute("x", 5 + index / 3 * 210 + "px", var11);
               $buf.$amp$plus(new Elem((String)null, "rect", var11, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
               MetaData $md = scala.xml.Null..MODULE$;
               MetaData var16 = new UnprefixedAttribute("y", 20 + index % 3 * 15 + "px", $md);
               var16 = new UnprefixedAttribute("x", 25 + index / 3 * 210 + "px", var16);
               TopScope var10006 = scala.xml.TopScope..MODULE$;
               NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               $buf.$amp$plus(name);
               $buf.$amp$plus(new Elem((String)null, "text", var16, var10006, false, var10008.seqToNodeSeq($buf)));
               return $buf;
            }
         }

         throw new MatchError(x0$1);
      }));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "svg", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      this.TIMELINE_LEGEND = new Elem((String)null, "div", var7, var10006, false, var10008.seqToNodeSeq($buf));
      this.MAX_TIMELINE_TASKS = BoxesRunTime.unboxToInt(parent.conf().get(UI$.MODULE$.UI_TIMELINE_TASKS_MAXIMUM()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
