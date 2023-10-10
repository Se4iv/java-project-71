package hexlet.code;

import picocli.CommandLine;
@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App {
    public static void main(String[] args) throws Exception {
       int exitcode = new CommandLine(new App()).execute(args);
       System.exit(exitcode);

    }
}
