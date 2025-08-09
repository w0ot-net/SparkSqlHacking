package org.apache.ivy.osgi.p2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.ivy.osgi.util.DelegatingHandler;
import org.apache.ivy.util.XMLHelper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public class P2CompositeParser implements XMLInputParser {
   private Set childLocations = new LinkedHashSet();

   public Set getChildLocations() {
      return this.childLocations;
   }

   public void parse(InputStream in) throws IOException, ParseException, SAXException {
      RepositoryHandler handler = new RepositoryHandler();

      try {
         XMLHelper.parse((InputStream)in, (URL)null, handler, (LexicalHandler)null);
      } catch (ParserConfigurationException e) {
         throw new SAXException(e);
      }

      this.childLocations.addAll(handler.childLocations);
   }

   private static class RepositoryHandler extends DelegatingHandler {
      private static final String REPOSITORY = "repository";
      List childLocations = Collections.emptyList();

      public RepositoryHandler() {
         super("repository");
         this.addChild(new ChildrenHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ChildrenHandler child) {
               RepositoryHandler.this.childLocations = child.childLocations;
            }
         });
      }
   }

   private static class ChildrenHandler extends DelegatingHandler {
      private static final String CHILDREN = "children";
      private static final String SIZE = "size";
      List childLocations;

      public ChildrenHandler() {
         super("children");
         this.addChild(new ChildHandler(), new DelegatingHandler.ChildElementHandler() {
            public void childHandled(ChildHandler child) {
               ChildrenHandler.this.childLocations.add(child.location);
            }
         });
      }

      protected void handleAttributes(Attributes atts) {
         int size = Integer.parseInt(atts.getValue("size"));
         this.childLocations = new ArrayList(size);
      }
   }

   private static class ChildHandler extends DelegatingHandler {
      private static final String CHILD = "child";
      private static final String LOCATION = "location";
      String location;

      public ChildHandler() {
         super("child");
      }

      protected void handleAttributes(Attributes atts) {
         this.location = atts.getValue("location");
      }
   }
}
