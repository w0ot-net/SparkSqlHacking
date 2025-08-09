package org.apache.spark.sql.hive.thriftserver.ui;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.sql.hive.thriftserver.HiveThriftServer2;
import org.apache.spark.util.kvstore.KVIndex;
import scala.Enumeration;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud!B\u000f\u001f\u0001\u0001b\u0003\u0002C\u001a\u0001\u0005\u000b\u0007I\u0011A\u001b\t\u0011Q\u0003!\u0011!Q\u0001\nYB\u0001\"\u0016\u0001\u0003\u0006\u0004%\t!\u000e\u0005\t-\u0002\u0011\t\u0011)A\u0005m!Aq\u000b\u0001BC\u0002\u0013\u0005Q\u0007\u0003\u0005Y\u0001\t\u0005\t\u0015!\u00037\u0011!I\u0006A!b\u0001\n\u0003Q\u0006\u0002\u00030\u0001\u0005\u0003\u0005\u000b\u0011B.\t\u0011}\u0003!Q1A\u0005\u0002UB\u0001\u0002\u0019\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\tC\u0002\u0011)\u0019!C\u00015\"A!\r\u0001B\u0001B\u0003%1\f\u0003\u0005d\u0001\t\u0015\r\u0011\"\u0001[\u0011!!\u0007A!A!\u0002\u0013Y\u0006\u0002C3\u0001\u0005\u000b\u0007I\u0011A\u001b\t\u0011\u0019\u0004!\u0011!Q\u0001\nYB\u0001b\u001a\u0001\u0003\u0006\u0004%\t!\u000e\u0005\tQ\u0002\u0011\t\u0011)A\u0005m!A\u0011\u000e\u0001BC\u0002\u0013\u0005!\u000eC\u0005\u0002\b\u0001\u0011\t\u0011)A\u0005W\"Q\u0011\u0011\u0002\u0001\u0003\u0006\u0004%\t!a\u0003\t\u0015\u0005u\u0001A!A!\u0002\u0013\ti\u0001C\u0005\u0002 \u0001\u0011)\u0019!C\u0001k!I\u0011\u0011\u0005\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\b\u0003G\u0001A\u0011AA\u0013\u0011\u0019\t\u0019\u0005\u0001C\u00055\"9\u0011Q\r\u0001\u0005\u0002\u0005\u001d\u0004bBA;\u0001\u0011\u0005\u0011q\u000f\u0002\u000e\u000bb,7-\u001e;j_:LeNZ8\u000b\u0005}\u0001\u0013AA;j\u0015\t\t#%\u0001\u0007uQJLg\r^:feZ,'O\u0003\u0002$I\u0005!\u0001.\u001b<f\u0015\t)c%A\u0002tc2T!a\n\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005%R\u0013AB1qC\u000eDWMC\u0001,\u0003\ry'oZ\n\u0003\u00015\u0002\"AL\u0019\u000e\u0003=R\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\u0012a!\u00118z%\u00164\u0017AB3yK\u000eLEm\u0001\u0001\u0016\u0003Y\u0002\"a\u000e \u000f\u0005ab\u0004CA\u001d0\u001b\u0005Q$BA\u001e5\u0003\u0019a$o\\8u}%\u0011QhL\u0001\u0007!J,G-\u001a4\n\u0005}\u0002%AB*ue&twM\u0003\u0002>_!\u0012\u0011A\u0011\u0016\u0003\u0007.\u0003\"\u0001R%\u000e\u0003\u0015S!AR$\u0002\u000f-48\u000f^8sK*\u0011\u0001JJ\u0001\u0005kRLG.\u0003\u0002K\u000b\n91JV%oI\u0016D8&\u0001'\u0011\u00055\u0013V\"\u0001(\u000b\u0005=\u0003\u0016\u0001B7fi\u0006T!!U\u0018\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002T\u001d\n1q-\u001a;uKJ\fq!\u001a=fG&#\u0007%A\u0005ti\u0006$X-\\3oi\u0006Q1\u000f^1uK6,g\u000e\u001e\u0011\u0002\u0013M,7o]5p]&#\u0017AC:fgNLwN\\%eA\u0005q1\u000f^1siRKW.Z:uC6\u0004X#A.\u0011\u00059b\u0016BA/0\u0005\u0011auN\\4\u0002\u001fM$\u0018M\u001d;US6,7\u000f^1na\u0002\n\u0001\"^:fe:\u000bW.Z\u0001\nkN,'OT1nK\u0002\nqBZ5oSNDG+[7fgR\fW\u000e]\u0001\u0011M&t\u0017n\u001d5US6,7\u000f^1na\u0002\nab\u00197pg\u0016$\u0016.\\3ti\u0006l\u0007/A\bdY>\u001cX\rV5nKN$\u0018-\u001c9!\u0003-)\u00070Z2vi\u0016\u0004F.\u00198\u0002\u0019\u0015DXmY;uKBc\u0017M\u001c\u0011\u0002\r\u0011,G/Y5m\u0003\u001d!W\r^1jY\u0002\nQa\u001d;bi\u0016,\u0012a\u001b\t\u0003Y~t!!\u001c?\u000f\u00059ThBA8z\u001d\t\u0001\bP\u0004\u0002ro:\u0011!O\u001e\b\u0003gVt!!\u000f;\n\u0003-J!!\u000b\u0016\n\u0005\u001dB\u0013BA\u0013'\u0013\t\u0019C%\u0003\u0002\"E%\u00111\u0010I\u0001\u0012\u0011&4X\r\u00165sS\u001a$8+\u001a:wKJ\u0014\u0014BA?\u007f\u00039)\u00050Z2vi&|gn\u0015;bi\u0016T!a\u001f\u0011\n\t\u0005\u0005\u00111\u0001\u0002\u0006-\u0006dW/Z\u0005\u0004\u0003\u000by#aC#ok6,'/\u0019;j_:\faa\u001d;bi\u0016\u0004\u0013!\u00026pE&#WCAA\u0007!\u0015\ty!!\u00077\u001b\t\t\tB\u0003\u0003\u0002\u0014\u0005U\u0011aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003/y\u0013AC2pY2,7\r^5p]&!\u00111DA\t\u0005-\t%O]1z\u0005V4g-\u001a:\u0002\r)|'-\u00133!\u0003\u001d9'o\\;q\u0013\u0012\f\u0001b\u001a:pkBLE\rI\u0001\u0007y%t\u0017\u000e\u001e \u00155\u0005\u001d\u00121FA\u0017\u0003_\t\t$a\r\u00026\u0005]\u0012\u0011HA\u001e\u0003{\ty$!\u0011\u0011\u0007\u0005%\u0002!D\u0001\u001f\u0011\u0015\u0019\u0014\u00041\u00017\u0011\u0015)\u0016\u00041\u00017\u0011\u00159\u0016\u00041\u00017\u0011\u0015I\u0016\u00041\u0001\\\u0011\u0015y\u0016\u00041\u00017\u0011\u0015\t\u0017\u00041\u0001\\\u0011\u0015\u0019\u0017\u00041\u0001\\\u0011\u0015)\u0017\u00041\u00017\u0011\u00159\u0017\u00041\u00017\u0011\u0015I\u0017\u00041\u0001l\u0011\u001d\tI!\u0007a\u0001\u0003\u001bAa!a\b\u001a\u0001\u00041\u0014a\u00044j]&\u001c\b\u000eV5nK&sG-\u001a=)\u0007i\t9\u0005\u0005\u0003\u0002J\u0005eSBAA&\u0015\r\t\u0016Q\n\u0006\u0005\u0003\u001f\n\t&A\u0004kC\u000e\\7o\u001c8\u000b\t\u0005M\u0013QK\u0001\nM\u0006\u001cH/\u001a:y[2T!!a\u0016\u0002\u0007\r|W.\u0003\u0003\u0002\\\u0005-#A\u0003&t_:LuM\\8sK\"2!dQA0\u0003C\nQA^1mk\u0016\f#!a\u0019\u0002\u0015\u0019Lg.[:i)&lW-A\tjg\u0016CXmY;uS>t\u0017i\u0019;jm\u0016,\"!!\u001b\u0011\u00079\nY'C\u0002\u0002n=\u0012qAQ8pY\u0016\fg\u000eK\u0002\u001c\u0003\u000fBcaG\"\u0002`\u0005M\u0014EAA3\u0003%!x\u000e^1m)&lW\rF\u0002\\\u0003sBa!a\u001f\u001d\u0001\u0004Y\u0016aB3oIRKW.\u001a"
)
public class ExecutionInfo {
   private final String execId;
   private final String statement;
   private final String sessionId;
   private final long startTimestamp;
   private final String userName;
   private final long finishTimestamp;
   private final long closeTimestamp;
   private final String executePlan;
   private final String detail;
   private final Enumeration.Value state;
   private final ArrayBuffer jobId;
   private final String groupId;

