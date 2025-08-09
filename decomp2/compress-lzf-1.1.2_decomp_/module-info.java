module com.ning.compress.lzf {
   requires transitive java.xml;
   requires jdk.unsupported;

   exports com.ning.compress;
   exports com.ning.compress.gzip;
   exports com.ning.compress.lzf;
   exports com.ning.compress.lzf.impl;
   exports com.ning.compress.lzf.parallel;
   exports com.ning.compress.lzf.util;
}
