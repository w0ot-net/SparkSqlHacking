package scala.xml.include.sax;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import scala.collection.immutable.List;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eg\u0001B\u0012%\u00015B\u0001\u0002\u0012\u0001\u0003\u0002\u0003\u0006I!\u0012\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005\u0019\")q\u000b\u0001C\u00011\"9Q\f\u0001a\u0001\n\u0003q\u0006b\u00022\u0001\u0001\u0004%\ta\u0019\u0005\u0007U\u0002\u0001\u000b\u0015B0\t\u000b-\u0004A\u0011\t7\t\u000bI\u0004A\u0011I:\t\u000bQ\u0004A\u0011I:\t\u000bU\u0004A\u0011\t<\t\u000bm\u0004A\u0011\t?\t\u000by\u0004A\u0011I@\t\u000f\u0005]\u0001\u0001\"\u0011\u0002\u001a!9\u0011\u0011\u0005\u0001\u0005B\u0005\r\u0002bBA\"\u0001\u0011\u0005\u0013Q\t\u0005\b\u0003\u001b\u0002A\u0011IA(\u0011\u001d\tI\u0006\u0001C!\u00037B\u0011\"!\u0019\u0001\u0001\u0004%I!a\u0019\t\u0013\u0005-\u0004\u00011A\u0005\n\u00055\u0004\u0002CA9\u0001\u0001\u0006K!!\u001a\t\u0013\u0005M\u0004\u00011A\u0005\n\u0005U\u0004\"CAD\u0001\u0001\u0007I\u0011BAE\u0011!\ti\t\u0001Q!\n\u0005]\u0004bBAH\u0001\u0011\u0005\u0013\u0011\u0013\u0005\u0007\u0003;\u0003A\u0011I:\t\u000f\u0005}\u0005\u0001\"\u0011\u0002\"\"9\u0011Q\u0015\u0001\u0005B\u0005\u001d\u0006BBAV\u0001\u0011\u00053\u000f\u0003\u0004\u0002.\u0002!\te\u001d\u0005\f\u0003_\u0003\u0001\u0019!a\u0001\n\u0013\t\t\fC\u0006\u0002:\u0002\u0001\r\u00111A\u0005\n\u0005m\u0006bCA`\u0001\u0001\u0007\t\u0011)Q\u0005\u0003gCq!!1\u0001\t\u0003\t\u0019\rC\u0004\u0002H\u0002!\t%!3\u0003\u0013aKen\u00197vI\u0016\u0014(BA\u0013'\u0003\r\u0019\u0018\r\u001f\u0006\u0003O!\nq!\u001b8dYV$WM\u0003\u0002*U\u0005\u0019\u00010\u001c7\u000b\u0003-\nQa]2bY\u0006\u001c\u0001a\u0005\u0003\u0001]Yr\u0004CA\u00185\u001b\u0005\u0001$BA\u00193\u0003\u0011a\u0017M\\4\u000b\u0003M\nAA[1wC&\u0011Q\u0007\r\u0002\u0007\u001f\nTWm\u0019;\u0011\u0005]bT\"\u0001\u001d\u000b\u0005\u0015J$BA\u0015;\u0015\u0005Y\u0014aA8sO&\u0011Q\b\u000f\u0002\u000f\u0007>tG/\u001a8u\u0011\u0006tG\r\\3s!\ty$)D\u0001A\u0015\t\t\u0005(A\u0002fqRL!a\u0011!\u0003\u001d1+\u00070[2bY\"\u000bg\u000e\u001a7fe\u0006!q.\u001e;t!\t1\u0015*D\u0001H\u0015\tA%'\u0001\u0002j_&\u0011!j\u0012\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\tK:\u001cw\u000eZ5oOB\u0011Q\n\u0016\b\u0003\u001dJ\u0003\"a\u0014\u0016\u000e\u0003AS!!\u0015\u0017\u0002\rq\u0012xn\u001c;?\u0013\t\u0019&&\u0001\u0004Qe\u0016$WMZ\u0005\u0003+Z\u0013aa\u0015;sS:<'BA*+\u0003\u0019a\u0014N\\5u}Q\u0019\u0011l\u0017/\u0011\u0005i\u0003Q\"\u0001\u0013\t\u000b\u0011\u001b\u0001\u0019A#\t\u000b-\u001b\u0001\u0019\u0001'\u0002\u0007=,H/F\u0001`!\t1\u0005-\u0003\u0002b\u000f\n\u0011r*\u001e;qkR\u001cFO]3b[^\u0013\u0018\u000e^3s\u0003\u001dyW\u000f^0%KF$\"\u0001\u001a5\u0011\u0005\u00154W\"\u0001\u0016\n\u0005\u001dT#\u0001B+oSRDq![\u0003\u0002\u0002\u0003\u0007q,A\u0002yIE\nAa\\;uA\u0005\u00112/\u001a;E_\u000e,X.\u001a8u\u0019>\u001c\u0017\r^8s)\t!W\u000eC\u0003o\u000f\u0001\u0007q.A\u0004m_\u000e\fGo\u001c:\u0011\u0005]\u0002\u0018BA99\u0005\u001daunY1u_J\fQb\u001d;beR$unY;nK:$H#\u00013\u0002\u0017\u0015tG\rR8dk6,g\u000e^\u0001\u0013gR\f'\u000f\u001e)sK\u001aL\u00070T1qa&tw\rF\u0002eofDQ\u0001\u001f\u0006A\u00021\u000ba\u0001\u001d:fM&D\b\"\u0002>\u000b\u0001\u0004a\u0015aA;sS\u0006\u0001RM\u001c3Qe\u00164\u0017\u000e_'baBLgn\u001a\u000b\u0003IvDQ\u0001_\u0006A\u00021\u000bAb\u001d;beR,E.Z7f]R$\u0012\u0002ZA\u0001\u0003\u000b\tI!!\u0004\t\r\u0005\rA\u00021\u0001M\u00031q\u0017-\\3ta\u0006\u001cW-\u0016*J\u0011\u0019\t9\u0001\u0004a\u0001\u0019\u0006IAn\\2bY:\u000bW.\u001a\u0005\u0007\u0003\u0017a\u0001\u0019\u0001'\u0002\u001bE,\u0018\r\\5gS\u0016$g*Y7f\u0011\u001d\ty\u0001\u0004a\u0001\u0003#\tA!\u0019;ugB\u0019q'a\u0005\n\u0007\u0005U\u0001H\u0001\u0006BiR\u0014\u0018NY;uKN\f!\"\u001a8e\u000b2,W.\u001a8u)\u001d!\u00171DA\u000f\u0003?Aa!a\u0001\u000e\u0001\u0004a\u0005BBA\u0004\u001b\u0001\u0007A\n\u0003\u0004\u0002\f5\u0001\r\u0001T\u0001\u000bG\"\f'/Y2uKJ\u001cHc\u00023\u0002&\u0005U\u0012q\b\u0005\b\u0003Oq\u0001\u0019AA\u0015\u0003\t\u0019\u0007\u000eE\u0003f\u0003W\ty#C\u0002\u0002.)\u0012Q!\u0011:sCf\u00042!ZA\u0019\u0013\r\t\u0019D\u000b\u0002\u0005\u0007\"\f'\u000fC\u0004\u000289\u0001\r!!\u000f\u0002\u000bM$\u0018M\u001d;\u0011\u0007\u0015\fY$C\u0002\u0002>)\u00121!\u00138u\u0011\u001d\t\tE\u0004a\u0001\u0003s\ta\u0001\\3oORD\u0017aE5h]>\u0014\u0018M\u00197f/\"LG/Z:qC\u000e,Gc\u00023\u0002H\u0005%\u00131\n\u0005\b\u0003Oy\u0001\u0019AA\u0015\u0011\u001d\t9d\u0004a\u0001\u0003sAq!!\u0011\u0010\u0001\u0004\tI$A\u000bqe>\u001cWm]:j]\u001eLen\u001d;sk\u000e$\u0018n\u001c8\u0015\u000b\u0011\f\t&!\u0016\t\r\u0005M\u0003\u00031\u0001M\u0003\u0019!\u0018M]4fi\"1\u0011q\u000b\tA\u00021\u000bA\u0001Z1uC\u0006i1o[5qa\u0016$WI\u001c;jif$2\u0001ZA/\u0011\u0019\ty&\u0005a\u0001\u0019\u0006!a.Y7f\u0003\u0015Ig\u000e\u0012+E+\t\t)\u0007E\u0002f\u0003OJ1!!\u001b+\u0005\u001d\u0011un\u001c7fC:\f\u0011\"\u001b8E)\u0012{F%Z9\u0015\u0007\u0011\fy\u0007\u0003\u0005j'\u0005\u0005\t\u0019AA3\u0003\u0019Ig\u000e\u0012+EA\u0005AQM\u001c;ji&,7/\u0006\u0002\u0002xA)\u0011\u0011PAB\u00196\u0011\u00111\u0010\u0006\u0005\u0003{\ny(A\u0005j[6,H/\u00192mK*\u0019\u0011\u0011\u0011\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0006\u0006m$\u0001\u0002'jgR\fA\"\u001a8uSRLWm]0%KF$2\u0001ZAF\u0011!Ig#!AA\u0002\u0005]\u0014!C3oi&$\u0018.Z:!\u0003!\u0019H/\u0019:u\tR#Ec\u00023\u0002\u0014\u0006U\u0015\u0011\u0014\u0005\u0007\u0003?B\u0002\u0019\u0001'\t\r\u0005]\u0005\u00041\u0001M\u0003!\u0001XO\u00197jG&#\u0005BBAN1\u0001\u0007A*\u0001\u0005tsN$X-\\%E\u0003\u0019)g\u000e\u001a#U\t\u0006Y1\u000f^1si\u0016sG/\u001b;z)\r!\u00171\u0015\u0005\u0007\u0003?R\u0002\u0019\u0001'\u0002\u0013\u0015tG-\u00128uSRLHc\u00013\u0002*\"1\u0011qL\u000eA\u00021\u000b!b\u001d;beR\u001cE)\u0011+B\u0003!)g\u000eZ\"E\u0003R\u000b\u0015A\u00024jYR,'/\u0006\u0002\u00024B\u0019!,!.\n\u0007\u0005]FE\u0001\bY\u0013:\u001cG.\u001e3f\r&dG/\u001a:\u0002\u0015\u0019LG\u000e^3s?\u0012*\u0017\u000fF\u0002e\u0003{C\u0001\"[\u0010\u0002\u0002\u0003\u0007\u00111W\u0001\bM&dG/\u001a:!\u0003%\u0019X\r\u001e$jYR,'\u000fF\u0002e\u0003\u000bDq!a,\"\u0001\u0004\t\u0019,A\u0004d_6lWM\u001c;\u0015\u000f\u0011\fY-!4\u0002P\"9\u0011q\u0005\u0012A\u0002\u0005%\u0002bBA\u001cE\u0001\u0007\u0011\u0011\b\u0005\b\u0003\u0003\u0012\u0003\u0019AA\u001d\u0001"
)
public class XIncluder implements ContentHandler, LexicalHandler {
   private final String encoding;
   private OutputStreamWriter out;
   private boolean inDTD;
   private List entities;
   private XIncludeFilter filter;

