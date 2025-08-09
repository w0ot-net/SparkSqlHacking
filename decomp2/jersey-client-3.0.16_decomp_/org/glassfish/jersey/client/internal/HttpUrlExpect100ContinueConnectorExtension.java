package org.glassfish.jersey.client.internal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.RequestEntityProcessing;

class HttpUrlExpect100ContinueConnectorExtension implements ConnectorExtension {
   private static final String EXCEPTION_MESSAGE = "Server rejected operation";

   public void invoke(ClientRequest request, HttpURLConnection uc) {
      long length = request.getLengthLong();
      RequestEntityProcessing entityProcessing = (RequestEntityProcessing)request.resolveProperty("jersey.config.client.request.entity.processing", RequestEntityProcessing.class);
      Boolean expectContinueActivated = (Boolean)request.resolveProperty("jersey.config.client.request.expect.100.continue.processing", Boolean.class);
      Long expectContinueSizeThreshold = (Long)request.resolveProperty("jersey.config.client.request.expect.100.continue.threshold.size", (Object)ClientProperties.DEFAULT_EXPECT_100_CONTINUE_THRESHOLD_SIZE);
      boolean allowStreaming = length > expectContinueSizeThreshold || entityProcessing == RequestEntityProcessing.CHUNKED;
      if (Boolean.TRUE.equals(expectContinueActivated) && ("POST".equals(uc.getRequestMethod()) || "PUT".equals(uc.getRequestMethod())) && allowStreaming) {
         uc.setRequestProperty("Expect", "100-Continue");
      }
   }

   public void postConnectionProcessing(HttpURLConnection extensionParam) {
   }

   public boolean handleException(ClientRequest request, HttpURLConnection extensionParam, IOException ex) {
      Boolean expectContinueActivated = (Boolean)request.resolveProperty("jersey.config.client.request.expect.100.continue.processing", (Object)Boolean.FALSE);
      return expectContinueActivated && ex instanceof ProtocolException && ex.getMessage().equals("Server rejected operation");
   }
}
