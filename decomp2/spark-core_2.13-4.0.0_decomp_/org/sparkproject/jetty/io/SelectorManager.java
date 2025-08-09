package org.sparkproject.jetty.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.ProcessorUtils;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.thread.Scheduler;
import org.sparkproject.jetty.util.thread.ThreadPool;
import org.sparkproject.jetty.util.thread.ThreadPoolBudget;

@ManagedObject("Manager of the NIO Selectors")
public abstract class SelectorManager extends ContainerLifeCycle implements Dumpable {
   public static final int DEFAULT_CONNECT_TIMEOUT = 15000;
   protected static final Logger LOG = LoggerFactory.getLogger(SelectorManager.class);
   private final Executor executor;
   private final Scheduler scheduler;
   private final ManagedSelector[] _selectors;
   private final AtomicInteger _selectorIndex;
   private final IntUnaryOperator _selectorIndexUpdate;
   private final List _acceptListeners;
   private long _connectTimeout;
   private ThreadPoolBudget.Lease _lease;

   private static int defaultSelectors(Executor executor) {
      if (executor instanceof ThreadPool.SizedThreadPool) {
         int threads = ((ThreadPool.SizedThreadPool)executor).getMaxThreads();
         int cpus = ProcessorUtils.availableProcessors();
         return Math.max(1, Math.min(cpus / 2, threads / 16));
      } else {
         return Math.max(1, ProcessorUtils.availableProcessors() / 2);
      }
   }

   protected SelectorManager(Executor executor, Scheduler scheduler) {
      this(executor, scheduler, -1);
   }

   protected SelectorManager(Executor executor, Scheduler scheduler, int selectors) {
      this._selectorIndex = new AtomicInteger();
      this._acceptListeners = new CopyOnWriteArrayList();
      this._connectTimeout = 15000L;
      if (selectors <= 0) {
         selectors = defaultSelectors(executor);
      }

      this.executor = executor;
      this.scheduler = scheduler;
      this._selectors = new ManagedSelector[selectors];
      this._selectorIndexUpdate = (index) -> (index + 1) % this._selectors.length;
   }

   @ManagedAttribute("The Executor")
   public Executor getExecutor() {
      return this.executor;
   }

   @ManagedAttribute("The Scheduler")
   public Scheduler getScheduler() {
      return this.scheduler;
   }

   @ManagedAttribute("The Connection timeout (ms)")
   public long getConnectTimeout() {
      return this._connectTimeout;
   }

   public void setConnectTimeout(long milliseconds) {
      this._connectTimeout = milliseconds;
   }

   protected void execute(Runnable task) {
      this.executor.execute(task);
   }

   @ManagedAttribute(
      value = "Total number of keys in all selectors",
      readonly = true
   )
   public int getTotalKeys() {
      int keys = 0;

      for(ManagedSelector selector : this._selectors) {
         if (selector != null) {
            keys += selector.getTotalKeys();
         }
      }

      return keys;
   }

   @ManagedAttribute("The number of NIO Selectors")
   public int getSelectorCount() {
      return this._selectors.length;
   }

   protected ManagedSelector chooseSelector() {
      return this._selectors[this._selectorIndex.updateAndGet(this._selectorIndexUpdate)];
   }

   public void connect(SelectableChannel channel, Object attachment) {
      ManagedSelector set = this.chooseSelector();
      if (set != null) {
         Objects.requireNonNull(set);
         set.submit(set.new Connect(channel, attachment));
      }

   }

   public void accept(SelectableChannel channel) {
      this.accept(channel, (Object)null);
   }

   public void accept(SelectableChannel channel, Object attachment) {
      ManagedSelector selector = this.chooseSelector();
      Objects.requireNonNull(selector);
      selector.submit(selector.new Accept(channel, attachment));
   }

   public Closeable acceptor(SelectableChannel server) {
      ManagedSelector selector = this.chooseSelector();
      Objects.requireNonNull(selector);
      ManagedSelector.Acceptor acceptor = selector.new Acceptor(server);
      selector.submit(acceptor);
      return acceptor;
   }

   protected void accepted(SelectableChannel channel) throws IOException {
      throw new UnsupportedOperationException();
   }

