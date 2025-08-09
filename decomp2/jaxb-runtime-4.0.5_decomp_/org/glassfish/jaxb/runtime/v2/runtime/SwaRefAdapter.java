package org.glassfish.jaxb.runtime.v2.runtime;

import jakarta.activation.DataHandler;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.attachment.AttachmentMarshaller;
import jakarta.xml.bind.attachment.AttachmentUnmarshaller;
import org.glassfish.jaxb.runtime.v2.runtime.unmarshaller.UnmarshallingContext;

public final class SwaRefAdapter extends XmlAdapter {
   public DataHandler unmarshal(String cid) {
      AttachmentUnmarshaller au = UnmarshallingContext.getInstance().parent.getAttachmentUnmarshaller();
      return au.getAttachmentAsDataHandler(cid);
   }

   public String marshal(DataHandler data) {
      if (data == null) {
         return null;
      } else {
         AttachmentMarshaller am = XMLSerializer.getInstance().attachmentMarshaller;
         return am.addSwaRefAttachment(data);
      }
   }
}
