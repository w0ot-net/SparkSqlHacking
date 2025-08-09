package org.apache.logging.log4j.core;

import java.io.Serializable;
import java.util.Map;
import org.apache.logging.log4j.core.layout.Encoder;

public interface Layout extends Encoder {
   String ELEMENT_TYPE = "layout";

   byte[] getFooter();

   byte[] getHeader();

   byte[] toByteArray(LogEvent event);

   Serializable toSerializable(LogEvent event);

   String getContentType();

   Map getContentFormat();
}
