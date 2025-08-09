package org.apache.spark.status;

import org.apache.spark.JobExecutionStatus;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.util.collection.OpenHashSet;
import org.apache.spark.util.collection.OpenHashSet$mcJ$sp;
import scala.Option;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eg\u0001B\u001b7\t}B\u0001\u0002\u0012\u0001\u0003\u0006\u0004%\t!\u0012\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005\r\"AQ\n\u0001B\u0001B\u0003%a\n\u0003\u0005Z\u0001\t\u0005\t\u0015!\u0003[\u0011!i\u0006A!b\u0001\n\u0003q\u0006\u0002\u00035\u0001\u0005\u0003\u0005\u000b\u0011B0\t\u0011%\u0004!Q1A\u0005\u0002)D\u0001\u0002\u001e\u0001\u0003\u0002\u0003\u0006Ia\u001b\u0005\tk\u0002\u0011\t\u0011)A\u00055\"Aa\u000f\u0001B\u0001B\u0003%q\u000f\u0003\u0005y\u0001\t\u0005\t\u0015!\u0003G\u0011!I\bA!A!\u0002\u0013Q\b\"\u0002@\u0001\t\u0003y\b\u0002CA\u000b\u0001\u0001\u0007I\u0011A#\t\u0013\u0005]\u0001\u00011A\u0005\u0002\u0005e\u0001bBA\u0013\u0001\u0001\u0006KA\u0012\u0005\t\u0003O\u0001\u0001\u0019!C\u0001\u000b\"I\u0011\u0011\u0006\u0001A\u0002\u0013\u0005\u00111\u0006\u0005\b\u0003_\u0001\u0001\u0015)\u0003G\u0011!\t\t\u0004\u0001a\u0001\n\u0003)\u0005\"CA\u001a\u0001\u0001\u0007I\u0011AA\u001b\u0011\u001d\tI\u0004\u0001Q!\n\u0019C\u0011\"a\u000f\u0001\u0005\u0004%\t!!\u0010\t\u0011\u00055\u0003\u0001)A\u0005\u0003\u007fA\u0001\"a\u0014\u0001\u0001\u0004%\t!\u0012\u0005\n\u0003#\u0002\u0001\u0019!C\u0001\u0003'Bq!a\u0016\u0001A\u0003&a\tC\u0005\u0002Z\u0001\u0001\r\u0011\"\u0001\u0002\\!I\u00111\r\u0001A\u0002\u0013\u0005\u0011Q\r\u0005\t\u0003S\u0002\u0001\u0015)\u0003\u0002^!A\u00111\u000e\u0001A\u0002\u0013\u0005Q\tC\u0005\u0002n\u0001\u0001\r\u0011\"\u0001\u0002p!9\u00111\u000f\u0001!B\u00131\u0005\"CA;\u0001\u0001\u0007I\u0011AA<\u0011%\t9\t\u0001a\u0001\n\u0003\tI\t\u0003\u0005\u0002\u000e\u0002\u0001\u000b\u0015BA=\u0011!9\u0004\u00011A\u0005\u0002\u0005=\u0005\"CAM\u0001\u0001\u0007I\u0011AAN\u0011!\ty\n\u0001Q!\n\u0005E\u0005\u0002CAQ\u0001\u0001\u0007I\u0011\u00010\t\u0013\u0005\r\u0006\u00011A\u0005\u0002\u0005\u0015\u0006bBAU\u0001\u0001\u0006Ka\u0018\u0005\n\u0003W\u0003\u0001\u0019!C\u0001\u0003[C\u0011\"a-\u0001\u0001\u0004%\t!!.\t\u0011\u0005e\u0006\u0001)Q\u0005\u0003_C\u0001\"a/\u0001\u0001\u0004%\t!\u0012\u0005\n\u0003{\u0003\u0001\u0019!C\u0001\u0003\u007fCq!a1\u0001A\u0003&a\t\u0003\u0005\u0002F\u0002\u0001\r\u0011\"\u0001F\u0011%\t9\r\u0001a\u0001\n\u0003\tI\rC\u0004\u0002N\u0002\u0001\u000b\u0015\u0002$\t\u000f\u0005=\u0007\u0001\"\u0015\u0002R\n9A*\u001b<f\u0015>\u0014'BA\u001c9\u0003\u0019\u0019H/\u0019;vg*\u0011\u0011HO\u0001\u0006gB\f'o\u001b\u0006\u0003wq\na!\u00199bG\",'\"A\u001f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0001\u0005CA!C\u001b\u00051\u0014BA\"7\u0005)a\u0015N^3F]RLG/_\u0001\u0006U>\u0014\u0017\nZ\u000b\u0002\rB\u0011qIS\u0007\u0002\u0011*\t\u0011*A\u0003tG\u0006d\u0017-\u0003\u0002L\u0011\n\u0019\u0011J\u001c;\u0002\r)|'-\u00133!\u0003\u0011q\u0017-\\3\u0011\u0005=3fB\u0001)U!\t\t\u0006*D\u0001S\u0015\t\u0019f(\u0001\u0004=e>|GOP\u0005\u0003+\"\u000ba\u0001\u0015:fI\u00164\u0017BA,Y\u0005\u0019\u0019FO]5oO*\u0011Q\u000bS\u0001\fI\u0016\u001c8M]5qi&|g\u000eE\u0002H7:K!\u0001\u0018%\u0003\r=\u0003H/[8o\u00039\u0019XOY7jgNLwN\u001c+j[\u0016,\u0012a\u0018\t\u0004\u000fn\u0003\u0007CA1g\u001b\u0005\u0011'BA2e\u0003\u0011)H/\u001b7\u000b\u0003\u0015\fAA[1wC&\u0011qM\u0019\u0002\u0005\t\u0006$X-A\btk\nl\u0017n]:j_:$\u0016.\\3!\u0003!\u0019H/Y4f\u0013\u0012\u001cX#A6\u0011\u00071\fhI\u0004\u0002n_:\u0011\u0011K\\\u0005\u0002\u0013&\u0011\u0001\u000fS\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00118OA\u0002TKFT!\u0001\u001d%\u0002\u0013M$\u0018mZ3JIN\u0004\u0013\u0001\u00036pE\u001e\u0013x.\u001e9\u0002\u000f)|'\rV1hgB\u0019A.\u001d(\u0002\u00119,X\u000eV1tWN\fab]9m\u000bb,7-\u001e;j_:LE\rE\u0002H7n\u0004\"a\u0012?\n\u0005uD%\u0001\u0002'p]\u001e\fa\u0001P5oSRtD\u0003FA\u0001\u0003\u0007\t)!a\u0002\u0002\n\u0005-\u0011QBA\b\u0003#\t\u0019\u0002\u0005\u0002B\u0001!)A)\u0004a\u0001\r\")Q*\u0004a\u0001\u001d\")\u0011,\u0004a\u00015\")Q,\u0004a\u0001?\")\u0011.\u0004a\u0001W\")Q/\u0004a\u00015\")a/\u0004a\u0001o\")\u00010\u0004a\u0001\r\")\u00110\u0004a\u0001u\u0006Y\u0011m\u0019;jm\u0016$\u0016m]6t\u0003=\t7\r^5wKR\u000b7o[:`I\u0015\fH\u0003BA\u000e\u0003C\u00012aRA\u000f\u0013\r\ty\u0002\u0013\u0002\u0005+:LG\u000f\u0003\u0005\u0002$=\t\t\u00111\u0001G\u0003\rAH%M\u0001\rC\u000e$\u0018N^3UCN\\7\u000fI\u0001\u000fG>l\u0007\u000f\\3uK\u0012$\u0016m]6t\u0003I\u0019w.\u001c9mKR,G\rV1tWN|F%Z9\u0015\t\u0005m\u0011Q\u0006\u0005\t\u0003G\u0011\u0012\u0011!a\u0001\r\u0006y1m\\7qY\u0016$X\r\u001a+bg.\u001c\b%A\u0006gC&dW\r\u001a+bg.\u001c\u0018a\u00044bS2,G\rV1tWN|F%Z9\u0015\t\u0005m\u0011q\u0007\u0005\t\u0003G)\u0012\u0011!a\u0001\r\u0006aa-Y5mK\u0012$\u0016m]6tA\u0005\u00012m\\7qY\u0016$X\rZ%oI&\u001cWm]\u000b\u0003\u0003\u007f\u0001R!!\u0011\u0002Jml!!a\u0011\u000b\t\u0005\u0015\u0013qI\u0001\u000bG>dG.Z2uS>t'BA29\u0013\u0011\tY%a\u0011\u0003\u0017=\u0003XM\u001c%bg\"\u001cV\r^\u0001\u0012G>l\u0007\u000f\\3uK\u0012Le\u000eZ5dKN\u0004\u0013aC6jY2,G\rV1tWN\fqb[5mY\u0016$G+Y:lg~#S-\u001d\u000b\u0005\u00037\t)\u0006\u0003\u0005\u0002$i\t\t\u00111\u0001G\u00031Y\u0017\u000e\u001c7fIR\u000b7o[:!\u00035Y\u0017\u000e\u001c7fIN+X.\\1ssV\u0011\u0011Q\f\t\u0006\u001f\u0006}cJR\u0005\u0004\u0003CB&aA'ba\u0006\t2.\u001b7mK\u0012\u001cV/\\7bef|F%Z9\u0015\t\u0005m\u0011q\r\u0005\n\u0003Gi\u0012\u0011!a\u0001\u0003;\nab[5mY\u0016$7+^7nCJL\b%\u0001\u0007tW&\u0004\b/\u001a3UCN\\7/\u0001\ttW&\u0004\b/\u001a3UCN\\7o\u0018\u0013fcR!\u00111DA9\u0011!\t\u0019\u0003IA\u0001\u0002\u00041\u0015!D:lSB\u0004X\r\u001a+bg.\u001c\b%A\u0007tW&\u0004\b/\u001a3Ti\u0006<Wm]\u000b\u0003\u0003s\u0002R!a\u001f\u0002\u0004\u001ak!!! \u000b\t\u0005}\u0014\u0011Q\u0001\nS6lW\u000f^1cY\u0016T1!!\u0012I\u0013\u0011\t))! \u0003\u0007M+G/A\ttW&\u0004\b/\u001a3Ti\u0006<Wm]0%KF$B!a\u0007\u0002\f\"I\u00111E\u0012\u0002\u0002\u0003\u0007\u0011\u0011P\u0001\u000fg.L\u0007\u000f]3e'R\fw-Z:!+\t\t\t\n\u0005\u0003\u0002\u0014\u0006UU\"\u0001\u001d\n\u0007\u0005]\u0005H\u0001\nK_\n,\u00050Z2vi&|gn\u0015;biV\u001c\u0018AC:uCR,8o\u0018\u0013fcR!\u00111DAO\u0011%\t\u0019CJA\u0001\u0002\u0004\t\t*A\u0004ti\u0006$Xo\u001d\u0011\u0002\u001d\r|W\u000e\u001d7fi&|g\u000eV5nK\u0006\u00112m\\7qY\u0016$\u0018n\u001c8US6,w\fJ3r)\u0011\tY\"a*\t\u0011\u0005\r\u0012&!AA\u0002}\u000bqbY8na2,G/[8o)&lW\rI\u0001\u0010G>l\u0007\u000f\\3uK\u0012\u001cF/Y4fgV\u0011\u0011q\u0016\t\u0005\u001f\u0006Ef)C\u0002\u0002\u0006b\u000b1cY8na2,G/\u001a3Ti\u0006<Wm]0%KF$B!a\u0007\u00028\"I\u00111\u0005\u0017\u0002\u0002\u0003\u0007\u0011qV\u0001\u0011G>l\u0007\u000f\\3uK\u0012\u001cF/Y4fg\u0002\nA\"Y2uSZ,7\u000b^1hKN\f\u0001#Y2uSZ,7\u000b^1hKN|F%Z9\u0015\t\u0005m\u0011\u0011\u0019\u0005\t\u0003Gy\u0013\u0011!a\u0001\r\u0006i\u0011m\u0019;jm\u0016\u001cF/Y4fg\u0002\nABZ1jY\u0016$7\u000b^1hKN\f\u0001CZ1jY\u0016$7\u000b^1hKN|F%Z9\u0015\t\u0005m\u00111\u001a\u0005\t\u0003G\u0011\u0014\u0011!a\u0001\r\u0006ia-Y5mK\u0012\u001cF/Y4fg\u0002\n\u0001\u0002Z8Va\u0012\fG/\u001a\u000b\u0003\u0003'\u00042aRAk\u0013\r\t9\u000e\u0013\u0002\u0004\u0003:L\b"
)
public class LiveJob extends LiveEntity {
   private final int jobId;
   private final String name;
   private final Option description;
   private final Option submissionTime;
   private final Seq stageIds;
   private final Option jobGroup;
   private final Seq jobTags;
   private final int numTasks;
   private final Option sqlExecutionId;
   private int activeTasks;
   private int completedTasks;
   private int failedTasks;
   private final OpenHashSet completedIndices;
   private int killedTasks;
   private Map killedSummary;
   private int skippedTasks;
   private Set skippedStages;
   private JobExecutionStatus status;
   private Option completionTime;
   private Set completedStages;
   private int activeStages;
   private int failedStages;

