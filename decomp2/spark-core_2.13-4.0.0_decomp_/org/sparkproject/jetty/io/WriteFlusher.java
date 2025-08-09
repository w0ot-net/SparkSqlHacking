package org.sparkproject.jetty.io;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritePendingException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.thread.Invocable;

public abstract class WriteFlusher {
   private static final Logger LOG = LoggerFactory.getLogger(WriteFlusher.class);
   private static final boolean DEBUG;
   private static final ByteBuffer[] EMPTY_BUFFERS;
   private static final EnumMap __stateTransitions;
   private static final State __IDLE;
   private static final State __WRITING;
   private static final State __COMPLETING;
   private final EndPoint _endPoint;
   private final AtomicReference _state = new AtomicReference();

   protected WriteFlusher(EndPoint endPoint) {
      this._state.set(__IDLE);
      this._endPoint = endPoint;
   }

   private boolean updateState(State previous, State next) {
      if (!this.isTransitionAllowed(previous, next)) {
         throw new IllegalStateException();
      } else {
         boolean updated = this._state.compareAndSet(previous, next);
         if (DEBUG) {
            LOG.debug("update {}:{}{}{}", new Object[]{this, previous, updated ? "-->" : "!->", next});
         }

         return updated;
      }
   }

   private boolean isTransitionAllowed(State currentState, State newState) {
      Set<StateType> allowedNewStateTypes = (Set)__stateTransitions.get(currentState.getType());
      if (!allowedNewStateTypes.contains(newState.getType())) {
         LOG.warn("{}: {} -> {} not allowed", new Object[]{this, currentState, newState});
         return false;
      } else {
         return true;
      }
   }

   public Invocable.InvocationType getCallbackInvocationType() {
      State s = (State)this._state.get();
      return s instanceof PendingState ? ((PendingState)s).getCallbackInvocationType() : Invocable.InvocationType.BLOCKING;
   }

   protected abstract void onIncompleteFlush();

   public void write(Callback callback, ByteBuffer... buffers) throws WritePendingException {
      this.write(callback, (SocketAddress)null, buffers);
   }

   public void write(Callback callback, SocketAddress address, ByteBuffer... buffers) throws WritePendingException {
      Objects.requireNonNull(callback);
      if (this.isFailed()) {
         this.fail(callback);
      } else {
         if (DEBUG) {
            LOG.debug("write: {} {}", this, BufferUtil.toDetailString(buffers));
         }

         if (!this.updateState(__IDLE, __WRITING)) {
            throw new WritePendingException();
         } else {
            try {
               buffers = this.flush(address, buffers);
               if (buffers != null) {
                  if (DEBUG) {
                     LOG.debug("flush incomplete {}", this);
                  }

                  PendingState pending = new PendingState(callback, address, buffers);
                  if (this.updateState(__WRITING, pending)) {
                     this.onIncompleteFlush();
                  } else {
                     this.fail(callback);
                  }

                  return;
               }

               if (this.updateState(__WRITING, __IDLE)) {
                  callback.succeeded();
               } else {
                  this.fail(callback);
               }
            } catch (Throwable var5) {
               if (DEBUG) {
                  LOG.debug("write exception", var5);
               }

               if (this.updateState(__WRITING, new FailedState(var5))) {
                  callback.failed(var5);
               } else {
                  this.fail(callback, var5);
               }
            }

         }
      }
   }

   private void fail(Callback callback, Throwable... suppressed) {
      while(true) {
         State state = (State)this._state.get();
         Throwable cause;
         switch (state.getType().ordinal()) {
            case 0:
               for(Throwable t : suppressed) {
                  LOG.warn("Failed Write Cause", t);
               }

               return;
            case 4:
               FailedState failed = (FailedState)state;
               cause = failed.getCause();
               break;
            default:
               Throwable t = new IllegalStateException();
               if (!this._state.compareAndSet(state, new FailedState(t))) {
                  continue;
               }

               cause = t;
         }

         for(Throwable t : suppressed) {
            if (t != cause) {
               cause.addSuppressed(t);
            }
         }

         callback.failed(cause);
         return;
      }
   }

