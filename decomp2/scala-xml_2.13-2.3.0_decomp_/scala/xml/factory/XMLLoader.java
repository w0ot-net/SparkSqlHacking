package scala.xml.factory;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.xml.Document;
import scala.xml.Node;
import scala.xml.NodeSeq$;
import scala.xml.Source$;
import scala.xml.parsing.FactoryAdapter;
import scala.xml.parsing.NoBindingFactoryAdapter;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055haB\u0013'!\u0003\r\t!\f\u0005\u0006g\u0001!\t\u0001\u000e\u0005\u0006q\u0001!I!\u000f\u0005\t\u000b\u0002A)\u0019!C\u0005\r\")q\u000b\u0001C\u00011\")\u0011\f\u0001C\u00015\")A\r\u0001C\u0001K\")q\u000f\u0001C\u0001q\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0005\b\u0003?\u0001A\u0011AA\u0011\u0011\u001d\ty\u0002\u0001C\u0001\u0003{Aq!a\u0005\u0001\t\u0003\ty\u0005C\u0004\u0002\u0014\u0001!\t!!\u0019\t\u000f\u0005}\u0001\u0001\"\u0001\u0002h!9\u00111\u0003\u0001\u0005\u0002\u0005M\u0004bBA\n\u0001\u0011\u0005\u0011q\u0010\u0005\b\u0003\u0013\u0003A\u0011AAF\u0011\u001d\t\t\n\u0001C\u0005\u0003'Cq!!'\u0001\t\u0003\tY\nC\u0004\u0002 \u0002!\t!!)\t\u000f\u0005}\u0005\u0001\"\u0001\u0002&\"9\u0011\u0011\u0014\u0001\u0005\u0002\u0005%\u0006bBAM\u0001\u0011\u0005\u0011Q\u0016\u0005\b\u0003?\u0003A\u0011AAY\u0011\u001d\tI\n\u0001C\u0001\u0003kCq!!'\u0001\t\u0003\tI\fC\u0004\u0002>\u0002!\t!a0\t\u000f\u0005\r\u0007\u0001\"\u0001\u0002F\"9\u0011\u0011\u001a\u0001\u0005\u0002\u0005-\u0007bBAe\u0001\u0011\u0005\u0011q\u001a\u0005\b\u0003\u0007\u0004A\u0011AAj\u0011\u001d\t\u0019\r\u0001C\u0001\u0003/Dq!!3\u0001\t\u0003\tY\u000eC\u0004\u0002D\u0002!\t!a8\t\u000f\u0005\r\u0007\u0001\"\u0001\u0002d\"9\u0011q\u001d\u0001\u0005\u0002\u0005%(!\u0003-N\u00192{\u0017\rZ3s\u0015\t9\u0003&A\u0004gC\u000e$xN]=\u000b\u0005%R\u0013a\u0001=nY*\t1&A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u00059B7C\u0001\u00010!\t\u0001\u0014'D\u0001+\u0013\t\u0011$F\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003U\u0002\"\u0001\r\u001c\n\u0005]R#\u0001B+oSR\fqb]3u'\u00064W\rR3gCVdGo\u001d\u000b\u0003kiBQa\u000f\u0002A\u0002q\nQ\u0002]1sg\u0016\u0014h)Y2u_JL\bCA\u001fD\u001b\u0005q$BA A\u0003\u001d\u0001\u0018M]:feNT!!K!\u000b\u0003\t\u000bQA[1wCbL!\u0001\u0012 \u0003!M\u000b\u0005\fU1sg\u0016\u0014h)Y2u_JL\u0018A\u00049beN,'/\u00138ti\u0006t7-Z\u000b\u0002\u000fB\u0019\u0001*T(\u000e\u0003%S!AS&\u0002\t1\fgn\u001a\u0006\u0002\u0019\u0006!!.\u0019<b\u0013\tq\u0015JA\u0006UQJ,\u0017\r\u001a'pG\u0006d\u0007C\u0001)U\u001d\t\t&+D\u0001)\u0013\t\u0019\u0006&A\u0004qC\u000e\\\u0017mZ3\n\u0005U3&!C*B1B\u000b'o]3s\u0015\t\u0019\u0006&\u0001\u0004qCJ\u001cXM]\u000b\u0002\u001f\u00061!/Z1eKJ,\u0012a\u0017\t\u00039\nl\u0011!\u0018\u0006\u0003=~\u000b1a]1y\u0015\tI\u0003MC\u0001b\u0003\ry'oZ\u0005\u0003Gv\u0013\u0011\u0002W'M%\u0016\fG-\u001a:\u0002\u000f1|\u0017\r\u001a-N\u0019R\u0019a-\u001d<\u0011\u0005\u001dDG\u0002\u0001\u0003\u0006S\u0002\u0011\rA\u001b\u0002\u0002)F\u00111N\u001c\t\u0003a1L!!\u001c\u0016\u0003\u000f9{G\u000f[5oOB\u0011\u0011k\\\u0005\u0003a\"\u0012AAT8eK\")!O\u0002a\u0001g\u0006Y\u0011N\u001c9viN{WO]2f!\t\u0001F/\u0003\u0002v-\nY\u0011J\u001c9viN{WO]2f\u0011\u00159f\u00011\u0001P\u00031aw.\u00193Y\u001b2su\u000eZ3t)\u0011Ix0!\u0001\u0011\u0007idhN\u0004\u00021w&\u00111KK\u0005\u0003{z\u00141aU3r\u0015\t\u0019&\u0006C\u0003s\u000f\u0001\u00071\u000fC\u0003X\u000f\u0001\u0007q*A\u0004bI\u0006\u0004H/\u001a:\u0016\u0005\u0005\u001d\u0001\u0003BA\u0005\u0003\u001fi!!a\u0003\u000b\u0007\u00055\u0001&A\u0004qCJ\u001c\u0018N\\4\n\t\u0005E\u00111\u0002\u0002\u000f\r\u0006\u001cGo\u001c:z\u0003\u0012\f\u0007\u000f^3s\u00031aw.\u00193E_\u000e,X.\u001a8u)\u0011\t9\"!\b\u0011\u0007E\u000bI\"C\u0002\u0002\u001c!\u0012\u0001\u0002R8dk6,g\u000e\u001e\u0005\u0006e&\u0001\ra]\u0001\u0011Y>\fGMR5mK\u0012{7-^7f]R$B!a\u0006\u0002$!9\u0011Q\u0005\u0006A\u0002\u0005\u001d\u0012\u0001\u00034jY\u0016t\u0015-\\3\u0011\t\u0005%\u0012q\u0007\b\u0005\u0003W\t\u0019\u0004E\u0002\u0002.)j!!a\f\u000b\u0007\u0005EB&\u0001\u0004=e>|GOP\u0005\u0004\u0003kQ\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002:\u0005m\"AB*ue&twMC\u0002\u00026)\"B!a\u0006\u0002@!9\u0011\u0011I\u0006A\u0002\u0005\r\u0013\u0001\u00024jY\u0016\u0004B!!\u0012\u0002L5\u0011\u0011q\t\u0006\u0004\u0003\u0013Z\u0015AA5p\u0013\u0011\ti%a\u0012\u0003\t\u0019KG.\u001a\u000b\u0005\u0003/\t\t\u0006C\u0004\u0002T1\u0001\r!!\u0016\u0002\u0007U\u0014H\u000e\u0005\u0003\u0002X\u0005uSBAA-\u0015\r\tYfS\u0001\u0004]\u0016$\u0018\u0002BA0\u00033\u00121!\u0016*M)\u0011\t9\"a\u0019\t\u000f\u0005\u0015T\u00021\u0001\u0002(\u0005)1/_:JIR!\u0011qCA5\u0011\u001d\tYG\u0004a\u0001\u0003[\naBZ5mK\u0012+7o\u0019:jaR|'\u000f\u0005\u0003\u0002F\u0005=\u0014\u0002BA9\u0003\u000f\u0012aBR5mK\u0012+7o\u0019:jaR|'\u000f\u0006\u0003\u0002\u0018\u0005U\u0004bBA<\u001f\u0001\u0007\u0011\u0011P\u0001\fS:\u0004X\u000f^*ue\u0016\fW\u000e\u0005\u0003\u0002F\u0005m\u0014\u0002BA?\u0003\u000f\u00121\"\u00138qkR\u001cFO]3b[R!\u0011qCAA\u0011\u0019I\u0006\u00031\u0001\u0002\u0004B!\u0011QIAC\u0013\u0011\t9)a\u0012\u0003\rI+\u0017\rZ3s\u0003Iaw.\u00193TiJLgn\u001a#pGVlWM\u001c;\u0015\t\u0005]\u0011Q\u0012\u0005\b\u0003\u001f\u000b\u0002\u0019AA\u0014\u0003\u0019\u0019HO]5oO\u0006Qq-\u001a;E_\u000e,E.Z7\u0015\u0007\u0019\f)\nC\u0004\u0002\u0018J\u0001\r!a\u0006\u0002\u0011\u0011|7-^7f]R\fA\u0001\\8bIR\u0019a-!(\t\u000bI\u001c\u0002\u0019A:\u0002\u00111|\u0017\r\u001a$jY\u0016$2AZAR\u0011\u001d\t)\u0003\u0006a\u0001\u0003O!2AZAT\u0011\u001d\t\t%\u0006a\u0001\u0003\u0007\"2AZAV\u0011\u001d\t\u0019F\u0006a\u0001\u0003+\"2AZAX\u0011\u001d\t)g\u0006a\u0001\u0003O!2AZAZ\u0011\u001d\tY\u0007\u0007a\u0001\u0003[\"2AZA\\\u0011\u001d\t9(\u0007a\u0001\u0003s\"2AZA^\u0011\u0019I&\u00041\u0001\u0002\u0004\u0006QAn\\1e'R\u0014\u0018N\\4\u0015\u0007\u0019\f\t\rC\u0004\u0002\u0010n\u0001\r!a\n\u0002\u00131|\u0017\r\u001a(pI\u0016\u001cHcA=\u0002H\")!\u000f\ba\u0001g\u0006iAn\\1e\r&dWMT8eKN$2!_Ag\u0011\u001d\t)#\ba\u0001\u0003O!2!_Ai\u0011\u001d\t\tE\ba\u0001\u0003\u0007\"2!_Ak\u0011\u001d\t\u0019f\ba\u0001\u0003+\"2!_Am\u0011\u001d\t)\u0007\ta\u0001\u0003O!2!_Ao\u0011\u001d\tY'\ta\u0001\u0003[\"2!_Aq\u0011\u001d\t9H\ta\u0001\u0003s\"2!_As\u0011\u0019I6\u00051\u0001\u0002\u0004\u0006yAn\\1e'R\u0014\u0018N\\4O_\u0012,7\u000fF\u0002z\u0003WDq!a$%\u0001\u0004\t9\u0003"
)
public interface XMLLoader {
   // $FF: synthetic method
   static void scala$xml$factory$XMLLoader$$setSafeDefaults$(final XMLLoader $this, final SAXParserFactory parserFactory) {
      $this.scala$xml$factory$XMLLoader$$setSafeDefaults(parserFactory);
   }

