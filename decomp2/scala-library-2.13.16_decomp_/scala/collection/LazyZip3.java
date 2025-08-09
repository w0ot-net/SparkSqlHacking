package scala.collection;

import scala.Function3;
import scala.Tuple3;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001\u0002\u000b\u0016\u0005iA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006I!\t\u0005\tY\u0001\u0011\t\u0011)A\u0005[!AA\u0007\u0001B\u0001B\u0003%Q\u0007\u0003\u0005:\u0001\t\u0005\t\u0015!\u0003;\u0011\u0019q\u0004\u0001\"\u0001\u0016\u007f!)Q\t\u0001C\u0001\r\")\u0011\u000b\u0001C\u0001%\")A\r\u0001C\u0001K\")\u0011\u000f\u0001C\u0001e\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001bBA\u0006\u0001\u0011\u0005\u0011Q\u0002\u0005\b\u0003#\u0001A\u0011AA\n\u0011\u001d\t9\u0003\u0001C\u0005\u0003SAq!!\r\u0001\t\u0013\t\u0019\u0004C\u0004\u0002<\u0001!\t%!\u0010\b\u000f\u0005=S\u0003#\u0001\u0002R\u00191A#\u0006E\u0001\u0003'BaAP\t\u0005\u0002\u0005U\u0003bBA,#\u0011\r\u0011\u0011\f\u0002\t\u0019\u0006T\u0018PW5qg)\u0011acF\u0001\u000bG>dG.Z2uS>t'\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U)1DM\u001c=GM\u0011\u0001\u0001\b\t\u0003;yi\u0011aF\u0005\u0003?]\u0011a!\u00118z%\u00164\u0017aA:sGB\u0011!e\t\u0007\u0001\t\u0015!\u0003A1\u0001&\u0005\t\u0019\u0015'\u0005\u0002'SA\u0011QdJ\u0005\u0003Q]\u0011qAT8uQ&tw\r\u0005\u0002\u001eU%\u00111f\u0006\u0002\u0004\u0003:L\u0018!B2pY2\f\u0004c\u0001\u00180c5\tQ#\u0003\u00021+\tA\u0011\n^3sC\ndW\r\u0005\u0002#e\u001111\u0007\u0001CC\u0002\u0015\u00121!\u001272\u0003\u0015\u0019w\u000e\u001c73!\rqsF\u000e\t\u0003E]\"a\u0001\u000f\u0001\u0005\u0006\u0004)#aA#me\u0005)1m\u001c7mgA\u0019afL\u001e\u0011\u0005\tbDAB\u001f\u0001\t\u000b\u0007QEA\u0002FYN\na\u0001P5oSRtD#\u0002!B\u0005\u000e#\u0005C\u0002\u0018\u0001cYZ\u0014\u0005C\u0003!\u000b\u0001\u0007\u0011\u0005C\u0003-\u000b\u0001\u0007Q\u0006C\u00035\u000b\u0001\u0007Q\u0007C\u0003:\u000b\u0001\u0007!(A\u0004mCjL(,\u001b9\u0016\u0005\u001dcEC\u0001%O!\u001dq\u0013*\r\u001c<\u0017\u0006J!AS\u000b\u0003\u00111\u000b'0\u001f.jaR\u0002\"A\t'\u0005\u000b53!\u0019A\u0013\u0003\u0003\tCQa\u0014\u0004A\u0002A\u000bA\u0001\u001e5biB\u0019afL&\u0002\u00075\f\u0007/F\u0002T=Z#\"\u0001V0\u0015\u0005UC\u0006C\u0001\u0012W\t\u00159vA1\u0001&\u0005\u0005\u0019\u0005\"B-\b\u0001\bQ\u0016A\u00012g!\u0015q3,I/V\u0013\taVCA\u0005Ck&dGM\u0012:p[B\u0011!E\u0018\u0003\u0006\u001b\u001e\u0011\r!\n\u0005\u0006A\u001e\u0001\r!Y\u0001\u0002MB1QDY\u00197wuK!aY\f\u0003\u0013\u0019+hn\u0019;j_:\u001c\u0014a\u00024mCRl\u0015\r]\u000b\u0004M6LGCA4o)\tA'\u000e\u0005\u0002#S\u0012)q\u000b\u0003b\u0001K!)\u0011\f\u0003a\u0002WB)afW\u0011mQB\u0011!%\u001c\u0003\u0006\u001b\"\u0011\r!\n\u0005\u0006A\"\u0001\ra\u001c\t\u0007;\t\fdg\u000f9\u0011\u00079zC.\u0001\u0004gS2$XM]\u000b\u0003gZ$\"\u0001\u001e?\u0015\u0005U<\bC\u0001\u0012w\t\u00159\u0016B1\u0001&\u0011\u0015I\u0016\u0002q\u0001y!\u0015q3,I=v!\u0015i\"0\r\u001c<\u0013\tYxC\u0001\u0004UkBdWm\r\u0005\u0006{&\u0001\rA`\u0001\u0002aB1QDY\u00197w}\u00042!HA\u0001\u0013\r\t\u0019a\u0006\u0002\b\u0005>|G.Z1o\u0003\u0019)\u00070[:ugR\u0019q0!\u0003\t\u000buT\u0001\u0019\u0001@\u0002\r\u0019|'/\u00197m)\ry\u0018q\u0002\u0005\u0006{.\u0001\rA`\u0001\bM>\u0014X-Y2i+\u0011\t)\"a\t\u0015\t\u0005]\u0011Q\u0004\t\u0004;\u0005e\u0011bAA\u000e/\t!QK\\5u\u0011\u0019\u0001G\u00021\u0001\u0002 A9QDY\u00197w\u0005\u0005\u0002c\u0001\u0012\u0002$\u00111\u0011Q\u0005\u0007C\u0002\u0015\u0012\u0011!V\u0001\u000bi>LE/\u001a:bE2,WCAA\u0016!\u0011q\u0013QF=\n\u0007\u0005=RC\u0001\u0003WS\u0016<\u0018\u0001\u0004>ja.swn\u001e8TSj,WCAA\u001b!\ri\u0012qG\u0005\u0004\u0003s9\"aA%oi\u0006AAo\\*ue&tw\r\u0006\u0002\u0002@A!\u0011\u0011IA&\u001b\t\t\u0019E\u0003\u0003\u0002F\u0005\u001d\u0013\u0001\u00027b]\u001eT!!!\u0013\u0002\t)\fg/Y\u0005\u0005\u0003\u001b\n\u0019E\u0001\u0004TiJLgnZ\u0001\t\u0019\u0006T\u0018PW5qgA\u0011a&E\n\u0003#q!\"!!\u0015\u0002%1\f'0\u001f.jaN\"v.\u0013;fe\u0006\u0014G.Z\u000b\t\u00037\n\u0019'a\u001a\u0002lQ!\u0011QLA7!\u0015q\u0013QFA0!!i\"0!\u0019\u0002f\u0005%\u0004c\u0001\u0012\u0002d\u0011)1g\u0005b\u0001KA\u0019!%a\u001a\u0005\u000ba\u001a\"\u0019A\u0013\u0011\u0007\t\nY\u0007B\u0003>'\t\u0007Q\u0005C\u0004\u0002pM\u0001\r!!\u001d\u0002\u000fiL\u0007\u000f]3egA\"\u00111OA<!)q\u0003!!\u0019\u0002f\u0005%\u0014Q\u000f\t\u0004E\u0005]DaCA=\u0003[\n\t\u0011!A\u0003\u0002\u0015\u00121a\u0018\u00133\u0001"
)
public final class LazyZip3 {
   private final Object src;
   public final Iterable scala$collection$LazyZip3$$coll1;
   public final Iterable scala$collection$LazyZip3$$coll2;
   public final Iterable scala$collection$LazyZip3$$coll3;

