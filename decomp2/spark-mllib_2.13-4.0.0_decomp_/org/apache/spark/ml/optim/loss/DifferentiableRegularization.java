package org.apache.spark.ml.optim.loss;

import breeze.optimize.DiffFunction;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A2\u0001BA\u0002\u0011\u0002G\u0005qa\u0004\u0005\u0006W\u00011\t\u0001\f\u0002\u001d\t&4g-\u001a:f]RL\u0017M\u00197f%\u0016<W\u000f\\1sSj\fG/[8o\u0015\t!Q!\u0001\u0003m_N\u001c(B\u0001\u0004\b\u0003\u0015y\u0007\u000f^5n\u0015\tA\u0011\"\u0001\u0002nY*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014x-\u0006\u0002\u0011CM\u0019\u0001!E\f\u0011\u0005I)R\"A\n\u000b\u0003Q\tQa]2bY\u0006L!AF\n\u0003\r\u0005s\u0017PU3g!\rARdH\u0007\u00023)\u0011!dG\u0001\t_B$\u0018.\\5{K*\tA$\u0001\u0004ce\u0016,'0Z\u0005\u0003=e\u0011A\u0002R5gM\u001a+hn\u0019;j_:\u0004\"\u0001I\u0011\r\u0001\u0011)!\u0005\u0001b\u0001I\t\tAk\u0001\u0001\u0012\u0005\u0015B\u0003C\u0001\n'\u0013\t93CA\u0004O_RD\u0017N\\4\u0011\u0005II\u0013B\u0001\u0016\u0014\u0005\r\te._\u0001\te\u0016<\u0007+\u0019:b[V\tQ\u0006\u0005\u0002\u0013]%\u0011qf\u0005\u0002\u0007\t>,(\r\\3"
)
public interface DifferentiableRegularization extends DiffFunction {
   double regParam();
}
