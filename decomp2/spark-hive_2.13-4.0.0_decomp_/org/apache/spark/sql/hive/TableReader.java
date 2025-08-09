package org.apache.spark.sql.hive;

import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.spark.rdd.RDD;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3\u0001b\u0001\u0003\u0011\u0002G\u0005BA\u0004\u0005\u0006+\u00011\ta\u0006\u0005\u0006c\u00011\tA\r\u0002\f)\u0006\u0014G.\u001a*fC\u0012,'O\u0003\u0002\u0006\r\u0005!\u0001.\u001b<f\u0015\t9\u0001\"A\u0002tc2T!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\n\u0003\u0001=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017aD7bW\u0016\u0014F\t\u0012$peR\u000b'\r\\3\u0004\u0001Q\u0011\u0001\u0004\n\t\u00043qqR\"\u0001\u000e\u000b\u0005mA\u0011a\u0001:eI&\u0011QD\u0007\u0002\u0004%\u0012#\u0005CA\u0010#\u001b\u0005\u0001#BA\u0011\u0007\u0003!\u0019\u0017\r^1msN$\u0018BA\u0012!\u0005-Ie\u000e^3s]\u0006d'k\\<\t\u000b\u0015\n\u0001\u0019\u0001\u0014\u0002\u0013!Lg/\u001a+bE2,\u0007CA\u00140\u001b\u0005A#BA\u0015+\u0003!iW\r^1eCR\f'BA\u0016-\u0003\t\tHN\u0003\u0002\u0006[)\u0011aFC\u0001\u0007Q\u0006$wn\u001c9\n\u0005AB#!\u0002+bE2,\u0017AG7bW\u0016\u0014F\t\u0012$peB\u000b'\u000f^5uS>tW\r\u001a+bE2,GC\u0001\r4\u0011\u0015!$\u00011\u00016\u0003)\u0001\u0018M\u001d;ji&|gn\u001d\t\u0004my\neBA\u001c=\u001d\tA4(D\u0001:\u0015\tQd#\u0001\u0004=e>|GOP\u0005\u0002%%\u0011Q(E\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0004IA\u0002TKFT!!P\t\u0011\u0005\u001d\u0012\u0015BA\")\u0005%\u0001\u0016M\u001d;ji&|g.\u000b\u0002\u0001\u000b&\u0011a\t\u0002\u0002\u0012\u0011\u0006$wn\u001c9UC\ndWMU3bI\u0016\u0014\b"
)
public interface TableReader {
   RDD makeRDDForTable(final Table hiveTable);

   RDD makeRDDForPartitionedTable(final Seq partitions);
}
