package org.apache.spark.mllib.stat.test;

import java.io.Serializable;
import org.apache.commons.math3.stat.descriptive.StatisticalSummaryValues;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.util.StatCounter;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a\u0001\u0003\b\u0010!\u0003\r\t#E\u000e\t\u000b)\u0002A\u0011\u0001\u0017\t\u000fA\u0002!\u0019!D\u0001c!9Q\b\u0001b\u0001\u000e\u0003\tT\u0001\u0002 \u0001\u0011}BQ\u0001\u0015\u0001\u0007\u0002ECQa\u0017\u0001\u0005\u0014q;aa\\\b\t\u0002E\u0001hA\u0002\b\u0010\u0011\u0003\t\u0012\u000fC\u0003s\u0011\u0011\u00051\u000fC\u0004u\u0011\t\u0007IQB;\t\riD\u0001\u0015!\u0004w\u0011\u0015Y\b\u0002\"\u0001}\u0011!y\b\"!A\u0005\n\u0005\u0005!aE*ue\u0016\fW.\u001b8h)\u0016\u001cH/T3uQ>$'B\u0001\t\u0012\u0003\u0011!Xm\u001d;\u000b\u0005I\u0019\u0012\u0001B:uCRT!\u0001F\u000b\u0002\u000b5dG.\u001b2\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e\u001c2\u0001\u0001\u000f#!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\u0019\te.\u001f*fMB\u00111\u0005K\u0007\u0002I)\u0011QEJ\u0001\u0003S>T\u0011aJ\u0001\u0005U\u00064\u0018-\u0003\u0002*I\ta1+\u001a:jC2L'0\u00192mK\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001.!\tib&\u0003\u00020=\t!QK\\5u\u0003)iW\r\u001e5pI:\u000bW.Z\u000b\u0002eA\u00111G\u000f\b\u0003ia\u0002\"!\u000e\u0010\u000e\u0003YR!aN\u0016\u0002\rq\u0012xn\u001c;?\u0013\tId$\u0001\u0004Qe\u0016$WMZ\u0005\u0003wq\u0012aa\u0015;sS:<'BA\u001d\u001f\u00039qW\u000f\u001c7IsB|G\u000f[3tSN\u0014\u0011cU;n[\u0006\u0014\u0018\u0010U1jeN#(/Z1n!\r\u0001UiR\u0007\u0002\u0003*\u0011!iQ\u0001\bIN$(/Z1n\u0015\t!U#A\u0005tiJ,\u0017-\\5oO&\u0011a)\u0011\u0002\b\tN#(/Z1n!\u0011i\u0002J\u0013&\n\u0005%s\"A\u0002+va2,'\u0007\u0005\u0002L\u001d6\tAJ\u0003\u0002N+\u0005!Q\u000f^5m\u0013\tyEJA\u0006Ti\u0006$8i\\;oi\u0016\u0014\u0018A\u00023p)\u0016\u001cH\u000f\u0006\u0002S/B\u0019\u0001)R*\u0011\u0005Q+V\"A\b\n\u0005Y{!aE*ue\u0016\fW.\u001b8h)\u0016\u001cHOU3tk2$\b\"\u0002-\u0006\u0001\u0004I\u0016aD:b[BdWmU;n[\u0006\u0014\u0018.Z:\u0011\u0005i#Q\"\u0001\u0001\u0002)Q|\u0017\t]1dQ\u0016\u001cu.\\7p]N\u001cF/\u0019;t)\ti\u0006\u000e\u0005\u0002_M6\tqL\u0003\u0002aC\u0006YA-Z:de&\u0004H/\u001b<f\u0015\t\u0011\"M\u0003\u0002dI\u0006)Q.\u0019;ig)\u0011QmF\u0001\bG>lWn\u001c8t\u0013\t9wL\u0001\rTi\u0006$\u0018n\u001d;jG\u0006d7+^7nCJLh+\u00197vKNDQ!\u001b\u0004A\u0002)\u000bAb];n[\u0006\u0014\u0018p\u0015;biNL3\u0001A6n\u0015\taw\"\u0001\u0007TiV$WM\u001c;U)\u0016\u001cHO\u0003\u0002o\u001f\u0005Qq+\u001a7dQR#Vm\u001d;\u0002'M#(/Z1nS:<G+Z:u\u001b\u0016$\bn\u001c3\u0011\u0005QC1c\u0001\u0005\u001dE\u00051A(\u001b8jiz\"\u0012\u0001]\u0001\u0014)\u0016\u001bFk\u0018(B\u001b\u0016{FkT0P\u0005*+5\tV\u000b\u0002mB!1g\u001e\u001az\u0013\tAHHA\u0002NCB\u0004\"\u0001\u0016\u0001\u0002)Q+5\u000bV0O\u00036+u\fV(`\u001f\nSUi\u0011+!\u0003U9W\r\u001e+fgRlU\r\u001e5pI\u001a\u0013x.\u001c(b[\u0016$\"!_?\t\u000byd\u0001\u0019\u0001\u001a\u0002\r5,G\u000f[8e\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\u0001\u0005\u0003\u0002\u0006\u0005-QBAA\u0004\u0015\r\tIAJ\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u000e\u0005\u001d!AB(cU\u0016\u001cG\u000f"
)
public interface StreamingTestMethod extends Serializable {
   static StreamingTestMethod getTestMethodFromName(final String method) {
      return StreamingTestMethod$.MODULE$.getTestMethodFromName(method);
   }

   String methodName();

   String nullHypothesis();

   DStream doTest(final DStream sampleSummaries);

   // $FF: synthetic method
   static StatisticalSummaryValues toApacheCommonsStats$(final StreamingTestMethod $this, final StatCounter summaryStats) {
      return $this.toApacheCommonsStats(summaryStats);
   }

   default StatisticalSummaryValues toApacheCommonsStats(final StatCounter summaryStats) {
      return new StatisticalSummaryValues(summaryStats.mean(), summaryStats.variance(), summaryStats.count(), summaryStats.max(), summaryStats.min(), summaryStats.mean() * (double)summaryStats.count());
   }

   static void $init$(final StreamingTestMethod $this) {
   }
}