   public int jobId() {
      return this.jobId;
   }

   public Option submissionTime() {
      return this.submissionTime;
   }

   public Seq stageIds() {
      return this.stageIds;
   }

   public int activeTasks() {
      return this.activeTasks;
   }

   public void activeTasks_$eq(final int x$1) {
      this.activeTasks = x$1;
   }

   public int completedTasks() {
      return this.completedTasks;
   }

   public void completedTasks_$eq(final int x$1) {
      this.completedTasks = x$1;
   }

   public int failedTasks() {
      return this.failedTasks;
   }

   public void failedTasks_$eq(final int x$1) {
      this.failedTasks = x$1;
   }

   public OpenHashSet completedIndices() {
      return this.completedIndices;
   }

   public int killedTasks() {
      return this.killedTasks;
   }

   public void killedTasks_$eq(final int x$1) {
      this.killedTasks = x$1;
   }

   public Map killedSummary() {
      return this.killedSummary;
   }

   public void killedSummary_$eq(final Map x$1) {
      this.killedSummary = x$1;
   }

   public int skippedTasks() {
      return this.skippedTasks;
   }

   public void skippedTasks_$eq(final int x$1) {
      this.skippedTasks = x$1;
   }

   public Set skippedStages() {
      return this.skippedStages;
   }

