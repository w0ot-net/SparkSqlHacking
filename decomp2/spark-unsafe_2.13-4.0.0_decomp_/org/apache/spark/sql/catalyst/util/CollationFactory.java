package org.apache.spark.sql.catalyst.util;

import com.ibm.icu.text.CollationKey;
import com.ibm.icu.text.Collator;
import com.ibm.icu.text.RuleBasedCollator;
import com.ibm.icu.text.StringSearch;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.VersionInfo;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToLongFunction;
import java.util.stream.Stream;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkException;
import org.apache.spark.SparkRuntimeException;
import org.apache.spark.unsafe.types.UTF8String;
import scala.collection.immutable.Map.;

public final class CollationFactory {
   public static final String CATALOG = "SYSTEM";
   public static final String SCHEMA = "BUILTIN";
   public static final String PROVIDER_SPARK = "spark";
   public static final String PROVIDER_ICU = "icu";
   public static final String PROVIDER_NULL = "null";
   public static final List SUPPORTED_PROVIDERS = List.of("spark", "icu");
   public static final String PAD_ATTRIBUTE_EMPTY = "NO_PAD";
   public static final String PAD_ATTRIBUTE_RTRIM = "RTRIM";
   public static final int UTF8_BINARY_COLLATION_ID;
   public static final int UTF8_LCASE_COLLATION_ID;
   public static final int UNICODE_COLLATION_ID;
   public static final int UNICODE_CI_COLLATION_ID;
   public static final int INDETERMINATE_COLLATION_ID = -1;

   public static StringSearch getStringSearch(UTF8String targetUTF8String, UTF8String patternUTF8String, int collationId) {
      return getStringSearch(targetUTF8String.toValidString(), patternUTF8String.toValidString(), collationId);
   }

   public static StringSearch getStringSearch(String targetString, String patternString, int collationId) {
      CharacterIterator target = new StringCharacterIterator(targetString);
      Collator collator = fetchCollation(collationId).getCollator();
      return new StringSearch(patternString, target, (RuleBasedCollator)collator);
   }

   public static StringSearch getStringSearch(UTF8String targetUTF8String, UTF8String patternUTF8String) {
      return new StringSearch(patternUTF8String.toValidString(), targetUTF8String.toValidString());
   }

   public static int collationNameToId(String collationName) throws SparkException {
      return CollationFactory.Collation.CollationSpec.collationNameToId(collationName);
   }

   public static String resolveFullyQualifiedName(String[] collationName) throws SparkException {
      if (collationName.length == 1) {
         return collationName[0];
      } else if (collationName.length == 3 && "SYSTEM".equalsIgnoreCase(collationName[0]) && "BUILTIN".equalsIgnoreCase(collationName[1])) {
         return collationName[2];
      } else {
         throw collationInvalidNameException(collationName.length != 0 ? collationName[collationName.length - 1] : "");
      }
   }

   public static SparkException collationInvalidNameException(String collationName) {
      Map<String, String> params = new HashMap();
      int maxSuggestions = 3;
      params.put("collationName", collationName);
      params.put("proposals", getClosestSuggestionsOnInvalidName(collationName, 3));
      return new SparkException("COLLATION_INVALID_NAME", SparkException.constructMessageParams(params), (Throwable)null);
   }

   public static String fullyQualifiedName(int collationId) {
      if (collationId == -1) {
         return CollationFactory.Collation.CollationSpec.INDETERMINATE_COLLATION.collationName;
      } else {
         Collation.CollationSpec.DefinitionOrigin definitionOrigin = CollationFactory.Collation.CollationSpec.getDefinitionOrigin(collationId);

         assert definitionOrigin == CollationFactory.Collation.CollationSpec.DefinitionOrigin.PREDEFINED;

         return String.format("%s.%s.%s", "SYSTEM", "BUILTIN", CollationFactory.Collation.CollationSpec.fetchCollation(collationId).collationName);
      }
   }

   public static boolean isCaseInsensitive(int collationId) {
      if (CollationFactory.Collation.CollationSpec.getImplementationProvider(collationId) != CollationFactory.Collation.CollationSpec.ImplementationProvider.ICU) {
         return false;
      } else {
         return CollationFactory.Collation.CollationSpecICU.fromCollationId(collationId).caseSensitivity == CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CI;
      }
   }

   public static boolean isAccentInsensitive(int collationId) {
      if (CollationFactory.Collation.CollationSpec.getImplementationProvider(collationId) != CollationFactory.Collation.CollationSpec.ImplementationProvider.ICU) {
         return false;
      } else {
         return CollationFactory.Collation.CollationSpecICU.fromCollationId(collationId).accentSensitivity == CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AI;
      }
   }

   public static void assertValidProvider(String provider) throws SparkException {
      if (!SUPPORTED_PROVIDERS.contains(provider.toLowerCase())) {
         Map<String, String> params = Map.of("provider", provider, "supportedProviders", String.join(", ", SUPPORTED_PROVIDERS));
         throw new SparkException("COLLATION_INVALID_PROVIDER", SparkException.constructMessageParams(params), (Throwable)null);
      }
   }

   public static Collation fetchCollation(int collationId) {
      return CollationFactory.Collation.CollationSpec.fetchCollation(collationId);
   }

   public static Collation fetchCollation(String collationName) throws SparkException {
      return fetchCollation(collationNameToId(collationName));
   }

