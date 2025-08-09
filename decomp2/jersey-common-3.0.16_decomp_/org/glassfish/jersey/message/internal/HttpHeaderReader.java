package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.glassfish.jersey.internal.util.collection.LRU;

public abstract class HttpHeaderReader {
   private static final ListElementCreator MATCHING_ENTITY_TAG_CREATOR = new ListElementCreator() {
      public MatchingEntityTag create(HttpHeaderReader reader) throws ParseException {
         return MatchingEntityTag.valueOf(reader);
      }
   };
   private static final MediaTypeListReader MEDIA_TYPE_LIST_READER = new MediaTypeListReader();
   private static final AcceptableMediaTypeListReader ACCEPTABLE_MEDIA_TYPE_LIST_READER = new AcceptableMediaTypeListReader();
   private static final QualitySourceMediaTypeListReader QUALITY_SOURCE_MEDIA_TYPE_LIST_READER = new QualitySourceMediaTypeListReader();
   private static final AcceptableTokenListReader ACCEPTABLE_TOKEN_LIST_READER = new AcceptableTokenListReader();
   private static final AcceptableLanguageTagListReader ACCEPTABLE_LANGUAGE_TAG_LIST_READER = new AcceptableLanguageTagListReader();
   private static final StringListReader STRING_LIST_READER = new StringListReader();

   public abstract boolean hasNext();

   public abstract boolean hasNextSeparator(char var1, boolean var2);

   public abstract Event next() throws ParseException;

   public abstract Event next(boolean var1) throws ParseException;

   protected abstract Event next(boolean var1, boolean var2) throws ParseException;

   protected abstract CharSequence nextSeparatedString(char var1, char var2) throws ParseException;

   protected abstract Event getEvent();

   public abstract CharSequence getEventValue();

   public abstract CharSequence getRemainder();

   public abstract int getIndex();

   public final CharSequence nextToken() throws ParseException {
      Event e = this.next(false);
      if (e != HttpHeaderReader.Event.Token) {
         throw new ParseException("Next event is not a Token", this.getIndex());
      } else {
         return this.getEventValue();
      }
   }

   public final void nextSeparator(char c) throws ParseException {
      Event e = this.next(false);
      if (e != HttpHeaderReader.Event.Separator) {
         throw new ParseException("Next event is not a Separator", this.getIndex());
      } else if (c != this.getEventValue().charAt(0)) {
         throw new ParseException("Expected separator '" + c + "' instead of '" + this.getEventValue().charAt(0) + "'", this.getIndex());
      }
   }

   public final CharSequence nextQuotedString() throws ParseException {
      Event e = this.next(false);
      if (e != HttpHeaderReader.Event.QuotedString) {
         throw new ParseException("Next event is not a Quoted String", this.getIndex());
      } else {
         return this.getEventValue();
      }
   }

   public final CharSequence nextTokenOrQuotedString() throws ParseException {
      return this.nextTokenOrQuotedString(false);
   }

   private CharSequence nextTokenOrQuotedString(boolean preserveBackslash) throws ParseException {
      Event e = this.next(false, preserveBackslash);
      if (e != HttpHeaderReader.Event.Token && e != HttpHeaderReader.Event.QuotedString) {
         throw new ParseException("Next event is not a Token or a Quoted String, " + this.getEventValue(), this.getIndex());
      } else {
         return this.getEventValue();
      }
   }

   public static HttpHeaderReader newInstance(String header) {
      return new HttpHeaderReaderImpl(header);
   }

   public static HttpHeaderReader newInstance(String header, boolean processComments) {
      return new HttpHeaderReaderImpl(header, processComments);
   }

   public static Date readDate(String date) throws ParseException {
      return HttpDateFormat.readDate(date);
   }

