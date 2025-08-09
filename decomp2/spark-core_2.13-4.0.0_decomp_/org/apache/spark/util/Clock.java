package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}1\u0001\u0002B\u0003\u0011\u0002G\u0005q!\u0004\u0005\u0006)\u00011\tA\u0006\u0005\u00065\u00011\tA\u0006\u0005\u00067\u00011\t\u0001\b\u0002\u0006\u00072|7m\u001b\u0006\u0003\r\u001d\tA!\u001e;jY*\u0011\u0001\"C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0015-\ta!\u00199bG\",'\"\u0001\u0007\u0002\u0007=\u0014xm\u0005\u0002\u0001\u001dA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\fQbZ3u)&lW-T5mY&\u001c8\u0001\u0001\u000b\u0002/A\u0011q\u0002G\u0005\u00033A\u0011A\u0001T8oO\u0006Aa.\u00198p)&lW-\u0001\u0007xC&$H+\u001b7m)&lW\r\u0006\u0002\u0018;!)ad\u0001a\u0001/\u0005QA/\u0019:hKR$\u0016.\\3"
)
public interface Clock {
   long getTimeMillis();

   long nanoTime();

   long waitTillTime(final long targetTime);
}
