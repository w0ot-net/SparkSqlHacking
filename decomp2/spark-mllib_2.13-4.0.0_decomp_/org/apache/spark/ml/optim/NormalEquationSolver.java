package org.apache.spark.ml.optim;

import org.apache.spark.ml.linalg.DenseVector;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2\u0001BA\u0002\u0011\u0002G\u00052!\u0004\u0005\u0006)\u00011\tA\u0006\u0002\u0015\u001d>\u0014X.\u00197FcV\fG/[8o'>dg/\u001a:\u000b\u0005\u0011)\u0011!B8qi&l'B\u0001\u0004\b\u0003\tiGN\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h'\t\u0001a\u0002\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VMZ\u0001\u0006g>dg/Z\u0002\u0001)\u001992\u0004\t\u0012+YA\u0011\u0001$G\u0007\u0002\u0007%\u0011!d\u0001\u0002\u0017\u001d>\u0014X.\u00197FcV\fG/[8o'>dW\u000f^5p]\")A$\u0001a\u0001;\u0005!!MQ1s!\tya$\u0003\u0002 !\t1Ai\\;cY\u0016DQ!I\u0001A\u0002u\tQA\u00192CCJDQaI\u0001A\u0002\u0011\nQ!\u00192CCJ\u0004\"!\n\u0015\u000e\u0003\u0019R!aJ\u0003\u0002\r1Lg.\u00197h\u0013\tIcEA\u0006EK:\u001cXMV3di>\u0014\b\"B\u0016\u0002\u0001\u0004!\u0013!B1b\u0005\u0006\u0014\b\"B\u0017\u0002\u0001\u0004!\u0013\u0001B1CCJL3\u0001A\u00182\u0013\t\u00014A\u0001\bDQ>dWm]6z'>dg/\u001a:\n\u0005I\u001a!!E)vCNLg*Z<u_:\u001cv\u000e\u001c<fe\u0002"
)
public interface NormalEquationSolver {
   NormalEquationSolution solve(final double bBar, final double bbBar, final DenseVector abBar, final DenseVector aaBar, final DenseVector aBar);
}
