package scala.collection.immutable;

import java.lang.reflect.Array;
import java.util.Arrays;
import scala.Function1;
import scala.collection.IterableOnce;

public final class VectorStatics$ {
   public static final VectorStatics$ MODULE$ = new VectorStatics$();
   private static final Object[] empty1 = new Object[0];
   private static final Object[][] empty2 = new Object[0][];
   private static final Object[][][] empty3 = new Object[0][][];
   private static final Object[][][][] empty4 = new Object[0][][][];
   private static final Object[][][][][] empty5 = new Object[0][][][][];
   private static final Object[][][][][][] empty6 = new Object[0][][][][][];

   public final Object[] copyAppend1(final Object[] a, final Object elem) {
      int alen = a.length;
      Object[] ac = new Object[alen + 1];
      System.arraycopy(a, 0, ac, 0, alen);
      ac[alen] = elem;
      return ac;
   }

   public final Object[] copyAppend(final Object[] a, final Object elem) {
      Object[] ac = Arrays.copyOf(a, a.length + 1);
      ac[ac.length - 1] = elem;
      return ac;
   }

   public final Object[] copyPrepend1(final Object elem, final Object[] a) {
      Object[] ac = new Object[a.length + 1];
      System.arraycopy(a, 0, ac, 1, a.length);
      ac[0] = elem;
      return ac;
   }

   public final Object[] copyPrepend(final Object elem, final Object[] a) {
      Object[] ac = Array.newInstance(a.getClass().getComponentType(), a.length + 1);
      System.arraycopy(a, 0, ac, 1, a.length);
      ac[0] = elem;
      return ac;
   }

   public final Object[] empty1() {
      return empty1;
   }

   public final Object[][] empty2() {
      return empty2;
   }

   public final Object[][][] empty3() {
      return empty3;
   }

   public final Object[][][][] empty4() {
      return empty4;
   }

   public final Object[][][][][] empty5() {
      return empty5;
   }

   public final Object[][][][][][] empty6() {
      return empty6;
   }

   public final void foreachRec(final int level, final Object[] a, final Function1 f) {
      int i = 0;
      int len = a.length;
      if (level == 0) {
         while(i < len) {
            f.apply(a[i]);
            ++i;
         }

      } else {
         for(int l = level - 1; i < len; ++i) {
            this.foreachRec(l, a[i], f);
         }

      }
   }

   public final Object[] mapElems1(final Object[] a, final Function1 f) {
      for(int i = 0; i < a.length; ++i) {
         Object v1 = a[i];
         Object v2 = f.apply(v1);
         if (v1 != v2) {
            Object[] mapElems1Rest_ac = new Object[a.length];
            if (i > 0) {
               System.arraycopy(a, 0, mapElems1Rest_ac, 0, i);
            }

            mapElems1Rest_ac[i] = v2;

            for(int mapElems1Rest_i = i + 1; mapElems1Rest_i < a.length; ++mapElems1Rest_i) {
               mapElems1Rest_ac[mapElems1Rest_i] = f.apply(a[mapElems1Rest_i]);
            }

            return mapElems1Rest_ac;
         }
      }

      return a;
   }

   public final Object[] mapElems1Rest(final Object[] a, final Function1 f, final int at, final Object v2) {
      Object[] ac = new Object[a.length];
      if (at > 0) {
         System.arraycopy(a, 0, ac, 0, at);
      }

      ac[at] = v2;

      for(int i = at + 1; i < a.length; ++i) {
         ac[i] = f.apply(a[i]);
      }

      return ac;
   }

