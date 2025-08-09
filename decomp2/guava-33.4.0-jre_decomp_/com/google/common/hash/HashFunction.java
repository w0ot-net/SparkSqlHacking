package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@Immutable
@ElementTypesAreNonnullByDefault
public interface HashFunction {
   Hasher newHasher();

   Hasher newHasher(int expectedInputSize);

   HashCode hashInt(int input);

   HashCode hashLong(long input);

   HashCode hashBytes(byte[] input);

   HashCode hashBytes(byte[] input, int off, int len);

   HashCode hashBytes(ByteBuffer input);

   HashCode hashUnencodedChars(CharSequence input);

   HashCode hashString(CharSequence input, Charset charset);

   HashCode hashObject(@ParametricNullness Object instance, Funnel funnel);

   int bits();
}
