package scala.xml;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import org.xml.sax.InputSource;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t<Qa\u0003\u0007\t\u0002E1Qa\u0005\u0007\t\u0002QAQ!G\u0001\u0005\u0002iAQaG\u0001\u0005\u0002qAQaG\u0001\u0005\u0002EBQ\u0001P\u0001\u0005\u0002uBQAR\u0001\u0005\u0002\u001dCQaG\u0001\u0005\u0002)CQ\u0001U\u0001\u0005\u0002ECQaV\u0001\u0005\u0002aCQaW\u0001\u0005\u0002q\u000baaU8ve\u000e,'BA\u0007\u000f\u0003\rAX\u000e\u001c\u0006\u0002\u001f\u0005)1oY1mC\u000e\u0001\u0001C\u0001\n\u0002\u001b\u0005a!AB*pkJ\u001cWm\u0005\u0002\u0002+A\u0011acF\u0007\u0002\u001d%\u0011\u0001D\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005\t\u0012\u0001\u00034s_64\u0015\u000e\\3\u0015\u0005u!\u0003C\u0001\u0010\"\u001d\t\u0011r$\u0003\u0002!\u0019\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0012$\u0005-Ie\u000e];u'>,(oY3\u000b\u0005\u0001b\u0001\"B\u0013\u0004\u0001\u00041\u0013\u0001\u00028b[\u0016\u0004\"a\n\u0018\u000f\u0005!b\u0003CA\u0015\u000f\u001b\u0005Q#BA\u0016\u0011\u0003\u0019a$o\\8u}%\u0011QFD\u0001\u0007!J,G-\u001a4\n\u0005=\u0002$AB*ue&twM\u0003\u0002.\u001dQ\u0011QD\r\u0005\u0006g\u0011\u0001\r\u0001N\u0001\u0005M&dW\r\u0005\u00026u5\taG\u0003\u00028q\u0005\u0011\u0011n\u001c\u0006\u0002s\u0005!!.\u0019<b\u0013\tYdG\u0001\u0003GS2,\u0017a\u00024s_6,&\u000f\u001c\u000b\u0003;yBQaP\u0003A\u0002\u0001\u000b1!\u001e:m!\t\tE)D\u0001C\u0015\t\u0019\u0005(A\u0002oKRL!!\u0012\"\u0003\u0007U\u0013F*A\u0005ge>l7+_:JIR\u0011Q\u0004\u0013\u0005\u0006\u0013\u001a\u0001\rAJ\u0001\u0006gf\u001c\u0018\n\u0012\u000b\u0003;-CQ\u0001T\u0004A\u00025\u000b!A\u001a3\u0011\u0005Ur\u0015BA(7\u000591\u0015\u000e\\3EKN\u001c'/\u001b9u_J\fqB\u001a:p[&s\u0007/\u001e;TiJ,\u0017-\u001c\u000b\u0003;ICQa\u0015\u0005A\u0002Q\u000b!![:\u0011\u0005U*\u0016B\u0001,7\u0005-Ie\u000e];u'R\u0014X-Y7\u0002\u0015\u0019\u0014x.\\*ue&tw\r\u0006\u0002\u001e3\")!,\u0003a\u0001M\u000511\u000f\u001e:j]\u001e\f!B\u001a:p[J+\u0017\rZ3s)\tiR\fC\u0003_\u0015\u0001\u0007q,\u0001\u0004sK\u0006$WM\u001d\t\u0003k\u0001L!!\u0019\u001c\u0003\rI+\u0017\rZ3s\u0001"
)
public final class Source {
   public static InputSource fromReader(final Reader reader) {
      return Source$.MODULE$.fromReader(reader);
   }

   public static InputSource fromString(final String string) {
      return Source$.MODULE$.fromString(string);
   }

   public static InputSource fromInputStream(final InputStream is) {
      return Source$.MODULE$.fromInputStream(is);
   }

   public static InputSource fromFile(final FileDescriptor fd) {
      return Source$.MODULE$.fromFile(fd);
   }

   public static InputSource fromSysId(final String sysID) {
      return Source$.MODULE$.fromSysId(sysID);
   }

   public static InputSource fromUrl(final URL url) {
      return Source$.MODULE$.fromUrl(url);
   }

   public static InputSource fromFile(final File file) {
      return Source$.MODULE$.fromFile(file);
   }

   public static InputSource fromFile(final String name) {
      return Source$.MODULE$.fromFile(name);
   }
}
