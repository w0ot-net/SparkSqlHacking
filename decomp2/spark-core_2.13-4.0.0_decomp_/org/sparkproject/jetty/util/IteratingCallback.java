package org.sparkproject.jetty.util;

import java.io.IOException;
import org.sparkproject.jetty.util.thread.AutoLock;

public abstract class IteratingCallback implements Callback {
   private final AutoLock _lock = new AutoLock();
   private State _state;
   private Throwable _failure;
   private boolean _iterate;

   protected IteratingCallback() {
      this._state = IteratingCallback.State.IDLE;
   }

   protected IteratingCallback(boolean needReset) {
      this._state = needReset ? IteratingCallback.State.SUCCEEDED : IteratingCallback.State.IDLE;
   }

   protected abstract Action process() throws Throwable;

   protected void onCompleteSuccess() {
   }

   protected void onCompleteFailure(Throwable cause) {
   }

   public void iterate() {
      boolean process = false;

      try (AutoLock ignored = this._lock.lock()) {
         switch (this._state.ordinal()) {
            case 0:
               this._state = IteratingCallback.State.PROCESSING;
               process = true;
               break;
            case 1:
               this._iterate = true;
            case 2:
            case 3:
            case 4:
            case 5:
               break;
            case 6:
            default:
               throw new IllegalStateException(this.toString());
         }
      }

      if (process) {
         this.processing();
      }

   }

   private void processing() {
      boolean notifyCompleteSuccess = false;
      Throwable notifyCompleteFailure = null;

      while(true) {
         Action action = null;

         try {
            action = this.process();
         } catch (Throwable x) {
            this.failed(x);
         }

         try (AutoLock ignored = this._lock.lock()) {
            label85:
            switch (this._state.ordinal()) {
               case 0:
               case 2:
               default:
                  throw new IllegalStateException(String.format("%s[action=%s]", this, action));
               case 1:
                  if (action != null) {
                     switch (action.ordinal()) {
                        case 0:
                           if (this._iterate) {
                              this._iterate = false;
                              continue;
                           }

                           this._state = IteratingCallback.State.IDLE;
                           break label85;
                        case 1:
                           this._state = IteratingCallback.State.PENDING;
                           break label85;
                        case 2:
                           this._iterate = false;
                           this._state = IteratingCallback.State.SUCCEEDED;
                           notifyCompleteSuccess = true;
                           break label85;
                     }
                  }

                  throw new IllegalStateException(String.format("%s[action=%s]", this, action));
               case 3:
                  if (action != IteratingCallback.Action.SCHEDULED) {
                     throw new IllegalStateException(String.format("%s[action=%s]", this, action));
                  }

                  this._state = IteratingCallback.State.PROCESSING;
                  continue;
               case 4:
                  break;
               case 5:
               case 6:
                  notifyCompleteFailure = this._failure;
                  this._failure = null;
            }
         }

         if (notifyCompleteSuccess) {
            this.onCompleteSuccess();
         } else if (notifyCompleteFailure != null) {
            this.onCompleteFailure(notifyCompleteFailure);
         }

         return;
      }
   }

   public void succeeded() {
      boolean process = false;

      try (AutoLock ignored = this._lock.lock()) {
         switch (this._state.ordinal()) {
            case 1:
               this._state = IteratingCallback.State.CALLED;
               break;
            case 2:
               this._state = IteratingCallback.State.PROCESSING;
               process = true;
               break;
            case 3:
            case 4:
            default:
               throw new IllegalStateException(this.toString());
            case 5:
            case 6:
         }
      }

      if (process) {
         this.processing();
      }

   }

   public void failed(Throwable x) {
      boolean failure = false;

      try (AutoLock ignored = this._lock.lock()) {
         switch (this._state.ordinal()) {
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
               break;
            case 1:
               this._state = IteratingCallback.State.FAILED;
               this._failure = x;
               break;
            case 2:
               this._state = IteratingCallback.State.FAILED;
               failure = true;
               break;
            default:
               throw new IllegalStateException(this.toString());
         }
      }

      if (failure) {
         this.onCompleteFailure(x);
      }

   }

   public void close() {
      String failure = null;

      try (AutoLock ignored = this._lock.lock()) {
         switch (this._state.ordinal()) {
            case 0:
            case 4:
            case 5:
               this._state = IteratingCallback.State.CLOSED;
               break;
            case 1:
               this._failure = new IOException(String.format("Close %s in state %s", this, this._state));
               this._state = IteratingCallback.State.CLOSED;
               break;
            case 2:
            case 3:
            default:
               failure = String.format("Close %s in state %s", this, this._state);
               this._state = IteratingCallback.State.CLOSED;
            case 6:
         }
      }

      if (failure != null) {
         this.onCompleteFailure(new IOException(failure));
      }

   }

   boolean isIdle() {
      boolean var2;
      try (AutoLock ignored = this._lock.lock()) {
         var2 = this._state == IteratingCallback.State.IDLE;
      }

      return var2;
   }

   public boolean isClosed() {
      boolean var2;
      try (AutoLock ignored = this._lock.lock()) {
         var2 = this._state == IteratingCallback.State.CLOSED;
      }

      return var2;
   }

   public boolean isFailed() {
      boolean var2;
      try (AutoLock ignored = this._lock.lock()) {
         var2 = this._state == IteratingCallback.State.FAILED;
      }

      return var2;
   }

   public boolean isSucceeded() {
      boolean var2;
      try (AutoLock ignored = this._lock.lock()) {
         var2 = this._state == IteratingCallback.State.SUCCEEDED;
      }

      return var2;
   }

   public boolean reset() {
      try (AutoLock ignored = this._lock.lock()) {
         switch (this._state.ordinal()) {
            case 0:
               return true;
            case 4:
            case 5:
               this._state = IteratingCallback.State.IDLE;
               this._failure = null;
               this._iterate = false;
               return true;
            default:
               return false;
         }
      }
   }

   public String toString() {
      return String.format("%s@%x[%s]", this.getClass().getSimpleName(), this.hashCode(), this._state);
   }

   private static enum State {
      IDLE,
      PROCESSING,
      PENDING,
      CALLED,
      SUCCEEDED,
      FAILED,
      CLOSED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{IDLE, PROCESSING, PENDING, CALLED, SUCCEEDED, FAILED, CLOSED};
      }
   }

   protected static enum Action {
      IDLE,
      SCHEDULED,
      SUCCEEDED;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{IDLE, SCHEDULED, SUCCEEDED};
      }
   }
}
