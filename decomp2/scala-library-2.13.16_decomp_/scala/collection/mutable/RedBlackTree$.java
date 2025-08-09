package scala.collection.mutable;

import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.Ordering;

public final class RedBlackTree$ {
   public static final RedBlackTree$ MODULE$ = new RedBlackTree$();

   public boolean isRed(final RedBlackTree.Node node) {
      return node != null && node.red();
   }

   public boolean isBlack(final RedBlackTree.Node node) {
      return node == null || !node.red();
   }

   public int size(final RedBlackTree.Node node) {
      return node == null ? 0 : 1 + this.size(node.left()) + this.size(node.right());
   }

   public int size(final RedBlackTree.Tree tree) {
      return tree.size();
   }

   public boolean isEmpty(final RedBlackTree.Tree tree) {
      return tree.root() == null;
   }

   public void clear(final RedBlackTree.Tree tree) {
      tree.root_$eq((RedBlackTree.Node)null);
      tree.size_$eq(0);
   }

   public Option get(final RedBlackTree.Tree tree, final Object key, final Ordering evidence$1) {
      RedBlackTree.Node var4 = this.getNode(tree.root(), key, evidence$1);
      return (Option)(var4 == null ? None$.MODULE$ : new Some(var4.value()));
   }

   private RedBlackTree.Node getNode(final RedBlackTree.Node node, final Object key, final Ordering ord) {
      while(node != null) {
         int cmp = ord.compare(key, node.key());
         if (cmp < 0) {
            RedBlackTree.Node var5 = node.left();
            ord = ord;
            key = key;
            node = var5;
         } else {
            if (cmp <= 0) {
               return node;
            }

            RedBlackTree.Node var10000 = node.right();
            ord = ord;
            key = key;
            node = var10000;
         }
      }

      return null;
   }

   public boolean contains(final RedBlackTree.Tree tree, final Object key, final Ordering evidence$2) {
      return this.getNode(tree.root(), key, evidence$2) != null;
   }

   public Option min(final RedBlackTree.Tree tree) {
      RedBlackTree.Node var2 = this.scala$collection$mutable$RedBlackTree$$minNode(tree.root());
      return (Option)(var2 == null ? None$.MODULE$ : new Some(new Tuple2(var2.key(), var2.value())));
   }

   public Option minKey(final RedBlackTree.Tree tree) {
      RedBlackTree.Node var2 = this.scala$collection$mutable$RedBlackTree$$minNode(tree.root());
      return (Option)(var2 == null ? None$.MODULE$ : new Some(var2.key()));
   }

   public RedBlackTree.Node scala$collection$mutable$RedBlackTree$$minNode(final RedBlackTree.Node node) {
      return node == null ? null : this.minNodeNonNull(node);
   }

   public RedBlackTree.Node minNodeNonNull(final RedBlackTree.Node node) {
      while(node.left() != null) {
         node = node.left();
      }

      return node;
   }

   public Option max(final RedBlackTree.Tree tree) {
      RedBlackTree.Node var2 = this.maxNode(tree.root());
      return (Option)(var2 == null ? None$.MODULE$ : new Some(new Tuple2(var2.key(), var2.value())));
   }

   public Option maxKey(final RedBlackTree.Tree tree) {
      RedBlackTree.Node var2 = this.maxNode(tree.root());
      return (Option)(var2 == null ? None$.MODULE$ : new Some(var2.key()));
   }

   private RedBlackTree.Node maxNode(final RedBlackTree.Node node) {
      return node == null ? null : this.maxNodeNonNull(node);
   }

   public RedBlackTree.Node maxNodeNonNull(final RedBlackTree.Node node) {
      while(node.right() != null) {
         node = node.right();
      }

      return node;
   }

   public Option minAfter(final RedBlackTree.Tree tree, final Object key, final Ordering ord) {
      RedBlackTree.Node var4 = this.scala$collection$mutable$RedBlackTree$$minNodeAfter(tree.root(), key, ord);
      return (Option)(var4 == null ? None$.MODULE$ : new Some(new Tuple2(var4.key(), var4.value())));
   }

