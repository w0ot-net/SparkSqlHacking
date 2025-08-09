package org.apache.derby.impl.store.access.sort;

final class NodeAllocator {
   private static final int DEFAULT_INIT_SIZE = 128;
   private static final int GROWTH_MULTIPLIER = 2;
   private static final int DEFAULT_MAX_SIZE = 1024;
   private Node[] array = null;
   private int maxSize = 0;
   private int nAllocated = 0;
   private Node freeList = null;

   public NodeAllocator() {
   }

   public Node newNode() {
      if (this.array == null && !this.init()) {
         return null;
      } else if (this.freeList != null) {
         Node var5 = this.freeList;
         this.freeList = var5.rightLink;
         var5.rightLink = null;
         return var5;
      } else {
         if (this.nAllocated == this.array.length) {
            if (this.array.length >= this.maxSize) {
               return null;
            }

            int var1 = (int)Math.min((long)this.array.length * 2L, (long)this.maxSize);

            Node[] var2;
            try {
               var2 = new Node[var1];
            } catch (OutOfMemoryError var4) {
               return null;
            }

            System.arraycopy(this.array, 0, var2, 0, this.array.length);
            this.array = var2;
         }

         if (this.array[this.nAllocated] == null) {
            this.array[this.nAllocated] = new Node(this.nAllocated);
         }

         return this.array[this.nAllocated++];
      }
   }

   public void freeNode(Node var1) {
      var1.reset();
      var1.rightLink = this.freeList;
      this.freeList = var1;
   }

   public boolean init() {
      return this.init(128, 1024);
   }

   public boolean init(int var1) {
      return this.init(128, var1);
   }

   public boolean init(int var1, int var2) {
      this.maxSize = var2;
      if (var2 < var1) {
         var1 = var2;
      }

      this.array = new Node[var1];
      if (this.array == null) {
         return false;
      } else {
         this.nAllocated = 0;
         return true;
      }
   }

   public void grow(int var1) {
      if (var1 > 0) {
         this.maxSize = (int)Math.min((long)this.maxSize * (long)(100 + var1) / 100L, 2147483647L);
      }

   }

   public void reset() {
      if (this.array != null) {
         for(int var1 = 0; var1 < this.nAllocated; ++var1) {
            this.array[var1].reset();
         }

         this.nAllocated = 0;
         this.freeList = null;
      }
   }

   public void close() {
      this.array = null;
      this.nAllocated = 0;
      this.maxSize = 0;
      this.freeList = null;
   }

   public int capacity() {
      return this.maxSize;
   }
}