   @KVIndex
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

   public long closeTimestamp() {
      return this.closeTimestamp;
   }

   public String executePlan() {
      return this.executePlan;
   }

   public String detail() {
      return this.detail;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public ArrayBuffer jobId() {
      return this.jobId;
   }

   public String groupId() {
      return this.groupId;
   }

   @JsonIgnore
   @KVIndex("finishTime")
   private long finishTimeIndex() {
      return this.finishTimestamp() > 0L && !this.isExecutionActive() ? this.finishTimestamp() : -1L;
   }

   @JsonIgnore
   @KVIndex("isExecutionActive")
   public boolean isExecutionActive() {
      boolean var8;
      label49: {
         label44: {
            Enumeration.Value var10000 = this.state();
            Enumeration.Value var1 = HiveThriftServer2.ExecutionState$.MODULE$.FAILED();
            if (var10000 == null) {
               if (var1 == null) {
                  break label44;
               }
            } else if (var10000.equals(var1)) {
               break label44;
            }

            var10000 = this.state();
            Enumeration.Value var2 = HiveThriftServer2.ExecutionState$.MODULE$.CANCELED();
            if (var10000 == null) {
               if (var2 == null) {
                  break label44;
               }
            } else if (var10000.equals(var2)) {
               break label44;
            }

            var10000 = this.state();
            Enumeration.Value var3 = HiveThriftServer2.ExecutionState$.MODULE$.TIMEDOUT();
            if (var10000 == null) {
               if (var3 == null) {
                  break label44;
               }
            } else if (var10000.equals(var3)) {
               break label44;
            }

            var10000 = this.state();
            Enumeration.Value var4 = HiveThriftServer2.ExecutionState$.MODULE$.CLOSED();
            if (var10000 == null) {
               if (var4 != null) {
                  break label49;
               }
            } else if (!var10000.equals(var4)) {
               break label49;
            }
         }

         var8 = false;
         return var8;
      }

      var8 = true;
      return var8;
   }

   public long totalTime(final long endTime) {
      return endTime == 0L ? System.currentTimeMillis() - this.startTimestamp() : endTime - this.startTimestamp();
   }

   public ExecutionInfo(final String execId, final String statement, final String sessionId, final long startTimestamp, final String userName, final long finishTimestamp, final long closeTimestamp, final String executePlan, final String detail, final Enumeration.Value state, final ArrayBuffer jobId, final String groupId) {
      this.execId = execId;
      this.statement = statement;
      this.sessionId = sessionId;
      this.startTimestamp = startTimestamp;
      this.userName = userName;
      this.finishTimestamp = finishTimestamp;
      this.closeTimestamp = closeTimestamp;
      this.executePlan = executePlan;
      this.detail = detail;
      this.state = state;
      this.jobId = jobId;
      this.groupId = groupId;
   }
}
