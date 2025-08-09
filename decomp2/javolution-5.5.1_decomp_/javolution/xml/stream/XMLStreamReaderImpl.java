package javolution.xml.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.realtime.MemoryArea;
import javolution.context.LogContext;
import javolution.context.ObjectFactory;
import javolution.io.UTF8StreamReader;
import javolution.lang.Reusable;
import javolution.text.CharArray;
import javolution.xml.sax.Attributes;

public final class XMLStreamReaderImpl implements XMLStreamReader, Reusable {
   static final String[] NAMES_OF_EVENTS = new String[]{"UNDEFINED", "START_ELEMENT", "END_ELEMENT", "PROCESSING_INSTRUCTIONS", "CHARACTERS", "COMMENT", "SPACE", "START_DOCUMENT", "END_DOCUMENT", "ENTITY_REFERENCE", "ATTRIBUTE", "DTD", "CDATA", "NAMESPACE", "NOTATION_DECLARATION", "ENTITY_DECLARATION"};
   static final int READER_BUFFER_CAPACITY = 4096;
   CharArray _prolog;
   ObjectFactory _objectFactory;
   private int _readIndex;
   private int _readCount;
   private char[] _data = new char[8192];
   private int _index;
   private int _depth;
   private CharArray _qName;
   private int _prefixSep;
   private CharArray _attrQName;
   private int _attrPrefixSep;
   private CharArray _attrValue;
   private int _eventType = 7;
   private boolean _isEmpty;
   boolean _charactersPending = false;
   private int _start;
   private int _state = 1;
   private CharArray _text;
   private Reader _reader;
   private final char[] _readBuffer = new char[4096];
   private int _startOffset;
   private final LocationImpl _location = new LocationImpl();
   private final NamespacesImpl _namespaces = new NamespacesImpl();
   private final AttributesImpl _attributes;
   private CharArray[] _elemStack;
   private String _encoding;
   private final EntitiesImpl _entities;
   private final UTF8StreamReader _utf8StreamReader;
   private static final int STATE_CHARACTERS = 1;
   private static final int STATE_MARKUP = 2;
   private static final int STATE_COMMENT = 3;
   private static final int STATE_PI = 4;
   private static final int STATE_CDATA = 5;
   private static final int STATE_OPEN_TAGxREAD_ELEM_NAME = 6;
   private static final int STATE_OPEN_TAGxELEM_NAME_READ = 7;
   private static final int STATE_OPEN_TAGxREAD_ATTR_NAME = 8;
   private static final int STATE_OPEN_TAGxATTR_NAME_READ = 9;
   private static final int STATE_OPEN_TAGxEQUAL_READ = 10;
   private static final int STATE_OPEN_TAGxREAD_ATTR_VALUE_SIMPLE_QUOTE = 11;
   private static final int STATE_OPEN_TAGxREAD_ATTR_VALUE_DOUBLE_QUOTE = 12;
   private static final int STATE_OPEN_TAGxEMPTY_TAG = 13;
   private static final int STATE_CLOSE_TAGxREAD_ELEM_NAME = 14;
   private static final int STATE_CLOSE_TAGxELEM_NAME_READ = 15;
   private static final int STATE_DTD = 16;
   private static final int STATE_DTD_INTERNAL = 17;
   private final Runnable _createSeqLogic;
   private CharArray[] _seqs;
   private int _seqsIndex;
   private int _seqsCapacity;
   private static final CharArray ENCODING = new CharArray("encoding");
   private static final CharArray VERSION = new CharArray("version");
   private static final CharArray STANDALONE = new CharArray("standalone");

   public XMLStreamReaderImpl() {
      this._attributes = new AttributesImpl(this._namespaces);
      this._elemStack = new CharArray[16];
      this._entities = new EntitiesImpl();
      this._utf8StreamReader = new UTF8StreamReader();
      this._createSeqLogic = new Runnable() {
         public void run() {
            if (XMLStreamReaderImpl.this._seqsCapacity >= XMLStreamReaderImpl.this._seqs.length) {
               CharArray[] tmp = new CharArray[XMLStreamReaderImpl.this._seqs.length * 2];
               System.arraycopy(XMLStreamReaderImpl.this._seqs, 0, tmp, 0, XMLStreamReaderImpl.this._seqs.length);
               XMLStreamReaderImpl.this._seqs = tmp;
            }

            CharArray seq = new CharArray();
            XMLStreamReaderImpl.this._seqs[XMLStreamReaderImpl.this._seqsCapacity++] = seq;
         }
      };
      this._seqs = new CharArray[256];
   }

