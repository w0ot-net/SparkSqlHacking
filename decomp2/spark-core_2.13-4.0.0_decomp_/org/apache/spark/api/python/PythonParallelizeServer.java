package org.apache.spark.api.python;

import java.io.InputStream;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2Q!\u0002\u0004\u0001\u0015AA\u0001\"\u0006\u0001\u0003\u0002\u0003\u0006Ia\u0006\u0005\t7\u0001\u0011\t\u0011)A\u00059!)!\u0005\u0001C\u0001G!)q\u0005\u0001C)Q\t9\u0002+\u001f;i_:\u0004\u0016M]1mY\u0016d\u0017N_3TKJ4XM\u001d\u0006\u0003\u000f!\ta\u0001]=uQ>t'BA\u0005\u000b\u0003\r\t\u0007/\u001b\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sON\u0011\u0001!\u0005\t\u0003%Mi\u0011AB\u0005\u0003)\u0019\u0011q\u0002U=uQ>t'\u000b\u0012#TKJ4XM]\u0001\u0003g\u000e\u001c\u0001\u0001\u0005\u0002\u001935\t!\"\u0003\u0002\u001b\u0015\ta1\u000b]1sW\u000e{g\u000e^3yi\u0006Y\u0001/\u0019:bY2,G.[:n!\ti\u0002%D\u0001\u001f\u0015\u0005y\u0012!B:dC2\f\u0017BA\u0011\u001f\u0005\rIe\u000e^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u0011*c\u0005\u0005\u0002\u0013\u0001!)Qc\u0001a\u0001/!)1d\u0001a\u00019\u0005Y1\u000f\u001e:fC6$vN\u0015#E)\tIS\u0007E\u0002+[=j\u0011a\u000b\u0006\u0003Y)\t1A\u001d3e\u0013\tq3FA\u0002S\t\u0012\u00032!\b\u00193\u0013\t\tdDA\u0003BeJ\f\u0017\u0010\u0005\u0002\u001eg%\u0011AG\b\u0002\u0005\u0005f$X\rC\u00037\t\u0001\u0007q'A\u0003j]B,H\u000f\u0005\u00029{5\t\u0011H\u0003\u0002;w\u0005\u0011\u0011n\u001c\u0006\u0002y\u0005!!.\u0019<b\u0013\tq\u0014HA\u0006J]B,Ho\u0015;sK\u0006l\u0007"
)
public class PythonParallelizeServer extends PythonRDDServer {
   private final SparkContext sc;
   private final int parallelism;

   public RDD streamToRDD(final InputStream input) {
      return JavaRDD$.MODULE$.toRDD(PythonRDD$.MODULE$.readRDDFromInputStream(this.sc, input, this.parallelism));
   }

   public PythonParallelizeServer(final SparkContext sc, final int parallelism) {
      this.sc = sc;
      this.parallelism = parallelism;
   }
}
