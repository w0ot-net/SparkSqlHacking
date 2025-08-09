package jakarta.ws.rs.container;

import jakarta.ws.rs.core.EntityTag;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public interface ContainerResponseContext {
   int getStatus();

   void setStatus(int var1);

   Response.StatusType getStatusInfo();

   void setStatusInfo(Response.StatusType var1);

   MultivaluedMap getHeaders();

   MultivaluedMap getStringHeaders();

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

   Object getEntity();

   Class getEntityClass();

   Type getEntityType();

   void setEntity(Object var1);

   void setEntity(Object var1, Annotation[] var2, MediaType var3);

   Annotation[] getEntityAnnotations();

   OutputStream getEntityStream();

   void setEntityStream(OutputStream var1);
}
