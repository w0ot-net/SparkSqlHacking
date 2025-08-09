package org.apache.spark.api.python;

import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:a\u0001B\u0003\t\u0002%yaAB\t\u0006\u0011\u0003I!\u0003C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0003#\u0003\u0011\u00051%A\nQsRDwN\\$bi\u0016<\u0018-_*feZ,'O\u0003\u0002\u0007\u000f\u00051\u0001/\u001f;i_:T!\u0001C\u0005\u0002\u0007\u0005\u0004\u0018N\u0003\u0002\u000b\u0017\u0005)1\u000f]1sW*\u0011A\"D\u0001\u0007CB\f7\r[3\u000b\u00039\t1a\u001c:h!\t\u0001\u0012!D\u0001\u0006\u0005M\u0001\u0016\u0010\u001e5p]\u001e\u000bG/Z<bsN+'O^3s'\r\t1#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iiR\"A\u000e\u000b\u0005qI\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005yY\"a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq\"\u0001\u0003nC&tGC\u0001\u0013(!\t!R%\u0003\u0002'+\t!QK\\5u\u0011\u0015A3\u00011\u0001*\u0003\u0011\t'oZ:\u0011\u0007QQC&\u0003\u0002,+\t)\u0011I\u001d:bsB\u0011Q\u0006\u000e\b\u0003]I\u0002\"aL\u000b\u000e\u0003AR!!\r\u0011\u0002\rq\u0012xn\u001c;?\u0013\t\u0019T#\u0001\u0004Qe\u0016$WMZ\u0005\u0003kY\u0012aa\u0015;sS:<'BA\u001a\u0016\u0001"
)
public final class PythonGatewayServer {
   public static void main(final String[] args) {
      PythonGatewayServer$.MODULE$.main(args);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return PythonGatewayServer$.MODULE$.LogStringContext(sc);
   }
}
