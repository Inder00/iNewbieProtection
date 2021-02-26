package pl.inder00.newbieprotection.utils;

public class TimeUtil {
	
	public static long addTime(int seconds){
		return getTime()+seconds*1000L;
	}
	
	public static long getTime(){
		return System.currentTimeMillis();
	}
    public static String convertTime(int input) {
    	int numberOfDays;
    	int numberOfHours;
    	int numberOfMinutes;
    	int numberOfSeconds;

    	numberOfDays = input / 86400;
    	numberOfHours = (input % 86400 ) / 3600;
    	numberOfMinutes = ((input % 86400 ) % 3600 ) / 60;
    	numberOfSeconds = ((input % 86400 ) % 3600 ) % 60;
    	
    	String output = "";
    	if(numberOfDays > 0) output = numberOfDays+"d. ";
    	if(numberOfHours > 0) output = output+numberOfHours+"h. ";
    	if(numberOfMinutes > 0) output = output+numberOfMinutes+"m. ";
    	output = output+numberOfSeconds+"s ";
    	
    	return output;
    }
	public static int fromTime(long start){
		return (int) ((start-getTime())/1000L);
	}

}

