package org.apache.spark.shuffle;

import org.apache.spark.storage.BlockManagerId;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592Q!\u0002\u0004\u0001\u00119A\u0001b\u0005\u0001\u0003\u0002\u0003\u0006I!\u0006\u0005\t7\u0001\u0011\t\u0011)A\u0005+!AA\u0004\u0001B\u0001B\u0003%Q\u0004C\u0003)\u0001\u0011\u0005\u0011F\u0001\u000fNKR\fG-\u0019;b\r\u0016$8\r\u001b$bS2,G-\u0012=dKB$\u0018n\u001c8\u000b\u0005\u001dA\u0011aB:ik\u001a4G.\u001a\u0006\u0003\u0013)\tQa\u001d9be.T!a\u0003\u0007\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0011aA8sON\u0011\u0001a\u0004\t\u0003!Ei\u0011AB\u0005\u0003%\u0019\u0011ACR3uG\"4\u0015-\u001b7fI\u0016C8-\u001a9uS>t\u0017!C:ik\u001a4G.Z%e\u0007\u0001\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u00111!\u00138u\u0003!\u0011X\rZ;dK&#\u0017aB7fgN\fw-\u001a\t\u0003=\u0015r!aH\u0012\u0011\u0005\u0001:R\"A\u0011\u000b\u0005\t\"\u0012A\u0002\u001fs_>$h(\u0003\u0002%/\u00051\u0001K]3eK\u001aL!AJ\u0014\u0003\rM#(/\u001b8h\u0015\t!s#\u0001\u0004=S:LGO\u0010\u000b\u0005U-bS\u0006\u0005\u0002\u0011\u0001!)1\u0003\u0002a\u0001+!)1\u0004\u0002a\u0001+!)A\u0004\u0002a\u0001;\u0001"
)
public class MetadataFetchFailedException extends FetchFailedException {
   public MetadataFetchFailedException(final int shuffleId, final int reduceId, final String message) {
      super((BlockManagerId)null, shuffleId, -1L, -1, reduceId, message, FetchFailedException$.MODULE$.$lessinit$greater$default$7());
   }
}