   public void setInput(InputStream in) throws XMLStreamException {
      this.setInput(in, this.detectEncoding(in));
      CharArray prologEncoding = this.getCharacterEncodingScheme();
      if (prologEncoding != null && !prologEncoding.equals(this._encoding) && (!isUTF8(prologEncoding) || !isUTF8(this._encoding))) {
         int startOffset = this._readCount;
         this.reset();
         this._startOffset = startOffset;
         this.setInput(in, prologEncoding.toString());
      }

   }

   private static boolean isUTF8(Object encoding) {
      return encoding.equals("utf-8") || encoding.equals("UTF-8") || encoding.equals("ASCII") || encoding.equals("utf8") || encoding.equals("UTF8");
   }

   public void setInput(InputStream in, String encoding) throws XMLStreamException {
      this._encoding = encoding;
      if (isUTF8(encoding)) {
         this.setInput((Reader)this._utf8StreamReader.setInput(in));
      } else {
         try {
            this.setInput((Reader)(new InputStreamReader(in, encoding)));
         } catch (UnsupportedEncodingException e) {
            throw new XMLStreamException(e);
         }
      }

   }

   public void setInput(Reader reader) throws XMLStreamException {
      if (this._reader != null) {
         throw new IllegalStateException("Reader not closed or reset");
      } else {
         this._reader = reader;

         try {
            int readCount = reader.read(this._readBuffer, this._startOffset, this._readBuffer.length - this._startOffset);
            this._readCount = readCount >= 0 ? readCount + this._startOffset : this._startOffset;
            if (this._readCount >= 5 && this._readBuffer[0] == '<' && this._readBuffer[1] == '?' && this._readBuffer[2] == 'x' && this._readBuffer[3] == 'm' && this._readBuffer[4] == 'l' && this._readBuffer[5] == ' ') {
               this.next();
               this._prolog = this.getPIData();
               this._index = this._prolog.offset() + this._prolog.length();
               this._start = this._index;
               this._eventType = 7;
            }

         } catch (IOException e) {
            throw new XMLStreamException(e);
         }
      }
   }

   public int getDepth() {
      return this._depth;
   }

   public CharArray getQName() {
      if (this._eventType != 1 && this._eventType != 2) {
         throw new IllegalStateException("Not a start element or an end element");
      } else {
         return this._qName;
      }
   }

   public CharArray getQName(int depth) {
      if (depth > this.getDepth()) {
         throw new IllegalArgumentException();
      } else {
         return this._elemStack[depth];
      }
   }

   public Attributes getAttributes() {
      if (this._eventType != 1) {
         throw new IllegalStateException("Not a start element");
      } else {
         return this._attributes;
      }
   }

   public void setEntities(Map entities) {
      this._entities.setEntitiesMapping(entities);
   }

   public String toString() {
      return "XMLStreamReader - State: " + NAMES_OF_EVENTS[this._eventType] + ", Location: " + this._location.toString();
   }