   public OutputStreamWriter out() {
      return this.out;
   }

   public void out_$eq(final OutputStreamWriter x$1) {
      this.out = x$1;
   }

   public void setDocumentLocator(final Locator locator) {
   }

   public void startDocument() {
      try {
         this.out().write((new StringBuilder(35)).append("<?xml version='1.0' encoding='").append(this.encoding).append("'?>\r\n").toString());
      } catch (IOException var2) {
         throw new SAXException("Write failed", var2);
      }
   }

   public void endDocument() {
      try {
         this.out().flush();
      } catch (IOException var2) {
         throw new SAXException("Flush failed", var2);
      }
   }

   public void startPrefixMapping(final String prefix, final String uri) {
   }

   public void endPrefixMapping(final String prefix) {
   }

   public void startElement(final String namespaceURI, final String localName, final String qualifiedName, final Attributes atts) {
      try {
         this.out().write((new StringBuilder(1)).append("<").append(qualifiedName).toString());

         for(int i = 0; i < atts.getLength(); ++i) {
            String value = atts.getValue(i);
            String valueStr = Utility$.MODULE$.escape(value);
            this.out().write((new StringBuilder(4)).append(" ").append(atts.getQName(i)).append("='").append(valueStr).append("'").toString());
         }

         this.out().write(">");
      } catch (IOException var9) {
         throw new SAXException("Write failed", var9);
      }
   }

