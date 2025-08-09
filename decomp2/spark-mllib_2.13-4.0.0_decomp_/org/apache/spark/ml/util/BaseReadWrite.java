package org.apache.spark.ml.util;

import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593\u0001\u0002C\u0005\u0011\u0002\u0007\u0005\u0012b\u0005\u0005\u00065\u0001!\t\u0001\b\u0005\bA\u0001\u0001\r\u0011\"\u0003\"\u0011\u001dY\u0003\u00011A\u0005\n1BQa\f\u0001\u0005\u0002ABQa\r\u0001\u0005\u0016uBQA\u0010\u0001\u0005\u0016}BQa\u0011\u0001\u0005\u0016\u0011\u0013QBQ1tKJ+\u0017\rZ,sSR,'B\u0001\u0006\f\u0003\u0011)H/\u001b7\u000b\u00051i\u0011AA7m\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7C\u0001\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u001e!\t)b$\u0003\u0002 -\t!QK\\5u\u0003Iy\u0007\u000f^5p]N\u0003\u0018M]6TKN\u001c\u0018n\u001c8\u0016\u0003\t\u00022!F\u0012&\u0013\t!cC\u0001\u0004PaRLwN\u001c\t\u0003M%j\u0011a\n\u0006\u0003Q5\t1a]9m\u0013\tQsE\u0001\u0007Ta\u0006\u00148nU3tg&|g.\u0001\fpaRLwN\\*qCJ\\7+Z:tS>tw\fJ3r)\tiR\u0006C\u0004/\u0007\u0005\u0005\t\u0019\u0001\u0012\u0002\u0007a$\u0013'A\u0004tKN\u001c\u0018n\u001c8\u0015\u0005E\u0012T\"\u0001\u0001\t\u000bM\"\u0001\u0019A\u0013\u0002\u0019M\u0004\u0018M]6TKN\u001c\u0018n\u001c8)\u0007\u0011)4\b\u0005\u00027s5\tqG\u0003\u00029\u001b\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005i:$!B*j]\u000e,\u0017%\u0001\u001f\u0002\u000bIr\u0003G\f\u0019\u0016\u0003\u0015\n!b]9m\u0007>tG/\u001a=u+\u0005\u0001\u0005C\u0001\u0014B\u0013\t\u0011uE\u0001\u0006T#2\u001buN\u001c;fqR\f!a]2\u0016\u0003\u0015\u0003\"AR$\u000e\u00035I!\u0001S\u0007\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;*\u0007\u0001QE*\u0003\u0002L\u0013\tAQ\n\u0014*fC\u0012,'/\u0003\u0002N\u0013\tAQ\nT,sSR,'\u000f"
)
public interface BaseReadWrite {
   Option org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession();

   void org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession_$eq(final Option x$1);

   // $FF: synthetic method
   static BaseReadWrite session$(final BaseReadWrite $this, final SparkSession sparkSession) {
      return $this.session(sparkSession);
   }

   default BaseReadWrite session(final SparkSession sparkSession) {
      this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession_$eq(.MODULE$.apply(sparkSession));
      return this;
   }

   // $FF: synthetic method
   static SparkSession sparkSession$(final BaseReadWrite $this) {
      return $this.sparkSession();
   }

   default SparkSession sparkSession() {
      if (this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession().isEmpty()) {
         this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession_$eq(new Some(org.apache.spark.sql.SparkSession..MODULE$.builder().getOrCreate()));
      }

      return (SparkSession)this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession().get();
   }

   // $FF: synthetic method
   static SQLContext sqlContext$(final BaseReadWrite $this) {
      return $this.sqlContext();
   }

   default SQLContext sqlContext() {
      return this.sparkSession().sqlContext();
   }

   // $FF: synthetic method
   static SparkContext sc$(final BaseReadWrite $this) {
      return $this.sc();
   }

   default SparkContext sc() {
      return this.sparkSession().sparkContext();
   }

   static void $init$(final BaseReadWrite $this) {
      $this.org$apache$spark$ml$util$BaseReadWrite$$optionSparkSession_$eq(scala.None..MODULE$);
   }
}
