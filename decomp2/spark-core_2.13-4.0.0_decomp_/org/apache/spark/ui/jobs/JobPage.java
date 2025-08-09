package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.spark.JobExecutionStatus;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ExecutorSummary;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.StageStatus;
import org.apache.spark.ui.ToolTips$;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.StringOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.ListBuffer;
import scala.math.Ordering.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b!\u0002\n\u0014\u0001Ui\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0013\t\u0011!\u0002!\u0011!Q\u0001\n%BQa\f\u0001\u0005\u0002ABq\u0001\u000e\u0001C\u0002\u0013%Q\u0007\u0003\u0004=\u0001\u0001\u0006IA\u000e\u0005\b{\u0001\u0011\r\u0011\"\u0003?\u0011\u0019\u0011\u0005\u0001)A\u0005\u007f!91\t\u0001b\u0001\n\u0013q\u0004B\u0002#\u0001A\u0003%q\bC\u0004F\u0001\t\u0007I\u0011\u0002$\t\rI\u0003\u0001\u0015!\u0003H\u0011\u001d\u0019\u0006A1A\u0005\n\u0019Ca\u0001\u0016\u0001!\u0002\u00139\u0005\"B+\u0001\t\u00131\u0006\"B6\u0001\t\u0003a\u0007\"B:\u0001\t\u0013!\bbBA\u0005\u0001\u0011\u0005\u00111\u0002\u0002\b\u0015>\u0014\u0007+Y4f\u0015\t!R#\u0001\u0003k_\n\u001c(B\u0001\f\u0018\u0003\t)\u0018N\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h'\t\u0001a\u0004\u0005\u0002 A5\tQ#\u0003\u0002\"+\tIq+\u001a2V\u0013B\u000bw-Z\u0001\u0007a\u0006\u0014XM\u001c;\u0004\u0001A\u0011QEJ\u0007\u0002'%\u0011qe\u0005\u0002\b\u0015>\u00147\u000fV1c\u0003\u0015\u0019Ho\u001c:f!\tQS&D\u0001,\u0015\tas#\u0001\u0004ti\u0006$Xo]\u0005\u0003]-\u0012a\"\u00119q'R\fG/^:Ti>\u0014X-\u0001\u0004=S:LGO\u0010\u000b\u0004cI\u001a\u0004CA\u0013\u0001\u0011\u0015\u00113\u00011\u0001%\u0011\u0015A3\u00011\u0001*\u0003A!\u0016*T#M\u0013:+u,\u0012(B\u00052+E)F\u00017!\t9$(D\u00019\u0015\u0005I\u0014!B:dC2\f\u0017BA\u001e9\u0005\u001d\u0011un\u001c7fC:\f\u0011\u0003V%N\u000b2Ke*R0F\u001d\u0006\u0013E*\u0012#!\u0003Mi\u0015\tW0U\u00136+E*\u0013(F?N#\u0016iR#T+\u0005y\u0004CA\u001cA\u0013\t\t\u0005HA\u0002J]R\fA#T!Y?RKU*\u0012'J\u001d\u0016{6\u000bV!H\u000bN\u0003\u0013AF'B1~#\u0016*T#M\u0013:+u,\u0012-F\u0007V#vJU*\u0002/5\u000b\u0005l\u0018+J\u001b\u0016c\u0015JT#`\u000bb+5)\u0016+P%N\u0003\u0013!D*U\u0003\u001e+5k\u0018'F\u000f\u0016sE)F\u0001H!\tAuJ\u0004\u0002J\u001bB\u0011!\nO\u0007\u0002\u0017*\u0011AjI\u0001\u0007yI|w\u000e\u001e \n\u00059C\u0014A\u0002)sK\u0012,g-\u0003\u0002Q#\n11\u000b\u001e:j]\u001eT!A\u0014\u001d\u0002\u001dM#\u0016iR#T?2+u)\u0012(EA\u0005\u0001R\tW#D+R{%kU0M\u000b\u001e+e\nR\u0001\u0012\u000bb+5)\u0016+P%N{F*R$F\u001d\u0012\u0003\u0013AD7bW\u0016\u001cF/Y4f\u000bZ,g\u000e\u001e\u000b\u0003/\u0002\u00042\u0001W/H\u001d\tI6L\u0004\u0002K5&\t\u0011(\u0003\u0002]q\u00059\u0001/Y2lC\u001e,\u0017B\u00010`\u0005\r\u0019V-\u001d\u0006\u00039bBQ!\u0019\bA\u0002\t\f!b\u001d;bO\u0016LeNZ8t!\rAVl\u0019\t\u0003I&l\u0011!\u001a\u0006\u0003M\u001e\f!A^\u0019\u000b\u0005!\\\u0013aA1qS&\u0011!.\u001a\u0002\n'R\fw-\u001a#bi\u0006\f\u0011#\\1lK\u0016CXmY;u_J,e/\u001a8u)\t9V\u000eC\u0003o\u001f\u0001\u0007q.A\u0005fq\u0016\u001cW\u000f^8sgB\u0019\u0001,\u00189\u0011\u0005\u0011\f\u0018B\u0001:f\u0005=)\u00050Z2vi>\u00148+^7nCJL\u0018\u0001D7bW\u0016$\u0016.\\3mS:,G\u0003B;}}~\u00042\u0001W/w!\t9(0D\u0001y\u0015\tI\b(A\u0002y[2L!a\u001f=\u0003\t9{G-\u001a\u0005\u0006{B\u0001\rAY\u0001\u0007gR\fw-Z:\t\u000b9\u0004\u0002\u0019A8\t\u000f\u0005\u0005\u0001\u00031\u0001\u0002\u0004\u0005a\u0011\r\u001d9Ti\u0006\u0014H\u000fV5nKB\u0019q'!\u0002\n\u0007\u0005\u001d\u0001H\u0001\u0003M_:<\u0017A\u0002:f]\u0012,'\u000fF\u0002v\u0003\u001bAq!a\u0004\u0012\u0001\u0004\t\t\"A\u0004sKF,Xm\u001d;\u0011\t\u0005M\u0011\u0011E\u0007\u0003\u0003+QA!a\u0006\u0002\u001a\u0005!\u0001\u000e\u001e;q\u0015\u0011\tY\"!\b\u0002\u000fM,'O\u001e7fi*\u0011\u0011qD\u0001\bU\u0006\\\u0017M\u001d;b\u0013\u0011\t\u0019#!\u0006\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f\u001e"
)
public class JobPage extends WebUIPage {
   private final JobsTab parent;
   private final AppStatusStore store;
   private final boolean TIMELINE_ENABLED;
   private final int MAX_TIMELINE_STAGES;
   private final int MAX_TIMELINE_EXECUTORS;
   private final String STAGES_LEGEND;
   private final String EXECUTORS_LEGEND;

