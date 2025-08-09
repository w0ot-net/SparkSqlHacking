package javolution.xml.stream;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.realtime.MemoryArea;
import javolution.context.ObjectFactory;
import javolution.io.UTF8StreamWriter;
import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.text.Text;
import javolution.text.TextBuilder;

public final class XMLStreamWriterImpl implements XMLStreamWriter, Reusable {
   private static final int BUFFER_LENGTH = 2048;
   private int _nesting = 0;
   private TextBuilder[] _qNames = new TextBuilder[16];
   private boolean _isElementOpen;
   private boolean _isEmptyElement;
   private final char[] _buffer = new char[2048];
   private final NamespacesImpl _namespaces = new NamespacesImpl();
   private int _index;
   private boolean _isRepairingNamespaces;
   private String _repairingPrefix = "ns";
   private String _indentation;
   private String _lineSeparator = "\n";
   private int _indentationLevel;
   private boolean _automaticEmptyElements;
   private boolean _noEmptyElementTag;
   private int _autoNSCount;
   private boolean _isAttributeValue;
   ObjectFactory _objectFactory;
   private Writer _writer;
   private String _encoding;
   private final UTF8StreamWriter _utf8StreamWriter = new UTF8StreamWriter();
   private final CharArray _noChar = new CharArray("");
   private final CharArray _tmpCharArray = new CharArray();
   private final TextBuilder _autoPrefix = new TextBuilder();

   public XMLStreamWriterImpl() {
      for(int i = 0; i < this._qNames.length; this._qNames[i++] = new TextBuilder()) {
      }

   }

   public void setOutput(OutputStream out) throws XMLStreamException {
      this._utf8StreamWriter.setOutput(out);
      this._encoding = "UTF-8";
      this.setOutput((Writer)this._utf8StreamWriter);
   }

   public void setOutput(OutputStream out, String encoding) throws XMLStreamException {
      if (!encoding.equals("UTF-8") && !encoding.equals("utf-8") && !encoding.equals("ASCII")) {
         try {
            this._encoding = encoding;
            this.setOutput((Writer)(new OutputStreamWriter(out, encoding)));
         } catch (UnsupportedEncodingException e) {
            throw new XMLStreamException(e);
         }
      } else {
         this.setOutput(out);
      }

   }

   public void setOutput(Writer writer) throws XMLStreamException {
      if (this._writer != null) {
         throw new IllegalStateException("Writer not closed or reset");
      } else {
         this._writer = writer;
      }
   }

   public void setRepairingNamespaces(boolean isRepairingNamespaces) {
      this._isRepairingNamespaces = isRepairingNamespaces;
   }

   public void setRepairingPrefix(String repairingPrefix) {
      this._repairingPrefix = repairingPrefix;
   }

   public void setIndentation(String indentation) {
      this._indentation = indentation;
   }

   public void setLineSeparator(String lineSeparator) {
      this._lineSeparator = lineSeparator;
   }

   public void setAutomaticEmptyElements(boolean automaticEmptyElements) {
      this._automaticEmptyElements = automaticEmptyElements;
   }

   public void setNoEmptyElementTag(boolean noEmptyElementTag) {
      this._noEmptyElementTag = noEmptyElementTag;
   }

   public void reset() {
      this._automaticEmptyElements = false;
      this._autoNSCount = 0;
      this._encoding = null;
      this._indentation = null;
      this._indentationLevel = 0;
      this._index = 0;
      this._isAttributeValue = false;
      this._isElementOpen = false;
      this._isEmptyElement = false;
      this._isRepairingNamespaces = false;
      this._namespaces.reset();
      this._nesting = 0;
      this._noEmptyElementTag = false;
      this._objectFactory = null;
      this._repairingPrefix = "ns";
      this._utf8StreamWriter.reset();
      this._writer = null;
   }

   public void writeStartElement(CharSequence localName) throws XMLStreamException {
      if (localName == null) {
         throw new XMLStreamException("Local name cannot be null");
      } else {
         this.writeNewElement((CharSequence)null, localName, (CharSequence)null);
      }
   }

   public void writeStartElement(CharSequence namespaceURI, CharSequence localName) throws XMLStreamException {
      if (localName == null) {
         throw new XMLStreamException("Local name cannot be null");
      } else if (namespaceURI == null) {
         throw new XMLStreamException("Namespace URI cannot be null");
      } else {
         this.writeNewElement((CharSequence)null, localName, namespaceURI);
      }
   }

