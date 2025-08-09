package org.apache.spark.ml.optim;

import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.mllib.linalg.CholeskyDecomposition$;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2Qa\u0001\u0003\u0001\t9AQ!\u0007\u0001\u0005\u0002mAQ!\b\u0001\u0005By\u0011ab\u00115pY\u0016\u001c8._*pYZ,'O\u0003\u0002\u0006\r\u0005)q\u000e\u001d;j[*\u0011q\u0001C\u0001\u0003[2T!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\u0017/5\tA!\u0003\u0002\u0019\t\t!bj\u001c:nC2,\u0015/^1uS>t7k\u001c7wKJ\fa\u0001P5oSRt4\u0001\u0001\u000b\u00029A\u0011a\u0003A\u0001\u0006g>dg/\u001a\u000b\u0007?\t:\u0013&M\u001a\u0011\u0005Y\u0001\u0013BA\u0011\u0005\u0005YquN]7bY\u0016\u000bX/\u0019;j_:\u001cv\u000e\\;uS>t\u0007\"B\u0012\u0003\u0001\u0004!\u0013\u0001\u00022CCJ\u0004\"\u0001E\u0013\n\u0005\u0019\n\"A\u0002#pk\ndW\rC\u0003)\u0005\u0001\u0007A%A\u0003cE\n\u000b'\u000fC\u0003+\u0005\u0001\u00071&A\u0003bE\n\u000b'\u000f\u0005\u0002-_5\tQF\u0003\u0002/\r\u00051A.\u001b8bY\u001eL!\u0001M\u0017\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\u0005\u0006e\t\u0001\raK\u0001\u0006C\u0006\u0014\u0015M\u001d\u0005\u0006i\t\u0001\raK\u0001\u0005C\n\u000b'\u000f"
)
public class CholeskySolver implements NormalEquationSolver {
   public NormalEquationSolution solve(final double bBar, final double bbBar, final DenseVector abBar, final DenseVector aaBar, final DenseVector aBar) {
      int k = abBar.size();
      double[] x = CholeskyDecomposition$.MODULE$.solve(aaBar.values(), abBar.values());
      double[] aaInv = CholeskyDecomposition$.MODULE$.inverse(aaBar.values(), k);
      return new NormalEquationSolution(x, new Some(aaInv), .MODULE$);
   }
}
