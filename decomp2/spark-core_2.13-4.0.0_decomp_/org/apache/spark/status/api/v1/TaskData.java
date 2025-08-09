package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;
import scala.Option;
import scala.collection.Seq;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mf\u0001B\u0016-\u0001eB\u0001\u0002\u0011\u0001\u0003\u0006\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0011)A\u0005\u0005\"Aa\t\u0001BC\u0002\u0013\u0005q\t\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003I\u0011!a\u0005A!b\u0001\n\u00039\u0005\u0002C'\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\u00119\u0003!Q1A\u0005\u0002\u001dC\u0001b\u0014\u0001\u0003\u0002\u0003\u0006I\u0001\u0013\u0005\t!\u0002\u0011)\u0019!C\u0001#\"A!\f\u0001B\u0001B\u0003%!\u000b\u0003\u0005\\\u0001\t\u0015\r\u0011\"\u0001]\u0011!\u0001\u0007A!A!\u0002\u0013i\u0006\u0002C1\u0001\u0005\u000b\u0007I\u0011\u00012\t\u0011\u0011\u0004!\u0011!Q\u0001\n\rD\u0001\"\u001a\u0001\u0003\u0006\u0004%\tA\u001a\u0005\te\u0002\u0011\t\u0011)A\u0005O\"A1\u000f\u0001BC\u0002\u0013\u0005a\r\u0003\u0005u\u0001\t\u0005\t\u0015!\u0003h\u0011!\t\u0004A!b\u0001\n\u00031\u0007\u0002C;\u0001\u0005\u0003\u0005\u000b\u0011B4\t\u0011Y\u0004!Q1A\u0005\u0002\u0019D\u0001b\u001e\u0001\u0003\u0002\u0003\u0006Ia\u001a\u0005\tq\u0002\u0011)\u0019!C\u0001s\"AQ\u0010\u0001B\u0001B\u0003%!\u0010\u0003\u0005\u007f\u0001\t\u0015\r\u0011\"\u0001\u0000\u0011)\t)\u0002\u0001B\u0001B\u0003%\u0011\u0011\u0001\u0005\u000b\u0003/\u0001!Q1A\u0005\u0002\u0005e\u0001BCA\u000f\u0001\t\u0005\t\u0015!\u0003\u0002\u001c!Q\u0011q\u0004\u0001\u0003\u0006\u0004%\t!!\t\t\u0015\u0005-\u0002A!A!\u0002\u0013\t\u0019\u0003\u0003\u0006\u0002.\u0001\u0011)\u0019!C\u0001\u0003_A!\"a\u000e\u0001\u0005\u0003\u0005\u000b\u0011BA\u0019\u0011%\tI\u0004\u0001BC\u0002\u0013\u0005\u0011\tC\u0005\u0002<\u0001\u0011\t\u0011)A\u0005\u0005\"I\u0011Q\b\u0001\u0003\u0006\u0004%\t!\u0011\u0005\n\u0003\u007f\u0001!\u0011!Q\u0001\n\tC\u0001\"!\u0011\u0001\t\u0003\u0011\u00141I\u0004\n\u0003/c\u0013\u0011!E\u0001\u000333\u0001b\u000b\u0017\u0002\u0002#\u0005\u00111\u0014\u0005\b\u0003\u0003:C\u0011AAO\u0011)\tyjJI\u0001\n\u0003\u0011\u0014\u0011\u0015\u0005\u000b\u0003k;\u0013\u0013!C\u0001e\u0005]&\u0001\u0003+bg.$\u0015\r^1\u000b\u00055r\u0013A\u0001<2\u0015\ty\u0003'A\u0002ba&T!!\r\u001a\u0002\rM$\u0018\r^;t\u0015\t\u0019D'A\u0003ta\u0006\u00148N\u0003\u00026m\u00051\u0011\r]1dQ\u0016T\u0011aN\u0001\u0004_J<7\u0001A\n\u0003\u0001i\u0002\"a\u000f \u000e\u0003qR\u0011!P\u0001\u0006g\u000e\fG.Y\u0005\u0003\u007fq\u0012a!\u00118z%\u00164\u0017A\u0002;bg.LE-F\u0001C!\tY4)\u0003\u0002Ey\t!Aj\u001c8h\u0003\u001d!\u0018m]6JI\u0002\nQ!\u001b8eKb,\u0012\u0001\u0013\t\u0003w%K!A\u0013\u001f\u0003\u0007%sG/\u0001\u0004j]\u0012,\u0007\u0010I\u0001\bCR$X-\u001c9u\u0003!\tG\u000f^3naR\u0004\u0013a\u00039beRLG/[8o\u0013\u0012\fA\u0002]1si&$\u0018n\u001c8JI\u0002\n!\u0002\\1v]\u000eDG+[7f+\u0005\u0011\u0006CA*Y\u001b\u0005!&BA+W\u0003\u0011)H/\u001b7\u000b\u0003]\u000bAA[1wC&\u0011\u0011\f\u0016\u0002\u0005\t\u0006$X-A\u0006mCVt7\r\u001b+j[\u0016\u0004\u0013\u0001\u0005:fgVdGOR3uG\"\u001cF/\u0019:u+\u0005i\u0006cA\u001e_%&\u0011q\f\u0010\u0002\u0007\u001fB$\u0018n\u001c8\u0002#I,7/\u001e7u\r\u0016$8\r[*uCJ$\b%\u0001\u0005ekJ\fG/[8o+\u0005\u0019\u0007cA\u001e_\u0005\u0006IA-\u001e:bi&|g\u000eI\u0001\u000bKb,7-\u001e;pe&#W#A4\u0011\u0005!|gBA5n!\tQG(D\u0001l\u0015\ta\u0007(\u0001\u0004=e>|GOP\u0005\u0003]r\na\u0001\u0015:fI\u00164\u0017B\u00019r\u0005\u0019\u0019FO]5oO*\u0011a\u000eP\u0001\fKb,7-\u001e;pe&#\u0007%\u0001\u0003i_N$\u0018!\u00025pgR\u0004\u0013aB:uCR,8\u000fI\u0001\ri\u0006\u001c8\u000eT8dC2LG/_\u0001\u000ei\u0006\u001c8\u000eT8dC2LG/\u001f\u0011\u0002\u0017M\u0004XmY;mCRLg/Z\u000b\u0002uB\u00111h_\u0005\u0003yr\u0012qAQ8pY\u0016\fg.\u0001\u0007ta\u0016\u001cW\u000f\\1uSZ,\u0007%\u0001\nbG\u000e,X.\u001e7bi>\u0014X\u000b\u001d3bi\u0016\u001cXCAA\u0001!\u0019\t\u0019!!\u0003\u0002\u000e5\u0011\u0011Q\u0001\u0006\u0004\u0003\u000fa\u0014AC2pY2,7\r^5p]&!\u00111BA\u0003\u0005\r\u0019V-\u001d\t\u0005\u0003\u001f\t\t\"D\u0001-\u0013\r\t\u0019\u0002\f\u0002\u0010\u0003\u000e\u001cW/\\;mC\ndW-\u00138g_\u0006\u0019\u0012mY2v[Vd\u0017\r^8s+B$\u0017\r^3tA\u0005aQM\u001d:pe6+7o]1hKV\u0011\u00111\u0004\t\u0004wy;\u0017!D3se>\u0014X*Z:tC\u001e,\u0007%A\u0006uCN\\W*\u001a;sS\u000e\u001cXCAA\u0012!\u0011Yd,!\n\u0011\t\u0005=\u0011qE\u0005\u0004\u0003Sa#a\u0003+bg.lU\r\u001e:jGN\fA\u0002^1tW6+GO]5dg\u0002\nA\"\u001a=fGV$xN\u001d'pON,\"!!\r\u0011\u000b!\f\u0019dZ4\n\u0007\u0005U\u0012OA\u0002NCB\fQ\"\u001a=fGV$xN\u001d'pON\u0004\u0013AD:dQ\u0016$W\u000f\\3s\t\u0016d\u0017-_\u0001\u0010g\u000eDW\rZ;mKJ$U\r\\1zA\u0005\tr-\u001a;uS:<'+Z:vYR$\u0016.\\3\u0002%\u001d,G\u000f^5oOJ+7/\u001e7u)&lW\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015M\u0005\u0015\u0013qIA%\u0003\u0017\ni%a\u0014\u0002R\u0005M\u0013\u0011QAB\u0003\u000b\u000b9)!#\u0002\f\u00065\u0015qRAI\u0003'\u000b)\nE\u0002\u0002\u0010\u0001AQ\u0001Q\u0013A\u0002\tCQAR\u0013A\u0002!CQ\u0001T\u0013A\u0002!CQAT\u0013A\u0002!CQ\u0001U\u0013A\u0002ICQaW\u0013A\u0002uCQ!Y\u0013A\u0002\rD\u0003\"a\u0015\u0002X\u0005M\u0014Q\u000f\t\u0005\u00033\ny'\u0004\u0002\u0002\\)!\u0011QLA0\u0003)\tgN\\8uCRLwN\u001c\u0006\u0005\u0003C\n\u0019'\u0001\u0005eCR\f'-\u001b8e\u0015\u0011\t)'a\u001a\u0002\u000f)\f7m[:p]*!\u0011\u0011NA6\u0003%1\u0017m\u001d;feblGN\u0003\u0002\u0002n\u0005\u00191m\\7\n\t\u0005E\u00141\f\u0002\u0010\u0015N|g\u000eR3tKJL\u0017\r\\5{K\u0006I1m\u001c8uK:$\u0018i]\u0012\u0003\u0003o\u0002B!!\u001f\u0002\u00005\u0011\u00111\u0010\u0006\u0004\u0003{2\u0016\u0001\u00027b]\u001eL1\u0001RA>\u0011\u0015)W\u00051\u0001h\u0011\u0015\u0019X\u00051\u0001h\u0011\u0015\tT\u00051\u0001h\u0011\u00151X\u00051\u0001h\u0011\u0015AX\u00051\u0001{\u0011\u0019qX\u00051\u0001\u0002\u0002!I\u0011qC\u0013\u0011\u0002\u0003\u0007\u00111\u0004\u0005\n\u0003?)\u0003\u0013!a\u0001\u0003GAq!!\f&\u0001\u0004\t\t\u0004\u0003\u0004\u0002:\u0015\u0002\rA\u0011\u0005\u0007\u0003{)\u0003\u0019\u0001\"\u0002\u0011Q\u000b7o\u001b#bi\u0006\u00042!a\u0004('\t9#\b\u0006\u0002\u0002\u001a\u0006aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\"TCAARU\u0011\tY\"!*,\u0005\u0005\u001d\u0006\u0003BAU\u0003ck!!a+\u000b\t\u00055\u0016qV\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0018=\u0013\u0011\t\u0019,a+\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u000f%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%M\u001b\u0016\u0005\u0005e&\u0006BA\u0012\u0003K\u0003"
)
public class TaskData {
   private final long taskId;
   private final int index;
   private final int attempt;
   private final int partitionId;
   private final Date launchTime;
   private final Option resultFetchStart;
   private final Option duration;
   private final String executorId;
   private final String host;
   private final String status;
   private final String taskLocality;
   private final boolean speculative;
   private final Seq accumulatorUpdates;
   private final Option errorMessage;
   private final Option taskMetrics;
   private final Map executorLogs;
   private final long schedulerDelay;
   private final long gettingResultTime;

