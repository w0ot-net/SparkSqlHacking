package org.apache.curator.shaded.com.google.common.base;

import java.util.Arrays;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class Objects extends ExtraObjectsMethodsForWeb {
   private Objects() {
   }

   public static boolean equal(@CheckForNull Object a, @CheckForNull Object b) {
      return a == b || a != null && a.equals(b);
   }

   public static int hashCode(@CheckForNull Object... objects) {
      return Arrays.hashCode(objects);
   }
}
