package org.apache.spark.security;

import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00152qAA\u0002\u0011\u0002G\u0005A\u0002C\u0003\u0014\u0001\u0019\u0005ACA\u000eHe>,\b/T1qa&twmU3sm&\u001cW\r\u0015:pm&$WM\u001d\u0006\u0003\t\u0015\t\u0001b]3dkJLG/\u001f\u0006\u0003\r\u001d\tQa\u001d9be.T!\u0001C\u0005\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0011aA8sO\u000e\u00011C\u0001\u0001\u000e!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fM\u0006Iq-\u001a;He>,\bo\u001d\u000b\u0003+\r\u00022AF\u000f!\u001d\t92\u0004\u0005\u0002\u0019\u001f5\t\u0011D\u0003\u0002\u001b\u0017\u00051AH]8pizJ!\u0001H\b\u0002\rA\u0013X\rZ3g\u0013\tqrDA\u0002TKRT!\u0001H\b\u0011\u0005Y\t\u0013B\u0001\u0012 \u0005\u0019\u0019FO]5oO\")A%\u0001a\u0001A\u0005AQo]3s\u001d\u0006lW\r"
)
public interface GroupMappingServiceProvider {
   Set getGroups(final String userName);
}
