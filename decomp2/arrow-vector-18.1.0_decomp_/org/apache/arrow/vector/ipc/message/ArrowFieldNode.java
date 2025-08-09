package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import org.apache.arrow.flatbuf.FieldNode;
import org.apache.arrow.memory.util.LargeMemoryUtil;

public class ArrowFieldNode implements FBSerializable {
   private final int length;
   private final int nullCount;

   public ArrowFieldNode(long length, long nullCount) {
      this.length = LargeMemoryUtil.checkedCastToInt(length);
      this.nullCount = LargeMemoryUtil.checkedCastToInt(nullCount);
   }

   public int writeTo(FlatBufferBuilder builder) {
      return FieldNode.createFieldNode(builder, (long)this.length, (long)this.nullCount);
   }

   public int getNullCount() {
      return this.nullCount;
   }

   public int getLength() {
      return this.length;
   }

   public String toString() {
      return "ArrowFieldNode [length=" + this.length + ", nullCount=" + this.nullCount + "]";
   }
}
