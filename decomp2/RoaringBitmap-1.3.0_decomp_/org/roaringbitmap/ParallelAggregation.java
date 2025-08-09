package org.roaringbitmap;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Collector.Characteristics;

public class ParallelAggregation {
   private static final Collector XOR = new ContainerCollector(ParallelAggregation::xor);
   private static final OrCollector OR = new OrCollector();

   public static SortedMap groupByKey(RoaringBitmap... bitmaps) {
      Map<Character, List<Container>> grouped = new HashMap();

      for(RoaringBitmap bitmap : bitmaps) {
         RoaringArray ra = bitmap.highLowContainer;

         for(int i = 0; i < ra.size; ++i) {
            Container container = ra.values[i];
            Character key = ra.keys[i];
            List<Container> slice = (List)grouped.get(key);
            if (null == slice) {
               slice = new ArrayList();
               grouped.put(key, slice);
            }

            slice.add(container);
         }
      }

      return new TreeMap(grouped);
   }

   public static RoaringBitmap or(RoaringBitmap... bitmaps) {
      SortedMap<Character, List<Container>> grouped = groupByKey(bitmaps);
      char[] keys = new char[grouped.size()];
      Container[] values = new Container[grouped.size()];
      List<List<Container>> slices = new ArrayList(grouped.size());
      int i = 0;

      for(Map.Entry slice : grouped.entrySet()) {
         keys[i++] = (Character)slice.getKey();
         slices.add((List)slice.getValue());
      }

      IntStream.range(0, i).parallel().forEach((position) -> values[position] = or((List)slices.get(position)));
      return new RoaringBitmap(new RoaringArray(keys, values, i));
   }

   public static RoaringBitmap xor(RoaringBitmap... bitmaps) {
      return (RoaringBitmap)groupByKey(bitmaps).entrySet().parallelStream().collect(XOR);
   }

   private static Container xor(List containers) {
      Container result = ((Container)containers.get(0)).clone();

      for(int i = 1; i < containers.size(); ++i) {
         result = result.ixor((Container)containers.get(i));
      }

      return result;
   }

   private static Container or(List containers) {
      if (containers.size() < 16) {
         Container result = ((Container)containers.get(0)).clone();

         for(int i = 1; i < containers.size(); ++i) {
            result = result.lazyIOR((Container)containers.get(i));
         }

         return result.repairAfterLazy();
      } else {
         int parallelism;
         if (containers.size() >= 512 && (parallelism = availableParallelism()) != 1) {
            int step = Math.floorDiv(containers.size(), parallelism);
            int mod = Math.floorMod(containers.size(), parallelism);
            return (Container)IntStream.range(0, parallelism).parallel().mapToObj((i) -> containers.subList(i * step + Math.min(i, mod), (i + 1) * step + Math.min(i + 1, mod))).collect(OR);
         } else {
            Container result = new BitmapContainer(new long[1024], -1);

            for(Container container : containers) {
               result = result.lazyIOR(container);
            }

            return result.repairAfterLazy();
         }
      }
   }

   private static int availableParallelism() {
      return ForkJoinTask.inForkJoinPool() ? ForkJoinTask.getPool().getParallelism() : ForkJoinPool.getCommonPoolParallelism();
   }

   public static class ContainerCollector implements Collector {
      private final Function reducer;

      ContainerCollector(Function reducer) {
         this.reducer = reducer;
      }

      public Supplier supplier() {
         return RoaringArray::new;
      }

      public BiConsumer accumulator() {
         return (l, r) -> {
            assert l.size == 0 || l.keys[l.size - 1] < (Character)r.getKey();

            Container container = (Container)this.reducer.apply((List)r.getValue());
            if (!container.isEmpty()) {
               l.append((Character)r.getKey(), container);
            }

         };
      }

      public BinaryOperator combiner() {
         return (l, r) -> {
            assert l.size == 0 || r.size == 0 || l.keys[l.size - 1] - r.keys[0] < 0;

            l.append(r);
            return l;
         };
      }

      public Function finisher() {
         return RoaringBitmap::new;
      }

      public Set characteristics() {
         return EnumSet.noneOf(Collector.Characteristics.class);
      }
   }

   public static class OrCollector implements Collector {
      public Supplier supplier() {
         return () -> new BitmapContainer(new long[1024], -1);
      }

      public BiConsumer accumulator() {
         return (l, r) -> l.lazyIOR(ParallelAggregation.or(r));
      }

      public BinaryOperator combiner() {
         return Container::lazyIOR;
      }

      public Function finisher() {
         return Container::repairAfterLazy;
      }

      public Set characteristics() {
         return EnumSet.of(Characteristics.UNORDERED);
      }
   }
}
