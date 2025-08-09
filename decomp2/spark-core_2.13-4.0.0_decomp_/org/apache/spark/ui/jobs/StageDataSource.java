package org.apache.spark.ui.jobs;

import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.ui.PagedDataSource;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005}4QAD\b\u0001#eA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tU\u0001\u0011\t\u0011)A\u0005W!A\u0011\t\u0001B\u0001B\u0003%!\tC\u0005G\u0001\t\u0005\t\u0015!\u0003H\u0015\"A1\n\u0001B\u0001B\u0003%A\n\u0003\u0005U\u0001\t\u0005\t\u0015!\u0003V\u0011\u0015A\u0006\u0001\"\u0001Z\u0011\u001d\t\u0007A1A\u0005\n\tDaA\u001b\u0001!\u0002\u0013\u0019\u0007\"B6\u0001\t\u0003b\u0007\"B7\u0001\t\u0003r\u0007\"\u0002;\u0001\t\u0013)\b\"\u0002=\u0001\t\u0013I(aD*uC\u001e,G)\u0019;b'>,(oY3\u000b\u0005A\t\u0012\u0001\u00026pENT!AE\n\u0002\u0005UL'B\u0001\u000b\u0016\u0003\u0015\u0019\b/\u0019:l\u0015\t1r#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00021\u0005\u0019qN]4\u0014\u0005\u0001Q\u0002cA\u000e\u001d=5\t\u0011#\u0003\u0002\u001e#\ty\u0001+Y4fI\u0012\u000bG/Y*pkJ\u001cW\r\u0005\u0002 A5\tq\"\u0003\u0002\"\u001f\t\t2\u000b^1hKR\u000b'\r\\3S_^$\u0015\r^1\u0002\u000bM$xN]3\u0004\u0001A\u0011Q\u0005K\u0007\u0002M)\u0011qeE\u0001\u0007gR\fG/^:\n\u0005%2#AD!qaN#\u0018\r^;t'R|'/Z\u0001\u0007gR\fw-Z:\u0011\u000712\u0014H\u0004\u0002.g9\u0011a&M\u0007\u0002_)\u0011\u0001gI\u0001\u0007yI|w\u000e\u001e \n\u0003I\nQa]2bY\u0006L!\u0001N\u001b\u0002\u000fA\f7m[1hK*\t!'\u0003\u00028q\t\u00191+Z9\u000b\u0005Q*\u0004C\u0001\u001e@\u001b\u0005Y$B\u0001\u001f>\u0003\t1\u0018G\u0003\u0002?M\u0005\u0019\u0011\r]5\n\u0005\u0001[$!C*uC\u001e,G)\u0019;b\u0003-\u0019WO\u001d:f]R$\u0016.\\3\u0011\u0005\r#U\"A\u001b\n\u0005\u0015+$\u0001\u0002'p]\u001e\f\u0001\u0002]1hKNK'0\u001a\t\u0003\u0007\"K!!S\u001b\u0003\u0007%sG/\u0003\u0002G9\u0005Q1o\u001c:u\u0007>dW/\u001c8\u0011\u00055\u000bfB\u0001(P!\tqS'\u0003\u0002Qk\u00051\u0001K]3eK\u001aL!AU*\u0003\rM#(/\u001b8h\u0015\t\u0001V'\u0001\u0003eKN\u001c\u0007CA\"W\u0013\t9VGA\u0004C_>dW-\u00198\u0002\rqJg.\u001b;?)\u001dQ6\fX/_?\u0002\u0004\"a\b\u0001\t\u000b\t:\u0001\u0019\u0001\u0013\t\u000b):\u0001\u0019A\u0016\t\u000b\u0005;\u0001\u0019\u0001\"\t\u000b\u0019;\u0001\u0019A$\t\u000b-;\u0001\u0019\u0001'\t\u000bQ;\u0001\u0019A+\u0002\t\u0011\fG/Y\u000b\u0002GB\u0019A-\u001b\u0010\u000e\u0003\u0015T!AZ4\u0002\u0013%lW.\u001e;bE2,'B\u000156\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003o\u0015\fQ\u0001Z1uC\u0002\n\u0001\u0002Z1uCNK'0Z\u000b\u0002\u000f\u0006I1\u000f\\5dK\u0012\u000bG/\u0019\u000b\u0004_B\u0014\bc\u0001\u00177=!)\u0011o\u0003a\u0001\u000f\u0006!aM]8n\u0011\u0015\u00198\u00021\u0001H\u0003\t!x.\u0001\u0005ti\u0006<WMU8x)\tqb\u000fC\u0003x\u0019\u0001\u0007\u0011(A\u0005ti\u0006<W\rR1uC\u0006AqN\u001d3fe&tw\rF\u0002{{z\u00042\u0001L>\u001f\u0013\ta\bH\u0001\u0005Pe\u0012,'/\u001b8h\u0011\u0015YU\u00021\u0001M\u0011\u0015!V\u00021\u0001V\u0001"
)
public class StageDataSource extends PagedDataSource {
   private final long currentTime;
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