   public void writeStartElement(CharSequence prefix, CharSequence localName, CharSequence namespaceURI) throws XMLStreamException {
      if (localName == null) {
         throw new XMLStreamException("Local name cannot be null");
      } else if (namespaceURI == null) {
         throw new XMLStreamException("Namespace URI cannot be null");
      } else if (prefix == null) {
         throw new XMLStreamException("Prefix cannot be null");
      } else {
         this.writeNewElement(prefix, localName, namespaceURI);
      }
   }

   public void writeEmptyElement(CharSequence localName) throws XMLStreamException {
      this.writeStartElement(localName);
      this._isEmptyElement = true;
   }

   public void writeEmptyElement(CharSequence namespaceURI, CharSequence localName) throws XMLStreamException {
      this.writeStartElement(namespaceURI, localName);
      this._isEmptyElement = true;
   }

   public void writeEmptyElement(CharSequence prefix, CharSequence localName, CharSequence namespaceURI) throws XMLStreamException {
      this.writeStartElement(prefix, localName, namespaceURI);
      this._isEmptyElement = true;
   }

   public void writeEndElement() throws XMLStreamException {
      if (this._isElementOpen) {
         if (this._isEmptyElement) {
            this.closeOpenTag();
         } else {
            if (this._automaticEmptyElements) {
               this._isEmptyElement = true;
               this.closeOpenTag();
               return;
            }

            this.closeOpenTag();
         }
      }

      if (this._indentation != null && this._indentationLevel != this._nesting - 1) {
         this.writeNoEscape(this._lineSeparator);

         for(int i = 1; i < this._nesting; ++i) {
            this.writeNoEscape(this._indentation);
         }
      }

      this.write('<');
      this.write('/');
      this.writeNoEscape(this._qNames[this._nesting--]);
      this.write('>');
      this._namespaces.pop();
   }

   public void writeEndDocument() throws XMLStreamException {
      if (this._isElementOpen) {
         this.closeOpenTag();
      }

      while(this._nesting > 0) {
         this.writeEndElement();
      }

      this.flush();
   }

   public void close() throws XMLStreamException {
      if (this._writer != null) {
         if (this._nesting != 0) {
            this.writeEndDocument();
         }

         this.flush();
      }

      if (this._objectFactory != null) {
         this._objectFactory.recycle(this);
      } else {
         this.reset();
      }

   }

   public void flush() throws XMLStreamException {
      this.flushBuffer();

      try {
         this._writer.flush();
      } catch (IOException e) {
         throw new XMLStreamException(e);
      }
   }

   public void writeAttribute(CharSequence localName, CharSequence value) throws XMLStreamException {
      if (localName == null) {
         throw new XMLStreamException("Local name cannot be null");
      } else if (value == null) {
         throw new XMLStreamException("Value cannot be null");
      } else {
         this.writeAttributeOrNamespace((CharSequence)null, (CharSequence)null, localName, value);
      }
   }

   public void writeAttribute(CharSequence namespaceURI, CharSequence localName, CharSequence value) throws XMLStreamException {
      if (localName == null) {
         throw new XMLStreamException("Local name cannot be null");
      } else if (value == null) {
         throw new XMLStreamException("Value cannot be null");
      } else if (namespaceURI == null) {
         throw new XMLStreamException("Namespace URI cannot be null");
      } else {
         this.writeAttributeOrNamespace((CharSequence)null, namespaceURI, localName, value);
      }
   }

   public void writeAttribute(CharSequence prefix, CharSequence namespaceURI, CharSequence localName, CharSequence value) throws XMLStreamException {
      if (localName == null) {
         throw new XMLStreamException("Local name cannot be null");
      } else if (value == null) {
         throw new XMLStreamException("Value cannot be null");
      } else if (namespaceURI == null) {
         throw new XMLStreamException("Namespace URI cannot be null");
      } else if (prefix == null) {
         throw new XMLStreamException("Prefix cannot be null");
      } else {
         this.writeAttributeOrNamespace(prefix, namespaceURI, localName, value);
      }
   }

   public void writeNamespace(CharSequence prefix, CharSequence namespaceURI) throws XMLStreamException {
      if (prefix == null || prefix.length() == 0 || this._namespaces._xmlns.equals((Object)prefix)) {
         prefix = this._namespaces._defaultNsPrefix;
      }

      if (!this._isElementOpen) {
         throw new IllegalStateException("No open start element");
      } else {
         this._namespaces.setPrefix(prefix, (CharSequence)(namespaceURI == null ? this._namespaces._nullNsURI : namespaceURI), true);
      }
   }

   public void writeDefaultNamespace(CharSequence namespaceURI) throws XMLStreamException {
      this.writeNamespace(this._namespaces._defaultNsPrefix, namespaceURI);
   }

