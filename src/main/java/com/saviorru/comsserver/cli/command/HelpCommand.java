package com.saviorru.comsserver.cli.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HelpCommand implements Command {

    private Map<String, CommandInfo> commandsMap;
    private List<String> commandNameList;

    public HelpCommand(Map<String, CommandInfo> commandsMap) {
        this.commandsMap = commandsMap;
        this.commandNameList = new ArrayList<>();
        fillCommandNameList(commandsMap);
    }

    private void fillCommandNameList(Map<String, CommandInfo> commandsMap) {
        for (Map.Entry entry : commandsMap.entrySet()) {
            commandNameList.add(entry.getKey().toString());
        }
    }

    @Override
    public Boolean execute() {
        boolean flag = false;
        for (String name : commandNameList) {
            System.out.println("Команда: " + commandsMap.get(name).getCommand() + " , " + "Формат ввода: " + commandsMap.get(name).getHelp());
            flag = true;
        }
        return flag;
    }

}
