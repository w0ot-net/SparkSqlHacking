package org.apache.curator.shaded.com.google.common.io;

import java.io.DataOutput;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface ByteArrayDataOutput extends DataOutput {
   void write(int b);

   void write(byte[] b);

   void write(byte[] b, int off, int len);

   void writeBoolean(boolean v);

   void writeByte(int v);

   void writeShort(int v);

   void writeChar(int v);

   void writeInt(int v);

   void writeLong(long v);

   void writeFloat(float v);

   void writeDouble(double v);

   void writeChars(String s);

   void writeUTF(String s);

   /** @deprecated */
   @Deprecated
   void writeBytes(String s);

   byte[] toByteArray();
}
