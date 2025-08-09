package scala;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Range;
import scala.collection.immutable.Range$;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.math.Numeric;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

public final class Array$ implements Serializable {
   public static final Array$ MODULE$ = new Array$();
   private static final boolean[] emptyBooleanArray = new boolean[0];
   private static final byte[] emptyByteArray = new byte[0];
   private static final char[] emptyCharArray = new char[0];
   private static final double[] emptyDoubleArray = new double[0];
   private static final float[] emptyFloatArray = new float[0];
   private static final int[] emptyIntArray = new int[0];
   private static final long[] emptyLongArray = new long[0];
   private static final short[] emptyShortArray = new short[0];
   private static final Object[] emptyObjectArray = new Object[0];

   public boolean[] emptyBooleanArray() {
      return emptyBooleanArray;
   }

   public byte[] emptyByteArray() {
      return emptyByteArray;
   }

   public char[] emptyCharArray() {
      return emptyCharArray;
   }

   public double[] emptyDoubleArray() {
      return emptyDoubleArray;
   }

   public float[] emptyFloatArray() {
      return emptyFloatArray;
   }

   public int[] emptyIntArray() {
      return emptyIntArray;
   }

   public long[] emptyLongArray() {
      return emptyLongArray;
   }

   public short[] emptyShortArray() {
      return emptyShortArray;
   }

   public Object[] emptyObjectArray() {
      return emptyObjectArray;
   }

   public Factory toFactory(final Array$ dummy, final ClassTag evidence$1) {
      return new Array.ArrayFactory(dummy, evidence$1);
   }

   public ArrayBuilder newBuilder(final ClassTag t) {
      ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
      Class var2 = t.runtimeClass();
      Class var3 = java.lang.Byte.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofByte();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofByte();
      }

      var3 = java.lang.Short.TYPE;
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

