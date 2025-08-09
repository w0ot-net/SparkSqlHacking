package org.glassfish.jaxb.runtime;

import jakarta.xml.bind.ValidationEventLocator;

public interface ValidationEventLocatorEx extends ValidationEventLocator {
   String getFieldName();
}