   public static int readQualityFactor(CharSequence q) throws ParseException {
      if (q != null && q.length() != 0) {
         int index = 0;
         int length = q.length();
         if (length > 5) {
            throw new ParseException("Quality value is greater than the maximum length, 5", 0);
         } else {
            char wholeNumber;
            char c = wholeNumber = q.charAt(index++);
            if (c != '0' && c != '1') {
               if (c != '.') {
                  throw new ParseException("Error parsing Quality value: a decimal numeral '0' or '1' is expected rather than '" + c + "'", index);
               }

               if (index == length) {
                  throw new ParseException("Error parsing Quality value: a decimal numeral is expected after the decimal point", index);
               }
            } else {
               if (index == length) {
                  return (c - 48) * 1000;
               }

               c = q.charAt(index++);
               if (c != '.') {
                  throw new ParseException("Error parsing Quality value: a decimal place is expected rather than '" + c + "'", index);
               }

               if (index == length) {
                  return (c - 48) * 1000;
               }
            }

            int value = 0;

            for(int exponent = 100; index < length; exponent /= 10) {
               c = q.charAt(index++);
               if (c < '0' || c > '9') {
                  throw new ParseException("Error parsing Quality value: a decimal numeral is expected rather than '" + c + "'", index);
               }

               value += (c - 48) * exponent;
            }

            if (wholeNumber == '1') {
               if (value > 0) {
                  throw new ParseException("The Quality value, " + q + ", is greater than 1", index);
               } else {
                  return 1000;
               }
            } else {
               return value;
            }
         }
      } else {
         throw new ParseException("Quality value cannot be null or an empty String", 0);
      }
   }

   public static int readQualityFactorParameter(HttpHeaderReader reader) throws ParseException {
      while(true) {
         if (reader.hasNext()) {
            reader.nextSeparator(';');
            if (!reader.hasNext()) {
               return 1000;
            }

            CharSequence name = reader.nextToken();
            reader.nextSeparator('=');
            CharSequence value = reader.nextTokenOrQuotedString();
            if (name.length() != 1 || name.charAt(0) != 'q' && name.charAt(0) != 'Q') {
               continue;
            }

            return readQualityFactor(value);
         }

         return 1000;
      }
   }

   public static Map readParameters(HttpHeaderReader reader) throws ParseException {
      return readParameters(reader, false);
   }

   public static Map readParameters(HttpHeaderReader reader, boolean fileNameFix) throws ParseException {
      Map<String, String> m;
      String name;
      String value;
      for(m = null; reader.hasNext(); m.put(name, value)) {
         reader.nextSeparator(';');

         while(reader.hasNextSeparator(';', true)) {
            reader.next();
         }

         if (!reader.hasNext()) {
            break;
         }

         name = reader.nextToken().toString().toLowerCase(Locale.ROOT);
         reader.nextSeparator('=');
         if ("filename".equals(name) && fileNameFix) {
            value = reader.nextTokenOrQuotedString(true).toString();
            value = value.substring(value.lastIndexOf(92) + 1);
         } else {
            value = reader.nextTokenOrQuotedString(false).toString();
         }

         if (m == null) {
            m = new LinkedHashMap();
         }
      }

      return m;
   }

   public static Map readCookies(String header) {
      return CookiesParser.parseCookies(header);
   }

   public static Cookie readCookie(String header) {
      return CookiesParser.parseCookie(header);
   }

   public static NewCookie readNewCookie(String header) {
      return CookiesParser.parseNewCookie(header);
   }

   public static Set readMatchingEntityTag(String header) throws ParseException {
      if ("*".equals(header)) {
         return MatchingEntityTag.ANY_MATCH;
      } else {
         HttpHeaderReader reader = new HttpHeaderReaderImpl(header);
         Set<MatchingEntityTag> l = new HashSet(1);
         HttpHeaderListAdapter adapter = new HttpHeaderListAdapter(reader);

         while(reader.hasNext()) {
            l.add(MATCHING_ENTITY_TAG_CREATOR.create(adapter));
            adapter.reset();
            if (reader.hasNext()) {
               reader.next();
            }
         }

         return l;
      }
   }

