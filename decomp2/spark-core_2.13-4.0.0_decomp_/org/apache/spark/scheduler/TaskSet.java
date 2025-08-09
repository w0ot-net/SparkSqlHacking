package org.apache.spark.scheduler;

import java.util.HashMap;
import java.util.Properties;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.LogKeys.STAGE_ID.;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4Q\u0001F\u000b\u0001/uA\u0001\u0002\n\u0001\u0003\u0006\u0004%\tA\n\u0005\tg\u0001\u0011\t\u0011)A\u0005O!A1\b\u0001BC\u0002\u0013\u0005A\b\u0003\u0005A\u0001\t\u0005\t\u0015!\u0003>\u0011!\t\u0005A!b\u0001\n\u0003a\u0004\u0002\u0003\"\u0001\u0005\u0003\u0005\u000b\u0011B\u001f\t\u0011\r\u0003!Q1A\u0005\u0002qB\u0001\u0002\u0012\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\t\u000b\u0002\u0011)\u0019!C\u0001\r\"Aq\n\u0001B\u0001B\u0003%q\t\u0003\u0005Q\u0001\t\u0015\r\u0011\"\u0001=\u0011!\t\u0006A!A!\u0002\u0013i\u0004\u0002\u0003*\u0001\u0005\u000b\u0007I\u0011A*\t\u0011]\u0003!\u0011!Q\u0001\nQCQ\u0001\u0017\u0001\u0005\u0002eCqa\u001a\u0001C\u0002\u0013\u0005\u0001\u000e\u0003\u0004u\u0001\u0001\u0006I!\u001b\u0005\u0006k\u0002!\tE\u001e\u0005\to\u0002A)\u0019!C\u0001q\n9A+Y:l'\u0016$(B\u0001\f\u0018\u0003%\u00198\r[3ek2,'O\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h'\t\u0001a\u0004\u0005\u0002 E5\t\u0001EC\u0001\"\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0003E\u0001\u0004B]f\u0014VMZ\u0001\u0006i\u0006\u001c8n]\u0002\u0001+\u00059\u0003cA\u0010)U%\u0011\u0011\u0006\t\u0002\u0006\u0003J\u0014\u0018-\u001f\u0019\u0003WE\u00022\u0001L\u00170\u001b\u0005)\u0012B\u0001\u0018\u0016\u0005\u0011!\u0016m]6\u0011\u0005A\nD\u0002\u0001\u0003\ne\t\t\t\u0011!A\u0003\u0002Q\u00121a\u0018\u00132\u0003\u0019!\u0018m]6tAE\u0011Q\u0007\u000f\t\u0003?YJ!a\u000e\u0011\u0003\u000f9{G\u000f[5oOB\u0011q$O\u0005\u0003u\u0001\u00121!\u00118z\u0003\u001d\u0019H/Y4f\u0013\u0012,\u0012!\u0010\t\u0003?yJ!a\u0010\u0011\u0003\u0007%sG/\u0001\u0005ti\u0006<W-\u00133!\u00039\u0019H/Y4f\u0003R$X-\u001c9u\u0013\u0012\fqb\u001d;bO\u0016\fE\u000f^3naRLE\rI\u0001\taJLwN]5us\u0006I\u0001O]5pe&$\u0018\u0010I\u0001\u000baJ|\u0007/\u001a:uS\u0016\u001cX#A$\u0011\u0005!kU\"A%\u000b\u0005)[\u0015\u0001B;uS2T\u0011\u0001T\u0001\u0005U\u00064\u0018-\u0003\u0002O\u0013\nQ\u0001K]8qKJ$\u0018.Z:\u0002\u0017A\u0014x\u000e]3si&,7\u000fI\u0001\u0012e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#\u0017A\u0005:fg>,(oY3Qe>4\u0017\u000e\\3JI\u0002\n\u0011b\u001d5vM\u001adW-\u00133\u0016\u0003Q\u00032aH+>\u0013\t1\u0006E\u0001\u0004PaRLwN\\\u0001\u000bg\",hM\u001a7f\u0013\u0012\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0005[7\u0006\u00147\rZ3g!\ta\u0003\u0001C\u0003%\u001f\u0001\u0007A\fE\u0002 Qu\u0003$A\u00181\u0011\u00071js\f\u0005\u00021A\u0012I!gWA\u0001\u0002\u0003\u0015\t\u0001\u000e\u0005\u0006w=\u0001\r!\u0010\u0005\u0006\u0003>\u0001\r!\u0010\u0005\u0006\u0007>\u0001\r!\u0010\u0005\u0006\u000b>\u0001\ra\u0012\u0005\u0006!>\u0001\r!\u0010\u0005\u0006%>\u0001\r\u0001V\u0001\u0003S\u0012,\u0012!\u001b\t\u0003UFt!a[8\u0011\u00051\u0004S\"A7\u000b\u00059,\u0013A\u0002\u001fs_>$h(\u0003\u0002qA\u00051\u0001K]3eK\u001aL!A]:\u0003\rM#(/\u001b8h\u0015\t\u0001\b%A\u0002jI\u0002\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002S\u0006)An\\4JIV\t\u0011\u0010\u0005\u0002{{6\t1P\u0003\u0002}/\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\u007fw\n\u0011R*Z:tC\u001e,w+\u001b;i\u0007>tG/\u001a=u\u0001"
)
public class TaskSet {
   private MessageWithContext logId;
   private final Task[] tasks;
   private final int stageId;
   private final int stageAttemptId;
   private final int priority;
   private final Properties properties;
   private final int resourceProfileId;
   private final Option shuffleId;
   private final String id;
   private volatile boolean bitmap$0;

   public Task[] tasks() {
      return this.tasks;
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public int priority() {
      return this.priority;
   }

   public Properties properties() {
      return this.properties;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public Option shuffleId() {
      return this.shuffleId;
   }

   public String id() {
      return this.id;
   }

   public String toString() {
      return "TaskSet " + this.id();
   }

   private MessageWithContext logId$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            HashMap hashMap = new HashMap();
            hashMap.put(.MODULE$.name(), Integer.toString(this.stageId()));
            hashMap.put(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT_ID..MODULE$.name(), Integer.toString(this.stageAttemptId()));
            this.logId = new MessageWithContext(this.id(), hashMap);
            this.bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.logId;
   }

   public MessageWithContext logId() {
      return !this.bitmap$0 ? this.logId$lzycompute() : this.logId;
   }

   public TaskSet(final Task[] tasks, final int stageId, final int stageAttemptId, final int priority, final Properties properties, final int resourceProfileId, final Option shuffleId) {
      this.tasks = tasks;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.priority = priority;
      this.properties = properties;
      this.resourceProfileId = resourceProfileId;
      this.shuffleId = shuffleId;
      this.id = stageId + "." + stageAttemptId;
   }
}
