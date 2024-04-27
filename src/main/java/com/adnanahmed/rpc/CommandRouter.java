package com.adnanahmed.rpc;

import java.util.List;

public class CommandRouter {
    public List<Command> commands;

    public CommandRouter(List<Command> commands) {
        this.commands = commands;
    }

    public Response handle(Request request) throws CommandNotFoundException, InvalidRequestException {
        Command cmd = this.findCommand(request);

        return cmd.handle(request);
    }

    private Command findCommand(Request request) throws CommandNotFoundException{
        for(Command cmd : this.commands) {
            if (request.commandIdentifier.toLowerCase().equalsIgnoreCase(cmd.getIdentifier())) {
                return cmd;
            }
        }

        throw new CommandNotFoundException();
    }
}
