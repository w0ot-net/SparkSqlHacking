package org.sparkproject.jpmml.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.sparkproject.dmg.pmml.ObjectFactory;
import org.xml.sax.SAXException;

public class JAXBUtil {
   private static JAXBContext context = null;
   private static Schema schema = null;

   private JAXBUtil() {
   }

   public static Class[] getObjectFactoryClasses() {
      return new Class[]{ObjectFactory.class, org.sparkproject.dmg.pmml.anomaly_detection.ObjectFactory.class, org.sparkproject.dmg.pmml.association.ObjectFactory.class, org.sparkproject.dmg.pmml.baseline.ObjectFactory.class, org.sparkproject.dmg.pmml.bayesian_network.ObjectFactory.class, org.sparkproject.dmg.pmml.clustering.ObjectFactory.class, org.sparkproject.dmg.pmml.gaussian_process.ObjectFactory.class, org.sparkproject.dmg.pmml.general_regression.ObjectFactory.class, org.sparkproject.dmg.pmml.mining.ObjectFactory.class, org.sparkproject.dmg.pmml.naive_bayes.ObjectFactory.class, org.sparkproject.dmg.pmml.nearest_neighbor.ObjectFactory.class, org.sparkproject.dmg.pmml.neural_network.ObjectFactory.class, org.sparkproject.dmg.pmml.regression.ObjectFactory.class, org.sparkproject.dmg.pmml.rule_set.ObjectFactory.class, org.sparkproject.dmg.pmml.scorecard.ObjectFactory.class, org.sparkproject.dmg.pmml.sequence.ObjectFactory.class, org.sparkproject.dmg.pmml.support_vector_machine.ObjectFactory.class, org.sparkproject.dmg.pmml.text.ObjectFactory.class, org.sparkproject.dmg.pmml.time_series.ObjectFactory.class, org.sparkproject.dmg.pmml.tree.ObjectFactory.class, org.sparkproject.jpmml.model.cells.ObjectFactory.class};
   }

   public static JAXBContext createContext() throws JAXBException {
      return JAXBContext.newInstance(getObjectFactoryClasses());
   }

   public static JAXBContext getContext() throws JAXBException {
      if (context == null) {
         context = createContext();
      }

      return context;
   }

   public static void setContext(JAXBContext context) {
      JAXBUtil.context = context;
   }

   public static Schema createSchema() throws IOException, SAXException {
      SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
      URL url = ObjectFactory.class.getResource("/pmml.xsd");
      if (url == null) {
         throw new FileNotFoundException();
      } else {
         return schemaFactory.newSchema(url);
      }
   }

   public static Schema getSchema() throws IOException, SAXException {
      if (schema == null) {
         schema = createSchema();
      }

      return schema;
   }

   public static void setSchema(Schema schema) {
      JAXBUtil.schema = schema;
   }
}