   public static String[] getICULocaleNames() {
      return CollationFactory.Collation.CollationSpecICU.ICULocaleNames;
   }

   public static UTF8String applyTrimmingPolicy(UTF8String input, int collationId) {
      return CollationFactory.Collation.CollationSpec.applyTrimmingPolicy(input, collationId);
   }

   public static boolean ignoresSpacesInTrimFunctions(int collationId, boolean isLTrim, boolean isRTrim) {
      return CollationFactory.Collation.CollationSpec.ignoresSpacesInTrimFunctions(collationId, isLTrim, isRTrim);
   }

   public static UTF8String getCollationKey(UTF8String input, int collationId) {
      Collation collation = fetchCollation(collationId);
      if (collation.supportsSpaceTrimming) {
         input = CollationFactory.Collation.CollationSpec.applyTrimmingPolicy(input, collationId);
      }

      if (collation.isUtf8BinaryType) {
         return input;
      } else if (collation.isUtf8LcaseType) {
         return CollationAwareUTF8String.lowerCaseCodePoints(input);
      } else {
         CollationKey collationKey = collation.getCollator().getCollationKey(input.toValidString());
         return UTF8String.fromBytes(collationKey.toByteArray());
      }
   }

   public static byte[] getCollationKeyBytes(UTF8String input, int collationId) {
      Collation collation = fetchCollation(collationId);
      if (collation.supportsSpaceTrimming) {
         input = CollationFactory.Collation.CollationSpec.applyTrimmingPolicy(input, collationId);
      }

      if (collation.isUtf8BinaryType) {
         return input.getBytes();
      } else {
         return collation.isUtf8LcaseType ? CollationAwareUTF8String.lowerCaseCodePoints(input).getBytes() : collation.getCollator().getCollationKey(input.toValidString()).toByteArray();
      }
   }

   public static String getClosestSuggestionsOnInvalidName(String collationName, int maxSuggestions) {
      String[] validRootNames;
      String[] validModifiers;
      if (collationName.startsWith("UTF8_")) {
         validRootNames = new String[]{CollationFactory.Collation.CollationSpecUTF8.UTF8_BINARY_COLLATION.collationName, CollationFactory.Collation.CollationSpecUTF8.UTF8_LCASE_COLLATION.collationName};
         validModifiers = new String[]{"_RTRIM"};
      } else {
         validRootNames = getICULocaleNames();
         validModifiers = new String[]{"_CI", "_AI", "_CS", "_AS", "_RTRIM"};
      }

      boolean foundModifier = true;
      String localeName = collationName.toUpperCase();
      List<String> modifiers = new ArrayList();

      while(foundModifier) {
         foundModifier = false;

         for(String modifier : validModifiers) {
            if (localeName.endsWith(modifier)) {
               modifiers.add(modifier);
               localeName = localeName.substring(0, localeName.length() - modifier.length());
               foundModifier = true;
               break;
            }
         }
      }

      Collections.reverse(modifiers);
      modifiers = modifiers.stream().distinct().toList();
      if (modifiers.contains("_CI") && modifiers.contains("_CS")) {
         modifiers = modifiers.stream().filter((m) -> !m.equals("_CI")).toList();
      }

      if (modifiers.contains("_AI") && modifiers.contains("_AS")) {
         modifiers = modifiers.stream().filter((m) -> !m.equals("_AI")).toList();
      }

      Comparator<String> distanceComparator = (c1, c2) -> {
         int distance1 = UTF8String.fromString(c1.toUpperCase()).levenshteinDistance(UTF8String.fromString(localeName));
         int distance2 = UTF8String.fromString(c2.toUpperCase()).levenshteinDistance(UTF8String.fromString(localeName));
         return Integer.compare(distance1, distance2);
      };
      String[] rootNamesByDistance = (String[])Arrays.copyOf(validRootNames, validRootNames.length);
      Arrays.sort(rootNamesByDistance, distanceComparator);
      Function<String, Boolean> isCollationNameValid = (name) -> {
         try {
            collationNameToId(name);
            return true;
         } catch (SparkException var2) {
            return false;
         }
      };
      int suggestionThreshold = 3;
      ArrayList<String> suggestions = new ArrayList(maxSuggestions);

      for(int i = 0; i < maxSuggestions; ++i) {
         String suggestion = rootNamesByDistance[i] + String.join("", modifiers);

         assert (Boolean)isCollationNameValid.apply(suggestion);

         if (suggestions.isEmpty()) {
            suggestions.add(suggestion);
         } else {
            int distance = UTF8String.fromString(suggestion.toUpperCase()).levenshteinDistance(UTF8String.fromString(collationName.toUpperCase()));
            if (distance >= 3) {
               break;
            }

            suggestions.add(suggestion);
         }
      }

      return String.join(", ", suggestions);
   }

   public static List listCollations() {
      return CollationFactory.Collation.CollationSpec.listCollations();
   }

   public static CollationMeta loadCollationMeta(CollationIdentifier collationIdentifier) {
      return CollationFactory.Collation.CollationSpec.loadCollationMeta(collationIdentifier);
   }

