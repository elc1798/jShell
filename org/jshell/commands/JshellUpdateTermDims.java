package org.jshell.commands;

import static org.jshell.Constants.rows;
import static org.jshell.Constants.columns;

import org.jshell.Jshell;
import org.jshell.JshellObject;
import org.jshell.utils.JshellCommand;

public class JshellUpdateTermDims extends JshellCommand {

    private int oldRows;
    private int oldCols;

    public JshellUpdateTermDims() {
    }

    @Override
    public void initialize() throws Exception {
        oldRows = (Integer) Jshell.env.get(rows).getValue();
        oldCols = (Integer) Jshell.env.get(columns).getValue();
    }

    @Override
    public void execute() throws Exception {
    }

    @Override
    public boolean isFinished() throws Exception {
        return true;
    }

    @Override
    public void end() throws Exception {
        Jshell.updateTermDimensions();
    }

    @Override
    public void interrupted() {
        Jshell.env.set(rows, new JshellObject(oldRows));
        Jshell.env.set(columns, new JshellObject(oldCols));
    }

}
