package org.apache.spark.sql.hive.thriftserver.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I4Q\u0001E\t\u0001'}A\u0001B\n\u0001\u0003\u0006\u0004%\t\u0001\u000b\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005S!A\u0001\n\u0001BC\u0002\u0013\u0005\u0011\n\u0003\u0005N\u0001\t\u0005\t\u0015!\u0003K\u0011!q\u0005A!b\u0001\n\u0003A\u0003\u0002C(\u0001\u0005\u0003\u0005\u000b\u0011B\u0015\t\u0011A\u0003!Q1A\u0005\u0002!B\u0001\"\u0015\u0001\u0003\u0002\u0003\u0006I!\u000b\u0005\t%\u0002\u0011)\u0019!C\u0001\u0013\"A1\u000b\u0001B\u0001B\u0003%!\n\u0003\u0005U\u0001\t\u0015\r\u0011\"\u0001J\u0011!)\u0006A!A!\u0002\u0013Q\u0005\"\u0002,\u0001\t\u00039\u0006\"\u00021\u0001\t\u0013I\u0005\"B9\u0001\t\u0003I%aC*fgNLwN\\%oM>T!AE\n\u0002\u0005UL'B\u0001\u000b\u0016\u00031!\bN]5giN,'O^3s\u0015\t1r#\u0001\u0003iSZ,'B\u0001\r\u001a\u0003\r\u0019\u0018\u000f\u001c\u0006\u00035m\tQa\u001d9be.T!\u0001H\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0012aA8sON\u0011\u0001\u0001\t\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0002\u0013M,7o]5p]&#7\u0001A\u000b\u0002SA\u0011!&\r\b\u0003W=\u0002\"\u0001\f\u0012\u000e\u00035R!AL\u0014\u0002\rq\u0012xn\u001c;?\u0013\t\u0001$%\u0001\u0004Qe\u0016$WMZ\u0005\u0003eM\u0012aa\u0015;sS:<'B\u0001\u0019#Q\t\tQG\u000b\u00027}A\u0011q\u0007P\u0007\u0002q)\u0011\u0011HO\u0001\bWZ\u001cHo\u001c:f\u0015\tY\u0014$\u0001\u0003vi&d\u0017BA\u001f9\u0005\u001dYe+\u00138eKb\\\u0013a\u0010\t\u0003\u0001\u0016k\u0011!\u0011\u0006\u0003\u0005\u000e\u000bA!\\3uC*\u0011AII\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001$B\u0005\u00199W\r\u001e;fe\u0006Q1/Z:tS>t\u0017\n\u001a\u0011\u0002\u001dM$\u0018M\u001d;US6,7\u000f^1naV\t!\n\u0005\u0002\"\u0017&\u0011AJ\t\u0002\u0005\u0019>tw-A\bti\u0006\u0014H\u000fV5nKN$\u0018-\u001c9!\u0003\tI\u0007/A\u0002ja\u0002\n\u0001\"^:fe:\u000bW.Z\u0001\nkN,'OT1nK\u0002\nqBZ5oSNDG+[7fgR\fW\u000e]\u0001\u0011M&t\u0017n\u001d5US6,7\u000f^1na\u0002\na\u0002^8uC2,\u00050Z2vi&|g.A\bu_R\fG.\u0012=fGV$\u0018n\u001c8!\u0003\u0019a\u0014N\\5u}Q9\u0001LW.];z{\u0006CA-\u0001\u001b\u0005\t\u0002\"\u0002\u0014\u000e\u0001\u0004I\u0003\"\u0002%\u000e\u0001\u0004Q\u0005\"\u0002(\u000e\u0001\u0004I\u0003\"\u0002)\u000e\u0001\u0004I\u0003\"\u0002*\u000e\u0001\u0004Q\u0005\"\u0002+\u000e\u0001\u0004Q\u0015a\u00044j]&\u001c\b\u000eV5nK&sG-\u001a=)\u00059\u0011\u0007CA2l\u001b\u0005!'B\u0001#f\u0015\t1w-A\u0004kC\u000e\\7o\u001c8\u000b\u0005!L\u0017!\u00034bgR,'\u000f_7m\u0015\u0005Q\u0017aA2p[&\u0011A\u000e\u001a\u0002\u000b\u0015N|g.S4o_J,\u0007\u0006\u0002\b7]>\fQA^1mk\u0016\f\u0013\u0001]\u0001\u000bM&t\u0017n\u001d5US6,\u0017!\u0003;pi\u0006dG+[7f\u0001"
)
public class SessionInfo {
   private final String sessionId;
   private final long startTimestamp;
   private final String ip;
   private final String userName;
   private final long finishTimestamp;
   private final long totalExecution;

   @KVIndex
   public String sessionId() {
      return this.sessionId;
   }

   public long startTimestamp() {
      return this.startTimestamp;
   }

   public String ip() {
      return this.ip;
   }

   public String userName() {
      return this.userName;
   }

   public long finishTimestamp() {
      return this.finishTimestamp;
   }

   public long totalExecution() {
      return this.totalExecution;
   }

   @JsonIgnore
   @KVIndex("finishTime")
   private long finishTimeIndex() {
      return this.finishTimestamp() > 0L ? this.finishTimestamp() : -1L;
   }

   public long totalTime() {
      return this.finishTimestamp() == 0L ? System.currentTimeMillis() - this.startTimestamp() : this.finishTimestamp() - this.startTimestamp();
   }

   public SessionInfo(final String sessionId, final long startTimestamp, final String ip, final String userName, final long finishTimestamp, final long totalExecution) {
      this.sessionId = sessionId;
      this.startTimestamp = startTimestamp;
      this.ip = ip;
      this.userName = userName;
      this.finishTimestamp = finishTimestamp;
      this.totalExecution = totalExecution;
   }
}
