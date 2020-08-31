package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int idx;
    public DeleteCommand(int idx) {
        super();
        this.cmd = CMD.DONE;
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.display("TASK IZ NAO DELETZ!!!!1!11!\n" + "  "
                    + taskList.popTask(this.idx)
                    + "\nNAO U HAS " + taskList.getNumberOfTasks() + " FINGS IN DA LIST LULZIES");
        storage.save(taskList);
    }

    @Override
    public String toString() {
        return cmd.toString() + ": " + idx;
    }
}