   public void writeComment(CharSequence data) throws XMLStreamException {
      if (this._isElementOpen) {
         this.closeOpenTag();
      }

      this.writeNoEscape("<!--");
      if (data != null) {
         this.writeNoEscape(data);
      }

      this.writeNoEscape("-->");
   }

   public void writeProcessingInstruction(CharSequence target) throws XMLStreamException {
      this.writeProcessingInstruction(target, this._noChar);
   }

   public void writeProcessingInstruction(CharSequence target, CharSequence data) throws XMLStreamException {
      if (target == null) {
         throw new XMLStreamException("Target cannot be null");
      } else if (data == null) {
         throw new XMLStreamException("Data cannot be null");
      } else {
         if (this._isElementOpen) {
            this.closeOpenTag();
         }

         this.writeNoEscape("<?");
         this.writeNoEscape(target);
         this.write(' ');
         this.writeNoEscape(data);
         this.writeNoEscape(" ?>");
      }
   }

   public void writeCData(CharSequence data) throws XMLStreamException {
      if (data == null) {
         throw new XMLStreamException("Data cannot be null");
      } else {
         if (this._isElementOpen) {
            this.closeOpenTag();
         }

         this.writeNoEscape("<![CDATA[");
         this.writeNoEscape(data);
         this.writeNoEscape("]]>");
      }
   }

   public void writeDTD(CharSequence dtd) throws XMLStreamException {
      if (dtd == null) {
         throw new XMLStreamException("DTD cannot be null");
      } else if (this._nesting > 0) {
         throw new XMLStreamException("DOCTYPE declaration (DTD) when not in document root (prolog)");
      } else {
         this.writeNoEscape(dtd);
      }
   }

   public void writeEntityRef(CharSequence name) throws XMLStreamException {
      this.write('&');
      this.writeNoEscape(name);
      this.write(';');
   }

   public void writeStartDocument() throws XMLStreamException {
      this.writeStartDocument((CharSequence)null, (CharSequence)null);
   }

   public void writeStartDocument(CharSequence version) throws XMLStreamException {
      this.writeStartDocument((CharSequence)null, version);
   }

   public void writeStartDocument(CharSequence encoding, CharSequence version) throws XMLStreamException {
      if (this._nesting > 0) {
         throw new XMLStreamException("Not in document root");
      } else {
         this.writeNoEscape("<?xml version=\"");
         if (version != null) {
            this.writeNoEscape(version);
            this.write('"');
         } else {
            this.writeNoEscape("1.0\"");
         }

         if (encoding != null) {
            this.writeNoEscape(" encoding=\"");
            this.writeNoEscape(encoding);
            this.write('"');
         } else if (this._encoding != null) {
            this.writeNoEscape(" encoding=\"");
            this.writeNoEscape(this._encoding);
            this.write('"');
         }

         this.writeNoEscape(" ?>");
      }
   }

   public void writeCharacters(CharSequence text) throws XMLStreamException {
      if (this._isElementOpen) {
         this.closeOpenTag();
      }

      if (text != null) {
         this.writeEscape(text);
      }
   }

   public void writeCharacters(char[] text, int start, int length) throws XMLStreamException {
      this._tmpCharArray.setArray(text, start, length);
      this.writeCharacters(this._tmpCharArray);
   }

   public CharSequence getPrefix(CharSequence uri) throws XMLStreamException {
      return this._namespaces.getPrefix(uri);
   }

   public void setPrefix(CharSequence prefix, CharSequence uri) throws XMLStreamException {
      this._namespaces.setPrefix(prefix, (CharSequence)(uri == null ? this._namespaces._nullNsURI : uri), false);
   }

   public void setDefaultNamespace(CharSequence uri) throws XMLStreamException {
      this.setPrefix(this._namespaces._defaultNsPrefix, uri);
   }

   public Object getProperty(String name) throws IllegalArgumentException {
      if (name.equals("javolution.xml.stream.isRepairingNamespaces")) {
         return new Boolean(this._isRepairingNamespaces);
      } else if (name.equals("javolution.xml.stream.repairingPrefix")) {
         return this._repairingPrefix;
      } else if (name.equals("javolution.xml.stream.automaticEmptyElements")) {
         return new Boolean(this._automaticEmptyElements);
      } else if (name.equals("javolution.xml.stream.noEmptyElementTag")) {
         return new Boolean(this._noEmptyElementTag);
      } else if (name.equals("javolution.xml.stream.indentation")) {
         return this._indentation;
      } else if (name.equals("javolution.xml.stream.lineSeparator")) {
         return this._lineSeparator;
      } else {
         throw new IllegalArgumentException("Property: " + name + " not supported");
      }
   }

