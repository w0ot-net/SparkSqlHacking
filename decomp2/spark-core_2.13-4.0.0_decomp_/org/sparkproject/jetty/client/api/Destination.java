package org.sparkproject.jetty.client.api;

import org.sparkproject.jetty.util.Promise;

public interface Destination {
   String getScheme();

   String getHost();

   int getPort();

   void newConnection(Promise var1);
}
