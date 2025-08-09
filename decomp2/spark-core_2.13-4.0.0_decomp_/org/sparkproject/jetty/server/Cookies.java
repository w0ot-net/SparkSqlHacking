package org.sparkproject.jetty.server;

import jakarta.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.ComplianceViolation;
import org.sparkproject.jetty.http.CookieCompliance;
import org.sparkproject.jetty.http.CookieParser;

public class Cookies implements CookieParser.Handler {
   protected static final Logger LOG = LoggerFactory.getLogger(Cookies.class);
   protected final List _rawFields;
   protected final List _cookieList;
   private int _addedFields;
   private boolean _parsed;
   private Cookie[] _cookies;
   private boolean _set;
   private final CookieParser _parser;

   public Cookies() {
      this(CookieCompliance.RFC6265, (ComplianceViolation.Listener)null);
   }

   public Cookies(CookieCompliance compliance, ComplianceViolation.Listener complianceListener) {
      this._rawFields = new ArrayList();
      this._cookieList = new ArrayList();
      this._parsed = false;
      this._set = false;
      this._parser = CookieParser.newParser(this, compliance, complianceListener);
   }

   public void addCookieField(String rawField) {
      if (this._set) {
         throw new IllegalStateException();
      } else if (rawField != null) {
         rawField = rawField.trim();
         if (rawField.length() != 0) {
            if (this._rawFields.size() > this._addedFields) {
               if (rawField.equals(this._rawFields.get(this._addedFields))) {
                  ++this._addedFields;
                  return;
               }

               while(this._rawFields.size() > this._addedFields) {
                  this._rawFields.remove(this._addedFields);
               }
            }

            this._rawFields.add(this._addedFields++, rawField);
            this._parsed = false;
         }
      }
   }

   public Cookie[] getCookies() {
      if (this._set) {
         return this._cookies;
      } else {
         while(this._rawFields.size() > this._addedFields) {
            this._rawFields.remove(this._addedFields);
            this._parsed = false;
         }

         if (this._parsed) {
            return this._cookies;
         } else {
            try {
               this._parser.parseFields(this._rawFields);
            } catch (CookieParser.InvalidCookieException invalidCookieException) {
               throw new BadMessageException(400, invalidCookieException.getMessage(), invalidCookieException);
            }

            this._cookies = (Cookie[])this._cookieList.toArray(new Cookie[this._cookieList.size()]);
            this._cookieList.clear();
            this._parsed = true;
            return this._cookies;
         }
      }
   }

   public void setCookies(Cookie[] cookies) {
      this._cookies = cookies;
      this._set = true;
   }

   public void reset() {
      if (this._set) {
         this._cookies = null;
      }

      this._set = false;
      this._addedFields = 0;
   }

   public void addCookie(String name, String value, int version, String domain, String path, String comment) {
      try {
         Cookie cookie = new Cookie(name, value);
         if (domain != null) {
            cookie.setDomain(domain);
         }

         if (path != null) {
            cookie.setPath(path);
         }

         if (version > 0) {
            cookie.setVersion(version);
         }

         if (comment != null) {
            cookie.setComment(comment);
         }

         this._cookieList.add(cookie);
      } catch (Exception e) {
         LOG.debug("Unable to add Cookie name={}, value={}, domain={}, path={}, version={}, comment={}", new Object[]{name, value, domain, path, version, comment, e});
      }

   }
}
