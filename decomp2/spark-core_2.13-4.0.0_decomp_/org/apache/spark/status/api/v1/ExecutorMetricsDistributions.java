package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001db\u0001\u0002\u0012$\u0001AB\u0001b\u000e\u0001\u0003\u0006\u0004%\t\u0001\u000f\u0005\t\u0011\u0002\u0011\t\u0011)A\u0005s!A\u0011\n\u0001BC\u0002\u0013\u0005\u0001\b\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003:\u0011!Y\u0005A!b\u0001\n\u0003A\u0004\u0002\u0003'\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u00115\u0003!Q1A\u0005\u0002aB\u0001B\u0014\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\t\u001f\u0002\u0011)\u0019!C\u0001q!A\u0001\u000b\u0001B\u0001B\u0003%\u0011\b\u0003\u0005R\u0001\t\u0015\r\u0011\"\u00019\u0011!\u0011\u0006A!A!\u0002\u0013I\u0004\u0002C*\u0001\u0005\u000b\u0007I\u0011\u0001\u001d\t\u0011Q\u0003!\u0011!Q\u0001\neB\u0001\"\u0016\u0001\u0003\u0006\u0004%\t\u0001\u000f\u0005\t-\u0002\u0011\t\u0011)A\u0005s!Aq\u000b\u0001BC\u0002\u0013\u0005\u0001\b\u0003\u0005Y\u0001\t\u0005\t\u0015!\u0003:\u0011!I\u0006A!b\u0001\n\u0003A\u0004\u0002\u0003.\u0001\u0005\u0003\u0005\u000b\u0011B\u001d\t\u0011m\u0003!Q1A\u0005\u0002aB\u0001\u0002\u0018\u0001\u0003\u0002\u0003\u0006I!\u000f\u0005\t;\u0002\u0011)\u0019!C\u0001q!Aa\f\u0001B\u0001B\u0003%\u0011\b\u0003\u0005`\u0001\t\u0015\r\u0011\"\u00019\u0011!\u0001\u0007A!A!\u0002\u0013I\u0004\u0002C1\u0001\u0005\u000b\u0007I\u0011\u0001\u001d\t\u0011\t\u0004!\u0011!Q\u0001\neB\u0001b\u0019\u0001\u0003\u0006\u0004%\t\u0001\u000f\u0005\tI\u0002\u0011\t\u0011)A\u0005s!AQ\r\u0001BC\u0002\u0013\u0005a\r\u0003\u0005l\u0001\t\u0005\t\u0015!\u0003h\u0011\u0019a\u0007\u0001\"\u0001*[\naR\t_3dkR|'/T3ue&\u001c7\u000fR5tiJL'-\u001e;j_:\u001c(B\u0001\u0013&\u0003\t1\u0018G\u0003\u0002'O\u0005\u0019\u0011\r]5\u000b\u0005!J\u0013AB:uCR,8O\u0003\u0002+W\u0005)1\u000f]1sW*\u0011A&L\u0001\u0007CB\f7\r[3\u000b\u00039\n1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0019\u0011\u0005I*T\"A\u001a\u000b\u0003Q\nQa]2bY\u0006L!AN\u001a\u0003\r\u0005s\u0017PU3g\u0003%\tX/\u00198uS2,7/F\u0001:!\rQ$)\u0012\b\u0003w\u0001s!\u0001P \u000e\u0003uR!AP\u0018\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0014BA!4\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0011#\u0003\u0015%sG-\u001a=fIN+\u0017O\u0003\u0002BgA\u0011!GR\u0005\u0003\u000fN\u0012a\u0001R8vE2,\u0017AC9vC:$\u0018\u000e\\3tA\u0005AA/Y:l)&lW-A\u0005uCN\\G+[7fA\u0005Ya-Y5mK\u0012$\u0016m]6t\u000311\u0017-\u001b7fIR\u000b7o[:!\u00039\u0019XoY2fK\u0012,G\rV1tWN\fqb];dG\u0016,G-\u001a3UCN\\7\u000fI\u0001\fW&dG.\u001a3UCN\\7/\u0001\u0007lS2dW\r\u001a+bg.\u001c\b%\u0001\u0006j]B,HOQ=uKN\f1\"\u001b8qkR\u0014\u0015\u0010^3tA\u0005a\u0011N\u001c9viJ+7m\u001c:eg\u0006i\u0011N\u001c9viJ+7m\u001c:eg\u0002\n1b\\;uaV$()\u001f;fg\u0006aq.\u001e;qkR\u0014\u0015\u0010^3tA\u0005iq.\u001e;qkR\u0014VmY8sIN\fab\\;uaV$(+Z2pe\u0012\u001c\b%A\u0006tQV4g\r\\3SK\u0006$\u0017\u0001D:ik\u001a4G.\u001a*fC\u0012\u0004\u0013AE:ik\u001a4G.\u001a*fC\u0012\u0014VmY8sIN\f1c\u001d5vM\u001adWMU3bIJ+7m\u001c:eg\u0002\nAb\u001d5vM\u001adWm\u0016:ji\u0016\fQb\u001d5vM\u001adWm\u0016:ji\u0016\u0004\u0013aE:ik\u001a4G.Z,sSR,'+Z2pe\u0012\u001c\u0018\u0001F:ik\u001a4G.Z,sSR,'+Z2pe\u0012\u001c\b%\u0001\nnK6|'/\u001f\"zi\u0016\u001c8\u000b]5mY\u0016$\u0017aE7f[>\u0014\u0018PQ=uKN\u001c\u0006/\u001b7mK\u0012\u0004\u0013\u0001\u00053jg.\u0014\u0015\u0010^3t'BLG\u000e\\3e\u0003E!\u0017n]6CsR,7o\u00159jY2,G\rI\u0001\u0012a\u0016\f7.T3n_JLX*\u001a;sS\u000e\u001cX#A4\u0011\u0005!LW\"A\u0012\n\u0005)\u001c#\u0001I#yK\u000e,Ho\u001c:QK\u0006\\W*\u001a;sS\u000e\u001cH)[:ue&\u0014W\u000f^5p]N\f!\u0003]3bW6+Wn\u001c:z\u001b\u0016$(/[2tA\u00051A(\u001b8jiz\"\u0012C\\8qcJ\u001cH/\u001e<xqfT8\u0010`?\u007f!\tA\u0007\u0001C\u00038C\u0001\u0007\u0011\bC\u0003JC\u0001\u0007\u0011\bC\u0003LC\u0001\u0007\u0011\bC\u0003NC\u0001\u0007\u0011\bC\u0003PC\u0001\u0007\u0011\bC\u0003RC\u0001\u0007\u0011\bC\u0003TC\u0001\u0007\u0011\bC\u0003VC\u0001\u0007\u0011\bC\u0003XC\u0001\u0007\u0011\bC\u0003ZC\u0001\u0007\u0011\bC\u0003\\C\u0001\u0007\u0011\bC\u0003^C\u0001\u0007\u0011\bC\u0003`C\u0001\u0007\u0011\bC\u0003bC\u0001\u0007\u0011\bC\u0003dC\u0001\u0007\u0011\bC\u0003fC\u0001\u0007q\rK\u0004\u007f\u0003\u0003\ti\"a\b\u0011\t\u0005\r\u0011\u0011D\u0007\u0003\u0003\u000bQA!a\u0002\u0002\n\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\t\u0005-\u0011QB\u0001\tI\u0006$\u0018MY5oI*!\u0011qBA\t\u0003\u001dQ\u0017mY6t_:TA!a\u0005\u0002\u0016\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0003\u0003/\t1aY8n\u0013\u0011\tY\"!\u0002\u0003\u001b)\u001bxN\\*fe&\fG.\u001b>f\u0003\u0015)8/\u001b8hG\t\t\t\u0003E\u0002i\u0003GI1!!\n$\u00059*\u00050Z2vi>\u0014\b+Z1l\u001b\u0016$(/[2t\t&\u001cHO]5ckRLwN\\:Kg>t7+\u001a:jC2L'0\u001a:"
)
public class ExecutorMetricsDistributions {
   private final IndexedSeq quantiles;
   private final IndexedSeq taskTime;
   private final IndexedSeq failedTasks;
   private final IndexedSeq succeededTasks;
   private final IndexedSeq killedTasks;
   private final IndexedSeq inputBytes;
   private final IndexedSeq inputRecords;
   private final IndexedSeq outputBytes;
   private final IndexedSeq outputRecords;
   private final IndexedSeq shuffleRead;
   private final IndexedSeq shuffleReadRecords;
   private final IndexedSeq shuffleWrite;
   private final IndexedSeq shuffleWriteRecords;
   private final IndexedSeq memoryBytesSpilled;
   private final IndexedSeq diskBytesSpilled;
   private final ExecutorPeakMetricsDistributions peakMemoryMetrics;

