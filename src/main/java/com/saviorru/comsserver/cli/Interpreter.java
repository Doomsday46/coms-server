package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.Command;

import java.io.InputStream;

public interface Interpreter {
    void parse(InputStream in);
}
