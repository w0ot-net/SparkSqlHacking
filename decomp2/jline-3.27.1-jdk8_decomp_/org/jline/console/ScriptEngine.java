package org.jline.console;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.jline.reader.Completer;

public interface ScriptEngine {
   String getEngineName();

   Collection getExtensions();

   Completer getScriptCompleter();

   boolean hasVariable(String var1);

   void put(String var1, Object var2);

   Object get(String var1);

   default Map find() {
      return this.find((String)null);
   }

   Map find(String var1);

   void del(String... var1);

   String toJson(Object var1);

   String toString(Object var1);

   Map toMap(Object var1);

   default Object deserialize(String value) {
      return this.deserialize(value, (String)null);
   }

   Object deserialize(String var1, String var2);

   List getSerializationFormats();

   List getDeserializationFormats();

   void persist(Path var1, Object var2);

   void persist(Path var1, Object var2, String var3);

   Object execute(String var1) throws Exception;

   default Object execute(File script) throws Exception {
      return this.execute((File)script, (Object[])null);
   }

   Object execute(File var1, Object[] var2) throws Exception;

   Object execute(Object var1, Object... var2);
}
