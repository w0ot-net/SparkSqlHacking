package org.apache.spark.mllib.tree.model;

import scala.Enumeration;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y2Q\u0001B\u0003\u0001\u000fEA\u0011B\u0006\u0001\u0003\u0002\u0003\u0006I\u0001\u0007\u0010\t\u0013}\u0001!\u0011!Q\u0001\n\u0001B\u0004\"B\u001d\u0001\t\u0003Q$A\u0004#v[6L\b*[4i'Bd\u0017\u000e\u001e\u0006\u0003\r\u001d\tQ!\\8eK2T!\u0001C\u0005\u0002\tQ\u0014X-\u001a\u0006\u0003\u0015-\tQ!\u001c7mS\nT!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0003\u0001I\u0001\"a\u0005\u000b\u000e\u0003\u0015I!!F\u0003\u0003\u000bM\u0003H.\u001b;\u0002\u000f\u0019,\u0017\r^;sK\u000e\u0001\u0001CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"aA%oi&\u0011a\u0003F\u0001\fM\u0016\fG/\u001e:f)f\u0004X\r\u0005\u0002\"k9\u0011!E\r\b\u0003GAr!\u0001J\u0018\u000f\u0005\u0015rcB\u0001\u0014.\u001d\t9CF\u0004\u0002)W5\t\u0011F\u0003\u0002+/\u00051AH]8pizJ\u0011\u0001E\u0005\u0003\u001d=I!\u0001D\u0007\n\u0005)Y\u0011B\u0001\u0005\n\u0013\t\tt!A\u0007d_:4\u0017nZ;sCRLwN\\\u0005\u0003gQ\n1BR3biV\u0014X\rV=qK*\u0011\u0011gB\u0005\u0003m]\u00121BR3biV\u0014X\rV=qK*\u00111\u0007N\u0005\u0003?Q\ta\u0001P5oSRtDcA\u001e={A\u00111\u0003\u0001\u0005\u0006-\r\u0001\r\u0001\u0007\u0005\u0006?\r\u0001\r\u0001\t"
)
public class DummyHighSplit extends Split {
   public DummyHighSplit(final int feature, final Enumeration.Value featureType) {
      super(feature, Double.MAX_VALUE, featureType, .MODULE$);
   }
}
