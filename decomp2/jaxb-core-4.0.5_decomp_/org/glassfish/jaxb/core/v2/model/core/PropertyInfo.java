package org.glassfish.jaxb.core.v2.model.core;

import com.sun.istack.Nullable;
import jakarta.activation.MimeType;
import java.util.Collection;
import javax.xml.namespace.QName;
import org.glassfish.jaxb.core.v2.model.annotation.AnnotationSource;

public interface PropertyInfo extends AnnotationSource {
   TypeInfo parent();

   String getName();

   String displayName();

   boolean isCollection();

   Collection ref();

   PropertyKind kind();

   Adapter getAdapter();

   ID id();

   MimeType getExpectedMimeType();

   boolean inlineBinaryData();

   @Nullable
   QName getSchemaType();
}
