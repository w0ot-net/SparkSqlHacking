package org.apache.spark.status;

import org.apache.spark.status.api.v1.SpeculationStageSummary;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3A\u0001F\u000b\u0005=!A1\u0005\u0001B\u0001B\u0003%A\u0005\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003%\u0011\u0015Y\u0003\u0001\"\u0001-\u0011\u001d\u0001\u0004\u00011A\u0005\u0002EBqA\r\u0001A\u0002\u0013\u00051\u0007\u0003\u0004:\u0001\u0001\u0006K\u0001\n\u0005\bu\u0001\u0001\r\u0011\"\u00012\u0011\u001dY\u0004\u00011A\u0005\u0002qBaA\u0010\u0001!B\u0013!\u0003bB \u0001\u0001\u0004%\t!\r\u0005\b\u0001\u0002\u0001\r\u0011\"\u0001B\u0011\u0019\u0019\u0005\u0001)Q\u0005I!9A\t\u0001a\u0001\n\u0003\t\u0004bB#\u0001\u0001\u0004%\tA\u0012\u0005\u0007\u0011\u0002\u0001\u000b\u0015\u0002\u0013\t\u000f%\u0003\u0001\u0019!C\u0001c!9!\n\u0001a\u0001\n\u0003Y\u0005BB'\u0001A\u0003&A\u0005C\u0003O\u0001\u0011EsJA\u000eMSZ,7\u000b]3dk2\fG/[8o'R\fw-Z*v[6\f'/\u001f\u0006\u0003-]\taa\u001d;biV\u001c(B\u0001\r\u001a\u0003\u0015\u0019\b/\u0019:l\u0015\tQ2$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00029\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\b\t\u0003A\u0005j\u0011!F\u0005\u0003EU\u0011!\u0002T5wK\u0016sG/\u001b;z\u0003\u001d\u0019H/Y4f\u0013\u0012\u0004\"!\n\u0015\u000e\u0003\u0019R\u0011aJ\u0001\u0006g\u000e\fG.Y\u0005\u0003S\u0019\u00121!\u00138u\u0003%\tG\u000f^3naRLE-\u0001\u0004=S:LGO\u0010\u000b\u0004[9z\u0003C\u0001\u0011\u0001\u0011\u0015\u00193\u00011\u0001%\u0011\u0015Q3\u00011\u0001%\u0003!qW/\u001c+bg.\u001cX#\u0001\u0013\u0002\u00199,X\u000eV1tWN|F%Z9\u0015\u0005Q:\u0004CA\u00136\u0013\t1dE\u0001\u0003V]&$\bb\u0002\u001d\u0006\u0003\u0003\u0005\r\u0001J\u0001\u0004q\u0012\n\u0014!\u00038v[R\u000b7o[:!\u00039qW/\\!di&4X\rV1tWN\f!C\\;n\u0003\u000e$\u0018N^3UCN\\7o\u0018\u0013fcR\u0011A'\u0010\u0005\bq!\t\t\u00111\u0001%\u0003=qW/\\!di&4X\rV1tWN\u0004\u0013!\u00058v[\u000e{W\u000e\u001d7fi\u0016$G+Y:lg\u0006)b.^7D_6\u0004H.\u001a;fIR\u000b7o[:`I\u0015\fHC\u0001\u001bC\u0011\u001dA4\"!AA\u0002\u0011\n!C\\;n\u0007>l\u0007\u000f\\3uK\u0012$\u0016m]6tA\u0005qa.^7GC&dW\r\u001a+bg.\u001c\u0018A\u00058v[\u001a\u000b\u0017\u000e\\3e)\u0006\u001c8n]0%KF$\"\u0001N$\t\u000far\u0011\u0011!a\u0001I\u0005ya.^7GC&dW\r\u001a+bg.\u001c\b%\u0001\bok6\\\u0015\u000e\u001c7fIR\u000b7o[:\u0002%9,XnS5mY\u0016$G+Y:lg~#S-\u001d\u000b\u0003i1Cq\u0001O\t\u0002\u0002\u0003\u0007A%A\bok6\\\u0015\u000e\u001c7fIR\u000b7o[:!\u0003!!w.\u00169eCR,G#\u0001)\u0011\u0005\u0015\n\u0016B\u0001*'\u0005\r\te.\u001f"
)
public class LiveSpeculationStageSummary extends LiveEntity {
   private final int stageId;
   private final int attemptId;
   private int numTasks;
   private int numActiveTasks;
   private int numCompletedTasks;
   private int numFailedTasks;
   private int numKilledTasks;

   public int numTasks() {
      return this.numTasks;
   }

   public void numTasks_$eq(final int x$1) {
      this.numTasks = x$1;
   }

   public int numActiveTasks() {
      return this.numActiveTasks;
   }

   public void numActiveTasks_$eq(final int x$1) {
      this.numActiveTasks = x$1;
   }

   public int numCompletedTasks() {
      return this.numCompletedTasks;
   }

   public void numCompletedTasks_$eq(final int x$1) {
      this.numCompletedTasks = x$1;
   }

   public int numFailedTasks() {
      return this.numFailedTasks;
   }

   public void numFailedTasks_$eq(final int x$1) {
      this.numFailedTasks = x$1;
   }

   public int numKilledTasks() {
      return this.numKilledTasks;
   }

   public void numKilledTasks_$eq(final int x$1) {
      this.numKilledTasks = x$1;
   }

   public Object doUpdate() {
      SpeculationStageSummary info = new SpeculationStageSummary(this.numTasks(), this.numActiveTasks(), this.numCompletedTasks(), this.numFailedTasks(), this.numKilledTasks());
      return new SpeculationStageSummaryWrapper(this.stageId, this.attemptId, info);
   }

   public LiveSpeculationStageSummary(final int stageId, final int attemptId) {
      this.stageId = stageId;
      this.attemptId = attemptId;
      this.numTasks = 0;
      this.numActiveTasks = 0;
      this.numCompletedTasks = 0;
      this.numFailedTasks = 0;
      this.numKilledTasks = 0;
   }
}
