package scala.collection;

import scala.Function4;
import scala.Tuple4;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ud\u0001\u0002\u000b\u0016\u0005iA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tY\u0001\u0011\t\u0011)A\u0005[!AA\u0007\u0001B\u0001B\u0003%Q\u0007\u0003\u0005:\u0001\t\u0005\t\u0015!\u0003;\u0011!q\u0004A!A!\u0002\u0013y\u0004BB\"\u0001\t\u0003)B\tC\u0003L\u0001\u0011\u0005A\nC\u0003`\u0001\u0011\u0005\u0001\rC\u0003m\u0001\u0011\u0005Q\u000eC\u0003~\u0001\u0011\u0005a\u0010C\u0004\u0002\u0002\u0001!\t!a\u0001\t\u000f\u0005\u001d\u0001\u0001\"\u0001\u0002\n!9\u0011Q\u0004\u0001\u0005\n\u0005}\u0001bBA\u0014\u0001\u0011%\u0011\u0011\u0006\u0005\b\u0003c\u0001A\u0011IA\u001a\u000f\u001d\t)%\u0006E\u0001\u0003\u000f2a\u0001F\u000b\t\u0002\u0005%\u0003BB\"\u0012\t\u0003\tY\u0005C\u0004\u0002NE!\u0019!a\u0014\u0003\u00111\u000b'0\u001f.jaRR!AF\f\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\u0019\u0003\u0015\u00198-\u00197b\u0007\u0001)ba\u0007\u001a8y\u0005\u001b3C\u0001\u0001\u001d!\tib$D\u0001\u0018\u0013\tyrC\u0001\u0004B]f\u0014VMZ\u0001\u0004gJ\u001c\u0007C\u0001\u0012$\u0019\u0001!Q\u0001\n\u0001C\u0002\u0015\u0012!aQ\u0019\u0012\u0005\u0019J\u0003CA\u000f(\u0013\tAsCA\u0004O_RD\u0017N\\4\u0011\u0005uQ\u0013BA\u0016\u0018\u0005\r\te._\u0001\u0006G>dG.\r\t\u0004]=\nT\"A\u000b\n\u0005A*\"\u0001C%uKJ\f'\r\\3\u0011\u0005\t\u0012DAB\u001a\u0001\t\u000b\u0007QEA\u0002FYF\nQaY8mYJ\u00022AL\u00187!\t\u0011s\u0007\u0002\u00049\u0001\u0011\u0015\r!\n\u0002\u0004\u000b2\u0014\u0014!B2pY2\u001c\u0004c\u0001\u00180wA\u0011!\u0005\u0010\u0003\u0007{\u0001!)\u0019A\u0013\u0003\u0007\u0015c7'A\u0003d_2dG\u0007E\u0002/_\u0001\u0003\"AI!\u0005\r\t\u0003AQ1\u0001&\u0005\r)E\u000eN\u0001\u0007y%t\u0017\u000e\u001e \u0015\r\u00153u\tS%K!\u001dq\u0003!\r\u001c<\u0001\u0006BQ\u0001\t\u0004A\u0002\u0005BQ\u0001\f\u0004A\u00025BQ\u0001\u000e\u0004A\u0002UBQ!\u000f\u0004A\u0002iBQA\u0010\u0004A\u0002}\n1!\\1q+\ri\u0005\f\u0015\u000b\u0003\u001dj#\"a\u0014*\u0011\u0005\t\u0002F!B)\b\u0005\u0004)#!A\"\t\u000bM;\u00019\u0001+\u0002\u0005\t4\u0007#\u0002\u0018VC]{\u0015B\u0001,\u0016\u0005%\u0011U/\u001b7e\rJ|W\u000e\u0005\u0002#1\u0012)\u0011l\u0002b\u0001K\t\t!\tC\u0003\\\u000f\u0001\u0007A,A\u0001g!\u001diR,\r\u001c<\u0001^K!AX\f\u0003\u0013\u0019+hn\u0019;j_:$\u0014a\u00024mCRl\u0015\r]\u000b\u0004C\"$GC\u00012j)\t\u0019W\r\u0005\u0002#I\u0012)\u0011\u000b\u0003b\u0001K!)1\u000b\u0003a\u0002MB)a&V\u0011hGB\u0011!\u0005\u001b\u0003\u00063\"\u0011\r!\n\u0005\u00067\"\u0001\rA\u001b\t\b;u\u000bdg\u000f!l!\rqsfZ\u0001\u0007M&dG/\u001a:\u0016\u00059\fHCA8x)\t\u0001(\u000f\u0005\u0002#c\u0012)\u0011+\u0003b\u0001K!)1+\u0003a\u0002gB)a&V\u0011uaB1Q$^\u00197w\u0001K!A^\f\u0003\rQ+\b\u000f\\35\u0011\u0015A\u0018\u00021\u0001z\u0003\u0005\u0001\bcB\u000f^cYZ\u0004I\u001f\t\u0003;mL!\u0001`\f\u0003\u000f\t{w\u000e\\3b]\u00061Q\r_5tiN$\"A_@\t\u000baT\u0001\u0019A=\u0002\r\u0019|'/\u00197m)\rQ\u0018Q\u0001\u0005\u0006q.\u0001\r!_\u0001\bM>\u0014X-Y2i+\u0011\tY!!\u0007\u0015\t\u00055\u00111\u0003\t\u0004;\u0005=\u0011bAA\t/\t!QK\\5u\u0011\u0019YF\u00021\u0001\u0002\u0016AAQ$X\u00197w\u0001\u000b9\u0002E\u0002#\u00033!a!a\u0007\r\u0005\u0004)#!A+\u0002\u0015Q|\u0017\n^3sC\ndW-\u0006\u0002\u0002\"A!a&a\tu\u0013\r\t)#\u0006\u0002\u0005-&,w/\u0001\u0007{SB\\en\\<o'&TX-\u0006\u0002\u0002,A\u0019Q$!\f\n\u0007\u0005=rCA\u0002J]R\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003k\u0001B!a\u000e\u0002B5\u0011\u0011\u0011\b\u0006\u0005\u0003w\ti$\u0001\u0003mC:<'BAA \u0003\u0011Q\u0017M^1\n\t\u0005\r\u0013\u0011\b\u0002\u0007'R\u0014\u0018N\\4\u0002\u00111\u000b'0\u001f.jaR\u0002\"AL\t\u0014\u0005EaBCAA$\u0003Ia\u0017M_=[SB$Dk\\%uKJ\f'\r\\3\u0016\u0015\u0005E\u0013\u0011LA/\u0003C\n)\u0007\u0006\u0003\u0002T\u0005\u001d\u0004#\u0002\u0018\u0002$\u0005U\u0003CC\u000fv\u0003/\nY&a\u0018\u0002dA\u0019!%!\u0017\u0005\u000bM\u001a\"\u0019A\u0013\u0011\u0007\t\ni\u0006B\u00039'\t\u0007Q\u0005E\u0002#\u0003C\"Q!P\nC\u0002\u0015\u00022AIA3\t\u0015\u00115C1\u0001&\u0011\u001d\tIg\u0005a\u0001\u0003W\nqA_5qa\u0016$G\u0007\r\u0003\u0002n\u0005E\u0004\u0003\u0004\u0018\u0001\u0003/\nY&a\u0018\u0002d\u0005=\u0004c\u0001\u0012\u0002r\u0011Y\u00111OA4\u0003\u0003\u0005\tQ!\u0001&\u0005\ryFe\r"
)
public final class LazyZip4 {
   private final Object src;
   public final Iterable scala$collection$LazyZip4$$coll1;
   public final Iterable scala$collection$LazyZip4$$coll2;
   public final Iterable scala$collection$LazyZip4$$coll3;
   public final Iterable scala$collection$LazyZip4$$coll4;

