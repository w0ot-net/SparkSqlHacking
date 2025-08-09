package org.apache.spark.ml.image;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.spark.sql.SparkSession;
import scala.Function0;
import scala.reflect.ScalaSignature;
import scala.util.Random;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b\u0001\u0002\n\u0014\tyAQ!\f\u0001\u0005\u00029Bq!\r\u0001C\u0002\u0013\u0005!\u0007\u0003\u0004<\u0001\u0001\u0006Ia\r\u0005\by\u0001\u0001\r\u0011\"\u0001>\u0011\u001d\u0011\u0005\u00011A\u0005\u0002\rCa!\u0013\u0001!B\u0013q\u0004\"\u0002&\u0001\t\u0003Z\u0005\"\u0002)\u0001\t\u0003\nv!\u0002.\u0014\u0011\u0013Yf!\u0002\n\u0014\u0011\u0013a\u0006\"B\u0017\u000b\t\u0003\u0001\u0007bB1\u000b\u0005\u0004%\tA\u0019\u0005\u0007W*\u0001\u000b\u0011B2\t\u000f1T!\u0019!C\u0001E\"1QN\u0003Q\u0001\n\rDQA\u001c\u0006\u0005\u0002=DQ!\u001d\u0006\u0005\u0002I\u0014\u0001cU1na2,\u0007+\u0019;i\r&dG/\u001a:\u000b\u0005Q)\u0012!B5nC\u001e,'B\u0001\f\u0018\u0003\tiGN\u0003\u0002\u00193\u0005)1\u000f]1sW*\u0011!dG\u0001\u0007CB\f7\r[3\u000b\u0003q\t1a\u001c:h\u0007\u0001\u00192\u0001A\u0010(!\t\u0001S%D\u0001\"\u0015\t\u00113%\u0001\u0003d_:4'B\u0001\u0013\u001a\u0003\u0019A\u0017\rZ8pa&\u0011a%\t\u0002\u000b\u0007>tg-[4ve\u0016$\u0007C\u0001\u0015,\u001b\u0005I#B\u0001\u0016$\u0003\t17/\u0003\u0002-S\tQ\u0001+\u0019;i\r&dG/\u001a:\u0002\rqJg.\u001b;?)\u0005y\u0003C\u0001\u0019\u0001\u001b\u0005\u0019\u0012A\u0002:b]\u0012|W.F\u00014!\t!\u0014(D\u00016\u0015\t1t'\u0001\u0003vi&d'\"\u0001\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005i*$A\u0002*b]\u0012|W.A\u0004sC:$w.\u001c\u0011\u0002\u0017M\fW\u000e\u001d7f%\u0006$\u0018n\\\u000b\u0002}A\u0011q\bQ\u0007\u0002o%\u0011\u0011i\u000e\u0002\u0007\t>,(\r\\3\u0002\u001fM\fW\u000e\u001d7f%\u0006$\u0018n\\0%KF$\"\u0001R$\u0011\u0005}*\u0015B\u0001$8\u0005\u0011)f.\u001b;\t\u000f!+\u0011\u0011!a\u0001}\u0005\u0019\u0001\u0010J\u0019\u0002\u0019M\fW\u000e\u001d7f%\u0006$\u0018n\u001c\u0011\u0002\u000fM,GoQ8oMR\u0011A\t\u0014\u0005\u0006E\u001d\u0001\r!\u0014\t\u0003A9K!aT\u0011\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003\u0019\t7mY3qiR\u0011!+\u0016\t\u0003\u007fMK!\u0001V\u001c\u0003\u000f\t{w\u000e\\3b]\")a\u000b\u0003a\u0001/\u0006!\u0001/\u0019;i!\tA\u0003,\u0003\u0002ZS\t!\u0001+\u0019;i\u0003A\u0019\u0016-\u001c9mKB\u000bG\u000f\u001b$jYR,'\u000f\u0005\u00021\u0015M\u0011!\"\u0018\t\u0003\u007fyK!aX\u001c\u0003\r\u0005s\u0017PU3g)\u0005Y\u0016A\u0003:bi&|\u0007+\u0019:b[V\t1\r\u0005\u0002eS6\tQM\u0003\u0002gO\u0006!A.\u00198h\u0015\u0005A\u0017\u0001\u00026bm\u0006L!A[3\u0003\rM#(/\u001b8h\u0003-\u0011\u0018\r^5p!\u0006\u0014\u0018-\u001c\u0011\u0002\u0013M,W\r\u001a)be\u0006l\u0017AC:fK\u0012\u0004\u0016M]1nA\u00051\u0011n\u001d$jY\u0016$\"A\u00159\t\u000bY\u0003\u0002\u0019A,\u0002\u001d]LG\u000f\u001b)bi\"4\u0015\u000e\u001c;feV\u00111o\u001e\u000b\bi\u0006-\u0011QBA\u000e)\r)\u0018\u0011\u0001\t\u0003m^d\u0001\u0001B\u0003y#\t\u0007\u0011PA\u0001U#\tQX\u0010\u0005\u0002@w&\u0011Ap\u000e\u0002\b\u001d>$\b.\u001b8h!\tyd0\u0003\u0002\u0000o\t\u0019\u0011I\\=\t\u0011\u0005\r\u0011\u0003\"a\u0001\u0003\u000b\t\u0011A\u001a\t\u0005\u007f\u0005\u001dQ/C\u0002\u0002\n]\u0012\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\u0006yE\u0001\rA\u0010\u0005\u00071E\u0001\r!a\u0004\u0011\t\u0005E\u0011qC\u0007\u0003\u0003'Q1!!\u0006\u0018\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u00033\t\u0019B\u0001\u0007Ta\u0006\u00148nU3tg&|g\u000eC\u0004\u0002\u001eE\u0001\r!a\b\u0002\tM,W\r\u001a\t\u0004\u007f\u0005\u0005\u0012bAA\u0012o\t!Aj\u001c8h\u0001"
)
public class SamplePathFilter extends Configured implements PathFilter {
   private final Random random = new Random();
   private double sampleRatio = (double)1.0F;

   public static Object withPathFilter(final double sampleRatio, final SparkSession spark, final long seed, final Function0 f) {
      return SamplePathFilter$.MODULE$.withPathFilter(sampleRatio, spark, seed, f);
   }

   public static boolean isFile(final Path path) {
      return SamplePathFilter$.MODULE$.isFile(path);
   }

   public static String seedParam() {
      return SamplePathFilter$.MODULE$.seedParam();
   }

   public static String ratioParam() {
      return SamplePathFilter$.MODULE$.ratioParam();
   }

   public Random random() {
      return this.random;
   }

   public double sampleRatio() {
      return this.sampleRatio;
   }

   public void sampleRatio_$eq(final double x$1) {
      this.sampleRatio = x$1;
   }

   public void setConf(final Configuration conf) {
      if (conf != null) {
         this.sampleRatio_$eq(conf.getDouble(SamplePathFilter$.MODULE$.ratioParam(), (double)1.0F));
         long seed = conf.getLong(SamplePathFilter$.MODULE$.seedParam(), 0L);
         this.random().setSeed(seed);
      }
   }

   public boolean accept(final Path path) {
      return !SamplePathFilter$.MODULE$.isFile(path) || this.random().nextDouble() < this.sampleRatio();
   }
}
