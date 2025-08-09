package scala.xml.include.sax;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;
import scala.reflect.ScalaSignature;
import scala.xml.package$;
import scala.xml.include.CircularIncludeException;
import scala.xml.include.UnavailableResourceException;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e\u0001\u0002\u0011\"\u0001)BQ!\u000e\u0001\u0005\u0002YBq!\u000f\u0001C\u0002\u0013\u0015!\b\u0003\u0004G\u0001\u0001\u0006ia\u000f\u0005\b\u000f\u0002\u0011\r\u0011\"\u0003I\u0011\u00199\u0006\u0001)A\u0005\u0013\"9\u0001\f\u0001b\u0001\n\u0013I\u0006BB0\u0001A\u0003%!\fC\u0003a\u0001\u0011\u0005\u0013\rC\u0004i\u0001\u0001\u0007I\u0011B5\t\u000f5\u0004\u0001\u0019!C\u0005]\"1\u0011\u000f\u0001Q!\n)DQA\u001d\u0001\u0005\u0002MDQa\u001e\u0001\u0005BaDq!!\u0003\u0001\t\u0003\nY\u0001\u0003\u0005\u0002\u0014\u0001\u0001\r\u0011\"\u0003j\u0011%\t)\u0002\u0001a\u0001\n\u0013\t9\u0002C\u0004\u0002\u001c\u0001\u0001\u000b\u0015\u00026\t\u000f\u0005u\u0001\u0001\"\u0011\u0002 !9\u0011\u0011\u0005\u0001\u0005B\u0005}\u0001bBA\u0012\u0001\u0011\u0005\u0013Q\u0005\u0005\b\u0003[\u0001A\u0011IA\u0018\u0011\u001d\t\u0019\u0004\u0001C!\u0003kAq!a\u0014\u0001\t\u0003\n\t\u0006C\u0004\u0002Z\u0001!\t%a\u0017\t\u000f\u0005\u0015\u0004\u0001\"\u0011\u0002h!1\u0011Q\u000e\u0001\u0005\niBq!a\u001c\u0001\t\u0013\t\t\b\u0003\u0005\u0002|\u0001\u0001\r\u0011\"\u0003t\u0011%\ti\b\u0001a\u0001\n\u0013\ty\bC\u0004\u0002\u0004\u0002\u0001\u000b\u0015\u0002;\t\u000f\u0005\u0015\u0005\u0001\"\u0003\u0002\b\nq\u0001,\u00138dYV$WMR5mi\u0016\u0014(B\u0001\u0012$\u0003\r\u0019\u0018\r\u001f\u0006\u0003I\u0015\nq!\u001b8dYV$WM\u0003\u0002'O\u0005\u0019\u00010\u001c7\u000b\u0003!\nQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001WA\u0011AfM\u0007\u0002[)\u0011afL\u0001\bQ\u0016d\u0007/\u001a:t\u0015\t\u0011\u0003G\u0003\u0002'c)\t!'A\u0002pe\u001eL!\u0001N\u0017\u0003\u001bakEJR5mi\u0016\u0014\u0018*\u001c9m\u0003\u0019a\u0014N\\5u}Q\tq\u0007\u0005\u00029\u00015\t\u0011%\u0001\nY\u0013:\u001bE*\u0016#F?:\u000bU*R*Q\u0003\u000e+U#A\u001e\u0011\u0005q\u001aeBA\u001fB!\tqt%D\u0001@\u0015\t\u0001\u0015&\u0001\u0004=e>|GOP\u0005\u0003\u0005\u001e\na\u0001\u0015:fI\u00164\u0017B\u0001#F\u0005\u0019\u0019FO]5oO*\u0011!iJ\u0001\u00141&s5\tT+E\u000b~s\u0015)T#T!\u0006\u001bU\tI\u0001\u0006E\u0006\u001cXm]\u000b\u0002\u0013B\u0019!jT)\u000e\u0003-S!\u0001T'\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u001d\u0006!!.\u0019<b\u0013\t\u00016JA\u0003Ti\u0006\u001c7\u000e\u0005\u0002S+6\t1K\u0003\u0002U\u001b\u0006\u0019a.\u001a;\n\u0005Y\u001b&aA+S\u0019\u00061!-Y:fg\u0002\n\u0001\u0002\\8dCR|'o]\u000b\u00025B\u0019!jT.\u0011\u0005qkV\"A\u0018\n\u0005y{#a\u0002'pG\u0006$xN]\u0001\nY>\u001c\u0017\r^8sg\u0002\n!c]3u\t>\u001cW/\\3oi2{7-\u0019;peR\u0011!M\u001a\t\u0003G\u0012l\u0011aJ\u0005\u0003K\u001e\u0012A!\u00168ji\")q\r\u0003a\u00017\u00069An\\2bi>\u0014\u0018!\u00027fm\u0016dW#\u00016\u0011\u0005\r\\\u0017B\u00017(\u0005\rIe\u000e^\u0001\nY\u00164X\r\\0%KF$\"AY8\t\u000fAT\u0011\u0011!a\u0001U\u0006\u0019\u0001\u0010J\u0019\u0002\r1,g/\u001a7!\u0003QIgn]5eK&s7\r\\;eK\u0016cW-\\3oiV\tA\u000f\u0005\u0002dk&\u0011ao\n\u0002\b\u0005>|G.Z1o\u00031\u0019H/\u0019:u\u000b2,W.\u001a8u)\u0015\u0011\u0017p_?\u0000\u0011\u0015QX\u00021\u0001<\u0003\r)(/\u001b\u0005\u0006y6\u0001\raO\u0001\nY>\u001c\u0017\r\u001c(b[\u0016DQA`\u0007A\u0002m\nQ!\u001d(b[\u0016Dq!!\u0001\u000e\u0001\u0004\t\u0019!A\u0003biR\u001c\u0018\u0007E\u0002]\u0003\u000bI1!a\u00020\u0005)\tE\u000f\u001e:jEV$Xm]\u0001\u000bK:$W\t\\3nK:$Hc\u00022\u0002\u000e\u0005=\u0011\u0011\u0003\u0005\u0006u:\u0001\ra\u000f\u0005\u0006y:\u0001\ra\u000f\u0005\u0006}:\u0001\raO\u0001\u0006I\u0016\u0004H\u000f[\u0001\nI\u0016\u0004H\u000f[0%KF$2AYA\r\u0011\u001d\u0001\b#!AA\u0002)\fa\u0001Z3qi\"\u0004\u0013!D:uCJ$Hi\\2v[\u0016tG\u000fF\u0001c\u0003-)g\u000e\u001a#pGVlWM\u001c;\u0002%M$\u0018M\u001d;Qe\u00164\u0017\u000e_'baBLgn\u001a\u000b\u0006E\u0006\u001d\u00121\u0006\u0005\u0007\u0003S!\u0002\u0019A\u001e\u0002\rA\u0014XMZ5y\u0011\u0015QH\u00031\u0001<\u0003A)g\u000e\u001a)sK\u001aL\u00070T1qa&tw\rF\u0002c\u0003cAa!!\u000b\u0016\u0001\u0004Y\u0014AC2iCJ\f7\r^3sgR9!-a\u000e\u0002H\u0005-\u0003bBA\u001d-\u0001\u0007\u00111H\u0001\u0003G\"\u0004RaYA\u001f\u0003\u0003J1!a\u0010(\u0005\u0015\t%O]1z!\r\u0019\u00171I\u0005\u0004\u0003\u000b:#\u0001B\"iCJDa!!\u0013\u0017\u0001\u0004Q\u0017!B:uCJ$\bBBA'-\u0001\u0007!.\u0001\u0004mK:<G\u000f[\u0001\u0014S\u001etwN]1cY\u0016<\u0006.\u001b;fgB\f7-\u001a\u000b\bE\u0006M\u0013QKA,\u0011\u001d\tId\u0006a\u0001\u0003wAa!!\u0013\u0018\u0001\u0004Q\u0007BBA'/\u0001\u0007!.A\u000bqe>\u001cWm]:j]\u001eLen\u001d;sk\u000e$\u0018n\u001c8\u0015\u000b\t\fi&!\u0019\t\r\u0005}\u0003\u00041\u0001<\u0003\u0019!\u0018M]4fi\"1\u00111\r\rA\u0002m\nA\u0001Z1uC\u0006i1o[5qa\u0016$WI\u001c;jif$2AYA5\u0011\u0019\tY'\u0007a\u0001w\u0005!a.Y7f\u0003-9W\r\u001e'pG\u0006$\u0018n\u001c8\u0002'%t7\r\\;eKR+\u0007\u0010\u001e#pGVlWM\u001c;\u0015\u000b\t\f\u0019(a\u001e\t\r\u0005U4\u00041\u0001<\u0003\r)(\u000f\u001c\u0005\u0007\u0003sZ\u0002\u0019A\u001e\u0002\u0013\u0015t7m\u001c3j]\u001e\f\u0014AB1u%>|G/\u0001\u0006biJ{w\u000e^0%KF$2AYAA\u0011\u001d\u0001X$!AA\u0002Q\fq!\u0019;S_>$\b%\u0001\nj]\u000edW\u000fZ3Y\u001b2#unY;nK:$Hc\u00012\u0002\n\"1\u0011QO\u0010A\u0002m\u0002"
)
public class XIncludeFilter extends XMLFilterImpl {
   private final String XINCLUDE_NAMESPACE = "http://www.w3.org/2001/XInclude";
   private final Stack bases = new Stack();
   private final Stack locators = new Stack();
   private int level = 0;
   private int depth = 0;
   private boolean atRoot = false;

