package org.apache.spark.ui.jobs;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.ui.PagedDataSource;
import org.apache.spark.ui.UIUtils$;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.xml.NodeSeq;

@ScalaSignature(
   bytes = "\u0006\u0005m4QAD\b\u0001#eA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\t!\u0001\u0011\t\u0011)A\u0005U!A\u0001\t\u0001B\u0001B\u0003%\u0011\tC\u0005J\u0001\t\u0005\t\u0015!\u0003K\u001d\"Aq\n\u0001B\u0001B\u0003%\u0011\t\u0003\u0005Q\u0001\t\u0005\t\u0015!\u0003R\u0011\u0015!\u0006\u0001\"\u0001V\u0011\u001di\u0006A1A\u0005\nyCaA\u001a\u0001!\u0002\u0013y\u0006\"B4\u0001\t\u0003B\u0007\"B5\u0001\t\u0003R\u0007\"\u00029\u0001\t\u0013\t\b\"\u0002;\u0001\t\u0013)(!\u0004&pE\u0012\u000bG/Y*pkJ\u001cWM\u0003\u0002\u0011#\u0005!!n\u001c2t\u0015\t\u00112#\u0001\u0002vS*\u0011A#F\u0001\u0006gB\f'o\u001b\u0006\u0003-]\ta!\u00199bG\",'\"\u0001\r\u0002\u0007=\u0014xm\u0005\u0002\u00015A\u00191\u0004\b\u0010\u000e\u0003EI!!H\t\u0003\u001fA\u000bw-\u001a3ECR\f7k\\;sG\u0016\u0004\"a\b\u0011\u000e\u0003=I!!I\b\u0003\u001f){'\rV1cY\u0016\u0014vn\u001e#bi\u0006\fQa\u001d;pe\u0016\u001c\u0001\u0001\u0005\u0002&Q5\taE\u0003\u0002('\u000511\u000f^1ukNL!!\u000b\u0014\u0003\u001d\u0005\u0003\bo\u0015;biV\u001c8\u000b^8sKB\u00191&\u000e\u001d\u000f\u00051\u0012dBA\u00171\u001b\u0005q#BA\u0018$\u0003\u0019a$o\\8u}%\t\u0011'A\u0003tG\u0006d\u0017-\u0003\u00024i\u00059\u0001/Y2lC\u001e,'\"A\u0019\n\u0005Y:$aA*fc*\u00111\u0007\u000e\t\u0003syj\u0011A\u000f\u0006\u0003wq\n!A^\u0019\u000b\u0005u2\u0013aA1qS&\u0011qH\u000f\u0002\b\u0015>\u0014G)\u0019;b\u0003!\u0011\u0017m]3QCRD\u0007C\u0001\"G\u001d\t\u0019E\t\u0005\u0002.i%\u0011Q\tN\u0001\u0007!J,G-\u001a4\n\u0005\u001dC%AB*ue&twM\u0003\u0002Fi\u0005A\u0001/Y4f'&TX\r\u0005\u0002L\u00196\tA'\u0003\u0002Ni\t\u0019\u0011J\u001c;\n\u0005%c\u0012AC:peR\u001cu\u000e\\;n]\u0006!A-Z:d!\tY%+\u0003\u0002Ti\t9!i\\8mK\u0006t\u0017A\u0002\u001fj]&$h\bF\u0004W/bK&l\u0017/\u0011\u0005}\u0001\u0001\"\u0002\u0012\b\u0001\u0004!\u0003\"\u0002\t\b\u0001\u0004Q\u0003\"\u0002!\b\u0001\u0004\t\u0005\"B%\b\u0001\u0004Q\u0005\"B(\b\u0001\u0004\t\u0005\"\u0002)\b\u0001\u0004\t\u0016\u0001\u00023bi\u0006,\u0012a\u0018\t\u0004A\u0016tR\"A1\u000b\u0005\t\u001c\u0017!C5n[V$\u0018M\u00197f\u0015\t!G'\u0001\u0006d_2dWm\u0019;j_:L!AN1\u0002\u000b\u0011\fG/\u0019\u0011\u0002\u0011\u0011\fG/Y*ju\u0016,\u0012AS\u0001\ng2L7-\u001a#bi\u0006$2a\u001b7o!\rYSG\b\u0005\u0006[.\u0001\rAS\u0001\u0005MJ|W\u000eC\u0003p\u0017\u0001\u0007!*\u0001\u0002u_\u00061!n\u001c2S_^$\"A\b:\t\u000bMd\u0001\u0019\u0001\u001d\u0002\u000f)|'\rR1uC\u0006AqN\u001d3fe&tw\rF\u0002wsj\u00042aK<\u001f\u0013\tAxG\u0001\u0005Pe\u0012,'/\u001b8h\u0011\u0015yU\u00021\u0001B\u0011\u0015\u0001V\u00021\u0001R\u0001"
)
public class JobDataSource extends PagedDataSource {
   private final AppStatusStore store;
   private final String basePath;
   private final Seq data;