   private StageTableRowData stageRow(final StageData stageData) {
      Option var4 = stageData.submissionTime();
      String var10000;
      if (var4 instanceof Some var5) {
         Date t = (Date)var5.value();
         var10000 = UIUtils$.MODULE$.formatDate(t);
      } else {
         if (!.MODULE$.equals(var4)) {
            throw new MatchError(var4);
         }

         var10000 = "Unknown";
      }

      String formattedSubmissionTime = var10000;
      long finishTime = BoxesRunTime.unboxToLong(stageData.completionTime().map((x$4) -> BoxesRunTime.boxToLong($anonfun$stageRow$1(x$4))).getOrElse((JFunction0.mcJ.sp)() -> this.currentTime));
      Option duration = stageData.firstTaskLaunchedTime().map((date) -> BoxesRunTime.boxToLong($anonfun$stageRow$3(this, finishTime, date)));
      String formattedDuration = (String)duration.map((d) -> $anonfun$stageRow$4(BoxesRunTime.unboxToLong(d))).getOrElse(() -> "Unknown");
      long inputRead = stageData.inputBytes();
      String inputReadWithUnit = inputRead > 0L ? Utils$.MODULE$.bytesToString(inputRead) : "";
      long outputWrite = stageData.outputBytes();
      String outputWriteWithUnit = outputWrite > 0L ? Utils$.MODULE$.bytesToString(outputWrite) : "";
      long shuffleRead = stageData.shuffleReadBytes();
      String shuffleReadWithUnit = shuffleRead > 0L ? Utils$.MODULE$.bytesToString(shuffleRead) : "";
      long shuffleWrite = stageData.shuffleWriteBytes();
      String shuffleWriteWithUnit = shuffleWrite > 0L ? Utils$.MODULE$.bytesToString(shuffleWrite) : "";
      return new StageTableRowData(stageData, new Some(stageData), stageData.stageId(), stageData.attemptId(), stageData.schedulingPool(), stageData.description(), (Date)stageData.submissionTime().getOrElse(() -> new Date(0L)), formattedSubmissionTime, BoxesRunTime.unboxToLong(duration.getOrElse((JFunction0.mcJ.sp)() -> -1L)), formattedDuration, inputRead, inputReadWithUnit, outputWrite, outputWriteWithUnit, shuffleRead, shuffleReadWithUnit, shuffleWrite, shuffleWriteWithUnit);
   }