   public final String XINCLUDE_NAMESPACE() {
      return this.XINCLUDE_NAMESPACE;
   }

   private Stack bases() {
      return this.bases;
   }

   private Stack locators() {
      return this.locators;
   }

   public void setDocumentLocator(final Locator locator) {
      this.locators().push(locator);
      String base = locator.getSystemId();

      try {
         this.bases().push(new URL(base));
      } catch (MalformedURLException var3) {
         throw new UnsupportedOperationException((new StringBuilder(24)).append("Unrecognized SYSTEM ID: ").append(base).toString());
      }

      super.setDocumentLocator(locator);
   }

   private int level() {
      return this.level;
   }

   private void level_$eq(final int x$1) {
      this.level = x$1;
   }

   public boolean insideIncludeElement() {
      return this.level() != 0;
   }

   public void startElement(final String uri, final String localName, final String qName, final Attributes atts1) {
      Attributes atts = atts1;
      if (this.level() == 0) {
         String base = atts1.getValue("http://www.w3.org/XML/1998/namespace", "base");
         URL parentBase = (URL)this.bases().peek();
         URL currentBase = parentBase;
         if (base != null) {
            try {
               currentBase = new URL(parentBase, base);
            } catch (MalformedURLException var17) {
               throw new SAXException((new StringBuilder(20)).append("Malformed base URL: ").append(parentBase).toString(), var17);
            }
         }

         label84: {
            this.bases().push(currentBase);
            String var10 = this.XINCLUDE_NAMESPACE();
            if (uri == null) {
               if (var10 != null) {
                  break label84;
               }
            } else if (!uri.equals(var10)) {
               break label84;
            }

            String var11 = "include";
            if (localName == null) {
               if (var11 != null) {
                  break label84;
               }
            } else if (!localName.equals(var11)) {
               break label84;
            }

            String href = atts1.getValue("href");
            if (href == null) {
               throw new SAXException("Missing href attribute");
            }

            String parse = atts1.getValue("parse");
            if (parse == null) {
               parse = "xml";
            }

            label57: {
               label79: {
                  String var14 = "text";
                  if (parse == null) {
                     if (var14 == null) {
                        break label79;
                     }
                  } else if (parse.equals(var14)) {
                     break label79;
                  }

                  String var15 = "xml";
                  if (parse == null) {
                     if (var15 != null) {
                        throw new SAXException((new StringBuilder(35)).append("Illegal value for parse attribute: ").append(parse).toString());
                     }
                  } else if (!parse.equals(var15)) {
                     throw new SAXException((new StringBuilder(35)).append("Illegal value for parse attribute: ").append(parse).toString());
                  }

                  this.includeXMLDocument(href);
                  break label57;
               }

               this.includeTextDocument(href, atts1.getValue("encoding"));
            }

            this.level_$eq(this.level() + 1);
            return;
         }

         if (this.atRoot()) {
            AttributesImpl attsImpl = new AttributesImpl(atts1);
            attsImpl.addAttribute("http://www.w3.org/XML/1998/namespace", "base", "xml:base", "CDATA", currentBase.toExternalForm());
            atts = attsImpl;
            this.atRoot_$eq(false);
         }

         super.startElement(uri, localName, qName, atts);
      }
   }

