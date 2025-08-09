package scala.collection;

import scala.Function2;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d\u0001B\n\u0015\u0005eA\u0001b\b\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\tW\u0001\u0011\t\u0011)A\u0005Y!A1\u0007\u0001B\u0001B\u0003%A\u0007\u0003\u00049\u0001\u0011\u0005A#\u000f\u0005\u0006}\u0001!\ta\u0010\u0005\u0006\u0015\u0002!\ta\u0013\u0005\u0006;\u0002!\tA\u0018\u0005\u0006U\u0002!\ta\u001b\u0005\u0006w\u0002!\t\u0001 \u0005\u0006}\u0002!\ta \u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u001d\tI\u0002\u0001C\u0005\u00037Aq!a\t\u0001\t\u0013\t)\u0003C\u0004\u0002.\u0001!\t%a\f\b\u000f\u0005\u0005C\u0003#\u0001\u0002D\u001911\u0003\u0006E\u0001\u0003\u000bBa\u0001\u000f\t\u0005\u0002\u0005\u001d\u0003bBA%!\u0011\r\u00111\n\u0002\t\u0019\u0006T\u0018PW5qe)\u0011QCF\u0001\u000bG>dG.Z2uS>t'\"A\f\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001U!!$\r\u001c#'\t\u00011\u0004\u0005\u0002\u001d;5\ta#\u0003\u0002\u001f-\t1\u0011I\\=SK\u001a\f1a\u001d:d!\t\t#\u0005\u0004\u0001\u0005\u000b\r\u0002!\u0019\u0001\u0013\u0003\u0005\r\u000b\u0014CA\u0013)!\tab%\u0003\u0002(-\t9aj\u001c;iS:<\u0007C\u0001\u000f*\u0013\tQcCA\u0002B]f\fQaY8mYF\u00022!\f\u00181\u001b\u0005!\u0012BA\u0018\u0015\u0005!IE/\u001a:bE2,\u0007CA\u00112\t\u0019\u0011\u0004\u0001\"b\u0001I\t\u0019Q\t\\\u0019\u0002\u000b\r|G\u000e\u001c\u001a\u0011\u00075rS\u0007\u0005\u0002\"m\u00111q\u0007\u0001CC\u0002\u0011\u00121!\u001273\u0003\u0019a\u0014N\\5u}Q!!h\u000f\u001f>!\u0015i\u0003\u0001M\u001b!\u0011\u0015yB\u00011\u0001!\u0011\u0015YC\u00011\u0001-\u0011\u0015\u0019D\u00011\u00015\u0003\u001da\u0017M_=[SB,\"\u0001Q#\u0015\u0005\u0005;\u0005CB\u0017CaU\"\u0005%\u0003\u0002D)\tAA*\u0019>z5&\u00048\u0007\u0005\u0002\"\u000b\u0012)a)\u0002b\u0001I\t\t!\tC\u0003I\u000b\u0001\u0007\u0011*\u0001\u0003uQ\u0006$\bcA\u0017/\t\u0006\u0019Q.\u00199\u0016\u00071;v\n\u0006\u0002N1R\u0011a*\u0015\t\u0003C=#Q\u0001\u0015\u0004C\u0002\u0011\u0012\u0011a\u0011\u0005\u0006%\u001a\u0001\u001daU\u0001\u0003E\u001a\u0004R!\f+!-:K!!\u0016\u000b\u0003\u0013\t+\u0018\u000e\u001c3Ge>l\u0007CA\u0011X\t\u00151eA1\u0001%\u0011\u0015If\u00011\u0001[\u0003\u00051\u0007#\u0002\u000f\\aU2\u0016B\u0001/\u0017\u0005%1UO\\2uS>t''A\u0004gY\u0006$X*\u00199\u0016\u0007}3'\r\u0006\u0002aOR\u0011\u0011m\u0019\t\u0003C\t$Q\u0001U\u0004C\u0002\u0011BQAU\u0004A\u0004\u0011\u0004R!\f+!K\u0006\u0004\"!\t4\u0005\u000b\u0019;!\u0019\u0001\u0013\t\u000be;\u0001\u0019\u00015\u0011\u000bqY\u0006'N5\u0011\u00075rS-\u0001\u0004gS2$XM]\u000b\u0003Y>$\"!\\;\u0015\u00059\u0004\bCA\u0011p\t\u0015\u0001\u0006B1\u0001%\u0011\u0015\u0011\u0006\u0002q\u0001r!\u0015iC\u000b\t:o!\u0011a2\u000fM\u001b\n\u0005Q4\"A\u0002+va2,'\u0007C\u0003w\u0011\u0001\u0007q/A\u0001q!\u0015a2\fM\u001by!\ta\u00120\u0003\u0002{-\t9!i\\8mK\u0006t\u0017AB3ySN$8\u000f\u0006\u0002y{\")a/\u0003a\u0001o\u00061am\u001c:bY2$2\u0001_A\u0001\u0011\u00151(\u00021\u0001x\u0003\u001d1wN]3bG\",B!a\u0002\u0002\u0016Q!\u0011\u0011BA\b!\ra\u00121B\u0005\u0004\u0003\u001b1\"\u0001B+oSRDa!W\u0006A\u0002\u0005E\u0001C\u0002\u000f\\aU\n\u0019\u0002E\u0002\"\u0003+!a!a\u0006\f\u0005\u0004!#!A+\u0002\u0015Q|\u0017\n^3sC\ndW-\u0006\u0002\u0002\u001eA!Q&a\bs\u0013\r\t\t\u0003\u0006\u0002\u0005-&,w/\u0001\u0007{SB\\en\\<o'&TX-\u0006\u0002\u0002(A\u0019A$!\u000b\n\u0007\u0005-bCA\u0002J]R\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003c\u0001B!a\r\u0002>5\u0011\u0011Q\u0007\u0006\u0005\u0003o\tI$\u0001\u0003mC:<'BAA\u001e\u0003\u0011Q\u0017M^1\n\t\u0005}\u0012Q\u0007\u0002\u0007'R\u0014\u0018N\\4\u0002\u00111\u000b'0\u001f.jaJ\u0002\"!\f\t\u0014\u0005AYBCAA\"\u0003Ia\u0017M_=[SB\u0014Dk\\%uKJ\f'\r\\3\u0016\r\u00055\u0013QKA-)\u0011\ty%a\u0017\u0011\u000b5\ny\"!\u0015\u0011\rq\u0019\u00181KA,!\r\t\u0013Q\u000b\u0003\u0006eI\u0011\r\u0001\n\t\u0004C\u0005eC!B\u001c\u0013\u0005\u0004!\u0003bBA/%\u0001\u0007\u0011qL\u0001\bu&\u0004\b/\u001a33a\u0011\t\t'!\u001a\u0011\u00115\u0002\u00111KA,\u0003G\u00022!IA3\t-\t9'a\u0017\u0002\u0002\u0003\u0005)\u0011\u0001\u0013\u0003\u0007}#\u0013\u0007"
)
public final class LazyZip2 {
   private final Object src;
   public final Iterable scala$collection$LazyZip2$$coll1;
   public final Iterable scala$collection$LazyZip2$$coll2;

