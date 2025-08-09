package org.apache.avro;

import java.util.regex.Pattern;

class SchemaFormatterFactoryConstants {
   static final Pattern SIMPLE_NAME_PATTERN = Pattern.compile("([a-z][0-9a-z]*)" + SchemaFormatterFactory.class.getSimpleName(), 66);
}
