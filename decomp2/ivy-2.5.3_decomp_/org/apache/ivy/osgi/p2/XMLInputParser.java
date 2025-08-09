package org.apache.ivy.osgi.p2;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import org.xml.sax.SAXException;

public interface XMLInputParser {
   void parse(InputStream var1) throws IOException, ParseException, SAXException;
}
