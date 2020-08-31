package duke.parser;

import duke.DukeException;
import duke.command.*;
import duke.datetime.DateTimeFormat;
import duke.datetime.DateTimeUtility;

public class Parser {
    public static Command parse(String userInput) throws DukeException {
        int space_idx = userInput.indexOf(' ');
        CMD cmd;

        try {
            cmd = CMD.valueOf((space_idx  == -1 ? userInput : userInput.substring(0, space_idx)).toUpperCase());
        } catch (IllegalArgumentException e) {
            cmd = CMD.DEFAULT;
        }

        String rest = space_idx == -1 ? "" : userInput.substring(space_idx + 1).trim();
        int dateStrIdx;
        String dateStr;
        String item;

        switch(cmd) {
            case BYE:
                return new ByeCommand();

            case LIST:
                dateStrIdx = rest.indexOf("/by");
                if (dateStrIdx == -1) {
                    return new ListCommand();
                } else {
                    dateStr = rest.substring(dateStrIdx + 3).trim();
                    if (DateTimeUtility.checkDateTimeType(dateStr) == DateTimeFormat.String) {
                        throw new DukeException("U NID 2 GIV CORRECT DATE FOMAT!");
                    } else {
                        return new ListCommand(dateStr);
                    }
                }

            case TODO:
                if (rest.isEmpty()) {
                    throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR TODO ITEM LULZ");
                } else {
                    return new TodoCommand(rest);
                }

            case DEADLINE:
                if (rest.isEmpty()) {
                    throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR DEDLINE ITEM LULZ");
                } else {
                    dateStrIdx = rest.indexOf("/by");
                    if (dateStrIdx == -1) {
                        throw new DukeException("ME FINKZ U NED 2 GIV DATE 4 TIEM 4 DA DEDLINE USIN /by");
                    } else {
                        item = rest.substring(0, dateStrIdx).trim();
                        dateStr = rest.substring(dateStrIdx + 3).trim();

                        if (item.isEmpty()) {
                            throw new DukeException("ME FINKZ U NED 2 GIV DA DEDLINE A NAEM");
                        } else if (dateStr.isEmpty()) {
                            throw new DukeException("ME FINKZ U NED 2 PUT SUMTHIN 4 DA DATE OR TIEM");
                        } else {
                            return new DeadlineCommand(item, dateStr);
                        }
                    }
                }

            case EVENT:
                if (rest.isEmpty()) {
                    throw new DukeException("ME FINKZ DAT U NED 2 ENTR NAYM 4 UR EVENT ITEM LULZ");
                } else {
                    dateStrIdx = rest.indexOf("/at");
                    if (dateStrIdx == -1) {
                        throw new DukeException("ME FINKZ U NED 2 GIV DATE 4 TIEM 4 DA EVENT USIN /at");
                    } else {
                        item = rest.substring(0, dateStrIdx).trim();
                        dateStr = rest.substring(dateStrIdx + 3).trim();

                        if (item.isEmpty()) {
                            throw new DukeException("ME FINKZ U NED 2 GIV DA EVENT A NAEM");
                        } else if (dateStr.isEmpty()) {
                            throw new DukeException("ME FINKZ U NED 2 PUT SUMTHIN 4 DA DATE OR TIEM");
                        } else {
                            return new EventCommand(item, dateStr);
                        }
                    }
                }

            case DONE:
                try {
                    int idx = Integer.parseInt(rest) - 1;
                    return new DoneCommand(idx);
                } catch (NumberFormatException e) {
                    throw new DukeException("U MUST ONLY PUT INDEX OV TASK LULS");
                }

            case DELETE:
                try {
                    int idx = Integer.parseInt(rest) - 1;
                    return new DeleteCommand(idx);
                } catch (NumberFormatException e) {
                    throw new DukeException("U MUST ONLY PUT INDEX OV TASK LULS");
                }

            case DEFAULT:
                return new Command();
        }

        return new Command();

    }
}
