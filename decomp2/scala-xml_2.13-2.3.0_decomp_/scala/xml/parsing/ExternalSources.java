package scala.xml.parsing;

import java.io.File;
import java.net.URL;
import scala.io.Source;
import scala.io.Source.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2\u0001b\u0001\u0003\u0011\u0002\u0007\u00051B\u000b\u0005\u0006!\u0001!\t!\u0005\u0005\u0006+\u0001!\tA\u0006\u0002\u0010\u000bb$XM\u001d8bYN{WO]2fg*\u0011QAB\u0001\ba\u0006\u00148/\u001b8h\u0015\t9\u0001\"A\u0002y[2T\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e\u001d5\t\u0001\"\u0003\u0002\u0010\u0011\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\n\u0011\u00055\u0019\u0012B\u0001\u000b\t\u0005\u0011)f.\u001b;\u0002\u001d\u0015DH/\u001a:oC2\u001cv.\u001e:dKR\u0011q#\b\t\u00031mi\u0011!\u0007\u0006\u00035!\t!![8\n\u0005qI\"AB*pkJ\u001cW\rC\u0003\u001f\u0005\u0001\u0007q$\u0001\u0005tsN$X-\\%e!\t\u0001sE\u0004\u0002\"KA\u0011!\u0005C\u0007\u0002G)\u0011AEC\u0001\u0007yI|w\u000e\u001e \n\u0005\u0019B\u0011A\u0002)sK\u0012,g-\u0003\u0002)S\t11\u000b\u001e:j]\u001eT!A\n\u0005\u0013\t-jsF\r\u0004\u0005Y\u0001\u0001!F\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002/\u00015\tA\u0001\u0005\u0002/a%\u0011\u0011\u0007\u0002\u0002\r\u001b\u0006\u00148.\u001e9QCJ\u001cXM\u001d\t\u0003]MJ!\u0001\u000e\u0003\u0003\u001b5\u000b'o[;q\u0011\u0006tG\r\\3s\u0001"
)
public interface ExternalSources {
   // $FF: synthetic method
   static Source externalSource$(final ExternalSources $this, final String systemId) {
      return $this.externalSource(systemId);
   }

   default Source externalSource(final String systemId) {
      if (systemId.startsWith("http:")) {
         return .MODULE$.fromURL(new URL(systemId), scala.io.Codec..MODULE$.fallbackSystemCodec());
      } else {
         String var4 = ((MarkupParser)this).input().descr();
         switch (var4 == null ? 0 : var4.hashCode()) {
            default:
               String fileStr = var4.startsWith("file:") ? scala.collection.StringOps..MODULE$.drop$extension(scala.Predef..MODULE$.augmentString(var4), 5) : scala.collection.StringOps..MODULE$.take$extension(scala.Predef..MODULE$.augmentString(var4), var4.lastIndexOf(File.separator) + 1);
               return .MODULE$.fromFile((new StringBuilder(0)).append(fileStr).append(systemId).toString(), scala.io.Codec..MODULE$.fallbackSystemCodec());
         }
      }
   }

   static void $init$(final ExternalSources $this) {
   }
}
