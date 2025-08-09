package org.apache.spark.network.server;

import io.netty.channel.Channel;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.client.TransportClient;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Preconditions;

public class OneForOneStreamManager extends StreamManager {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(OneForOneStreamManager.class);
   private final AtomicLong nextStreamId = new AtomicLong((long)(new Random()).nextInt(Integer.MAX_VALUE) * 1000L);
   private final ConcurrentHashMap streams = new ConcurrentHashMap();

   public ManagedBuffer getChunk(long streamId, int chunkIndex) {
      StreamState state = (StreamState)this.streams.get(streamId);
      if (state == null) {
         throw new IllegalStateException(String.format("Requested chunk not available since streamId %s is closed", streamId));
      } else if (chunkIndex != state.curChunk) {
         throw new IllegalStateException(String.format("Received out-of-order chunk index %s (expected %s)", chunkIndex, state.curChunk));
      } else if (!state.buffers.hasNext()) {
         throw new IllegalStateException(String.format("Requested chunk index beyond end %s", chunkIndex));
      } else {
         ++state.curChunk;
         ManagedBuffer nextChunk = (ManagedBuffer)state.buffers.next();
         if (!state.buffers.hasNext()) {
            logger.trace("Removing stream id {}", streamId);
            this.streams.remove(streamId);
         }

         return nextChunk;
      }
   }

   public ManagedBuffer openStream(String streamChunkId) {
      Pair<Long, Integer> streamChunkIdPair = parseStreamChunkId(streamChunkId);
      return this.getChunk((Long)streamChunkIdPair.getLeft(), (Integer)streamChunkIdPair.getRight());
   }

   public static String genStreamChunkId(long streamId, int chunkId) {
      return String.format("%d_%d", streamId, chunkId);
   }

   public static Pair parseStreamChunkId(String streamChunkId) {
      String[] array = streamChunkId.split("_");

      assert array.length == 2 : "Stream id and chunk index should be specified.";

      long streamId = Long.valueOf(array[0]);
      int chunkIndex = Integer.valueOf(array[1]);
      return ImmutablePair.of(streamId, chunkIndex);
   }

   public void connectionTerminated(Channel channel) {
      RuntimeException failedToReleaseBufferException = null;

      for(Map.Entry entry : this.streams.entrySet()) {
         StreamState state = (StreamState)entry.getValue();
         if (state.associatedChannel == channel) {
            this.streams.remove(entry.getKey());

            try {
               while(!state.isBufferMaterializedOnNext && state.buffers.hasNext()) {
                  ManagedBuffer buffer = (ManagedBuffer)state.buffers.next();
                  if (buffer != null) {
                     buffer.release();
                  }
               }
            } catch (RuntimeException e) {
               if (failedToReleaseBufferException == null) {
                  failedToReleaseBufferException = e;
               } else {
                  logger.error("Exception trying to release remaining StreamState buffers", e);
               }
            }
         }
      }

      if (failedToReleaseBufferException != null) {
         throw failedToReleaseBufferException;
      }
   }

   public void checkAuthorization(TransportClient client, long streamId) {
      if (client.getClientId() != null) {
         StreamState state = (StreamState)this.streams.get(streamId);
         Preconditions.checkArgument(state != null, "Unknown stream ID.");
         if (!client.getClientId().equals(state.appId)) {
            throw new SecurityException(String.format("Client %s not authorized to read stream %d (app %s).", client.getClientId(), streamId, state.appId));
         }
      }

   }

   public void chunkBeingSent(long streamId) {
      StreamState streamState = (StreamState)this.streams.get(streamId);
      if (streamState != null) {
         streamState.chunksBeingTransferred.incrementAndGet();
      }

   }

   public void streamBeingSent(String streamId) {
      this.chunkBeingSent((Long)parseStreamChunkId(streamId).getLeft());
   }

   public void chunkSent(long streamId) {
      StreamState streamState = (StreamState)this.streams.get(streamId);
      if (streamState != null) {
         streamState.chunksBeingTransferred.decrementAndGet();
      }

   }

   public void streamSent(String streamId) {
      this.chunkSent((Long)parseStreamChunkId(streamId).getLeft());
   }

   public long chunksBeingTransferred() {
      long sum = 0L;

      for(StreamState streamState : this.streams.values()) {
         sum += streamState.chunksBeingTransferred.get();
      }

      return sum;
   }

   public long registerStream(String appId, Iterator buffers, Channel channel, boolean isBufferMaterializedOnNext) {
      long myStreamId = this.nextStreamId.getAndIncrement();
      this.streams.put(myStreamId, new StreamState(appId, buffers, channel, isBufferMaterializedOnNext));
      return myStreamId;
   }

   public long registerStream(String appId, Iterator buffers, Channel channel) {
      return this.registerStream(appId, buffers, channel, false);
   }

   @VisibleForTesting
   public int numStreamStates() {
      return this.streams.size();
   }

   private static class StreamState {
      final String appId;
      final Iterator buffers;
      final Channel associatedChannel;
      final boolean isBufferMaterializedOnNext;
      int curChunk = 0;
      final AtomicLong chunksBeingTransferred = new AtomicLong(0L);

      StreamState(String appId, Iterator buffers, Channel channel, boolean isBufferMaterializedOnNext) {
         this.appId = appId;
         this.buffers = (Iterator)Preconditions.checkNotNull(buffers);
         this.associatedChannel = channel;
         this.isBufferMaterializedOnNext = isBufferMaterializedOnNext;
      }
   }
}
