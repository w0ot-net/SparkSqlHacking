package org.apache.commons.cli;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

@FunctionalInterface
public interface Converter {
   Converter DEFAULT = (s) -> s;
   Converter CLASS = Class::forName;
   Converter FILE = File::new;
   Converter PATH = (x$0) -> Paths.get(x$0);
   Converter NUMBER = (s) -> (Number)(s.indexOf(46) != -1 ? Double.valueOf(s) : Long.valueOf(s));
   Converter OBJECT = (s) -> ((Class)CLASS.apply(s)).getConstructor().newInstance();
   Converter URL = URL::new;
   Converter DATE = (s) -> (new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")).parse(s);

   Object apply(String var1) throws Throwable;
}
