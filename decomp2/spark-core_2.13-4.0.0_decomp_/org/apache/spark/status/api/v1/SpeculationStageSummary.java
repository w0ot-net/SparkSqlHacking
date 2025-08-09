package org.apache.spark.status.api.v1;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a2A\u0001D\u0007\u00015!A\u0011\u0005\u0001BC\u0002\u0013\u0005!\u0005\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003$\u0011!9\u0003A!b\u0001\n\u0003\u0011\u0003\u0002\u0003\u0015\u0001\u0005\u0003\u0005\u000b\u0011B\u0012\t\u0011%\u0002!Q1A\u0005\u0002\tB\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\tW\u0001\u0011)\u0019!C\u0001E!AA\u0006\u0001B\u0001B\u0003%1\u0005\u0003\u0005.\u0001\t\u0015\r\u0011\"\u0001#\u0011!q\u0003A!A!\u0002\u0013\u0019\u0003BB\u0018\u0001\t\u0003\u0019\u0002GA\fTa\u0016\u001cW\u000f\\1uS>t7\u000b^1hKN+X.\\1ss*\u0011abD\u0001\u0003mFR!\u0001E\t\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u0013'\u000511\u000f^1ukNT!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\u0002\u0001'\t\u00011\u0004\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VMZ\u0001\t]VlG+Y:lgV\t1\u0005\u0005\u0002\u001dI%\u0011Q%\b\u0002\u0004\u0013:$\u0018!\u00038v[R\u000b7o[:!\u00039qW/\\!di&4X\rV1tWN\fqB\\;n\u0003\u000e$\u0018N^3UCN\\7\u000fI\u0001\u0012]Vl7i\\7qY\u0016$X\r\u001a+bg.\u001c\u0018A\u00058v[\u000e{W\u000e\u001d7fi\u0016$G+Y:lg\u0002\naB\\;n\r\u0006LG.\u001a3UCN\\7/A\bok64\u0015-\u001b7fIR\u000b7o[:!\u00039qW/\\&jY2,G\rV1tWN\fqB\\;n\u0017&dG.\u001a3UCN\\7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\rE\u001aD'\u000e\u001c8!\t\u0011\u0004!D\u0001\u000e\u0011\u0015\t3\u00021\u0001$\u0011\u001593\u00021\u0001$\u0011\u0015I3\u00021\u0001$\u0011\u0015Y3\u00021\u0001$\u0011\u0015i3\u00021\u0001$\u0001"
)
public class SpeculationStageSummary {
   private final int numTasks;
   private final int numActiveTasks;
   private final int numCompletedTasks;
   private final int numFailedTasks;
   private final int numKilledTasks;

   public int numTasks() {
      return this.numTasks;
   }

   public int numActiveTasks() {
      return this.numActiveTasks;
   }

   public int numCompletedTasks() {
      return this.numCompletedTasks;
   }

   public int numFailedTasks() {
      return this.numFailedTasks;
   }

   public int numKilledTasks() {
      return this.numKilledTasks;
   }

   public SpeculationStageSummary(final int numTasks, final int numActiveTasks, final int numCompletedTasks, final int numFailedTasks, final int numKilledTasks) {
      this.numTasks = numTasks;
      this.numActiveTasks = numActiveTasks;
      this.numCompletedTasks = numCompletedTasks;
      this.numFailedTasks = numFailedTasks;
      this.numKilledTasks = numKilledTasks;
   }
}
