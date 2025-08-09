package org.apache.logging.log4j.core.config.plugins.convert;

public interface TypeConverter {
   Object convert(String s) throws Exception;
}
