package org.sparkproject.jetty.server;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Condition;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.LogarithmicArrayByteBufferPool;
import org.sparkproject.jetty.util.ProcessorUtils;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.Container;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.Graceful;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;
import org.sparkproject.jetty.util.thread.Scheduler;
import org.sparkproject.jetty.util.thread.ThreadPoolBudget;

@ManagedObject("Abstract implementation of the Connector Interface")
public abstract class AbstractConnector extends ContainerLifeCycle implements Connector, Dumpable {
   protected static final Logger LOG = LoggerFactory.getLogger(AbstractConnector.class);
   private final AutoLock _lock = new AutoLock();
   private final Condition _setAccepting;
   private final Map _factories;
   private final Server _server;
   private final Executor _executor;
   private final Scheduler _scheduler;
   private final ByteBufferPool _byteBufferPool;
   private final Thread[] _acceptors;
   private final Set _endpoints;
   private final Set _immutableEndPoints;
   private Graceful.Shutdown _shutdown;
   private HttpChannel.Listener _httpChannelListeners;
   private long _idleTimeout;
   private long _shutdownIdleTimeout;
   private String _defaultProtocol;
   private ConnectionFactory _defaultConnectionFactory;
   private String _name;
   private int _acceptorPriorityDelta;
   private boolean _accepting;
   private ThreadPoolBudget.Lease _lease;

   public AbstractConnector(Server server, Executor executor, Scheduler scheduler, ByteBufferPool pool, int acceptors, ConnectionFactory... factories) {
      this._setAccepting = this._lock.newCondition();
      this._factories = new LinkedHashMap();
      this._endpoints = Collections.newSetFromMap(new ConcurrentHashMap());
      this._immutableEndPoints = Collections.unmodifiableSet(this._endpoints);
      this._httpChannelListeners = HttpChannel.NOOP_LISTENER;
      this._idleTimeout = 30000L;
      this._shutdownIdleTimeout = 1000L;
      this._acceptorPriorityDelta = -2;
      this._accepting = true;
      this._server = server;
      this._executor = (Executor)(executor != null ? executor : this._server.getThreadPool());
      this.addBean(this._executor);
      if (executor == null) {
         this.unmanage(this._executor);
      }

      if (scheduler == null) {
         scheduler = (Scheduler)this._server.getBean(Scheduler.class);
      }

      this._scheduler = (Scheduler)(scheduler != null ? scheduler : new ScheduledExecutorScheduler(String.format("Connector-Scheduler-%x", this.hashCode()), false));
      this.addBean(this._scheduler);
      synchronized(server) {
         if (pool == null) {
            pool = (ByteBufferPool)server.getBean(ByteBufferPool.class);
            if (pool == null) {
               pool = new LogarithmicArrayByteBufferPool();
               server.addBean(pool, true);
            }

            this.addBean(pool, false);
         } else {
            this.addBean(pool, true);
         }
      }

      this._byteBufferPool = pool;
      this.addBean(pool.asRetainableByteBufferPool());
      this.addEventListener(new Container.Listener() {
         public void beanAdded(Container parent, Object bean) {
            if (bean instanceof HttpChannel.Listener) {
               AbstractConnector.this._httpChannelListeners = new HttpChannelListeners(AbstractConnector.this.getBeans(HttpChannel.Listener.class));
            }

         }

         public void beanRemoved(Container parent, Object bean) {
            if (bean instanceof HttpChannel.Listener) {
               AbstractConnector.this._httpChannelListeners = new HttpChannelListeners(AbstractConnector.this.getBeans(HttpChannel.Listener.class));
            }

         }
      });

      for(ConnectionFactory factory : factories) {
         this.addConnectionFactory(factory);
      }

      int cores = ProcessorUtils.availableProcessors();
      if (acceptors < 0) {
         acceptors = Math.max(1, Math.min(4, cores / 8));
      }

      if (acceptors > cores) {
         LOG.warn("Acceptors should be <= availableProcessors: {} ", this);
      }

      this._acceptors = new Thread[acceptors];
   }

   public HttpChannel.Listener getHttpChannelListeners() {
      return this._httpChannelListeners;
   }

   public Server getServer() {
      return this._server;
   }

   public Executor getExecutor() {
      return this._executor;
   }

   public ByteBufferPool getByteBufferPool() {
      return this._byteBufferPool;
   }

   @ManagedAttribute("The connection idle timeout in milliseconds")
   public long getIdleTimeout() {
      return this._idleTimeout;
   }