   default void scala$xml$factory$XMLLoader$$setSafeDefaults(final SAXParserFactory parserFactory) {
      parserFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
      parserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      parserFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      parserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      parserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      parserFactory.setFeature("http://xml.org/sax/features/resolve-dtd-uris", false);
      parserFactory.setXIncludeAware(false);
      parserFactory.setNamespaceAware(false);
   }

   // $FF: synthetic method
   static ThreadLocal scala$xml$factory$XMLLoader$$parserInstance$(final XMLLoader $this) {
      return $this.scala$xml$factory$XMLLoader$$parserInstance();
   }

   default ThreadLocal scala$xml$factory$XMLLoader$$parserInstance() {
      return new ThreadLocal() {
         // $FF: synthetic field
         private final XMLLoader $outer;

         public SAXParser initialValue() {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            this.$outer.scala$xml$factory$XMLLoader$$setSafeDefaults(parserFactory);
            return parserFactory.newSAXParser();
         }

         public {
            if (XMLLoader.this == null) {
               throw null;
            } else {
               this.$outer = XMLLoader.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static SAXParser parser$(final XMLLoader $this) {
      return $this.parser();
   }

   default SAXParser parser() {
      return (SAXParser)this.scala$xml$factory$XMLLoader$$parserInstance().get();
   }

   // $FF: synthetic method
   static XMLReader reader$(final XMLLoader $this) {
      return $this.reader();
   }

   default XMLReader reader() {
      return this.parser().getXMLReader();
   }

   // $FF: synthetic method
   static Node loadXML$(final XMLLoader $this, final InputSource inputSource, final SAXParser parser) {
      return $this.loadXML(inputSource, parser);
   }

   default Node loadXML(final InputSource inputSource, final SAXParser parser) {
      return this.getDocElem(this.adapter().loadDocument(inputSource, parser.getXMLReader()));
   }

   // $FF: synthetic method
   static Seq loadXMLNodes$(final XMLLoader $this, final InputSource inputSource, final SAXParser parser) {
      return $this.loadXMLNodes(inputSource, parser);
   }

   default Seq loadXMLNodes(final InputSource inputSource, final SAXParser parser) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.adapter().loadDocument(inputSource, parser.getXMLReader()).children());
   }

   // $FF: synthetic method
   static FactoryAdapter adapter$(final XMLLoader $this) {
      return $this.adapter();
   }

   default FactoryAdapter adapter() {
      return new NoBindingFactoryAdapter();
   }

   // $FF: synthetic method
   static Document loadDocument$(final XMLLoader $this, final InputSource inputSource) {
      return $this.loadDocument(inputSource);
   }

   default Document loadDocument(final InputSource inputSource) {
      return this.adapter().loadDocument(inputSource, this.reader());
   }

   // $FF: synthetic method
   static Document loadFileDocument$(final XMLLoader $this, final String fileName) {
      return $this.loadFileDocument(fileName);
   }

   default Document loadFileDocument(final String fileName) {
      return this.loadDocument(Source$.MODULE$.fromFile(fileName));
   }

   // $FF: synthetic method
   static Document loadFileDocument$(final XMLLoader $this, final File file) {
      return $this.loadFileDocument(file);
   }

   default Document loadFileDocument(final File file) {
      return this.loadDocument(Source$.MODULE$.fromFile(file));
   }

   // $FF: synthetic method
   static Document loadDocument$(final XMLLoader $this, final URL url) {
      return $this.loadDocument(url);
   }

   default Document loadDocument(final URL url) {
      return this.loadDocument(Source$.MODULE$.fromUrl(url));
   }

   // $FF: synthetic method
   static Document loadDocument$(final XMLLoader $this, final String sysId) {
      return $this.loadDocument(sysId);
   }

   default Document loadDocument(final String sysId) {
      return this.loadDocument(Source$.MODULE$.fromSysId(sysId));
   }

   // $FF: synthetic method
   static Document loadFileDocument$(final XMLLoader $this, final FileDescriptor fileDescriptor) {
      return $this.loadFileDocument(fileDescriptor);
   }

   default Document loadFileDocument(final FileDescriptor fileDescriptor) {
      return this.loadDocument(Source$.MODULE$.fromFile(fileDescriptor));
   }

   // $FF: synthetic method
   static Document loadDocument$(final XMLLoader $this, final InputStream inputStream) {
      return $this.loadDocument(inputStream);
   }

   default Document loadDocument(final InputStream inputStream) {
      return this.loadDocument(Source$.MODULE$.fromInputStream(inputStream));
   }

   // $FF: synthetic method
   static Document loadDocument$(final XMLLoader $this, final Reader reader) {
      return $this.loadDocument(reader);
   }

   default Document loadDocument(final Reader reader) {
      return this.loadDocument(Source$.MODULE$.fromReader(reader));
   }

   // $FF: synthetic method
   static Document loadStringDocument$(final XMLLoader $this, final String string) {
      return $this.loadStringDocument(string);
   }

   default Document loadStringDocument(final String string) {
      return this.loadDocument(Source$.MODULE$.fromString(string));
   }

   private Node getDocElem(final Document document) {
      return document.docElem();
   }

   // $FF: synthetic method
   static Node load$(final XMLLoader $this, final InputSource inputSource) {
      return $this.load(inputSource);
   }

   default Node load(final InputSource inputSource) {
      return this.getDocElem(this.loadDocument(inputSource));
   }

   // $FF: synthetic method
   static Node loadFile$(final XMLLoader $this, final String fileName) {
      return $this.loadFile(fileName);
   }

   default Node loadFile(final String fileName) {
      return this.getDocElem(this.loadFileDocument(fileName));
   }

   // $FF: synthetic method
   static Node loadFile$(final XMLLoader $this, final File file) {
      return $this.loadFile(file);
   }

   default Node loadFile(final File file) {
      return this.getDocElem(this.loadFileDocument(file));
   }

   // $FF: synthetic method
   static Node load$(final XMLLoader $this, final URL url) {
      return $this.load(url);
   }

   default Node load(final URL url) {
      return this.getDocElem(this.loadDocument(url));
   }

   // $FF: synthetic method
   static Node load$(final XMLLoader $this, final String sysId) {
      return $this.load(sysId);
   }

   default Node load(final String sysId) {
      return this.getDocElem(this.loadDocument(sysId));
   }

   // $FF: synthetic method
   static Node loadFile$(final XMLLoader $this, final FileDescriptor fileDescriptor) {
      return $this.loadFile(fileDescriptor);
   }

   default Node loadFile(final FileDescriptor fileDescriptor) {
      return this.getDocElem(this.loadFileDocument(fileDescriptor));
   }

   // $FF: synthetic method
   static Node load$(final XMLLoader $this, final InputStream inputStream) {
      return $this.load(inputStream);
   }

   default Node load(final InputStream inputStream) {
      return this.getDocElem(this.loadDocument(inputStream));
   }

   // $FF: synthetic method
   static Node load$(final XMLLoader $this, final Reader reader) {
      return $this.load(reader);
   }

   default Node load(final Reader reader) {
      return this.getDocElem(this.loadDocument(reader));
   }

   // $FF: synthetic method
   static Node loadString$(final XMLLoader $this, final String string) {
      return $this.loadString(string);
   }

   default Node loadString(final String string) {
      return this.getDocElem(this.loadStringDocument(string));
   }

   // $FF: synthetic method
   static Seq loadNodes$(final XMLLoader $this, final InputSource inputSource) {
      return $this.loadNodes(inputSource);
   }

   default Seq loadNodes(final InputSource inputSource) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadDocument(inputSource).children());
   }

