package org.apache.commons.text.lookup;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class StringLookupFactory {
   public static final String DEFAULT_STRING_LOOKUPS_PROPERTY = "org.apache.commons.text.lookup.StringLookupFactory.defaultStringLookups";
   public static final StringLookupFactory INSTANCE = new StringLookupFactory();
   static final FunctionStringLookup INSTANCE_BASE64_DECODER = FunctionStringLookup.on((Function)((key) -> new String(Base64.getDecoder().decode(key), StandardCharsets.ISO_8859_1)));
   static final FunctionStringLookup INSTANCE_BASE64_ENCODER = FunctionStringLookup.on((Function)((key) -> Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.ISO_8859_1))));
   static final FunctionStringLookup INSTANCE_ENVIRONMENT_VARIABLES = FunctionStringLookup.on(System::getenv);
   static final FunctionStringLookup INSTANCE_NULL = FunctionStringLookup.on((Function)((key) -> null));
   static final FunctionStringLookup INSTANCE_SYSTEM_PROPERTIES = FunctionStringLookup.on(System::getProperty);
   public static final String KEY_BASE64_DECODER = "base64Decoder";
   public static final String KEY_BASE64_ENCODER = "base64Encoder";
   public static final String KEY_CONST = "const";
   public static final String KEY_DATE = "date";
   public static final String KEY_DNS = "dns";
   public static final String KEY_ENV = "env";
   public static final String KEY_FILE = "file";
   public static final String KEY_JAVA = "java";
   public static final String KEY_LOCALHOST = "localhost";
   public static final String KEY_LOOPBACK_ADDRESS = "loobackAddress";
   public static final String KEY_PROPERTIES = "properties";
   public static final String KEY_RESOURCE_BUNDLE = "resourceBundle";
   public static final String KEY_SCRIPT = "script";
   public static final String KEY_SYS = "sys";
   public static final String KEY_URL = "url";
   public static final String KEY_URL_DECODER = "urlDecoder";
   public static final String KEY_URL_ENCODER = "urlEncoder";
   public static final String KEY_XML = "xml";
   public static final String KEY_XML_DECODER = "xmlDecoder";
   public static final String KEY_XML_ENCODER = "xmlEncoder";
   private final Path[] fences;

   public static Builder builder() {
      return new Builder();
   }

   public static void clear() {
      ConstantStringLookup.clear();
   }

   static String toKey(String key) {
      return key.toLowerCase(Locale.ROOT);
   }

   static Map toMap(Map map) {
      return map == null ? Collections.emptyMap() : map;
   }

   private StringLookupFactory() {
      this((Path[])null);
   }

   private StringLookupFactory(Path[] fences) {
      this.fences = fences;
   }

   public void addDefaultStringLookups(Map stringLookupMap) {
      if (stringLookupMap != null) {
         stringLookupMap.putAll(StringLookupFactory.DefaultStringLookupsHolder.INSTANCE.getDefaultStringLookups());
      }

   }

   public StringLookup base64DecoderStringLookup() {
      return INSTANCE_BASE64_DECODER;
   }

   public StringLookup base64EncoderStringLookup() {
      return INSTANCE_BASE64_ENCODER;
   }

   /** @deprecated */
   @Deprecated
   public StringLookup base64StringLookup() {
      return INSTANCE_BASE64_DECODER;
   }

   public BiStringLookup biFunctionStringLookup(BiFunction biFunction) {
      return BiFunctionStringLookup.on(biFunction);
   }

   public StringLookup constantStringLookup() {
      return ConstantStringLookup.INSTANCE;
   }

   public StringLookup dateStringLookup() {
      return DateStringLookup.INSTANCE;
   }

   public StringLookup dnsStringLookup() {
      return DnsStringLookup.INSTANCE;
   }

   public StringLookup environmentVariableStringLookup() {
      return INSTANCE_ENVIRONMENT_VARIABLES;
   }

   public StringLookup fileStringLookup() {
      return (StringLookup)(this.fences != null ? this.fileStringLookup(this.fences) : FileStringLookup.INSTANCE);
   }

   public StringLookup fileStringLookup(Path... fences) {
      return new FileStringLookup(fences);
   }

   public StringLookup functionStringLookup(Function function) {
      return FunctionStringLookup.on(function);
   }

   public StringLookup interpolatorStringLookup() {
      return InterpolatorStringLookup.INSTANCE;
   }

   public StringLookup interpolatorStringLookup(Map stringLookupMap, StringLookup defaultStringLookup, boolean addDefaultLookups) {
      return new InterpolatorStringLookup(stringLookupMap, defaultStringLookup, addDefaultLookups);
   }

   public StringLookup interpolatorStringLookup(Map map) {
      return new InterpolatorStringLookup(map);
   }

   public StringLookup interpolatorStringLookup(StringLookup defaultStringLookup) {
      return new InterpolatorStringLookup(defaultStringLookup);
   }

   public StringLookup javaPlatformStringLookup() {
      return JavaPlatformStringLookup.INSTANCE;
   }

   public StringLookup localHostStringLookup() {
      return InetAddressStringLookup.LOCAL_HOST;
   }

   public StringLookup loopbackAddressStringLookup() {
      return InetAddressStringLookup.LOOPACK_ADDRESS;
   }

   public StringLookup mapStringLookup(Map map) {
      return FunctionStringLookup.on(map);
   }

   public StringLookup nullStringLookup() {
      return INSTANCE_NULL;
   }

   public StringLookup propertiesStringLookup() {
      return (StringLookup)(this.fences != null ? this.propertiesStringLookup(this.fences) : PropertiesStringLookup.INSTANCE);
   }

   public StringLookup propertiesStringLookup(Path... fences) {
      return new PropertiesStringLookup(fences);
   }

   public StringLookup resourceBundleStringLookup() {
      return ResourceBundleStringLookup.INSTANCE;
   }

   public StringLookup resourceBundleStringLookup(String bundleName) {
      return new ResourceBundleStringLookup(bundleName);
   }

   public StringLookup scriptStringLookup() {
      return ScriptStringLookup.INSTANCE;
   }

   public StringLookup systemPropertyStringLookup() {
      return INSTANCE_SYSTEM_PROPERTIES;
   }

   public StringLookup urlDecoderStringLookup() {
      return UrlDecoderStringLookup.INSTANCE;
   }

   public StringLookup urlEncoderStringLookup() {
      return UrlEncoderStringLookup.INSTANCE;
   }

   public StringLookup urlStringLookup() {
      return UrlStringLookup.INSTANCE;
   }

   public StringLookup xmlDecoderStringLookup() {
      return XmlDecoderStringLookup.INSTANCE;
   }

   public StringLookup xmlEncoderStringLookup() {
      return XmlEncoderStringLookup.INSTANCE;
   }

   public StringLookup xmlStringLookup() {
      return (StringLookup)(this.fences != null ? this.xmlStringLookup(XmlStringLookup.DEFAULT_FEATURES, this.fences) : XmlStringLookup.INSTANCE);
   }

   public StringLookup xmlStringLookup(Map xPathFactoryFeatures) {
      return this.xmlStringLookup(xPathFactoryFeatures, this.fences);
   }

   public StringLookup xmlStringLookup(Map xPathFactoryFeatures, Path... fences) {
      return new XmlStringLookup(xPathFactoryFeatures, fences);
   }

   public static final class Builder implements Supplier {
      private Path[] fences;

      public StringLookupFactory get() {
         return new StringLookupFactory(this.fences);
      }

      public Builder setFences(Path... fences) {
         this.fences = fences;
         return this;
      }
   }

   static final class DefaultStringLookupsHolder {
      static final DefaultStringLookupsHolder INSTANCE = new DefaultStringLookupsHolder(System.getProperties());
      private final Map defaultStringLookups;

      private static void addLookup(DefaultStringLookup lookup, Map map) {
         map.put(StringLookupFactory.toKey(lookup.getKey()), lookup.getStringLookup());
         if (DefaultStringLookup.BASE64_DECODER.equals(lookup)) {
            map.put(StringLookupFactory.toKey("base64"), lookup.getStringLookup());
         }

      }

      private static Map createDefaultStringLookups() {
         Map<String, StringLookup> lookupMap = new HashMap();
         addLookup(DefaultStringLookup.BASE64_DECODER, lookupMap);
         addLookup(DefaultStringLookup.BASE64_ENCODER, lookupMap);
         addLookup(DefaultStringLookup.CONST, lookupMap);
         addLookup(DefaultStringLookup.DATE, lookupMap);
         addLookup(DefaultStringLookup.ENVIRONMENT, lookupMap);
         addLookup(DefaultStringLookup.FILE, lookupMap);
         addLookup(DefaultStringLookup.JAVA, lookupMap);
         addLookup(DefaultStringLookup.LOCAL_HOST, lookupMap);
         addLookup(DefaultStringLookup.LOCAL_HOST, lookupMap);
         addLookup(DefaultStringLookup.PROPERTIES, lookupMap);
         addLookup(DefaultStringLookup.RESOURCE_BUNDLE, lookupMap);
         addLookup(DefaultStringLookup.SYSTEM_PROPERTIES, lookupMap);
         addLookup(DefaultStringLookup.URL_DECODER, lookupMap);
         addLookup(DefaultStringLookup.URL_ENCODER, lookupMap);
         addLookup(DefaultStringLookup.XML, lookupMap);
         addLookup(DefaultStringLookup.XML_DECODER, lookupMap);
         addLookup(DefaultStringLookup.XML_ENCODER, lookupMap);
         return lookupMap;
      }

      private static Map parseStringLookups(String str) {
         Map<String, StringLookup> lookupMap = new HashMap();

         try {
            for(String lookupName : str.split("[\\s,]+")) {
               if (!lookupName.isEmpty()) {
                  addLookup(DefaultStringLookup.valueOf(lookupName.toUpperCase()), lookupMap);
               }
            }

            return lookupMap;
         } catch (IllegalArgumentException exc) {
            throw new IllegalArgumentException("Invalid default string lookups definition: " + str, exc);
         }
      }

      DefaultStringLookupsHolder(Properties props) {
         Map<String, StringLookup> lookups = props.containsKey("org.apache.commons.text.lookup.StringLookupFactory.defaultStringLookups") ? parseStringLookups(props.getProperty("org.apache.commons.text.lookup.StringLookupFactory.defaultStringLookups")) : createDefaultStringLookups();
         this.defaultStringLookups = Collections.unmodifiableMap(lookups);
      }

      Map getDefaultStringLookups() {
         return this.defaultStringLookups;
      }
   }
}