   public static View lazyZip2ToIterable(final LazyZip2 zipped2) {
      LazyZip2$ var10000 = LazyZip2$.MODULE$;
      if (zipped2 == null) {
         throw null;
      } else {
         return new AbstractView() {
            // $FF: synthetic field
            private final LazyZip2 $outer;

            public AbstractIterator iterator() {
               return new AbstractIterator() {
                  private final Iterator elems1;
                  private final Iterator elems2;

                  public boolean hasNext() {
                     return this.elems1.hasNext() && this.elems2.hasNext();
                  }

                  public Tuple2 next() {
                     return new Tuple2(this.elems1.next(), this.elems2.next());
                  }

                  public {
                     this.elems1 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll2.iterator();
                  }
               };
            }

            public int knownSize() {
               return this.$outer.scala$collection$LazyZip2$$zipKnownSize();
            }

            public boolean isEmpty() {
               return this.$outer.scala$collection$LazyZip2$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip2$$coll2.isEmpty();
            }

            // $FF: synthetic method
            public LazyZip2 scala$collection$LazyZip2$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (LazyZip2.this == null) {
                  throw null;
               } else {
                  this.$outer = LazyZip2.this;
               }
            }
         };
      }
   }

   public LazyZip3 lazyZip(final Iterable that) {
      return new LazyZip3(this.src, this.scala$collection$LazyZip2$$coll1, this.scala$collection$LazyZip2$$coll2, that);
   }

   public Object map(final Function2 f, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(f) {
         // $FF: synthetic field
         private final LazyZip2 $outer;
         public final Function2 f$1;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               public boolean hasNext() {
                  return this.elems1.hasNext() && this.elems2.hasNext();
               }

               public Object next() {
                  return this.$outer.f$1.apply(this.elems1.next(), this.elems2.next());
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll2.iterator();
                  }
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip2$$zipKnownSize();
         }

         public boolean isEmpty() {
            return this.$outer.scala$collection$LazyZip2$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip2$$coll2.isEmpty();
         }

         // $FF: synthetic method
         public LazyZip2 scala$collection$LazyZip2$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip2.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip2.this;
               this.f$1 = f$1;
            }
         }
      });
   }

   public Object flatMap(final Function2 f, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(f) {
         // $FF: synthetic field
         private final LazyZip2 $outer;
         public final Function2 f$2;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private Iterator _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Iterator current() {
                  while(!this._current.hasNext() && this.elems1.hasNext() && this.elems2.hasNext()) {
                     this._current = ((IterableOnce)this.$outer.f$2.apply(this.elems1.next(), this.elems2.next())).iterator();
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
                     this.elems1 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll2.iterator();
                     Iterator$ var10001 = Iterator$.MODULE$;
                     this._current = Iterator$.scala$collection$Iterator$$_empty;
                  }
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip2$$coll1.knownSize() != 0 && this.$outer.scala$collection$LazyZip2$$coll2.knownSize() != 0 ? -1 : 0;
         }

         public boolean isEmpty() {
            return this.$outer.scala$collection$LazyZip2$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip2$$coll2.isEmpty();
         }

         // $FF: synthetic method
         public LazyZip2 scala$collection$LazyZip2$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip2.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip2.this;
               this.f$2 = f$2;
            }
         }
      });
   }

   public Object filter(final Function2 p, final BuildFrom bf) {
      return bf.fromSpecific(this.src, new AbstractView(p) {
         // $FF: synthetic field
         private final LazyZip2 $outer;
         public final Function2 p$1;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private Tuple2 _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Tuple2 current() {
                  while(this._current == null && this.elems1.hasNext() && this.elems2.hasNext()) {
                     Object e1 = this.elems1.next();
                     Object e2 = this.elems2.next();
                     if (BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(e1, e2))) {
                        this._current = new Tuple2(e1, e2);
                     }
                  }

                  return this._current;
               }

               public boolean hasNext() {
                  return this.current() != null;
               }

               public Tuple2 next() {
                  Tuple2 c = this.current();
                  if (c != null) {
                     this._current = null;
                     return c;
                  } else {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return (Tuple2)Iterator$.scala$collection$Iterator$$_empty.next();
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll2.iterator();
                  }
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip2$$coll1.knownSize() != 0 && this.$outer.scala$collection$LazyZip2$$coll2.knownSize() != 0 ? -1 : 0;
         }

         public boolean isEmpty() {
            return (new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;
               private Tuple2 _current;
               // $FF: synthetic field
               private final <undefinedtype> $outer;

               private Tuple2 current() {
                  while(this._current == null && this.elems1.hasNext() && this.elems2.hasNext()) {
                     Object e1 = this.elems1.next();
                     Object e2 = this.elems2.next();
                     if (BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(e1, e2))) {
                        this._current = new Tuple2(e1, e2);
                     }
                  }

                  return this._current;
               }

               public boolean hasNext() {
                  return this.current() != null;
               }

               public Tuple2 next() {
                  Tuple2 c = this.current();
                  if (c != null) {
                     this._current = null;
                     return c;
                  } else {
                     Iterator$ var10000 = Iterator$.MODULE$;
                     return (Tuple2)Iterator$.scala$collection$Iterator$$_empty.next();
                  }
               }

               public {
                  if (<VAR_NAMELESS_ENCLOSURE> == null) {
                     throw null;
                  } else {
                     this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     this.elems1 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll2.iterator();
                  }
               }
            }).hasNext();
         }

         // $FF: synthetic method
         public LazyZip2 scala$collection$LazyZip2$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip2.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip2.this;
               this.p$1 = p$1;
            }
         }
      });
   }

   public boolean exists(final Function2 p) {
      Iterator elems1 = this.scala$collection$LazyZip2$$coll1.iterator();
      Iterator elems2 = this.scala$collection$LazyZip2$$coll2.iterator();

      boolean res;
      for(res = false; !res && elems1.hasNext() && elems2.hasNext(); res = BoxesRunTime.unboxToBoolean(p.apply(elems1.next(), elems2.next()))) {
      }

      return res;
   }

   public boolean forall(final Function2 p) {
      Iterator exists_elems1 = this.scala$collection$LazyZip2$$coll1.iterator();
      Iterator exists_elems2 = this.scala$collection$LazyZip2$$coll2.iterator();

      boolean exists_res;
      Object var5;
      Object var6;
      for(exists_res = false; !exists_res && exists_elems1.hasNext() && exists_elems2.hasNext(); exists_res = !BoxesRunTime.unboxToBoolean(p.apply(var5, var6))) {
         Object var10000 = exists_elems1.next();
         var6 = exists_elems2.next();
         var5 = var10000;
      }

      Object var7 = null;
      Object var8 = null;
      return !exists_res;
   }

   public void foreach(final Function2 f) {
      Iterator elems1 = this.scala$collection$LazyZip2$$coll1.iterator();
      Iterator elems2 = this.scala$collection$LazyZip2$$coll2.iterator();

      while(elems1.hasNext() && elems2.hasNext()) {
         f.apply(elems1.next(), elems2.next());
      }

   }

   public View scala$collection$LazyZip2$$toIterable() {
      return new AbstractView() {
         // $FF: synthetic field
         private final LazyZip2 $outer;

         public AbstractIterator iterator() {
            return new AbstractIterator() {
               private final Iterator elems1;
               private final Iterator elems2;

               public boolean hasNext() {
                  return this.elems1.hasNext() && this.elems2.hasNext();
               }

               public Tuple2 next() {
                  return new Tuple2(this.elems1.next(), this.elems2.next());
               }

               public {
                  this.elems1 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll1.iterator();
                  this.elems2 = scala$collection$LazyZip2$$anon$$$outer().scala$collection$LazyZip2$$coll2.iterator();
               }
            };
         }

         public int knownSize() {
            return this.$outer.scala$collection$LazyZip2$$zipKnownSize();
         }

         public boolean isEmpty() {
            return this.$outer.scala$collection$LazyZip2$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip2$$coll2.isEmpty();
         }

         // $FF: synthetic method
         public LazyZip2 scala$collection$LazyZip2$$anon$$$outer() {
            return this.$outer;
         }

         public {
            if (LazyZip2.this == null) {
               throw null;
            } else {
               this.$outer = LazyZip2.this;
            }
         }
      };
   }

   public int scala$collection$LazyZip2$$zipKnownSize() {
      int s1 = this.scala$collection$LazyZip2$$coll1.knownSize();
      if (s1 == 0) {
         return 0;
      } else {
         int s2 = this.scala$collection$LazyZip2$$coll2.knownSize();
         if (s2 == 0) {
            return 0;
         } else {
            RichInt$ var10000 = RichInt$.MODULE$;
            scala.math.package$ var3 = scala.math.package$.MODULE$;
            return Math.min(s1, s2);
         }
      }
   }

   public String toString() {
      return (new StringBuilder(10)).append(this.scala$collection$LazyZip2$$coll1).append(".lazyZip(").append(this.scala$collection$LazyZip2$$coll2).append(")").toString();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$forall$1(final Function2 p$2, final Object el1, final Object el2) {
      return !BoxesRunTime.unboxToBoolean(p$2.apply(el1, el2));
   }

   public LazyZip2(final Object src, final Iterable coll1, final Iterable coll2) {
      this.src = src;
      this.scala$collection$LazyZip2$$coll1 = coll1;
      this.scala$collection$LazyZip2$$coll2 = coll2;
   }

   // $FF: synthetic method
   public static final Object $anonfun$forall$1$adapted(final Function2 p$2, final Object el1, final Object el2) {
      return BoxesRunTime.boxToBoolean($anonfun$forall$1(p$2, el1, el2));
   }
}
