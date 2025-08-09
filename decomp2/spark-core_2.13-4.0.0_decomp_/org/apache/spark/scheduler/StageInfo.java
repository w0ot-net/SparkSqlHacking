package org.apache.spark.scheduler;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.executor.TaskMetrics;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\t]b\u0001\u0002\u001e<\u0001\u0011C\u0001b\u0013\u0001\u0003\u0006\u0004%\t\u0001\u0014\u0005\t!\u0002\u0011\t\u0011)A\u0005\u001b\"A\u0011\u000b\u0001BC\u0002\u0013%A\n\u0003\u0005S\u0001\t\u0005\t\u0015!\u0003N\u0011!\u0019\u0006A!b\u0001\n\u0003!\u0006\u0002\u00031\u0001\u0005\u0003\u0005\u000b\u0011B+\t\u0011\u0005\u0004!Q1A\u0005\u00021C\u0001B\u0019\u0001\u0003\u0002\u0003\u0006I!\u0014\u0005\tG\u0002\u0011)\u0019!C\u0001I\"AA\u000f\u0001B\u0001B\u0003%Q\r\u0003\u0005v\u0001\t\u0015\r\u0011\"\u0001w\u0011!A\bA!A!\u0002\u00139\b\u0002C=\u0001\u0005\u000b\u0007I\u0011\u0001+\t\u0011i\u0004!\u0011!Q\u0001\nUC\u0001b\u001f\u0001\u0003\u0006\u0004%\t\u0001 \u0005\n\u0003\u000f\u0001!\u0011!Q\u0001\nuD1\"!\u0003\u0001\u0005\u000b\u0007I\u0011A\u001f\u0002\f!Q\u0011\u0011\u0004\u0001\u0003\u0002\u0003\u0006I!!\u0004\t\u0017\u0005m\u0001A!b\u0001\n\u0003i\u0014Q\u0004\u0005\u000b\u0003K\u0001!\u0011!Q\u0001\n\u0005}\u0001\"CA\u0014\u0001\t\u0015\r\u0011\"\u0001M\u0011%\tI\u0003\u0001B\u0001B\u0003%Q\nC\u0006\u0002,\u0001\u0011\t\u0019!C\u0001{\u00055\u0002bCA\u001b\u0001\t\u0005\r\u0011\"\u0001>\u0003oA!\"a\u0011\u0001\u0005\u0003\u0005\u000b\u0015BA\u0018\u0011)\t)\u0005\u0001BA\u0002\u0013\u0005Q\b\u0014\u0005\f\u0003\u000f\u0002!\u00111A\u0005\u0002u\nI\u0005C\u0005\u0002N\u0001\u0011\t\u0011)Q\u0005\u001b\"9\u0011q\n\u0001\u0005\u0002\u0005E\u0003\"CA8\u0001\u0001\u0007I\u0011AA9\u0011%\tY\b\u0001a\u0001\n\u0003\ti\b\u0003\u0005\u0002\u0002\u0002\u0001\u000b\u0015BA:\u0011%\t\u0019\t\u0001a\u0001\n\u0003\t\t\bC\u0005\u0002\u0006\u0002\u0001\r\u0011\"\u0001\u0002\b\"A\u00111\u0012\u0001!B\u0013\t\u0019\bC\u0005\u0002\u000e\u0002\u0001\r\u0011\"\u0001\u0002\u0010\"I\u00111\u0013\u0001A\u0002\u0013\u0005\u0011Q\u0013\u0005\t\u00033\u0003\u0001\u0015)\u0003\u0002\u0012\"I\u00111\u0014\u0001C\u0002\u0013\u0005\u0011Q\u0014\u0005\t\u0003k\u0003\u0001\u0015!\u0003\u0002 \"9\u0011q\u0017\u0001\u0005\u0002\u0005e\u0006bBA`\u0001\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003\u0007\u0004A\u0011A\u001fU\u0011!\t)\r\u0001C\u0001{\u0005\u001d\u0007\u0002CAg\u0001\u0011\u0005Q(a4\b\u0011\u0005\r8\b#\u0001>\u0003K4qAO\u001e\t\u0002u\n9\u000fC\u0004\u0002P=\"\t!!;\t\u000f\u0005-x\u0006\"\u0001\u0002n\"I!1A\u0018\u0012\u0002\u0013\u0005!Q\u0001\u0005\n\u00053y\u0013\u0013!C\u0001\u00057A\u0011Ba\b0#\u0003%\tA!\t\t\u0013\t\u0015r&%A\u0005\u0002\tm\u0001\"\u0003B\u0014_E\u0005I\u0011\u0001B\u0011\u0011%\u0011IcLI\u0001\n\u0003\u0011)\u0001C\u0005\u0003,=\n\n\u0011\"\u0001\u0003.!I!\u0011G\u0018\u0012\u0002\u0013\u0005!1\u0007\u0002\n'R\fw-Z%oM>T!\u0001P\u001f\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001 @\u0003\u0015\u0019\b/\u0019:l\u0015\t\u0001\u0015)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0005\u0006\u0019qN]4\u0004\u0001M\u0011\u0001!\u0012\t\u0003\r&k\u0011a\u0012\u0006\u0002\u0011\u0006)1oY1mC&\u0011!j\u0012\u0002\u0007\u0003:L(+\u001a4\u0002\u000fM$\u0018mZ3JIV\tQ\n\u0005\u0002G\u001d&\u0011qj\u0012\u0002\u0004\u0013:$\u0018\u0001C:uC\u001e,\u0017\n\u001a\u0011\u0002\u0013\u0005$H/Z7qi&#\u0017AC1ui\u0016l\u0007\u000f^%eA\u0005!a.Y7f+\u0005)\u0006C\u0001,^\u001d\t96\f\u0005\u0002Y\u000f6\t\u0011L\u0003\u0002[\u0007\u00061AH]8pizJ!\u0001X$\u0002\rA\u0013X\rZ3g\u0013\tqvL\u0001\u0004TiJLgn\u001a\u0006\u00039\u001e\u000bQA\\1nK\u0002\n\u0001B\\;n)\u0006\u001c8n]\u0001\n]VlG+Y:lg\u0002\n\u0001B\u001d3e\u0013:4wn]\u000b\u0002KB\u0019am\u001b8\u000f\u0005\u001dLgB\u0001-i\u0013\u0005A\u0015B\u00016H\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001\\7\u0003\u0007M+\u0017O\u0003\u0002k\u000fB\u0011qN]\u0007\u0002a*\u0011\u0011/P\u0001\bgR|'/Y4f\u0013\t\u0019\bOA\u0004S\t\u0012KeNZ8\u0002\u0013I$G-\u00138g_N\u0004\u0013!\u00039be\u0016tG/\u00133t+\u00059\bc\u00014l\u001b\u0006Q\u0001/\u0019:f]RLEm\u001d\u0011\u0002\u000f\u0011,G/Y5mg\u0006AA-\u001a;bS2\u001c\b%A\u0006uCN\\W*\u001a;sS\u000e\u001cX#A?\u0011\u0007y\f\u0019!D\u0001\u0000\u0015\r\t\t!P\u0001\tKb,7-\u001e;pe&\u0019\u0011QA@\u0003\u0017Q\u000b7o['fiJL7m]\u0001\ri\u0006\u001c8.T3ue&\u001c7\u000fI\u0001\u0018i\u0006\u001c8\u000eT8dC2LG/\u001f)sK\u001a,'/\u001a8dKN,\"!!\u0004\u0011\t\u0019\\\u0017q\u0002\t\u0005M.\f\t\u0002\u0005\u0003\u0002\u0014\u0005UQ\"A\u001e\n\u0007\u0005]1H\u0001\u0007UCN\\Gj\\2bi&|g.\u0001\ruCN\\Gj\\2bY&$\u0018\u0010\u0015:fM\u0016\u0014XM\\2fg\u0002\nAb\u001d5vM\u001adW\rR3q\u0013\u0012,\"!a\b\u0011\t\u0019\u000b\t#T\u0005\u0004\u0003G9%AB(qi&|g.A\u0007tQV4g\r\\3EKBLE\rI\u0001\u0012e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#\u0017A\u0005:fg>,(oY3Qe>4\u0017\u000e\\3JI\u0002\nA#[:TQV4g\r\\3QkNDWI\\1cY\u0016$WCAA\u0018!\r1\u0015\u0011G\u0005\u0004\u0003g9%a\u0002\"p_2,\u0017M\\\u0001\u0019SN\u001c\u0006.\u001e4gY\u0016\u0004Vo\u001d5F]\u0006\u0014G.\u001a3`I\u0015\fH\u0003BA\u001d\u0003\u007f\u00012ARA\u001e\u0013\r\tid\u0012\u0002\u0005+:LG\u000fC\u0005\u0002Ba\t\t\u00111\u0001\u00020\u0005\u0019\u0001\u0010J\u0019\u0002+%\u001c8\u000b[;gM2,\u0007+^:i\u000b:\f'\r\\3eA\u0005\u00112\u000f[;gM2,W*\u001a:hKJ\u001cu.\u001e8u\u0003Y\u0019\b.\u001e4gY\u0016lUM]4fe\u000e{WO\u001c;`I\u0015\fH\u0003BA\u001d\u0003\u0017B\u0001\"!\u0011\u001c\u0003\u0003\u0005\r!T\u0001\u0014g\",hM\u001a7f\u001b\u0016\u0014x-\u001a:D_VtG\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u00159\u0005M\u0013QKA,\u00033\nY&!\u0018\u0002`\u0005\u0005\u00141MA3\u0003O\nI'a\u001b\u0002nA\u0019\u00111\u0003\u0001\t\u000b-k\u0002\u0019A'\t\u000bEk\u0002\u0019A'\t\u000bMk\u0002\u0019A+\t\u000b\u0005l\u0002\u0019A'\t\u000b\rl\u0002\u0019A3\t\u000bUl\u0002\u0019A<\t\u000bel\u0002\u0019A+\t\u000fml\u0002\u0013!a\u0001{\"I\u0011\u0011B\u000f\u0011\u0002\u0003\u0007\u0011Q\u0002\u0005\n\u00037i\u0002\u0013!a\u0001\u0003?Aa!a\n\u001e\u0001\u0004i\u0005\"CA\u0016;A\u0005\t\u0019AA\u0018\u0011!\t)%\bI\u0001\u0002\u0004i\u0015AD:vE6L7o]5p]RKW.Z\u000b\u0003\u0003g\u0002RARA\u0011\u0003k\u00022ARA<\u0013\r\tIh\u0012\u0002\u0005\u0019>tw-\u0001\ntk\nl\u0017n]:j_:$\u0016.\\3`I\u0015\fH\u0003BA\u001d\u0003\u007fB\u0011\"!\u0011 \u0003\u0003\u0005\r!a\u001d\u0002\u001fM,(-\\5tg&|g\u000eV5nK\u0002\nabY8na2,G/[8o)&lW-\u0001\nd_6\u0004H.\u001a;j_:$\u0016.\\3`I\u0015\fH\u0003BA\u001d\u0003\u0013C\u0011\"!\u0011#\u0003\u0003\u0005\r!a\u001d\u0002\u001f\r|W\u000e\u001d7fi&|g\u000eV5nK\u0002\nQBZ1jYV\u0014XMU3bg>tWCAAI!\u00111\u0015\u0011E+\u0002#\u0019\f\u0017\u000e\\;sKJ+\u0017m]8o?\u0012*\u0017\u000f\u0006\u0003\u0002:\u0005]\u0005\"CA!K\u0005\u0005\t\u0019AAI\u000391\u0017-\u001b7ve\u0016\u0014V-Y:p]\u0002\nA\"Y2dk6,H.\u00192mKN,\"!a(\u0011\u0011\u0005\u0005\u00161VA;\u0003_k!!a)\u000b\t\u0005\u0015\u0016qU\u0001\b[V$\u0018M\u00197f\u0015\r\tIkR\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAW\u0003G\u0013q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0003\u0002\u0014\u0005E\u0016bAAZw\ty\u0011iY2v[Vd\u0017M\u00197f\u0013:4w.A\u0007bG\u000e,X.\u001e7bE2,7\u000fI\u0001\fgR\fw-\u001a$bS2,G\r\u0006\u0003\u0002:\u0005m\u0006BBA_S\u0001\u0007Q+\u0001\u0004sK\u0006\u001cxN\\\u0001\u000eCR$X-\u001c9u\u001dVl'-\u001a:\u0015\u00035\u000bqbZ3u'R\fG/^:TiJLgnZ\u0001\u0016g\u0016$8\u000b[;gM2,W*\u001a:hKJ\u001cu.\u001e8u)\u0011\tI$!3\t\r\u0005-G\u00061\u0001N\u0003\u001diWM]4feN\f!d]3u!V\u001c\bNQ1tK\u0012\u001c\u0006.\u001e4gY\u0016,e.\u00192mK\u0012$B!!\u000f\u0002R\"9\u00111[\u0017A\u0002\u0005=\u0012a\u00069vg\"\u0014\u0015m]3e'\",hM\u001a7f\u000b:\f'\r\\3eQ\r\u0001\u0011q\u001b\t\u0005\u00033\fy.\u0004\u0002\u0002\\*\u0019\u0011Q\\\u001f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002b\u0006m'\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017!C*uC\u001e,\u0017J\u001c4p!\r\t\u0019bL\n\u0003_\u0015#\"!!:\u0002\u0013\u0019\u0014x.\\*uC\u001e,GCDA*\u0003_\fI0a?\u0002~\u0006}(\u0011\u0001\u0005\b\u0003c\f\u0004\u0019AAz\u0003\u0015\u0019H/Y4f!\u0011\t\u0019\"!>\n\u0007\u0005]8HA\u0003Ti\u0006<W\rC\u0003Rc\u0001\u0007Q\n\u0003\u0005bcA\u0005\t\u0019AA\u0010\u0011\u001dY\u0018\u0007%AA\u0002uD\u0011\"!\u00032!\u0003\u0005\r!!\u0004\t\r\u0005\u001d\u0012\u00071\u0001N\u0003M1'o\\7Ti\u0006<W\r\n3fM\u0006,H\u000e\u001e\u00134+\t\u00119A\u000b\u0003\u0002 \t%1F\u0001B\u0006!\u0011\u0011iA!\u0006\u000e\u0005\t=!\u0002\u0002B\t\u0005'\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005uw)\u0003\u0003\u0003\u0018\t=!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u0019bM]8n'R\fw-\u001a\u0013eK\u001a\fW\u000f\u001c;%iU\u0011!Q\u0004\u0016\u0004{\n%\u0011a\u00054s_6\u001cF/Y4fI\u0011,g-Y;mi\u0012*TC\u0001B\u0012U\u0011\tiA!\u0003\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00139\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%s\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u0002\u0014\u0001\b\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013GM\u000b\u0003\u0005_QC!a\f\u0003\n\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u001aTC\u0001B\u001bU\ri%\u0011\u0002"
)
public class StageInfo {
   private final int stageId;
   private final int attemptId;
   private final String name;
   private final int numTasks;
   private final Seq rddInfos;
   private final Seq parentIds;
   private final String details;
   private final TaskMetrics taskMetrics;
   private final Seq taskLocalityPreferences;
   private final Option shuffleDepId;
   private final int resourceProfileId;
   private boolean isShufflePushEnabled;
   private int shuffleMergerCount;
   private Option submissionTime;
   private Option completionTime;
   private Option failureReason;
   private final HashMap accumulables;

