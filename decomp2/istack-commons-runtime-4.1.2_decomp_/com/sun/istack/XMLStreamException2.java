package com.sun.istack;

import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;

public class XMLStreamException2 extends XMLStreamException {
   private static final long serialVersionUID = 1409033131880742500L;

   public XMLStreamException2(String msg) {
      super(msg);
   }

   public XMLStreamException2(Throwable th) {
      super(th);
   }

   public XMLStreamException2(String msg, Throwable th) {
      super(msg, th);
   }

   public XMLStreamException2(String msg, Location location) {
      super(msg, location);
   }

   public XMLStreamException2(String msg, Location location, Throwable th) {
      super(msg, location, th);
   }

   public Throwable getCause() {
      return this.getNestedException();
   }
}
