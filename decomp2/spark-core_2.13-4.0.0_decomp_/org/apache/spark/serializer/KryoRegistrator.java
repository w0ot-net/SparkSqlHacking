package org.apache.spark.serializer;

import com.esotericsoftware.kryo.Kryo;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005)2qAA\u0002\u0011\u0002G\u0005A\u0002C\u0003\u0014\u0001\u0019\u0005ACA\bLef|'+Z4jgR\u0014\u0018\r^8s\u0015\t!Q!\u0001\u0006tKJL\u0017\r\\5{KJT!AB\u0004\u0002\u000bM\u0004\u0018M]6\u000b\u0005!I\u0011AB1qC\u000eDWMC\u0001\u000b\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0002\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VMZ\u0001\u0010e\u0016<\u0017n\u001d;fe\u000ec\u0017m]:fgR\u0011Q\u0003\u0007\t\u0003\u001dYI!aF\b\u0003\tUs\u0017\u000e\u001e\u0005\u00063\u0005\u0001\rAG\u0001\u0005WJLx\u000e\u0005\u0002\u001cC5\tAD\u0003\u0002\u001a;)\u0011adH\u0001\u0011KN|G/\u001a:jGN|g\r^<be\u0016T\u0011\u0001I\u0001\u0004G>l\u0017B\u0001\u0012\u001d\u0005\u0011Y%/_8)\u0005\u0001!\u0003CA\u0013)\u001b\u00051#BA\u0014\u0006\u0003)\tgN\\8uCRLwN\\\u0005\u0003S\u0019\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public interface KryoRegistrator {
   void registerClasses(final Kryo kryo);
}