   public static int $lessinit$greater$default$13() {
      return StageInfo$.MODULE$.$lessinit$greater$default$13();
   }

   public static boolean $lessinit$greater$default$12() {
      return StageInfo$.MODULE$.$lessinit$greater$default$12();
   }

   public static Option $lessinit$greater$default$10() {
      return StageInfo$.MODULE$.$lessinit$greater$default$10();
   }

   public static Seq $lessinit$greater$default$9() {
      return StageInfo$.MODULE$.$lessinit$greater$default$9();
   }

   public static TaskMetrics $lessinit$greater$default$8() {
      return StageInfo$.MODULE$.$lessinit$greater$default$8();
   }

   public static Seq fromStage$default$5() {
      return StageInfo$.MODULE$.fromStage$default$5();
   }

   public static TaskMetrics fromStage$default$4() {
      return StageInfo$.MODULE$.fromStage$default$4();
   }

   public static Option fromStage$default$3() {
      return StageInfo$.MODULE$.fromStage$default$3();
   }

   public static StageInfo fromStage(final Stage stage, final int attemptId, final Option numTasks, final TaskMetrics taskMetrics, final Seq taskLocalityPreferences, final int resourceProfileId) {
      return StageInfo$.MODULE$.fromStage(stage, attemptId, numTasks, taskMetrics, taskLocalityPreferences, resourceProfileId);
   }