   private boolean TIMELINE_ENABLED() {
      return this.TIMELINE_ENABLED;
   }

   private int MAX_TIMELINE_STAGES() {
      return this.MAX_TIMELINE_STAGES;
   }

   private int MAX_TIMELINE_EXECUTORS() {
      return this.MAX_TIMELINE_EXECUTORS;
   }

   private String STAGES_LEGEND() {
      return this.STAGES_LEGEND;
   }

   private String EXECUTORS_LEGEND() {
      return this.EXECUTORS_LEGEND;
   }

   private Seq makeStageEvent(final Seq stageInfos) {
      long now = System.currentTimeMillis();
      return (Seq)((IterableOps)((IterableOps)stageInfos.sortBy((s) -> new Tuple2.mcJJ.sp(BoxesRunTime.unboxToLong(s.completionTime().map((x$3) -> BoxesRunTime.boxToLong($anonfun$makeStageEvent$2(x$3))).getOrElse((JFunction0.mcJ.sp)() -> now)), ((Date)s.submissionTime().get()).getTime()), .MODULE$.Tuple2(scala.math.Ordering.Long..MODULE$, scala.math.Ordering.Long..MODULE$))).takeRight(this.MAX_TIMELINE_STAGES())).map((stage) -> {
         int stageId;
         int attemptId;
         String status;
         long submissionTime;
         long completionTime;
         String jsEscapedNameForTooltip;
         String jsEscapedNameForLabel;
         StringOps var10000;
         Predef var10001;
         String var10008;
         String var10009;
         String var10010;
         label23: {
            stageId = stage.stageId();
            attemptId = stage.attemptId();
            String name = stage.name();
            status = stage.status().toString().toLowerCase(Locale.ROOT);
            submissionTime = ((Date)stage.submissionTime().get()).getTime();
            completionTime = BoxesRunTime.unboxToLong(stage.completionTime().map((x$4) -> BoxesRunTime.boxToLong($anonfun$makeStageEvent$5(x$4))).getOrElse((JFunction0.mcJ.sp)() -> now));
            String escapedName = scala.xml.Utility..MODULE$.escape(name);
            jsEscapedNameForTooltip = StringEscapeUtils.escapeEcmaScript(scala.xml.Utility..MODULE$.escape(escapedName));
            jsEscapedNameForLabel = StringEscapeUtils.escapeEcmaScript(escapedName);
            var10000 = scala.collection.StringOps..MODULE$;
            var10001 = scala.Predef..MODULE$;
            var10008 = status.toUpperCase(Locale.ROOT);
            var10009 = UIUtils$.MODULE$.formatDate(submissionTime);
            String var14 = "running";
            if (status == null) {
               if (var14 != null) {
                  break label23;
               }
            } else if (!status.equals(var14)) {
               break label23;
            }

            var10010 = "";
            return var10000.stripMargin$extension(var10001.augmentString("\n         |{\n         |  'className': 'stage job-timeline-object " + status + "',\n         |  'group': 'stages',\n         |  'start': new Date(" + submissionTime + "),\n         |  'end': new Date(" + completionTime + "),\n         |  'content': '<div class=\"job-timeline-content\" data-toggle=\"tooltip\"' +\n         |   'data-placement=\"top\" data-html=\"true\"' +\n         |   'data-title=\"" + jsEscapedNameForTooltip + " (Stage " + stageId + "." + attemptId + ")<br>' +\n         |   'Status: " + var10008 + "<br>' +\n         |   'Submitted: " + var10009 + "' +\n         |   '" + var10010 + "\">' +\n         |    '" + jsEscapedNameForLabel + " (Stage " + stageId + "." + attemptId + ")</div>',\n         |}\n       "));
         }

         var10010 = "<br>Completed: " + UIUtils$.MODULE$.formatDate(completionTime);
         return var10000.stripMargin$extension(var10001.augmentString("\n         |{\n         |  'className': 'stage job-timeline-object " + status + "',\n         |  'group': 'stages',\n         |  'start': new Date(" + submissionTime + "),\n         |  'end': new Date(" + completionTime + "),\n         |  'content': '<div class=\"job-timeline-content\" data-toggle=\"tooltip\"' +\n         |   'data-placement=\"top\" data-html=\"true\"' +\n         |   'data-title=\"" + jsEscapedNameForTooltip + " (Stage " + stageId + "." + attemptId + ")<br>' +\n         |   'Status: " + var10008 + "<br>' +\n         |   'Submitted: " + var10009 + "' +\n         |   '" + var10010 + "\">' +\n         |    '" + jsEscapedNameForLabel + " (Stage " + stageId + "." + attemptId + ")</div>',\n         |}\n       "));
      });
   }

   public Seq makeExecutorEvent(final Seq executors) {
      ListBuffer events = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      ((IterableOnceOps)((IterableOps)executors.sortBy((e) -> BoxesRunTime.boxToLong($anonfun$makeExecutorEvent$1(e)), scala.math.Ordering.Long..MODULE$)).takeRight(this.MAX_TIMELINE_EXECUTORS())).foreach((e) -> {
         $anonfun$makeExecutorEvent$4(events, e);
         return BoxedUnit.UNIT;
      });
      return events.toSeq();
   }

