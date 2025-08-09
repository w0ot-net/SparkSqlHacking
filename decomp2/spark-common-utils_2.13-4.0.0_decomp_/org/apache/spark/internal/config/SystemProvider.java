package org.apache.spark.internal.config;

import scala.Option;
import scala.reflect.ScalaSignature;
import scala.sys.package.;

@ScalaSignature(
   bytes = "\u0006\u0005=2Qa\u0001\u0003\u0001\u00119AQ!\u0007\u0001\u0005\u0002mAQ!\b\u0001\u0005By\u0011abU=ti\u0016l\u0007K]8wS\u0012,'O\u0003\u0002\u0006\r\u000511m\u001c8gS\u001eT!a\u0002\u0005\u0002\u0011%tG/\u001a:oC2T!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\n\u0004\u0001=)\u0002C\u0001\t\u0014\u001b\u0005\t\"\"\u0001\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005Q\t\"AB!osJ+g\r\u0005\u0002\u0017/5\tA!\u0003\u0002\u0019\t\tq1i\u001c8gS\u001e\u0004&o\u001c<jI\u0016\u0014\u0018A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003q\u0001\"A\u0006\u0001\u0002\u0007\u001d,G\u000f\u0006\u0002 [A\u0019\u0001\u0003\t\u0012\n\u0005\u0005\n\"AB(qi&|g\u000e\u0005\u0002$U9\u0011A\u0005\u000b\t\u0003KEi\u0011A\n\u0006\u0003Oi\ta\u0001\u0010:p_Rt\u0014BA\u0015\u0012\u0003\u0019\u0001&/\u001a3fM&\u00111\u0006\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005%\n\u0002\"\u0002\u0018\u0003\u0001\u0004\u0011\u0013aA6fs\u0002"
)
public class SystemProvider implements ConfigProvider {
   public Option get(final String key) {
      return .MODULE$.props().get(key);
   }
}
