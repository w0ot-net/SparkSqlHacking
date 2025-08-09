package scala.compat;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;

/** @deprecated */
public final class Platform$ {
   public static final Platform$ MODULE$ = new Platform$();
   /** @deprecated */
   private static final String EOL = System.lineSeparator();

   /** @deprecated */
   public void arraycopy(final Object src, final int srcPos, final Object dest, final int destPos, final int length) {
      System.arraycopy(src, srcPos, dest, destPos, length);
   }

   /** @deprecated */
   public Object createArray(final Class elemClass, final int length) {
      return Array.newInstance(elemClass, length);
   }

   /** @deprecated */
   public void arrayclear(final int[] arr) {
      Arrays.fill(arr, 0);
   }

   /** @deprecated */
   public Class getClassForName(final String name) {
      return Class.forName(name);
   }

   /** @deprecated */
   public String EOL() {
      return EOL;
   }

   /** @deprecated */
   public long currentTime() {
      return System.currentTimeMillis();
   }

   /** @deprecated */
   public void collectGarbage() {
      System.gc();
   }

   /** @deprecated */
   public String defaultCharsetName() {
      return Charset.defaultCharset().name();
   }

   private Platform$() {
   }
}
