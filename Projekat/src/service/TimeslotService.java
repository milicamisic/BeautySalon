package service;

import java.time.LocalDateTime;

import otherEntities.Timeslot;

public class TimeslotService {

	public static boolean areOverlaping(Timeslot ts1, Timeslot ts2) {
		LocalDateTime firstStart = ts1.getStartTime();
		LocalDateTime firstEnd = ts1.getEndTime();
		LocalDateTime secondStart = ts2.getStartTime();
		LocalDateTime secondEnd = ts2.getEndTime();

        return !(firstStart.isAfter(secondEnd) || firstEnd.isBefore(secondStart));
		
	}
}
