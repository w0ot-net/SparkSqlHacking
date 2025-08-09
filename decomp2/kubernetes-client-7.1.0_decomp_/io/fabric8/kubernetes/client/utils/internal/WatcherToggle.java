package io.fabric8.kubernetes.client.utils.internal;

import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import java.util.Objects;

public class WatcherToggle implements Watcher {
   private final Watcher delegate;
   private boolean enabled;

   public WatcherToggle(Watcher delegate, boolean enabled) {
      this.delegate = (Watcher)Objects.requireNonNull(delegate, "delegate watcher cannot be null");
      this.enabled = enabled;
   }

   public void disable() {
      this.enabled = false;
   }

   public void enable() {
      this.enabled = true;
   }

   public void eventReceived(Watcher.Action action, Object resource) {
      if (this.enabled) {
         this.delegate.eventReceived(action, resource);
      }

   }

   public void onClose(WatcherException cause) {
      if (this.enabled) {
         this.delegate.onClose(cause);
      }

   }

   public void onClose() {
      if (this.enabled) {
         this.delegate.onClose();
      }

   }

   public boolean reconnecting() {
      return this.delegate.reconnecting();
   }
}
