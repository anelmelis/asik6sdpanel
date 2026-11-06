# Assignment 6 — Visitor Pattern

SE-2527 MELIS ANEL 

## Advanced File System

Java 17-compatible Maven project implementing the Visitor design pattern.

## Description

This project implements an in-memory hierarchical file system.

It supports files, directories, and three independent operations using the Visitor pattern.

## Features

- TotalSizeVisitor — calculates the total size of files.
- SearchVisitor — searches files by filename and minimum size.
- StatisticsVisitor — counts files, directories, and maximum depth.
- FileSystemWalker — traverses the file system.
- Tree integrity — prevents negative file sizes, duplicate names, cycles, and multiple parent ownership.

## Requirements

- Java 17 or newer
- Apache Maven

## Run Tests

From the project root:

```bash
mvn test
```

## Run Application

From the project root:

```bash
mvn compile
java -cp target/classes Main
```

## Expected Demo Output

```text
Total size: 1000 bytes
Search results:
notes.txt
Statistics:
Files: 3
Directories: 2
Maximum depth: 2
```

## Testing

The project includes 12 JUnit 5 tests covering file system operations, traversal, double dispatch, repeat execution, and tree integrity.

## Design Decisions

FileSystemWalker owns traversal. Visitors perform independent operations without recursively traversing directories themselves.

Paths are relative to the selected root and use `/` separators.

Maximum depth starts at 0 for the root element.

Visitor instances are reset before each traversal to avoid stale results.

New visitors can be added without modifying FileNode or DirectoryNode. Adding new element types requires extending the Visitor interface and updating existing visitors.
