package org.sparkproject.jetty.server;

import java.nio.ByteBuffer;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.util.Callback;

public interface HttpTransport {
   String UPGRADE_CONNECTION_ATTRIBUTE = HttpTransport.class.getName() + ".UPGRADE";

   void send(MetaData.Request var1, MetaData.Response var2, ByteBuffer var3, boolean var4, Callback var5);

   boolean isPushSupported();

   void push(MetaData.Request var1);

   void onCompleted();

   void abort(Throwable var1);
}
