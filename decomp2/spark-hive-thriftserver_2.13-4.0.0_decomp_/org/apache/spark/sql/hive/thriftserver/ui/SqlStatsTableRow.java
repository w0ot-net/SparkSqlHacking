package org.apache.spark.sql.hive.thriftserver.ui;

import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3Q\u0001D\u0007\u0001\u001bmA\u0001B\t\u0001\u0003\u0006\u0004%\t\u0001\n\u0005\ts\u0001\u0011\t\u0011)A\u0005K!A!\b\u0001BC\u0002\u0013\u00051\b\u0003\u0005@\u0001\t\u0005\t\u0015!\u0003=\u0011!\u0001\u0005A!b\u0001\n\u0003Y\u0004\u0002C!\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u0011\t\u0003!Q1A\u0005\u0002\rC\u0001\u0002\u0013\u0001\u0003\u0002\u0003\u0006I\u0001\u0012\u0005\t\u0013\u0002\u0011)\u0019!C\u0001\u0015\"A1\n\u0001B\u0001B\u0003%\u0011\u0007C\u0003M\u0001\u0011\u0005QJ\u0001\tTc2\u001cF/\u0019;t)\u0006\u0014G.\u001a*po*\u0011abD\u0001\u0003k&T!\u0001E\t\u0002\u0019QD'/\u001b4ug\u0016\u0014h/\u001a:\u000b\u0005I\u0019\u0012\u0001\u00025jm\u0016T!\u0001F\u000b\u0002\u0007M\fHN\u0003\u0002\u0017/\u0005)1\u000f]1sW*\u0011\u0001$G\u0001\u0007CB\f7\r[3\u000b\u0003i\t1a\u001c:h'\t\u0001A\u0004\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VMZ\u0001\u0006U>\u0014\u0017\nZ\u0002\u0001+\u0005)\u0003c\u0001\u0014/c9\u0011q\u0005\f\b\u0003Q-j\u0011!\u000b\u0006\u0003U\r\na\u0001\u0010:p_Rt\u0014\"A\u0010\n\u00055r\u0012a\u00029bG.\fw-Z\u0005\u0003_A\u00121aU3r\u0015\tic\u0004\u0005\u00023m9\u00111\u0007\u000e\t\u0003QyI!!\u000e\u0010\u0002\rA\u0013X\rZ3g\u0013\t9\u0004H\u0001\u0004TiJLgn\u001a\u0006\u0003ky\taA[8c\u0013\u0012\u0004\u0013\u0001\u00033ve\u0006$\u0018n\u001c8\u0016\u0003q\u0002\"!H\u001f\n\u0005yr\"\u0001\u0002'p]\u001e\f\u0011\u0002Z;sCRLwN\u001c\u0011\u0002\u001b\u0015DXmY;uS>tG+[7f\u00039)\u00070Z2vi&|g\u000eV5nK\u0002\nQ\"\u001a=fGV$\u0018n\u001c8J]\u001a|W#\u0001#\u0011\u0005\u00153U\"A\u0007\n\u0005\u001dk!!D#yK\u000e,H/[8o\u0013:4w.\u0001\bfq\u0016\u001cW\u000f^5p]&sgm\u001c\u0011\u0002\r\u0011,G/Y5m+\u0005\t\u0014a\u00023fi\u0006LG\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\r9{\u0005+\u0015*T!\t)\u0005\u0001C\u0003#\u0017\u0001\u0007Q\u0005C\u0003;\u0017\u0001\u0007A\bC\u0003A\u0017\u0001\u0007A\bC\u0003C\u0017\u0001\u0007A\tC\u0003J\u0017\u0001\u0007\u0011\u0007"
)
public class SqlStatsTableRow {
   private final Seq jobId;
   private final long duration;
   private final long executionTime;
   private final ExecutionInfo executionInfo;
   private final String detail;

   public Seq jobId() {
      return this.jobId;
   }

   public long duration() {
      return this.duration;
   }

   public long executionTime() {
      return this.executionTime;
   }

   public ExecutionInfo executionInfo() {
      return this.executionInfo;
   }

   public String detail() {
      return this.detail;
   }

   public SqlStatsTableRow(final Seq jobId, final long duration, final long executionTime, final ExecutionInfo executionInfo, final String detail) {
      this.jobId = jobId;
      this.duration = duration;
      this.executionTime = executionTime;
      this.executionInfo = executionInfo;
      this.detail = detail;
   }
}