   public int next() throws XMLStreamException {
      if (this._eventType == 1) {
         this._attributes.reset();
         if (this._isEmpty) {
            this._isEmpty = false;
            return this._eventType = 2;
         }
      } else if (this._eventType == 2) {
         this._namespaces.pop();
         CharArray startElem = this._elemStack[this._depth--];
         this._start = this._index = startElem.offset();

         while(this._seqs[--this._seqsIndex] != startElem) {
         }
      }

      label346:
      while(this._readIndex < this._readCount || !this.isEndOfStream()) {
         char c = this._readBuffer[this._readIndex++];
         if (c <= '&') {
            c = c == '&' ? this.replaceEntity() : (c < ' ' ? this.handleEndOfLine(c) : c);
         }

         this._data[this._index++] = c;
         switch (this._state) {
            case 1:
               for(; c != '<'; this._data[this._index++] = c) {
                  if (this._readIndex >= this._readCount && this.isEndOfStream()) {
                     return this._eventType;
                  }

                  c = this._readBuffer[this._readIndex++];
                  if (c <= '&') {
                     c = c == '&' ? this.replaceEntity() : (c < ' ' ? this.handleEndOfLine(c) : c);
                  }
               }

               int length = this._index - this._start - 1;
               if (length > 0) {
                  if (this._charactersPending) {
                     this._text.setArray(this._data, this._text.offset(), this._text.length() + length);
                  } else {
                     this._text = this.newSeq(this._start, length);
                     this._charactersPending = true;
                  }

                  this._start = this._index - 1;
               }

               this._state = 2;
               break;
            case 2:
               if (this._index - this._start == 2) {
                  if (c == '/') {
                     this._start = this._index -= 2;
                     this._state = 14;
                     this._prefixSep = -1;
                     if (this._charactersPending) {
                        this._charactersPending = false;
                        return this._eventType = 4;
                     }
                  } else if (c == '?') {
                     this._start = this._index -= 2;
                     this._state = 4;
                     if (this._charactersPending) {
                        this._charactersPending = false;
                        return this._eventType = 4;
                     }
                  } else if (c != '!') {
                     this._data[this._start] = c;
                     this._index = this._start + 1;
                     this._state = 6;
                     this._prefixSep = -1;
                     if (this._charactersPending) {
                        this._charactersPending = false;
                        return this._eventType = 4;
                     }
                  }
               } else if (this._index - this._start == 4 && this._data[this._start + 1] == '!' && this._data[this._start + 2] == '-' && this._data[this._start + 3] == '-') {
                  this._start = this._index -= 4;
                  this._state = 3;
                  if (this._charactersPending) {
                     this._charactersPending = false;
                     return this._eventType = 4;
                  }
               } else if (this._index - this._start == 9 && this._data[this._start + 1] == '!' && this._data[this._start + 2] == '[' && this._data[this._start + 3] == 'C' && this._data[this._start + 4] == 'D' && this._data[this._start + 5] == 'A' && this._data[this._start + 6] == 'T' && this._data[this._start + 7] == 'A' && this._data[this._start + 8] == '[') {
                  this._start = this._index -= 9;
                  this._state = 5;
               } else if (this._index - this._start == 9 && this._data[this._start + 1] == '!' && this._data[this._start + 2] == 'D' && this._data[this._start + 3] == 'O' && this._data[this._start + 4] == 'C' && this._data[this._start + 5] == 'T' && this._data[this._start + 6] == 'Y' && this._data[this._start + 7] == 'P' && this._data[this._start + 8] == 'E') {
                  this._state = 16;
               }
               break;
            case 3:
               for(; c != '>' || this._index - this._start < 3 || this._data[this._index - 2] != '-' || this._data[this._index - 3] != '-'; this._data[this._index++] = c) {
                  if (this._readIndex >= this._readCount) {
                     this.reloadBuffer();
                  }

                  c = this._readBuffer[this._readIndex++];
                  if (c < ' ') {
                     c = this.handleEndOfLine(c);
                  }
               }

               this._index -= 3;
               this._text = this.newSeq(this._start, this._index - this._start);
               this._state = 1;
               this._index = this._start;
               return this._eventType = 5;
            case 4:
               if (c == '>' && this._index - this._start >= 2 && this._data[this._index - 2] == '?') {
                  this._index -= 2;
                  this._text = this.newSeq(this._start, this._index - this._start);
                  this._state = 1;
                  this._index = this._start;
                  return this._eventType = 3;
               }
               break;
            case 5:
               for(; c != '>' || this._index - this._start < 3 || this._data[this._index - 2] != ']' || this._data[this._index - 3] != ']'; this._data[this._index++] = c) {
                  if (this._readIndex >= this._readCount) {
                     this.reloadBuffer();
                  }

                  c = this._readBuffer[this._readIndex++];
                  if (c < ' ') {
                     c = this.handleEndOfLine(c);
                  }
               }

               this._index -= 3;
               int length = this._index - this._start;
               if (length > 0) {
                  if (this._charactersPending) {
                     this._text.setArray(this._data, this._text.offset(), this._text.length() + length);
                  } else {
                     this._text = this.newSeq(this._start, length);
                     this._charactersPending = true;
                  }
               }

               this._start = this._index;
               this._state = 1;
               break;
            case 6:
               while(true) {
                  if (c < '@') {
                     if (c == '>') {
                        this._qName = this.newSeq(this._start, --this._index - this._start);
                        this._start = this._index;
                        this._state = 1;
                        this.processStartTag();
                        this._isEmpty = false;
                        return this._eventType = 1;
                     }

                     if (c == '/') {
                        this._qName = this.newSeq(this._start, --this._index - this._start);
                        this._start = this._index;
                        this._state = 13;
                        continue label346;
                     }

                     if (c == ':') {
                        this._prefixSep = this._index - 1;
                     } else if (c <= ' ') {
                        this._qName = this.newSeq(this._start, --this._index - this._start);
                        this._state = 7;
                        continue label346;
                     }
                  }

                  if (this._readIndex >= this._readCount) {
                     this.reloadBuffer();
                  }

                  c = this._data[this._index++] = this._readBuffer[this._readIndex++];
               }
            case 7:
               if (c == '>') {
                  this._start = --this._index;
                  this._state = 1;
                  this.processStartTag();
                  this._isEmpty = false;
                  return this._eventType = 1;
               }

               if (c == '/') {
                  this._state = 13;
               } else if (c > ' ') {
                  this._start = this._index - 1;
                  this._attrPrefixSep = -1;
                  this._state = 8;
               }
               break;
            case 8:
               while(true) {
                  if (c < '@') {
                     if (c <= ' ') {
                        this._attrQName = this.newSeq(this._start, --this._index - this._start);
                        this._state = 9;
                        continue label346;
                     }

                     if (c == '=') {
                        this._attrQName = this.newSeq(this._start, --this._index - this._start);
                        this._state = 10;
                        continue label346;
                     }

                     if (c == ':') {
                        this._attrPrefixSep = this._index - 1;
                     }
                  }

                  if (this._readIndex >= this._readCount) {
                     this.reloadBuffer();
                  }

                  this._data[this._index++] = c = this._readBuffer[this._readIndex++];
               }
            case 9:
               if (c == '=') {
                  --this._index;
                  this._state = 10;
               } else if (c > ' ') {
                  throw new XMLStreamException("'=' expected", this._location);
               }
               break;
            case 10:
               if (c == '\'') {
                  this._start = --this._index;
                  this._state = 11;
               } else if (c == '"') {
                  this._start = --this._index;
                  this._state = 12;
               } else if (c > ' ') {
                  throw new XMLStreamException("Quotes expected", this._location);
               }
               break;
            case 11:
               for(; c != '\''; this._data[this._index++] = c) {
                  if (this._readIndex >= this._readCount) {
                     this.reloadBuffer();
                  }

                  c = this._readBuffer[this._readIndex++];
                  if (c == '&') {
                     c = this.replaceEntity();
                  }
               }

               this._attrValue = this.newSeq(this._start, --this._index - this._start);
               this.processAttribute();
               this._state = 7;
               break;
            case 12:
               for(; c != '"'; this._data[this._index++] = c) {
                  if (this._readIndex >= this._readCount) {
                     this.reloadBuffer();
                  }

                  c = this._readBuffer[this._readIndex++];
                  if (c == '&') {
                     c = this.replaceEntity();
                  }
               }

               this._attrValue = this.newSeq(this._start, --this._index - this._start);
               this.processAttribute();
               this._state = 7;
               break;
            case 13:
               if (c == '>') {
                  this._start = --this._index;
                  this._state = 1;
                  this.processStartTag();
                  this._isEmpty = true;
                  return this._eventType = 1;
               }

               throw new XMLStreamException("'>' expected", this._location);
            case 14:
               while(true) {
                  if (c < '@') {
                     if (c == '>') {
                        this._qName = this.newSeq(this._start, --this._index - this._start);
                        this._start = this._index;
                        this._state = 1;
                        this.processEndTag();
                        return this._eventType = 2;
                     }

                     if (c == ':') {
                        this._prefixSep = this._index - 1;
                     } else if (c <= ' ') {
                        this._qName = this.newSeq(this._start, --this._index - this._start);
                        this._state = 15;
                        continue label346;
                     }
                  }

                  if (this._readIndex >= this._readCount) {
                     this.reloadBuffer();
                  }

                  c = this._data[this._index++] = this._readBuffer[this._readIndex++];
               }
            case 15:
               if (c == '>') {
                  this._start = --this._index;
                  this._state = 1;
                  this.processEndTag();
                  return this._eventType = 2;
               }

               if (c > ' ') {
                  throw new XMLStreamException("'>' expected", this._location);
               }
               break;
            case 16:
               if (c == '>') {
                  this._text = this.newSeq(this._start, this._index - this._start);
                  this._index = this._start;
                  this._state = 1;
                  return this._eventType = 11;
               }

               if (c == '[') {
                  this._state = 17;
               }
               break;
            case 17:
               if (c == ']') {
                  this._state = 16;
               }
               break;
            default:
               throw new XMLStreamException("State unknown: " + this._state, this._location);
         }
      }

      return this._eventType;
   }