   public static View lazyZip4ToIterable(final LazyZip4 zipped4) {
      LazyZip4$ var10000 = LazyZip4$.MODULE$;
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
               if (LazyZip4.this == null) {
                  throw null;
               } else {
                  this.$outer = LazyZip4.this;
               }
            }
         };
      }
   }

   public Object map(final Function4 f, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(f) {
         // $FF: synthetic field
         private final LazyZip4 $outer;
         public final Function4 f$5;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private final Iterator elems4;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public boolean hasNext() {
                  return this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext() && this.elems4.hasNext();
               }

               public Object next() {
                  return this.$outer.f$5.apply(this.elems1.next(), this.elems2.next(), this.elems3.next(), this.elems4.next());
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll3.iterator();
                     this.elems4 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll4.iterator();
                  }
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
            if (LazyZip4.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip4.this;
               this.f$5 = f$5;
            }
         }
      });
   }

   public Object flatMap(final Function4 f, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(f) {
         // $FF: synthetic field
         private final LazyZip4 $outer;
         public final Function4 f$6;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private final Iterator elems4;
               private Iterator _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Iterator current() {
                  while(!this._current.hasNext() && this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext() && this.elems4.hasNext()) {
                     this._current = ((IterableOnce)this.$outer.f$6.apply(this.elems1.next(), this.elems2.next(), this.elems3.next(), this.elems4.next())).iterator();
                  }

                  return this._current;
               }

               public boolean hasNext() {
                  return this.current().hasNext();
               }

               public Object next() {
                  return this.current().next();
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll3.iterator();
                     this.elems4 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll4.iterator();
                     Iterator$ var10001 = Iterator$.MODULE$;
                     this._current = Iterator$.scala$collection$Iterator$$_empty;
                  }
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip4$$coll1.knownSize() != 0 && this.$outer.scala$collection$LazyZip4$$coll2.knownSize() != 0 && this.$outer.scala$collection$LazyZip4$$coll3.knownSize() != 0 && this.$outer.scala$collection$LazyZip4$$coll4.knownSize() != 0 ? -1 : 0;
         }

         public boolean isEmpty() {
            return (new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private final Iterator elems4;
               private Iterator _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Iterator current() {
                  while(!this._current.hasNext() && this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext() && this.elems4.hasNext()) {
                     this._current = ((IterableOnce)this.$outer.f$6.apply(this.elems1.next(), this.elems2.next(), this.elems3.next(), this.elems4.next())).iterator();
                  }

                  return this._current;
               }

               public boolean hasNext() {
                  return this.current().hasNext();
               }

               public Object next() {
                  return this.current().next();
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll3.iterator();
                     this.elems4 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll4.iterator();
                     Iterator$ var10001 = Iterator$.MODULE$;
                     this._current = Iterator$.scala$collection$Iterator$$_empty;
                  }
               }
            }).isEmpty();
         }

         // $FF: synthetic method
         public LazyZip4 scala$collection$LazyZip4$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip4.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip4.this;
               this.f$6 = f$6;
            }
         }
      });
   }

   public Object filter(final Function4 p, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(p) {
         // $FF: synthetic field
         private final LazyZip4 $outer;
         public final Function4 p$5;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private final Iterator elems4;
               private Tuple4 _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Tuple4 current() {
                  while(this._current == null && this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext() && this.elems4.hasNext()) {
                     Object e1 = this.elems1.next();
                     Object e2 = this.elems2.next();
                     Object e3 = this.elems3.next();
                     Object e4 = this.elems4.next();
                     if (BoxesRunTime.unboxToBoolean(this.$outer.p$5.apply(e1, e2, e3, e4))) {
                        this._current = new Tuple4(e1, e2, e3, e4);
                     }
                  }

                  return this._current;
               }

               public boolean hasNext() {
                  return this.current() != null;
               }

               public Tuple4 next() {
                  Tuple4 c = this.current();
                  if (c != null) {
                     this._current = null;
                     return c;
                  } else {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return (Tuple4)Iterator$.scala$collection$Iterator$$_empty.next();
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll3.iterator();
                     this.elems4 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll4.iterator();
                  }
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip4$$coll1.knownSize() != 0 && this.$outer.scala$collection$LazyZip4$$coll2.knownSize() != 0 && this.$outer.scala$collection$LazyZip4$$coll3.knownSize() != 0 && this.$outer.scala$collection$LazyZip4$$coll4.knownSize() != 0 ? -1 : 0;
         }

         public boolean isEmpty() {
            return (new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private final Iterator elems4;
               private Tuple4 _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Tuple4 current() {
                  while(this._current == null && this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext() && this.elems4.hasNext()) {
                     Object e1 = this.elems1.next();
                     Object e2 = this.elems2.next();
                     Object e3 = this.elems3.next();
                     Object e4 = this.elems4.next();
                     if (BoxesRunTime.unboxToBoolean(this.$outer.p$5.apply(e1, e2, e3, e4))) {
                        this._current = new Tuple4(e1, e2, e3, e4);
                     }
                  }

                  return this._current;
               }

               public boolean hasNext() {
                  return this.current() != null;
               }

               public Tuple4 next() {
                  Tuple4 c = this.current();
                  if (c != null) {
                     this._current = null;
                     return c;
                  } else {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return (Tuple4)Iterator$.scala$collection$Iterator$$_empty.next();
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll3.iterator();
                     this.elems4 = scala$collection$LazyZip4$$anon$$$outer().scala$collection$LazyZip4$$coll4.iterator();
                  }
               }
            }).isEmpty();
         }

         // $FF: synthetic method
         public LazyZip4 scala$collection$LazyZip4$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip4.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip4.this;
               this.p$5 = p$5;
            }
         }
      });
   }

   public boolean exists(final Function4 p) {
      Iterator elems1 = this.scala$collection$LazyZip4$$coll1.iterator();
      Iterator elems2 = this.scala$collection$LazyZip4$$coll2.iterator();
      Iterator elems3 = this.scala$collection$LazyZip4$$coll3.iterator();
      Iterator elems4 = this.scala$collection$LazyZip4$$coll4.iterator();

      boolean res;
      for(res = false; !res && elems1.hasNext() && elems2.hasNext() && elems3.hasNext() && elems4.hasNext(); res = BoxesRunTime.unboxToBoolean(p.apply(elems1.next(), elems2.next(), elems3.next(), elems4.next()))) {
      }

      return res;
   }

   public boolean forall(final Function4 p) {
      Iterator exists_elems1 = this.scala$collection$LazyZip4$$coll1.iterator();
      Iterator exists_elems2 = this.scala$collection$LazyZip4$$coll2.iterator();
      Iterator exists_elems3 = this.scala$collection$LazyZip4$$coll3.iterator();
      Iterator exists_elems4 = this.scala$collection$LazyZip4$$coll4.iterator();

      boolean exists_res;
      Object var7;
      Object var8;
      Object var9;
      Object var10;
      for(exists_res = false; !exists_res && exists_elems1.hasNext() && exists_elems2.hasNext() && exists_elems3.hasNext() && exists_elems4.hasNext(); exists_res = !BoxesRunTime.unboxToBoolean(p.apply(var7, var8, var9, var10))) {
         Object var10000 = exists_elems1.next();
         Object var10001 = exists_elems2.next();
         Object var10002 = exists_elems3.next();
         var10 = exists_elems4.next();
         var9 = var10002;
         var8 = var10001;
         var7 = var10000;
      }

      Object var11 = null;
      Object var12 = null;
      Object var13 = null;
      Object var14 = null;
      return !exists_res;
   }

   public void foreach(final Function4 f) {
      Iterator elems1 = this.scala$collection$LazyZip4$$coll1.iterator();
      Iterator elems2 = this.scala$collection$LazyZip4$$coll2.iterator();
      Iterator elems3 = this.scala$collection$LazyZip4$$coll3.iterator();
      Iterator elems4 = this.scala$collection$LazyZip4$$coll4.iterator();

      while(elems1.hasNext() && elems2.hasNext() && elems3.hasNext() && elems4.hasNext()) {
         f.apply(elems1.next(), elems2.next(), elems3.next(), elems4.next());
      }

   }

   public View scala$collection$LazyZip4$$toIterable() {
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
            if (LazyZip4.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip4.this;
            }
         }
      };
   }

   public int scala$collection$LazyZip4$$zipKnownSize() {
      int s1 = this.scala$collection$LazyZip4$$coll1.knownSize();
      if (s1 == 0) {
         return 0;
      } else {
         int s2 = this.scala$collection$LazyZip4$$coll2.knownSize();
         if (s2 == 0) {
            return 0;
         } else {
            int s3 = this.scala$collection$LazyZip4$$coll3.knownSize();
            if (s3 == 0) {
               return 0;
            } else {
               int s4 = this.scala$collection$LazyZip4$$coll4.knownSize();
               if (s4 == 0) {
                  return 0;
               } else {
                  RichInt$ var10000 = RichInt$.MODULE$;
                  var10000 = RichInt$.MODULE$;
                  var10000 = RichInt$.MODULE$;
                  scala.math.package$ var9 = scala.math.package$.MODULE$;
                  int var5 = Math.min(s1, s2);
                  var9 = scala.math.package$.MODULE$;
                  int var6 = Math.min(var5, s3);
                  var9 = scala.math.package$.MODULE$;
                  return Math.min(var6, s4);
               }
            }
         }
      }
   }

   public String toString() {
      return (new StringBuilder(30)).append(this.scala$collection$LazyZip4$$coll1).append(".lazyZip(").append(this.scala$collection$LazyZip4$$coll2).append(").lazyZip(").append(this.scala$collection$LazyZip4$$coll3).append(").lazyZip(").append(this.scala$collection$LazyZip4$$coll4).append(")").toString();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$forall$3(final Function4 p$6, final Object el1, final Object el2, final Object el3, final Object el4) {
      return !BoxesRunTime.unboxToBoolean(p$6.apply(el1, el2, el3, el4));
   }

   public LazyZip4(final Object src, final Iterable coll1, final Iterable coll2, final Iterable coll3, final Iterable coll4) {
      this.src = src;
      this.scala$collection$LazyZip4$$coll1 = coll1;
      this.scala$collection$LazyZip4$$coll2 = coll2;
      this.scala$collection$LazyZip4$$coll3 = coll3;
      this.scala$collection$LazyZip4$$coll4 = coll4;
   }

   // $FF: synthetic method
   public static final Object $anonfun$forall$3$adapted(final Function4 p$6, final Object el1, final Object el2, final Object el3, final Object el4) {
      return BoxesRunTime.boxToBoolean($anonfun$forall$3(p$6, el1, el2, el3, el4));
   }
}
