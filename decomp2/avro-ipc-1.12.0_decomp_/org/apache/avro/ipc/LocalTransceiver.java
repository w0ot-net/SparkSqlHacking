package org.apache.avro.ipc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

public class LocalTransceiver extends Transceiver {
   private Responder responder;

   public LocalTransceiver(Responder responder) {
      this.responder = responder;
   }

   public String getRemoteName() {
      return "local";
   }

   public List transceive(List request) throws IOException {
      return this.responder.respond(request);
   }

   public List readBuffers() throws IOException {
      throw new UnsupportedOperationException();
   }

   public void writeBuffers(List buffers) throws IOException {
      throw new UnsupportedOperationException();
   }
}
