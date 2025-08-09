package scala.collection;

import scala.Tuple4;

public final class LazyZip4$ {
   public static final LazyZip4$ MODULE$ = new LazyZip4$();

   public View lazyZip4ToIterable(final LazyZip4 zipped4) {
      if (zipped4 == null) {
         throw null;
      } else {
         return new AbstractView() {
            // $FF: synthetic field
            private final LazyZip4 $outer;

            public AbstractIterator iterator() {
               return new AbstractIterator() {
                  private final Iterator elems1;
                  private final Iterator elems2;
                  private final Iterator elems3;
                  private final Iterator elems4;

                  public boolean hasNext() {
                     return this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext() && this.elems4.hasNext();
                  }

                  public Tuple4 next() {
                     return new Tuple4(this.elems1.next(), this.elems2.next(), this.elems3.next(), this.elems4.next());
                  }

                  public {
                     this.elems1 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll3.iterator();
                     this.elems4 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll4.iterator();
                  }
               };
            }

            public int knownSize() {
               return this.$outer.scala$collection$LazyZip4$$zipKnownSize();
            }

            public boolean isEmpty() {
               return this.$outer.scala$collection$LazyZip4$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip4$$coll2.isEmpty() || this.$outer.scala$collection$LazyZip4$$coll3.isEmpty() || this.$outer.scala$collection$LazyZip4$$coll4.isEmpty();
            }

            // $FF: synthetic method
            public LazyZip4 scala$collection$LazyZip4$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (LazyZip4$.this == null) {
                  throw null;
               } else {
                  this.$outer = LazyZip4$.this;
               }
            }
         };
      }
   }

   private LazyZip4$() {
   }
}
