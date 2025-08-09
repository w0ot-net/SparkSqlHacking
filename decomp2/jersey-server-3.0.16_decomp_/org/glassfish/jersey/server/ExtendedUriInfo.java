package org.glassfish.jersey.server;

import jakarta.ws.rs.core.UriInfo;
import java.util.List;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;

public interface ExtendedUriInfo extends UriInfo {
   Throwable getMappedThrowable();

   List getMatchedResults();

   List getMatchedTemplates();

   List getPathSegments(String var1);

   List getPathSegments(String var1, boolean var2);

   List getMatchedRuntimeResources();

   ResourceMethod getMatchedResourceMethod();

   Resource getMatchedModelResource();

   List getMatchedResourceLocators();

   List getLocatorSubResources();
}