   protected void doStart() throws Exception {
      this._lease = ThreadPoolBudget.leaseFrom(this.getExecutor(), this, this._selectors.length);

      for(int i = 0; i < this._selectors.length; ++i) {
         ManagedSelector selector = this.newSelector(i);
         this._selectors[i] = selector;
         this.addBean(selector);
      }

      super.doStart();
   }

   protected ManagedSelector newSelector(int id) {
      return new ManagedSelector(this, id);
   }

   protected Selector newSelector() throws IOException {
      return Selector.open();
   }

   protected void doStop() throws Exception {
      boolean var11 = false;

      try {
         var11 = true;
         super.doStop();
         var11 = false;
      } finally {
         if (var11) {
            for(ManagedSelector selector : this._selectors) {
               if (selector != null) {
                  this.removeBean(selector);
               }
            }

            Arrays.fill(this._selectors, (Object)null);
            if (this._lease != null) {
               this._lease.close();
            }

         }
      }

      for(ManagedSelector selector : this._selectors) {
         if (selector != null) {
            this.removeBean(selector);
         }
      }

      Arrays.fill(this._selectors, (Object)null);
      if (this._lease != null) {
         this._lease.close();
      }

   }

   protected void endPointOpened(EndPoint endpoint) {
   }

   protected void endPointClosed(EndPoint endpoint) {
   }

   public void connectionOpened(Connection connection, Object context) {
      try {
         connection.onOpen();
      } catch (Throwable var4) {
         if (this.isRunning()) {
            LOG.warn("Exception while notifying connection {}", connection, var4);
         } else {
            LOG.debug("Exception while notifying connection {}", connection, var4);
         }

         throw var4;
      }
   }

   public void connectionClosed(Connection connection, Throwable cause) {
      try {
         connection.onClose(cause);
      } catch (Throwable x) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Exception while notifying connection {}", connection, x);
         }
      }

   }

   protected boolean doFinishConnect(SelectableChannel channel) throws IOException {
      return ((SocketChannel)channel).finishConnect();
   }

   protected boolean isConnectionPending(SelectableChannel channel) {
      return ((SocketChannel)channel).isConnectionPending();
   }

   protected SelectableChannel doAccept(SelectableChannel server) throws IOException {
      return ((ServerSocketChannel)server).accept();
   }

   protected void connectionFailed(SelectableChannel channel, Throwable ex, Object attachment) {
      LOG.warn(String.format("%s - %s", channel, attachment), ex);
   }

   protected abstract EndPoint newEndPoint(SelectableChannel var1, ManagedSelector var2, SelectionKey var3) throws IOException;

   public abstract Connection newConnection(SelectableChannel var1, EndPoint var2, Object var3) throws IOException;

   public boolean addEventListener(EventListener listener) {
      if (super.addEventListener(listener)) {
         if (listener instanceof AcceptListener) {
            this._acceptListeners.add((AcceptListener)listener);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean removeEventListener(EventListener listener) {
      if (super.removeEventListener(listener)) {
         if (listener instanceof AcceptListener) {
            this._acceptListeners.remove(listener);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void onAccepting(SelectableChannel channel) {
      for(AcceptListener l : this._acceptListeners) {
         try {
            l.onAccepting(channel);
         } catch (Throwable x) {
            LOG.warn("Failed to notify onAccepting on listener {}", l, x);
         }
      }

   }

   protected void onAcceptFailed(SelectableChannel channel, Throwable cause) {
      for(AcceptListener l : this._acceptListeners) {
         try {
            l.onAcceptFailed(channel, cause);
         } catch (Throwable x) {
            LOG.warn("Failed to notify onAcceptFailed on listener {}", l, x);
         }
      }

   }

   protected void onAccepted(SelectableChannel channel) {
      for(AcceptListener l : this._acceptListeners) {
         try {
            l.onAccepted(channel);
         } catch (Throwable x) {
            LOG.warn("Failed to notify onAccepted on listener {}", l, x);
         }
      }

   }

   public String toString() {
      return String.format("%s@%x[keys=%d]", this.getClass().getSimpleName(), this.hashCode(), this.getTotalKeys());
   }

   public interface AcceptListener extends SelectorManagerListener {
      default void onAccepting(SelectableChannel channel) {
      }

      default void onAcceptFailed(SelectableChannel channel, Throwable cause) {
      }

      default void onAccepted(SelectableChannel channel) {
      }
   }

   public interface SelectorManagerListener extends EventListener {
   }
}
