package org.apache.commons.lang3.concurrent.locks;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Supplier;
import org.apache.commons.lang3.function.Failable;
import org.apache.commons.lang3.function.FailableConsumer;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.function.Suppliers;

public class LockingVisitors {
   public static ReadWriteLockVisitor create(Object object, ReadWriteLock readWriteLock) {
      return new ReadWriteLockVisitor(object, readWriteLock);
   }

   public static ReadWriteLockVisitor reentrantReadWriteLockVisitor(Object object) {
      return create(object, new ReentrantReadWriteLock());
   }

   public static StampedLockVisitor stampedLockVisitor(Object object) {
      return new StampedLockVisitor(object, new StampedLock());
   }

   public static class LockVisitor {
      private final Object lock;
      private final Object object;
      private final Supplier readLockSupplier;
      private final Supplier writeLockSupplier;

      protected LockVisitor(Object object, Object lock, Supplier readLockSupplier, Supplier writeLockSupplier) {
         this.object = Objects.requireNonNull(object, "object");
         this.lock = Objects.requireNonNull(lock, "lock");
         this.readLockSupplier = (Supplier)Objects.requireNonNull(readLockSupplier, "readLockSupplier");
         this.writeLockSupplier = (Supplier)Objects.requireNonNull(writeLockSupplier, "writeLockSupplier");
      }

      public void acceptReadLocked(FailableConsumer consumer) {
         this.lockAcceptUnlock(this.readLockSupplier, consumer);
      }

      public void acceptWriteLocked(FailableConsumer consumer) {
         this.lockAcceptUnlock(this.writeLockSupplier, consumer);
      }

      public Object applyReadLocked(FailableFunction function) {
         return this.lockApplyUnlock(this.readLockSupplier, function);
      }

      public Object applyWriteLocked(FailableFunction function) {
         return this.lockApplyUnlock(this.writeLockSupplier, function);
      }

      public Object getLock() {
         return this.lock;
      }

      public Object getObject() {
         return this.object;
      }

      protected void lockAcceptUnlock(Supplier lockSupplier, FailableConsumer consumer) {
         Lock lock = (Lock)Objects.requireNonNull((Lock)Suppliers.get(lockSupplier), "lock");
         lock.lock();

         try {
            if (consumer != null) {
               consumer.accept(this.object);
            }
         } catch (Throwable t) {
            throw Failable.rethrow(t);
         } finally {
            lock.unlock();
         }

      }

      protected Object lockApplyUnlock(Supplier lockSupplier, FailableFunction function) {
         Lock lock = (Lock)Objects.requireNonNull((Lock)Suppliers.get(lockSupplier), "lock");
         lock.lock();

         Object var4;
         try {
            var4 = function.apply(this.object);
         } catch (Throwable t) {
            throw Failable.rethrow(t);
         } finally {
            lock.unlock();
         }

         return var4;
      }
   }

   public static class ReadWriteLockVisitor extends LockVisitor {
      protected ReadWriteLockVisitor(Object object, ReadWriteLock readWriteLock) {
         Objects.requireNonNull(readWriteLock);
         Supplier var10003 = readWriteLock::readLock;
         Objects.requireNonNull(readWriteLock);
         super(object, readWriteLock, var10003, readWriteLock::writeLock);
      }
   }

   public static class StampedLockVisitor extends LockVisitor {
      protected StampedLockVisitor(Object object, StampedLock stampedLock) {
         Objects.requireNonNull(stampedLock);
         Supplier var10003 = stampedLock::asReadLock;
         Objects.requireNonNull(stampedLock);
         super(object, stampedLock, var10003, stampedLock::asWriteLock);
      }
   }
}
