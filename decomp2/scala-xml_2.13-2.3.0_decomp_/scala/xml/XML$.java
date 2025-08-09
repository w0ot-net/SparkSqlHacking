package scala.xml;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.nio.channels.Channels;
import javax.xml.parsers.SAXParser;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import scala.Enumeration;
import scala.collection.immutable.Seq;
import scala.runtime.java8.JFunction0;
import scala.util.control.Exception.;
import scala.xml.dtd.DocType;
import scala.xml.factory.XMLLoader;
import scala.xml.parsing.FactoryAdapter;

public final class XML$ implements XMLLoader {
   public static final XML$ MODULE$ = new XML$();
   private static final String xml;
   private static final String xmlns;
   private static final String namespace;
   private static final String preserve;
   private static final String space;
   private static final String lang;
   private static final String encoding;
   private static ThreadLocal scala$xml$factory$XMLLoader$$parserInstance;
   private static volatile boolean bitmap$0;

   static {
      XMLLoader.$init$(MODULE$);
      xml = "xml";
      xmlns = "xmlns";
      namespace = "http://www.w3.org/XML/1998/namespace";
      preserve = "preserve";
      space = "space";
      lang = "lang";
      encoding = "UTF-8";
   }

   public SAXParser parser() {
      return XMLLoader.parser$(this);
   }

   public XMLReader reader() {
      return XMLLoader.reader$(this);
   }

   public Node loadXML(final InputSource inputSource, final SAXParser parser) {
      return XMLLoader.loadXML$(this, inputSource, parser);
   }

   public Seq loadXMLNodes(final InputSource inputSource, final SAXParser parser) {
      return XMLLoader.loadXMLNodes$(this, inputSource, parser);
   }

   public FactoryAdapter adapter() {
      return XMLLoader.adapter$(this);
   }

   public Document loadDocument(final InputSource inputSource) {
      return XMLLoader.loadDocument$(this, (InputSource)inputSource);
   }

   public Document loadFileDocument(final String fileName) {
      return XMLLoader.loadFileDocument$(this, (String)fileName);
   }

   public Document loadFileDocument(final File file) {
      return XMLLoader.loadFileDocument$(this, (File)file);
   }

   public Document loadDocument(final URL url) {
      return XMLLoader.loadDocument$(this, (URL)url);
   }

   public Document loadDocument(final String sysId) {
      return XMLLoader.loadDocument$(this, (String)sysId);
   }

   public Document loadFileDocument(final FileDescriptor fileDescriptor) {
      return XMLLoader.loadFileDocument$(this, (FileDescriptor)fileDescriptor);
   }

   public Document loadDocument(final InputStream inputStream) {
      return XMLLoader.loadDocument$(this, (InputStream)inputStream);
   }

   public Document loadDocument(final Reader reader) {
      return XMLLoader.loadDocument$(this, (Reader)reader);
   }

   public Document loadStringDocument(final String string) {
      return XMLLoader.loadStringDocument$(this, string);
   }

   public Node load(final InputSource inputSource) {
      return XMLLoader.load$(this, (InputSource)inputSource);
   }

   public Node loadFile(final String fileName) {
      return XMLLoader.loadFile$(this, (String)fileName);
   }

   public Node loadFile(final File file) {
      return XMLLoader.loadFile$(this, (File)file);
   }

   public Node load(final URL url) {
      return XMLLoader.load$(this, (URL)url);
   }

   public Node load(final String sysId) {
      return XMLLoader.load$(this, (String)sysId);
   }

   public Node loadFile(final FileDescriptor fileDescriptor) {
      return XMLLoader.loadFile$(this, (FileDescriptor)fileDescriptor);
   }

   public Node load(final InputStream inputStream) {
      return XMLLoader.load$(this, (InputStream)inputStream);
   }

   public Node load(final Reader reader) {
      return XMLLoader.load$(this, (Reader)reader);
   }

   public Node loadString(final String string) {
      return XMLLoader.loadString$(this, string);
   }

   public Seq loadNodes(final InputSource inputSource) {
      return XMLLoader.loadNodes$(this, (InputSource)inputSource);
   }

   public Seq loadFileNodes(final String fileName) {
      return XMLLoader.loadFileNodes$(this, (String)fileName);
   }

   public Seq loadFileNodes(final File file) {
      return XMLLoader.loadFileNodes$(this, (File)file);
   }

