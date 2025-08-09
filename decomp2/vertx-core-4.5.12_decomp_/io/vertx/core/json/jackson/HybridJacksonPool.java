package io.vertx.core.json.jackson;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.JsonRecyclerPools;
import com.fasterxml.jackson.core.util.RecyclerPool;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class HybridJacksonPool implements RecyclerPool {
   private static final HybridJacksonPool INSTANCE = new HybridJacksonPool();
   private static final Predicate isVirtual = HybridJacksonPool.VirtualPredicate.findIsVirtualPredicate();
   private final RecyclerPool nativePool = JsonRecyclerPools.threadLocalPool();
   private static final int MAX_POW2 = 1073741824;

   private HybridJacksonPool() {
   }

   public static HybridJacksonPool getInstance() {
      return INSTANCE;
   }

   public BufferRecycler acquirePooled() {
      return isVirtual.test(Thread.currentThread()) ? HybridJacksonPool.VirtualPoolHolder.virtualPool.acquirePooled() : (BufferRecycler)this.nativePool.acquirePooled();
   }

   public BufferRecycler acquireAndLinkPooled() {
      return isVirtual.test(Thread.currentThread()) ? (BufferRecycler)HybridJacksonPool.VirtualPoolHolder.virtualPool.acquireAndLinkPooled() : (BufferRecycler)this.nativePool.acquirePooled();
   }

   public void releasePooled(BufferRecycler bufferRecycler) {
      if (bufferRecycler instanceof VThreadBufferRecycler) {
         HybridJacksonPool.VirtualPoolHolder.virtualPool.releasePooled(bufferRecycler);
      }

   }

   private static int roundToPowerOfTwo(int value) {
      if (value > 1073741824) {
         throw new IllegalArgumentException("There is no larger power of 2 int for value:" + value + " since it exceeds 2^31.");
      } else if (value < 0) {
         throw new IllegalArgumentException("Given value:" + value + ". Expecting value >= 0.");
      } else {
         int nextPow2 = 1 << 32 - Integer.numberOfLeadingZeros(value - 1);
         return nextPow2;
      }
   }

   private static class VirtualPoolHolder {
      private static final StripedLockFreePool virtualPool = new StripedLockFreePool(Runtime.getRuntime().availableProcessors());
   }

   static class StripedLockFreePool implements RecyclerPool {
      private static final int CACHE_LINE_SHIFT = 4;
      private static final int CACHE_LINE_PADDING = 16;
      private final XorShiftThreadProbe threadProbe;
      private final AtomicReferenceArray topStacks;
      private final int stripesCount;

      public StripedLockFreePool(int stripesCount) {
         if (stripesCount <= 0) {
            throw new IllegalArgumentException("Expecting a stripesCount that is larger than 0");
         } else {
            this.stripesCount = stripesCount;
            int size = HybridJacksonPool.roundToPowerOfTwo(stripesCount);
            this.topStacks = new AtomicReferenceArray(size * 16);
            int mask = size - 1 << 4;
            this.threadProbe = new XorShiftThreadProbe(mask);
         }
      }

      public int size() {
         return this.stackSizes().sum();
      }

      public int[] stackStats() {
         return this.stackSizes().toArray();
      }

      private IntStream stackSizes() {
         return IntStream.range(0, this.stripesCount).map((i) -> {
            Node node = (Node)this.topStacks.get(i * 16);
            return node == null ? 0 : node.level;
         });
      }

      public BufferRecycler acquirePooled() {
         int index = this.threadProbe.index();

         for(Node currentHead = (Node)this.topStacks.get(index); currentHead != null; currentHead = (Node)this.topStacks.get(index)) {
            if (this.topStacks.compareAndSet(index, currentHead, currentHead.next)) {
               currentHead.next = null;
               return currentHead.value;
            }
         }

         return new VThreadBufferRecycler(index);
      }

      public void releasePooled(BufferRecycler recycler) {
         VThreadBufferRecycler vThreadBufferRecycler = (VThreadBufferRecycler)recycler;
         Node newHead = new Node(vThreadBufferRecycler);
         Node next = (Node)this.topStacks.get(vThreadBufferRecycler.slot);

         while(true) {
            newHead.level = next == null ? 1 : next.level + 1;
            if (this.topStacks.compareAndSet(vThreadBufferRecycler.slot, next, newHead)) {
               newHead.next = next;
               return;
            }

            next = (Node)this.topStacks.get(vThreadBufferRecycler.slot);
         }
      }

      private static class Node {
         final VThreadBufferRecycler value;
         Node next;
         int level = 0;

         Node(VThreadBufferRecycler value) {
            this.value = value;
         }
      }
   }

   private static class VThreadBufferRecycler extends BufferRecycler {
      private final int slot;

      VThreadBufferRecycler(int slot) {
         this.slot = slot;
      }
   }

   private static class VirtualPredicate {
      private static final MethodHandle virtualMh = findVirtualMH();

      private static MethodHandle findVirtualMH() {
         try {
            return MethodHandles.publicLookup().findVirtual(Thread.class, "isVirtual", MethodType.methodType(Boolean.TYPE));
         } catch (Exception var1) {
            return null;
         }
      }

      private static Predicate findIsVirtualPredicate() {
         return virtualMh != null ? new Predicate() {
            public boolean test(Thread thread) {
               try {
                  return HybridJacksonPool.VirtualPredicate.virtualMh.invokeExact(thread);
               } catch (Throwable e) {
                  throw new RuntimeException(e);
               }
            }
         } : new Predicate() {
            public boolean test(Thread thread) {
               return false;
            }
         };
      }
   }

   private static class XorShiftThreadProbe {
      private final int mask;

      XorShiftThreadProbe(int mask) {
         this.mask = mask;
      }

      public int index() {
         return this.probe() & this.mask;
      }

      private int probe() {
         int probe = (int)(Thread.currentThread().getId() * -1640531527L & 2147483647L);
         probe ^= probe << 13;
         probe ^= probe >>> 17;
         probe ^= probe << 5;
         return probe;
      }
   }
}
