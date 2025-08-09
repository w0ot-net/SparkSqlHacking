package org.apache.spark.api.python;

import java.nio.charset.StandardCharsets;
import org.apache.spark.api.java.function.Function;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m2Aa\u0001\u0003\u0005\u001f!)1\u0007\u0001C\u0001i!)q\u0007\u0001C!q\ti!)\u001f;fgR{7\u000b\u001e:j]\u001eT!!\u0002\u0004\u0002\rALH\u000f[8o\u0015\t9\u0001\"A\u0002ba&T!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\u0002\u0001'\r\u0001\u0001\u0003\u0007\t\u0003#Yi\u0011A\u0005\u0006\u0003'Q\tA\u0001\\1oO*\tQ#\u0001\u0003kCZ\f\u0017BA\f\u0013\u0005\u0019y%M[3diB!\u0011$H\u0010)\u001b\u0005Q\"BA\u000e\u001d\u0003!1WO\\2uS>t'BA\u000b\u0007\u0013\tq\"D\u0001\u0005Gk:\u001cG/[8o!\r\u00013%J\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t)\u0011I\u001d:bsB\u0011\u0001EJ\u0005\u0003O\u0005\u0012AAQ=uKB\u0011\u0011\u0006\r\b\u0003U9\u0002\"aK\u0011\u000e\u00031R!!\f\b\u0002\rq\u0012xn\u001c;?\u0013\ty\u0013%\u0001\u0004Qe\u0016$WMZ\u0005\u0003cI\u0012aa\u0015;sS:<'BA\u0018\"\u0003\u0019a\u0014N\\5u}Q\tQ\u0007\u0005\u00027\u00015\tA!\u0001\u0003dC2dGC\u0001\u0015:\u0011\u0015Q$\u00011\u0001 \u0003\r\t'O\u001d"
)
public class BytesToString implements Function {
   public String call(final byte[] arr) {
      return new String(arr, StandardCharsets.UTF_8);
   }
}
