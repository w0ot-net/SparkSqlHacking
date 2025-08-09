package scala.xml;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import scala.Enumeration;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.xml.dtd.DocType;
import scala.xml.factory.XMLLoader;
import scala.xml.parsing.FactoryAdapter;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=s!B\r\u001b\u0011\u0003yb!B\u0011\u001b\u0011\u0003\u0011\u0003\"\u0002\u0019\u0002\t\u0003\t\u0004bB\u000e\u0002\u0005\u0004%\tA\r\u0005\u0007}\u0005\u0001\u000b\u0011B\u001a\t\u000f}\n!\u0019!C\u0001e!1\u0001)\u0001Q\u0001\nMBq!Q\u0001C\u0002\u0013\u0005!\u0007\u0003\u0004C\u0003\u0001\u0006Ia\r\u0005\b\u0007\u0006\u0011\r\u0011\"\u00013\u0011\u0019!\u0015\u0001)A\u0005g!9Q)\u0001b\u0001\n\u0003\u0011\u0004B\u0002$\u0002A\u0003%1\u0007C\u0004H\u0003\t\u0007I\u0011\u0001\u001a\t\r!\u000b\u0001\u0015!\u00034\u0011\u001dI\u0015A1A\u0005\u0002IBaAS\u0001!\u0002\u0013\u0019\u0004\"B&\u0002\t\u0003a\u0005\"\u0002,\u0002\t\u00039\u0006\"B/\u0002\t\u000bq\u0006b\u0002=\u0002#\u0003%)!\u001f\u0005\n\u0003\u0013\t\u0011\u0013!C\u0003\u0003\u0017A\u0011\"a\u0004\u0002#\u0003%)!!\u0005\t\u000f\u0005U\u0011\u0001\"\u0002\u0002\u0018!I\u0011\u0011J\u0001\u0012\u0002\u0013\u0015\u00111J\u0001\u000416c%BA\u000e\u001d\u0003\rAX\u000e\u001c\u0006\u0002;\u0005)1oY1mC\u000e\u0001\u0001C\u0001\u0011\u0002\u001b\u0005Q\"a\u0001-N\u0019N\u0019\u0011aI\u0014\u0011\u0005\u0011*S\"\u0001\u000f\n\u0005\u0019b\"AB!osJ+g\rE\u0002)W5j\u0011!\u000b\u0006\u0003Ui\tqAZ1di>\u0014\u00180\u0003\u0002-S\tI\u0001,\u0014'M_\u0006$WM\u001d\t\u0003A9J!a\f\u000e\u0003\t\u0015cW-\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003})\u0012a\r\t\u0003imr!!N\u001d\u0011\u0005YbR\"A\u001c\u000b\u0005ar\u0012A\u0002\u001fs_>$h(\u0003\u0002;9\u00051\u0001K]3eK\u001aL!\u0001P\u001f\u0003\rM#(/\u001b8h\u0015\tQD$\u0001\u0003y[2\u0004\u0013!\u0002=nY:\u001c\u0018A\u0002=nY:\u001c\b%A\u0005oC6,7\u000f]1dK\u0006Qa.Y7fgB\f7-\u001a\u0011\u0002\u0011A\u0014Xm]3sm\u0016\f\u0011\u0002\u001d:fg\u0016\u0014h/\u001a\u0011\u0002\u000bM\u0004\u0018mY3\u0002\rM\u0004\u0018mY3!\u0003\u0011a\u0017M\\4\u0002\u000b1\fgn\u001a\u0011\u0002\u0011\u0015t7m\u001c3j]\u001e\f\u0011\"\u001a8d_\u0012Lgn\u001a\u0011\u0002\u001b]LG\u000f[*B1B\u000b'o]3s)\t9S\nC\u0003O#\u0001\u0007q*A\u0001q!\t\u00016K\u0004\u0002!#&\u0011!KG\u0001\ba\u0006\u001c7.Y4f\u0013\t!VKA\u0005T\u0003b\u0003\u0016M]:fe*\u0011!KG\u0001\u000eo&$\b\u000eW'M%\u0016\fG-\u001a:\u0015\u0005\u001dB\u0006\"B-\u0013\u0001\u0004Q\u0016!\u0001:\u0011\u0005A[\u0016B\u0001/V\u0005%AV\n\u0014*fC\u0012,'/\u0001\u0003tCZ,GCB0cI&\\\u0007\u000f\u0005\u0002%A&\u0011\u0011\r\b\u0002\u0005+:LG\u000fC\u0003d'\u0001\u00071'\u0001\u0005gS2,g.Y7f\u0011\u0015)7\u00031\u0001g\u0003\u0011qw\u000eZ3\u0011\u0005\u0001:\u0017B\u00015\u001b\u0005\u0011qu\u000eZ3\t\u000f)\u001c\u0002\u0013!a\u0001g\u0005\u0019QM\\2\t\u000f1\u001c\u0002\u0013!a\u0001[\u00069\u00010\u001c7EK\u000ed\u0007C\u0001\u0013o\u0013\tyGDA\u0004C_>dW-\u00198\t\u000fE\u001c\u0002\u0013!a\u0001e\u00069Am\\2usB,\u0007CA:w\u001b\u0005!(BA;\u001b\u0003\r!G\u000fZ\u0005\u0003oR\u0014q\u0001R8d)f\u0004X-\u0001\btCZ,G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003iT#aM>,\u0003q\u00042!`A\u0003\u001b\u0005q(bA@\u0002\u0002\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u0007a\u0012AC1o]>$\u0018\r^5p]&\u0019\u0011q\u0001@\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\btCZ,G\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u00055!FA7|\u00039\u0019\u0018M^3%I\u00164\u0017-\u001e7uIU*\"!a\u0005+\u0005I\\\u0018!B<sSR,G#D0\u0002\u001a\u00055\u0012qFA\u0019\u0003g\t)\u0004C\u0004\u0002\u001c]\u0001\r!!\b\u0002\u0003]\u0004B!a\b\u0002*5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#\u0001\u0002j_*\u0011\u0011qE\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002,\u0005\u0005\"AB,sSR,'\u000fC\u0003f/\u0001\u0007a\rC\u0003k/\u0001\u00071\u0007C\u0003m/\u0001\u0007Q\u000eC\u0003r/\u0001\u0007!\u000fC\u0005\u00028]\u0001\n\u00111\u0001\u0002:\u0005aQ.\u001b8j[&TX\rV1hgB!\u00111HA!\u001d\r\u0001\u0013QH\u0005\u0004\u0003\u007fQ\u0012\u0001D'j]&l\u0017N_3N_\u0012,\u0017\u0002BA\"\u0003\u000b\u0012QAV1mk\u0016L1!a\u0012\u001d\u0005-)e.^7fe\u0006$\u0018n\u001c8\u0002\u001f]\u0014\u0018\u000e^3%I\u00164\u0017-\u001e7uIY*\"!!\u0014+\u0007\u0005e2\u0010"
)
public final class XML {
   public static Enumeration.Value write$default$6() {
      return XML$.MODULE$.write$default$6();
   }

