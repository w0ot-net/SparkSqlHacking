module org.apache.arrow.vector {
   requires com.fasterxml.jackson.annotation;
   requires com.fasterxml.jackson.core;
   requires com.fasterxml.jackson.databind;
   requires com.fasterxml.jackson.datatype.jsr310;
   requires flatbuffers.java;
   requires jdk.unsupported;
   requires org.apache.arrow.format;
   requires org.apache.arrow.memory.core;
   requires org.apache.commons.codec;
   requires org.slf4j;

   exports org.apache.arrow.vector;
   exports org.apache.arrow.vector.compare;
   exports org.apache.arrow.vector.compare.util;
   exports org.apache.arrow.vector.complex;
   exports org.apache.arrow.vector.complex.impl;
   exports org.apache.arrow.vector.complex.reader;
   exports org.apache.arrow.vector.complex.writer;
   exports org.apache.arrow.vector.compression;
   exports org.apache.arrow.vector.dictionary;
   exports org.apache.arrow.vector.extension;
   exports org.apache.arrow.vector.holders;
   exports org.apache.arrow.vector.ipc;
   exports org.apache.arrow.vector.ipc.message;
   exports org.apache.arrow.vector.table;
   exports org.apache.arrow.vector.types;
   exports org.apache.arrow.vector.types.pojo;
   exports org.apache.arrow.vector.util;
   exports org.apache.arrow.vector.validate;

   opens org.apache.arrow.vector.types.pojo to
      com.fasterxml.jackson.databind;

   uses org.apache.arrow.vector.compression.CompressionCodec$Factory;
}
