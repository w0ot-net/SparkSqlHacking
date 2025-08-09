package scala.reflect.internal.util;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Map;

public final class JavaClearable$ {
   public static final JavaClearable$ MODULE$ = new JavaClearable$();

   public JavaClearable forCollection(final Collection data) {
      return new JavaClearable.JavaClearableCollection(new WeakReference(data));
   }

   public JavaClearable forMap(final Map data) {
      return new JavaClearable.JavaClearableMap(new WeakReference(data));
   }

   private JavaClearable$() {
   }
}
