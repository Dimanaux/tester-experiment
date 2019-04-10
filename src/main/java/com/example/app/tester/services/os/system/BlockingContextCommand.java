package com.example.app.tester.services.os.system;

import com.example.app.tester.services.os.system.SystemCommand;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ContextCommand let you run your command in context of your program!
 * Let you run it in specified directory.
 */
public class BlockingContextCommand implements SystemCommand {
    private File path;
    private String command;
    private StringBuilder output = new StringBuilder();
    private StringBuilder errors = new StringBuilder();

    public BlockingContextCommand(File path, String command) {
        this.path = path;
        this.command = command;
    }

    /**
     * runs the command
     *
     * @param args - params of the command to be run
     */
    @Override
    public void run(String... args) throws IOException {
        final String[] shellCommand = {/*"bash", "-c",*/ command};

        String[] commandWithArgs = Arrays.copyOf(
                shellCommand,
                shellCommand.length + args.length
        );

        // compose array of {"bash", "-c", command, ...args}
        System.arraycopy(args, 0, commandWithArgs, shellCommand.length, args.length);

        // pass null to save the context - use env.vars of this program
        Process process = Runtime.getRuntime().exec(
                commandWithArgs,
                null,
                path
        );

        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // saving process output
        Scanner outputStream = new Scanner(process.getInputStream()).useDelimiter("\\A");
        outputStream.forEachRemaining(output::append);

        Scanner errorStream = new Scanner(process.getErrorStream()).useDelimiter("\\A");
        errorStream.forEachRemaining(errors::append);
    }

    /**
     * @return the output of command run
     */
    @Override
    public String getOutput() {
        return output.toString();
    }

    /**
     * @return error if present
     */
    @Override
    public String getError() {
        return errors.toString();
    }
}
