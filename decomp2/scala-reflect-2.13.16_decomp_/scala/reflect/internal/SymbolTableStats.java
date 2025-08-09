package scala.reflect.internal;

import scala.reflect.ScalaSignature;
import scala.reflect.internal.util.Statistics;

@ScalaSignature(
   bytes = "\u0006\u0005-2\u0001b\u0001\u0003\u0011\u0002\u0007\u00051b\b\u0005\u0006!\u0001!\t!\u0005\u0005\b+\u0001\u0011\r\u0011\"\u0001\u0017\u0005A\u0019\u00160\u001c2pYR\u000b'\r\\3Ti\u0006$8O\u0003\u0002\u0006\r\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\b\u0011\u00059!/\u001a4mK\u000e$(\"A\u0005\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001\u0001\u0004\t\u0003\u001b9i\u0011\u0001C\u0005\u0003\u001f!\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0013!\ti1#\u0003\u0002\u0015\u0011\t!QK\\5u\u00039\u0019G.Y:t%\u0016\fGMT1o_N,\u0012a\u0006\t\u00031ei\u0011\u0001A\u0005\u00035m\u0011Q\u0001V5nKJL!\u0001H\u000f\u0003\u0015M#\u0018\r^5ti&\u001c7O\u0003\u0002\u001f\t\u0005!Q\u000f^5m%\r\u0001#\u0005\n\u0004\u0005C\u0001\u0001qD\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002$\u00015\tAAE\u0002&M%2A!\t\u0001\u0001IA\u00111eJ\u0005\u0003Q\u0011\u0011!\u0002V=qKN\u001cF/\u0019;t!\tQ3$D\u0001\u001e\u0001"
)
public interface SymbolTableStats {
   void scala$reflect$internal$SymbolTableStats$_setter_$classReadNanos_$eq(final Statistics.Timer x$1);

   Statistics.Timer classReadNanos();

   static void $init$(final SymbolTableStats $this) {
      $this.scala$reflect$internal$SymbolTableStats$_setter_$classReadNanos_$eq(((Statistics)$this).newSubTimer("time classfilereading", ((TypesStats)$this).typerNanos()));
   }
}