   public void completeWrite() {
      if (DEBUG) {
         LOG.debug("completeWrite: {}", this);
      }

      State previous = (State)this._state.get();
      if (previous.getType() == WriteFlusher.StateType.PENDING) {
         PendingState pending = (PendingState)previous;
         if (this.updateState(pending, __COMPLETING)) {
            Callback callback = pending._callback;

            try {
               ByteBuffer[] buffers = pending._buffers;
               SocketAddress address = pending._address;
               buffers = this.flush(address, buffers);
               if (buffers != null) {
                  if (DEBUG) {
                     LOG.debug("flushed incomplete {}", BufferUtil.toDetailString(buffers));
                  }

                  if (buffers != pending._buffers) {
                     pending = new PendingState(callback, address, buffers);
                  }

                  if (this.updateState(__COMPLETING, pending)) {
                     this.onIncompleteFlush();
                  } else {
                     this.fail(callback);
                  }

                  return;
               }

               if (this.updateState(__COMPLETING, __IDLE)) {
                  callback.succeeded();
               } else {
                  this.fail(callback);
               }
            } catch (Throwable var6) {
               if (DEBUG) {
                  LOG.debug("completeWrite exception", var6);
               }

               if (this.updateState(__COMPLETING, new FailedState(var6))) {
                  callback.failed(var6);
               } else {
                  this.fail(callback, var6);
               }
            }

         }
      }
   }

   protected ByteBuffer[] flush(SocketAddress address, ByteBuffer[] buffers) throws IOException {
      boolean progress = true;

      while(progress && buffers != null) {
         long before = BufferUtil.remaining(buffers);
         boolean flushed = address == null ? this._endPoint.flush(buffers) : ((DatagramChannelEndPoint)this._endPoint).send(address, buffers);
         long after = BufferUtil.remaining(buffers);
         long written = before - after;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Flushed={} written={} remaining={} {}", new Object[]{flushed, written, after, this});
         }

         if (written > 0L) {
            Connection connection = this._endPoint.getConnection();
            if (connection instanceof Listener) {
               ((Listener)connection).onFlushed(written);
            }
         }

         if (flushed) {
            return null;
         }

         progress = written > 0L;
         int index = 0;

         while(true) {
            if (index == buffers.length) {
               buffers = null;
               index = 0;
               break;
            }

            int remaining = buffers[index].remaining();
            if (remaining > 0) {
               break;
            }

            ++index;
            progress = true;
         }

         if (index > 0) {
            buffers = (ByteBuffer[])Arrays.copyOfRange(buffers, index, buffers.length);
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("!fully flushed {}", this);
      }

      return buffers == null ? EMPTY_BUFFERS : buffers;
   }

   public boolean onFail(Throwable cause) {
      while(true) {
         State current = (State)this._state.get();
         switch (current.getType().ordinal()) {
            case 0:
            case 4:
               if (DEBUG) {
                  LOG.debug("ignored: {} {}", cause, this);
                  LOG.trace("IGNORED", cause);
               }

               return false;
            case 1:
            case 3:
               if (DEBUG) {
                  LOG.debug("failed: {}", this, cause);
               }

               if (!this.updateState(current, new FailedState(cause))) {
                  break;
               }

               return true;
            case 2:
               if (DEBUG) {
                  LOG.debug("failed: {}", this, cause);
               }

               PendingState pending = (PendingState)current;
               if (!this.updateState(pending, new FailedState(cause))) {
                  break;
               }

               pending._callback.failed(cause);
               return true;
            default:
               throw new IllegalStateException();
         }
      }
   }

   public void onClose() {
      switch (((State)this._state.get()).getType().ordinal()) {
         case 0:
         case 4:
            return;
         default:
            this.onFail(new ClosedChannelException());
      }
   }

