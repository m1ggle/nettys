package org.yahve.nio.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class TestFileWalkTree {
    public static void main(String[] args) throws IOException {
        walkTreeDeleteDir();
    }

    private static void walkTreeDeleteDir() throws IOException {
        Files.walkFileTree(Paths.get("D:\\dragonwell-8.16.17_bak"), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    private static void walkTreeToConsole() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\dragonwell-8.16.17"), new SimpleFileVisitor<Path>(){

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("======>" + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                fileCount.incrementAndGet();
                System.out.println(file);
                return super.visitFile(file, attrs);
            }


        });

        System.out.println("dirCount:" + dirCount);
        System.out.println("fileCount:" + fileCount);
    }
    private static void walkTreeJarToConsole() throws IOException {
        AtomicInteger jarCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\dragonwell-8.16.17"), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                jarCount.incrementAndGet();
                if (file.toString().endsWith(".jar")){
                    System.out.println(file);
                }
                return super.visitFile(file, attrs);
            }


        });

        System.out.println("dirCount:" + jarCount);
    }
}
