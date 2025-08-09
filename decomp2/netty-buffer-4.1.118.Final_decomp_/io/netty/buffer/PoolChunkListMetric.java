package io.netty.buffer;

public interface PoolChunkListMetric extends Iterable {
   int minUsage();

   int maxUsage();
}
