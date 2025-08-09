package py4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;
import py4j.reflection.TypeUtil;

public class JVMView {
   private ConcurrentMap singleImportsMap;
   private Set starImports;
   private Set lastImportSearches;
   private String name;
   private String id;
   private AtomicInteger sequenceId = new AtomicInteger(1);
   public static final String JAVA_LANG_STAR_IMPORT = "java.lang";

   public JVMView(String name, String id) {
      this.name = name;
      this.id = id;
      this.starImports = new ConcurrentSkipListSet();
      this.lastImportSearches = new ConcurrentSkipListSet();
      this.singleImportsMap = new ConcurrentHashMap();
      this.starImports.add("java.lang");
   }

   public void addSingleImport(String singleImport) {
      String simpleName = TypeUtil.getName(singleImport, true);
      this.singleImportsMap.putIfAbsent(simpleName, singleImport);
      this.sequenceId.incrementAndGet();
   }

   public void addStarImport(String starImport) {
      String packageName = TypeUtil.getPackage(starImport);
      if (!this.starImports.contains(packageName)) {
         this.starImports.add(packageName);
      }

   }

   public void clearImports() {
      this.singleImportsMap.clear();
      this.starImports.clear();
      this.starImports.add("java.lang");
      this.sequenceId.incrementAndGet();
   }

   public String getId() {
      return this.id;
   }

   public Set getLastImportSearches() {
      return this.lastImportSearches;
   }

   public String getName() {
      return this.name;
   }

   public Map getSingleImportsMap() {
      return this.singleImportsMap;
   }

   public Set getStarImports() {
      return this.starImports;
   }

   public boolean removeSingleImport(String importString) {
      boolean removed = false;
      String simpleName = TypeUtil.getName(importString, true);
      removed = this.singleImportsMap.remove(simpleName, importString);
      this.sequenceId.incrementAndGet();
      return removed;
   }

   public boolean removeStarImport(String starImport) {
      String packageName = TypeUtil.getPackage(starImport);
      boolean result = this.starImports.remove(packageName);
      return result;
   }

   public void setId(String id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String[] getImportedNames() {
      Set<String> namesSet = this.singleImportsMap.keySet();
      return (String[])namesSet.toArray(new String[namesSet.size()]);
   }

   public int getSequenceId() {
      return this.sequenceId.get();
   }
}
