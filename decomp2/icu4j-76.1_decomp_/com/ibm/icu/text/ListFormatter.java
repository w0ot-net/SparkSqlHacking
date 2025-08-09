package com.ibm.icu.text;

import com.ibm.icu.impl.FormattedStringBuilder;
import com.ibm.icu.impl.FormattedValueStringBuilderImpl;
import com.ibm.icu.impl.ICUCache;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.SimpleCache;
import com.ibm.icu.impl.SimpleFormatterImpl;
import com.ibm.icu.impl.Utility;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.UResourceBundle;
import java.io.InvalidObjectException;
import java.text.AttributedCharacterIterator;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;

public final class ListFormatter {
   private final String start;
   private final String middle;
   private final ULocale locale;
   private final PatternHandler patternHandler;
   private static final String compiledY = compilePattern("{0} y {1}", new StringBuilder());
   private static final String compiledE = compilePattern("{0} e {1}", new StringBuilder());
   private static final String compiledO = compilePattern("{0} o {1}", new StringBuilder());
   private static final String compiledU = compilePattern("{0} u {1}", new StringBuilder());
   private static final Pattern changeToE = Pattern.compile("(i.*|hi|hi[^ae].*)", 2);
   private static final Pattern changeToU = Pattern.compile("((o|ho|8).*|11)", 2);
   private static final String compiledVav = compilePattern("{0} ו{1}", new StringBuilder());
   private static final String compiledVavDash = compilePattern("{0} ו-{1}", new StringBuilder());
   private static final Pattern changeToVavDash = Pattern.compile("^[\\P{InHebrew}].*$");
   static Cache cache = new Cache();

   /** @deprecated */
   @Deprecated
   public ListFormatter(String two, String start, String middle, String end) {
      this(compilePattern(two, new StringBuilder()), compilePattern(start, new StringBuilder()), compilePattern(middle, new StringBuilder()), compilePattern(end, new StringBuilder()), (ULocale)null);
   }

   private ListFormatter(String two, String start, String middle, String end, ULocale locale) {
      this.start = start;
      this.middle = middle;
      this.locale = locale;
      this.patternHandler = this.createPatternHandler(two, end);
   }

   private static String compilePattern(String pattern, StringBuilder sb) {
      return SimpleFormatterImpl.compileToStringMinMaxArguments(pattern, sb, 2, 2);
   }

   public static ListFormatter getInstance(ULocale locale, Type type, Width width) {
      String styleName = typeWidthToStyleString(type, width);
      if (styleName == null) {
         throw new IllegalArgumentException("Invalid list format type/width");
      } else {
         return cache.get(locale, styleName);
      }
   }

   public static ListFormatter getInstance(Locale locale, Type type, Width width) {
      return getInstance(ULocale.forLocale(locale), type, width);
   }

   public static ListFormatter getInstance(ULocale locale) {
      return getInstance(locale, ListFormatter.Type.AND, ListFormatter.Width.WIDE);
   }

   public static ListFormatter getInstance(Locale locale) {
      return getInstance(ULocale.forLocale(locale), ListFormatter.Type.AND, ListFormatter.Width.WIDE);
   }

   public static ListFormatter getInstance() {
      return getInstance(ULocale.getDefault(ULocale.Category.FORMAT));
   }

   public String format(Object... items) {
      return this.format((Collection)Arrays.asList(items));
   }

   public String format(Collection items) {
      return this.formatImpl(items, false).toString();
   }

   public FormattedList formatToValue(Object... items) {
      return this.formatToValue((Collection)Arrays.asList(items));
   }

   public FormattedList formatToValue(Collection items) {
      return this.formatImpl(items, true).toValue();
   }

   FormattedListBuilder formatImpl(Collection items, boolean needsFields) {
      Iterator<?> it = items.iterator();
      int count = items.size();
      switch (count) {
         case 0:
            return new FormattedListBuilder("", needsFields);
         case 1:
            return new FormattedListBuilder(it.next(), needsFields);
         case 2:
            Object first = it.next();
            Object second = it.next();
            return (new FormattedListBuilder(first, needsFields)).append(this.patternHandler.getTwoPattern(String.valueOf(second)), second, 1);
         default:
            FormattedListBuilder builder = new FormattedListBuilder(it.next(), needsFields);
            builder.append(this.start, it.next(), 1);

            for(int idx = 2; idx < count - 1; ++idx) {
               builder.append(this.middle, it.next(), idx);
            }

            Object last = it.next();
            return builder.append(this.patternHandler.getEndPattern(String.valueOf(last)), last, count - 1);
      }
   }

