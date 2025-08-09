package org.apache.spark.internal.config;

import java.util.Map;
import scala.Option;
import scala.Option.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i2Q\u0001B\u0003\u0001\u0013=A\u0001B\u0007\u0001\u0003\u0002\u0003\u0006I\u0001\b\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006g\u0001!\t\u0005\u000e\u0002\f\u001b\u0006\u0004\bK]8wS\u0012,'O\u0003\u0002\u0007\u000f\u000511m\u001c8gS\u001eT!\u0001C\u0005\u0002\u0011%tG/\u001a:oC2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0004\u0001A1\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\r\u0005\u0002\u001815\tQ!\u0003\u0002\u001a\u000b\tq1i\u001c8gS\u001e\u0004&o\u001c<jI\u0016\u0014\u0018\u0001B2p]\u001a\u001c\u0001\u0001\u0005\u0003\u001eE\u0011\"S\"\u0001\u0010\u000b\u0005}\u0001\u0013\u0001B;uS2T\u0011!I\u0001\u0005U\u00064\u0018-\u0003\u0002$=\t\u0019Q*\u00199\u0011\u0005\u0015bcB\u0001\u0014+!\t9##D\u0001)\u0015\tI3$\u0001\u0004=e>|GOP\u0005\u0003WI\ta\u0001\u0015:fI\u00164\u0017BA\u0017/\u0005\u0019\u0019FO]5oO*\u00111FE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005E\u0012\u0004CA\f\u0001\u0011\u0015Q\"\u00011\u0001\u001d\u0003\r9W\r\u001e\u000b\u0003ka\u00022!\u0005\u001c%\u0013\t9$C\u0001\u0004PaRLwN\u001c\u0005\u0006s\r\u0001\r\u0001J\u0001\u0004W\u0016L\b"
)
public class MapProvider implements ConfigProvider {
   private final Map conf;

   public Option get(final String key) {
      return .MODULE$.apply(this.conf.get(key));
   }

   public MapProvider(final Map conf) {
      this.conf = conf;
   }
}