   public void skippedStages_$eq(final Set x$1) {
      this.skippedStages = x$1;
   }

   public JobExecutionStatus status() {
      return this.status;
   }

   public void status_$eq(final JobExecutionStatus x$1) {
      this.status = x$1;
   }

   public Option completionTime() {
      return this.completionTime;
   }

   public void completionTime_$eq(final Option x$1) {
      this.completionTime = x$1;
   }

   public Set completedStages() {
      return this.completedStages;
   }

   public void completedStages_$eq(final Set x$1) {
      this.completedStages = x$1;
   }

   public int activeStages() {
      return this.activeStages;
   }

   public void activeStages_$eq(final int x$1) {
      this.activeStages = x$1;
   }

   public int failedStages() {
      return this.failedStages;
   }

   public void failedStages_$eq(final int x$1) {
      this.failedStages = x$1;
   }

   public Object doUpdate() {
      JobData info = new JobData(this.jobId(), this.name, this.description, this.submissionTime(), this.completionTime(), this.stageIds(), this.jobGroup, this.jobTags, this.status(), this.numTasks, this.activeTasks(), this.completedTasks(), this.skippedTasks(), this.failedTasks(), this.killedTasks(), this.completedIndices().size(), this.activeStages(), this.completedStages().size(), this.skippedStages().size(), this.failedStages(), this.killedSummary());
      return new JobDataWrapper(info, this.skippedStages(), this.sqlExecutionId);
   }

   public LiveJob(final int jobId, final String name, final Option description, final Option submissionTime, final Seq stageIds, final Option jobGroup, final Seq jobTags, final int numTasks, final Option sqlExecutionId) {
      this.jobId = jobId;
      this.name = name;
      this.description = description;
      this.submissionTime = submissionTime;
      this.stageIds = stageIds;
      this.jobGroup = jobGroup;
      this.jobTags = jobTags;
      this.numTasks = numTasks;
      this.sqlExecutionId = sqlExecutionId;
      this.activeTasks = 0;
      this.completedTasks = 0;
      this.failedTasks = 0;
      this.completedIndices = new OpenHashSet$mcJ$sp(.MODULE$.Long());
      this.killedTasks = 0;
      this.killedSummary = (Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$);
      this.skippedTasks = 0;
      this.skippedStages = (Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$);
      this.status = JobExecutionStatus.RUNNING;
      this.completionTime = scala.None..MODULE$;
      this.completedStages = (Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$);
      this.activeStages = 0;
      this.failedStages = 0;
   }
}
