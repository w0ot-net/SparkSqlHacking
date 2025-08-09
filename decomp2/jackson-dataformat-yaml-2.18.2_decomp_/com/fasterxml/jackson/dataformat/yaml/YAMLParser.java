package com.fasterxml.jackson.dataformat.yaml;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.JsonParser.NumberTypeFP;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigInteger;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.MarkedYAMLException;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.events.AliasEvent;
import org.yaml.snakeyaml.events.CollectionStartEvent;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.NodeEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.Event.ID;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;
import org.yaml.snakeyaml.resolver.Resolver;

public class YAMLParser extends ParserBase {
   protected ObjectCodec _objectCodec;
   protected int _formatFeatures;
   protected boolean _cfgEmptyStringsToNull;
   protected final Reader _reader;
   protected final ParserImpl _yamlParser;
   protected final Resolver _yamlResolver;
   protected Event _lastEvent;
   protected Event _lastTagEvent;
   protected String _textValue;
   protected String _cleanedTextValue;
   protected String _currentFieldName;
   protected boolean _currentIsAlias;
   protected String _currentAnchor;

   /** @deprecated */
   @Deprecated
   public YAMLParser(IOContext ctxt, BufferRecycler br, int parserFeatures, int formatFeatures, ObjectCodec codec, Reader reader) {
      this(ctxt, parserFeatures, formatFeatures, (LoaderOptions)null, (ObjectCodec)codec, (Reader)reader);
   }

   public YAMLParser(IOContext ctxt, int parserFeatures, int formatFeatures, LoaderOptions loaderOptions, ObjectCodec codec, Reader reader) {
      this(ctxt, parserFeatures, formatFeatures, codec, reader, new ParserImpl(new StreamReader(reader), loaderOptions == null ? new LoaderOptions() : loaderOptions));
   }

   protected YAMLParser(IOContext ctxt, int parserFeatures, int formatFeatures, ObjectCodec codec, Reader reader, ParserImpl yamlParser) {
      super(ctxt, parserFeatures);
      this._yamlResolver = new Resolver();
      this._objectCodec = codec;
      this._formatFeatures = formatFeatures;
      this._reader = reader;
      this._yamlParser = yamlParser;
      this._cfgEmptyStringsToNull = YAMLParser.Feature.EMPTY_STRING_AS_NULL.enabledIn(formatFeatures);
   }

   public ObjectCodec getCodec() {
      return this._objectCodec;
   }

   public void setCodec(ObjectCodec c) {
      this._objectCodec = c;
   }

   public boolean isCurrentAlias() {
      return this._currentIsAlias;
   }

   /** @deprecated */
   @Deprecated
   public String getCurrentAnchor() {
      return this._currentAnchor;
   }

   public Version version() {
      return PackageVersion.VERSION;
   }

   public boolean requiresCustomCodec() {
      return false;
   }

   public boolean canReadObjectId() {
      return true;
   }

   public boolean canReadTypeId() {
      return true;
   }

   public JacksonFeatureSet getReadCapabilities() {
      return DEFAULT_READ_CAPABILITIES;
   }

