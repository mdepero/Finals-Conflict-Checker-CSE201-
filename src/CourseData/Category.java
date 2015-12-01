package CourseData;

import java.util.ArrayList;

import Main.Constants;

public class Category {

	public ArrayList<Class> classesInThisCategory;

	ArrayList<Integer> meetingDayPatterns;

	// start time inclusive, end time exclusive
	int startTime, endTime;

	// represents the time the final starts for this category.
	public int finalTime;
	public String finalDay;

	public boolean hasConflicts;

	public Category(int startTime, int endTime, int finalTime, String finalDay,
			String meetingDays) {

		hasConflicts = false;

		this.startTime = startTime;
		this.endTime = endTime;

		this.finalTime = finalTime;
		this.finalDay = finalDay;

		this.meetingDayPatterns = new ArrayList<Integer>();
		this.classesInThisCategory = new ArrayList<Class>();

		this.addMeetingPattern(meetingDays);

	}

	public Category(int startTime, int endTime, int finalTime, String finalDay) {

		this(startTime, endTime, finalTime, finalDay, "");

	}

	public boolean matches(Class c) {

		if (c.startTime >= this.startTime && c.startTime < this.endTime) {

			for (int m : this.meetingDayPatterns) {

				if (c.matchesMeetingTime(m)) {

					if (!this.classesInThisCategory.contains(c)){
						
						this.classesInThisCategory.add(c);
					}

					return true;
				}
			}

			return false;

		} else {

			return false;
		}

	}

	public void addMeetingPattern(String pattern) {

		int n = this.convertStringToBinary(pattern);

		this.meetingDayPatterns.add(n);

	}

	private int convertStringToBinary(String days) {

		int ret = 0;

		for (int i = 0; i < days.length(); i++) {

			char x = days.charAt(i);

			switch (x) {

			case 'M':
				ret = ret | 16;
				// finalDay = "Monday";
				break;
			case 'T':
				ret = ret | 8;
				// finalDay = "Tuesday";
				break;
			case 'W':
				ret = ret | 4;
				// finalDay = "Wednesday";
				break;
			case 'R':
				ret = ret | 2;
				// finalDay = "Thursay";
				break;
			case 'F':
				ret = ret | 1;
				// finalDay = "Friday";
				break;
			default:
				continue;
			}

		}

		return ret;

	}

	private String getLongDay(String day) {

		char x = day.charAt(0);

		switch (x) {

		case 'M':
			return "Monday";
		case 'T':
			return "Tuesday";
		case 'W':
			return "Wednesday";
		case 'R':
			return "Thursay";
		case 'F':
			return "Friday";
		default:
			return "INVALID INPUT";
		}

	}

	public String toString() {

		return "Final on " + getLongDay(this.finalDay) + 
				" at " + Constants.timeToString(this.finalTime);

	}

}
