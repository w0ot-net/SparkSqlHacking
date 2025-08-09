package jakarta.xml.bind;

import javax.xml.validation.Schema;

public abstract class Binder {
   protected Binder() {
   }

   public abstract Object unmarshal(Object var1) throws JAXBException;

   public abstract JAXBElement unmarshal(Object var1, Class var2) throws JAXBException;

   public abstract void marshal(Object var1, Object var2) throws JAXBException;

   public abstract Object getXMLNode(Object var1);

   public abstract Object getJAXBNode(Object var1);

   public abstract Object updateXML(Object var1) throws JAXBException;

   public abstract Object updateXML(Object var1, Object var2) throws JAXBException;

   public abstract Object updateJAXB(Object var1) throws JAXBException;

   public abstract void setSchema(Schema var1);

   public abstract Schema getSchema();

   public abstract void setEventHandler(ValidationEventHandler var1) throws JAXBException;

   public abstract ValidationEventHandler getEventHandler() throws JAXBException;

   public abstract void setProperty(String var1, Object var2) throws PropertyException;

   public abstract Object getProperty(String var1) throws PropertyException;
}
