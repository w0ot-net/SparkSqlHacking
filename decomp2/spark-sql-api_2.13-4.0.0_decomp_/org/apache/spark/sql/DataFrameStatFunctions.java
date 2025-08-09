package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.util.sketch.BloomFilter;
import org.apache.spark.util.sketch.CountMinSketch;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005}h!B\r\u001b\u0003\u0003\u0019\u0003\"\u0002\u0016\u0001\t\u0003Y\u0003\"\u0002\u0018\u0001\r#y\u0003\"B\u001c\u0001\t\u0003A\u0004\"B\u001c\u0001\r\u0003\u0001\u0006\"B,\u0001\r\u0003A\u0006\"B/\u0001\r\u0003q\u0006\"B/\u0001\t\u0003\u0019\u0007\"\u00024\u0001\r\u00039\u0007\"\u00026\u0001\t\u0003Y\u0007\"\u00026\u0001\t\u0003y\u0007\"\u00026\u0001\r\u0003\t\b\"\u00026\u0001\t\u0003a\b\"\u0002@\u0001\t\u0003y\bB\u0002@\u0001\t\u0003\ty\u0003\u0003\u0004\u007f\u0001\u0019\u0005\u0011Q\u000b\u0005\u0007}\u0002!\t!a\u001b\t\u000f\u0005m\u0004\u0001\"\u0001\u0002~!9\u00111\u0010\u0001\u0005\u0002\u0005\u0005\u0006bBA>\u0001\u0011\u0005\u0011q\u0016\u0005\b\u0003w\u0002A\u0011AA]\u0011\u001d\t\u0019\r\u0001C\u0001\u0003\u000bDq!a1\u0001\t\u0003\t9\u000eC\u0004\u0002D\u0002!\t!a8\t\u000f\u0005\r\u0007\u0001\"\u0001\u0002j\n1B)\u0019;b\rJ\fW.Z*uCR4UO\\2uS>t7O\u0003\u0002\u001c9\u0005\u00191/\u001d7\u000b\u0005uq\u0012!B:qCJ\\'BA\u0010!\u0003\u0019\t\u0007/Y2iK*\t\u0011%A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001IA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u0017\u0011\u00055\u0002Q\"\u0001\u000e\u0002\u0005\u00114W#\u0001\u0019\u0011\u0005E\"dBA\u00173\u0013\t\u0019$$A\u0004qC\u000e\\\u0017mZ3\n\u0005U2$!\u0003#bi\u00064%/Y7f\u0015\t\u0019$$\u0001\bbaB\u0014x\u000e_)vC:$\u0018\u000e\\3\u0015\tezDJ\u0014\t\u0004Kib\u0014BA\u001e'\u0005\u0015\t%O]1z!\t)S(\u0003\u0002?M\t1Ai\\;cY\u0016DQ\u0001Q\u0002A\u0002\u0005\u000b1aY8m!\t\u0011\u0015J\u0004\u0002D\u000fB\u0011AIJ\u0007\u0002\u000b*\u0011aII\u0001\u0007yI|w\u000e\u001e \n\u0005!3\u0013A\u0002)sK\u0012,g-\u0003\u0002K\u0017\n11\u000b\u001e:j]\u001eT!\u0001\u0013\u0014\t\u000b5\u001b\u0001\u0019A\u001d\u0002\u001bA\u0014xNY1cS2LG/[3t\u0011\u0015y5\u00011\u0001=\u00035\u0011X\r\\1uSZ,WI\u001d:peR!\u0011KU+W!\r)#(\u000f\u0005\u0006'\u0012\u0001\r\u0001V\u0001\u0005G>d7\u000fE\u0002&u\u0005CQ!\u0014\u0003A\u0002eBQa\u0014\u0003A\u0002q\n1aY8w)\ra\u0014l\u0017\u0005\u00065\u0016\u0001\r!Q\u0001\u0005G>d\u0017\u0007C\u0003]\u000b\u0001\u0007\u0011)\u0001\u0003d_2\u0014\u0014\u0001B2peJ$B\u0001P0aC\")!L\u0002a\u0001\u0003\")AL\u0002a\u0001\u0003\")!M\u0002a\u0001\u0003\u00061Q.\u001a;i_\u0012$2\u0001\u00103f\u0011\u0015Qv\u00011\u0001B\u0011\u0015av\u00011\u0001B\u0003!\u0019'o\\:ti\u0006\u0014Gc\u0001\u0019iS\")!\f\u0003a\u0001\u0003\")A\f\u0003a\u0001\u0003\u0006IaM]3r\u0013R,Wn\u001d\u000b\u0004a1l\u0007\"B*\n\u0001\u0004!\u0006\"\u00028\n\u0001\u0004a\u0014aB:vaB|'\u000f\u001e\u000b\u0003aADQa\u0015\u0006A\u0002Q#2\u0001\r:|\u0011\u0015\u00196\u00021\u0001t!\r!\b0\u0011\b\u0003k^t!\u0001\u0012<\n\u0003\u001dJ!a\r\u0014\n\u0005eT(aA*fc*\u00111G\n\u0005\u0006].\u0001\r\u0001\u0010\u000b\u0003auDQa\u0015\u0007A\u0002M\f\u0001b]1na2,')_\u000b\u0005\u0003\u0003\t\u0019\u0002F\u00041\u0003\u0007\t)!!\n\t\u000b\u0001k\u0001\u0019A!\t\u000f\u0005\u001dQ\u00021\u0001\u0002\n\u0005IaM]1di&|gn\u001d\t\u0007\u0005\u0006-\u0011q\u0002\u001f\n\u0007\u000551JA\u0002NCB\u0004B!!\u0005\u0002\u00141\u0001AaBA\u000b\u001b\t\u0007\u0011q\u0003\u0002\u0002)F!\u0011\u0011DA\u0010!\r)\u00131D\u0005\u0004\u0003;1#a\u0002(pi\"Lgn\u001a\t\u0004K\u0005\u0005\u0012bAA\u0012M\t\u0019\u0011I\\=\t\u000f\u0005\u001dR\u00021\u0001\u0002*\u0005!1/Z3e!\r)\u00131F\u0005\u0004\u0003[1#\u0001\u0002'p]\u001e,B!!\r\u0002HQ9\u0001'a\r\u00026\u0005M\u0003\"\u0002!\u000f\u0001\u0004\t\u0005bBA\u0004\u001d\u0001\u0007\u0011q\u0007\t\t\u0003s\t\u0019%!\u0012\u0002J5\u0011\u00111\b\u0006\u0005\u0003{\ty$\u0001\u0003vi&d'BAA!\u0003\u0011Q\u0017M^1\n\t\u00055\u00111\b\t\u0005\u0003#\t9\u0005B\u0004\u0002\u00169\u0011\r!a\u0006\u0011\t\u0005-\u0013\u0011K\u0007\u0003\u0003\u001bRA!a\u0014\u0002@\u0005!A.\u00198h\u0013\rq\u0014Q\n\u0005\b\u0003Oq\u0001\u0019AA\u0015+\u0011\t9&a\u001a\u0015\u000fA\nI&!\u0019\u0002j!1\u0001i\u0004a\u0001\u00037\u00022!LA/\u0013\r\tyF\u0007\u0002\u0007\u0007>dW/\u001c8\t\u000f\u0005\u001dq\u00021\u0001\u0002dA1!)a\u0003\u0002fq\u0002B!!\u0005\u0002h\u00119\u0011QC\bC\u0002\u0005]\u0001bBA\u0014\u001f\u0001\u0007\u0011\u0011F\u000b\u0005\u0003[\n9\bF\u00041\u0003_\n\t(!\u001f\t\r\u0001\u0003\u0002\u0019AA.\u0011\u001d\t9\u0001\u0005a\u0001\u0003g\u0002\u0002\"!\u000f\u0002D\u0005U\u0014\u0011\n\t\u0005\u0003#\t9\bB\u0004\u0002\u0016A\u0011\r!a\u0006\t\u000f\u0005\u001d\u0002\u00031\u0001\u0002*\u0005q1m\\;oi6KgnU6fi\u000eDGCCA@\u0003\u001b\u000b\t*a'\u0002 B!\u0011\u0011QAE\u001b\t\t\u0019I\u0003\u0003\u0002\u0006\u0006\u001d\u0015AB:lKR\u001c\u0007NC\u0002\u0002>qIA!a#\u0002\u0004\nq1i\\;oi6KgnU6fi\u000eD\u0007BBAH#\u0001\u0007\u0011)A\u0004d_2t\u0015-\\3\t\u000f\u0005M\u0015\u00031\u0001\u0002\u0016\u0006)A-\u001a9uQB\u0019Q%a&\n\u0007\u0005eeEA\u0002J]RDq!!(\u0012\u0001\u0004\t)*A\u0003xS\u0012$\b\u000eC\u0004\u0002(E\u0001\r!!&\u0015\u0015\u0005}\u00141UAS\u0003S\u000bi\u000b\u0003\u0004\u0002\u0010J\u0001\r!\u0011\u0005\u0007\u0003O\u0013\u0002\u0019\u0001\u001f\u0002\u0007\u0015\u00048\u000f\u0003\u0004\u0002,J\u0001\r\u0001P\u0001\u000bG>tg-\u001b3f]\u000e,\u0007bBA\u0014%\u0001\u0007\u0011Q\u0013\u000b\u000b\u0003\u007f\n\t,a-\u00026\u0006]\u0006B\u0002!\u0014\u0001\u0004\tY\u0006C\u0004\u0002\u0014N\u0001\r!!&\t\u000f\u0005u5\u00031\u0001\u0002\u0016\"9\u0011qE\nA\u0002\u0005UECCA@\u0003w\u000bi,a0\u0002B\"1\u0001\t\u0006a\u0001\u00037Ba!a*\u0015\u0001\u0004a\u0004BBAV)\u0001\u0007A\bC\u0004\u0002(Q\u0001\r!!&\u0002\u0017\tdwn\\7GS2$XM\u001d\u000b\t\u0003\u000f\fi-a4\u0002TB!\u0011\u0011QAe\u0013\u0011\tY-a!\u0003\u0017\tcwn\\7GS2$XM\u001d\u0005\u0007\u0003\u001f+\u0002\u0019A!\t\u000f\u0005EW\u00031\u0001\u0002*\u0005\u0001R\r\u001f9fGR,GMT;n\u0013R,Wn\u001d\u0005\u0007\u0003+,\u0002\u0019\u0001\u001f\u0002\u0007\u0019\u0004\b\u000f\u0006\u0005\u0002H\u0006e\u00171\\Ao\u0011\u0019\u0001e\u00031\u0001\u0002\\!9\u0011\u0011\u001b\fA\u0002\u0005%\u0002BBAk-\u0001\u0007A\b\u0006\u0005\u0002H\u0006\u0005\u00181]As\u0011\u0019\tyi\u0006a\u0001\u0003\"9\u0011\u0011[\fA\u0002\u0005%\u0002bBAt/\u0001\u0007\u0011\u0011F\u0001\b]Vl')\u001b;t)!\t9-a;\u0002n\u0006=\bB\u0002!\u0019\u0001\u0004\tY\u0006C\u0004\u0002Rb\u0001\r!!\u000b\t\u000f\u0005\u001d\b\u00041\u0001\u0002*!\u001a\u0001!a=\u0011\t\u0005U\u00181`\u0007\u0003\u0003oT1!!?\u001d\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003{\f9P\u0001\u0004Ti\u0006\u0014G.\u001a"
)
public abstract class DataFrameStatFunctions {
   public abstract Dataset df();

