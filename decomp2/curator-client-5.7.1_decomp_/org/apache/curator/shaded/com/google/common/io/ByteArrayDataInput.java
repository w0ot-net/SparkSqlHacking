package org.apache.curator.shaded.com.google.common.io;

import java.io.DataInput;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface ByteArrayDataInput extends DataInput {
   void readFully(byte[] b);

   void readFully(byte[] b, int off, int len);

   int skipBytes(int n);

   @CanIgnoreReturnValue
   boolean readBoolean();

   @CanIgnoreReturnValue
   byte readByte();

   @CanIgnoreReturnValue
   int readUnsignedByte();

   @CanIgnoreReturnValue
   short readShort();

   @CanIgnoreReturnValue
   int readUnsignedShort();

   @CanIgnoreReturnValue
   char readChar();

   @CanIgnoreReturnValue
   int readInt();

   @CanIgnoreReturnValue
   long readLong();

   @CanIgnoreReturnValue
   float readFloat();

   @CanIgnoreReturnValue
   double readDouble();

   @CheckForNull
   @CanIgnoreReturnValue
   String readLine();

   @CanIgnoreReturnValue
   String readUTF();
}
