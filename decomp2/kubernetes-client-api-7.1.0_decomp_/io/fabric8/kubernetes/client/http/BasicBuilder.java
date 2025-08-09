package io.fabric8.kubernetes.client.http;

import java.net.URI;

public interface BasicBuilder {
   BasicBuilder uri(URI var1);

   BasicBuilder header(String var1, String var2);

   BasicBuilder setHeader(String var1, String var2);
}
