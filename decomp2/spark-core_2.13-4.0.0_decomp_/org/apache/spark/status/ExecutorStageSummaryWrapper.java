package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.status.api.v1.ExecutorStageSummary;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005A4QAD\b\u0001#]A\u0001B\b\u0001\u0003\u0006\u0004%\t\u0001\t\u0005\tI\u0001\u0011\t\u0011)A\u0005C!AQ\u0005\u0001BC\u0002\u0013\u0005\u0001\u0005\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003\"\u0011!9\u0003A!b\u0001\n\u0003A\u0003\u0002\u0003\u001b\u0001\u0005\u0003\u0005\u000b\u0011B\u0015\t\u0011U\u0002!Q1A\u0005\u0002YB\u0001b\u0010\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\u0006\u0001\u0002!\t!\u0011\u0005\b\u0011\u0002\u0011\r\u0011\"\u0003J\u0011\u0019\u0001\u0006\u0001)A\u0005\u0015\")q\r\u0001C\u0005Q\")a\u000e\u0001C\u0001\u0013\nYR\t_3dkR|'o\u0015;bO\u0016\u001cV/\\7bef<&/\u00199qKJT!\u0001E\t\u0002\rM$\u0018\r^;t\u0015\t\u00112#A\u0003ta\u0006\u00148N\u0003\u0002\u0015+\u00051\u0011\r]1dQ\u0016T\u0011AF\u0001\u0004_J<7C\u0001\u0001\u0019!\tIB$D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u0019\te.\u001f*fM\u000691\u000f^1hK&#7\u0001A\u000b\u0002CA\u0011\u0011DI\u0005\u0003Gi\u00111!\u00138u\u0003!\u0019H/Y4f\u0013\u0012\u0004\u0013AD:uC\u001e,\u0017\t\u001e;f[B$\u0018\nZ\u0001\u0010gR\fw-Z!ui\u0016l\u0007\u000f^%eA\u0005QQ\r_3dkR|'/\u00133\u0016\u0003%\u0002\"AK\u0019\u000f\u0005-z\u0003C\u0001\u0017\u001b\u001b\u0005i#B\u0001\u0018 \u0003\u0019a$o\\8u}%\u0011\u0001GG\u0001\u0007!J,G-\u001a4\n\u0005I\u001a$AB*ue&twM\u0003\u000215\u0005YQ\r_3dkR|'/\u00133!\u0003\u0011IgNZ8\u0016\u0003]\u0002\"\u0001O\u001f\u000e\u0003eR!AO\u001e\u0002\u0005Y\f$B\u0001\u001f\u0010\u0003\r\t\u0007/[\u0005\u0003}e\u0012A#\u0012=fGV$xN]*uC\u001e,7+^7nCJL\u0018!B5oM>\u0004\u0013A\u0002\u001fj]&$h\bF\u0003C\t\u00163u\t\u0005\u0002D\u00015\tq\u0002C\u0003\u001f\u0013\u0001\u0007\u0011\u0005C\u0003&\u0013\u0001\u0007\u0011\u0005C\u0003(\u0013\u0001\u0007\u0011\u0006C\u00036\u0013\u0001\u0007q'A\u0002`S\u0012,\u0012A\u0013\t\u00043-k\u0015B\u0001'\u001b\u0005\u0015\t%O]1z!\tIb*\u0003\u0002P5\t\u0019\u0011I\\=\u0002\t}KG\r\t\u0015\u0003\u0017I\u0003\"a\u0015/\u000e\u0003QS!!\u0016,\u0002\u0015\u0005tgn\u001c;bi&|gN\u0003\u0002X1\u00069!.Y2lg>t'BA-[\u0003%1\u0017m\u001d;feblGNC\u0001\\\u0003\r\u0019w.\\\u0005\u0003;R\u0013!BS:p]&;gn\u001c:fQ\tYq\f\u0005\u0002aK6\t\u0011M\u0003\u0002cG\u000691N^:u_J,'B\u00013\u0012\u0003\u0011)H/\u001b7\n\u0005\u0019\f'aB&W\u0013:$W\r_\u0001\u0006gR\fw-Z\u000b\u0002SB\u0019\u0011dS\u0011)\u00051\u0011\u0006\u0006\u0002\u0007`Y6\fQA^1mk\u0016\f\u0013aZ\u0001\u0003S\u0012D#!\u0004*"
)
public class ExecutorStageSummaryWrapper {
   private final int stageId;
   private final int stageAttemptId;
   private final String executorId;
   private final ExecutorStageSummary info;
   @JsonIgnore
   @KVIndex
   private final Object[] _id;

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public String executorId() {
      return this.executorId;
   }

   public ExecutorStageSummary info() {
      return this.info;
   }

   private Object[] _id() {
      return this._id;
   }

   @JsonIgnore
   @KVIndex("stage")
   private int[] stage() {
      return new int[]{this.stageId(), this.stageAttemptId()};
   }

   @JsonIgnore
   public Object[] id() {
      return this._id();
   }

   public ExecutorStageSummaryWrapper(final int stageId, final int stageAttemptId, final String executorId, final ExecutorStageSummary info) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.executorId = executorId;
      this.info = info;
      this._id = new Object[]{BoxesRunTime.boxToInteger(stageId), BoxesRunTime.boxToInteger(stageAttemptId), executorId};
   }
}