   private void reloadBuffer() throws XMLStreamException {
      LocationImpl var10000 = this._location;
      var10000._column += this._readIndex;
      var10000 = this._location;
      var10000._charactersRead += this._readIndex;
      this._readIndex = 0;

      try {
         this._readCount = this._reader.read(this._readBuffer, 0, this._readBuffer.length);
         if (this._readCount <= 0 && (this._depth != 0 || this._state != 1)) {
            throw new XMLStreamException("Unexpected end of document", this._location);
         }
      } catch (IOException e) {
         throw new XMLStreamException(e);
      }

      while(this._index + this._readCount >= this._data.length) {
         this.increaseDataBuffer();
      }

   }

   private boolean isEndOfStream() throws XMLStreamException {
      if (this._readIndex >= this._readCount) {
         this.reloadBuffer();
      }

      if (this._readCount <= 0) {
         if (this._eventType == 8) {
            throw new XMLStreamException("End document has already been reached");
         } else {
            int length = this._index - this._start;
            if (length > 0) {
               if (this._charactersPending) {
                  this._text.setArray(this._data, this._text.offset(), this._text.length() + length);
               } else {
                  this._text = this.newSeq(this._start, length);
               }

               this._start = this._index;
               this._eventType = 4;
            } else {
               this._eventType = 8;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private char handleEndOfLine(char c) throws XMLStreamException {
      if (c == '\r') {
         if (this._readIndex >= this._readCount) {
            this.reloadBuffer();
         }

         if (this._readIndex < this._readCount && this._readBuffer[this._readIndex] == '\n') {
            ++this._readIndex;
         }

         c = '\n';
      }

      if (c == '\n') {
         ++this._location._line;
         this._location._column = -this._readIndex;
      } else if (c == 0) {
         throw new XMLStreamException("Illegal XML character U+0000", this._location);
      }

      return c;
   }

   private char replaceEntity() throws XMLStreamException {
      if (this._state != 3 && this._state != 4 && this._state != 5) {
         int start = this._index;
         this._data[this._index++] = '&';

         char c;
         do {
            if (this._readIndex >= this._readCount) {
               this.reloadBuffer();
            }

            c = this._data[this._index++] = this._readBuffer[this._readIndex++];
            if (c == ';') {
               while(start + this._entities.getMaxLength() >= this._data.length) {
                  this.increaseDataBuffer();
               }

               c = (char)this._entities.replaceEntity(this._data, start, this._index - start);
               this._index = start + c;
               if (this._readIndex >= this._readCount) {
                  this.reloadBuffer();
               }

               char c = this._readBuffer[this._readIndex++];
               return c == '&' ? this.replaceEntity() : c;
            }
         } while(c > ' ');

         throw new XMLStreamException("';' expected", this._location);
      } else {
         return '&';
      }
   }

   private void processAttribute() throws XMLStreamException {
      if (this._attrPrefixSep < 0) {
         if (isXMLNS(this._attrQName)) {
            this._namespaces.setPrefix(this._namespaces._defaultNsPrefix, this._attrValue);
         } else {
            this._attributes.addAttribute(this._attrQName, (CharArray)null, this._attrQName, this._attrValue);
         }
      } else {
         int offset = this._attrQName.offset();
         int length = this._attrQName.length();
         CharArray prefix = this.newSeq(offset, this._attrPrefixSep - offset);
         CharArray localName = this.newSeq(this._attrPrefixSep + 1, offset + length - this._attrPrefixSep - 1);
         if (isXMLNS(prefix)) {
            this._namespaces.setPrefix(localName, this._attrValue);
         } else {
            this._attributes.addAttribute(localName, prefix, this._attrQName, this._attrValue);
         }
      }

   }

   private static boolean isXMLNS(CharArray chars) {
      return chars.length() == 5 && chars.charAt(0) == 'x' && chars.charAt(1) == 'm' && chars.charAt(2) == 'l' && chars.charAt(3) == 'n' && chars.charAt(4) == 's';
   }

   private void processEndTag() throws XMLStreamException {
      if (!this._qName.equals(this._elemStack[this._depth])) {
         throw new XMLStreamException("Unexpected end tag for " + this._qName, this._location);
      }
   }

   private void processStartTag() throws XMLStreamException {
      if (++this._depth >= this._elemStack.length) {
         this.increaseStack();
      }

      this._elemStack[this._depth] = this._qName;
      this._namespaces.push();
   }

   public void reset() {
      this._attributes.reset();
      this._attrPrefixSep = 0;
      this._attrQName = null;
      this._attrValue = null;
      this._attrQName = null;
      this._charactersPending = false;
      this._encoding = null;
      this._entities.reset();
      this._eventType = 7;
      this._index = 0;
      this._isEmpty = false;
      this._location.reset();
      this._namespaces.reset();
      this._objectFactory = null;
      this._prolog = null;
      this._readCount = 0;
      this._reader = null;
      this._depth = 0;
      this._readIndex = 0;
      this._seqsIndex = 0;
      this._start = 0;
      this._startOffset = 0;
      this._state = 1;
      this._utf8StreamReader.reset();
   }

   private CharArray newSeq(int offset, int length) {
      CharArray seq = this._seqsIndex < this._seqsCapacity ? this._seqs[this._seqsIndex++] : this.newSeq2();
      return seq.setArray(this._data, offset, length);
   }

   private CharArray newSeq2() {
      MemoryArea.getMemoryArea(this).executeInArea(this._createSeqLogic);
      return this._seqs[this._seqsIndex++];
   }

   private void increaseDataBuffer() {
      MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
         public void run() {
            char[] tmp = new char[XMLStreamReaderImpl.this._data.length * 2];
            LogContext.info((CharSequence)(new CharArray("XMLStreamReaderImpl: Data buffer increased to " + tmp.length)));
            System.arraycopy(XMLStreamReaderImpl.this._data, 0, tmp, 0, XMLStreamReaderImpl.this._data.length);
            XMLStreamReaderImpl.this._data = tmp;
         }
      });
   }

   private void increaseStack() {
      MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
         public void run() {
            CharArray[] tmp = new CharArray[XMLStreamReaderImpl.this._elemStack.length * 2];
            LogContext.info((CharSequence)(new CharArray("XMLStreamReaderImpl: CharArray stack increased to " + tmp.length)));
            System.arraycopy(XMLStreamReaderImpl.this._elemStack, 0, tmp, 0, XMLStreamReaderImpl.this._elemStack.length);
            XMLStreamReaderImpl.this._elemStack = tmp;
         }
      });
   }

