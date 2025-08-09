package org.apache.spark.ml.ann;

import java.io.Serializable;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-2\u0001b\u0001\u0003\u0011\u0002G\u0005AA\u0004\u0005\u0006E\u00011\ta\t\u0005\u0006U\u00011\ta\t\u0002\u0013\u0003\u000e$\u0018N^1uS>tg)\u001e8di&|gN\u0003\u0002\u0006\r\u0005\u0019\u0011M\u001c8\u000b\u0005\u001dA\u0011AA7m\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<7c\u0001\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u0004\"AF\u0010\u000f\u0005]ibB\u0001\r\u001d\u001b\u0005I\"B\u0001\u000e\u001c\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\n\n\u0005y\t\u0012a\u00029bG.\fw-Z\u0005\u0003A\u0005\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!AH\t\u0002\t\u00154\u0018\r\\\u000b\u0002IA!\u0001#J\u0014(\u0013\t1\u0013CA\u0005Gk:\u001cG/[8ocA\u0011\u0001\u0003K\u0005\u0003SE\u0011a\u0001R8vE2,\u0017A\u00033fe&4\u0018\r^5wK\u0002"
)
public interface ActivationFunction extends Serializable {
   Function1 eval();

   Function1 derivative();
}
