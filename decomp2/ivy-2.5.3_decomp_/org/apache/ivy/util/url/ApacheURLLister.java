package org.apache.ivy.util.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;

public class ApacheURLLister {
   private static final Pattern PATTERN = Pattern.compile("<a[^>]*href=\"([^\"]*)\"[^>]*>(?:<[^>]+>)*?([^<>]+?)(?:<[^>]+>)*?</a>", 2);

   public List listAll(URL url) throws IOException {
      return this.retrieveListing(url, true, true);
   }

   public List listDirectories(URL url) throws IOException {
      return this.retrieveListing(url, false, true);
   }

   public List listFiles(URL url) throws IOException {
      return this.retrieveListing(url, true, false);
   }

   public List retrieveListing(URL url, boolean includeFiles, boolean includeDirectories) throws IOException {
      List<URL> urlList = new ArrayList();
      if (!url.getPath().endsWith("/") && !url.getPath().endsWith(".html")) {
         url = new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getPath() + "/");
      }

      URLHandler urlHandler = URLHandlerRegistry.getDefault();
      URLHandler.URLInfo urlInfo = urlHandler.getURLInfo(url);
      if (urlInfo == URLHandler.UNAVAILABLE) {
         return urlList;
      } else {
         String charset = urlInfo.getBodyCharset();
         InputStream contentStream = urlHandler.openStream(url);
         BufferedReader r = null;
         if (charset == null) {
            r = new BufferedReader(new InputStreamReader(contentStream));
         } else {
            r = new BufferedReader(new InputStreamReader(contentStream, charset));
         }

         String htmlText = FileUtil.readEntirely(r);
         Matcher matcher = PATTERN.matcher(htmlText);

         while(true) {
            String href;
            String text;
            while(true) {
               if (!matcher.find()) {
                  return urlList;
               }

               href = matcher.group(1);
               text = matcher.group(2);
               if (href != null && text != null) {
                  text = text.trim();

                  try {
                     URI uri = new URI(href);
                     href = uri.getPath();
                     if (uri.getScheme() == null) {
                        break;
                     }

                     if (href.startsWith(url.getPath())) {
                        href = href.substring(url.getPath().length());
                        break;
                     }
                  } catch (URISyntaxException var16) {
                  }
               }
            }

            if (!href.startsWith("../")) {
               if (href.startsWith("/")) {
                  int slashIndex = href.substring(0, href.length() - 1).lastIndexOf(47);
                  href = href.substring(slashIndex + 1);
               }

               if (href.startsWith("./")) {
                  href = href.substring("./".length());
               }

               if (text.endsWith("..>")) {
                  if (!href.startsWith(text.substring(0, text.length() - 3))) {
                     continue;
                  }
               } else if (text.endsWith("..&gt;")) {
                  if (!href.startsWith(text.substring(0, text.length() - 6))) {
                     continue;
                  }
               } else {
                  String strippedHref = href.endsWith("/") ? href.substring(0, href.length() - 1) : href;
                  String strippedText = text.endsWith("/") ? text.substring(0, text.length() - 1) : text;
                  if (!strippedHref.equalsIgnoreCase(strippedText)) {
                     continue;
                  }
               }

               boolean directory = href.endsWith("/");
               if (directory && includeDirectories || !directory && includeFiles) {
                  URL child = new URL(url, href);
                  urlList.add(child);
                  Message.debug("ApacheURLLister found URL=[" + child + "].");
               }
            }
         }
      }
   }
}
