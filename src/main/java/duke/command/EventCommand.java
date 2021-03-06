package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    private String taskName;
    private String by;

    public EventCommand(String taskName, String by) {
        super();
        this.cmd = CMD.EVENT;
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public String getResponse(TaskList tasklist, Storage storage) throws DukeException {
        String msg = "I PUT NEW TING IN DA LIST\n  " + tasklist.addEvent(this.taskName, this.by)
                + "\nNAO U HAS " + tasklist.getNumberOfTasks() + " FINGS IN DA LIST LULZIES";
        storage.save(tasklist);
        return msg;
    }

    @Override
    public String toString() {
        return this.cmd.toString() + ": " + this.taskName + "(" + this.by + ")";
    }
}