   public static View lazyZip3ToIterable(final LazyZip3 zipped3) {
      LazyZip3$ var10000 = LazyZip3$.MODULE$;
      if (zipped3 == null) {
         throw null;
      } else {
         return new AbstractView() {
            // $FF: synthetic field
            private final LazyZip3 $outer;

            public AbstractIterator iterator() {
               return new AbstractIterator() {
                  private final Iterator elems1;
                  private final Iterator elems2;
                  private final Iterator elems3;

                  public boolean hasNext() {
                     return this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext();
                  }

                  public Tuple3 next() {
                     return new Tuple3(this.elems1.next(), this.elems2.next(), this.elems3.next());
                  }

                  public {
                     this.elems1 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll3.iterator();
                  }
               };
            }

            public int knownSize() {
               return this.$outer.scala$collection$LazyZip3$$zipKnownSize();
            }

            public boolean isEmpty() {
               return this.$outer.scala$collection$LazyZip3$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip3$$coll2.isEmpty() || this.$outer.scala$collection$LazyZip3$$coll3.isEmpty();
            }

            // $FF: synthetic method
            public LazyZip3 scala$collection$LazyZip3$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (LazyZip3.this == null) {
                  throw null;
               } else {
                  this.$outer = LazyZip3.this;
               }
            }
         };
      }
   }

   public LazyZip4 lazyZip(final Iterable that) {
      return new LazyZip4(this.src, this.scala$collection$LazyZip3$$coll1, this.scala$collection$LazyZip3$$coll2, this.scala$collection$LazyZip3$$coll3, that);
   }

   public Object map(final Function3 f, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(f) {
         // $FF: synthetic field
         private final LazyZip3 $outer;
         public final Function3 f$3;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public boolean hasNext() {
                  return this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext();
               }

               public Object next() {
                  return this.$outer.f$3.apply(this.elems1.next(), this.elems2.next(), this.elems3.next());
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll3.iterator();
                  }
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip3$$zipKnownSize();
         }

         public boolean isEmpty() {
            return this.$outer.scala$collection$LazyZip3$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip3$$coll2.isEmpty() || this.$outer.scala$collection$LazyZip3$$coll3.isEmpty();
         }

         // $FF: synthetic method
         public LazyZip3 scala$collection$LazyZip3$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip3.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip3.this;
               this.f$3 = f$3;
            }
         }
      });
   }

   public Object flatMap(final Function3 f, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(f) {
         // $FF: synthetic field
         private final LazyZip3 $outer;
         public final Function3 f$4;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private Iterator _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Iterator current() {
                  while(!this._current.hasNext() && this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext()) {
                     this._current = ((IterableOnce)this.$outer.f$4.apply(this.elems1.next(), this.elems2.next(), this.elems3.next())).iterator();
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
                     this.elems1 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll3.iterator();
                     Iterator$ var10001 = Iterator$.MODULE$;
                     this._current = Iterator$.scala$collection$Iterator$$_empty;
                  }
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip3$$coll1.knownSize() != 0 && this.$outer.scala$collection$LazyZip3$$coll2.knownSize() != 0 && this.$outer.scala$collection$LazyZip3$$coll3.knownSize() != 0 ? -1 : 0;
         }

         public boolean isEmpty() {
            return (new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private Iterator _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Iterator current() {
                  while(!this._current.hasNext() && this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext()) {
                     this._current = ((IterableOnce)this.$outer.f$4.apply(this.elems1.next(), this.elems2.next(), this.elems3.next())).iterator();
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
                     this.elems1 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll3.iterator();
                     Iterator$ var10001 = Iterator$.MODULE$;
                     this._current = Iterator$.scala$collection$Iterator$$_empty;
                  }
               }
            }).isEmpty();
         }

         // $FF: synthetic method
         public LazyZip3 scala$collection$LazyZip3$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip3.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip3.this;
               this.f$4 = f$4;
            }
         }
      });
   }

   public Object filter(final Function3 p, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(p) {
         // $FF: synthetic field
         private final LazyZip3 $outer;
         public final Function3 p$3;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private Tuple3 _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Tuple3 current() {
                  while(this._current == null && this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext()) {
                     Object e1 = this.elems1.next();
                     Object e2 = this.elems2.next();
                     Object e3 = this.elems3.next();
                     if (BoxesRunTime.unboxToBoolean(this.$outer.p$3.apply(e1, e2, e3))) {
                        this._current = new Tuple3(e1, e2, e3);
                     }
                  }

                  return this._current;
               }

               public boolean hasNext() {
                  return this.current() != null;
               }

               public Tuple3 next() {
                  Tuple3 c = this.current();
                  if (c != null) {
                     this._current = null;
                     return c;
                  } else {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return (Tuple3)Iterator$.scala$collection$Iterator$$_empty.next();
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll3.iterator();
                  }
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip3$$coll1.knownSize() != 0 && this.$outer.scala$collection$LazyZip3$$coll2.knownSize() != 0 && this.$outer.scala$collection$LazyZip3$$coll3.knownSize() != 0 ? -1 : 0;
         }

         public boolean isEmpty() {
            return (new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;
               private Tuple3 _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Tuple3 current() {
                  while(this._current == null && this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext()) {
                     Object e1 = this.elems1.next();
                     Object e2 = this.elems2.next();
                     Object e3 = this.elems3.next();
                     if (BoxesRunTime.unboxToBoolean(this.$outer.p$3.apply(e1, e2, e3))) {
                        this._current = new Tuple3(e1, e2, e3);
                     }
                  }

                  return this._current;
               }

               public boolean hasNext() {
                  return this.current() != null;
               }

               public Tuple3 next() {
                  Tuple3 c = this.current();
                  if (c != null) {
                     this._current = null;
                     return c;
                  } else {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return (Tuple3)Iterator$.scala$collection$Iterator$$_empty.next();
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll3.iterator();
                  }
               }
            }).isEmpty();
         }

         // $FF: synthetic method
         public LazyZip3 scala$collection$LazyZip3$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip3.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip3.this;
               this.p$3 = p$3;
            }
         }
      });
   }

   public boolean exists(final Function3 p) {
      Iterator elems1 = this.scala$collection$LazyZip3$$coll1.iterator();
      Iterator elems2 = this.scala$collection$LazyZip3$$coll2.iterator();
      Iterator elems3 = this.scala$collection$LazyZip3$$coll3.iterator();

      boolean res;
      for(res = false; !res && elems1.hasNext() && elems2.hasNext() && elems3.hasNext(); res = BoxesRunTime.unboxToBoolean(p.apply(elems1.next(), elems2.next(), elems3.next()))) {
      }

      return res;
   }

   public boolean forall(final Function3 p) {
      Iterator exists_elems1 = this.scala$collection$LazyZip3$$coll1.iterator();
      Iterator exists_elems2 = this.scala$collection$LazyZip3$$coll2.iterator();
      Iterator exists_elems3 = this.scala$collection$LazyZip3$$coll3.iterator();

      boolean exists_res;
      Object var6;
      Object var7;
      Object var8;
      for(exists_res = false; !exists_res && exists_elems1.hasNext() && exists_elems2.hasNext() && exists_elems3.hasNext(); exists_res = !BoxesRunTime.unboxToBoolean(p.apply(var6, var7, var8))) {
         Object var10000 = exists_elems1.next();
         Object var10001 = exists_elems2.next();
         var8 = exists_elems3.next();
         var7 = var10001;
         var6 = var10000;
      }

      Object var9 = null;
      Object var10 = null;
      Object var11 = null;
      return !exists_res;
   }

   public void foreach(final Function3 f) {
      Iterator elems1 = this.scala$collection$LazyZip3$$coll1.iterator();
      Iterator elems2 = this.scala$collection$LazyZip3$$coll2.iterator();
      Iterator elems3 = this.scala$collection$LazyZip3$$coll3.iterator();

      while(elems1.hasNext() && elems2.hasNext() && elems3.hasNext()) {
         f.apply(elems1.next(), elems2.next(), elems3.next());
      }

   }

   public View scala$collection$LazyZip3$$toIterable() {
      return new AbstractView() {
         // $FF: synthetic field
         private final LazyZip3 $outer;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private final Iterator elems3;

               public boolean hasNext() {
                  return this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext();
               }

               public Tuple3 next() {
                  return new Tuple3(this.elems1.next(), this.elems2.next(), this.elems3.next());
               }

               public {
                  this.elems1 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll1.iterator();
                  this.elems2 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll2.iterator();
                  this.elems3 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll3.iterator();
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip3$$zipKnownSize();
         }

         public boolean isEmpty() {
            return this.$outer.scala$collection$LazyZip3$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip3$$coll2.isEmpty() || this.$outer.scala$collection$LazyZip3$$coll3.isEmpty();
         }

         // $FF: synthetic method
         public LazyZip3 scala$collection$LazyZip3$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip3.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip3.this;
            }
         }
      };
   }

   public int scala$collection$LazyZip3$$zipKnownSize() {
      int s1 = this.scala$collection$LazyZip3$$coll1.knownSize();
      if (s1 == 0) {
         return 0;
      } else {
         int s2 = this.scala$collection$LazyZip3$$coll2.knownSize();
         if (s2 == 0) {
            return 0;
         } else {
            int s3 = this.scala$collection$LazyZip3$$coll3.knownSize();
            if (s3 == 0) {
               return 0;
            } else {
               RichInt$ var10000 = RichInt$.MODULE$;
               var10000 = RichInt$.MODULE$;
               scala.math.package$ var6 = scala.math.package$.MODULE$;
               int var4 = Math.min(s1, s2);
               var6 = scala.math.package$.MODULE$;
               return Math.min(var4, s3);
            }
         }
      }
   }

   public String toString() {
      return (new StringBuilder(20)).append(this.scala$collection$LazyZip3$$coll1).append(".lazyZip(").append(this.scala$collection$LazyZip3$$coll2).append(").lazyZip(").append(this.scala$collection$LazyZip3$$coll3).append(")").toString();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$forall$2(final Function3 p$4, final Object el1, final Object el2, final Object el3) {
      return !BoxesRunTime.unboxToBoolean(p$4.apply(el1, el2, el3));
   }

   public LazyZip3(final Object src, final Iterable coll1, final Iterable coll2, final Iterable coll3) {
      this.src = src;
      this.scala$collection$LazyZip3$$coll1 = coll1;
      this.scala$collection$LazyZip3$$coll2 = coll2;
      this.scala$collection$LazyZip3$$coll3 = coll3;
   }

   // $FF: synthetic method
   public static final Object $anonfun$forall$2$adapted(final Function3 p$4, final Object el1, final Object el2, final Object el3) {
      return BoxesRunTime.boxToBoolean($anonfun$forall$2(p$4, el1, el2, el3));
   }
}
