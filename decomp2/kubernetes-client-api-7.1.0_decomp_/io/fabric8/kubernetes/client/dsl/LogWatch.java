package io.fabric8.kubernetes.client.dsl;

import java.io.Closeable;
import java.io.InputStream;

public interface LogWatch extends Closeable {
   InputStream getOutput();

   void close();
}
