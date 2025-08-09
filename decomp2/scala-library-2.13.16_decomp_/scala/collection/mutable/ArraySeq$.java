package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.collection.ClassTagIterableFactory;
import scala.collection.ClassTagSeqFactory;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedClassTagSeqFactory;
import scala.math.Integral;
import scala.reflect.ClassTag;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;

public final class ArraySeq$ implements StrictOptimizedClassTagSeqFactory {
   public static final ArraySeq$ MODULE$ = new ArraySeq$();
   private static final long serialVersionUID = 3L;
   private static final SeqFactory untagged;
   private static final ArraySeq.ofRef EmptyArraySeq;

   static {
      ArraySeq$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      untagged = new ClassTagSeqFactory.AnySeqDelegate(MODULE$);
      EmptyArraySeq = new ArraySeq.ofRef(new Object[0]);
   }

   public scala.collection.SeqOps fill(final int n, final Function0 elem, final ClassTag evidence$34) {
      return StrictOptimizedClassTagSeqFactory.fill$(this, n, elem, evidence$34);
   }

   public scala.collection.SeqOps tabulate(final int n, final Function1 f, final ClassTag evidence$35) {
      return StrictOptimizedClassTagSeqFactory.tabulate$(this, n, f, evidence$35);
   }

   public final scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      return ClassTagSeqFactory.unapplySeq$(this, x);
   }

   public Object range(final Object start, final Object end, final Integral evidence$22, final ClassTag evidence$23) {
      return ClassTagIterableFactory.range$(this, start, end, evidence$22, evidence$23);
   }

   public Object range(final Object start, final Object end, final Object step, final Integral evidence$24, final ClassTag evidence$25) {
      return ClassTagIterableFactory.range$(this, start, end, step, evidence$24, evidence$25);
   }

   public Object fill(final int n1, final int n2, final Function0 elem, final ClassTag evidence$26) {
      return ClassTagIterableFactory.fill$(this, n1, n2, elem, evidence$26);
   }

   public Object fill(final int n1, final int n2, final int n3, final Function0 elem, final ClassTag evidence$27) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, elem, evidence$27);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final ClassTag evidence$28) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, n4, elem, evidence$28);
   }

   public Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final ClassTag evidence$29) {
      return ClassTagIterableFactory.fill$(this, n1, n2, n3, n4, n5, elem, evidence$29);
   }

   public Object tabulate(final int n1, final int n2, final Function2 f, final ClassTag evidence$30) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, f, evidence$30);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final Function3 f, final ClassTag evidence$31) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, f, evidence$31);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final ClassTag evidence$32) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, n4, f, evidence$32);
   }

   public Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final ClassTag evidence$33) {
      return ClassTagIterableFactory.tabulate$(this, n1, n2, n3, n4, n5, f, evidence$33);
   }

   public Object apply(final scala.collection.immutable.Seq xs, final Object evidence$7) {
      return EvidenceIterableFactory.apply$(this, xs, evidence$7);
   }

   public Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      return EvidenceIterableFactory.iterate$(this, start, len, f, evidence$10);
   }

   public Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      return EvidenceIterableFactory.unfold$(this, init, f, evidence$11);
   }

   public Factory evidenceIterableFactory(final Object evidence$13) {
      return EvidenceIterableFactory.evidenceIterableFactory$(this, evidence$13);
   }

   public SeqFactory untagged() {
      return untagged;
   }

   public ArraySeq empty(final ClassTag evidence$1) {
      return EmptyArraySeq;
   }

   public ArraySeq from(final IterableOnce it, final ClassTag evidence$2) {
      return this.make(Array$.MODULE$.from(it, evidence$2));
   }

   public Builder newBuilder(final ClassTag evidence$3) {
      Object var13;
      label113: {
         label115: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            Class var2 = evidence$3.runtimeClass();
            Class var4 = Byte.TYPE;
            if (var4 == null) {
               if (var2 == null) {
                  break label115;
               }
            } else if (var4.equals(var2)) {
               break label115;
            }

            label116: {
               var4 = Short.TYPE;
               if (var4 == null) {
                  if (var2 == null) {
                     break label116;
                  }
               } else if (var4.equals(var2)) {
                  break label116;
               }

               label117: {
                  var4 = Character.TYPE;
                  if (var4 == null) {
                     if (var2 == null) {
                        break label117;
                     }
                  } else if (var4.equals(var2)) {
                     break label117;
                  }

                  label118: {
                     var4 = Integer.TYPE;
                     if (var4 == null) {
                        if (var2 == null) {
                           break label118;
                        }
                     } else if (var4.equals(var2)) {
                        break label118;
                     }

                     label119: {
                        var4 = Long.TYPE;
                        if (var4 == null) {
                           if (var2 == null) {
                              break label119;
                           }
                        } else if (var4.equals(var2)) {
                           break label119;
                        }

                        label120: {
                           var4 = Float.TYPE;
                           if (var4 == null) {
                              if (var2 == null) {
                                 break label120;
                              }
                           } else if (var4.equals(var2)) {
                              break label120;
                           }

                           label121: {
                              var4 = Double.TYPE;
                              if (var4 == null) {
                                 if (var2 == null) {
                                    break label121;
                                 }
                              } else if (var4.equals(var2)) {
                                 break label121;
                              }

                              label122: {
                                 var4 = Boolean.TYPE;
                                 if (var4 == null) {
                                    if (var2 == null) {
                                       break label122;
                                    }
                                 } else if (var4.equals(var2)) {
                                    break label122;
                                 }

                                 label56: {
                                    var4 = Void.TYPE;
                                    if (var4 == null) {
                                       if (var2 == null) {
                                          break label56;
                                       }
                                    } else if (var4.equals(var2)) {
                                       break label56;
                                    }

                                    var13 = new ArrayBuilder.ofRef(evidence$3);
                                    break label113;
                                 }

                                 var13 = new ArrayBuilder.ofUnit();
                                 break label113;
                              }

                              var13 = new ArrayBuilder.ofBoolean();
                              break label113;
                           }

                           var13 = new ArrayBuilder.ofDouble();
                           break label113;
                        }

                        var13 = new ArrayBuilder.ofFloat();
                        break label113;
                     }

                     var13 = new ArrayBuilder.ofLong();
                     break label113;
                  }

                  var13 = new ArrayBuilder.ofInt();
                  break label113;
               }

               var13 = new ArrayBuilder.ofChar();
               break label113;
            }

            var13 = new ArrayBuilder.ofShort();
            break label113;
         }

         var13 = new ArrayBuilder.ofByte();
      }

      Object var3 = null;
      return ((ArrayBuilder)var13).mapResult((x) -> MODULE$.make(x));
   }

   public ArraySeq make(final Object x) {
      if (x == null) {
         return null;
      } else if (x instanceof Object[]) {
         Object[] var2 = x;
         return new ArraySeq.ofRef(var2);
      } else if (x instanceof int[]) {
         int[] var3 = (int[])x;
         return new ArraySeq.ofInt(var3);
      } else if (x instanceof double[]) {
         double[] var4 = (double[])x;
         return new ArraySeq.ofDouble(var4);
      } else if (x instanceof long[]) {
         long[] var5 = (long[])x;
         return new ArraySeq.ofLong(var5);
      } else if (x instanceof float[]) {
         float[] var6 = (float[])x;
         return new ArraySeq.ofFloat(var6);
      } else if (x instanceof char[]) {
         char[] var7 = (char[])x;
         return new ArraySeq.ofChar(var7);
      } else if (x instanceof byte[]) {
         byte[] var8 = (byte[])x;
         return new ArraySeq.ofByte(var8);
      } else if (x instanceof short[]) {
         short[] var9 = (short[])x;
         return new ArraySeq.ofShort(var9);
      } else if (x instanceof boolean[]) {
         boolean[] var10 = (boolean[])x;
         return new ArraySeq.ofBoolean(var10);
      } else if (x instanceof BoxedUnit[]) {
         BoxedUnit[] var11 = (BoxedUnit[])x;
         return new ArraySeq.ofUnit(var11);
      } else {
         throw new MatchError(x);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ArraySeq$.class);
   }

   private ArraySeq$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