   boolean isFailed() {
      return this.isState(WriteFlusher.StateType.FAILED);
   }

   boolean isIdle() {
      return this.isState(WriteFlusher.StateType.IDLE);
   }

   public boolean isPending() {
      return this.isState(WriteFlusher.StateType.PENDING);
   }

   private boolean isState(StateType type) {
      return ((State)this._state.get()).getType() == type;
   }

   public String toStateString() {
      switch (((State)this._state.get()).getType().ordinal()) {
         case 0:
            return "-";
         case 1:
            return "W";
         case 2:
            return "P";
         case 3:
            return "C";
         case 4:
            return "F";
         default:
            return "?";
      }
   }

   public String toString() {
      State s = (State)this._state.get();
      return String.format("WriteFlusher@%x{%s}->%s", this.hashCode(), s, s instanceof PendingState ? ((PendingState)s)._callback : null);
   }

   static {
      DEBUG = LOG.isDebugEnabled();
      EMPTY_BUFFERS = new ByteBuffer[]{BufferUtil.EMPTY_BUFFER};
      __stateTransitions = new EnumMap(StateType.class);
      __IDLE = new IdleState();
      __WRITING = new WritingState();
      __COMPLETING = new CompletingState();
      __stateTransitions.put(WriteFlusher.StateType.IDLE, EnumSet.of(WriteFlusher.StateType.WRITING));
      __stateTransitions.put(WriteFlusher.StateType.WRITING, EnumSet.of(WriteFlusher.StateType.IDLE, WriteFlusher.StateType.PENDING, WriteFlusher.StateType.FAILED));
      __stateTransitions.put(WriteFlusher.StateType.PENDING, EnumSet.of(WriteFlusher.StateType.COMPLETING, WriteFlusher.StateType.IDLE, WriteFlusher.StateType.FAILED));
      __stateTransitions.put(WriteFlusher.StateType.COMPLETING, EnumSet.of(WriteFlusher.StateType.IDLE, WriteFlusher.StateType.PENDING, WriteFlusher.StateType.FAILED));
      __stateTransitions.put(WriteFlusher.StateType.FAILED, EnumSet.noneOf(StateType.class));
   }

   private static enum StateType {
      IDLE,
      WRITING,
      PENDING,
      COMPLETING,
      FAILED;

      // $FF: synthetic method
      private static StateType[] $values() {
         return new StateType[]{IDLE, WRITING, PENDING, COMPLETING, FAILED};
      }
   }

   private static class State {
      private final StateType _type;

      private State(StateType stateType) {
         this._type = stateType;
      }

      public StateType getType() {
         return this._type;
      }

      public String toString() {
         return String.format("%s", this._type);
      }
   }

   private static class IdleState extends State {
      private IdleState() {
         super(WriteFlusher.StateType.IDLE);
      }
   }

   private static class WritingState extends State {
      private WritingState() {
         super(WriteFlusher.StateType.WRITING);
      }
   }

   private static class FailedState extends State {
      private final Throwable _cause;

      private FailedState(Throwable cause) {
         super(WriteFlusher.StateType.FAILED);
         this._cause = cause;
      }

      public Throwable getCause() {
         return this._cause;
      }
   }

   private static class CompletingState extends State {
      private CompletingState() {
         super(WriteFlusher.StateType.COMPLETING);
      }
   }

   private class PendingState extends State {
      private final Callback _callback;
      private final SocketAddress _address;
      private final ByteBuffer[] _buffers;

      private PendingState(Callback callback, SocketAddress address, ByteBuffer[] buffers) {
         super(WriteFlusher.StateType.PENDING);
         this._callback = callback;
         this._address = address;
         this._buffers = buffers;
      }

      Invocable.InvocationType getCallbackInvocationType() {
         return Invocable.getInvocationType(this._callback);
      }
   }

   public interface Listener {
      void onFlushed(long var1) throws IOException;
   }
}
