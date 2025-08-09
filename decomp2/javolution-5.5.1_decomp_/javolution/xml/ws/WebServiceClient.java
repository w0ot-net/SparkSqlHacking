package javolution.xml.ws;

import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javolution.io.AppendableWriter;
import javolution.io.UTF8StreamWriter;
import javolution.text.Text;
import javolution.text.TextBuilder;
import javolution.xml.XMLObjectReader;
import javolution.xml.XMLObjectWriter;
import javolution.xml.stream.XMLStreamException;
import javolution.xml.stream.XMLStreamReader;
import javolution.xml.stream.XMLStreamWriter;

public abstract class WebServiceClient {
   public static final String ENVELOPE_PREFIX = "env";
   public static final String ENVELOPE_URI = "http://schemas.xmlsoap.org/soap/envelope/";
   Object _url;
   private final TextBuilder _buffer = new TextBuilder();
   private final AppendableWriter _out = new AppendableWriter();
   private final XMLObjectWriter _writer = new XMLObjectWriter();
   private final UTF8StreamWriter _utf8Writer = new UTF8StreamWriter();
   private final XMLObjectReader _reader = new XMLObjectReader();

   public WebServiceClient setAddress(String address) {
      try {
         this._url = new URL(address);
         return this;
      } catch (MalformedURLException var3) {
         throw new IllegalArgumentException("Malformed URL: " + address);
      }
   }

   public void invoke() throws IOException, XMLStreamException {
      try {
         this._out.setOutput(this._buffer);
         this._writer.setOutput((Writer)this._out);
         XMLStreamWriter xmlOut = this._writer.getStreamWriter();
         xmlOut.setPrefix(csq("env"), csq("http://schemas.xmlsoap.org/soap/envelope/"));
         xmlOut.writeStartElement(csq("http://schemas.xmlsoap.org/soap/envelope/"), csq("Envelope"));
         xmlOut.writeNamespace(csq("env"), csq("http://schemas.xmlsoap.org/soap/envelope/"));
         xmlOut.writeStartElement(csq("http://schemas.xmlsoap.org/soap/envelope/"), csq("Header"));
         xmlOut.writeEndElement();
         xmlOut.writeStartElement(csq("http://schemas.xmlsoap.org/soap/envelope/"), csq("Body"));
         this.writeRequest(this._writer);
         this._writer.close();
         if (this._url == null) {
            throw new IOException("URL not set");
         }

         HttpURLConnection http = (HttpURLConnection)((URL)this._url).openConnection();
         http.setRequestProperty("Content-Length", String.valueOf(this._buffer.length()));
         http.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
         http.setRequestMethod("POST");
         http.setDoOutput(true);
         http.setDoInput(true);
         this._utf8Writer.setOutput(http.getOutputStream());
         this._buffer.print(this._utf8Writer);
         this._utf8Writer.close();
         this._reader.setInput(http.getInputStream());
         XMLStreamReader xmlIn = this._reader.getStreamReader();

         while(xmlIn.hasNext()) {
            if (xmlIn.next() == 1 && xmlIn.getLocalName().equals("Body") && xmlIn.getNamespaceURI().equals("http://schemas.xmlsoap.org/soap/envelope/")) {
               xmlIn.next();
               this.readResponse(this._reader);
               break;
            }
         }
      } finally {
         this._reader.close();
         this._writer.reset();
         this._out.reset();
         this._buffer.reset();
         this._utf8Writer.reset();
         this._reader.reset();
      }

   }

   protected abstract void writeRequest(XMLObjectWriter var1) throws XMLStreamException;

   protected void readResponse(XMLObjectReader in) throws XMLStreamException {
      XMLStreamReader xml = in.getStreamReader();

      label38:
      while(xml.hasNext()) {
         switch (xml.next()) {
            case 1:
               System.out.println("Start Element: " + xml.getLocalName() + "(" + xml.getNamespaceURI() + ")");
               int i = 0;
               int n = xml.getAttributeCount();

               while(true) {
                  if (i >= n) {
                     continue label38;
                  }

                  System.out.println("   Attribute: " + xml.getAttributeLocalName(i) + "(" + xml.getAttributeNamespace(i) + "), Value: " + xml.getAttributeValue(i));
                  ++i;
               }
            case 2:
               if (xml.getLocalName().equals("Body") && xml.getNamespaceURI().equals("http://schemas.xmlsoap.org/soap/envelope/")) {
                  return;
               }

               System.out.println("End Element: " + xml.getLocalName() + "(" + xml.getNamespaceURI() + ")");
               continue;
            case 3:
            case 9:
            case 10:
            case 11:
            default:
               System.out.println(xml);
               continue;
            case 4:
               System.out.println("Characters: " + xml.getText());
               continue;
            case 5:
               System.out.println("Comment: " + xml.getText());
               continue;
            case 6:
               System.out.println("Space");
               continue;
            case 7:
               System.out.println("Start Document");
               continue;
            case 8:
               System.out.println("End Document.");
               continue;
            case 12:
         }

         System.out.println("CDATA: " + xml.getText());
      }

   }

   private static final CharSequence csq(Object string) {
      return (CharSequence)(string instanceof CharSequence ? (CharSequence)string : Text.valueOf(string));
   }
}