   public void endElement(final String uri, final String localName, final String qName) {
      label29: {
         label26: {
            String var4 = this.XINCLUDE_NAMESPACE();
            if (uri == null) {
               if (var4 != null) {
                  break label26;
               }
            } else if (!uri.equals(var4)) {
               break label26;
            }

            String var5 = "include";
            if (localName == null) {
               if (var5 == null) {
                  break label29;
               }
            } else if (localName.equals(var5)) {
               break label29;
            }
         }

         if (this.level() == 0) {
            this.bases().pop();
            super.endElement(uri, localName, qName);
            return;
         }

         return;
      }

      this.level_$eq(this.level() - 1);
   }

   private int depth() {
      return this.depth;
   }

   private void depth_$eq(final int x$1) {
      this.depth = x$1;
   }

   public void startDocument() {
      this.level_$eq(0);
      if (this.depth() == 0) {
         super.startDocument();
      }

      this.depth_$eq(this.depth() + 1);
   }

   public void endDocument() {
      this.locators().pop();
      this.bases().pop();
      this.depth_$eq(this.depth() - 1);
      if (this.depth() == 0) {
         super.endDocument();
      }
   }

   public void startPrefixMapping(final String prefix, final String uri) {
      if (this.level() == 0) {
         super.startPrefixMapping(prefix, uri);
      }
   }

