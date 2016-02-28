package org.jshell.commands;

import java.util.Scanner;

import org.jshell.Jshell;
import org.jshell.utils.JshellCommand;

public class JshellLeftPrompt extends JshellCommand {

    private String prompt;
    private String input;
    private Scanner userIn;

    public JshellLeftPrompt() {
    }

    @Override
    public void initialize() throws Exception {
        // Get the prompt. This will later be put into an env variable
        prompt = "$ ";
        // Print the prompt
        System.out.print(prompt);
        // Create the Scanner
        userIn = new Scanner(System.in);
    }

    @Override
    public void execute() throws Exception {
        input = userIn.nextLine();
    }

    @Override
    public boolean isFinished() throws Exception {
        return input != null;
    }

    @Override
    public void end() throws Exception {
        Jshell.scheduler.schedule(new JshellProcRunner(input.split(" ")));
        Jshell.scheduleRequiredCommands();
        userIn.close();
    }

    @Override
    public void interrupted() {
        userIn.close();
    }

}
