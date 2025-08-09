package org.glassfish.jersey.client.spi;

import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.glassfish.jersey.Beta;
import org.glassfish.jersey.spi.Contract;

@Contract
@ConstrainedTo(RuntimeType.CLIENT)
@Beta
public interface InvocationBuilderListener {
   void onNewBuilder(InvocationBuilderContext var1);

   public interface InvocationBuilderContext {
      InvocationBuilderContext accept(String... var1);

      InvocationBuilderContext accept(MediaType... var1);

      InvocationBuilderContext acceptLanguage(Locale... var1);

      InvocationBuilderContext acceptLanguage(String... var1);

      InvocationBuilderContext acceptEncoding(String... var1);

      InvocationBuilderContext cookie(Cookie var1);

      InvocationBuilderContext cookie(String var1, String var2);

      InvocationBuilderContext cacheControl(CacheControl var1);

      List getAccepted();

      List getAcceptedLanguages();

      List getCacheControls();

      Configuration getConfiguration();

      Map getCookies();

      List getEncodings();

      List getHeader(String var1);

      MultivaluedMap getHeaders();

      Object getProperty(String var1);

      Collection getPropertyNames();

      URI getUri();

      InvocationBuilderContext header(String var1, Object var2);

      InvocationBuilderContext headers(MultivaluedMap var1);

      InvocationBuilderContext property(String var1, Object var2);

      void removeProperty(String var1);
   }
}