   public Seq loadNodes(final URL url) {
      return XMLLoader.loadNodes$(this, (URL)url);
   }

   public Seq loadNodes(final String sysId) {
      return XMLLoader.loadNodes$(this, (String)sysId);
   }

   public Seq loadFileNodes(final FileDescriptor fileDescriptor) {
      return XMLLoader.loadFileNodes$(this, (FileDescriptor)fileDescriptor);
   }

   public Seq loadNodes(final InputStream inputStream) {
      return XMLLoader.loadNodes$(this, (InputStream)inputStream);
   }

   public Seq loadNodes(final Reader reader) {
      return XMLLoader.loadNodes$(this, (Reader)reader);
   }

   public Seq loadStringNodes(final String string) {
      return XMLLoader.loadStringNodes$(this, string);
   }

   private ThreadLocal scala$xml$factory$XMLLoader$$parserInstance$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            scala$xml$factory$XMLLoader$$parserInstance = XMLLoader.scala$xml$factory$XMLLoader$$parserInstance$(this);
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return scala$xml$factory$XMLLoader$$parserInstance;
   }

   public ThreadLocal scala$xml$factory$XMLLoader$$parserInstance() {
      return !bitmap$0 ? this.scala$xml$factory$XMLLoader$$parserInstance$lzycompute() : scala$xml$factory$XMLLoader$$parserInstance;
   }

   public String xml() {
      return xml;
   }

   public String xmlns() {
      return xmlns;
   }

   public String namespace() {
      return namespace;
   }

   public String preserve() {
      return preserve;
   }

   public String space() {
      return space;
   }

   public String lang() {
      return lang;
   }

   public String encoding() {
      return encoding;
   }

   public XMLLoader withSAXParser(final SAXParser p) {
      return new XMLLoader(p) {
         private final SAXParser parser;
         private ThreadLocal scala$xml$factory$XMLLoader$$parserInstance;
         private volatile boolean bitmap$0;

         public XMLReader reader() {
            return XMLLoader.reader$(this);
         }

         public Node loadXML(final InputSource inputSource, final SAXParser parser) {
            return XMLLoader.loadXML$(this, inputSource, parser);
         }

         public Seq loadXMLNodes(final InputSource inputSource, final SAXParser parser) {
            return XMLLoader.loadXMLNodes$(this, inputSource, parser);
         }

         public FactoryAdapter adapter() {
            return XMLLoader.adapter$(this);
         }

         public Document loadDocument(final InputSource inputSource) {
            return XMLLoader.loadDocument$(this, (InputSource)inputSource);
         }

         public Document loadFileDocument(final String fileName) {
            return XMLLoader.loadFileDocument$(this, (String)fileName);
         }

         public Document loadFileDocument(final File file) {
            return XMLLoader.loadFileDocument$(this, (File)file);
         }

         public Document loadDocument(final URL url) {
            return XMLLoader.loadDocument$(this, (URL)url);
         }

         public Document loadDocument(final String sysId) {
            return XMLLoader.loadDocument$(this, (String)sysId);
         }

         public Document loadFileDocument(final FileDescriptor fileDescriptor) {
            return XMLLoader.loadFileDocument$(this, (FileDescriptor)fileDescriptor);
         }

         public Document loadDocument(final InputStream inputStream) {
            return XMLLoader.loadDocument$(this, (InputStream)inputStream);
         }

         public Document loadDocument(final Reader reader) {
            return XMLLoader.loadDocument$(this, (Reader)reader);
         }

         public Document loadStringDocument(final String string) {
            return XMLLoader.loadStringDocument$(this, string);
         }

         public Node load(final InputSource inputSource) {
            return XMLLoader.load$(this, (InputSource)inputSource);
         }

         public Node loadFile(final String fileName) {
            return XMLLoader.loadFile$(this, (String)fileName);
         }

         public Node loadFile(final File file) {
            return XMLLoader.loadFile$(this, (File)file);
         }

         public Node load(final URL url) {
            return XMLLoader.load$(this, (URL)url);
         }

         public Node load(final String sysId) {
            return XMLLoader.load$(this, (String)sysId);
         }

         public Node loadFile(final FileDescriptor fileDescriptor) {
            return XMLLoader.loadFile$(this, (FileDescriptor)fileDescriptor);
         }

         public Node load(final InputStream inputStream) {
            return XMLLoader.load$(this, (InputStream)inputStream);
         }

         public Node load(final Reader reader) {
            return XMLLoader.load$(this, (Reader)reader);
         }

         public Node loadString(final String string) {
            return XMLLoader.loadString$(this, string);
         }

         public Seq loadNodes(final InputSource inputSource) {
            return XMLLoader.loadNodes$(this, (InputSource)inputSource);
         }

         public Seq loadFileNodes(final String fileName) {
            return XMLLoader.loadFileNodes$(this, (String)fileName);
         }

         public Seq loadFileNodes(final File file) {
            return XMLLoader.loadFileNodes$(this, (File)file);
         }

         public Seq loadNodes(final URL url) {
            return XMLLoader.loadNodes$(this, (URL)url);
         }

         public Seq loadNodes(final String sysId) {
            return XMLLoader.loadNodes$(this, (String)sysId);
         }

         public Seq loadFileNodes(final FileDescriptor fileDescriptor) {
            return XMLLoader.loadFileNodes$(this, (FileDescriptor)fileDescriptor);
         }

         public Seq loadNodes(final InputStream inputStream) {
            return XMLLoader.loadNodes$(this, (InputStream)inputStream);
         }

         public Seq loadNodes(final Reader reader) {
            return XMLLoader.loadNodes$(this, (Reader)reader);
         }

         public Seq loadStringNodes(final String string) {
            return XMLLoader.loadStringNodes$(this, string);
         }

         private ThreadLocal scala$xml$factory$XMLLoader$$parserInstance$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.scala$xml$factory$XMLLoader$$parserInstance = XMLLoader.scala$xml$factory$XMLLoader$$parserInstance$(this);
                  this.bitmap$0 = true;
               }
            } catch (Throwable var3) {
               throw var3;
            }

            return this.scala$xml$factory$XMLLoader$$parserInstance;
         }

         public ThreadLocal scala$xml$factory$XMLLoader$$parserInstance() {
            return !this.bitmap$0 ? this.scala$xml$factory$XMLLoader$$parserInstance$lzycompute() : this.scala$xml$factory$XMLLoader$$parserInstance;
         }

         public SAXParser parser() {
            return this.parser;
         }

         public {
            XMLLoader.$init$(this);
            this.parser = p$1;
         }
      };
   }

   public XMLLoader withXMLReader(final XMLReader r) {
      return new XMLLoader(r) {
         private final XMLReader reader;
         private ThreadLocal scala$xml$factory$XMLLoader$$parserInstance;
         private volatile boolean bitmap$0;

         public SAXParser parser() {
            return XMLLoader.parser$(this);
         }

         public Node loadXML(final InputSource inputSource, final SAXParser parser) {
            return XMLLoader.loadXML$(this, inputSource, parser);
         }

         public Seq loadXMLNodes(final InputSource inputSource, final SAXParser parser) {
            return XMLLoader.loadXMLNodes$(this, inputSource, parser);
         }

         public FactoryAdapter adapter() {
            return XMLLoader.adapter$(this);
         }

         public Document loadDocument(final InputSource inputSource) {
            return XMLLoader.loadDocument$(this, (InputSource)inputSource);
         }

         public Document loadFileDocument(final String fileName) {
            return XMLLoader.loadFileDocument$(this, (String)fileName);
         }

         public Document loadFileDocument(final File file) {
            return XMLLoader.loadFileDocument$(this, (File)file);
         }

         public Document loadDocument(final URL url) {
            return XMLLoader.loadDocument$(this, (URL)url);
         }

         public Document loadDocument(final String sysId) {
            return XMLLoader.loadDocument$(this, (String)sysId);
         }

         public Document loadFileDocument(final FileDescriptor fileDescriptor) {
            return XMLLoader.loadFileDocument$(this, (FileDescriptor)fileDescriptor);
         }

         public Document loadDocument(final InputStream inputStream) {
            return XMLLoader.loadDocument$(this, (InputStream)inputStream);
         }

         public Document loadDocument(final Reader reader) {
            return XMLLoader.loadDocument$(this, (Reader)reader);
         }

         public Document loadStringDocument(final String string) {
            return XMLLoader.loadStringDocument$(this, string);
         }

         public Node load(final InputSource inputSource) {
            return XMLLoader.load$(this, (InputSource)inputSource);
         }

         public Node loadFile(final String fileName) {
            return XMLLoader.loadFile$(this, (String)fileName);
         }

         public Node loadFile(final File file) {
            return XMLLoader.loadFile$(this, (File)file);
         }

         public Node load(final URL url) {
            return XMLLoader.load$(this, (URL)url);
         }

         public Node load(final String sysId) {
            return XMLLoader.load$(this, (String)sysId);
         }

         public Node loadFile(final FileDescriptor fileDescriptor) {
            return XMLLoader.loadFile$(this, (FileDescriptor)fileDescriptor);
         }

         public Node load(final InputStream inputStream) {
            return XMLLoader.load$(this, (InputStream)inputStream);
         }

         public Node load(final Reader reader) {
            return XMLLoader.load$(this, (Reader)reader);
         }

         public Node loadString(final String string) {
            return XMLLoader.loadString$(this, string);
         }

         public Seq loadNodes(final InputSource inputSource) {
            return XMLLoader.loadNodes$(this, (InputSource)inputSource);
         }

         public Seq loadFileNodes(final String fileName) {
            return XMLLoader.loadFileNodes$(this, (String)fileName);
         }

         public Seq loadFileNodes(final File file) {
            return XMLLoader.loadFileNodes$(this, (File)file);
         }

         public Seq loadNodes(final URL url) {
            return XMLLoader.loadNodes$(this, (URL)url);
         }

         public Seq loadNodes(final String sysId) {
            return XMLLoader.loadNodes$(this, (String)sysId);
         }

         public Seq loadFileNodes(final FileDescriptor fileDescriptor) {
            return XMLLoader.loadFileNodes$(this, (FileDescriptor)fileDescriptor);
         }

         public Seq loadNodes(final InputStream inputStream) {
            return XMLLoader.loadNodes$(this, (InputStream)inputStream);
         }

         public Seq loadNodes(final Reader reader) {
            return XMLLoader.loadNodes$(this, (Reader)reader);
         }

         public Seq loadStringNodes(final String string) {
            return XMLLoader.loadStringNodes$(this, string);
         }

         private ThreadLocal scala$xml$factory$XMLLoader$$parserInstance$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.scala$xml$factory$XMLLoader$$parserInstance = XMLLoader.scala$xml$factory$XMLLoader$$parserInstance$(this);
                  this.bitmap$0 = true;
               }
            } catch (Throwable var3) {
               throw var3;
            }

            return this.scala$xml$factory$XMLLoader$$parserInstance;
         }

         public ThreadLocal scala$xml$factory$XMLLoader$$parserInstance() {
            return !this.bitmap$0 ? this.scala$xml$factory$XMLLoader$$parserInstance$lzycompute() : this.scala$xml$factory$XMLLoader$$parserInstance;
         }

         public XMLReader reader() {
            return this.reader;
         }

         public {
            XMLLoader.$init$(this);
            this.reader = r$1;
         }
      };
   }

   public final void save(final String filename, final Node node, final String enc, final boolean xmlDecl, final DocType doctype) {
      FileOutputStream fos = new FileOutputStream(filename);
      Writer w = Channels.newWriter(fos.getChannel(), enc);
      .MODULE$.ultimately((JFunction0.mcV.sp)() -> w.close()).apply((JFunction0.mcV.sp)() -> MODULE$.write(w, node, enc, xmlDecl, doctype, MODULE$.write$default$6()));
   }

   public final String save$default$3() {
      return "UTF-8";
   }

   public final boolean save$default$4() {
      return false;
   }

   public final DocType save$default$5() {
      return null;
   }

   public final void write(final Writer w, final Node node, final String enc, final boolean xmlDecl, final DocType doctype, final Enumeration.Value minimizeTags) {
      if (xmlDecl) {
         w.write((new StringBuilder(34)).append("<?xml version='1.0' encoding='").append(enc).append("'?>\n").toString());
      }

      if (doctype != null) {
         w.write((new StringBuilder(1)).append(doctype).append("\n").toString());
      }

      NamespaceBinding x$3 = Utility$.MODULE$.serialize$default$2();
      scala.collection.mutable.StringBuilder x$4 = Utility$.MODULE$.serialize$default$3();
      boolean x$5 = Utility$.MODULE$.serialize$default$4();
      boolean x$6 = Utility$.MODULE$.serialize$default$5();
      boolean x$7 = Utility$.MODULE$.serialize$default$6();
      w.write(Utility$.MODULE$.serialize(node, x$3, x$4, x$5, x$6, x$7, minimizeTags).toString());
   }

   public final Enumeration.Value write$default$6() {
      return MinimizeMode$.MODULE$.Default();
   }

   private XML$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
