package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.databind.util.LookupCache;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y:Q\u0001B\u0003\t\u0002A1QAE\u0003\t\u0002MAQ\u0001H\u0001\u0005\u0002uAQAH\u0001\u0005B}\t\u0011\u0004R3gCVdG\u000fT8pWV\u00048)Y2iK\u001a\u000b7\r^8ss*\u0011aaB\u0001\u0006g\u000e\fG.\u0019\u0006\u0003\u0011%\ta!\\8ek2,'B\u0001\u0006\f\u0003\u001dQ\u0017mY6t_:T!\u0001D\u0007\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\b\u0002\u0007\r|Wn\u0001\u0001\u0011\u0005E\tQ\"A\u0003\u00033\u0011+g-Y;mi2{wn[;q\u0007\u0006\u001c\u0007.\u001a$bGR|'/_\n\u0004\u0003QI\u0002CA\u000b\u0018\u001b\u00051\"\"\u0001\u0004\n\u0005a1\"AB!osJ+g\r\u0005\u0002\u00125%\u00111$\u0002\u0002\u0013\u0019>|7.\u001e9DC\u000eDWMR1di>\u0014\u00180\u0001\u0004=S:LGO\u0010\u000b\u0002!\u0005\t2M]3bi\u0016dun\\6va\u000e\u000b7\r[3\u0016\u0007\u0001ZS\u0007F\u0002\"oq\u0002BAI\u0014*i5\t1E\u0003\u0002%K\u0005!Q\u000f^5m\u0015\t1\u0013\"\u0001\u0005eCR\f'-\u001b8e\u0013\tA3EA\u0006M_>\\W\u000f]\"bG\",\u0007C\u0001\u0016,\u0019\u0001!Q\u0001L\u0002C\u00025\u0012\u0011aS\t\u0003]E\u0002\"!F\u0018\n\u0005A2\"a\u0002(pi\"Lgn\u001a\t\u0003+IJ!a\r\f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002+k\u0011)ag\u0001b\u0001[\t\ta\u000bC\u00039\u0007\u0001\u0007\u0011(\u0001\bj]&$\u0018.\u00197F]R\u0014\u0018.Z:\u0011\u0005UQ\u0014BA\u001e\u0017\u0005\rIe\u000e\u001e\u0005\u0006{\r\u0001\r!O\u0001\u000b[\u0006DXI\u001c;sS\u0016\u001c\b"
)
public final class DefaultLookupCacheFactory {
   public static LookupCache createLookupCache(final int initialEntries, final int maxEntries) {
      return DefaultLookupCacheFactory$.MODULE$.createLookupCache(initialEntries, maxEntries);
   }
}