   public void setIdleTimeout(long idleTimeout) {
      this._idleTimeout = idleTimeout;
      if (this._idleTimeout == 0L) {
         this._shutdownIdleTimeout = 0L;
      } else if (this._idleTimeout < this._shutdownIdleTimeout) {
         this._shutdownIdleTimeout = Math.min(1000L, this._idleTimeout);
      }

   }

   public void setShutdownIdleTimeout(long idle) {
      this._shutdownIdleTimeout = idle;
   }

   public long getShutdownIdleTimeout() {
      return this._shutdownIdleTimeout;
   }

   @ManagedAttribute("number of acceptor threads")
   public int getAcceptors() {
      return this._acceptors.length;
   }

   protected void doStart() throws Exception {
      Stream var10000 = this.getConnectionFactories().stream();
      Objects.requireNonNull(ConnectionFactory.Configuring.class);
      var10000 = var10000.filter(ConnectionFactory.Configuring.class::isInstance);
      Objects.requireNonNull(ConnectionFactory.Configuring.class);
      var10000.map(ConnectionFactory.Configuring.class::cast).forEach((configuring) -> configuring.configure(this));
      this._shutdown = new Graceful.Shutdown(this) {
         public boolean isShutdownDone() {
            if (!AbstractConnector.this._endpoints.isEmpty()) {
               return false;
            } else {
               for(Thread a : AbstractConnector.this._acceptors) {
                  if (a != null) {
                     return false;
                  }
               }

               return true;
            }
         }
      };
      if (this._defaultProtocol == null) {
         throw new IllegalStateException("No default protocol for " + String.valueOf(this));
      } else {
         this._defaultConnectionFactory = this.getConnectionFactory(this._defaultProtocol);
         if (this._defaultConnectionFactory == null) {
            String var10002 = this._defaultProtocol;
            throw new IllegalStateException("No protocol factory for default protocol '" + var10002 + "' in " + String.valueOf(this));
         } else {
            SslConnectionFactory ssl = (SslConnectionFactory)this.getConnectionFactory(SslConnectionFactory.class);
            if (ssl != null) {
               String next = ssl.getNextProtocol();
               ConnectionFactory cf = this.getConnectionFactory(next);
               if (cf == null) {
                  throw new IllegalStateException("No protocol factory for SSL next protocol: '" + next + "' in " + String.valueOf(this));
               }
            }

            this._lease = ThreadPoolBudget.leaseFrom(this.getExecutor(), this, this._acceptors.length);
            super.doStart();

            for(int i = 0; i < this._acceptors.length; ++i) {
               Acceptor a = new Acceptor(i);
               this.addBean(a);
               this.getExecutor().execute(a);
            }

            LOG.info("Started {}", this);
         }
      }
   }

   protected void interruptAcceptors() {
      try (AutoLock lock = this._lock.lock()) {
         for(Thread thread : this._acceptors) {
            if (thread != null) {
               thread.interrupt();
            }
         }
      }

   }

   public CompletableFuture shutdown() {
      Graceful.Shutdown shutdown = this._shutdown;
      if (shutdown == null) {
         return CompletableFuture.completedFuture((Object)null);
      } else {
         CompletableFuture<Void> done = shutdown.shutdown();
         this.interruptAcceptors();

         for(EndPoint ep : this._endpoints) {
            ep.setIdleTimeout(this.getShutdownIdleTimeout());
         }

         return done;
      }
   }

   public boolean isShutdown() {
      Graceful.Shutdown shutdown = this._shutdown;
      return shutdown == null || shutdown.isShutdown();
   }

   protected void doStop() throws Exception {
      if (this._lease != null) {
         this._lease.close();
      }

      this.interruptAcceptors();
      super.doStop();

      for(Acceptor a : this.getBeans(Acceptor.class)) {
         this.removeBean(a);
      }

      this._shutdown = null;
      LOG.info("Stopped {}", this);
   }

   public void join() throws InterruptedException {
      this.join(0L);
   }

   public void join(long timeout) throws InterruptedException {
      try (AutoLock lock = this._lock.lock()) {
         for(Thread thread : this._acceptors) {
            if (thread != null) {
               thread.join(timeout);
            }
         }
      }

   }

   protected abstract void accept(int var1) throws IOException, InterruptedException;

   public boolean isAccepting() {
      try (AutoLock lock = this._lock.lock()) {
         return this._accepting;
      }
   }

