package com.clearspring.analytics.stream.quantile;

import com.clearspring.analytics.util.AbstractIterator;
import com.clearspring.analytics.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class GroupTree implements Iterable {
   private int count;
   private int size;
   private int depth;
   private TDigest.Group leaf;
   private GroupTree left;
   private GroupTree right;

   public GroupTree() {
      this.count = this.size = this.depth = 0;
      this.leaf = null;
      this.left = this.right = null;
   }

   public GroupTree(TDigest.Group leaf) {
      this.size = this.depth = 1;
      this.leaf = leaf;
      this.count = leaf.count();
      this.left = this.right = null;
   }

   public GroupTree(GroupTree left, GroupTree right) {
      this.left = left;
      this.right = right;
      this.count = left.count + right.count;
      this.size = left.size + right.size;
      this.rebalance();
      this.leaf = this.right.first();
   }

   public void add(TDigest.Group group) {
      if (this.size == 0) {
         this.leaf = group;
         this.depth = 1;
         this.count = group.count();
         this.size = 1;
      } else {
         if (this.size == 1) {
            int order = group.compareTo(this.leaf);
            if (order < 0) {
               this.left = new GroupTree(group);
               this.right = new GroupTree(this.leaf);
            } else if (order > 0) {
               this.left = new GroupTree(this.leaf);
               this.right = new GroupTree(group);
               this.leaf = group;
            }
         } else if (group.compareTo(this.leaf) < 0) {
            this.left.add(group);
         } else {
            this.right.add(group);
         }

         this.count += group.count();
         ++this.size;
         this.depth = Math.max(this.left.depth, this.right.depth) + 1;
         this.rebalance();
      }
   }

   private void rebalance() {
      int l = this.left.depth();
      int r = this.right.depth();
      if (l > r + 1) {
         if (this.left.left.depth() > this.left.right.depth()) {
            this.rotate(this.left.left.left, this.left.left.right, this.left.right, this.right);
         } else {
            this.rotate(this.left.left, this.left.right.left, this.left.right.right, this.right);
         }
      } else if (r > l + 1) {
         if (this.right.left.depth() > this.right.right.depth()) {
            this.rotate(this.left, this.right.left.left, this.right.left.right, this.right.right);
         } else {
            this.rotate(this.left, this.right.left, this.right.right.left, this.right.right.right);
         }
      } else {
         this.depth = Math.max(this.left.depth(), this.right.depth()) + 1;
      }

   }

   private void rotate(GroupTree a, GroupTree b, GroupTree c, GroupTree d) {
      this.left = new GroupTree(a, b);
      this.right = new GroupTree(c, d);
      this.count = this.left.count + this.right.count;
      this.size = this.left.size + this.right.size;
      this.depth = Math.max(this.left.depth(), this.right.depth()) + 1;
      this.leaf = this.right.first();
   }

   private int depth() {
      return this.depth;
   }

   public int size() {
      return this.size;
   }

   public int headCount(TDigest.Group base) {
      if (this.size == 0) {
         return 0;
      } else if (this.left == null) {
         return this.leaf.compareTo(base) < 0 ? 1 : 0;
      } else {
         return base.compareTo(this.leaf) < 0 ? this.left.headCount(base) : this.left.size + this.right.headCount(base);
      }
   }

   public int headSum(TDigest.Group base) {
      if (this.size == 0) {
         return 0;
      } else if (this.left == null) {
         return this.leaf.compareTo(base) < 0 ? this.count : 0;
      } else {
         return base.compareTo(this.leaf) <= 0 ? this.left.headSum(base) : this.left.count + this.right.headSum(base);
      }
   }

   public TDigest.Group first() {
      Preconditions.checkState(this.size > 0, "No first element of empty set");
      return this.left == null ? this.leaf : this.left.first();
   }

   public Iterator iterator() {
      return this.iterator((TDigest.Group)null);
   }

   private Iterator iterator(final TDigest.Group start) {
      return new AbstractIterator() {
         Deque stack = new ArrayDeque();

         {
            this.push(GroupTree.this, start);
         }

         private void push(GroupTree z, TDigest.Group startx) {
            while(z.left != null) {
               if (start != null && start.compareTo(z.leaf) >= 0) {
                  z = z.right;
               } else {
                  this.stack.push(z.right);
                  z = z.left;
               }
            }

            if (start == null || z.leaf.compareTo(start) >= 0) {
               this.stack.push(z);
            }

         }

         protected TDigest.Group computeNext() {
            GroupTree r;
            for(r = (GroupTree)this.stack.poll(); r != null && r.left != null; r = (GroupTree)this.stack.poll()) {
               this.push(r, start);
            }

            return r != null ? r.leaf : (TDigest.Group)this.endOfData();
         }
      };
   }

   public void remove(TDigest.Group base) {
      Preconditions.checkState(this.size > 0, "Cannot remove from empty set");
      if (this.size == 1) {
         Preconditions.checkArgument(base.compareTo(this.leaf) == 0, "Element %s not found", base);
         this.count = this.size = 0;
         this.leaf = null;
      } else if (base.compareTo(this.leaf) < 0) {
         if (this.left.size > 1) {
            this.left.remove(base);
            this.count -= base.count();
            --this.size;
            this.rebalance();
         } else {
            this.size = this.right.size;
            this.count = this.right.count;
            this.depth = this.right.depth;
            this.leaf = this.right.leaf;
            this.left = this.right.left;
            this.right = this.right.right;
         }
      } else if (this.right.size > 1) {
         this.right.remove(base);
         this.leaf = this.right.first();
         this.count -= base.count();
         --this.size;
         this.rebalance();
      } else {
         this.size = this.left.size;
         this.count = this.left.count;
         this.depth = this.left.depth;
         this.leaf = this.left.leaf;
         this.right = this.left.right;
         this.left = this.left.left;
      }

   }

   public TDigest.Group floor(TDigest.Group base) {
      if (this.size == 0) {
         return null;
      } else if (this.size == 1) {
         return base.compareTo(this.leaf) >= 0 ? this.leaf : null;
      } else if (base.compareTo(this.leaf) < 0) {
         return this.left.floor(base);
      } else {
         TDigest.Group floor = this.right.floor(base);
         if (floor == null) {
            floor = this.left.last();
         }

         return floor;
      }
   }

   public TDigest.Group last() {
      Preconditions.checkState(this.size > 0, "Cannot find last element of empty set");
      return this.size == 1 ? this.leaf : this.right.last();
   }

   public TDigest.Group ceiling(TDigest.Group base) {
      if (this.size == 0) {
         return null;
      } else if (this.size == 1) {
         return base.compareTo(this.leaf) <= 0 ? this.leaf : null;
      } else if (base.compareTo(this.leaf) < 0) {
         TDigest.Group r = this.left.ceiling(base);
         if (r == null) {
            r = this.right.first();
         }

         return r;
      } else {
         return this.right.ceiling(base);
      }
   }

   public Iterable tailSet(final TDigest.Group start) {
      return new Iterable() {
         public Iterator iterator() {
            return GroupTree.this.iterator(start);
         }
      };
   }

   public int sum() {
      return this.count;
   }

   public void checkBalance() {
      if (this.left != null) {
         Preconditions.checkState(Math.abs(this.left.depth() - this.right.depth()) < 2, "Imbalanced");
         int l = this.left.depth();
         int r = this.right.depth();
         Preconditions.checkState(this.depth == Math.max(l, r) + 1, "Depth doesn't match children");
         Preconditions.checkState(this.size == this.left.size + this.right.size, "Sizes don't match children");
         Preconditions.checkState(this.count == this.left.count + this.right.count, "Counts don't match children");
         Preconditions.checkState(this.leaf.compareTo(this.right.first()) == 0, "Split is wrong %.5d != %.5d or %d != %d", this.leaf.mean(), this.right.first().mean(), this.leaf.id(), this.right.first().id());
         this.left.checkBalance();
         this.right.checkBalance();
      }

   }

   public void print(int depth) {
      for(int i = 0; i < depth; ++i) {
         System.out.printf("| ");
      }

      int imbalance = Math.abs((this.left != null ? this.left.depth : 1) - (this.right != null ? this.right.depth : 1));
      System.out.printf("%s%s, %d, %d, %d\n", (imbalance > 1 ? "* " : "") + (this.right != null && this.leaf.compareTo(this.right.first()) != 0 ? "+ " : ""), this.leaf, this.size, this.count, this.depth);
      if (this.left != null) {
         this.left.print(depth + 1);
         this.right.print(depth + 1);
      }

   }
}
