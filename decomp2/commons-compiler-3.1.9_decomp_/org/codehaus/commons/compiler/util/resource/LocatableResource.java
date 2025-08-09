package org.codehaus.commons.compiler.util.resource;

import java.io.IOException;
import java.net.URL;

public interface LocatableResource extends Resource {
   URL getLocation() throws IOException;
}
