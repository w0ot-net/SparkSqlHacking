package org.apache.arrow.memory;

class ChildAllocator extends BaseAllocator {
   ChildAllocator(BaseAllocator parentAllocator, String name, BaseAllocator.Config config) {
      super(parentAllocator, name, config);
   }
}
