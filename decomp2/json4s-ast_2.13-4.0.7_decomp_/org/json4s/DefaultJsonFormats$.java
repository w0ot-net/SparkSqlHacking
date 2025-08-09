package org.json4s;

import scala.collection.Factory;
import scala.reflect.ClassTag;

public final class DefaultJsonFormats$ implements DoubleJsonFormats {
   public static final DefaultJsonFormats$ MODULE$ = new DefaultJsonFormats$();
   private static Writer FloatWriter;
   private static Writer DoubleWriter;
   private static Writer BigDecimalWriter;
   private static Writer IntWriter;
   private static Writer ByteWriter;
   private static Writer ShortWriter;
   private static Writer LongWriter;
   private static Writer BigIntWriter;
   private static Writer BooleanWriter;
   private static Writer StringWriter;
   private static Writer JValueWriter;
   private static Reader IntReader;
   private static Reader BigIntReader;
   private static Reader LongReader;
   private static Reader ShortReader;
   private static Reader ByteReader;
   private static Reader FloatReader;
   private static Reader DoubleReader;
   private static Reader BigDecimalReader;
   private static Reader BooleanReader;
   private static Reader StringReader;
   private static Reader JValueReader;
   private static Reader JObjectReader;
   private static Reader JArrayReader;

   static {
      DefaultReaders0.$init$(MODULE$);
      DefaultReaders.$init$(MODULE$);
      DefaultWriters.$init$(MODULE$);
      DoubleWriters.$init$(MODULE$);
   }

   public Writer arrayWriter(final Writer valueWriter) {
      return DefaultWriters.arrayWriter$(this, valueWriter);
   }

   public Writer seqWriter(final Writer evidence$1) {
      return DefaultWriters.seqWriter$(this, evidence$1);
   }

   public Writer mapWriter(final JsonKeyWriter keyWriter, final Writer valueWriter) {
      return DefaultWriters.mapWriter$(this, keyWriter, valueWriter);
   }

   public Writer OptionWriter(final Writer valueWriter) {
      return DefaultWriters.OptionWriter$(this, valueWriter);
   }

   public Reader mapReader(final Reader valueReader) {
      return DefaultReaders.mapReader$(this, valueReader);
   }

   public Reader arrayReader(final ClassTag evidence$1, final Reader evidence$2) {
      return DefaultReaders.arrayReader$(this, evidence$1, evidence$2);
   }

   public Reader OptionReader(final Reader valueReader) {
      return DefaultReaders.OptionReader$(this, valueReader);
   }

   public Reader iterableReader(final Factory f, final Reader valueReader) {
      return DefaultReaders0.iterableReader$(this, f, valueReader);
   }

   public Writer FloatWriter() {
      return FloatWriter;
   }

   public Writer DoubleWriter() {
      return DoubleWriter;
   }

   public Writer BigDecimalWriter() {
      return BigDecimalWriter;
   }

   public void org$json4s$DoubleWriters$_setter_$FloatWriter_$eq(final Writer x$1) {
      FloatWriter = x$1;
   }

   public void org$json4s$DoubleWriters$_setter_$DoubleWriter_$eq(final Writer x$1) {
      DoubleWriter = x$1;
   }

   public void org$json4s$DoubleWriters$_setter_$BigDecimalWriter_$eq(final Writer x$1) {
      BigDecimalWriter = x$1;
   }

   public Writer IntWriter() {
      return IntWriter;
   }

   public Writer ByteWriter() {
      return ByteWriter;
   }

   public Writer ShortWriter() {
      return ShortWriter;
   }

   public Writer LongWriter() {
      return LongWriter;
   }

   public Writer BigIntWriter() {
      return BigIntWriter;
   }

   public Writer BooleanWriter() {
      return BooleanWriter;
   }

   public Writer StringWriter() {
      return StringWriter;
   }

   public Writer JValueWriter() {
      return JValueWriter;
   }

   public void org$json4s$DefaultWriters$_setter_$IntWriter_$eq(final Writer x$1) {
      IntWriter = x$1;
   }

   public void org$json4s$DefaultWriters$_setter_$ByteWriter_$eq(final Writer x$1) {
      ByteWriter = x$1;
   }

   public void org$json4s$DefaultWriters$_setter_$ShortWriter_$eq(final Writer x$1) {
      ShortWriter = x$1;
   }

   public void org$json4s$DefaultWriters$_setter_$LongWriter_$eq(final Writer x$1) {
      LongWriter = x$1;
   }

   public void org$json4s$DefaultWriters$_setter_$BigIntWriter_$eq(final Writer x$1) {
      BigIntWriter = x$1;
   }

   public void org$json4s$DefaultWriters$_setter_$BooleanWriter_$eq(final Writer x$1) {
      BooleanWriter = x$1;
   }

   public void org$json4s$DefaultWriters$_setter_$StringWriter_$eq(final Writer x$1) {
      StringWriter = x$1;
   }

   public void org$json4s$DefaultWriters$_setter_$JValueWriter_$eq(final Writer x$1) {
      JValueWriter = x$1;
   }

   public Reader IntReader() {
      return IntReader;
   }

   public Reader BigIntReader() {
      return BigIntReader;
   }

   public Reader LongReader() {
      return LongReader;
   }

   public Reader ShortReader() {
      return ShortReader;
   }

   public Reader ByteReader() {
      return ByteReader;
   }

   public Reader FloatReader() {
      return FloatReader;
   }

   public Reader DoubleReader() {
      return DoubleReader;
   }

   public Reader BigDecimalReader() {
      return BigDecimalReader;
   }

   public Reader BooleanReader() {
      return BooleanReader;
   }

   public Reader StringReader() {
      return StringReader;
   }

   public Reader JValueReader() {
      return JValueReader;
   }

   public Reader JObjectReader() {
      return JObjectReader;
   }

   public Reader JArrayReader() {
      return JArrayReader;
   }

   public void org$json4s$DefaultReaders$_setter_$IntReader_$eq(final Reader x$1) {
      IntReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$BigIntReader_$eq(final Reader x$1) {
      BigIntReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$LongReader_$eq(final Reader x$1) {
      LongReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$ShortReader_$eq(final Reader x$1) {
      ShortReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$ByteReader_$eq(final Reader x$1) {
      ByteReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$FloatReader_$eq(final Reader x$1) {
      FloatReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$DoubleReader_$eq(final Reader x$1) {
      DoubleReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$BigDecimalReader_$eq(final Reader x$1) {
      BigDecimalReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$BooleanReader_$eq(final Reader x$1) {
      BooleanReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$StringReader_$eq(final Reader x$1) {
      StringReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$JValueReader_$eq(final Reader x$1) {
      JValueReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$JObjectReader_$eq(final Reader x$1) {
      JObjectReader = x$1;
   }

   public void org$json4s$DefaultReaders$_setter_$JArrayReader_$eq(final Reader x$1) {
      JArrayReader = x$1;
   }

   private DefaultJsonFormats$() {
   }
}