   private Seq makeTimeline(final Seq stages, final Seq executors, final long appStartTime) {
      if (!this.TIMELINE_ENABLED()) {
         return (Seq)scala.package..MODULE$.Seq().empty();
      } else {
         Seq stageEventJsonAsStrSeq = this.makeStageEvent(stages);
         Seq executorsJsonAsStrSeq = this.makeExecutorEvent(executors);
         StringOps var10000 = scala.collection.StringOps..MODULE$;
         Predef var10001 = scala.Predef..MODULE$;
         String var10002 = this.EXECUTORS_LEGEND();
         String groupJsonArrayAsStr = var10000.stripMargin$extension(var10001.augmentString("\n          |[\n          |  {\n          |    'id': 'executors',\n          |    'content': '<div>Executors</div>" + var10002 + "',\n          |  },\n          |  {\n          |    'id': 'stages',\n          |    'content': '<div>Stages</div>" + this.STAGES_LEGEND() + "',\n          |  }\n          |]\n        "));
         String eventArrayAsStr = ((IterableOnceOps)stageEventJsonAsStrSeq.$plus$plus(executorsJsonAsStrSeq)).mkString("[", ",", "]");
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var28 = new UnprefixedAttribute("class", new Text("expand-job-timeline"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var29 = new UnprefixedAttribute("class", new Text("expand-job-timeline-arrow arrow-closed"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var29, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var30 = new UnprefixedAttribute("data-placement", new Text("top"), $md);
         var30 = new UnprefixedAttribute("title", ToolTips$.MODULE$.STAGE_TIMELINE(), var30);
         var30 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var30);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        Event Timeline\n      "));
         $buf.$amp$plus(new Elem((String)null, "a", var30, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n    "));
         Elem var39 = new Elem((String)null, "span", var28, var10005, false, var10007.seqToNodeSeq($buf));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var33 = new UnprefixedAttribute("class", new Text("collapsed"), $md);
         var33 = new UnprefixedAttribute("id", new Text("job-timeline"), var33);
         Elem var41 = new Elem;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         Object var10010;
         if (this.MAX_TIMELINE_STAGES() < stages.size()) {
            Null var45 = scala.xml.Null..MODULE$;
            TopScope var10015 = scala.xml.TopScope..MODULE$;
            NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var10023 = scala.xml.Null..MODULE$;
            TopScope var10024 = scala.xml.TopScope..MODULE$;
            NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            Only the most recent "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(this.MAX_TIMELINE_STAGES()));
            $buf.$amp$plus(new Text(" submitted/completed stages\n            (of "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(stages.size()));
            $buf.$amp$plus(new Text(" total) are shown.\n          "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10023, var10024, false, var10026.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            var10010 = new Elem((String)null, "div", var45, var10015, false, var10017.seqToNodeSeq($buf));
         } else {
            var10010 = scala.package..MODULE$.Seq().empty();
         }

         $buf.$amp$plus(var10010);
         $buf.$amp$plus(new Text("\n      "));
         if (this.MAX_TIMELINE_EXECUTORS() < executors.size()) {
            Null var46 = scala.xml.Null..MODULE$;
            TopScope var47 = scala.xml.TopScope..MODULE$;
            NodeSeq var49 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var51 = scala.xml.Null..MODULE$;
            TopScope var52 = scala.xml.TopScope..MODULE$;
            NodeSeq var54 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            Only the most recent "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(this.MAX_TIMELINE_EXECUTORS()));
            $buf.$amp$plus(new Text(" added/removed executors\n            (of "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(executors.size()));
            $buf.$amp$plus(new Text(" total) are shown.\n          "));
            $buf.$amp$plus(new Elem((String)null, "strong", var51, var52, false, var54.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            var10010 = new Elem((String)null, "div", var46, var47, false, var49.seqToNodeSeq($buf));
         } else {
            var10010 = scala.package..MODULE$.Seq().empty();
         }

         $buf.$amp$plus(var10010);
         $buf.$amp$plus(new Text("\n      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var35 = new UnprefixedAttribute("class", new Text("control-panel"), $md);
         TopScope var48 = scala.xml.TopScope..MODULE$;
         NodeSeq var50 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var36 = new UnprefixedAttribute("id", new Text("job-timeline-zoom-lock"), $md);
         TopScope var53 = scala.xml.TopScope..MODULE$;
         NodeSeq var55 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var37 = new UnprefixedAttribute("type", new Text("checkbox"), $md);
         $buf.$amp$plus(new Elem((String)null, "input", var37, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n          "));
         Null var10032 = scala.xml.Null..MODULE$;
         TopScope var10033 = scala.xml.TopScope..MODULE$;
         NodeSeq var10035 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Enable zooming"));
         $buf.$amp$plus(new Elem((String)null, "span", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "div", var36, var53, false, var55.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         $buf.$amp$plus(new Elem((String)null, "div", var35, var48, false, var50.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n    "));
         var41.<init>((String)null, "div", var33, var10006, false, var10008.seqToNodeSeq($buf));
         NodeSeq var40 = var39.$plus$plus(var41);
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var38 = new UnprefixedAttribute("type", new Text("text/javascript"), $md);
         var10006 = scala.xml.TopScope..MODULE$;
         var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply("drawJobTimeline(" + groupJsonArrayAsStr + ", " + eventArrayAsStr + ", " + appStartTime + ", " + UIUtils$.MODULE$.getTimeZoneOffset() + ");"));
         $buf.$amp$plus(new Text("\n    "));
         return var40.$plus$plus(new Elem((String)null, "script", var38, var10006, false, var10008.seqToNodeSeq($buf)));
      }
   }

   public Seq render(final HttpServletRequest request) {
      Object var4 = new Object();

      Seq var10000;
      try {
         String parameterId = request.getParameter("id");
         scala.Predef..MODULE$.require(parameterId != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(parameterId)), () -> "Missing id parameter");
         int jobId = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(parameterId));
         Tuple2 var8 = (Tuple2)this.store.asOption(() -> this.store.jobWithAssociatedSql(jobId)).getOrElse(() -> {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var8 = new UnprefixedAttribute("id", new Text("no-info"), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var10013 = scala.xml.Null..MODULE$;
            TopScope var10014 = scala.xml.TopScope..MODULE$;
            NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("No information to display for job "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(jobId));
            $buf.$amp$plus(new Elem((String)null, "p", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            Elem content = new Elem((String)null, "div", var8, var10005, false, var10007.seqToNodeSeq($buf));
            throw new NonLocalReturnControl(var4, UIUtils$.MODULE$.headerSparkPage(request, "Details for Job " + jobId, () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7()));
         });
         if (var8 == null) {
            throw new MatchError(var8);
         }

         JobData jobData;
         Option sqlExecutionId;
         label119: {
            label118: {
               JobData jobData = (JobData)var8._1();
               Option sqlExecutionId = (Option)var8._2();
               Tuple2 var7 = new Tuple2(jobData, sqlExecutionId);
               jobData = (JobData)var7._1();
               sqlExecutionId = (Option)var7._2();
               JobExecutionStatus var154 = jobData.status();
               JobExecutionStatus var14 = JobExecutionStatus.RUNNING;
               if (var154 == null) {
                  if (var14 != null) {
                     break label118;
                  }
               } else if (!var154.equals(var14)) {
                  break label118;
               }

               var155 = false;
               break label119;
            }

            var155 = true;
         }

         boolean isComplete = var155;
         scala.collection.Seq stages = (scala.collection.Seq)jobData.stageIds().map((stageId) -> $anonfun$render$5(this, BoxesRunTime.unboxToInt(stageId)));
         Buffer activeStages = (Buffer)scala.collection.mutable.Buffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         Buffer completedStages = (Buffer)scala.collection.mutable.Buffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         Buffer pendingOrSkippedStages = (Buffer)scala.collection.mutable.Buffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         Buffer failedStages = (Buffer)scala.collection.mutable.Buffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         stages.foreach((stage) -> {
            if (stage.submissionTime().isEmpty()) {
               return (Buffer)pendingOrSkippedStages.$plus$eq(stage);
            } else if (!stage.completionTime().isDefined()) {
               return (Buffer)activeStages.$plus$eq(stage);
            } else {
               StageStatus var10000 = stage.status();
               StageStatus var5 = StageStatus.FAILED;
               if (var10000 == null) {
                  if (var5 == null) {
                     return (Buffer)failedStages.$plus$eq(stage);
                  }
               } else if (var10000.equals(var5)) {
                  return (Buffer)failedStages.$plus$eq(stage);
               }

               return (Buffer)completedStages.$plus$eq(stage);
            }
         });
         String basePath = "jobs/job";
         String pendingOrSkippedTableId = isComplete ? "skipped" : "pending";
         StageTableBase activeStagesTable = new StageTableBase(this.store, request, activeStages.toSeq(), "active", "activeStage", this.parent.basePath(), basePath, this.parent.isFairScheduler(), this.parent.killEnabled(), false);
         StageTableBase pendingOrSkippedStagesTable = new StageTableBase(this.store, request, pendingOrSkippedStages.toSeq(), pendingOrSkippedTableId, "pendingStage", this.parent.basePath(), basePath, this.parent.isFairScheduler(), false, false);
         StageTableBase completedStagesTable = new StageTableBase(this.store, request, completedStages.toSeq(), "completed", "completedStage", this.parent.basePath(), basePath, this.parent.isFairScheduler(), false, false);
         StageTableBase failedStagesTable = new StageTableBase(this.store, request, failedStages.toSeq(), "failed", "failedStage", this.parent.basePath(), basePath, this.parent.isFairScheduler(), false, true);
         boolean shouldShowActiveStages = activeStages.nonEmpty();
         boolean shouldShowPendingStages = !isComplete && pendingOrSkippedStages.nonEmpty();
         boolean shouldShowCompletedStages = completedStages.nonEmpty();
         boolean shouldShowSkippedStages = isComplete && pendingOrSkippedStages.nonEmpty();
         boolean shouldShowFailedStages = failedStages.nonEmpty();
         Elem var156 = new Elem;
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var121 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
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
         $buf.$amp$plus(new Text("Status:"));
         $buf.$amp$plus(new Elem((String)null, "Strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(jobData.status());
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
         $buf.$amp$plus(new Text("Submitted:"));
         $buf.$amp$plus(new Elem((String)null, "Strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(JobDataUtil$.MODULE$.getFormattedSubmissionTime(jobData));
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
         $buf.$amp$plus(new Text("Duration:"));
         $buf.$amp$plus(new Elem((String)null, "Strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(JobDataUtil$.MODULE$.getFormattedDuration(jobData));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         Object var10018;
         if (sqlExecutionId.isDefined()) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            var10031 = scala.xml.Null..MODULE$;
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Associated SQL Query: "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var122 = new UnprefixedAttribute("href", scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s/SQL/execution/?id=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{UIUtils$.MODULE$.prependBaseUri(request, this.parent.basePath(), UIUtils$.MODULE$.prependBaseUri$default$3()), sqlExecutionId.get()})), $md);
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(sqlExecutionId.get());
            $buf.$amp$plus(new Elem((String)null, "a", var122, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (jobData.jobGroup().isDefined()) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            var10031 = scala.xml.Null..MODULE$;
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Job Group:"));
            $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(jobData.jobGroup().get());
            $buf.$amp$plus(new Text("\n              "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (shouldShowActiveStages) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var123 = new UnprefixedAttribute("href", new Text("#active"), $md);
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            Null var10040 = scala.xml.Null..MODULE$;
            TopScope var10041 = scala.xml.TopScope..MODULE$;
            NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Active Stages:"));
            $buf.$amp$plus(new Elem((String)null, "strong", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Elem((String)null, "a", var123, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(activeStages.size()));
            $buf.$amp$plus(new Text("\n              "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (shouldShowPendingStages) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var124 = new UnprefixedAttribute("href", new Text("#pending"), $md);
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                  "));
            Null var272 = scala.xml.Null..MODULE$;
            TopScope var276 = scala.xml.TopScope..MODULE$;
            NodeSeq var280 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Pending Stages:"));
            $buf.$amp$plus(new Elem((String)null, "strong", var272, var276, false, var280.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(new Elem((String)null, "a", var124, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(pendingOrSkippedStages.size()));
            $buf.$amp$plus(new Text("\n              "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (shouldShowCompletedStages) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var125 = new UnprefixedAttribute("href", new Text("#completed"), $md);
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            Null var273 = scala.xml.Null..MODULE$;
            TopScope var277 = scala.xml.TopScope..MODULE$;
            NodeSeq var281 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Completed Stages:"));
            $buf.$amp$plus(new Elem((String)null, "strong", var273, var277, false, var281.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Elem((String)null, "a", var125, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(completedStages.size()));
            $buf.$amp$plus(new Text("\n              "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (shouldShowSkippedStages) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var126 = new UnprefixedAttribute("href", new Text("#skipped"), $md);
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            Null var274 = scala.xml.Null..MODULE$;
            TopScope var278 = scala.xml.TopScope..MODULE$;
            NodeSeq var282 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Skipped Stages:"));
            $buf.$amp$plus(new Elem((String)null, "strong", var274, var278, false, var282.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Elem((String)null, "a", var126, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(pendingOrSkippedStages.size()));
            $buf.$amp$plus(new Text("\n            "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n          "));
         if (shouldShowFailedStages) {
            var10022 = scala.xml.Null..MODULE$;
            var10023 = scala.xml.TopScope..MODULE$;
            var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var127 = new UnprefixedAttribute("href", new Text("#failed"), $md);
            var10032 = scala.xml.TopScope..MODULE$;
            var10034 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            Null var275 = scala.xml.Null..MODULE$;
            TopScope var279 = scala.xml.TopScope..MODULE$;
            NodeSeq var283 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Failed Stages:"));
            $buf.$amp$plus(new Elem((String)null, "strong", var275, var279, false, var283.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Elem((String)null, "a", var127, var10032, false, var10034.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(failedStages.size()));
            $buf.$amp$plus(new Text("\n              "));
            var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
         } else {
            var10018 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10018);
         $buf.$amp$plus(new Text("\n        "));
         var10009.<init>((String)null, "ul", var121, var10014, false, var10016.seqToNodeSeq($buf));
         $buf.$amp$plus(var10009);
         $buf.$amp$plus(new Text("\n      "));
         var156.<init>((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         NodeSeq summary = var156;
         ObjectRef content = ObjectRef.create(summary);
         long appStartTime = ((ApplicationAttemptInfo)this.store.applicationInfo().attempts().head()).startTime().getTime();
         content.elem = ((NodeSeq)content.elem).$plus$plus(this.makeTimeline(((IterableOnceOps)((IterableOps)activeStages.$plus$plus(completedStages)).$plus$plus(failedStages)).toSeq(), this.store.executorList(false), appStartTime));
         Option var71 = this.store.asOption(() -> this.store.operationGraphForJob(jobId));
         Object var157;
         if (var71 instanceof Some var72) {
            scala.collection.Seq operationGraph = (scala.collection.Seq)var72.value();
            var157 = UIUtils$.MODULE$.showDagVizForJob(jobId, operationGraph);
         } else {
            if (!scala.None..MODULE$.equals(var71)) {
               throw new MatchError(var71);
            }

            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var128 = new UnprefixedAttribute("id", new Text("no-info"), $md);
            var10005 = scala.xml.TopScope..MODULE$;
            var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var10013 = scala.xml.Null..MODULE$;
            var10014 = scala.xml.TopScope..MODULE$;
            var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("No DAG visualization information to display for job "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(jobId));
            $buf.$amp$plus(new Elem((String)null, "p", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            var157 = new Elem((String)null, "div", var128, var10005, false, var10007.seqToNodeSeq($buf));
         }

         scala.collection.Seq operationGraphContent = (scala.collection.Seq)var157;
         content.elem = scala.xml.NodeSeq..MODULE$.seqToNodeSeq((scala.collection.Seq)((NodeSeq)content.elem).$plus$plus(operationGraphContent));
         if (shouldShowActiveStages) {
            NodeSeq var10001 = (NodeSeq)content.elem;
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var129 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-activeStages','aggregated-activeStages')"), $md);
            var129 = new UnprefixedAttribute("class", new Text("collapse-aggregated-activeStages collapse-table"), var129);
            var129 = new UnprefixedAttribute("id", new Text("active"), var129);
            TopScope var168 = scala.xml.TopScope..MODULE$;
            NodeSeq var177 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var10015 = scala.xml.Null..MODULE$;
            TopScope var192 = scala.xml.TopScope..MODULE$;
            NodeSeq var203 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var132 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
            $buf.$amp$plus(new Elem((String)null, "span", var132, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
            $buf.$amp$plus(new Text("\n            "));
            Null var10024 = scala.xml.Null..MODULE$;
            TopScope var239 = scala.xml.TopScope..MODULE$;
            NodeSeq var10027 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Active Stages ("));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(activeStages.size()));
            $buf.$amp$plus(new Text(")"));
            $buf.$amp$plus(new Elem((String)null, "a", var10024, var239, false, var10027.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "h4", var10015, var192, false, var203.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            Elem var10002 = new Elem((String)null, "span", var129, var168, false, var177.seqToNodeSeq($buf));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var133 = new UnprefixedAttribute("class", new Text("aggregated-activeStages collapsible-table"), $md);
            TopScope var10008 = scala.xml.TopScope..MODULE$;
            NodeSeq var10010 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(activeStagesTable.toNodeSeq());
            $buf.$amp$plus(new Text("\n        "));
            content.elem = var10001.$plus$plus(var10002.$plus$plus(new Elem((String)null, "div", var133, var10008, false, var10010.seqToNodeSeq($buf))));
         }

         if (shouldShowPendingStages) {
            NodeSeq var158 = (NodeSeq)content.elem;
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var134 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-pendingOrSkippedStages',\n            'aggregated-pendingOrSkippedStages')"), $md);
            var134 = new UnprefixedAttribute("class", new Text("collapse-aggregated-pendingOrSkippedStages collapse-table"), var134);
            var134 = new UnprefixedAttribute("id", new Text("pending"), var134);
            TopScope var169 = scala.xml.TopScope..MODULE$;
            NodeSeq var178 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var187 = scala.xml.Null..MODULE$;
            TopScope var193 = scala.xml.TopScope..MODULE$;
            NodeSeq var204 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var137 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
            $buf.$amp$plus(new Elem((String)null, "span", var137, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
            $buf.$amp$plus(new Text("\n            "));
            Null var226 = scala.xml.Null..MODULE$;
            TopScope var240 = scala.xml.TopScope..MODULE$;
            NodeSeq var244 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Pending Stages ("));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(pendingOrSkippedStages.size()));
            $buf.$amp$plus(new Text(")"));
            $buf.$amp$plus(new Elem((String)null, "a", var226, var240, false, var244.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "h4", var187, var193, false, var204.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            Elem var162 = new Elem((String)null, "span", var134, var169, false, var178.seqToNodeSeq($buf));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var138 = new UnprefixedAttribute("class", new Text("aggregated-pendingOrSkippedStages collapsible-table"), $md);
            TopScope var173 = scala.xml.TopScope..MODULE$;
            NodeSeq var182 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(pendingOrSkippedStagesTable.toNodeSeq());
            $buf.$amp$plus(new Text("\n        "));
            content.elem = var158.$plus$plus(var162.$plus$plus(new Elem((String)null, "div", var138, var173, false, var182.seqToNodeSeq($buf))));
         }

         if (shouldShowCompletedStages) {
            NodeSeq var159 = (NodeSeq)content.elem;
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var139 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-completedStages',\n            'aggregated-completedStages')"), $md);
            var139 = new UnprefixedAttribute("class", new Text("collapse-aggregated-completedStages collapse-table"), var139);
            var139 = new UnprefixedAttribute("id", new Text("completed"), var139);
            TopScope var170 = scala.xml.TopScope..MODULE$;
            NodeSeq var179 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var188 = scala.xml.Null..MODULE$;
            TopScope var194 = scala.xml.TopScope..MODULE$;
            NodeSeq var205 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var142 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
            $buf.$amp$plus(new Elem((String)null, "span", var142, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
            $buf.$amp$plus(new Text("\n            "));
            Null var227 = scala.xml.Null..MODULE$;
            TopScope var241 = scala.xml.TopScope..MODULE$;
            NodeSeq var245 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Completed Stages ("));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(completedStages.size()));
            $buf.$amp$plus(new Text(")"));
            $buf.$amp$plus(new Elem((String)null, "a", var227, var241, false, var245.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "h4", var188, var194, false, var205.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            Elem var163 = new Elem((String)null, "span", var139, var170, false, var179.seqToNodeSeq($buf));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var143 = new UnprefixedAttribute("class", new Text("aggregated-completedStages collapsible-table"), $md);
            TopScope var174 = scala.xml.TopScope..MODULE$;
            NodeSeq var183 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(completedStagesTable.toNodeSeq());
            $buf.$amp$plus(new Text("\n        "));
            content.elem = var159.$plus$plus(var163.$plus$plus(new Elem((String)null, "div", var143, var174, false, var183.seqToNodeSeq($buf))));
         }

         if (shouldShowSkippedStages) {
            NodeSeq var160 = (NodeSeq)content.elem;
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var144 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-pendingOrSkippedStages',\n            'aggregated-pendingOrSkippedStages')"), $md);
            var144 = new UnprefixedAttribute("class", new Text("collapse-aggregated-pendingOrSkippedStages collapse-table"), var144);
            var144 = new UnprefixedAttribute("id", new Text("skipped"), var144);
            TopScope var171 = scala.xml.TopScope..MODULE$;
            NodeSeq var180 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var189 = scala.xml.Null..MODULE$;
            TopScope var195 = scala.xml.TopScope..MODULE$;
            NodeSeq var206 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var147 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
            $buf.$amp$plus(new Elem((String)null, "span", var147, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
            $buf.$amp$plus(new Text("\n            "));
            Null var228 = scala.xml.Null..MODULE$;
            TopScope var242 = scala.xml.TopScope..MODULE$;
            NodeSeq var246 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Skipped Stages ("));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(pendingOrSkippedStages.size()));
            $buf.$amp$plus(new Text(")"));
            $buf.$amp$plus(new Elem((String)null, "a", var228, var242, false, var246.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "h4", var189, var195, false, var206.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            Elem var164 = new Elem((String)null, "span", var144, var171, false, var180.seqToNodeSeq($buf));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var148 = new UnprefixedAttribute("class", new Text("aggregated-pendingOrSkippedStages collapsible-table"), $md);
            TopScope var175 = scala.xml.TopScope..MODULE$;
            NodeSeq var184 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(pendingOrSkippedStagesTable.toNodeSeq());
            $buf.$amp$plus(new Text("\n        "));
            content.elem = var160.$plus$plus(var164.$plus$plus(new Elem((String)null, "div", var148, var175, false, var184.seqToNodeSeq($buf))));
         }

         if (shouldShowFailedStages) {
            NodeSeq var161 = (NodeSeq)content.elem;
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var149 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-failedStages','aggregated-failedStages')"), $md);
            var149 = new UnprefixedAttribute("class", new Text("collapse-aggregated-failedStages collapse-table"), var149);
            var149 = new UnprefixedAttribute("id", new Text("failed"), var149);
            TopScope var172 = scala.xml.TopScope..MODULE$;
            NodeSeq var181 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var190 = scala.xml.Null..MODULE$;
            TopScope var196 = scala.xml.TopScope..MODULE$;
            NodeSeq var207 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var152 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
            $buf.$amp$plus(new Elem((String)null, "span", var152, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
            $buf.$amp$plus(new Text("\n            "));
            Null var229 = scala.xml.Null..MODULE$;
            TopScope var243 = scala.xml.TopScope..MODULE$;
            NodeSeq var247 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Failed Stages ("));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(failedStages.size()));
            $buf.$amp$plus(new Text(")"));
            $buf.$amp$plus(new Elem((String)null, "a", var229, var243, false, var247.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "h4", var190, var196, false, var207.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            Elem var165 = new Elem((String)null, "span", var149, var172, false, var181.seqToNodeSeq($buf));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var153 = new UnprefixedAttribute("class", new Text("aggregated-failedStages collapsible-table"), $md);
            TopScope var176 = scala.xml.TopScope..MODULE$;
            NodeSeq var185 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(failedStagesTable.toNodeSeq());
            $buf.$amp$plus(new Text("\n        "));
            content.elem = var161.$plus$plus(var165.$plus$plus(new Elem((String)null, "div", var153, var176, false, var185.seqToNodeSeq($buf))));
         }

         String x$2 = "Details for Job " + jobId;
         Function0 x$3 = () -> (NodeSeq)content.elem;
         JobsTab x$4 = this.parent;
         boolean x$5 = true;
         Option x$6 = UIUtils$.MODULE$.headerSparkPage$default$5();
         boolean x$7 = UIUtils$.MODULE$.headerSparkPage$default$7();
         var10000 = UIUtils$.MODULE$.headerSparkPage(request, x$2, x$3, x$4, x$6, true, x$7);
      } catch (NonLocalReturnControl var120) {
         if (var120.key() != var4) {
            throw var120;
         }

         var10000 = (Seq)var120.value();
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$STAGES_LEGEND$1(final char x$1) {
      return x$1 != '\n';
   }

   // $FF: synthetic method
   public static final boolean $anonfun$EXECUTORS_LEGEND$1(final char x$2) {
      return x$2 != '\n';
   }

   // $FF: synthetic method
   public static final long $anonfun$makeStageEvent$2(final Date x$3) {
      return x$3.getTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$makeStageEvent$5(final Date x$4) {
      return x$4.getTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$makeExecutorEvent$2(final Date x$5) {
      return x$5.getTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$makeExecutorEvent$1(final ExecutorSummary e) {
      return BoxesRunTime.unboxToLong(e.removeTime().map((x$5) -> BoxesRunTime.boxToLong($anonfun$makeExecutorEvent$2(x$5))).getOrElse((JFunction0.mcJ.sp)() -> e.addTime().getTime()));
   }

   // $FF: synthetic method
   public static final void $anonfun$makeExecutorEvent$4(final ListBuffer events$1, final ExecutorSummary e) {
      StringOps var10000 = scala.collection.StringOps..MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      long var10002 = e.addTime().getTime();
      String addedEvent = var10000.stripMargin$extension(var10001.augmentString("\n           |{\n           |  'className': 'executor added',\n           |  'group': 'executors',\n           |  'start': new Date(" + var10002 + "),\n           |  'content': '<div class=\"executor-event-content\"' +\n           |    'data-toggle=\"tooltip\" data-placement=\"top\"' +\n           |    'data-title=\"Executor " + e.id() + "<br>' +\n           |    'Added at " + UIUtils$.MODULE$.formatDate(e.addTime()) + "\"' +\n           |    'data-html=\"true\">Executor " + e.id() + " added</div>'\n           |}\n         "));
      events$1.$plus$eq(addedEvent);
      e.removeTime().foreach((removeTime) -> {
         StringOps var10000 = scala.collection.StringOps..MODULE$;
         Predef var10001 = scala.Predef..MODULE$;
         long var10002 = removeTime.getTime();
         String removedEvent = var10000.stripMargin$extension(var10001.augmentString("\n             |{\n             |  'className': 'executor removed',\n             |  'group': 'executors',\n             |  'start': new Date(" + var10002 + "),\n             |  'content': '<div class=\"executor-event-content\"' +\n             |    'data-toggle=\"tooltip\" data-placement=\"top\"' +\n             |    'data-title=\"Executor " + e.id() + "<br>' +\n             |    'Removed at " + UIUtils$.MODULE$.formatDate(removeTime) + "' +\n             |    '" + e.removeReason().map((reason) -> "<br>Reason: " + StringEscapeUtils.escapeEcmaScript(reason.replace("\n", " "))).getOrElse(() -> "") + "\"' +\n             |    'data-html=\"true\">Executor " + e.id() + " removed</div>'\n             |}\n           "));
         return (ListBuffer)events$1.$plus$eq(removedEvent);
      });
   }

   // $FF: synthetic method
   public static final StageData $anonfun$render$5(final JobPage $this, final int stageId) {
      return (StageData)$this.store.asOption(() -> $this.store.lastStageAttempt(stageId)).getOrElse(() -> new StageData(StageStatus.PENDING, stageId, 0, 0, 0, 0, 0, 0, 0, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, "Unknown", scala.None..MODULE$, "Unknown", (String)null, scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID(), scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, false, 0));
   }

   public JobPage(final JobsTab parent, final AppStatusStore store) {
      super("job");
      this.parent = parent;
      this.store = store;
      this.TIMELINE_ENABLED = BoxesRunTime.unboxToBoolean(parent.conf().get(UI$.MODULE$.UI_TIMELINE_ENABLED()));
      this.MAX_TIMELINE_STAGES = BoxesRunTime.unboxToInt(parent.conf().get(UI$.MODULE$.UI_TIMELINE_STAGES_MAXIMUM()));
      this.MAX_TIMELINE_EXECUTORS = BoxesRunTime.unboxToInt(parent.conf().get(UI$.MODULE$.UI_TIMELINE_EXECUTORS_MAXIMUM()));
      StringOps var10001 = scala.collection.StringOps..MODULE$;
      Predef var10002 = scala.Predef..MODULE$;
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var26 = new UnprefixedAttribute("class", new Text("legend-area"), $md);
      TopScope var10008 = scala.xml.TopScope..MODULE$;
      NodeSeq var10010 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var27 = new UnprefixedAttribute("height", new Text("85px"), $md);
      var27 = new UnprefixedAttribute("width", new Text("150px"), var27);
      TopScope var10017 = scala.xml.TopScope..MODULE$;
      NodeSeq var10019 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var29 = new UnprefixedAttribute("ry", new Text("2px"), $md);
      var29 = new UnprefixedAttribute("rx", new Text("2px"), var29);
      var29 = new UnprefixedAttribute("height", new Text("15px"), var29);
      var29 = new UnprefixedAttribute("width", new Text("20px"), var29);
      var29 = new UnprefixedAttribute("y", new Text("5px"), var29);
      var29 = new UnprefixedAttribute("x", new Text("5px"), var29);
      var29 = new UnprefixedAttribute("class", new Text("completed-stage-legend"), var29);
      $buf.$amp$plus(new Elem((String)null, "rect", var29, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var36 = new UnprefixedAttribute("y", new Text("17px"), $md);
      var36 = new UnprefixedAttribute("x", new Text("35px"), var36);
      TopScope var10026 = scala.xml.TopScope..MODULE$;
      NodeSeq var10028 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Completed"));
      $buf.$amp$plus(new Elem((String)null, "text", var36, var10026, false, var10028.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var38 = new UnprefixedAttribute("ry", new Text("2px"), $md);
      var38 = new UnprefixedAttribute("rx", new Text("2px"), var38);
      var38 = new UnprefixedAttribute("height", new Text("15px"), var38);
      var38 = new UnprefixedAttribute("width", new Text("20px"), var38);
      var38 = new UnprefixedAttribute("y", new Text("30px"), var38);
      var38 = new UnprefixedAttribute("x", new Text("5px"), var38);
      var38 = new UnprefixedAttribute("class", new Text("failed-stage-legend"), var38);
      $buf.$amp$plus(new Elem((String)null, "rect", var38, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var45 = new UnprefixedAttribute("y", new Text("42px"), $md);
      var45 = new UnprefixedAttribute("x", new Text("35px"), var45);
      var10026 = scala.xml.TopScope..MODULE$;
      var10028 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Failed"));
      $buf.$amp$plus(new Elem((String)null, "text", var45, var10026, false, var10028.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var47 = new UnprefixedAttribute("ry", new Text("2px"), $md);
      var47 = new UnprefixedAttribute("rx", new Text("2px"), var47);
      var47 = new UnprefixedAttribute("height", new Text("15px"), var47);
      var47 = new UnprefixedAttribute("width", new Text("20px"), var47);
      var47 = new UnprefixedAttribute("y", new Text("55px"), var47);
      var47 = new UnprefixedAttribute("x", new Text("5px"), var47);
      var47 = new UnprefixedAttribute("class", new Text("active-stage-legend"), var47);
      $buf.$amp$plus(new Elem((String)null, "rect", var47, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var54 = new UnprefixedAttribute("y", new Text("67px"), $md);
      var54 = new UnprefixedAttribute("x", new Text("35px"), var54);
      var10026 = scala.xml.TopScope..MODULE$;
      var10028 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Active"));
      $buf.$amp$plus(new Elem((String)null, "text", var54, var10026, false, var10028.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      $buf.$amp$plus(new Elem((String)null, "svg", var27, var10017, false, var10019.seqToNodeSeq($buf)));
      this.STAGES_LEGEND = var10001.filter$extension(var10002.augmentString((new Elem((String)null, "div", var26, var10008, false, var10010.seqToNodeSeq($buf))).toString()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$STAGES_LEGEND$1(BoxesRunTime.unboxToChar(x$1))));
      var10001 = scala.collection.StringOps..MODULE$;
      var10002 = scala.Predef..MODULE$;
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var56 = new UnprefixedAttribute("class", new Text("legend-area"), $md);
      var10008 = scala.xml.TopScope..MODULE$;
      var10010 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var57 = new UnprefixedAttribute("height", new Text("55px"), $md);
      var57 = new UnprefixedAttribute("width", new Text("150px"), var57);
      var10017 = scala.xml.TopScope..MODULE$;
      var10019 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var59 = new UnprefixedAttribute("ry", new Text("2px"), $md);
      var59 = new UnprefixedAttribute("rx", new Text("2px"), var59);
      var59 = new UnprefixedAttribute("height", new Text("15px"), var59);
      var59 = new UnprefixedAttribute("width", new Text("20px"), var59);
      var59 = new UnprefixedAttribute("y", new Text("5px"), var59);
      var59 = new UnprefixedAttribute("x", new Text("5px"), var59);
      var59 = new UnprefixedAttribute("class", new Text("executor-added-legend"), var59);
      $buf.$amp$plus(new Elem((String)null, "rect", var59, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var66 = new UnprefixedAttribute("y", new Text("17px"), $md);
      var66 = new UnprefixedAttribute("x", new Text("35px"), var66);
      var10026 = scala.xml.TopScope..MODULE$;
      var10028 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Added"));
      $buf.$amp$plus(new Elem((String)null, "text", var66, var10026, false, var10028.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var68 = new UnprefixedAttribute("ry", new Text("2px"), $md);
      var68 = new UnprefixedAttribute("rx", new Text("2px"), var68);
      var68 = new UnprefixedAttribute("height", new Text("15px"), var68);
      var68 = new UnprefixedAttribute("width", new Text("20px"), var68);
      var68 = new UnprefixedAttribute("y", new Text("30px"), var68);
      var68 = new UnprefixedAttribute("x", new Text("5px"), var68);
      var68 = new UnprefixedAttribute("class", new Text("executor-removed-legend"), var68);
      $buf.$amp$plus(new Elem((String)null, "rect", var68, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var75 = new UnprefixedAttribute("y", new Text("42px"), $md);
      var75 = new UnprefixedAttribute("x", new Text("35px"), var75);
      var10026 = scala.xml.TopScope..MODULE$;
      var10028 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Removed"));
      $buf.$amp$plus(new Elem((String)null, "text", var75, var10026, false, var10028.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      $buf.$amp$plus(new Elem((String)null, "svg", var57, var10017, false, var10019.seqToNodeSeq($buf)));
      this.EXECUTORS_LEGEND = var10001.filter$extension(var10002.augmentString((new Elem((String)null, "div", var56, var10008, false, var10010.seqToNodeSeq($buf))).toString()), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$EXECUTORS_LEGEND$1(BoxesRunTime.unboxToChar(x$2))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