   public Option minKeyAfter(final RedBlackTree.Tree tree, final Object key, final Ordering ord) {
      RedBlackTree.Node var4 = this.scala$collection$mutable$RedBlackTree$$minNodeAfter(tree.root(), key, ord);
      return (Option)(var4 == null ? None$.MODULE$ : new Some(var4.key()));
   }

   public RedBlackTree.Node scala$collection$mutable$RedBlackTree$$minNodeAfter(final RedBlackTree.Node node, final Object key, final Ordering ord) {
      if (node == null) {
         return null;
      } else {
         RedBlackTree.Node y = null;
         RedBlackTree.Node x = node;

         int cmp;
         for(cmp = 1; x != null && cmp != 0; x = cmp < 0 ? x.left() : x.right()) {
            y = x;
            cmp = ord.compare(key, x.key());
         }

         return cmp <= 0 ? y : this.scala$collection$mutable$RedBlackTree$$successor(y);
      }
   }

   public Option maxBefore(final RedBlackTree.Tree tree, final Object key, final Ordering ord) {
      RedBlackTree.Node var4 = this.maxNodeBefore(tree.root(), key, ord);
      return (Option)(var4 == null ? None$.MODULE$ : new Some(new Tuple2(var4.key(), var4.value())));
   }

   public Option maxKeyBefore(final RedBlackTree.Tree tree, final Object key, final Ordering ord) {
      RedBlackTree.Node var4 = this.maxNodeBefore(tree.root(), key, ord);
      return (Option)(var4 == null ? None$.MODULE$ : new Some(var4.key()));
   }

   private RedBlackTree.Node maxNodeBefore(final RedBlackTree.Node node, final Object key, final Ordering ord) {
      if (node == null) {
         return null;
      } else {
         RedBlackTree.Node y = null;
         RedBlackTree.Node x = node;

         int cmp;
         for(cmp = 1; x != null && cmp != 0; x = cmp < 0 ? x.left() : x.right()) {
            y = x;
            cmp = ord.compare(key, x.key());
         }

         return cmp > 0 ? y : this.predecessor(y);
      }
   }

   public void insert(final RedBlackTree.Tree tree, final Object key, final Object value, final Ordering ord) {
      RedBlackTree.Node y = null;
      RedBlackTree.Node x = tree.root();

      int cmp;
      for(cmp = 1; x != null && cmp != 0; x = cmp < 0 ? x.left() : x.right()) {
         y = x;
         cmp = ord.compare(key, x.key());
      }

      if (cmp == 0) {
         y.value_$eq(value);
      } else {
         RedBlackTree.Node$ var10000 = RedBlackTree.Node$.MODULE$;
         boolean leaf_red = true;
         RedBlackTree.Node z = new RedBlackTree.Node(key, value, leaf_red, (RedBlackTree.Node)null, (RedBlackTree.Node)null, y);
         if (y == null) {
            tree.root_$eq(z);
         } else if (cmp < 0) {
            y.left_$eq(z);
         } else {
            y.right_$eq(z);
         }

         this.fixAfterInsert(tree, z);
         tree.size_$eq(tree.size() + 1);
      }
   }

   private void fixAfterInsert(final RedBlackTree.Tree tree, final RedBlackTree.Node node) {
      RedBlackTree.Node z = node;

      while(this.isRed(z.parent())) {
         if (z.parent() == z.parent().parent().left()) {
            RedBlackTree.Node y = z.parent().parent().right();
            if (this.isRed(y)) {
               z.parent().red_$eq(false);
               y.red_$eq(false);
               z.parent().parent().red_$eq(true);
               z = z.parent().parent();
            } else {
               if (z == z.parent().right()) {
                  z = z.parent();
                  this.rotateLeft(tree, z);
               }

               z.parent().red_$eq(false);
               z.parent().parent().red_$eq(true);
               this.rotateRight(tree, z.parent().parent());
            }
         } else {
            RedBlackTree.Node y = z.parent().parent().left();
            if (this.isRed(y)) {
               z.parent().red_$eq(false);
               y.red_$eq(false);
               z.parent().parent().red_$eq(true);
               z = z.parent().parent();
            } else {
               if (z == z.parent().left()) {
                  z = z.parent();
                  this.rotateRight(tree, z);
               }

               z.parent().red_$eq(false);
               z.parent().parent().red_$eq(true);
               this.rotateLeft(tree, z.parent().parent());
            }
         }
      }

      tree.root().red_$eq(false);
   }

