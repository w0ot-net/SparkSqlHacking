package org.sparkproject.guava.net;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Ascii;
import org.sparkproject.guava.base.CharMatcher;
import org.sparkproject.guava.base.Joiner;
import org.sparkproject.guava.base.MoreObjects;
import org.sparkproject.guava.base.Objects;
import org.sparkproject.guava.base.Optional;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.ImmutableListMultimap;
import org.sparkproject.guava.collect.ImmutableMultiset;
import org.sparkproject.guava.collect.ImmutableSet;
import org.sparkproject.guava.collect.ListMultimap;
import org.sparkproject.guava.collect.Maps;
import org.sparkproject.guava.collect.Multimap;
import org.sparkproject.guava.collect.Multimaps;

@Immutable
@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class MediaType {
   private static final String CHARSET_ATTRIBUTE = "charset";
   private static final ImmutableListMultimap UTF_8_CONSTANT_PARAMETERS;
   private static final CharMatcher TOKEN_MATCHER;
   private static final CharMatcher QUOTED_TEXT_MATCHER;
   private static final CharMatcher LINEAR_WHITE_SPACE;
   private static final String APPLICATION_TYPE = "application";
   private static final String AUDIO_TYPE = "audio";
   private static final String IMAGE_TYPE = "image";
   private static final String TEXT_TYPE = "text";
   private static final String VIDEO_TYPE = "video";
   private static final String FONT_TYPE = "font";
   private static final String WILDCARD = "*";
   private static final Map KNOWN_TYPES;
   public static final MediaType ANY_TYPE;
   public static final MediaType ANY_TEXT_TYPE;
   public static final MediaType ANY_IMAGE_TYPE;
   public static final MediaType ANY_AUDIO_TYPE;
   public static final MediaType ANY_VIDEO_TYPE;
   public static final MediaType ANY_APPLICATION_TYPE;
   public static final MediaType ANY_FONT_TYPE;
   public static final MediaType CACHE_MANIFEST_UTF_8;
   public static final MediaType CSS_UTF_8;
   public static final MediaType CSV_UTF_8;
   public static final MediaType HTML_UTF_8;
   public static final MediaType I_CALENDAR_UTF_8;
   public static final MediaType MD_UTF_8;
   public static final MediaType PLAIN_TEXT_UTF_8;
   public static final MediaType TEXT_JAVASCRIPT_UTF_8;
   public static final MediaType TSV_UTF_8;
   public static final MediaType VCARD_UTF_8;
   public static final MediaType WML_UTF_8;
   public static final MediaType XML_UTF_8;
   public static final MediaType VTT_UTF_8;
   public static final MediaType BMP;
   public static final MediaType CRW;
   public static final MediaType GIF;
   public static final MediaType ICO;
   public static final MediaType JPEG;
   public static final MediaType PNG;
   public static final MediaType PSD;
   public static final MediaType SVG_UTF_8;
   public static final MediaType TIFF;
   public static final MediaType WEBP;
   public static final MediaType HEIF;
   public static final MediaType JP2K;
   public static final MediaType MP4_AUDIO;
   public static final MediaType MPEG_AUDIO;
   public static final MediaType OGG_AUDIO;
   public static final MediaType WEBM_AUDIO;
   public static final MediaType L16_AUDIO;
   public static final MediaType L24_AUDIO;
   public static final MediaType BASIC_AUDIO;
   public static final MediaType AAC_AUDIO;
   public static final MediaType VORBIS_AUDIO;
   public static final MediaType WMA_AUDIO;
   public static final MediaType WAX_AUDIO;
   public static final MediaType VND_REAL_AUDIO;
   public static final MediaType VND_WAVE_AUDIO;
   public static final MediaType MP4_VIDEO;
   public static final MediaType MPEG_VIDEO;
   public static final MediaType OGG_VIDEO;
   public static final MediaType QUICKTIME;
   public static final MediaType WEBM_VIDEO;
   public static final MediaType WMV;
   public static final MediaType FLV_VIDEO;
   public static final MediaType THREE_GPP_VIDEO;
   public static final MediaType THREE_GPP2_VIDEO;
   public static final MediaType APPLICATION_XML_UTF_8;
   public static final MediaType ATOM_UTF_8;
   public static final MediaType BZIP2;
   public static final MediaType DART_UTF_8;
   public static final MediaType APPLE_PASSBOOK;
   public static final MediaType EOT;
   public static final MediaType EPUB;
   public static final MediaType FORM_DATA;
   public static final MediaType KEY_ARCHIVE;
   public static final MediaType APPLICATION_BINARY;
   public static final MediaType CBOR;
   public static final MediaType GEO_JSON;
   public static final MediaType GZIP;
   public static final MediaType HAL_JSON;
   public static final MediaType JAVASCRIPT_UTF_8;
   public static final MediaType JOSE;
   public static final MediaType JOSE_JSON;
   public static final MediaType JSON_UTF_8;
   public static final MediaType JWT;
   public static final MediaType MANIFEST_JSON_UTF_8;
   public static final MediaType KML;
   public static final MediaType KMZ;
   public static final MediaType MBOX;
   public static final MediaType APPLE_MOBILE_CONFIG;
   public static final MediaType MICROSOFT_EXCEL;
   public static final MediaType MICROSOFT_OUTLOOK;
   public static final MediaType MICROSOFT_POWERPOINT;
   public static final MediaType MICROSOFT_WORD;
   public static final MediaType MEDIA_PRESENTATION_DESCRIPTION;
   public static final MediaType WASM_APPLICATION;
   public static final MediaType NACL_APPLICATION;
   public static final MediaType NACL_PORTABLE_APPLICATION;
   public static final MediaType OCTET_STREAM;
   public static final MediaType OGG_CONTAINER;
   public static final MediaType OOXML_DOCUMENT;
   public static final MediaType OOXML_PRESENTATION;
   public static final MediaType OOXML_SHEET;
   public static final MediaType OPENDOCUMENT_GRAPHICS;
   public static final MediaType OPENDOCUMENT_PRESENTATION;
   public static final MediaType OPENDOCUMENT_SPREADSHEET;
   public static final MediaType OPENDOCUMENT_TEXT;
   public static final MediaType OPENSEARCH_DESCRIPTION_UTF_8;
   public static final MediaType PDF;
   public static final MediaType POSTSCRIPT;
   public static final MediaType PROTOBUF;
   public static final MediaType RDF_XML_UTF_8;
   public static final MediaType RTF_UTF_8;
   public static final MediaType SFNT;
   public static final MediaType SHOCKWAVE_FLASH;
   public static final MediaType SKETCHUP;
   public static final MediaType SOAP_XML_UTF_8;
   public static final MediaType TAR;
   public static final MediaType WOFF;
   public static final MediaType WOFF2;
   public static final MediaType XHTML_UTF_8;
   public static final MediaType XRD_UTF_8;
   public static final MediaType ZIP;
   public static final MediaType FONT_COLLECTION;
   public static final MediaType FONT_OTF;
   public static final MediaType FONT_SFNT;
   public static final MediaType FONT_TTF;
   public static final MediaType FONT_WOFF;
   public static final MediaType FONT_WOFF2;
   private final String type;
   private final String subtype;
   private final ImmutableListMultimap parameters;
   @LazyInit
   @CheckForNull
   private String toString;
   @LazyInit
   private int hashCode;
   @LazyInit
   @CheckForNull
   private Optional parsedCharset;
   private static final Joiner.MapJoiner PARAMETER_JOINER;

   private static MediaType createConstant(String type, String subtype) {
      MediaType mediaType = addKnownType(new MediaType(type, subtype, ImmutableListMultimap.of()));
      mediaType.parsedCharset = Optional.absent();
      return mediaType;
   }

   private static MediaType createConstantUtf8(String type, String subtype) {
      MediaType mediaType = addKnownType(new MediaType(type, subtype, UTF_8_CONSTANT_PARAMETERS));
      mediaType.parsedCharset = Optional.of(StandardCharsets.UTF_8);
      return mediaType;
   }

   private static MediaType addKnownType(MediaType mediaType) {
      KNOWN_TYPES.put(mediaType, mediaType);
      return mediaType;
   }

   private MediaType(String type, String subtype, ImmutableListMultimap parameters) {
      this.type = type;
      this.subtype = subtype;
      this.parameters = parameters;
   }

   public String type() {
      return this.type;
   }

   public String subtype() {
      return this.subtype;
   }

   public ImmutableListMultimap parameters() {
      return this.parameters;
   }

   private Map parametersAsMap() {
      return Maps.transformValues((Map)this.parameters.asMap(), ImmutableMultiset::copyOf);
   }

   public Optional charset() {
      Optional<Charset> local = this.parsedCharset;
      if (local == null) {
         String value = null;
         local = Optional.absent();

         for(String currentValue : this.parameters.get("charset")) {
            if (value == null) {
               value = currentValue;
               local = Optional.of(Charset.forName(currentValue));
            } else if (!value.equals(currentValue)) {
               throw new IllegalStateException("Multiple charset values defined: " + value + ", " + currentValue);
            }
         }

         this.parsedCharset = local;
      }

      return local;
   }

   public MediaType withoutParameters() {
      return this.parameters.isEmpty() ? this : create(this.type, this.subtype);
   }

   public MediaType withParameters(Multimap parameters) {
      return create(this.type, this.subtype, parameters);
   }

   public MediaType withParameters(String attribute, Iterable values) {
      Preconditions.checkNotNull(attribute);
      Preconditions.checkNotNull(values);
      String normalizedAttribute = normalizeToken(attribute);
      ImmutableListMultimap.Builder<String, String> builder = ImmutableListMultimap.builder();

      for(Map.Entry entry : this.parameters.entries()) {
         String key = (String)entry.getKey();
         if (!normalizedAttribute.equals(key)) {
            builder.put(key, (String)entry.getValue());
         }
      }

      for(String value : values) {
         builder.put(normalizedAttribute, normalizeParameterValue(normalizedAttribute, value));
      }

      MediaType mediaType = new MediaType(this.type, this.subtype, builder.build());
      if (!normalizedAttribute.equals("charset")) {
         mediaType.parsedCharset = this.parsedCharset;
      }

      return (MediaType)MoreObjects.firstNonNull((MediaType)KNOWN_TYPES.get(mediaType), mediaType);
   }

   public MediaType withParameter(String attribute, String value) {
      return this.withParameters(attribute, ImmutableSet.of(value));
   }

   public MediaType withCharset(Charset charset) {
      Preconditions.checkNotNull(charset);
      MediaType withCharset = this.withParameter("charset", charset.name());
      withCharset.parsedCharset = Optional.of(charset);
      return withCharset;
   }

   public boolean hasWildcard() {
      return "*".equals(this.type) || "*".equals(this.subtype);
   }

   public boolean is(MediaType mediaTypeRange) {
      return (mediaTypeRange.type.equals("*") || mediaTypeRange.type.equals(this.type)) && (mediaTypeRange.subtype.equals("*") || mediaTypeRange.subtype.equals(this.subtype)) && this.parameters.entries().containsAll(mediaTypeRange.parameters.entries());
   }

   public static MediaType create(String type, String subtype) {
      MediaType mediaType = create(type, subtype, ImmutableListMultimap.of());
      mediaType.parsedCharset = Optional.absent();
      return mediaType;
   }

   private static MediaType create(String type, String subtype, Multimap parameters) {
      Preconditions.checkNotNull(type);
      Preconditions.checkNotNull(subtype);
      Preconditions.checkNotNull(parameters);
      String normalizedType = normalizeToken(type);
      String normalizedSubtype = normalizeToken(subtype);
      Preconditions.checkArgument(!"*".equals(normalizedType) || "*".equals(normalizedSubtype), "A wildcard type cannot be used with a non-wildcard subtype");
      ImmutableListMultimap.Builder<String, String> builder = ImmutableListMultimap.builder();

      for(Map.Entry entry : parameters.entries()) {
         String attribute = normalizeToken((String)entry.getKey());
         builder.put(attribute, normalizeParameterValue(attribute, (String)entry.getValue()));
      }

      MediaType mediaType = new MediaType(normalizedType, normalizedSubtype, builder.build());
      return (MediaType)MoreObjects.firstNonNull((MediaType)KNOWN_TYPES.get(mediaType), mediaType);
   }

   static MediaType createApplicationType(String subtype) {
      return create("application", subtype);
   }

   static MediaType createAudioType(String subtype) {
      return create("audio", subtype);
   }

   static MediaType createFontType(String subtype) {
      return create("font", subtype);
   }

   static MediaType createImageType(String subtype) {
      return create("image", subtype);
   }

   static MediaType createTextType(String subtype) {
      return create("text", subtype);
   }

   static MediaType createVideoType(String subtype) {
      return create("video", subtype);
   }

   private static String normalizeToken(String token) {
      Preconditions.checkArgument(TOKEN_MATCHER.matchesAllOf(token));
      Preconditions.checkArgument(!token.isEmpty());
      return Ascii.toLowerCase(token);
   }

   private static String normalizeParameterValue(String attribute, String value) {
      Preconditions.checkNotNull(value);
      Preconditions.checkArgument(CharMatcher.ascii().matchesAllOf(value), "parameter values must be ASCII: %s", (Object)value);
      return "charset".equals(attribute) ? Ascii.toLowerCase(value) : value;
   }

   @CanIgnoreReturnValue
   public static MediaType parse(String input) {
      Preconditions.checkNotNull(input);
      Tokenizer tokenizer = new Tokenizer(input);

      try {
         String type = tokenizer.consumeToken(TOKEN_MATCHER);
         consumeSeparator(tokenizer, '/');
         String subtype = tokenizer.consumeToken(TOKEN_MATCHER);

         ImmutableListMultimap.Builder<String, String> parameters;
         String attribute;
         String value;
         for(parameters = ImmutableListMultimap.builder(); tokenizer.hasMore(); parameters.put(attribute, value)) {
            consumeSeparator(tokenizer, ';');
            attribute = tokenizer.consumeToken(TOKEN_MATCHER);
            consumeSeparator(tokenizer, '=');
            if ('"' != tokenizer.previewChar()) {
               value = tokenizer.consumeToken(TOKEN_MATCHER);
            } else {
               tokenizer.consumeCharacter('"');
               StringBuilder valueBuilder = new StringBuilder();

               while('"' != tokenizer.previewChar()) {
                  if ('\\' == tokenizer.previewChar()) {
                     tokenizer.consumeCharacter('\\');
                     valueBuilder.append(tokenizer.consumeCharacter(CharMatcher.ascii()));
                  } else {
                     valueBuilder.append(tokenizer.consumeToken(QUOTED_TEXT_MATCHER));
                  }
               }

               value = valueBuilder.toString();
               tokenizer.consumeCharacter('"');
            }
         }

         return create(type, subtype, parameters.build());
      } catch (IllegalStateException e) {
         throw new IllegalArgumentException("Could not parse '" + input + "'", e);
      }
   }

   private static void consumeSeparator(Tokenizer tokenizer, char c) {
      tokenizer.consumeTokenIfPresent(LINEAR_WHITE_SPACE);
      tokenizer.consumeCharacter(c);
      tokenizer.consumeTokenIfPresent(LINEAR_WHITE_SPACE);
   }

   public boolean equals(@CheckForNull Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof MediaType)) {
         return false;
      } else {
         MediaType that = (MediaType)obj;
         return this.type.equals(that.type) && this.subtype.equals(that.subtype) && this.parametersAsMap().equals(that.parametersAsMap());
      }
   }

   public int hashCode() {
      int h = this.hashCode;
      if (h == 0) {
         h = Objects.hashCode(this.type, this.subtype, this.parametersAsMap());
         this.hashCode = h;
      }

      return h;
   }

   public String toString() {
      String result = this.toString;
      if (result == null) {
         result = this.computeToString();
         this.toString = result;
      }

      return result;
   }

   private String computeToString() {
      StringBuilder builder = (new StringBuilder()).append(this.type).append('/').append(this.subtype);
      if (!this.parameters.isEmpty()) {
         builder.append("; ");
         Multimap<String, String> quotedParameters = Multimaps.transformValues((ListMultimap)this.parameters, (value) -> TOKEN_MATCHER.matchesAllOf(value) && !value.isEmpty() ? value : escapeAndQuote(value));
         PARAMETER_JOINER.appendTo((StringBuilder)builder, (Iterable)quotedParameters.entries());
      }

      return builder.toString();
   }

   private static String escapeAndQuote(String value) {
      StringBuilder escaped = (new StringBuilder(value.length() + 16)).append('"');

      for(int i = 0; i < value.length(); ++i) {
         char ch = value.charAt(i);
         if (ch == '\r' || ch == '\\' || ch == '"') {
            escaped.append('\\');
         }

         escaped.append(ch);
      }

      return escaped.append('"').toString();
   }

   static {
      UTF_8_CONSTANT_PARAMETERS = ImmutableListMultimap.of("charset", Ascii.toLowerCase(StandardCharsets.UTF_8.name()));
      TOKEN_MATCHER = CharMatcher.ascii().and(CharMatcher.javaIsoControl().negate()).and(CharMatcher.isNot(' ')).and(CharMatcher.noneOf("()<>@,;:\\\"/[]?="));
      QUOTED_TEXT_MATCHER = CharMatcher.ascii().and(CharMatcher.noneOf("\"\\\r"));
      LINEAR_WHITE_SPACE = CharMatcher.anyOf(" \t\r\n");
      KNOWN_TYPES = Maps.newHashMap();
      ANY_TYPE = createConstant("*", "*");
      ANY_TEXT_TYPE = createConstant("text", "*");
      ANY_IMAGE_TYPE = createConstant("image", "*");
      ANY_AUDIO_TYPE = createConstant("audio", "*");
      ANY_VIDEO_TYPE = createConstant("video", "*");
      ANY_APPLICATION_TYPE = createConstant("application", "*");
      ANY_FONT_TYPE = createConstant("font", "*");
      CACHE_MANIFEST_UTF_8 = createConstantUtf8("text", "cache-manifest");
      CSS_UTF_8 = createConstantUtf8("text", "css");
      CSV_UTF_8 = createConstantUtf8("text", "csv");
      HTML_UTF_8 = createConstantUtf8("text", "html");
      I_CALENDAR_UTF_8 = createConstantUtf8("text", "calendar");
      MD_UTF_8 = createConstantUtf8("text", "markdown");
      PLAIN_TEXT_UTF_8 = createConstantUtf8("text", "plain");
      TEXT_JAVASCRIPT_UTF_8 = createConstantUtf8("text", "javascript");
      TSV_UTF_8 = createConstantUtf8("text", "tab-separated-values");
      VCARD_UTF_8 = createConstantUtf8("text", "vcard");
      WML_UTF_8 = createConstantUtf8("text", "vnd.wap.wml");
      XML_UTF_8 = createConstantUtf8("text", "xml");
      VTT_UTF_8 = createConstantUtf8("text", "vtt");
      BMP = createConstant("image", "bmp");
      CRW = createConstant("image", "x-canon-crw");
      GIF = createConstant("image", "gif");
      ICO = createConstant("image", "vnd.microsoft.icon");
      JPEG = createConstant("image", "jpeg");
      PNG = createConstant("image", "png");
      PSD = createConstant("image", "vnd.adobe.photoshop");
      SVG_UTF_8 = createConstantUtf8("image", "svg+xml");
      TIFF = createConstant("image", "tiff");
      WEBP = createConstant("image", "webp");
      HEIF = createConstant("image", "heif");
      JP2K = createConstant("image", "jp2");
      MP4_AUDIO = createConstant("audio", "mp4");
      MPEG_AUDIO = createConstant("audio", "mpeg");
      OGG_AUDIO = createConstant("audio", "ogg");
      WEBM_AUDIO = createConstant("audio", "webm");
      L16_AUDIO = createConstant("audio", "l16");
      L24_AUDIO = createConstant("audio", "l24");
      BASIC_AUDIO = createConstant("audio", "basic");
      AAC_AUDIO = createConstant("audio", "aac");
      VORBIS_AUDIO = createConstant("audio", "vorbis");
      WMA_AUDIO = createConstant("audio", "x-ms-wma");
      WAX_AUDIO = createConstant("audio", "x-ms-wax");
      VND_REAL_AUDIO = createConstant("audio", "vnd.rn-realaudio");
      VND_WAVE_AUDIO = createConstant("audio", "vnd.wave");
      MP4_VIDEO = createConstant("video", "mp4");
      MPEG_VIDEO = createConstant("video", "mpeg");
      OGG_VIDEO = createConstant("video", "ogg");
      QUICKTIME = createConstant("video", "quicktime");
      WEBM_VIDEO = createConstant("video", "webm");
      WMV = createConstant("video", "x-ms-wmv");
      FLV_VIDEO = createConstant("video", "x-flv");
      THREE_GPP_VIDEO = createConstant("video", "3gpp");
      THREE_GPP2_VIDEO = createConstant("video", "3gpp2");
      APPLICATION_XML_UTF_8 = createConstantUtf8("application", "xml");
      ATOM_UTF_8 = createConstantUtf8("application", "atom+xml");
      BZIP2 = createConstant("application", "x-bzip2");
      DART_UTF_8 = createConstantUtf8("application", "dart");
      APPLE_PASSBOOK = createConstant("application", "vnd.apple.pkpass");
      EOT = createConstant("application", "vnd.ms-fontobject");
      EPUB = createConstant("application", "epub+zip");
      FORM_DATA = createConstant("application", "x-www-form-urlencoded");
      KEY_ARCHIVE = createConstant("application", "pkcs12");
      APPLICATION_BINARY = createConstant("application", "binary");
      CBOR = createConstant("application", "cbor");
      GEO_JSON = createConstant("application", "geo+json");
      GZIP = createConstant("application", "x-gzip");
      HAL_JSON = createConstant("application", "hal+json");
      JAVASCRIPT_UTF_8 = createConstantUtf8("application", "javascript");
      JOSE = createConstant("application", "jose");
      JOSE_JSON = createConstant("application", "jose+json");
      JSON_UTF_8 = createConstantUtf8("application", "json");
      JWT = createConstant("application", "jwt");
      MANIFEST_JSON_UTF_8 = createConstantUtf8("application", "manifest+json");
      KML = createConstant("application", "vnd.google-earth.kml+xml");
      KMZ = createConstant("application", "vnd.google-earth.kmz");
      MBOX = createConstant("application", "mbox");
      APPLE_MOBILE_CONFIG = createConstant("application", "x-apple-aspen-config");
      MICROSOFT_EXCEL = createConstant("application", "vnd.ms-excel");
      MICROSOFT_OUTLOOK = createConstant("application", "vnd.ms-outlook");
      MICROSOFT_POWERPOINT = createConstant("application", "vnd.ms-powerpoint");
      MICROSOFT_WORD = createConstant("application", "msword");
      MEDIA_PRESENTATION_DESCRIPTION = createConstant("application", "dash+xml");
      WASM_APPLICATION = createConstant("application", "wasm");
      NACL_APPLICATION = createConstant("application", "x-nacl");
      NACL_PORTABLE_APPLICATION = createConstant("application", "x-pnacl");
      OCTET_STREAM = createConstant("application", "octet-stream");
      OGG_CONTAINER = createConstant("application", "ogg");
      OOXML_DOCUMENT = createConstant("application", "vnd.openxmlformats-officedocument.wordprocessingml.document");
      OOXML_PRESENTATION = createConstant("application", "vnd.openxmlformats-officedocument.presentationml.presentation");
      OOXML_SHEET = createConstant("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      OPENDOCUMENT_GRAPHICS = createConstant("application", "vnd.oasis.opendocument.graphics");
      OPENDOCUMENT_PRESENTATION = createConstant("application", "vnd.oasis.opendocument.presentation");
      OPENDOCUMENT_SPREADSHEET = createConstant("application", "vnd.oasis.opendocument.spreadsheet");
      OPENDOCUMENT_TEXT = createConstant("application", "vnd.oasis.opendocument.text");
      OPENSEARCH_DESCRIPTION_UTF_8 = createConstantUtf8("application", "opensearchdescription+xml");
      PDF = createConstant("application", "pdf");
      POSTSCRIPT = createConstant("application", "postscript");
      PROTOBUF = createConstant("application", "protobuf");
      RDF_XML_UTF_8 = createConstantUtf8("application", "rdf+xml");
      RTF_UTF_8 = createConstantUtf8("application", "rtf");
      SFNT = createConstant("application", "font-sfnt");
      SHOCKWAVE_FLASH = createConstant("application", "x-shockwave-flash");
      SKETCHUP = createConstant("application", "vnd.sketchup.skp");
      SOAP_XML_UTF_8 = createConstantUtf8("application", "soap+xml");
      TAR = createConstant("application", "x-tar");
      WOFF = createConstant("application", "font-woff");
      WOFF2 = createConstant("application", "font-woff2");
      XHTML_UTF_8 = createConstantUtf8("application", "xhtml+xml");
      XRD_UTF_8 = createConstantUtf8("application", "xrd+xml");
      ZIP = createConstant("application", "zip");
      FONT_COLLECTION = createConstant("font", "collection");
      FONT_OTF = createConstant("font", "otf");
      FONT_SFNT = createConstant("font", "sfnt");
      FONT_TTF = createConstant("font", "ttf");
      FONT_WOFF = createConstant("font", "woff");
      FONT_WOFF2 = createConstant("font", "woff2");
      PARAMETER_JOINER = Joiner.on("; ").withKeyValueSeparator("=");
   }

   private static final class Tokenizer {
      final String input;
      int position = 0;

      Tokenizer(String input) {
         this.input = input;
      }

      @CanIgnoreReturnValue
      String consumeTokenIfPresent(CharMatcher matcher) {
         Preconditions.checkState(this.hasMore());
         int startPosition = this.position;
         this.position = matcher.negate().indexIn(this.input, startPosition);
         return this.hasMore() ? this.input.substring(startPosition, this.position) : this.input.substring(startPosition);
      }

      String consumeToken(CharMatcher matcher) {
         int startPosition = this.position;
         String token = this.consumeTokenIfPresent(matcher);
         Preconditions.checkState(this.position != startPosition);
         return token;
      }

      char consumeCharacter(CharMatcher matcher) {
         Preconditions.checkState(this.hasMore());
         char c = this.previewChar();
         Preconditions.checkState(matcher.matches(c));
         ++this.position;
         return c;
      }

      @CanIgnoreReturnValue
      char consumeCharacter(char c) {
         Preconditions.checkState(this.hasMore());
         Preconditions.checkState(this.previewChar() == c);
         ++this.position;
         return c;
      }

      char previewChar() {
         Preconditions.checkState(this.hasMore());
         return this.input.charAt(this.position);
      }

      boolean hasMore() {
         return this.position >= 0 && this.position < this.input.length();
      }
   }
}
