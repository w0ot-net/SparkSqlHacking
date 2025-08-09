package org.json4s.reflect;

import java.util.concurrent.ConcurrentHashMap;
import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193QAB\u0004\u0001\u000f5AQ!\u0006\u0001\u0005\u0002]Aa\u0001\u000b\u0001!\u0002\u0013I\u0003\"B\u001a\u0001\t\u0003!\u0004\"\u0002\u001f\u0001\t\u0003i\u0004\"B!\u0001\t\u0003\u0011%\u0001B'f[>T!\u0001C\u0005\u0002\u000fI,g\r\\3di*\u0011!bC\u0001\u0007UN|g\u000eN:\u000b\u00031\t1a\u001c:h+\rqADJ\n\u0003\u0001=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003a\u0001B!\u0007\u0001\u001bK5\tq\u0001\u0005\u0002\u001c91\u0001A!B\u000f\u0001\u0005\u0004q\"!A!\u0012\u0005}\u0011\u0003C\u0001\t!\u0013\t\t\u0013CA\u0004O_RD\u0017N\\4\u0011\u0005A\u0019\u0013B\u0001\u0013\u0012\u0005\r\te.\u001f\t\u00037\u0019\"Qa\n\u0001C\u0002y\u0011\u0011AU\u0001\u0006G\u0006\u001c\u0007.\u001a\t\u0005UERR%D\u0001,\u0015\taS&\u0001\u0006d_:\u001cWO\u001d:f]RT!AL\u0018\u0002\tU$\u0018\u000e\u001c\u0006\u0002a\u0005!!.\u0019<b\u0013\t\u00114FA\tD_:\u001cWO\u001d:f]RD\u0015m\u001d5NCB\fQ!\u00199qYf$2!J\u001b8\u0011\u001514\u00011\u0001\u001b\u0003\u0005A\b\"\u0002\u001d\u0004\u0001\u0004I\u0014!\u00014\u0011\tAQ$$J\u0005\u0003wE\u0011\u0011BR;oGRLwN\\\u0019\u0002\u000fI,\u0007\u000f\\1dKR\u0019QEP \t\u000bY\"\u0001\u0019\u0001\u000e\t\u000b\u0001#\u0001\u0019A\u0013\u0002\u0003Y\fQa\u00197fCJ$\u0012a\u0011\t\u0003!\u0011K!!R\t\u0003\tUs\u0017\u000e\u001e"
)
public class Memo {
   private final ConcurrentHashMap cache = new ConcurrentHashMap(1500, 1.0F, 1);

   public Object apply(final Object x, final Function1 f) {
      Object var10000;
      if (this.cache.containsKey(x)) {
         var10000 = this.cache.get(x);
      } else {
         Object v = f.apply(x);
         var10000 = this.replace(x, v);
      }

      return var10000;
   }

   public Object replace(final Object x, final Object v) {
      this.cache.put(x, v);
      return v;
   }

   public void clear() {
      this.cache.clear();
   }
}