   public void delete(final RedBlackTree.Tree tree, final Object key, final Ordering ord) {
      RedBlackTree.Node z = this.getNode(tree.root(), key, ord);
      if (z != null) {
         boolean yIsRed = z.red();
         RedBlackTree.Node x;
         RedBlackTree.Node xParent;
         if (z.left() == null) {
            x = z.right();
            this.transplant(tree, z, z.right());
            xParent = z.parent();
         } else if (z.right() == null) {
            x = z.left();
            this.transplant(tree, z, z.left());
            xParent = z.parent();
         } else {
            RedBlackTree.Node y = this.minNodeNonNull(z.right());
            yIsRed = y.red();
            x = y.right();
            if (y.parent() == z) {
               xParent = y;
            } else {
               xParent = y.parent();
               this.transplant(tree, y, y.right());
               y.right_$eq(z.right());
               y.right().parent_$eq(y);
            }

            this.transplant(tree, z, y);
            y.left_$eq(z.left());
            y.left().parent_$eq(y);
            y.red_$eq(z.red());
         }

         if (!yIsRed) {
            this.fixAfterDelete(tree, x, xParent);
         }

         tree.size_$eq(tree.size() - 1);
      }
   }

   private void fixAfterDelete(final RedBlackTree.Tree tree, final RedBlackTree.Node node, final RedBlackTree.Node parent) {
      RedBlackTree.Node x = node;

      for(RedBlackTree.Node xParent = parent; x != tree.root() && this.isBlack(x); xParent = x.parent()) {
         if (x == xParent.left()) {
            RedBlackTree.Node w = xParent.right();
            if (w.red()) {
               w.red_$eq(false);
               xParent.red_$eq(true);
               this.rotateLeft(tree, xParent);
               w = xParent.right();
            }

            if (this.isBlack(w.left()) && this.isBlack(w.right())) {
               w.red_$eq(true);
               x = xParent;
            } else {
               if (this.isBlack(w.right())) {
                  w.left().red_$eq(false);
                  w.red_$eq(true);
                  this.rotateRight(tree, w);
                  w = xParent.right();
               }

               w.red_$eq(xParent.red());
               xParent.red_$eq(false);
               w.right().red_$eq(false);
               this.rotateLeft(tree, xParent);
               x = tree.root();
            }
         } else {
            RedBlackTree.Node w = xParent.left();
            if (w.red()) {
               w.red_$eq(false);
               xParent.red_$eq(true);
               this.rotateRight(tree, xParent);
               w = xParent.left();
            }

            if (this.isBlack(w.right()) && this.isBlack(w.left())) {
               w.red_$eq(true);
               x = xParent;
            } else {
               if (this.isBlack(w.left())) {
                  w.right().red_$eq(false);
                  w.red_$eq(true);
                  this.rotateLeft(tree, w);
                  w = xParent.left();
               }

               w.red_$eq(xParent.red());
               xParent.red_$eq(false);
               w.left().red_$eq(false);
               this.rotateRight(tree, xParent);
               x = tree.root();
            }
         }
      }

      if (x != null) {
         x.red_$eq(false);
      }
   }

