package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public enum StandardSystemProperty {
   JAVA_VERSION("java.version"),
   JAVA_VENDOR("java.vendor"),
   JAVA_VENDOR_URL("java.vendor.url"),
   JAVA_HOME("java.home"),
   JAVA_VM_SPECIFICATION_VERSION("java.vm.specification.version"),
   JAVA_VM_SPECIFICATION_VENDOR("java.vm.specification.vendor"),
   JAVA_VM_SPECIFICATION_NAME("java.vm.specification.name"),
   JAVA_VM_VERSION("java.vm.version"),
   JAVA_VM_VENDOR("java.vm.vendor"),
   JAVA_VM_NAME("java.vm.name"),
   JAVA_SPECIFICATION_VERSION("java.specification.version"),
   JAVA_SPECIFICATION_VENDOR("java.specification.vendor"),
   JAVA_SPECIFICATION_NAME("java.specification.name"),
   JAVA_CLASS_VERSION("java.class.version"),
   JAVA_CLASS_PATH("java.class.path"),
   JAVA_LIBRARY_PATH("java.library.path"),
   JAVA_IO_TMPDIR("java.io.tmpdir"),
   JAVA_COMPILER("java.compiler"),
   /** @deprecated */
   @Deprecated
   JAVA_EXT_DIRS("java.ext.dirs"),
   OS_NAME("os.name"),
   OS_ARCH("os.arch"),
   OS_VERSION("os.version"),
   FILE_SEPARATOR("file.separator"),
   PATH_SEPARATOR("path.separator"),
   LINE_SEPARATOR("line.separator"),
   USER_NAME("user.name"),
   USER_HOME("user.home"),
   USER_DIR("user.dir");

   private final String key;

   private StandardSystemProperty(String key) {
      this.key = key;
   }

   public String key() {
      return this.key;
   }

   @CheckForNull
   public String value() {
      return System.getProperty(this.key);
   }

   public String toString() {
      return this.key() + "=" + this.value();
   }

   // $FF: synthetic method
   private static StandardSystemProperty[] $values() {
      return new StandardSystemProperty[]{JAVA_VERSION, JAVA_VENDOR, JAVA_VENDOR_URL, JAVA_HOME, JAVA_VM_SPECIFICATION_VERSION, JAVA_VM_SPECIFICATION_VENDOR, JAVA_VM_SPECIFICATION_NAME, JAVA_VM_VERSION, JAVA_VM_VENDOR, JAVA_VM_NAME, JAVA_SPECIFICATION_VERSION, JAVA_SPECIFICATION_VENDOR, JAVA_SPECIFICATION_NAME, JAVA_CLASS_VERSION, JAVA_CLASS_PATH, JAVA_LIBRARY_PATH, JAVA_IO_TMPDIR, JAVA_COMPILER, JAVA_EXT_DIRS, OS_NAME, OS_ARCH, OS_VERSION, FILE_SEPARATOR, PATH_SEPARATOR, LINE_SEPARATOR, USER_NAME, USER_HOME, USER_DIR};
   }
}
