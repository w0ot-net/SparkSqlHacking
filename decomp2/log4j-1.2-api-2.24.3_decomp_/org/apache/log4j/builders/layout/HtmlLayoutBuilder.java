package org.apache.log4j.builders.layout;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.log4j.Layout;
import org.apache.log4j.bridge.LayoutWrapper;
import org.apache.log4j.builders.AbstractBuilder;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.HtmlLayout;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.HTMLLayout",
   category = "Log4j Builder"
)
public class HtmlLayoutBuilder extends AbstractBuilder implements LayoutBuilder {
   private static final String DEFAULT_TITLE = "Log4J Log Messages";
   private static final String TITLE_PARAM = "Title";
   private static final String LOCATION_INFO_PARAM = "LocationInfo";

   public HtmlLayoutBuilder() {
   }

   public HtmlLayoutBuilder(final String prefix, final Properties props) {
      super(prefix, props);
   }

   public Layout parse(final Element layoutElement, final XmlConfiguration config) {
      AtomicReference<String> title = new AtomicReference("Log4J Log Messages");
      AtomicBoolean locationInfo = new AtomicBoolean();
      XmlConfiguration.forEachElement(layoutElement.getElementsByTagName("param"), (currentElement) -> {
         if (currentElement.getTagName().equals("param")) {
            if ("Title".equalsIgnoreCase(currentElement.getAttribute("name"))) {
               title.set(currentElement.getAttribute("value"));
            } else if ("LocationInfo".equalsIgnoreCase(currentElement.getAttribute("name"))) {
               locationInfo.set(this.getBooleanValueAttribute(currentElement));
            }
         }

      });
      return this.createLayout((String)title.get(), locationInfo.get());
   }

   public Layout parse(final PropertiesConfiguration config) {
      String title = this.getProperty("Title", "Log4J Log Messages");
      boolean locationInfo = this.getBooleanProperty("LocationInfo");
      return this.createLayout(title, locationInfo);
   }

   private Layout createLayout(final String title, final boolean locationInfo) {
      return LayoutWrapper.adapt(HtmlLayout.newBuilder().withTitle(title).withLocationInfo(locationInfo).build());
   }
}
