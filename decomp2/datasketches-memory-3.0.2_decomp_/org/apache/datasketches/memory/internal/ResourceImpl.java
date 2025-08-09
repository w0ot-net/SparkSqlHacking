package org.apache.datasketches.memory.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.datasketches.memory.MemoryBoundsException;
import org.apache.datasketches.memory.MemoryRequestServer;
import org.apache.datasketches.memory.ReadOnlyException;
import org.apache.datasketches.memory.Resource;

public abstract class ResourceImpl implements Resource {
   static final String JDK;
   static final int JDK_MAJOR;
   static final int BOOLEAN_SHIFT = 0;
   static final int BYTE_SHIFT = 0;
   static final long SHORT_SHIFT = 1L;
   static final long CHAR_SHIFT = 1L;
   static final long INT_SHIFT = 2L;
   static final long LONG_SHIFT = 3L;
   static final long FLOAT_SHIFT = 2L;
   static final long DOUBLE_SHIFT = 3L;
   static final int WRITABLE = 0;
   static final int READONLY = 1;
   static final int REGION = 2;
   static final int DUPLICATE = 4;
   static final int HEAP = 0;
   static final int DIRECT = 8;
   static final int MAP = 16;
   static final int NATIVE_BO = 0;
   static final int NONNATIVE_BO = 32;
   static final int MEMORY = 0;
   static final int BUFFER = 64;
   static final int BYTEBUF = 128;
   public static final String LS = System.getProperty("line.separator");
   static final String NOT_MAPPED_FILE_RESOURCE = "This is not a memory-mapped file resource";
   static final String THREAD_EXCEPTION_TEXT = "Attempted access outside owning thread";
   long capacityBytes;
   long cumOffsetBytes;
   long offsetBytes;
   int typeId;
   Thread owner = null;
   MemoryRequestServer memReqSvr = null;

   ResourceImpl() {
   }

   public MemoryRequestServer getMemoryRequestServer() {
      return this.memReqSvr;
   }

   public boolean hasMemoryRequestServer() {
      return this.memReqSvr != null;
   }

   public void setMemoryRequestServer(MemoryRequestServer memReqSvr) {
      this.memReqSvr = memReqSvr;
   }

   public static void checkBounds(long reqOff, long reqLen, long allocSize) {
      if ((reqOff | reqLen | reqOff + reqLen | allocSize - (reqOff + reqLen)) < 0L) {
         throw new MemoryBoundsException("reqOffset: " + reqOff + ", reqLength: " + reqLen + ", (reqOff + reqLen): " + (reqOff + reqLen) + ", allocSize: " + allocSize);
      }
   }

   static void checkJavaVersion(String jdkVer, int p0, int p1) {
      boolean ok = p0 == 1 && p1 == 8 || p0 == 8 || p0 == 11 || p0 == 17 || p0 == 21;
      if (!ok) {
         throw new IllegalArgumentException("Unsupported JDK Major Version. It must be one of 1.8, 8, 11, 17, 21: " + jdkVer);
      }
   }

   void checkNotReadOnly() {
      if (this.isReadOnly()) {
         throw new ReadOnlyException("Cannot write to a read-only Resource.");
      }
   }

   static final void checkThread(Thread owner) {
      if (owner != Thread.currentThread()) {
         throw new IllegalStateException("Attempted access outside owning thread");
      }
   }

   void checkValid() {
      if (!this.isAlive()) {
         throw new IllegalStateException("this Resource is AutoCloseable, and already closed, i.e., not <em>alive</em>.");
      }
   }

   public final void checkValidAndBounds(long offsetBytes, long lengthBytes) {
      this.checkValid();
      checkBounds(offsetBytes, lengthBytes, this.getCapacity());
   }

   final void checkValidAndBoundsForWrite(long offsetBytes, long lengthBytes) {
      this.checkValid();
      checkBounds(offsetBytes, lengthBytes, this.getCapacity());
      if (this.isReadOnly()) {
         throw new ReadOnlyException("Memory is read-only.");
      }
   }

   public void close() {
   }

   public final boolean equalTo(long thisOffsetBytes, Resource that, long thatOffsetBytes, long lengthBytes) {
      return that == null ? false : CompareAndCopy.equals(this, thisOffsetBytes, (ResourceImpl)that, thatOffsetBytes, lengthBytes);
   }

   public void force() {
      throw new UnsupportedOperationException("This is not a memory-mapped file resource");
   }

   ByteBuffer getByteBuffer() {
      return null;
   }

   public final ByteOrder getTypeByteOrder() {
      return isNativeOrder(this.getTypeId()) ? Util.NATIVE_BYTE_ORDER : Util.NON_NATIVE_BYTE_ORDER;
   }

   public long getCapacity() {
      this.checkValid();
      return this.capacityBytes;
   }

   public long getCumulativeOffset(long addOffsetBytes) {
      return this.cumOffsetBytes + addOffsetBytes;
   }

