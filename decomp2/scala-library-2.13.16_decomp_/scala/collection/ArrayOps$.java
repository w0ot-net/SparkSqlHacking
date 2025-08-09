package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.$less$colon$less$;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Predef$;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.convert.impl.BoxedBooleanArrayStepper;
import scala.collection.convert.impl.DoubleArrayStepper;
import scala.collection.convert.impl.IntArrayStepper;
import scala.collection.convert.impl.LongArrayStepper;
import scala.collection.convert.impl.ObjectArrayStepper;
import scala.collection.convert.impl.WidenedByteArrayStepper;
import scala.collection.convert.impl.WidenedCharArrayStepper;
import scala.collection.convert.impl.WidenedFloatArrayStepper;
import scala.collection.convert.impl.WidenedShortArrayStepper;
import scala.collection.generic.CommonErrors$;
import scala.collection.immutable.ArraySeq$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.math.Ordering$;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;
import scala.util.Sorting$;

public final class ArrayOps$ {
   public static final ArrayOps$ MODULE$ = new ArrayOps$();
   private static final Function1 fallback = (x$1) -> MODULE$.fallback();

   private final int MaxStableSortLength() {
      return 300;
   }

   private Function1 fallback() {
      return fallback;
   }

   public final ClassTag scala$collection$ArrayOps$$elemTag$extension(final Object $this) {
      return ClassTag$.MODULE$.apply($this.getClass().getComponentType());
   }

   public final int size$extension(final Object $this) {
      return Array.getLength($this);
   }

   public final int knownSize$extension(final Object $this) {
      return Array.getLength($this);
   }

   public final boolean isEmpty$extension(final Object $this) {
      return Array.getLength($this) == 0;
   }

   public final boolean nonEmpty$extension(final Object $this) {
      return Array.getLength($this) != 0;
   }

   public final Object head$extension(final Object $this) {
      if (Array.getLength($this) != 0) {
         return ScalaRunTime$.MODULE$.array_apply($this, 0);
      } else {
         throw new NoSuchElementException("head of empty array");
      }
   }

   public final Object last$extension(final Object $this) {
      if (Array.getLength($this) != 0) {
         return ScalaRunTime$.MODULE$.array_apply($this, Array.getLength($this) - 1);
      } else {
         throw new NoSuchElementException("last of empty array");
      }
   }

   public final Option headOption$extension(final Object $this) {
      return (Option)(Array.getLength($this) == 0 ? None$.MODULE$ : new Some(this.head$extension($this)));
   }

   public final Option lastOption$extension(final Object $this) {
      return (Option)(Array.getLength($this) == 0 ? None$.MODULE$ : new Some(this.last$extension($this)));
   }

   public final int sizeCompare$extension(final Object $this, final int otherSize) {
      return Integer.compare(Array.getLength($this), otherSize);
   }

   public final int lengthCompare$extension(final Object $this, final int len) {
      return Integer.compare(Array.getLength($this), len);
   }

   public final int sizeIs$extension(final Object $this) {
      return Array.getLength($this);
   }

   public final int lengthIs$extension(final Object $this) {
      return Array.getLength($this);
   }

   public final Object slice$extension(final Object $this, final int from, final int until) {
      int lo = Math.max(from, 0);
      int hi = Math.min(until, Array.getLength($this));
      if (hi > lo) {
         if ($this instanceof Object[]) {
            return Arrays.copyOfRange($this, lo, hi);
         } else if ($this instanceof int[]) {
            return Arrays.copyOfRange((int[])$this, lo, hi);
         } else if ($this instanceof double[]) {
            return Arrays.copyOfRange((double[])$this, lo, hi);
         } else if ($this instanceof long[]) {
            return Arrays.copyOfRange((long[])$this, lo, hi);
         } else if ($this instanceof float[]) {
            return Arrays.copyOfRange((float[])$this, lo, hi);
         } else if ($this instanceof char[]) {
            return Arrays.copyOfRange((char[])$this, lo, hi);
         } else if ($this instanceof byte[]) {
            return Arrays.copyOfRange((byte[])$this, lo, hi);
         } else if ($this instanceof short[]) {
            return Arrays.copyOfRange((short[])$this, lo, hi);
         } else if ($this instanceof boolean[]) {
            return Arrays.copyOfRange((boolean[])$this, lo, hi);
         } else {
            throw new MatchError($this);
         }
      } else {
         return ClassTag$.MODULE$.apply($this.getClass().getComponentType()).newArray(0);
      }
   }

   public final Object tail$extension(final Object $this) {
      if (Array.getLength($this) == 0) {
         throw new UnsupportedOperationException("tail of empty array");
      } else {
         return this.slice$extension($this, 1, Array.getLength($this));
      }
   }

   public final Object init$extension(final Object $this) {
      if (Array.getLength($this) == 0) {
         throw new UnsupportedOperationException("init of empty array");
      } else {
         return this.slice$extension($this, 0, Array.getLength($this) - 1);
      }
   }

