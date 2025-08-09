package org.apache.spark.mllib.stat.test;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4A!\u0004\b\u00017!A\u0011\u0006\u0001BC\u0002\u0013\u0005#\u0006\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003,\u0011!y\u0003A!b\u0001\n\u0003\u0002\u0004\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u0011q\u0002!Q1A\u0005B)B\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\t\u0001\u0002\u0011)\u0019!C\u0001\u0003\"Aa\n\u0001B\u0001B\u0003%!\t\u0003\u0005Q\u0001\t\u0015\r\u0011\"\u0011B\u0011!\u0011\u0006A!A!\u0002\u0013\u0011\u0005B\u0002+\u0001\t\u0003\u0001R\u000bC\u0003a\u0001\u0011\u0005\u0013MA\bDQ&\u001c\u0016\u000fV3tiJ+7/\u001e7u\u0015\ty\u0001#\u0001\u0003uKN$(BA\t\u0013\u0003\u0011\u0019H/\u0019;\u000b\u0005M!\u0012!B7mY&\u0014'BA\u000b\u0017\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0002$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0005\u0019qN]4\u0004\u0001M\u0019\u0001\u0001\b\u0012\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\r\u0005s\u0017PU3g!\r\u0019CEJ\u0007\u0002\u001d%\u0011QE\u0004\u0002\u000b)\u0016\u001cHOU3tk2$\bCA\u000f(\u0013\tAcDA\u0002J]R\fa\u0001\u001d,bYV,W#A\u0016\u0011\u0005ua\u0013BA\u0017\u001f\u0005\u0019!u.\u001e2mK\u00069\u0001OV1mk\u0016\u0004\u0013\u0001\u00053fOJ,Wm](g\rJ,W\rZ8n+\u00051\u0003fA\u00023qA\u00111GN\u0007\u0002i)\u0011Q\u0007F\u0001\u000bC:tw\u000e^1uS>t\u0017BA\u001c5\u0005\u0015\u0019\u0016N\\2fC\u0005I\u0014!B\u0019/c9\u0002\u0014!\u00053fOJ,Wm](g\rJ,W\rZ8nA!\u001aAA\r\u001d\u0002\u0013M$\u0018\r^5ti&\u001c\u0007fA\u00033q\u0005Q1\u000f^1uSN$\u0018n\u0019\u0011)\u0007\u0019\u0011\u0004(\u0001\u0004nKRDw\u000eZ\u000b\u0002\u0005B\u00111I\u0013\b\u0003\t\"\u0003\"!\u0012\u0010\u000e\u0003\u0019S!a\u0012\u000e\u0002\rq\u0012xn\u001c;?\u0013\tIe$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u00172\u0013aa\u0015;sS:<'BA%\u001fQ\r9!\u0007O\u0001\b[\u0016$\bn\u001c3!Q\rA!\u0007O\u0001\u000f]VdG\u000eS=q_RDWm]5tQ\rI!\u0007O\u0001\u0010]VdG\u000eS=q_RDWm]5tA!\u001a!B\r\u001d\u0002\rqJg.\u001b;?)\u00191v\u000b\u0017.]=B\u00111\u0005\u0001\u0005\u0006S-\u0001\ra\u000b\u0005\u0006_-\u0001\rA\n\u0015\u00041JB\u0004\"\u0002\u001f\f\u0001\u0004Y\u0003f\u0001.3q!)\u0001i\u0003a\u0001\u0005\"\u001aAL\r\u001d\t\u000bA[\u0001\u0019\u0001\")\u0007y\u0013\u0004(\u0001\u0005u_N#(/\u001b8h)\u0005\u0011\u0005f\u0001\u00013q\u0001"
)
public class ChiSqTestResult implements TestResult {
   private final double pValue;
   private final int degreesOfFreedom;
   private final double statistic;
   private final String method;
   private final String nullHypothesis;

   public double pValue() {
      return this.pValue;
   }

   public int degreesOfFreedom() {
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
      return "Chi squared test summary:\nmethod: " + var10000 + "\n" + TestResult.toString$(this);
   }

   public ChiSqTestResult(final double pValue, final int degreesOfFreedom, final double statistic, final String method, final String nullHypothesis) {
      this.pValue = pValue;
      this.degreesOfFreedom = degreesOfFreedom;
      this.statistic = statistic;
      this.method = method;
      this.nullHypothesis = nullHypothesis;
      TestResult.$init$(this);
   }
}
