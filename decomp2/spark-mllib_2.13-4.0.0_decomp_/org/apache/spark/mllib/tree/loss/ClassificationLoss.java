package org.apache.spark.mllib.tree.loss;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2\u0001BA\u0002\u0011\u0002G\u0005\u0011b\u0004\u0005\u00075\u00011\t!C\u000e\u0003%\rc\u0017m]:jM&\u001c\u0017\r^5p]2{7o\u001d\u0006\u0003\t\u0015\tA\u0001\\8tg*\u0011aaB\u0001\u0005iJ,WM\u0003\u0002\t\u0013\u0005)Q\u000e\u001c7jE*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xmE\u0002\u0001!Y\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0007CA\f\u0019\u001b\u0005\u0019\u0011BA\r\u0004\u0005\u0011aun]:\u0002%\r|W\u000e];uKB\u0013xNY1cS2LG/\u001f\u000b\u00039}\u0001\"!E\u000f\n\u0005y\u0011\"A\u0002#pk\ndW\rC\u0003!\u0003\u0001\u0007A$\u0001\u0004nCJ<\u0017N\\\u0002\u0001\u0001"
)
public interface ClassificationLoss extends Loss {
   double computeProbability(final double margin);
}
