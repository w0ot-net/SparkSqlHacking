package io.fabric8.kubernetes.client.dsl;

public interface WatchAndWaitable extends Watchable, Waitable {
   Watchable withResourceVersion(String var1);
}
