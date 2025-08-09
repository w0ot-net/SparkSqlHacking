package org.apache.thrift;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNonblockingMultiFetchClient {
   private static final Logger LOGGER = LoggerFactory.getLogger(TNonblockingMultiFetchClient.class);
   private int maxRecvBufBytesPerServer;
   private int fetchTimeoutSeconds;
   private ByteBuffer requestBuf;
   private ByteBuffer requestBufDuplication;
   private List servers;
   private TNonblockingMultiFetchStats stats;
   private ByteBuffer[] recvBuf;

   public TNonblockingMultiFetchClient(int maxRecvBufBytesPerServer, int fetchTimeoutSeconds, ByteBuffer requestBuf, List servers) {
      this.maxRecvBufBytesPerServer = maxRecvBufBytesPerServer;
      this.fetchTimeoutSeconds = fetchTimeoutSeconds;
      this.requestBuf = requestBuf;
      this.servers = servers;
      this.stats = new TNonblockingMultiFetchStats();
      this.recvBuf = null;
   }

   public synchronized int getMaxRecvBufBytesPerServer() {
      return this.maxRecvBufBytesPerServer;
   }

   public synchronized int getFetchTimeoutSeconds() {
      return this.fetchTimeoutSeconds;
   }

   public synchronized ByteBuffer getRequestBuf() {
      if (this.requestBuf == null) {
         return null;
      } else {
         if (this.requestBufDuplication == null) {
            this.requestBufDuplication = this.requestBuf.duplicate();
         }

         return this.requestBufDuplication;
      }
   }

   public synchronized List getServerList() {
      return this.servers == null ? null : Collections.unmodifiableList(this.servers);
   }

   public synchronized TNonblockingMultiFetchStats getFetchStats() {
      return this.stats;
   }

   public synchronized ByteBuffer[] fetch() {
      this.recvBuf = null;
      this.stats.clear();
      if (this.servers != null && this.servers.size() != 0 && this.requestBuf != null && this.fetchTimeoutSeconds > 0) {
         ExecutorService executor = Executors.newSingleThreadExecutor();
         MultiFetch multiFetch = new MultiFetch();
         FutureTask<?> task = new FutureTask(multiFetch, (Object)null);
         executor.execute(task);

         try {
            task.get((long)this.fetchTimeoutSeconds, TimeUnit.SECONDS);
         } catch (InterruptedException ie) {
            task.cancel(true);
            LOGGER.error("Interrupted during fetch", ie);
         } catch (ExecutionException ee) {
            task.cancel(true);
            LOGGER.error("Exception during fetch", ee);
         } catch (TimeoutException te) {
            task.cancel(true);
            LOGGER.error("Timeout for fetch", te);
         }

         executor.shutdownNow();
         multiFetch.close();
         return this.recvBuf;
      } else {
         return this.recvBuf;
      }
   }

   private class MultiFetch implements Runnable {
      private Selector selector;

      private MultiFetch() {
      }

      public void run() {
         long t1 = System.currentTimeMillis();
         int numTotalServers = TNonblockingMultiFetchClient.this.servers.size();
         TNonblockingMultiFetchClient.this.stats.setNumTotalServers(numTotalServers);
         TNonblockingMultiFetchClient.this.recvBuf = new ByteBuffer[numTotalServers];
         ByteBuffer[] sendBuf = new ByteBuffer[numTotalServers];
         long[] numBytesRead = new long[numTotalServers];
         int[] frameSize = new int[numTotalServers];
         boolean[] hasReadFrameSize = new boolean[numTotalServers];

         try {
            this.selector = Selector.open();
         } catch (IOException ioe) {
            TNonblockingMultiFetchClient.LOGGER.error("Selector opens error", ioe);
            return;
         }

         for(int i = 0; i < numTotalServers; ++i) {
            sendBuf[i] = TNonblockingMultiFetchClient.this.requestBuf.duplicate();
            TNonblockingMultiFetchClient.this.recvBuf[i] = ByteBuffer.allocate(4);
            TNonblockingMultiFetchClient.this.stats.incTotalRecvBufBytes(4);
            InetSocketAddress server = (InetSocketAddress)TNonblockingMultiFetchClient.this.servers.get(i);
            SocketChannel s = null;
            SelectionKey key = null;

            try {
               s = SocketChannel.open();
               s.configureBlocking(false);
               s.connect(server);
               key = s.register(this.selector, s.validOps());
               key.attach(i);
            } catch (Exception e) {
               TNonblockingMultiFetchClient.this.stats.incNumConnectErrorServers();
               TNonblockingMultiFetchClient.LOGGER.error("Set up socket to server {} error", server, e);
               if (s != null) {
                  try {
                     s.close();
                  } catch (Exception var17) {
                  }
               }

               if (key != null) {
                  key.cancel();
               }
            }
         }

         while(TNonblockingMultiFetchClient.this.stats.getNumReadCompletedServers() + TNonblockingMultiFetchClient.this.stats.getNumConnectErrorServers() < TNonblockingMultiFetchClient.this.stats.getNumTotalServers()) {
            if (Thread.currentThread().isInterrupted()) {
               return;
            }

            try {
               this.selector.select();
            } catch (Exception e) {
               TNonblockingMultiFetchClient.LOGGER.error("Selector selects error", e);
               continue;
            }

            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();

            while(it.hasNext()) {
               SelectionKey selKey = (SelectionKey)it.next();
               it.remove();
               int index = (Integer)selKey.attachment();
               if (selKey.isValid() && selKey.isConnectable()) {
                  try {
                     SocketChannel sChannel = (SocketChannel)selKey.channel();
                     sChannel.finishConnect();
                  } catch (Exception e) {
                     TNonblockingMultiFetchClient.this.stats.incNumConnectErrorServers();
                     TNonblockingMultiFetchClient.LOGGER.error("Socket {} connects to server {} error", new Object[]{index, TNonblockingMultiFetchClient.this.servers.get(index), e});
                  }
               }

               if (selKey.isValid() && selKey.isWritable() && sendBuf[index].hasRemaining()) {
                  try {
                     SocketChannel sChannel = (SocketChannel)selKey.channel();
                     sChannel.write(sendBuf[index]);
                  } catch (Exception e) {
                     TNonblockingMultiFetchClient.LOGGER.error("Socket {} writes to server {} error", new Object[]{index, TNonblockingMultiFetchClient.this.servers.get(index), e});
                  }
               }

               if (selKey.isValid() && selKey.isReadable()) {
                  try {
                     SocketChannel sChannel = (SocketChannel)selKey.channel();
                     int bytesRead = sChannel.read(TNonblockingMultiFetchClient.this.recvBuf[index]);
                     if (bytesRead > 0) {
                        numBytesRead[index] += (long)bytesRead;
                        if (!hasReadFrameSize[index] && TNonblockingMultiFetchClient.this.recvBuf[index].remaining() == 0) {
                           frameSize[index] = TNonblockingMultiFetchClient.this.recvBuf[index].getInt(0);
                           if (frameSize[index] <= 0) {
                              TNonblockingMultiFetchClient.this.stats.incNumInvalidFrameSize();
                              TNonblockingMultiFetchClient.LOGGER.error("Read an invalid frame size {} from {}. Does the server use TFramedTransport?", frameSize[index], TNonblockingMultiFetchClient.this.servers.get(index));
                              sChannel.close();
                              continue;
                           }

                           if (frameSize[index] + 4 > TNonblockingMultiFetchClient.this.stats.getMaxResponseBytes()) {
                              TNonblockingMultiFetchClient.this.stats.setMaxResponseBytes(frameSize[index] + 4);
                           }

                           if (frameSize[index] + 4 > TNonblockingMultiFetchClient.this.maxRecvBufBytesPerServer) {
                              TNonblockingMultiFetchClient.this.stats.incNumOverflowedRecvBuf();
                              TNonblockingMultiFetchClient.LOGGER.error("Read frame size {} from {}, total buffer size would exceed limit {}", new Object[]{frameSize[index], TNonblockingMultiFetchClient.this.servers.get(index), TNonblockingMultiFetchClient.this.maxRecvBufBytesPerServer});
                              sChannel.close();
                              continue;
                           }

                           TNonblockingMultiFetchClient.this.recvBuf[index] = ByteBuffer.allocate(frameSize[index] + 4);
                           TNonblockingMultiFetchClient.this.recvBuf[index].putInt(frameSize[index]);
                           TNonblockingMultiFetchClient.this.stats.incTotalRecvBufBytes(frameSize[index]);
                           hasReadFrameSize[index] = true;
                        }

                        if (hasReadFrameSize[index] && numBytesRead[index] >= (long)(frameSize[index] + 4)) {
                           sChannel.close();
                           TNonblockingMultiFetchClient.this.stats.incNumReadCompletedServers();
                           long t2 = System.currentTimeMillis();
                           TNonblockingMultiFetchClient.this.stats.setReadTime(t2 - t1);
                        }
                     }
                  } catch (Exception e) {
                     TNonblockingMultiFetchClient.LOGGER.error("Socket {} reads from server {} error", new Object[]{index, TNonblockingMultiFetchClient.this.servers.get(index), e});
                  }
               }
            }
         }

      }

      public void close() {
         try {
            if (this.selector.isOpen()) {
               for(SelectionKey selKey : this.selector.keys()) {
                  SocketChannel sChannel = (SocketChannel)selKey.channel();
                  sChannel.close();
               }

               this.selector.close();
            }
         } catch (IOException e) {
            TNonblockingMultiFetchClient.LOGGER.error("Free resource error", e);
         }

      }
   }
}
