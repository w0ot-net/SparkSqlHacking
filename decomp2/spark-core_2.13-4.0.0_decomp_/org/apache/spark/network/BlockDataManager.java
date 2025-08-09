package org.apache.spark.network;

import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.shuffle.checksum.Cause;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.StorageLevel;
import scala.Option;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005b\u0001\u0003\u0005\n!\u0003\r\naC\t\t\u000ba\u0001a\u0011\u0001\u000e\t\u000bu\u0002a\u0011\u0001 \t\u000b\t\u0003a\u0011A\"\t\u000b5\u0003a\u0011\u0001(\t\u000bA\u0003a\u0011A)\t\u000bE\u0004a\u0011\u0001:\t\u000f\u0005\r\u0001A\"\u0001\u0002\u0006\t\u0001\"\t\\8dW\u0012\u000bG/Y'b]\u0006<WM\u001d\u0006\u0003\u0015-\tqA\\3uo>\u00148N\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\t\u0001!\u0003\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BC\u0001\u0004B]f\u0014VMZ\u0001\u001fI&\fwM\\8tKNCWO\u001a4mK\ncwnY6D_J\u0014X\u000f\u001d;j_:\u001c\u0001\u0001\u0006\u0003\u001cG-\u0002\u0004C\u0001\u000f\"\u001b\u0005i\"B\u0001\u0010 \u0003!\u0019\u0007.Z2lgVl'B\u0001\u0011\n\u0003\u001d\u0019\b.\u001e4gY\u0016L!AI\u000f\u0003\u000b\r\u000bWo]3\t\u000b\u0011\n\u0001\u0019A\u0013\u0002\u000f\tdwnY6JIB\u0011a%K\u0007\u0002O)\u0011\u0001fC\u0001\bgR|'/Y4f\u0013\tQsEA\u0004CY>\u001c7.\u00133\t\u000b1\n\u0001\u0019A\u0017\u0002!\rDWmY6tk6\u0014\u0015PU3bI\u0016\u0014\bCA\n/\u0013\tyCC\u0001\u0003M_:<\u0007\"B\u0019\u0002\u0001\u0004\u0011\u0014!C1mO>\u0014\u0018\u000e\u001e5n!\t\u0019$H\u0004\u00025qA\u0011Q\u0007F\u0007\u0002m)\u0011q'G\u0001\u0007yI|w\u000e\u001e \n\u0005e\"\u0012A\u0002)sK\u0012,g-\u0003\u0002<y\t11\u000b\u001e:j]\u001eT!!\u000f\u000b\u0002!\u001d,G\u000fT8dC2$\u0015n]6ESJ\u001cX#A \u0011\u0007M\u0001%'\u0003\u0002B)\t)\u0011I\u001d:bs\u00069r-\u001a;I_N$Hj\\2bYNCWO\u001a4mK\u0012\u000bG/\u0019\u000b\u0004\t*[\u0005CA#I\u001b\u00051%BA$\n\u0003\u0019\u0011WO\u001a4fe&\u0011\u0011J\u0012\u0002\u000e\u001b\u0006t\u0017mZ3e\u0005V4g-\u001a:\t\u000b\u0011\u001a\u0001\u0019A\u0013\t\u000b1\u001b\u0001\u0019A \u0002\t\u0011L'o]\u0001\u0012O\u0016$Hj\\2bY\ncwnY6ECR\fGC\u0001#P\u0011\u0015!C\u00011\u0001&\u00031\u0001X\u000f\u001e\"m_\u000e\\G)\u0019;b)\u0015\u0011VK\u0016-^!\t\u00192+\u0003\u0002U)\t9!i\\8mK\u0006t\u0007\"\u0002\u0013\u0006\u0001\u0004)\u0003\"B,\u0006\u0001\u0004!\u0015\u0001\u00023bi\u0006DQ!W\u0003A\u0002i\u000bQ\u0001\\3wK2\u0004\"AJ.\n\u0005q;#\u0001D*u_J\fw-\u001a'fm\u0016d\u0007\"\u00020\u0006\u0001\u0004y\u0016\u0001C2mCN\u001cH+Y41\u0005\u0001D\u0007cA1eM6\t!M\u0003\u0002d)\u00059!/\u001a4mK\u000e$\u0018BA3c\u0005!\u0019E.Y:t)\u0006<\u0007CA4i\u0019\u0001!\u0011\"[/\u0002\u0002\u0003\u0005)\u0011\u00016\u0003\u0007}#\u0013'\u0005\u0002l]B\u00111\u0003\\\u0005\u0003[R\u0011qAT8uQ&tw\r\u0005\u0002\u0014_&\u0011\u0001\u000f\u0006\u0002\u0004\u0003:L\u0018\u0001\u00069vi\ncwnY6ECR\f\u0017i]*ue\u0016\fW\u000e\u0006\u0003tsj\\\bC\u0001;x\u001b\u0005)(B\u0001<\n\u0003\u0019\u0019G.[3oi&\u0011\u00010\u001e\u0002\u0015'R\u0014X-Y7DC2d'-Y2l/&$\b.\u0013#\t\u000b\u00112\u0001\u0019A\u0013\t\u000be3\u0001\u0019\u0001.\t\u000by3\u0001\u0019\u0001?1\u0005u|\bcA1e}B\u0011qm \u0003\u000b\u0003\u0003Y\u0018\u0011!A\u0001\u0006\u0003Q'aA0%e\u0005Y!/\u001a7fCN,Gj\\2l)\u0019\t9!!\u0004\u0002\u0010A\u00191#!\u0003\n\u0007\u0005-AC\u0001\u0003V]&$\b\"\u0002\u0013\b\u0001\u0004)\u0003bBA\t\u000f\u0001\u0007\u00111C\u0001\fi\u0006\u001c8nQ8oi\u0016DH\u000fE\u0003\u0014\u0003+\tI\"C\u0002\u0002\u0018Q\u0011aa\u00149uS>t\u0007\u0003BA\u000e\u0003;i\u0011aC\u0005\u0004\u0003?Y!a\u0003+bg.\u001cuN\u001c;fqR\u0004"
)
public interface BlockDataManager {
   Cause diagnoseShuffleBlockCorruption(final BlockId blockId, final long checksumByReader, final String algorithm);

   String[] getLocalDiskDirs();

   ManagedBuffer getHostLocalShuffleData(final BlockId blockId, final String[] dirs);

   ManagedBuffer getLocalBlockData(final BlockId blockId);

   boolean putBlockData(final BlockId blockId, final ManagedBuffer data, final StorageLevel level, final ClassTag classTag);

   StreamCallbackWithID putBlockDataAsStream(final BlockId blockId, final StorageLevel level, final ClassTag classTag);

   void releaseLock(final BlockId blockId, final Option taskContext);
}
