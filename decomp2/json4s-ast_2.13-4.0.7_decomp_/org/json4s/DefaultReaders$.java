package org.json4s;

import scala.collection.Factory;
import scala.reflect.ClassTag;

public final class DefaultReaders$ implements DefaultReaders {
   public static final DefaultReaders$ MODULE$ = new DefaultReaders$();
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

   private DefaultReaders$() {
   }
}
