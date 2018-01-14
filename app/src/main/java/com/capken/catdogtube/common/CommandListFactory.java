package com.capken.catdogtube.common;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2018/01/13..
 */

public class CommandListFactory {

    public static List<TuneCommand> create() {
        List<TuneCommand> list = new ArrayList<>();
        list.add(new TuneCommand("name1"));
        list.add(new TuneCommand("name2"));
        list.add(new TuneCommand("name3"));
        list.add(new TuneCommand("name4"));
        return list;
    }
}
