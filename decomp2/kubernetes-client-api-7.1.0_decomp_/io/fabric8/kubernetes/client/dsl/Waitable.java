package io.fabric8.kubernetes.client.dsl;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public interface Waitable {
   Object waitUntilReady(long var1, TimeUnit var3);

   Object waitUntilCondition(Predicate var1, long var2, TimeUnit var4);
}
