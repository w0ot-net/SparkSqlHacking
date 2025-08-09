package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.spark.JobExecutionStatus;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.scheduler.SchedulingMode$;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.AppSummary;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.status.api.v1.ExecutorSummary;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.ui.ToolTips$;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.StringOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ListBuffer;
import scala.math.Ordering.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
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
   bytes = "\u0006\u0005\u0005]b!B\n\u0015\u0001Yq\u0002\u0002C\u0012\u0001\u0005\u0003\u0005\u000b\u0011B\u0013\t\u0011%\u0002!\u0011!Q\u0001\n)BQ\u0001\r\u0001\u0005\u0002EBq!\u000e\u0001C\u0002\u0013%a\u0007\u0003\u0004>\u0001\u0001\u0006Ia\u000e\u0005\b}\u0001\u0011\r\u0011\"\u0003@\u0011\u0019\u0019\u0005\u0001)A\u0005\u0001\"9A\t\u0001b\u0001\n\u0013y\u0004BB#\u0001A\u0003%\u0001\tC\u0004G\u0001\t\u0007I\u0011B$\t\rM\u0003\u0001\u0015!\u0003I\u0011\u001d!\u0006A1A\u0005\n\u001dCa!\u0016\u0001!\u0002\u0013A\u0005\"\u0002,\u0001\t\u00139\u0006\"B6\u0001\t\u0013a\u0007\"B:\u0001\t\u0013!\bbBA\u0004\u0001\u0011%\u0011\u0011\u0002\u0005\b\u0003c\u0001A\u0011AA\u001a\u0005-\tE\u000e\u001c&pEN\u0004\u0016mZ3\u000b\u0005U1\u0012\u0001\u00026pENT!a\u0006\r\u0002\u0005UL'BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0014\u0005\u0001y\u0002C\u0001\u0011\"\u001b\u00051\u0012B\u0001\u0012\u0017\u0005%9VMY+J!\u0006<W-\u0001\u0004qCJ,g\u000e^\u0002\u0001!\t1s%D\u0001\u0015\u0013\tACCA\u0004K_\n\u001cH+\u00192\u0002\u000bM$xN]3\u0011\u0005-rS\"\u0001\u0017\u000b\u00055B\u0012AB:uCR,8/\u0003\u00020Y\tq\u0011\t\u001d9Ti\u0006$Xo]*u_J,\u0017A\u0002\u001fj]&$h\bF\u00023gQ\u0002\"A\n\u0001\t\u000b\r\u001a\u0001\u0019A\u0013\t\u000b%\u001a\u0001\u0019\u0001\u0016\u0002!QKU*\u0012'J\u001d\u0016{VIT!C\u0019\u0016#U#A\u001c\u0011\u0005aZT\"A\u001d\u000b\u0003i\nQa]2bY\u0006L!\u0001P\u001d\u0003\u000f\t{w\u000e\\3b]\u0006\tB+S'F\u0019&sUiX#O\u0003\ncU\t\u0012\u0011\u0002#5\u000b\u0005l\u0018+J\u001b\u0016c\u0015JT#`\u0015>\u00135+F\u0001A!\tA\u0014)\u0003\u0002Cs\t\u0019\u0011J\u001c;\u0002%5\u000b\u0005l\u0018+J\u001b\u0016c\u0015JT#`\u0015>\u00135\u000bI\u0001\u0017\u001b\u0006Cv\fV%N\u000b2Ke*R0F1\u0016\u001bU\u000bV(S'\u00069R*\u0011-`)&kU\tT%O\u000b~+\u0005,R\"V)>\u00136\u000bI\u0001\f\u0015>\u00135k\u0018'F\u000f\u0016sE)F\u0001I!\tI\u0005K\u0004\u0002K\u001dB\u00111*O\u0007\u0002\u0019*\u0011Q\nJ\u0001\u0007yI|w\u000e\u001e \n\u0005=K\u0014A\u0002)sK\u0012,g-\u0003\u0002R%\n11\u000b\u001e:j]\u001eT!aT\u001d\u0002\u0019){%iU0M\u000b\u001e+e\n\u0012\u0011\u0002!\u0015CViQ+U\u001fJ\u001bv\fT#H\u000b:#\u0015!E#Y\u000b\u000e+Fk\u0014*T?2+u)\u0012(EA\u0005aQ.Y6f\u0015>\u0014WI^3oiR\u0011\u0001,\u0019\t\u00043zCeB\u0001.]\u001d\tY5,C\u0001;\u0013\ti\u0016(A\u0004qC\u000e\\\u0017mZ3\n\u0005}\u0003'aA*fc*\u0011Q,\u000f\u0005\u0006+9\u0001\rA\u0019\t\u00043z\u001b\u0007C\u00013j\u001b\u0005)'B\u00014h\u0003\t1\u0018G\u0003\u0002iY\u0005\u0019\u0011\r]5\n\u0005),'a\u0002&pE\u0012\u000bG/Y\u0001\u0012[\u0006\\W-\u0012=fGV$xN]#wK:$HC\u0001-n\u0011\u0015qw\u00021\u0001p\u0003%)\u00070Z2vi>\u00148\u000fE\u0002Z=B\u0004\"\u0001Z9\n\u0005I,'aD#yK\u000e,Ho\u001c:Tk6l\u0017M]=\u0002\u00195\f7.\u001a+j[\u0016d\u0017N\\3\u0015\tUdXP \t\u00043z3\bCA<{\u001b\u0005A(BA=:\u0003\rAX\u000e\\\u0005\u0003wb\u0014AAT8eK\")Q\u0003\u0005a\u0001E\")a\u000e\u0005a\u0001_\"1q\u0010\u0005a\u0001\u0003\u0003\t\u0011b\u001d;beR$\u0016.\\3\u0011\u0007a\n\u0019!C\u0002\u0002\u0006e\u0012A\u0001T8oO\u0006I!n\u001c2t)\u0006\u0014G.\u001a\u000b\fk\u0006-\u00111EA\u0014\u0003W\ti\u0003C\u0004\u0002\u000eE\u0001\r!a\u0004\u0002\u000fI,\u0017/^3tiB!\u0011\u0011CA\u0010\u001b\t\t\u0019B\u0003\u0003\u0002\u0016\u0005]\u0011\u0001\u00025uiBTA!!\u0007\u0002\u001c\u000591/\u001a:wY\u0016$(BAA\u000f\u0003\u001dQ\u0017m[1si\u0006LA!!\t\u0002\u0014\t\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0011\u0019\t)#\u0005a\u0001\u0011\u0006iA/\u00192mK\"+\u0017\rZ3s\u0013\u0012Da!!\u000b\u0012\u0001\u0004A\u0015A\u00026pER\u000bw\rC\u0003\u0016#\u0001\u0007!\r\u0003\u0004\u00020E\u0001\raN\u0001\fW&dG.\u00128bE2,G-\u0001\u0004sK:$WM\u001d\u000b\u0004k\u0006U\u0002bBA\u0007%\u0001\u0007\u0011q\u0002"
)
public class AllJobsPage extends WebUIPage {
   private final JobsTab parent;
   private final AppStatusStore store;
   private final boolean TIMELINE_ENABLED;
   private final int MAX_TIMELINE_JOBS;
   private final int MAX_TIMELINE_EXECUTORS;
   private final String JOBS_LEGEND;
   private final String EXECUTORS_LEGEND;

