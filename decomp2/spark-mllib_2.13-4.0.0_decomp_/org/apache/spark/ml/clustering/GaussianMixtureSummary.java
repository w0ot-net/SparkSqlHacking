package org.apache.spark.ml.clustering;

import org.apache.spark.sql.Dataset;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q4A\u0001D\u0007\u00011!IQ\u0004\u0001B\u0001B\u0003%aD\r\u0005\ng\u0001\u0011\t\u0011)A\u0005iyB\u0001b\u0010\u0001\u0003\u0006\u0004%\t\u0001\u0011\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005i!IA\n\u0001B\u0001B\u0003%A'\u0014\u0005\n\u001d\u0002\u0011\t\u0011)A\u0005\u001fNC\u0001\u0002\u0016\u0001\u0003\u0006\u0004%\t!\u0016\u0005\t9\u0002\u0011\t\u0011)A\u0005-\"Ia\f\u0001B\u0001B\u0003%qj\u0018\u0005\u0007A\u0002!\t!D1\t\u00111\u0004\u0001R1A\u0005\u00025\u0014acR1vgNL\u0017M\\'jqR,(/Z*v[6\f'/\u001f\u0006\u0003\u001d=\t!b\u00197vgR,'/\u001b8h\u0015\t\u0001\u0012#\u0001\u0002nY*\u0011!cE\u0001\u0006gB\f'o\u001b\u0006\u0003)U\ta!\u00199bG\",'\"\u0001\f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001I\u0002C\u0001\u000e\u001c\u001b\u0005i\u0011B\u0001\u000f\u000e\u0005E\u0019E.^:uKJLgnZ*v[6\f'/_\u0001\faJ,G-[2uS>t7\u000f\u0005\u0002 _9\u0011\u0001\u0005\f\b\u0003C)r!AI\u0015\u000f\u0005\rBcB\u0001\u0013(\u001b\u0005)#B\u0001\u0014\u0018\u0003\u0019a$o\\8u}%\ta#\u0003\u0002\u0015+%\u0011!cE\u0005\u0003WE\t1a]9m\u0013\tic&A\u0004qC\u000e\\\u0017mZ3\u000b\u0005-\n\u0012B\u0001\u00192\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0002.]%\u0011QdG\u0001\u000eaJ,G-[2uS>t7i\u001c7\u0011\u0005UZdB\u0001\u001c:!\t!sGC\u00019\u0003\u0015\u00198-\u00197b\u0013\tQt'\u0001\u0004Qe\u0016$WMZ\u0005\u0003yu\u0012aa\u0015;sS:<'B\u0001\u001e8\u0013\t\u00194$\u0001\bqe>\u0014\u0017MY5mSRL8i\u001c7\u0016\u0003QB3a\u0001\"I!\t\u0019e)D\u0001E\u0015\t)\u0015#\u0001\u0006b]:|G/\u0019;j_:L!a\u0012#\u0003\u000bMKgnY3\"\u0003%\u000bQA\r\u00181]A\nq\u0002\u001d:pE\u0006\u0014\u0017\u000e\\5us\u000e{G\u000e\t\u0015\u0004\t\tC\u0015a\u00034fCR,(/Z:D_2L!\u0001T\u000e\u0002\u0003-\u0004\"\u0001U)\u000e\u0003]J!AU\u001c\u0003\u0007%sG/\u0003\u0002O7\u0005iAn\\4MS.,G.\u001b5p_\u0012,\u0012A\u0016\t\u0003!^K!\u0001W\u001c\u0003\r\u0011{WO\u00197fQ\r9!IW\u0011\u00027\u0006)!G\f\u001a/a\u0005qAn\\4MS.,G.\u001b5p_\u0012\u0004\u0003f\u0001\u0005C5\u00069a.^7Ji\u0016\u0014\u0018B\u00010\u001c\u0003\u0019a\u0014N\\5u}QA!m\u00193fO\"L7\u000e\u0005\u0002\u001b\u0001!)QD\u0003a\u0001=!)1G\u0003a\u0001i!)qH\u0003a\u0001i!\u001aQM\u0011%\t\u000b1S\u0001\u0019\u0001\u001b\t\u000b9S\u0001\u0019A(\t\u000bQS\u0001\u0019\u0001,)\u0007%\u0014%\fC\u0003_\u0015\u0001\u0007q*A\u0006qe>\u0014\u0017MY5mSRLX#\u0001\u0010)\u0007-\u0011\u0005\n\u000b\u0002\faB\u0011\u0001+]\u0005\u0003e^\u0012\u0011\u0002\u001e:b]NLWM\u001c;)\u0007\u0001\u0011\u0005\n"
)
public class GaussianMixtureSummary extends ClusteringSummary {
   private transient Dataset probability;
   private final String probabilityCol;
   private final double logLikelihood;
   private transient volatile boolean bitmap$trans$0;

   public String probabilityCol() {
      return this.probabilityCol;
   }

   public double logLikelihood() {
      return this.logLikelihood;
   }

   private Dataset probability$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.probability = super.predictions().select(this.probabilityCol(), .MODULE$);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.probability;
   }

   public Dataset probability() {
      return !this.bitmap$trans$0 ? this.probability$lzycompute() : this.probability;
   }

   public GaussianMixtureSummary(final Dataset predictions, final String predictionCol, final String probabilityCol, final String featuresCol, final int k, final double logLikelihood, final int numIter) {
      super(predictions, predictionCol, featuresCol, k, numIter);
      this.probabilityCol = probabilityCol;
      this.logLikelihood = logLikelihood;
   }
}
