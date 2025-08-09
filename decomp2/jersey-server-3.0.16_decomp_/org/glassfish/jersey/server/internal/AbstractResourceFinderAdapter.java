package org.glassfish.jersey.server.internal;

import org.glassfish.jersey.server.ResourceFinder;

public abstract class AbstractResourceFinderAdapter implements ResourceFinder {
   public void remove() {
      throw new UnsupportedOperationException();
   }

   public void close() {
   }
}
