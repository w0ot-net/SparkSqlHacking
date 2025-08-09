package org.sparkproject.spark_core.protobuf;

import java.io.InputStream;
import java.nio.ByteBuffer;

public interface Parser {
   Object parseFrom(CodedInputStream input) throws InvalidProtocolBufferException;

   Object parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parsePartialFrom(CodedInputStream input) throws InvalidProtocolBufferException;

   Object parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parseFrom(ByteBuffer data) throws InvalidProtocolBufferException;

   Object parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parseFrom(ByteString data) throws InvalidProtocolBufferException;

   Object parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parsePartialFrom(ByteString data) throws InvalidProtocolBufferException;

   Object parsePartialFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parseFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException;

   Object parseFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parseFrom(byte[] data) throws InvalidProtocolBufferException;

   Object parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parsePartialFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException;

   Object parsePartialFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parsePartialFrom(byte[] data) throws InvalidProtocolBufferException;

   Object parsePartialFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parseFrom(InputStream input) throws InvalidProtocolBufferException;

   Object parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parsePartialFrom(InputStream input) throws InvalidProtocolBufferException;

   Object parsePartialFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parseDelimitedFrom(InputStream input) throws InvalidProtocolBufferException;

   Object parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;

   Object parsePartialDelimitedFrom(InputStream input) throws InvalidProtocolBufferException;

   Object parsePartialDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException;
}