   public IndexedSeq quantiles() {
      return this.quantiles;
   }

   public IndexedSeq taskTime() {
      return this.taskTime;
   }

   public IndexedSeq failedTasks() {
      return this.failedTasks;
   }

   public IndexedSeq succeededTasks() {
      return this.succeededTasks;
   }

   public IndexedSeq killedTasks() {
      return this.killedTasks;
   }

   public IndexedSeq inputBytes() {
      return this.inputBytes;
   }

   public IndexedSeq inputRecords() {
      return this.inputRecords;
   }

   public IndexedSeq outputBytes() {
      return this.outputBytes;
   }

   public IndexedSeq outputRecords() {
      return this.outputRecords;
   }

   public IndexedSeq shuffleRead() {
      return this.shuffleRead;
   }

   public IndexedSeq shuffleReadRecords() {
      return this.shuffleReadRecords;
   }

   public IndexedSeq shuffleWrite() {
      return this.shuffleWrite;
   }

   public IndexedSeq shuffleWriteRecords() {
      return this.shuffleWriteRecords;
   }

   public IndexedSeq memoryBytesSpilled() {
      return this.memoryBytesSpilled;
   }

   public IndexedSeq diskBytesSpilled() {
      return this.diskBytesSpilled;
   }

   public ExecutorPeakMetricsDistributions peakMemoryMetrics() {
      return this.peakMemoryMetrics;
   }