   private boolean TIMELINE_ENABLED() {
      return this.TIMELINE_ENABLED;
   }

   private int MAX_TIMELINE_JOBS() {
      return this.MAX_TIMELINE_JOBS;
   }

   private int MAX_TIMELINE_EXECUTORS() {
      return this.MAX_TIMELINE_EXECUTORS;
   }

   private String JOBS_LEGEND() {
      return this.JOBS_LEGEND;
   }

   private String EXECUTORS_LEGEND() {
      return this.EXECUTORS_LEGEND;
   }

   private Seq makeJobEvent(final Seq jobs) {
      long now = System.currentTimeMillis();
      return (Seq)((IterableOps)((IterableOps)((SeqOps)jobs.filter((job) -> BoxesRunTime.boxToBoolean($anonfun$makeJobEvent$1(job)))).sortBy((j) -> new Tuple2.mcJJ.sp(BoxesRunTime.unboxToLong(j.completionTime().map((x$3) -> BoxesRunTime.boxToLong($anonfun$makeJobEvent$3(x$3))).getOrElse((JFunction0.mcJ.sp)() -> now)), ((Date)j.submissionTime().get()).getTime()), .MODULE$.Tuple2(scala.math.Ordering.Long..MODULE$, scala.math.Ordering.Long..MODULE$))).takeRight(this.MAX_TIMELINE_JOBS())).map((job) -> {
         int jobId = job.jobId();
         JobExecutionStatus status = job.status();
         Tuple2 var9 = ApiHelper$.MODULE$.lastStageNameAndDescription(this.store, job);
         if (var9 != null) {
            String lastStageDescription = (String)var9._2();
            String jobDescription = UIUtils$.MODULE$.makeDescription((String)job.description().getOrElse(() -> lastStageDescription), "", true).text();
            long submissionTime = ((Date)job.submissionTime().get()).getTime();
            long completionTime = BoxesRunTime.unboxToLong(job.completionTime().map((x$4) -> BoxesRunTime.boxToLong($anonfun$makeJobEvent$7(x$4))).getOrElse((JFunction0.mcJ.sp)() -> now));
            String var10000;
            if (JobExecutionStatus.SUCCEEDED.equals(status)) {
               var10000 = "succeeded";
            } else if (JobExecutionStatus.FAILED.equals(status)) {
               var10000 = "failed";
            } else if (JobExecutionStatus.RUNNING.equals(status)) {
               var10000 = "running";
            } else {
               if (!JobExecutionStatus.UNKNOWN.equals(status)) {
                  throw new MatchError(status);
               }

               var10000 = "unknown";
            }

            String classNameByStatus;
            String jsEscapedDescForTooltip;
            String jsEscapedDescForLabel;
            Predef var10001;
            String var10008;
            String var10009;
            label39: {
               label38: {
                  classNameByStatus = var10000;
                  String escapedDesc = scala.xml.Utility..MODULE$.escape(jobDescription);
                  jsEscapedDescForTooltip = StringEscapeUtils.escapeEcmaScript(scala.xml.Utility..MODULE$.escape(escapedDesc));
                  jsEscapedDescForLabel = StringEscapeUtils.escapeEcmaScript(escapedDesc);
                  var23 = scala.collection.StringOps..MODULE$;
                  var10001 = scala.Predef..MODULE$;
                  var10008 = UIUtils$.MODULE$.formatDate(new Date(submissionTime));
                  JobExecutionStatus var22 = JobExecutionStatus.RUNNING;
                  if (status == null) {
                     if (var22 != null) {
                        break label38;
                     }
                  } else if (!status.equals(var22)) {
                     break label38;
                  }

                  var10009 = "";
                  break label39;
               }

               UIUtils$ var24 = UIUtils$.MODULE$;
               var10009 = "<br>Completed: " + var24.formatDate(new Date(completionTime));
            }

            String jobEventJsonAsStr = var23.stripMargin$extension(var10001.augmentString("\n           |{\n           |  'className': 'job application-timeline-object " + classNameByStatus + "',\n           |  'group': 'jobs',\n           |  'start': new Date(" + submissionTime + "),\n           |  'end': new Date(" + completionTime + "),\n           |  'content': '<div class=\"application-timeline-content\"' +\n           |     'data-html=\"true\" data-placement=\"top\" data-toggle=\"tooltip\"' +\n           |     'data-title=\"" + jsEscapedDescForTooltip + " (Job " + jobId + ")<br>' +\n           |     'Status: " + status + "<br>' +\n           |     'Submitted: " + var10008 + "' +\n           |     '" + var10009 + "\">' +\n           |    '" + jsEscapedDescForLabel + " (Job " + jobId + ")</div>'\n           |}\n         "));
            return jobEventJsonAsStr;
         } else {
            throw new MatchError(var9);
         }
      });
   }

