package org.apache.spark.mllib.pmml;

import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import javax.xml.transform.stream.StreamResult;
import org.apache.spark.SparkContext;
import org.apache.spark.mllib.pmml.export.PMMLModelExport;
import org.apache.spark.mllib.pmml.export.PMMLModelExportFactory$;
import org.sparkproject.jpmml.model.JAXBSerializer;
import scala.collection.immutable.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}3qa\u0002\u0005\u0011\u0002\u0007\u00051\u0003C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011%\u0001\u0005C\u0003 \u0001\u0011\u0005q\u0006C\u0003 \u0001\u0011\u0005a\tC\u0003 \u0001\u0011\u0005\u0001\u000bC\u0003 \u0001\u0011\u0005AL\u0001\bQ\u001b6cU\t\u001f9peR\f'\r\\3\u000b\u0005%Q\u0011\u0001\u00029n[2T!a\u0003\u0007\u0002\u000b5dG.\u001b2\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001)A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u000f\u0011\u0005Ui\u0012B\u0001\u0010\u0017\u0005\u0011)f.\u001b;\u0002\rQ|\u0007+T'M)\ta\u0012\u0005C\u0003#\u0005\u0001\u00071%\u0001\u0007tiJ,\u0017-\u001c*fgVdG\u000f\u0005\u0002%[5\tQE\u0003\u0002'O\u000511\u000f\u001e:fC6T!\u0001K\u0015\u0002\u0013Q\u0014\u0018M\\:g_Jl'B\u0001\u0016,\u0003\rAX\u000e\u001c\u0006\u0002Y\u0005)!.\u0019<bq&\u0011a&\n\u0002\r'R\u0014X-Y7SKN,H\u000e\u001e\u000b\u00039ABQ!M\u0002A\u0002I\n\u0011\u0002\\8dC2\u0004\u0016\r\u001e5\u0011\u0005MRdB\u0001\u001b9!\t)d#D\u00017\u0015\t9$#\u0001\u0004=e>|GOP\u0005\u0003sY\ta\u0001\u0015:fI\u00164\u0017BA\u001e=\u0005\u0019\u0019FO]5oO*\u0011\u0011H\u0006\u0015\u0004\u0007y\"\u0005CA C\u001b\u0005\u0001%BA!\r\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0007\u0002\u0013QaU5oG\u0016\f\u0013!R\u0001\u0006c9\"d\u0006\r\u000b\u00049\u001dk\u0005\"\u0002%\u0005\u0001\u0004I\u0015AA:d!\tQ5*D\u0001\r\u0013\taEB\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000fC\u0003O\t\u0001\u0007!'\u0001\u0003qCRD\u0007f\u0001\u0003?\tR\u0011A$\u0015\u0005\u0006%\u0016\u0001\raU\u0001\r_V$\b/\u001e;TiJ,\u0017-\u001c\t\u0003)fk\u0011!\u0016\u0006\u0003-^\u000b!![8\u000b\u0003a\u000bAA[1wC&\u0011!,\u0016\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0015\u0004\u000by\"E#\u0001\u001a)\u0007\u0019qD\tK\u0002\u0001}\u0011\u0003"
)
public interface PMMLExportable {
   private void toPMML(final StreamResult streamResult) {
      PMMLModelExport pmmlModelExport = PMMLModelExportFactory$.MODULE$.createPMMLModelExport(this);
      JAXBSerializer jaxbSerializer = new JAXBSerializer();
      jaxbSerializer.marshalPretty(pmmlModelExport.getPmml(), streamResult);
   }

   // $FF: synthetic method
   static void toPMML$(final PMMLExportable $this, final String localPath) {
      $this.toPMML(localPath);
   }

   default void toPMML(final String localPath) {
      this.toPMML(new StreamResult(new File(localPath)));
   }

   // $FF: synthetic method
   static void toPMML$(final PMMLExportable $this, final SparkContext sc, final String path) {
      $this.toPMML(sc, path);
   }

   default void toPMML(final SparkContext sc, final String path) {
      String pmml = this.toPMML();
      sc.parallelize(new .colon.colon(pmml, scala.collection.immutable.Nil..MODULE$), 1, scala.reflect.ClassTag..MODULE$.apply(String.class)).saveAsTextFile(path);
   }

   // $FF: synthetic method
   static void toPMML$(final PMMLExportable $this, final OutputStream outputStream) {
      $this.toPMML(outputStream);
   }

   default void toPMML(final OutputStream outputStream) {
      this.toPMML(new StreamResult(outputStream));
   }

   // $FF: synthetic method
   static String toPMML$(final PMMLExportable $this) {
      return $this.toPMML();
   }

   default String toPMML() {
      StringWriter writer = new StringWriter();
      this.toPMML(new StreamResult(writer));
      return writer.toString();
   }

   static void $init$(final PMMLExportable $this) {
   }
}
