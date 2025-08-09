package org.apache.spark.sql.hive.thriftserver.ui;

import org.apache.spark.sql.hive.thriftserver.HiveThriftServer2;
import org.apache.spark.status.LiveEntity;
import scala.Enumeration;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mc!B\u0011#\u0001\u0011\u0002\u0004\u0002C\u001c\u0001\u0005\u000b\u0007I\u0011A\u001d\t\u0011\u001d\u0003!\u0011!Q\u0001\niB\u0001\u0002\u0013\u0001\u0003\u0006\u0004%\t!\u000f\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005u!A!\n\u0001BC\u0002\u0013\u0005\u0011\b\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003;\u0011!a\u0005A!b\u0001\n\u0003i\u0005\u0002\u0003*\u0001\u0005\u0003\u0005\u000b\u0011\u0002(\t\u0011M\u0003!Q1A\u0005\u0002eB\u0001\u0002\u0016\u0001\u0003\u0002\u0003\u0006IA\u000f\u0005\u0006+\u0002!\tA\u0016\u0005\b=\u0002\u0001\r\u0011\"\u0001N\u0011\u001dy\u0006\u00011A\u0005\u0002\u0001DaA\u001a\u0001!B\u0013q\u0005bB4\u0001\u0001\u0004%\t!\u0014\u0005\bQ\u0002\u0001\r\u0011\"\u0001j\u0011\u0019Y\u0007\u0001)Q\u0005\u001d\"9A\u000e\u0001a\u0001\n\u0003I\u0004bB7\u0001\u0001\u0004%\tA\u001c\u0005\u0007a\u0002\u0001\u000b\u0015\u0002\u001e\t\u000fE\u0004\u0001\u0019!C\u0001s!9!\u000f\u0001a\u0001\n\u0003\u0019\bBB;\u0001A\u0003&!\bC\u0004w\u0001\u0001\u0007I\u0011A<\t\u0013\u0005\u0005\u0002\u00011A\u0005\u0002\u0005\r\u0002bBA\u0014\u0001\u0001\u0006K\u0001\u001f\u0005\n\u0003S\u0001!\u0019!C\u0001\u0003WA\u0001\"!\u0010\u0001A\u0003%\u0011Q\u0006\u0005\t\u0003\u007f\u0001\u0001\u0019!C\u0001s!I\u0011\u0011\t\u0001A\u0002\u0013\u0005\u00111\t\u0005\b\u0003\u000f\u0002\u0001\u0015)\u0003;\u0011\u001d\tI\u0005\u0001C)\u0003\u0017\u0012\u0011\u0003T5wK\u0016CXmY;uS>tG)\u0019;b\u0015\t\u0019C%\u0001\u0002vS*\u0011QEJ\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003O!\nA\u0001[5wK*\u0011\u0011FK\u0001\u0004gFd'BA\u0016-\u0003\u0015\u0019\b/\u0019:l\u0015\tic&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002_\u0005\u0019qN]4\u0014\u0005\u0001\t\u0004C\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b+\u0003\u0019\u0019H/\u0019;vg&\u0011ag\r\u0002\u000b\u0019&4X-\u00128uSRL\u0018AB3yK\u000eLEm\u0001\u0001\u0016\u0003i\u0002\"a\u000f#\u000f\u0005q\u0012\u0005CA\u001fA\u001b\u0005q$BA 9\u0003\u0019a$o\\8u})\t\u0011)A\u0003tG\u0006d\u0017-\u0003\u0002D\u0001\u00061\u0001K]3eK\u001aL!!\u0012$\u0003\rM#(/\u001b8h\u0015\t\u0019\u0005)A\u0004fq\u0016\u001c\u0017\n\u001a\u0011\u0002\u0013M$\u0018\r^3nK:$\u0018AC:uCR,W.\u001a8uA\u0005I1/Z:tS>t\u0017\nZ\u0001\u000bg\u0016\u001c8/[8o\u0013\u0012\u0004\u0013AD:uCJ$H+[7fgR\fW\u000e]\u000b\u0002\u001dB\u0011q\nU\u0007\u0002\u0001&\u0011\u0011\u000b\u0011\u0002\u0005\u0019>tw-A\bti\u0006\u0014H\u000fV5nKN$\u0018-\u001c9!\u0003!)8/\u001a:OC6,\u0017!C;tKJt\u0015-\\3!\u0003\u0019a\u0014N\\5u}Q1q+\u0017.\\9v\u0003\"\u0001\u0017\u0001\u000e\u0003\tBQaN\u0006A\u0002iBQ\u0001S\u0006A\u0002iBQAS\u0006A\u0002iBQ\u0001T\u0006A\u00029CQaU\u0006A\u0002i\nqBZ5oSNDG+[7fgR\fW\u000e]\u0001\u0014M&t\u0017n\u001d5US6,7\u000f^1na~#S-\u001d\u000b\u0003C\u0012\u0004\"a\u00142\n\u0005\r\u0004%\u0001B+oSRDq!Z\u0007\u0002\u0002\u0003\u0007a*A\u0002yIE\n\u0001CZ5oSNDG+[7fgR\fW\u000e\u001d\u0011\u0002\u001d\rdwn]3US6,7\u000f^1na\u0006\u00112\r\\8tKRKW.Z:uC6\u0004x\fJ3r)\t\t'\u000eC\u0004f!\u0005\u0005\t\u0019\u0001(\u0002\u001f\rdwn]3US6,7\u000f^1na\u0002\n1\"\u001a=fGV$X\r\u00157b]\u0006yQ\r_3dkR,\u0007\u000b\\1o?\u0012*\u0017\u000f\u0006\u0002b_\"9QmEA\u0001\u0002\u0004Q\u0014\u0001D3yK\u000e,H/\u001a)mC:\u0004\u0013A\u00023fi\u0006LG.\u0001\u0006eKR\f\u0017\u000e\\0%KF$\"!\u0019;\t\u000f\u00154\u0012\u0011!a\u0001u\u00059A-\u001a;bS2\u0004\u0013!B:uCR,W#\u0001=\u0011\u0007e\fIBD\u0002{\u0003'q1a_A\b\u001d\ra\u0018Q\u0002\b\u0004{\u0006-ab\u0001@\u0002\n9\u0019q0a\u0002\u000f\t\u0005\u0005\u0011Q\u0001\b\u0004{\u0005\r\u0011\"A\u0018\n\u00055r\u0013BA\u0016-\u0013\tI#&\u0003\u0002(Q%\u0011QEJ\u0005\u0004\u0003#!\u0013!\u0005%jm\u0016$\u0006N]5giN+'O^3se%!\u0011QCA\f\u00039)\u00050Z2vi&|gn\u0015;bi\u0016T1!!\u0005%\u0013\u0011\tY\"!\b\u0003\u000bY\u000bG.^3\n\u0007\u0005}\u0001IA\u0006F]VlWM]1uS>t\u0017!C:uCR,w\fJ3r)\r\t\u0017Q\u0005\u0005\bKf\t\t\u00111\u0001y\u0003\u0019\u0019H/\u0019;fA\u0005)!n\u001c2JIV\u0011\u0011Q\u0006\t\u0006\u0003_\tIDO\u0007\u0003\u0003cQA!a\r\u00026\u00059Q.\u001e;bE2,'bAA\u001c\u0001\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005m\u0012\u0011\u0007\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'/\u0001\u0004k_\nLE\rI\u0001\bOJ|W\u000f]%e\u0003-9'o\\;q\u0013\u0012|F%Z9\u0015\u0007\u0005\f)\u0005C\u0004f=\u0005\u0005\t\u0019\u0001\u001e\u0002\u0011\u001d\u0014x.\u001e9JI\u0002\n\u0001\u0002Z8Va\u0012\fG/\u001a\u000b\u0003\u0003\u001b\u00022aTA(\u0013\r\t\t\u0006\u0011\u0002\u0004\u0003:L\b"
)
public class LiveExecutionData extends LiveEntity {
   private final String execId;
   private final String statement;
   private final String sessionId;
   private final long startTimestamp;
   private final String userName;
   private long finishTimestamp;
   private long closeTimestamp;
   private String executePlan;
   private String detail;
   private Enumeration.Value state;
   private final ArrayBuffer jobId;
   private String groupId;

