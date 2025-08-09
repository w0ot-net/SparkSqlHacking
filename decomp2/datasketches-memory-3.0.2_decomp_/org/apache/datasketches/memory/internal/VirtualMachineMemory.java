package org.apache.datasketches.memory.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class VirtualMachineMemory {
   private static final Class VM_CLASS;
   private static final Method VM_IS_DIRECT_MEMORY_PAGE_ALIGNED_METHOD;
   private static final boolean isPageAligned;

   public static boolean getIsPageAligned() {
      return isPageAligned;
   }

   static {
      try {
         VM_CLASS = Class.forName("sun.misc.VM");
         VM_IS_DIRECT_MEMORY_PAGE_ALIGNED_METHOD = VM_CLASS.getDeclaredMethod("isDirectMemoryPageAligned");
         VM_IS_DIRECT_MEMORY_PAGE_ALIGNED_METHOD.setAccessible(true);
         isPageAligned = (Boolean)VM_IS_DIRECT_MEMORY_PAGE_ALIGNED_METHOD.invoke((Object)null);
      } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | ClassNotFoundException e) {
         throw new RuntimeException("Could not acquire sun.misc.VM class: " + e.getClass());
      }
   }
}
