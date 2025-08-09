package io.netty.util.concurrent;

import io.netty.util.internal.InternalThreadLocalMap;
import io.netty.util.internal.PlatformDependent;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public class FastThreadLocal {
   private final int index = InternalThreadLocalMap.nextVariableIndex();

   public static void removeAll() {
      InternalThreadLocalMap threadLocalMap = InternalThreadLocalMap.getIfSet();
      if (threadLocalMap != null) {
         try {
            Object v = threadLocalMap.indexedVariable(InternalThreadLocalMap.VARIABLES_TO_REMOVE_INDEX);
            if (v != null && v != InternalThreadLocalMap.UNSET) {
               Set<FastThreadLocal<?>> variablesToRemove = (Set)v;
               FastThreadLocal<?>[] variablesToRemoveArray = (FastThreadLocal[])variablesToRemove.toArray(new FastThreadLocal[0]);

               for(FastThreadLocal tlv : variablesToRemoveArray) {
                  tlv.remove(threadLocalMap);
               }
            }
         } finally {
            InternalThreadLocalMap.remove();
         }

      }
   }

   public static int size() {
      InternalThreadLocalMap threadLocalMap = InternalThreadLocalMap.getIfSet();
      return threadLocalMap == null ? 0 : threadLocalMap.size();
   }

   public static void destroy() {
      InternalThreadLocalMap.destroy();
   }

   private static void addToVariablesToRemove(InternalThreadLocalMap threadLocalMap, FastThreadLocal variable) {
      Object v = threadLocalMap.indexedVariable(InternalThreadLocalMap.VARIABLES_TO_REMOVE_INDEX);
      Set<FastThreadLocal<?>> variablesToRemove;
      if (v != InternalThreadLocalMap.UNSET && v != null) {
         variablesToRemove = (Set)v;
      } else {
         variablesToRemove = Collections.newSetFromMap(new IdentityHashMap());
         threadLocalMap.setIndexedVariable(InternalThreadLocalMap.VARIABLES_TO_REMOVE_INDEX, variablesToRemove);
      }

      variablesToRemove.add(variable);
   }

   private static void removeFromVariablesToRemove(InternalThreadLocalMap threadLocalMap, FastThreadLocal variable) {
      Object v = threadLocalMap.indexedVariable(InternalThreadLocalMap.VARIABLES_TO_REMOVE_INDEX);
      if (v != InternalThreadLocalMap.UNSET && v != null) {
         Set<FastThreadLocal<?>> variablesToRemove = (Set)v;
         variablesToRemove.remove(variable);
      }
   }

   public final Object get() {
      InternalThreadLocalMap threadLocalMap = InternalThreadLocalMap.get();
      Object v = threadLocalMap.indexedVariable(this.index);
      return v != InternalThreadLocalMap.UNSET ? v : this.initialize(threadLocalMap);
   }

   public final Object getIfExists() {
      InternalThreadLocalMap threadLocalMap = InternalThreadLocalMap.getIfSet();
      if (threadLocalMap != null) {
         Object v = threadLocalMap.indexedVariable(this.index);
         if (v != InternalThreadLocalMap.UNSET) {
            return v;
         }
      }

      return null;
   }

   public final Object get(InternalThreadLocalMap threadLocalMap) {
      Object v = threadLocalMap.indexedVariable(this.index);
      return v != InternalThreadLocalMap.UNSET ? v : this.initialize(threadLocalMap);
   }

   private Object initialize(InternalThreadLocalMap threadLocalMap) {
      V v = (V)null;

      try {
         v = (V)this.initialValue();
         if (v == InternalThreadLocalMap.UNSET) {
            throw new IllegalArgumentException("InternalThreadLocalMap.UNSET can not be initial value.");
         }
      } catch (Exception e) {
         PlatformDependent.throwException(e);
      }

      threadLocalMap.setIndexedVariable(this.index, v);
      addToVariablesToRemove(threadLocalMap, this);
      return v;
   }

   public final void set(Object value) {
      if (value != InternalThreadLocalMap.UNSET) {
         InternalThreadLocalMap threadLocalMap = InternalThreadLocalMap.get();
         this.setKnownNotUnset(threadLocalMap, value);
      } else {
         this.remove();
      }

   }

   public final void set(InternalThreadLocalMap threadLocalMap, Object value) {
      if (value != InternalThreadLocalMap.UNSET) {
         this.setKnownNotUnset(threadLocalMap, value);
      } else {
         this.remove(threadLocalMap);
      }

   }

   private void setKnownNotUnset(InternalThreadLocalMap threadLocalMap, Object value) {
      if (threadLocalMap.setIndexedVariable(this.index, value)) {
         addToVariablesToRemove(threadLocalMap, this);
      }

   }

   public final boolean isSet() {
      return this.isSet(InternalThreadLocalMap.getIfSet());
   }

   public final boolean isSet(InternalThreadLocalMap threadLocalMap) {
      return threadLocalMap != null && threadLocalMap.isIndexedVariableSet(this.index);
   }

   public final void remove() {
      this.remove(InternalThreadLocalMap.getIfSet());
   }

   public final void remove(InternalThreadLocalMap threadLocalMap) {
      if (threadLocalMap != null) {
         Object v = threadLocalMap.removeIndexedVariable(this.index);
         if (v != InternalThreadLocalMap.UNSET) {
            removeFromVariablesToRemove(threadLocalMap, this);

            try {
               this.onRemoval(v);
            } catch (Exception e) {
               PlatformDependent.throwException(e);
            }
         }

      }
   }

   protected Object initialValue() throws Exception {
      return null;
   }

   protected void onRemoval(Object value) throws Exception {
   }
}
