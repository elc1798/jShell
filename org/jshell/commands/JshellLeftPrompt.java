package org.jshell.commands;

import org.jshell.Jshell;
import org.jshell.utils.JshellCommand;

public class JshellLeftPrompt extends JshellCommand {

    private String prompt;
    private String input;

    public JshellLeftPrompt() {
    }

    @Override
    public void initialize() throws Exception {
        // Get the prompt. This will later be put into an env variable
        prompt = "$ ";
        // Print the prompt
        System.out.print(prompt);
    }

    @Override
    public void execute() throws Exception {
        input = Jshell.stdin.nextLine();
    }

    @Override
    public boolean isFinished() throws Exception {
        return input != null;
    }

    @Override
    public void end() throws Exception {
        Jshell.scheduler.schedule(new JshellProcRunner(input.split(" ")));
        Jshell.scheduleRequiredCommands();
    }

    @Override
    public void interrupted() {
    }

}
