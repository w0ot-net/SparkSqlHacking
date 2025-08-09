package org.apache.spark.mllib.linalg.distributed;

import breeze.linalg.DenseMatrix;
import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t3q\u0001B\u0003\u0011\u0002G\u0005!\u0003C\u0003&\u0001\u0019\u0005a\u0005C\u00034\u0001\u0019\u0005a\u0005\u0003\u00046\u0001\u0019\u0005\u0011B\u000e\u0002\u0012\t&\u001cHO]5ckR,G-T1ue&D(B\u0001\u0004\b\u0003-!\u0017n\u001d;sS\n,H/\u001a3\u000b\u0005!I\u0011A\u00027j]\u0006dwM\u0003\u0002\u000b\u0017\u0005)Q\u000e\u001c7jE*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035\tr!a\u0007\u0011\u000f\u0005qyR\"A\u000f\u000b\u0005y\t\u0012A\u0002\u001fs_>$h(C\u0001\u0017\u0013\t\tS#A\u0004qC\u000e\\\u0017mZ3\n\u0005\r\"#\u0001D*fe&\fG.\u001b>bE2,'BA\u0011\u0016\u0003\u001dqW/\u001c*poN$\u0012a\n\t\u0003)!J!!K\u000b\u0003\t1{gn\u001a\u0015\u0004\u0003-\n\u0004C\u0001\u00170\u001b\u0005i#B\u0001\u0018\f\u0003)\tgN\\8uCRLwN\\\u0005\u0003a5\u0012QaU5oG\u0016\f\u0013AM\u0001\u0006c9\u0002d\u0006M\u0001\b]Vl7i\u001c7tQ\r\u00111&M\u0001\ti>\u0014%/Z3{KR\tq\u0007E\u00029yyj\u0011!\u000f\u0006\u0003\u0011iR\u0011aO\u0001\u0007EJ,WM_3\n\u0005uJ$a\u0003#f]N,W*\u0019;sSb\u0004\"\u0001F \n\u0005\u0001+\"A\u0002#pk\ndW\rK\u0002\u0001WE\u0002"
)
public interface DistributedMatrix extends Serializable {
   long numRows();

   long numCols();

   DenseMatrix toBreeze();
}