   private void writeNewElement(CharSequence prefix, CharSequence localName, CharSequence namespaceURI) throws XMLStreamException {
      if (this._isElementOpen) {
         this.closeOpenTag();
      }

      if (this._indentation != null) {
         this.writeNoEscape(this._lineSeparator);
         this._indentationLevel = this._nesting;

         for(int i = 0; i < this._indentationLevel; ++i) {
            this.writeNoEscape(this._indentation);
         }
      }

      this.write('<');
      this._isElementOpen = true;
      if (++this._nesting >= this._qNames.length) {
         this.resizeElemStack();
      }

      this._namespaces.push();
      TextBuilder qName = this._qNames[this._nesting].clear();
      if (namespaceURI != null && !this._namespaces._defaultNamespace.equals((Object)namespaceURI)) {
         if (this._isRepairingNamespaces) {
            prefix = this.getRepairedPrefix(prefix, namespaceURI);
         } else if (prefix == null) {
            prefix = this.getPrefix(namespaceURI);
            if (prefix == null) {
               throw new XMLStreamException("URI: " + namespaceURI + " not bound and repairing namespaces disabled");
            }
         }

         if (prefix.length() > 0) {
            qName.append(prefix);
            qName.append(':');
         }
      }

      qName.append(localName);
      this.writeNoEscape(qName);
   }

   private void writeAttributeOrNamespace(CharSequence prefix, CharSequence namespaceURI, CharSequence localName, CharSequence value) throws XMLStreamException {
      if (!this._isElementOpen) {
         throw new IllegalStateException("No open start element");
      } else {
         this.write(' ');
         if (namespaceURI != null && !this._namespaces._defaultNamespace.equals((Object)namespaceURI)) {
            if (this._isRepairingNamespaces) {
               prefix = this.getRepairedPrefix(prefix, namespaceURI);
            } else if (prefix == null) {
               prefix = this.getPrefix(namespaceURI);
               if (prefix == null) {
                  throw new XMLStreamException("URI: " + namespaceURI + " not bound and repairing namespaces disabled");
               }
            }

            if (prefix.length() > 0) {
               this.writeNoEscape(prefix);
               this.write(':');
            }
         }

         this.writeNoEscape(localName);
         this.write('=');
         this.write('"');
         this._isAttributeValue = true;
         this.writeEscape(value);
         this._isAttributeValue = false;
         this.write('"');
      }
   }

   private void closeOpenTag() throws XMLStreamException {
      this.writeNamespaces();
      this._isElementOpen = false;
      if (this._isEmptyElement) {
         if (this._noEmptyElementTag) {
            this.write('<');
            this.write('/');
            this.writeNoEscape(this._qNames[this._nesting]);
            this.write('>');
         } else {
            this.write('/');
            this.write('>');
         }

         --this._nesting;
         this._namespaces.pop();
         this._isEmptyElement = false;
      } else {
         this.write('>');
      }

   }

   private void writeNamespaces() throws XMLStreamException {
      int i0 = this._nesting > 1 ? this._namespaces._namespacesCount[this._nesting - 2] : 3;
      int i1 = this._namespaces._namespacesCount[this._nesting - 1];
      int i2 = this._namespaces._namespacesCount[this._nesting];

      for(int i = i0; i < i2; ++i) {
         if (this._isRepairingNamespaces && i < i1 && !this._namespaces._prefixesWritten[i] || i >= i1 && this._namespaces._prefixesWritten[i]) {
            if (this._isRepairingNamespaces) {
               CharArray prefix = this._namespaces.getPrefix(this._namespaces._namespaces[i], i);
               if (this._namespaces._prefixes[i].equals(prefix)) {
                  continue;
               }
            }

            if (this._namespaces._prefixes[i].length() == 0) {
               this.writeAttributeOrNamespace((CharSequence)null, (CharSequence)null, this._namespaces._xmlns, this._namespaces._namespaces[i]);
            } else {
               this.writeAttributeOrNamespace(this._namespaces._xmlns, this._namespaces._xmlnsURI, this._namespaces._prefixes[i], this._namespaces._namespaces[i]);
            }
         }
      }

   }

   private CharSequence getRepairedPrefix(CharSequence prefix, CharSequence namespaceURI) throws XMLStreamException {
      CharArray prefixForURI = this._namespaces.getPrefix(namespaceURI);
      if (prefixForURI == null || prefix != null && !prefixForURI.equals((Object)prefix)) {
         if (prefix == null || prefix.length() == 0) {
            prefix = this._autoPrefix.clear().append(this._repairingPrefix).append(this._autoNSCount++);
         }

         this._namespaces.setPrefix(prefix, namespaceURI, true);
         return prefix;
      } else {
         return prefixForURI;
      }
   }

