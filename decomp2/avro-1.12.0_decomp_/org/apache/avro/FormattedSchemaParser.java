package org.apache.avro;

import java.io.IOException;
import java.net.URI;

public interface FormattedSchemaParser {
   Schema parse(ParseContext parseContext, URI baseUri, CharSequence formattedSchema) throws IOException, SchemaParseException;
}
