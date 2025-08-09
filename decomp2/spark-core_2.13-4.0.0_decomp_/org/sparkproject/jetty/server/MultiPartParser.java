package org.sparkproject.jetty.server;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.HttpTokens;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.SearchPattern;
import org.sparkproject.jetty.util.Utf8StringBuilder;

public class MultiPartParser {
   public static final Logger LOG = LoggerFactory.getLogger(MultiPartParser.class);
   private static final EnumSet __delimiterStates;
   private static final int MAX_HEADER_LINE_LENGTH = 998;
   private final boolean debugEnabled;
   private final Handler _handler;
   private final SearchPattern _delimiterSearch;
   private String _fieldName;
   private String _fieldValue;
   private State _state;
   private FieldState _fieldState;
   private int _partialBoundary;
   private boolean _cr;
   private ByteBuffer _patternBuffer;
   private final Utf8StringBuilder _string;
   private int _length;
   private int _totalHeaderLineLength;

   public MultiPartParser(Handler handler, String boundary) {
      this.debugEnabled = LOG.isDebugEnabled();
      this._state = MultiPartParser.State.PREAMBLE;
      this._fieldState = MultiPartParser.FieldState.FIELD;
      this._partialBoundary = 2;
      this._string = new Utf8StringBuilder();
      this._totalHeaderLineLength = -1;
      this._handler = handler;
      String delimiter = "\r\n--" + boundary;
      this._patternBuffer = ByteBuffer.wrap(delimiter.getBytes(StandardCharsets.US_ASCII));
      this._delimiterSearch = SearchPattern.compile(this._patternBuffer.array());
   }

   public void reset() {
      this._state = MultiPartParser.State.PREAMBLE;
      this._fieldState = MultiPartParser.FieldState.FIELD;
      this._partialBoundary = 2;
   }

   public Handler getHandler() {
      return this._handler;
   }

   public State getState() {
      return this._state;
   }

   public boolean isState(State state) {
      return this._state == state;
   }

   private static boolean hasNextByte(ByteBuffer buffer) {
      return BufferUtil.hasContent(buffer);
   }

   private HttpTokens.Token next(ByteBuffer buffer) {
      byte ch = buffer.get();
      HttpTokens.Token t = HttpTokens.TOKENS[255 & ch];
      switch (t.getType()) {
         case CNTL:
            throw new IllegalCharacterException(this._state, t, buffer);
         case LF:
            this._cr = false;
            break;
         case CR:
            if (this._cr) {
               throw new BadMessageException("Bad EOL");
            }

            this._cr = true;
            return null;
         case ALPHA:
         case DIGIT:
         case TCHAR:
         case VCHAR:
         case HTAB:
         case SPACE:
         case OTEXT:
         case COLON:
            if (this._cr) {
               throw new BadMessageException("Bad EOL");
            }
      }

      return t;
   }

   private void setString(String s) {
      this._string.reset();
      this._string.append(s);
      this._length = s.length();
   }

   private String takeString() {
      String s = this._string.toString();
      if (s.length() > this._length) {
         s = s.substring(0, this._length);
      }

      this._string.reset();
      this._length = -1;
      return s;
   }

