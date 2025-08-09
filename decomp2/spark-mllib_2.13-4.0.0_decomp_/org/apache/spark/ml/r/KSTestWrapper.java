package org.apache.spark.ml.r;

import org.apache.spark.mllib.stat.test.KolmogorovSmirnovTestResult;
import org.apache.spark.sql.Dataset;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U4Q!\u0005\n\u0001%qA\u0001b\t\u0001\u0003\u0006\u0004%\t!\n\u0005\ta\u0001\u0011\t\u0011)A\u0005M!A\u0011\u0007\u0001BC\u0002\u0013\u0005!\u0007\u0003\u0005?\u0001\t\u0005\t\u0015!\u00034\u0011!y\u0004A!b\u0001\n\u0003\u0001\u0005\u0002C$\u0001\u0005\u0003\u0005\u000b\u0011B!\t\u000b!\u0003A\u0011B%\t\u0011=\u0003\u0001R1A\u0005\u0002AC\u0001\"\u0015\u0001\t\u0006\u0004%\t\u0001\u0015\u0005\t%\u0002A)\u0019!C\u0001e!A1\u000b\u0001EC\u0002\u0013\u0005A\u000bC\u0003Y\u0001\u0011\u0005!g\u0002\u0004Z%!\u0005!C\u0017\u0004\u0007#IA\tAE.\t\u000b!sA\u0011\u0001/\t\u000b%rA\u0011A/\u0003\u001b-\u001bF+Z:u/J\f\u0007\u000f]3s\u0015\t\u0019B#A\u0001s\u0015\t)b#\u0001\u0002nY*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xm\u0005\u0002\u0001;A\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t1\u0011I\\=SK\u001a\f!\u0002^3tiJ+7/\u001e7u\u0007\u0001)\u0012A\n\t\u0003O9j\u0011\u0001\u000b\u0006\u0003S)\nA\u0001^3ti*\u00111\u0006L\u0001\u0005gR\fGO\u0003\u0002.-\u0005)Q\u000e\u001c7jE&\u0011q\u0006\u000b\u0002\u001c\u0017>dWn\\4pe>48+\\5s]>4H+Z:u%\u0016\u001cX\u000f\u001c;\u0002\u0017Q,7\u000f\u001e*fgVdG\u000fI\u0001\tI&\u001cHOT1nKV\t1\u0007\u0005\u00025w9\u0011Q'\u000f\t\u0003m}i\u0011a\u000e\u0006\u0003q\u0011\na\u0001\u0010:p_Rt\u0014B\u0001\u001e \u0003\u0019\u0001&/\u001a3fM&\u0011A(\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005iz\u0012!\u00033jgRt\u0015-\\3!\u0003)!\u0017n\u001d;QCJ\fWn]\u000b\u0002\u0003B\u0019aD\u0011#\n\u0005\r{\"!B!se\u0006L\bC\u0001\u0010F\u0013\t1uD\u0001\u0004E_V\u0014G.Z\u0001\fI&\u001cH\u000fU1sC6\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0005\u00152ke\n\u0005\u0002L\u00015\t!\u0003C\u0003$\u000f\u0001\u0007a\u0005C\u00032\u000f\u0001\u00071\u0007C\u0003@\u000f\u0001\u0007\u0011)\u0001\u0004q-\u0006dW/Z\u000b\u0002\t\u0006I1\u000f^1uSN$\u0018nY\u0001\u000f]VdG\u000eS=q_RDWm]5t\u0003A!Wm\u001a:fKN|eM\u0012:fK\u0012|W.F\u0001V!\tqb+\u0003\u0002X?\t\u0019\u0011J\u001c;\u0002\u000fM,X.\\1ss\u0006i1j\u0015+fgR<&/\u00199qKJ\u0004\"a\u0013\b\u0014\u00059iB#\u0001.\u0015\u000b)s\u0016o\u001d;\t\u000b}\u0003\u0002\u0019\u00011\u0002\t\u0011\fG/\u0019\t\u0003C:t!AY6\u000f\u0005\rLgB\u00013i\u001d\t)wM\u0004\u00027M&\t1$\u0003\u0002\u001a5%\u0011q\u0003G\u0005\u0003UZ\t1a]9m\u0013\taW.A\u0004qC\u000e\\\u0017mZ3\u000b\u0005)4\u0012BA8q\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0002m[\")!\u000f\u0005a\u0001g\u0005Ya-Z1ukJ,g*Y7f\u0011\u0015\t\u0004\u00031\u00014\u0011\u0015y\u0004\u00031\u0001B\u0001"
)
public class KSTestWrapper {
   private double pValue;
   private double statistic;
   private String nullHypothesis;
   private int degreesOfFreedom;
   private final KolmogorovSmirnovTestResult testResult;
   private final String distName;
   private final double[] distParams;
   private volatile byte bitmap$0;

   public static KSTestWrapper test(final Dataset data, final String featureName, final String distName, final double[] distParams) {
      return KSTestWrapper$.MODULE$.test(data, featureName, distName, distParams);
   }

   public KolmogorovSmirnovTestResult testResult() {
      return this.testResult;
   }

   public String distName() {
      return this.distName;
   }

   public double[] distParams() {
      return this.distParams;
   }

   private double pValue$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.pValue = this.testResult().pValue();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.pValue;
   }

   public double pValue() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.pValue$lzycompute() : this.pValue;
   }

   private double statistic$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.statistic = this.testResult().statistic();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.statistic;
   }

   public double statistic() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.statistic$lzycompute() : this.statistic;
   }

   private String nullHypothesis$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.nullHypothesis = this.testResult().nullHypothesis();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.nullHypothesis;
   }

   public String nullHypothesis() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.nullHypothesis$lzycompute() : this.nullHypothesis;
   }

   private int degreesOfFreedom$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.degreesOfFreedom = this.testResult().degreesOfFreedom();
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.degreesOfFreedom;
   }

   public int degreesOfFreedom() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.degreesOfFreedom$lzycompute() : this.degreesOfFreedom;
   }

   public String summary() {
      return this.testResult().toString();
   }

   public KSTestWrapper(final KolmogorovSmirnovTestResult testResult, final String distName, final double[] distParams) {
      this.testResult = testResult;
      this.distName = distName;
      this.distParams = distParams;
   }
}
