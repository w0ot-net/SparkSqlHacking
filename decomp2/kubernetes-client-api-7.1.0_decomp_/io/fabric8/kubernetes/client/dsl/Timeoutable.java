package io.fabric8.kubernetes.client.dsl;

import java.util.concurrent.TimeUnit;

public interface Timeoutable {
   Object withTimeout(long var1, TimeUnit var3);

   Object withTimeoutInMillis(long var1);
}
