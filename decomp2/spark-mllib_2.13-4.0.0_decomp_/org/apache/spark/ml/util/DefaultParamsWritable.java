package org.apache.spark.ml.util;

import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592\u0001b\u0001\u0003\u0011\u0002\u0007\u0005q\u0002\n\u0005\u00065\u0001!\ta\u0007\u0005\u0006?\u0001!\t\u0005\t\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:Xe&$\u0018M\u00197f\u0015\t)a!\u0001\u0003vi&d'BA\u0004\t\u0003\tiGN\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h\u0007\u0001\u00192\u0001\u0001\t\u0017!\t\tB#D\u0001\u0013\u0015\u0005\u0019\u0012!B:dC2\f\u0017BA\u000b\u0013\u0005\u0019\te.\u001f*fMB\u0011q\u0003G\u0007\u0002\t%\u0011\u0011\u0004\u0002\u0002\u000b\u001b2;&/\u001b;bE2,\u0017A\u0002\u0013j]&$H\u0005F\u0001\u001d!\t\tR$\u0003\u0002\u001f%\t!QK\\5u\u0003\u00159(/\u001b;f+\u0005\t\u0003CA\f#\u0013\t\u0019CA\u0001\u0005N\u0019^\u0013\u0018\u000e^3s%\r)s\u0005\u000b\u0004\u0005M\u0001\u0001AE\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002\u0018\u0001A\u0011\u0011\u0006L\u0007\u0002U)\u00111FB\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003[)\u0012a\u0001U1sC6\u001c\b"
)
public interface DefaultParamsWritable extends MLWritable {
   // $FF: synthetic method
   static MLWriter write$(final DefaultParamsWritable $this) {
      return $this.write();
   }

   default MLWriter write() {
      return new DefaultParamsWriter((Params)this);
   }

   static void $init$(final DefaultParamsWritable $this) {
   }
}
