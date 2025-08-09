package org.apache.hadoop.hive.ant;

import java.io.File;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class GenHiveTemplate extends Task {
   private String templateFile;

   public String getTemplateFile() {
      return this.templateFile;
   }

   public void setTemplateFile(String templateFile) {
      this.templateFile = templateFile;
   }

   private void generate() throws Exception {
      File current = new File(this.templateFile);
      if (current.exists()) {
         ClassLoader loader = GenHiveTemplate.class.getClassLoader();
         URL url = loader.getResource("org/apache/hadoop/hive/conf/HiveConf.class");
         if (url != null) {
            File file = new File(url.getFile());
            if (file.exists() && file.lastModified() < current.lastModified()) {
               return;
            }
         }
      }

      this.writeToFile(current, this.generateTemplate());
   }

   private Document generateTemplate() throws Exception {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = dbf.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      doc.appendChild(doc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"configuration.xsl\""));
      doc.appendChild(doc.createComment("\n   Licensed to the Apache Software Foundation (ASF) under one or more\n   contributor license agreements.  See the NOTICE file distributed with\n   this work for additional information regarding copyright ownership.\n   The ASF licenses this file to You under the Apache License, Version 2.0\n   (the \"License\"); you may not use this file except in compliance with\n   the License.  You may obtain a copy of the License at\n\n       http://www.apache.org/licenses/LICENSE-2.0\n\n   Unless required by applicable law or agreed to in writing, software\n   distributed under the License is distributed on an \"AS IS\" BASIS,\n   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n   See the License for the specific language governing permissions and\n   limitations under the License.\n"));
      Element root = doc.createElement("configuration");
      doc.appendChild(root);
      root.appendChild(doc.createComment(" WARNING!!! This file is auto generated for documentation purposes ONLY! "));
      root.appendChild(doc.createComment(" WARNING!!! Any changes you make to this file will be ignored by Hive.   "));
      root.appendChild(doc.createComment(" WARNING!!! You must make your changes in hive-site.xml instead.         "));
      root.appendChild(doc.createComment(" Hive Execution Parameters "));
      Thread.currentThread().setContextClassLoader(ShimLoader.class.getClassLoader());

      for(HiveConf.ConfVars confVars : HiveConf.ConfVars.values()) {
         if (!confVars.isExcluded()) {
            Element property = this.appendElement(root, "property", (String)null);
            this.appendElement(property, "name", confVars.varname);
            this.appendElement(property, "value", confVars.getDefaultExpr());
            this.appendElement(property, "description", this.normalize(confVars.getDescription()));
         }
      }

      return doc;
   }

   private String normalize(String description) {
      int index = description.indexOf(10);
      if (index < 0) {
         return description;
      } else {
         int prev = 0;

         StringBuilder builder;
         for(builder = new StringBuilder(description.length() << 1); index > 0; index = description.indexOf(10, prev = index + 1)) {
            builder.append("\n      ").append(description.substring(prev, index));
         }

         if (prev < description.length()) {
            builder.append("\n      ").append(description.substring(prev));
         }

         builder.append("\n    ");
         return builder.toString();
      }
   }

   private void writeToFile(File template, Document document) throws Exception {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty("indent", "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      DOMSource source = new DOMSource(document);
      StreamResult result = new StreamResult(template);
      transformer.transform(source, result);
   }

   private Element appendElement(Element parent, String name, String text) {
      Document document = parent.getOwnerDocument();
      Element child = document.createElement(name);
      parent.appendChild(child);
      if (text != null) {
         Text textNode = document.createTextNode(text);
         child.appendChild(textNode);
      }

      return child;
   }

   public void execute() throws BuildException {
      try {
         this.generate();
      } catch (Exception e) {
         throw new BuildException(e);
      }
   }

   public static void main(String[] args) throws Exception {
      GenHiveTemplate gen = new GenHiveTemplate();
      gen.generate();
   }
}