   public static List readMediaTypes(List l, String header) throws ParseException {
      return MEDIA_TYPE_LIST_READER.readList(l, header);
   }

   public static List readAcceptMediaType(String header) throws ParseException {
      return ACCEPTABLE_MEDIA_TYPE_LIST_READER.readList(header);
   }

   public static List readQualitySourceMediaType(String header) throws ParseException {
      return QUALITY_SOURCE_MEDIA_TYPE_LIST_READER.readList(header);
   }

   public static List readQualitySourceMediaType(String[] header) throws ParseException {
      if (header.length < 2) {
         return readQualitySourceMediaType(header[0]);
      } else {
         StringBuilder sb = new StringBuilder();

         for(String h : header) {
            if (sb.length() > 0) {
               sb.append(",");
            }

            sb.append(h);
         }

         return readQualitySourceMediaType(sb.toString());
      }
   }

   public static List readAcceptMediaType(String header, List priorityMediaTypes) throws ParseException {
      return (new AcceptMediaTypeListReader(priorityMediaTypes)).readList(header);
   }

   public static List readAcceptToken(String header) throws ParseException {
      return ACCEPTABLE_TOKEN_LIST_READER.readList(header);
   }

   public static List readAcceptLanguage(String header) throws ParseException {
      return ACCEPTABLE_LANGUAGE_TAG_LIST_READER.readList(header);
   }

   public static List readStringList(String header) throws ParseException {
      return STRING_LIST_READER.readList(header);
   }

   public static enum Event {
      Token,
      QuotedString,
      Comment,
      Separator,
      Control;
   }

   private static class MediaTypeListReader extends ListReader {
      private static final ListElementCreator MEDIA_TYPE_CREATOR = new ListElementCreator() {
         public MediaType create(HttpHeaderReader reader) throws ParseException {
            return MediaTypeProvider.valueOf(reader);
         }
      };

      List readList(List l, String header) throws ParseException {
         return super.readList(l, header);
      }

      private MediaTypeListReader() {
         super(MEDIA_TYPE_CREATOR);
      }
   }

   private static class AcceptableMediaTypeListReader extends QualifiedListReader {
      private static final ListElementCreator ACCEPTABLE_MEDIA_TYPE_CREATOR = new ListElementCreator() {
         public AcceptableMediaType create(HttpHeaderReader reader) throws ParseException {
            return AcceptableMediaType.valueOf(reader);
         }
      };

      private AcceptableMediaTypeListReader() {
         super(ACCEPTABLE_MEDIA_TYPE_CREATOR, AcceptableMediaType.COMPARATOR);
      }
   }

   private static class QualitySourceMediaTypeListReader extends QualifiedListReader {
      private static final ListElementCreator QUALITY_SOURCE_MEDIA_TYPE_CREATOR = new ListElementCreator() {
         public QualitySourceMediaType create(HttpHeaderReader reader) throws ParseException {
            return QualitySourceMediaType.valueOf(reader);
         }
      };

      private QualitySourceMediaTypeListReader() {
         super(QUALITY_SOURCE_MEDIA_TYPE_CREATOR, QualitySourceMediaType.COMPARATOR);
      }
   }

   private static class AcceptMediaTypeListReader extends QualifiedListReader {
      private static final ListElementCreator ACCEPTABLE_MEDIA_TYPE_CREATOR = new ListElementCreator() {
         public AcceptableMediaType create(HttpHeaderReader reader) throws ParseException {
            return AcceptableMediaType.valueOf(reader);
         }
      };

      AcceptMediaTypeListReader(List priorityMediaTypes) {
         super(ACCEPTABLE_MEDIA_TYPE_CREATOR, (Comparator)(new AcceptableMediaTypeComparator(priorityMediaTypes)));
      }

      private static class AcceptableMediaTypeComparator implements Comparator {
         private final List priorityMediaTypes;

         private AcceptableMediaTypeComparator(List priorityMediaTypes) {
            this.priorityMediaTypes = priorityMediaTypes;
         }

