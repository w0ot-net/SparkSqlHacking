package org.apache.curator.framework.state;

import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CircuitBreakingConnectionStateListener implements ConnectionStateListener {
   private final Logger log;
   private final CuratorFramework client;
   private final ConnectionStateListener listener;
   private final CircuitBreaker circuitBreaker;
   private boolean circuitLostHasBeenSent;
   private ConnectionState circuitLastState;
   private ConnectionState circuitInitialState;

   public CircuitBreakingConnectionStateListener(CuratorFramework client, ConnectionStateListener listener, RetryPolicy retryPolicy) {
      this(client, listener, CircuitBreaker.build(retryPolicy));
   }

   public CircuitBreakingConnectionStateListener(CuratorFramework client, ConnectionStateListener listener, RetryPolicy retryPolicy, ScheduledExecutorService service) {
      this(client, listener, CircuitBreaker.build(retryPolicy, service));
   }

   CircuitBreakingConnectionStateListener(CuratorFramework client, ConnectionStateListener listener, CircuitBreaker circuitBreaker) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.client = (CuratorFramework)Objects.requireNonNull(client, "client cannot be null");
      this.listener = (ConnectionStateListener)Objects.requireNonNull(listener, "listener cannot be null");
      this.circuitBreaker = (CircuitBreaker)Objects.requireNonNull(circuitBreaker, "circuitBreaker cannot be null");
      this.reset();
   }

   public synchronized void stateChanged(CuratorFramework client, ConnectionState newState) {
      if (this.circuitBreaker.isOpen()) {
         this.handleOpenStateChange(newState);
      } else {
         this.handleClosedStateChange(newState);
      }

   }

   public synchronized boolean isOpen() {
      return this.circuitBreaker.isOpen();
   }

   private synchronized void handleClosedStateChange(ConnectionState newState) {
      if (!newState.isConnected()) {
         if (this.circuitBreaker.tryToOpen(this::checkCloseCircuit)) {
            this.log.info("Circuit is opening. State: {} post-retryCount: {}", newState, this.circuitBreaker.getRetryCount());
            this.circuitLastState = this.circuitInitialState = newState;
            this.circuitLostHasBeenSent = newState == ConnectionState.LOST;
         } else {
            this.log.debug("Could not open circuit breaker. State: {}", newState);
         }
      }

      this.callListener(newState);
   }

   private synchronized void handleOpenStateChange(ConnectionState newState) {
      if (!this.circuitLostHasBeenSent && newState == ConnectionState.LOST) {
         this.log.debug("Circuit is open. State changed to LOST. Sending to listener.");
         this.circuitLostHasBeenSent = true;
         this.circuitLastState = this.circuitInitialState = ConnectionState.LOST;
         this.callListener(ConnectionState.LOST);
      } else {
         this.log.debug("Circuit is open. Ignoring state change: {}", newState);
         this.circuitLastState = newState;
      }

   }

   private synchronized void checkCloseCircuit() {
      if (this.circuitLastState != null && !this.circuitLastState.isConnected()) {
         if (this.circuitBreaker.tryToRetry(this::checkCloseCircuit)) {
            this.log.debug("Circuit open is continuing due to retry. State: {} post-retryCount: {}", this.circuitLastState, this.circuitBreaker.getRetryCount());
         } else {
            this.log.info("Circuit is closing due to retries exhausted. Initial state: {} - Last state: {}", this.circuitInitialState, this.circuitLastState);
            this.closeCircuit();
         }
      } else {
         this.log.info("Circuit is closing. Initial state: {} - Last state: {}", this.circuitInitialState, this.circuitLastState);
         this.closeCircuit();
      }

   }

   private synchronized void callListener(ConnectionState newState) {
      if (newState != null) {
         this.listener.stateChanged(this.client, newState);
      }

   }

   private synchronized void closeCircuit() {
      ConnectionState stateToSend = this.circuitLastState == this.circuitInitialState ? null : this.circuitLastState;
      this.reset();
      this.callListener(stateToSend);
   }

   private synchronized void reset() {
      this.circuitLastState = null;
      this.circuitInitialState = null;
      this.circuitLostHasBeenSent = false;
      this.circuitBreaker.close();
   }
}
