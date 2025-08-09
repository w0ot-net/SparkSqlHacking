module org.apache.arrow.memory.core {
   requires java.compiler;
   requires transitive jdk.unsupported;
   requires static org.checkerframework.checker.qual;
   requires static org.immutables.value.annotations;
   requires static com.google.errorprone.annotations;
   requires org.slf4j;

   exports org.apache.arrow.memory;
   exports org.apache.arrow.memory.rounding;
   exports org.apache.arrow.memory.util;
   exports org.apache.arrow.memory.util.hash;
   exports org.apache.arrow.util;
}