   private Seq data() {
      return this.data;
   }

   public int dataSize() {
      return this.data().size();
   }

   public Seq sliceData(final int from, final int to) {
      return (Seq)this.data().slice(from, to);
   }

   private JobTableRowData jobRow(final JobData jobData) {
      Option duration = JobDataUtil$.MODULE$.getDuration(jobData);
      String formattedDuration = JobDataUtil$.MODULE$.getFormattedDuration(jobData);
      Option submissionTime = jobData.submissionTime();
      String formattedSubmissionTime = JobDataUtil$.MODULE$.getFormattedSubmissionTime(jobData);
      Tuple2 var8 = ApiHelper$.MODULE$.lastStageNameAndDescription(this.store, jobData);
      if (var8 != null) {
         String lastStageName = (String)var8._1();
         String lastStageDescription = (String)var8._2();
         Tuple2 var7 = new Tuple2(lastStageName, lastStageDescription);
         String lastStageName = (String)var7._1();
         String lastStageDescription = (String)var7._2();
         NodeSeq jobDescription = UIUtils$.MODULE$.makeDescription((String)jobData.description().getOrElse(() -> lastStageDescription), this.basePath, false);
         String detailUrl = .MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s/jobs/job/?id=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.basePath, BoxesRunTime.boxToInteger(jobData.jobId())}));
         return new JobTableRowData(jobData, lastStageName, lastStageDescription, BoxesRunTime.unboxToLong(duration.getOrElse((JFunction0.mcJ.sp)() -> -1L)), formattedDuration, BoxesRunTime.unboxToLong(submissionTime.map((x$9) -> BoxesRunTime.boxToLong($anonfun$jobRow$3(x$9))).getOrElse((JFunction0.mcJ.sp)() -> -1L)), formattedSubmissionTime, jobDescription, detailUrl);
      } else {
         throw new MatchError(var8);
      }
   }

   private Ordering ordering(final String sortColumn, final boolean desc) {
      Ordering var10000;
      label56: {
         switch (sortColumn == null ? 0 : sortColumn.hashCode()) {
            case -2070946658:
               if (!"Job Id".equals(sortColumn)) {
                  throw new IllegalArgumentException("Unknown column: " + sortColumn);
               }
               break;
            case -1942320933:
               if (!"Submitted".equals(sortColumn)) {
                  throw new IllegalArgumentException("Unknown column: " + sortColumn);
               }

               var10000 = scala.package..MODULE$.Ordering().by((x$11) -> BoxesRunTime.boxToLong($anonfun$ordering$3(x$11)), scala.math.Ordering.Long..MODULE$);
               break label56;
            case -1927368268:
               if (!"Duration".equals(sortColumn)) {
                  throw new IllegalArgumentException("Unknown column: " + sortColumn);
               }

               var10000 = scala.package..MODULE$.Ordering().by((x$12) -> BoxesRunTime.boxToLong($anonfun$ordering$4(x$12)), scala.math.Ordering.Long..MODULE$);
               break label56;
            case -168253133:
               if (!"Job Id (Job Group)".equals(sortColumn)) {
                  throw new IllegalArgumentException("Unknown column: " + sortColumn);
               }
               break;
            case -56677412:
               if (!"Description".equals(sortColumn)) {
                  throw new IllegalArgumentException("Unknown column: " + sortColumn);
               }

               var10000 = scala.package..MODULE$.Ordering().by((x) -> new Tuple2(x.lastStageDescription(), x.lastStageName()), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$));
               break label56;
            case 1981537384:
               if ("Tasks (for all stages): Succeeded/Total".equals(sortColumn)) {
                  throw new IllegalArgumentException("Unsortable column: " + sortColumn);
               }

               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            case 2094321979:
               if ("Stages: Succeeded/Total".equals(sortColumn)) {
                  throw new IllegalArgumentException("Unsortable column: " + sortColumn);
               }

               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            default:
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
         }

         var10000 = scala.package..MODULE$.Ordering().by((x$10) -> BoxesRunTime.boxToInteger($anonfun$ordering$1(x$10)), scala.math.Ordering.Int..MODULE$);
      }

      Ordering ordering = var10000;
      if (desc) {
         return ordering.reverse();
      } else {
         return ordering;
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$jobRow$3(final Date x$9) {
      return x$9.getTime();
   }

   // $FF: synthetic method
   public static final int $anonfun$ordering$1(final JobTableRowData x$10) {
      return x$10.jobData().jobId();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$3(final JobTableRowData x$11) {
      return x$11.submissionTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$4(final JobTableRowData x$12) {
      return x$12.duration();
   }

   public JobDataSource(final AppStatusStore store, final Seq jobs, final String basePath, final int pageSize, final String sortColumn, final boolean desc) {
      super(pageSize);
      this.store = store;
      this.basePath = basePath;
      this.data = (Seq)((SeqOps)jobs.map((jobData) -> this.jobRow(jobData))).sorted(this.ordering(sortColumn, desc));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
