package org.json4s;

public final class BigDecimalWriters$ implements BigDecimalWriters {
   public static final BigDecimalWriters$ MODULE$ = new BigDecimalWriters$();
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

   static {
      DefaultWriters.$init$(MODULE$);
      BigDecimalWriters.$init$(MODULE$);
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

   public Writer FloatWriter() {
      return FloatWriter;
   }

   public Writer DoubleWriter() {
      return DoubleWriter;
   }

   public Writer BigDecimalWriter() {
      return BigDecimalWriter;
   }

   public void org$json4s$BigDecimalWriters$_setter_$FloatWriter_$eq(final Writer x$1) {
      FloatWriter = x$1;
   }

   public void org$json4s$BigDecimalWriters$_setter_$DoubleWriter_$eq(final Writer x$1) {
      DoubleWriter = x$1;
   }

   public void org$json4s$BigDecimalWriters$_setter_$BigDecimalWriter_$eq(final Writer x$1) {
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

   private BigDecimalWriters$() {
   }
}
