package org.glassfish.jersey.client.internal;

import org.glassfish.jersey.client.ClientRequest;

public interface ConnectorExtension {
   void invoke(ClientRequest var1, Object var2);

   void postConnectionProcessing(Object var1);

   boolean handleException(ClientRequest var1, Object var2, Exception var3) throws Exception;
}
