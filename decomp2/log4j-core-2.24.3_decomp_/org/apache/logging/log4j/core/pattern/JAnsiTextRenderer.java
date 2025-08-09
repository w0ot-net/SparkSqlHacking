package org.apache.logging.log4j.core.pattern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Strings;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiRenderer;
import org.fusesource.jansi.AnsiRenderer.Code;

public final class JAnsiTextRenderer implements TextRenderer {
   public static final Map DefaultExceptionStyleMap;
   static final Map DefaultMessageStyleMap;
   private static final Map PrefedinedStyleMaps;
   private final String beginToken;
   private final int beginTokenLen;
   private final String endToken;
   private final int endTokenLen;
   private final Map styleMap;

   private static void put(final Map map, final String name, final AnsiRenderer.Code... codes) {
      map.put(name, codes);
   }

   public JAnsiTextRenderer(final String[] formats, final Map defaultStyleMap) {
      String tempBeginToken = "@|";
      String tempEndToken = "|@";
      Map<String, AnsiRenderer.Code[]> map;
      if (formats.length > 1) {
         String allStylesStr = formats[1];
         String[] allStyleAssignmentsArr = allStylesStr.split(" ");
         map = new HashMap(allStyleAssignmentsArr.length + defaultStyleMap.size());
         map.putAll(defaultStyleMap);

         for(String styleAssignmentStr : allStyleAssignmentsArr) {
            String[] styleAssignmentArr = styleAssignmentStr.split("=");
            if (styleAssignmentArr.length != 2) {
               StatusLogger.getLogger().warn("{} parsing style \"{}\", expected format: StyleName=Code(,Code)*", this.getClass().getSimpleName(), styleAssignmentStr);
            } else {
               String styleName = styleAssignmentArr[0];
               String codeListStr = styleAssignmentArr[1];
               String[] codeNames = codeListStr.split(",");
               if (codeNames.length == 0) {
                  StatusLogger.getLogger().warn("{} parsing style \"{}\", expected format: StyleName=Code(,Code)*", this.getClass().getSimpleName(), styleAssignmentStr);
               } else {
                  AnsiRenderer.Code[] codes;
                  int i;
                  switch (styleName) {
                     case "BeginToken":
                        tempBeginToken = codeNames[0];
                        continue;
                     case "EndToken":
                        tempEndToken = codeNames[0];
                        continue;
                     case "StyleMapName":
                        String predefinedMapName = codeNames[0];
                        Map<String, AnsiRenderer.Code[]> predefinedMap = (Map)PrefedinedStyleMaps.get(predefinedMapName);
                        if (predefinedMap != null) {
                           map.putAll(predefinedMap);
                        } else {
                           StatusLogger.getLogger().warn("Unknown predefined map name {}, pick one of {}", predefinedMapName, (Object)null);
                        }
                        continue;
                     default:
                        codes = new AnsiRenderer.Code[codeNames.length];
                        i = 0;
                  }

                  while(i < codes.length) {
                     codes[i] = this.toCode(codeNames[i]);
                     ++i;
                  }

                  map.put(styleName, codes);
               }
            }
         }
      } else {
         map = defaultStyleMap;
      }

      this.styleMap = map;
      this.beginToken = tempBeginToken;
      this.endToken = tempEndToken;
      this.beginTokenLen = tempBeginToken.length();
      this.endTokenLen = tempEndToken.length();
   }

   public Map getStyleMap() {
      return this.styleMap;
   }

   private void render(final Ansi ansi, final AnsiRenderer.Code code) {
      if (code.isColor()) {
         if (code.isBackground()) {
            ansi.bg(code.getColor());
         } else {
            ansi.fg(code.getColor());
         }
      } else if (code.isAttribute()) {
         ansi.a(code.getAttribute());
      }

   }

   private void render(final Ansi ansi, final AnsiRenderer.Code... codes) {
      for(AnsiRenderer.Code code : codes) {
         this.render(ansi, code);
      }

   }

   private String render(final String text, final String... names) {
      Ansi ansi = Ansi.ansi();

      for(String name : names) {
         AnsiRenderer.Code[] codes = (AnsiRenderer.Code[])this.styleMap.get(name);
         if (codes != null) {
            this.render(ansi, codes);
         } else {
            this.render(ansi, this.toCode(name));
         }
      }

      return ansi.a(text).reset().toString();
   }

   public void render(final String input, final StringBuilder output, final String styleName) throws IllegalArgumentException {
      output.append(this.render(input, styleName));
   }

   public void render(final StringBuilder input, final StringBuilder output) throws IllegalArgumentException {
      int i = 0;

      while(true) {
         int j = input.indexOf(this.beginToken, i);
         if (j == -1) {
            if (i == 0) {
               output.append(input);
               return;
            }

            output.append(input.substring(i, input.length()));
            return;
         }

         output.append(input.substring(i, j));
         int k = input.indexOf(this.endToken, j);
         if (k == -1) {
            output.append(input);
            return;
         }

         j += this.beginTokenLen;
         String spec = input.substring(j, k);
         String[] items = spec.split(" ", 2);
         if (items.length == 1) {
            output.append(input);
            return;
         }

         String replacement = this.render(items[1], items[0].split(","));
         output.append(replacement);
         i = k + this.endTokenLen;
      }
   }