   public final Object[] mapElems(final int n, final Object[] a, final Function1 f) {
      if (n == 1) {
         for(int mapElems1_i = 0; mapElems1_i < a.length; ++mapElems1_i) {
            Object mapElems1_v1 = a[mapElems1_i];
            Object mapElems1_v2 = f.apply(mapElems1_v1);
            if (mapElems1_v1 != mapElems1_v2) {
               Object[] mapElems1_mapElems1Rest_ac = new Object[a.length];
               if (mapElems1_i > 0) {
                  System.arraycopy(a, 0, mapElems1_mapElems1Rest_ac, 0, mapElems1_i);
               }

               mapElems1_mapElems1Rest_ac[mapElems1_i] = mapElems1_v2;

               for(int mapElems1_mapElems1Rest_i = mapElems1_i + 1; mapElems1_mapElems1Rest_i < a.length; ++mapElems1_mapElems1Rest_i) {
                  mapElems1_mapElems1Rest_ac[mapElems1_mapElems1Rest_i] = f.apply(a[mapElems1_mapElems1Rest_i]);
               }

               return mapElems1_mapElems1Rest_ac;
            }
         }

         return a;
      } else {
         for(int i = 0; i < a.length; ++i) {
            Object v1 = a[i];
            Object[] v2 = this.mapElems(n - 1, v1, f);
            if (v1 != v2) {
               Object[] mapElemsRest_ac = Array.newInstance(a.getClass().getComponentType(), a.length);
               if (i > 0) {
                  System.arraycopy(a, 0, mapElemsRest_ac, 0, i);
               }

               mapElemsRest_ac[i] = v2;

               for(int mapElemsRest_i = i + 1; mapElemsRest_i < a.length; ++mapElemsRest_i) {
                  int var10002 = n - 1;
                  Object[] mapElemsRest_mapElems_a = a[mapElemsRest_i];
                  int mapElemsRest_mapElems_n = var10002;
                  Object[] var34;
                  if (mapElemsRest_mapElems_n != 1) {
                     int mapElemsRest_mapElems_i = 0;

                     while(true) {
                        if (mapElemsRest_mapElems_i >= mapElemsRest_mapElems_a.length) {
                           var34 = mapElemsRest_mapElems_a;
                           break;
                        }

                        Object mapElemsRest_mapElems_v1 = mapElemsRest_mapElems_a[mapElemsRest_mapElems_i];
                        Object[] mapElemsRest_mapElems_v2 = this.mapElems(mapElemsRest_mapElems_n - 1, mapElemsRest_mapElems_v1, f);
                        if (mapElemsRest_mapElems_v1 != mapElemsRest_mapElems_v2) {
                           var34 = this.mapElemsRest(mapElemsRest_mapElems_n, mapElemsRest_mapElems_a, f, mapElemsRest_mapElems_i, mapElemsRest_mapElems_v2);
                           break;
                        }

                        ++mapElemsRest_mapElems_i;
                     }
                  } else {
                     int mapElemsRest_mapElems_mapElems1_i = 0;

                     while(true) {
                        if (mapElemsRest_mapElems_mapElems1_i >= mapElemsRest_mapElems_a.length) {
                           var34 = mapElemsRest_mapElems_a;
                           break;
                        }

                        Object mapElemsRest_mapElems_mapElems1_v1 = mapElemsRest_mapElems_a[mapElemsRest_mapElems_mapElems1_i];
                        Object mapElemsRest_mapElems_mapElems1_v2 = f.apply(mapElemsRest_mapElems_mapElems1_v1);
                        if (mapElemsRest_mapElems_mapElems1_v1 != mapElemsRest_mapElems_mapElems1_v2) {
                           Object[] mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac = new Object[mapElemsRest_mapElems_a.length];
                           if (mapElemsRest_mapElems_mapElems1_i > 0) {
                              System.arraycopy(mapElemsRest_mapElems_a, 0, mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac, 0, mapElemsRest_mapElems_mapElems1_i);
                           }

                           mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac[mapElemsRest_mapElems_mapElems1_i] = mapElemsRest_mapElems_mapElems1_v2;

                           for(int mapElemsRest_mapElems_mapElems1_mapElems1Rest_i = mapElemsRest_mapElems_mapElems1_i + 1; mapElemsRest_mapElems_mapElems1_mapElems1Rest_i < mapElemsRest_mapElems_a.length; ++mapElemsRest_mapElems_mapElems1_mapElems1Rest_i) {
                              mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac[mapElemsRest_mapElems_mapElems1_mapElems1Rest_i] = f.apply(mapElemsRest_mapElems_a[mapElemsRest_mapElems_mapElems1_mapElems1Rest_i]);
                           }

                           var34 = mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac;
                           mapElemsRest_mapElems_mapElems1_mapElems1Rest_ac = null;
                           break;
                        }

                        ++mapElemsRest_mapElems_mapElems1_i;
                     }

                     Object var27 = null;
                     Object var29 = null;
                     Object var32 = null;
                  }

                  mapElemsRest_mapElems_a = null;
                  Object var25 = null;
                  Object var26 = null;
                  Object var28 = null;
                  Object var30 = null;
                  Object var33 = null;
                  mapElemsRest_ac[mapElemsRest_i] = var34;
               }

               return mapElemsRest_ac;
            }
         }

         return a;
      }
   }

