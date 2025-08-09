package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Charsets {
   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static final Charset US_ASCII;
   /** @deprecated */
   @Deprecated
   public static final Charset ISO_8859_1;
   /** @deprecated */
   @Deprecated
   public static final Charset UTF_8;
   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static final Charset UTF_16BE;
   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static final Charset UTF_16LE;
   /** @deprecated */
   @Deprecated
   @J2ktIncompatible
   @GwtIncompatible
   public static final Charset UTF_16;

   private Charsets() {
   }

   static {
      US_ASCII = StandardCharsets.US_ASCII;
      ISO_8859_1 = StandardCharsets.ISO_8859_1;
      UTF_8 = StandardCharsets.UTF_8;
      UTF_16BE = StandardCharsets.UTF_16BE;
      UTF_16LE = StandardCharsets.UTF_16LE;
      UTF_16 = StandardCharsets.UTF_16;
   }
}
