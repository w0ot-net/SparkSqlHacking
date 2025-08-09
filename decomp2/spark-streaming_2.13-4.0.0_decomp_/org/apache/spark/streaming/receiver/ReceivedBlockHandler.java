package org.apache.spark.streaming.receiver;

import org.apache.spark.storage.StreamBlockId;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2\u0001b\u0001\u0003\u0011\u0002G\u0005aA\u0004\u0005\u0006+\u00011\ta\u0006\u0005\u0006S\u00011\tA\u000b\u0002\u0015%\u0016\u001cW-\u001b<fI\ncwnY6IC:$G.\u001a:\u000b\u0005\u00151\u0011\u0001\u0003:fG\u0016Lg/\u001a:\u000b\u0005\u001dA\u0011!C:ue\u0016\fW.\u001b8h\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<7C\u0001\u0001\u0010!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fM\u0006Q1\u000f^8sK\ncwnY6\u0004\u0001Q\u0019\u0001\u0004\b\u0013\u0011\u0005eQR\"\u0001\u0003\n\u0005m!!\u0001\u0007*fG\u0016Lg/\u001a3CY>\u001c7n\u0015;pe\u0016\u0014Vm];mi\")Q$\u0001a\u0001=\u00059!\r\\8dW&#\u0007CA\u0010#\u001b\u0005\u0001#BA\u0011\t\u0003\u001d\u0019Ho\u001c:bO\u0016L!a\t\u0011\u0003\u001bM#(/Z1n\u00052|7m[%e\u0011\u0015)\u0013\u00011\u0001'\u00035\u0011XmY3jm\u0016$'\t\\8dWB\u0011\u0011dJ\u0005\u0003Q\u0011\u0011QBU3dK&4X\r\u001a\"m_\u000e\\\u0017\u0001E2mK\u0006tW\u000f](mI\ncwnY6t)\tYc\u0006\u0005\u0002\u0011Y%\u0011Q&\u0005\u0002\u0005+:LG\u000fC\u00030\u0005\u0001\u0007\u0001'\u0001\u0006uQJ,7\u000f\u001b+j[\u0016\u0004\"\u0001E\u0019\n\u0005I\n\"\u0001\u0002'p]\u001e\u0004"
)
public interface ReceivedBlockHandler {
   ReceivedBlockStoreResult storeBlock(final StreamBlockId blockId, final ReceivedBlock receivedBlock);

   void cleanupOldBlocks(final long threshTime);
}