   public void endElement(final String namespaceURI, final String localName, final String qualifiedName) {
      try {
         this.out().write((new StringBuilder(3)).append("</").append(qualifiedName).append(">").toString());
      } catch (IOException var5) {
         throw new SAXException("Write failed", var5);
      }
   }

   public void characters(final char[] ch, final int start, final int length) {
      try {
         for(int i = 0; i < length; ++i) {
            char c = ch[start + i];
            if (c == '&') {
               this.out().write("&amp;");
            } else if (c == '<') {
               this.out().write("&lt;");
            } else if (c == '>') {
               this.out().write("&gt;");
            } else {
               this.out().write(c);
            }
         }

      } catch (IOException var7) {
         throw new SAXException("Write failed", var7);
      }
   }

   public void ignorableWhitespace(final char[] ch, final int start, final int length) {
      this.characters(ch, start, length);
   }

   public void processingInstruction(final String target, final String data) {
      try {
         this.out().write((new StringBuilder(5)).append("<?").append(target).append(" ").append(data).append("?>").toString());
      } catch (IOException var4) {
         throw new SAXException("Write failed", var4);
      }
   }

   public void skippedEntity(final String name) {
      try {
         this.out().write((new StringBuilder(2)).append("&").append(name).append(";").toString());
      } catch (IOException var3) {
         throw new SAXException("Write failed", var3);
      }
   }

