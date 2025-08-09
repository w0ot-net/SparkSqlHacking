package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Strings;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class StringRegistry extends DefaultRegistry {
   private final Function CASE_FN;
   private final Map CI_VALUES;

   public StringRegistry(String name, String keyName, Collection values, Function keyFn, boolean caseSensitive) {
      this(name, keyName, values, keyFn, (Function)(caseSensitive ? Functions.identity() : StringRegistry.CaseInsensitiveFunction.ENGLISH));
   }

   public StringRegistry(String name, String keyName, Collection values, Function keyFn, Function caseFn) {
      super(name, keyName, values, keyFn);
      this.CASE_FN = (Function)Assert.notNull(caseFn, "Case function cannot be null.");
      Map<String, V> m = new LinkedHashMap(this.values().size());

      for(Object value : values) {
         String key = (String)keyFn.apply(value);
         key = (String)this.CASE_FN.apply(key);
         m.put(key, value);
      }

      this.CI_VALUES = Collections.immutable(m);
   }

   public Object get(Object key) {
      String id = (String)key;
      Assert.hasText(id, "id argument cannot be null or empty.");
      V instance = (V)super.get(id);
      if (instance == null) {
         id = (String)this.CASE_FN.apply(id);
         instance = (V)this.CI_VALUES.get(id);
      }

      return instance;
   }

   private static final class CaseInsensitiveFunction implements Function {
      private static final CaseInsensitiveFunction ENGLISH;
      private final Locale LOCALE;

      private CaseInsensitiveFunction(Locale locale) {
         this.LOCALE = (Locale)Assert.notNull(locale, "Case insensitive Locale argument cannot be null.");
      }

      public String apply(String s) {
         s = (String)Assert.notNull(Strings.clean(s), "String identifier cannot be null or empty.");
         return s.toUpperCase(this.LOCALE);
      }

      static {
         ENGLISH = new CaseInsensitiveFunction(Locale.ENGLISH);
      }
   }
}
