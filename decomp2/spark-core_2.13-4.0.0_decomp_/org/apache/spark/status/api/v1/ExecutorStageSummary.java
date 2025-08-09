package org.apache.spark.status.api.v1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001\u0002\u0013&\u0001IB\u0001\"\u000f\u0001\u0003\u0006\u0004%\tA\u000f\u0005\t}\u0001\u0011\t\u0011)A\u0005w!Aq\b\u0001BC\u0002\u0013\u0005\u0001\t\u0003\u0005E\u0001\t\u0005\t\u0015!\u0003B\u0011!)\u0005A!b\u0001\n\u0003\u0001\u0005\u0002\u0003$\u0001\u0005\u0003\u0005\u000b\u0011B!\t\u0011\u001d\u0003!Q1A\u0005\u0002\u0001C\u0001\u0002\u0013\u0001\u0003\u0002\u0003\u0006I!\u0011\u0005\t\u0013\u0002\u0011)\u0019!C\u0001u!A!\n\u0001B\u0001B\u0003%1\b\u0003\u0005L\u0001\t\u0015\r\u0011\"\u0001;\u0011!a\u0005A!A!\u0002\u0013Y\u0004\u0002C'\u0001\u0005\u000b\u0007I\u0011\u0001\u001e\t\u00119\u0003!\u0011!Q\u0001\nmB\u0001b\u0014\u0001\u0003\u0006\u0004%\tA\u000f\u0005\t!\u0002\u0011\t\u0011)A\u0005w!A\u0011\u000b\u0001BC\u0002\u0013\u0005!\b\u0003\u0005S\u0001\t\u0005\t\u0015!\u0003<\u0011!\u0019\u0006A!b\u0001\n\u0003Q\u0004\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011B\u001e\t\u0011U\u0003!Q1A\u0005\u0002iB\u0001B\u0016\u0001\u0003\u0002\u0003\u0006Ia\u000f\u0005\t/\u0002\u0011)\u0019!C\u0001u!A\u0001\f\u0001B\u0001B\u0003%1\b\u0003\u0005Z\u0001\t\u0015\r\u0011\"\u0001;\u0011!Q\u0006A!A!\u0002\u0013Y\u0004\u0002C.\u0001\u0005\u000b\u0007I\u0011\u0001\u001e\t\u0011q\u0003!\u0011!Q\u0001\nmB\u0001\"\u0018\u0001\u0003\u0006\u0004%\tA\u0018\u0005\tY\u0002\u0011\t\u0011)A\u0005?\"Aa\u000e\u0001BC\u0002\u0013\u0005q\u000e\u0003\u0005z\u0001\t\u0005\t\u0015!\u0003q\u0011!Q\bA!b\u0001\n\u0003q\u0006\u0002C>\u0001\u0005\u0003\u0005\u000b\u0011B0\t\rq\u0004A\u0011A\u0016~\u0005Q)\u00050Z2vi>\u00148\u000b^1hKN+X.\\1ss*\u0011aeJ\u0001\u0003mFR!\u0001K\u0015\u0002\u0007\u0005\u0004\u0018N\u0003\u0002+W\u000511\u000f^1ukNT!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\u0002\u0001'\t\u00011\u0007\u0005\u00025o5\tQGC\u00017\u0003\u0015\u00198-\u00197b\u0013\tATG\u0001\u0004B]f\u0014VMZ\u0001\ti\u0006\u001c8\u000eV5nKV\t1\b\u0005\u00025y%\u0011Q(\u000e\u0002\u0005\u0019>tw-A\u0005uCN\\G+[7fA\u0005Ya-Y5mK\u0012$\u0016m]6t+\u0005\t\u0005C\u0001\u001bC\u0013\t\u0019UGA\u0002J]R\fABZ1jY\u0016$G+Y:lg\u0002\nab];dG\u0016,G-\u001a3UCN\\7/A\btk\u000e\u001cW-\u001a3fIR\u000b7o[:!\u0003-Y\u0017\u000e\u001c7fIR\u000b7o[:\u0002\u0019-LG\u000e\\3e)\u0006\u001c8n\u001d\u0011\u0002\u0015%t\u0007/\u001e;CsR,7/A\u0006j]B,HOQ=uKN\u0004\u0013\u0001D5oaV$(+Z2pe\u0012\u001c\u0018!D5oaV$(+Z2pe\u0012\u001c\b%A\u0006pkR\u0004X\u000f\u001e\"zi\u0016\u001c\u0018\u0001D8viB,HOQ=uKN\u0004\u0013!D8viB,HOU3d_J$7/\u0001\bpkR\u0004X\u000f\u001e*fG>\u0014Hm\u001d\u0011\u0002\u0017MDWO\u001a4mKJ+\u0017\rZ\u0001\rg\",hM\u001a7f%\u0016\fG\rI\u0001\u0013g\",hM\u001a7f%\u0016\fGMU3d_J$7/A\ntQV4g\r\\3SK\u0006$'+Z2pe\u0012\u001c\b%\u0001\u0007tQV4g\r\\3Xe&$X-A\u0007tQV4g\r\\3Xe&$X\rI\u0001\u0014g\",hM\u001a7f/JLG/\u001a*fG>\u0014Hm]\u0001\u0015g\",hM\u001a7f/JLG/\u001a*fG>\u0014Hm\u001d\u0011\u0002%5,Wn\u001c:z\u0005f$Xm]*qS2dW\rZ\u0001\u0014[\u0016lwN]=CsR,7o\u00159jY2,G\rI\u0001\u0011I&\u001c8NQ=uKN\u001c\u0006/\u001b7mK\u0012\f\u0011\u0003Z5tW\nKH/Z:Ta&dG.\u001a3!\u0003UI7O\u00117bG.d\u0017n\u001d;fI\u001a{'o\u0015;bO\u0016,\u0012a\u0018\t\u0003i\u0001L!!Y\u001b\u0003\u000f\t{w\u000e\\3b]\"2Qd\u00194hS*\u0004\"\u0001\u000e3\n\u0005\u0015,$A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017%\u00015\u0002=U\u001cX\rI5t\u000bb\u001cG.\u001e3fI\u001a{'o\u0015;bO\u0016\u0004\u0013N\\:uK\u0006$\u0017!B:j]\u000e,\u0017%A6\u0002\u000bMr\u0013G\f\u0019\u0002-%\u001c(\t\\1dW2L7\u000f^3e\r>\u00148\u000b^1hK\u0002BcAH2gO&T\u0017!\u00059fC.lU-\\8ss6+GO]5dgV\t\u0001\u000fE\u00025cNL!A]\u001b\u0003\r=\u0003H/[8o!\t!x/D\u0001v\u0015\t18&\u0001\u0005fq\u0016\u001cW\u000f^8s\u0013\tAXOA\bFq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t\u0003I\u0001X-Y6NK6|'/_'fiJL7m\u001d\u0011\u0002%%\u001cX\t_2mk\u0012,GMR8s'R\fw-Z\u0001\u0014SN,\u0005p\u00197vI\u0016$gi\u001c:Ti\u0006<W\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015Gy\f\t!a\u0001\u0002\u0006\u0005\u001d\u0011\u0011BA\u0006\u0003\u001b\ty!!\u0005\u0002\u0014\u0005U\u0011qCA\r\u00037\ti\"a\b\u0002ZA\u0011q\u0010A\u0007\u0002K!)\u0011h\ta\u0001w!)qh\ta\u0001\u0003\")Qi\ta\u0001\u0003\")qi\ta\u0001\u0003\")\u0011j\ta\u0001w!)1j\ta\u0001w!)Qj\ta\u0001w!)qj\ta\u0001w!)\u0011k\ta\u0001w!)1k\ta\u0001w!)Qk\ta\u0001w!)qk\ta\u0001w!)\u0011l\ta\u0001w!)1l\ta\u0001w!)Ql\ta\u0001?\")an\ta\u0001a\"B\u0011qDA\u0012\u0003\u007f\t\t\u0005\u0005\u0003\u0002&\u0005mRBAA\u0014\u0015\u0011\tI#a\u000b\u0002\u0015\u0005tgn\u001c;bi&|gN\u0003\u0003\u0002.\u0005=\u0012\u0001\u00033bi\u0006\u0014\u0017N\u001c3\u000b\t\u0005E\u00121G\u0001\bU\u0006\u001c7n]8o\u0015\u0011\t)$a\u000e\u0002\u0013\u0019\f7\u000f^3sq6d'BAA\u001d\u0003\r\u0019w.\\\u0005\u0005\u0003{\t9CA\u0007Kg>t7+\u001a:jC2L'0Z\u0001\u0006kNLgnZ\u0012\u0003\u0003\u0007\u00022a`A#\u0013\r\t9%\n\u0002\u001e\u000bb,7-\u001e;pe6+GO]5dg*\u001bxN\\*fe&\fG.\u001b>fe\"B\u0011qDA&\u0003\u007f\t\t\u0006\u0005\u0003\u0002&\u00055\u0013\u0002BA(\u0003O\u0011qBS:p]\u0012+7/\u001a:jC2L'0Z\u0012\u0003\u0003'\u00022a`A+\u0013\r\t9&\n\u0002 \u000bb,7-\u001e;pe6+GO]5dg*\u001bxN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\"\u0002>$\u0001\u0004y\u0006"
)
public class ExecutorStageSummary {
   private final long taskTime;
   private final int failedTasks;
   private final int succeededTasks;
   private final int killedTasks;
   private final long inputBytes;
   private final long inputRecords;
   private final long outputBytes;
   private final long outputRecords;
   private final long shuffleRead;
   private final long shuffleReadRecords;
   private final long shuffleWrite;
   private final long shuffleWriteRecords;
   private final long memoryBytesSpilled;
   private final long diskBytesSpilled;
   /** @deprecated */
   private final boolean isBlacklistedForStage;
   private final Option peakMemoryMetrics;
   private final boolean isExcludedForStage;

