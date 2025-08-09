package org.apache.spark.mllib.stat.test;

import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514Q!\u0004\b\u0001!iA\u0001\"\u000e\u0001\u0003\u0006\u0004%\tE\u000e\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005K!A!\t\u0001BC\u0002\u0013\u0005c\u0007\u0003\u0005E\u0001\t\u0005\t\u0015!\u0003&\u0011!1\u0005A!b\u0001\n\u00032\u0004\u0002\u0003%\u0001\u0005\u0003\u0005\u000b\u0011B\u0013\t\u0011)\u0003!Q1A\u0005\u0002-C\u0001\"\u0016\u0001\u0003\u0002\u0003\u0006I\u0001\u0014\u0005\t/\u0002\u0011)\u0019!C!\u0017\"A\u0011\f\u0001B\u0001B\u0003%A\nC\u0003\\\u0001\u0011\u0005A\fC\u0003j\u0001\u0011\u0005#NA\nTiJ,\u0017-\\5oOR+7\u000f\u001e*fgVdGO\u0003\u0002\u0010!\u0005!A/Z:u\u0015\t\t\"#\u0001\u0003ti\u0006$(BA\n\u0015\u0003\u0015iG\u000e\\5c\u0015\t)b#A\u0003ta\u0006\u00148N\u0003\u0002\u00181\u00051\u0011\r]1dQ\u0016T\u0011!G\u0001\u0004_J<7\u0003\u0002\u0001\u001cC!\u0002\"\u0001H\u0010\u000e\u0003uQ\u0011AH\u0001\u0006g\u000e\fG.Y\u0005\u0003Au\u0011a!\u00118z%\u00164\u0007c\u0001\u0012$K5\ta\"\u0003\u0002%\u001d\tQA+Z:u%\u0016\u001cX\u000f\u001c;\u0011\u0005q1\u0013BA\u0014\u001e\u0005\u0019!u.\u001e2mKB\u0011\u0011F\r\b\u0003UAr!aK\u0018\u000e\u00031R!!\f\u0018\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AH\u0005\u0003cu\tq\u0001]1dW\u0006<W-\u0003\u00024i\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011'H\u0001\u0007aZ\u000bG.^3\u0016\u0003\u0015B3!\u0001\u001d?!\tID(D\u0001;\u0015\tYD#\u0001\u0006b]:|G/\u0019;j_:L!!\u0010\u001e\u0003\u000bMKgnY3\"\u0003}\nQ!\r\u00187]A\nq\u0001\u001d,bYV,\u0007\u0005K\u0002\u0003qy\n\u0001\u0003Z3he\u0016,7o\u00144Ge\u0016,Gm\\7)\u0007\rAd(A\teK\u001e\u0014X-Z:PM\u001a\u0013X-\u001a3p[\u0002B3\u0001\u0002\u001d?\u0003%\u0019H/\u0019;jgRL7\rK\u0002\u0006qy\n!b\u001d;bi&\u001cH/[2!Q\r1\u0001HP\u0001\u0007[\u0016$\bn\u001c3\u0016\u00031\u0003\"!T)\u000f\u00059{\u0005CA\u0016\u001e\u0013\t\u0001V$\u0001\u0004Qe\u0016$WMZ\u0005\u0003%N\u0013aa\u0015;sS:<'B\u0001)\u001eQ\r9\u0001HP\u0001\b[\u0016$\bn\u001c3!Q\rA\u0001HP\u0001\u000f]VdG\u000eS=q_RDWm]5tQ\rI\u0001HP\u0001\u0010]VdG\u000eS=q_RDWm]5tA!\u001a!\u0002\u000f \u0002\rqJg.\u001b;?)\u0019if\f\u00192eMB\u0011!\u0005\u0001\u0005\u0006k-\u0001\r!\n\u0015\u0004=br\u0004\"\u0002\"\f\u0001\u0004)\u0003f\u000119}!)ai\u0003a\u0001K!\u001a!\r\u000f \t\u000b)[\u0001\u0019\u0001')\u0007\u0011Dd\bC\u0003X\u0017\u0001\u0007A\nK\u0002gqyB3a\u0003\u001d?\u0003!!xn\u0015;sS:<G#\u0001')\u0007\u0001Ad\b"
)
public class StreamingTestResult implements TestResult, Serializable {
   private final double pValue;
   private final double degreesOfFreedom;
   private final double statistic;
   private final String method;
   private final String nullHypothesis;

   public double pValue() {
      return this.pValue;
   }

   public double degreesOfFreedom() {
      return this.degreesOfFreedom;
   }

   public double statistic() {
      return this.statistic;
   }

   public String method() {
      return this.method;
   }

   public String nullHypothesis() {
      return this.nullHypothesis;
   }

   public String toString() {
      String var10000 = this.method();
      return "Streaming test summary:\nmethod: " + var10000 + "\n" + TestResult.toString$(this);
   }

   public StreamingTestResult(final double pValue, final double degreesOfFreedom, final double statistic, final String method, final String nullHypothesis) {
      this.pValue = pValue;
      this.degreesOfFreedom = degreesOfFreedom;
      this.statistic = statistic;
      this.method = method;
      this.nullHypothesis = nullHypothesis;
      TestResult.$init$(this);
   }
}
