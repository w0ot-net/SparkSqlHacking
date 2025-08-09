package org.apache.spark.ui.jobs;

import org.apache.spark.status.api.v1.JobData;
import scala.reflect.ScalaSignature;
import scala.xml.NodeSeq;

@ScalaSignature(
   bytes = "\u0006\u0005!4Q\u0001F\u000b\u0001/}A\u0001B\n\u0001\u0003\u0006\u0004%\t\u0001\u000b\u0005\tg\u0001\u0011\t\u0011)A\u0005S!AA\u0007\u0001BC\u0002\u0013\u0005Q\u0007\u0003\u0005B\u0001\t\u0005\t\u0015!\u00037\u0011!\u0011\u0005A!b\u0001\n\u0003)\u0004\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001c\t\u0011\u0011\u0003!Q1A\u0005\u0002\u0015C\u0001\"\u0013\u0001\u0003\u0002\u0003\u0006IA\u0012\u0005\t\u0015\u0002\u0011)\u0019!C\u0001k!A1\n\u0001B\u0001B\u0003%a\u0007\u0003\u0005M\u0001\t\u0015\r\u0011\"\u0001F\u0011!i\u0005A!A!\u0002\u00131\u0005\u0002\u0003(\u0001\u0005\u000b\u0007I\u0011A\u001b\t\u0011=\u0003!\u0011!Q\u0001\nYB\u0001\u0002\u0015\u0001\u0003\u0006\u0004%\t!\u0015\u0005\t1\u0002\u0011\t\u0011)A\u0005%\"A\u0011\f\u0001BC\u0002\u0013\u0005Q\u0007\u0003\u0005[\u0001\t\u0005\t\u0015!\u00037\u0011\u0015Y\u0006\u0001\"\u0001]\u0005=QuN\u0019+bE2,'k\\<ECR\f'B\u0001\f\u0018\u0003\u0011QwNY:\u000b\u0005aI\u0012AA;j\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<7C\u0001\u0001!!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u0019\te.\u001f*fM\u00069!n\u001c2ECR\f7\u0001A\u000b\u0002SA\u0011!&M\u0007\u0002W)\u0011A&L\u0001\u0003mFR!AL\u0018\u0002\u0007\u0005\u0004\u0018N\u0003\u000213\u000511\u000f^1ukNL!AM\u0016\u0003\u000f){'\rR1uC\u0006A!n\u001c2ECR\f\u0007%A\u0007mCN$8\u000b^1hK:\u000bW.Z\u000b\u0002mA\u0011qG\u0010\b\u0003qq\u0002\"!\u000f\u0012\u000e\u0003iR!aO\u0014\u0002\rq\u0012xn\u001c;?\u0013\ti$%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u007f\u0001\u0013aa\u0015;sS:<'BA\u001f#\u00039a\u0017m\u001d;Ti\u0006<WMT1nK\u0002\nA\u0003\\1tiN#\u0018mZ3EKN\u001c'/\u001b9uS>t\u0017!\u00067bgR\u001cF/Y4f\t\u0016\u001c8M]5qi&|g\u000eI\u0001\tIV\u0014\u0018\r^5p]V\ta\t\u0005\u0002\"\u000f&\u0011\u0001J\t\u0002\u0005\u0019>tw-A\u0005ekJ\fG/[8oA\u0005\tbm\u001c:nCR$X\r\u001a#ve\u0006$\u0018n\u001c8\u0002%\u0019|'/\\1ui\u0016$G)\u001e:bi&|g\u000eI\u0001\u000fgV\u0014W.[:tS>tG+[7f\u0003=\u0019XOY7jgNLwN\u001c+j[\u0016\u0004\u0013a\u00064pe6\fG\u000f^3e'V\u0014W.[:tS>tG+[7f\u0003a1wN]7biR,GmU;c[&\u001c8/[8o)&lW\rI\u0001\u000fU>\u0014G)Z:de&\u0004H/[8o+\u0005\u0011\u0006CA*W\u001b\u0005!&BA+#\u0003\rAX\u000e\\\u0005\u0003/R\u0013qAT8eKN+\u0017/A\bk_\n$Um]2sSB$\u0018n\u001c8!\u0003%!W\r^1jYV\u0013H.\u0001\u0006eKR\f\u0017\u000e\\+sY\u0002\na\u0001P5oSRtDCC/`A\u0006\u00147\rZ3gOB\u0011a\fA\u0007\u0002+!)ae\u0005a\u0001S!)Ag\u0005a\u0001m!)!i\u0005a\u0001m!)Ai\u0005a\u0001\r\")!j\u0005a\u0001m!)Aj\u0005a\u0001\r\")aj\u0005a\u0001m!)\u0001k\u0005a\u0001%\")\u0011l\u0005a\u0001m\u0001"
)
public class JobTableRowData {
   private final JobData jobData;
   private final String lastStageName;
   private final String lastStageDescription;
   private final long duration;
   private final String formattedDuration;
   private final long submissionTime;
   private final String formattedSubmissionTime;
   private final NodeSeq jobDescription;
   private final String detailUrl;

   public JobData jobData() {
      return this.jobData;
   }

   public String lastStageName() {
      return this.lastStageName;
   }

   public String lastStageDescription() {
      return this.lastStageDescription;
   }

   public long duration() {
      return this.duration;
   }

   public String formattedDuration() {
      return this.formattedDuration;
   }

   public long submissionTime() {
      return this.submissionTime;
   }

   public String formattedSubmissionTime() {
      return this.formattedSubmissionTime;
   }

   public NodeSeq jobDescription() {
      return this.jobDescription;
   }

   public String detailUrl() {
      return this.detailUrl;
   }

   public JobTableRowData(final JobData jobData, final String lastStageName, final String lastStageDescription, final long duration, final String formattedDuration, final long submissionTime, final String formattedSubmissionTime, final NodeSeq jobDescription, final String detailUrl) {
      this.jobData = jobData;
      this.lastStageName = lastStageName;
      this.lastStageDescription = lastStageDescription;
      this.duration = duration;
      this.formattedDuration = formattedDuration;
      this.submissionTime = submissionTime;
      this.formattedSubmissionTime = formattedSubmissionTime;
      this.jobDescription = jobDescription;
      this.detailUrl = detailUrl;
   }
}