   public static void write(final Writer w, final Node node, final String enc, final boolean xmlDecl, final DocType doctype, final Enumeration.Value minimizeTags) {
      XML$.MODULE$.write(w, node, enc, xmlDecl, doctype, minimizeTags);
   }

   public static DocType save$default$5() {
      return XML$.MODULE$.save$default$5();
   }

   public static boolean save$default$4() {
      return XML$.MODULE$.save$default$4();
   }

   public static String save$default$3() {
      return XML$.MODULE$.save$default$3();
   }

   public static void save(final String filename, final Node node, final String enc, final boolean xmlDecl, final DocType doctype) {
      XML$.MODULE$.save(filename, node, enc, xmlDecl, doctype);
   }

   public static XMLLoader withXMLReader(final XMLReader r) {
      return XML$.MODULE$.withXMLReader(r);
   }

   public static XMLLoader withSAXParser(final SAXParser p) {
      return XML$.MODULE$.withSAXParser(p);
   }

   public static String encoding() {
      return XML$.MODULE$.encoding();
   }

   public static String lang() {
      return XML$.MODULE$.lang();
   }

   public static String space() {
      return XML$.MODULE$.space();
   }

   public static String preserve() {
      return XML$.MODULE$.preserve();
   }

   public static String namespace() {
      return XML$.MODULE$.namespace();
   }

