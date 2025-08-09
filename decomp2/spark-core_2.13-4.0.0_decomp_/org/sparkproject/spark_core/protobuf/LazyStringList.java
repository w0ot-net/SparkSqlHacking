package org.sparkproject.spark_core.protobuf;

import java.util.Collection;
import java.util.List;

public interface LazyStringList extends ProtocolStringList {
   ByteString getByteString(int index);

   Object getRaw(int index);

   byte[] getByteArray(int index);

   void add(ByteString element);

   void add(byte[] element);

   void set(int index, ByteString element);

   void set(int index, byte[] element);

   boolean addAllByteString(Collection c);

   boolean addAllByteArray(Collection c);

   List getUnderlyingElements();

   void mergeFrom(LazyStringList other);

   List asByteArrayList();

   LazyStringList getUnmodifiableView();
}