   protected void _closeInput() throws IOException {
      if (this._reader != null && (this._ioContext.isResourceManaged() || this.isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.AUTO_CLOSE_SOURCE))) {
         this._reader.close();
      }

   }

   public int getFormatFeatures() {
      return this._formatFeatures;
   }

   public JsonParser overrideFormatFeatures(int values, int mask) {
      this._formatFeatures = this._formatFeatures & ~mask | values & mask;
      this._cfgEmptyStringsToNull = YAMLParser.Feature.EMPTY_STRING_AS_NULL.enabledIn(this._formatFeatures);
      return this;
   }

   public JsonParser enable(Feature f) {
      this._formatFeatures |= f.getMask();
      this._cfgEmptyStringsToNull = YAMLParser.Feature.EMPTY_STRING_AS_NULL.enabledIn(this._formatFeatures);
      return this;
   }

   public JsonParser disable(Feature f) {
      this._formatFeatures &= ~f.getMask();
      this._cfgEmptyStringsToNull = YAMLParser.Feature.EMPTY_STRING_AS_NULL.enabledIn(this._formatFeatures);
      return this;
   }

   public JsonParser configure(Feature f, boolean state) {
      if (state) {
         this.enable(f);
      } else {
         this.disable(f);
      }

      return this;
   }

   public boolean isEnabled(Feature f) {
      return (this._formatFeatures & f.getMask()) != 0;
   }

   public JsonLocation currentLocation() {
      return this._lastEvent == null ? JsonLocation.NA : this._locationFor(this._lastEvent.getEndMark());
   }

   public JsonLocation currentTokenLocation() {
      return this._lastEvent == null ? JsonLocation.NA : this._locationFor(this._lastEvent.getStartMark());
   }

   /** @deprecated */
   @Deprecated
   public JsonLocation getCurrentLocation() {
      return this.currentLocation();
   }

   /** @deprecated */
   @Deprecated
   public JsonLocation getTokenLocation() {
      return this.currentTokenLocation();
   }

   protected JsonLocation _locationFor(Mark m) {
      return m == null ? new JsonLocation(this._ioContext.contentReference(), -1L, -1, -1) : new JsonLocation(this._ioContext.contentReference(), (long)m.getIndex(), m.getLine() + 1, m.getColumn() + 1);
   }

   public JsonToken nextToken() throws IOException {
      this._currentIsAlias = false;
      this._binaryValue = null;
      if (this._closed) {
         return null;
      } else {
         while(true) {
            Event evt;
            try {
               evt = this.getEvent();
            } catch (YAMLException e) {
               if (e instanceof MarkedYAMLException) {
                  throw com.fasterxml.jackson.dataformat.yaml.snakeyaml.error.MarkedYAMLException.from(this, (MarkedYAMLException)e);
               }

               throw new JacksonYAMLParseException(this, e.getMessage(), e);
            } catch (NumberFormatException e) {
               throw this._constructError(String.format("Malformed Number token: failed to tokenize due to (%s): %s", e.getClass().getName(), e.getMessage()), e);
            }

            if (evt == null) {
               this._currentAnchor = null;
               this._lastTagEvent = null;
               return this._updateTokenToNull();
            }

            this._lastEvent = evt;
            if (this._parsingContext.inObject()) {
               if (this._currToken != JsonToken.FIELD_NAME) {
                  if (!evt.is(ID.Scalar)) {
                     this._currentAnchor = null;
                     this._lastTagEvent = null;
                     if (evt.is(ID.MappingEnd)) {
                        if (!this._parsingContext.inObject()) {
                           this._reportMismatchedEndMarker(125, ']');
                        }

                        this._parsingContext = this._parsingContext.getParent();
                        return this._updateToken(JsonToken.END_OBJECT);
                     }

                     this._reportError("Expected a field name (Scalar value in YAML), got this instead: " + evt);
                  }

                  ScalarEvent scalar = (ScalarEvent)evt;
                  String newAnchor = scalar.getAnchor();
                  boolean firstEntry = this._currToken == JsonToken.START_OBJECT;
                  if (newAnchor != null || !firstEntry) {
                     this._currentAnchor = scalar.getAnchor();
                  }

                  if (!firstEntry) {
                     this._lastTagEvent = evt;
                  }

                  String name = scalar.getValue();
                  this._currentFieldName = name;
                  this._parsingContext.setCurrentName(name);
                  return this._updateToken(JsonToken.FIELD_NAME);
               }
            } else if (this._parsingContext.inArray()) {
               this._parsingContext.expectComma();
            }

            this._currentAnchor = null;
            this._lastTagEvent = evt;
            if (evt.is(ID.Scalar)) {
               return this._updateToken(this._decodeScalar((ScalarEvent)evt));
            }

            if (evt.is(ID.MappingStart)) {
               Mark m = evt.getStartMark();
               MappingStartEvent map = (MappingStartEvent)evt;
               this._currentAnchor = map.getAnchor();
               this.createChildObjectContext(m.getLine(), m.getColumn());
               return this._updateToken(JsonToken.START_OBJECT);
            }

            if (evt.is(ID.MappingEnd)) {
               this._reportError("Not expecting END_OBJECT but a value");
            }

            if (evt.is(ID.SequenceStart)) {
               Mark m = evt.getStartMark();
               this._currentAnchor = ((NodeEvent)evt).getAnchor();
               this.createChildArrayContext(m.getLine(), m.getColumn());
               return this._updateToken(JsonToken.START_ARRAY);
            }

            if (evt.is(ID.SequenceEnd)) {
               if (!this._parsingContext.inArray()) {
                  this._reportMismatchedEndMarker(93, '}');
               }

               this._parsingContext = this._parsingContext.getParent();
               return this._updateToken(JsonToken.END_ARRAY);
            }

            if (!evt.is(ID.DocumentEnd) && !evt.is(ID.DocumentStart)) {
               if (evt.is(ID.Alias)) {
                  AliasEvent alias = (AliasEvent)evt;
                  this._currentIsAlias = true;
                  this._textValue = alias.getAnchor();
                  this._cleanedTextValue = null;
                  return this._updateToken(JsonToken.VALUE_STRING);
               }

               if (evt.is(ID.StreamEnd)) {
                  this.close();
                  return this._updateTokenToNull();
               }
            }
         }
      }
   }

   protected Event getEvent() {
      return this._yamlParser.getEvent();
   }

   protected JsonToken _decodeScalar(ScalarEvent scalar) throws IOException {
      String value = scalar.getValue();
      this._textValue = value;
      this._cleanedTextValue = null;
      if (!this._cfgEmptyStringsToNull && value.isEmpty()) {
         return JsonToken.VALUE_STRING;
      } else {
         String typeTag = scalar.getTag();
         int len = value.length();
         if (typeTag != null && !typeTag.equals("!")) {
            if (typeTag.startsWith("tag:yaml.org,2002:")) {
               typeTag = typeTag.substring("tag:yaml.org,2002:".length());
               if (typeTag.contains(",")) {
                  String[] tags = typeTag.split(",");
                  typeTag = tags.length == 0 ? "" : tags[0];
               }
            }

            if ("binary".equals(typeTag)) {
               value = value.trim();

               try {
                  this._binaryValue = Base64Variants.MIME.decode(value);
               } catch (IllegalArgumentException e) {
                  this._reportError(e.getMessage());
               }

               return JsonToken.VALUE_EMBEDDED_OBJECT;
            }

            if ("bool".equals(typeTag)) {
               Boolean B = this._matchYAMLBoolean(value, len);
               if (B != null) {
                  return B ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
               }
            } else if (len > 0) {
               if ("int".equals(typeTag)) {
                  return this._decodeNumberScalar(value, len);
               }

               if ("float".equals(typeTag)) {
                  this._numTypesValid = 0;
                  return this._cleanYamlFloat(value);
               }

               if ("null".equals(typeTag)) {
                  return JsonToken.VALUE_NULL;
               }
            }
         } else {
            Tag nodeTag = this._yamlResolver.resolve(NodeId.scalar, value, scalar.getImplicit().canOmitTagInPlainScalar());
            if (nodeTag == Tag.STR) {
               return JsonToken.VALUE_STRING;
            }

            if (nodeTag == Tag.INT) {
               return this._decodeNumberScalar(value, len);
            }

            if (nodeTag == Tag.FLOAT) {
               this._numTypesValid = 0;
               return this._cleanYamlFloat(value);
            }

            if (nodeTag != Tag.BOOL) {
               if (nodeTag == Tag.NULL) {
                  return JsonToken.VALUE_NULL;
               }

               return JsonToken.VALUE_STRING;
            }

            Boolean B = this._matchYAMLBoolean(value, len);
            if (B != null) {
               return B ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
            }
         }

         return JsonToken.VALUE_STRING;
      }
   }

   protected Boolean _matchYAMLBoolean(String value, int len) {
      if (this.isEnabled(YAMLParser.Feature.PARSE_BOOLEAN_LIKE_WORDS_AS_STRINGS)) {
         if ("true".equalsIgnoreCase(value)) {
            return Boolean.TRUE;
         }

         if ("false".equalsIgnoreCase(value)) {
            return Boolean.FALSE;
         }
      } else {
         switch (len) {
            case 1:
               switch (value.charAt(0)) {
                  case 'N':
                  case 'n':
                     return Boolean.FALSE;
                  case 'Y':
                  case 'y':
                     return Boolean.TRUE;
                  default:
                     return null;
               }
            case 2:
               if ("no".equalsIgnoreCase(value)) {
                  return Boolean.FALSE;
               }

               if ("on".equalsIgnoreCase(value)) {
                  return Boolean.TRUE;
               }
               break;
            case 3:
               if ("yes".equalsIgnoreCase(value)) {
                  return Boolean.TRUE;
               }

               if ("off".equalsIgnoreCase(value)) {
                  return Boolean.FALSE;
               }
               break;
            case 4:
               if ("true".equalsIgnoreCase(value)) {
                  return Boolean.TRUE;
               }
               break;
            case 5:
               if ("false".equalsIgnoreCase(value)) {
                  return Boolean.FALSE;
               }
         }
      }

      return null;
   }

   protected JsonToken _decodeNumberScalar(String value, int len) throws IOException {
      char ch = value.charAt(0);
      int i;
      if (ch == '-') {
         this._numberNegative = true;
         i = 1;
      } else if (ch == '+') {
         this._numberNegative = false;
         if (len == 1) {
            return null;
         }

         i = 1;
      } else {
         this._numberNegative = false;
         i = 0;
      }

      if (len == i) {
         return null;
      } else if (value.charAt(i) == '0') {
         ++i;
         if (i == len) {
            this._numberInt = 0;
            this._numTypesValid = 1;
            return JsonToken.VALUE_NUMBER_INT;
         } else {
            ch = value.charAt(i);
            switch (ch) {
               case '0':
               case '1':
               case '2':
               case '3':
               case '4':
               case '5':
               case '6':
               case '7':
               case '8':
               case '9':
               case '_':
                  return this._decodeNumberIntOctal(value, i, len, this._numberNegative);
               case 'B':
               case 'b':
                  return this._decodeNumberIntBinary(value, i + 1, len, this._numberNegative);
               case 'X':
               case 'x':
                  return this._decodeNumberIntHex(value, i + 1, len, this._numberNegative);
               default:
                  return JsonToken.VALUE_STRING;
            }
         }
      } else {
         boolean underscores = false;

         do {
            int c = value.charAt(i);
            if (c > 57 || c < 48) {
               if (c != 95) {
                  return JsonToken.VALUE_STRING;
               }

               underscores = true;
            }

            ++i;
         } while(i != len);

         this._numTypesValid = 0;
         if (underscores) {
            return this._cleanYamlInt(value);
         } else {
            this._cleanedTextValue = this._textValue;
            return JsonToken.VALUE_NUMBER_INT;
         }
      }
   }

   protected JsonToken _decodeNumberIntBinary(String value, int i, int origLen, boolean negative) throws IOException {
      String cleansed = this._cleanUnderscores(value, i, origLen);
      int digitLen = cleansed.length();
      if (digitLen <= 31) {
         int v = this._decodeInt(cleansed, 2);
         if (negative) {
            v = -v;
         }

         this._numberInt = v;
         this._numTypesValid = 1;
         return JsonToken.VALUE_NUMBER_INT;
      } else {
         return digitLen <= 63 ? this._decodeFromLong(this._decodeLong(cleansed, 2), negative, digitLen == 32) : this._decodeFromBigInteger(this._decodeBigInt(cleansed, 2), negative);
      }
   }

   protected JsonToken _decodeNumberIntOctal(String value, int i, int origLen, boolean negative) throws IOException {
      String cleansed = this._cleanUnderscores(value, i, origLen);
      int digitLen = cleansed.length();
      if (digitLen <= 10) {
         int v = this._decodeInt(cleansed, 8);
         if (negative) {
            v = -v;
         }

         this._numberInt = v;
         this._numTypesValid = 1;
         return JsonToken.VALUE_NUMBER_INT;
      } else {
         return digitLen <= 21 ? this._decodeFromLong(this._decodeLong(cleansed, 8), negative, false) : this._decodeFromBigInteger(this._decodeBigInt(cleansed, 8), negative);
      }
   }

   protected JsonToken _decodeNumberIntHex(String value, int i, int origLen, boolean negative) throws IOException {
      String cleansed = this._cleanUnderscores(value, i, origLen);
      int digitLen = cleansed.length();
      if (digitLen <= 7) {
         int v = this._decodeInt(cleansed, 16);
         if (negative) {
            v = -v;
         }

         this._numberInt = v;
         this._numTypesValid = 1;
         return JsonToken.VALUE_NUMBER_INT;
      } else {
         return digitLen <= 15 ? this._decodeFromLong(this._decodeLong(cleansed, 16), negative, digitLen == 8) : this._decodeFromBigInteger(this._decodeBigInt(cleansed, 16), negative);
      }
   }

   private JsonToken _decodeFromLong(long unsignedValue, boolean negative, boolean checkIfInt) {
      long actualValue;
      if (negative) {
         actualValue = -unsignedValue;
         if (checkIfInt && actualValue >= -2147483648L) {
            this._numberInt = (int)actualValue;
            this._numTypesValid = 1;
            return JsonToken.VALUE_NUMBER_INT;
         }
      } else {
         if (checkIfInt && unsignedValue < 2147483647L) {
            this._numberInt = (int)unsignedValue;
            this._numTypesValid = 1;
            return JsonToken.VALUE_NUMBER_INT;
         }

         actualValue = unsignedValue;
      }

      this._numberLong = actualValue;
      this._numTypesValid = 2;
      return JsonToken.VALUE_NUMBER_INT;
   }

   private JsonToken _decodeFromBigInteger(BigInteger unsignedValue, boolean negative) {
      if (negative) {
         this._numberBigInt = unsignedValue.negate();
      } else {
         this._numberBigInt = unsignedValue;
      }

      this._numTypesValid = 4;
      return JsonToken.VALUE_NUMBER_INT;
   }

   private int _decodeInt(String str, int base) throws IOException {
      try {
         return Integer.parseInt(str, base);
      } catch (NumberFormatException e) {
         return (Integer)this._reportInvalidNumber(str, base, e);
      }
   }

   private long _decodeLong(String str, int base) throws IOException {
      try {
         return Long.parseLong(str, base);
      } catch (NumberFormatException e) {
         return (Long)this._reportInvalidNumber(str, base, e);
      }
   }

   private BigInteger _decodeBigInt(String numStr, int base) throws IOException {
      this.streamReadConstraints().validateIntegerLength(numStr.length());

      try {
         return base == 10 ? NumberInput.parseBigInteger(numStr, this.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER)) : NumberInput.parseBigIntegerWithRadix(numStr, base, this.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
      } catch (NumberFormatException e) {
         return (BigInteger)this._reportInvalidNumber(numStr, base, e);
      }
   }

   private Object _reportInvalidNumber(String str, int base, Exception e) throws IOException {
      this._reportError(String.format("Invalid base-%d number ('%s'), problem: %s", base, str, e.getMessage()));
      return null;
   }

   public String currentName() throws IOException {
      return this._currToken == JsonToken.FIELD_NAME ? this._currentFieldName : super.currentName();
   }

   /** @deprecated */
   @Deprecated
   public String getCurrentName() throws IOException {
      return this._currToken == JsonToken.FIELD_NAME ? this._currentFieldName : super.getCurrentName();
   }

   public boolean hasTextCharacters() {
      return false;
   }

   public String getText() throws IOException {
      if (this._currToken == JsonToken.VALUE_STRING) {
         return this._textValue;
      } else if (this._currToken == JsonToken.FIELD_NAME) {
         return this._currentFieldName;
      } else if (this._currToken != null) {
         return this._currToken.isScalarValue() ? this._textValue : this._currToken.asString();
      } else {
         return null;
      }
   }

   public char[] getTextCharacters() throws IOException {
      String text = this.getText();
      return text == null ? null : text.toCharArray();
   }

   public int getTextLength() throws IOException {
      String text = this.getText();
      return text == null ? 0 : text.length();
   }

   public int getTextOffset() throws IOException {
      return 0;
   }

   public int getText(Writer writer) throws IOException {
      String str = this.getText();
      if (str == null) {
         return 0;
      } else {
         writer.write(str);
         return str.length();
      }
   }

   public Object getEmbeddedObject() throws IOException {
      return this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT ? this._binaryValue : null;
   }

   public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException {
      byte[] b = this.getBinaryValue(b64variant);
      out.write(b);
      return b.length;
   }

   public JsonParser.NumberTypeFP getNumberTypeFP() throws IOException {
      return NumberTypeFP.UNKNOWN;
   }

   public Object getNumberValueDeferred() throws IOException {
      if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
         if ((this._numTypesValid & 1) != 0) {
            return this._numberInt;
         } else if ((this._numTypesValid & 2) != 0) {
            return this._numberLong;
         } else if ((this._numTypesValid & 4) != 0) {
            return this._getBigInteger();
         } else {
            if (this._cleanedTextValue == null) {
               this._reportError("Internal number decoding error: `_cleanedTextValue` null when nothing decoded for `JsonToken.VALUE_NUMBER_INT`");
            }

            return this._cleanedTextValue;
         }
      } else {
         if (this._currToken != JsonToken.VALUE_NUMBER_FLOAT) {
            this._reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
         }

         if ((this._numTypesValid & 16) != 0) {
            return this._getBigDecimal();
         } else if ((this._numTypesValid & 8) != 0) {
            return this._getNumberDouble();
         } else {
            return (this._numTypesValid & 32) != 0 ? this._getNumberFloat() : this._cleanedTextValue;
         }
      }
   }

   protected void _parseNumericValue(int expType) throws IOException {
      if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
         int len = this._cleanedTextValue.length();
         if (this._numberNegative) {
            --len;
         }

         if (len <= 9) {
            this._numberInt = this._decodeInt(this._cleanedTextValue, 10);
            this._numTypesValid = 1;
            return;
         }

         if (len <= 18) {
            long l = Long.parseLong(this._cleanedTextValue);
            if (len == 10) {
               if (this._numberNegative) {
                  if (l >= -2147483648L) {
                     this._numberInt = (int)l;
                     this._numTypesValid = 1;
                     return;
                  }
               } else if (l <= 2147483647L) {
                  this._numberInt = (int)l;
                  this._numTypesValid = 1;
                  return;
               }
            }

            this._numberLong = l;
            this._numTypesValid = 2;
            return;
         }

         String numStr = this._cleanedTextValue;

         try {
            this.streamReadConstraints().validateIntegerLength(numStr.length());
            BigInteger n = NumberInput.parseBigInteger(numStr, this.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
            if (len == 19 && n.bitLength() <= 63) {
               this._numberLong = n.longValue();
               this._numTypesValid = 2;
               return;
            }

            this._numberBigInt = n;
            this._numTypesValid = 4;
            return;
         } catch (NumberFormatException nex) {
            this._wrapError("Malformed numeric value '" + this._textValue + "'", nex);
         }
      }

      if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
         String numStr = this._cleanedTextValue;

         try {
            if (expType == 16) {
               this.streamReadConstraints().validateFPLength(numStr.length());
               this._numberBigDecimal = NumberInput.parseBigDecimal(numStr, this.isEnabled(StreamReadFeature.USE_FAST_BIG_NUMBER_PARSER));
               this._numTypesValid = 16;
            } else {
               this.streamReadConstraints().validateFPLength(numStr.length());
               this._numberDouble = NumberInput.parseDouble(numStr, this.isEnabled(StreamReadFeature.USE_FAST_DOUBLE_PARSER));
               this._numTypesValid = 8;
            }
         } catch (NumberFormatException nex) {
            this._wrapError("Malformed numeric value '" + this._textValue + "'", nex);
         }

      } else {
         this._reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
      }
   }

   protected int _parseIntValue() throws IOException {
      if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
         int len = this._cleanedTextValue.length();
         if (this._numberNegative) {
            --len;
         }

         if (len <= 9) {
            this._numberInt = this._decodeInt(this._cleanedTextValue, 10);
            this._numTypesValid = 1;
            return this._numberInt;
         }
      }

      this._parseNumericValue(1);
      if ((this._numTypesValid & 1) == 0) {
         this.convertNumberToInt();
      }

      return this._numberInt;
   }

   public String getObjectId() throws IOException {
      return this._currentAnchor;
   }

   public String getTypeId() throws IOException {
      String tag;
      if (this._lastTagEvent instanceof CollectionStartEvent) {
         tag = ((CollectionStartEvent)this._lastTagEvent).getTag();
      } else {
         if (!(this._lastTagEvent instanceof ScalarEvent)) {
            return null;
         }

         tag = ((ScalarEvent)this._lastTagEvent).getTag();
      }

      if (tag == null) {
         return null;
      } else {
         while(tag.startsWith("!")) {
            tag = tag.substring(1);
         }

         return tag;
      }
   }

   private JsonToken _cleanYamlInt(String str) throws IOException {
      int len = str.length();
      StringBuilder sb = new StringBuilder(len);

      for(int i = str.charAt(0) == '+' ? 1 : 0; i < len; ++i) {
         char c = str.charAt(i);
         if (c != '_') {
            sb.append(c);
         }
      }

      this._cleanedTextValue = sb.toString();
      if (this._cleanedTextValue.isEmpty() || "-".equals(this._cleanedTextValue)) {
         this._reportError(String.format("Invalid number ('%s')", str));
      }

      return JsonToken.VALUE_NUMBER_INT;
   }

   private String _cleanUnderscores(String str, int i, int len) {
      StringBuilder sb;
      for(sb = new StringBuilder(len); i < len; ++i) {
         char ch = str.charAt(i);
         if (ch != '_') {
            sb.append(ch);
         }
      }

      if (sb.length() == len) {
         return str;
      } else {
         return sb.toString();
      }
   }

   private JsonToken _cleanYamlFloat(String str) {
      int len = str.length();
      int ix = str.indexOf(95);
      if (ix >= 0 && len != 0) {
         StringBuilder sb = new StringBuilder(len);

         for(int i = str.charAt(0) == '+' ? 1 : 0; i < len; ++i) {
            char c = str.charAt(i);
            if (c != '_') {
               sb.append(c);
            }
         }

         this._cleanedTextValue = sb.toString();
         return JsonToken.VALUE_NUMBER_FLOAT;
      } else {
         this._cleanedTextValue = str;
         return JsonToken.VALUE_NUMBER_FLOAT;
      }
   }

   public static enum Feature implements FormatFeature {
      EMPTY_STRING_AS_NULL(true),
      PARSE_BOOLEAN_LIKE_WORDS_AS_STRINGS(false);

      final boolean _defaultState;
      final int _mask;

      public static int collectDefaults() {
         int flags = 0;

         for(Feature f : values()) {
            if (f.enabledByDefault()) {
               flags |= f.getMask();
            }
         }

         return flags;
      }

      private Feature(boolean defaultState) {
         this._defaultState = defaultState;
         this._mask = 1 << this.ordinal();
      }

      public boolean enabledByDefault() {
         return this._defaultState;
      }

      public boolean enabledIn(int flags) {
         return (flags & this._mask) != 0;
      }

      public int getMask() {
         return this._mask;
      }
   }
}
