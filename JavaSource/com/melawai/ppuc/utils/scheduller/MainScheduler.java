package com.melawai.ppuc.utils.scheduller;

import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

import com.melawai.ppuc.model.User;

/**
 * TaskScheduler Spring
 * Bisa menggunakan tiga buah macam penjadwalan (lihat contoh dibawah).
 * - fixedDelay berarti task akan jalan secara periode fixed, tapi baru berjalan setelah task sebelumnya selesai
 * - fixedRate berarti task akan jalan secara periode fixed, TIDAK PEDULI task sebelumnya sudah selesai/belum
 * - cron berarti menggunakan cron expression (http://en.wikipedia.org/wiki/Cron), berjalan secara periodik, TIDAK PEDULI task sebelumnya sudah selesai/belum
 * 
		Provides a parser and evaluator for unix-like cron expressions. 
		Cron expressions provide the ability to specify complex time combinations 
		such as "At 8:00am every Monday through Friday" or "At 1:30am every last Friday of the month".
		
		Cron expressions are comprised of 6 required fields and one optional field separated by white space. 
		The fields respectively are described as follows:
		Field Name 	  	Allowed Values 	  	Allowed Special Characters
		Seconds 	  	0-59 	  			, - * /
		Minutes 	  	0-59 	  			, - * /
		Hours 	  		0-23 	  			, - * /
		Day-of-month 	1-31 	  			, - * ? / L W
		Month 	  		1-12 or JAN-DEC 	, - * /
		Day-of-Week 	1-7 or SUN-SAT 	  	, - * ? / L #
		Year (Optional) empty, 1970-2099 	, - * /
		
		The '*' character is used to specify all values. 
			For example, "*" in the minute field means "every minute".
		The '?' character is allowed for the day-of-month and day-of-week fields. 
			It is used to specify 'no specific value'. This is useful when you need to specify something in one of the two fileds, but not the other.
		The '-' character is used to specify ranges 
			For example "10-12" in the hour field means "the hours 10, 11 and 12".
		The ',' character is used to specify additional values. 
			For example "MON,WED,FRI" in the day-of-week field means "the days Monday, Wednesday, and Friday".
		The '/' character is used to specify increments. 
			For example "0/15" in the seconds field means "the seconds 0, 15, 30, and 45". 
			And "5/15" in the seconds field means "the seconds 5, 20, 35, and 50". 
			Specifying '*' before the '/' is equivalent to specifying 0 is the value to start with. 
			Essentially, for each field in the expression, there is a set of numbers that can be turned on or off. 
			For seconds and minutes, the numbers range from 0 to 59. 
			For hours 0 to 23, for days of the month 0 to 31, and for months 1 to 12. 
			The "/" character simply helps you turn on every "nth" value in the given set. 
			Thus "7/6" in the month field only turns on month "7", it does NOT mean every 6th month, please note that subtlety.
		The 'L' character is allowed for the day-of-month and day-of-week fields. 
			This character is short-hand for "last", but it has different meaning in each of the two fields. 
			For example, the value "L" in the day-of-month field means "the last day of the month" - 
			day 31 for January, day 28 for February on non-leap years. 
			If used in the day-of-week field by itself, it simply means "7" or "SAT". 
			But if used in the day-of-week field after another value, it means "the last xxx day of the month" - 
			for example "6L" means "the last friday of the month". 
			When using the 'L' option, it is important not to specify lists, or ranges of values, as you'll get confusing results.
		The 'W' character is allowed for the day-of-month field. 
			This character is used to specify the weekday (Monday-Friday) nearest the given day. 
			As an example, if you were to specify "15W" as the value for the day-of-month field, 
			the meaning is: "the nearest weekday to the 15th of the month". 
			So if the 15th is a Saturday, the trigger will fire on Friday the 14th. 
			If the 15th is a Sunday, the trigger will fire on Monday the 16th. 
			If the 15th is a Tuesday, then it will fire on Tuesday the 15th. 
			However if you specify "1W" as the value for day-of-month, and the 1st is a Saturday, 
			the trigger will fire on Monday the 3rd, as it will not 'jump' over the boundary of a month's days. 
			The 'W' character can only be specified when the day-of-month is a single day, not a range or list of days.
		
		The 'L' and 'W' characters can also be combined for the day-of-month expression to yield 'LW', which translates to "last weekday of the month".
		The '#' character is allowed for the day-of-week field. 
			This character is used to specify "the nth" XXX day of the month. 
			For example, the value of "6#3" in the day-of-week field means the third Friday of the month 
			(day 6 = Friday and "#3" = the 3rd one in the month). 
			Other examples: "2#1" = the first Monday of the month and "4#5" = the fifth Wednesday of the month. 
			Note that if you specify "#5" and there is not 5 of the given day-of-week in the month, then no firing will occur that month.
		
		The legal characters and the names of months and days of the week are not case sensitive. 
 * 
 * @author BERTHO RAFITYA IWASURYA
 * 
 */
@Component //@Component berarti otomatis register sebagai bean, tanpa perlu didefinisikan di spring xml
public class MainScheduler {

	private static Logger logger = Logger.getLogger(MainScheduler.class);
	
	@Autowired
	private Properties props;
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	@Scheduled(fixedDelay=10000) //tiap 10 detik, baru akan berjalan setelah task sebelumnya selesai 
	public void tiapSepuluhDetik() {
		logger.debug("=== TIAP 10 DETIK (fixedDelay) === " + new Date());

//		remove this, untuk test session replication saja
		System.out.println("CURRENTLY LOGGED-IN USER:");
		
		
		for(int i=0;i < sessionRegistry.getAllPrincipals().size();i++) {
			User user=(User) sessionRegistry.getAllPrincipals().get(i);
			
			System.out.print("- Username: ");
			System.out.print(user.getUser_name());
		}		
	}

	@Scheduled(fixedRate=20000) //tiap 20 detik, akan selalu jalan, tidak peduli task sebelumnya selesai/belum 
	public void tiapDuaPuluhDetik() {
		//logger.debug("=== TIAP 20 DETIK (fixedRate) === " + new Date());
	}

	@Scheduled(cron="${scheduller.satumenit}") //tiap 1 menit, akan selalu jalan, tidak peduli task sebelumnya selesai/belum
	public void tiapSatuMenit() {
		logger.debug("=== TIAP 1 MENIT (cron) === " + new Date());		
		System.out.println("=== TIAP 1 MENIT (cron) === " + new Date());
	}
	
	

}