   public final Iterator tails$extension(final Object $this) {
      Function1 iterateUntilEmpty$extension_f = (xs) -> MODULE$.tail$extension(xs);
      Iterator$ var10000 = Iterator$.MODULE$;
      Iterator var4 = (new AbstractIterator($this, iterateUntilEmpty$extension_f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      }).takeWhile((x) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x)));
      Function0 iterateUntilEmpty$extension_$plus$plus_xs = () -> {
         Iterator$ var10000 = Iterator$.MODULE$;
         ClassTag empty_evidence$4 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
         var10000 = (Iterator$)empty_evidence$4.newArray(0);
         Object var3 = null;
         Object single_a = var10000;
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      };
      if (var4 == null) {
         throw null;
      } else {
         return var4.concat(iterateUntilEmpty$extension_$plus$plus_xs);
      }
   }

   public final Iterator inits$extension(final Object $this) {
      Function1 iterateUntilEmpty$extension_f = (xs) -> MODULE$.init$extension(xs);
      Iterator$ var10000 = Iterator$.MODULE$;
      Iterator var4 = (new AbstractIterator($this, iterateUntilEmpty$extension_f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      }).takeWhile((x) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x)));
      Function0 iterateUntilEmpty$extension_$plus$plus_xs = () -> {
         Iterator$ var10000 = Iterator$.MODULE$;
         ClassTag empty_evidence$4 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
         var10000 = (Iterator$)empty_evidence$4.newArray(0);
         Object var3 = null;
         Object single_a = var10000;
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      };
      if (var4 == null) {
         throw null;
      } else {
         return var4.concat(iterateUntilEmpty$extension_$plus$plus_xs);
      }
   }

   public final Iterator iterateUntilEmpty$extension(final Object $this, final Function1 f) {
      Iterator$ var10000 = Iterator$.MODULE$;
      Iterator var4 = (new AbstractIterator($this, f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      }).takeWhile((x) -> BoxesRunTime.boxToBoolean($anonfun$iterateUntilEmpty$1(x)));
      Function0 $plus$plus_xs = () -> {
         Iterator$ var10000 = Iterator$.MODULE$;
         ClassTag empty_evidence$4 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
         var10000 = (Iterator$)empty_evidence$4.newArray(0);
         Object var3 = null;
         Object single_a = var10000;
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      };
      if (var4 == null) {
         throw null;
      } else {
         return var4.concat($plus$plus_xs);
      }
   }

   public final Object take$extension(final Object $this, final int n) {
      return this.slice$extension($this, 0, n);
   }

   public final Object drop$extension(final Object $this, final int n) {
      return this.slice$extension($this, n, Array.getLength($this));
   }

   public final Object takeRight$extension(final Object $this, final int n) {
      return this.drop$extension($this, Array.getLength($this) - Math.max(n, 0));
   }

   public final Object dropRight$extension(final Object $this, final int n) {
      int take$extension_n = Array.getLength($this) - Math.max(n, 0);
      return this.slice$extension($this, 0, take$extension_n);
   }

   public final Object takeWhile$extension(final Object $this, final Function1 p) {
      int indexWhere$extension_i = 0;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= Array.getLength($this)) {
            var10000 = -1;
            break;
         }

         Object var6 = ScalaRunTime$.MODULE$.array_apply($this, indexWhere$extension_i);
         if (!BoxesRunTime.unboxToBoolean(p.apply(var6))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int i = var10000;
      int hi = i < 0 ? Array.getLength($this) : i;
      return this.slice$extension($this, 0, hi);
   }

   public final Object dropWhile$extension(final Object $this, final Function1 p) {
      int indexWhere$extension_i = 0;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= Array.getLength($this)) {
            var10000 = -1;
            break;
         }

         Object var6 = ScalaRunTime$.MODULE$.array_apply($this, indexWhere$extension_i);
         if (!BoxesRunTime.unboxToBoolean(p.apply(var6))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int i = var10000;
      int lo = i < 0 ? Array.getLength($this) : i;
      return this.slice$extension($this, lo, Array.getLength($this));
   }

   public final Iterator iterator$extension(final Object $this) {
      if ($this instanceof Object[]) {
         Object[] var2 = $this;
         return new ArrayOps.ArrayIterator(var2);
      } else if ($this instanceof int[]) {
         int[] var3 = (int[])$this;
         return new ArrayOps$ArrayIterator$mcI$sp(var3);
      } else if ($this instanceof double[]) {
         double[] var4 = (double[])$this;
         return new ArrayOps$ArrayIterator$mcD$sp(var4);
      } else if ($this instanceof long[]) {
         long[] var5 = (long[])$this;
         return new ArrayOps$ArrayIterator$mcJ$sp(var5);
      } else if ($this instanceof float[]) {
         float[] var6 = (float[])$this;
         return new ArrayOps$ArrayIterator$mcF$sp(var6);
      } else if ($this instanceof char[]) {
         char[] var7 = (char[])$this;
         return new ArrayOps$ArrayIterator$mcC$sp(var7);
      } else if ($this instanceof byte[]) {
         byte[] var8 = (byte[])$this;
         return new ArrayOps$ArrayIterator$mcB$sp(var8);
      } else if ($this instanceof short[]) {
         short[] var9 = (short[])$this;
         return new ArrayOps$ArrayIterator$mcS$sp(var9);
      } else if ($this instanceof boolean[]) {
         boolean[] var10 = (boolean[])$this;
         return new ArrayOps$ArrayIterator$mcZ$sp(var10);
      } else if ($this instanceof BoxedUnit[]) {
         BoxedUnit[] var11 = (BoxedUnit[])$this;
         return new ArrayOps$ArrayIterator$mcV$sp(var11);
      } else if ($this == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError($this);
      }
   }

   public final Stepper stepper$extension(final Object $this, final StepperShape shape) {
      int var3 = shape.shape();
      Object var10000;
      if (StepperShape$.MODULE$.ReferenceShape() == var3) {
         if ($this instanceof boolean[]) {
            boolean[] var4 = (boolean[])$this;
            var10000 = new BoxedBooleanArrayStepper(var4, 0, Array.getLength($this));
         } else {
            var10000 = new ObjectArrayStepper($this, 0, Array.getLength($this));
         }
      } else if (StepperShape$.MODULE$.IntShape() == var3) {
         var10000 = new IntArrayStepper((int[])$this, 0, Array.getLength($this));
      } else if (StepperShape$.MODULE$.LongShape() == var3) {
         var10000 = new LongArrayStepper((long[])$this, 0, Array.getLength($this));
      } else if (StepperShape$.MODULE$.DoubleShape() == var3) {
         var10000 = new DoubleArrayStepper((double[])$this, 0, Array.getLength($this));
      } else if (StepperShape$.MODULE$.ByteShape() == var3) {
         var10000 = new WidenedByteArrayStepper((byte[])$this, 0, Array.getLength($this));
      } else if (StepperShape$.MODULE$.ShortShape() == var3) {
         var10000 = new WidenedShortArrayStepper((short[])$this, 0, Array.getLength($this));
      } else if (StepperShape$.MODULE$.CharShape() == var3) {
         var10000 = new WidenedCharArrayStepper((char[])$this, 0, Array.getLength($this));
      } else {
         if (StepperShape$.MODULE$.FloatShape() != var3) {
            throw new MatchError(new StepperShape.Shape(var3));
         }

         var10000 = new WidenedFloatArrayStepper((float[])$this, 0, Array.getLength($this));
      }

      return (Stepper)var10000;
   }

   public final Iterator grouped$extension(final Object $this, final int size) {
      return new ArrayOps.GroupedIterator($this, size);
   }

   public final Tuple2 span$extension(final Object $this, final Function1 p) {
      int indexWhere$extension_i = 0;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= Array.getLength($this)) {
            var10000 = -1;
            break;
         }

         Object var6 = ScalaRunTime$.MODULE$.array_apply($this, indexWhere$extension_i);
         if (!BoxesRunTime.unboxToBoolean(p.apply(var6))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int i = var10000;
      int idx = i < 0 ? Array.getLength($this) : i;
      return new Tuple2(this.slice$extension($this, 0, idx), this.slice$extension($this, idx, Array.getLength($this)));
   }

   public final Tuple2 splitAt$extension(final Object $this, final int n) {
      return new Tuple2(this.slice$extension($this, 0, n), this.drop$extension($this, n));
   }

   public final Tuple2 partition$extension(final Object $this, final Function1 p) {
      Object var24;
      label235: {
         label238: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            ClassTag make_evidence$1 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
            Class var8 = make_evidence$1.runtimeClass();
            Class var15 = Byte.TYPE;
            if (var15 == null) {
               if (var8 == null) {
                  break label238;
               }
            } else if (var15.equals(var8)) {
               break label238;
            }

            label239: {
               var15 = Short.TYPE;
               if (var15 == null) {
                  if (var8 == null) {
                     break label239;
                  }
               } else if (var15.equals(var8)) {
                  break label239;
               }

               label240: {
                  var15 = Character.TYPE;
                  if (var15 == null) {
                     if (var8 == null) {
                        break label240;
                     }
                  } else if (var15.equals(var8)) {
                     break label240;
                  }

                  label241: {
                     var15 = Integer.TYPE;
                     if (var15 == null) {
                        if (var8 == null) {
                           break label241;
                        }
                     } else if (var15.equals(var8)) {
                        break label241;
                     }

                     label242: {
                        var15 = Long.TYPE;
                        if (var15 == null) {
                           if (var8 == null) {
                              break label242;
                           }
                        } else if (var15.equals(var8)) {
                           break label242;
                        }

                        label243: {
                           var15 = Float.TYPE;
                           if (var15 == null) {
                              if (var8 == null) {
                                 break label243;
                              }
                           } else if (var15.equals(var8)) {
                              break label243;
                           }

                           label244: {
                              var15 = Double.TYPE;
                              if (var15 == null) {
                                 if (var8 == null) {
                                    break label244;
                                 }
                              } else if (var15.equals(var8)) {
                                 break label244;
                              }

                              label245: {
                                 var15 = Boolean.TYPE;
                                 if (var15 == null) {
                                    if (var8 == null) {
                                       break label245;
                                    }
                                 } else if (var15.equals(var8)) {
                                    break label245;
                                 }

                                 label178: {
                                    var15 = Void.TYPE;
                                    if (var15 == null) {
                                       if (var8 == null) {
                                          break label178;
                                       }
                                    } else if (var15.equals(var8)) {
                                       break label178;
                                    }

                                    var24 = new ArrayBuilder.ofRef(make_evidence$1);
                                    break label235;
                                 }

                                 var24 = new ArrayBuilder.ofUnit();
                                 break label235;
                              }

                              var24 = new ArrayBuilder.ofBoolean();
                              break label235;
                           }

                           var24 = new ArrayBuilder.ofDouble();
                           break label235;
                        }

                        var24 = new ArrayBuilder.ofFloat();
                        break label235;
                     }

                     var24 = new ArrayBuilder.ofLong();
                     break label235;
                  }

                  var24 = new ArrayBuilder.ofInt();
                  break label235;
               }

               var24 = new ArrayBuilder.ofChar();
               break label235;
            }

            var24 = new ArrayBuilder.ofShort();
            break label235;
         }

         var24 = new ArrayBuilder.ofByte();
      }

      ArrayBuilder res1;
      label171: {
         label246: {
            Object var11 = null;
            Object var12 = null;
            res1 = (ArrayBuilder)var24;
            ArrayBuilder$ var25 = ArrayBuilder$.MODULE$;
            ClassTag make_evidence$1 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
            Class var10 = make_evidence$1.runtimeClass();
            Class var26 = Byte.TYPE;
            if (var26 == null) {
               if (var10 == null) {
                  break label246;
               }
            } else if (var26.equals(var10)) {
               break label246;
            }

            label247: {
               var26 = Short.TYPE;
               if (var26 == null) {
                  if (var10 == null) {
                     break label247;
                  }
               } else if (var26.equals(var10)) {
                  break label247;
               }

               label248: {
                  var26 = Character.TYPE;
                  if (var26 == null) {
                     if (var10 == null) {
                        break label248;
                     }
                  } else if (var26.equals(var10)) {
                     break label248;
                  }

                  label249: {
                     var26 = Integer.TYPE;
                     if (var26 == null) {
                        if (var10 == null) {
                           break label249;
                        }
                     } else if (var26.equals(var10)) {
                        break label249;
                     }

                     label250: {
                        var26 = Long.TYPE;
                        if (var26 == null) {
                           if (var10 == null) {
                              break label250;
                           }
                        } else if (var26.equals(var10)) {
                           break label250;
                        }

                        label251: {
                           var26 = Float.TYPE;
                           if (var26 == null) {
                              if (var10 == null) {
                                 break label251;
                              }
                           } else if (var26.equals(var10)) {
                              break label251;
                           }

                           label252: {
                              var26 = Double.TYPE;
                              if (var26 == null) {
                                 if (var10 == null) {
                                    break label252;
                                 }
                              } else if (var26.equals(var10)) {
                                 break label252;
                              }

                              label253: {
                                 var26 = Boolean.TYPE;
                                 if (var26 == null) {
                                    if (var10 == null) {
                                       break label253;
                                    }
                                 } else if (var26.equals(var10)) {
                                    break label253;
                                 }

                                 label114: {
                                    var26 = Void.TYPE;
                                    if (var26 == null) {
                                       if (var10 == null) {
                                          break label114;
                                       }
                                    } else if (var26.equals(var10)) {
                                       break label114;
                                    }

                                    var24 = new ArrayBuilder.ofRef(make_evidence$1);
                                    break label171;
                                 }

                                 var24 = new ArrayBuilder.ofUnit();
                                 break label171;
                              }

                              var24 = new ArrayBuilder.ofBoolean();
                              break label171;
                           }

                           var24 = new ArrayBuilder.ofDouble();
                           break label171;
                        }

                        var24 = new ArrayBuilder.ofFloat();
                        break label171;
                     }

                     var24 = new ArrayBuilder.ofLong();
                     break label171;
                  }

                  var24 = new ArrayBuilder.ofInt();
                  break label171;
               }

               var24 = new ArrayBuilder.ofChar();
               break label171;
            }

            var24 = new ArrayBuilder.ofShort();
            break label171;
         }

         var24 = new ArrayBuilder.ofByte();
      }

      Object var13 = null;
      Object var14 = null;
      ArrayBuilder res2 = (ArrayBuilder)var24;

      for(int i = 0; i < Array.getLength($this); ++i) {
         Object x = ScalaRunTime$.MODULE$.array_apply($this, i);
         (BoxesRunTime.unboxToBoolean(p.apply(x)) ? res1 : res2).addOne(x);
      }

      return new Tuple2(res1.result(), res2.result());
   }

   public final Tuple2 partitionMap$extension(final Object $this, final Function1 f, final ClassTag evidence$3, final ClassTag evidence$4) {
      Object var24;
      label239: {
         label242: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var11 = evidence$3.runtimeClass();
            Class var15 = Byte.TYPE;
            if (var15 == null) {
               if (var11 == null) {
                  break label242;
               }
            } else if (var15.equals(var11)) {
               break label242;
            }

            label243: {
               var15 = Short.TYPE;
               if (var15 == null) {
                  if (var11 == null) {
                     break label243;
                  }
               } else if (var15.equals(var11)) {
                  break label243;
               }

               label244: {
                  var15 = Character.TYPE;
                  if (var15 == null) {
                     if (var11 == null) {
                        break label244;
                     }
                  } else if (var15.equals(var11)) {
                     break label244;
                  }

                  label245: {
                     var15 = Integer.TYPE;
                     if (var15 == null) {
                        if (var11 == null) {
                           break label245;
                        }
                     } else if (var15.equals(var11)) {
                        break label245;
                     }

                     label246: {
                        var15 = Long.TYPE;
                        if (var15 == null) {
                           if (var11 == null) {
                              break label246;
                           }
                        } else if (var15.equals(var11)) {
                           break label246;
                        }

                        label247: {
                           var15 = Float.TYPE;
                           if (var15 == null) {
                              if (var11 == null) {
                                 break label247;
                              }
                           } else if (var15.equals(var11)) {
                              break label247;
                           }

                           label248: {
                              var15 = Double.TYPE;
                              if (var15 == null) {
                                 if (var11 == null) {
                                    break label248;
                                 }
                              } else if (var15.equals(var11)) {
                                 break label248;
                              }

                              label249: {
                                 var15 = Boolean.TYPE;
                                 if (var15 == null) {
                                    if (var11 == null) {
                                       break label249;
                                    }
                                 } else if (var15.equals(var11)) {
                                    break label249;
                                 }

                                 label182: {
                                    var15 = Void.TYPE;
                                    if (var15 == null) {
                                       if (var11 == null) {
                                          break label182;
                                       }
                                    } else if (var15.equals(var11)) {
                                       break label182;
                                    }

                                    var24 = new ArrayBuilder.ofRef(evidence$3);
                                    break label239;
                                 }

                                 var24 = new ArrayBuilder.ofUnit();
                                 break label239;
                              }

                              var24 = new ArrayBuilder.ofBoolean();
                              break label239;
                           }

                           var24 = new ArrayBuilder.ofDouble();
                           break label239;
                        }

                        var24 = new ArrayBuilder.ofFloat();
                        break label239;
                     }

                     var24 = new ArrayBuilder.ofLong();
                     break label239;
                  }

                  var24 = new ArrayBuilder.ofInt();
                  break label239;
               }

               var24 = new ArrayBuilder.ofChar();
               break label239;
            }

            var24 = new ArrayBuilder.ofShort();
            break label239;
         }

         var24 = new ArrayBuilder.ofByte();
      }

      ArrayBuilder res1;
      label175: {
         label250: {
            Object var13 = null;
            res1 = (ArrayBuilder)var24;
            ArrayBuilder$ var25 = ArrayBuilder$.MODULE$;
            Class var12 = evidence$4.runtimeClass();
            Class var26 = Byte.TYPE;
            if (var26 == null) {
               if (var12 == null) {
                  break label250;
               }
            } else if (var26.equals(var12)) {
               break label250;
            }

            label251: {
               var26 = Short.TYPE;
               if (var26 == null) {
                  if (var12 == null) {
                     break label251;
                  }
               } else if (var26.equals(var12)) {
                  break label251;
               }

               label252: {
                  var26 = Character.TYPE;
                  if (var26 == null) {
                     if (var12 == null) {
                        break label252;
                     }
                  } else if (var26.equals(var12)) {
                     break label252;
                  }

                  label253: {
                     var26 = Integer.TYPE;
                     if (var26 == null) {
                        if (var12 == null) {
                           break label253;
                        }
                     } else if (var26.equals(var12)) {
                        break label253;
                     }

                     label254: {
                        var26 = Long.TYPE;
                        if (var26 == null) {
                           if (var12 == null) {
                              break label254;
                           }
                        } else if (var26.equals(var12)) {
                           break label254;
                        }

                        label255: {
                           var26 = Float.TYPE;
                           if (var26 == null) {
                              if (var12 == null) {
                                 break label255;
                              }
                           } else if (var26.equals(var12)) {
                              break label255;
                           }

                           label256: {
                              var26 = Double.TYPE;
                              if (var26 == null) {
                                 if (var12 == null) {
                                    break label256;
                                 }
                              } else if (var26.equals(var12)) {
                                 break label256;
                              }

                              label257: {
                                 var26 = Boolean.TYPE;
                                 if (var26 == null) {
                                    if (var12 == null) {
                                       break label257;
                                    }
                                 } else if (var26.equals(var12)) {
                                    break label257;
                                 }

                                 label118: {
                                    var26 = Void.TYPE;
                                    if (var26 == null) {
                                       if (var12 == null) {
                                          break label118;
                                       }
                                    } else if (var26.equals(var12)) {
                                       break label118;
                                    }

                                    var24 = new ArrayBuilder.ofRef(evidence$4);
                                    break label175;
                                 }

                                 var24 = new ArrayBuilder.ofUnit();
                                 break label175;
                              }

                              var24 = new ArrayBuilder.ofBoolean();
                              break label175;
                           }

                           var24 = new ArrayBuilder.ofDouble();
                           break label175;
                        }

                        var24 = new ArrayBuilder.ofFloat();
                        break label175;
                     }

                     var24 = new ArrayBuilder.ofLong();
                     break label175;
                  }

                  var24 = new ArrayBuilder.ofInt();
                  break label175;
               }

               var24 = new ArrayBuilder.ofChar();
               break label175;
            }

            var24 = new ArrayBuilder.ofShort();
            break label175;
         }

         var24 = new ArrayBuilder.ofByte();
      }

      Object var14 = null;
      ArrayBuilder res2 = (ArrayBuilder)var24;

      for(int i = 0; i < Array.getLength($this); ++i) {
         Either var8 = (Either)f.apply(ScalaRunTime$.MODULE$.array_apply($this, i));
         if (var8 instanceof Left) {
            Object x = ((Left)var8).value();
            ArrayBuilder var36 = (ArrayBuilder)res1.addOne(x);
         } else {
            if (!(var8 instanceof Right)) {
               throw new MatchError(var8);
            }

            Object x = ((Right)var8).value();
            ArrayBuilder var37 = (ArrayBuilder)res2.addOne(x);
         }
      }

      return new Tuple2(res1.result(), res2.result());
   }

   public final Object reverse$extension(final Object $this) {
      int len = Array.getLength($this);
      Object res = ClassTag$.MODULE$.apply($this.getClass().getComponentType()).newArray(len);

      for(int i = 0; i < len; ++i) {
         ScalaRunTime$.MODULE$.array_update(res, len - i - 1, ScalaRunTime$.MODULE$.array_apply($this, i));
      }

      return res;
   }

   public final Iterator reverseIterator$extension(final Object $this) {
      if ($this instanceof Object[]) {
         Object[] var2 = $this;
         return new ArrayOps.ReverseIterator(var2);
      } else if ($this instanceof int[]) {
         int[] var3 = (int[])$this;
         return new ArrayOps$ReverseIterator$mcI$sp(var3);
      } else if ($this instanceof double[]) {
         double[] var4 = (double[])$this;
         return new ArrayOps$ReverseIterator$mcD$sp(var4);
      } else if ($this instanceof long[]) {
         long[] var5 = (long[])$this;
         return new ArrayOps$ReverseIterator$mcJ$sp(var5);
      } else if ($this instanceof float[]) {
         float[] var6 = (float[])$this;
         return new ArrayOps$ReverseIterator$mcF$sp(var6);
      } else if ($this instanceof char[]) {
         char[] var7 = (char[])$this;
         return new ArrayOps$ReverseIterator$mcC$sp(var7);
      } else if ($this instanceof byte[]) {
         byte[] var8 = (byte[])$this;
         return new ArrayOps$ReverseIterator$mcB$sp(var8);
      } else if ($this instanceof short[]) {
         short[] var9 = (short[])$this;
         return new ArrayOps$ReverseIterator$mcS$sp(var9);
      } else if ($this instanceof boolean[]) {
         boolean[] var10 = (boolean[])$this;
         return new ArrayOps$ReverseIterator$mcZ$sp(var10);
      } else if ($this instanceof BoxedUnit[]) {
         BoxedUnit[] var11 = (BoxedUnit[])$this;
         return new ArrayOps$ReverseIterator$mcV$sp(var11);
      } else if ($this == null) {
         throw new NullPointerException();
      } else {
         throw new MatchError($this);
      }
   }

   public final Object filter$extension(final Object $this, final Function1 p) {
      Object var19;
      label124: {
         label127: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            ClassTag make_evidence$1 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
            Class var7 = make_evidence$1.runtimeClass();
            Class var10 = Byte.TYPE;
            if (var10 == null) {
               if (var7 == null) {
                  break label127;
               }
            } else if (var10.equals(var7)) {
               break label127;
            }

            label128: {
               var10 = Short.TYPE;
               if (var10 == null) {
                  if (var7 == null) {
                     break label128;
                  }
               } else if (var10.equals(var7)) {
                  break label128;
               }

               label129: {
                  var10 = Character.TYPE;
                  if (var10 == null) {
                     if (var7 == null) {
                        break label129;
                     }
                  } else if (var10.equals(var7)) {
                     break label129;
                  }

                  label130: {
                     var10 = Integer.TYPE;
                     if (var10 == null) {
                        if (var7 == null) {
                           break label130;
                        }
                     } else if (var10.equals(var7)) {
                        break label130;
                     }

                     label131: {
                        var10 = Long.TYPE;
                        if (var10 == null) {
                           if (var7 == null) {
                              break label131;
                           }
                        } else if (var10.equals(var7)) {
                           break label131;
                        }

                        label132: {
                           var10 = Float.TYPE;
                           if (var10 == null) {
                              if (var7 == null) {
                                 break label132;
                              }
                           } else if (var10.equals(var7)) {
                              break label132;
                           }

                           label133: {
                              var10 = Double.TYPE;
                              if (var10 == null) {
                                 if (var7 == null) {
                                    break label133;
                                 }
                              } else if (var10.equals(var7)) {
                                 break label133;
                              }

                              label134: {
                                 var10 = Boolean.TYPE;
                                 if (var10 == null) {
                                    if (var7 == null) {
                                       break label134;
                                    }
                                 } else if (var10.equals(var7)) {
                                    break label134;
                                 }

                                 label67: {
                                    var10 = Void.TYPE;
                                    if (var10 == null) {
                                       if (var7 == null) {
                                          break label67;
                                       }
                                    } else if (var10.equals(var7)) {
                                       break label67;
                                    }

                                    var19 = new ArrayBuilder.ofRef(make_evidence$1);
                                    break label124;
                                 }

                                 var19 = new ArrayBuilder.ofUnit();
                                 break label124;
                              }

                              var19 = new ArrayBuilder.ofBoolean();
                              break label124;
                           }

                           var19 = new ArrayBuilder.ofDouble();
                           break label124;
                        }

                        var19 = new ArrayBuilder.ofFloat();
                        break label124;
                     }

                     var19 = new ArrayBuilder.ofLong();
                     break label124;
                  }

                  var19 = new ArrayBuilder.ofInt();
                  break label124;
               }

               var19 = new ArrayBuilder.ofChar();
               break label124;
            }

            var19 = new ArrayBuilder.ofShort();
            break label124;
         }

         var19 = new ArrayBuilder.ofByte();
      }

      Object var8 = null;
      Object var9 = null;
      ArrayBuilder res = (ArrayBuilder)var19;

      for(int i = 0; i < Array.getLength($this); ++i) {
         Object x = ScalaRunTime$.MODULE$.array_apply($this, i);
         if (BoxesRunTime.unboxToBoolean(p.apply(x))) {
            res.addOne(x);
         }
      }

      return res.result();
   }

   public final Object filterNot$extension(final Object $this, final Function1 p) {
      Object var19;
      label129: {
         label132: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            ClassTag filter$extension_make_evidence$1 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
            Class var7 = filter$extension_make_evidence$1.runtimeClass();
            Class var10 = Byte.TYPE;
            if (var10 == null) {
               if (var7 == null) {
                  break label132;
               }
            } else if (var10.equals(var7)) {
               break label132;
            }

            label133: {
               var10 = Short.TYPE;
               if (var10 == null) {
                  if (var7 == null) {
                     break label133;
                  }
               } else if (var10.equals(var7)) {
                  break label133;
               }

               label134: {
                  var10 = Character.TYPE;
                  if (var10 == null) {
                     if (var7 == null) {
                        break label134;
                     }
                  } else if (var10.equals(var7)) {
                     break label134;
                  }

                  label135: {
                     var10 = Integer.TYPE;
                     if (var10 == null) {
                        if (var7 == null) {
                           break label135;
                        }
                     } else if (var10.equals(var7)) {
                        break label135;
                     }

                     label136: {
                        var10 = Long.TYPE;
                        if (var10 == null) {
                           if (var7 == null) {
                              break label136;
                           }
                        } else if (var10.equals(var7)) {
                           break label136;
                        }

                        label137: {
                           var10 = Float.TYPE;
                           if (var10 == null) {
                              if (var7 == null) {
                                 break label137;
                              }
                           } else if (var10.equals(var7)) {
                              break label137;
                           }

                           label138: {
                              var10 = Double.TYPE;
                              if (var10 == null) {
                                 if (var7 == null) {
                                    break label138;
                                 }
                              } else if (var10.equals(var7)) {
                                 break label138;
                              }

                              label139: {
                                 var10 = Boolean.TYPE;
                                 if (var10 == null) {
                                    if (var7 == null) {
                                       break label139;
                                    }
                                 } else if (var10.equals(var7)) {
                                    break label139;
                                 }

                                 label72: {
                                    var10 = Void.TYPE;
                                    if (var10 == null) {
                                       if (var7 == null) {
                                          break label72;
                                       }
                                    } else if (var10.equals(var7)) {
                                       break label72;
                                    }

                                    var19 = new ArrayBuilder.ofRef(filter$extension_make_evidence$1);
                                    break label129;
                                 }

                                 var19 = new ArrayBuilder.ofUnit();
                                 break label129;
                              }

                              var19 = new ArrayBuilder.ofBoolean();
                              break label129;
                           }

                           var19 = new ArrayBuilder.ofDouble();
                           break label129;
                        }

                        var19 = new ArrayBuilder.ofFloat();
                        break label129;
                     }

                     var19 = new ArrayBuilder.ofLong();
                     break label129;
                  }

                  var19 = new ArrayBuilder.ofInt();
                  break label129;
               }

               var19 = new ArrayBuilder.ofChar();
               break label129;
            }

            var19 = new ArrayBuilder.ofShort();
            break label129;
         }

         var19 = new ArrayBuilder.ofByte();
      }

      Object var8 = null;
      Object var9 = null;
      ArrayBuilder filter$extension_res = (ArrayBuilder)var19;

      for(int filter$extension_i = 0; filter$extension_i < Array.getLength($this); ++filter$extension_i) {
         Object filter$extension_x = ScalaRunTime$.MODULE$.array_apply($this, filter$extension_i);
         if (!BoxesRunTime.unboxToBoolean(p.apply(filter$extension_x))) {
            filter$extension_res.addOne(filter$extension_x);
         }
      }

      return filter$extension_res.result();
   }

   public final Object sorted$extension(final Object $this, final Ordering ord) {
      int len = Array.getLength($this);
      if (len <= 1) {
         return ScalaRunTime$.MODULE$.array_clone($this);
      } else if ($this instanceof Object[]) {
         Object[] a = Arrays.copyOf($this, len);
         Arrays.sort(a, ord);
         return a;
      } else if ($this instanceof int[]) {
         int[] var5 = (int[])$this;
         if (ord == Ordering.Int$.MODULE$) {
            int[] a = Arrays.copyOf(var5, len);
            Arrays.sort(a);
            return a;
         } else {
            return boxed$1(len, $this, ord);
         }
      } else if ($this instanceof long[]) {
         long[] var7 = (long[])$this;
         if (ord == Ordering.Long$.MODULE$) {
            long[] a = Arrays.copyOf(var7, len);
            Arrays.sort(a);
            return a;
         } else {
            return boxed$1(len, $this, ord);
         }
      } else if ($this instanceof char[]) {
         char[] var9 = (char[])$this;
         if (ord == Ordering.Char$.MODULE$) {
            char[] a = Arrays.copyOf(var9, len);
            Arrays.sort(a);
            return a;
         } else {
            return boxed$1(len, $this, ord);
         }
      } else if ($this instanceof byte[]) {
         byte[] var11 = (byte[])$this;
         if (ord == Ordering.Byte$.MODULE$) {
            byte[] a = Arrays.copyOf(var11, len);
            Arrays.sort(a);
            return a;
         } else {
            return boxed$1(len, $this, ord);
         }
      } else if ($this instanceof short[]) {
         short[] var13 = (short[])$this;
         if (ord == Ordering.Short$.MODULE$) {
            short[] a = Arrays.copyOf(var13, len);
            Arrays.sort(a);
            return a;
         } else {
            return boxed$1(len, $this, ord);
         }
      } else if ($this instanceof boolean[]) {
         boolean[] var15 = (boolean[])$this;
         if (ord == Ordering.Boolean$.MODULE$) {
            boolean[] a = Arrays.copyOf(var15, len);
            Ordering stableSort_evidence$3 = Ordering.Boolean$.MODULE$;
            Sorting$.MODULE$.stableSort(a, 0, a.length, stableSort_evidence$3);
            return a;
         } else {
            return boxed$1(len, $this, ord);
         }
      } else {
         return boxed$1(len, $this, ord);
      }
   }

   public final Object sortWith$extension(final Object $this, final Function2 lt) {
      Ordering$ var10002 = Ordering$.MODULE$;
      return this.sorted$extension($this, new Ordering(lt) {
         private final Function2 cmp$2;

         public Some tryCompare(final Object x, final Object y) {
            return Ordering.tryCompare$(this, x, y);
         }

         public boolean equiv(final Object x, final Object y) {
            return Ordering.equiv$(this, x, y);
         }

         public Object max(final Object x, final Object y) {
            return Ordering.max$(this, x, y);
         }

         public Object min(final Object x, final Object y) {
            return Ordering.min$(this, x, y);
         }

         public Ordering reverse() {
            return Ordering.reverse$(this);
         }

         public boolean isReverseOf(final Ordering other) {
            return Ordering.isReverseOf$(this, other);
         }

         public Ordering on(final Function1 f) {
            return Ordering.on$(this, f);
         }

         public Ordering orElse(final Ordering other) {
            return Ordering.orElse$(this, other);
         }

         public Ordering orElseBy(final Function1 f, final Ordering ord) {
            return Ordering.orElseBy$(this, f, ord);
         }

         public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
            return Ordering.mkOrderingOps$(this, lhs);
         }

         public int compare(final Object x, final Object y) {
            if (BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y))) {
               return -1;
            } else {
               return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x)) ? 1 : 0;
            }
         }

         public boolean lt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean gt(final Object x, final Object y) {
            return BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public boolean gteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(x, y));
         }

         public boolean lteq(final Object x, final Object y) {
            return !BoxesRunTime.unboxToBoolean(this.cmp$2.apply(y, x));
         }

         public {
            this.cmp$2 = cmp$2;
         }
      });
   }

   public final Object sortBy$extension(final Object $this, final Function1 f, final Ordering ord) {
      return this.sorted$extension($this, ord.on(f));
   }

   public final ArrayOps.WithFilter withFilter$extension(final Object $this, final Function1 p) {
      return new ArrayOps.WithFilter(p, $this);
   }

   public final int indexOf$extension(final Object $this, final Object elem, final int from) {
      for(int i = from; i < Array.getLength($this); ++i) {
         if (BoxesRunTime.equals(elem, ScalaRunTime$.MODULE$.array_apply($this, i))) {
            return i;
         }
      }

      return -1;
   }

   public final int indexOf$default$2$extension(final Object $this) {
      return 0;
   }

   public final int indexWhere$extension(final Object $this, final Function1 p, final int from) {
      for(int i = from; i < Array.getLength($this); ++i) {
         if (BoxesRunTime.unboxToBoolean(p.apply(ScalaRunTime$.MODULE$.array_apply($this, i)))) {
            return i;
         }
      }

      return -1;
   }

   public final int indexWhere$default$2$extension(final Object $this) {
      return 0;
   }

   public final int lastIndexOf$extension(final Object $this, final Object elem, final int end) {
      for(int i = Math.min(end, Array.getLength($this) - 1); i >= 0; --i) {
         if (BoxesRunTime.equals(elem, ScalaRunTime$.MODULE$.array_apply($this, i))) {
            return i;
         }
      }

      return -1;
   }

   public final int lastIndexOf$default$2$extension(final Object $this) {
      return Array.getLength($this) - 1;
   }

   public final int lastIndexWhere$extension(final Object $this, final Function1 p, final int end) {
      for(int i = Math.min(end, Array.getLength($this) - 1); i >= 0; --i) {
         if (BoxesRunTime.unboxToBoolean(p.apply(ScalaRunTime$.MODULE$.array_apply($this, i)))) {
            return i;
         }
      }

      return -1;
   }

   public final int lastIndexWhere$default$2$extension(final Object $this) {
      return Array.getLength($this) - 1;
   }

   public final Option find$extension(final Object $this, final Function1 p) {
      int indexWhere$extension_i = 0;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= Array.getLength($this)) {
            var10000 = -1;
            break;
         }

         if (BoxesRunTime.unboxToBoolean(p.apply(ScalaRunTime$.MODULE$.array_apply($this, indexWhere$extension_i)))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      int idx = var10000;
      return (Option)(idx == -1 ? None$.MODULE$ : new Some(ScalaRunTime$.MODULE$.array_apply($this, idx)));
   }

   public final boolean exists$extension(final Object $this, final Function1 p) {
      int indexWhere$extension_i = 0;

      int var10000;
      while(true) {
         if (indexWhere$extension_i >= Array.getLength($this)) {
            var10000 = -1;
            break;
         }

         if (BoxesRunTime.unboxToBoolean(p.apply(ScalaRunTime$.MODULE$.array_apply($this, indexWhere$extension_i)))) {
            var10000 = indexWhere$extension_i;
            break;
         }

         ++indexWhere$extension_i;
      }

      return var10000 >= 0;
   }

   public final boolean forall$extension(final Object $this, final Function1 p) {
      for(int i = 0; i < Array.getLength($this); ++i) {
         if (!BoxesRunTime.unboxToBoolean(p.apply(ScalaRunTime$.MODULE$.array_apply($this, i)))) {
            return false;
         }
      }

      return true;
   }

   public final Object foldLeft$extension(final Object $this, final Object z, final Function2 op) {
      if ($this == null) {
         throw new NullPointerException();
      } else if ($this instanceof Object[]) {
         Object[] var4 = $this;
         int f$2_length = var4.length;
         Object f$2_v = z;

         for(int f$2_i = 0; f$2_i < f$2_length; ++f$2_i) {
            f$2_v = op.apply(f$2_v, var4[f$2_i]);
         }

         return f$2_v;
      } else if ($this instanceof int[]) {
         int[] var5 = (int[])$this;
         int f$mIc$sp$1_length = var5.length;
         Object f$mIc$sp$1_v = z;

         for(int f$mIc$sp$1_i = 0; f$mIc$sp$1_i < f$mIc$sp$1_length; ++f$mIc$sp$1_i) {
            f$mIc$sp$1_v = op.apply(f$mIc$sp$1_v, var5[f$mIc$sp$1_i]);
         }

         return f$mIc$sp$1_v;
      } else if ($this instanceof double[]) {
         double[] var6 = (double[])$this;
         int f$mDc$sp$1_length = var6.length;
         Object f$mDc$sp$1_v = z;

         for(int f$mDc$sp$1_i = 0; f$mDc$sp$1_i < f$mDc$sp$1_length; ++f$mDc$sp$1_i) {
            f$mDc$sp$1_v = op.apply(f$mDc$sp$1_v, var6[f$mDc$sp$1_i]);
         }

         return f$mDc$sp$1_v;
      } else if ($this instanceof long[]) {
         long[] var7 = (long[])$this;
         int f$mJc$sp$1_length = var7.length;
         Object f$mJc$sp$1_v = z;

         for(int f$mJc$sp$1_i = 0; f$mJc$sp$1_i < f$mJc$sp$1_length; ++f$mJc$sp$1_i) {
            f$mJc$sp$1_v = op.apply(f$mJc$sp$1_v, var7[f$mJc$sp$1_i]);
         }

         return f$mJc$sp$1_v;
      } else if ($this instanceof float[]) {
         float[] var8 = (float[])$this;
         int f$mFc$sp$1_length = var8.length;
         Object f$mFc$sp$1_v = z;

         for(int f$mFc$sp$1_i = 0; f$mFc$sp$1_i < f$mFc$sp$1_length; ++f$mFc$sp$1_i) {
            f$mFc$sp$1_v = op.apply(f$mFc$sp$1_v, var8[f$mFc$sp$1_i]);
         }

         return f$mFc$sp$1_v;
      } else if ($this instanceof char[]) {
         char[] var9 = (char[])$this;
         int f$mCc$sp$1_length = var9.length;
         Object f$mCc$sp$1_v = z;

         for(int f$mCc$sp$1_i = 0; f$mCc$sp$1_i < f$mCc$sp$1_length; ++f$mCc$sp$1_i) {
            f$mCc$sp$1_v = op.apply(f$mCc$sp$1_v, var9[f$mCc$sp$1_i]);
         }

         return f$mCc$sp$1_v;
      } else if ($this instanceof byte[]) {
         byte[] var10 = (byte[])$this;
         int f$mBc$sp$1_length = var10.length;
         Object f$mBc$sp$1_v = z;

         for(int f$mBc$sp$1_i = 0; f$mBc$sp$1_i < f$mBc$sp$1_length; ++f$mBc$sp$1_i) {
            f$mBc$sp$1_v = op.apply(f$mBc$sp$1_v, var10[f$mBc$sp$1_i]);
         }

         return f$mBc$sp$1_v;
      } else if ($this instanceof short[]) {
         short[] var11 = (short[])$this;
         int f$mSc$sp$1_length = var11.length;
         Object f$mSc$sp$1_v = z;

         for(int f$mSc$sp$1_i = 0; f$mSc$sp$1_i < f$mSc$sp$1_length; ++f$mSc$sp$1_i) {
            f$mSc$sp$1_v = op.apply(f$mSc$sp$1_v, var11[f$mSc$sp$1_i]);
         }

         return f$mSc$sp$1_v;
      } else if ($this instanceof boolean[]) {
         boolean[] var12 = (boolean[])$this;
         int f$mZc$sp$1_length = var12.length;
         Object f$mZc$sp$1_v = z;

         for(int f$mZc$sp$1_i = 0; f$mZc$sp$1_i < f$mZc$sp$1_length; ++f$mZc$sp$1_i) {
            f$mZc$sp$1_v = op.apply(f$mZc$sp$1_v, var12[f$mZc$sp$1_i]);
         }

         return f$mZc$sp$1_v;
      } else if (!($this instanceof BoxedUnit[])) {
         throw new MatchError($this);
      } else {
         BoxedUnit[] var13 = (BoxedUnit[])$this;
         int f$mVc$sp$1_length = var13.length;
         Object f$mVc$sp$1_v = z;

         for(int f$mVc$sp$1_i = 0; f$mVc$sp$1_i < f$mVc$sp$1_length; ++f$mVc$sp$1_i) {
            f$mVc$sp$1_v = op.apply(f$mVc$sp$1_v, var13[f$mVc$sp$1_i]);
         }

         return f$mVc$sp$1_v;
      }
   }

   public final Object scanLeft$extension(final Object $this, final Object z, final Function2 op, final ClassTag evidence$5) {
      Object v = z;
      int i = 0;

      Object res;
      for(res = evidence$5.newArray(Array.getLength($this) + 1); i < Array.getLength($this); ++i) {
         ScalaRunTime$.MODULE$.array_update(res, i, v);
         v = op.apply(v, ScalaRunTime$.MODULE$.array_apply($this, i));
      }

      ScalaRunTime$.MODULE$.array_update(res, i, v);
      return res;
   }

   public final Object scan$extension(final Object $this, final Object z, final Function2 op, final ClassTag evidence$6) {
      Object scanLeft$extension_v = z;
      int scanLeft$extension_i = 0;

      Object scanLeft$extension_res;
      for(scanLeft$extension_res = evidence$6.newArray(Array.getLength($this) + 1); scanLeft$extension_i < Array.getLength($this); ++scanLeft$extension_i) {
         ScalaRunTime$.MODULE$.array_update(scanLeft$extension_res, scanLeft$extension_i, scanLeft$extension_v);
         scanLeft$extension_v = op.apply(scanLeft$extension_v, ScalaRunTime$.MODULE$.array_apply($this, scanLeft$extension_i));
      }

      ScalaRunTime$.MODULE$.array_update(scanLeft$extension_res, scanLeft$extension_i, scanLeft$extension_v);
      return scanLeft$extension_res;
   }

   public final Object scanRight$extension(final Object $this, final Object z, final Function2 op, final ClassTag evidence$7) {
      Object v = z;
      int i = Array.getLength($this) - 1;
      Object res = evidence$7.newArray(Array.getLength($this) + 1);
      ScalaRunTime$.MODULE$.array_update(res, Array.getLength($this), z);

      while(i >= 0) {
         v = op.apply(ScalaRunTime$.MODULE$.array_apply($this, i), v);
         ScalaRunTime$.MODULE$.array_update(res, i, v);
         --i;
      }

      return res;
   }

   public final Object foldRight$extension(final Object $this, final Object z, final Function2 op) {
      if ($this == null) {
         throw new NullPointerException();
      } else if ($this instanceof Object[]) {
         Object[] var4 = $this;
         Object f$3_v = z;

         for(int f$3_i = var4.length - 1; f$3_i >= 0; --f$3_i) {
            f$3_v = op.apply(var4[f$3_i], f$3_v);
         }

         return f$3_v;
      } else if ($this instanceof int[]) {
         int[] var5 = (int[])$this;
         Object f$mIc$sp$2_v = z;

         for(int f$mIc$sp$2_i = var5.length - 1; f$mIc$sp$2_i >= 0; --f$mIc$sp$2_i) {
            f$mIc$sp$2_v = op.apply(var5[f$mIc$sp$2_i], f$mIc$sp$2_v);
         }

         return f$mIc$sp$2_v;
      } else if ($this instanceof double[]) {
         double[] var6 = (double[])$this;
         Object f$mDc$sp$2_v = z;

         for(int f$mDc$sp$2_i = var6.length - 1; f$mDc$sp$2_i >= 0; --f$mDc$sp$2_i) {
            f$mDc$sp$2_v = op.apply(var6[f$mDc$sp$2_i], f$mDc$sp$2_v);
         }

         return f$mDc$sp$2_v;
      } else if ($this instanceof long[]) {
         long[] var7 = (long[])$this;
         Object f$mJc$sp$2_v = z;

         for(int f$mJc$sp$2_i = var7.length - 1; f$mJc$sp$2_i >= 0; --f$mJc$sp$2_i) {
            f$mJc$sp$2_v = op.apply(var7[f$mJc$sp$2_i], f$mJc$sp$2_v);
         }

         return f$mJc$sp$2_v;
      } else if ($this instanceof float[]) {
         float[] var8 = (float[])$this;
         Object f$mFc$sp$2_v = z;

         for(int f$mFc$sp$2_i = var8.length - 1; f$mFc$sp$2_i >= 0; --f$mFc$sp$2_i) {
            f$mFc$sp$2_v = op.apply(var8[f$mFc$sp$2_i], f$mFc$sp$2_v);
         }

         return f$mFc$sp$2_v;
      } else if ($this instanceof char[]) {
         char[] var9 = (char[])$this;
         Object f$mCc$sp$2_v = z;

         for(int f$mCc$sp$2_i = var9.length - 1; f$mCc$sp$2_i >= 0; --f$mCc$sp$2_i) {
            f$mCc$sp$2_v = op.apply(var9[f$mCc$sp$2_i], f$mCc$sp$2_v);
         }

         return f$mCc$sp$2_v;
      } else if ($this instanceof byte[]) {
         byte[] var10 = (byte[])$this;
         Object f$mBc$sp$2_v = z;

         for(int f$mBc$sp$2_i = var10.length - 1; f$mBc$sp$2_i >= 0; --f$mBc$sp$2_i) {
            f$mBc$sp$2_v = op.apply(var10[f$mBc$sp$2_i], f$mBc$sp$2_v);
         }

         return f$mBc$sp$2_v;
      } else if ($this instanceof short[]) {
         short[] var11 = (short[])$this;
         Object f$mSc$sp$2_v = z;

         for(int f$mSc$sp$2_i = var11.length - 1; f$mSc$sp$2_i >= 0; --f$mSc$sp$2_i) {
            f$mSc$sp$2_v = op.apply(var11[f$mSc$sp$2_i], f$mSc$sp$2_v);
         }

         return f$mSc$sp$2_v;
      } else if ($this instanceof boolean[]) {
         boolean[] var12 = (boolean[])$this;
         Object f$mZc$sp$2_v = z;

         for(int f$mZc$sp$2_i = var12.length - 1; f$mZc$sp$2_i >= 0; --f$mZc$sp$2_i) {
            f$mZc$sp$2_v = op.apply(var12[f$mZc$sp$2_i], f$mZc$sp$2_v);
         }

         return f$mZc$sp$2_v;
      } else if (!($this instanceof BoxedUnit[])) {
         throw new MatchError($this);
      } else {
         BoxedUnit[] var13 = (BoxedUnit[])$this;
         Object f$mVc$sp$2_v = z;

         for(int f$mVc$sp$2_i = var13.length - 1; f$mVc$sp$2_i >= 0; --f$mVc$sp$2_i) {
            f$mVc$sp$2_v = op.apply(var13[f$mVc$sp$2_i], f$mVc$sp$2_v);
         }

         return f$mVc$sp$2_v;
      }
   }

   public final Object fold$extension(final Object $this, final Object z, final Function2 op) {
      if ($this == null) {
         throw new NullPointerException();
      } else if ($this instanceof Object[]) {
         Object[] var4 = $this;
         int foldLeft$extension_f$2_length = var4.length;
         Object foldLeft$extension_f$2_v = z;

         for(int foldLeft$extension_f$2_i = 0; foldLeft$extension_f$2_i < foldLeft$extension_f$2_length; ++foldLeft$extension_f$2_i) {
            foldLeft$extension_f$2_v = op.apply(foldLeft$extension_f$2_v, var4[foldLeft$extension_f$2_i]);
         }

         return foldLeft$extension_f$2_v;
      } else if ($this instanceof int[]) {
         int[] var5 = (int[])$this;
         int foldLeft$extension_f$mIc$sp$1_length = var5.length;
         Object foldLeft$extension_f$mIc$sp$1_v = z;

         for(int foldLeft$extension_f$mIc$sp$1_i = 0; foldLeft$extension_f$mIc$sp$1_i < foldLeft$extension_f$mIc$sp$1_length; ++foldLeft$extension_f$mIc$sp$1_i) {
            foldLeft$extension_f$mIc$sp$1_v = op.apply(foldLeft$extension_f$mIc$sp$1_v, var5[foldLeft$extension_f$mIc$sp$1_i]);
         }

         return foldLeft$extension_f$mIc$sp$1_v;
      } else if ($this instanceof double[]) {
         double[] var6 = (double[])$this;
         int foldLeft$extension_f$mDc$sp$1_length = var6.length;
         Object foldLeft$extension_f$mDc$sp$1_v = z;

         for(int foldLeft$extension_f$mDc$sp$1_i = 0; foldLeft$extension_f$mDc$sp$1_i < foldLeft$extension_f$mDc$sp$1_length; ++foldLeft$extension_f$mDc$sp$1_i) {
            foldLeft$extension_f$mDc$sp$1_v = op.apply(foldLeft$extension_f$mDc$sp$1_v, var6[foldLeft$extension_f$mDc$sp$1_i]);
         }

         return foldLeft$extension_f$mDc$sp$1_v;
      } else if ($this instanceof long[]) {
         long[] var7 = (long[])$this;
         int foldLeft$extension_f$mJc$sp$1_length = var7.length;
         Object foldLeft$extension_f$mJc$sp$1_v = z;

         for(int foldLeft$extension_f$mJc$sp$1_i = 0; foldLeft$extension_f$mJc$sp$1_i < foldLeft$extension_f$mJc$sp$1_length; ++foldLeft$extension_f$mJc$sp$1_i) {
            foldLeft$extension_f$mJc$sp$1_v = op.apply(foldLeft$extension_f$mJc$sp$1_v, var7[foldLeft$extension_f$mJc$sp$1_i]);
         }

         return foldLeft$extension_f$mJc$sp$1_v;
      } else if ($this instanceof float[]) {
         float[] var8 = (float[])$this;
         int foldLeft$extension_f$mFc$sp$1_length = var8.length;
         Object foldLeft$extension_f$mFc$sp$1_v = z;

         for(int foldLeft$extension_f$mFc$sp$1_i = 0; foldLeft$extension_f$mFc$sp$1_i < foldLeft$extension_f$mFc$sp$1_length; ++foldLeft$extension_f$mFc$sp$1_i) {
            foldLeft$extension_f$mFc$sp$1_v = op.apply(foldLeft$extension_f$mFc$sp$1_v, var8[foldLeft$extension_f$mFc$sp$1_i]);
         }

         return foldLeft$extension_f$mFc$sp$1_v;
      } else if ($this instanceof char[]) {
         char[] var9 = (char[])$this;
         int foldLeft$extension_f$mCc$sp$1_length = var9.length;
         Object foldLeft$extension_f$mCc$sp$1_v = z;

         for(int foldLeft$extension_f$mCc$sp$1_i = 0; foldLeft$extension_f$mCc$sp$1_i < foldLeft$extension_f$mCc$sp$1_length; ++foldLeft$extension_f$mCc$sp$1_i) {
            foldLeft$extension_f$mCc$sp$1_v = op.apply(foldLeft$extension_f$mCc$sp$1_v, var9[foldLeft$extension_f$mCc$sp$1_i]);
         }

         return foldLeft$extension_f$mCc$sp$1_v;
      } else if ($this instanceof byte[]) {
         byte[] var10 = (byte[])$this;
         int foldLeft$extension_f$mBc$sp$1_length = var10.length;
         Object foldLeft$extension_f$mBc$sp$1_v = z;

         for(int foldLeft$extension_f$mBc$sp$1_i = 0; foldLeft$extension_f$mBc$sp$1_i < foldLeft$extension_f$mBc$sp$1_length; ++foldLeft$extension_f$mBc$sp$1_i) {
            foldLeft$extension_f$mBc$sp$1_v = op.apply(foldLeft$extension_f$mBc$sp$1_v, var10[foldLeft$extension_f$mBc$sp$1_i]);
         }

         return foldLeft$extension_f$mBc$sp$1_v;
      } else if ($this instanceof short[]) {
         short[] var11 = (short[])$this;
         int foldLeft$extension_f$mSc$sp$1_length = var11.length;
         Object foldLeft$extension_f$mSc$sp$1_v = z;

         for(int foldLeft$extension_f$mSc$sp$1_i = 0; foldLeft$extension_f$mSc$sp$1_i < foldLeft$extension_f$mSc$sp$1_length; ++foldLeft$extension_f$mSc$sp$1_i) {
            foldLeft$extension_f$mSc$sp$1_v = op.apply(foldLeft$extension_f$mSc$sp$1_v, var11[foldLeft$extension_f$mSc$sp$1_i]);
         }

         return foldLeft$extension_f$mSc$sp$1_v;
      } else if ($this instanceof boolean[]) {
         boolean[] var12 = (boolean[])$this;
         int foldLeft$extension_f$mZc$sp$1_length = var12.length;
         Object foldLeft$extension_f$mZc$sp$1_v = z;

         for(int foldLeft$extension_f$mZc$sp$1_i = 0; foldLeft$extension_f$mZc$sp$1_i < foldLeft$extension_f$mZc$sp$1_length; ++foldLeft$extension_f$mZc$sp$1_i) {
            foldLeft$extension_f$mZc$sp$1_v = op.apply(foldLeft$extension_f$mZc$sp$1_v, var12[foldLeft$extension_f$mZc$sp$1_i]);
         }

         return foldLeft$extension_f$mZc$sp$1_v;
      } else if (!($this instanceof BoxedUnit[])) {
         throw new MatchError($this);
      } else {
         BoxedUnit[] var13 = (BoxedUnit[])$this;
         int foldLeft$extension_f$mVc$sp$1_length = var13.length;
         Object foldLeft$extension_f$mVc$sp$1_v = z;

         for(int foldLeft$extension_f$mVc$sp$1_i = 0; foldLeft$extension_f$mVc$sp$1_i < foldLeft$extension_f$mVc$sp$1_length; ++foldLeft$extension_f$mVc$sp$1_i) {
            foldLeft$extension_f$mVc$sp$1_v = op.apply(foldLeft$extension_f$mVc$sp$1_v, var13[foldLeft$extension_f$mVc$sp$1_i]);
         }

         return foldLeft$extension_f$mVc$sp$1_v;
      }
   }

   public final Object map$extension(final Object $this, final Function1 f, final ClassTag ct) {
      int len = Array.getLength($this);
      Object ys = ct.newArray(len);
      if (len > 0) {
         int i = 0;
         if ($this instanceof Object[]) {
            for(Object[] var7 = $this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var7[i]));
            }
         } else if ($this instanceof int[]) {
            for(int[] var8 = (int[])$this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var8[i]));
            }
         } else if ($this instanceof double[]) {
            for(double[] var9 = (double[])$this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var9[i]));
            }
         } else if ($this instanceof long[]) {
            for(long[] var10 = (long[])$this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var10[i]));
            }
         } else if ($this instanceof float[]) {
            for(float[] var11 = (float[])$this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var11[i]));
            }
         } else if ($this instanceof char[]) {
            for(char[] var12 = (char[])$this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var12[i]));
            }
         } else if ($this instanceof byte[]) {
            for(byte[] var13 = (byte[])$this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var13[i]));
            }
         } else if ($this instanceof short[]) {
            for(short[] var14 = (short[])$this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var14[i]));
            }
         } else {
            if (!($this instanceof boolean[])) {
               throw new MatchError($this);
            }

            for(boolean[] var15 = (boolean[])$this; i < len; ++i) {
               ScalaRunTime$.MODULE$.array_update(ys, i, f.apply(var15[i]));
            }
         }
      }

      return ys;
   }

   public final Object mapInPlace$extension(final Object $this, final Function1 f) {
      for(int i = 0; i < Array.getLength($this); ++i) {
         ScalaRunTime$.MODULE$.array_update($this, i, f.apply(ScalaRunTime$.MODULE$.array_apply($this, i)));
      }

      return $this;
   }

   public final Object flatMap$extension(final Object $this, final Function1 f, final ClassTag evidence$8) {
      Object var19;
      label122: {
         label125: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var6 = evidence$8.runtimeClass();
            Class var10 = Byte.TYPE;
            if (var10 == null) {
               if (var6 == null) {
                  break label125;
               }
            } else if (var10.equals(var6)) {
               break label125;
            }

            label126: {
               var10 = Short.TYPE;
               if (var10 == null) {
                  if (var6 == null) {
                     break label126;
                  }
               } else if (var10.equals(var6)) {
                  break label126;
               }

               label127: {
                  var10 = Character.TYPE;
                  if (var10 == null) {
                     if (var6 == null) {
                        break label127;
                     }
                  } else if (var10.equals(var6)) {
                     break label127;
                  }

                  label128: {
                     var10 = Integer.TYPE;
                     if (var10 == null) {
                        if (var6 == null) {
                           break label128;
                        }
                     } else if (var10.equals(var6)) {
                        break label128;
                     }

                     label129: {
                        var10 = Long.TYPE;
                        if (var10 == null) {
                           if (var6 == null) {
                              break label129;
                           }
                        } else if (var10.equals(var6)) {
                           break label129;
                        }

                        label130: {
                           var10 = Float.TYPE;
                           if (var10 == null) {
                              if (var6 == null) {
                                 break label130;
                              }
                           } else if (var10.equals(var6)) {
                              break label130;
                           }

                           label131: {
                              var10 = Double.TYPE;
                              if (var10 == null) {
                                 if (var6 == null) {
                                    break label131;
                                 }
                              } else if (var10.equals(var6)) {
                                 break label131;
                              }

                              label132: {
                                 var10 = Boolean.TYPE;
                                 if (var10 == null) {
                                    if (var6 == null) {
                                       break label132;
                                    }
                                 } else if (var10.equals(var6)) {
                                    break label132;
                                 }

                                 label65: {
                                    var10 = Void.TYPE;
                                    if (var10 == null) {
                                       if (var6 == null) {
                                          break label65;
                                       }
                                    } else if (var10.equals(var6)) {
                                       break label65;
                                    }

                                    var19 = new ArrayBuilder.ofRef(evidence$8);
                                    break label122;
                                 }

                                 var19 = new ArrayBuilder.ofUnit();
                                 break label122;
                              }

                              var19 = new ArrayBuilder.ofBoolean();
                              break label122;
                           }

                           var19 = new ArrayBuilder.ofDouble();
                           break label122;
                        }

                        var19 = new ArrayBuilder.ofFloat();
                        break label122;
                     }

                     var19 = new ArrayBuilder.ofLong();
                     break label122;
                  }

                  var19 = new ArrayBuilder.ofInt();
                  break label122;
               }

               var19 = new ArrayBuilder.ofChar();
               break label122;
            }

            var19 = new ArrayBuilder.ofShort();
            break label122;
         }

         var19 = new ArrayBuilder.ofByte();
      }

      Object var8 = null;
      ArrayBuilder b = (ArrayBuilder)var19;

      for(int i = 0; i < Array.getLength($this); ++i) {
         IterableOnce $plus$plus$eq_elems = (IterableOnce)f.apply(ScalaRunTime$.MODULE$.array_apply($this, i));
         b.addAll($plus$plus$eq_elems);
         $plus$plus$eq_elems = null;
      }

      return b.result();
   }

   public final Object flatMap$extension(final Object $this, final Function1 f, final Function1 asIterable, final ClassTag m) {
      Object var21;
      label122: {
         label125: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var7 = m.runtimeClass();
            Class var12 = Byte.TYPE;
            if (var12 == null) {
               if (var7 == null) {
                  break label125;
               }
            } else if (var12.equals(var7)) {
               break label125;
            }

            label126: {
               var12 = Short.TYPE;
               if (var12 == null) {
                  if (var7 == null) {
                     break label126;
                  }
               } else if (var12.equals(var7)) {
                  break label126;
               }

               label127: {
                  var12 = Character.TYPE;
                  if (var12 == null) {
                     if (var7 == null) {
                        break label127;
                     }
                  } else if (var12.equals(var7)) {
                     break label127;
                  }

                  label128: {
                     var12 = Integer.TYPE;
                     if (var12 == null) {
                        if (var7 == null) {
                           break label128;
                        }
                     } else if (var12.equals(var7)) {
                        break label128;
                     }

                     label129: {
                        var12 = Long.TYPE;
                        if (var12 == null) {
                           if (var7 == null) {
                              break label129;
                           }
                        } else if (var12.equals(var7)) {
                           break label129;
                        }

                        label130: {
                           var12 = Float.TYPE;
                           if (var12 == null) {
                              if (var7 == null) {
                                 break label130;
                              }
                           } else if (var12.equals(var7)) {
                              break label130;
                           }

                           label131: {
                              var12 = Double.TYPE;
                              if (var12 == null) {
                                 if (var7 == null) {
                                    break label131;
                                 }
                              } else if (var12.equals(var7)) {
                                 break label131;
                              }

                              label132: {
                                 var12 = Boolean.TYPE;
                                 if (var12 == null) {
                                    if (var7 == null) {
                                       break label132;
                                    }
                                 } else if (var12.equals(var7)) {
                                    break label132;
                                 }

                                 label65: {
                                    var12 = Void.TYPE;
                                    if (var12 == null) {
                                       if (var7 == null) {
                                          break label65;
                                       }
                                    } else if (var12.equals(var7)) {
                                       break label65;
                                    }

                                    var21 = new ArrayBuilder.ofRef(m);
                                    break label122;
                                 }

                                 var21 = new ArrayBuilder.ofUnit();
                                 break label122;
                              }

                              var21 = new ArrayBuilder.ofBoolean();
                              break label122;
                           }

                           var21 = new ArrayBuilder.ofDouble();
                           break label122;
                        }

                        var21 = new ArrayBuilder.ofFloat();
                        break label122;
                     }

                     var21 = new ArrayBuilder.ofLong();
                     break label122;
                  }

                  var21 = new ArrayBuilder.ofInt();
                  break label122;
               }

               var21 = new ArrayBuilder.ofChar();
               break label122;
            }

            var21 = new ArrayBuilder.ofShort();
            break label122;
         }

         var21 = new ArrayBuilder.ofByte();
      }

      Object var10 = null;
      ArrayBuilder flatMap$extension_b = (ArrayBuilder)var21;

      for(int flatMap$extension_i = 0; flatMap$extension_i < Array.getLength($this); ++flatMap$extension_i) {
         Object var9 = ScalaRunTime$.MODULE$.array_apply($this, flatMap$extension_i);
         IterableOnce flatMap$extension_$plus$plus$eq_elems = (Iterable)asIterable.apply(f.apply(var9));
         flatMap$extension_b.addAll(flatMap$extension_$plus$plus$eq_elems);
         flatMap$extension_$plus$plus$eq_elems = null;
      }

      return flatMap$extension_b.result();
   }

   public final Object flatten$extension(final Object $this, final Function1 asIterable, final ClassTag m) {
      Object var24;
      label144: {
         label147: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var10 = m.runtimeClass();
            Class var15 = Byte.TYPE;
            if (var15 == null) {
               if (var10 == null) {
                  break label147;
               }
            } else if (var15.equals(var10)) {
               break label147;
            }

            label148: {
               var15 = Short.TYPE;
               if (var15 == null) {
                  if (var10 == null) {
                     break label148;
                  }
               } else if (var15.equals(var10)) {
                  break label148;
               }

               label149: {
                  var15 = Character.TYPE;
                  if (var15 == null) {
                     if (var10 == null) {
                        break label149;
                     }
                  } else if (var15.equals(var10)) {
                     break label149;
                  }

                  label150: {
                     var15 = Integer.TYPE;
                     if (var15 == null) {
                        if (var10 == null) {
                           break label150;
                        }
                     } else if (var15.equals(var10)) {
                        break label150;
                     }

                     label151: {
                        var15 = Long.TYPE;
                        if (var15 == null) {
                           if (var10 == null) {
                              break label151;
                           }
                        } else if (var15.equals(var10)) {
                           break label151;
                        }

                        label152: {
                           var15 = Float.TYPE;
                           if (var15 == null) {
                              if (var10 == null) {
                                 break label152;
                              }
                           } else if (var15.equals(var10)) {
                              break label152;
                           }

                           label153: {
                              var15 = Double.TYPE;
                              if (var15 == null) {
                                 if (var10 == null) {
                                    break label153;
                                 }
                              } else if (var15.equals(var10)) {
                                 break label153;
                              }

                              label154: {
                                 var15 = Boolean.TYPE;
                                 if (var15 == null) {
                                    if (var10 == null) {
                                       break label154;
                                    }
                                 } else if (var15.equals(var10)) {
                                    break label154;
                                 }

                                 label87: {
                                    var15 = Void.TYPE;
                                    if (var15 == null) {
                                       if (var10 == null) {
                                          break label87;
                                       }
                                    } else if (var15.equals(var10)) {
                                       break label87;
                                    }

                                    var24 = new ArrayBuilder.ofRef(m);
                                    break label144;
                                 }

                                 var24 = new ArrayBuilder.ofUnit();
                                 break label144;
                              }

                              var24 = new ArrayBuilder.ofBoolean();
                              break label144;
                           }

                           var24 = new ArrayBuilder.ofDouble();
                           break label144;
                        }

                        var24 = new ArrayBuilder.ofFloat();
                        break label144;
                     }

                     var24 = new ArrayBuilder.ofLong();
                     break label144;
                  }

                  var24 = new ArrayBuilder.ofInt();
                  break label144;
               }

               var24 = new ArrayBuilder.ofChar();
               break label144;
            }

            var24 = new ArrayBuilder.ofShort();
            break label144;
         }

         var24 = new ArrayBuilder.ofByte();
      }

      Object var13 = null;
      ArrayBuilder b = (ArrayBuilder)var24;
      int len = Array.getLength($this);
      int size = 0;

      for(int i = 0; i < len; ++i) {
         Object var8 = ScalaRunTime$.MODULE$.array_apply($this, i);
         if (var8 instanceof IterableOnce) {
            int k = ((IterableOnce)var8).knownSize();
            if (k > 0) {
               size += k;
            }
         } else if (ScalaRunTime$.MODULE$.isArray(var8, 1)) {
            size += Array.getLength(var8);
         }
      }

      if (size > 0) {
         b.sizeHint(size);
      }

      for(int var12 = 0; var12 < len; ++var12) {
         IterableOnce $plus$plus$eq_elems = (IterableOnce)asIterable.apply(ScalaRunTime$.MODULE$.array_apply($this, var12));
         b.addAll($plus$plus$eq_elems);
         $plus$plus$eq_elems = null;
      }

      return b.result();
   }

   public final Object collect$extension(final Object $this, final PartialFunction pf, final ClassTag evidence$9) {
      Function1 fallback;
      Object var19;
      label124: {
         label127: {
            fallback = this.fallback();
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var8 = evidence$9.runtimeClass();
            Class var10 = Byte.TYPE;
            if (var10 == null) {
               if (var8 == null) {
                  break label127;
               }
            } else if (var10.equals(var8)) {
               break label127;
            }

            label128: {
               var10 = Short.TYPE;
               if (var10 == null) {
                  if (var8 == null) {
                     break label128;
                  }
               } else if (var10.equals(var8)) {
                  break label128;
               }

               label129: {
                  var10 = Character.TYPE;
                  if (var10 == null) {
                     if (var8 == null) {
                        break label129;
                     }
                  } else if (var10.equals(var8)) {
                     break label129;
                  }

                  label130: {
                     var10 = Integer.TYPE;
                     if (var10 == null) {
                        if (var8 == null) {
                           break label130;
                        }
                     } else if (var10.equals(var8)) {
                        break label130;
                     }

                     label131: {
                        var10 = Long.TYPE;
                        if (var10 == null) {
                           if (var8 == null) {
                              break label131;
                           }
                        } else if (var10.equals(var8)) {
                           break label131;
                        }

                        label132: {
                           var10 = Float.TYPE;
                           if (var10 == null) {
                              if (var8 == null) {
                                 break label132;
                              }
                           } else if (var10.equals(var8)) {
                              break label132;
                           }

                           label133: {
                              var10 = Double.TYPE;
                              if (var10 == null) {
                                 if (var8 == null) {
                                    break label133;
                                 }
                              } else if (var10.equals(var8)) {
                                 break label133;
                              }

                              label134: {
                                 var10 = Boolean.TYPE;
                                 if (var10 == null) {
                                    if (var8 == null) {
                                       break label134;
                                    }
                                 } else if (var10.equals(var8)) {
                                    break label134;
                                 }

                                 label67: {
                                    var10 = Void.TYPE;
                                    if (var10 == null) {
                                       if (var8 == null) {
                                          break label67;
                                       }
                                    } else if (var10.equals(var8)) {
                                       break label67;
                                    }

                                    var19 = new ArrayBuilder.ofRef(evidence$9);
                                    break label124;
                                 }

                                 var19 = new ArrayBuilder.ofUnit();
                                 break label124;
                              }

                              var19 = new ArrayBuilder.ofBoolean();
                              break label124;
                           }

                           var19 = new ArrayBuilder.ofDouble();
                           break label124;
                        }

                        var19 = new ArrayBuilder.ofFloat();
                        break label124;
                     }

                     var19 = new ArrayBuilder.ofLong();
                     break label124;
                  }

                  var19 = new ArrayBuilder.ofInt();
                  break label124;
               }

               var19 = new ArrayBuilder.ofChar();
               break label124;
            }

            var19 = new ArrayBuilder.ofShort();
            break label124;
         }

         var19 = new ArrayBuilder.ofByte();
      }

      Object var9 = null;
      ArrayBuilder b = (ArrayBuilder)var19;

      for(int i = 0; i < Array.getLength($this); ++i) {
         Object v = pf.applyOrElse(ScalaRunTime$.MODULE$.array_apply($this, i), fallback);
         if (v != fallback) {
            b.addOne(v);
         }
      }

      return b.result();
   }

   public final Option collectFirst$extension(final Object $this, final PartialFunction pf) {
      Function1 fallback = this.fallback();

      for(int i = 0; i < Array.getLength($this); ++i) {
         Object v = pf.applyOrElse(ScalaRunTime$.MODULE$.array_apply($this, i), fallback);
         if (v != fallback) {
            return new Some(v);
         }
      }

      return None$.MODULE$;
   }

   public final Tuple2[] zip$extension(final Object $this, final IterableOnce that) {
      ArrayBuilder.ofRef b = new ArrayBuilder.ofRef(ClassTag$.MODULE$.apply(Tuple2.class));
      int k = that.knownSize();
      b.sizeHint(k >= 0 ? Math.min(k, Array.getLength($this)) : Array.getLength($this));
      int i = 0;

      for(Iterator it = that.iterator(); i < Array.getLength($this) && it.hasNext(); ++i) {
         Object $plus$eq_elem = new Tuple2(ScalaRunTime$.MODULE$.array_apply($this, i), it.next());
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return (Tuple2[])b.result();
   }

   public final LazyZip2 lazyZip$extension(final Object $this, final Iterable that) {
      return new LazyZip2($this, ArraySeq$.MODULE$.unsafeWrapArray($this), that);
   }

   public final Tuple2[] zipAll$extension(final Object $this, final Iterable that, final Object thisElem, final Object thatElem) {
      ArrayBuilder.ofRef b = new ArrayBuilder.ofRef(ClassTag$.MODULE$.apply(Tuple2.class));
      int k = that.knownSize();
      b.sizeHint(Math.max(k, Array.getLength($this)));
      int i = 0;

      Iterator it;
      for(it = that.iterator(); i < Array.getLength($this) && it.hasNext(); ++i) {
         Object $plus$eq_elem = new Tuple2(ScalaRunTime$.MODULE$.array_apply($this, i), it.next());
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      while(it.hasNext()) {
         Object var12 = it.next();
         Object $plus$eq_elem = new Tuple2(thisElem, var12);
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
         ++i;
      }

      while(i < Array.getLength($this)) {
         Object $plus$eq_elem = new Tuple2(ScalaRunTime$.MODULE$.array_apply($this, i), thatElem);
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
         ++i;
      }

      return (Tuple2[])b.result();
   }

   public final Tuple2[] zipWithIndex$extension(final Object $this) {
      Tuple2[] b = new Tuple2[Array.getLength($this)];

      for(int i = 0; i < Array.getLength($this); ++i) {
         b[i] = new Tuple2(ScalaRunTime$.MODULE$.array_apply($this, i), i);
      }

      return b;
   }

   public final Object appended$extension(final Object $this, final Object x, final ClassTag evidence$10) {
      Object dest = Array$.MODULE$.copyAs($this, Array.getLength($this) + 1, evidence$10);
      ScalaRunTime$.MODULE$.array_update(dest, Array.getLength($this), x);
      return dest;
   }

   public final Object $colon$plus$extension(final Object $this, final Object x, final ClassTag evidence$11) {
      return this.appended$extension($this, x, evidence$11);
   }

   public final Object prepended$extension(final Object $this, final Object x, final ClassTag evidence$12) {
      Object dest = evidence$12.newArray(Array.getLength($this) + 1);
      ScalaRunTime$.MODULE$.array_update(dest, 0, x);
      Array$.MODULE$.copy($this, 0, dest, 1, Array.getLength($this));
      return dest;
   }

   public final Object $plus$colon$extension(final Object $this, final Object x, final ClassTag evidence$13) {
      return this.prepended$extension($this, x, evidence$13);
   }

   public final Object prependedAll$extension(final Object $this, final IterableOnce prefix, final ClassTag evidence$14) {
      Object var17;
      label121: {
         label124: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var6 = evidence$14.runtimeClass();
            Class var8 = Byte.TYPE;
            if (var8 == null) {
               if (var6 == null) {
                  break label124;
               }
            } else if (var8.equals(var6)) {
               break label124;
            }

            label125: {
               var8 = Short.TYPE;
               if (var8 == null) {
                  if (var6 == null) {
                     break label125;
                  }
               } else if (var8.equals(var6)) {
                  break label125;
               }

               label126: {
                  var8 = Character.TYPE;
                  if (var8 == null) {
                     if (var6 == null) {
                        break label126;
                     }
                  } else if (var8.equals(var6)) {
                     break label126;
                  }

                  label127: {
                     var8 = Integer.TYPE;
                     if (var8 == null) {
                        if (var6 == null) {
                           break label127;
                        }
                     } else if (var8.equals(var6)) {
                        break label127;
                     }

                     label128: {
                        var8 = Long.TYPE;
                        if (var8 == null) {
                           if (var6 == null) {
                              break label128;
                           }
                        } else if (var8.equals(var6)) {
                           break label128;
                        }

                        label129: {
                           var8 = Float.TYPE;
                           if (var8 == null) {
                              if (var6 == null) {
                                 break label129;
                              }
                           } else if (var8.equals(var6)) {
                              break label129;
                           }

                           label130: {
                              var8 = Double.TYPE;
                              if (var8 == null) {
                                 if (var6 == null) {
                                    break label130;
                                 }
                              } else if (var8.equals(var6)) {
                                 break label130;
                              }

                              label131: {
                                 var8 = Boolean.TYPE;
                                 if (var8 == null) {
                                    if (var6 == null) {
                                       break label131;
                                    }
                                 } else if (var8.equals(var6)) {
                                    break label131;
                                 }

                                 label64: {
                                    var8 = Void.TYPE;
                                    if (var8 == null) {
                                       if (var6 == null) {
                                          break label64;
                                       }
                                    } else if (var8.equals(var6)) {
                                       break label64;
                                    }

                                    var17 = new ArrayBuilder.ofRef(evidence$14);
                                    break label121;
                                 }

                                 var17 = new ArrayBuilder.ofUnit();
                                 break label121;
                              }

                              var17 = new ArrayBuilder.ofBoolean();
                              break label121;
                           }

                           var17 = new ArrayBuilder.ofDouble();
                           break label121;
                        }

                        var17 = new ArrayBuilder.ofFloat();
                        break label121;
                     }

                     var17 = new ArrayBuilder.ofLong();
                     break label121;
                  }

                  var17 = new ArrayBuilder.ofInt();
                  break label121;
               }

               var17 = new ArrayBuilder.ofChar();
               break label121;
            }

            var17 = new ArrayBuilder.ofShort();
            break label121;
         }

         var17 = new ArrayBuilder.ofByte();
      }

      Object var7 = null;
      ArrayBuilder b = (ArrayBuilder)var17;
      int k = prefix.knownSize();
      if (k >= 0) {
         b.sizeHint(k + Array.getLength($this));
      }

      b.addAll(prefix);
      if (k < 0) {
         b.sizeHint(b.length() + Array.getLength($this));
      }

      b.addAll($this);
      return b.result();
   }

   public final Object prependedAll$extension(final Object $this, final Object prefix, final ClassTag evidence$15) {
      Object dest = Array$.MODULE$.copyAs(prefix, Array.getLength(prefix) + Array.getLength($this), evidence$15);
      Array$.MODULE$.copy($this, 0, dest, Array.getLength(prefix), Array.getLength($this));
      return dest;
   }

   public final Object $plus$plus$colon$extension(final Object $this, final IterableOnce prefix, final ClassTag evidence$16) {
      return this.prependedAll$extension($this, prefix, evidence$16);
   }

   public final Object $plus$plus$colon$extension(final Object $this, final Object prefix, final ClassTag evidence$17) {
      return this.prependedAll$extension($this, prefix, evidence$17);
   }

   public final Object appendedAll$extension(final Object $this, final IterableOnce suffix, final ClassTag evidence$18) {
      Object var17;
      label113: {
         label115: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var5 = evidence$18.runtimeClass();
            Class var8 = Byte.TYPE;
            if (var8 == null) {
               if (var5 == null) {
                  break label115;
               }
            } else if (var8.equals(var5)) {
               break label115;
            }

            label116: {
               var8 = Short.TYPE;
               if (var8 == null) {
                  if (var5 == null) {
                     break label116;
                  }
               } else if (var8.equals(var5)) {
                  break label116;
               }

               label117: {
                  var8 = Character.TYPE;
                  if (var8 == null) {
                     if (var5 == null) {
                        break label117;
                     }
                  } else if (var8.equals(var5)) {
                     break label117;
                  }

                  label118: {
                     var8 = Integer.TYPE;
                     if (var8 == null) {
                        if (var5 == null) {
                           break label118;
                        }
                     } else if (var8.equals(var5)) {
                        break label118;
                     }

                     label119: {
                        var8 = Long.TYPE;
                        if (var8 == null) {
                           if (var5 == null) {
                              break label119;
                           }
                        } else if (var8.equals(var5)) {
                           break label119;
                        }

                        label120: {
                           var8 = Float.TYPE;
                           if (var8 == null) {
                              if (var5 == null) {
                                 break label120;
                              }
                           } else if (var8.equals(var5)) {
                              break label120;
                           }

                           label121: {
                              var8 = Double.TYPE;
                              if (var8 == null) {
                                 if (var5 == null) {
                                    break label121;
                                 }
                              } else if (var8.equals(var5)) {
                                 break label121;
                              }

                              label122: {
                                 var8 = Boolean.TYPE;
                                 if (var8 == null) {
                                    if (var5 == null) {
                                       break label122;
                                    }
                                 } else if (var8.equals(var5)) {
                                    break label122;
                                 }

                                 label56: {
                                    var8 = Void.TYPE;
                                    if (var8 == null) {
                                       if (var5 == null) {
                                          break label56;
                                       }
                                    } else if (var8.equals(var5)) {
                                       break label56;
                                    }

                                    var17 = new ArrayBuilder.ofRef(evidence$18);
                                    break label113;
                                 }

                                 var17 = new ArrayBuilder.ofUnit();
                                 break label113;
                              }

                              var17 = new ArrayBuilder.ofBoolean();
                              break label113;
                           }

                           var17 = new ArrayBuilder.ofDouble();
                           break label113;
                        }

                        var17 = new ArrayBuilder.ofFloat();
                        break label113;
                     }

                     var17 = new ArrayBuilder.ofLong();
                     break label113;
                  }

                  var17 = new ArrayBuilder.ofInt();
                  break label113;
               }

               var17 = new ArrayBuilder.ofChar();
               break label113;
            }

            var17 = new ArrayBuilder.ofShort();
            break label113;
         }

         var17 = new ArrayBuilder.ofByte();
      }

      Object var7 = null;
      ArrayBuilder b = (ArrayBuilder)var17;
      int sizeHint_delta = Array.getLength($this);
      Builder.sizeHint$(b, suffix, sizeHint_delta);
      b.addAll($this);
      b.addAll(suffix);
      return b.result();
   }

   public final Object appendedAll$extension(final Object $this, final Object suffix, final ClassTag evidence$19) {
      Object dest = Array$.MODULE$.copyAs($this, Array.getLength($this) + Array.getLength(suffix), evidence$19);
      Array$.MODULE$.copy(suffix, 0, dest, Array.getLength($this), Array.getLength(suffix));
      return dest;
   }

   public final Object $colon$plus$plus$extension(final Object $this, final IterableOnce suffix, final ClassTag evidence$20) {
      return this.appendedAll$extension($this, suffix, evidence$20);
   }

   public final Object $colon$plus$plus$extension(final Object $this, final Object suffix, final ClassTag evidence$21) {
      return this.appendedAll$extension($this, suffix, evidence$21);
   }

   public final Object concat$extension(final Object $this, final IterableOnce suffix, final ClassTag evidence$22) {
      return this.appendedAll$extension($this, suffix, evidence$22);
   }

   public final Object concat$extension(final Object $this, final Object suffix, final ClassTag evidence$23) {
      return this.appendedAll$extension($this, suffix, evidence$23);
   }

   public final Object $plus$plus$extension(final Object $this, final IterableOnce xs, final ClassTag evidence$24) {
      return this.appendedAll$extension($this, xs, evidence$24);
   }

   public final Object $plus$plus$extension(final Object $this, final Object xs, final ClassTag evidence$25) {
      return this.appendedAll$extension($this, xs, evidence$25);
   }

   public final boolean contains$extension(final Object $this, final Object elem) {
      int exists$extension_indexWhere$extension_i = 0;

      int var10000;
      while(true) {
         if (exists$extension_indexWhere$extension_i >= Array.getLength($this)) {
            var10000 = -1;
            break;
         }

         Object var4 = ScalaRunTime$.MODULE$.array_apply($this, exists$extension_indexWhere$extension_i);
         if ($anonfun$contains$1(elem, var4)) {
            var10000 = exists$extension_indexWhere$extension_i;
            break;
         }

         ++exists$extension_indexWhere$extension_i;
      }

      return var10000 >= 0;
   }

   public final Object patch$extension(final Object $this, final int from, final IterableOnce other, final int replaced, final ClassTag evidence$26) {
      Object var22;
      label135: {
         label138: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var11 = evidence$26.runtimeClass();
            Class var13 = Byte.TYPE;
            if (var13 == null) {
               if (var11 == null) {
                  break label138;
               }
            } else if (var13.equals(var11)) {
               break label138;
            }

            label139: {
               var13 = Short.TYPE;
               if (var13 == null) {
                  if (var11 == null) {
                     break label139;
                  }
               } else if (var13.equals(var11)) {
                  break label139;
               }

               label140: {
                  var13 = Character.TYPE;
                  if (var13 == null) {
                     if (var11 == null) {
                        break label140;
                     }
                  } else if (var13.equals(var11)) {
                     break label140;
                  }

                  label141: {
                     var13 = Integer.TYPE;
                     if (var13 == null) {
                        if (var11 == null) {
                           break label141;
                        }
                     } else if (var13.equals(var11)) {
                        break label141;
                     }

                     label142: {
                        var13 = Long.TYPE;
                        if (var13 == null) {
                           if (var11 == null) {
                              break label142;
                           }
                        } else if (var13.equals(var11)) {
                           break label142;
                        }

                        label143: {
                           var13 = Float.TYPE;
                           if (var13 == null) {
                              if (var11 == null) {
                                 break label143;
                              }
                           } else if (var13.equals(var11)) {
                              break label143;
                           }

                           label144: {
                              var13 = Double.TYPE;
                              if (var13 == null) {
                                 if (var11 == null) {
                                    break label144;
                                 }
                              } else if (var13.equals(var11)) {
                                 break label144;
                              }

                              label145: {
                                 var13 = Boolean.TYPE;
                                 if (var13 == null) {
                                    if (var11 == null) {
                                       break label145;
                                    }
                                 } else if (var13.equals(var11)) {
                                    break label145;
                                 }

                                 label78: {
                                    var13 = Void.TYPE;
                                    if (var13 == null) {
                                       if (var11 == null) {
                                          break label78;
                                       }
                                    } else if (var13.equals(var11)) {
                                       break label78;
                                    }

                                    var22 = new ArrayBuilder.ofRef(evidence$26);
                                    break label135;
                                 }

                                 var22 = new ArrayBuilder.ofUnit();
                                 break label135;
                              }

                              var22 = new ArrayBuilder.ofBoolean();
                              break label135;
                           }

                           var22 = new ArrayBuilder.ofDouble();
                           break label135;
                        }

                        var22 = new ArrayBuilder.ofFloat();
                        break label135;
                     }

                     var22 = new ArrayBuilder.ofLong();
                     break label135;
                  }

                  var22 = new ArrayBuilder.ofInt();
                  break label135;
               }

               var22 = new ArrayBuilder.ofChar();
               break label135;
            }

            var22 = new ArrayBuilder.ofShort();
            break label135;
         }

         var22 = new ArrayBuilder.ofByte();
      }

      Object var12 = null;
      ArrayBuilder b = (ArrayBuilder)var22;
      int k = other.knownSize();
      int r = replaced < 0 ? 0 : replaced;
      if (k >= 0) {
         b.sizeHint(Array.getLength($this) + k - r);
      }

      int chunk1 = from > 0 ? Math.min(from, Array.getLength($this)) : 0;
      if (chunk1 > 0) {
         b.addAll($this, 0, chunk1);
      }

      b.addAll(other);
      int remaining = Array.getLength($this) - chunk1 - r;
      if (remaining > 0) {
         b.addAll($this, Array.getLength($this) - remaining, remaining);
      }

      return b.result();
   }

   public final Tuple2 unzip$extension(final Object $this, final Function1 asPair, final ClassTag ct1, final ClassTag ct2) {
      Object a1 = ct1.newArray(Array.getLength($this));
      Object a2 = ct2.newArray(Array.getLength($this));

      for(int i = 0; i < Array.getLength($this); ++i) {
         Tuple2 e = (Tuple2)asPair.apply(ScalaRunTime$.MODULE$.array_apply($this, i));
         ScalaRunTime$.MODULE$.array_update(a1, i, e._1());
         ScalaRunTime$.MODULE$.array_update(a2, i, e._2());
      }

      return new Tuple2(a1, a2);
   }

   public final Tuple3 unzip3$extension(final Object $this, final Function1 asTriple, final ClassTag ct1, final ClassTag ct2, final ClassTag ct3) {
      Object a1 = ct1.newArray(Array.getLength($this));
      Object a2 = ct2.newArray(Array.getLength($this));
      Object a3 = ct3.newArray(Array.getLength($this));

      for(int i = 0; i < Array.getLength($this); ++i) {
         Tuple3 e = (Tuple3)asTriple.apply(ScalaRunTime$.MODULE$.array_apply($this, i));
         ScalaRunTime$.MODULE$.array_update(a1, i, e._1());
         ScalaRunTime$.MODULE$.array_update(a2, i, e._2());
         ScalaRunTime$.MODULE$.array_update(a3, i, e._3());
      }

      return new Tuple3(a1, a2, a3);
   }

   public final Object[] transpose$extension(final Object $this, final Function1 asArray) {
      Class aClass = $this.getClass().getComponentType();
      ArrayBuilder.ofRef bb = new ArrayBuilder.ofRef(ClassTag$.MODULE$.apply(aClass));
      if (Array.getLength($this) == 0) {
         return bb.result();
      } else {
         Object map$extension_$this = asArray.apply(ScalaRunTime$.MODULE$.array_apply($this, 0));
         int map$extension_len = Array.getLength(map$extension_$this);
         Object map$extension_ys = new ArrayBuilder[map$extension_len];
         if (map$extension_len > 0) {
            int map$extension_i = 0;
            if (map$extension_$this instanceof Object[]) {
               for(Object[] var10 = map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  Object var10000 = var10[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            } else if (map$extension_$this instanceof int[]) {
               for(int[] var11 = (int[])map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  int var80 = var11[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            } else if (map$extension_$this instanceof double[]) {
               for(double[] var12 = (double[])map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  double var81 = var12[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            } else if (map$extension_$this instanceof long[]) {
               for(long[] var13 = (long[])map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  long var82 = var13[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            } else if (map$extension_$this instanceof float[]) {
               for(float[] var14 = (float[])map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  float var83 = var14[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            } else if (map$extension_$this instanceof char[]) {
               for(char[] var15 = (char[])map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  char var84 = var15[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            } else if (map$extension_$this instanceof byte[]) {
               for(byte[] var16 = (byte[])map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  byte var85 = var16[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            } else if (map$extension_$this instanceof short[]) {
               for(short[] var17 = (short[])map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  short var86 = var17[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            } else {
               if (!(map$extension_$this instanceof boolean[])) {
                  throw new MatchError(map$extension_$this);
               }

               for(boolean[] var18 = (boolean[])map$extension_$this; map$extension_i < map$extension_len; ++map$extension_i) {
                  boolean var87 = var18[map$extension_i];
                  Object array_update_value = mkRowBuilder$1(aClass);
                  ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
                  array_update_value = null;
               }
            }
         }

         map$extension_$this = null;
         Object var44 = null;
         Object var45 = null;
         Object var46 = null;
         Object var47 = null;
         Object var48 = null;
         Object var49 = null;
         Object var50 = null;
         Object var51 = null;
         Object var52 = null;
         Object var53 = null;
         ArrayBuilder[] bs = (ArrayBuilder[])map$extension_ys;
         int foreach$extension_len = Array.getLength($this);
         int foreach$extension_i = 0;
         if ($this instanceof Object[]) {
            for(Object[] var21 = $this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Object var32 = var21[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var32);
            }
         } else if ($this instanceof int[]) {
            for(int[] var22 = (int[])$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Integer var63 = var22[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var63);
            }
         } else if ($this instanceof double[]) {
            for(double[] var23 = (double[])$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Double var64 = var23[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var64);
            }
         } else if ($this instanceof long[]) {
            for(long[] var24 = (long[])$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Long var65 = var24[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var65);
            }
         } else if ($this instanceof float[]) {
            for(float[] var25 = (float[])$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Float var66 = var25[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var66);
            }
         } else if ($this instanceof char[]) {
            for(char[] var26 = (char[])$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Character var67 = var26[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var67);
            }
         } else if ($this instanceof byte[]) {
            for(byte[] var27 = (byte[])$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Byte var68 = var27[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var68);
            }
         } else if ($this instanceof short[]) {
            for(short[] var28 = (short[])$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Short var69 = var28[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var69);
            }
         } else {
            if (!($this instanceof boolean[])) {
               throw new MatchError($this);
            }

            for(boolean[] var29 = (boolean[])$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
               Boolean var70 = var29[foreach$extension_i];
               $anonfun$transpose$2(asArray, bs, var70);
            }
         }

         Object var54 = null;
         Object var55 = null;
         Object var56 = null;
         Object var57 = null;
         Object var58 = null;
         Object var59 = null;
         Object var60 = null;
         Object var61 = null;
         Object var62 = null;

         for(Object var33 : bs) {
            $anonfun$transpose$4(bb, (ArrayBuilder)var33);
         }

         return bb.result();
      }
   }

   public final void foreach$extension(final Object $this, final Function1 f) {
      int len = Array.getLength($this);
      int i = 0;
      if ($this instanceof Object[]) {
         for(Object[] var5 = $this; i < len; ++i) {
            f.apply(var5[i]);
         }

      } else if ($this instanceof int[]) {
         for(int[] var6 = (int[])$this; i < len; ++i) {
            f.apply(var6[i]);
         }

      } else if ($this instanceof double[]) {
         for(double[] var7 = (double[])$this; i < len; ++i) {
            f.apply(var7[i]);
         }

      } else if ($this instanceof long[]) {
         for(long[] var8 = (long[])$this; i < len; ++i) {
            f.apply(var8[i]);
         }

      } else if ($this instanceof float[]) {
         for(float[] var9 = (float[])$this; i < len; ++i) {
            f.apply(var9[i]);
         }

      } else if ($this instanceof char[]) {
         for(char[] var10 = (char[])$this; i < len; ++i) {
            f.apply(var10[i]);
         }

      } else if ($this instanceof byte[]) {
         for(byte[] var11 = (byte[])$this; i < len; ++i) {
            f.apply(var11[i]);
         }

      } else if ($this instanceof short[]) {
         for(short[] var12 = (short[])$this; i < len; ++i) {
            f.apply(var12[i]);
         }

      } else if (!($this instanceof boolean[])) {
         throw new MatchError($this);
      } else {
         for(boolean[] var13 = (boolean[])$this; i < len; ++i) {
            f.apply(var13[i]);
         }

      }
   }

   public final Object distinct$extension(final Object $this) {
      Function1 distinctBy$extension_f;
      Object var16;
      label113: {
         label115: {
            distinctBy$extension_f = (x) -> Predef$.MODULE$.identity(x);
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            ClassTag distinctBy$extension_make_evidence$1 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
            Class var4 = distinctBy$extension_make_evidence$1.runtimeClass();
            Class var7 = Byte.TYPE;
            if (var7 == null) {
               if (var4 == null) {
                  break label115;
               }
            } else if (var7.equals(var4)) {
               break label115;
            }

            label116: {
               var7 = Short.TYPE;
               if (var7 == null) {
                  if (var4 == null) {
                     break label116;
                  }
               } else if (var7.equals(var4)) {
                  break label116;
               }

               label117: {
                  var7 = Character.TYPE;
                  if (var7 == null) {
                     if (var4 == null) {
                        break label117;
                     }
                  } else if (var7.equals(var4)) {
                     break label117;
                  }

                  label118: {
                     var7 = Integer.TYPE;
                     if (var7 == null) {
                        if (var4 == null) {
                           break label118;
                        }
                     } else if (var7.equals(var4)) {
                        break label118;
                     }

                     label119: {
                        var7 = Long.TYPE;
                        if (var7 == null) {
                           if (var4 == null) {
                              break label119;
                           }
                        } else if (var7.equals(var4)) {
                           break label119;
                        }

                        label120: {
                           var7 = Float.TYPE;
                           if (var7 == null) {
                              if (var4 == null) {
                                 break label120;
                              }
                           } else if (var7.equals(var4)) {
                              break label120;
                           }

                           label121: {
                              var7 = Double.TYPE;
                              if (var7 == null) {
                                 if (var4 == null) {
                                    break label121;
                                 }
                              } else if (var7.equals(var4)) {
                                 break label121;
                              }

                              label122: {
                                 var7 = Boolean.TYPE;
                                 if (var7 == null) {
                                    if (var4 == null) {
                                       break label122;
                                    }
                                 } else if (var7.equals(var4)) {
                                    break label122;
                                 }

                                 label56: {
                                    var7 = Void.TYPE;
                                    if (var7 == null) {
                                       if (var4 == null) {
                                          break label56;
                                       }
                                    } else if (var7.equals(var4)) {
                                       break label56;
                                    }

                                    var16 = new ArrayBuilder.ofRef(distinctBy$extension_make_evidence$1);
                                    break label113;
                                 }

                                 var16 = new ArrayBuilder.ofUnit();
                                 break label113;
                              }

                              var16 = new ArrayBuilder.ofBoolean();
                              break label113;
                           }

                           var16 = new ArrayBuilder.ofDouble();
                           break label113;
                        }

                        var16 = new ArrayBuilder.ofFloat();
                        break label113;
                     }

                     var16 = new ArrayBuilder.ofLong();
                     break label113;
                  }

                  var16 = new ArrayBuilder.ofInt();
                  break label113;
               }

               var16 = new ArrayBuilder.ofChar();
               break label113;
            }

            var16 = new ArrayBuilder.ofShort();
            break label113;
         }

         var16 = new ArrayBuilder.ofByte();
      }

      Object var5 = null;
      Object var6 = null;
      return ((ArrayBuilder)var16).addAll((IterableOnce)this.iterator$extension($this).distinctBy(distinctBy$extension_f)).result();
   }

   public final Object distinctBy$extension(final Object $this, final Function1 f) {
      Object var16;
      label113: {
         label115: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            ClassTag make_evidence$1 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
            Class var4 = make_evidence$1.runtimeClass();
            Class var7 = Byte.TYPE;
            if (var7 == null) {
               if (var4 == null) {
                  break label115;
               }
            } else if (var7.equals(var4)) {
               break label115;
            }

            label116: {
               var7 = Short.TYPE;
               if (var7 == null) {
                  if (var4 == null) {
                     break label116;
                  }
               } else if (var7.equals(var4)) {
                  break label116;
               }

               label117: {
                  var7 = Character.TYPE;
                  if (var7 == null) {
                     if (var4 == null) {
                        break label117;
                     }
                  } else if (var7.equals(var4)) {
                     break label117;
                  }

                  label118: {
                     var7 = Integer.TYPE;
                     if (var7 == null) {
                        if (var4 == null) {
                           break label118;
                        }
                     } else if (var7.equals(var4)) {
                        break label118;
                     }

                     label119: {
                        var7 = Long.TYPE;
                        if (var7 == null) {
                           if (var4 == null) {
                              break label119;
                           }
                        } else if (var7.equals(var4)) {
                           break label119;
                        }

                        label120: {
                           var7 = Float.TYPE;
                           if (var7 == null) {
                              if (var4 == null) {
                                 break label120;
                              }
                           } else if (var7.equals(var4)) {
                              break label120;
                           }

                           label121: {
                              var7 = Double.TYPE;
                              if (var7 == null) {
                                 if (var4 == null) {
                                    break label121;
                                 }
                              } else if (var7.equals(var4)) {
                                 break label121;
                              }

                              label122: {
                                 var7 = Boolean.TYPE;
                                 if (var7 == null) {
                                    if (var4 == null) {
                                       break label122;
                                    }
                                 } else if (var7.equals(var4)) {
                                    break label122;
                                 }

                                 label56: {
                                    var7 = Void.TYPE;
                                    if (var7 == null) {
                                       if (var4 == null) {
                                          break label56;
                                       }
                                    } else if (var7.equals(var4)) {
                                       break label56;
                                    }

                                    var16 = new ArrayBuilder.ofRef(make_evidence$1);
                                    break label113;
                                 }

                                 var16 = new ArrayBuilder.ofUnit();
                                 break label113;
                              }

                              var16 = new ArrayBuilder.ofBoolean();
                              break label113;
                           }

                           var16 = new ArrayBuilder.ofDouble();
                           break label113;
                        }

                        var16 = new ArrayBuilder.ofFloat();
                        break label113;
                     }

                     var16 = new ArrayBuilder.ofLong();
                     break label113;
                  }

                  var16 = new ArrayBuilder.ofInt();
                  break label113;
               }

               var16 = new ArrayBuilder.ofChar();
               break label113;
            }

            var16 = new ArrayBuilder.ofShort();
            break label113;
         }

         var16 = new ArrayBuilder.ofByte();
      }

      Object var5 = null;
      Object var6 = null;
      return ((ArrayBuilder)var16).addAll((IterableOnce)this.iterator$extension($this).distinctBy(f)).result();
   }

   public final Object padTo$extension(final Object $this, final int len, final Object elem, final ClassTag evidence$27) {
      int i = Array.getLength($this);
      int newlen = Math.max(i, len);

      Object dest;
      for(dest = Array$.MODULE$.copyAs($this, newlen, evidence$27); i < newlen; ++i) {
         ScalaRunTime$.MODULE$.array_update(dest, i, elem);
      }

      return dest;
   }

   public final Range indices$extension(final Object $this) {
      Range$ var10000 = Range$.MODULE$;
      int apply_end = Array.getLength($this);
      int apply_start = 0;
      return new Range.Exclusive(apply_start, apply_end, 1);
   }

   public final scala.collection.immutable.Map groupBy$extension(final Object $this, final Function1 f) {
      scala.collection.mutable.Map m = (scala.collection.mutable.Map)scala.collection.mutable.Map$.MODULE$.empty();
      int len = Array.getLength($this);

      for(int i = 0; i < len; ++i) {
         Object elem = ScalaRunTime$.MODULE$.array_apply($this, i);
         Object key = f.apply(elem);
         ArrayBuilder bldr = (ArrayBuilder)m.getOrElseUpdate(key, () -> {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            ClassTag make_evidence$1 = ClassTag$.MODULE$.apply($this.getClass().getComponentType());
            Class var2 = make_evidence$1.runtimeClass();
            Class var3 = Byte.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofByte();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofByte();
            }

            var3 = Short.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofShort();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofShort();
            }

            var3 = Character.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofChar();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofChar();
            }

            var3 = Integer.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofInt();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofInt();
            }

            var3 = Long.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofLong();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofLong();
            }

            var3 = Float.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofFloat();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofFloat();
            }

            var3 = Double.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofDouble();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofDouble();
            }

            var3 = Boolean.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofBoolean();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofBoolean();
            }

            var3 = Void.TYPE;
            if (var3 == null) {
               if (var2 == null) {
                  return new ArrayBuilder.ofUnit();
               }
            } else if (var3.equals(var2)) {
               return new ArrayBuilder.ofUnit();
            }

            return new ArrayBuilder.ofRef(make_evidence$1);
         });
         if (bldr == null) {
            throw null;
         }

         bldr.addOne(elem);
      }

      return m.view().mapValues((x$3) -> x$3.result()).toMap($less$colon$less$.MODULE$.refl());
   }

   public final scala.collection.immutable.Map groupMap$extension(final Object $this, final Function1 key, final Function1 f, final ClassTag evidence$28) {
      scala.collection.mutable.Map m = (scala.collection.mutable.Map)scala.collection.mutable.Map$.MODULE$.empty();
      int len = Array.getLength($this);

      for(int i = 0; i < len; ++i) {
         Object elem = ScalaRunTime$.MODULE$.array_apply($this, i);
         Object k = key.apply(elem);
         ArrayBuilder bldr = (ArrayBuilder)m.getOrElseUpdate(k, () -> ArrayBuilder$.MODULE$.make(evidence$28));
         Object $plus$eq_elem = f.apply(elem);
         if (bldr == null) {
            throw null;
         }

         bldr.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return m.view().mapValues((x$4) -> x$4.result()).toMap($less$colon$less$.MODULE$.refl());
   }

   public final scala.collection.immutable.Seq toSeq$extension(final Object $this) {
      return this.toIndexedSeq$extension($this);
   }

   public final scala.collection.immutable.IndexedSeq toIndexedSeq$extension(final Object $this) {
      return ArraySeq$.MODULE$.unsafeWrapArray(Array$.MODULE$.copyOf($this, Array.getLength($this)));
   }

   public final int copyToArray$extension(final Object $this, final Object xs) {
      int copyToArray$extension_start = 0;
      return this.copyToArray$extension($this, xs, copyToArray$extension_start, Integer.MAX_VALUE);
   }

   public final int copyToArray$extension(final Object $this, final Object xs, final int start) {
      return this.copyToArray$extension($this, xs, start, Integer.MAX_VALUE);
   }

   public final int copyToArray$extension(final Object $this, final Object xs, final int start, final int len) {
      IterableOnce$ var10000 = IterableOnce$.MODULE$;
      int var8 = Array.getLength($this);
      int elemsToCopyToArray_destLen = Array.getLength(xs);
      int elemsToCopyToArray_srcLen = var8;
      scala.math.package$ var9 = scala.math.package$.MODULE$;
      var9 = scala.math.package$.MODULE$;
      var9 = scala.math.package$.MODULE$;
      int copied = Math.max(Math.min(Math.min(len, elemsToCopyToArray_srcLen), elemsToCopyToArray_destLen - start), 0);
      if (copied > 0) {
         Array$.MODULE$.copy($this, 0, xs, start, copied);
      }

      return copied;
   }

   public final Object toArray$extension(final Object $this, final ClassTag evidence$29) {
      Object destination = evidence$29.newArray(Array.getLength($this));
      int copyToArray$extension_start = 0;
      this.copyToArray$extension($this, destination, copyToArray$extension_start, Integer.MAX_VALUE);
      return destination;
   }

   public final int count$extension(final Object $this, final Function1 p) {
      int i = 0;
      int res = 0;

      for(int len = Array.getLength($this); i < len; ++i) {
         if (BoxesRunTime.unboxToBoolean(p.apply(ScalaRunTime$.MODULE$.array_apply($this, i)))) {
            ++res;
         }
      }

      return res;
   }

   public final boolean startsWith$extension(final Object $this, final Object that) {
      return this.startsWith$extension($this, (Object)that, 0);
   }

   public final boolean startsWith$extension(final Object $this, final Object that, final int offset) {
      RichInt$ var10000 = RichInt$.MODULE$;
      int max$extension_that = 0;
      scala.math.package$ var8 = scala.math.package$.MODULE$;
      int safeOffset = Math.max(offset, max$extension_that);
      int thatl = Array.getLength(that);
      if (thatl > Array.getLength($this) - safeOffset) {
         return thatl == 0;
      } else {
         for(int i = 0; i < thatl; ++i) {
            if (!BoxesRunTime.equals(ScalaRunTime$.MODULE$.array_apply($this, i + safeOffset), ScalaRunTime$.MODULE$.array_apply(that, i))) {
               return false;
            }
         }

         return true;
      }
   }

   public final boolean endsWith$extension(final Object $this, final Object that) {
      int thatl = Array.getLength(that);
      int off = Array.getLength($this) - thatl;
      if (off < 0) {
         return false;
      } else {
         for(int i = 0; i < thatl; ++i) {
            if (!BoxesRunTime.equals(ScalaRunTime$.MODULE$.array_apply($this, i + off), ScalaRunTime$.MODULE$.array_apply(that, i))) {
               return false;
            }
         }

         return true;
      }
   }

   public final Object updated$extension(final Object $this, final int index, final Object elem, final ClassTag evidence$30) {
      if (index >= 0 && index < Array.getLength($this)) {
         Object dest = this.toArray$extension($this, evidence$30);
         ScalaRunTime$.MODULE$.array_update(dest, index, elem);
         return dest;
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(index, Array.getLength($this) - 1);
      }
   }

   public final IndexedSeqView view$extension(final Object $this) {
      return new ArrayOps.ArrayView($this);
   }

   public final Object diff$extension(final Object $this, final Seq that) {
      ArraySeq var10000 = scala.collection.mutable.ArraySeq$.MODULE$.make($this);
      if (var10000 == null) {
         throw null;
      } else {
         return ((IterableOnceOps)StrictOptimizedSeqOps.diff$(var10000, that)).toArray(ClassTag$.MODULE$.apply($this.getClass().getComponentType()));
      }
   }

   public final Object intersect$extension(final Object $this, final Seq that) {
      ArraySeq var10000 = scala.collection.mutable.ArraySeq$.MODULE$.make($this);
      if (var10000 == null) {
         throw null;
      } else {
         return ((IterableOnceOps)StrictOptimizedSeqOps.intersect$(var10000, that)).toArray(ClassTag$.MODULE$.apply($this.getClass().getComponentType()));
      }
   }

   public final Iterator sliding$extension(final Object $this, final int size, final int step) {
      return scala.collection.mutable.ArraySeq$.MODULE$.make($this).sliding(size, step).map((x$5) -> x$5.toArray(ClassTag$.MODULE$.apply($this.getClass().getComponentType())));
   }

   public final int sliding$default$2$extension(final Object $this) {
      return 1;
   }

   public final Iterator combinations$extension(final Object $this, final int n) {
      return scala.collection.mutable.ArraySeq$.MODULE$.make($this).combinations(n).map((x$6) -> x$6.toArray(ClassTag$.MODULE$.apply($this.getClass().getComponentType())));
   }

   public final Iterator permutations$extension(final Object $this) {
      return scala.collection.mutable.ArraySeq$.MODULE$.make($this).permutations().map((x$7) -> x$7.toArray(ClassTag$.MODULE$.apply($this.getClass().getComponentType())));
   }

   public final boolean startsWith$extension(final Object $this, final IterableOnce that, final int offset) {
      return scala.collection.mutable.ArraySeq$.MODULE$.make($this).startsWith(that, offset);
   }

   public final int startsWith$default$2$extension(final Object $this) {
      return 0;
   }

   public final boolean endsWith$extension(final Object $this, final Iterable that) {
      return scala.collection.mutable.ArraySeq$.MODULE$.make($this).endsWith(that);
   }

   public final int hashCode$extension(final Object $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final Object $this, final Object x$1) {
      if (x$1 instanceof ArrayOps) {
         Object var3 = x$1 == null ? null : ((ArrayOps)x$1).scala$collection$ArrayOps$$xs();
         if (BoxesRunTime.equals($this, var3)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterateUntilEmpty$1(final Object x) {
      return Array.getLength(x) != 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$takeWhile$1(final Function1 p$1, final Object x) {
      return !BoxesRunTime.unboxToBoolean(p$1.apply(x));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$dropWhile$1(final Function1 p$2, final Object x) {
      return !BoxesRunTime.unboxToBoolean(p$2.apply(x));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$span$1(final Function1 p$3, final Object x) {
      return !BoxesRunTime.unboxToBoolean(p$3.apply(x));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterNot$1(final Function1 p$4, final Object x) {
      return !BoxesRunTime.unboxToBoolean(p$4.apply(x));
   }

   private static final Object boxed$1(final int len$1, final Object $this$2, final Ordering ord$1) {
      if (len$1 < 300) {
         Object a = ScalaRunTime$.MODULE$.array_clone($this$2);
         Sorting$.MODULE$.stableSort(a, 0, Array.getLength(a), ord$1);
         return a;
      } else {
         Object[] a = Array$.MODULE$.copyAs($this$2, len$1, ClassTag$.MODULE$.AnyRef());
         Arrays.sort(a, ord$1);
         return Array$.MODULE$.copyAs(a, len$1, ClassTag$.MODULE$.apply($this$2.getClass().getComponentType()));
      }
   }

   private static final Object f$mZc$sp$1(final boolean[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$mBc$sp$1(final byte[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$mCc$sp$1(final char[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$mDc$sp$1(final double[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$mFc$sp$1(final float[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$mIc$sp$1(final int[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$mJc$sp$1(final long[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$mSc$sp$1(final short[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$mVc$sp$1(final BoxedUnit[] xs, final Function2 op, final Object z) {
      int length = xs.length;
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, xs[i]);
      }

      return v;
   }

   private static final Object f$2(final Object xs, final Function2 op, final Object z) {
      int length = Array.getLength(xs);
      Object v = z;

      for(int i = 0; i < length; ++i) {
         v = op.apply(v, ScalaRunTime$.MODULE$.array_apply(xs, i));
      }

      return v;
   }

   private static final Object f$mZc$sp$2(final boolean[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$mBc$sp$2(final byte[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$mCc$sp$2(final char[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$mDc$sp$2(final double[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$mFc$sp$2(final float[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$mIc$sp$2(final int[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$mJc$sp$2(final long[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$mSc$sp$2(final short[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$mVc$sp$2(final BoxedUnit[] xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = xs.length - 1; i >= 0; --i) {
         v = op.apply(xs[i], v);
      }

      return v;
   }

   private static final Object f$3(final Object xs, final Function2 op, final Object z) {
      Object v = z;

      for(int i = Array.getLength(xs) - 1; i >= 0; --i) {
         v = op.apply(ScalaRunTime$.MODULE$.array_apply(xs, i), v);
      }

      return v;
   }

   // $FF: synthetic method
   public static final Iterable $anonfun$flatMap$2(final Function1 asIterable$2, final Function1 f$4, final Object x) {
      return (Iterable)asIterable$2.apply(f$4.apply(x));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$contains$1(final Object elem$1, final Object x$2) {
      return BoxesRunTime.equals(x$2, elem$1);
   }

   private static final ArrayBuilder mkRowBuilder$1(final Class aClass$1) {
      ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
      ClassTag make_evidence$1 = ClassTag$.MODULE$.apply(aClass$1.getComponentType());
      Class var2 = make_evidence$1.runtimeClass();
      Class var3 = Byte.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofByte();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofByte();
      }

      var3 = Short.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofShort();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofShort();
      }

      var3 = Character.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofChar();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofChar();
      }

      var3 = Integer.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofInt();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofInt();
      }

      var3 = Long.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofLong();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofLong();
      }

      var3 = Float.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofFloat();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofFloat();
      }

      var3 = Double.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofDouble();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofDouble();
      }

      var3 = Boolean.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofBoolean();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofBoolean();
      }

      var3 = Void.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofUnit();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofUnit();
      }

      return new ArrayBuilder.ofRef(make_evidence$1);
   }

   // $FF: synthetic method
   public static final ArrayBuilder $anonfun$transpose$1(final Class aClass$1, final Object x) {
      return mkRowBuilder$1(aClass$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$transpose$3(final ArrayBuilder[] bs$1, final IntRef i$1, final Object x) {
      bs$1[i$1.elem].$plus$eq(x);
      ++i$1.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$transpose$2(final Function1 asArray$1, final ArrayBuilder[] bs$1, final Object xs) {
      int var16 = 0;
      Object foreach$extension_$this = asArray$1.apply(xs);
      int foreach$extension_len = Array.getLength(foreach$extension_$this);
      int foreach$extension_i = 0;
      if (foreach$extension_$this instanceof Object[]) {
         for(Object[] var6 = foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Object var15 = var6[foreach$extension_i];
            bs$1[var16].$plus$eq(var15);
            ++var16;
         }
      } else if (foreach$extension_$this instanceof int[]) {
         for(int[] var7 = (int[])foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Integer var17 = var7[foreach$extension_i];
            bs$1[var16].$plus$eq(var17);
            ++var16;
         }
      } else if (foreach$extension_$this instanceof double[]) {
         for(double[] var8 = (double[])foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Double var18 = var8[foreach$extension_i];
            bs$1[var16].$plus$eq(var18);
            ++var16;
         }
      } else if (foreach$extension_$this instanceof long[]) {
         for(long[] var9 = (long[])foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Long var19 = var9[foreach$extension_i];
            bs$1[var16].$plus$eq(var19);
            ++var16;
         }
      } else if (foreach$extension_$this instanceof float[]) {
         for(float[] var10 = (float[])foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Float var20 = var10[foreach$extension_i];
            bs$1[var16].$plus$eq(var20);
            ++var16;
         }
      } else if (foreach$extension_$this instanceof char[]) {
         for(char[] var11 = (char[])foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Character var21 = var11[foreach$extension_i];
            bs$1[var16].$plus$eq(var21);
            ++var16;
         }
      } else if (foreach$extension_$this instanceof byte[]) {
         for(byte[] var12 = (byte[])foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Byte var22 = var12[foreach$extension_i];
            bs$1[var16].$plus$eq(var22);
            ++var16;
         }
      } else if (foreach$extension_$this instanceof short[]) {
         for(short[] var13 = (short[])foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Short var23 = var13[foreach$extension_i];
            bs$1[var16].$plus$eq(var23);
            ++var16;
         }
      } else {
         if (!(foreach$extension_$this instanceof boolean[])) {
            throw new MatchError(foreach$extension_$this);
         }

         for(boolean[] var14 = (boolean[])foreach$extension_$this; foreach$extension_i < foreach$extension_len; ++foreach$extension_i) {
            Boolean var24 = var14[foreach$extension_i];
            bs$1[var16].$plus$eq(var24);
            ++var16;
         }
      }

   }

   // $FF: synthetic method
   public static final ArrayBuilder.ofRef $anonfun$transpose$4(final ArrayBuilder.ofRef bb$1, final ArrayBuilder b) {
      Object $plus$eq_elem = b.result();
      return bb$1.addOne($plus$eq_elem);
   }

   private ArrayOps$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$takeWhile$1$adapted(final Function1 p$1, final Object x) {
      return BoxesRunTime.boxToBoolean($anonfun$takeWhile$1(p$1, x));
   }

   // $FF: synthetic method
   public static final Object $anonfun$dropWhile$1$adapted(final Function1 p$2, final Object x) {
      return BoxesRunTime.boxToBoolean($anonfun$dropWhile$1(p$2, x));
   }

   // $FF: synthetic method
   public static final Object $anonfun$span$1$adapted(final Function1 p$3, final Object x) {
      return BoxesRunTime.boxToBoolean($anonfun$span$1(p$3, x));
   }

   // $FF: synthetic method
   public static final Object $anonfun$filterNot$1$adapted(final Function1 p$4, final Object x) {
      return BoxesRunTime.boxToBoolean($anonfun$filterNot$1(p$4, x));
   }

   // $FF: synthetic method
   public static final Object $anonfun$contains$1$adapted(final Object elem$1, final Object x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$contains$1(elem$1, x$2));
   }

   // $FF: synthetic method
   public static final Object $anonfun$transpose$2$adapted(final Function1 asArray$1, final ArrayBuilder[] bs$1, final Object xs) {
      $anonfun$transpose$2(asArray$1, bs$1, xs);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final Object $anonfun$transpose$3$adapted(final ArrayBuilder[] bs$1, final IntRef i$1, final Object x) {
      $anonfun$transpose$3(bs$1, i$1, x);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