   public boolean parse(ByteBuffer buffer, boolean last) {
      boolean handle = false;

      while(!handle && BufferUtil.hasContent(buffer)) {
         switch (this._state.ordinal()) {
            case 0:
               this.parsePreamble(buffer);
               break;
            case 1:
            case 2:
            case 3:
               this.parseDelimiter(buffer);
               break;
            case 4:
               handle = this.parseMimePartHeaders(buffer);
               break;
            case 5:
            case 6:
               handle = this.parseOctetContent(buffer);
               break;
            case 7:
               BufferUtil.clear(buffer);
               break;
            case 8:
               handle = true;
               break;
            default:
               throw new IllegalStateException();
         }
      }

      if (last && BufferUtil.isEmpty(buffer)) {
         if (this._state == MultiPartParser.State.EPILOGUE) {
            this._state = MultiPartParser.State.END;
            if (LOG.isDebugEnabled()) {
               LOG.debug("messageComplete {}", this);
            }

            return this._handler.messageComplete();
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("earlyEOF {}", this);
            }

            this._handler.earlyEOF();
            return true;
         }
      } else {
         return handle;
      }
   }

   private void parsePreamble(ByteBuffer buffer) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("parsePreamble({})", BufferUtil.toDetailString(buffer));
      }

      if (this._partialBoundary > 0) {
         int partial = this._delimiterSearch.startsWith(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining(), this._partialBoundary);
         if (partial > 0) {
            if (partial == this._delimiterSearch.getLength()) {
               buffer.position(buffer.position() + partial - this._partialBoundary);
               this._partialBoundary = 0;
               this.setState(MultiPartParser.State.DELIMITER);
               return;
            }

            this._partialBoundary = partial;
            BufferUtil.clear(buffer);
            return;
         }

         this._partialBoundary = 0;
      }

      int delimiter = this._delimiterSearch.match(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
      if (delimiter >= 0) {
         buffer.position(delimiter - buffer.arrayOffset() + this._delimiterSearch.getLength());
         this.setState(MultiPartParser.State.DELIMITER);
      } else {
         this._partialBoundary = this._delimiterSearch.endsWith(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
         BufferUtil.clear(buffer);
      }
   }

   private void parseDelimiter(ByteBuffer buffer) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("parseDelimiter({})", BufferUtil.toDetailString(buffer));
      }

      while(__delimiterStates.contains(this._state) && hasNextByte(buffer)) {
         HttpTokens.Token t = this.next(buffer);
         if (t == null) {
            return;
         }

         if (t.getType() == HttpTokens.Type.LF) {
            this.setState(MultiPartParser.State.BODY_PART);
            if (LOG.isDebugEnabled()) {
               LOG.debug("startPart {}", this);
            }

            this._handler.startPart();
            return;
         }

         switch (this._state.ordinal()) {
            case 1:
               if (t.getChar() == '-') {
                  this.setState(MultiPartParser.State.DELIMITER_CLOSE);
               } else {
                  this.setState(MultiPartParser.State.DELIMITER_PADDING);
               }
            case 2:
            default:
               break;
            case 3:
               if (t.getChar() == '-') {
                  this.setState(MultiPartParser.State.EPILOGUE);
                  return;
               }

               this.setState(MultiPartParser.State.DELIMITER_PADDING);
         }
      }

   }

   protected boolean parseMimePartHeaders(ByteBuffer buffer) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("parseMimePartHeaders({})", BufferUtil.toDetailString(buffer));
      }

      while(this._state == MultiPartParser.State.BODY_PART && hasNextByte(buffer)) {
         HttpTokens.Token t = this.next(buffer);
         if (t == null) {
            break;
         }

         if (t.getType() != HttpTokens.Type.LF) {
            ++this._totalHeaderLineLength;
         }

         if (this._totalHeaderLineLength > 998) {
            throw new IllegalStateException("Header Line Exceeded Max Length");
         }

         switch (this._fieldState.ordinal()) {
            case 0:
               switch (t.getType()) {
                  case LF:
                     this.handleField();
                     this.setState(MultiPartParser.State.FIRST_OCTETS);
                     this._partialBoundary = 2;
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("headerComplete {}", this);
                     }

                     if (this._handler.headerComplete()) {
                        return true;
                     }
                     continue;
                  case CR:
                  case VCHAR:
                  default:
                     throw new IllegalCharacterException(this._state, t, buffer);
                  case ALPHA:
                  case DIGIT:
                  case TCHAR:
                     this.handleField();
                     this.setState(MultiPartParser.FieldState.IN_NAME);
                     this._string.reset();
                     this._string.append(t.getChar());
                     this._length = 1;
                     continue;
                  case HTAB:
                  case SPACE:
                     if (this._fieldName == null) {
                        throw new IllegalStateException("First field folded");
                     }

                     if (this._fieldValue == null) {
                        this._string.reset();
                        this._length = 0;
                     } else {
                        this.setString(this._fieldValue);
                        this._string.append(' ');
                        ++this._length;
                        this._fieldValue = null;
                     }

                     this.setState(MultiPartParser.FieldState.VALUE);
                     continue;
               }
            case 1:
               switch (t.getType()) {
                  case LF:
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Line Feed in Name {}", this);
                     }

                     this.handleField();
                     this.setState(MultiPartParser.FieldState.FIELD);
                     continue;
                  case CR:
                  case VCHAR:
                  case HTAB:
                  case OTEXT:
                  default:
                     throw new IllegalCharacterException(this._state, t, buffer);
                  case ALPHA:
                  case DIGIT:
                  case TCHAR:
                     this._string.append(t.getChar());
                     this._length = this._string.length();
                     continue;
                  case SPACE:
                     this.setState(MultiPartParser.FieldState.AFTER_NAME);
                     continue;
                  case COLON:
                     this._fieldName = this.takeString();
                     this._length = -1;
                     this.setState(MultiPartParser.FieldState.VALUE);
                     continue;
               }
            case 2:
               switch (t.getType()) {
                  case LF:
                     this._fieldName = this.takeString();
                     this._string.reset();
                     this._fieldValue = "";
                     this._length = -1;
                  case SPACE:
                     continue;
                  case COLON:
                     this._fieldName = this.takeString();
                     this._length = -1;
                     this.setState(MultiPartParser.FieldState.VALUE);
                     continue;
                  default:
                     throw new IllegalCharacterException(this._state, t, buffer);
               }
            case 3:
               switch (t.getType()) {
                  case LF:
                     this._string.reset();
                     this._fieldValue = "";
                     this._length = -1;
                     this.setState(MultiPartParser.FieldState.FIELD);
                     continue;
                  case CR:
                  default:
                     throw new IllegalCharacterException(this._state, t, buffer);
                  case ALPHA:
                  case DIGIT:
                  case TCHAR:
                  case VCHAR:
                  case OTEXT:
                  case COLON:
                     this._string.append(t.getByte());
                     this._length = this._string.length();
                     this.setState(MultiPartParser.FieldState.IN_VALUE);
                  case HTAB:
                  case SPACE:
                     continue;
               }
            case 4:
               switch (t.getType()) {
                  case LF:
                     if (this._length > 0) {
                        this._fieldValue = this.takeString();
                        this._length = -1;
                        this._totalHeaderLineLength = -1;
                     }

                     this.setState(MultiPartParser.FieldState.FIELD);
                     continue;
                  case CR:
                  default:
                     throw new IllegalCharacterException(this._state, t, buffer);
                  case ALPHA:
                  case DIGIT:
                  case TCHAR:
                  case VCHAR:
                  case OTEXT:
                  case COLON:
                     this._string.append(t.getByte());
                     this._length = this._string.length();
                     continue;
                  case HTAB:
                  case SPACE:
                     this._string.append(' ');
                     continue;
               }
            default:
               throw new IllegalStateException(this._state.toString());
         }
      }

      return false;
   }

   private void handleField() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("parsedField:  _fieldName={} _fieldValue={} {}", new Object[]{this._fieldName, this._fieldValue, this});
      }

      if (this._fieldName != null && this._fieldValue != null) {
         this._handler.parsedField(this._fieldName, this._fieldValue);
      }

      this._fieldName = this._fieldValue = null;
   }

   protected boolean parseOctetContent(ByteBuffer buffer) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("parseOctetContent({})", BufferUtil.toDetailString(buffer));
      }

      if (this._partialBoundary > 0) {
         int partial = this._delimiterSearch.startsWith(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining(), this._partialBoundary);
         if (partial > 0) {
            if (partial == this._delimiterSearch.getLength()) {
               buffer.position(buffer.position() + this._delimiterSearch.getLength() - this._partialBoundary);
               this.setState(MultiPartParser.State.DELIMITER);
               this._partialBoundary = 0;
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Content={}, Last={} {}", new Object[]{BufferUtil.toDetailString(BufferUtil.EMPTY_BUFFER), true, this});
               }

               return this._handler.content(BufferUtil.EMPTY_BUFFER, true);
            }

            this._partialBoundary = partial;
            BufferUtil.clear(buffer);
            return false;
         }

         ByteBuffer content = this._patternBuffer.slice();
         if (this._state == MultiPartParser.State.FIRST_OCTETS) {
            this.setState(MultiPartParser.State.OCTETS);
            content.position(2);
         }

         content.limit(this._partialBoundary);
         this._partialBoundary = 0;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Content={}, Last={} {}", new Object[]{BufferUtil.toDetailString(content), false, this});
         }

         if (this._handler.content(content, false)) {
            return true;
         }
      }

      int delimiter = this._delimiterSearch.match(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
      if (delimiter >= 0) {
         ByteBuffer content = buffer.slice();
         content.limit(delimiter - buffer.arrayOffset() - buffer.position());
         buffer.position(delimiter - buffer.arrayOffset() + this._delimiterSearch.getLength());
         this.setState(MultiPartParser.State.DELIMITER);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Content={}, Last={} {}", new Object[]{BufferUtil.toDetailString(content), true, this});
         }

         return this._handler.content(content, true);
      } else {
         this._partialBoundary = this._delimiterSearch.endsWith(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
         if (this._partialBoundary > 0) {
            ByteBuffer content = buffer.slice();
            content.limit(content.limit() - this._partialBoundary);
            if (LOG.isDebugEnabled()) {
               LOG.debug("Content={}, Last={} {}", new Object[]{BufferUtil.toDetailString(content), false, this});
            }

            BufferUtil.clear(buffer);
            return this._handler.content(content, false);
         } else {
            ByteBuffer content = buffer.slice();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Content={}, Last={} {}", new Object[]{BufferUtil.toDetailString(content), false, this});
            }

            BufferUtil.clear(buffer);
            return this._handler.content(content, false);
         }
      }
   }

   private void setState(State state) {
      if (this.debugEnabled) {
         LOG.debug("{} --> {}", this._state, state);
      }

      this._state = state;
   }

   private void setState(FieldState state) {
      if (this.debugEnabled) {
         LOG.debug("{}:{} --> {}", new Object[]{this._state, this._fieldState, state});
      }

      this._fieldState = state;
   }

   public String toString() {
      return String.format("%s{s=%s}", this.getClass().getSimpleName(), this._state);
   }

   static {
      __delimiterStates = EnumSet.of(MultiPartParser.State.DELIMITER, MultiPartParser.State.DELIMITER_CLOSE, MultiPartParser.State.DELIMITER_PADDING);
   }

   public static enum FieldState {
      FIELD,
      IN_NAME,
      AFTER_NAME,
      VALUE,
      IN_VALUE;

      // $FF: synthetic method
      private static FieldState[] $values() {
         return new FieldState[]{FIELD, IN_NAME, AFTER_NAME, VALUE, IN_VALUE};
      }
   }

   public static enum State {
      PREAMBLE,
      DELIMITER,
      DELIMITER_PADDING,
      DELIMITER_CLOSE,
      BODY_PART,
      FIRST_OCTETS,
      OCTETS,
      EPILOGUE,
      END;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{PREAMBLE, DELIMITER, DELIMITER_PADDING, DELIMITER_CLOSE, BODY_PART, FIRST_OCTETS, OCTETS, EPILOGUE, END};
      }
   }

   public interface Handler {
      default void startPart() {
      }

      default void parsedField(String name, String value) {
      }

      default boolean headerComplete() {
         return false;
      }

      default boolean content(ByteBuffer item, boolean last) {
         return false;
      }

      default boolean messageComplete() {
         return false;
      }

      default void earlyEOF() {
      }
   }

   private static class IllegalCharacterException extends BadMessageException {
      private IllegalCharacterException(State state, HttpTokens.Token token, ByteBuffer buffer) {
         super(400, String.format("Illegal character %s", token));
         if (MultiPartParser.LOG.isDebugEnabled()) {
            MultiPartParser.LOG.debug(String.format("Illegal character %s in state=%s for buffer %s", token, state, BufferUtil.toDetailString(buffer)));
         }

      }
   }
}
