package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.ext.RuntimeDelegate;
import java.net.URI;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.internal.RuntimeDelegateDecorator;

public abstract class MessageHeaderMethods {
   protected RuntimeDelegate runtimeDelegateDecorator;

   protected MessageHeaderMethods(Configuration configuration) {
      this.runtimeDelegateDecorator = RuntimeDelegateDecorator.configured(configuration);
   }

   protected MessageHeaderMethods(MessageHeaderMethods other) {
      this.runtimeDelegateDecorator = other.runtimeDelegateDecorator;
   }

   public abstract String getHeaderString(String var1);

   public abstract MultivaluedMap getHeaders();

   protected abstract HeaderValueException.Context getHeaderValueExceptionContext();

   public abstract Set getLinks();

   public boolean containsHeaderString(String name, String valueSeparatorRegex, Predicate valuePredicate) {
      String header = this.getHeaderString(name);
      if (header == null) {
         return false;
      } else {
         String[] split = header.split(valueSeparatorRegex);

         for(String s : split) {
            if (valuePredicate.test(s.trim())) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean containsHeaderString(String name, Predicate valuePredicate) {
      return this.containsHeaderString(name, ",", valuePredicate);
   }

   public Set getAllowedMethods() {
      String allowed = this.getHeaderString("Allow");
      if (allowed != null && !allowed.isEmpty()) {
         try {
            return new HashSet(HttpHeaderReader.readStringList(allowed.toUpperCase(Locale.ROOT)));
         } catch (ParseException e) {
            throw this.exception("Allow", allowed, e);
         }
      } else {
         return Collections.emptySet();
      }
   }

   public Date getDate() {
      return (Date)this.singleHeader("Date", Date.class, (input) -> {
         try {
            return HttpHeaderReader.readDate(input);
         } catch (ParseException e) {
            throw new ProcessingException(e);
         }
      }, false);
   }

   public EntityTag getEntityTag() {
      return (EntityTag)this.singleHeader("ETag", EntityTag.class, new Function() {
         public EntityTag apply(String value) {
            try {
               return value == null ? null : EntityTag.valueOf(value);
            } catch (IllegalArgumentException ex) {
               throw new ProcessingException(ex);
            }
         }
      }, false);
   }

   public Locale getLanguage() {
      return (Locale)this.singleHeader("Content-Language", Locale.class, (input) -> {
         try {
            return (new LanguageTag(input)).getAsLocale();
         } catch (ParseException e) {
            throw new ProcessingException(e);
         }
      }, false);
   }

   public Date getLastModified() {
      return (Date)this.singleHeader("Last-Modified", Date.class, new Function() {
         public Date apply(String input) {
            try {
               return HttpHeaderReader.readDate(input);
            } catch (ParseException e) {
               throw new ProcessingException(e);
            }
         }
      }, false);
   }

   public int getLength() {
      return (Integer)this.singleHeader("Content-Length", Integer.class, (input) -> {
         try {
            if (input != null && !input.isEmpty()) {
               int i = Integer.parseInt(input);
               if (i >= 0) {
                  return i;
               }
            }

            return -1;
         } catch (NumberFormatException ex) {
            throw new ProcessingException(ex);
         }
      }, true);
   }

   public long getLengthLong() {
      return (Long)this.singleHeader("Content-Length", Long.class, (input) -> {
         try {
            if (input != null && !input.isEmpty()) {
               long l = Long.parseLong(input);
               if (l >= 0L) {
                  return l;
               }
            }

            return -1L;
         } catch (NumberFormatException ex) {
            throw new ProcessingException(ex);
         }
      }, true);
   }

   public Link getLink(String relation) {
      for(Link link : this.getLinks()) {
         List<String> relations = LinkProvider.getLinkRelations(link.getRel());
         if (relations != null && relations.contains(relation)) {
            return link;
         }
      }

      return null;
   }

   public Link.Builder getLinkBuilder(String relation) {
      Link link = this.getLink(relation);
      return link == null ? null : Link.fromLink(link);
   }

   public URI getLocation() {
      return (URI)this.singleHeader("Location", URI.class, (value) -> {
         try {
            return value == null ? null : URI.create(value);
         } catch (IllegalArgumentException ex) {
            throw new ProcessingException(ex);
         }
      }, false);
   }

   public Map getRequestCookies() {
      List<Object> cookies = (List)this.getHeaders().get("Cookie");
      if (cookies != null && !cookies.isEmpty()) {
         Map<String, Cookie> result = new HashMap();

         for(String cookie : this.toStringList(cookies)) {
            if (cookie != null) {
               result.putAll(HttpHeaderReader.readCookies(cookie));
            }
         }

         return result;
      } else {
         return Collections.emptyMap();
      }
   }

   public Map getResponseCookies() {
      List<Object> cookies = (List)this.getHeaders().get("Set-Cookie");
      if (cookies != null && !cookies.isEmpty()) {
         Map<String, NewCookie> result = new HashMap();

         for(String cookie : this.toStringList(cookies)) {
            if (cookie != null) {
               NewCookie newCookie = HttpHeaderReader.readNewCookie(cookie);
               String cookieName = newCookie.getName();
               if (result.containsKey(cookieName)) {
                  result.put(cookieName, HeaderUtils.getPreferredCookie((NewCookie)result.get(cookieName), newCookie));
               } else {
                  result.put(cookieName, newCookie);
               }
            }
         }

         return result;
      } else {
         return Collections.emptyMap();
      }
   }

   public boolean hasLink(String relation) {
      for(Link link : this.getLinks()) {
         List<String> relations = LinkProvider.getLinkRelations(link.getRel());
         if (relations != null && relations.contains(relation)) {
            return true;
         }
      }

      return false;
   }

   protected Object singleHeader(String name, Class valueType, Function converter, boolean convertNull) {
      List<Object> values = (List)this.getHeaders().get(name);
      if (values != null && !values.isEmpty()) {
         if (values.size() > 1) {
            throw new HeaderValueException(LocalizationMessages.TOO_MANY_HEADER_VALUES(name, values.toString()), this.getHeaderValueExceptionContext());
         } else {
            Object value = values.get(0);
            if (value == null) {
               return convertNull ? converter.apply((Object)null) : null;
            } else if (HeaderValueException.Context.OUTBOUND == this.getHeaderValueExceptionContext() && valueType.isInstance(value)) {
               return valueType.cast(value);
            } else {
               try {
                  return converter.apply(HeaderUtils.asString(value, this.runtimeDelegateDecorator));
               } catch (ProcessingException ex) {
                  throw this.exception(name, value, ex);
               }
            }
         }
      } else {
         return convertNull ? converter.apply((Object)null) : null;
      }
   }

   protected Object singleHeader(String name, Function converter, boolean convertNull) {
      return this.singleHeader(name, (Class)null, converter, convertNull);
   }

   protected HeaderValueException exception(String headerName, Object headerValue, Exception e) {
      return new HeaderValueException(LocalizationMessages.UNABLE_TO_PARSE_HEADER_VALUE(headerName, headerValue), e, this.getHeaderValueExceptionContext());
   }

   private List toStringList(List list) {
      return this.getHeaderValueExceptionContext() == HeaderValueException.Context.OUTBOUND ? HeaderUtils.asStringList(list, this.runtimeDelegateDecorator) : list;
   }
}
