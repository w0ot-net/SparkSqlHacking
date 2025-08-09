package shaded.parquet.com.fasterxml.jackson.core.json;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonLocation;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.ObjectCodec;
import shaded.parquet.com.fasterxml.jackson.core.base.ParserBase;
import shaded.parquet.com.fasterxml.jackson.core.io.CharTypes;
import shaded.parquet.com.fasterxml.jackson.core.io.IOContext;
import shaded.parquet.com.fasterxml.jackson.core.util.JacksonFeatureSet;

public abstract class JsonParserBase extends ParserBase {
   protected static final int FEAT_MASK_TRAILING_COMMA;
   protected static final int FEAT_MASK_LEADING_ZEROS;
   protected static final int FEAT_MASK_NON_NUM_NUMBERS;
   protected static final int FEAT_MASK_ALLOW_MISSING;
   protected static final int FEAT_MASK_ALLOW_SINGLE_QUOTES;
   protected static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES;
   protected static final int FEAT_MASK_ALLOW_JAVA_COMMENTS;
   protected static final int FEAT_MASK_ALLOW_YAML_COMMENTS;
   protected static final int[] INPUT_CODES_LATIN1;
   protected static final int[] INPUT_CODES_UTF8;
   protected ObjectCodec _objectCodec;

   protected JsonParserBase(IOContext ioCtxt, int features, ObjectCodec codec) {
      super(ioCtxt, features);
      this._objectCodec = codec;
   }

   public ObjectCodec getCodec() {
      return this._objectCodec;
   }

   public void setCodec(ObjectCodec c) {
      this._objectCodec = c;
   }

   public final JacksonFeatureSet getReadCapabilities() {
      return JSON_READ_CAPABILITIES;
   }

   public JsonParser.NumberTypeFP getNumberTypeFP() throws IOException {
      return JsonParser.NumberTypeFP.UNKNOWN;
   }

   public abstract JsonLocation currentLocation();

   public abstract JsonLocation currentTokenLocation();

   protected abstract JsonLocation _currentLocationMinusOne();

   /** @deprecated */
   @Deprecated
   public final JsonLocation getCurrentLocation() {
      return this.currentLocation();
   }

   /** @deprecated */
   @Deprecated
   public final JsonLocation getTokenLocation() {
      return this.currentTokenLocation();
   }

   static {
      FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
      FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
      FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
      FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
      FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
      FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
      FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
      FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
      INPUT_CODES_LATIN1 = CharTypes.getInputCodeLatin1();
      INPUT_CODES_UTF8 = CharTypes.getInputCodeUtf8();
   }
}