   static {
      UTF8_BINARY_COLLATION_ID = CollationFactory.Collation.CollationSpecUTF8.UTF8_BINARY_COLLATION_ID;
      UTF8_LCASE_COLLATION_ID = CollationFactory.Collation.CollationSpecUTF8.UTF8_LCASE_COLLATION_ID;
      UNICODE_COLLATION_ID = CollationFactory.Collation.CollationSpecICU.UNICODE_COLLATION_ID;
      UNICODE_CI_COLLATION_ID = CollationFactory.Collation.CollationSpecICU.UNICODE_CI_COLLATION_ID;
   }

   public static class CollationIdentifier {
      private final String provider;
      private final String name;
      private final String version;

      public CollationIdentifier(String provider, String collationName, String version) {
         this.provider = provider;
         this.name = collationName;
         this.version = version;
      }

      public static CollationIdentifier fromString(String identifier) {
         long numDots = identifier.chars().filter((ch) -> ch == 46).count();

         assert numDots > 0L;

         if (numDots == 1L) {
            String[] parts = identifier.split("\\.", 2);
            return new CollationIdentifier(parts[0], parts[1], (String)null);
         } else {
            String[] parts = identifier.split("\\.", 3);
            return new CollationIdentifier(parts[0], parts[1], parts[2]);
         }
      }

      public String toStringWithoutVersion() {
         return String.format("%s.%s", this.provider, this.name);
      }

      public String getProvider() {
         return this.provider;
      }

      public String getName() {
         return this.name;
      }

      public Optional getVersion() {
         return Optional.ofNullable(this.version);
      }
   }

   public static record CollationMeta(String catalog, String schema, String collationName, String language, String country, String icuVersion, String padAttribute, boolean accentSensitivity, boolean caseSensitivity, String spaceTrimming) {
   }

   public static class Collation {
      public final String collationName;
      public final String provider;
      private final Collator collator;
      public final Comparator comparator;
      public final String version;
      public final ToLongFunction hashFunction;
      public final BiFunction equalsFunction;
      public final boolean supportsBinaryEquality;
      public final boolean supportsBinaryOrdering;
      public final boolean supportsLowercaseEquality;
      public final boolean supportsSpaceTrimming;
      public final boolean isUtf8BinaryType;
      public final boolean isUtf8LcaseType;

      public Collation(String collationName, String provider, Collator collator, Comparator comparator, String version, ToLongFunction hashFunction, BiFunction equalsFunction, boolean isUtf8BinaryType, boolean isUtf8LcaseType, boolean supportsSpaceTrimming) {
         this.collationName = collationName;
         this.provider = provider;
         this.collator = collator;
         this.comparator = comparator;
         this.version = version;
         this.hashFunction = hashFunction;
         this.isUtf8BinaryType = isUtf8BinaryType;
         this.isUtf8LcaseType = isUtf8LcaseType;
         this.equalsFunction = equalsFunction;
         this.supportsSpaceTrimming = supportsSpaceTrimming;
         this.supportsBinaryEquality = !supportsSpaceTrimming && isUtf8BinaryType;
         this.supportsBinaryOrdering = !supportsSpaceTrimming && isUtf8BinaryType;
         this.supportsLowercaseEquality = !supportsSpaceTrimming && isUtf8LcaseType;

         assert !this.supportsBinaryEquality || !this.supportsLowercaseEquality;

         assert CollationFactory.SUPPORTED_PROVIDERS.contains(provider) || provider.equals("null");

      }

      public Collator getCollator() {
         return this.collator;
      }

      public CollationIdentifier identifier() {
         return new CollationIdentifier(this.provider, this.collationName, this.version);
      }

      private abstract static class CollationSpec {
         private static final int DEFINITION_ORIGIN_OFFSET = 30;
         private static final int DEFINITION_ORIGIN_MASK = 1;
         protected static final int IMPLEMENTATION_PROVIDER_OFFSET = 29;
         protected static final int IMPLEMENTATION_PROVIDER_MASK = 1;
         protected static final int SPACE_TRIMMING_OFFSET = 18;
         protected static final int SPACE_TRIMMING_MASK = 1;
         private static final int INDETERMINATE_COLLATION_ID = -1;
         private static final Collation INDETERMINATE_COLLATION = new IndeterminateCollation();
         private static final Map collationMap = new ConcurrentHashMap();
         protected SpaceTrimming spaceTrimming;

         protected static ImplementationProvider getImplementationProvider(int collationId) {
            return collationId == -1 ? CollationFactory.Collation.CollationSpec.ImplementationProvider.INDETERMINATE : CollationFactory.Collation.CollationSpec.ImplementationProvider.values()[CollationFactory.Collation.SpecifierUtils.getSpecValue(collationId, 29, 1)];
         }

         private static DefinitionOrigin getDefinitionOrigin(int collationId) {
            return CollationFactory.Collation.CollationSpec.DefinitionOrigin.values()[CollationFactory.Collation.SpecifierUtils.getSpecValue(collationId, 30, 1)];
         }

         protected static SpaceTrimming getSpaceTrimming(int collationId) {
            return CollationFactory.Collation.CollationSpec.SpaceTrimming.values()[CollationFactory.Collation.SpecifierUtils.getSpecValue(collationId, 18, 1)];
         }

         protected static UTF8String applyTrimmingPolicy(UTF8String s, int collationId) {
            return applyTrimmingPolicy(s, getSpaceTrimming(collationId));
         }

         protected static boolean ignoresSpacesInTrimFunctions(int collationId, boolean isLTrim, boolean isRTrim) {
            return isRTrim && getSpaceTrimming(collationId) == CollationFactory.Collation.CollationSpec.SpaceTrimming.RTRIM;
         }

