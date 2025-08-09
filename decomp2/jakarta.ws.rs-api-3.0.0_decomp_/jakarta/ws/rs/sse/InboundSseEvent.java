package jakarta.ws.rs.sse;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public interface InboundSseEvent extends SseEvent {
   boolean isEmpty();

   String readData();

   Object readData(Class var1);

   Object readData(GenericType var1);

   Object readData(Class var1, MediaType var2);

   Object readData(GenericType var1, MediaType var2);
}