   private AnsiRenderer.Code toCode(final String name) {
      return Code.valueOf(Strings.toRootUpperCase(name));
   }

   public String toString() {
      return "JAnsiMessageRenderer [beginToken=" + this.beginToken + ", beginTokenLen=" + this.beginTokenLen + ", endToken=" + this.endToken + ", endTokenLen=" + this.endTokenLen + ", styleMap=" + this.styleMap + "]";
   }

   static {
      Map<String, Map<String, AnsiRenderer.Code[]>> tempPreDefs = new HashMap();
      Map<String, AnsiRenderer.Code[]> map = new HashMap();
      put(map, "Prefix", Code.WHITE);
      put(map, "Name", Code.BG_RED, Code.WHITE);
      put(map, "NameMessageSeparator", Code.BG_RED, Code.WHITE);
      put(map, "Message", Code.BG_RED, Code.WHITE, Code.BOLD);
      put(map, "At", Code.WHITE);
      put(map, "CauseLabel", Code.WHITE);
      put(map, "Text", Code.WHITE);
      put(map, "More", Code.WHITE);
      put(map, "Suppressed", Code.WHITE);
      put(map, "StackTraceElement.ClassLoaderName", Code.WHITE);
      put(map, "StackTraceElement.ClassLoaderSeparator", Code.WHITE);
      put(map, "StackTraceElement.ModuleName", Code.WHITE);
      put(map, "StackTraceElement.ModuleVersionSeparator", Code.WHITE);
      put(map, "StackTraceElement.ModuleVersion", Code.WHITE);
      put(map, "StackTraceElement.ModuleNameSeparator", Code.WHITE);
      put(map, "StackTraceElement.ClassName", Code.YELLOW);
      put(map, "StackTraceElement.ClassMethodSeparator", Code.YELLOW);
      put(map, "StackTraceElement.MethodName", Code.YELLOW);
      put(map, "StackTraceElement.NativeMethod", Code.YELLOW);
      put(map, "StackTraceElement.FileName", Code.RED);
      put(map, "StackTraceElement.LineNumber", Code.RED);
      put(map, "StackTraceElement.Container", Code.RED);
      put(map, "StackTraceElement.ContainerSeparator", Code.WHITE);
      put(map, "StackTraceElement.UnknownSource", Code.RED);
      put(map, "ExtraClassInfo.Inexact", Code.YELLOW);
      put(map, "ExtraClassInfo.Container", Code.YELLOW);
      put(map, "ExtraClassInfo.ContainerSeparator", Code.YELLOW);
      put(map, "ExtraClassInfo.Location", Code.YELLOW);
      put(map, "ExtraClassInfo.Version", Code.YELLOW);
      DefaultExceptionStyleMap = Collections.unmodifiableMap(map);
      tempPreDefs.put("Spock", DefaultExceptionStyleMap);
      map = new HashMap();
      put(map, "Prefix", Code.WHITE);
      put(map, "Name", Code.BG_RED, Code.YELLOW, Code.BOLD);
      put(map, "NameMessageSeparator", Code.BG_RED, Code.YELLOW);
      put(map, "Message", Code.BG_RED, Code.WHITE, Code.BOLD);
      put(map, "At", Code.WHITE);
      put(map, "CauseLabel", Code.WHITE);
      put(map, "Text", Code.WHITE);
      put(map, "More", Code.WHITE);
      put(map, "Suppressed", Code.WHITE);
      put(map, "StackTraceElement.ClassLoaderName", Code.WHITE);
      put(map, "StackTraceElement.ClassLoaderSeparator", Code.WHITE);
      put(map, "StackTraceElement.ModuleName", Code.WHITE);
      put(map, "StackTraceElement.ModuleVersionSeparator", Code.WHITE);
      put(map, "StackTraceElement.ModuleVersion", Code.WHITE);
      put(map, "StackTraceElement.ModuleNameSeparator", Code.WHITE);
      put(map, "StackTraceElement.ClassName", Code.BG_RED, Code.WHITE);
      put(map, "StackTraceElement.ClassMethodSeparator", Code.BG_RED, Code.YELLOW);
      put(map, "StackTraceElement.MethodName", Code.BG_RED, Code.YELLOW);
      put(map, "StackTraceElement.NativeMethod", Code.BG_RED, Code.YELLOW);
      put(map, "StackTraceElement.FileName", Code.RED);
      put(map, "StackTraceElement.LineNumber", Code.RED);
      put(map, "StackTraceElement.Container", Code.RED);
      put(map, "StackTraceElement.ContainerSeparator", Code.WHITE);
      put(map, "StackTraceElement.UnknownSource", Code.RED);
      put(map, "ExtraClassInfo.Inexact", Code.YELLOW);
      put(map, "ExtraClassInfo.Container", Code.WHITE);
      put(map, "ExtraClassInfo.ContainerSeparator", Code.WHITE);
      put(map, "ExtraClassInfo.Location", Code.YELLOW);
      put(map, "ExtraClassInfo.Version", Code.YELLOW);
      tempPreDefs.put("Kirk", Collections.unmodifiableMap(map));
      map = new HashMap();
      DefaultMessageStyleMap = Collections.unmodifiableMap(map);
      PrefedinedStyleMaps = Collections.unmodifiableMap(tempPreDefs);
   }
}