   public int stageId() {
      return this.stageId;
   }

   private int attemptId() {
      return this.attemptId;
   }

   public String name() {
      return this.name;
   }

   public int numTasks() {
      return this.numTasks;
   }

   public Seq rddInfos() {
      return this.rddInfos;
   }

   public Seq parentIds() {
      return this.parentIds;
   }

   public String details() {
      return this.details;
   }

   public TaskMetrics taskMetrics() {
      return this.taskMetrics;
   }

   public Seq taskLocalityPreferences() {
      return this.taskLocalityPreferences;
   }

   public Option shuffleDepId() {
      return this.shuffleDepId;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public boolean isShufflePushEnabled() {
      return this.isShufflePushEnabled;
   }

   public void isShufflePushEnabled_$eq(final boolean x$1) {
      this.isShufflePushEnabled = x$1;
   }

   public int shuffleMergerCount() {
      return this.shuffleMergerCount;
   }

   public void shuffleMergerCount_$eq(final int x$1) {
      this.shuffleMergerCount = x$1;
   }

   public Option submissionTime() {
      return this.submissionTime;
   }

   public void submissionTime_$eq(final Option x$1) {
      this.submissionTime = x$1;
   }

   public Option completionTime() {
      return this.completionTime;
   }

   public void completionTime_$eq(final Option x$1) {
      this.completionTime = x$1;
   }

   public Option failureReason() {
      return this.failureReason;
   }

   public void failureReason_$eq(final Option x$1) {
      this.failureReason = x$1;
   }

   public HashMap accumulables() {
      return this.accumulables;
   }

   public void stageFailed(final String reason) {
      this.failureReason_$eq(new Some(reason));
      this.completionTime_$eq(new Some(BoxesRunTime.boxToLong(System.currentTimeMillis())));
   }

   public int attemptNumber() {
      return this.attemptId();
   }

   public String getStatusString() {
      if (this.completionTime().isDefined()) {
         return this.failureReason().isDefined() ? "failed" : "succeeded";
      } else {
         return "running";
      }
   }

   public void setShuffleMergerCount(final int mergers) {
      this.shuffleMergerCount_$eq(mergers);
   }

   public void setPushBasedShuffleEnabled(final boolean pushBasedShuffleEnabled) {
      this.isShufflePushEnabled_$eq(pushBasedShuffleEnabled);
   }

   public StageInfo(final int stageId, final int attemptId, final String name, final int numTasks, final Seq rddInfos, final Seq parentIds, final String details, final TaskMetrics taskMetrics, final Seq taskLocalityPreferences, final Option shuffleDepId, final int resourceProfileId, final boolean isShufflePushEnabled, final int shuffleMergerCount) {
      this.stageId = stageId;
      this.attemptId = attemptId;
      this.name = name;
      this.numTasks = numTasks;
      this.rddInfos = rddInfos;
      this.parentIds = parentIds;
      this.details = details;
      this.taskMetrics = taskMetrics;
      this.taskLocalityPreferences = taskLocalityPreferences;
      this.shuffleDepId = shuffleDepId;
      this.resourceProfileId = resourceProfileId;
      this.isShufflePushEnabled = isShufflePushEnabled;
      this.shuffleMergerCount = shuffleMergerCount;
      super();
      this.submissionTime = .MODULE$;
      this.completionTime = .MODULE$;
      this.failureReason = .MODULE$;
      this.accumulables = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }
}
