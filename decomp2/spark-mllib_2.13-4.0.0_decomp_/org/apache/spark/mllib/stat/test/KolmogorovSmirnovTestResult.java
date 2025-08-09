package org.apache.spark.mllib.stat.test;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3Aa\u0003\u0007\u00013!Aq\u0005\u0001BC\u0002\u0013\u0005\u0003\u0006\u0003\u00056\u0001\t\u0005\t\u0015!\u0003*\u0011!9\u0004A!b\u0001\n\u0003B\u0003\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011B\u0015\t\u0011m\u0002!Q1A\u0005BqB\u0001\"\u0013\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\u0007\u0017\u0002!\tA\u0004'\t\u000fQ\u0003!\u0019!C!+\"1q\u000b\u0001Q\u0001\n\u0011BQ!\u0017\u0001\u0005Bi\u00131dS8m[><wN]8w'6L'O\\8w)\u0016\u001cHOU3tk2$(BA\u0007\u000f\u0003\u0011!Xm\u001d;\u000b\u0005=\u0001\u0012\u0001B:uCRT!!\u0005\n\u0002\u000b5dG.\u001b2\u000b\u0005M!\u0012!B:qCJ\\'BA\u000b\u0017\u0003\u0019\t\u0007/Y2iK*\tq#A\u0002pe\u001e\u001c\u0001aE\u0002\u00015\u0001\u0002\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011a!\u00118z%\u00164\u0007cA\u0011#I5\tA\"\u0003\u0002$\u0019\tQA+Z:u%\u0016\u001cX\u000f\u001c;\u0011\u0005m)\u0013B\u0001\u0014\u001d\u0005\rIe\u000e^\u0001\u0007aZ\u000bG.^3\u0016\u0003%\u0002\"a\u0007\u0016\n\u0005-b\"A\u0002#pk\ndW\rK\u0002\u0002[M\u0002\"AL\u0019\u000e\u0003=R!\u0001\r\n\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u00023_\t)1+\u001b8dK\u0006\nA'A\u00032]Ur\u0003'A\u0004q-\u0006dW/\u001a\u0011)\u0007\ti3'A\u0005ti\u0006$\u0018n\u001d;jG\"\u001a1!L\u001a\u0002\u0015M$\u0018\r^5ti&\u001c\u0007\u0005K\u0002\u0005[M\naB\\;mY\"K\bo\u001c;iKNL7/F\u0001>!\tqTI\u0004\u0002@\u0007B\u0011\u0001\tH\u0007\u0002\u0003*\u0011!\tG\u0001\u0007yI|w\u000e\u001e \n\u0005\u0011c\u0012A\u0002)sK\u0012,g-\u0003\u0002G\u000f\n11\u000b\u001e:j]\u001eT!\u0001\u0012\u000f)\u0007\u0015i3'A\bok2d\u0007*\u001f9pi\",7/[:!Q\r1QfM\u0001\u0007y%t\u0017\u000e\u001e \u0015\t5s\u0005K\u0015\t\u0003C\u0001AQaJ\u0004A\u0002%B3AT\u00174\u0011\u00159t\u00011\u0001*Q\r\u0001Vf\r\u0005\u0006w\u001d\u0001\r!\u0010\u0015\u0004%6\u001a\u0014\u0001\u00053fOJ,Wm](g\rJ,W\rZ8n+\u0005!\u0003f\u0001\u0005.g\u0005\tB-Z4sK\u0016\u001cxJ\u001a$sK\u0016$w.\u001c\u0011)\u0007%i3'\u0001\u0005u_N#(/\u001b8h)\u0005i\u0004f\u0001\u0001.g\u0001"
)
public class KolmogorovSmirnovTestResult implements TestResult {
   private final double pValue;
   private final double statistic;
   private final String nullHypothesis;
   private final int degreesOfFreedom;

   public double pValue() {
      return this.pValue;
   }

   public double statistic() {
      return this.statistic;
   }

   public String nullHypothesis() {
      return this.nullHypothesis;
   }

   public int degreesOfFreedom() {
      return this.degreesOfFreedom;
   }

   public String toString() {
      return "Kolmogorov-Smirnov test summary:\n" + TestResult.toString$(this);
   }

   public KolmogorovSmirnovTestResult(final double pValue, final double statistic, final String nullHypothesis) {
      this.pValue = pValue;
      this.statistic = statistic;
      this.nullHypothesis = nullHypothesis;
      TestResult.$init$(this);
      this.degreesOfFreedom = 0;
   }
}