   private void resizeElemStack() {
      MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
         public void run() {
            int oldLength = XMLStreamWriterImpl.this._qNames.length;
            int newLength = oldLength * 2;
            TextBuilder[] tmp = new TextBuilder[newLength];
            System.arraycopy(XMLStreamWriterImpl.this._qNames, 0, tmp, 0, oldLength);
            XMLStreamWriterImpl.this._qNames = tmp;

            for(int i = oldLength; i < newLength; ++i) {
               XMLStreamWriterImpl.this._qNames[i] = new TextBuilder();
            }

         }
      });
   }

   private final void writeNoEscape(String str) throws XMLStreamException {
      this.write(str, 0, str.length(), false);
   }

   private final void writeNoEscape(TextBuilder tb) throws XMLStreamException {
      this.write(tb, 0, tb.length(), false);
   }

   private final void writeNoEscape(CharSequence csq) throws XMLStreamException {
      this.write(csq, 0, csq.length(), false);
   }

   private final void writeEscape(CharSequence csq) throws XMLStreamException {
      this.write(csq, 0, csq.length(), true);
   }

   private final void write(Object csq, int start, int length, boolean escapeMarkup) throws XMLStreamException {
      if (this._index + length <= 2048) {
         if (csq instanceof String) {
            ((String)csq).getChars(start, start + length, this._buffer, this._index);
         } else if (csq instanceof Text) {
            ((Text)csq).getChars(start, start + length, this._buffer, this._index);
         } else if (csq instanceof TextBuilder) {
            ((TextBuilder)csq).getChars(start, start + length, this._buffer, this._index);
         } else if (csq instanceof CharArray) {
            ((CharArray)csq).getChars(start, start + length, this._buffer, this._index);
         } else {
            getChars((CharSequence)csq, start, start + length, this._buffer, this._index);
         }

         if (escapeMarkup) {
            int end = this._index + length;

            for(int i = this._index; i < end; ++i) {
               char c = this._buffer[i];
               if (c < '?' && this.isEscaped(c)) {
                  this._index = i;
                  this.flushBuffer();
                  this.writeDirectEscapedCharacters(this._buffer, i, end);
                  return;
               }
            }
         }

         this._index += length;
      } else if (length <= 2048) {
         this.flushBuffer();
         this.write(csq, start, length, escapeMarkup);
      } else {
         int half = length >> 1;
         this.write(csq, start, half, escapeMarkup);
         this.write(csq, start + half, length - half, escapeMarkup);
      }

   }

   private static void getChars(CharSequence csq, int start, int end, char[] dest, int destPos) {
      int i = start;

      for(int j = destPos; i < end; dest[j++] = csq.charAt(i++)) {
      }

   }

   private final void writeDirectEscapedCharacters(char[] chars, int start, int end) throws XMLStreamException {
      try {
         int blockStart = start;
         int i = start;

         while(i < end) {
            char c = chars[i++];
            if (c < '?' && this.isEscaped(c)) {
               int blockLength = i - blockStart - 1;
               if (blockLength > 0) {
                  this._writer.write(this._buffer, blockStart, blockLength);
               }

               blockStart = i;
               switch (c) {
                  case '"':
                     this._writer.write("&quot;");
                     break;
                  case '&':
                     this._writer.write("&amp;");
                     break;
                  case '\'':
                     this._writer.write("&apos;");
                     break;
                  case '<':
                     this._writer.write("&lt;");
                     break;
                  case '>':
                     this._writer.write("&gt;");
                     break;
                  default:
                     this._writer.write("&#");
                     this._writer.write((char)(48 + c / 10));
                     this._writer.write((char)(48 + c % 10));
                     this._writer.write(59);
               }
            }
         }

         i = end - blockStart;
         if (i > 0) {
            this._writer.write(this._buffer, blockStart, i);
         }

      } catch (IOException e) {
         throw new XMLStreamException(e);
      }
   }

   private boolean isEscaped(char c) {
      return c < ' ' && this._isAttributeValue || c == '"' && this._isAttributeValue || c == '<' || c == '>' || c == '&';
   }

   private final void write(char c) throws XMLStreamException {
      if (this._index == 2048) {
         this.flushBuffer();
      }

      this._buffer[this._index++] = c;
   }

   private void flushBuffer() throws XMLStreamException {
      try {
         this._writer.write(this._buffer, 0, this._index);
      } catch (IOException e) {
         throw new XMLStreamException(e);
      } finally {
         this._index = 0;
      }

   }
}