   public void setAccepting(boolean accepting) {
      try (AutoLock l = this._lock.lock()) {
         this._accepting = accepting;
         this._setAccepting.signalAll();
      }

   }

   public ConnectionFactory getConnectionFactory(String protocol) {
      try (AutoLock lock = this._lock.lock()) {
         return (ConnectionFactory)this._factories.get(StringUtil.asciiToLowerCase(protocol));
      }
   }

   public Object getConnectionFactory(Class factoryType) {
      try (AutoLock lock = this._lock.lock()) {
         for(ConnectionFactory f : this._factories.values()) {
            if (factoryType.isAssignableFrom(f.getClass())) {
               return f;
            }
         }

         return null;
      }
   }

   public void addConnectionFactory(ConnectionFactory factory) {
      if (this.isRunning()) {
         throw new IllegalStateException(this.getState());
      } else {
         Set<ConnectionFactory> toRemove = new HashSet();

         for(String key : factory.getProtocols()) {
            key = StringUtil.asciiToLowerCase(key);
            ConnectionFactory old = (ConnectionFactory)this._factories.remove(key);
            if (old != null) {
               if (old.getProtocol().equals(this._defaultProtocol)) {
                  this._defaultProtocol = null;
               }

               toRemove.add(old);
            }

            this._factories.put(key, factory);
         }

         for(ConnectionFactory f : this._factories.values()) {
            toRemove.remove(f);
         }

         for(ConnectionFactory old : toRemove) {
            this.removeBean(old);
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} removed {}", this, old);
            }
         }

