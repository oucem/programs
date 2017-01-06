package ru.epatko.find;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         06.01.17.
 *
 * Used source code from the Find example:
 * https://docs.oracle.com/javase/tutorial/essential/io/find.html
 */

public class Find extends SimpleFileVisitor<Path> {

    /**
     * Path matcher.
     */
    private final PathMatcher matcher;
    /**
     * Total number of matches.
     */
    private int numMatches = 0;
    /**
     * Logger.
     */
    private Logger logger;

    /**
     * Recursively find files.
     * @param aPattern - a Pattern to find.
     * @param aLogFile - log file.
     * @throws IOException - exception.
     */
    Find(Pattern aPattern, String aLogFile) throws IOException {
        matcher = FileSystems.getDefault().getPathMatcher("regex:" + aPattern);
        try {
            File logFile = new File(aLogFile);
            logFile.delete();
            logFile.createNewFile();
            this.logger = new Logger(logFile);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * // Compares the regex pattern against the file or directory name.
     * @param path - path by file to compare.
     * @throws IOException - exception.
     */
    void find(Path path) throws IOException {
        Path fileName = path.getFileName();
        if (fileName != null && matcher.matches(fileName)) {
            numMatches++;
            this.logger.log(path.toString());
        }
    }
    /**
     * Logs the total number of matches to log file.
     * @throws IOException - exception.
     */
    void done() throws IOException {
        this.logger.log("------------------------------------------------");
        this.logger.log(String.format("Matched: %d", numMatches));
    }

    // Invoke the pattern matching method on each file.

    /**
     * Invoke the pattern matching method on each file.
     * @param file - path by file to compare.
     * @param attrs - basic file attributes.
     * @return - file visit result.
     * @throws IOException - exception.
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file);
        return CONTINUE;
    }
    /**
     * Invoke the pattern matching method on each directory.
     * @param dir - path by directory to compare.
     * @param attrs - basic directory attributes.
     * @return - directory visit result.
     * @throws IOException - exception.
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        find(dir);
        return CONTINUE;
    }

    /**
     * Reaction by path access exception.
     * @param file -  path by file or by directory.
     * @param exc - exception.
     * @return - reaction by exception.
     * @throws IOException - exception.
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        this.logger.log(exc.getMessage());
        return CONTINUE;
    }
    /**
     * Print help and exit program.
     */
    static void usage() {
        System.out.println("Incorrect argument(s). Use command syntax:");
        System.out.println("java -jar find.jar -d [START DIRECTORY] -n [FILE NAME] -{m, f, r} -o [LOG FILE]");
        System.out.println();
        System.out.println("-d [START DIRECTORY] - start directory name;");
        System.out.println("-n [FILE NAME] - file name to search;");
        System.out.print("-{m, f, r} - find options: m - by mask (mask example: txt, exe),");
        System.out.println(" f - by file name, r - by regular expression;");
        System.out.println("-o [LOG FILE] - log file name.");
        System.exit(0);
    }
    /**
     * Main method.
     * @param args - d [START DIRECTORY] -n [FILE NAME] -{m, f, r} -o [LOG FILE].
     * @throws IOException - exception.
     */
    public static void main(String[] args) throws IOException {
        /**
         * String pattern to compare.
         */
        String pattern = null;
        if (args.length != 7 || !"-d".equals(args[0]) || !"-n".equals(args[2]) || !"-o".equals(args[5])) {
            usage();
        }
        if ("-m".equals(args[4])) {
            if (args[3].contains("*") || args[3].contains(".")) {
                usage();
            } else {
                pattern = String.format(".*\\.%s$", args[3]);
            }
        } else if ("-f".equals(args[4])) {
           pattern = String.format("^%s$", args[3]);

        } else if ("-r".equals(args[4])) {
            pattern = args[3];

        } else {
            usage();
        }

        try {
            Path startingDir = Paths.get(args[1]);
            Pattern aPattern = Pattern.compile(pattern);
            Find finder = new Find(aPattern, args[6]);
            Files.walkFileTree(startingDir, finder);
            finder.done();
        } catch (PatternSyntaxException pse) {
            System.out.println(pse.getMessage());
        }
    }
}