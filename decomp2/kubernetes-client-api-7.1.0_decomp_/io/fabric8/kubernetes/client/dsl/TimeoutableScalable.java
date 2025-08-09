package io.fabric8.kubernetes.client.dsl;

import java.util.concurrent.TimeUnit;

public interface TimeoutableScalable extends Deletable {
   Object scale(int var1);

   TimeoutableScalable withTimeout(long var1, TimeUnit var3);

   TimeoutableScalable withTimeoutInMillis(long var1);
}
