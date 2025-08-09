package org.glassfish.jersey.client;

public interface Initializable {
   Initializable preInitialize();

   ClientConfig getConfiguration();
}