   public long taskTime() {
      return this.taskTime;
   }

   public int failedTasks() {
      return this.failedTasks;
   }

   public int succeededTasks() {
      return this.succeededTasks;
   }

   public int killedTasks() {
      return this.killedTasks;
   }

   public long inputBytes() {
      return this.inputBytes;
   }

   public long inputRecords() {
      return this.inputRecords;
   }

   public long outputBytes() {
      return this.outputBytes;
   }

   public long outputRecords() {
      return this.outputRecords;
   }

   public long shuffleRead() {
      return this.shuffleRead;
   }

   public long shuffleReadRecords() {
      return this.shuffleReadRecords;
   }

   public long shuffleWrite() {
      return this.shuffleWrite;
   }

   public long shuffleWriteRecords() {
      return this.shuffleWriteRecords;
   }

   public long memoryBytesSpilled() {
      return this.memoryBytesSpilled;
   }

   public long diskBytesSpilled() {
      return this.diskBytesSpilled;
   }

   /** @deprecated */
   public boolean isBlacklistedForStage() {
      return this.isBlacklistedForStage;
   }

   public Option peakMemoryMetrics() {
      return this.peakMemoryMetrics;
   }

   public boolean isExcludedForStage() {
      return this.isExcludedForStage;
   }

   public ExecutorStageSummary(final long taskTime, final int failedTasks, final int succeededTasks, final int killedTasks, final long inputBytes, final long inputRecords, final long outputBytes, final long outputRecords, final long shuffleRead, final long shuffleReadRecords, final long shuffleWrite, final long shuffleWriteRecords, final long memoryBytesSpilled, final long diskBytesSpilled, final boolean isBlacklistedForStage, @JsonSerialize(using = ExecutorMetricsJsonSerializer.class) @JsonDeserialize(using = ExecutorMetricsJsonDeserializer.class) final Option peakMemoryMetrics, final boolean isExcludedForStage) {
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
      this.isBlacklistedForStage = isBlacklistedForStage;
      this.peakMemoryMetrics = peakMemoryMetrics;
      this.isExcludedForStage = isExcludedForStage;
   }
}