   public RedBlackTree.Node scala$collection$mutable$RedBlackTree$$successor(final RedBlackTree.Node node) {
      if (node.right() != null) {
         return this.minNodeNonNull(node.right());
      } else {
         RedBlackTree.Node x = node;

         RedBlackTree.Node y;
         for(y = node.parent(); y != null && x == y.right(); y = y.parent()) {
            x = y;
         }

         return y;
      }
   }

   private RedBlackTree.Node predecessor(final RedBlackTree.Node node) {
      if (node.left() != null) {
         return this.maxNodeNonNull(node.left());
      } else {
         RedBlackTree.Node x = node;

         RedBlackTree.Node y;
         for(y = node.parent(); y != null && x == y.left(); y = y.parent()) {
            x = y;
         }

         return y;
      }
   }

   private void rotateLeft(final RedBlackTree.Tree tree, final RedBlackTree.Node x) {
      if (x != null) {
         RedBlackTree.Node y = x.right();
         x.right_$eq(y.left());
         if (y.left() != null) {
            y.left().parent_$eq(x);
         }

         y.parent_$eq(x.parent());
         if (x.parent() == null) {
            tree.root_$eq(y);
         } else if (x == x.parent().left()) {
            x.parent().left_$eq(y);
         } else {
            x.parent().right_$eq(y);
         }

         y.left_$eq(x);
         x.parent_$eq(y);
      }
   }

   private void rotateRight(final RedBlackTree.Tree tree, final RedBlackTree.Node x) {
      if (x != null) {
         RedBlackTree.Node y = x.left();
         x.left_$eq(y.right());
         if (y.right() != null) {
            y.right().parent_$eq(x);
         }

         y.parent_$eq(x.parent());
         if (x.parent() == null) {
            tree.root_$eq(y);
         } else if (x == x.parent().right()) {
            x.parent().right_$eq(y);
         } else {
            x.parent().left_$eq(y);
         }

         y.right_$eq(x);
         x.parent_$eq(y);
      }
   }

   private void transplant(final RedBlackTree.Tree tree, final RedBlackTree.Node to, final RedBlackTree.Node from) {
      if (to.parent() == null) {
         tree.root_$eq(from);
      } else if (to == to.parent().left()) {
         to.parent().left_$eq(from);
      } else {
         to.parent().right_$eq(from);
      }

      if (from != null) {
         from.parent_$eq(to.parent());
      }
   }

   public void foreach(final RedBlackTree.Tree tree, final Function1 f) {
      RedBlackTree.Node foreachNode_node = tree.root();
      if (foreachNode_node != null) {
         RedBlackTree.Node foreachNode_foreachNodeNonNull_node = foreachNode_node;

         while(true) {
            if (foreachNode_foreachNodeNonNull_node.left() != null) {
               RedBlackTree.Node foreachNodeNonNull_node = foreachNode_foreachNodeNonNull_node.left();

               while(true) {
                  if (foreachNodeNonNull_node.left() != null) {
                     this.foreachNodeNonNull(foreachNodeNonNull_node.left(), f);
                  }

                  f.apply(new Tuple2(foreachNodeNonNull_node.key(), foreachNodeNonNull_node.value()));
                  if (foreachNodeNonNull_node.right() == null) {
                     Object var6 = null;
                     break;
                  }

                  foreachNodeNonNull_node = foreachNodeNonNull_node.right();
               }
            }

            f.apply(new Tuple2(foreachNode_foreachNodeNonNull_node.key(), foreachNode_foreachNodeNonNull_node.value()));
            if (foreachNode_foreachNodeNonNull_node.right() == null) {
               return;
            }

            foreachNode_foreachNodeNonNull_node = foreachNode_foreachNodeNonNull_node.right();
         }
      }
   }

   private void foreachNode(final RedBlackTree.Node node, final Function1 f) {
      if (node != null) {
         RedBlackTree.Node foreachNodeNonNull_node = node;

         while(true) {
            if (foreachNodeNonNull_node.left() != null) {
               this.foreachNodeNonNull(foreachNodeNonNull_node.left(), f);
            }

            f.apply(new Tuple2(foreachNodeNonNull_node.key(), foreachNodeNonNull_node.value()));
            if (foreachNodeNonNull_node.right() == null) {
               return;
            }

            foreachNodeNonNull_node = foreachNodeNonNull_node.right();
         }
      }
   }