   private PatternHandler createPatternHandler(String two, String end) {
      if (this.locale != null) {
         String language = this.locale.getLanguage();
         if (language.equals("es")) {
            boolean twoIsY = two.equals(compiledY);
            boolean endIsY = end.equals(compiledY);
            if (!twoIsY && !endIsY) {
               boolean twoIsO = two.equals(compiledO);
               boolean endIsO = end.equals(compiledO);
               if (!twoIsO && !endIsO) {
                  return new StaticHandler(two, end);
               }

               return new ContextualHandler(changeToU, twoIsO ? compiledU : two, two, endIsO ? compiledU : end, end);
            }

            return new ContextualHandler(changeToE, twoIsY ? compiledE : two, two, endIsY ? compiledE : end, end);
         } else if (language.equals("he") || language.equals("iw")) {
            boolean twoIsVav = two.equals(compiledVav);
            boolean endIsVav = end.equals(compiledVav);
            if (twoIsVav || endIsVav) {
               return new ContextualHandler(changeToVavDash, twoIsVav ? compiledVavDash : two, two, endIsVav ? compiledVavDash : end, end);
            }
         }
      }

      return new StaticHandler(two, end);
   }

   public String getPatternForNumItems(int count) {
      if (count <= 0) {
         throw new IllegalArgumentException("count must be > 0");
      } else {
         ArrayList<String> list = new ArrayList();

         for(int i = 0; i < count; ++i) {
            list.add(String.format("{%d}", i));
         }

         return this.format((Collection)list);
      }
   }

   /** @deprecated */
   @Deprecated
   public ULocale getLocale() {
      return this.locale;
   }

   static String typeWidthToStyleString(Type type, Width width) {
      switch (type) {
         case AND:
            switch (width) {
               case WIDE:
                  return "standard";
               case SHORT:
                  return "standard-short";
               case NARROW:
                  return "standard-narrow";
               default:
                  return null;
            }
         case OR:
            switch (width) {
               case WIDE:
                  return "or";
               case SHORT:
                  return "or-short";
               case NARROW:
                  return "or-narrow";
               default:
                  return null;
            }
         case UNITS:
            switch (width) {
               case WIDE:
                  return "unit";
               case SHORT:
                  return "unit-short";
               case NARROW:
                  return "unit-narrow";
            }
      }

      return null;
   }

   public static enum Type {
      AND,
      OR,
      UNITS;
   }

   public static enum Width {
      WIDE,
      SHORT,
      NARROW;
   }

   public static final class SpanField extends UFormat.SpanField {
      private static final long serialVersionUID = 3563544214705634403L;
      public static final SpanField LIST_SPAN = new SpanField("list-span");

      private SpanField(String name) {
         super(name);
      }

      /** @deprecated */
      @Deprecated
      protected Object readResolve() throws InvalidObjectException {
         if (this.getName().equals(LIST_SPAN.getName())) {
            return LIST_SPAN;
         } else {
            throw new InvalidObjectException("An invalid object.");
         }
      }
   }

   public static final class Field extends Format.Field {
      private static final long serialVersionUID = -8071145668708265437L;
      public static Field LITERAL = new Field("literal");
      public static Field ELEMENT = new Field("element");

      private Field(String name) {
         super(name);
      }

      /** @deprecated */
      @Deprecated
      protected Object readResolve() throws InvalidObjectException {
         if (this.getName().equals(LITERAL.getName())) {
            return LITERAL;
         } else if (this.getName().equals(ELEMENT.getName())) {
            return ELEMENT;
         } else {
            throw new InvalidObjectException("An invalid object.");
         }
      }
   }

   public static final class FormattedList implements FormattedValue {
      private final FormattedStringBuilder string;

      FormattedList(FormattedStringBuilder string) {
         this.string = string;
      }

      public String toString() {
         return this.string.toString();
      }

      public int length() {
         return this.string.length();
      }

      public char charAt(int index) {
         return this.string.charAt(index);
      }

      public CharSequence subSequence(int start, int end) {
         return this.string.subString(start, end);
      }

      public Appendable appendTo(Appendable appendable) {
         return Utility.appendTo(this.string, appendable);
      }

      public boolean nextPosition(ConstrainedFieldPosition cfpos) {
         return FormattedValueStringBuilderImpl.nextPosition(this.string, cfpos, (Format.Field)null);
      }

      public AttributedCharacterIterator toCharacterIterator() {
         return FormattedValueStringBuilderImpl.toCharacterIterator(this.string, (Format.Field)null);
      }
   }

