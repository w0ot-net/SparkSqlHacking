package org.rocksdb;

public abstract class AbstractSlice extends RocksMutableObject {
   protected AbstractSlice() {
   }

   protected AbstractSlice(long var1) {
      super(var1);
   }

   public Object data() {
      return this.data0(this.getNativeHandle());
   }

   protected abstract Object data0(long var1);

   public abstract void removePrefix(int var1);

   public abstract void clear();

   public int size() {
      return size0(this.getNativeHandle());
   }

   public boolean empty() {
      return empty0(this.getNativeHandle());
   }

   public String toString(boolean var1) {
      return toString0(this.getNativeHandle(), var1);
   }

   public String toString() {
      return this.toString(false);
   }

   public int compare(AbstractSlice var1) {
      assert var1 != null;

      if (this.isOwningHandle() && var1.isOwningHandle()) {
         return compare0(this.getNativeHandle(), var1.getNativeHandle());
      } else if (!this.isOwningHandle() && !var1.isOwningHandle()) {
         return 0;
      } else {
         return this.isOwningHandle() ? 1 : -1;
      }
   }

   public int hashCode() {
      return this.toString().hashCode();
   }

   public boolean equals(Object var1) {
      if (var1 instanceof AbstractSlice) {
         return this.compare((AbstractSlice)var1) == 0;
      } else {
         return false;
      }
   }

   public boolean startsWith(AbstractSlice var1) {
      return var1 != null ? startsWith0(this.getNativeHandle(), var1.getNativeHandle()) : false;
   }

   protected static native long createNewSliceFromString(String var0);

   private static native int size0(long var0);

   private static native boolean empty0(long var0);

   private static native String toString0(long var0, boolean var2);

   private static native int compare0(long var0, long var2);

   private static native boolean startsWith0(long var0, long var2);

   protected final void disposeInternal(long var1) {
      disposeInternalJni(var1);
   }

   private static native void disposeInternalJni(long var0);
}
