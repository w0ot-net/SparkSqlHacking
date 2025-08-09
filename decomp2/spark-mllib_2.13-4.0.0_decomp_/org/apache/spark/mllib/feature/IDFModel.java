package org.apache.spark.mllib.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ec\u0001\u0002\n\u0014\u0001yA\u0001\"\r\u0001\u0003\u0006\u0004%\tA\r\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005g!AA\t\u0001BC\u0002\u0013\u0005Q\t\u0003\u0005P\u0001\t\u0005\t\u0015!\u0003G\u0011!\t\u0006A!b\u0001\n\u0003\u0011\u0006\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011B%\t\rY\u0003A\u0011A\fX\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u0015\u0001\u0007\u0001\"\u0001l\u0011\u0015\u0001\u0007\u0001\"\u0001r\u000f\u0019i8\u0003#\u0001\u0018}\u001a1!c\u0005E\u0001/}DaA\u0016\u0007\u0005\u0002\u00055\u0001B\u00021\r\t\u0003\ty\u0001\u0003\u0005\u0002\u00161!\taFA\f\u0011!\t9\u0003\u0004C\u0001/\u0005%\u0002\"CA!\u0019\u0005\u0005I\u0011BA\"\u0005!IEIR'pI\u0016d'B\u0001\u000b\u0016\u0003\u001d1W-\u0019;ve\u0016T!AF\f\u0002\u000b5dG.\u001b2\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c\u0001aE\u0002\u0001?\u0015\u0002\"\u0001I\u0012\u000e\u0003\u0005R\u0011AI\u0001\u0006g\u000e\fG.Y\u0005\u0003I\u0005\u0012a!\u00118z%\u00164\u0007C\u0001\u0014/\u001d\t9CF\u0004\u0002)W5\t\u0011F\u0003\u0002+;\u00051AH]8pizJ\u0011AI\u0005\u0003[\u0005\nq\u0001]1dW\u0006<W-\u0003\u00020a\ta1+\u001a:jC2L'0\u00192mK*\u0011Q&I\u0001\u0004S\u00124W#A\u001a\u0011\u0005Q:T\"A\u001b\u000b\u0005Y*\u0012A\u00027j]\u0006dw-\u0003\u00029k\t1a+Z2u_JD3!\u0001\u001eA!\tYd(D\u0001=\u0015\tit#\u0001\u0006b]:|G/\u0019;j_:L!a\u0010\u001f\u0003\u000bMKgnY3\"\u0003\u0005\u000bQ!\r\u00182]A\nA!\u001b3gA!\u001a!A\u000f!\u0002\u000f\u0011|7M\u0012:fcV\ta\tE\u0002!\u000f&K!\u0001S\u0011\u0003\u000b\u0005\u0013(/Y=\u0011\u0005\u0001R\u0015BA&\"\u0005\u0011auN\\4)\u0007\rQT*I\u0001O\u0003\u0015\u0019d\u0006\r\u00181\u0003!!wn\u0019$sKF\u0004\u0003f\u0001\u0003;\u001b\u00069a.^7E_\u000e\u001cX#A%)\u0007\u0015QT*\u0001\u0005ok6$unY:!Q\r1!(T\u0001\u0007y%t\u0017\u000e\u001e \u0015\taSFL\u0018\t\u00033\u0002i\u0011a\u0005\u0005\u0006c\u001d\u0001\ra\r\u0015\u00045j\u0002\u0005\"\u0002#\b\u0001\u00041\u0005f\u0001/;\u001b\")\u0011k\u0002a\u0001\u0013\"\u001aaLO'\u0002\u0013Q\u0014\u0018M\\:g_JlGC\u00012i!\r\u0019gmM\u0007\u0002I*\u0011QmF\u0001\u0004e\u0012$\u0017BA4e\u0005\r\u0011F\t\u0012\u0005\u0006S\"\u0001\rAY\u0001\bI\u0006$\u0018m]3uQ\rA!\b\u0011\u000b\u0003g1DQ!\\\u0005A\u0002M\n\u0011A\u001e\u0015\u0004\u0013iz\u0017%\u00019\u0002\u000bEr3G\f\u0019\u0015\u0005IT\bcA:yg5\tAO\u0003\u0002vm\u0006!!.\u0019<b\u0015\t9x#A\u0002ba&L!!\u001f;\u0003\u000f)\u000bg/\u0019*E\t\")\u0011N\u0003a\u0001e\"\u001a!B\u000f!)\u0007\u0001Q\u0004)\u0001\u0005J\t\u001aku\u000eZ3m!\tIFb\u0005\u0003\r?\u0005\u0005\u0001\u0003BA\u0002\u0003\u0017i!!!\u0002\u000b\t\u0005\u001d\u0011\u0011B\u0001\u0003S>T\u0011!^\u0005\u0004_\u0005\u0015A#\u0001@\u0015\u000bM\n\t\"a\u0005\t\u000bEr\u0001\u0019A\u001a\t\u000b5t\u0001\u0019A\u001a\u0002\u001dQ\u0014\u0018M\\:g_JlG)\u001a8tKR1\u0011\u0011DA\u0011\u0003G\u0001B\u0001I$\u0002\u001cA\u0019\u0001%!\b\n\u0007\u0005}\u0011E\u0001\u0004E_V\u0014G.\u001a\u0005\u0006c=\u0001\ra\r\u0005\b\u0003Ky\u0001\u0019AA\r\u0003\u00191\u0018\r\\;fg\u0006yAO]1og\u001a|'/\\*qCJ\u001cX\r\u0006\u0005\u0002,\u0005e\u00121HA !\u001d\u0001\u0013QFA\u0019\u00033I1!a\f\"\u0005\u0019!V\u000f\u001d7feA!\u0001eRA\u001a!\r\u0001\u0013QG\u0005\u0004\u0003o\t#aA%oi\")\u0011\u0007\u0005a\u0001g!9\u0011Q\b\tA\u0002\u0005E\u0012aB5oI&\u001cWm\u001d\u0005\b\u0003K\u0001\u0002\u0019AA\r\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0005\u0005\u0003\u0002H\u00055SBAA%\u0015\u0011\tY%!\u0003\u0002\t1\fgnZ\u0005\u0005\u0003\u001f\nIE\u0001\u0004PE*,7\r\u001e"
)
public class IDFModel implements Serializable {
   private final Vector idf;
   private final long[] docFreq;
   private final long numDocs;

   public Vector idf() {
      return this.idf;
   }

   public long[] docFreq() {
      return this.docFreq;
   }

   public long numDocs() {
      return this.numDocs;
   }

   public RDD transform(final RDD dataset) {
      Broadcast bcIdf = dataset.context().broadcast(this.idf(), .MODULE$.apply(Vector.class));
      return dataset.mapPartitions((iter) -> iter.map((v) -> IDFModel$.MODULE$.transform((Vector)bcIdf.value(), v)), dataset.mapPartitions$default$2(), .MODULE$.apply(Vector.class));
   }

   public Vector transform(final Vector v) {
      return IDFModel$.MODULE$.transform(this.idf(), v);
   }

   public JavaRDD transform(final JavaRDD dataset) {
      return this.transform(dataset.rdd()).toJavaRDD();
   }

   public IDFModel(final Vector idf, final long[] docFreq, final long numDocs) {
      this.idf = idf;
      this.docFreq = docFreq;
      this.numDocs = numDocs;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
