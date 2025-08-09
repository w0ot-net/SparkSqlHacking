package org.apache.curator.shaded.com.google.common.base;

import java.nio.charset.Charset;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
public final class Charsets {
   @J2ktIncompatible
   @GwtIncompatible
   public static final Charset US_ASCII = Charset.forName("US-ASCII");
   public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
   public static final Charset UTF_8 = Charset.forName("UTF-8");
   @J2ktIncompatible
   @GwtIncompatible
   public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
   @J2ktIncompatible
   @GwtIncompatible
   public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
   @J2ktIncompatible
   @GwtIncompatible
   public static final Charset UTF_16 = Charset.forName("UTF-16");

   private Charsets() {
   }
}
