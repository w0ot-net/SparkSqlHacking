package org.codehaus.commons.compiler.util.reflect;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.AccessControlException;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.Map;
import org.codehaus.commons.nullanalysis.Nullable;

public class ByteArrayClassLoader extends ClassLoader {
   private final Map classes;
   private Map resources = Collections.emptyMap();

   public ByteArrayClassLoader(Map classes) {
      this.classes = classes;
   }

   public ByteArrayClassLoader(Map classes, ClassLoader parent) {
      super(parent);
      this.classes = classes;
   }

   public void setResources(Map resources) {
      this.resources = resources;
   }

   protected Class findClass(@Nullable String name) throws ClassNotFoundException {
      assert name != null;

      byte[] data = (byte[])this.classes.get(name);
      if (data == null) {
         data = (byte[])this.classes.get(name.replace('.', '/') + ".class");
         if (data == null) {
            throw new ClassNotFoundException(name);
         }
      }

      ProtectionDomain protectionDomain;
      try {
         protectionDomain = this.getClass().getProtectionDomain();
      } catch (AccessControlException var5) {
         protectionDomain = null;
      }

      return super.defineClass(name, data, 0, data.length, protectionDomain);
   }

   public InputStream getResourceAsStream(String name) {
      InputStream result = super.getResourceAsStream(name);
      if (result != null) {
         return result;
      } else {
         byte[] ba = (byte[])this.resources.get(name);
         return ba == null ? null : new ByteArrayInputStream(ba);
      }
   }
}