   public long getRelativeOffset() {
      return this.offsetBytes;
   }

   int getTypeId() {
      return this.typeId;
   }

   Object getUnsafeObject() {
      return null;
   }

   public boolean hasByteBuffer() {
      return (this.getTypeId() & 128) > 0;
   }

   public final boolean isByteOrderCompatible(ByteOrder byteOrder) {
      ByteOrder typeBO = this.getTypeByteOrder();
      return typeBO == ByteOrder.nativeOrder() && typeBO == byteOrder;
   }

   static final boolean isBuffer(int typeId) {
      return (typeId & 64) > 0;
   }

   public boolean isCloseable() {
      return (this.getTypeId() & 24) > 0 && this.isAlive();
   }

   public final boolean isDirect() {
      return this.getUnsafeObject() == null;
   }

   public boolean isDuplicate() {
      return (this.getTypeId() & 4) > 0;
   }

   public final boolean isHeap() {
      this.checkValid();
      return this.getUnsafeObject() != null;
   }

   public boolean isLoaded() {
      throw new IllegalStateException("This is not a memory-mapped file resource");
   }

   public boolean isMapped() {
      return (this.getTypeId() & 16) > 0;
   }

   public boolean isMemory() {
      return (this.getTypeId() & 64) == 0;
   }

   static final boolean isNativeOrder(int typeId) {
      return (typeId & 32) == 0;
   }

   public boolean isNonNativeOrder() {
      return (this.getTypeId() & 32) > 0;
   }

   public final boolean isReadOnly() {
      this.checkValid();
      return (this.getTypeId() & 1) > 0;
   }

   public boolean isRegionView() {
      return (this.getTypeId() & 2) > 0;
   }

   public boolean isSameResource(Resource that) {
      this.checkValid();
      if (that == null) {
         return false;
      } else {
         ResourceImpl that1 = (ResourceImpl)that;
         that1.checkValid();
         if (this == that1) {
            return true;
         } else {
            return this.getCumulativeOffset(0L) == that1.getCumulativeOffset(0L) && this.getCapacity() == that1.getCapacity() && this.getUnsafeObject() == that1.getUnsafeObject() && this.getByteBuffer() == that1.getByteBuffer();
         }
      }
   }

   public boolean isAlive() {
      return true;
   }

   public void load() {
      throw new IllegalStateException("This is not a memory-mapped file resource");
   }

   private static String pad(String s, int fieldLen) {
      return Util.characterPad(s, fieldLen, ' ', true);
   }

   static int[] parseJavaVersion(String jdkVer) {
      int p0;
      int p1;
      try {
         String[] parts = jdkVer.trim().split("^0-9\\.");
         parts = parts[0].split("\\.");
         p0 = Integer.parseInt(parts[0]);
         p1 = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
      } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
         throw new IllegalArgumentException("Improper Java -version string: " + jdkVer + LS + e);
      }

