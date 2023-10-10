package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.io.File;
import java.nio.file.Path;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {

    @Parameters(index = "filepath1", description = "path to first file")
    private Path filepath1;
    @Parameters(index = "filepath2", description = "path to second file")
    private Path filepath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;
    public static void main(String[] args) throws Exception {
       int exitcode = new CommandLine(new App()).execute(args);
       System.exit(exitcode);

    }
}
