package au.com.bytecode.opencsv.bean;

import au.com.bytecode.opencsv.CSVReader;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;

public interface MappingStrategy {
   PropertyDescriptor findDescriptor(int var1) throws IntrospectionException;

   Object createBean() throws InstantiationException, IllegalAccessException;

   void captureHeader(CSVReader var1) throws IOException;
}
