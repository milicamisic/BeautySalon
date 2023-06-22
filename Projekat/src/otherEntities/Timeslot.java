package otherEntities;

import java.time.LocalDateTime;

public class Timeslot {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public Timeslot(LocalDateTime dt1, LocalDateTime dt2) {
		super();
		this.startTime = dt1;
		this.endTime = dt2;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Timeslot [startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	public String toLine() {
		return startTime.toString() + ";" + endTime.toString();
	}
}
