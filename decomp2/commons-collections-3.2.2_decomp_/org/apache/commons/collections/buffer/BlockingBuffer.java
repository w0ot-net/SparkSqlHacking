package org.apache.commons.collections.buffer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUnderflowException;

public class BlockingBuffer extends SynchronizedBuffer {
   private static final long serialVersionUID = 1719328905017860541L;
   private final long timeout;

   public static Buffer decorate(Buffer buffer) {
      return new BlockingBuffer(buffer);
   }

   public static Buffer decorate(Buffer buffer, long timeoutMillis) {
      return new BlockingBuffer(buffer, timeoutMillis);
   }

   protected BlockingBuffer(Buffer buffer) {
      super(buffer);
      this.timeout = 0L;
   }

   protected BlockingBuffer(Buffer buffer, long timeoutMillis) {
      super(buffer);
      this.timeout = timeoutMillis < 0L ? 0L : timeoutMillis;
   }

   public boolean add(Object o) {
      synchronized(this.lock) {
         boolean result = this.collection.add(o);
         this.lock.notifyAll();
         return result;
      }
   }

   public boolean addAll(Collection c) {
      synchronized(this.lock) {
         boolean result = this.collection.addAll(c);
         this.lock.notifyAll();
         return result;
      }
   }

   public Object get() {
      synchronized(this.lock) {
         while(this.collection.isEmpty()) {
            try {
               if (this.timeout > 0L) {
                  Object var10000 = this.get(this.timeout);
                  return var10000;
               }

               this.lock.wait();
            } catch (InterruptedException e) {
               PrintWriter out = new PrintWriter(new StringWriter());
               e.printStackTrace(out);
               throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
            }
         }

         return this.getBuffer().get();
      }
   }

   public Object get(long timeout) {
      synchronized(this.lock) {
         long expiration = System.currentTimeMillis() + timeout;
         long timeLeft = expiration - System.currentTimeMillis();

         while(timeLeft > 0L && this.collection.isEmpty()) {
            try {
               this.lock.wait(timeLeft);
               timeLeft = expiration - System.currentTimeMillis();
            } catch (InterruptedException e) {
               PrintWriter out = new PrintWriter(new StringWriter());
               e.printStackTrace(out);
               throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
            }
         }

         if (this.collection.isEmpty()) {
            throw new BufferUnderflowException("Timeout expired");
         } else {
            return this.getBuffer().get();
         }
      }
   }

   public Object remove() {
      synchronized(this.lock) {
         while(this.collection.isEmpty()) {
            try {
               if (this.timeout > 0L) {
                  Object var10000 = this.remove(this.timeout);
                  return var10000;
               }

               this.lock.wait();
            } catch (InterruptedException e) {
               PrintWriter out = new PrintWriter(new StringWriter());
               e.printStackTrace(out);
               throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
            }
         }

         return this.getBuffer().remove();
      }
   }

   public Object remove(long timeout) {
      synchronized(this.lock) {
         long expiration = System.currentTimeMillis() + timeout;
         long timeLeft = expiration - System.currentTimeMillis();

         while(timeLeft > 0L && this.collection.isEmpty()) {
            try {
               this.lock.wait(timeLeft);
               timeLeft = expiration - System.currentTimeMillis();
            } catch (InterruptedException e) {
               PrintWriter out = new PrintWriter(new StringWriter());
               e.printStackTrace(out);
               throw new BufferUnderflowException("Caused by InterruptedException: " + out.toString());
            }
         }

         if (this.collection.isEmpty()) {
            throw new BufferUnderflowException("Timeout expired");
         } else {
            return this.getBuffer().remove();
         }
      }
   }
}