   private void foreachNodeNonNull(final RedBlackTree.Node node, final Function1 f) {
      while(true) {
         if (node.left() != null) {
            this.foreachNodeNonNull(node.left(), f);
         }

         f.apply(new Tuple2(node.key(), node.value()));
         if (node.right() == null) {
            return;
         }

         RedBlackTree.Node var10000 = node.right();
         f = f;
         node = var10000;
      }
   }

   public void foreachKey(final RedBlackTree.Tree tree, final Function1 f) {
      RedBlackTree.Node r = tree.root();
      if (r != null) {
         RedBlackTree.Node g$1_node = r;

         while(true) {
            RedBlackTree.Node g$1_l = g$1_node.left();
            if (g$1_l != null) {
               this.g$1(g$1_l, f);
            }

            f.apply(g$1_node.key());
            RedBlackTree.Node g$1_r = g$1_node.right();
            if (g$1_r == null) {
               return;
            }

            g$1_node = g$1_r;
         }
      }
   }

   public void foreachEntry(final RedBlackTree.Tree tree, final Function2 f) {
      RedBlackTree.Node r = tree.root();
      if (r != null) {
         RedBlackTree.Node g$2_node = r;

         while(true) {
            RedBlackTree.Node g$2_l = g$2_node.left();
            if (g$2_l != null) {
               this.g$2(g$2_l, f);
            }

            f.apply(g$2_node.key(), g$2_node.value());
            RedBlackTree.Node g$2_r = g$2_node.right();
            if (g$2_r == null) {
               return;
            }

            g$2_node = g$2_r;
         }
      }
   }

   public void transform(final RedBlackTree.Tree tree, final Function2 f) {
      RedBlackTree.Node transformNode_node = tree.root();
      if (transformNode_node != null) {
         RedBlackTree.Node transformNode_transformNodeNonNull_node = transformNode_node;

         while(true) {
            if (transformNode_transformNodeNonNull_node.left() != null) {
               RedBlackTree.Node transformNodeNonNull_node = transformNode_transformNodeNonNull_node.left();

               while(true) {
                  if (transformNodeNonNull_node.left() != null) {
                     this.transformNodeNonNull(transformNodeNonNull_node.left(), f);
                  }

                  transformNodeNonNull_node.value_$eq(f.apply(transformNodeNonNull_node.key(), transformNodeNonNull_node.value()));
                  if (transformNodeNonNull_node.right() == null) {
                     Object var6 = null;
                     break;
                  }

                  transformNodeNonNull_node = transformNodeNonNull_node.right();
               }
            }

            transformNode_transformNodeNonNull_node.value_$eq(f.apply(transformNode_transformNodeNonNull_node.key(), transformNode_transformNodeNonNull_node.value()));
            if (transformNode_transformNodeNonNull_node.right() == null) {
               return;
            }

            transformNode_transformNodeNonNull_node = transformNode_transformNodeNonNull_node.right();
         }
      }
   }

   private void transformNode(final RedBlackTree.Node node, final Function2 f) {
      if (node != null) {
         RedBlackTree.Node transformNodeNonNull_node = node;

         while(true) {
            if (transformNodeNonNull_node.left() != null) {
               this.transformNodeNonNull(transformNodeNonNull_node.left(), f);
            }

            transformNodeNonNull_node.value_$eq(f.apply(transformNodeNonNull_node.key(), transformNodeNonNull_node.value()));
            if (transformNodeNonNull_node.right() == null) {
               return;
            }

            transformNodeNonNull_node = transformNodeNonNull_node.right();
         }
      }
   }