   public ExecutorMetricsDistributions(final IndexedSeq quantiles, final IndexedSeq taskTime, final IndexedSeq failedTasks, final IndexedSeq succeededTasks, final IndexedSeq killedTasks, final IndexedSeq inputBytes, final IndexedSeq inputRecords, final IndexedSeq outputBytes, final IndexedSeq outputRecords, final IndexedSeq shuffleRead, final IndexedSeq shuffleReadRecords, final IndexedSeq shuffleWrite, final IndexedSeq shuffleWriteRecords, final IndexedSeq memoryBytesSpilled, final IndexedSeq diskBytesSpilled, @JsonSerialize(using = ExecutorPeakMetricsDistributionsJsonSerializer.class) final ExecutorPeakMetricsDistributions peakMemoryMetrics) {
      this.quantiles = quantiles;
      this.taskTime = taskTime;
      this.failedTasks = failedTasks;
      this.succeededTasks = succeededTasks;
      this.killedTasks = killedTasks;
      this.inputBytes = inputBytes;
      this.inputRecords = inputRecords;
      this.outputBytes = outputBytes;
      this.outputRecords = outputRecords;
      this.shuffleRead = shuffleRead;
      this.shuffleReadRecords = shuffleReadRecords;
      this.shuffleWrite = shuffleWrite;
      this.shuffleWriteRecords = shuffleWriteRecords;
      this.memoryBytesSpilled = memoryBytesSpilled;
      this.diskBytesSpilled = diskBytesSpilled;
      this.peakMemoryMetrics = peakMemoryMetrics;
   }
}
