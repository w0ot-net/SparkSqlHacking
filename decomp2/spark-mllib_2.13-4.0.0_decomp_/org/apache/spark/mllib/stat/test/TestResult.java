package org.apache.spark.mllib.stat.test;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3qa\u0002\u0005\u0011\u0002\u0007\u0005Q\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003#\u0001\u0019\u00051\u0005C\u00031\u0001\u0019\u0005\u0011\u0007C\u0003?\u0001\u0019\u00051\u0005C\u0003A\u0001\u0019\u0005\u0011\tC\u0003O\u0001\u0011\u0005sJ\u0001\u0006UKN$(+Z:vYRT!!\u0003\u0006\u0002\tQ,7\u000f\u001e\u0006\u0003\u00171\tAa\u001d;bi*\u0011QBD\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sO\u000e\u0001QC\u0001\f5'\t\u0001q\u0003\u0005\u0002\u001975\t\u0011DC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0013\ta\u0012D\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003}\u0001\"\u0001\u0007\u0011\n\u0005\u0005J\"\u0001B+oSR\fa\u0001\u001d,bYV,W#\u0001\u0013\u0011\u0005a)\u0013B\u0001\u0014\u001a\u0005\u0019!u.\u001e2mK\"\u001a!\u0001\u000b\u0018\u0011\u0005%bS\"\u0001\u0016\u000b\u0005-r\u0011AC1o]>$\u0018\r^5p]&\u0011QF\u000b\u0002\u0006'&t7-Z\u0011\u0002_\u0005)\u0011GL\u0019/a\u0005\u0001B-Z4sK\u0016\u001cxJ\u001a$sK\u0016$w.\\\u000b\u0002eA\u00111\u0007\u000e\u0007\u0001\t\u0015)\u0004A1\u00017\u0005\t!e)\u0005\u00028uA\u0011\u0001\u0004O\u0005\u0003se\u0011qAT8uQ&tw\r\u0005\u0002\u0019w%\u0011A(\u0007\u0002\u0004\u0003:L\bfA\u0002)]\u0005I1\u000f^1uSN$\u0018n\u0019\u0015\u0004\t!r\u0013A\u00048vY2D\u0015\u0010]8uQ\u0016\u001c\u0018n]\u000b\u0002\u0005B\u00111I\u0013\b\u0003\t\"\u0003\"!R\r\u000e\u0003\u0019S!a\u0012\u000b\u0002\rq\u0012xn\u001c;?\u0013\tI\u0015$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00172\u0013aa\u0015;sS:<'BA%\u001aQ\r)\u0001FL\u0001\ti>\u001cFO]5oOR\t!\tK\u0002\u0001Q9\u0002"
)
public interface TestResult {
   double pValue();

   Object degreesOfFreedom();

   double statistic();

   String nullHypothesis();

   // $FF: synthetic method
   static String toString$(final TestResult $this) {
      return $this.toString();
   }

   default String toString() {
      String pValueExplain = this.pValue() <= 0.01 ? "Very strong presumption against null hypothesis: " + this.nullHypothesis() + "." : (0.01 < this.pValue() && this.pValue() <= 0.05 ? "Strong presumption against null hypothesis: " + this.nullHypothesis() + "." : (0.05 < this.pValue() && this.pValue() <= 0.1 ? "Low presumption against null hypothesis: " + this.nullHypothesis() + "." : "No presumption against null hypothesis: " + this.nullHypothesis() + "."));
      String var10000 = this.degreesOfFreedom().toString();
      return "degrees of freedom = " + var10000 + " \nstatistic = " + this.statistic() + " \npValue = " + this.pValue() + " \n" + pValueExplain;
   }

   static void $init$(final TestResult $this) {
   }
}