   private void transformNodeNonNull(final RedBlackTree.Node node, final Function2 f) {
      while(true) {
         if (node.left() != null) {
            this.transformNodeNonNull(node.left(), f);
         }

         node.value_$eq(f.apply(node.key(), node.value()));
         if (node.right() == null) {
            return;
         }

         RedBlackTree.Node var10000 = node.right();
         f = f;
         node = var10000;
      }
   }

   public Iterator iterator(final RedBlackTree.Tree tree, final Option start, final Option end, final Ordering evidence$3) {
      return new RedBlackTree.EntriesIterator(tree, start, end, evidence$3);
   }

   public None$ iterator$default$2() {
      return None$.MODULE$;
   }

   public None$ iterator$default$3() {
      return None$.MODULE$;
   }

   public Iterator keysIterator(final RedBlackTree.Tree tree, final Option start, final Option end, final Ordering evidence$4) {
      return new RedBlackTree.KeysIterator(tree, start, end, evidence$4);
   }

   public None$ keysIterator$default$2() {
      return None$.MODULE$;
   }

   public None$ keysIterator$default$3() {
      return None$.MODULE$;
   }

   public Iterator valuesIterator(final RedBlackTree.Tree tree, final Option start, final Option end, final Ordering evidence$5) {
      return new RedBlackTree.ValuesIterator(tree, start, end, evidence$5);
   }

   public None$ valuesIterator$default$2() {
      return None$.MODULE$;
   }

   public None$ valuesIterator$default$3() {
      return None$.MODULE$;
   }

   public boolean isValid(final RedBlackTree.Tree tree, final Ordering evidence$9) {
      return this.isValidBST(tree.root(), evidence$9) && this.hasProperParentRefs(tree) && this.isValidRedBlackTree(tree) && this.size(tree.root()) == tree.size();
   }

   private boolean hasProperParentRefs(final RedBlackTree.Tree tree) {
      if (tree.root() == null) {
         return true;
      } else {
         return tree.root().parent() == null && this.hasProperParentRefs$1(tree.root());
      }
   }

   private boolean isValidBST(final RedBlackTree.Node node, final Ordering ord) {
      while(node != null) {
         if ((node.left() == null || ord.compare(node.key(), node.left().key()) > 0) && (node.right() == null || ord.compare(node.key(), node.right().key()) < 0)) {
            if (this.isValidBST(node.left(), ord)) {
               RedBlackTree.Node var10000 = node.right();
               ord = ord;
               node = var10000;
               continue;
            }

            return false;
         }

         return false;
      }

      return true;
   }

   private boolean isValidRedBlackTree(final RedBlackTree.Tree tree) {
      return this.isBlack(tree.root()) && this.noRedAfterRed$1(tree.root()) && this.blackHeight$1(tree.root()) >= 0;
   }

   public RedBlackTree.Tree fromOrderedKeys(final Iterator xs, final int size) {
      int maxUsedDepth = 32 - Integer.numberOfLeadingZeros(size);
      return new RedBlackTree.Tree(f$3(1, size, xs, maxUsedDepth), size);
   }

   public RedBlackTree.Tree fromOrderedEntries(final Iterator xs, final int size) {
      int maxUsedDepth = 32 - Integer.numberOfLeadingZeros(size);
      return new RedBlackTree.Tree(f$4(1, size, xs, maxUsedDepth), size);
   }

   public RedBlackTree.Node copyTree(final RedBlackTree.Node n) {
      if (n == null) {
         return null;
      } else {
         RedBlackTree.Node c = new RedBlackTree.Node(n.key(), n.value(), n.red(), this.copyTree(n.left()), this.copyTree(n.right()), (RedBlackTree.Node)null);
         if (c.left() != null) {
            c.left().parent_$eq(c);
         }

         if (c.right() != null) {
            c.right().parent_$eq(c);
         }

         return c;
      }
   }

   private final void g$1(final RedBlackTree.Node node, final Function1 f$1) {
      while(true) {
         RedBlackTree.Node l = node.left();
         if (l != null) {
            this.g$1(l, f$1);
         }

         f$1.apply(node.key());
         RedBlackTree.Node r = node.right();
         if (r == null) {
            return;
         }

         node = r;
      }
   }

