package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;

public interface FBSerializable {
   int writeTo(FlatBufferBuilder var1);
}
