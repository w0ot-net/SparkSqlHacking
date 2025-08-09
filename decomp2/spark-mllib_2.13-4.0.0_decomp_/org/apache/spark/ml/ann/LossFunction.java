package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2\u0001BA\u0002\u0011\u0002G\u00051!\u0004\u0005\u0006)\u00011\tA\u0006\u0002\r\u0019>\u001c8OR;oGRLwN\u001c\u0006\u0003\t\u0015\t1!\u00198o\u0015\t1q!\u0001\u0002nY*\u0011\u0001\"C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0015-\ta!\u00199bG\",'\"\u0001\u0007\u0002\u0007=\u0014xm\u0005\u0002\u0001\u001dA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\fA\u0001\\8tg\u000e\u0001A\u0003B\f\u001bI\u0019\u0002\"a\u0004\r\n\u0005e\u0001\"A\u0002#pk\ndW\rC\u0003\u001c\u0003\u0001\u0007A$\u0001\u0004pkR\u0004X\u000f\u001e\t\u0004;\t:R\"\u0001\u0010\u000b\u0005}\u0001\u0013A\u00027j]\u0006dwMC\u0001\"\u0003\u0019\u0011'/Z3{K&\u00111E\b\u0002\f\t\u0016t7/Z'biJL\u0007\u0010C\u0003&\u0003\u0001\u0007A$\u0001\u0004uCJ<W\r\u001e\u0005\u0006O\u0005\u0001\r\u0001H\u0001\u0006I\u0016dG/\u0019"
)
public interface LossFunction {
   double loss(final DenseMatrix output, final DenseMatrix target, final DenseMatrix delta);
}