         protected static UTF8String applyTrimmingPolicy(UTF8String s, SpaceTrimming spaceTrimming) {
            return spaceTrimming == CollationFactory.Collation.CollationSpec.SpaceTrimming.RTRIM ? s.trimRight() : s;
         }

         private static Collation fetchCollation(int collationId) {
            assert collationId == -1 || getDefinitionOrigin(collationId) == CollationFactory.Collation.CollationSpec.DefinitionOrigin.PREDEFINED;

            if (collationId == CollationFactory.UTF8_BINARY_COLLATION_ID) {
               return CollationFactory.Collation.CollationSpecUTF8.UTF8_BINARY_COLLATION;
            } else if (collationMap.containsKey(collationId)) {
               return (Collation)collationMap.get(collationId);
            } else if (collationId == -1) {
               return INDETERMINATE_COLLATION;
            } else {
               ImplementationProvider implementationProvider = getImplementationProvider(collationId);
               CollationSpec spec;
               if (implementationProvider == CollationFactory.Collation.CollationSpec.ImplementationProvider.UTF8_BINARY) {
                  spec = CollationFactory.Collation.CollationSpecUTF8.fromCollationId(collationId);
               } else {
                  spec = CollationFactory.Collation.CollationSpecICU.fromCollationId(collationId);
               }

               Collation collation = spec.buildCollation();
               collationMap.put(collationId, collation);
               return collation;
            }
         }

         private static int collationNameToId(String collationName) throws SparkException {
            String collationNameUpper = collationName.toUpperCase();
            return collationNameUpper.startsWith("UTF8_") ? CollationFactory.Collation.CollationSpecUTF8.collationNameToId(collationName, collationNameUpper) : CollationFactory.Collation.CollationSpecICU.collationNameToId(collationName, collationNameUpper);
         }

         protected String getPadding() {
            if (this.spaceTrimming == CollationFactory.Collation.CollationSpec.SpaceTrimming.RTRIM) {
               return "RTRIM";
            } else {
               assert this.spaceTrimming == CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE;

               return "NO_PAD";
            }
         }

         protected abstract Collation buildCollation();

         protected abstract CollationMeta buildCollationMeta();

         protected abstract String normalizedCollationName();

         static List listCollations() {
            return Stream.concat(CollationFactory.Collation.CollationSpecUTF8.listCollations().stream(), CollationFactory.Collation.CollationSpecICU.listCollations().stream()).toList();
         }

         static CollationMeta loadCollationMeta(CollationIdentifier collationIdentifier) {
            CollationMeta collationSpecUTF8 = CollationFactory.Collation.CollationSpecUTF8.loadCollationMeta(collationIdentifier);
            return collationSpecUTF8 == null ? CollationFactory.Collation.CollationSpecICU.loadCollationMeta(collationIdentifier) : collationSpecUTF8;
         }

         private static enum DefinitionOrigin {
            PREDEFINED,
            USER_DEFINED;

            // $FF: synthetic method
            private static DefinitionOrigin[] $values() {
               return new DefinitionOrigin[]{PREDEFINED, USER_DEFINED};
            }
         }

         protected static enum ImplementationProvider {
            UTF8_BINARY,
            ICU,
            INDETERMINATE;

            // $FF: synthetic method
            private static ImplementationProvider[] $values() {
               return new ImplementationProvider[]{UTF8_BINARY, ICU, INDETERMINATE};
            }
         }

         protected static enum SpaceTrimming {
            NONE,
            RTRIM;

            // $FF: synthetic method
            private static SpaceTrimming[] $values() {
               return new SpaceTrimming[]{NONE, RTRIM};
            }
         }
      }

      private static class CollationSpecUTF8 extends CollationSpec {
         private static final int CASE_SENSITIVITY_OFFSET = 0;
         private static final int CASE_SENSITIVITY_MASK = 1;
         private static final int UTF8_BINARY_COLLATION_ID;
         private static final int UTF8_LCASE_COLLATION_ID;
         protected static Collation UTF8_BINARY_COLLATION;
         protected static Collation UTF8_LCASE_COLLATION;
         private final CaseSensitivity caseSensitivity;
         private final int collationId;

         private CollationSpecUTF8(CaseSensitivity caseSensitivity, CollationSpec.SpaceTrimming spaceTrimming) {
            this.caseSensitivity = caseSensitivity;
            this.spaceTrimming = spaceTrimming;
            int collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(0, 0, caseSensitivity);
            this.collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 18, spaceTrimming);
         }

