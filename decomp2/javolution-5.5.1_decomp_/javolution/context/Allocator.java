package javolution.context;

public abstract class Allocator {
   protected Thread user;
   protected Object[] queue = new Object[16];
   protected int queueSize;

   protected Allocator() {
   }

   public final Object next() {
      return this.queueSize > 0 ? this.queue[--this.queueSize] : this.allocate();
   }

   protected abstract Object allocate();

   protected abstract void recycle(Object var1);

   void resize() {
      T[] tmp = (T[])((Object[])(new Object[this.queue.length * 2]));
      System.arraycopy(this.queue, 0, tmp, 0, this.queue.length);
      this.queue = tmp;
   }
}
