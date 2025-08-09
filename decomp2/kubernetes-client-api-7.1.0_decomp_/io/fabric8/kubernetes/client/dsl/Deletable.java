package io.fabric8.kubernetes.client.dsl;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface Deletable extends Timeoutable {
   List delete();

   Deletable withTimeout(long var1, TimeUnit var3);

   Deletable withTimeoutInMillis(long var1);
}
