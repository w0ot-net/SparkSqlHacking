package org.glassfish.jersey.server;

import java.io.InputStream;
import java.util.Iterator;

public interface ResourceFinder extends Iterator, AutoCloseable {
   InputStream open();

   void close();

   void reset();

   void remove();
}
