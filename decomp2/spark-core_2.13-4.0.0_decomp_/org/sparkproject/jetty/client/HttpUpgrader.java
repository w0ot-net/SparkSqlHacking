package org.sparkproject.jetty.client;

import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.util.Callback;

public interface HttpUpgrader {
   void prepare(HttpRequest var1);

   void upgrade(HttpResponse var1, EndPoint var2, Callback var3);

   public interface Factory {
      HttpUpgrader newHttpUpgrader(HttpVersion var1);
   }
}