      checkJavaVersion(jdkVer, p0, p1);
      return new int[]{p0, p1};
   }

   static void reachabilityFence(Object obj) {
   }

   static final int removeNnBuf(int typeId) {
      return typeId & -33 & -65;
   }

   static final int setReadOnlyBit(int typeId, boolean readOnly) {
      return readOnly ? typeId | 1 : typeId & -2;
   }

   static final String toHex(ResourceImpl state, String preamble, long offsetBytes, int lengthBytes, boolean withData) {
      long capacity = state.getCapacity();
      checkBounds(offsetBytes, (long)lengthBytes, capacity);
      StringBuilder sb = new StringBuilder();
      Object uObj = state.getUnsafeObject();
      String uObjStr;
      long uObjHeader;
      if (uObj == null) {
         uObjStr = "null";
         uObjHeader = 0L;
      } else {
         uObjStr = uObj.getClass().getSimpleName() + ", " + ((long)uObj.hashCode() & 4294967295L);
         uObjHeader = UnsafeUtil.getArrayBaseOffset(uObj.getClass());
      }

      ByteBuffer bb = state.getByteBuffer();
      String bbStr = bb == null ? "null" : bb.getClass().getSimpleName() + ", " + ((long)bb.hashCode() & 4294967295L);
      MemoryRequestServer memReqSvr = state.getMemoryRequestServer();
      String memReqStr = memReqSvr != null ? memReqSvr.getClass().getSimpleName() + ", " + ((long)memReqSvr.hashCode() & 4294967295L) : "null";
      long cumBaseOffset = state.getCumulativeOffset(0L);
      sb.append(preamble).append(LS);
      sb.append("UnsafeObj, hashCode : ").append(uObjStr).append(LS);
      sb.append("UnsafeObjHeader     : ").append(uObjHeader).append(LS);
      sb.append("ByteBuf, hashCode   : ").append(bbStr).append(LS);
      sb.append("RegionOffset        : ").append(state.getRelativeOffset()).append(LS);
      if (isBuffer(state.typeId)) {
         sb.append("Start               : ").append(((PositionalImpl)state).getStart()).append(LS);
         sb.append("Position            : ").append(((PositionalImpl)state).getPosition()).append(LS);
         sb.append("End                 : ").append(((PositionalImpl)state).getEnd()).append(LS);
      }

      sb.append("Capacity            : ").append(capacity).append(LS);
      sb.append("CumBaseOffset       : ").append(cumBaseOffset).append(LS);
      sb.append("MemReqSvr, hashCode : ").append(memReqStr).append(LS);
      sb.append("is Alive            : ").append(state.isAlive()).append(LS);
      sb.append("Read Only           : ").append(state.isReadOnly()).append(LS);
      sb.append("Type Byte Order     : ").append(state.getTypeByteOrder().toString()).append(LS);
      sb.append("Native Byte Order   : ").append(ByteOrder.nativeOrder().toString()).append(LS);
      sb.append("JDK Runtime Version : ").append(JDK).append(LS);
      if (withData) {
         sb.append("Data, bytes         :  0  1  2  3  4  5  6  7");

         for(long i = 0L; i < (long)lengthBytes; ++i) {
            int b = UnsafeUtil.unsafe.getByte(uObj, cumBaseOffset + offsetBytes + i) & 255;
            if (i % 8L == 0L) {
               sb.append(String.format("%n%20s: ", offsetBytes + i));
            }

            sb.append(String.format("%02x ", b));
         }

         sb.append(LS);
      }

      sb.append("### END SUMMARY");
      return sb.toString();
   }

   public final String toString(String header, long offsetBytes, int lengthBytes, boolean withData) {
      this.checkValid();
      String klass = this.getClass().getSimpleName();
      String s1 = String.format("(..., %d, %d)", offsetBytes, lengthBytes);
      long hcode = (long)this.hashCode() & 4294967295L;
      String call = ".toHexString" + s1 + ", hashCode: " + hcode;
      StringBuilder sb = new StringBuilder();
      sb.append("### ").append(klass).append(" SUMMARY ###").append(LS);
      sb.append("Type Info           : ").append(typeDecode(this.typeId)).append(LS + LS);
      sb.append("Header Comment      : ").append(header).append(LS);
      sb.append("Call Parameters     : ").append(call);
      return toHex(this, sb.toString(), offsetBytes, lengthBytes, withData);
   }

   public final String toString() {
      return this.toString("", 0L, (int)this.getCapacity(), false);
   }

   static final String typeDecode(int typeId) {
      StringBuilder sb = new StringBuilder();
      int group1 = typeId & 7;
      switch (group1) {
         case 0:
            sb.append(pad("Writable + ", 32));
            break;
         case 1:
            sb.append(pad("ReadOnly + ", 32));
            break;
         case 2:
            sb.append(pad("Writable + Region + ", 32));
            break;
         case 3:
            sb.append(pad("ReadOnly + Region + ", 32));
            break;
         case 4:
            sb.append(pad("Writable + Duplicate + ", 32));
            break;
         case 5:
            sb.append(pad("ReadOnly + Duplicate + ", 32));
            break;
         case 6:
            sb.append(pad("Writable + Region + Duplicate + ", 32));
            break;
         case 7:
            sb.append(pad("ReadOnly + Region + Duplicate + ", 32));
      }

      int group2 = typeId >>> 3 & 3;
      switch (group2) {
         case 0:
            sb.append(pad("Heap + ", 15));
            break;
         case 1:
            sb.append(pad("Direct + ", 15));
            break;
         case 2:
            sb.append(pad("Map + Direct + ", 15));
            break;
         case 3:
            sb.append(pad("Map + Direct + ", 15));
      }

      int group3 = typeId >>> 5 & 1;
      switch (group3) {
         case 0:
            sb.append(pad("NativeOrder + ", 17));
            break;
         case 1:
            sb.append(pad("NonNativeOrder + ", 17));
      }

      int group4 = typeId >>> 6 & 1;
      switch (group4) {
         case 0:
            sb.append(pad("Memory + ", 9));
            break;
         case 1:
            sb.append(pad("Buffer + ", 9));
      }

      int group5 = typeId >>> 7 & 1;
      switch (group5) {
         case 0:
            sb.append(pad("", 10));
            break;
         case 1:
            sb.append(pad("ByteBuffer", 10));
      }

      return sb.toString();
   }

   public final long xxHash64(long offsetBytes, long lengthBytes, long seed) {
      this.checkValid();
      return XxHash64.hash(this.getUnsafeObject(), this.getCumulativeOffset(0L) + offsetBytes, lengthBytes, seed);
   }

   public final long xxHash64(long in, long seed) {
      return XxHash64.hash(in, seed);
   }

   static {
      String jdkVer = System.getProperty("java.version");
      int[] p = parseJavaVersion(jdkVer);
      JDK = p[0] + "." + p[1];
      JDK_MAJOR = p[0] == 1 ? p[1] : p[0];
   }
}