      var3 = java.lang.Long.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofLong();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofLong();
      }

      var3 = java.lang.Float.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofFloat();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofFloat();
      }

      var3 = java.lang.Double.TYPE;
      if (var3 == null) {
         if (var2 == null) {
            return new ArrayBuilder.ofDouble();
         }
      } else if (var3.equals(var2)) {
         return new ArrayBuilder.ofDouble();
      }

      var3 = java.lang.Boolean.TYPE;
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

      return new ArrayBuilder.ofRef(t);
   }

   public Object from(final IterableOnce it, final ClassTag evidence$3) {
      return it instanceof Iterable ? ((Iterable)it).toArray(evidence$3) : it.iterator().toArray(evidence$3);
   }

   private void slowcopy(final Object src, final int srcPos, final Object dest, final int destPos, final int length) {
      int i = srcPos;
      int j = destPos;

      for(int srcUntil = srcPos + length; i < srcUntil; ++j) {
         ScalaRunTime$.MODULE$.array_update(dest, j, ScalaRunTime$.MODULE$.array_apply(src, i));
         ++i;
      }

   }

   public void copy(final Object src, final int srcPos, final Object dest, final int destPos, final int length) {
      Class srcClass = src.getClass();
      if (srcClass.isArray() && dest.getClass().isAssignableFrom(srcClass)) {
         System.arraycopy(src, srcPos, dest, destPos, length);
      } else {
         this.slowcopy(src, srcPos, dest, destPos, length);
      }
   }

   public Object copyOf(final Object original, final int newLength) {
      if (original instanceof BoxedUnit[]) {
         BoxedUnit[] newUnitArray_result = new BoxedUnit[newLength];
         Arrays.fill(newUnitArray_result, BoxedUnit.UNIT);
         return newUnitArray_result;
      } else if (original instanceof Object[]) {
         return Arrays.copyOf(original, newLength);
      } else if (original instanceof int[]) {
         return Arrays.copyOf((int[])original, newLength);
      } else if (original instanceof double[]) {
         return Arrays.copyOf((double[])original, newLength);
      } else if (original instanceof long[]) {
         return Arrays.copyOf((long[])original, newLength);
      } else if (original instanceof float[]) {
         return Arrays.copyOf((float[])original, newLength);
      } else if (original instanceof char[]) {
         return Arrays.copyOf((char[])original, newLength);
      } else if (original instanceof byte[]) {
         return Arrays.copyOf((byte[])original, newLength);
      } else if (original instanceof short[]) {
         return Arrays.copyOf((short[])original, newLength);
      } else if (original instanceof boolean[]) {
         return Arrays.copyOf((boolean[])original, newLength);
      } else {
         throw new MatchError(original);
      }
   }

   public Object copyAs(final Object original, final int newLength, final ClassTag ct) {
      label24: {
         Class runtimeClass = ct.runtimeClass();
         Class var5 = Void.TYPE;
         if (runtimeClass == null) {
            if (var5 == null) {
               break label24;
            }
         } else if (runtimeClass.equals(var5)) {
            break label24;
         }

         if (runtimeClass.isAssignableFrom(original.getClass().getComponentType())) {
            if (runtimeClass.isPrimitive()) {
               return this.copyOf(original, newLength);
            }

            Class destArrayClass = java.lang.reflect.Array.newInstance(runtimeClass, 0).getClass();
            return Arrays.copyOf(original, newLength, destArrayClass);
         }

         Object dest = ct.newArray(newLength);
         this.copy(original, 0, dest, 0, java.lang.reflect.Array.getLength(original));
         return dest;
      }

      BoxedUnit[] newUnitArray_result = new BoxedUnit[newLength];
      Arrays.fill(newUnitArray_result, BoxedUnit.UNIT);
      return newUnitArray_result;
   }

   private BoxedUnit[] newUnitArray(final int len) {
      BoxedUnit[] result = new BoxedUnit[len];
      Arrays.fill(result, BoxedUnit.UNIT);
      return result;
   }

   public Object empty(final ClassTag evidence$4) {
      return evidence$4.newArray(0);
   }

   public Object apply(final Seq xs, final ClassTag evidence$5) {
      int len = xs.length();
      if (xs instanceof ArraySeq) {
         ArraySeq var4 = (ArraySeq)xs;
         Class var10000 = var4.unsafeArray().getClass().getComponentType();
         scala.reflect.package$ var10001 = scala.reflect.package$.MODULE$;
         Class var5 = evidence$5.runtimeClass();
         if (var10000 == null) {
            if (var5 == null) {
               return ScalaRunTime$.MODULE$.array_clone(var4.unsafeArray());
            }
         } else if (var10000.equals(var5)) {
            return ScalaRunTime$.MODULE$.array_clone(var4.unsafeArray());
         }
      }

      Object array = evidence$5.newArray(len);
      Iterator iterator = xs.iterator();

      for(int i = 0; iterator.hasNext(); ++i) {
         ScalaRunTime$.MODULE$.array_update(array, i, iterator.next());
      }

      return array;
   }

   public boolean[] apply(final boolean x, final Seq xs) {
      boolean[] array = new boolean[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = BoxesRunTime.unboxToBoolean(iterator.next());
      }

      return array;
   }

   public byte[] apply(final byte x, final Seq xs) {
      byte[] array = new byte[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = BoxesRunTime.unboxToByte(iterator.next());
      }

      return array;
   }

   public short[] apply(final short x, final Seq xs) {
      short[] array = new short[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = BoxesRunTime.unboxToShort(iterator.next());
      }

      return array;
   }

   public char[] apply(final char x, final Seq xs) {
      char[] array = new char[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = BoxesRunTime.unboxToChar(iterator.next());
      }

      return array;
   }

   public int[] apply(final int x, final Seq xs) {
      int[] array = new int[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = BoxesRunTime.unboxToInt(iterator.next());
      }

      return array;
   }

   public long[] apply(final long x, final Seq xs) {
      long[] array = new long[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = BoxesRunTime.unboxToLong(iterator.next());
      }

      return array;
   }

   public float[] apply(final float x, final Seq xs) {
      float[] array = new float[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = BoxesRunTime.unboxToFloat(iterator.next());
      }

      return array;
   }

   public double[] apply(final double x, final Seq xs) {
      double[] array = new double[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = BoxesRunTime.unboxToDouble(iterator.next());
      }

      return array;
   }

   public BoxedUnit[] apply(final BoxedUnit x, final Seq xs) {
      BoxedUnit[] array = new BoxedUnit[xs.length() + 1];
      array[0] = x;
      Iterator iterator = xs.iterator();

      for(int i = 1; iterator.hasNext(); ++i) {
         array[i] = (BoxedUnit)iterator.next();
      }

      return array;
   }

   public Object ofDim(final int n1, final ClassTag evidence$6) {
      return evidence$6.newArray(n1);
   }

   public Object[] ofDim(final int n1, final int n2, final ClassTag evidence$7) {
      Object[] arr = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$7.runtimeClass())).newArray(n1);
      RichInt$ var10000 = RichInt$.MODULE$;
      byte var5 = 0;
      Range$ var8 = Range$.MODULE$;
      Range foreach$mVc$sp_this = new Range.Exclusive(var5, n1, 1);
      if (!foreach$mVc$sp_this.isEmpty()) {
         int foreach$mVc$sp_i = foreach$mVc$sp_this.start();

         while(true) {
            arr[foreach$mVc$sp_i] = evidence$7.newArray(n2);
            if (foreach$mVc$sp_i == foreach$mVc$sp_this.scala$collection$immutable$Range$$lastElement) {
               break;
            }

            foreach$mVc$sp_i += foreach$mVc$sp_this.step();
         }
      }

      return arr;
   }

   public Object[][] ofDim(final int n1, final int n2, final int n3, final ClassTag evidence$8) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$8.runtimeClass())));
      Object var10000;
      if (n1 <= 0) {
         var10000 = tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            ScalaRunTime$.MODULE$.array_update(tabulate_array, tabulate_i, $anonfun$ofDim$2(n2, n3, evidence$8, tabulate_i));
         }

         var10000 = tabulate_array;
      }

      return var10000;
   }

   public Object[][][] ofDim(final int n1, final int n2, final int n3, final int n4, final ClassTag evidence$9) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$9.runtimeClass()))));
      Object var10000;
      if (n1 <= 0) {
         var10000 = tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            ScalaRunTime$.MODULE$.array_update(tabulate_array, tabulate_i, $anonfun$ofDim$3(n2, n3, n4, evidence$9, tabulate_i));
         }

         var10000 = tabulate_array;
      }

      return var10000;
   }

   public Object[][][][] ofDim(final int n1, final int n2, final int n3, final int n4, final int n5, final ClassTag evidence$10) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$10.runtimeClass())))));
      Object var10000;
      if (n1 <= 0) {
         var10000 = tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            ScalaRunTime$.MODULE$.array_update(tabulate_array, tabulate_i, $anonfun$ofDim$4(n2, n3, n4, n5, evidence$10, tabulate_i));
         }

         var10000 = tabulate_array;
      }

      return var10000;
   }

   public Object concat(final Seq xss, final ClassTag evidence$11) {
      Object var15;
      label113: {
         label115: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var4 = evidence$11.runtimeClass();
            Class var6 = java.lang.Byte.TYPE;
            if (var6 == null) {
               if (var4 == null) {
                  break label115;
               }
            } else if (var6.equals(var4)) {
               break label115;
            }

            label116: {
               var6 = java.lang.Short.TYPE;
               if (var6 == null) {
                  if (var4 == null) {
                     break label116;
                  }
               } else if (var6.equals(var4)) {
                  break label116;
               }

               label117: {
                  var6 = Character.TYPE;
                  if (var6 == null) {
                     if (var4 == null) {
                        break label117;
                     }
                  } else if (var6.equals(var4)) {
                     break label117;
                  }

                  label118: {
                     var6 = Integer.TYPE;
                     if (var6 == null) {
                        if (var4 == null) {
                           break label118;
                        }
                     } else if (var6.equals(var4)) {
                        break label118;
                     }

                     label119: {
                        var6 = java.lang.Long.TYPE;
                        if (var6 == null) {
                           if (var4 == null) {
                              break label119;
                           }
                        } else if (var6.equals(var4)) {
                           break label119;
                        }

                        label120: {
                           var6 = java.lang.Float.TYPE;
                           if (var6 == null) {
                              if (var4 == null) {
                                 break label120;
                              }
                           } else if (var6.equals(var4)) {
                              break label120;
                           }

                           label121: {
                              var6 = java.lang.Double.TYPE;
                              if (var6 == null) {
                                 if (var4 == null) {
                                    break label121;
                                 }
                              } else if (var6.equals(var4)) {
                                 break label121;
                              }

                              label122: {
                                 var6 = java.lang.Boolean.TYPE;
                                 if (var6 == null) {
                                    if (var4 == null) {
                                       break label122;
                                    }
                                 } else if (var6.equals(var4)) {
                                    break label122;
                                 }

                                 label56: {
                                    var6 = Void.TYPE;
                                    if (var6 == null) {
                                       if (var4 == null) {
                                          break label56;
                                       }
                                    } else if (var6.equals(var4)) {
                                       break label56;
                                    }

                                    var15 = new ArrayBuilder.ofRef(evidence$11);
                                    break label113;
                                 }

                                 var15 = new ArrayBuilder.ofUnit();
                                 break label113;
                              }

                              var15 = new ArrayBuilder.ofBoolean();
                              break label113;
                           }

                           var15 = new ArrayBuilder.ofDouble();
                           break label113;
                        }

                        var15 = new ArrayBuilder.ofFloat();
                        break label113;
                     }

                     var15 = new ArrayBuilder.ofLong();
                     break label113;
                  }

                  var15 = new ArrayBuilder.ofInt();
                  break label113;
               }

               var15 = new ArrayBuilder.ofChar();
               break label113;
            }

            var15 = new ArrayBuilder.ofShort();
            break label113;
         }

         var15 = new ArrayBuilder.ofByte();
      }

      Object var5 = null;
      ArrayBuilder b = (ArrayBuilder)var15;
      b.sizeHint(BoxesRunTime.unboxToInt(((IterableOnceOps)xss.map((x$4) -> BoxesRunTime.boxToInteger($anonfun$concat$1(x$4)))).sum(Numeric.IntIsIntegral$.MODULE$)));
      xss.foreach((xs) -> {
         IterableOnce $plus$plus$eq_elems = Predef$.MODULE$.genericWrapArray(xs);
         return (ArrayBuilder)b.addAll($plus$plus$eq_elems);
      });
      return b.result();
   }

   public Object fill(final int n, final Function0 elem, final ClassTag evidence$12) {
      if (n <= 0) {
         return evidence$12.newArray(0);
      } else {
         Object array = evidence$12.newArray(n);

         for(int i = 0; i < n; ++i) {
            ScalaRunTime$.MODULE$.array_update(array, i, elem.apply());
         }

         return array;
      }
   }

   public Object[] fill(final int n1, final int n2, final Function0 elem, final ClassTag evidence$13) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$13.runtimeClass()));
      Object var10000;
      if (n1 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            Object var10003;
            if (n2 <= 0) {
               var10003 = evidence$13.newArray(0);
            } else {
               Object fill_array = evidence$13.newArray(n2);

               for(int fill_i = 0; fill_i < n2; ++fill_i) {
                  ScalaRunTime$.MODULE$.array_update(fill_array, fill_i, elem.apply());
               }

               var10003 = fill_array;
            }

            Object var10 = null;
            var10000.array_update(tabulate_array, tabulate_i, var10003);
         }

         var10000 = (ScalaRunTime$)tabulate_array;
      }

      return var10000;
   }

   public Object[][] fill(final int n1, final int n2, final int n3, final Function0 elem, final ClassTag evidence$14) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$14.runtimeClass())));
      Object var10000;
      if (n1 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            ClassTag fill_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$14.runtimeClass()));
            Object var18;
            if (n2 <= 0) {
               var18 = (ScalaRunTime$)fill_tabulate_evidence$17.newArray(0);
            } else {
               Object fill_tabulate_array = fill_tabulate_evidence$17.newArray(n2);

               for(int fill_tabulate_i = 0; fill_tabulate_i < n2; ++fill_tabulate_i) {
                  var18 = ScalaRunTime$.MODULE$;
                  Object var10006;
                  if (n3 <= 0) {
                     var10006 = evidence$14.newArray(0);
                  } else {
                     Object fill_array = evidence$14.newArray(n3);

                     for(int fill_i = 0; fill_i < n3; ++fill_i) {
                        ScalaRunTime$.MODULE$.array_update(fill_array, fill_i, elem.apply());
                     }

                     var10006 = fill_array;
                  }

                  Object var16 = null;
                  var18.array_update(fill_tabulate_array, fill_tabulate_i, var10006);
               }

               var18 = (ScalaRunTime$)fill_tabulate_array;
            }

            Object var14 = null;
            Object var15 = null;
            var10000.array_update(tabulate_array, tabulate_i, var18);
         }

         var10000 = (ScalaRunTime$)tabulate_array;
      }

      return var10000;
   }

   public Object[][][] fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final ClassTag evidence$15) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$15.runtimeClass()))));
      Object var10000;
      if (n1 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            ClassTag fill_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$15.runtimeClass())));
            Object var25;
            if (n2 <= 0) {
               var25 = (ScalaRunTime$)fill_tabulate_evidence$17.newArray(0);
            } else {
               Object fill_tabulate_array = fill_tabulate_evidence$17.newArray(n2);

               for(int fill_tabulate_i = 0; fill_tabulate_i < n2; ++fill_tabulate_i) {
                  var25 = ScalaRunTime$.MODULE$;
                  ClassTag fill_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$15.runtimeClass()));
                  Object var26;
                  if (n3 <= 0) {
                     var26 = (ScalaRunTime$)fill_tabulate_evidence$17.newArray(0);
                  } else {
                     Object fill_tabulate_array = fill_tabulate_evidence$17.newArray(n3);

                     for(int fill_tabulate_i = 0; fill_tabulate_i < n3; ++fill_tabulate_i) {
                        var26 = ScalaRunTime$.MODULE$;
                        Object var10009;
                        if (n4 <= 0) {
                           var10009 = evidence$15.newArray(0);
                        } else {
                           Object fill_fill_array = evidence$15.newArray(n4);

                           for(int fill_fill_i = 0; fill_fill_i < n4; ++fill_fill_i) {
                              ScalaRunTime$.MODULE$.array_update(fill_fill_array, fill_fill_i, elem.apply());
                           }

                           var10009 = fill_fill_array;
                        }

                        Object var22 = null;
                        var26.array_update(fill_tabulate_array, fill_tabulate_i, var10009);
                     }

                     var26 = (ScalaRunTime$)fill_tabulate_array;
                  }

                  Object var20 = null;
                  Object var21 = null;
                  var26 = var26;
                  Object var23 = null;
                  var25.array_update(fill_tabulate_array, fill_tabulate_i, var26);
               }

               var25 = (ScalaRunTime$)fill_tabulate_array;
            }

            Object var18 = null;
            Object var19 = null;
            var10000.array_update(tabulate_array, tabulate_i, var25);
         }

         var10000 = (ScalaRunTime$)tabulate_array;
      }

      return var10000;
   }

   public Object[][][][] fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final ClassTag evidence$16) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$16.runtimeClass())))));
      Object var10000;
      if (n1 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            ClassTag fill_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$16.runtimeClass()))));
            Object var32;
            if (n2 <= 0) {
               var32 = (ScalaRunTime$)fill_tabulate_evidence$17.newArray(0);
            } else {
               Object fill_tabulate_array = fill_tabulate_evidence$17.newArray(n2);

               for(int fill_tabulate_i = 0; fill_tabulate_i < n2; ++fill_tabulate_i) {
                  var32 = ScalaRunTime$.MODULE$;
                  ClassTag fill_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$16.runtimeClass())));
                  Object var33;
                  if (n3 <= 0) {
                     var33 = (ScalaRunTime$)fill_tabulate_evidence$17.newArray(0);
                  } else {
                     Object fill_tabulate_array = fill_tabulate_evidence$17.newArray(n3);

                     for(int fill_tabulate_i = 0; fill_tabulate_i < n3; ++fill_tabulate_i) {
                        var33 = ScalaRunTime$.MODULE$;
                        ClassTag fill_fill_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$16.runtimeClass()));
                        Object var35;
                        if (n4 <= 0) {
                           var35 = (ScalaRunTime$)fill_fill_tabulate_evidence$17.newArray(0);
                        } else {
                           Object fill_fill_tabulate_array = fill_fill_tabulate_evidence$17.newArray(n4);

                           for(int fill_fill_tabulate_i = 0; fill_fill_tabulate_i < n4; ++fill_fill_tabulate_i) {
                              var35 = ScalaRunTime$.MODULE$;
                              Object var10012;
                              if (n5 <= 0) {
                                 var10012 = evidence$16.newArray(0);
                              } else {
                                 Object fill_array = evidence$16.newArray(n5);

                                 for(int fill_i = 0; fill_i < n5; ++fill_i) {
                                    ScalaRunTime$.MODULE$.array_update(fill_array, fill_i, elem.apply());
                                 }

                                 var10012 = fill_array;
                              }

                              Object var30 = null;
                              var35.array_update(fill_fill_tabulate_array, fill_fill_tabulate_i, var10012);
                           }

                           var35 = (ScalaRunTime$)fill_fill_tabulate_array;
                        }

                        Object var26 = null;
                        Object var28 = null;
                        var33.array_update(fill_tabulate_array, fill_tabulate_i, var35);
                     }

                     var33 = (ScalaRunTime$)fill_tabulate_array;
                  }

                  Object var24 = null;
                  Object var25 = null;
                  var33 = var33;
                  Object var27 = null;
                  Object var29 = null;
                  var32.array_update(fill_tabulate_array, fill_tabulate_i, var33);
               }

               var32 = (ScalaRunTime$)fill_tabulate_array;
            }

            Object var22 = null;
            Object var23 = null;
            var10000.array_update(tabulate_array, tabulate_i, var32);
         }

         var10000 = (ScalaRunTime$)tabulate_array;
      }

      return var10000;
   }

   public Object tabulate(final int n, final Function1 f, final ClassTag evidence$17) {
      if (n <= 0) {
         return evidence$17.newArray(0);
      } else {
         Object array = evidence$17.newArray(n);

         for(int i = 0; i < n; ++i) {
            ScalaRunTime$.MODULE$.array_update(array, i, f.apply(i));
         }

         return array;
      }
   }

   public Object[] tabulate(final int n1, final int n2, final Function2 f, final ClassTag evidence$18) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$18.runtimeClass()));
      Object var10000;
      if (n1 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            Object var10003;
            if (n2 <= 0) {
               var10003 = evidence$18.newArray(0);
            } else {
               Object $anonfun$tabulate$1_tabulate_array = evidence$18.newArray(n2);

               for(int $anonfun$tabulate$1_tabulate_i = 0; $anonfun$tabulate$1_tabulate_i < n2; ++$anonfun$tabulate$1_tabulate_i) {
                  ScalaRunTime$.MODULE$.array_update($anonfun$tabulate$1_tabulate_array, $anonfun$tabulate$1_tabulate_i, f.apply(tabulate_i, $anonfun$tabulate$1_tabulate_i));
               }

               var10003 = $anonfun$tabulate$1_tabulate_array;
            }

            Object var10 = null;
            var10000.array_update(tabulate_array, tabulate_i, var10003);
         }

         var10000 = (ScalaRunTime$)tabulate_array;
      }

      return var10000;
   }

   public Object[][] tabulate(final int n1, final int n2, final int n3, final Function3 f, final ClassTag evidence$19) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$19.runtimeClass())));
      Object var10000;
      if (n1 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            ClassTag $anonfun$tabulate$3_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$19.runtimeClass()));
            Object var19;
            if (n2 <= 0) {
               var19 = (ScalaRunTime$)$anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(0);
            } else {
               Object $anonfun$tabulate$3_tabulate_tabulate_array = $anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(n2);

               for(int $anonfun$tabulate$3_tabulate_tabulate_i = 0; $anonfun$tabulate$3_tabulate_tabulate_i < n2; ++$anonfun$tabulate$3_tabulate_tabulate_i) {
                  var19 = ScalaRunTime$.MODULE$;
                  Object var10006;
                  if (n3 <= 0) {
                     var10006 = evidence$19.newArray(0);
                  } else {
                     Object $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array = evidence$19.newArray(n3);

                     for(int $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i = 0; $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i < n3; ++$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i) {
                        ScalaRunTime$.MODULE$.array_update($anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array, $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i, f.apply(tabulate_i, $anonfun$tabulate$3_tabulate_tabulate_i, $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i));
                     }

                     var10006 = $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array;
                  }

                  Object var16 = null;
                  var19.array_update($anonfun$tabulate$3_tabulate_tabulate_array, $anonfun$tabulate$3_tabulate_tabulate_i, var10006);
               }

               var19 = (ScalaRunTime$)$anonfun$tabulate$3_tabulate_tabulate_array;
            }

            Object var14 = null;
            Object var15 = null;
            var19 = var19;
            Object var17 = null;
            var10000.array_update(tabulate_array, tabulate_i, var19);
         }

         var10000 = (ScalaRunTime$)tabulate_array;
      }

      return var10000;
   }

   public Object[][][] tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final ClassTag evidence$20) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$20.runtimeClass()))));
      Object var10000;
      if (n1 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            ClassTag $anonfun$tabulate$5_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$20.runtimeClass())));
            Object var28;
            if (n2 <= 0) {
               var28 = (ScalaRunTime$)$anonfun$tabulate$5_tabulate_tabulate_evidence$17.newArray(0);
            } else {
               Object $anonfun$tabulate$5_tabulate_tabulate_array = $anonfun$tabulate$5_tabulate_tabulate_evidence$17.newArray(n2);

               for(int $anonfun$tabulate$5_tabulate_tabulate_i = 0; $anonfun$tabulate$5_tabulate_tabulate_i < n2; ++$anonfun$tabulate$5_tabulate_tabulate_i) {
                  var28 = ScalaRunTime$.MODULE$;
                  ClassTag $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$20.runtimeClass()));
                  Object var30;
                  if (n3 <= 0) {
                     var30 = (ScalaRunTime$)$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(0);
                  } else {
                     Object $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array = $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(n3);

                     for(int $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i = 0; $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i < n3; ++$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i) {
                        var30 = ScalaRunTime$.MODULE$;
                        Object var10009;
                        if (n4 <= 0) {
                           var10009 = evidence$20.newArray(0);
                        } else {
                           Object $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array = evidence$20.newArray(n4);

                           for(int $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i = 0; $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i < n4; ++$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i) {
                              ScalaRunTime$.MODULE$.array_update($anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array, $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i, f.apply(tabulate_i, $anonfun$tabulate$5_tabulate_tabulate_i, $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i, $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i));
                           }

                           var10009 = $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array;
                        }

                        Object var24 = null;
                        var30.array_update($anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array, $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i, var10009);
                     }

                     var30 = (ScalaRunTime$)$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array;
                  }

                  Object var20 = null;
                  Object var22 = null;
                  var30 = var30;
                  Object var25 = null;
                  var28.array_update($anonfun$tabulate$5_tabulate_tabulate_array, $anonfun$tabulate$5_tabulate_tabulate_i, var30);
               }

               var28 = (ScalaRunTime$)$anonfun$tabulate$5_tabulate_tabulate_array;
            }

            Object var18 = null;
            Object var19 = null;
            var28 = var28;
            Object var21 = null;
            Object var23 = null;
            Object var26 = null;
            var10000.array_update(tabulate_array, tabulate_i, var28);
         }

         var10000 = (ScalaRunTime$)tabulate_array;
      }

      return var10000;
   }

   public Object[][][][] tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final ClassTag evidence$21) {
      ClassTag tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$21.runtimeClass())))));
      Object var10000;
      if (n1 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_array = tabulate_evidence$17.newArray(n1);

         for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            ClassTag $anonfun$tabulate$7_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$21.runtimeClass()))));
            Object var39;
            if (n2 <= 0) {
               var39 = (ScalaRunTime$)$anonfun$tabulate$7_tabulate_tabulate_evidence$17.newArray(0);
            } else {
               Object $anonfun$tabulate$7_tabulate_tabulate_array = $anonfun$tabulate$7_tabulate_tabulate_evidence$17.newArray(n2);

               for(int $anonfun$tabulate$7_tabulate_tabulate_i = 0; $anonfun$tabulate$7_tabulate_tabulate_i < n2; ++$anonfun$tabulate$7_tabulate_tabulate_i) {
                  var39 = ScalaRunTime$.MODULE$;
                  ClassTag $anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$21.runtimeClass())));
                  Object var41;
                  if (n3 <= 0) {
                     var41 = (ScalaRunTime$)$anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_evidence$17.newArray(0);
                  } else {
                     Object $anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_array = $anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_evidence$17.newArray(n3);

                     for(int $anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_i = 0; $anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_i < n3; ++$anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_i) {
                        var41 = ScalaRunTime$.MODULE$;
                        ClassTag $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$21.runtimeClass()));
                        Object var43;
                        if (n4 <= 0) {
                           var43 = (ScalaRunTime$)$anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(0);
                        } else {
                           Object $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array = $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(n4);

                           for(int $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i = 0; $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i < n4; ++$anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i) {
                              var43 = ScalaRunTime$.MODULE$;
                              Object var10012;
                              if (n5 <= 0) {
                                 var10012 = evidence$21.newArray(0);
                              } else {
                                 Object $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array = evidence$21.newArray(n5);

                                 for(int $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i = 0; $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i < n5; ++$anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i) {
                                    ScalaRunTime$.MODULE$.array_update($anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array, $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i, f.apply(tabulate_i, $anonfun$tabulate$7_tabulate_tabulate_i, $anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_i, $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i, $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i));
                                 }

                                 var10012 = $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array;
                              }

                              Object var34 = null;
                              var43.array_update($anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array, $anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i, var10012);
                           }

                           var43 = (ScalaRunTime$)$anonfun$tabulate$7_$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array;
                        }

                        Object var28 = null;
                        Object var31 = null;
                        var43 = var43;
                        Object var35 = null;
                        var41.array_update($anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_array, $anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_i, var43);
                     }

                     var41 = (ScalaRunTime$)$anonfun$tabulate$7_$anonfun$tabulate$5_tabulate_tabulate_array;
                  }

                  Object var24 = null;
                  Object var26 = null;
                  var41 = var41;
                  Object var29 = null;
                  Object var32 = null;
                  Object var36 = null;
                  var39.array_update($anonfun$tabulate$7_tabulate_tabulate_array, $anonfun$tabulate$7_tabulate_tabulate_i, var41);
               }

               var39 = (ScalaRunTime$)$anonfun$tabulate$7_tabulate_tabulate_array;
            }

            Object var22 = null;
            Object var23 = null;
            var39 = var39;
            Object var25 = null;
            Object var27 = null;
            Object var30 = null;
            Object var33 = null;
            Object var37 = null;
            var10000.array_update(tabulate_array, tabulate_i, var39);
         }

         var10000 = (ScalaRunTime$)tabulate_array;
      }

      return var10000;
   }

   public int[] range(final int start, final int end) {
      return this.range(start, end, 1);
   }

   public int[] range(final int start, final int end, final int step) {
      if (step == 0) {
         throw new IllegalArgumentException("zero step");
      } else {
         int[] array = new int[Range$.MODULE$.count(start, end, step, false)];
         int n = 0;
         int i = start;

         while(true) {
            if (step < 0) {
               if (end >= i) {
                  break;
               }
            } else if (i >= end) {
               break;
            }

            array[n] = i;
            i += step;
            ++n;
         }

         return array;
      }
   }

   public Object iterate(final Object start, final int len, final Function1 f, final ClassTag evidence$22) {
      if (len <= 0) {
         return evidence$22.newArray(0);
      } else {
         Object array = evidence$22.newArray(len);
         Object acc = start;
         int i = 1;
         ScalaRunTime$.MODULE$.array_update(array, 0, start);

         while(i < len) {
            acc = f.apply(acc);
            ScalaRunTime$.MODULE$.array_update(array, i, acc);
            ++i;
         }

         return array;
      }
   }

   public boolean equals(final Object[] xs, final Object[] ys) {
      if (xs != ys) {
         if (xs.length == ys.length) {
            int i;
            for(i = 0; i < xs.length && BoxesRunTime.equals(xs[i], ys[i]); ++i) {
            }

            if (i >= xs.length) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public Object unapplySeq(final Object x) {
      return x;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Array$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$ofDim$1(final Object[] arr$1, final ClassTag evidence$7$1, final int n2$1, final int i) {
      arr$1[i] = evidence$7$1.newArray(n2$1);
   }

   // $FF: synthetic method
   public static final Object[] $anonfun$ofDim$2(final int n2$2, final int n3$1, final ClassTag evidence$8$1, final int x$1) {
      return MODULE$.ofDim(n2$2, n3$1, evidence$8$1);
   }

   // $FF: synthetic method
   public static final Object[][] $anonfun$ofDim$3(final int n2$3, final int n3$2, final int n4$1, final ClassTag evidence$9$1, final int x$2) {
      return MODULE$.ofDim(n2$3, n3$2, n4$1, evidence$9$1);
   }

   // $FF: synthetic method
   public static final Object[][][] $anonfun$ofDim$4(final int n2$4, final int n3$3, final int n4$2, final int n5$1, final ClassTag evidence$10$1, final int x$3) {
      return MODULE$.ofDim(n2$4, n3$3, n4$2, n5$1, evidence$10$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$concat$1(final Object x$4) {
      return ScalaRunTime$.MODULE$.array_length(x$4);
   }

   // $FF: synthetic method
   public static final Object $anonfun$fill$1(final int n2$5, final Function0 elem$1, final ClassTag evidence$13$1, final int x$5) {
      return MODULE$.fill(n2$5, elem$1, evidence$13$1);
   }

   // $FF: synthetic method
   public static final Object[] $anonfun$fill$2(final int n2$6, final int n3$4, final Function0 elem$2, final ClassTag evidence$14$1, final int x$6) {
      return MODULE$.fill(n2$6, n3$4, elem$2, evidence$14$1);
   }

   // $FF: synthetic method
   public static final Object[][] $anonfun$fill$3(final int n2$7, final int n3$5, final int n4$3, final Function0 elem$3, final ClassTag evidence$15$1, final int x$7) {
      return MODULE$.fill(n2$7, n3$5, n4$3, elem$3, evidence$15$1);
   }

   // $FF: synthetic method
   public static final Object[][][] $anonfun$fill$4(final int n2$8, final int n3$6, final int n4$4, final int n5$2, final Function0 elem$4, final ClassTag evidence$16$1, final int x$8) {
      return MODULE$.fill(n2$8, n3$6, n4$4, n5$2, elem$4, evidence$16$1);
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$2(final Function2 f$1, final int i1$1, final int x$9) {
      return f$1.apply(BoxesRunTime.boxToInteger(i1$1), BoxesRunTime.boxToInteger(x$9));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$1(final int n2$9, final Function2 f$1, final ClassTag evidence$18$1, final int i1) {
      if (n2$9 <= 0) {
         return evidence$18$1.newArray(0);
      } else {
         Object tabulate_array = evidence$18$1.newArray(n2$9);

         for(int tabulate_i = 0; tabulate_i < n2$9; ++tabulate_i) {
            ScalaRunTime$.MODULE$.array_update(tabulate_array, tabulate_i, f$1.apply(i1, tabulate_i));
         }

         return tabulate_array;
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$4(final Function3 f$2, final int i1$2, final int x$10, final int x$11) {
      return f$2.apply(BoxesRunTime.boxToInteger(i1$2), BoxesRunTime.boxToInteger(x$10), BoxesRunTime.boxToInteger(x$11));
   }

   // $FF: synthetic method
   public static final Object[] $anonfun$tabulate$3(final int n2$10, final int n3$7, final Function3 f$2, final ClassTag evidence$19$1, final int i1) {
      ClassTag tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$19$1.runtimeClass()));
      Object var10000;
      if (n2$10 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_tabulate_array = tabulate_tabulate_evidence$17.newArray(n2$10);

         for(int tabulate_tabulate_i = 0; tabulate_tabulate_i < n2$10; ++tabulate_tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            Object var10003;
            if (n3$7 <= 0) {
               var10003 = evidence$19$1.newArray(0);
            } else {
               Object $anonfun$tabulate$1_tabulate_array = evidence$19$1.newArray(n3$7);

               for(int $anonfun$tabulate$1_tabulate_i = 0; $anonfun$tabulate$1_tabulate_i < n3$7; ++$anonfun$tabulate$1_tabulate_i) {
                  ScalaRunTime$.MODULE$.array_update($anonfun$tabulate$1_tabulate_array, $anonfun$tabulate$1_tabulate_i, f$2.apply(i1, tabulate_tabulate_i, $anonfun$tabulate$1_tabulate_i));
               }

               var10003 = $anonfun$tabulate$1_tabulate_array;
            }

            Object var10 = null;
            var10000.array_update(tabulate_tabulate_array, tabulate_tabulate_i, var10003);
         }

         var10000 = (ScalaRunTime$)tabulate_tabulate_array;
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$6(final Function4 f$3, final int i1$3, final int x$12, final int x$13, final int x$14) {
      return f$3.apply(BoxesRunTime.boxToInteger(i1$3), BoxesRunTime.boxToInteger(x$12), BoxesRunTime.boxToInteger(x$13), BoxesRunTime.boxToInteger(x$14));
   }

   // $FF: synthetic method
   public static final Object[][] $anonfun$tabulate$5(final int n2$11, final int n3$8, final int n4$5, final Function4 f$3, final ClassTag evidence$20$1, final int i1) {
      ClassTag tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$20$1.runtimeClass())));
      Object var10000;
      if (n2$11 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_tabulate_array = tabulate_tabulate_evidence$17.newArray(n2$11);

         for(int tabulate_tabulate_i = 0; tabulate_tabulate_i < n2$11; ++tabulate_tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            ClassTag $anonfun$tabulate$3_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$20$1.runtimeClass()));
            Object var19;
            if (n3$8 <= 0) {
               var19 = (ScalaRunTime$)$anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(0);
            } else {
               Object $anonfun$tabulate$3_tabulate_tabulate_array = $anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(n3$8);

               for(int $anonfun$tabulate$3_tabulate_tabulate_i = 0; $anonfun$tabulate$3_tabulate_tabulate_i < n3$8; ++$anonfun$tabulate$3_tabulate_tabulate_i) {
                  var19 = ScalaRunTime$.MODULE$;
                  Object var10006;
                  if (n4$5 <= 0) {
                     var10006 = evidence$20$1.newArray(0);
                  } else {
                     Object $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array = evidence$20$1.newArray(n4$5);

                     for(int $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i = 0; $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i < n4$5; ++$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i) {
                        ScalaRunTime$.MODULE$.array_update($anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array, $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i, f$3.apply(i1, tabulate_tabulate_i, $anonfun$tabulate$3_tabulate_tabulate_i, $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i));
                     }

                     var10006 = $anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array;
                  }

                  Object var16 = null;
                  var19.array_update($anonfun$tabulate$3_tabulate_tabulate_array, $anonfun$tabulate$3_tabulate_tabulate_i, var10006);
               }

               var19 = (ScalaRunTime$)$anonfun$tabulate$3_tabulate_tabulate_array;
            }

            Object var14 = null;
            Object var15 = null;
            var19 = var19;
            Object var17 = null;
            var10000.array_update(tabulate_tabulate_array, tabulate_tabulate_i, var19);
         }

         var10000 = (ScalaRunTime$)tabulate_tabulate_array;
      }

      return var10000;
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$8(final Function5 f$4, final int i1$4, final int x$15, final int x$16, final int x$17, final int x$18) {
      return f$4.apply(BoxesRunTime.boxToInteger(i1$4), BoxesRunTime.boxToInteger(x$15), BoxesRunTime.boxToInteger(x$16), BoxesRunTime.boxToInteger(x$17), BoxesRunTime.boxToInteger(x$18));
   }

   // $FF: synthetic method
   public static final Object[][][] $anonfun$tabulate$7(final int n2$12, final int n3$9, final int n4$6, final int n5$3, final Function5 f$4, final ClassTag evidence$21$1, final int i1) {
      ClassTag tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$21$1.runtimeClass()))));
      Object var10000;
      if (n2$12 <= 0) {
         var10000 = (ScalaRunTime$)tabulate_tabulate_evidence$17.newArray(0);
      } else {
         Object tabulate_tabulate_array = tabulate_tabulate_evidence$17.newArray(n2$12);

         for(int tabulate_tabulate_i = 0; tabulate_tabulate_i < n2$12; ++tabulate_tabulate_i) {
            var10000 = ScalaRunTime$.MODULE$;
            ClassTag $anonfun$tabulate$5_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(ScalaRunTime$.MODULE$.arrayClass(evidence$21$1.runtimeClass())));
            Object var28;
            if (n3$9 <= 0) {
               var28 = (ScalaRunTime$)$anonfun$tabulate$5_tabulate_tabulate_evidence$17.newArray(0);
            } else {
               Object $anonfun$tabulate$5_tabulate_tabulate_array = $anonfun$tabulate$5_tabulate_tabulate_evidence$17.newArray(n3$9);

               for(int $anonfun$tabulate$5_tabulate_tabulate_i = 0; $anonfun$tabulate$5_tabulate_tabulate_i < n3$9; ++$anonfun$tabulate$5_tabulate_tabulate_i) {
                  var28 = ScalaRunTime$.MODULE$;
                  ClassTag $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17 = ClassTag$.MODULE$.apply(ScalaRunTime$.MODULE$.arrayClass(evidence$21$1.runtimeClass()));
                  Object var30;
                  if (n4$6 <= 0) {
                     var30 = (ScalaRunTime$)$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(0);
                  } else {
                     Object $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array = $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_evidence$17.newArray(n4$6);

                     for(int $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i = 0; $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i < n4$6; ++$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i) {
                        var30 = ScalaRunTime$.MODULE$;
                        Object var10009;
                        if (n5$3 <= 0) {
                           var10009 = evidence$21$1.newArray(0);
                        } else {
                           Object $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array = evidence$21$1.newArray(n5$3);

                           for(int $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i = 0; $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i < n5$3; ++$anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i) {
                              ScalaRunTime$.MODULE$.array_update($anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array, $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i, f$4.apply(i1, tabulate_tabulate_i, $anonfun$tabulate$5_tabulate_tabulate_i, $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i, $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_i));
                           }

                           var10009 = $anonfun$tabulate$5_$anonfun$tabulate$3_$anonfun$tabulate$1_tabulate_array;
                        }

                        Object var24 = null;
                        var30.array_update($anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array, $anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_i, var10009);
                     }

                     var30 = (ScalaRunTime$)$anonfun$tabulate$5_$anonfun$tabulate$3_tabulate_tabulate_array;
                  }

                  Object var20 = null;
                  Object var22 = null;
                  var30 = var30;
                  Object var25 = null;
                  var28.array_update($anonfun$tabulate$5_tabulate_tabulate_array, $anonfun$tabulate$5_tabulate_tabulate_i, var30);
               }

               var28 = (ScalaRunTime$)$anonfun$tabulate$5_tabulate_tabulate_array;
            }

            Object var18 = null;
            Object var19 = null;
            var28 = var28;
            Object var21 = null;
            Object var23 = null;
            Object var26 = null;
            var10000.array_update(tabulate_tabulate_array, tabulate_tabulate_i, var28);
         }

         var10000 = (ScalaRunTime$)tabulate_tabulate_array;
      }

      return var10000;
   }

   private Array$() {
   }

   // $FF: synthetic method
   public static final Object[] $anonfun$ofDim$2$adapted(final int n2$2, final int n3$1, final ClassTag evidence$8$1, final Object x$1) {
      return $anonfun$ofDim$2(n2$2, n3$1, evidence$8$1, BoxesRunTime.unboxToInt(x$1));
   }

   // $FF: synthetic method
   public static final Object[][] $anonfun$ofDim$3$adapted(final int n2$3, final int n3$2, final int n4$1, final ClassTag evidence$9$1, final Object x$2) {
      return $anonfun$ofDim$3(n2$3, n3$2, n4$1, evidence$9$1, BoxesRunTime.unboxToInt(x$2));
   }

   // $FF: synthetic method
   public static final Object[][][] $anonfun$ofDim$4$adapted(final int n2$4, final int n3$3, final int n4$2, final int n5$1, final ClassTag evidence$10$1, final Object x$3) {
      return $anonfun$ofDim$4(n2$4, n3$3, n4$2, n5$1, evidence$10$1, BoxesRunTime.unboxToInt(x$3));
   }

   // $FF: synthetic method
   public static final Object $anonfun$fill$1$adapted(final int n2$5, final Function0 elem$1, final ClassTag evidence$13$1, final Object x$5) {
      return $anonfun$fill$1(n2$5, elem$1, evidence$13$1, BoxesRunTime.unboxToInt(x$5));
   }

   // $FF: synthetic method
   public static final Object[] $anonfun$fill$2$adapted(final int n2$6, final int n3$4, final Function0 elem$2, final ClassTag evidence$14$1, final Object x$6) {
      return $anonfun$fill$2(n2$6, n3$4, elem$2, evidence$14$1, BoxesRunTime.unboxToInt(x$6));
   }

   // $FF: synthetic method
   public static final Object[][] $anonfun$fill$3$adapted(final int n2$7, final int n3$5, final int n4$3, final Function0 elem$3, final ClassTag evidence$15$1, final Object x$7) {
      return $anonfun$fill$3(n2$7, n3$5, n4$3, elem$3, evidence$15$1, BoxesRunTime.unboxToInt(x$7));
   }

   // $FF: synthetic method
   public static final Object[][][] $anonfun$fill$4$adapted(final int n2$8, final int n3$6, final int n4$4, final int n5$2, final Function0 elem$4, final ClassTag evidence$16$1, final Object x$8) {
      return $anonfun$fill$4(n2$8, n3$6, n4$4, n5$2, elem$4, evidence$16$1, BoxesRunTime.unboxToInt(x$8));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$1$adapted(final int n2$9, final Function2 f$1, final ClassTag evidence$18$1, final Object i1) {
      return $anonfun$tabulate$1(n2$9, f$1, evidence$18$1, BoxesRunTime.unboxToInt(i1));
   }

   // $FF: synthetic method
   public static final Object[] $anonfun$tabulate$3$adapted(final int n2$10, final int n3$7, final Function3 f$2, final ClassTag evidence$19$1, final Object i1) {
      return $anonfun$tabulate$3(n2$10, n3$7, f$2, evidence$19$1, BoxesRunTime.unboxToInt(i1));
   }

   // $FF: synthetic method
   public static final Object[][] $anonfun$tabulate$5$adapted(final int n2$11, final int n3$8, final int n4$5, final Function4 f$3, final ClassTag evidence$20$1, final Object i1) {
      return $anonfun$tabulate$5(n2$11, n3$8, n4$5, f$3, evidence$20$1, BoxesRunTime.unboxToInt(i1));
   }

   // $FF: synthetic method
   public static final Object[][][] $anonfun$tabulate$7$adapted(final int n2$12, final int n3$9, final int n4$6, final int n5$3, final Function5 f$4, final ClassTag evidence$21$1, final Object i1) {
      return $anonfun$tabulate$7(n2$12, n3$9, n4$6, n5$3, f$4, evidence$21$1, BoxesRunTime.unboxToInt(i1));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$2$adapted(final Function2 f$1, final int i1$1, final Object x$9) {
      return $anonfun$tabulate$2(f$1, i1$1, BoxesRunTime.unboxToInt(x$9));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$4$adapted(final Function3 f$2, final int i1$2, final Object x$10, final Object x$11) {
      return $anonfun$tabulate$4(f$2, i1$2, BoxesRunTime.unboxToInt(x$10), BoxesRunTime.unboxToInt(x$11));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$6$adapted(final Function4 f$3, final int i1$3, final Object x$12, final Object x$13, final Object x$14) {
      return $anonfun$tabulate$6(f$3, i1$3, BoxesRunTime.unboxToInt(x$12), BoxesRunTime.unboxToInt(x$13), BoxesRunTime.unboxToInt(x$14));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$8$adapted(final Function5 f$4, final int i1$4, final Object x$15, final Object x$16, final Object x$17, final Object x$18) {
      return $anonfun$tabulate$8(f$4, i1$4, BoxesRunTime.unboxToInt(x$15), BoxesRunTime.unboxToInt(x$16), BoxesRunTime.unboxToInt(x$17), BoxesRunTime.unboxToInt(x$18));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