   public double[] approxQuantile(final String col, final double[] probabilities, final double relativeError) {
      return (double[])CurrentOrigin$.MODULE$.withOrigin(() -> (double[]).MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.approxQuantile((String[])((Object[])(new String[]{col})), probabilities, relativeError))));
   }

   public abstract double[][] approxQuantile(final String[] cols, final double[] probabilities, final double relativeError);

   public abstract double cov(final String col1, final String col2);

   public abstract double corr(final String col1, final String col2, final String method);

   public double corr(final String col1, final String col2) {
      return this.corr(col1, col2, "pearson");
   }

   public abstract Dataset crosstab(final String col1, final String col2);

   public Dataset freqItems(final String[] cols, final double support) {
      return this.freqItems((Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(cols).toImmutableArraySeq(), support);
   }

   public Dataset freqItems(final String[] cols) {
      return this.freqItems(cols, 0.01);
   }

   public abstract Dataset freqItems(final Seq cols, final double support);

   public Dataset freqItems(final Seq cols) {
      return this.freqItems(cols, 0.01);
   }

   public Dataset sampleBy(final String col, final Map fractions, final long seed) {
      return this.sampleBy(Column$.MODULE$.apply(col), fractions, seed);
   }

   public Dataset sampleBy(final String col, final java.util.Map fractions, final long seed) {
      return this.sampleBy(col, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(fractions).asScala().toMap(scala..less.colon.less..MODULE$.refl()), seed);
   }

   public abstract Dataset sampleBy(final Column col, final Map fractions, final long seed);

   public Dataset sampleBy(final Column col, final java.util.Map fractions, final long seed) {
      return this.sampleBy(col, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(fractions).asScala().toMap(scala..less.colon.less..MODULE$.refl()), seed);
   }

   public CountMinSketch countMinSketch(final String colName, final int depth, final int width, final int seed) {
      return this.countMinSketch(Column$.MODULE$.apply(colName), depth, width, seed);
   }

   public CountMinSketch countMinSketch(final String colName, final double eps, final double confidence, final int seed) {
      return this.countMinSketch(Column$.MODULE$.apply(colName), eps, confidence, seed);
   }

   public CountMinSketch countMinSketch(final Column col, final int depth, final int width, final int seed) {
      double eps = (double)2.0F / (double)width;
      double confidence = (double)1 - (double)1 / Math.pow((double)2.0F, (double)depth);
      return this.countMinSketch(col, eps, confidence, seed);
   }

   public CountMinSketch countMinSketch(final Column col, final double eps, final double confidence, final int seed) {
      return (CountMinSketch)CurrentOrigin$.MODULE$.withOrigin(() -> {
         Column cms = functions$.MODULE$.count_min_sketch(col, functions$.MODULE$.lit(BoxesRunTime.boxToDouble(eps)), functions$.MODULE$.lit(BoxesRunTime.boxToDouble(confidence)), functions$.MODULE$.lit(BoxesRunTime.boxToInteger(seed)));
         byte[] bytes = (byte[])this.df().select((Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{cms})).as((Encoder)AgnosticEncoders.BinaryEncoder$.MODULE$).head();
         return CountMinSketch.readFrom(bytes);
      });
   }

   public BloomFilter bloomFilter(final String colName, final long expectedNumItems, final double fpp) {
      return this.bloomFilter(Column$.MODULE$.apply(colName), expectedNumItems, fpp);
   }

   public BloomFilter bloomFilter(final Column col, final long expectedNumItems, final double fpp) {
      long numBits = BloomFilter.optimalNumOfBits(expectedNumItems, fpp);
      return this.bloomFilter(col, expectedNumItems, numBits);
   }

   public BloomFilter bloomFilter(final String colName, final long expectedNumItems, final long numBits) {
      return this.bloomFilter(Column$.MODULE$.apply(colName), expectedNumItems, numBits);
   }

   public BloomFilter bloomFilter(final Column col, final long expectedNumItems, final long numBits) {
      return (BloomFilter)CurrentOrigin$.MODULE$.withOrigin(() -> {
         Column bf = Column$.MODULE$.internalFn("bloom_filter_agg", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{col, functions$.MODULE$.lit(BoxesRunTime.boxToLong(expectedNumItems)), functions$.MODULE$.lit(BoxesRunTime.boxToLong(numBits))}));
         byte[] bytes = (byte[])this.df().select((Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{bf})).as((Encoder)AgnosticEncoders.BinaryEncoder$.MODULE$).head();
         return BloomFilter.readFrom(bytes);
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
