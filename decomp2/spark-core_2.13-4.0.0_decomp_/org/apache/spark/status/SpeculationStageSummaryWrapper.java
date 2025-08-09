package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.status.api.v1.SpeculationStageSummary;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3QAC\u0006\u0001\u001bMA\u0001B\u0007\u0001\u0003\u0006\u0004%\t\u0001\b\u0005\tA\u0001\u0011\t\u0011)A\u0005;!A\u0011\u0005\u0001BC\u0002\u0013\u0005A\u0004\u0003\u0005#\u0001\t\u0005\t\u0015!\u0003\u001e\u0011!\u0019\u0003A!b\u0001\n\u0003!\u0003\u0002C\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0013\t\u000b9\u0002A\u0011A\u0018\t\u000bU\u0002A\u0011\u0002\u001c\t\rI\u0003\u0001\u0015!\u00038\u0005y\u0019\u0006/Z2vY\u0006$\u0018n\u001c8Ti\u0006<WmU;n[\u0006\u0014\u0018p\u0016:baB,'O\u0003\u0002\r\u001b\u000511\u000f^1ukNT!AD\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005A\t\u0012AB1qC\u000eDWMC\u0001\u0013\u0003\ry'oZ\n\u0003\u0001Q\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z%\u00164\u0017aB:uC\u001e,\u0017\nZ\u0002\u0001+\u0005i\u0002CA\u000b\u001f\u0013\tybCA\u0002J]R\f\u0001b\u001d;bO\u0016LE\rI\u0001\u000fgR\fw-Z!ui\u0016l\u0007\u000f^%e\u0003=\u0019H/Y4f\u0003R$X-\u001c9u\u0013\u0012\u0004\u0013\u0001B5oM>,\u0012!\n\t\u0003M-j\u0011a\n\u0006\u0003Q%\n!A^\u0019\u000b\u0005)Z\u0011aA1qS&\u0011Af\n\u0002\u0018'B,7-\u001e7bi&|gn\u0015;bO\u0016\u001cV/\\7bef\fQ!\u001b8g_\u0002\na\u0001P5oSRtD\u0003\u0002\u00193gQ\u0002\"!\r\u0001\u000e\u0003-AQAG\u0004A\u0002uAQ!I\u0004A\u0002uAQaI\u0004A\u0002\u0015\nQa\u001d;bO\u0016,\u0012a\u000e\t\u0004+aj\u0012BA\u001d\u0017\u0005\u0015\t%O]1zQ\tA1\b\u0005\u0002=\u000b6\tQH\u0003\u0002?\u007f\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0005\u0001\u000b\u0015a\u00026bG.\u001cxN\u001c\u0006\u0003\u0005\u000e\u000b\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003\u0011\u000b1aY8n\u0013\t1UH\u0001\u0006Kg>t\u0017j\u001a8pe\u0016DC\u0001\u0003%Q#B\u0011\u0011JT\u0007\u0002\u0015*\u00111\nT\u0001\bWZ\u001cHo\u001c:f\u0015\tiU\"\u0001\u0003vi&d\u0017BA(K\u0005\u001dYe+\u00138eKb\fQA^1mk\u0016\f\u0013!N\u0001\u0003S\u0012D#!\u0003%"
)
public class SpeculationStageSummaryWrapper {
   private final int stageId;
   private final int stageAttemptId;
   private final SpeculationStageSummary info;
   @KVIndex
   private final int[] id;

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public SpeculationStageSummary info() {
      return this.info;
   }

   @JsonIgnore
   @KVIndex("stage")
   private int[] stage() {
      return new int[]{this.stageId(), this.stageAttemptId()};
   }

   public SpeculationStageSummaryWrapper(final int stageId, final int stageAttemptId, final SpeculationStageSummary info) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.info = info;
      this.id = new int[]{stageId, stageAttemptId};
   }
}