   public void require(int type, CharSequence namespaceURI, CharSequence localName) throws XMLStreamException {
      if (this._eventType != type) {
         throw new XMLStreamException("Expected event: " + NAMES_OF_EVENTS[type] + ", found event: " + NAMES_OF_EVENTS[this._eventType]);
      } else if (namespaceURI != null && !this.getNamespaceURI().equals((Object)namespaceURI)) {
         throw new XMLStreamException("Expected namespace URI: " + namespaceURI + ", found: " + this.getNamespaceURI());
      } else if (localName != null && !this.getLocalName().equals((Object)localName)) {
         throw new XMLStreamException("Expected local name: " + localName + ", found: " + this.getLocalName());
      }
   }

   public CharArray getElementText() throws XMLStreamException {
      if (this.getEventType() != 1) {
         throw new XMLStreamException("Parser must be on START_ELEMENT to read next text", this.getLocation());
      } else {
         CharArray text = null;

         for(int eventType = this.next(); eventType != 2; eventType = this.next()) {
            if (eventType == 4) {
               if (text == null) {
                  text = this.getText();
               } else {
                  text.setArray(this._data, text.offset(), text.length() + this.getText().length());
               }
            } else if (eventType != 3 && eventType != 5) {
               if (eventType == 8) {
                  throw new XMLStreamException("Unexpected end of document when reading element text content", this.getLocation());
               }

               if (eventType == 1) {
                  throw new XMLStreamException("Element text content may not contain START_ELEMENT", this.getLocation());
               }

               throw new XMLStreamException("Unexpected event type " + NAMES_OF_EVENTS[eventType], this.getLocation());
            }
         }

         return text != null ? text : this.newSeq(0, 0);
      }
   }

