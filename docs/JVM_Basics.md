# JVM Basics

## JDK, JRE, JVM

**JVM (Java Virtual Machine)** is the engine that runs Java programs. It reads compiled bytecode and executes it on whatever operating system you are using.

**JRE (Java Runtime Environment)** is the JVM plus the standard libraries a program needs to run. If you only want to *run* Java programs, the JRE is enough.

**JDK (Java Development Kit)** is the full developer toolkit. It contains the JRE plus development tools — most importantly the compiler `javac`. Building this project requires the JDK.

In short: **the JDK contains the JRE, and the JRE contains the JVM.**

## What is Bytecode?

When you compile a `.java` file with `javac`, you do not get machine code for your specific computer. You get a `.class` file containing **bytecode** — a compact, platform-independent intermediate language. The JVM reads this bytecode and translates it into instructions the local machine understands at run time.

## What does "Write Once, Run Anywhere" mean?

Because Java compiles to bytecode instead of to one operating system's machine code, the same compiled `.class` file can run on Windows, macOS, or Linux — as long as that machine has a JVM. You write and compile your code once, and any JVM can run it. This portability is what "write once, run anywhere" refers to, and it is one of Java's defining strengths.