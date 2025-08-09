package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.activation.MimeType;
import jakarta.activation.MimeTypeParseException;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSchemaTypes;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationReader;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationSource;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;

final class Util {
   static QName calcSchemaType(AnnotationReader reader, AnnotationSource primarySource, Object enclosingClass, Object individualType, Locatable src) {
      XmlSchemaType xst = (XmlSchemaType)primarySource.readAnnotation(XmlSchemaType.class);
      if (xst != null) {
         return new QName(xst.namespace(), xst.name());
      } else {
         XmlSchemaTypes xsts = (XmlSchemaTypes)reader.getPackageAnnotation(XmlSchemaTypes.class, enclosingClass, src);
         XmlSchemaType[] values = null;
         if (xsts != null) {
            values = xsts.value();
         } else {
            xst = (XmlSchemaType)reader.getPackageAnnotation(XmlSchemaType.class, enclosingClass, src);
            if (xst != null) {
               values = new XmlSchemaType[]{xst};
            }
         }

         if (values != null) {
            for(XmlSchemaType item : values) {
               if (reader.getClassValue(item, "type").equals(individualType)) {
                  return new QName(item.namespace(), item.name());
               }
            }
         }

         return null;
      }
   }

   static MimeType calcExpectedMediaType(AnnotationSource primarySource, ModelBuilder builder) {
      XmlMimeType xmt = (XmlMimeType)primarySource.readAnnotation(XmlMimeType.class);
      if (xmt == null) {
         return null;
      } else {
         try {
            return new MimeType(xmt.value());
         } catch (MimeTypeParseException e) {
            builder.reportError(new IllegalAnnotationException(Messages.ILLEGAL_MIME_TYPE.format(xmt.value(), e.getMessage()), xmt));
            return null;
         }
      }
   }
}