   private final void g$2(final RedBlackTree.Node node, final Function2 f$2) {
      while(true) {
         RedBlackTree.Node l = node.left();
         if (l != null) {
            this.g$2(l, f$2);
         }

         f$2.apply(node.key(), node.value());
         RedBlackTree.Node r = node.right();
         if (r == null) {
            return;
         }

         node = r;
      }
   }

   private final boolean hasProperParentRefs$1(final RedBlackTree.Node node) {
      while(node != null) {
         if ((node.left() == null || node.left().parent() == node) && (node.right() == null || node.right().parent() == node)) {
            if (this.hasProperParentRefs$1(node.left())) {
               node = node.right();
               continue;
            }

            return false;
         }

         return false;
      }

      return true;
   }

   private final boolean noRedAfterRed$1(final RedBlackTree.Node node) {
      while(node != null) {
         if (!node.red() || !this.isRed(node.left()) && !this.isRed(node.right())) {
            if (this.noRedAfterRed$1(node.left())) {
               node = node.right();
               continue;
            }

            return false;
         }

         return false;
      }

      return true;
   }

   private final int blackHeight$1(final RedBlackTree.Node node) {
      if (node == null) {
         return 1;
      } else {
         int lh = this.blackHeight$1(node.left());
         int rh = this.blackHeight$1(node.right());
         if (lh != -1 && lh == rh) {
            return this.isRed(node) ? lh : lh + 1;
         } else {
            return -1;
         }
      }
   }

   private static final RedBlackTree.Node f$3(final int level, final int size, final Iterator xs$1, final int maxUsedDepth$1) {
      switch (size) {
         case 0:
            return null;
         case 1:
            return new RedBlackTree.Node(xs$1.next(), (Object)null, level == maxUsedDepth$1 && level != 1, (RedBlackTree.Node)null, (RedBlackTree.Node)null, (RedBlackTree.Node)null);
         default:
            int leftSize = (size - 1) / 2;
            RedBlackTree.Node left = f$3(level + 1, leftSize, xs$1, maxUsedDepth$1);
            Object x = xs$1.next();
            RedBlackTree.Node right = f$3(level + 1, size - 1 - leftSize, xs$1, maxUsedDepth$1);
            RedBlackTree.Node n = new RedBlackTree.Node(x, (Object)null, false, left, right, (RedBlackTree.Node)null);
            if (left != null) {
               left.parent_$eq(n);
            }

            right.parent_$eq(n);
            return n;
      }
   }

   private static final RedBlackTree.Node f$4(final int level, final int size, final Iterator xs$2, final int maxUsedDepth$2) {
      switch (size) {
         case 0:
            return null;
         case 1:
            Tuple2 var4 = (Tuple2)xs$2.next();
            if (var4 == null) {
               throw new MatchError((Object)null);
            }

            Object k = var4._1();
            Object v = var4._2();
            return new RedBlackTree.Node(k, v, level == maxUsedDepth$2 && level != 1, (RedBlackTree.Node)null, (RedBlackTree.Node)null, (RedBlackTree.Node)null);
         default:
            int leftSize = (size - 1) / 2;
            RedBlackTree.Node left = f$4(level + 1, leftSize, xs$2, maxUsedDepth$2);
            Tuple2 var9 = (Tuple2)xs$2.next();
            if (var9 != null) {
               Object k = var9._1();
               Object v = var9._2();
               RedBlackTree.Node right = f$4(level + 1, size - 1 - leftSize, xs$2, maxUsedDepth$2);
               RedBlackTree.Node n = new RedBlackTree.Node(k, v, false, left, right, (RedBlackTree.Node)null);
               if (left != null) {
                  left.parent_$eq(n);
               }

               right.parent_$eq(n);
               return n;
            } else {
               throw new MatchError((Object)null);
            }
      }
   }

   private RedBlackTree$() {
   }
}