   public Object getProperty(String name) throws IllegalArgumentException {
      if (name.equals("javolution.xml.stream.isCoalescing")) {
         return Boolean.TRUE;
      } else if (name.equals("javolution.xml.stream.entities")) {
         return this._entities.getEntitiesMapping();
      } else {
         throw new IllegalArgumentException("Property: " + name + " not supported");
      }
   }

   public void close() throws XMLStreamException {
      if (this._objectFactory != null) {
         this._objectFactory.recycle(this);
      } else {
         this.reset();
      }

   }

   public int getAttributeCount() {
      if (this._eventType != 1) {
         throw this.illegalState("Not a start element");
      } else {
         return this._attributes.getLength();
      }
   }

   public CharArray getAttributeLocalName(int index) {
      if (this._eventType != 1) {
         throw this.illegalState("Not a start element");
      } else {
         return this._attributes.getLocalName(index);
      }
   }

   public CharArray getAttributeNamespace(int index) {
      if (this._eventType != 1) {
         throw this.illegalState("Not a start element");
      } else {
         CharArray prefix = this._attributes.getPrefix(index);
         return this._namespaces.getNamespaceURINullAllowed(prefix);
      }
   }

   public CharArray getAttributePrefix(int index) {
      if (this._eventType != 1) {
         throw this.illegalState("Not a start element");
      } else {
         return this._attributes.getPrefix(index);
      }
   }

   public CharArray getAttributeType(int index) {
      if (this._eventType != 1) {
         throw this.illegalState("Not a start element");
      } else {
         return this._attributes.getType(index);
      }
   }

