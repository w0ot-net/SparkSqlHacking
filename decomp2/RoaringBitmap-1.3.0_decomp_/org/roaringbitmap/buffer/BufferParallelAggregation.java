package org.roaringbitmap.buffer;

import java.nio.LongBuffer;
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

public class BufferParallelAggregation {
   private static final Collector XOR = new ContainerCollector(BufferParallelAggregation::xor);
   private static final OrCollector OR = new OrCollector();

   public static SortedMap groupByKey(ImmutableRoaringBitmap... bitmaps) {
      Map<Character, List<MappeableContainer>> grouped = new HashMap();

      for(ImmutableRoaringBitmap bitmap : bitmaps) {
         MappeableContainerPointer it = bitmap.highLowContainer.getContainerPointer();

         while(null != it.getContainer()) {
            MappeableContainer container = it.getContainer();
            Character key = it.key();
            List<MappeableContainer> slice = (List)grouped.get(key);
            if (null == slice) {
               slice = new ArrayList();
               grouped.put(key, slice);
            }

            slice.add(container);
            it.advance();
         }
      }

      return new TreeMap(grouped);
   }

   public static MutableRoaringBitmap or(ImmutableRoaringBitmap... bitmaps) {
      SortedMap<Character, List<MappeableContainer>> grouped = groupByKey(bitmaps);
      char[] keys = new char[grouped.size()];
      MappeableContainer[] values = new MappeableContainer[grouped.size()];
      List<List<MappeableContainer>> slices = new ArrayList(grouped.size());
      int i = 0;

      for(Map.Entry slice : grouped.entrySet()) {
         keys[i++] = (Character)slice.getKey();
         slices.add((List)slice.getValue());
      }

      IntStream.range(0, i).parallel().forEach((position) -> values[position] = or((List)slices.get(position)));
      return new MutableRoaringBitmap(new MutableRoaringArray(keys, values, i));
   }

   public static MutableRoaringBitmap xor(ImmutableRoaringBitmap... bitmaps) {
      return (MutableRoaringBitmap)groupByKey(bitmaps).entrySet().parallelStream().collect(XOR);
   }

   private static MappeableContainer xor(List containers) {
      MappeableContainer result = ((MappeableContainer)containers.get(0)).clone();

      for(int i = 1; i < containers.size(); ++i) {
         result = result.ixor((MappeableContainer)containers.get(i));
      }

      return result;
   }

   private static MappeableContainer or(List containers) {
      if (containers.size() < 16) {
         MappeableContainer result = ((MappeableContainer)containers.get(0)).clone();

         for(int i = 1; i < containers.size(); ++i) {
            result = result.lazyIOR((MappeableContainer)containers.get(i));
         }

         return result.repairAfterLazy();
      } else {
         int parallelism;
         if (containers.size() >= 512 && (parallelism = availableParallelism()) != 1) {
            int step = Math.floorDiv(containers.size(), parallelism);
            int mod = Math.floorMod(containers.size(), parallelism);
            return (MappeableContainer)IntStream.range(0, parallelism).parallel().mapToObj((i) -> containers.subList(i * step + Math.min(i, mod), (i + 1) * step + Math.min(i + 1, mod))).collect(OR);
         } else {
            MappeableContainer result = new MappeableBitmapContainer(LongBuffer.allocate(1024), -1);

            for(MappeableContainer container : containers) {
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
         return MutableRoaringArray::new;
      }

      public BiConsumer accumulator() {
         return (l, r) -> {
            assert l.size == 0 || l.keys[l.size - 1] < (Character)r.getKey();

            MappeableContainer container = (MappeableContainer)this.reducer.apply((List)r.getValue());
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
         return MutableRoaringBitmap::new;
      }

      public Set characteristics() {
         return EnumSet.noneOf(Collector.Characteristics.class);
      }
   }

   public static class OrCollector implements Collector {
      public Supplier supplier() {
         return () -> new MappeableBitmapContainer(LongBuffer.allocate(1024), -1);
      }

      public BiConsumer accumulator() {
         return (l, r) -> l.lazyIOR(BufferParallelAggregation.or(r));
      }

      public BinaryOperator combiner() {
         return MappeableContainer::lazyIOR;
      }

      public Function finisher() {
         return MappeableContainer::repairAfterLazy;
      }

      public Set characteristics() {
         return EnumSet.of(Characteristics.UNORDERED);
      }
   }
}
