package org.apache.spark.mllib.tree.impurity;

import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00113qa\u0001\u0003\u0011\u0002G\u0005\u0011\u0003C\u0003%\u0001\u0019\u0005Q\u0005C\u0003%\u0001\u0019\u0005\u0011H\u0001\u0005J[B,(/\u001b;z\u0015\t)a!\u0001\u0005j[B,(/\u001b;z\u0015\t9\u0001\"\u0001\u0003ue\u0016,'BA\u0005\u000b\u0003\u0015iG\u000e\\5c\u0015\tYA\"A\u0003ta\u0006\u00148N\u0003\u0002\u000e\u001d\u00051\u0011\r]1dQ\u0016T\u0011aD\u0001\u0004_J<7\u0001A\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001aC9\u0011!d\b\b\u00037yi\u0011\u0001\b\u0006\u0003;A\ta\u0001\u0010:p_Rt\u0014\"A\u000b\n\u0005\u0001\"\u0012a\u00029bG.\fw-Z\u0005\u0003E\r\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\t\u000b\u0002\u0013\r\fGnY;mCR,Gc\u0001\u0014*]A\u00111cJ\u0005\u0003QQ\u0011a\u0001R8vE2,\u0007\"\u0002\u0016\u0002\u0001\u0004Y\u0013AB2pk:$8\u000fE\u0002\u0014Y\u0019J!!\f\u000b\u0003\u000b\u0005\u0013(/Y=\t\u000b=\n\u0001\u0019\u0001\u0014\u0002\u0015Q|G/\u00197D_VtG\u000fK\u0002\u0002c]\u0002\"AM\u001b\u000e\u0003MR!\u0001\u000e\u0006\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u00027g\t)1+\u001b8dK\u0006\n\u0001(A\u00032]Er\u0003\u0007\u0006\u0003'uqr\u0004\"B\u001e\u0003\u0001\u00041\u0013!B2pk:$\b\"B\u001f\u0003\u0001\u00041\u0013aA:v[\")qH\u0001a\u0001M\u0005Q1/^7TcV\f'/Z:)\u0007\t\t\u0014)I\u0001C\u0003\u0015\td\u0006\r\u00181Q\r\u0001\u0011'\u0011"
)
public interface Impurity extends Serializable {
   double calculate(final double[] counts, final double totalCount);

   double calculate(final double count, final double sum, final double sumSquares);
}
