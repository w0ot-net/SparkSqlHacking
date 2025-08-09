package breeze.io;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import scala.collection.IterableOnce;
import scala.collection.JavaConverters.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;

public final class CSVWriter$ {
   public static final CSVWriter$ MODULE$ = new CSVWriter$();

   public void write(final Writer output, final IterableOnce mat, final char separator, final char quote, final char escape) {
      au.com.bytecode.opencsv.CSVWriter writer = new au.com.bytecode.opencsv.CSVWriter(output, separator, quote, escape);
      if (mat instanceof Seq) {
         Seq var9 = (Seq)mat;
         writer.writeAll((List).MODULE$.seqAsJavaListConverter((scala.collection.Seq)var9.map((x$1) -> (String[])x$1.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)))).asJava());
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         mat.iterator().foreach((l) -> {
            $anonfun$write$2(writer, l);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10 = BoxedUnit.UNIT;
      }

      writer.flush();
   }

   public char write$default$3() {
      return ',';
   }

   public char write$default$4() {
      return '"';
   }

   public char write$default$5() {
      return '\\';
   }

   public void writeFile(final File file, final IndexedSeq mat, final char separator, final char quote, final char escape) {
      FileWriter out = new FileWriter(file);
      this.write(out, mat, separator, quote, escape);
      out.close();
   }

   public char writeFile$default$3() {
      return ',';
   }

   public char writeFile$default$4() {
      return '"';
   }

   public char writeFile$default$5() {
      return '\\';
   }

   public String mkString(final IndexedSeq mat, final char separator, final char quote, final char escape) {
      StringWriter out = new StringWriter();
      this.write(out, mat, separator, quote, escape);
      return out.toString();
   }

   public char mkString$default$2() {
      return ',';
   }

   public char mkString$default$3() {
      return '"';
   }

   public char mkString$default$4() {
      return '\\';
   }

   // $FF: synthetic method
   public static final void $anonfun$write$2(final au.com.bytecode.opencsv.CSVWriter writer$1, final IndexedSeq l) {
      writer$1.writeNext((String[])l.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   private CSVWriter$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
