package org.apache.spark.storage.memory;

import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.StorageLevel;
import scala.Function0;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3\u0001BA\u0002\u0011\u0002G\u0005Q!\u0004\u0005\u0007)\u00011\t!B\u000b\u0003)\tcwnY6Fm&\u001cG/[8o\u0011\u0006tG\r\\3s\u0015\t!Q!\u0001\u0004nK6|'/\u001f\u0006\u0003\r\u001d\tqa\u001d;pe\u0006<WM\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h'\t\u0001a\u0002\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VMZ\u0001\u000fIJ|\u0007O\u0012:p[6+Wn\u001c:z+\t1r\u0005F\u0002\u0018aU\"\"\u0001\u0007\u000f\u0011\u0005eQR\"A\u0003\n\u0005m)!\u0001D*u_J\fw-\u001a'fm\u0016d\u0007bB\u000f\u0002\u0003\u0003\u0005\u001daH\u0001\u000bKZLG-\u001a8dK\u0012\n4\u0001\u0001\t\u0004A\r*S\"A\u0011\u000b\u0005\t\u0002\u0012a\u0002:fM2,7\r^\u0005\u0003I\u0005\u0012\u0001b\u00117bgN$\u0016m\u001a\t\u0003M\u001db\u0001\u0001B\u0003)\u0003\t\u0007\u0011FA\u0001U#\tQS\u0006\u0005\u0002\u0010W%\u0011A\u0006\u0005\u0002\b\u001d>$\b.\u001b8h!\tya&\u0003\u00020!\t\u0019\u0011I\\=\t\u000bE\n\u0001\u0019\u0001\u001a\u0002\u000f\tdwnY6JIB\u0011\u0011dM\u0005\u0003i\u0015\u0011qA\u00117pG.LE\rC\u00037\u0003\u0001\u0007q'\u0001\u0003eCR\f\u0007cA\b9u%\u0011\u0011\b\u0005\u0002\n\rVt7\r^5p]B\u0002BaO\"G\u0013:\u0011A(\u0011\b\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007fy\ta\u0001\u0010:p_Rt\u0014\"A\t\n\u0005\t\u0003\u0012a\u00029bG.\fw-Z\u0005\u0003\t\u0016\u0013a!R5uQ\u0016\u0014(B\u0001\"\u0011!\ryq)J\u0005\u0003\u0011B\u0011Q!\u0011:sCf\u0004\"AS(\u000e\u0003-S!\u0001T'\u0002\u0005%|'B\u0001(\b\u0003\u0011)H/\u001b7\n\u0005A[%!E\"ik:\\W\r\u001a\"zi\u0016\u0014UO\u001a4fe\u0002"
)
public interface BlockEvictionHandler {
   StorageLevel dropFromMemory(final BlockId blockId, final Function0 data, final ClassTag evidence$1);
}