   // $FF: synthetic method
   static Seq loadFileNodes$(final XMLLoader $this, final String fileName) {
      return $this.loadFileNodes(fileName);
   }

   default Seq loadFileNodes(final String fileName) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadFileDocument(fileName).children());
   }

   // $FF: synthetic method
   static Seq loadFileNodes$(final XMLLoader $this, final File file) {
      return $this.loadFileNodes(file);
   }

   default Seq loadFileNodes(final File file) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadFileDocument(file).children());
   }

   // $FF: synthetic method
   static Seq loadNodes$(final XMLLoader $this, final URL url) {
      return $this.loadNodes(url);
   }

   default Seq loadNodes(final URL url) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadDocument(url).children());
   }

   // $FF: synthetic method
   static Seq loadNodes$(final XMLLoader $this, final String sysId) {
      return $this.loadNodes(sysId);
   }

   default Seq loadNodes(final String sysId) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadDocument(sysId).children());
   }

   // $FF: synthetic method
   static Seq loadFileNodes$(final XMLLoader $this, final FileDescriptor fileDescriptor) {
      return $this.loadFileNodes(fileDescriptor);
   }

   default Seq loadFileNodes(final FileDescriptor fileDescriptor) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadFileDocument(fileDescriptor).children());
   }

   // $FF: synthetic method
   static Seq loadNodes$(final XMLLoader $this, final InputStream inputStream) {
      return $this.loadNodes(inputStream);
   }

   default Seq loadNodes(final InputStream inputStream) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadDocument(inputStream).children());
   }

   // $FF: synthetic method
   static Seq loadNodes$(final XMLLoader $this, final Reader reader) {
      return $this.loadNodes(reader);
   }

   default Seq loadNodes(final Reader reader) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadDocument(reader).children());
   }

   // $FF: synthetic method
   static Seq loadStringNodes$(final XMLLoader $this, final String string) {
      return $this.loadStringNodes(string);
   }

   default Seq loadStringNodes(final String string) {
      return NodeSeq$.MODULE$.seqToNodeSeq(this.loadStringDocument(string).children());
   }

   static void $init$(final XMLLoader $this) {
   }
}