   private static final class StaticHandler implements PatternHandler {
      private final String twoPattern;
      private final String endPattern;

      StaticHandler(String two, String end) {
         this.twoPattern = two;
         this.endPattern = end;
      }

      public String getTwoPattern(String text) {
         return this.twoPattern;
      }

      public String getEndPattern(String text) {
         return this.endPattern;
      }
   }

   private static final class ContextualHandler implements PatternHandler {
      private final Pattern regexp;
      private final String thenTwoPattern;
      private final String elseTwoPattern;
      private final String thenEndPattern;
      private final String elseEndPattern;

      ContextualHandler(Pattern regexp, String thenTwo, String elseTwo, String thenEnd, String elseEnd) {
         this.regexp = regexp;
         this.thenTwoPattern = thenTwo;
         this.elseTwoPattern = elseTwo;
         this.thenEndPattern = thenEnd;
         this.elseEndPattern = elseEnd;
      }

      public String getTwoPattern(String text) {
         return this.regexp.matcher(text).matches() ? this.thenTwoPattern : this.elseTwoPattern;
      }

      public String getEndPattern(String text) {
         return this.regexp.matcher(text).matches() ? this.thenEndPattern : this.elseEndPattern;
      }
   }

   static class FormattedListBuilder {
      private FormattedStringBuilder string = new FormattedStringBuilder();
      boolean needsFields;

      public FormattedListBuilder(Object start, boolean needsFields) {
         this.needsFields = needsFields;
         this.string.setAppendableField(ListFormatter.Field.LITERAL);
         this.appendElement(start, 0);
      }

      public FormattedListBuilder append(String compiledPattern, Object next, int position) {
         assert SimpleFormatterImpl.getArgumentLimit(compiledPattern) == 2;

         this.string.setAppendIndex(0);
         long state = 0L;

         while(true) {
            state = SimpleFormatterImpl.IterInternal.step(state, compiledPattern, this.string);
            if (state == -1L) {
               return this;
            }

            int argIndex = SimpleFormatterImpl.IterInternal.getArgIndex(state);
            if (argIndex == 0) {
               this.string.setAppendIndex(this.string.length());
            } else {
               this.appendElement(next, position);
            }
         }
      }

      private void appendElement(Object element, int position) {
         String elementString = element.toString();
         if (this.needsFields) {
            FormattedValueStringBuilderImpl.SpanFieldPlaceholder field = new FormattedValueStringBuilderImpl.SpanFieldPlaceholder();
            field.spanField = ListFormatter.SpanField.LIST_SPAN;
            field.normalField = ListFormatter.Field.ELEMENT;
            field.value = position;
            field.start = -1;
            field.length = elementString.length();
            this.string.append((CharSequence)elementString, (Object)field);
         } else {
            this.string.append((CharSequence)elementString, (Object)null);
         }

      }

      public void appendTo(Appendable appendable) {
         Utility.appendTo(this.string, appendable);
      }

      public int getOffset(int fieldPositionFoundIndex) {
         return FormattedValueStringBuilderImpl.findSpan(this.string, fieldPositionFoundIndex);
      }

      public String toString() {
         return this.string.toString();
      }

      public FormattedList toValue() {
         return new FormattedList(this.string);
      }
   }

   private static class Cache {
      private final ICUCache cache;

      private Cache() {
         this.cache = new SimpleCache();
      }

      public ListFormatter get(ULocale locale, String style) {
         String key = String.format("%s:%s", locale.toString(), style);
         ListFormatter result = (ListFormatter)this.cache.get(key);
         if (result == null) {
            result = load(locale, style);
            this.cache.put(key, result);
         }

         return result;
      }

      private static ListFormatter load(ULocale ulocale, String style) {
         ICUResourceBundle r = (ICUResourceBundle)UResourceBundle.getBundleInstance("com/ibm/icu/impl/data/icudata", ulocale);
         StringBuilder sb = new StringBuilder();
         return new ListFormatter(ListFormatter.compilePattern(r.getWithFallback("listPattern/" + style + "/2").getString(), sb), ListFormatter.compilePattern(r.getWithFallback("listPattern/" + style + "/start").getString(), sb), ListFormatter.compilePattern(r.getWithFallback("listPattern/" + style + "/middle").getString(), sb), ListFormatter.compilePattern(r.getWithFallback("listPattern/" + style + "/end").getString(), sb), ulocale);
      }
   }

   private interface PatternHandler {
      String getTwoPattern(String var1);

      String getEndPattern(String var1);
   }
}
