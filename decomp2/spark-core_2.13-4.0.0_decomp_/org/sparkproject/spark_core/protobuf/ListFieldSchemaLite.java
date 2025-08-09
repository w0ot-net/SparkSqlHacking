package org.sparkproject.spark_core.protobuf;

import java.util.List;

final class ListFieldSchemaLite implements ListFieldSchema {
   public List mutableListAt(Object message, long offset) {
      Internal.ProtobufList<L> list = getProtobufList(message, offset);
      if (!list.isModifiable()) {
         int size = list.size();
         list = list.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
         UnsafeUtil.putObject((Object)message, offset, list);
      }

      return list;
   }

   public void makeImmutableListAt(Object message, long offset) {
      Internal.ProtobufList<?> list = getProtobufList(message, offset);
      list.makeImmutable();
   }

   public void mergeListsAt(Object msg, Object otherMsg, long offset) {
      Internal.ProtobufList<E> mine = getProtobufList(msg, offset);
      Internal.ProtobufList<E> other = getProtobufList(otherMsg, offset);
      int size = mine.size();
      int otherSize = other.size();
      if (size > 0 && otherSize > 0) {
         if (!mine.isModifiable()) {
            mine = mine.mutableCopyWithCapacity(size + otherSize);
         }

         mine.addAll(other);
      }

      Internal.ProtobufList<E> merged = size > 0 ? mine : other;
      UnsafeUtil.putObject((Object)msg, offset, merged);
   }

   static Internal.ProtobufList getProtobufList(Object message, long offset) {
      return (Internal.ProtobufList)UnsafeUtil.getObject(message, offset);
   }
}
