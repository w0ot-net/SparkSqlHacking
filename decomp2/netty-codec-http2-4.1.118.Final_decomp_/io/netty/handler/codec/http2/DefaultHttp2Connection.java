package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseNotifier;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DefaultHttp2Connection implements Http2Connection {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(DefaultHttp2Connection.class);
   final IntObjectMap streamMap;
   final PropertyKeyRegistry propertyKeyRegistry;
   final ConnectionStream connectionStream;
   final DefaultEndpoint localEndpoint;
   final DefaultEndpoint remoteEndpoint;
   final List listeners;
   final ActiveStreams activeStreams;
   Promise closePromise;

   public DefaultHttp2Connection(boolean server) {
      this(server, 100);
   }

   public DefaultHttp2Connection(boolean server, int maxReservedStreams) {
      this.streamMap = new IntObjectHashMap();
      this.propertyKeyRegistry = new PropertyKeyRegistry();
      this.connectionStream = new ConnectionStream();
      this.listeners = new ArrayList(4);
      this.activeStreams = new ActiveStreams(this.listeners);
      this.localEndpoint = new DefaultEndpoint(server, server ? Integer.MAX_VALUE : maxReservedStreams);
      this.remoteEndpoint = new DefaultEndpoint(!server, maxReservedStreams);
      this.streamMap.put(this.connectionStream.id(), this.connectionStream);
   }

   final boolean isClosed() {
      return this.closePromise != null;
   }

   public Future close(Promise promise) {
      ObjectUtil.checkNotNull(promise, "promise");
      if (this.closePromise != null) {
         if (this.closePromise != promise) {
            if (promise instanceof ChannelPromise && ((ChannelFuture)this.closePromise).isVoid()) {
               this.closePromise = promise;
            } else {
               PromiseNotifier.cascade(this.closePromise, promise);
            }
         }
      } else {
         this.closePromise = promise;
      }

      if (this.isStreamMapEmpty()) {
         promise.trySuccess((Object)null);
         return promise;
      } else {
         Iterator<IntObjectMap.PrimitiveEntry<Http2Stream>> itr = this.streamMap.entries().iterator();
         if (this.activeStreams.allowModifications()) {
            this.activeStreams.incrementPendingIterations();

            try {
               while(itr.hasNext()) {
                  DefaultStream stream = (DefaultStream)((IntObjectMap.PrimitiveEntry)itr.next()).value();
                  if (stream.id() != 0) {
                     stream.close(itr);
                  }
               }
            } finally {
               this.activeStreams.decrementPendingIterations();
            }
         } else {
            while(itr.hasNext()) {
               Http2Stream stream = (Http2Stream)((IntObjectMap.PrimitiveEntry)itr.next()).value();
               if (stream.id() != 0) {
                  stream.close();
               }
            }
         }

         return this.closePromise;
      }
   }

   public void addListener(Http2Connection.Listener listener) {
      this.listeners.add(listener);
   }

   public void removeListener(Http2Connection.Listener listener) {
      this.listeners.remove(listener);
   }

   public boolean isServer() {
      return this.localEndpoint.isServer();
   }

   public Http2Stream connectionStream() {
      return this.connectionStream;
   }

   public Http2Stream stream(int streamId) {
      return (Http2Stream)this.streamMap.get(streamId);
   }

   public boolean streamMayHaveExisted(int streamId) {
      return this.remoteEndpoint.mayHaveCreatedStream(streamId) || this.localEndpoint.mayHaveCreatedStream(streamId);
   }

   public int numActiveStreams() {
      return this.activeStreams.size();
   }

   public Http2Stream forEachActiveStream(Http2StreamVisitor visitor) throws Http2Exception {
      return this.activeStreams.forEachActiveStream(visitor);
   }

   public Http2Connection.Endpoint local() {
      return this.localEndpoint;
   }

   public Http2Connection.Endpoint remote() {
      return this.remoteEndpoint;
   }

   public boolean goAwayReceived() {
      return this.localEndpoint.lastStreamKnownByPeer >= 0;
   }

   public void goAwayReceived(int lastKnownStream, long errorCode, ByteBuf debugData) throws Http2Exception {
      if (this.localEndpoint.lastStreamKnownByPeer() >= 0 && this.localEndpoint.lastStreamKnownByPeer() < lastKnownStream) {
         throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "lastStreamId MUST NOT increase. Current value: %d new value: %d", this.localEndpoint.lastStreamKnownByPeer(), lastKnownStream);
      } else {
         this.localEndpoint.lastStreamKnownByPeer(lastKnownStream);

         for(int i = 0; i < this.listeners.size(); ++i) {
            try {
               ((Http2Connection.Listener)this.listeners.get(i)).onGoAwayReceived(lastKnownStream, errorCode, debugData);
            } catch (Throwable cause) {
               logger.error("Caught Throwable from listener onGoAwayReceived.", cause);
            }
         }

         this.closeStreamsGreaterThanLastKnownStreamId(lastKnownStream, this.localEndpoint);
      }
   }

   public boolean goAwaySent() {
      return this.remoteEndpoint.lastStreamKnownByPeer >= 0;
   }

   public boolean goAwaySent(int lastKnownStream, long errorCode, ByteBuf debugData) throws Http2Exception {
      if (this.remoteEndpoint.lastStreamKnownByPeer() >= 0) {
         if (lastKnownStream == this.remoteEndpoint.lastStreamKnownByPeer()) {
            return false;
         }

         if (lastKnownStream > this.remoteEndpoint.lastStreamKnownByPeer()) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Last stream identifier must not increase between sending multiple GOAWAY frames (was '%d', is '%d').", this.remoteEndpoint.lastStreamKnownByPeer(), lastKnownStream);
         }
      }

      this.remoteEndpoint.lastStreamKnownByPeer(lastKnownStream);

      for(int i = 0; i < this.listeners.size(); ++i) {
         try {
            ((Http2Connection.Listener)this.listeners.get(i)).onGoAwaySent(lastKnownStream, errorCode, debugData);
         } catch (Throwable cause) {
            logger.error("Caught Throwable from listener onGoAwaySent.", cause);
         }
      }

      this.closeStreamsGreaterThanLastKnownStreamId(lastKnownStream, this.remoteEndpoint);
      return true;
   }

   private void closeStreamsGreaterThanLastKnownStreamId(final int lastKnownStream, final DefaultEndpoint endpoint) throws Http2Exception {
      this.forEachActiveStream(new Http2StreamVisitor() {
         public boolean visit(Http2Stream stream) {
            if (stream.id() > lastKnownStream && endpoint.isValidStreamId(stream.id())) {
               stream.close();
            }

            return true;
         }
      });
   }

   private boolean isStreamMapEmpty() {
      return this.streamMap.size() == 1;
   }

   void removeStream(DefaultStream stream, Iterator itr) {
      boolean removed;
      if (itr == null) {
         removed = this.streamMap.remove(stream.id()) != null;
      } else {
         itr.remove();
         removed = true;
      }

      if (removed) {
         for(int i = 0; i < this.listeners.size(); ++i) {
            try {
               ((Http2Connection.Listener)this.listeners.get(i)).onStreamRemoved(stream);
            } catch (Throwable cause) {
               logger.error("Caught Throwable from listener onStreamRemoved.", cause);
            }
         }

         if (this.closePromise != null && this.isStreamMapEmpty()) {
            this.closePromise.trySuccess((Object)null);
         }
      }

   }

   static Http2Stream.State activeState(int streamId, Http2Stream.State initialState, boolean isLocal, boolean halfClosed) throws Http2Exception {
      switch (initialState) {
         case IDLE:
            return halfClosed ? (isLocal ? Http2Stream.State.HALF_CLOSED_LOCAL : Http2Stream.State.HALF_CLOSED_REMOTE) : Http2Stream.State.OPEN;
         case RESERVED_LOCAL:
            return Http2Stream.State.HALF_CLOSED_REMOTE;
         case RESERVED_REMOTE:
            return Http2Stream.State.HALF_CLOSED_LOCAL;
         default:
            throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Attempting to open a stream in an invalid state: " + initialState);
      }
   }

   void notifyHalfClosed(Http2Stream stream) {
      for(int i = 0; i < this.listeners.size(); ++i) {
         try {
            ((Http2Connection.Listener)this.listeners.get(i)).onStreamHalfClosed(stream);
         } catch (Throwable cause) {
            logger.error("Caught Throwable from listener onStreamHalfClosed.", cause);
         }
      }

   }

   void notifyClosed(Http2Stream stream) {
      for(int i = 0; i < this.listeners.size(); ++i) {
         try {
            ((Http2Connection.Listener)this.listeners.get(i)).onStreamClosed(stream);
         } catch (Throwable cause) {
            logger.error("Caught Throwable from listener onStreamClosed.", cause);
         }
      }

   }

   public Http2Connection.PropertyKey newKey() {
      return this.propertyKeyRegistry.newKey();
   }

   final DefaultPropertyKey verifyKey(Http2Connection.PropertyKey key) {
      return ((DefaultPropertyKey)ObjectUtil.checkNotNull((DefaultPropertyKey)key, "key")).verifyConnection(this);
   }

   private class DefaultStream implements Http2Stream {
      private static final byte META_STATE_SENT_RST = 1;
      private static final byte META_STATE_SENT_HEADERS = 2;
      private static final byte META_STATE_SENT_TRAILERS = 4;
      private static final byte META_STATE_SENT_PUSHPROMISE = 8;
      private static final byte META_STATE_RECV_HEADERS = 16;
      private static final byte META_STATE_RECV_TRAILERS = 32;
      private final int id;
      private final long identity;
      private final PropertyMap properties = new PropertyMap();
      private Http2Stream.State state;
      private byte metaState;

      DefaultStream(long identity, int id, Http2Stream.State state) {
         this.identity = identity;
         this.id = id;
         this.state = state;
      }

      public final int id() {
         return this.id;
      }

      public final Http2Stream.State state() {
         return this.state;
      }

      public boolean isResetSent() {
         return (this.metaState & 1) != 0;
      }

      public Http2Stream resetSent() {
         this.metaState = (byte)(this.metaState | 1);
         return this;
      }

      public Http2Stream headersSent(boolean isInformational) {
         if (!isInformational) {
            this.metaState = (byte)(this.metaState | (this.isHeadersSent() ? 4 : 2));
         }

         return this;
      }

      public boolean isHeadersSent() {
         return (this.metaState & 2) != 0;
      }

      public boolean isTrailersSent() {
         return (this.metaState & 4) != 0;
      }

      public Http2Stream headersReceived(boolean isInformational) {
         if (!isInformational) {
            this.metaState = (byte)(this.metaState | (this.isHeadersReceived() ? 32 : 16));
         }

         return this;
      }

      public boolean isHeadersReceived() {
         return (this.metaState & 16) != 0;
      }

      public boolean isTrailersReceived() {
         return (this.metaState & 32) != 0;
      }

      public Http2Stream pushPromiseSent() {
         this.metaState = (byte)(this.metaState | 8);
         return this;
      }

      public boolean isPushPromiseSent() {
         return (this.metaState & 8) != 0;
      }

      public final Object setProperty(Http2Connection.PropertyKey key, Object value) {
         return this.properties.add(DefaultHttp2Connection.this.verifyKey(key), value);
      }

      public final Object getProperty(Http2Connection.PropertyKey key) {
         return this.properties.get(DefaultHttp2Connection.this.verifyKey(key));
      }

      public final Object removeProperty(Http2Connection.PropertyKey key) {
         return this.properties.remove(DefaultHttp2Connection.this.verifyKey(key));
      }

      public Http2Stream open(boolean halfClosed) throws Http2Exception {
         this.state = DefaultHttp2Connection.activeState(this.id, this.state, this.isLocal(), halfClosed);
         DefaultEndpoint<? extends Http2FlowController> endpoint = this.createdBy();
         if (!endpoint.canOpenStream()) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Maximum active streams violated for this endpoint: " + endpoint.maxActiveStreams());
         } else {
            this.activate();
            return this;
         }
      }

      void activate() {
         if (this.state == Http2Stream.State.HALF_CLOSED_LOCAL) {
            this.headersSent(false);
         } else if (this.state == Http2Stream.State.HALF_CLOSED_REMOTE) {
            this.headersReceived(false);
         }

         DefaultHttp2Connection.this.activeStreams.activate(this);
      }

      Http2Stream close(Iterator itr) {
         if (this.state == Http2Stream.State.CLOSED) {
            return this;
         } else {
            this.state = Http2Stream.State.CLOSED;
            --this.createdBy().numStreams;
            DefaultHttp2Connection.this.activeStreams.deactivate(this, itr);
            return this;
         }
      }

      public Http2Stream close() {
         return this.close((Iterator)null);
      }

      public Http2Stream closeLocalSide() {
         switch (this.state) {
            case OPEN:
               this.state = Http2Stream.State.HALF_CLOSED_LOCAL;
               DefaultHttp2Connection.this.notifyHalfClosed(this);
            case HALF_CLOSED_LOCAL:
               break;
            default:
               this.close();
         }

         return this;
      }

      public Http2Stream closeRemoteSide() {
         switch (this.state) {
            case OPEN:
               this.state = Http2Stream.State.HALF_CLOSED_REMOTE;
               DefaultHttp2Connection.this.notifyHalfClosed(this);
            case HALF_CLOSED_REMOTE:
               break;
            default:
               this.close();
         }

         return this;
      }

      DefaultEndpoint createdBy() {
         return DefaultHttp2Connection.this.localEndpoint.isValidStreamId(this.id) ? DefaultHttp2Connection.this.localEndpoint : DefaultHttp2Connection.this.remoteEndpoint;
      }

      final boolean isLocal() {
         return DefaultHttp2Connection.this.localEndpoint.isValidStreamId(this.id);
      }

      public boolean equals(Object obj) {
         return super.equals(obj);
      }

      public int hashCode() {
         long value = this.identity;
         return value == 0L ? System.identityHashCode(this) : (int)(value ^ value >>> 32);
      }

      private class PropertyMap {
         Object[] values;

         private PropertyMap() {
            this.values = EmptyArrays.EMPTY_OBJECTS;
         }

         Object add(DefaultPropertyKey key, Object value) {
            this.resizeIfNecessary(key.index);
            V prevValue = (V)this.values[key.index];
            this.values[key.index] = value;
            return prevValue;
         }

         Object get(DefaultPropertyKey key) {
            return key.index >= this.values.length ? null : this.values[key.index];
         }

         Object remove(DefaultPropertyKey key) {
            V prevValue = (V)null;
            if (key.index < this.values.length) {
               prevValue = (V)this.values[key.index];
               this.values[key.index] = null;
            }

            return prevValue;
         }

         void resizeIfNecessary(int index) {
            if (index >= this.values.length) {
               this.values = Arrays.copyOf(this.values, DefaultHttp2Connection.this.propertyKeyRegistry.size());
            }

         }
      }
   }

   private final class ConnectionStream extends DefaultStream {
      ConnectionStream() {
         super(0L, 0, Http2Stream.State.IDLE);
      }

      public boolean isResetSent() {
         return false;
      }

      DefaultEndpoint createdBy() {
         return null;
      }

      public Http2Stream resetSent() {
         throw new UnsupportedOperationException();
      }

      public Http2Stream open(boolean halfClosed) {
         throw new UnsupportedOperationException();
      }

      public Http2Stream close() {
         throw new UnsupportedOperationException();
      }

      public Http2Stream closeLocalSide() {
         throw new UnsupportedOperationException();
      }

      public Http2Stream closeRemoteSide() {
         throw new UnsupportedOperationException();
      }

      public Http2Stream headersSent(boolean isInformational) {
         throw new UnsupportedOperationException();
      }

      public boolean isHeadersSent() {
         throw new UnsupportedOperationException();
      }

      public Http2Stream pushPromiseSent() {
         throw new UnsupportedOperationException();
      }

      public boolean isPushPromiseSent() {
         throw new UnsupportedOperationException();
      }
   }

   private final class DefaultEndpoint implements Http2Connection.Endpoint {
      private final boolean server;
      private long lastCreatedStreamIdentity = 0L;
      private int nextStreamIdToCreate;
      private int nextReservationStreamId;
      private int lastStreamKnownByPeer = -1;
      private boolean pushToAllowed;
      private Http2FlowController flowController;
      private int maxStreams;
      private int maxActiveStreams;
      private final int maxReservedStreams;
      int numActiveStreams;
      int numStreams;

      DefaultEndpoint(boolean server, int maxReservedStreams) {
         this.server = server;
         if (server) {
            this.nextStreamIdToCreate = 2;
            this.nextReservationStreamId = 0;
         } else {
            this.nextStreamIdToCreate = 1;
            this.nextReservationStreamId = 1;
         }

         this.pushToAllowed = !server;
         this.maxActiveStreams = Integer.MAX_VALUE;
         this.maxReservedStreams = ObjectUtil.checkPositiveOrZero(maxReservedStreams, "maxReservedStreams");
         this.updateMaxStreams();
      }

      public int incrementAndGetNextStreamId() {
         return this.nextReservationStreamId >= 0 ? (this.nextReservationStreamId += 2) : this.nextReservationStreamId;
      }

      private void incrementExpectedStreamId(int streamId) {
         if (streamId > this.nextReservationStreamId && this.nextReservationStreamId >= 0) {
            this.nextReservationStreamId = streamId;
         }

         this.nextStreamIdToCreate = streamId + 2;
         ++this.numStreams;
      }

      public boolean isValidStreamId(int streamId) {
         return streamId > 0 && this.server == ((streamId & 1) == 0);
      }

      public boolean mayHaveCreatedStream(int streamId) {
         return this.isValidStreamId(streamId) && streamId <= this.lastStreamCreated();
      }

      public boolean canOpenStream() {
         return this.numActiveStreams < this.maxActiveStreams;
      }

      public DefaultStream createStream(int streamId, boolean halfClosed) throws Http2Exception {
         Http2Stream.State state = DefaultHttp2Connection.activeState(streamId, Http2Stream.State.IDLE, this.isLocal(), halfClosed);
         this.checkNewStreamAllowed(streamId, state);
         ++this.lastCreatedStreamIdentity;
         DefaultStream stream = DefaultHttp2Connection.this.new DefaultStream(this.lastCreatedStreamIdentity, streamId, state);
         this.incrementExpectedStreamId(streamId);
         this.addStream(stream);
         stream.activate();
         return stream;
      }

      public boolean created(Http2Stream stream) {
         return stream instanceof DefaultStream && ((DefaultStream)stream).createdBy() == this;
      }

      public boolean isServer() {
         return this.server;
      }

      public DefaultStream reservePushStream(int streamId, Http2Stream parent) throws Http2Exception {
         if (parent == null) {
            throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Parent stream missing");
         } else {
            if (this.isLocal()) {
               if (!parent.state().localSideOpen()) {
                  throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream %d is not open for sending push promise", parent.id());
               }
            } else if (!parent.state().remoteSideOpen()) {
               throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Stream %d is not open for sending push promise", parent.id());
            }

            if (!this.opposite().allowPushTo()) {
               throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Server push not allowed to opposite endpoint");
            } else {
               Http2Stream.State state = this.isLocal() ? Http2Stream.State.RESERVED_LOCAL : Http2Stream.State.RESERVED_REMOTE;
               this.checkNewStreamAllowed(streamId, state);
               ++this.lastCreatedStreamIdentity;
               DefaultStream stream = DefaultHttp2Connection.this.new DefaultStream(this.lastCreatedStreamIdentity, streamId, state);
               this.incrementExpectedStreamId(streamId);
               this.addStream(stream);
               return stream;
            }
         }
      }

      private void addStream(DefaultStream stream) {
         DefaultHttp2Connection.this.streamMap.put(stream.id(), stream);

         for(int i = 0; i < DefaultHttp2Connection.this.listeners.size(); ++i) {
            try {
               ((Http2Connection.Listener)DefaultHttp2Connection.this.listeners.get(i)).onStreamAdded(stream);
            } catch (Throwable cause) {
               DefaultHttp2Connection.logger.error("Caught Throwable from listener onStreamAdded.", cause);
            }
         }

      }

      public void allowPushTo(boolean allow) {
         if (allow && this.server) {
            throw new IllegalArgumentException("Servers do not allow push");
         } else {
            this.pushToAllowed = allow;
         }
      }

      public boolean allowPushTo() {
         return this.pushToAllowed;
      }

      public int numActiveStreams() {
         return this.numActiveStreams;
      }

      public int maxActiveStreams() {
         return this.maxActiveStreams;
      }

      public void maxActiveStreams(int maxActiveStreams) {
         this.maxActiveStreams = maxActiveStreams;
         this.updateMaxStreams();
      }

      public int lastStreamCreated() {
         return Math.max(0, this.nextStreamIdToCreate - 2);
      }

      public int lastStreamKnownByPeer() {
         return this.lastStreamKnownByPeer;
      }

      private void lastStreamKnownByPeer(int lastKnownStream) {
         this.lastStreamKnownByPeer = lastKnownStream;
      }

      public Http2FlowController flowController() {
         return this.flowController;
      }

      public void flowController(Http2FlowController flowController) {
         this.flowController = (Http2FlowController)ObjectUtil.checkNotNull(flowController, "flowController");
      }

      public Http2Connection.Endpoint opposite() {
         return this.isLocal() ? DefaultHttp2Connection.this.remoteEndpoint : DefaultHttp2Connection.this.localEndpoint;
      }

      private void updateMaxStreams() {
         this.maxStreams = (int)Math.min(2147483647L, (long)this.maxActiveStreams + (long)this.maxReservedStreams);
      }

      private void checkNewStreamAllowed(int streamId, Http2Stream.State state) throws Http2Exception {
         assert state != Http2Stream.State.IDLE;

         if (this.lastStreamKnownByPeer >= 0 && streamId > this.lastStreamKnownByPeer) {
            throw Http2Exception.streamError(streamId, Http2Error.REFUSED_STREAM, "Cannot create stream %d greater than Last-Stream-ID %d from GOAWAY.", streamId, this.lastStreamKnownByPeer);
         } else if (!this.isValidStreamId(streamId)) {
            if (streamId < 0) {
               throw new Http2NoMoreStreamIdsException();
            } else {
               throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Request stream %d is not correct for %s connection", streamId, this.server ? "server" : "client");
            }
         } else if (streamId < this.nextStreamIdToCreate) {
            throw Http2Exception.closedStreamError(Http2Error.PROTOCOL_ERROR, "Request stream %d is behind the next expected stream %d", streamId, this.nextStreamIdToCreate);
         } else if (this.nextStreamIdToCreate <= 0) {
            throw new Http2Exception(Http2Error.REFUSED_STREAM, "Stream IDs are exhausted for this endpoint.", Http2Exception.ShutdownHint.GRACEFUL_SHUTDOWN);
         } else {
            boolean isReserved = state == Http2Stream.State.RESERVED_LOCAL || state == Http2Stream.State.RESERVED_REMOTE;
            if ((isReserved || this.canOpenStream()) && (!isReserved || this.numStreams < this.maxStreams)) {
               if (DefaultHttp2Connection.this.isClosed()) {
                  throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Attempted to create stream id %d after connection was closed", streamId);
               }
            } else {
               throw Http2Exception.streamError(streamId, Http2Error.REFUSED_STREAM, "Maximum active streams violated for this endpoint: " + (isReserved ? this.maxStreams : this.maxActiveStreams));
            }
         }
      }

      private boolean isLocal() {
         return this == DefaultHttp2Connection.this.localEndpoint;
      }
   }

   private final class ActiveStreams {
      private final List listeners;
      private final Queue pendingEvents = new ArrayDeque(4);
      private final Set streams = new LinkedHashSet();
      private int pendingIterations;

      ActiveStreams(List listeners) {
         this.listeners = listeners;
      }

      public int size() {
         return this.streams.size();
      }

      public void activate(final DefaultStream stream) {
         if (this.allowModifications()) {
            this.addToActiveStreams(stream);
         } else {
            this.pendingEvents.add(new Event() {
               public void process() {
                  ActiveStreams.this.addToActiveStreams(stream);
               }
            });
         }

      }

      public void deactivate(final DefaultStream stream, final Iterator itr) {
         if (!this.allowModifications() && itr == null) {
            this.pendingEvents.add(new Event() {
               public void process() {
                  ActiveStreams.this.removeFromActiveStreams(stream, itr);
               }
            });
         } else {
            this.removeFromActiveStreams(stream, itr);
         }

      }

      public Http2Stream forEachActiveStream(Http2StreamVisitor visitor) throws Http2Exception {
         this.incrementPendingIterations();

         Http2Stream var4;
         try {
            Iterator var2 = this.streams.iterator();

            Http2Stream stream;
            do {
               if (!var2.hasNext()) {
                  Object var8 = null;
                  return (Http2Stream)var8;
               }

               stream = (Http2Stream)var2.next();
            } while(visitor.visit(stream));

            var4 = stream;
         } finally {
            this.decrementPendingIterations();
         }

         return var4;
      }

      void addToActiveStreams(DefaultStream stream) {
         if (this.streams.add(stream)) {
            ++stream.createdBy().numActiveStreams;

            for(int i = 0; i < this.listeners.size(); ++i) {
               try {
                  ((Http2Connection.Listener)this.listeners.get(i)).onStreamActive(stream);
               } catch (Throwable cause) {
                  DefaultHttp2Connection.logger.error("Caught Throwable from listener onStreamActive.", cause);
               }
            }
         }

      }

      void removeFromActiveStreams(DefaultStream stream, Iterator itr) {
         if (this.streams.remove(stream)) {
            --stream.createdBy().numActiveStreams;
            DefaultHttp2Connection.this.notifyClosed(stream);
         }

         DefaultHttp2Connection.this.removeStream(stream, itr);
      }

      boolean allowModifications() {
         return this.pendingIterations == 0;
      }

      void incrementPendingIterations() {
         ++this.pendingIterations;
      }

      void decrementPendingIterations() {
         --this.pendingIterations;
         if (this.allowModifications()) {
            while(true) {
               Event event = (Event)this.pendingEvents.poll();
               if (event == null) {
                  break;
               }

               try {
                  event.process();
               } catch (Throwable cause) {
                  DefaultHttp2Connection.logger.error("Caught Throwable while processing pending ActiveStreams$Event.", cause);
               }
            }
         }

      }
   }

   final class DefaultPropertyKey implements Http2Connection.PropertyKey {
      final int index;

      DefaultPropertyKey(int index) {
         this.index = index;
      }

      DefaultPropertyKey verifyConnection(Http2Connection connection) {
         if (connection != DefaultHttp2Connection.this) {
            throw new IllegalArgumentException("Using a key that was not created by this connection");
         } else {
            return this;
         }
      }
   }

   private final class PropertyKeyRegistry {
      final List keys;

      private PropertyKeyRegistry() {
         this.keys = new ArrayList(4);
      }

      DefaultPropertyKey newKey() {
         DefaultPropertyKey key = DefaultHttp2Connection.this.new DefaultPropertyKey(this.keys.size());
         this.keys.add(key);
         return key;
      }

      int size() {
         return this.keys.size();
      }
   }

   interface Event {
      void process();
   }
}