   public String execId() {
      return this.execId;
   }

   public String statement() {
      return this.statement;
   }

   public String sessionId() {
      return this.sessionId;
   }

   public long startTimestamp() {
      return this.startTimestamp;
   }

   public String userName() {
      return this.userName;
   }

   public long finishTimestamp() {
      return this.finishTimestamp;
   }

   public void finishTimestamp_$eq(final long x$1) {
      this.finishTimestamp = x$1;
   }

   public long closeTimestamp() {
      return this.closeTimestamp;
   }

   public void closeTimestamp_$eq(final long x$1) {
      this.closeTimestamp = x$1;
   }

   public String executePlan() {
      return this.executePlan;
   }

   public void executePlan_$eq(final String x$1) {
      this.executePlan = x$1;
   }

   public String detail() {
      return this.detail;
   }

   public void detail_$eq(final String x$1) {
      this.detail = x$1;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public ArrayBuffer jobId() {
      return this.jobId;
   }

   public String groupId() {
      return this.groupId;
   }

   public void groupId_$eq(final String x$1) {
      this.groupId = x$1;
   }

   public Object doUpdate() {
      return new ExecutionInfo(this.execId(), this.statement(), this.sessionId(), this.startTimestamp(), this.userName(), this.finishTimestamp(), this.closeTimestamp(), this.executePlan(), this.detail(), this.state(), this.jobId(), this.groupId());
   }

   public LiveExecutionData(final String execId, final String statement, final String sessionId, final long startTimestamp, final String userName) {
      this.execId = execId;
      this.statement = statement;
      this.sessionId = sessionId;
      this.startTimestamp = startTimestamp;
      this.userName = userName;
      this.finishTimestamp = 0L;
      this.closeTimestamp = 0L;
      this.executePlan = "";
      this.detail = "";
      this.state = HiveThriftServer2.ExecutionState$.MODULE$.STARTED();
      this.jobId = (ArrayBuffer).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.groupId = "";
   }
}
