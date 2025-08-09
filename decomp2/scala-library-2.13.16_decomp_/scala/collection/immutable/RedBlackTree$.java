package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.Iterator;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

public final class RedBlackTree$ {
   public static final RedBlackTree$ MODULE$ = new RedBlackTree$();
   private static final Tuple2 null2 = new Tuple2((Object)null, (Object)null);

   public RedBlackTree.Tree validate(final RedBlackTree.Tree tree, final Ordering ordering) {
      if (tree != null) {
         Function1 impl$1_keyProp = (x$1) -> BoxesRunTime.boxToBoolean($anonfun$validate$7(x$1));
         Object var10000 = tree.scala$collection$immutable$RedBlackTree$Tree$$_key;
         if (tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            label78: {
               if (tree.scala$collection$immutable$RedBlackTree$Tree$$_left != null) {
                  RedBlackTree.Tree var6 = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
                  if (var6 == null) {
                     throw null;
                  }

                  if (var6.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
                     var7 = false;
                     break label78;
                  }
               }

               var7 = true;
            }

            if (!var7) {
               throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$validate$2(tree)).toString());
            }

            label71: {
               if (tree.scala$collection$immutable$RedBlackTree$Tree$$_right != null) {
                  RedBlackTree.Tree var8 = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
                  if (var8 == null) {
                     throw null;
                  }

                  if (var8.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
                     var9 = false;
                     break label71;
                  }
               }

               var9 = true;
            }

            if (!var9) {
               throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$validate$3(tree)).toString());
            }
         }

         int impl$1_leftBlacks = tree.scala$collection$immutable$RedBlackTree$Tree$$_left == null ? 0 : impl$1(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, (k) -> BoxesRunTime.boxToBoolean($anonfun$validate$4(keyProp, ordering$1, tree, k)), ordering);
         int impl$1_rightBlacks = tree.scala$collection$immutable$RedBlackTree$Tree$$_right == null ? 0 : impl$1(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, (k) -> BoxesRunTime.boxToBoolean($anonfun$validate$5(keyProp, ordering$1, tree, k)), ordering);
         if (impl$1_leftBlacks != impl$1_rightBlacks) {
            throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$validate$6(tree)).toString());
         }

         int var10 = tree.scala$collection$immutable$RedBlackTree$Tree$$_count;
      }

      return tree;
   }

   public boolean isEmpty(final RedBlackTree.Tree tree) {
      return tree == null;
   }

   public boolean contains(final RedBlackTree.Tree tree, final Object x, final Ordering evidence$1) {
      return this.lookup(tree, x, evidence$1) != null;
   }

   public Option get(final RedBlackTree.Tree tree, final Object x, final Ordering evidence$2) {
      RedBlackTree.Tree var4 = this.lookup(tree, x, evidence$2);
      return (Option)(var4 == null ? None$.MODULE$ : new Some(var4.scala$collection$immutable$RedBlackTree$Tree$$_value));
   }

   public RedBlackTree.Tree lookup(final RedBlackTree.Tree tree, final Object x, final Ordering ordering) {
      while(tree != null) {
         int cmp = ordering.compare(x, tree.scala$collection$immutable$RedBlackTree$Tree$$_key);
         if (cmp < 0) {
            ordering = ordering;
            x = x;
            tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
         } else {
            if (cmp <= 0) {
               return tree;
            }

            ordering = ordering;
            x = x;
            tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
         }
      }

      return null;
   }

   public int count(final RedBlackTree.Tree tree) {
      return tree == null ? 0 : tree.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE;
   }

   public RedBlackTree.Tree update(final RedBlackTree.Tree tree, final Object k, final Object v, final boolean overwrite, final Ordering evidence$3) {
      return this.blacken(this.upd(tree, k, v, overwrite, evidence$3));
   }

   public RedBlackTree.Tree delete(final RedBlackTree.Tree tree, final Object k, final Ordering evidence$4) {
      return this.blacken(this.del(tree, k, evidence$4));
   }

   public RedBlackTree.Tree rangeImpl(final RedBlackTree.Tree tree, final Option from, final Option until, final Ordering evidence$5) {
      Tuple2 var5 = new Tuple2(from, until);
      if (from instanceof Some) {
         Object from = ((Some)from).value();
         if (until instanceof Some) {
            Object until = ((Some)until).value();
            return this.range(tree, from, until, evidence$5);
         }
      }

      if (from instanceof Some) {
         Object from = ((Some)from).value();
         if (None$.MODULE$.equals(until)) {
            return this.from(tree, from, evidence$5);
         }
      }

      if (None$.MODULE$.equals(from) && until instanceof Some) {
         Object until = ((Some)until).value();
         return this.until(tree, until, evidence$5);
      } else if (None$.MODULE$.equals(from) && None$.MODULE$.equals(until)) {
         return tree;
      } else {
         throw new MatchError(var5);
      }
   }

   public RedBlackTree.Tree range(final RedBlackTree.Tree tree, final Object from, final Object until, final Ordering evidence$6) {
      return this.blacken(this.doRange(tree, from, until, evidence$6));
   }

   public RedBlackTree.Tree from(final RedBlackTree.Tree tree, final Object from, final Ordering evidence$7) {
      return this.blacken(this.doFrom(tree, from, evidence$7));
   }

   public RedBlackTree.Tree to(final RedBlackTree.Tree tree, final Object to, final Ordering evidence$8) {
      return this.blacken(this.doTo(tree, to, evidence$8));
   }

   public RedBlackTree.Tree until(final RedBlackTree.Tree tree, final Object key, final Ordering evidence$9) {
      return this.blacken(this.doUntil(tree, key, evidence$9));
   }

   public RedBlackTree.Tree drop(final RedBlackTree.Tree tree, final int n, final Ordering evidence$10) {
      return this.blacken(this.doDrop(tree, n));
   }

   public RedBlackTree.Tree take(final RedBlackTree.Tree tree, final int n, final Ordering evidence$11) {
      return this.blacken(this.doTake(tree, n));
   }

   public RedBlackTree.Tree slice(final RedBlackTree.Tree tree, final int from, final int until, final Ordering evidence$12) {
      return this.blacken(this.doSlice(tree, from, until));
   }

   public RedBlackTree.Tree smallest(final RedBlackTree.Tree tree) {
      if (tree == null) {
         throw new NoSuchElementException("empty tree");
      } else {
         for(RedBlackTree.Tree result = tree; result != null; result = result.scala$collection$immutable$RedBlackTree$Tree$$_left) {
            if (result.scala$collection$immutable$RedBlackTree$Tree$$_left == null) {
               return result;
            }
         }

         throw null;
      }
   }

   public RedBlackTree.Tree greatest(final RedBlackTree.Tree tree) {
      if (tree == null) {
         throw new NoSuchElementException("empty tree");
      } else {
         for(RedBlackTree.Tree result = tree; result != null; result = result.scala$collection$immutable$RedBlackTree$Tree$$_right) {
            if (result.scala$collection$immutable$RedBlackTree$Tree$$_right == null) {
               return result;
            }
         }

         throw null;
      }
   }

   public RedBlackTree.Tree tail(final RedBlackTree.Tree tree) {
      return this.blacken(this._tail$1(tree));
   }

   public RedBlackTree.Tree init(final RedBlackTree.Tree tree) {
      return this.blacken(this._init$1(tree));
   }

   public RedBlackTree.Tree minAfter(final RedBlackTree.Tree tree, final Object x, final Ordering ordering) {
      while(tree != null) {
         int cmp = ordering.compare(x, tree.scala$collection$immutable$RedBlackTree$Tree$$_key);
         if (cmp == 0) {
            return tree;
         }

         if (cmp < 0) {
            RedBlackTree.Tree l = this.minAfter(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, x, ordering);
            if (l != null) {
               return l;
            }

            return tree;
         }

         ordering = ordering;
         x = x;
         tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
      }

      return null;
   }

   public RedBlackTree.Tree maxBefore(final RedBlackTree.Tree tree, final Object x, final Ordering ordering) {
      while(tree != null) {
         if (ordering.compare(x, tree.scala$collection$immutable$RedBlackTree$Tree$$_key) > 0) {
            RedBlackTree.Tree r = this.maxBefore(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, x, ordering);
            if (r != null) {
               return r;
            }

            return tree;
         }

         ordering = ordering;
         x = x;
         tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
      }

      return null;
   }

   public void foreach(final RedBlackTree.Tree tree, final Function1 f) {
      if (tree != null) {
         for(RedBlackTree.Tree _foreach_tree = tree; _foreach_tree != null; _foreach_tree = _foreach_tree.scala$collection$immutable$RedBlackTree$Tree$$_right) {
            if (_foreach_tree.scala$collection$immutable$RedBlackTree$Tree$$_left != null) {
               this._foreach(_foreach_tree.scala$collection$immutable$RedBlackTree$Tree$$_left, f);
            }

            f.apply(new Tuple2(_foreach_tree.scala$collection$immutable$RedBlackTree$Tree$$_key, _foreach_tree.scala$collection$immutable$RedBlackTree$Tree$$_value));
            if (_foreach_tree.scala$collection$immutable$RedBlackTree$Tree$$_right == null) {
               return;
            }
         }

         throw null;
      }
   }

   public boolean keysEqual(final RedBlackTree.Tree a, final RedBlackTree.Tree b, final Ordering evidence$13) {
      if (a == b) {
         return true;
      } else if (a == null) {
         return false;
      } else if (b == null) {
         return false;
      } else {
         return (a.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) == (b.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) && (new RedBlackTree.EqualsIterator(a, evidence$13)).sameKeys(new RedBlackTree.EqualsIterator(b, evidence$13));
      }
   }

   public boolean valuesEqual(final RedBlackTree.Tree a, final RedBlackTree.Tree b, final Ordering evidence$14) {
      if (a == b) {
         return true;
      } else if (a == null) {
         return false;
      } else if (b == null) {
         return false;
      } else {
         return (a.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) == (b.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) && (new RedBlackTree.EqualsIterator(a, evidence$14)).sameValues(new RedBlackTree.EqualsIterator(b, evidence$14));
      }
   }

   public boolean entriesEqual(final RedBlackTree.Tree a, final RedBlackTree.Tree b, final Ordering evidence$15) {
      if (a == b) {
         return true;
      } else if (a == null) {
         return false;
      } else if (b == null) {
         return false;
      } else {
         return (a.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) == (b.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) && (new RedBlackTree.EqualsIterator(a, evidence$15)).sameEntries(new RedBlackTree.EqualsIterator(b, evidence$15));
      }
   }

   private void _foreach(final RedBlackTree.Tree tree, final Function1 f) {
      while(tree != null) {
         if (tree.scala$collection$immutable$RedBlackTree$Tree$$_left != null) {
            this._foreach(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, f);
         }

         f.apply(new Tuple2(tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value));
         if (tree.scala$collection$immutable$RedBlackTree$Tree$$_right == null) {
            return;
         }

         f = f;
         tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
      }

      throw null;
   }

   public void foreachKey(final RedBlackTree.Tree tree, final Function1 f) {
      if (tree != null) {
         for(RedBlackTree.Tree _foreachKey_tree = tree; _foreachKey_tree != null; _foreachKey_tree = _foreachKey_tree.scala$collection$immutable$RedBlackTree$Tree$$_right) {
            if (_foreachKey_tree.scala$collection$immutable$RedBlackTree$Tree$$_left != null) {
               this._foreachKey(_foreachKey_tree.scala$collection$immutable$RedBlackTree$Tree$$_left, f);
            }

            f.apply(_foreachKey_tree.scala$collection$immutable$RedBlackTree$Tree$$_key);
            if (_foreachKey_tree.scala$collection$immutable$RedBlackTree$Tree$$_right == null) {
               return;
            }
         }

         throw null;
      }
   }

   private void _foreachKey(final RedBlackTree.Tree tree, final Function1 f) {
      while(tree != null) {
         if (tree.scala$collection$immutable$RedBlackTree$Tree$$_left != null) {
            this._foreachKey(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, f);
         }

         f.apply(tree.scala$collection$immutable$RedBlackTree$Tree$$_key);
         if (tree.scala$collection$immutable$RedBlackTree$Tree$$_right == null) {
            return;
         }

         f = f;
         tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
      }

      throw null;
   }

   public void foreachEntry(final RedBlackTree.Tree tree, final Function2 f) {
      if (tree != null) {
         for(RedBlackTree.Tree _foreachEntry_tree = tree; _foreachEntry_tree != null; _foreachEntry_tree = _foreachEntry_tree.scala$collection$immutable$RedBlackTree$Tree$$_right) {
            if (_foreachEntry_tree.scala$collection$immutable$RedBlackTree$Tree$$_left != null) {
               this._foreachEntry(_foreachEntry_tree.scala$collection$immutable$RedBlackTree$Tree$$_left, f);
            }

            f.apply(_foreachEntry_tree.scala$collection$immutable$RedBlackTree$Tree$$_key, _foreachEntry_tree.scala$collection$immutable$RedBlackTree$Tree$$_value);
            if (_foreachEntry_tree.scala$collection$immutable$RedBlackTree$Tree$$_right == null) {
               return;
            }
         }

         throw null;
      }
   }

   private void _foreachEntry(final RedBlackTree.Tree tree, final Function2 f) {
      while(tree != null) {
         if (tree.scala$collection$immutable$RedBlackTree$Tree$$_left != null) {
            this._foreachEntry(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, f);
         }

         f.apply(tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value);
         if (tree.scala$collection$immutable$RedBlackTree$Tree$$_right == null) {
            return;
         }

         f = f;
         tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
      }

      throw null;
   }

   public Iterator iterator(final RedBlackTree.Tree tree, final Option start, final Ordering evidence$16) {
      return new RedBlackTree.EntriesIterator(tree, start, evidence$16);
   }

   public None$ iterator$default$2() {
      return None$.MODULE$;
   }

   public Iterator keysIterator(final RedBlackTree.Tree tree, final Option start, final Ordering evidence$17) {
      return new RedBlackTree.KeysIterator(tree, start, evidence$17);
   }

   public None$ keysIterator$default$2() {
      return None$.MODULE$;
   }

   public Iterator valuesIterator(final RedBlackTree.Tree tree, final Option start, final Ordering evidence$18) {
      return new RedBlackTree.ValuesIterator(tree, start, evidence$18);
   }

   public None$ valuesIterator$default$2() {
      return None$.MODULE$;
   }

   public RedBlackTree.Tree nth(final RedBlackTree.Tree tree, final int n) {
      while(tree != null) {
         int count = this.count(tree.scala$collection$immutable$RedBlackTree$Tree$$_left);
         if (n < count) {
            n = n;
            tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
         } else {
            if (n <= count) {
               return tree;
            }

            n = n - count - 1;
            tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
         }
      }

      throw null;
   }

   public boolean isBlack(final RedBlackTree.Tree tree) {
      return tree == null || tree.scala$collection$immutable$RedBlackTree$Tree$$_count < 0;
   }

   public boolean scala$collection$immutable$RedBlackTree$$isRedTree(final RedBlackTree.Tree tree) {
      return tree != null && tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
   }

   private boolean isBlackTree(final RedBlackTree.Tree tree) {
      return tree != null && tree.scala$collection$immutable$RedBlackTree$Tree$$_count < 0;
   }

   private RedBlackTree.Tree blacken(final RedBlackTree.Tree t) {
      return t == null ? null : t.black();
   }

   private RedBlackTree.Tree maybeBlacken(final RedBlackTree.Tree t) {
      if (this.isBlack(t)) {
         return t;
      } else if (t == null) {
         throw null;
      } else {
         RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = t.scala$collection$immutable$RedBlackTree$Tree$$_left;
         boolean var10000 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
         Object var4 = null;
         if (!var10000) {
            RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = t.scala$collection$immutable$RedBlackTree$Tree$$_right;
            var10000 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
            Object var5 = null;
            if (!var10000) {
               return t;
            }
         }

         return t.black();
      }
   }

   private RedBlackTree.Tree mkTree(final boolean isBlack, final Object key, final Object value, final RedBlackTree.Tree left, final RedBlackTree.Tree right) {
      int sizeAndColour = (left == null ? 0 : left.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) + (right == null ? 0 : right.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) + 1 | (isBlack ? Integer.MIN_VALUE : 0);
      return new RedBlackTree.Tree(key, value, left, right, sizeAndColour);
   }

   private RedBlackTree.Tree balanceLeft(final RedBlackTree.Tree tree, final RedBlackTree.Tree newLeft) {
      if (tree == null) {
         throw null;
      } else if (tree.scala$collection$immutable$RedBlackTree$Tree$$_left == newLeft) {
         return tree;
      } else if (newLeft == null) {
         throw null;
      } else if (newLeft.scala$collection$immutable$RedBlackTree$Tree$$_count < 0) {
         return tree.withLeft(newLeft);
      } else {
         RedBlackTree.Tree newLeft_left = newLeft.scala$collection$immutable$RedBlackTree$Tree$$_left;
         RedBlackTree.Tree newLeft_right = newLeft.scala$collection$immutable$RedBlackTree$Tree$$_right;
         if (newLeft_left != null && newLeft_left.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            RedBlackTree.Tree resultLeft = newLeft_left.black();
            RedBlackTree.Tree resultRight = tree.blackWithLeft(newLeft_right);
            return newLeft.withLeftRight(resultLeft, resultRight);
         } else if (newLeft_right != null && newLeft_right.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            if (newLeft_right == null) {
               throw null;
            } else {
               RedBlackTree.Tree newLeft_right_right = newLeft_right.scala$collection$immutable$RedBlackTree$Tree$$_right;
               RedBlackTree.Tree resultLeft = newLeft.blackWithRight(newLeft_right.scala$collection$immutable$RedBlackTree$Tree$$_left);
               RedBlackTree.Tree resultRight = tree.blackWithLeft(newLeft_right_right);
               return newLeft_right.withLeftRight(resultLeft, resultRight);
            }
         } else {
            return tree.withLeft(newLeft);
         }
      }
   }

   private RedBlackTree.Tree balanceRight(final RedBlackTree.Tree tree, final RedBlackTree.Tree newRight) {
      if (tree == null) {
         throw null;
      } else if (tree.scala$collection$immutable$RedBlackTree$Tree$$_right == newRight) {
         return tree;
      } else if (newRight == null) {
         throw null;
      } else if (newRight.scala$collection$immutable$RedBlackTree$Tree$$_count < 0) {
         return tree.withRight(newRight);
      } else {
         RedBlackTree.Tree newRight_left = newRight.scala$collection$immutable$RedBlackTree$Tree$$_left;
         if (newRight_left != null && newRight_left.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            if (newRight_left == null) {
               throw null;
            } else {
               RedBlackTree.Tree resultLeft = tree.blackWithRight(newRight_left.scala$collection$immutable$RedBlackTree$Tree$$_left);
               RedBlackTree.Tree resultRight = newRight.blackWithLeft(newRight_left.scala$collection$immutable$RedBlackTree$Tree$$_right);
               return newRight_left.withLeftRight(resultLeft, resultRight);
            }
         } else {
            RedBlackTree.Tree newRight_right = newRight.scala$collection$immutable$RedBlackTree$Tree$$_right;
            if (newRight_right != null && newRight_right.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
               RedBlackTree.Tree resultLeft = tree.blackWithRight(newRight_left);
               RedBlackTree.Tree resultRight = newRight_right.black();
               return newRight.withLeftRight(resultLeft, resultRight);
            } else {
               return tree.withRight(newRight);
            }
         }
      }
   }

   private RedBlackTree.Tree upd(final RedBlackTree.Tree tree, final Object k, final Object v, final boolean overwrite, final Ordering ordering) {
      if (tree == null) {
         return this.RedTree(k, v, (RedBlackTree.Tree)null, (RedBlackTree.Tree)null);
      } else if (k == tree.scala$collection$immutable$RedBlackTree$Tree$$_key) {
         return overwrite ? tree.withV(v) : tree;
      } else {
         int cmp = ordering.compare(k, tree.scala$collection$immutable$RedBlackTree$Tree$$_key);
         if (cmp < 0) {
            return this.balanceLeft(tree, this.upd(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, k, v, overwrite, ordering));
         } else if (cmp > 0) {
            return this.balanceRight(tree, this.upd(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, k, v, overwrite, ordering));
         } else {
            return overwrite && v != tree.scala$collection$immutable$RedBlackTree$Tree$$_value ? tree.withV(v) : tree;
         }
      }
   }

   private RedBlackTree.Tree updNth(final RedBlackTree.Tree tree, final int idx, final Object k, final Object v) {
      if (tree == null) {
         return this.RedTree(k, v, (RedBlackTree.Tree)null, (RedBlackTree.Tree)null);
      } else {
         int rank = this.count(tree.scala$collection$immutable$RedBlackTree$Tree$$_left) + 1;
         if (idx < rank) {
            return this.balanceLeft(tree, this.updNth(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, idx, k, v));
         } else {
            return idx > rank ? this.balanceRight(tree, this.updNth(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, idx - rank, k, v)) : tree;
         }
      }
   }

   private RedBlackTree.Tree doFrom(final RedBlackTree.Tree tree, final Object from, final Ordering ordering) {
      if (tree == null) {
         return null;
      } else if (ordering.lt(tree.scala$collection$immutable$RedBlackTree$Tree$$_key, from)) {
         return this.doFrom(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, from, ordering);
      } else {
         RedBlackTree.Tree newLeft = this.doFrom(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, from, ordering);
         if (newLeft == tree.scala$collection$immutable$RedBlackTree$Tree$$_left) {
            return tree;
         } else {
            return newLeft == null ? this.maybeBlacken(this.upd(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, false, ordering)) : this.scala$collection$immutable$RedBlackTree$$join(newLeft, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, tree.scala$collection$immutable$RedBlackTree$Tree$$_right);
         }
      }
   }

   private RedBlackTree.Tree doTo(final RedBlackTree.Tree tree, final Object to, final Ordering ordering) {
      if (tree == null) {
         return null;
      } else if (ordering.lt(to, tree.scala$collection$immutable$RedBlackTree$Tree$$_key)) {
         return this.doTo(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, to, ordering);
      } else {
         RedBlackTree.Tree newRight = this.doTo(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, to, ordering);
         if (newRight == tree.scala$collection$immutable$RedBlackTree$Tree$$_right) {
            return tree;
         } else {
            return newRight == null ? this.maybeBlacken(this.upd(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, false, ordering)) : this.scala$collection$immutable$RedBlackTree$$join(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, newRight);
         }
      }
   }

   private RedBlackTree.Tree doUntil(final RedBlackTree.Tree tree, final Object until, final Ordering ordering) {
      if (tree == null) {
         return null;
      } else if (ordering.lteq(until, tree.scala$collection$immutable$RedBlackTree$Tree$$_key)) {
         return this.doUntil(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, until, ordering);
      } else {
         RedBlackTree.Tree newRight = this.doUntil(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, until, ordering);
         if (newRight == tree.scala$collection$immutable$RedBlackTree$Tree$$_right) {
            return tree;
         } else {
            return newRight == null ? this.maybeBlacken(this.upd(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, false, ordering)) : this.scala$collection$immutable$RedBlackTree$$join(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, newRight);
         }
      }
   }

   private RedBlackTree.Tree doRange(final RedBlackTree.Tree tree, final Object from, final Object until, final Ordering ordering) {
      if (tree == null) {
         return null;
      } else if (ordering.lt(tree.scala$collection$immutable$RedBlackTree$Tree$$_key, from)) {
         return this.doRange(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, from, until, ordering);
      } else if (ordering.lteq(until, tree.scala$collection$immutable$RedBlackTree$Tree$$_key)) {
         return this.doRange(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, from, until, ordering);
      } else {
         RedBlackTree.Tree newLeft = this.doFrom(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, from, ordering);
         RedBlackTree.Tree newRight = this.doUntil(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, until, ordering);
         if (newLeft == tree.scala$collection$immutable$RedBlackTree$Tree$$_left && newRight == tree.scala$collection$immutable$RedBlackTree$Tree$$_right) {
            return tree;
         } else if (newLeft == null) {
            return this.upd(newRight, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, false, ordering);
         } else {
            return newRight == null ? this.upd(newLeft, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, false, ordering) : this.scala$collection$immutable$RedBlackTree$$join(newLeft, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, newRight);
         }
      }
   }

   private RedBlackTree.Tree doDrop(final RedBlackTree.Tree tree, final int n) {
      while(true) {
         if (tree != null && n > 0) {
            if (n >= (tree.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE)) {
               return null;
            }

            int l = this.count(tree.scala$collection$immutable$RedBlackTree$Tree$$_left);
            if (n > l) {
               n = n - l - 1;
               tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
               continue;
            }

            if (n == l) {
               return this.scala$collection$immutable$RedBlackTree$$join((RedBlackTree.Tree)null, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, tree.scala$collection$immutable$RedBlackTree$Tree$$_right);
            }

            return this.scala$collection$immutable$RedBlackTree$$join(this.doDrop(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, n), tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, tree.scala$collection$immutable$RedBlackTree$Tree$$_right);
         }

         return tree;
      }
   }

   private RedBlackTree.Tree doTake(final RedBlackTree.Tree tree, final int n) {
      while(true) {
         if (tree != null && n > 0) {
            if (n >= (tree.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE)) {
               return tree;
            }

            int l = this.count(tree.scala$collection$immutable$RedBlackTree$Tree$$_left);
            if (n <= l) {
               n = n;
               tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
               continue;
            }

            if (n == l + 1) {
               return this.maybeBlacken(this.updNth(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, n, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value));
            }

            return this.scala$collection$immutable$RedBlackTree$$join(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, this.doTake(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, n - l - 1));
         }

         return null;
      }
   }

   private RedBlackTree.Tree doSlice(final RedBlackTree.Tree tree, final int from, final int until) {
      while(true) {
         if (tree != null && from < until && from < (tree.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) && until > 0) {
            if (from <= 0 && until >= (tree.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE)) {
               return tree;
            }

            int l = this.count(tree.scala$collection$immutable$RedBlackTree$Tree$$_left);
            if (until <= l) {
               until = until;
               from = from;
               tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
               continue;
            }

            if (from > l) {
               int var10001 = from - l - 1;
               until = until - l - 1;
               from = var10001;
               tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
               continue;
            }

            return this.scala$collection$immutable$RedBlackTree$$join(this.doDrop(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, from), tree.scala$collection$immutable$RedBlackTree$Tree$$_key, tree.scala$collection$immutable$RedBlackTree$Tree$$_value, this.doTake(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, until - l - 1));
         }

         return null;
      }
   }

   public final int colourBit() {
      return Integer.MIN_VALUE;
   }

   public final int colourMask() {
      return Integer.MAX_VALUE;
   }

   public final int initialBlackCount() {
      return Integer.MIN_VALUE;
   }

   public final int initialRedCount() {
      return 0;
   }

   public RedBlackTree.Tree mutableRedTree(final Object key, final Object value, final RedBlackTree.Tree left, final RedBlackTree.Tree right) {
      return new RedBlackTree.Tree(key, value, left, right, 0);
   }

   public RedBlackTree.Tree mutableBlackTree(final Object key, final Object value, final RedBlackTree.Tree left, final RedBlackTree.Tree right) {
      return new RedBlackTree.Tree(key, value, left, right, Integer.MIN_VALUE);
   }

   public RedBlackTree.Tree RedTree(final Object key, final Object value, final RedBlackTree.Tree left, final RedBlackTree.Tree right) {
      int size = (left == null ? 0 : left.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) + (right == null ? 0 : right.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) + 1;
      return new RedBlackTree.Tree(key, value, left, right, 0 | size);
   }

   public RedBlackTree.Tree BlackTree(final Object key, final Object value, final RedBlackTree.Tree left, final RedBlackTree.Tree right) {
      int size = (left == null ? 0 : left.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) + (right == null ? 0 : right.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE) + 1;
      return new RedBlackTree.Tree(key, value, left, right, Integer.MIN_VALUE | size);
   }

   private int sizeOf(final RedBlackTree.Tree tree) {
      return tree == null ? 0 : tree.scala$collection$immutable$RedBlackTree$Tree$$_count & Integer.MAX_VALUE;
   }

   public RedBlackTree.Tree fromOrderedKeys(final Iterator xs, final int size) {
      int maxUsedDepth = 32 - Integer.numberOfLeadingZeros(size);
      return this.f$1(1, size, maxUsedDepth, xs);
   }

   public RedBlackTree.Tree fromOrderedEntries(final Iterator xs, final int size) {
      int maxUsedDepth = 32 - Integer.numberOfLeadingZeros(size);
      return this.f$2(1, size, xs, maxUsedDepth);
   }

   public RedBlackTree.Tree transform(final RedBlackTree.Tree t, final Function2 f) {
      if (t == null) {
         return null;
      } else {
         Object k = t.scala$collection$immutable$RedBlackTree$Tree$$_key;
         Object v = t.scala$collection$immutable$RedBlackTree$Tree$$_value;
         RedBlackTree.Tree l = t.scala$collection$immutable$RedBlackTree$Tree$$_left;
         RedBlackTree.Tree r = t.scala$collection$immutable$RedBlackTree$Tree$$_right;
         RedBlackTree.Tree l2 = this.transform(l, f);
         Object v2 = f.apply(k, v);
         RedBlackTree.Tree r2 = this.transform(r, f);
         return v2 == v && l2 == l && r2 == r ? t : this.mkTree(t.scala$collection$immutable$RedBlackTree$Tree$$_count < 0, k, v2, l2, r2);
      }
   }

   public RedBlackTree.Tree filterEntries(final RedBlackTree.Tree t, final Function2 f) {
      if (t == null) {
         return null;
      } else {
         Object fk$1_k = t.scala$collection$immutable$RedBlackTree$Tree$$_key;
         Object fk$1_v = t.scala$collection$immutable$RedBlackTree$Tree$$_value;
         RedBlackTree.Tree fk$1_l = t.scala$collection$immutable$RedBlackTree$Tree$$_left;
         RedBlackTree.Tree fk$1_r = t.scala$collection$immutable$RedBlackTree$Tree$$_right;
         RedBlackTree.Tree fk$1_l2 = fk$1_l == null ? null : this.fk$1(fk$1_l, f);
         boolean fk$1_keep = BoxesRunTime.unboxToBoolean(f.apply(fk$1_k, fk$1_v));
         RedBlackTree.Tree fk$1_r2 = fk$1_r == null ? null : this.fk$1(fk$1_r, f);
         RedBlackTree.Tree var10001 = !fk$1_keep ? this.scala$collection$immutable$RedBlackTree$$join2(fk$1_l2, fk$1_r2) : (fk$1_l2 == fk$1_l && fk$1_r2 == fk$1_r ? t : this.scala$collection$immutable$RedBlackTree$$join(fk$1_l2, fk$1_k, fk$1_v, fk$1_r2));
         fk$1_k = null;
         fk$1_v = null;
         fk$1_l = null;
         fk$1_r = null;
         fk$1_l2 = null;
         fk$1_r2 = null;
         return this.blacken(var10001);
      }
   }

   public Tuple2 partitionEntries(final RedBlackTree.Tree t, final Function2 p) {
      if (t == null) {
         return new Tuple2((Object)null, (Object)null);
      } else {
         LazyRef partitioner$module = new LazyRef();
         partitioner$1$ var10000;
         if (partitioner$module.initialized()) {
            var10000 = (partitioner$1$)partitioner$module.value();
         } else {
            synchronized(partitioner$module){}

            partitioner$1$ var4;
            try {
               var4 = partitioner$module.initialized() ? (partitioner$1$)partitioner$module.value() : (partitioner$1$)partitioner$module.initialize(new partitioner$1$(p));
            } catch (Throwable var6) {
               throw var6;
            }

            var10000 = var4;
            var4 = null;
         }

         Object var8 = null;
         var10000.fk(t);
         return new Tuple2(this.blacken(this.partitioner$2(partitioner$module, p).tmpk()), this.blacken(this.partitioner$2(partitioner$module, p).tmpd()));
      }
   }

   private RedBlackTree.Tree del(final RedBlackTree.Tree tree, final Object k, final Ordering ordering) {
      if (tree == null) {
         return null;
      } else {
         int cmp = ordering.compare(k, tree.scala$collection$immutable$RedBlackTree$Tree$$_key);
         if (cmp < 0) {
            RedBlackTree.Tree newLeft = this.del(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, k, ordering);
            if (newLeft == tree.scala$collection$immutable$RedBlackTree$Tree$$_left) {
               return tree;
            } else {
               RedBlackTree.Tree isBlackTree_tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
               boolean var11 = isBlackTree_tree != null && isBlackTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count < 0;
               Object var9 = null;
               return var11 ? this.balLeft(tree, newLeft, tree.scala$collection$immutable$RedBlackTree$Tree$$_right) : tree.redWithLeft(newLeft);
            }
         } else if (cmp > 0) {
            RedBlackTree.Tree newRight = this.del(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, k, ordering);
            if (newRight == tree.scala$collection$immutable$RedBlackTree$Tree$$_right) {
               return tree;
            } else {
               RedBlackTree.Tree isBlackTree_tree = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
               boolean var10000 = isBlackTree_tree != null && isBlackTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count < 0;
               Object var10 = null;
               return var10000 ? this.balRight(tree, tree.scala$collection$immutable$RedBlackTree$Tree$$_left, newRight) : tree.redWithRight(newRight);
            }
         } else {
            return this.append(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, tree.scala$collection$immutable$RedBlackTree$Tree$$_right);
         }
      }
   }

   private RedBlackTree.Tree balance(final RedBlackTree.Tree tree, final RedBlackTree.Tree tl, final RedBlackTree.Tree tr) {
      if (tl != null && tl.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
         if (tr != null && tr.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            return tree.redWithLeftRight(tl.black(), tr.black());
         } else if (tl == null) {
            throw null;
         } else {
            RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = tl.scala$collection$immutable$RedBlackTree$Tree$$_left;
            boolean var14 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
            Object var8 = null;
            if (var14) {
               return tl.withLeftRight(tl.scala$collection$immutable$RedBlackTree$Tree$$_left.black(), tree.blackWithLeftRight(tl.scala$collection$immutable$RedBlackTree$Tree$$_right, tr));
            } else {
               RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = tl.scala$collection$immutable$RedBlackTree$Tree$$_right;
               var14 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
               Object var9 = null;
               if (var14) {
                  RedBlackTree.Tree var16 = tl.scala$collection$immutable$RedBlackTree$Tree$$_right;
                  RedBlackTree.Tree var10002 = tl.scala$collection$immutable$RedBlackTree$Tree$$_right;
                  if (var10002 == null) {
                     throw null;
                  } else {
                     RedBlackTree.Tree var17 = tl.blackWithRight(var10002.scala$collection$immutable$RedBlackTree$Tree$$_left);
                     RedBlackTree.Tree var19 = tl.scala$collection$immutable$RedBlackTree$Tree$$_right;
                     if (var19 == null) {
                        throw null;
                     } else {
                        return var16.withLeftRight(var17, tree.blackWithLeftRight(var19.scala$collection$immutable$RedBlackTree$Tree$$_right, tr));
                     }
                  }
               } else {
                  return tree.blackWithLeftRight(tl, tr);
               }
            }
         }
      } else if (tr != null && tr.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
         if (tr == null) {
            throw null;
         } else {
            RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = tr.scala$collection$immutable$RedBlackTree$Tree$$_right;
            boolean var10000 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
            Object var10 = null;
            if (var10000) {
               return tr.withLeftRight(tree.blackWithLeftRight(tl, tr.scala$collection$immutable$RedBlackTree$Tree$$_left), tr.scala$collection$immutable$RedBlackTree$Tree$$_right.black());
            } else {
               RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = tr.scala$collection$immutable$RedBlackTree$Tree$$_left;
               var10000 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
               Object var11 = null;
               if (var10000) {
                  RedBlackTree.Tree var13 = tr.scala$collection$immutable$RedBlackTree$Tree$$_left;
                  RedBlackTree.Tree var10003 = tr.scala$collection$immutable$RedBlackTree$Tree$$_left;
                  if (var10003 == null) {
                     throw null;
                  } else {
                     RedBlackTree.Tree var10001 = tree.blackWithLeftRight(tl, var10003.scala$collection$immutable$RedBlackTree$Tree$$_left);
                     var10003 = tr.scala$collection$immutable$RedBlackTree$Tree$$_left;
                     if (var10003 == null) {
                        throw null;
                     } else {
                        return var13.withLeftRight(var10001, tr.blackWithLeftRight(var10003.scala$collection$immutable$RedBlackTree$Tree$$_right, tr.scala$collection$immutable$RedBlackTree$Tree$$_right));
                     }
                  }
               } else {
                  return tree.blackWithLeftRight(tl, tr);
               }
            }
         }
      } else {
         return tree.blackWithLeftRight(tl, tr);
      }
   }

   private RedBlackTree.Tree balLeft(final RedBlackTree.Tree tree, final RedBlackTree.Tree tl, final RedBlackTree.Tree tr) {
      if (tl != null && tl.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
         return tree.redWithLeftRight(tl.black(), tr);
      } else if (tr != null && tr.scala$collection$immutable$RedBlackTree$Tree$$_count < 0) {
         return this.balance(tree, tl, tr.red());
      } else {
         if (tr != null && tr.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            if (tr == null) {
               throw null;
            }

            RedBlackTree.Tree isBlackTree_tree = tr.scala$collection$immutable$RedBlackTree$Tree$$_left;
            boolean var10000 = isBlackTree_tree != null && isBlackTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count < 0;
            Object var6 = null;
            if (var10000) {
               RedBlackTree.Tree var8 = tr.scala$collection$immutable$RedBlackTree$Tree$$_left;
               RedBlackTree.Tree var10003 = tr.scala$collection$immutable$RedBlackTree$Tree$$_left;
               if (var10003 == null) {
                  throw null;
               }

               RedBlackTree.Tree var10001 = tree.blackWithLeftRight(tl, var10003.scala$collection$immutable$RedBlackTree$Tree$$_left);
               RedBlackTree.Tree var10004 = tr.scala$collection$immutable$RedBlackTree$Tree$$_left;
               if (var10004 == null) {
                  throw null;
               }

               return var8.redWithLeftRight(var10001, this.balance(tr, var10004.scala$collection$immutable$RedBlackTree$Tree$$_right, tr.scala$collection$immutable$RedBlackTree$Tree$$_right.red()));
            }
         }

         scala.sys.package$ var7 = scala.sys.package$.MODULE$;
         String error_message = "Defect: invariance violation";
         throw new RuntimeException(error_message);
      }
   }

   private RedBlackTree.Tree balRight(final RedBlackTree.Tree tree, final RedBlackTree.Tree tl, final RedBlackTree.Tree tr) {
      if (tr != null && tr.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
         return tree.redWithLeftRight(tl, tr.black());
      } else if (tl != null && tl.scala$collection$immutable$RedBlackTree$Tree$$_count < 0) {
         return this.balance(tree, tl.red(), tr);
      } else {
         if (tl != null && tl.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            if (tl == null) {
               throw null;
            }

            RedBlackTree.Tree isBlackTree_tree = tl.scala$collection$immutable$RedBlackTree$Tree$$_right;
            boolean var10000 = isBlackTree_tree != null && isBlackTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count < 0;
            Object var6 = null;
            if (var10000) {
               RedBlackTree.Tree var8 = tl.scala$collection$immutable$RedBlackTree$Tree$$_right;
               RedBlackTree.Tree var10003 = tl.scala$collection$immutable$RedBlackTree$Tree$$_left.red();
               RedBlackTree.Tree var10004 = tl.scala$collection$immutable$RedBlackTree$Tree$$_right;
               if (var10004 == null) {
                  throw null;
               }

               RedBlackTree.Tree var10001 = this.balance(tl, var10003, var10004.scala$collection$immutable$RedBlackTree$Tree$$_left);
               var10003 = tl.scala$collection$immutable$RedBlackTree$Tree$$_right;
               if (var10003 == null) {
                  throw null;
               }

               return var8.redWithLeftRight(var10001, tree.blackWithLeftRight(var10003.scala$collection$immutable$RedBlackTree$Tree$$_right, tr));
            }
         }

         scala.sys.package$ var7 = scala.sys.package$.MODULE$;
         String error_message = "Defect: invariance violation";
         throw new RuntimeException(error_message);
      }
   }

   private RedBlackTree.Tree append(final RedBlackTree.Tree tl, final RedBlackTree.Tree tr) {
      if (tl == null) {
         return tr;
      } else if (tr == null) {
         return tl;
      } else if (tl.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
         if (tr.scala$collection$immutable$RedBlackTree$Tree$$_count < 0) {
            return tl.withRight(this.append(tl.scala$collection$immutable$RedBlackTree$Tree$$_right, tr));
         } else {
            RedBlackTree.Tree bc = this.append(tl.scala$collection$immutable$RedBlackTree$Tree$$_right, tr.scala$collection$immutable$RedBlackTree$Tree$$_left);
            if (bc != null && bc.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
               if (bc == null) {
                  throw null;
               } else {
                  return bc.withLeftRight(tl.withRight(bc.scala$collection$immutable$RedBlackTree$Tree$$_left), tr.withLeft(bc.scala$collection$immutable$RedBlackTree$Tree$$_right));
               }
            } else {
               return tl.withRight(tr.withLeft(bc));
            }
         }
      } else if (tr.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
         return tr.withLeft(this.append(tl, tr.scala$collection$immutable$RedBlackTree$Tree$$_left));
      } else {
         RedBlackTree.Tree bc = this.append(tl.scala$collection$immutable$RedBlackTree$Tree$$_right, tr.scala$collection$immutable$RedBlackTree$Tree$$_left);
         if (bc != null && bc.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            if (bc == null) {
               throw null;
            } else {
               return bc.withLeftRight(tl.withRight(bc.scala$collection$immutable$RedBlackTree$Tree$$_left), tr.withLeft(bc.scala$collection$immutable$RedBlackTree$Tree$$_right));
            }
         } else {
            return this.balLeft(tl, tl.scala$collection$immutable$RedBlackTree$Tree$$_left, tr.withLeft(bc));
         }
      }
   }

   public RedBlackTree.Tree union(final RedBlackTree.Tree t1, final RedBlackTree.Tree t2, final Ordering ordering) {
      return this.blacken(this._union(t1, t2, ordering));
   }

   public RedBlackTree.Tree intersect(final RedBlackTree.Tree t1, final RedBlackTree.Tree t2, final Ordering ordering) {
      return this.blacken(this._intersect(t1, t2, ordering));
   }

   public RedBlackTree.Tree difference(final RedBlackTree.Tree t1, final RedBlackTree.Tree t2, final Ordering ordering) {
      return this.blacken(this._difference(t1, t2, ordering));
   }

   private int rank(final RedBlackTree.Tree t, final int bh) {
      if (t == null) {
         return 0;
      } else {
         return t.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? 2 * (bh - 1) : 2 * bh - 1;
      }
   }

   private RedBlackTree.Tree joinRight(final RedBlackTree.Tree tl, final Object k, final Object v, final RedBlackTree.Tree tr, final int bhtl, final int rtr) {
      if ((tl == null ? 0 : (tl.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? 2 * (bhtl - 1) : 2 * bhtl - 1)) == rtr / 2 * 2) {
         return this.RedTree(k, v, tl, tr);
      } else {
         boolean tlBlack = tl != null && tl.scala$collection$immutable$RedBlackTree$Tree$$_count < 0;
         int bhtlr = tlBlack ? bhtl - 1 : bhtl;
         if (tl == null) {
            throw null;
         } else {
            RedBlackTree.Tree ttr = this.joinRight(tl.scala$collection$immutable$RedBlackTree$Tree$$_right, k, v, tr, bhtlr, rtr);
            if (tlBlack && ttr != null && ttr.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
               if (ttr == null) {
                  throw null;
               }

               RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = ttr.scala$collection$immutable$RedBlackTree$Tree$$_right;
               boolean var10000 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
               Object var11 = null;
               if (var10000) {
                  return this.RedTree(ttr.scala$collection$immutable$RedBlackTree$Tree$$_key, ttr.scala$collection$immutable$RedBlackTree$Tree$$_value, this.BlackTree(tl.scala$collection$immutable$RedBlackTree$Tree$$_key, tl.scala$collection$immutable$RedBlackTree$Tree$$_value, tl.scala$collection$immutable$RedBlackTree$Tree$$_left, ttr.scala$collection$immutable$RedBlackTree$Tree$$_left), ttr.scala$collection$immutable$RedBlackTree$Tree$$_right.black());
               }
            }

            return this.mkTree(tlBlack, tl.scala$collection$immutable$RedBlackTree$Tree$$_key, tl.scala$collection$immutable$RedBlackTree$Tree$$_value, tl.scala$collection$immutable$RedBlackTree$Tree$$_left, ttr);
         }
      }
   }

   private RedBlackTree.Tree joinLeft(final RedBlackTree.Tree tl, final Object k, final Object v, final RedBlackTree.Tree tr, final int rtl, final int bhtr) {
      if ((tr == null ? 0 : (tr.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? 2 * (bhtr - 1) : 2 * bhtr - 1)) == rtl / 2 * 2) {
         return this.RedTree(k, v, tl, tr);
      } else {
         boolean trBlack = tr != null && tr.scala$collection$immutable$RedBlackTree$Tree$$_count < 0;
         int bhtrl = trBlack ? bhtr - 1 : bhtr;
         if (tr == null) {
            throw null;
         } else {
            RedBlackTree.Tree ttl = this.joinLeft(tl, k, v, tr.scala$collection$immutable$RedBlackTree$Tree$$_left, rtl, bhtrl);
            if (trBlack && ttl != null && ttl.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
               if (ttl == null) {
                  throw null;
               }

               RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = ttl.scala$collection$immutable$RedBlackTree$Tree$$_left;
               boolean var10000 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
               Object var11 = null;
               if (var10000) {
                  return this.RedTree(ttl.scala$collection$immutable$RedBlackTree$Tree$$_key, ttl.scala$collection$immutable$RedBlackTree$Tree$$_value, ttl.scala$collection$immutable$RedBlackTree$Tree$$_left.black(), this.BlackTree(tr.scala$collection$immutable$RedBlackTree$Tree$$_key, tr.scala$collection$immutable$RedBlackTree$Tree$$_value, ttl.scala$collection$immutable$RedBlackTree$Tree$$_right, tr.scala$collection$immutable$RedBlackTree$Tree$$_right));
               }
            }

            return this.mkTree(trBlack, tr.scala$collection$immutable$RedBlackTree$Tree$$_key, tr.scala$collection$immutable$RedBlackTree$Tree$$_value, ttl, tr.scala$collection$immutable$RedBlackTree$Tree$$_right);
         }
      }
   }

   public RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$join(final RedBlackTree.Tree tl, final Object k, final Object v, final RedBlackTree.Tree tr) {
      int bhtl = this.h$1(tl, 0);
      int bhtr = this.h$1(tr, 0);
      if (bhtl > bhtr) {
         RedBlackTree.Tree tt = this.joinRight(tl, k, v, tr, bhtl, tr == null ? 0 : (tr.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? 2 * (bhtr - 1) : 2 * bhtr - 1));
         if (tt != null && tt.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            if (tt == null) {
               throw null;
            }

            RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = tt.scala$collection$immutable$RedBlackTree$Tree$$_right;
            boolean var13 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
            Object var11 = null;
            if (var13) {
               return tt.black();
            }
         }

         return tt;
      } else if (bhtr > bhtl) {
         RedBlackTree.Tree tt = this.joinLeft(tl, k, v, tr, tl == null ? 0 : (tl.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? 2 * (bhtl - 1) : 2 * bhtl - 1), bhtr);
         if (tt != null && tt.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            if (tt == null) {
               throw null;
            }

            RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$isRedTree_tree = tt.scala$collection$immutable$RedBlackTree$Tree$$_left;
            boolean var10000 = scala$collection$immutable$RedBlackTree$$isRedTree_tree != null && scala$collection$immutable$RedBlackTree$$isRedTree_tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0;
            Object var12 = null;
            if (var10000) {
               return tt.black();
            }
         }

         return tt;
      } else {
         return this.mkTree(tl != null && tl.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0 || tr != null && tr.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0, k, v, tl, tr);
      }
   }

   private Tuple4 split(final RedBlackTree.Tree t, final Object k2, final Ordering ordering) {
      if (t == null) {
         return new Tuple4((Object)null, (Object)null, (Object)null, k2);
      } else {
         int cmp = ordering.compare(k2, t.scala$collection$immutable$RedBlackTree$Tree$$_key);
         if (cmp == 0) {
            return new Tuple4(t.scala$collection$immutable$RedBlackTree$Tree$$_left, t, t.scala$collection$immutable$RedBlackTree$Tree$$_right, t.scala$collection$immutable$RedBlackTree$Tree$$_key);
         } else if (cmp < 0) {
            Tuple4 var5 = this.split(t.scala$collection$immutable$RedBlackTree$Tree$$_left, k2, ordering);
            if (var5 != null) {
               RedBlackTree.Tree ll = (RedBlackTree.Tree)var5._1();
               RedBlackTree.Tree b = (RedBlackTree.Tree)var5._2();
               RedBlackTree.Tree lr = (RedBlackTree.Tree)var5._3();
               Object k1 = var5._4();
               return new Tuple4(ll, b, this.scala$collection$immutable$RedBlackTree$$join(lr, t.scala$collection$immutable$RedBlackTree$Tree$$_key, t.scala$collection$immutable$RedBlackTree$Tree$$_value, t.scala$collection$immutable$RedBlackTree$Tree$$_right), k1);
            } else {
               throw new MatchError((Object)null);
            }
         } else {
            Tuple4 var10 = this.split(t.scala$collection$immutable$RedBlackTree$Tree$$_right, k2, ordering);
            if (var10 != null) {
               RedBlackTree.Tree rl = (RedBlackTree.Tree)var10._1();
               RedBlackTree.Tree b = (RedBlackTree.Tree)var10._2();
               RedBlackTree.Tree rr = (RedBlackTree.Tree)var10._3();
               Object k1 = var10._4();
               return new Tuple4(this.scala$collection$immutable$RedBlackTree$$join(t.scala$collection$immutable$RedBlackTree$Tree$$_left, t.scala$collection$immutable$RedBlackTree$Tree$$_key, t.scala$collection$immutable$RedBlackTree$Tree$$_value, rl), b, rr, k1);
            } else {
               throw new MatchError((Object)null);
            }
         }
      }
   }

   private Tuple3 splitLast(final RedBlackTree.Tree t) {
      if (t == null) {
         throw null;
      } else if (t.scala$collection$immutable$RedBlackTree$Tree$$_right == null) {
         return new Tuple3(t.scala$collection$immutable$RedBlackTree$Tree$$_left, t.scala$collection$immutable$RedBlackTree$Tree$$_key, t.scala$collection$immutable$RedBlackTree$Tree$$_value);
      } else {
         Tuple3 var2 = this.splitLast(t.scala$collection$immutable$RedBlackTree$Tree$$_right);
         if (var2 != null) {
            RedBlackTree.Tree tt = (RedBlackTree.Tree)var2._1();
            Object kk = var2._2();
            Object vv = var2._3();
            return new Tuple3(this.scala$collection$immutable$RedBlackTree$$join(t.scala$collection$immutable$RedBlackTree$Tree$$_left, t.scala$collection$immutable$RedBlackTree$Tree$$_key, t.scala$collection$immutable$RedBlackTree$Tree$$_value, tt), kk, vv);
         } else {
            throw new MatchError((Object)null);
         }
      }
   }

   public RedBlackTree.Tree scala$collection$immutable$RedBlackTree$$join2(final RedBlackTree.Tree tl, final RedBlackTree.Tree tr) {
      if (tl == null) {
         return tr;
      } else if (tr == null) {
         return tl;
      } else {
         Tuple3 var3 = this.splitLast(tl);
         if (var3 != null) {
            RedBlackTree.Tree ttl = (RedBlackTree.Tree)var3._1();
            Object k = var3._2();
            Object v = var3._3();
            return this.scala$collection$immutable$RedBlackTree$$join(ttl, k, v, tr);
         } else {
            throw new MatchError((Object)null);
         }
      }
   }

   private RedBlackTree.Tree _union(final RedBlackTree.Tree t1, final RedBlackTree.Tree t2, final Ordering ordering) {
      if (t1 != null && t1 != t2) {
         if (t2 == null) {
            return t1;
         } else {
            Tuple4 var4 = this.split(t1, t2.scala$collection$immutable$RedBlackTree$Tree$$_key, ordering);
            if (var4 != null) {
               RedBlackTree.Tree l1 = (RedBlackTree.Tree)var4._1();
               RedBlackTree.Tree r1 = (RedBlackTree.Tree)var4._3();
               Object k1 = var4._4();
               RedBlackTree.Tree tl = this._union(l1, t2.scala$collection$immutable$RedBlackTree$Tree$$_left, ordering);
               RedBlackTree.Tree tr = this._union(r1, t2.scala$collection$immutable$RedBlackTree$Tree$$_right, ordering);
               return this.scala$collection$immutable$RedBlackTree$$join(tl, k1, t2.scala$collection$immutable$RedBlackTree$Tree$$_value, tr);
            } else {
               throw new MatchError((Object)null);
            }
         }
      } else {
         return t2;
      }
   }

   private RedBlackTree.Tree _intersect(final RedBlackTree.Tree t1, final RedBlackTree.Tree t2, final Ordering ordering) {
      if (t1 != null && t2 != null) {
         if (t1 == t2) {
            return t1;
         } else {
            Tuple4 var4 = this.split(t1, t2.scala$collection$immutable$RedBlackTree$Tree$$_key, ordering);
            if (var4 != null) {
               RedBlackTree.Tree l1 = (RedBlackTree.Tree)var4._1();
               RedBlackTree.Tree b = (RedBlackTree.Tree)var4._2();
               RedBlackTree.Tree r1 = (RedBlackTree.Tree)var4._3();
               Object k1 = var4._4();
               RedBlackTree.Tree tl = this._intersect(l1, t2.scala$collection$immutable$RedBlackTree$Tree$$_left, ordering);
               RedBlackTree.Tree tr = this._intersect(r1, t2.scala$collection$immutable$RedBlackTree$Tree$$_right, ordering);
               return b != null ? this.scala$collection$immutable$RedBlackTree$$join(tl, k1, t2.scala$collection$immutable$RedBlackTree$Tree$$_value, tr) : this.scala$collection$immutable$RedBlackTree$$join2(tl, tr);
            } else {
               throw new MatchError((Object)null);
            }
         }
      } else {
         return null;
      }
   }

   private RedBlackTree.Tree _difference(final RedBlackTree.Tree t1, final RedBlackTree.Tree t2, final Ordering ordering) {
      if (t1 != null && t2 != null) {
         if (t1 == t2) {
            return null;
         } else {
            Tuple4 var4 = this.split(t1, t2.scala$collection$immutable$RedBlackTree$Tree$$_key, ordering);
            if (var4 != null) {
               RedBlackTree.Tree l1 = (RedBlackTree.Tree)var4._1();
               RedBlackTree.Tree r1 = (RedBlackTree.Tree)var4._3();
               RedBlackTree.Tree tl = this._difference(l1, t2.scala$collection$immutable$RedBlackTree$Tree$$_left, ordering);
               RedBlackTree.Tree tr = this._difference(r1, t2.scala$collection$immutable$RedBlackTree$Tree$$_right, ordering);
               return this.scala$collection$immutable$RedBlackTree$$join2(tl, tr);
            } else {
               throw new MatchError((Object)null);
            }
         }
      } else {
         return t1;
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$validate$1(final RedBlackTree.Tree tree$1) {
      return (new StringBuilder(18)).append("key check failed: ").append(tree$1).toString();
   }

   // $FF: synthetic method
   public static final String $anonfun$validate$2(final RedBlackTree.Tree tree$1) {
      return (new StringBuilder(13)).append("red-red left ").append(tree$1).toString();
   }

   // $FF: synthetic method
   public static final String $anonfun$validate$3(final RedBlackTree.Tree tree$1) {
      return (new StringBuilder(14)).append("red-red right ").append(tree$1).toString();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$validate$4(final Function1 keyProp$1, final Ordering ordering$1, final RedBlackTree.Tree tree$1, final Object k) {
      if (BoxesRunTime.unboxToBoolean(keyProp$1.apply(k))) {
         if (tree$1 == null) {
            throw null;
         }

         if (ordering$1.compare(k, tree$1.scala$collection$immutable$RedBlackTree$Tree$$_key) < 0) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$validate$5(final Function1 keyProp$1, final Ordering ordering$1, final RedBlackTree.Tree tree$1, final Object k) {
      if (BoxesRunTime.unboxToBoolean(keyProp$1.apply(k))) {
         if (tree$1 == null) {
            throw null;
         }

         if (ordering$1.compare(k, tree$1.scala$collection$immutable$RedBlackTree$Tree$$_key) > 0) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final String $anonfun$validate$6(final RedBlackTree.Tree tree$1) {
      return (new StringBuilder(14)).append("not balanced: ").append(tree$1).toString();
   }

   private static final int impl$1(final RedBlackTree.Tree tree, final Function1 keyProp, final Ordering ordering$1) {
      if (!BoxesRunTime.unboxToBoolean(keyProp.apply(tree.scala$collection$immutable$RedBlackTree$Tree$$_key))) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$validate$1(tree)).toString());
      } else {
         if (tree.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
            boolean var5;
            label88: {
               if (tree.scala$collection$immutable$RedBlackTree$Tree$$_left != null) {
                  RedBlackTree.Tree var10000 = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
                  if (var10000 == null) {
                     throw null;
                  }

                  if (var10000.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
                     var5 = false;
                     break label88;
                  }
               }

               var5 = true;
            }

            if (!var5) {
               throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$validate$2(tree)).toString());
            }

            label81: {
               if (tree.scala$collection$immutable$RedBlackTree$Tree$$_right != null) {
                  RedBlackTree.Tree var6 = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
                  if (var6 == null) {
                     throw null;
                  }

                  if (var6.scala$collection$immutable$RedBlackTree$Tree$$_count >= 0) {
                     var5 = false;
                     break label81;
                  }
               }

               var5 = true;
            }

            if (!var5) {
               throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$validate$3(tree)).toString());
            }
         }

         int leftBlacks = tree.scala$collection$immutable$RedBlackTree$Tree$$_left == null ? 0 : impl$1(tree.scala$collection$immutable$RedBlackTree$Tree$$_left, (k) -> BoxesRunTime.boxToBoolean($anonfun$validate$4(keyProp, ordering$1, tree, k)), ordering$1);
         int rightBlacks = tree.scala$collection$immutable$RedBlackTree$Tree$$_right == null ? 0 : impl$1(tree.scala$collection$immutable$RedBlackTree$Tree$$_right, (k) -> BoxesRunTime.boxToBoolean($anonfun$validate$5(keyProp, ordering$1, tree, k)), ordering$1);
         if (leftBlacks != rightBlacks) {
            throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$validate$6(tree)).toString());
         } else {
            return leftBlacks + (tree.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? 1 : 0);
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$validate$7(final Object x$1) {
      return true;
   }

   private final RedBlackTree.Tree _tail$1(final RedBlackTree.Tree tree) {
      if (tree == null) {
         throw new NoSuchElementException("empty tree");
      } else {
         RedBlackTree.Tree tl = tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
         if (tl == null) {
            return tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
         } else {
            return tl.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? this.balLeft(tree, this._tail$1(tl), tree.scala$collection$immutable$RedBlackTree$Tree$$_right) : tree.redWithLeft(this._tail$1(tree.scala$collection$immutable$RedBlackTree$Tree$$_left));
         }
      }
   }

   private final RedBlackTree.Tree _init$1(final RedBlackTree.Tree tree) {
      if (tree == null) {
         throw new NoSuchElementException("empty tree");
      } else {
         RedBlackTree.Tree tr = tree.scala$collection$immutable$RedBlackTree$Tree$$_right;
         if (tr == null) {
            return tree.scala$collection$immutable$RedBlackTree$Tree$$_left;
         } else {
            return tr.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? this.balRight(tree, tree.scala$collection$immutable$RedBlackTree$Tree$$_left, this._init$1(tr)) : tree.redWithRight(this._init$1(tr));
         }
      }
   }

   private final RedBlackTree.Tree f$1(final int level, final int size, final int maxUsedDepth$1, final Iterator xs$1) {
      switch (size) {
         case 0:
            return null;
         case 1:
            return this.mkTree(level != maxUsedDepth$1 || level == 1, xs$1.next(), (Object)null, (RedBlackTree.Tree)null, (RedBlackTree.Tree)null);
         default:
            int leftSize = (size - 1) / 2;
            RedBlackTree.Tree left = this.f$1(level + 1, leftSize, maxUsedDepth$1, xs$1);
            Object x = xs$1.next();
            RedBlackTree.Tree right = this.f$1(level + 1, size - 1 - leftSize, maxUsedDepth$1, xs$1);
            return this.BlackTree(x, (Object)null, left, right);
      }
   }

   private final RedBlackTree.Tree f$2(final int level, final int size, final Iterator xs$2, final int maxUsedDepth$2) {
      switch (size) {
         case 0:
            return null;
         case 1:
            Tuple2 var5 = (Tuple2)xs$2.next();
            if (var5 == null) {
               throw new MatchError((Object)null);
            }

            Object k = var5._1();
            Object v = var5._2();
            return this.mkTree(level != maxUsedDepth$2 || level == 1, k, v, (RedBlackTree.Tree)null, (RedBlackTree.Tree)null);
         default:
            int leftSize = (size - 1) / 2;
            RedBlackTree.Tree left = this.f$2(level + 1, leftSize, xs$2, maxUsedDepth$2);
            Tuple2 var10 = (Tuple2)xs$2.next();
            if (var10 != null) {
               Object k = var10._1();
               Object v = var10._2();
               RedBlackTree.Tree right = this.f$2(level + 1, size - 1 - leftSize, xs$2, maxUsedDepth$2);
               return this.BlackTree(k, v, left, right);
            } else {
               throw new MatchError((Object)null);
            }
      }
   }

   private final RedBlackTree.Tree fk$1(final RedBlackTree.Tree t, final Function2 f$3) {
      if (t == null) {
         throw null;
      } else {
         Object k = t.scala$collection$immutable$RedBlackTree$Tree$$_key;
         Object v = t.scala$collection$immutable$RedBlackTree$Tree$$_value;
         RedBlackTree.Tree l = t.scala$collection$immutable$RedBlackTree$Tree$$_left;
         RedBlackTree.Tree r = t.scala$collection$immutable$RedBlackTree$Tree$$_right;
         RedBlackTree.Tree l2 = l == null ? null : this.fk$1(l, f$3);
         boolean keep = BoxesRunTime.unboxToBoolean(f$3.apply(k, v));
         RedBlackTree.Tree r2 = r == null ? null : this.fk$1(r, f$3);
         if (!keep) {
            return this.scala$collection$immutable$RedBlackTree$$join2(l2, r2);
         } else {
            return l2 == l && r2 == r ? t : this.scala$collection$immutable$RedBlackTree$$join(l2, k, v, r2);
         }
      }
   }

   // $FF: synthetic method
   private static final partitioner$1$ partitioner$lzycompute$1(final LazyRef partitioner$module$1, final Function2 p$1) {
      synchronized(partitioner$module$1){}

      partitioner$1$ var2;
      try {
         var2 = partitioner$module$1.initialized() ? (partitioner$1$)partitioner$module$1.value() : (partitioner$1$)partitioner$module$1.initialize(new partitioner$1$(p$1));
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final partitioner$1$ partitioner$2(final LazyRef partitioner$module$1, final Function2 p$1) {
      class partitioner$1$ {
         private RedBlackTree.Tree tmpk;
         private RedBlackTree.Tree tmpd;
         private final Function2 p$1;

         public RedBlackTree.Tree tmpk() {
            return this.tmpk;
         }

         public void tmpk_$eq(final RedBlackTree.Tree x$1) {
            this.tmpk = x$1;
         }

         public RedBlackTree.Tree tmpd() {
            return this.tmpd;
         }

         public void tmpd_$eq(final RedBlackTree.Tree x$1) {
            this.tmpd = x$1;
         }

         public void fk(final RedBlackTree.Tree t) {
            if (t == null) {
               throw null;
            } else {
               Object k = t.scala$collection$immutable$RedBlackTree$Tree$$_key;
               Object v = t.scala$collection$immutable$RedBlackTree$Tree$$_value;
               RedBlackTree.Tree l = t.scala$collection$immutable$RedBlackTree$Tree$$_left;
               RedBlackTree.Tree r = t.scala$collection$immutable$RedBlackTree$Tree$$_right;
               RedBlackTree.Tree l2k = null;
               RedBlackTree.Tree l2d = null;
               RedBlackTree.Tree r2k = null;
               RedBlackTree.Tree r2d = null;
               if (l != null) {
                  this.fk(l);
                  l2k = this.tmpk();
                  l2d = this.tmpd();
               }

               boolean keep = BoxesRunTime.unboxToBoolean(this.p$1.apply(k, v));
               if (r != null) {
                  this.fk(r);
                  r2k = this.tmpk();
                  r2d = this.tmpd();
               }

               RedBlackTree.Tree jk = !keep ? RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$join2(l2k, r2k) : (l2k == l && r2k == r ? t : RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$join(l2k, k, v, r2k));
               RedBlackTree.Tree jd = keep ? RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$join2(l2d, r2d) : (l2d == l && r2d == r ? t : RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$join(l2d, k, v, r2d));
               this.tmpk_$eq(jk);
               this.tmpd_$eq(jd);
            }
         }

         public partitioner$1$(final Function2 p$1) {
            this.p$1 = p$1;
            this.tmpk = null;
            this.tmpd = null;
         }
      }

      if (partitioner$module$1.initialized()) {
         return (partitioner$1$)partitioner$module$1.value();
      } else {
         synchronized(partitioner$module$1){}

         partitioner$1$ var3;
         try {
            var3 = partitioner$module$1.initialized() ? (partitioner$1$)partitioner$module$1.value() : (partitioner$1$)partitioner$module$1.initialize(new partitioner$1$(p$1));
         } catch (Throwable var5) {
            throw var5;
         }

         return var3;
      }
   }

   private final int h$1(final RedBlackTree.Tree t, final int i) {
      while(t != null) {
         i = t.scala$collection$immutable$RedBlackTree$Tree$$_count < 0 ? i + 1 : i;
         t = t.scala$collection$immutable$RedBlackTree$Tree$$_left;
      }

      return i + 1;
   }

   private RedBlackTree$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
