package org.apache.http.impl.cookie;

import [Ljava.lang.String;;
import java.util.Date;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;

@Contract(
   threading = ThreadingBehavior.IMMUTABLE
)
public class BasicExpiresHandler extends AbstractCookieAttributeHandler implements CommonCookieAttributeHandler {
   private final String[] datePatterns;

   public BasicExpiresHandler(String[] datePatterns) {
      Args.notNull(datePatterns, "Array of date patterns");
      this.datePatterns = (String[])((String;)datePatterns).clone();
   }

   public void parse(SetCookie cookie, String value) throws MalformedCookieException {
      Args.notNull(cookie, "Cookie");
      if (value == null) {
         throw new MalformedCookieException("Missing value for 'expires' attribute");
      } else {
         Date expiry = org.apache.http.client.utils.DateUtils.parseDate(value, this.datePatterns);
         if (expiry == null) {
            throw new MalformedCookieException("Invalid 'expires' attribute: " + value);
         } else {
            cookie.setExpiryDate(expiry);
         }
      }
   }

   public String getAttributeName() {
      return "expires";
   }
}
