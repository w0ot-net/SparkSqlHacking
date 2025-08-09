package jakarta.ws.rs.core;

import java.net.URI;
import java.util.List;

public interface UriInfo {
   String getPath();

   String getPath(boolean var1);

   List getPathSegments();

   List getPathSegments(boolean var1);

   URI getRequestUri();

   UriBuilder getRequestUriBuilder();

   URI getAbsolutePath();

   UriBuilder getAbsolutePathBuilder();

   URI getBaseUri();

   UriBuilder getBaseUriBuilder();

   MultivaluedMap getPathParameters();

   MultivaluedMap getPathParameters(boolean var1);

   MultivaluedMap getQueryParameters();

   MultivaluedMap getQueryParameters(boolean var1);

   List getMatchedURIs();

   List getMatchedURIs(boolean var1);

   List getMatchedResources();

   URI resolve(URI var1);

   URI relativize(URI var1);
}
