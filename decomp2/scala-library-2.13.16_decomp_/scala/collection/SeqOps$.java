package scala.collection;

import scala.runtime.BoxesRunTime;

public final class SeqOps$ {
   public static final SeqOps$ MODULE$ = new SeqOps$();

   public int scala$collection$SeqOps$$kmpSearch(final Seq S, final int m0, final int m1, final Seq W, final int n0, final int n1, final boolean forward) {
      if (n1 == n0 + 1) {
         return forward ? clipR$1(S.indexOf(W.apply(n0), m0), m1) : clipL$1(S.lastIndexOf(W.apply(n0), m1 - 1), m0 - 1);
      } else if (m1 - m0 == n1 - n0) {
         return S.iterator().slice(m0, m1).sameElements(W.iterator().slice(n0, n1)) ? m0 : -1;
      } else if (S instanceof IndexedSeq) {
         IndexedSeqView Wopt = this.kmpOptimizeWord(W, n0, n1, forward);
         int[] T = this.kmpJumpTable(Wopt, n1 - n0);
         int i = 0;
         int m = 0;
         int zero = forward ? m0 : m1 - 1;
         int delta = forward ? 1 : -1;

         while(i + m < m1 - m0) {
            if (BoxesRunTime.equals(Wopt.apply(i), S.apply(zero + delta * (i + m)))) {
               ++i;
               if (i == n1 - n0) {
                  if (forward) {
                     return m + m0;
                  }

                  return m1 - m - i;
               }
            } else {
               int ti = T[i];
               m += i - ti;
               if (i > 0) {
                  i = ti;
               }
            }
         }

         return -1;
      } else {
         Iterator iter = S.iterator().drop(m0);
         IndexedSeqView Wopt = this.kmpOptimizeWord(W, n0, n1, true);
         int[] T = this.kmpJumpTable(Wopt, n1 - n0);
         Object[] cache = new Object[n1 - n0];
         int largest = 0;
         int i = 0;
         int m = 0;
         int answer = -1;

         while(m + m0 + n1 - n0 <= m1) {
            while(i + m >= largest) {
               cache[largest % (n1 - n0)] = iter.next();
               ++largest;
            }

            if (BoxesRunTime.equals(Wopt.apply(i), cache[(i + m) % (n1 - n0)])) {
               ++i;
               if (i == n1 - n0) {
                  if (forward) {
                     return m + m0;
                  }

                  --i;
                  answer = m + m0;
                  int ti = T[i];
                  m += i - ti;
                  if (i > 0) {
                     i = ti;
                  }
               }
            } else {
               int ti = T[i];
               m += i - ti;
               if (i > 0) {
                  i = ti;
               }
            }
         }

         return answer;
      }
   }

   private IndexedSeqView kmpOptimizeWord(final Seq W, final int n0, final int n1, final boolean forward) {
      if (W instanceof IndexedSeq) {
         IndexedSeq var5 = (IndexedSeq)W;
         if (forward && n0 == 0 && n1 == W.length()) {
            return var5.view();
         } else {
            return forward ? new AbstractIndexedSeqView(n1, n0, var5) {
               private final int length;
               private final int n0$1;
               private final IndexedSeq x2$1;

               public int length() {
                  return this.length;
               }

               public Object apply(final int x) {
                  return this.x2$1.apply(this.n0$1 + x);
               }

               public {
                  this.n0$1 = n0$1;
                  this.x2$1 = x2$1;
                  this.length = n1$1 - n0$1;
               }
            } : new AbstractIndexedSeqView(n1, n0, var5) {
               private final int n1$1;
               private final int n0$1;
               private final IndexedSeq x2$1;

               public int length() {
                  return this.n1$1 - this.n0$1;
               }

               public Object apply(final int x) {
                  return this.x2$1.apply(this.n1$1 - 1 - x);
               }

               public {
                  this.n1$1 = n1$1;
                  this.n0$1 = n0$1;
                  this.x2$1 = x2$1;
               }
            };
         }
      } else {
         return new AbstractIndexedSeqView(n1, n0, forward, W) {
            private final Object[] Warr;
            private final int delta;
            private final int done;
            private final Iterator wit;
            private int i;
            private final int length;

            private Iterator wit() {
               return this.wit;
            }

            private int i() {
               return this.i;
            }

            private void i_$eq(final int x$1) {
               this.i = x$1;
            }

            public int length() {
               return this.length;
            }

            public Object apply(final int x) {
               return this.Warr[x];
            }

            public {
               this.Warr = new Object[n1$1 - n0$1];
               this.delta = forward$1 ? 1 : -1;
               this.done = forward$1 ? n1$1 - n0$1 : -1;
               this.wit = W$1.iterator().drop(n0$1);
               this.i = forward$1 ? 0 : n1$1 - n0$1 - 1;

               while(this.i() != this.done) {
                  this.Warr[this.i()] = this.wit().next();
                  this.i_$eq(this.i() + this.delta);
               }

               this.length = n1$1 - n0$1;
            }
         };
      }
   }

   private int[] kmpJumpTable(final IndexedSeqView Wopt, final int wlen) {
      int[] arr = new int[wlen];
      int pos = 2;
      int cnd = 0;
      arr[0] = -1;
      arr[1] = 0;

      while(pos < wlen) {
         if (BoxesRunTime.equals(Wopt.apply(pos - 1), Wopt.apply(cnd))) {
            arr[pos] = cnd + 1;
            ++pos;
            ++cnd;
         } else if (cnd > 0) {
            cnd = arr[cnd];
         } else {
            arr[pos] = 0;
            ++pos;
         }
      }

      return arr;
   }

   private static final int clipR$1(final int x, final int y) {
      return x < y ? x : -1;
   }

   private static final int clipL$1(final int x, final int y) {
      return x > y ? x : -1;
   }

   private SeqOps$() {
   }
}
