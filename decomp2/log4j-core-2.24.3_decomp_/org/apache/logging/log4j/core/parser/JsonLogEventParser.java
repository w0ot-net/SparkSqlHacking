package org.apache.logging.log4j.core.parser;

import org.apache.logging.log4j.core.jackson.Log4jJsonObjectMapper;

public class JsonLogEventParser extends AbstractJacksonLogEventParser {
   public JsonLogEventParser() {
      super(new Log4jJsonObjectMapper());
   }
}
