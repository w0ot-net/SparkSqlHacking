package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.databind.util.LookupCache;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2qAA\u0002\u0011\u0002G\u0005a\u0002C\u0003\u0015\u0001\u0019\u0005QC\u0001\nM_>\\W\u000f]\"bG\",g)Y2u_JL(B\u0001\u0003\u0006\u0003\u0015\u00198-\u00197b\u0015\t1q!\u0001\u0004n_\u0012,H.\u001a\u0006\u0003\u0011%\tqA[1dWN|gN\u0003\u0002\u000b\u0017\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002\u0019\u0005\u00191m\\7\u0004\u0001M\u0011\u0001a\u0004\t\u0003!Ii\u0011!\u0005\u0006\u0002\t%\u00111#\u0005\u0002\u0007\u0003:L(+\u001a4\u0002#\r\u0014X-\u0019;f\u0019>|7.\u001e9DC\u000eDW-F\u0002\u0017C-\"2aF\u00173!\u0011ARd\b\u0016\u000e\u0003eQ!AG\u000e\u0002\tU$\u0018\u000e\u001c\u0006\u00039\u001d\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003=e\u00111\u0002T8pWV\u00048)Y2iKB\u0011\u0001%\t\u0007\u0001\t\u0015\u0011\u0013A1\u0001$\u0005\u0005Y\u0015C\u0001\u0013(!\t\u0001R%\u0003\u0002'#\t9aj\u001c;iS:<\u0007C\u0001\t)\u0013\tI\u0013CA\u0002B]f\u0004\"\u0001I\u0016\u0005\u000b1\n!\u0019A\u0012\u0003\u0003YCQAL\u0001A\u0002=\na\"\u001b8ji&\fG.\u00128ue&,7\u000f\u0005\u0002\u0011a%\u0011\u0011'\u0005\u0002\u0004\u0013:$\b\"B\u001a\u0002\u0001\u0004y\u0013AC7bq\u0016sGO]5fg\u0002"
)
public interface LookupCacheFactory {
   LookupCache createLookupCache(final int initialEntries, final int maxEntries);
}