   public CharArray getAttributeValue(CharSequence uri, CharSequence localName) {
      if (this._eventType != 1) {
         throw this.illegalState("Not a start element");
      } else {
         return uri == null ? this._attributes.getValue(localName) : this._attributes.getValue(uri, localName);
      }
   }

   public CharArray getAttributeValue(int index) {
      if (this._eventType != 1) {
         throw this.illegalState("Not a start element");
      } else {
         return this._attributes.getValue(index);
      }
   }

   public CharArray getCharacterEncodingScheme() {
      return this.readPrologAttribute(ENCODING);
   }

   public String getEncoding() {
      return this._encoding;
   }

   public int getEventType() {
      return this._eventType;
   }

   public CharArray getLocalName() {
      if (this._eventType != 1 && this._eventType != 2) {
         throw this.illegalState("Not a start or end element");
      } else if (this._prefixSep < 0) {
         return this._qName;
      } else {
         CharArray localName = this.newSeq(this._prefixSep + 1, this._qName.offset() + this._qName.length() - this._prefixSep - 1);
         return localName;
      }
   }

   public Location getLocation() {
      return this._location;
   }

   public int getNamespaceCount() {
      if (this._eventType != 1 && this._eventType != 2) {
         throw this.illegalState("Not a start or end element");
      } else {
         return this._namespaces._namespacesCount[this._depth];
      }
   }

   public CharArray getNamespacePrefix(int index) {
      if (this._eventType != 1 && this._eventType != 2) {
         throw this.illegalState("Not a start or end element");
      } else {
         return this._namespaces._prefixes[index];
      }
   }

   public CharArray getNamespaceURI(CharSequence prefix) {
      if (this._eventType != 1 && this._eventType != 2) {
         throw this.illegalState("Not a start or end element");
      } else {
         return this._namespaces.getNamespaceURI(prefix);
      }
   }

   public CharArray getNamespaceURI(int index) {
      if (this._eventType != 1 && this._eventType != 2) {
         throw this.illegalState("Not a start or end element");
      } else {
         return this._namespaces._namespaces[index];
      }
   }

   public NamespaceContext getNamespaceContext() {
      return this._namespaces;
   }

   public CharArray getNamespaceURI() {
      return this._namespaces.getNamespaceURINullAllowed(this.getPrefix());
   }

   public CharArray getPrefix() {
      if (this._eventType != 1 && this._eventType != 2) {
         throw this.illegalState("Not a start or end element");
      } else if (this._prefixSep < 0) {
         return null;
      } else {
         int offset = this._qName.offset();
         CharArray prefix = this.newSeq(offset, this._prefixSep - offset);
         return prefix;
      }
   }

   public CharArray getPIData() {
      if (this._eventType != 3) {
         throw this.illegalState("Not a processing instruction");
      } else {
         int offset = this._text.indexOf(' ') + this._text.offset() + 1;
         CharArray piData = this.newSeq(offset, this._text.length() - offset);
         return piData;
      }
   }

   public CharArray getPITarget() {
      if (this._eventType != 3) {
         throw this.illegalState("Not a processing instruction");
      } else {
         CharArray piTarget = this.newSeq(this._text.offset(), this._text.indexOf(' ') + this._text.offset());
         return piTarget;
      }
   }

   public CharArray getText() {
      if (this._eventType != 4 && this._eventType != 5 && this._eventType != 11) {
         throw this.illegalState("Not a text event");
      } else {
         return this._text;
      }
   }

   public char[] getTextCharacters() {
      return this.getText().array();
   }

   public int getTextCharacters(int sourceStart, char[] target, int targetStart, int length) throws XMLStreamException {
      CharArray text = this.getText();
      int copyLength = Math.min(length, text.length());
      System.arraycopy(text.array(), sourceStart + text.offset(), target, targetStart, copyLength);
      return copyLength;
   }

   public int getTextLength() {
      return this.getText().length();
   }

   public int getTextStart() {
      return this.getText().offset();
   }

   public CharArray getVersion() {
      return this.readPrologAttribute(VERSION);
   }

   public boolean isStandalone() {
      CharArray standalone = this.readPrologAttribute(STANDALONE);
      return standalone != null ? standalone.equals("no") : true;
   }

   public boolean standaloneSet() {
      return this.readPrologAttribute(STANDALONE) != null;
   }

   public boolean hasName() {
      return this._eventType == 1 || this._eventType == 2;
   }

   public boolean hasNext() throws XMLStreamException {
      return this._eventType != 8;
   }

   public boolean hasText() {
      return (this._eventType == 4 || this._eventType == 5 || this._eventType == 11) && this._text.length() > 0;
   }

   public boolean isAttributeSpecified(int index) {
      if (this._eventType != 1) {
         throw new IllegalStateException("Not a start element");
      } else {
         return this._attributes.getValue(index) != null;
      }
   }

