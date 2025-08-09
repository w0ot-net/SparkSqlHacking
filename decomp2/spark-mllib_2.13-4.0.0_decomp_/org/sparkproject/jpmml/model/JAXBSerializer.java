package org.sparkproject.jpmml.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.sparkproject.dmg.pmml.PMMLObject;

public class JAXBSerializer implements TextSerializer {
   private JAXBContext context;

   public JAXBSerializer() throws JAXBException {
      this(JAXBUtil.getContext());
   }

   public JAXBSerializer(JAXBContext context) {
      this.context = null;
      this.setContext(context);
   }

   public PMMLObject deserialize(InputStream is) throws JAXBException {
      return this.unmarshal(new StreamSource(is));
   }

   public void serialize(PMMLObject object, OutputStream os) throws JAXBException {
      this.marshal(object, new StreamResult(os));
   }

   public void serializePretty(PMMLObject object, OutputStream os) throws JAXBException {
      this.marshalPretty(object, new StreamResult(os));
   }

   public PMMLObject unmarshal(Source source) throws JAXBException {
      Unmarshaller unmarshaller = this.createUnmarshaller();
      return (PMMLObject)unmarshaller.unmarshal(source);
   }

   public void marshal(PMMLObject object, Result result) throws JAXBException {
      Marshaller marshaller = this.createMarshaller();
      marshaller.marshal(object, result);
   }

   public void marshalPretty(PMMLObject object, Result result) throws JAXBException {
      Marshaller marshaller = this.createMarshaller();
      marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
      marshaller.marshal(object, result);
   }

   protected Marshaller createMarshaller() throws JAXBException {
      JAXBContext context = this.getContext();
      Marshaller marshaller = context.createMarshaller();
      return marshaller;
   }

   protected Unmarshaller createUnmarshaller() throws JAXBException {
      JAXBContext context = this.getContext();
      Unmarshaller unmarshaller = context.createUnmarshaller();
      return unmarshaller;
   }

   protected JAXBContext getContext() {
      return this.context;
   }

   private void setContext(JAXBContext context) {
      this.context = (JAXBContext)Objects.requireNonNull(context);
   }
}
