package org.sparkproject.jpmml.model;

import jakarta.xml.bind.JAXBException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLClassLoader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import org.sparkproject.dmg.pmml.PMML;
import org.sparkproject.jpmml.model.filters.ImportFilter;
import org.xml.sax.SAXException;

public class PMMLUtil {
   private PMMLUtil() {
   }

   public static PMML load(URL url) throws IOException {
      URLClassLoader clazzLoader = URLClassLoader.newInstance(new URL[]{url});

      PMML var2;
      try {
         var2 = load((ClassLoader)clazzLoader);
      } finally {
         if (clazzLoader instanceof Closeable) {
            clazzLoader.close();
         }

      }

      return var2;
   }

   public static PMML load(ClassLoader clazzLoader) {
      return (PMML)ServiceLoaderUtil.load(PMML.class, clazzLoader);
   }

   public static PMML unmarshal(InputStream is) throws ParserConfigurationException, SAXException, JAXBException {
      JAXBSerializer serializer = new JAXBSerializer();
      Source source = SAXUtil.createFilteredSource(is, new ImportFilter());
      return (PMML)serializer.unmarshal(source);
   }

   public static void marshal(PMML pmml, OutputStream os) throws JAXBException {
      JAXBSerializer serializer = new JAXBSerializer();
      Result result = new StreamResult(os);
      serializer.marshal(pmml, result);
   }
}
