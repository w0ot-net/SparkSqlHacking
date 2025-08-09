package org.apache.spark.sql;

import scala.collection.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3q\u0001C\u0005\u0011\u0002\u0007\u0005!\u0003C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0019\u0005\u0001\u0005C\u0003 \u0001\u0011\u00051\bC\u0003 \u0001\u0011\u0005\u0011\tC\u0003 \u0001\u0011\u0005q\tC\u0003N\u0001\u0019\u0005a\nC\u0003N\u0001\u0019\u0005aK\u0001\nXe&$XmQ8oM&<W*\u001a;i_\u0012\u001c(B\u0001\u0006\f\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sO\u000e\u0001QCA\n$'\t\u0001A\u0003\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003q\u0001\"!F\u000f\n\u0005y1\"\u0001B+oSR\faa\u001c9uS>tGcA\u0011-sA\u0011!e\t\u0007\u0001\t\u0015!\u0003A1\u0001&\u0005\u0005\u0011\u0016C\u0001\u0014*!\t)r%\u0003\u0002)-\t9aj\u001c;iS:<\u0007CA\u000b+\u0013\tYcCA\u0002B]fDQ!\f\u0002A\u00029\n1a[3z!\tycG\u0004\u00021iA\u0011\u0011GF\u0007\u0002e)\u00111'E\u0001\u0007yI|w\u000e\u001e \n\u0005U2\u0012A\u0002)sK\u0012,g-\u0003\u00028q\t11\u000b\u001e:j]\u001eT!!\u000e\f\t\u000bi\u0012\u0001\u0019\u0001\u0018\u0002\u000bY\fG.^3\u0015\u0007\u0005bT\bC\u0003.\u0007\u0001\u0007a\u0006C\u0003;\u0007\u0001\u0007a\b\u0005\u0002\u0016\u007f%\u0011\u0001I\u0006\u0002\b\u0005>|G.Z1o)\r\t#i\u0011\u0005\u0006[\u0011\u0001\rA\f\u0005\u0006u\u0011\u0001\r\u0001\u0012\t\u0003+\u0015K!A\u0012\f\u0003\t1{gn\u001a\u000b\u0004C!K\u0005\"B\u0017\u0006\u0001\u0004q\u0003\"\u0002\u001e\u0006\u0001\u0004Q\u0005CA\u000bL\u0013\taeC\u0001\u0004E_V\u0014G.Z\u0001\b_B$\u0018n\u001c8t)\t\ts\nC\u0003N\r\u0001\u0007\u0001\u000b\u0005\u0003R):rS\"\u0001*\u000b\u0005M3\u0012AC2pY2,7\r^5p]&\u0011QK\u0015\u0002\u0004\u001b\u0006\u0004HCA\u0011X\u0011\u0015iu\u00011\u0001Y!\u0011IfL\f\u0018\u000e\u0003iS!a\u0017/\u0002\tU$\u0018\u000e\u001c\u0006\u0002;\u0006!!.\u0019<b\u0013\t)&\f"
)
public interface WriteConfigMethods {
   Object option(final String key, final String value);

   // $FF: synthetic method
   static Object option$(final WriteConfigMethods $this, final String key, final boolean value) {
      return $this.option(key, value);
   }

   default Object option(final String key, final boolean value) {
      return this.option(key, Boolean.toString(value));
   }

   // $FF: synthetic method
   static Object option$(final WriteConfigMethods $this, final String key, final long value) {
      return $this.option(key, value);
   }

   default Object option(final String key, final long value) {
      return this.option(key, Long.toString(value));
   }

   // $FF: synthetic method
   static Object option$(final WriteConfigMethods $this, final String key, final double value) {
      return $this.option(key, value);
   }

   default Object option(final String key, final double value) {
      return this.option(key, Double.toString(value));
   }

   Object options(final Map options);

   Object options(final java.util.Map options);

   static void $init$(final WriteConfigMethods $this) {
   }
}
