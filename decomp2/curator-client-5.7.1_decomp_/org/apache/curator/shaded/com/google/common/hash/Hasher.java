package org.apache.curator.shaded.com.google.common.hash;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@Beta
public interface Hasher extends PrimitiveSink {
   @CanIgnoreReturnValue
   Hasher putByte(byte b);

   @CanIgnoreReturnValue
   Hasher putBytes(byte[] bytes);

   @CanIgnoreReturnValue
   Hasher putBytes(byte[] bytes, int off, int len);

   @CanIgnoreReturnValue
   Hasher putBytes(ByteBuffer bytes);

   @CanIgnoreReturnValue
   Hasher putShort(short s);

   @CanIgnoreReturnValue
   Hasher putInt(int i);

   @CanIgnoreReturnValue
   Hasher putLong(long l);

   @CanIgnoreReturnValue
   Hasher putFloat(float f);

   @CanIgnoreReturnValue
   Hasher putDouble(double d);

   @CanIgnoreReturnValue
   Hasher putBoolean(boolean b);

   @CanIgnoreReturnValue
   Hasher putChar(char c);

   @CanIgnoreReturnValue
   Hasher putUnencodedChars(CharSequence charSequence);

   @CanIgnoreReturnValue
   Hasher putString(CharSequence charSequence, Charset charset);

   @CanIgnoreReturnValue
   Hasher putObject(@ParametricNullness Object instance, Funnel funnel);

   HashCode hash();

   /** @deprecated */
   @Deprecated
   int hashCode();
}
