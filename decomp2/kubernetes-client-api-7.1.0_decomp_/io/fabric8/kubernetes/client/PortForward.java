package io.fabric8.kubernetes.client;

import java.io.Closeable;
import java.util.Collection;

public interface PortForward extends Closeable {
   boolean isAlive();

   boolean errorOccurred();

   Collection getClientThrowables();

   Collection getServerThrowables();
}