   public long taskId() {
      return this.taskId;
   }

   public int index() {
      return this.index;
   }

   public int attempt() {
      return this.attempt;
   }

   public int partitionId() {
      return this.partitionId;
   }

   public Date launchTime() {
      return this.launchTime;
   }

   public Option resultFetchStart() {
      return this.resultFetchStart;
   }

   public Option duration() {
      return this.duration;
   }

   public String executorId() {
      return this.executorId;
   }

   public String host() {
      return this.host;
   }

   public String status() {
      return this.status;
   }

   public String taskLocality() {
      return this.taskLocality;
   }

   public boolean speculative() {
      return this.speculative;
   }

   public Seq accumulatorUpdates() {
      return this.accumulatorUpdates;
   }

   public Option errorMessage() {
      return this.errorMessage;
   }

   public Option taskMetrics() {
      return this.taskMetrics;
   }

   public Map executorLogs() {
      return this.executorLogs;
   }

   public long schedulerDelay() {
      return this.schedulerDelay;
   }

   public long gettingResultTime() {
      return this.gettingResultTime;
   }

   public TaskData(final long taskId, final int index, final int attempt, final int partitionId, final Date launchTime, final Option resultFetchStart, @JsonDeserialize(contentAs = Long.class) final Option duration, final String executorId, final String host, final String status, final String taskLocality, final boolean speculative, final Seq accumulatorUpdates, final Option errorMessage, final Option taskMetrics, final Map executorLogs, final long schedulerDelay, final long gettingResultTime) {
      this.taskId = taskId;
      this.index = index;
      this.attempt = attempt;
      this.partitionId = partitionId;
      this.launchTime = launchTime;
      this.resultFetchStart = resultFetchStart;
      this.duration = duration;
      this.executorId = executorId;
      this.host = host;
      this.status = status;
      this.taskLocality = taskLocality;
      this.speculative = speculative;
      this.accumulatorUpdates = accumulatorUpdates;
      this.errorMessage = errorMessage;
      this.taskMetrics = taskMetrics;
      this.executorLogs = executorLogs;
      this.schedulerDelay = schedulerDelay;
      this.gettingResultTime = gettingResultTime;
   }
}
