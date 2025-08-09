package org.sparkproject.jetty.client;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.util.component.Dumpable;

public class ProtocolHandlers implements Dumpable {
   private final Map handlers = new LinkedHashMap();

   protected ProtocolHandlers() {
   }

   public ProtocolHandler put(ProtocolHandler protocolHandler) {
      return (ProtocolHandler)this.handlers.put(protocolHandler.getName(), protocolHandler);
   }

   public ProtocolHandler remove(String name) {
      return (ProtocolHandler)this.handlers.remove(name);
   }

   public void clear() {
      this.handlers.clear();
   }

   public ProtocolHandler find(Request request, Response response) {
      for(ProtocolHandler handler : this.handlers.values()) {
         if (handler.accept(request, response)) {
            return handler;
         }
      }

      return null;
   }

   public String dump() {
      return Dumpable.dump(this);
   }

   public void dump(Appendable out, String indent) throws IOException {
      Dumpable.dumpObjects(out, indent, this, this.handlers);
   }
}