   private boolean inDTD() {
      return this.inDTD;
   }

   private void inDTD_$eq(final boolean x$1) {
      this.inDTD = x$1;
   }

   private List entities() {
      return this.entities;
   }

   private void entities_$eq(final List x$1) {
      this.entities = x$1;
   }

   public void startDTD(final String name, final String publicID, final String systemID) {
      this.inDTD_$eq(true);
      if (this.entities().isEmpty()) {
         String id = "";
         if (publicID != null) {
            id = (new StringBuilder(13)).append(" PUBLIC \"").append(publicID).append("\" \"").append(systemID).append("\"").toString();
         } else if (systemID != null) {
            id = (new StringBuilder(10)).append(" SYSTEM \"").append(systemID).append("\"").toString();
         }

         try {
            this.out().write((new StringBuilder(13)).append("<!DOCTYPE ").append(name).append(id).append(">\r\n").toString());
         } catch (IOException var6) {
            throw new SAXException("Error while writing DOCTYPE", var6);
         }
      }
   }

   public void endDTD() {
   }

   public void startEntity(final String name) {
      this.entities_$eq(this.entities().$colon$colon(name));
   }

   public void endEntity(final String name) {
      this.entities_$eq((List)this.entities().tail());
   }

   public void startCDATA() {
   }

   public void endCDATA() {
   }

   private XIncludeFilter filter() {
      return this.filter;
   }

   private void filter_$eq(final XIncludeFilter x$1) {
      this.filter = x$1;
   }

   public void setFilter(final XIncludeFilter filter) {
      this.filter_$eq(filter);
   }

   public void comment(final char[] ch, final int start, final int length) {
      if (!this.inDTD() && !this.filter().insideIncludeElement()) {
         try {
            this.out().write("<!--");
            this.out().write(ch, start, length);
            this.out().write("-->");
         } catch (IOException var5) {
            throw new SAXException("Write failed", var5);
         }
      }
   }

   public XIncluder(final OutputStream outs, final String encoding) {
      this.encoding = encoding;
      this.out = new OutputStreamWriter(outs, encoding);
      this.inDTD = false;
      this.entities = .MODULE$.List().empty();
   }
}
