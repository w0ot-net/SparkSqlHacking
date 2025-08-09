package io.vertx.core.net.impl.pool;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.http.ConnectionPoolTooBusyException;
import io.vertx.core.impl.ContextInternal;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class SimpleConnectionPool implements ConnectionPool {
   private static final Future POOL_CLOSED = Future.failedFuture("Pool closed");
   private static final BiFunction SAME_EVENT_LOOP_SELECTOR = (waiter, list) -> {
      int size = list.size();

      for(int i = 0; i < size; ++i) {
         PoolConnection slot = (PoolConnection)list.get(i);
         if (slot.context().nettyEventLoop() == waiter.context().nettyEventLoop() && slot.available() > 0L) {
            return slot;
         }
      }

      return null;
   };
   private static final BiFunction FIRST_AVAILABLE_SELECTOR = (waiter, list) -> {
      int size = list.size();

      for(int i = 0; i < size; ++i) {
         PoolConnection slot = (PoolConnection)list.get(i);
         if (slot.available() > 0L) {
            return slot;
         }
      }

      return null;
   };
   private final PoolConnector connector;
   private final int maxWaiters;
   private final int maxCapacity;
   private final int[] capacityFactors;
   private final Executor sync;
   private final ListImpl list;
   private boolean closed;
   private BiFunction selector;
   private Function contextProvider;
   private BiFunction fallbackSelector;
   private final Slot[] slots;
   private int size;
   private int capacity;
   private final Waiters waiters;
   private int requests;

   SimpleConnectionPool(PoolConnector connector, int[] maxSizes) {
      this(connector, maxSizes, -1);
   }

   SimpleConnectionPool(PoolConnector connector, int[] maxSizes, int maxWaiters) {
      this.list = new ListImpl();
      int[] capacities = new int[maxSizes.length];
      int maxCapacity = 1;
      int numSlots = 0;

      for(int i = 0; i < maxSizes.length; ++i) {
         int maxSize = maxSizes[i];
         if (maxSize < 1) {
            throw new IllegalArgumentException();
         }

         maxCapacity *= maxSize;
         numSlots = Math.max(numSlots, maxSize);
      }

      for(int i = 0; i < maxSizes.length; ++i) {
         capacities[i] = maxCapacity / maxSizes[i];
      }

      this.capacityFactors = capacities;
      this.connector = connector;
      this.slots = new Slot[numSlots];
      this.size = 0;
      this.maxWaiters = maxWaiters;
      this.capacity = 0;
      this.maxCapacity = maxCapacity;
      this.sync = new CombinerExecutor(this);
      this.selector = SAME_EVENT_LOOP_SELECTOR;
      this.fallbackSelector = FIRST_AVAILABLE_SELECTOR;
      this.contextProvider = EVENT_LOOP_CONTEXT_PROVIDER;
      this.waiters = new Waiters();
   }

   public ConnectionPool connectionSelector(BiFunction selector) {
      this.selector = selector;
      return this;
   }

   public ConnectionPool contextProvider(Function contextProvider) {
      this.contextProvider = contextProvider;
      return this;
   }

   private void execute(Executor.Action action) {
      this.sync.submit(action);
   }

   public int size() {
      return this.size;
   }

   public void connect(Slot slot, PoolWaiter waiter) {
      slot.initiator = waiter;
      this.connector.connect(slot.context, slot, (ar) -> {
         slot.initiator = null;
         if (ar.succeeded()) {
            this.execute(new ConnectSuccess(slot, (ConnectResult)ar.result(), waiter));
         } else {
            this.execute(new ConnectFailed(slot, ar.cause(), waiter));
         }

      });
   }

   private void setConcurrency(Slot slot, long concurrency) {
      this.execute(new SetConcurrency(slot, concurrency));
   }

   private void remove(Slot removed) {
      this.execute(new Remove(removed));
   }

   public void evict(Predicate predicate, Handler handler) {
      this.execute(new Evict(predicate, handler));
   }

   public void acquire(ContextInternal context, PoolWaiter.Listener listener, int kind, Handler handler) {
      this.execute(new Acquire(context, listener, this.capacityFactors[kind], handler));
   }

   public void acquire(ContextInternal context, int kind, Handler handler) {
      this.acquire(context, PoolWaiter.NULL_LISTENER, kind, handler);
   }

   public void cancel(PoolWaiter waiter, Handler handler) {
      this.execute(new Cancel(waiter, handler));
   }

   private void recycle(LeaseImpl lease) {
      if (lease.recycled) {
         throw new IllegalStateException("Attempt to recycle more than permitted");
      } else {
         lease.recycled = true;
         this.execute(new Recycle(lease.slot));
      }
   }

   public int waiters() {
      return this.waiters.size();
   }

   public int capacity() {
      return this.capacity;
   }

   public int requests() {
      return this.requests;
   }

   public void close(Handler handler) {
      this.execute(new Close(handler));
   }

   static class Slot implements PoolConnector.Listener, PoolConnection {
      private final SimpleConnectionPool pool;
      private final ContextInternal context;
      private final Promise result;
      private PoolWaiter initiator;
      private Object connection;
      private int index;
      private int usage;
      private long concurrency;
      private int capacity;

      public Slot(SimpleConnectionPool pool, ContextInternal context, int index, int capacity) {
         this.pool = pool;
         this.context = context;
         this.connection = null;
         this.usage = 0;
         this.index = index;
         this.capacity = capacity;
         this.result = context.promise();
      }

      public void onRemove() {
         this.pool.remove(this);
      }

      public void onConcurrencyChange(long concurrency) {
         this.pool.setConcurrency(this, concurrency);
      }

      public ContextInternal context() {
         return this.context;
      }

      public Object get() {
         return this.connection;
      }

      public int usage() {
         return this.usage;
      }

      public long available() {
         return this.concurrency - (long)this.usage;
      }

      public long concurrency() {
         return this.concurrency;
      }
   }

   private static class ConnectSuccess implements Executor.Action {
      private final Slot slot;
      private final ConnectResult result;
      private PoolWaiter waiter;

      private ConnectSuccess(Slot slot, ConnectResult result, PoolWaiter waiter) {
         this.slot = slot;
         this.result = result;
         this.waiter = waiter;
      }

      public Task execute(SimpleConnectionPool pool) {
         int capacity = pool.capacityFactors[(int)this.result.weight()];
         int initialCapacity = this.slot.capacity;
         this.slot.connection = this.result.connection();
         this.slot.concurrency = this.result.concurrency();
         this.slot.capacity = capacity;
         this.slot.usage = 0;
         pool.requests--;
         pool.capacity = pool.capacity + (capacity - initialCapacity);
         if (pool.closed) {
            if (this.waiter.disposed) {
               this.waiter = null;
            } else {
               this.waiter.disposed = true;
            }

            return new Task() {
               public void run() {
                  if (ConnectSuccess.this.waiter != null) {
                     ConnectSuccess.this.slot.context.emit(SimpleConnectionPool.POOL_CLOSED, ConnectSuccess.this.waiter.handler);
                  }

                  ConnectSuccess.this.slot.result.complete(ConnectSuccess.this.slot.connection);
               }
            };
         } else {
            long acquisitions = this.slot.concurrency;
            if (acquisitions == 0L) {
               if (!this.waiter.disposed) {
                  pool.waiters.addFirst(this.waiter);
               }

               return null;
            } else {
               final LeaseImpl<C> lease;
               int c;
               if (this.waiter.disposed) {
                  lease = null;
                  c = 0;
               } else {
                  lease = new LeaseImpl(this.slot, this.waiter.handler);
                  c = 1;
                  this.waiter.disposed = true;
                  --acquisitions;
               }

               int m = (int)Math.min(acquisitions, (long)pool.waiters.size());
               final LeaseImpl<C>[] leases;
               if (m > 0) {
                  c += m;
                  leases = new LeaseImpl[m];

                  for(int i = 0; i < m; ++i) {
                     leases[i] = new LeaseImpl(this.slot, pool.waiters.poll().handler);
                  }
               } else {
                  leases = null;
               }

               this.slot.usage = c;
               return new Task() {
                  public void run() {
                     if (lease != null) {
                        lease.emit();
                     }

                     if (leases != null) {
                        for(LeaseImpl lease : leases) {
                           lease.emit();
                        }
                     }

                     ConnectSuccess.this.slot.result.complete(ConnectSuccess.this.slot.connection);
                  }
               };
            }
         }
      }
   }

   private static class ConnectFailed implements Executor.Action {
      private final Slot removed;
      private final Throwable cause;
      private PoolWaiter waiter;

      public ConnectFailed(Slot removed, Throwable cause, PoolWaiter waiter) {
         this.removed = removed;
         this.cause = cause;
         this.waiter = waiter;
      }

      public Task execute(final SimpleConnectionPool pool) {
         pool.requests--;
         if (this.waiter.disposed) {
            this.waiter = null;
         } else {
            this.waiter.disposed = true;
         }

         Task task = new Task() {
            public void run() {
               if (ConnectFailed.this.waiter != null) {
                  Future<Lease<C>> waiterFailure;
                  if (pool.closed) {
                     waiterFailure = SimpleConnectionPool.POOL_CLOSED;
                  } else {
                     waiterFailure = Future.failedFuture(ConnectFailed.this.cause);
                  }

                  ConnectFailed.this.removed.context.emit(waiterFailure, ConnectFailed.this.waiter.handler);
               }

               ConnectFailed.this.removed.result.fail(ConnectFailed.this.cause);
            }
         };
         if (!pool.closed) {
            Task removeTask = (new Remove(this.removed)).execute(pool);
            if (removeTask != null) {
               removeTask.next(task);
               task = removeTask;
            }
         }

         return task;
      }
   }

   private static class Remove implements Executor.Action {
      protected final Slot removed;

      private Remove(Slot removed) {
         this.removed = removed;
      }

      public Task execute(final SimpleConnectionPool pool) {
         if (!pool.closed && pool.slots[this.removed.index] == this.removed) {
            int w = this.removed.capacity;
            this.removed.usage = 0;
            this.removed.concurrency = 0L;
            this.removed.connection = null;
            this.removed.capacity = 0;
            final PoolWaiter<C> waiter = pool.waiters.poll();
            if (waiter != null) {
               ContextInternal connectionContext = (ContextInternal)pool.contextProvider.apply(waiter.context);
               final Slot<C> slot = new Slot(pool, connectionContext, this.removed.index, waiter.capacity);
               pool.capacity = pool.capacity - w;
               pool.capacity = pool.capacity + waiter.capacity;
               pool.slots[this.removed.index] = slot;
               pool.requests++;
               return new Task() {
                  public void run() {
                     if (waiter.listener != null) {
                        waiter.listener.onConnect(waiter);
                     }

                     pool.connect(slot, waiter);
                  }
               };
            } else if (pool.size > 1) {
               Slot<C> tmp = pool.slots[pool.size - 1];
               tmp.index = this.removed.index;
               pool.slots[this.removed.index] = tmp;
               pool.slots[pool.size - 1] = null;
               pool.size--;
               pool.capacity = pool.capacity - w;
               return null;
            } else {
               pool.slots[0] = null;
               pool.size--;
               pool.capacity = pool.capacity - w;
               return null;
            }
         } else {
            return null;
         }
      }
   }

   private static class SetConcurrency implements Executor.Action {
      private final Slot slot;
      private final long concurrency;

      SetConcurrency(Slot slot, long concurrency) {
         this.slot = slot;
         this.concurrency = concurrency;
      }

      public Task execute(SimpleConnectionPool pool) {
         if (this.slot.connection == null) {
            return null;
         } else {
            long diff = this.concurrency - this.slot.concurrency;
            Slot var4 = this.slot;
            var4.concurrency = var4.concurrency + diff;
            if (diff <= 0L) {
               return null;
            } else {
               int m = (int)Math.min(this.slot.concurrency - (long)this.slot.usage, (long)pool.waiters.size());
               if (m <= 0) {
                  return null;
               } else {
                  final LeaseImpl<C>[] extra = new LeaseImpl[m];

                  for(int i = 0; i < m; ++i) {
                     extra[i] = new LeaseImpl(this.slot, pool.waiters.poll().handler);
                  }

                  Slot var8 = this.slot;
                  var8.usage = var8.usage + m;
                  return new Task() {
                     public void run() {
                        for(LeaseImpl lease : extra) {
                           lease.emit();
                        }

                     }
                  };
               }
            }
         }
      }
   }

   private static class Evict implements Executor.Action {
      private final Predicate predicate;
      private final Handler handler;

      public Evict(Predicate predicate, Handler handler) {
         this.predicate = predicate;
         this.handler = handler;
      }

      public Task execute(SimpleConnectionPool pool) {
         if (pool.closed) {
            return new Task() {
               public void run() {
                  Evict.this.handler.handle(SimpleConnectionPool.POOL_CLOSED);
               }
            };
         } else {
            final List<C> res = new ArrayList();
            List<Slot<C>> removed = new ArrayList();

            for(int i = pool.size - 1; i >= 0; --i) {
               Slot<C> slot = pool.slots[i];
               if (slot.connection != null && slot.usage == 0 && this.predicate.test(slot.connection)) {
                  removed.add(slot);
                  res.add(slot.connection);
               }
            }

            Task head = new Task() {
               public void run() {
                  Evict.this.handler.handle(Future.succeededFuture(res));
               }
            };
            Task tail = head;

            for(Slot slot : removed) {
               Task next = (new Remove(slot)).execute(pool);
               if (next != null) {
                  tail.next(next);
                  tail = next;
               }
            }

            return head;
         }
      }
   }

   private static class Acquire extends PoolWaiter implements Executor.Action {
      public Acquire(ContextInternal context, PoolWaiter.Listener listener, int capacity, Handler handler) {
         super(listener, context, capacity, handler);
      }

      public Task execute(final SimpleConnectionPool pool) {
         if (pool.closed) {
            return new Task() {
               public void run() {
                  Acquire.this.context.emit(SimpleConnectionPool.POOL_CLOSED, Acquire.this.handler);
               }
            };
         } else {
            Slot<C> slot1 = (Slot)pool.selector.apply(this, pool.list);
            if (slot1 != null) {
               slot1.usage++;
               final LeaseImpl<C> lease = new LeaseImpl(slot1, this.handler);
               return new Task() {
                  public void run() {
                     lease.emit();
                  }
               };
            } else if (pool.capacity < pool.maxCapacity) {
               pool.capacity = pool.capacity + this.capacity;
               ContextInternal connectionContext = (ContextInternal)pool.contextProvider.apply(this.context);
               final Slot<C> slot2 = new Slot(pool, connectionContext, pool.size, this.capacity);
               pool.slots[pool.size++] = slot2;
               pool.requests++;
               return new Task() {
                  public void run() {
                     if (Acquire.this.listener != null) {
                        Acquire.this.listener.onConnect(Acquire.this);
                     }

                     pool.connect(slot2, Acquire.this);
                  }
               };
            } else {
               Slot<C> slot3 = (Slot)pool.fallbackSelector.apply(this, pool.list);
               if (slot3 != null) {
                  slot3.usage++;
                  final LeaseImpl<C> lease = new LeaseImpl(slot3, this.handler);
                  return new Task() {
                     public void run() {
                        lease.emit();
                     }
                  };
               } else if (pool.maxWaiters != -1 && pool.waiters.size() + pool.requests >= pool.maxWaiters) {
                  return new Task() {
                     public void run() {
                        Acquire.this.context.emit(Future.failedFuture((Throwable)(new ConnectionPoolTooBusyException("Connection pool reached max wait queue size of " + pool.maxWaiters))), Acquire.this.handler);
                     }
                  };
               } else {
                  pool.waiters.addLast(this);
                  return this.listener != null ? new Task() {
                     public void run() {
                        Acquire.this.listener.onEnqueue(Acquire.this);
                     }
                  } : null;
               }
            }
         }
      }
   }

   private static class Cancel extends Task implements Executor.Action {
      private final PoolWaiter waiter;
      private final Handler handler;
      private boolean cancelled;

      public Cancel(PoolWaiter waiter, Handler handler) {
         this.waiter = waiter;
         this.handler = handler;
      }

      public Task execute(SimpleConnectionPool pool) {
         if (pool.closed) {
            return new Task() {
               public void run() {
                  Cancel.this.handler.handle(SimpleConnectionPool.POOL_CLOSED);
               }
            };
         } else {
            if (pool.waiters.remove(this.waiter)) {
               this.cancelled = true;
               this.waiter.disposed = true;
            } else if (!this.waiter.disposed) {
               this.waiter.disposed = true;
               this.cancelled = true;
            } else {
               this.cancelled = false;
            }

            return this;
         }
      }

      public void run() {
         this.handler.handle(Future.succeededFuture(this.cancelled));
      }
   }

   static class LeaseImpl implements Lease {
      private final Handler handler;
      private final Slot slot;
      private final Object connection;
      private boolean recycled;

      public LeaseImpl(Slot slot, Handler handler) {
         this.handler = handler;
         this.slot = slot;
         this.connection = slot.connection;
      }

      public Object get() {
         return this.connection;
      }

      public void recycle() {
         this.slot.pool.recycle(this);
      }

      void emit() {
         this.slot.context.emit(Future.succeededFuture(this), this.handler);
      }
   }

   private static class Recycle implements Executor.Action {
      private final Slot slot;

      public Recycle(Slot slot) {
         this.slot = slot;
      }

      public Task execute(SimpleConnectionPool pool) {
         if (!pool.closed && this.slot.connection != null) {
            PoolWaiter<C> waiter;
            if ((long)this.slot.usage <= this.slot.concurrency && (waiter = pool.waiters.poll()) != null) {
               final LeaseImpl<C> lease = new LeaseImpl(this.slot, waiter.handler);
               return new Task() {
                  public void run() {
                     lease.emit();
                  }
               };
            }

            this.slot.usage--;
         }

         return null;
      }
   }

   private static class Close implements Executor.Action {
      private final Handler handler;

      private Close(Handler handler) {
         this.handler = handler;
      }

      public Task execute(SimpleConnectionPool pool) {
         if (pool.closed) {
            return new Task() {
               public void run() {
                  Close.this.handler.handle(SimpleConnectionPool.POOL_CLOSED);
               }
            };
         } else {
            pool.closed = true;
            final List<PoolWaiter<C>> waiters = pool.waiters.clear();
            final List<Future<C>> list = new ArrayList();

            for(int i = 0; i < pool.size; ++i) {
               Slot<C> slot = pool.slots[i];
               pool.slots[i] = null;
               PoolWaiter<C> waiter = slot.initiator;
               if (waiter != null) {
                  waiters.add(slot.initiator);
                  slot.initiator.disposed = true;
                  slot.initiator = null;
               }

               pool.capacity = pool.capacity - slot.capacity;
               list.add(slot.result.future());
            }

            pool.size = 0;
            return new Task() {
               public void run() {
                  waiters.forEach((w) -> w.context.emit(SimpleConnectionPool.POOL_CLOSED, w.handler));
                  Close.this.handler.handle(Future.succeededFuture(list));
               }
            };
         }
      }
   }

   private static class Waiters implements Iterable {
      private final PoolWaiter head = new PoolWaiter((PoolWaiter.Listener)null, (ContextInternal)null, 0, (Handler)null);
      private int size;

      public Waiters() {
         this.head.next = this.head.prev = this.head;
      }

      PoolWaiter poll() {
         if (this.head.next == this.head) {
            return null;
         } else {
            PoolWaiter<C> node = this.head.next;
            this.remove(node);
            return node;
         }
      }

      void addLast(PoolWaiter node) {
         if (node.queued) {
            throw new IllegalStateException();
         } else {
            node.queued = true;
            node.prev = this.head.prev;
            node.next = this.head;
            this.head.prev.next = node;
            this.head.prev = node;
            ++this.size;
         }
      }

      void addFirst(PoolWaiter node) {
         if (node.queued) {
            throw new IllegalStateException();
         } else {
            node.queued = true;
            node.prev = this.head;
            node.next = this.head.prev;
            this.head.next.prev = node;
            this.head.next = node;
            ++this.size;
         }
      }

      boolean remove(PoolWaiter node) {
         if (!node.queued) {
            return false;
         } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.next = node.prev = null;
            node.queued = false;
            --this.size;
            return true;
         }
      }

      List clear() {
         List<PoolWaiter<C>> lst = new ArrayList(this.size);
         this.forEach(lst::add);
         this.size = 0;
         this.head.next = this.head.prev = this.head;
         return lst;
      }

      int size() {
         return this.size;
      }

      public Iterator iterator() {
         return new Iterator() {
            PoolWaiter current;

            {
               this.current = Waiters.this.head;
            }

            public boolean hasNext() {
               return this.current.next != Waiters.this.head;
            }

            public PoolWaiter next() {
               if (this.current.next == Waiters.this.head) {
                  throw new NoSuchElementException();
               } else {
                  PoolWaiter var1;
                  try {
                     var1 = this.current.next;
                  } finally {
                     this.current = this.current.next;
                  }

                  return var1;
               }
            }
         };
      }
   }

   class ListImpl extends AbstractList {
      public PoolConnection get(int index) {
         return SimpleConnectionPool.this.slots[index];
      }

      public int size() {
         return SimpleConnectionPool.this.size;
      }
   }
}
