package org.json4s;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u9Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQaF\u0001\u0005\u0002a1q\u0001D\u0003\u0011\u0002G\u0005\u0011$\u0001\nEK\u001a\fW\u000f\u001c;Kg>tgi\u001c:nCR\u001c(B\u0001\u0004\b\u0003\u0019Q7o\u001c85g*\t\u0001\"A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\f\u00035\tQA\u0001\nEK\u001a\fW\u000f\u001c;Kg>tgi\u001c:nCR\u001c8cA\u0001\u000f)A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u0004\"aC\u000b\n\u0005Y)!!\u0005#pk\ndWMS:p]\u001a{'/\\1ug\u00061A(\u001b8jiz\"\u0012AC\n\u0004\u00079Q\u0002CA\u0006\u001c\u0013\taRA\u0001\bEK\u001a\fW\u000f\u001c;SK\u0006$WM]:"
)
public interface DefaultJsonFormats extends DefaultReaders {
   static Writer BigDecimalWriter() {
      return DefaultJsonFormats$.MODULE$.BigDecimalWriter();
   }

   static Writer DoubleWriter() {
      return DefaultJsonFormats$.MODULE$.DoubleWriter();
   }

   static Writer FloatWriter() {
      return DefaultJsonFormats$.MODULE$.FloatWriter();
   }

   static Writer OptionWriter(final Writer valueWriter) {
      return DefaultJsonFormats$.MODULE$.OptionWriter(valueWriter);
   }

   static Writer JValueWriter() {
      return DefaultJsonFormats$.MODULE$.JValueWriter();
   }

   static Writer mapWriter(final JsonKeyWriter keyWriter, final Writer valueWriter) {
      return DefaultJsonFormats$.MODULE$.mapWriter(keyWriter, valueWriter);
   }

   static Writer seqWriter(final Writer evidence$1) {
      return DefaultJsonFormats$.MODULE$.seqWriter(evidence$1);
   }

   static Writer arrayWriter(final Writer valueWriter) {
      return DefaultJsonFormats$.MODULE$.arrayWriter(valueWriter);
   }

   static Writer StringWriter() {
      return DefaultJsonFormats$.MODULE$.StringWriter();
   }

   static Writer BooleanWriter() {
      return DefaultJsonFormats$.MODULE$.BooleanWriter();
   }

   static Writer BigIntWriter() {
      return DefaultJsonFormats$.MODULE$.BigIntWriter();
   }

   static Writer LongWriter() {
      return DefaultJsonFormats$.MODULE$.LongWriter();
   }

   static Writer ShortWriter() {
      return DefaultJsonFormats$.MODULE$.ShortWriter();
   }

   static Writer ByteWriter() {
      return DefaultJsonFormats$.MODULE$.ByteWriter();
   }

   static Writer IntWriter() {
      return DefaultJsonFormats$.MODULE$.IntWriter();
   }
}
