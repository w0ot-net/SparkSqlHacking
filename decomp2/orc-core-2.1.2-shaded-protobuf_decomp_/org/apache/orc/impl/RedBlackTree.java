package org.apache.orc.impl;

abstract class RedBlackTree {
   public static final int NULL = -1;
   private static final int LEFT_OFFSET = 0;
   private static final int RIGHT_OFFSET = 1;
   private static final int ELEMENT_SIZE = 2;
   protected int size = 0;
   private final DynamicIntArray data;
   protected int root = -1;
   protected int lastAdd = 0;
   private boolean wasAdd = false;

   RedBlackTree(int initialCapacity) {
      this.data = new DynamicIntArray(initialCapacity * 2);
   }

   private int insert(int left, int right, boolean isRed) {
      int position = this.size++;
      this.setLeft(position, left, isRed);
      this.setRight(position, right);
      return position;
   }

   protected abstract int compareValue(int var1);

   protected boolean isRed(int position) {
      return position != -1 && (this.data.get(position * 2 + 0) & 1) == 1;
   }

   private void setRed(int position, boolean isRed) {
      int offset = position * 2 + 0;
      if (isRed) {
         this.data.set(offset, this.data.get(offset) | 1);
      } else {
         this.data.set(offset, this.data.get(offset) & -2);
      }

   }

   protected int getLeft(int position) {
      return this.data.get(position * 2 + 0) >> 1;
   }

   protected int getRight(int position) {
      return this.data.get(position * 2 + 1);
   }

   private void setLeft(int position, int left) {
      int offset = position * 2 + 0;
      this.data.set(offset, left << 1 | this.data.get(offset) & 1);
   }

   private void setLeft(int position, int left, boolean isRed) {
      int offset = position * 2 + 0;
      this.data.set(offset, left << 1 | (isRed ? 1 : 0));
   }

   private void setRight(int position, int right) {
      this.data.set(position * 2 + 1, right);
   }

   private boolean add(int node, boolean fromLeft, int parent, int grandparent, int greatGrandparent) {
      if (node == -1) {
         if (this.root == -1) {
            this.lastAdd = this.insert(-1, -1, false);
            this.root = this.lastAdd;
            this.wasAdd = true;
            return false;
         }

         this.lastAdd = this.insert(-1, -1, true);
         node = this.lastAdd;
         this.wasAdd = true;
         if (fromLeft) {
            this.setLeft(parent, node);
         } else {
            this.setRight(parent, node);
         }
      } else {
         int compare = this.compareValue(node);
         boolean keepGoing;
         if (compare < 0) {
            keepGoing = this.add(this.getLeft(node), true, node, parent, grandparent);
         } else {
            if (compare <= 0) {
               this.lastAdd = node;
               this.wasAdd = false;
               return false;
            }

            keepGoing = this.add(this.getRight(node), false, node, parent, grandparent);
         }

         if (node == this.root || !keepGoing) {
            return false;
         }
      }

      if (this.isRed(node) && this.isRed(parent)) {
         if (parent == this.getLeft(grandparent)) {
            int uncle = this.getRight(grandparent);
            if (this.isRed(uncle)) {
               this.setRed(parent, false);
               this.setRed(uncle, false);
               this.setRed(grandparent, true);
               return true;
            } else {
               if (node == this.getRight(parent)) {
                  int tmp = node;
                  node = parent;
                  parent = tmp;
                  this.setLeft(grandparent, tmp);
                  this.setRight(node, this.getLeft(tmp));
                  this.setLeft(tmp, node);
               }

               this.setRed(parent, false);
               this.setRed(grandparent, true);
               if (greatGrandparent == -1) {
                  this.root = parent;
               } else if (this.getLeft(greatGrandparent) == grandparent) {
                  this.setLeft(greatGrandparent, parent);
               } else {
                  this.setRight(greatGrandparent, parent);
               }

               this.setLeft(grandparent, this.getRight(parent));
               this.setRight(parent, grandparent);
               return false;
            }
         } else {
            int uncle = this.getLeft(grandparent);
            if (this.isRed(uncle)) {
               this.setRed(parent, false);
               this.setRed(uncle, false);
               this.setRed(grandparent, true);
               return true;
            } else {
               if (node == this.getLeft(parent)) {
                  int tmp = node;
                  node = parent;
                  parent = tmp;
                  this.setRight(grandparent, tmp);
                  this.setLeft(node, this.getRight(tmp));
                  this.setRight(tmp, node);
               }

               this.setRed(parent, false);
               this.setRed(grandparent, true);
               if (greatGrandparent == -1) {
                  this.root = parent;
               } else if (this.getRight(greatGrandparent) == grandparent) {
                  this.setRight(greatGrandparent, parent);
               } else {
                  this.setLeft(greatGrandparent, parent);
               }

               this.setRight(grandparent, this.getLeft(parent));
               this.setLeft(parent, grandparent);
               return false;
            }
         }
      } else {
         return true;
      }
   }

   protected boolean add() {
      this.add(this.root, false, -1, -1, -1);
      if (this.wasAdd) {
         this.setRed(this.root, false);
         return true;
      } else {
         return false;
      }
   }

   public int size() {
      return this.size;
   }

   public void clear() {
      this.root = -1;
      this.size = 0;
      this.data.clear();
   }

   public long getSizeInBytes() {
      return (long)this.data.getSizeInBytes();
   }
}
