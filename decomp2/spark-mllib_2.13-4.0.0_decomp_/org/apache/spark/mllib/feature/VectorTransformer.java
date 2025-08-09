package org.apache.spark.mllib.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005I3q!\u0002\u0004\u0011\u0002\u0007\u0005\u0011\u0003C\u0003%\u0001\u0011\u0005Q\u0005C\u0003*\u0001\u0019\u0005!\u0006C\u0003*\u0001\u0011\u0005A\bC\u0003*\u0001\u0011\u0005aIA\tWK\u000e$xN\u001d+sC:\u001chm\u001c:nKJT!a\u0002\u0005\u0002\u000f\u0019,\u0017\r^;sK*\u0011\u0011BC\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sO\u000e\u00011c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!G\u0011\u000f\u0005iybBA\u000e\u001f\u001b\u0005a\"BA\u000f\u0011\u0003\u0019a$o\\8u}%\tQ#\u0003\u0002!)\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0012$\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0001C#\u0001\u0004%S:LG\u000f\n\u000b\u0002MA\u00111cJ\u0005\u0003QQ\u0011A!\u00168ji\u0006IAO]1og\u001a|'/\u001c\u000b\u0003WE\u0002\"\u0001L\u0018\u000e\u00035R!A\f\u0005\u0002\r1Lg.\u00197h\u0013\t\u0001TF\u0001\u0004WK\u000e$xN\u001d\u0005\u0006e\t\u0001\raK\u0001\u0007m\u0016\u001cGo\u001c:)\u0007\t!$\b\u0005\u00026q5\taG\u0003\u00028\u0015\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005e2$!B*j]\u000e,\u0017%A\u001e\u0002\u000bEr\u0013G\f\u0019\u0015\u0005u\u001a\u0005c\u0001 BW5\tqH\u0003\u0002A\u0015\u0005\u0019!\u000f\u001a3\n\u0005\t{$a\u0001*E\t\")Ai\u0001a\u0001{\u0005!A-\u0019;bQ\r\u0019AG\u000f\u000b\u0003\u000f>\u00032\u0001S',\u001b\u0005I%B\u0001&L\u0003\u0011Q\u0017M^1\u000b\u00051S\u0011aA1qS&\u0011a*\u0013\u0002\b\u0015\u00064\u0018M\u0015#E\u0011\u0015!E\u00011\u0001HQ\r!AG\u000f\u0015\u0004\u0001QR\u0004"
)
public interface VectorTransformer extends Serializable {
   Vector transform(final Vector vector);

   // $FF: synthetic method
   static RDD transform$(final VectorTransformer $this, final RDD data) {
      return $this.transform(data);
   }

   default RDD transform(final RDD data) {
      return data.map((x) -> this.transform(x), .MODULE$.apply(Vector.class));
   }

   // $FF: synthetic method
   static JavaRDD transform$(final VectorTransformer $this, final JavaRDD data) {
      return $this.transform(data);
   }

   default JavaRDD transform(final JavaRDD data) {
      return org.apache.spark.api.java.JavaRDD..MODULE$.fromRDD(this.transform(data.rdd()), .MODULE$.apply(Vector.class));
   }

   static void $init$(final VectorTransformer $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