         private static int collationNameToId(String originalName, String collationName) throws SparkException {
            int baseId;
            String collationNamePrefix;
            if (collationName.startsWith(UTF8_BINARY_COLLATION.collationName)) {
               baseId = UTF8_BINARY_COLLATION_ID;
               collationNamePrefix = UTF8_BINARY_COLLATION.collationName;
            } else {
               if (!collationName.startsWith(UTF8_LCASE_COLLATION.collationName)) {
                  throw CollationFactory.collationInvalidNameException(originalName);
               }

               baseId = UTF8_LCASE_COLLATION_ID;
               collationNamePrefix = UTF8_LCASE_COLLATION.collationName;
            }

            String remainingSpecifiers = collationName.substring(collationNamePrefix.length());
            if (remainingSpecifiers.isEmpty()) {
               return baseId;
            } else if (!remainingSpecifiers.startsWith("_")) {
               throw CollationFactory.collationInvalidNameException(originalName);
            } else {
               CollationSpec.SpaceTrimming spaceTrimming = CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE;
               String remainingSpec = remainingSpecifiers.substring(1);
               if (remainingSpec.equals("RTRIM")) {
                  spaceTrimming = CollationFactory.Collation.CollationSpec.SpaceTrimming.RTRIM;
                  return CollationFactory.Collation.SpecifierUtils.setSpecValue(baseId, 18, spaceTrimming);
               } else {
                  throw CollationFactory.collationInvalidNameException(originalName);
               }
            }
         }

         private static CollationSpecUTF8 fromCollationId(int collationId) {
            int caseConversionOrdinal = CollationFactory.Collation.SpecifierUtils.getSpecValue(collationId, 0, 1);
            int spaceTrimmingOrdinal = getSpaceTrimming(collationId).ordinal();

            assert isValidCollationId(collationId);

            return new CollationSpecUTF8(CollationFactory.Collation.CollationSpecUTF8.CaseSensitivity.values()[caseConversionOrdinal], CollationFactory.Collation.CollationSpec.SpaceTrimming.values()[spaceTrimmingOrdinal]);
         }

         private static boolean isValidCollationId(int collationId) {
            collationId = CollationFactory.Collation.SpecifierUtils.removeSpec(collationId, 18, 1);
            collationId = CollationFactory.Collation.SpecifierUtils.removeSpec(collationId, 0, 1);
            return collationId == 0;
         }

         protected Collation buildCollation() {
            if (this.caseSensitivity == CollationFactory.Collation.CollationSpecUTF8.CaseSensitivity.UNSPECIFIED) {
               boolean supportsSpaceTrimming = this.spaceTrimming != CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE;
               BiFunction<UTF8String, UTF8String, Boolean> equalsFunction;
               Comparator<UTF8String> comparator;
               ToLongFunction<UTF8String> hashFunction;
               if (this.spaceTrimming == CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE) {
                  comparator = UTF8String::binaryCompare;
                  hashFunction = (s) -> (long)s.hashCode();
                  equalsFunction = UTF8String::equals;
               } else {
                  comparator = (s1, s2) -> applyTrimmingPolicy(s1, this.spaceTrimming).binaryCompare(applyTrimmingPolicy(s2, this.spaceTrimming));
                  hashFunction = (s) -> (long)applyTrimmingPolicy(s, this.spaceTrimming).hashCode();
                  equalsFunction = (s1, s2) -> applyTrimmingPolicy(s1, this.spaceTrimming).equals(applyTrimmingPolicy(s2, this.spaceTrimming));
               }

               return new Collation(this.normalizedCollationName(), "spark", (Collator)null, comparator, CollationFactory.Collation.CollationSpecICU.ICU_VERSION, hashFunction, equalsFunction, true, false, this.spaceTrimming != CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE);
            } else {
               Comparator<UTF8String> comparator;
               ToLongFunction<UTF8String> hashFunction;
               if (this.spaceTrimming == CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE) {
                  comparator = CollationAwareUTF8String::compareLowerCase;
                  hashFunction = (s) -> (long)CollationAwareUTF8String.lowerCaseCodePoints(s).hashCode();
               } else {
                  comparator = (s1, s2) -> CollationAwareUTF8String.compareLowerCase(applyTrimmingPolicy(s1, this.spaceTrimming), applyTrimmingPolicy(s2, this.spaceTrimming));
                  hashFunction = (s) -> (long)CollationAwareUTF8String.lowerCaseCodePoints(applyTrimmingPolicy(s, this.spaceTrimming)).hashCode();
               }

               return new Collation(this.normalizedCollationName(), "spark", (Collator)null, comparator, CollationFactory.Collation.CollationSpecICU.ICU_VERSION, hashFunction, (s1, s2) -> comparator.compare(s1, s2) == 0, false, true, this.spaceTrimming != CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE);
            }
         }

         protected CollationMeta buildCollationMeta() {
            return this.caseSensitivity == CollationFactory.Collation.CollationSpecUTF8.CaseSensitivity.UNSPECIFIED ? new CollationMeta("SYSTEM", "BUILTIN", this.normalizedCollationName(), (String)null, (String)null, (String)null, this.getPadding(), true, true, this.spaceTrimming.toString()) : new CollationMeta("SYSTEM", "BUILTIN", this.normalizedCollationName(), (String)null, (String)null, (String)null, this.getPadding(), true, false, this.spaceTrimming.toString());
         }

         protected String normalizedCollationName() {
            StringBuilder builder = new StringBuilder();
            if (this.caseSensitivity == CollationFactory.Collation.CollationSpecUTF8.CaseSensitivity.UNSPECIFIED) {
               builder.append("UTF8_BINARY");
            } else {
               builder.append("UTF8_LCASE");
            }

            if (this.spaceTrimming != CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE) {
               builder.append('_');
               builder.append(this.spaceTrimming.toString());
            }

            return builder.toString();
         }

