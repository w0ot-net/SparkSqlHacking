package org.apache.derby.impl.tools.planexporter;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class CreateHTMLFile {
   private static String xslStyleSheetName = "resources/vanilla_html.xsl";

   public void getHTML(String var1, String var2, String var3, boolean var4) throws Exception {
      if (!var3.toUpperCase().endsWith(".HTML")) {
         var3 = var3 + ".html";
      }

      TransformerFactory var5 = TransformerFactory.newInstance();
      Transformer var6;
      if (var4) {
         URL var7 = this.getClass().getResource(var2);
         var6 = var5.newTransformer(new StreamSource(var7.openStream()));
      } else {
         File var9 = new File(var2);
         if (var9.exists()) {
            var6 = var5.newTransformer(new StreamSource(var2));
         } else {
            URL var8 = this.getClass().getResource(xslStyleSheetName);
            var6 = var5.newTransformer(new StreamSource(var8.openStream()));
         }
      }

      var6.transform(new StreamSource(var1), new StreamResult(new FileOutputStream(var3)));
   }
}
