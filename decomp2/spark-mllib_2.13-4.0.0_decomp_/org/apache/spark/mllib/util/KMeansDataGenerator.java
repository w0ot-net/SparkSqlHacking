package org.apache.spark.mllib.util;

import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%<QAB\u0004\t\u0002I1Q\u0001F\u0004\t\u0002UAQ\u0001H\u0001\u0005\u0002uAQAH\u0001\u0005\u0002}Aq\u0001S\u0001\u0012\u0002\u0013\u0005\u0011\nC\u0003T\u0003\u0011\u0005A+A\nL\u001b\u0016\fgn\u001d#bi\u0006<UM\\3sCR|'O\u0003\u0002\t\u0013\u0005!Q\u000f^5m\u0015\tQ1\"A\u0003nY2L'M\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u0001\"aE\u0001\u000e\u0003\u001d\u00111cS'fC:\u001cH)\u0019;b\u000f\u0016tWM]1u_J\u001c\"!\u0001\f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\t!#A\thK:,'/\u0019;f\u00176+\u0017M\\:S\t\u0012#r\u0001\t\u00173oeZT\bE\u0002\"I\u0019j\u0011A\t\u0006\u0003G-\t1A\u001d3e\u0013\t)#EA\u0002S\t\u0012\u00032aF\u0014*\u0013\tA\u0003DA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0018U%\u00111\u0006\u0007\u0002\u0007\t>,(\r\\3\t\u000b5\u001a\u0001\u0019\u0001\u0018\u0002\u0005M\u001c\u0007CA\u00181\u001b\u0005Y\u0011BA\u0019\f\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0011\u0015\u00194\u00011\u00015\u0003%qW/\u001c)pS:$8\u000f\u0005\u0002\u0018k%\u0011a\u0007\u0007\u0002\u0004\u0013:$\b\"\u0002\u001d\u0004\u0001\u0004!\u0014!A6\t\u000bi\u001a\u0001\u0019\u0001\u001b\u0002\u0003\u0011DQ\u0001P\u0002A\u0002%\n\u0011A\u001d\u0005\b}\r\u0001\n\u00111\u00015\u00035qW/\u001c)beRLG/[8og\"\u001a1\u0001\u0011$\u0011\u0005\u0005#U\"\u0001\"\u000b\u0005\r[\u0011AC1o]>$\u0018\r^5p]&\u0011QI\u0011\u0002\u0006'&t7-Z\u0011\u0002\u000f\u0006)\u0001G\f\u001d/a\u0005Yr-\u001a8fe\u0006$XmS'fC:\u001c(\u000b\u0012#%I\u00164\u0017-\u001e7uIY*\u0012A\u0013\u0016\u0003i-[\u0013\u0001\u0014\t\u0003\u001bFk\u0011A\u0014\u0006\u0003\u001fB\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\rC\u0012B\u0001*O\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0005[\u0006Lg\u000e\u0006\u0002V1B\u0011qCV\u0005\u0003/b\u0011A!\u00168ji\")\u0011,\u0002a\u00015\u0006!\u0011M]4t!\r9re\u0017\t\u00039\u000et!!X1\u0011\u0005yCR\"A0\u000b\u0005\u0001\f\u0012A\u0002\u001fs_>$h(\u0003\u0002c1\u00051\u0001K]3eK\u001aL!\u0001Z3\u0003\rM#(/\u001b8h\u0015\t\u0011\u0007\u0004K\u0002\u0006\u0001\u001aC3!\u0001!GQ\r\u0001\u0001I\u0012"
)
public final class KMeansDataGenerator {
   public static void main(final String[] args) {
      KMeansDataGenerator$.MODULE$.main(args);
   }

   public static int generateKMeansRDD$default$6() {
      return KMeansDataGenerator$.MODULE$.generateKMeansRDD$default$6();
   }

   public static RDD generateKMeansRDD(final SparkContext sc, final int numPoints, final int k, final int d, final double r, final int numPartitions) {
      return KMeansDataGenerator$.MODULE$.generateKMeansRDD(sc, numPoints, k, d, r, numPartitions);
   }
}