         static List listCollations() {
            CollationIdentifier UTF8_BINARY_COLLATION_IDENT = new CollationIdentifier("spark", "UTF8_BINARY", CollationFactory.Collation.CollationSpecICU.ICU_VERSION);
            CollationIdentifier UTF8_LCASE_COLLATION_IDENT = new CollationIdentifier("spark", "UTF8_LCASE", CollationFactory.Collation.CollationSpecICU.ICU_VERSION);
            CollationIdentifier UTF8_BINARY_RTRIM_COLLATION_IDENT = new CollationIdentifier("spark", "UTF8_BINARY_RTRIM", CollationFactory.Collation.CollationSpecICU.ICU_VERSION);
            CollationIdentifier UTF8_LCASE_RTRIM_COLLATION_IDENT = new CollationIdentifier("spark", "UTF8_LCASE_RTRIM", CollationFactory.Collation.CollationSpecICU.ICU_VERSION);
            return Arrays.asList(UTF8_BINARY_COLLATION_IDENT, UTF8_LCASE_COLLATION_IDENT, UTF8_BINARY_RTRIM_COLLATION_IDENT, UTF8_LCASE_RTRIM_COLLATION_IDENT);
         }

         static CollationMeta loadCollationMeta(CollationIdentifier collationIdentifier) {
            try {
               int collationId = collationNameToId(collationIdentifier.name, collationIdentifier.name.toUpperCase());
               return fromCollationId(collationId).buildCollationMeta();
            } catch (SparkException var2) {
               return null;
            }
         }

         static {
            UTF8_BINARY_COLLATION_ID = (new CollationSpecUTF8(CollationFactory.Collation.CollationSpecUTF8.CaseSensitivity.UNSPECIFIED, CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE)).collationId;
            UTF8_LCASE_COLLATION_ID = (new CollationSpecUTF8(CollationFactory.Collation.CollationSpecUTF8.CaseSensitivity.LCASE, CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE)).collationId;
            UTF8_BINARY_COLLATION = (new CollationSpecUTF8(CollationFactory.Collation.CollationSpecUTF8.CaseSensitivity.UNSPECIFIED, CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE)).buildCollation();
            UTF8_LCASE_COLLATION = (new CollationSpecUTF8(CollationFactory.Collation.CollationSpecUTF8.CaseSensitivity.LCASE, CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE)).buildCollation();
         }

         private static enum CaseSensitivity {
            UNSPECIFIED,
            LCASE;

            // $FF: synthetic method
            private static CaseSensitivity[] $values() {
               return new CaseSensitivity[]{UNSPECIFIED, LCASE};
            }
         }
      }

      private static class CollationSpecICU extends CollationSpec {
         private static final int CASE_SENSITIVITY_OFFSET = 17;
         private static final int CASE_SENSITIVITY_MASK = 1;
         private static final int ACCENT_SENSITIVITY_OFFSET = 16;
         private static final int ACCENT_SENSITIVITY_MASK = 1;
         private static final String[] ICULocaleNames;
         private static final Map ICULocaleMap = new HashMap();
         private static final Map ICULocaleMapUppercase = new HashMap();
         private static final Map ICULocaleToId = new HashMap();
         private static final String ICU_VERSION;
         private static final int UNICODE_COLLATION_ID;
         private static final int UNICODE_CI_COLLATION_ID;
         private final CaseSensitivity caseSensitivity;
         private final AccentSensitivity accentSensitivity;
         private final String locale;
         private final int collationId;

