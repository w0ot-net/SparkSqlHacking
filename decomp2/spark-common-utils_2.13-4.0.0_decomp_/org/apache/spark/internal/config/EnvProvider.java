package org.apache.spark.internal.config;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005=2Qa\u0001\u0003\u0001\u00119AQ!\u0007\u0001\u0005\u0002mAQ!\b\u0001\u0005By\u00111\"\u00128w!J|g/\u001b3fe*\u0011QAB\u0001\u0007G>tg-[4\u000b\u0005\u001dA\u0011\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005%Q\u0011!B:qCJ\\'BA\u0006\r\u0003\u0019\t\u0007/Y2iK*\tQ\"A\u0002pe\u001e\u001c2\u0001A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0011acF\u0007\u0002\t%\u0011\u0001\u0004\u0002\u0002\u000f\u0007>tg-[4Qe>4\u0018\u000eZ3s\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u000f\u0011\u0005Y\u0001\u0011aA4fiR\u0011q$\f\t\u0004!\u0001\u0012\u0013BA\u0011\u0012\u0005\u0019y\u0005\u000f^5p]B\u00111E\u000b\b\u0003I!\u0002\"!J\t\u000e\u0003\u0019R!a\n\u000e\u0002\rq\u0012xn\u001c;?\u0013\tI\u0013#\u0001\u0004Qe\u0016$WMZ\u0005\u0003W1\u0012aa\u0015;sS:<'BA\u0015\u0012\u0011\u0015q#\u00011\u0001#\u0003\rYW-\u001f"
)
public class EnvProvider implements ConfigProvider {
   public Option get(final String key) {
      return .MODULE$.env().get(key);
   }
}