   public void endPrefixMapping(final String prefix) {
      if (this.level() == 0) {
         super.endPrefixMapping(prefix);
      }
   }

   public void characters(final char[] ch, final int start, final int length) {
      if (this.level() == 0) {
         super.characters(ch, start, length);
      }
   }

   public void ignorableWhitespace(final char[] ch, final int start, final int length) {
      if (this.level() == 0) {
         super.ignorableWhitespace(ch, start, length);
      }
   }

   public void processingInstruction(final String target, final String data) {
      if (this.level() == 0) {
         super.processingInstruction(target, data);
      }
   }

   public void skippedEntity(final String name) {
      if (this.level() == 0) {
         super.skippedEntity(name);
      }
   }

   private String getLocation() {
      String locationString = "";
      Locator locator = (Locator)this.locators().peek();
      String publicID = "";
      String systemID = "";
      int column = -1;
      int line = -1;
      if (locator != null) {
         publicID = locator.getPublicId();
         systemID = locator.getSystemId();
         line = locator.getLineNumber();
         column = locator.getColumnNumber();
      }

      locationString = (new StringBuilder(49)).append(" in document included from ").append(publicID).append(" at ").append(systemID).append(" at line ").append(line).append(", column ").append(column).toString();
      return locationString;
   }

   private void includeTextDocument(final String url, final String encoding1) {
      String encoding = encoding1;
      if (encoding1 == null || encoding1.trim().isEmpty()) {
         encoding = "UTF-8";
      }

      URL source = null;

      try {
         URL base = (URL)this.bases().peek();
         source = new URL(base, url);
      } catch (MalformedURLException var19) {
         UnavailableResourceException ex = new UnavailableResourceException((new StringBuilder(17)).append("Unresolvable URL ").append(url).append(this.getLocation()).toString());
         ex.setRootCause(var19);
         throw new SAXException((new StringBuilder(17)).append("Unresolvable URL ").append(url).append(this.getLocation()).toString(), ex);
      }

      try {
         URLConnection uc = source.openConnection();
         BufferedInputStream in = new BufferedInputStream(uc.getInputStream());
         String encodingFromHeader = uc.getContentEncoding();
         String contentType = uc.getContentType();
         if (encodingFromHeader != null) {
            encoding = encodingFromHeader;
         } else if (contentType != null) {
            label68: {
               label81: {
                  contentType = contentType.toLowerCase();
                  String var12 = "text/xml";
                  if (contentType == null) {
                     if (var12 == null) {
                        break label81;
                     }
                  } else if (contentType.equals(var12)) {
                     break label81;
                  }

                  String var13 = "application/xml";
                  if (contentType == null) {
                     if (var13 == null) {
                        break label81;
                     }
                  } else if (contentType.equals(var13)) {
                     break label81;
                  }

                  if ((!contentType.startsWith("text/") || !contentType.endsWith("+xml")) && (!contentType.startsWith("application/") || !contentType.endsWith("+xml"))) {
                     break label68;
                  }
               }

               encoding = EncodingHeuristics$.MODULE$.readEncodingFromStream(in);
            }
         }

         InputStreamReader reader = new InputStreamReader(in, encoding);
         char[] c = new char[1024];
         int charsRead = 0;

         do {
            charsRead = reader.read(c, 0, 1024);
            if (charsRead > 0) {
               this.characters(c, 0, charsRead);
            }
         } while(charsRead != -1);

      } catch (UnsupportedEncodingException var20) {
         throw new SAXException((new StringBuilder(22)).append("Unsupported encoding: ").append(encoding).append(this.getLocation()).toString(), var20);
      } catch (IOException var21) {
         throw new SAXException((new StringBuilder(20)).append("Document not found: ").append(source.toExternalForm()).append(this.getLocation()).toString(), var21);
      }
   }

