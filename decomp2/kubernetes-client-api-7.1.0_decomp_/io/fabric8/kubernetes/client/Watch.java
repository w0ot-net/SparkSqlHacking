package io.fabric8.kubernetes.client;

import java.io.Closeable;

public interface Watch extends Closeable {
   void close();
}
