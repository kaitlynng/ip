package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Command {
    protected CMD cmd;
    protected boolean isExit;

    public Command() {
        this.cmd = CMD.DEFAULT;
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("CAN I HAZ CHEEZBURGER?");
    }

    @Override
    public String toString() {
        return cmd.toString();
    }
}