package io.fabric8.kubernetes.client.dsl.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.StatusDetails;
import io.fabric8.kubernetes.api.model.WatchEvent;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import io.fabric8.kubernetes.client.Watcher.Action;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.utils.ExponentialBackoffIntervalCalculator;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import io.fabric8.kubernetes.client.utils.Utils;
import io.fabric8.kubernetes.client.utils.internal.SerialExecutor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWatchManager implements Watch {
   private static final Logger logger = LoggerFactory.getLogger(AbstractWatchManager.class);
   private static final int INFO_LOG_CONNECTION_ERRORS = 10;
   final Watcher watcher;
   final AtomicReference resourceVersion;
   final AtomicBoolean forceClosed;
   private final int reconnectLimit;
   private final ExponentialBackoffIntervalCalculator retryIntervalCalculator;
   private Future reconnectAttempt;
   protected final HttpClient client;
   protected BaseOperation baseOperation;
   private final ListOptions listOptions;
   private final URL requestUrl;
   private final boolean receiveBookmarks;
   volatile WatchRequestState latestRequestState;
   private final Map endErrors = new ConcurrentHashMap();
   private AtomicInteger retryAfterSeconds = new AtomicInteger();
   private int watchEndCheckMs = 120000;

   AbstractWatchManager(Watcher watcher, BaseOperation baseOperation, ListOptions listOptions, int reconnectLimit, int reconnectInterval, HttpClient client) throws MalformedURLException {
      this.watcher = new SerialWatcher(watcher, new SerialExecutor(baseOperation.getOperationContext().getExecutor()));
      this.reconnectLimit = reconnectLimit;
      this.retryIntervalCalculator = new ExponentialBackoffIntervalCalculator(reconnectInterval, reconnectLimit);
      this.resourceVersion = new AtomicReference(listOptions.getResourceVersion());
      this.forceClosed = new AtomicBoolean();
      this.receiveBookmarks = Boolean.TRUE.equals(listOptions.getAllowWatchBookmarks());
      if (listOptions.getAllowWatchBookmarks() == null) {
         listOptions.setAllowWatchBookmarks(true);
      }

      this.baseOperation = baseOperation;
      this.requestUrl = baseOperation.getNamespacedUrl();
      this.listOptions = listOptions;
      this.client = client;
      this.startWatch();
   }

   protected abstract void start(URL var1, Map var2, WatchRequestState var3);

   public synchronized void closeRequest() {
      WatchRequestState state = this.latestRequestState;
      if (state != null && state.closed.compareAndSet(false, true)) {
         logger.debug("Closing the current watch");
         this.closeCurrentRequest();
         CompletableFuture<Void> future = Utils.schedule(this.baseOperation.getOperationContext().getExecutor(), () -> this.failSafeReconnect(state), (long)this.watchEndCheckMs, TimeUnit.MILLISECONDS);
         state.ended.whenComplete((v, t) -> future.cancel(true));
      }

   }

   private synchronized void failSafeReconnect(WatchRequestState state) {
      if (state == this.latestRequestState && !this.forceClosed.get() && (this.reconnectAttempt == null || this.reconnectAttempt.isDone())) {
         logger.error("The last watch has yet to terminate as expected, will force start another watch. Please report this to the Fabric8 Kubernetes Client development team.");
         this.reconnect();
      }

   }

   public void setWatchEndCheckMs(int watchEndCheckMs) {
      this.watchEndCheckMs = watchEndCheckMs;
   }

   protected abstract void closeCurrentRequest();

   final void close(WatcherException cause) {
      if (!this.forceClosed.compareAndSet(false, true)) {
         logger.debug("Ignoring duplicate firing of onClose event");
      } else {
         this.closeRequest();

         try {
            this.watcher.onClose(cause);
         } finally {
            this.close();
         }
      }

   }

   final void closeEvent() {
      if (this.forceClosed.getAndSet(true)) {
         logger.debug("Ignoring duplicate firing of onClose event");
      } else {
         this.watcher.onClose();
      }
   }

   final synchronized void cancelReconnect() {
      if (this.reconnectAttempt != null) {
         this.reconnectAttempt.cancel(true);
      }

   }

   void scheduleReconnect(WatchRequestState state) {
      if (state.reconnected.compareAndSet(false, true)) {
         if (this.isForceClosed()) {
            logger.debug("Ignoring already closed/closing connection");
         } else if (this.cannotReconnect()) {
            this.close(new WatcherException("Exhausted reconnects"));
         } else {
            long delay = this.nextReconnectInterval();
            logger.debug("Scheduling reconnect task in {} ms", delay);
            synchronized(this) {
               this.reconnectAttempt = Utils.schedule(this.baseOperation.getOperationContext().getExecutor(), this::reconnect, delay, TimeUnit.MILLISECONDS);
               if (this.isForceClosed()) {
                  this.cancelReconnect();
               }

            }
         }
      }
   }

   synchronized void reconnect() {
      try {
         if (this.client.isClosed()) {
            logger.debug("The client has closed, closing the watch");
            this.close();
            return;
         }

         this.startWatch();
         if (this.isForceClosed()) {
            this.closeRequest();
         }
      } catch (Exception e) {
         logger.error("Exception in reconnect", e);
         this.close(new WatcherException("Unhandled exception in reconnect attempt", e));
      }

   }

   final boolean cannotReconnect() {
      return !this.watcher.reconnecting() && this.retryIntervalCalculator.getCurrentReconnectAttempt() >= this.reconnectLimit && this.reconnectLimit >= 0;
   }

   final long nextReconnectInterval() {
      return Math.max((long)this.retryAfterSeconds.getAndSet(0) * 1000L, this.retryIntervalCalculator.nextReconnectInterval());
   }

   void resetReconnectAttempts(WatchRequestState state) {
      if (!state.closed.get()) {
         this.retryIntervalCalculator.resetReconnectAttempts();
      }
   }

   boolean isForceClosed() {
      return this.forceClosed.get();
   }

   void eventReceived(Watcher.Action action, HasMetadata resource) {
      if (this.receiveBookmarks || action != Action.BOOKMARK) {
         if (resource != null && !this.baseOperation.getType().isAssignableFrom(resource.getClass())) {
            resource = (HasMetadata)this.baseOperation.getKubernetesSerialization().convertValue(resource, this.baseOperation.getType());
         }

         T t = (T)resource;

         try {
            this.watcher.eventReceived(action, t);
         } catch (Exception e) {
            logger.error("Unhandled exception encountered in watcher event handler", e);
         }

      }
   }

   void updateResourceVersion(String newResourceVersion) {
      this.resourceVersion.set(newResourceVersion);
   }

   protected void startWatch() {
      this.listOptions.setResourceVersion((String)this.resourceVersion.get());
      URL url = this.baseOperation.appendListOptionParams(this.requestUrl, this.listOptions);
      String var10000 = this.requestUrl.getProtocol();
      String origin = var10000 + "://" + this.requestUrl.getHost();
      if (this.requestUrl.getPort() != -1) {
         origin = origin + ":" + this.requestUrl.getPort();
      }

      Map<String, String> headers = new HashMap();
      headers.put("Origin", origin);
      logger.debug("Watching {}...", url);
      this.closeRequest();
      this.latestRequestState = new WatchRequestState();
      this.start(url, headers, this.latestRequestState);
   }

   public void close() {
      logger.debug("Force closing the watch");
      this.closeEvent();
      this.closeRequest();
      this.cancelReconnect();
   }

   private WatchEvent contextAwareWatchEventDeserializer(String messageSource) throws JsonProcessingException {
      KubernetesSerialization kubernetesSerialization = this.baseOperation.getKubernetesSerialization();

      try {
         return (WatchEvent)kubernetesSerialization.unmarshal(messageSource, WatchEvent.class);
      } catch (Exception var8) {
         JsonNode json = (JsonNode)kubernetesSerialization.unmarshal(messageSource, JsonNode.class);
         JsonNode objectJson = null;
         if (json instanceof ObjectNode && json.has("object")) {
            objectJson = ((ObjectNode)json).remove("object");
         }

         WatchEvent watchEvent = (WatchEvent)kubernetesSerialization.convertValue(json, WatchEvent.class);
         KubernetesResource object = (KubernetesResource)kubernetesSerialization.convertValue(objectJson, this.baseOperation.getType());
         watchEvent.setObject(object);
         return watchEvent;
      }
   }

   protected void onMessage(String message, WatchRequestState state) {
      this.endErrors.clear();
      if (!state.closed.get() && !this.forceClosed.get()) {
         try {
            WatchEvent event = this.contextAwareWatchEventDeserializer(message);
            Object object = event.getObject();
            Watcher.Action action = Action.valueOf(event.getType());
            if (action == Action.ERROR) {
               if (object instanceof Status) {
                  Status status = (Status)object;
                  this.onStatus(status, state);
               } else {
                  logger.error("Received an error which is not a status but {} - will retry", message);
                  this.closeRequest();
               }
            } else if (object instanceof HasMetadata) {
               HasMetadata hasMetadata = (HasMetadata)object;
               this.updateResourceVersion(hasMetadata.getMetadata().getResourceVersion());
               this.eventReceived(action, hasMetadata);
            } else {
               String msg = String.format("Invalid object received: %s", message);
               this.close(new WatcherException(msg, (Throwable)null, message));
            }
         } catch (ClassCastException e) {
            String msg = "Received wrong type of object for watch";
            this.close(new WatcherException("Received wrong type of object for watch", e, message));
         } catch (JsonProcessingException e) {
            String msg = "Couldn't deserialize watch event: " + message;
            this.close(new WatcherException(msg, e, message));
         } catch (Exception e) {
            String msg = "Unexpected exception processing watch event";
            this.close(new WatcherException("Unexpected exception processing watch event", e, message));
         }

      }
   }

   protected boolean onStatus(Status status, WatchRequestState state) {
      this.endErrors.clear();
      if (state.closed.get()) {
         return true;
      } else if (Integer.valueOf(410).equals(status.getCode())) {
         this.close(new WatcherException(status.getMessage(), new KubernetesClientException(status)));
         return true;
      } else {
         logger.error("Error received: {}, will retry", status);
         this.retryAfterSeconds.set((Integer)Optional.ofNullable(status.getDetails()).map(StatusDetails::getRetryAfterSeconds).orElse(0));
         this.closeRequest();
         return false;
      }
   }

   void watchEnded(Throwable t, WatchRequestState state) {
      state.ended.complete((Object)null);
      if (state != this.latestRequestState) {
         if (t != null) {
            logger.debug("Watch error received after the next watch started", t);
         }

      } else if (t instanceof ProtocolException) {
         this.close(new WatcherException("Could not process Watch response", t));
      } else {
         try {
            if (t != null) {
               this.logEndError(t);
            }
         } finally {
            this.scheduleReconnect(state);
         }

      }
   }

   private void logEndError(Throwable t) {
      int occurrences = (Integer)this.endErrors.compute(t.getClass(), (k, v) -> v == null ? 1 : v + 1);
      if (!(t instanceof IOException) && !(t instanceof KubernetesClientException)) {
         if (occurrences > 1) {
            logger.info("Unknown Watch error received {} times without progress, will reconnect if possible", occurrences, t);
         } else {
            logger.debug("Unknown Watch error received, will reconnect if possible", t);
         }
      } else if (occurrences > 10) {
         logger.info("Watch connection error received {} times without progress, will reconnect if possible", occurrences, t);
      } else {
         logger.debug("Watch connection error, will reconnect if possible", t);
      }

   }

   private static final class SerialWatcher implements Watcher {
      private final Watcher watcher;
      SerialExecutor serialExecutor;

      private SerialWatcher(Watcher watcher, SerialExecutor serialExecutor) {
         this.watcher = watcher;
         this.serialExecutor = serialExecutor;
      }

      public void eventReceived(Watcher.Action action, Object resource) {
         this.serialExecutor.execute(() -> this.watcher.eventReceived(action, resource));
      }

      public void onClose(WatcherException cause) {
         this.serialExecutor.execute(() -> {
            this.watcher.onClose(cause);
            this.serialExecutor.shutdownNow();
         });
      }

      public void onClose() {
         this.serialExecutor.execute(() -> {
            this.watcher.onClose();
            this.serialExecutor.shutdownNow();
         });
      }

      public boolean reconnecting() {
         return this.watcher.reconnecting();
      }
   }

   public static class WatchRequestState {
      final AtomicBoolean reconnected = new AtomicBoolean();
      final AtomicBoolean closed = new AtomicBoolean();
      final CompletableFuture ended = new CompletableFuture();
   }
}
