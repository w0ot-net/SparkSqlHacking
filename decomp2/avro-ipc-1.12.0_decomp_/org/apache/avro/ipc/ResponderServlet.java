package org.apache.avro.ipc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.avro.AvroRuntimeException;

public class ResponderServlet extends HttpServlet {
   private Responder responder;

   public ResponderServlet(Responder responder) throws IOException {
      this.responder = responder;
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      response.setContentType("avro/binary");
      List<ByteBuffer> requestBufs = HttpTransceiver.readBuffers(request.getInputStream());

      try {
         List<ByteBuffer> responseBufs = this.responder.respond(requestBufs);
         response.setContentLength(HttpTransceiver.getLength(responseBufs));
         HttpTransceiver.writeBuffers(responseBufs, response.getOutputStream());
      } catch (AvroRuntimeException e) {
         throw new ServletException(e);
      }
   }
}