   public static String xmlns() {
      return XML$.MODULE$.xmlns();
   }

   public static String xml() {
      return XML$.MODULE$.xml();
   }

   public static Seq loadStringNodes(final String string) {
      return XML$.MODULE$.loadStringNodes(string);
   }

   public static Seq loadNodes(final Reader reader) {
      return XML$.MODULE$.loadNodes(reader);
   }

   public static Seq loadNodes(final InputStream inputStream) {
      return XML$.MODULE$.loadNodes(inputStream);
   }

   public static Seq loadFileNodes(final FileDescriptor fileDescriptor) {
      return XML$.MODULE$.loadFileNodes(fileDescriptor);
   }

   public static Seq loadNodes(final String sysId) {
      return XML$.MODULE$.loadNodes(sysId);
   }

   public static Seq loadNodes(final URL url) {
      return XML$.MODULE$.loadNodes(url);
   }

   public static Seq loadFileNodes(final File file) {
      return XML$.MODULE$.loadFileNodes(file);
   }

   public static Seq loadFileNodes(final String fileName) {
      return XML$.MODULE$.loadFileNodes(fileName);
   }

   public static Seq loadNodes(final InputSource inputSource) {
      return XML$.MODULE$.loadNodes(inputSource);
   }

   public static Node loadString(final String string) {
      return XML$.MODULE$.loadString(string);
   }

   public static Node load(final Reader reader) {
      return XML$.MODULE$.load(reader);
   }

   public static Node load(final InputStream inputStream) {
      return XML$.MODULE$.load(inputStream);
   }

   public static Node loadFile(final FileDescriptor fileDescriptor) {
      return XML$.MODULE$.loadFile(fileDescriptor);
   }

   public static Node load(final String sysId) {
      return XML$.MODULE$.load(sysId);
   }

   public static Node load(final URL url) {
      return XML$.MODULE$.load(url);
   }

   public static Node loadFile(final File file) {
      return XML$.MODULE$.loadFile(file);
   }

   public static Node loadFile(final String fileName) {
      return XML$.MODULE$.loadFile(fileName);
   }

   public static Node load(final InputSource inputSource) {
      return XML$.MODULE$.load(inputSource);
   }

   public static Document loadStringDocument(final String string) {
      return XML$.MODULE$.loadStringDocument(string);
   }

   public static Document loadDocument(final Reader reader) {
      return XML$.MODULE$.loadDocument(reader);
   }

   public static Document loadDocument(final InputStream inputStream) {
      return XML$.MODULE$.loadDocument(inputStream);
   }

   public static Document loadFileDocument(final FileDescriptor fileDescriptor) {
      return XML$.MODULE$.loadFileDocument(fileDescriptor);
   }

   public static Document loadDocument(final String sysId) {
      return XML$.MODULE$.loadDocument(sysId);
   }

   public static Document loadDocument(final URL url) {
      return XML$.MODULE$.loadDocument(url);
   }

   public static Document loadFileDocument(final File file) {
      return XML$.MODULE$.loadFileDocument(file);
   }

   public static Document loadFileDocument(final String fileName) {
      return XML$.MODULE$.loadFileDocument(fileName);
   }

   public static Document loadDocument(final InputSource inputSource) {
      return XML$.MODULE$.loadDocument(inputSource);
   }

   public static FactoryAdapter adapter() {
      return XML$.MODULE$.adapter();
   }

   public static Seq loadXMLNodes(final InputSource inputSource, final SAXParser parser) {
      return XML$.MODULE$.loadXMLNodes(inputSource, parser);
   }

   public static Node loadXML(final InputSource inputSource, final SAXParser parser) {
      return XML$.MODULE$.loadXML(inputSource, parser);
   }

   public static XMLReader reader() {
      return XML$.MODULE$.reader();
   }

   public static SAXParser parser() {
      return XML$.MODULE$.parser();
   }
}
