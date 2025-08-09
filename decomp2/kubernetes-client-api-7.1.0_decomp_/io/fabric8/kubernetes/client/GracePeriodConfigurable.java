package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.Deletable;

public interface GracePeriodConfigurable extends Deletable {
   Object withGracePeriod(long var1);
}
