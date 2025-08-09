package io.fabric8.kubernetes.client.dsl;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;

public interface ExecWatch extends Closeable {
   OutputStream getInput();

   InputStream getOutput();

   InputStream getError();

   InputStream getErrorChannel();

   void close();

   void resize(int var1, int var2);

   CompletableFuture exitCode();
}
