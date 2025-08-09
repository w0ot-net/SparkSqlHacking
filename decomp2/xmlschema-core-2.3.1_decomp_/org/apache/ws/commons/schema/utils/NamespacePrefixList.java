package org.apache.ws.commons.schema.utils;

import javax.xml.namespace.NamespaceContext;

public interface NamespacePrefixList extends NamespaceContext {
   String[] getDeclaredPrefixes();
}