         this.addBean(factory);
         if (this._defaultProtocol == null) {
            this._defaultProtocol = factory.getProtocol();
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("{} added {}", this, factory);
         }

      }
   }

   public void addFirstConnectionFactory(ConnectionFactory factory) {
      if (this.isRunning()) {
         throw new IllegalStateException(this.getState());
      } else {
         List<ConnectionFactory> existings = new ArrayList(this._factories.values());
         this.clearConnectionFactories();
         this.addConnectionFactory(factory);

         for(ConnectionFactory existing : existings) {
            this.addConnectionFactory(existing);
         }

      }
   }

   public void addIfAbsentConnectionFactory(ConnectionFactory factory) {
      if (this.isRunning()) {
         throw new IllegalStateException(this.getState());
      } else {
         String key = StringUtil.asciiToLowerCase(factory.getProtocol());
         if (!this._factories.containsKey(key)) {
            this.addConnectionFactory(factory);
         }

      }
   }

   public ConnectionFactory removeConnectionFactory(String protocol) {
      if (this.isRunning()) {
         throw new IllegalStateException(this.getState());
      } else {
         ConnectionFactory factory = (ConnectionFactory)this._factories.remove(StringUtil.asciiToLowerCase(protocol));
         if (this._factories.isEmpty()) {
            this._defaultProtocol = null;
         }

         this.removeBean(factory);
         return factory;
      }
   }

   public Collection getConnectionFactories() {
      return this._factories.values();
   }

   public void setConnectionFactories(Collection factories) {
      if (this.isRunning()) {
         throw new IllegalStateException(this.getState());
      } else {
         for(ConnectionFactory factory : new ArrayList(this._factories.values())) {
            this.removeConnectionFactory(factory.getProtocol());
         }

         for(ConnectionFactory factory : factories) {
            if (factory != null) {
               this.addConnectionFactory(factory);
            }
         }

      }
   }

   public void clearConnectionFactories() {
      if (this.isRunning()) {
         throw new IllegalStateException(this.getState());
      } else {
         this._factories.clear();
         this._defaultProtocol = null;
      }
   }

   @ManagedAttribute("The priority delta to apply to acceptor threads")
   public int getAcceptorPriorityDelta() {
      return this._acceptorPriorityDelta;
   }

   public void setAcceptorPriorityDelta(int acceptorPriorityDelta) {
      int old = this._acceptorPriorityDelta;
      this._acceptorPriorityDelta = acceptorPriorityDelta;
      if (old != acceptorPriorityDelta && this.isStarted()) {
         for(Thread thread : this._acceptors) {
            thread.setPriority(Math.max(1, Math.min(10, thread.getPriority() - old + acceptorPriorityDelta)));
         }
      }

   }

   @ManagedAttribute("Protocols supported by this connector")
   public List getProtocols() {
      return new ArrayList(this._factories.keySet());
   }

   @ManagedAttribute("This connector's default protocol")
   public String getDefaultProtocol() {
      return this._defaultProtocol;
   }

   public void setDefaultProtocol(String defaultProtocol) {
      this._defaultProtocol = StringUtil.asciiToLowerCase(defaultProtocol);
      if (this.isRunning()) {
         this._defaultConnectionFactory = this.getConnectionFactory(this._defaultProtocol);
      }

   }

   public ConnectionFactory getDefaultConnectionFactory() {
      return this.isStarted() ? this._defaultConnectionFactory : this.getConnectionFactory(this._defaultProtocol);
   }

   protected boolean handleAcceptFailure(Throwable ex) {
      if (this.isRunning()) {
         if (ex instanceof InterruptedException) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Accept Interrupted", ex);
            }

            return true;
         } else if (ex instanceof ClosedByInterruptException) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Accept Closed by Interrupt", ex);
            }

            return false;
         } else {
            LOG.warn("Accept Failure", ex);

            try {
               Thread.sleep(1000L);
               return true;
            } catch (Throwable x) {
               LOG.trace("IGNORED", x);
               return false;
            }
         }
      } else {
         LOG.trace("IGNORED", ex);
         return false;
      }
   }

   public Collection getConnectedEndPoints() {
      return this._immutableEndPoints;
   }

   protected void onEndPointOpened(EndPoint endp) {
      this._endpoints.add(endp);
   }

   protected void onEndPointClosed(EndPoint endp) {
      this._endpoints.remove(endp);
      Graceful.Shutdown shutdown = this._shutdown;
      if (shutdown != null) {
         shutdown.check();
      }

   }

   public Scheduler getScheduler() {
      return this._scheduler;
   }

   public String getName() {
      return this._name;
   }

   public void setName(String name) {
      this._name = name;
   }

   public String toString() {
      return String.format("%s@%x{%s, %s}", this._name == null ? this.getClass().getSimpleName() : this._name, this.hashCode(), this.getDefaultProtocol(), this.getProtocols().stream().collect(Collectors.joining(", ", "(", ")")));
   }

   private class Acceptor implements Runnable {
      private final int _id;
      private String _name;

      private Acceptor(int id) {
         this._id = id;
      }

      public void run() {
         Thread thread = Thread.currentThread();
         String name = thread.getName();
         this._name = String.format("%s-acceptor-%d@%x-%s", name, this._id, this.hashCode(), AbstractConnector.this.toString());
         thread.setName(this._name);
         int priority = thread.getPriority();
         if (AbstractConnector.this._acceptorPriorityDelta != 0) {
            thread.setPriority(Math.max(1, Math.min(10, priority + AbstractConnector.this._acceptorPriorityDelta)));
         }

         try (AutoLock l = AbstractConnector.this._lock.lock()) {
            AbstractConnector.this._acceptors[this._id] = thread;
         }

         try {
            while(AbstractConnector.this.isRunning() && !AbstractConnector.this._shutdown.isShutdown()) {
               try {
                  AutoLock l = AbstractConnector.this._lock.lock();

                  label249: {
                     try {
                        if (AbstractConnector.this._accepting || !AbstractConnector.this.isRunning()) {
                           break label249;
                        }

                        AbstractConnector.this._setAccepting.await();
                     } catch (Throwable var28) {
                        if (l != null) {
                           try {
                              l.close();
                           } catch (Throwable var24) {
                              var28.addSuppressed(var24);
                           }
                        }

                        throw var28;
                     }

                     if (l != null) {
                        l.close();
                     }
                     continue;
                  }

                  if (l != null) {
                     l.close();
                  }
               } catch (InterruptedException var29) {
                  continue;
               }

               try {
                  AbstractConnector.this.accept(this._id);
               } catch (Throwable x) {
                  if (!AbstractConnector.this.handleAcceptFailure(x)) {
                     break;
                  }
               }
            }
         } finally {
            thread.setName(name);
            if (AbstractConnector.this._acceptorPriorityDelta != 0) {
               thread.setPriority(priority);
            }

            try (AutoLock l = AbstractConnector.this._lock.lock()) {
               AbstractConnector.this._acceptors[this._id] = null;
            }

            Graceful.Shutdown shutdown = AbstractConnector.this._shutdown;
            if (shutdown != null) {
               shutdown.check();
            }

         }

      }

      public String toString() {
         String name = this._name;
         return name == null ? String.format("acceptor-%d@%x", this._id, this.hashCode()) : name;
      }
   }
}