   public final Object[] mapElemsRest(final int n, final Object[] a, final Function1 f, final int at, final Object v2) {
      Object[] ac = Array.newInstance(a.getClass().getComponentType(), a.length);
      if (at > 0) {
         System.arraycopy(a, 0, ac, 0, at);
      }

      ac[at] = v2;

      for(int i = at + 1; i < a.length; ++i) {
         int var10002 = n - 1;
         Object[] mapElems_a = a[i];
         int mapElems_n = var10002;
         Object[] var28;
         if (mapElems_n != 1) {
            int mapElems_i = 0;

            while(true) {
               if (mapElems_i >= mapElems_a.length) {
                  var28 = mapElems_a;
                  break;
               }

               Object mapElems_v1 = mapElems_a[mapElems_i];
               Object[] mapElems_v2 = this.mapElems(mapElems_n - 1, mapElems_v1, f);
               if (mapElems_v1 != mapElems_v2) {
                  var28 = this.mapElemsRest(mapElems_n, mapElems_a, f, mapElems_i, mapElems_v2);
                  break;
               }

               ++mapElems_i;
            }
         } else {
            int mapElems_mapElems1_i = 0;

            while(true) {
               if (mapElems_mapElems1_i >= mapElems_a.length) {
                  var28 = mapElems_a;
                  break;
               }

               Object mapElems_mapElems1_v1 = mapElems_a[mapElems_mapElems1_i];
               Object mapElems_mapElems1_v2 = f.apply(mapElems_mapElems1_v1);
               if (mapElems_mapElems1_v1 != mapElems_mapElems1_v2) {
                  Object[] mapElems_mapElems1_mapElems1Rest_ac = new Object[mapElems_a.length];
                  if (mapElems_mapElems1_i > 0) {
                     System.arraycopy(mapElems_a, 0, mapElems_mapElems1_mapElems1Rest_ac, 0, mapElems_mapElems1_i);
                  }

                  mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_i] = mapElems_mapElems1_v2;

                  for(int mapElems_mapElems1_mapElems1Rest_i = mapElems_mapElems1_i + 1; mapElems_mapElems1_mapElems1Rest_i < mapElems_a.length; ++mapElems_mapElems1_mapElems1Rest_i) {
                     mapElems_mapElems1_mapElems1Rest_ac[mapElems_mapElems1_mapElems1Rest_i] = f.apply(mapElems_a[mapElems_mapElems1_mapElems1Rest_i]);
                  }

                  var28 = mapElems_mapElems1_mapElems1Rest_ac;
                  mapElems_mapElems1_mapElems1Rest_ac = null;
                  break;
               }

               ++mapElems_mapElems1_i;
            }

            Object var21 = null;
            Object var23 = null;
            Object var26 = null;
         }

         mapElems_a = null;
         Object var19 = null;
         Object var20 = null;
         Object var22 = null;
         Object var24 = null;
         Object var27 = null;
         ac[i] = var28;
      }

      return ac;
   }

   public final Object[] prepend1IfSpace(final Object[] prefix1, final IterableOnce xs) {
      if (xs instanceof Iterable) {
         Iterable var3 = (Iterable)xs;
         if (var3.sizeCompare(32 - prefix1.length) <= 0) {
            int var4 = var3.size();
            switch (var4) {
               case 0:
                  return null;
               case 1:
                  return this.copyPrepend(var3.head(), prefix1);
               default:
                  Object[] prefix1b = new Object[prefix1.length + var4];
                  System.arraycopy(prefix1, 0, prefix1b, var4, prefix1.length);
                  var3.copyToArray(prefix1b, 0);
                  return prefix1b;
            }
         } else {
            return null;
         }
      } else {
         int s = xs.knownSize();
         if (s > 0 && s <= 32 - prefix1.length) {
            Object[] prefix1b = new Object[prefix1.length + s];
            System.arraycopy(prefix1, 0, prefix1b, s, prefix1.length);
            xs.iterator().copyToArray(prefix1b, 0);
            return prefix1b;
         } else {
            return null;
         }
      }
   }

   public final Object[] append1IfSpace(final Object[] suffix1, final IterableOnce xs) {
      if (xs instanceof Iterable) {
         Iterable var3 = (Iterable)xs;
         if (var3.sizeCompare(32 - suffix1.length) <= 0) {
            int var4 = var3.size();
            switch (var4) {
               case 0:
                  return null;
               case 1:
                  return this.copyAppend(suffix1, var3.head());
               default:
                  Object[] suffix1b = Arrays.copyOf(suffix1, suffix1.length + var4);
                  var3.copyToArray(suffix1b, suffix1.length);
                  return suffix1b;
            }
         } else {
            return null;
         }
      } else {
         int s = xs.knownSize();
         if (s > 0 && s <= 32 - suffix1.length) {
            Object[] suffix1b = Arrays.copyOf(suffix1, suffix1.length + s);
            xs.iterator().copyToArray(suffix1b, suffix1.length);
            return suffix1b;
         } else {
            return null;
         }
      }
   }

   private VectorStatics$() {
   }
}
