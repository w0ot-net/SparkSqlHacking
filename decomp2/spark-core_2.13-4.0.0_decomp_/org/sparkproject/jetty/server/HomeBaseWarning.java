package org.sparkproject.jetty.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.StringUtil;

public class HomeBaseWarning {
   private static final Logger LOG = LoggerFactory.getLogger(HomeBaseWarning.class);

   public HomeBaseWarning() {
      boolean showWarn = false;
      String home = System.getProperty("jetty.home");
      String base = System.getProperty("jetty.base");
      if (!StringUtil.isBlank(base)) {
         Path homePath = (new File(home)).toPath();
         Path basePath = (new File(base)).toPath();

         try {
            showWarn = Files.isSameFile(homePath, basePath);
         } catch (IOException e) {
            LOG.trace("IGNORED", e);
            return;
         }

         if (showWarn) {
            StringBuilder warn = new StringBuilder();
            warn.append("This instance of Jetty is not running from a separate {jetty.base} directory");
            warn.append(", this is not recommended.  See documentation at https://jetty.org/docs/");
            LOG.warn("{}", warn.toString());
         }

      }
   }
}
