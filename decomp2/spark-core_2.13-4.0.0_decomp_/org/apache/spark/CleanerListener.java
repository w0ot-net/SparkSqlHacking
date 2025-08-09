package org.apache.spark;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E2\u0001BB\u0004\u0011\u0002G\u0005q!\u0004\u0005\u0006)\u00011\tA\u0006\u0005\u0006?\u00011\t\u0001\t\u0005\u0006G\u00011\t\u0001\n\u0005\u0006U\u00011\ta\u000b\u0005\u0006]\u00011\ta\f\u0002\u0010\u00072,\u0017M\\3s\u0019&\u001cH/\u001a8fe*\u0011\u0001\"C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0015-\ta!\u00199bG\",'\"\u0001\u0007\u0002\u0007=\u0014xm\u0005\u0002\u0001\u001dA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\f!B\u001d3e\u00072,\u0017M\\3e\u0007\u0001!\"a\u0006\u000e\u0011\u0005=A\u0012BA\r\u0011\u0005\u0011)f.\u001b;\t\u000bm\t\u0001\u0019\u0001\u000f\u0002\u000bI$G-\u00133\u0011\u0005=i\u0012B\u0001\u0010\u0011\u0005\rIe\u000e^\u0001\u000fg\",hM\u001a7f\u00072,\u0017M\\3e)\t9\u0012\u0005C\u0003#\u0005\u0001\u0007A$A\u0005tQV4g\r\\3JI\u0006\u0001\"M]8bI\u000e\f7\u000f^\"mK\u0006tW\r\u001a\u000b\u0003/\u0015BQAJ\u0002A\u0002\u001d\n1B\u0019:pC\u0012\u001c\u0017m\u001d;JIB\u0011q\u0002K\u0005\u0003SA\u0011A\u0001T8oO\u0006a\u0011mY2v[\u000ecW-\u00198fIR\u0011q\u0003\f\u0005\u0006[\u0011\u0001\raJ\u0001\u0006C\u000e\u001c\u0017\nZ\u0001\u0012G\",7m\u001b9pS:$8\t\\3b]\u0016$GCA\f1\u0011\u0015YR\u00011\u0001(\u0001"
)
public interface CleanerListener {
   void rddCleaned(final int rddId);

   void shuffleCleaned(final int shuffleId);

   void broadcastCleaned(final long broadcastId);

   void accumCleaned(final long accId);

   void checkpointCleaned(final long rddId);
}