   private Ordering ordering(final String sortColumn, final boolean desc) {
      Ordering var10000;
      switch (sortColumn == null ? 0 : sortColumn.hashCode()) {
         case -1942320933:
            if (!"Submitted".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$7) -> x$7.submissionTime(), scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms()));
            break;
         case -1927368268:
            if (!"Duration".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$8) -> BoxesRunTime.boxToLong($anonfun$ordering$5(x$8)), scala.math.Ordering.Long..MODULE$);
            break;
         case -1921645279:
            if (!"Output".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$10) -> BoxesRunTime.boxToLong($anonfun$ordering$7(x$10)), scala.math.Ordering.Long..MODULE$);
            break;
         case -685632584:
            if (!"Shuffle Write".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$12) -> BoxesRunTime.boxToLong($anonfun$ordering$9(x$12)), scala.math.Ordering.Long..MODULE$);
            break;
         case -56677412:
            if (!"Description".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x) -> new Tuple2(x.descriptionOption(), x.stage().name()), scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering..MODULE$.Option(scala.math.Ordering.String..MODULE$), scala.math.Ordering.String..MODULE$));
            break;
         case 70805418:
            if (!"Input".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$9) -> BoxesRunTime.boxToLong($anonfun$ordering$6(x$9)), scala.math.Ordering.Long..MODULE$);
            break;
         case 168564751:
            if (!"Pool Name".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$6) -> x$6.schedulingPool(), scala.math.Ordering.String..MODULE$);
            break;
         case 393363101:
            if (!"Shuffle Read".equals(sortColumn)) {
               throw new IllegalArgumentException("Unknown column: " + sortColumn);
            }

            var10000 = scala.package..MODULE$.Ordering().by((x$11) -> BoxesRunTime.boxToLong($anonfun$ordering$8(x$11)), scala.math.Ordering.Long..MODULE$);
            break;
         case 1268589634:
            if ("Tasks: Succeeded/Total".equals(sortColumn)) {
               throw new IllegalArgumentException("Unsortable column: " + sortColumn);
            }

            throw new IllegalArgumentException("Unknown column: " + sortColumn);
         case 1370773949:
            if ("Stage Id".equals(sortColumn)) {
               var10000 = scala.package..MODULE$.Ordering().by((x$5) -> BoxesRunTime.boxToInteger($anonfun$ordering$1(x$5)), scala.math.Ordering.Int..MODULE$);
               break;
            }

            throw new IllegalArgumentException("Unknown column: " + sortColumn);
         default:
            throw new IllegalArgumentException("Unknown column: " + sortColumn);
      }

      Ordering ordering = var10000;
      if (desc) {
         return ordering.reverse();
      } else {
         return ordering;
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$stageRow$1(final Date x$4) {
      return x$4.getTime();
   }

   // $FF: synthetic method
   public static final long $anonfun$stageRow$3(final StageDataSource $this, final long finishTime$1, final Date date) {
      long time = date.getTime();
      if (finishTime$1 > time) {
         return finishTime$1 - time;
      } else {
         None var10000 = .MODULE$;
         return $this.currentTime - time;
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$stageRow$4(final long d) {
      return UIUtils$.MODULE$.formatDuration(d);
   }

   // $FF: synthetic method
   public static final int $anonfun$ordering$1(final StageTableRowData x$5) {
      return x$5.stageId();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$5(final StageTableRowData x$8) {
      return x$8.duration();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$6(final StageTableRowData x$9) {
      return x$9.inputRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$7(final StageTableRowData x$10) {
      return x$10.outputWrite();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$8(final StageTableRowData x$11) {
      return x$11.shuffleRead();
   }

   // $FF: synthetic method
   public static final long $anonfun$ordering$9(final StageTableRowData x$12) {
      return x$12.shuffleWrite();
   }

   public StageDataSource(final AppStatusStore store, final Seq stages, final long currentTime, final int pageSize, final String sortColumn, final boolean desc) {
      super(pageSize);
      this.currentTime = currentTime;
      this.data = (Seq)((SeqOps)stages.map((stageData) -> this.stageRow(stageData))).sorted(this.ordering(sortColumn, desc));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
