package com.saviorru.comsserver.domain;

public class TimeSettings {
    private Integer allowedHourStart;
    private Integer allowedHourEnd;
    private Integer dateHourOffset;


    public TimeSettings()  {
        this.allowedHourStart = 10;
        this.allowedHourEnd = 20;
        this.dateHourOffset = 12;
    }
    public TimeSettings(Integer allowedHourStart, Integer allowedHourEnd, Integer dateHourOffset) throws Exception {
        if (allowedHourStart == null || allowedHourEnd == null || dateHourOffset == null) throw new NullPointerException();
        if (dateHourOffset < 0) throw new Exception("Time offset cannot be below zero");
        if (allowedHourEnd <= allowedHourStart) throw new Exception("End of allowed time cannot be lower or equal to start time");
        if ((allowedHourEnd > 23) || (allowedHourEnd < 0) || (allowedHourStart > 23) || (allowedHourStart < 0))
            throw new Exception("Bad hour values");
        this.allowedHourStart = allowedHourStart;
        this.allowedHourEnd = allowedHourEnd;
        this.dateHourOffset = dateHourOffset;
    }


    public Integer getAllowedHourStart() {
        return allowedHourStart;
    }

    public Integer getAllowedHourEnd() {
        return allowedHourEnd;
    }

    public Integer getDateHourOffset() {
        return dateHourOffset;
    }
}