         public int compare(AcceptableMediaType o1, AcceptableMediaType o2) {
            boolean q_o1_set = false;
            int q_o1 = 0;
            boolean q_o2_set = false;
            int q_o2 = 0;

            for(QualitySourceMediaType priorityType : this.priorityMediaTypes) {
               if (!q_o1_set && MediaTypes.typeEqual(o1, priorityType)) {
                  q_o1 = o1.getQuality() * priorityType.getQuality();
                  q_o1_set = true;
               } else if (!q_o2_set && MediaTypes.typeEqual(o2, priorityType)) {
                  q_o2 = o2.getQuality() * priorityType.getQuality();
                  q_o2_set = true;
               }
            }

            int i = q_o2 - q_o1;
            if (i != 0) {
               return i;
            } else {
               i = o2.getQuality() - o1.getQuality();
               if (i != 0) {
                  return i;
               } else {
                  return MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(o1, o2);
               }
            }
         }
      }
   }

   private static class AcceptableTokenListReader extends QualifiedListReader {
      private static final ListElementCreator ACCEPTABLE_TOKEN_CREATOR = new ListElementCreator() {
         public AcceptableToken create(HttpHeaderReader reader) throws ParseException {
            return new AcceptableToken(reader);
         }
      };

      private AcceptableTokenListReader() {
         super(ACCEPTABLE_TOKEN_CREATOR, (<undefinedtype>)null);
      }
   }

   private static class AcceptableLanguageTagListReader extends QualifiedListReader {
      private static final ListElementCreator LANGUAGE_CREATOR = new ListElementCreator() {
         public AcceptableLanguageTag create(HttpHeaderReader reader) throws ParseException {
            return new AcceptableLanguageTag(reader);
         }
      };

      private AcceptableLanguageTagListReader() {
         super(LANGUAGE_CREATOR, (<undefinedtype>)null);
      }
   }

   private abstract static class QualifiedListReader extends ListReader {
      private final Comparator comparator;

      public List readList(String header) throws ParseException {
         List<T> l = super.readList(header);
         Collections.sort(l, this.comparator);
         return l;
      }

      private QualifiedListReader(ListElementCreator creator) {
         this(creator, Quality.QUALIFIED_COMPARATOR);
      }

      protected QualifiedListReader(ListElementCreator creator, Comparator comparator) {
         super(creator);
         this.comparator = comparator;
      }
   }

   private static class StringListReader extends ListReader {
      private static final ListElementCreator listElementCreator = new ListElementCreator() {
         public String create(HttpHeaderReader reader) throws ParseException {
            reader.hasNext();
            return reader.nextToken().toString();
         }
      };

      private StringListReader() {
         super(listElementCreator);
      }
   }

   private abstract static class ListReader {
      private final LRU LIST_CACHE = LRU.create();
      protected final ListElementCreator creator;

      protected ListReader(ListElementCreator creator) {
         this.creator = creator;
      }

      protected List readList(String header) throws ParseException {
         return this.readList(new ArrayList(), header);
      }

      private List readList(List l, String header) throws ParseException {
         List<T> list = (List)this.LIST_CACHE.getIfPresent(header);
         if (list == null) {
            synchronized(this.LIST_CACHE) {
               list = (List)this.LIST_CACHE.getIfPresent(header);
               if (list == null) {
                  HttpHeaderReader reader = new HttpHeaderReaderImpl(header);
                  HttpHeaderListAdapter adapter = new HttpHeaderListAdapter(reader);
                  list = new LinkedList();

                  while(reader.hasNext()) {
                     list.add(this.creator.create(adapter));
                     adapter.reset();
                     if (reader.hasNext()) {
                        reader.next();
                     }
                  }

                  this.LIST_CACHE.put(header, list);
               }
            }
         }

         l.addAll(list);
         return l;
      }
   }

   private interface ListElementCreator {
      Object create(HttpHeaderReader var1) throws ParseException;
   }
}