         private CollationSpecICU(String locale, CaseSensitivity caseSensitivity, AccentSensitivity accentSensitivity, CollationSpec.SpaceTrimming spaceTrimming) {
            this.locale = locale;
            this.caseSensitivity = caseSensitivity;
            this.accentSensitivity = accentSensitivity;
            this.spaceTrimming = spaceTrimming;
            int collationId = (Integer)ICULocaleToId.get(locale);
            collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 29, CollationFactory.Collation.CollationSpec.ImplementationProvider.ICU);
            collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 17, caseSensitivity);
            collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 16, accentSensitivity);
            collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 18, spaceTrimming);
            this.collationId = collationId;
         }

         private static int collationNameToId(String originalName, String collationName) throws SparkException {
            int lastPos = -1;

            for(int i = 1; i <= collationName.length(); ++i) {
               String localeName = collationName.substring(0, i);
               if (ICULocaleMapUppercase.containsKey(localeName)) {
                  lastPos = i;
               }
            }

            if (lastPos == -1) {
               throw CollationFactory.collationInvalidNameException(originalName);
            } else {
               String locale = collationName.substring(0, lastPos);
               int collationId = (Integer)ICULocaleToId.get(ICULocaleMapUppercase.get(locale));
               collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 29, CollationFactory.Collation.CollationSpec.ImplementationProvider.ICU);
               if (collationName.equals(locale)) {
                  return collationId;
               } else if (collationName.charAt(locale.length()) != '_') {
                  throw CollationFactory.collationInvalidNameException(originalName);
               } else {
                  String remainingSpecifiers = collationName.substring(lastPos + 1);
                  boolean isCaseSpecifierSet = false;
                  boolean isAccentSpecifierSet = false;
                  boolean isSpaceTrimmingSpecifierSet = false;
                  CaseSensitivity caseSensitivity = CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CS;
                  AccentSensitivity accentSensitivity = CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AS;
                  CollationSpec.SpaceTrimming spaceTrimming = CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE;
                  String[] specifiers = remainingSpecifiers.split("_");

                  for(String specifier : specifiers) {
                     switch (specifier) {
                        case "CI":
                        case "CS":
                           if (isCaseSpecifierSet) {
                              throw CollationFactory.collationInvalidNameException(originalName);
                           }

                           caseSensitivity = CollationFactory.Collation.CollationSpecICU.CaseSensitivity.valueOf(specifier);
                           isCaseSpecifierSet = true;
                           break;
                        case "AI":
                        case "AS":
                           if (isAccentSpecifierSet) {
                              throw CollationFactory.collationInvalidNameException(originalName);
                           }

                           accentSensitivity = CollationFactory.Collation.CollationSpecICU.AccentSensitivity.valueOf(specifier);
                           isAccentSpecifierSet = true;
                           break;
                        case "RTRIM":
                           if (isSpaceTrimmingSpecifierSet) {
                              throw CollationFactory.collationInvalidNameException(originalName);
                           }

                           spaceTrimming = CollationFactory.Collation.CollationSpec.SpaceTrimming.valueOf(specifier);
                           isSpaceTrimmingSpecifierSet = true;
                           break;
                        default:
                           throw CollationFactory.collationInvalidNameException(originalName);
                     }
                  }

                  collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 17, caseSensitivity);
                  collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 16, accentSensitivity);
                  collationId = CollationFactory.Collation.SpecifierUtils.setSpecValue(collationId, 18, spaceTrimming);
                  return collationId;
               }
            }
         }

         private static CollationSpecICU fromCollationId(int collationId) {
            int spaceTrimmingOrdinal = CollationFactory.Collation.SpecifierUtils.getSpecValue(collationId, 18, 1);
            int caseSensitivityOrdinal = CollationFactory.Collation.SpecifierUtils.getSpecValue(collationId, 17, 1);
            int accentSensitivityOrdinal = CollationFactory.Collation.SpecifierUtils.getSpecValue(collationId, 16, 1);
            collationId = CollationFactory.Collation.SpecifierUtils.removeSpec(collationId, 29, 1);
            collationId = CollationFactory.Collation.SpecifierUtils.removeSpec(collationId, 18, 1);
            collationId = CollationFactory.Collation.SpecifierUtils.removeSpec(collationId, 17, 1);
            collationId = CollationFactory.Collation.SpecifierUtils.removeSpec(collationId, 16, 1);

            assert collationId >= 0 && collationId < ICULocaleNames.length;

            CaseSensitivity caseSensitivity = CollationFactory.Collation.CollationSpecICU.CaseSensitivity.values()[caseSensitivityOrdinal];
            AccentSensitivity accentSensitivity = CollationFactory.Collation.CollationSpecICU.AccentSensitivity.values()[accentSensitivityOrdinal];
            CollationSpec.SpaceTrimming spaceTrimming = CollationFactory.Collation.CollationSpec.SpaceTrimming.values()[spaceTrimmingOrdinal];
            String locale = ICULocaleNames[collationId];
            return new CollationSpecICU(locale, caseSensitivity, accentSensitivity, spaceTrimming);
         }

         protected Collation buildCollation() {
            ULocale.Builder builder = new ULocale.Builder();
            builder.setLocale((ULocale)ICULocaleMap.get(this.locale));
            if (this.caseSensitivity == CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CS && this.accentSensitivity == CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AS) {
               builder.setUnicodeLocaleKeyword("ks", "level3");
            } else if (this.caseSensitivity == CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CS && this.accentSensitivity == CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AI) {
               builder.setUnicodeLocaleKeyword("ks", "level1").setUnicodeLocaleKeyword("kc", "true");
            } else if (this.caseSensitivity == CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CI && this.accentSensitivity == CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AS) {
               builder.setUnicodeLocaleKeyword("ks", "level2");
            } else if (this.caseSensitivity == CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CI && this.accentSensitivity == CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AI) {
               builder.setUnicodeLocaleKeyword("ks", "level1");
            }

            ULocale resultLocale = builder.build();
            Collator collator = Collator.getInstance(resultLocale);
            collator.freeze();
            Comparator<UTF8String> comparator;
            ToLongFunction<UTF8String> hashFunction;
            if (this.spaceTrimming == CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE) {
               hashFunction = (s) -> (long)collator.getCollationKey(s.toValidString()).hashCode();
               comparator = (s1, s2) -> collator.compare(s1.toValidString(), s2.toValidString());
            } else {
               comparator = (s1, s2) -> collator.compare(applyTrimmingPolicy(s1, this.spaceTrimming).toValidString(), applyTrimmingPolicy(s2, this.spaceTrimming).toValidString());
               hashFunction = (s) -> (long)collator.getCollationKey(applyTrimmingPolicy(s, this.spaceTrimming).toValidString()).hashCode();
            }

            return new Collation(this.normalizedCollationName(), "icu", collator, comparator, ICU_VERSION, hashFunction, (s1, s2) -> comparator.compare(s1, s2) == 0, false, false, this.spaceTrimming != CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE);
         }

         protected CollationMeta buildCollationMeta() {
            String language = ((ULocale)ICULocaleMap.get(this.locale)).getDisplayLanguage();
            String country = ((ULocale)ICULocaleMap.get(this.locale)).getDisplayCountry();
            return new CollationMeta("SYSTEM", "BUILTIN", this.normalizedCollationName(), language.isEmpty() ? null : language, country.isEmpty() ? null : country, VersionInfo.ICU_VERSION.toString(), this.getPadding(), this.accentSensitivity == CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AS, this.caseSensitivity == CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CS, this.spaceTrimming.toString());
         }

         protected String normalizedCollationName() {
            StringBuilder builder = new StringBuilder();
            builder.append(this.locale);
            if (this.caseSensitivity != CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CS) {
               builder.append('_');
               builder.append(this.caseSensitivity.toString());
            }

            if (this.accentSensitivity != CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AS) {
               builder.append('_');
               builder.append(this.accentSensitivity.toString());
            }

            if (this.spaceTrimming != CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE) {
               builder.append('_');
               builder.append(this.spaceTrimming.toString());
            }

            return builder.toString();
         }

         private static List allCollationNames() {
            List<String> collationNames = new ArrayList();
            List<String> caseAccentSpecifiers = Arrays.asList("", "_AI", "_CI", "_CI_AI");
            List<String> trimmingSpecifiers = Arrays.asList("", "_RTRIM");

            for(String locale : ICULocaleToId.keySet()) {
               for(String caseAccent : caseAccentSpecifiers) {
                  for(String trimming : trimmingSpecifiers) {
                     String collationName = locale + caseAccent + trimming;
                     collationNames.add(collationName);
                  }
               }
            }

            return collationNames.stream().sorted().toList();
         }

         static List listCollations() {
            return allCollationNames().stream().map((name) -> new CollationIdentifier("icu", name, VersionInfo.ICU_VERSION.toString())).toList();
         }

         static CollationMeta loadCollationMeta(CollationIdentifier collationIdentifier) {
            try {
               int collationId = collationNameToId(collationIdentifier.name, collationIdentifier.name.toUpperCase());
               return fromCollationId(collationId).buildCollationMeta();
            } catch (SparkException var2) {
               return null;
            }
         }

         static {
            ICU_VERSION = String.format("%d.%d", VersionInfo.ICU_VERSION.getMajor(), VersionInfo.ICU_VERSION.getMinor());
            ICULocaleMap.put("UNICODE", ULocale.ROOT);
            ULocale[] locales = Collator.getAvailableULocales();

            for(ULocale locale : locales) {
               if (locale.getVariant().isEmpty()) {
                  String language = locale.getLanguage();

                  assert !language.isEmpty();

                  StringBuilder builder = new StringBuilder(language);
                  String script = locale.getScript();
                  if (!script.isEmpty()) {
                     builder.append('_');
                     builder.append(script);
                  }

                  String country = locale.getISO3Country();
                  if (!country.isEmpty()) {
                     builder.append('_');
                     builder.append(country);
                  }

                  String localeName = builder.toString();

                  assert !ICULocaleMap.containsKey(localeName);

                  ICULocaleMap.put(localeName, locale);
               }
            }

            for(String localeName : ICULocaleMap.keySet()) {
               String localeUppercase = localeName.toUpperCase();

               assert !ICULocaleMapUppercase.containsKey(localeUppercase);

               ICULocaleMapUppercase.put(localeUppercase, localeName);
            }

            ICULocaleNames = (String[])ICULocaleMap.keySet().toArray(new String[0]);
            Arrays.sort(ICULocaleNames);

            assert ICULocaleNames.length <= 4096;

            for(int i = 0; i < ICULocaleNames.length; ++i) {
               ICULocaleToId.put(ICULocaleNames[i], i);
            }

            UNICODE_COLLATION_ID = (new CollationSpecICU("UNICODE", CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CS, CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AS, CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE)).collationId;
            UNICODE_CI_COLLATION_ID = (new CollationSpecICU("UNICODE", CollationFactory.Collation.CollationSpecICU.CaseSensitivity.CI, CollationFactory.Collation.CollationSpecICU.AccentSensitivity.AS, CollationFactory.Collation.CollationSpec.SpaceTrimming.NONE)).collationId;
         }

         private static enum CaseSensitivity {
            CS,
            CI;

            // $FF: synthetic method
            private static CaseSensitivity[] $values() {
               return new CaseSensitivity[]{CS, CI};
            }
         }

         private static enum AccentSensitivity {
            AS,
            AI;

            // $FF: synthetic method
            private static AccentSensitivity[] $values() {
               return new AccentSensitivity[]{AS, AI};
            }
         }
      }

      private static class IndeterminateCollation extends Collation {
         IndeterminateCollation() {
            super("null", "null", (Collator)null, (s1, s2) -> {
               throw indeterminateError();
            }, (String)null, (s) -> {
               throw indeterminateError();
            }, (s1, s2) -> {
               throw indeterminateError();
            }, false, false, false);
         }

         public Collator getCollator() {
            throw indeterminateError();
         }

         private static SparkRuntimeException indeterminateError() {
            return new SparkRuntimeException("INDETERMINATE_COLLATION", .MODULE$.empty(), (Throwable)null, new QueryContext[0], "");
         }
      }

      private static class SpecifierUtils {
         private static int getSpecValue(int collationId, int offset, int mask) {
            return collationId >> offset & mask;
         }

         private static int removeSpec(int collationId, int offset, int mask) {
            return collationId & ~(mask << offset);
         }

         private static int setSpecValue(int collationId, int offset, Enum spec) {
            return collationId | spec.ordinal() << offset;
         }
      }
   }
}
