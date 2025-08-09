package org.apache.spark.mllib.optimization;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053Qa\u0001\u0003\u0002\u0002=AQA\t\u0001\u0005\u0002\rBQA\n\u0001\u0007\u0002\u001d\u0012q!\u00169eCR,'O\u0003\u0002\u0006\r\u0005aq\u000e\u001d;j[&T\u0018\r^5p]*\u0011q\u0001C\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u0013)\tQa\u001d9be.T!a\u0003\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0011aA8sO\u000e\u00011c\u0001\u0001\u0011-A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\u0004\"aF\u0010\u000f\u0005aibBA\r\u001d\u001b\u0005Q\"BA\u000e\u000f\u0003\u0019a$o\\8u}%\t1#\u0003\u0002\u001f%\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0011\"\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tq\"#\u0001\u0004=S:LGO\u0010\u000b\u0002IA\u0011Q\u0005A\u0007\u0002\t\u000591m\\7qkR,GC\u0002\u00155maRt\b\u0005\u0003\u0012S-\n\u0014B\u0001\u0016\u0013\u0005\u0019!V\u000f\u001d7feA\u0011AfL\u0007\u0002[)\u0011aFB\u0001\u0007Y&t\u0017\r\\4\n\u0005Aj#A\u0002,fGR|'\u000f\u0005\u0002\u0012e%\u00111G\u0005\u0002\u0007\t>,(\r\\3\t\u000bU\u0012\u0001\u0019A\u0016\u0002\u0015],\u0017n\u001a5ug>cG\rC\u00038\u0005\u0001\u00071&\u0001\u0005he\u0006$\u0017.\u001a8u\u0011\u0015I$\u00011\u00012\u0003!\u0019H/\u001a9TSj,\u0007\"B\u001e\u0003\u0001\u0004a\u0014\u0001B5uKJ\u0004\"!E\u001f\n\u0005y\u0012\"aA%oi\")\u0001I\u0001a\u0001c\u0005A!/Z4QCJ\fW\u000e"
)
public abstract class Updater implements Serializable {
   public abstract Tuple2 compute(final Vector weightsOld, final Vector gradient, final double stepSize, final int iter, final double regParam);
}