   public boolean isCharacters() {
      return this._eventType == 4;
   }

   public boolean isEndElement() {
      return this._eventType == 2;
   }

   public boolean isStartElement() {
      return this._eventType == 1;
   }

   public boolean isWhiteSpace() {
      if (this.isCharacters()) {
         char[] chars = this._text.array();
         int i = this._text.offset();
         int end = this._text.offset() + this._text.length();

         while(i < end) {
            if (!isWhiteSpace(chars[i++])) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private static boolean isWhiteSpace(char c) {
      return c == ' ' || c == '\t' || c == '\r' || c == '\n';
   }

   public int nextTag() throws XMLStreamException {
      int eventType;
      for(eventType = this.next(); eventType == 5 || eventType == 3 || eventType == 11 || eventType == 4 && this.isWhiteSpace(); eventType = this.next()) {
      }

      if (eventType != 1 && eventType != 2) {
         throw new XMLStreamException("Tag expected (but found " + NAMES_OF_EVENTS[this._eventType] + ")");
      } else {
         return eventType;
      }
   }

   private IllegalStateException illegalState(String msg) {
      return new IllegalStateException(msg + " (" + NAMES_OF_EVENTS[this._eventType] + ")");
   }

   private String detectEncoding(InputStream input) throws XMLStreamException {
      int byte0;
      try {
         byte0 = input.read();
      } catch (IOException e) {
         throw new XMLStreamException(e);
      }

      if (byte0 == -1) {
         throw new XMLStreamException("Premature End-Of-File");
      } else if (byte0 == 60) {
         this._readBuffer[this._startOffset++] = '<';
         return "UTF-8";
      } else {
         int byte1;
         try {
            byte1 = input.read();
         } catch (IOException e) {
            throw new XMLStreamException(e);
         }

         if (byte1 == -1) {
            throw new XMLStreamException("Premature End-Of-File");
         } else if (byte0 == 0 && byte1 == 60) {
            this._readBuffer[this._startOffset++] = '<';
            return "UTF-16BE";
         } else if (byte0 == 60 && byte1 == 0) {
            this._readBuffer[this._startOffset++] = '<';
            return "UTF-16LE";
         } else if (byte0 == 255 && byte1 == 254) {
            return "UTF-16";
         } else if (byte0 == 254 && byte1 == 255) {
            return "UTF-16";
         } else {
            this._readBuffer[this._startOffset++] = (char)byte0;
            this._readBuffer[this._startOffset++] = (char)byte1;
            return "UTF-8";
         }
      }
   }

   private final CharArray readPrologAttribute(CharSequence name) {
      if (this._prolog == null) {
         return null;
      } else {
         int READ_EQUAL = 0;
         int READ_QUOTE = 1;
         int VALUE_SIMPLE_QUOTE = 2;
         int VALUE_DOUBLE_QUOTE = 3;
         int i = this._prolog.indexOf(name);
         if (i >= 0) {
            i += this._prolog.offset();
            int maxIndex = this._prolog.offset() + this._prolog.length();
            i += name.length();
            int state = 0;
            int valueOffset = 0;

            while(i < maxIndex) {
               char c = this._prolog.array()[i++];
               switch (state) {
                  case 0:
                     if (c == '=') {
                        state = 1;
                     }
                     break;
                  case 1:
                     if (c == '"') {
                        state = 3;
                        valueOffset = i;
                     } else if (c == '\'') {
                        state = 2;
                        valueOffset = i;
                     }
                     break;
                  case 2:
                     if (c == '\'') {
                        return this.newSeq(valueOffset, i - valueOffset - 1);
                     }
                     break;
                  case 3:
                     if (c == '"') {
                        return this.newSeq(valueOffset, i - valueOffset - 1);
                     }
               }
            }
         }

         return null;
      }
   }

   private final class LocationImpl implements Location, Reusable {
      int _column;
      int _line;
      int _charactersRead;

      private LocationImpl() {
      }

      public int getLineNumber() {
         return this._line + 1;
      }

      public int getColumnNumber() {
         return this._column + XMLStreamReaderImpl.this._readIndex;
      }

      public int getCharacterOffset() {
         return this._charactersRead + XMLStreamReaderImpl.this._readIndex;
      }

      public String getPublicId() {
         return null;
      }

      public String getSystemId() {
         return null;
      }

      public String toString() {
         return "Line " + this.getLineNumber() + ", Column " + this.getColumnNumber();
      }

      public void reset() {
         this._line = 0;
         this._column = 0;
         this._charactersRead = 0;
      }
   }
}