   private boolean atRoot() {
      return this.atRoot;
   }

   private void atRoot_$eq(final boolean x$1) {
      this.atRoot = x$1;
   }

   private void includeXMLDocument(final String url) {
      URL var10000;
      try {
         var10000 = new URL((URL)this.bases().peek(), url);
      } catch (MalformedURLException var12) {
         UnavailableResourceException ex = new UnavailableResourceException((new StringBuilder(17)).append("Unresolvable URL ").append(url).append(this.getLocation()).toString());
         ex.setRootCause(var12);
         throw new SAXException((new StringBuilder(17)).append("Unresolvable URL ").append(url).append(this.getLocation()).toString(), ex);
      }

      URL source = var10000;

      try {
         try {
            var13 = XMLReaderFactory.createXMLReader();
         } catch (SAXException var10) {
            try {
               var13 = XMLReaderFactory.createXMLReader(package$.MODULE$.XercesClassName());
            } catch (SAXException var9) {
               System.err.println("Could not find an XML parser");
               return;
            }
         }

         XMLReader parser = var13;
         parser.setContentHandler(this);
         EntityResolver resolver = this.getEntityResolver();
         if (resolver != null) {
            parser.setEntityResolver(resolver);
         }

         int previousLevel = this.level();
         this.level_$eq(0);
         if (this.bases().contains(source)) {
            throw new SAXException("Circular XInclude Reference", new CircularIncludeException((new StringBuilder(31)).append("Circular XInclude Reference to ").append(source).append(this.getLocation()).toString()));
         } else {
            this.bases().push(source);
            this.atRoot_$eq(true);
            parser.parse(source.toExternalForm());
            this.level_$eq(previousLevel);
            this.bases().pop();
         }
      } catch (IOException var11) {
         throw new SAXException((new StringBuilder(20)).append("Document not found: ").append(source.toExternalForm()).append(this.getLocation()).toString(), var11);
      }
   }
}