   private Seq makeExecutorEvent(final Seq executors) {
      ListBuffer events = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      ((IterableOnceOps)((IterableOps)executors.sortBy((e) -> BoxesRunTime.boxToLong($anonfun$makeExecutorEvent$1(e)), scala.math.Ordering.Long..MODULE$)).takeRight(this.MAX_TIMELINE_EXECUTORS())).foreach((e) -> {
         $anonfun$makeExecutorEvent$4(events, e);
         return BoxedUnit.UNIT;
      });
      return events.toSeq();
   }

   private Seq makeTimeline(final Seq jobs, final Seq executors, final long startTime) {
      if (!this.TIMELINE_ENABLED()) {
         return (Seq)scala.package..MODULE$.Seq().empty();
      } else {
         Seq jobEventJsonAsStrSeq = this.makeJobEvent(jobs);
         Seq executorEventJsonAsStrSeq = this.makeExecutorEvent(executors);
         StringOps var10000 = scala.collection.StringOps..MODULE$;
         Predef var10001 = scala.Predef..MODULE$;
         String var10002 = this.EXECUTORS_LEGEND();
         String groupJsonArrayAsStr = var10000.stripMargin$extension(var10001.augmentString("\n          |[\n          |  {\n          |    'id': 'executors',\n          |    'content': '<div>Executors</div>" + var10002 + "',\n          |  },\n          |  {\n          |    'id': 'jobs',\n          |    'content': '<div>Jobs</div>" + this.JOBS_LEGEND() + "',\n          |  }\n          |]\n        "));
         String eventArrayAsStr = ((IterableOnceOps)jobEventJsonAsStrSeq.$plus$plus(executorEventJsonAsStrSeq)).mkString("[", ",", "]");
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var28 = new UnprefixedAttribute("class", new Text("expand-application-timeline"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var29 = new UnprefixedAttribute("class", new Text("expand-application-timeline-arrow arrow-closed"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var29, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n      "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var30 = new UnprefixedAttribute("data-placement", new Text("top"), $md);
         var30 = new UnprefixedAttribute("title", ToolTips$.MODULE$.JOB_TIMELINE(), var30);
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
         var33 = new UnprefixedAttribute("id", new Text("application-timeline"), var33);
         Elem var41 = new Elem;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         Object var10010;
         if (this.MAX_TIMELINE_JOBS() < jobs.size()) {
            Null var45 = scala.xml.Null..MODULE$;
            TopScope var10015 = scala.xml.TopScope..MODULE$;
            NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            Null var10023 = scala.xml.Null..MODULE$;
            TopScope var10024 = scala.xml.TopScope..MODULE$;
            NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              Only the most recent "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(this.MAX_TIMELINE_JOBS()));
            $buf.$amp$plus(new Text(" submitted/completed jobs\n              (of "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(jobs.size()));
            $buf.$amp$plus(new Text(" total) are shown.\n            "));
            $buf.$amp$plus(new Elem((String)null, "strong", var10023, var10024, false, var10026.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
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
            $buf.$amp$plus(new Text("\n            "));
            Null var51 = scala.xml.Null..MODULE$;
            TopScope var52 = scala.xml.TopScope..MODULE$;
            NodeSeq var54 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              Only the most recent "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(this.MAX_TIMELINE_EXECUTORS()));
            $buf.$amp$plus(new Text(" added/removed executors\n              (of "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(executors.size()));
            $buf.$amp$plus(new Text(" total) are shown.\n            "));
            $buf.$amp$plus(new Elem((String)null, "strong", var51, var52, false, var54.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
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
         MetaData var36 = new UnprefixedAttribute("id", new Text("application-timeline-zoom-lock"), $md);
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
         $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply("drawApplicationTimeline(" + groupJsonArrayAsStr + "," + eventArrayAsStr + ", " + startTime + ", " + UIUtils$.MODULE$.getTimeZoneOffset() + ");"));
         $buf.$amp$plus(new Text("\n    "));
         return var40.$plus$plus(new Elem((String)null, "script", var38, var10006, false, var10008.seqToNodeSeq($buf)));
      }
   }

   private Seq jobsTable(final HttpServletRequest request, final String tableHeaderId, final String jobTag, final Seq jobs, final boolean killEnabled) {
      boolean someJobHasJobGroup = jobs.exists((x$6) -> BoxesRunTime.boxToBoolean($anonfun$jobsTable$1(x$6)));
      String jobIdTitle = someJobHasJobGroup ? "Job Id (Job Group)" : "Job Id";
      int jobPage = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter(jobTag + ".page")).map((x$7) -> BoxesRunTime.boxToInteger($anonfun$jobsTable$2(x$7))).getOrElse((JFunction0.mcI.sp)() -> 1));

      Object var10000;
      try {
         var10000 = (new JobPagedTable(request, this.store, jobs, tableHeaderId, jobTag, UIUtils$.MODULE$.prependBaseUri(request, this.parent.basePath(), UIUtils$.MODULE$.prependBaseUri$default$3()), "jobs", killEnabled, jobIdTitle)).table(jobPage);
      } catch (Throwable var17) {
         if (!(var17 instanceof IllegalArgumentException ? true : var17 instanceof IndexOutOfBoundsException)) {
            throw var17;
         }

         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var18 = new UnprefixedAttribute("class", new Text("alert alert-error"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Error while rendering job table:"));
         $buf.$amp$plus(new Elem((String)null, "p", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(Utils$.MODULE$.exceptionString(var17));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "pre", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         var10000 = new Elem((String)null, "div", var18, var10005, false, var10007.seqToNodeSeq($buf));
      }

      return (Seq)var10000;
   }

   public Seq render(final HttpServletRequest request) {
      ApplicationInfo appInfo = this.store.applicationInfo();
      Date startDate = ((ApplicationAttemptInfo)appInfo.attempts().head()).startTime();
      long startTime = startDate.getTime();
      long endTime = ((ApplicationAttemptInfo)appInfo.attempts().head()).endTime().getTime();
      ListBuffer activeJobs = new ListBuffer();
      ListBuffer completedJobs = new ListBuffer();
      ListBuffer failedJobs = new ListBuffer();
      this.store.jobsList((List)null).foreach((job) -> {
         JobExecutionStatus var5 = job.status();
         if (JobExecutionStatus.SUCCEEDED.equals(var5)) {
            return (ListBuffer)completedJobs.$plus$eq(job);
         } else {
            return JobExecutionStatus.FAILED.equals(var5) ? (ListBuffer)failedJobs.$plus$eq(job) : (ListBuffer)activeJobs.$plus$eq(job);
         }
      });
      Seq activeJobsTable = this.jobsTable(request, "active", "activeJob", activeJobs.toSeq(), this.parent.killEnabled());
      Seq completedJobsTable = this.jobsTable(request, "completed", "completedJob", completedJobs.toSeq(), false);
      Seq failedJobsTable = this.jobsTable(request, "failed", "failedJob", failedJobs.toSeq(), false);
      boolean shouldShowActiveJobs = activeJobs.nonEmpty();
      boolean shouldShowCompletedJobs = completedJobs.nonEmpty();
      boolean shouldShowFailedJobs = failedJobs.nonEmpty();
      AppSummary appSummary = this.store.appSummary();
      String completedJobNumStr = completedJobs.size() == appSummary.numCompletedJobs() ? String.valueOf(BoxesRunTime.boxToInteger(completedJobs.size())) : appSummary.numCompletedJobs() + ", only showing " + completedJobs.size();
      String schedulingMode = (String)this.store.environmentInfo().sparkProperties().toMap(scala..less.colon.less..MODULE$.refl()).get(package$.MODULE$.SCHEDULER_MODE().key()).map((mode) -> SchedulingMode$.MODULE$.withName(mode.toUpperCase(Locale.ROOT)).toString()).getOrElse(() -> "Unknown");
      Elem var10000 = new Elem;
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var68 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
      Elem var10009 = new Elem;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      Null var10022 = scala.xml.Null..MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n\n            "));
      Null var10031 = scala.xml.Null..MODULE$;
      TopScope var10032 = scala.xml.TopScope..MODULE$;
      NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("User:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(this.parent.getSparkUser());
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
      $buf.$amp$plus(new Text("Started At:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(UIUtils$.MODULE$.formatDate(startDate));
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
      $buf.$amp$plus(new Text("Total Uptime:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(endTime < 0L && this.parent.sc().isDefined() ? UIUtils$.MODULE$.formatDuration(System.currentTimeMillis() - startTime) : (endTime > 0L ? UIUtils$.MODULE$.formatDuration(endTime - startTime) : BoxedUnit.UNIT));
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
      $buf.$amp$plus(new Text("Scheduling Mode: "));
      $buf.$amp$plus(new Elem((String)null, "strong", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(schedulingMode);
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      Object var10018;
      if (shouldShowActiveJobs) {
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var69 = new UnprefixedAttribute("href", new Text("#active"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         Null var10040 = scala.xml.Null..MODULE$;
         TopScope var10041 = scala.xml.TopScope..MODULE$;
         NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Active Jobs:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Elem((String)null, "a", var69, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(activeJobs.size()));
         $buf.$amp$plus(new Text("\n              "));
         var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
      } else {
         var10018 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10018);
      $buf.$amp$plus(new Text("\n          "));
      if (shouldShowCompletedJobs) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var70 = new UnprefixedAttribute("id", new Text("completed-summary"), $md);
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var71 = new UnprefixedAttribute("href", new Text("#completed"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         Null var151 = scala.xml.Null..MODULE$;
         TopScope var153 = scala.xml.TopScope..MODULE$;
         NodeSeq var155 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Completed Jobs:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var151, var153, false, var155.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Elem((String)null, "a", var71, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                "));
         $buf.$amp$plus(completedJobNumStr);
         $buf.$amp$plus(new Text("\n              "));
         var10018 = new Elem((String)null, "li", var70, var10023, false, var10025.seqToNodeSeq($buf));
      } else {
         var10018 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10018);
      $buf.$amp$plus(new Text("\n          "));
      if (shouldShowFailedJobs) {
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var72 = new UnprefixedAttribute("href", new Text("#failed"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         Null var152 = scala.xml.Null..MODULE$;
         TopScope var154 = scala.xml.TopScope..MODULE$;
         NodeSeq var156 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Failed Jobs:"));
         $buf.$amp$plus(new Elem((String)null, "strong", var152, var154, false, var156.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Elem((String)null, "a", var72, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(failedJobs.size()));
         $buf.$amp$plus(new Text("\n              "));
         var10018 = new Elem((String)null, "li", var10022, var10023, false, var10025.seqToNodeSeq($buf));
      } else {
         var10018 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10018);
      $buf.$amp$plus(new Text("\n        "));
      var10009.<init>((String)null, "ul", var68, var10014, false, var10016.seqToNodeSeq($buf));
      $buf.$amp$plus(var10009);
      $buf.$amp$plus(new Text("\n      "));
      var10000.<init>((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      NodeSeq summary = var10000;
      ObjectRef content = ObjectRef.create(summary);
      content.elem = ((NodeSeq)content.elem).$plus$plus(this.makeTimeline(((IterableOnceOps)((IterableOps)activeJobs.$plus$plus(completedJobs)).$plus$plus(failedJobs)).toSeq(), this.store.executorList(false), startTime));
      if (shouldShowActiveJobs) {
         NodeSeq var10001 = (NodeSeq)content.elem;
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var73 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-activeJobs','aggregated-activeJobs')"), $md);
         var73 = new UnprefixedAttribute("class", new Text("collapse-aggregated-activeJobs collapse-table"), var73);
         var73 = new UnprefixedAttribute("id", new Text("active"), var73);
         TopScope var92 = scala.xml.TopScope..MODULE$;
         NodeSeq var97 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10015 = scala.xml.Null..MODULE$;
         TopScope var104 = scala.xml.TopScope..MODULE$;
         NodeSeq var109 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var76 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var76, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n            "));
         Null var10024 = scala.xml.Null..MODULE$;
         TopScope var131 = scala.xml.TopScope..MODULE$;
         NodeSeq var10027 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Active Jobs ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(activeJobs.size()));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var10024, var131, false, var10027.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "h4", var10015, var104, false, var109.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         Elem var10002 = new Elem((String)null, "span", var73, var92, false, var97.seqToNodeSeq($buf));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var77 = new UnprefixedAttribute("class", new Text("aggregated-activeJobs collapsible-table"), $md);
         TopScope var10008 = scala.xml.TopScope..MODULE$;
         NodeSeq var10010 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(activeJobsTable);
         $buf.$amp$plus(new Text("\n        "));
         content.elem = var10001.$plus$plus(var10002.$plus$plus(new Elem((String)null, "div", var77, var10008, false, var10010.seqToNodeSeq($buf))));
      }

      if (shouldShowCompletedJobs) {
         NodeSeq var88 = (NodeSeq)content.elem;
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var78 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-completedJobs','aggregated-completedJobs')"), $md);
         var78 = new UnprefixedAttribute("class", new Text("collapse-aggregated-completedJobs collapse-table"), var78);
         var78 = new UnprefixedAttribute("id", new Text("completed"), var78);
         TopScope var93 = scala.xml.TopScope..MODULE$;
         NodeSeq var98 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var102 = scala.xml.Null..MODULE$;
         TopScope var105 = scala.xml.TopScope..MODULE$;
         NodeSeq var110 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var81 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var81, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n            "));
         Null var123 = scala.xml.Null..MODULE$;
         TopScope var132 = scala.xml.TopScope..MODULE$;
         NodeSeq var134 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Completed Jobs ("));
         $buf.$amp$plus(completedJobNumStr);
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var123, var132, false, var134.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "h4", var102, var105, false, var110.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         Elem var90 = new Elem((String)null, "span", var78, var93, false, var98.seqToNodeSeq($buf));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var82 = new UnprefixedAttribute("class", new Text("aggregated-completedJobs collapsible-table"), $md);
         TopScope var95 = scala.xml.TopScope..MODULE$;
         NodeSeq var100 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(completedJobsTable);
         $buf.$amp$plus(new Text("\n        "));
         content.elem = var88.$plus$plus(var90.$plus$plus(new Elem((String)null, "div", var82, var95, false, var100.seqToNodeSeq($buf))));
      }

      if (shouldShowFailedJobs) {
         NodeSeq var89 = (NodeSeq)content.elem;
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var83 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-failedJobs','aggregated-failedJobs')"), $md);
         var83 = new UnprefixedAttribute("class", new Text("collapse-aggregated-failedJobs collapse-table"), var83);
         var83 = new UnprefixedAttribute("id", new Text("failed"), var83);
         TopScope var94 = scala.xml.TopScope..MODULE$;
         NodeSeq var99 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var103 = scala.xml.Null..MODULE$;
         TopScope var106 = scala.xml.TopScope..MODULE$;
         NodeSeq var111 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var86 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var86, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         $buf.$amp$plus(new Text("\n            "));
         Null var124 = scala.xml.Null..MODULE$;
         TopScope var133 = scala.xml.TopScope..MODULE$;
         NodeSeq var135 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Failed Jobs ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(failedJobs.size()));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var124, var133, false, var135.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "h4", var103, var106, false, var111.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         Elem var91 = new Elem((String)null, "span", var83, var94, false, var99.seqToNodeSeq($buf));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var87 = new UnprefixedAttribute("class", new Text("aggregated-failedJobs collapsible-table"), $md);
         TopScope var96 = scala.xml.TopScope..MODULE$;
         NodeSeq var101 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(failedJobsTable);
         $buf.$amp$plus(new Text("\n      "));
         content.elem = var89.$plus$plus(var91.$plus$plus(new Elem((String)null, "div", var87, var96, false, var101.seqToNodeSeq($buf))));
      }

      String helpText = "A job is triggered by an action, like count() or saveAsTextFile(). Click on a job to see information about the stages of tasks inside it.";
      return UIUtils$.MODULE$.headerSparkPage(request, "Spark Jobs", () -> (NodeSeq)content.elem, this.parent, new Some(helpText), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$JOBS_LEGEND$1(final char x$1) {
      return x$1 != '\n';
   }

   // $FF: synthetic method
   public static final boolean $anonfun$EXECUTORS_LEGEND$1(final char x$2) {
      return x$2 != '\n';
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeJobEvent$1(final JobData job) {
      boolean var2;
      label18: {
         JobExecutionStatus var10000 = job.status();
         JobExecutionStatus var1 = JobExecutionStatus.UNKNOWN;
         if (var10000 == null) {
            if (var1 == null) {
               break label18;
            }
         } else if (var10000.equals(var1)) {
            break label18;
         }

         if (job.submissionTime().isDefined()) {
            var2 = true;
            return var2;
         }
      }

      var2 = false;
      return var2;
   }

   // $FF: synthetic method
   public static final long $anonfun$makeJobEvent$3(final Date x$3) {
      return x$3.getTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$makeJobEvent$7(final Date x$4) {
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
   public static final boolean $anonfun$jobsTable$1(final JobData x$6) {
      return x$6.jobGroup().isDefined();
   }

   // $FF: synthetic method
   public static final int $anonfun$jobsTable$2(final String x$7) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$7));
   }

   public AllJobsPage(final JobsTab parent, final AppStatusStore store) {
      super("");
      this.parent = parent;
      this.store = store;
      this.TIMELINE_ENABLED = BoxesRunTime.unboxToBoolean(parent.conf().get(UI$.MODULE$.UI_TIMELINE_ENABLED()));
      this.MAX_TIMELINE_JOBS = BoxesRunTime.unboxToInt(parent.conf().get(UI$.MODULE$.UI_TIMELINE_JOBS_MAXIMUM()));
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
      var29 = new UnprefixedAttribute("class", new Text("succeeded-job-legend"), var29);
      $buf.$amp$plus(new Elem((String)null, "rect", var29, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var36 = new UnprefixedAttribute("y", new Text("17px"), $md);
      var36 = new UnprefixedAttribute("x", new Text("35px"), var36);
      TopScope var10026 = scala.xml.TopScope..MODULE$;
      NodeSeq var10028 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Succeeded"));
      $buf.$amp$plus(new Elem((String)null, "text", var36, var10026, false, var10028.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var38 = new UnprefixedAttribute("ry", new Text("2px"), $md);
      var38 = new UnprefixedAttribute("rx", new Text("2px"), var38);
      var38 = new UnprefixedAttribute("height", new Text("15px"), var38);
      var38 = new UnprefixedAttribute("width", new Text("20px"), var38);
      var38 = new UnprefixedAttribute("y", new Text("30px"), var38);
      var38 = new UnprefixedAttribute("x", new Text("5px"), var38);
      var38 = new UnprefixedAttribute("class", new Text("failed-job-legend"), var38);
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
      var47 = new UnprefixedAttribute("class", new Text("running-job-legend"), var47);
      $buf.$amp$plus(new Elem((String)null, "rect", var47, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var54 = new UnprefixedAttribute("y", new Text("67px"), $md);
      var54 = new UnprefixedAttribute("x", new Text("35px"), var54);
      var10026 = scala.xml.TopScope..MODULE$;
      var10028 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Running"));
      $buf.$amp$plus(new Elem((String)null, "text", var54, var10026, false, var10028.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      $buf.$amp$plus(new Elem((String)null, "svg", var27, var10017, false, var10019.seqToNodeSeq($buf)));
      this.JOBS_LEGEND = var10001.filter$extension(var10002.augmentString((new Elem((String)null, "div", var26, var10008, false, var10010.seqToNodeSeq($buf))).toString()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$JOBS_LEGEND$1(BoxesRunTime.unboxToChar(x$1))));
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
