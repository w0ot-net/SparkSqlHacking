package org.apache.spark.shuffle;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000552\u0001BB\u0004\u0011\u0002G\u0005\u0011b\u0004\u0005\u0007-\u00011\t!C\f\t\r\u0005\u0002a\u0011A\u0005#\u0011\u0019!\u0003A\"\u0001\nK!1q\u0005\u0001D\u0001\u0013!BaA\u000b\u0001\u0007\u0002%Y#aG*ik\u001a4G.Z,sSR,W*\u001a;sS\u000e\u001c(+\u001a9peR,'O\u0003\u0002\t\u0013\u000591\u000f[;gM2,'B\u0001\u0006\f\u0003\u0015\u0019\b/\u0019:l\u0015\taQ\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0005\u0019qN]4\u0014\u0005\u0001\u0001\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g-A\bj]\u000e\u0014\u0015\u0010^3t/JLG\u000f^3o)\tA2\u0004\u0005\u0002\u00123%\u0011!D\u0005\u0002\u0005+:LG\u000fC\u0003\u001d\u0003\u0001\u0007a$A\u0001w\u0007\u0001\u0001\"!E\u0010\n\u0005\u0001\u0012\"\u0001\u0002'p]\u001e\f\u0011#\u001b8d%\u0016\u001cwN\u001d3t/JLG\u000f^3o)\tA2\u0005C\u0003\u001d\u0005\u0001\u0007a$\u0001\u0007j]\u000e<&/\u001b;f)&lW\r\u0006\u0002\u0019M!)Ad\u0001a\u0001=\u0005yA-Z2CsR,7o\u0016:jiR,g\u000e\u0006\u0002\u0019S!)A\u0004\u0002a\u0001=\u0005\tB-Z2SK\u000e|'\u000fZ:Xe&$H/\u001a8\u0015\u0005aa\u0003\"\u0002\u000f\u0006\u0001\u0004q\u0002"
)
public interface ShuffleWriteMetricsReporter {
   void incBytesWritten(final long v);

   void incRecordsWritten(final long v);

   void incWriteTime(final long v);

   void decBytesWritten(final long v);

   void decRecordsWritten(final long v);
}
