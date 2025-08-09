package jakarta.ws.rs.sse;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface SseBroadcaster extends AutoCloseable {
   void onError(BiConsumer var1);

   void onClose(Consumer var1);

   void register(SseEventSink var1);

   CompletionStage broadcast(OutboundSseEvent var1);

   void close();
}
