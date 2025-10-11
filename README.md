# PROGRAMACION-DE-SERVICIOS ⚙️

This repository contains Java exercises focused on **service programming fundamentals**, particularly **command-line argument handling** and basic I/O operations.

It includes several practical examples such as:
- `EchoCommandLine`: reads and echoes command-line input.  
- `EchoCommandLineMandatory`: validates mandatory parameters.  
- `EchoCommandLineOptional`: handles optional arguments gracefully.  
- `Tarea1.06`: a task combining service invocation and argument parsing.

The project is part of coursework for the *Programación de Servicios y Procesos* subject, exploring how Java programs interact through the command line and external processes.

---

## 🧠 Learning Goals

- Understand how to manage and validate command-line parameters  
- Implement service-like behavior using Java processes  
- Work with JAR execution (`java -jar`)  
- Practice clean input/output and argument parsing  

---

## 🛠️ Running

Compile or use provided `.jar` files:

```bash
java -jar EchoCommandLine.jar arg1 arg2
java -jar EchoCommandLineMandatory.jar --name=John --age=25
java -jar EchoCommandLineOptional.jar --verbose
