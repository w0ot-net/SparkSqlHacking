package org.apache.spark.sql.streaming;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.module.scala.ClassTagExtensions;
import com.fasterxml.jackson.module.scala.JavaTypeable;
import com.fasterxml.jackson.module.scala.DefaultScalaModule.;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import scala.reflect.ClassTag;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingQueryProgress$ implements Serializable {
   public static final StreamingQueryProgress$ MODULE$ = new StreamingQueryProgress$();
   private static final ObjectMapper mapper;

   static {
      ObjectMapper ret = new ClassTagExtensions() {
         /** @deprecated */
         public final ObjectMapper addMixin(final ClassTag evidence$1, final ClassTag evidence$2) {
            return ClassTagExtensions.addMixin$(this, evidence$1, evidence$2);
         }

         /** @deprecated */
         public final Class findMixInClassFor(final ClassTag evidence$3) {
            return ClassTagExtensions.findMixInClassFor$(this, evidence$3);
         }

         public JavaType constructType(final JavaTypeable evidence$4) {
            return ClassTagExtensions.constructType$(this, evidence$4);
         }

         public Object readValue(final JsonParser jp, final JavaTypeable evidence$5) {
            return ClassTagExtensions.readValue$(this, jp, evidence$5);
         }

         public MappingIterator readValues(final JsonParser jp, final JavaTypeable evidence$6) {
            return ClassTagExtensions.readValues$(this, jp, evidence$6);
         }

         public Object treeToValue(final TreeNode n, final JavaTypeable evidence$7) {
            return ClassTagExtensions.treeToValue$(this, n, evidence$7);
         }

         public Object readValue(final File src, final JavaTypeable evidence$8) {
            return ClassTagExtensions.readValue$(this, src, evidence$8);
         }

         public Object readValue(final URL src, final JavaTypeable evidence$9) {
            return ClassTagExtensions.readValue$(this, src, evidence$9);
         }

         public Object readValue(final String content, final JavaTypeable evidence$10) {
            return ClassTagExtensions.readValue$(this, content, evidence$10);
         }

         public Object readValue(final Reader src, final JavaTypeable evidence$11) {
            return ClassTagExtensions.readValue$(this, src, evidence$11);
         }

         public Object readValue(final InputStream src, final JavaTypeable evidence$12) {
            return ClassTagExtensions.readValue$(this, src, evidence$12);
         }

         public Object readValue(final byte[] src, final JavaTypeable evidence$13) {
            return ClassTagExtensions.readValue$(this, src, evidence$13);
         }

         public Object readValue(final byte[] src, final int offset, final int len, final JavaTypeable evidence$14) {
            return ClassTagExtensions.readValue$(this, src, offset, len, evidence$14);
         }

         public Object updateValue(final Object valueToUpdate, final File src, final JavaTypeable evidence$15) {
            return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$15);
         }

         public Object updateValue(final Object valueToUpdate, final URL src, final JavaTypeable evidence$16) {
            return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$16);
         }

         public Object updateValue(final Object valueToUpdate, final String content, final JavaTypeable evidence$17) {
            return ClassTagExtensions.updateValue$(this, valueToUpdate, content, evidence$17);
         }

         public Object updateValue(final Object valueToUpdate, final Reader src, final JavaTypeable evidence$18) {
            return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$18);
         }

         public Object updateValue(final Object valueToUpdate, final InputStream src, final JavaTypeable evidence$19) {
            return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$19);
         }

         public Object updateValue(final Object valueToUpdate, final byte[] src, final JavaTypeable evidence$20) {
            return ClassTagExtensions.updateValue$(this, valueToUpdate, src, evidence$20);
         }

         public Object updateValue(final Object valueToUpdate, final byte[] src, final int offset, final int len, final JavaTypeable evidence$21) {
            return ClassTagExtensions.updateValue$(this, valueToUpdate, src, offset, len, evidence$21);
         }

         public ObjectWriter writerWithView(final ClassTag evidence$23) {
            return ClassTagExtensions.writerWithView$(this, evidence$23);
         }

         public ObjectWriter writerFor(final JavaTypeable evidence$24) {
            return ClassTagExtensions.writerFor$(this, evidence$24);
         }

         public ObjectReader readerFor(final JavaTypeable evidence$25) {
            return ClassTagExtensions.readerFor$(this, evidence$25);
         }

         public ObjectReader readerWithView(final ClassTag evidence$26) {
            return ClassTagExtensions.readerWithView$(this, evidence$26);
         }

         public Object convertValue(final Object fromValue, final JavaTypeable evidence$27) {
            return ClassTagExtensions.convertValue$(this, fromValue, evidence$27);
         }

         public {
            ClassTagExtensions.$init$(this);
         }
      };
      ret.registerModule(.MODULE$);
      ret.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      mapper = ret;
   }

   public String jsonString(final StreamingQueryProgress progress) {
      return mapper.writeValueAsString(progress);
   }

   public StreamingQueryProgress fromJson(final String json) {
      return (StreamingQueryProgress)((ClassTagExtensions)mapper).readValue(json, com.fasterxml.jackson.module.scala.JavaTypeable..MODULE$.gen0JavaTypeable(scala.reflect.ClassTag..MODULE$.apply(StreamingQueryProgress.class)));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingQueryProgress$.class);
   }

   private StreamingQueryProgress$() {
   }
}
