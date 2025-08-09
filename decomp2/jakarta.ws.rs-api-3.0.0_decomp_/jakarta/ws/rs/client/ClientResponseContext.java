package jakarta.ws.rs.client;

import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public interface ClientResponseContext {
   int getStatus();

   void setStatus(int var1);

   Response.StatusType getStatusInfo();

   void setStatusInfo(Response.StatusType var1);

   MultivaluedMap getHeaders();

   String getHeaderString(String var1);

   Set getAllowedMethods();

   Date getDate();

   Locale getLanguage();

   int getLength();

   MediaType getMediaType();

   Map getCookies();

   EntityTag getEntityTag();

   Date getLastModified();

   URI getLocation();

   Set getLinks();

   boolean hasLink(String var1);

   Link getLink(String var1);

   Link.Builder getLinkBuilder(String var1);

   boolean hasEntity();

   InputStream getEntityStream();

   void setEntityStream(InputStream var1);
}
