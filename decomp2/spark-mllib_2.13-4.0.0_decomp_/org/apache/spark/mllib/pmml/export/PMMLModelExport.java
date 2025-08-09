package org.apache.spark.mllib.pmml.export;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.sparkproject.dmg.pmml.Application;
import org.sparkproject.dmg.pmml.DataDictionary;
import org.sparkproject.dmg.pmml.Header;
import org.sparkproject.dmg.pmml.PMML;
import org.sparkproject.dmg.pmml.Timestamp;
import org.sparkproject.dmg.pmml.Version;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005!B\u0005\u0005\u00063\u0001!\ta\u0007\u0005\b?\u0001\u0011\r\u0011\"\u0003!\u0011\u001dI\u0001A1A\u0005\u0002-BQA\u000f\u0001\u0005\u0002m\u0012q\u0002U'N\u00196{G-\u001a7FqB|'\u000f\u001e\u0006\u0003\u000f!\ta!\u001a=q_J$(BA\u0005\u000b\u0003\u0011\u0001X.\u001c7\u000b\u0005-a\u0011!B7mY&\u0014'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005a\u0002C\u0001\u000b\u001e\u0013\tqRC\u0001\u0003V]&$\u0018a\u0005#B)\u0016{F+S'F?\u001a{%+T!U)\u0016\u0013V#A\u0011\u0011\u0005\tJS\"A\u0012\u000b\u0005\u0011*\u0013A\u00024pe6\fGO\u0003\u0002'O\u0005!A/[7f\u0015\u0005A\u0013\u0001\u00026bm\u0006L!AK\u0012\u0003#\u0011\u000bG/\u001a+j[\u00164uN]7biR,'/F\u0001-!\ti\u0013'D\u0001/\u0015\tIqF\u0003\u00021!\u0005\u0019A-\\4\n\u0005Ir#\u0001\u0002)N\u001b2C#a\u0001\u001b\u0011\u0005UBT\"\u0001\u001c\u000b\u0005]*\u0012!\u00022fC:\u001c\u0018BA\u001d7\u00051\u0011U-\u00198Qe>\u0004XM\u001d;z\u0003\u001d9W\r\u001e)n[2$\u0012\u0001\f\u0015\u0003\tQ\u0002"
)
public interface PMMLModelExport {
   void org$apache$spark$mllib$pmml$export$PMMLModelExport$_setter_$org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER_$eq(final DateTimeFormatter x$1);

   void org$apache$spark$mllib$pmml$export$PMMLModelExport$_setter_$pmml_$eq(final PMML x$1);

   DateTimeFormatter org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER();

   PMML pmml();

   // $FF: synthetic method
   static PMML getPmml$(final PMMLModelExport $this) {
      return $this.getPmml();
   }

   default PMML getPmml() {
      return this.pmml();
   }

   static void $init$(final PMMLModelExport $this) {
      $this.org$apache$spark$mllib$pmml$export$PMMLModelExport$_setter_$org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER_$eq(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.US).withZone(ZoneId.systemDefault()));
      String version = $this.getClass().getPackage().getImplementationVersion();
      Application app = (new Application("Apache Spark MLlib")).setVersion(version);
      Timestamp timestamp = (new Timestamp()).addContent($this.org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER().format(Instant.now()));
      Header header = (new Header()).setApplication(app).setTimestamp(timestamp);
      $this.org$apache$spark$mllib$pmml$export$PMMLModelExport$_setter_$pmml_$eq(new PMML(Version.PMML_4_4.getVersion(), header, (DataDictionary)null));
   }
}
