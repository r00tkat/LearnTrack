# Setup Instructions

## JDK Version Used
This project was built and tested with **JDK 17 (LTS)**:

```
java version "17.0.12" 2024-07-16 LTS
Java(TM) SE Runtime Environment (build 17.0.12+8-LTS-286)
javac 17.0.12
```

## How to Verify Java is Installed
Open a terminal (PowerShell on Windows) and run:

```
java -version
javac -version
```

Both commands should print version 17.x. If they do not, install the JDK and add it to your system PATH.

## Running the Project (IntelliJ IDEA) — Recommended
1. Open the `LearnTrack` folder in IntelliJ IDEA.
2. Ensure `src` is marked as the **Sources Root** (right-click `src` → *Mark Directory as* → *Sources Root*).
3. Open `src/com/airtribe/learntrack/Main.java`.
4. Click the green ▶ arrow next to the class → **Run 'Main.main()'**.
5. The interactive menu appears in the Run panel at the bottom.

## Running the Project (Terminal)
From the project root (`C:\Course\LearnTrack`):

**Windows (PowerShell):**
```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out com.learntrack.Main
```

**macOS / Linux:**
```bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.learntrack.Main
```

> Both approaches compile every `.java` file under `src` into an `out` folder, then run the `Main` class using its fully qualified name.

## Hello World Check (Environment Sanity Test)
Before building the main project, a simple test confirmed Java was working:

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

Compiled with `javac Hello.java` and run with `java Hello`, it printed:

```
Hello World
```

This confirmed the JDK was correctly installed and available on the system PATH.